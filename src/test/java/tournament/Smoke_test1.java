package tournament;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScreenOrientation;
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
import support.Recovery;
import support.UserName;

public class Smoke_test1 extends Recovery { //Used this class to test the stage environment.
	
	ExtentReports rep = ExtentManager.initializeRepObject();
	Helper support = new Helper();
	
	String sPassword = "Quicken@01";
	String sDataset ="";

	public String getUsername_basedOnEnv() throws Exception{
		
		UserName r = new UserName();
		r.stage_ios = "newuserautomation_ios++@quicken.com";
		r.stage_android = "newuserautomation_android++@quicken.com";
		r.prod_ios = "ios2_automation@mailinator.com";
		r.prod_android = "android2_automation@mailinator.com";
		return r.getUserName();	
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
	
	@Test(priority = 0, enabled = false)
	public void NUF_Test1() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
	
		String sUsername = getUsername_basedOnEnv();
		String sAcct= "auto_"+new SimpleDateFormat("HH-mm-ss").format(new Date());
		
		WelcomePage w = new WelcomePage();
		w.setEnvironment(h.getEnv());
		
		SignInPage signIn = new SignInPage();
		signIn.signIn(sUsername, sPassword, sDataset);
	
	}
	
	@Test(priority = 1, enabled = false)
	public void verifyAccountTest1() throws Exception {
		
		//ExtentTest quickenTest = Recovery.quickenTest;
		String acctToVerify = "AllReconciledTransactions";
		Helper helper = new Helper();
		SoftAssert sa = new SoftAssert();
		
		WelcomePage w = new WelcomePage();
		
		SignInPage signIn = new SignInPage();
		signIn.signIn();
		Thread.sleep(10000);
		//Engine.ad.rotate(ScreenOrientation.LANDSCAPE);
		//Engine.getDriver().rotate(ScreenOrientation.LANDSCAPE);
	    
		Thread.sleep(4000);
	    OverviewPage op = new OverviewPage();
	    op.navigateToAcctList();
	    
	   	sa.assertAll(); 
	}
	
	@Test(priority = 0)
	public void verifyAccountTest() throws Exception {
		
		//ExtentTest quickenTest = Recovery.quickenTest;
		String acctToVerify = "AllReconciledTransactions";
		Helper helper = new Helper();
		SoftAssert sa = new SoftAssert();
		
		WelcomePage w = new WelcomePage();
		
		SignInPage signIn = new SignInPage();
		signIn.signIn();
	    
	    OverviewPage op = new OverviewPage();
	    op.navigateToAcctList();
	    
	    // verify if checking account Exists or not
	    BankingAndCreditCardPage acctListPage = new BankingAndCreditCardPage();
		
		if (acctListPage.getAccount(acctToVerify).getText().replace("Account Name: ", "").trim().equals(acctToVerify))
			Commentary.log(sa, LogStatus.INFO,"Checking account exists");
		else
			Commentary.log(sa, LogStatus.FAIL,"Checking account verification failed");
		
		// tap on the account
	   	acctListPage.getAccount(acctToVerify).click();
	   	Thread.sleep(2000);
	   	
	   	// verify account name on the transactionsPage
	   	TransactionsPage txnPage = new TransactionsPage();
	   	if (txnPage.verifyAccount(acctToVerify) == true)
	   		Commentary.log(sa, LogStatus.INFO,"Account name verification on Transaction page Pass.");
	   	else
	   		Commentary.log(sa, LogStatus.FAIL,"Account name verification on Transaction page failed.");
	   	
	   	// get back to overview screen
	   	/*Engine.ad.navigate().back();
	   	helper.waitForRefresh(2000);
	   	acctListPage.backButton.click();*/
	   	//txnPage.navigateBackToDashboard();
	   	sa.assertAll(); 
	}
	
	@Test(priority = 1)
	public void settingsPageTest() throws Exception{
		
		//ExtentTest quickenTest = Recovery.quickenTest;
		String userID = support.getUsername();//"kalyan.grandhi@quicken.com";
		String cloudName= support.getDatasetName();//"Transaction_Status";
		SoftAssert sa = new SoftAssert();
	
		WelcomePage w = new WelcomePage();
		
		/*SignInPage signIn = new SignInPage();
		signIn.signIn();*/
		
		OverviewPage op = new OverviewPage();
		op.hambergerIcon.click();
		support.waitForRefresh(2000);
		
		SettingsPage settingsPage = new SettingsPage();
		
		/*
		if (settingsPage.verifyQuickenID(userID))
			Commentary.log(sa, LogStatus.INFO,"PASS: QuickenID ["+userID+"] verified successfully!");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: QuickenID ["+userID+"] verification Failed.");
		
		if (settingsPage.verifyCloudAccountName(cloudName))
			Commentary.log(sa, LogStatus.INFO,"PASS: CloudName ["+cloudName+"] verified successfully!");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: CloudName ["+cloudName+"] verification Failed.");
		
		try {
			System.out.println("1*******");
			System.out.println(settingsPage.passcode.getText());
			System.out.println("1*******");
			System.out.println("");
		}
		catch (Exception e) {
			System.out.println("tttttttttttttt "+e.getMessage());
		}
		
		try {
			System.out.println("*******");
			System.out.println(Engine.ad.findElement(By.xpath("//android.widget.TextView[normalize-space(@text)='Passcode']")).getText());
			System.out.println("*******");
		}
		catch (Exception e) {
			System.out.println("uuuuuuuuuuuu "+e.getMessage());
		}
		
		// verify other options on settings page
		if (Verify.objExists(settingsPage.passcode))
			Commentary.log(sa, LogStatus.INFO,"Passcode verified successfully!");
		else
			Commentary.log(sa, LogStatus.FAIL,"Passcode verification Failed.");
		
		if (Verify.objExists(settingsPage.helpAndLegal))
			Commentary.log(sa, LogStatus.INFO,"help verified successfully!");
		else
			Commentary.log(sa, LogStatus.FAIL,"help verification Failed.");
		
		if (Verify.objExists(settingsPage.accounts))
			Commentary.log(sa, LogStatus.INFO,"accounts verified successfully!");
		else
			Commentary.log(sa, LogStatus.FAIL,"accounts verification Failed.");
		
		if (Verify.objExists(settingsPage.logout))
			Commentary.log(sa, LogStatus.INFO,"logout verified successfully!");
		else
			Commentary.log(sa, LogStatus.FAIL,"logout verification Failed.");*/
		
		sa.assertAll();	
	}
	
	@Test(priority = 2)
	public void RecentTransactionsTest() throws Exception{
		
		/*WelcomePage w = new WelcomePage();
		w.setEnvironment("stage");*/
		
		/*SignInPage signIn = new SignInPage();
		signIn.signIn();*/
		SoftAssert sa = new SoftAssert();
		
		// overview screen
		OverviewPage o = new OverviewPage();
		
		o.tapOnRecentTransactionsCard();
		
		AllAccountsPage aap = new AllAccountsPage();
		if (Verify.objExists(aap.textAllTransactions))
			Commentary.log(sa, LogStatus.INFO,"RecentTransactions card tap > All transactions screen got dispalyed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"RecentTransactions card tap > All transactions screen did not appear.");
		
		aap.navigateBackToDashboard();
		
		if (Verify.objExists(o.hambergerIcon))
			Commentary.log(sa, LogStatus.INFO,"RecentTransactions card, back button tap > Overview screen got dispalyed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"RecentTransactions card, back button tap > Overview screen did not appear.");
		
		sa.assertAll();
	}
	
	@Test(priority = 3)
	public void TrendingCategoriesTest() throws Exception{
		
		/*WelcomePage w = new WelcomePage();
		w.setEnvironment("stage");
		
		SignInPage signIn = new SignInPage();
		signIn.signIn();*/
		SoftAssert sa = new SoftAssert();
		
		
		// overview screen
		OverviewPage o = new OverviewPage();
		o.tapOnTrendingCard();
		
		SpendingTrendPage st = new SpendingTrendPage();
		if (Verify.objExists(st.spendingTrendHeader))
			Commentary.log(sa, LogStatus.INFO,"SpendingTrend card tap >SpendingTrend screen got dispalyed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"SpendingTrend card tap > SpendingTrend screen did not appear.");
		
		st.navigateBackToDashboard();
		
		if (Verify.objExists(o.hambergerIcon))
			Commentary.log(sa, LogStatus.INFO,"SpendingTrend card, back button tap > Overview screen got dispalyed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"SpendingTrend card, back button tap > Overview screen did not appear.");	
		
		sa.assertAll();
	}
	
	@Test(priority = 4)
	public void TransactionwSummaryTest() throws Exception{
		
		/*WelcomePage w = new WelcomePage();
		w.setEnvironment("stage");*/
		
		/*SignInPage signIn = new SignInPage();
		signIn.signIn();*/
		SoftAssert sa = new SoftAssert();
		
		// overview screen
		OverviewPage o = new OverviewPage();
		o.tapOnTransactionSummaryCard();
		
		TransactionSummaryPage cashflow = new TransactionSummaryPage();
		
		if (Verify.objExists(cashflow.transactionSummaryHeader))
			System.out.println("pass");
			//Commentary.log(sa, LogStatus.INFO,"cashflow card tap >Transaction summary screen got dispalyed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"cashflow card tap > cashflow/Transaction summary screen did not appear.");
		
		cashflow.navigateBackToDashboard();
		
		System.out.println("back doneeeeeeee");
		
		if (Verify.objExists(o.hambergerIcon))
			Commentary.log(sa, LogStatus.INFO,"cashflow card, back button tap > Overview screen got dispalyed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"cashflow card, back button tap > Overview screen did not appear.");	
		sa.assertAll();
			
	}
	
	@Test(priority = 5)
	public void SpendingOverTimeTest() throws Exception{
		
		/*WelcomePage w = new WelcomePage();
		w.setEnvironment("stage");*/
		SoftAssert sa = new SoftAssert();
		
		DateFormat date =  new SimpleDateFormat("MMM");
		Date date1 = new Date();
		//System.out.println(date.format(date1).toString());
		
		String currentMonthSpending = "Total Spending: "+date.format(date1).toString();
		
		/*SignInPage signIn = new SignInPage();
		signIn.signIn();*/
		
		// overview screen
		OverviewPage o = new OverviewPage();
		o.tapOnSpendingOverTimeCard();
		
		SpendingOverTimePage sot = new SpendingOverTimePage();
		
		if (Verify.objExists(sot.spendingOverTimeHeader))
			Commentary.log(sa, LogStatus.INFO,"SpendingOverTimeCard card tap >SpendingOverTime screen got dispalyed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"SpendingOverTimeCard tap > SpendingOverTimeCard screen did not appear.");
		
		if (Verify.objExists(sot.totalSpendingCurrentMonth))
			Commentary.log(sa, LogStatus.INFO,"Total Spending text got displayed on SpendingOverTimeCard.");
		else
			Commentary.log(sa, LogStatus.FAIL,"Total Spending text did not get displayed on SpendingOverTimeCard.");
		
		if (sot.totalSpendingCurrentMonth.getText().equals(currentMonthSpending))
			Commentary.log(sa, LogStatus.INFO,"Total Spending text for the current month ["+currentMonthSpending+"] got displayed on SpendingOverTimeCard.");
		else
			Commentary.log(sa, LogStatus.INFO,"Text ["+currentMonthSpending+"] not displayed on SpendingOverTimeCard.");
		
		sot.navigateBackToDashboard();
		
		if (Verify.objExists(o.hambergerIcon))
			Commentary.log(sa, LogStatus.INFO,"cashflow card, back button tap > Overview screen got dispalyed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"cashflow card, back button tap > Overview screen did not appear.");	
		
		sa.assertAll();
			
	}
	
	@Test(priority = 6)
	public void NetIncomeOverTimeTest() throws Exception{
		
		/*WelcomePage w = new WelcomePage();
		w.setEnvironment("stage");*/
		SoftAssert sa = new SoftAssert();
		
		DateFormat date =  new SimpleDateFormat("MMM");
		Date date1 = new Date();
		//System.out.println(date.format(date1).toString());
		
		String currentMonthIncome = "Net Income: "+date.format(date1).toString();
		
		/*SignInPage signIn = new SignInPage();
		signIn.signIn();*/
		
		// overview screen
		OverviewPage o = new OverviewPage();
		
		o.tapOnNetIncomeOverTimeCard();
		
		NetIncomeOverTimePage not = new NetIncomeOverTimePage();
		
		if (Verify.objExists(not.netIncomeOverTimeHeader))
			Commentary.log(sa, LogStatus.INFO,"NetIncomeOverTimeCard card tap >NetIncomeOverTime screen got dispalyed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"NetIncomeOverTimeCard tap > NetIncomeOverTime screen did not appear.");
		
		if (Verify.objExists(not.netIncomeCurrentMonth))
			Commentary.log(sa, LogStatus.INFO,"Net Income text got displayed on NetIncomeOverTime screen.");
		else
			Commentary.log(sa, LogStatus.FAIL,"Net Income text did not appear on NetIncomeOverTime screen.");
		
		if (not.netIncomeCurrentMonth.getText().equals(currentMonthIncome))
			Commentary.log(sa, LogStatus.INFO,"Net Income text for the current month ["+currentMonthIncome+"] got displayed on NetIncomeOverTimeCard.");
		else
			Commentary.log(sa, LogStatus.INFO,"Text ["+currentMonthIncome+"] not displayed on NetIncomeOverTimeCard.");
		
		/*
		not.navigateBackToDashboard();
			
		if (Verify.objExists(o.hambergerIcon))
			Commentary.log(sa, LogStatus.INFO,"cashflow card, back button tap > Overview screen got dispalyed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"cashflow card, back button tap > Overview screen did not appear.");	
		*/
		sa.assertAll();	
	}
	
	@Test(priority = 7)
	public void BudgetTest() throws Exception{
	
		SoftAssert sa = new SoftAssert();
		
		/*SignInPage signIn = new SignInPage();
		signIn.signIn();*/
		
		// overview screen
		OverviewPage o = new OverviewPage();
		o.tapOnBudgetCard();
		
		BudgetsPage b = new BudgetsPage();
		
		if (b.verify_budgetHeader())
			Commentary.log(sa, LogStatus.INFO,"Budgets card tap >Budgets screen got dispalyed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"Budgets card tap >Budgets screen did not appear.");
		
		if (Verify.objExists(b.personalExpenses))
			Commentary.log(sa, LogStatus.INFO,"Budgets card, Personal Expenses group got dispalyed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"Budgets card, Personal Expenses group did not appear.");
		
		sa.assertAll();
		
	}
	
	@Test(priority = 8)
	public void InvestingTest() throws Exception{
	
		SoftAssert sa = new SoftAssert();
		
		/*SignInPage signIn = new SignInPage();
		signIn.signIn();*/
		
		// overview screen
		OverviewPage o = new OverviewPage();
		o.tapOnInvestingCard();
		
		InvestingPage i = new InvestingPage();
		
		if (Verify.objExists(i.investingHeader))
			Commentary.log(sa, LogStatus.INFO,"Investing card tap >Investments screen got dispalyed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"Investing card tap >Investing screen did not appear.");
		
		if (Verify.objExists(i.accountsTab))
			Commentary.log(sa, LogStatus.INFO,"Investing card > Accounts Tab got dispalyed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"Investing card > Accounts Tab did not appear.");
		
		if (Verify.objExists(i.holdingsTab))
			Commentary.log(sa, LogStatus.INFO,"Investing card > Holdings Tab got dispalyed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"Investing card > Holdings Tab did not appear.");
		
		if (Verify.objExists(i.watchlistTab))
			Commentary.log(sa, LogStatus.INFO,"Investing card > Watchlist Tab got dispalyed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"Investing card > Watchlist Tab did not appear.");
		
		i.navigateBackToDashboard();
		
		if (Verify.objExists(o.hambergerIcon))
			Commentary.log(sa, LogStatus.INFO,"Investing card, back button tap > Overview screen got dispalyed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"Investing card, back button tap > Overview screen did not appear.");	
	
		sa.assertAll();	
	}
	
}
