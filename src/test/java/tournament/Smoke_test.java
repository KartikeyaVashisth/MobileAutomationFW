package tournament;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import com.saucelabs.saucerest.SauceREST;

import dugout.AllAccountsPage;
import dugout.BankingAndCreditCardPage;
import dugout.BudgetsPage;
import dugout.InvestingPage;
import dugout.NetIncomeOverTimePage;
import dugout.OverviewPage;
import dugout.SettingsPage;
import dugout.SignInPage;
import dugout.SpendingOverTimePage;
import dugout.SpendingTrendPage;
import dugout.TransactionSummaryPage;
import dugout.TransactionsPage;
import dugout.WelcomePage;
import io.appium.java_client.MobileBy;
import referee.Commentary;
import referee.ExtentManager;
import referee.Verify;
import support.Engine;
import support.Helper;
import support.Recovery;
import support.UserName;

public class Smoke_test extends Recovery {

	ExtentReports rep = ExtentManager.initializeRepObject();
	Helper support = new Helper();
	
	String sPassword_prod = "Intuit!1";
	String sDataset_prod = "Transaction_Status";
	String sPassword_stage = "Quicken@01";
	String sDataset_stage = "SmokeTest_Stage";
	
	public String getUsername_basedOnEnv() throws Exception {

		UserName un = new UserName();
		un.stage_ios = "companionregression++@stage.com";
		un.stage_android = "companionregression++@stage.com";
		un.prod_ios = "kalyan.grandhi@quicken.com";
		un.prod_android = "kalyan.grandhi@quicken.com";
		return un.getUserName();	
	}
	
	//****** This piece of code we can enable when we want to use the Testrail integration with our project . Based on test case status the status on Testrail will get update for each testcase ******	
//	@Override
//	@BeforeTest
//	@Parameters({"host","engine","test","env","RUN_ID"})
//	public void beforeTestEnter(@Optional("readFromPropertiesFile")String host, @Optional("readFromPropertiesFile")String engine, @Optional("readFromPropertiesFile")String testName, @Optional("readFromPropertiesFile")String env, @Optional("readFromPropertiesFile")String RUN_ID) throws Exception{
//		this.testRunId.set("2330");
//		super.beforeTestEnter(host, engine, testName, env, RUN_ID);
//		
//	}

	@Test(priority = 0, enabled = true)
	public void verifyAccountTest() throws Exception {

		String acctToVerify = "AllReconciledTransactions";
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		String sUsername = getUsername_basedOnEnv();
		WelcomePage w = new WelcomePage();
		w.setEnvironment(h.getEnv());

		SignInPage si = new SignInPage();
		if(h.getEnv().contentEquals("stage"))
			si.signIn(sUsername, sPassword_stage, sDataset_stage);
		else
			si.signIn(sUsername, sPassword_prod, sDataset_prod);
		
//		SignInPage signIn = new SignInPage();
//		signIn.signIn();

		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();

		BankingAndCreditCardPage acctListPage = new BankingAndCreditCardPage();

		if (acctListPage.getAccount(acctToVerify).getText().replace("Account Name: ", "").trim().equals(acctToVerify))
			Commentary.log(LogStatus.INFO,"PASS: Checking account exists.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Checking account verification failed.");

		acctListPage.getAccount(acctToVerify).click();
		Thread.sleep(2000);

		TransactionsPage txnPage = new TransactionsPage();
		if (txnPage.verifyAccount(acctToVerify) == true)
			Commentary.log(LogStatus.INFO,"PASS: Account name verification on Transaction page.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Account name verification on Transaction page.");

		sa.assertAll();

	}

	@Test(priority = 1, enabled = true)
	public void HamburgerMenuOptionsTest() throws Exception{

		String userID = support.getUsername();//"kalyan.grandhi@quicken.com";
		String cloudName= support.getDatasetName();//"Transaction_Status";
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		OverviewPage op = new OverviewPage();
		Verify.waitForObject(op.hambergerIcon, 1);
		op.hambergerIcon.click();
		support.waitForRefresh(2000);

		SettingsPage sp = new SettingsPage();
		Verify.waitForObject(sp.dashboardOption, 1);
		Verify.waitForObject(sp.versionNumber, 1);

		if (Verify.objExists(sp.dashboardOption))
			Commentary.log(LogStatus.INFO, "PASS: Dashboard option is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Dashboard option is NOT displayed.");
		
		if (Verify.objExists(sp.accountTxt))
			Commentary.log(LogStatus.INFO, "PASS: Accounts option is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Accounts option is NOT displayed.");

		if (Verify.objExists(sp.allTransactionsOption))
			Commentary.log(LogStatus.INFO, "PASS: All Transactions option is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: All Transactions option is NOT displayed.");
		
		if (Verify.objExists(sp.billsOption))
			Commentary.log(LogStatus.INFO, "PASS: Bills option is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Bills option is NOT displayed.");
		
//		if (Verify.objExists(sp.budgetsOption))
//			Commentary.log(LogStatus.INFO, "PASS: Budgets option is displayed.");
//		else
//			Commentary.log(sa, LogStatus.FAIL, "FAIL: Budgets option is NOT displayed.");
		
		if (Verify.objExists(sp.investingOption))
			Commentary.log(LogStatus.INFO, "PASS: Investments option is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Investments option is NOT displayed.");
		
		if (Verify.objExists(sp.reportsOption))
			Commentary.log(LogStatus.INFO, "PASS: Reports option is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Reports option is NOT displayed.");

		if (Verify.objExists(sp.profileOption))
			Commentary.log(LogStatus.INFO, "PASS: Profile option is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Profile option is NOT displayed.");
		
		if (Verify.objExists(sp.settingsOption))
			Commentary.log(LogStatus.INFO, "PASS: Settings option is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Settings option is NOT displayed.");
		
		if (Verify.objExists(sp.helpOption))
			Commentary.log(LogStatus.INFO, "PASS: Help option is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Help option is NOT displayed.");
		
		if (Verify.objExists(sp.datasetDDButton))
			Commentary.log(LogStatus.INFO, "PASS: Dataset DD button button is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Dataset DD button button is NOT displayed.");
		
		if (Verify.objExists(sp.versionNumber))
			Commentary.log(LogStatus.INFO, "PASS: Version number is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Version number is NOT displayed.");

		if (Verify.objExists(sp.logout))
			Commentary.log(LogStatus.INFO, "PASS: Logout button is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Logout button is NOT displayed.");

//		if (h.getEngine().equalsIgnoreCase("Android")) {
//			if (Verify.objExists(sp.FeedbackTxt))
//				Commentary.log(LogStatus.INFO, "PASS: Feedback Text is displayed.");
//			else
//				Commentary.log(sa, LogStatus.FAIL, "FAIL: Feedback Text is NOT displayed.");
//		} else {
//			Commentary.log(LogStatus.INFO, "PASS: Feedback options is not supported for IOS Simulator.");
//		}
		
		sa.assertAll();
	}

	@Test(priority = 2, enabled = true)
	public void RecentTransactionsTest() throws Exception{

		SoftAssert sa = new SoftAssert();
		OverviewPage o = new OverviewPage();

		o.tapOnRecentTransactionsCard();

		AllAccountsPage aap = new AllAccountsPage();
		Verify.waitForObject(aap.textAllTransactions, 1);
		if (Verify.objExists(aap.textAllTransactions))
			Commentary.log(LogStatus.INFO,"PASS: RecentTransactions card tap > All transactions screen got displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: RecentTransactions card tap > All transactions screen did not appear.");

		aap.navigateBackToDashboard();

		if (Verify.objExists(o.hambergerIcon))
			Commentary.log(LogStatus.INFO,"PASS: RecentTransactions card, back button tap > Overview screen got displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: RecentTransactions card, back button tap > Overview screen did not appear.");

		sa.assertAll();
	}

	@Test(priority = 3, enabled = true)
	public void TrendingCategoriesTest() throws Exception{

		SoftAssert sa = new SoftAssert();
		OverviewPage o = new OverviewPage();
		
//		o.tapOnTrendingCard();

		SpendingTrendPage st = new SpendingTrendPage();
		st.navigateToTopTrendingCategoriesCard();
		
		Verify.waitForObject(st.spendingTrendHeader, 1);
		if (Verify.objExists(st.spendingTrendHeader))
			Commentary.log(LogStatus.INFO,"PASS: SpendingTrend card tap > SpendingTrend screen got displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: SpendingTrend card tap > SpendingTrend screen did not appear.");

		st.navigateBackToDashboard();

		if (Verify.objExists(o.hambergerIcon))
			Commentary.log(LogStatus.INFO,"PASS: SpendingTrend card, back button tap > Overview screen got displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: SpendingTrend card, back button tap > Overview screen did not appear.");	

		sa.assertAll();
	}

	@Test(priority = 4, enabled = true)
	public void TransactionSummaryTest() throws Exception{

		SoftAssert sa = new SoftAssert();

		OverviewPage o = new OverviewPage();
		o.tapOnTransactionSummaryCard();

		TransactionSummaryPage cashflow = new TransactionSummaryPage();
		Verify.waitForObject(cashflow.monthlySummaryHeader, 1);
		if (Verify.objExists(cashflow.monthlySummaryHeader))
			Commentary.log(LogStatus.INFO,"PASS: Monthly summary card tap > Monthly summary screen got displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Monthly summary card tap > Monthly summary screen did not appear.");

		cashflow.navigateBackToDashboard();

		if (Verify.objExists(o.hambergerIcon))
			Commentary.log(LogStatus.INFO,"PASS: Monthly summary card, back button tap > Overview screen got displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Monthly summary card, back button tap > Overview screen did not appear.");	
		sa.assertAll();	
	}

	@Test(priority = 5, enabled = false)
	public void SpendingOverTimeTest() throws Exception{

		SoftAssert sa = new SoftAssert();

		DateFormat date =  new SimpleDateFormat("MMM");
		Date date1 = new Date();

		String currentMonthSpending = "Total Spending: "+date.format(date1).toString();

		OverviewPage o = new OverviewPage();
		o.tapOnSpendingOverTimeCard();

		SpendingOverTimePage sot = new SpendingOverTimePage();
		Verify.waitForObject(sot.spendingOverTimeHeader, 1);
		if (Verify.objExists(sot.spendingOverTimeHeader))
			Commentary.log(LogStatus.INFO,"PASS: SpendingOverTimeCard card tap > SpendingOverTime screen got displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: SpendingOverTimeCard tap > SpendingOverTimeCard screen did not appear.");
	
		if (Verify.objExists(sot.totalSpendingCurrentMonth))
			Commentary.log(LogStatus.INFO,"PASS: Total Spending text got displayed on SpendingOverTimeCard.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Total Spending text did not get displayed on SpendingOverTimeCard.");

		if (sot.totalSpendingCurrentMonth.getText().equals(currentMonthSpending))
			Commentary.log(LogStatus.INFO,"PASS: Total Spending text for the current month ["+currentMonthSpending+"] got displayed on SpendingOverTimeCard.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Text ["+currentMonthSpending+"] not displayed on SpendingOverTimeCard.");
		 
		sot.navigateBackToDashboard();

		if (Verify.objExists(o.hambergerIcon))
			Commentary.log(LogStatus.INFO,"PASS: cashflow card, back button tap > Overview screen got displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: cashflow card, back button tap > Overview screen did not appear.");	

		sa.assertAll();		
	}

	@Test(priority = 6, enabled = false)
	public void NetIncomeOverTimeTest() throws Exception{

		SoftAssert sa = new SoftAssert();

		DateFormat date =  new SimpleDateFormat("MMM");
		Date date1 = new Date();

		String currentMonthIncome = "Net Income: "+date.format(date1).toString();

		OverviewPage o = new OverviewPage();

		o.tapOnNetIncomeOverTimeCard();

		NetIncomeOverTimePage not = new NetIncomeOverTimePage();
		Verify.waitForObject(not.netIncomeOverTimeHeader, 1);
		if (Verify.objExists(not.netIncomeOverTimeHeader))
			Commentary.log(LogStatus.INFO,"PASS: NetIncomeOverTimeCard card tap > NetIncomeOverTime screen got displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: NetIncomeOverTimeCard tap > NetIncomeOverTime screen did not appear.");

		if (Verify.objExists(not.netIncomeCurrentMonth))
			Commentary.log(LogStatus.INFO,"PASS: Net Income text got displayed on NetIncomeOverTime screen.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Net Income text did not appear on NetIncomeOverTime screen.");

		if (not.netIncomeCurrentMonth.getText().equals(currentMonthIncome))
			Commentary.log(LogStatus.INFO,"PASS: Net Income text for the current month ["+currentMonthIncome+"] got displayed on NetIncomeOverTimeCard.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Text ["+currentMonthIncome+"] not displayed on NetIncomeOverTimeCard.");

		not.navigateBackToDashboard();

		if (Verify.objExists(o.hambergerIcon))
			Commentary.log(LogStatus.INFO,"PASS: cashflow card, back button tap > Overview screen got displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: cashflow card, back button tap > Overview screen did not appear.");	

		sa.assertAll();	
	}

	@Test(priority = 7, enabled = true)
	public void BudgetTest() throws Exception{

		SoftAssert sa = new SoftAssert();

		OverviewPage o = new OverviewPage();
		o.tapOnBudgetCard();

		Commentary.log(sa, LogStatus.INFO,"Budgets card got displayed.");

//		BudgetsPage b = new BudgetsPage();
//
//		if (b.verify_budgetHeader())
//			Commentary.log(sa, LogStatus.INFO,"Budgets card tap >Budgets screen got displayed.");
//		else
//			Commentary.log(sa, LogStatus.FAIL,"Budgets card tap >Budgets screen did not appear.");
//
//		if (Verify.objExists(b.personalExpenses))
//			Commentary.log(sa, LogStatus.INFO,"Budgets card, Personal Expenses group got displayed.");
//		else
//			Commentary.log(sa, LogStatus.FAIL,"Budgets card, Personal Expenses group did not appear.");

		sa.assertAll();	
	}

	@Test(priority = 8, enabled = true)
	public void InvestingTest() throws Exception{

		SoftAssert sa = new SoftAssert();
		OverviewPage o = new OverviewPage();
		
		o.tapOnInvestingCard();

		InvestingPage i = new InvestingPage();
		Verify.waitForObject(i.holdingsTab, 2);

		if (Verify.objExists(i.investingHeader))
			Commentary.log(LogStatus.INFO,"PASS: Investing card tap >Investments screen got displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Investing card tap > Investing screen did not appear.");

		if (Verify.objExists(i.accountsTab))
			Commentary.log(LogStatus.INFO,"PASS: Investing card > Accounts Tab got displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Investing card > Accounts Tab did not appear.");

		if (Verify.objExists(i.holdingsTab))
			Commentary.log(LogStatus.INFO,"PASS: Investing card > Holdings Tab got displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Investing card > Holdings Tab did not appear.");

		if (Verify.objExists(i.watchlistTab))
			Commentary.log(LogStatus.INFO,"PASS: Investing card > Watchlist Tab got displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Investing card > Watchlist Tab did not appear.");

		i.navigateBackToDashboard();

		if (Verify.objExists(o.hambergerIcon))
			Commentary.log(LogStatus.INFO,"PASS: Investing card, back button tap > Overview screen got displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Investing card, back button tap > Overview screen did not appear.");	

		sa.assertAll();	
	}
}
