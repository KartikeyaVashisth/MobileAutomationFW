package tournament;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import dugout.AllAccountsPage;
import dugout.BankingAndCreditCardPage;
import dugout.NetIncomeOverTimePage;
import dugout.OverviewPage;
import dugout.SettingsPage;
import dugout.SignInPage;
import dugout.SpendingOverTimePage;
import dugout.SpendingTrendPage;
import dugout.TransactionSummaryPage;
import dugout.TransactionsPage;
import dugout.WelcomePage;
import io.appium.java_client.MobileElement;
import referee.ExtentManager;
import referee.Verify;
import support.Engine;
import support.Helper;
import support.Recovery;

public class Smoke_test extends Recovery {
	
	ExtentReports rep = ExtentManager.initializeRepObject();
	Helper support = new Helper();
	
	@Test(priority = 0)
	public void verifyAccountTest() throws Exception {
		
		//ExtentTest quickenTest = Recovery.quickenTest;
		String acctToVerify = "AllReconciledTransactions";
		Helper helper = new Helper();
		
		WelcomePage w = new WelcomePage();
		
		SignInPage signIn = new SignInPage();
		signIn.signIn();
	    
	    OverviewPage op = new OverviewPage();
	    op.navigateToAcctList();
	    
	   
	    
	    // verify if checking account Exists or not
	    BankingAndCreditCardPage acctListPage = new BankingAndCreditCardPage();
		
		if (acctListPage.getAccount(acctToVerify).getText().trim().equals(acctToVerify))
			quickenTest.log(LogStatus.INFO,"Checking account exists");
		else
			quickenTest.log(LogStatus.FAIL,"Checking account verification failed");
		
		// tap on the account
	   	acctListPage.getAccount(acctToVerify).click();
	   	Thread.sleep(2000);
	   	
	   	// verify account name on the transactionsPage
	   	TransactionsPage txnPage = new TransactionsPage();
	   	if (txnPage.verifyAccount(acctToVerify) == true)
	   		quickenTest.log(LogStatus.INFO,"Account name verification on Transaction page Pass.");
	   	else
	   		quickenTest.log(LogStatus.FAIL,"Account name verification on Transaction page failed.");
	   	
	   	// get back to overview screen
	   	/*Engine.ad.navigate().back();
	   	helper.waitForRefresh(2000);
	   	acctListPage.backButton.click();*/
	   	txnPage.navigateBackToDashboard();
	   	
	    
	}
	
	@Test(priority = 1)
	public void settingsPageTest() throws Exception{
		
		//ExtentTest quickenTest = Recovery.quickenTest;
		String userID = support.getUsername();//"kalyan.grandhi@quicken.com";
		String cloudName= support.getDatasetName();//"Transaction_Status";
		
		
		WelcomePage w = new WelcomePage();
		
		/*SignInPage signIn = new SignInPage();
		signIn.signIn();*/
		
		
		OverviewPage op = new OverviewPage();
		op.hambergerIcon.click();
		support.waitForRefresh(2000);
		
		SettingsPage settingsPage = new SettingsPage();
		
		if (settingsPage.verifyQuickenID(userID))
			quickenTest.log(LogStatus.INFO,"PASS: QuickenID ["+userID+"] verified successfully!");
		else
			quickenTest.log(LogStatus.FAIL,"FAIL: QuickenID ["+userID+"] verification Failed.");
		
		if (settingsPage.verifyCloudAccountName(cloudName))
			quickenTest.log(LogStatus.INFO,"PASS: QuickenID ["+cloudName+"] verified successfully!");
		else
			quickenTest.log(LogStatus.FAIL,"FAIL: QuickenID ["+cloudName+"] verification Failed.");
		
		// verify other options on settings page
		if (Verify.objExists(settingsPage.passcode))
			quickenTest.log(LogStatus.INFO,"Passcode verified successfully!");
		else
			quickenTest.log(LogStatus.FAIL,"Passcode verification Failed.");
		
		if (Verify.objExists(settingsPage.helpAndLegal))
			quickenTest.log(LogStatus.INFO,"help verified successfully!");
		else
			quickenTest.log(LogStatus.FAIL,"help verification Failed.");
		
		if (Verify.objExists(settingsPage.accounts))
			quickenTest.log(LogStatus.INFO,"accounts verified successfully!");
		else
			quickenTest.log(LogStatus.FAIL,"accounts verification Failed.");
		
		if (Verify.objExists(settingsPage.logout))
			quickenTest.log(LogStatus.INFO,"logout verified successfully!");
		else
			quickenTest.log(LogStatus.FAIL,"logout verification Failed.");
		
	}
	
	@Test(priority = 2)
	public void RecentTransactionsTest() throws Exception{
		
		/*WelcomePage w = new WelcomePage();
		w.setEnvironment("stage");*/
		
		/*SignInPage signIn = new SignInPage();
		signIn.signIn();*/
		
		
		// overview screen
		OverviewPage o = new OverviewPage();
		
		o.tapOnRecentTransactionsCard();
		
		AllAccountsPage aap = new AllAccountsPage();
		if (Verify.objExists(aap.textAllTransactions))
			quickenTest.log(LogStatus.INFO,"RecentTransactions card tap > All transactions screen got dispalyed.");
		else
			quickenTest.log(LogStatus.FAIL,"RecentTransactions card tap > All transactions screen did not appear.");
		
		aap.navigateBackToDashboard();
		
		if (Verify.objExists(o.hambergerIcon))
			quickenTest.log(LogStatus.INFO,"RecentTransactions card, back button tap > Overview screen got dispalyed.");
		else
			quickenTest.log(LogStatus.FAIL,"RecentTransactions card, back button tap > Overview screen did not appear.");	
	}
	
	@Test(priority = 3)
	public void TrendingCategoriesTest() throws Exception{
		
		/*WelcomePage w = new WelcomePage();
		w.setEnvironment("stage");
		
		SignInPage signIn = new SignInPage();
		signIn.signIn();*/
		
		
		// overview screen
		OverviewPage o = new OverviewPage();
		o.tapOnTrendingCard();
		
		SpendingTrendPage st = new SpendingTrendPage();
		if (Verify.objExists(st.spendingTrendHeader))
			quickenTest.log(LogStatus.INFO,"SpendingTrend card tap >SpendingTrend screen got dispalyed.");
		else
			quickenTest.log(LogStatus.FAIL,"SpendingTrend card tap > SpendingTrend screen did not appear.");
		
		st.navigateBackToDashboard();
		
		if (Verify.objExists(o.hambergerIcon))
			quickenTest.log(LogStatus.INFO,"SpendingTrend card, back button tap > Overview screen got dispalyed.");
		else
			quickenTest.log(LogStatus.FAIL,"SpendingTrend card, back button tap > Overview screen did not appear.");	
	}
	
	@Test(priority = 4)
	public void TransactionwSummaryTest() throws Exception{
		
		/*WelcomePage w = new WelcomePage();
		w.setEnvironment("stage");*/
		
		/*SignInPage signIn = new SignInPage();
		signIn.signIn();*/
		
		
		// overview screen
		OverviewPage o = new OverviewPage();
		o.tapOnTransactionSummaryCard();
		
		TransactionSummaryPage cashflow = new TransactionSummaryPage();
		
		if (Verify.objExists(cashflow.transactionSummaryHeader))
			quickenTest.log(LogStatus.INFO,"cashflow card tap >Transaction summary screen got dispalyed.");
		else
			quickenTest.log(LogStatus.FAIL,"cashflow card tap > cashflow/Transaction summary screen did not appear.");
		
		cashflow.navigateBackToDashboard();
		
		System.out.println("back doneeeeeeee");
		
		if (Verify.objExists(o.hambergerIcon))
			quickenTest.log(LogStatus.INFO,"cashflow card, back button tap > Overview screen got dispalyed.");
		else
			quickenTest.log(LogStatus.FAIL,"cashflow card, back button tap > Overview screen did not appear.");	
			
	}
	
	@Test(priority = 5)
	public void SpendingOverTimeTest() throws Exception{
		
		/*WelcomePage w = new WelcomePage();
		w.setEnvironment("stage");*/
		
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
			quickenTest.log(LogStatus.INFO,"SpendingOverTimeCard card tap >SpendingOverTime screen got dispalyed.");
		else
			quickenTest.log(LogStatus.FAIL,"SpendingOverTimeCard tap > SpendingOverTimeCard screen did not appear.");
		
		if (Verify.objExists(sot.totalSpendingCurrentMonth))
			quickenTest.log(LogStatus.INFO,"Total Spending text got displayed on SpendingOverTimeCard.");
		else
			quickenTest.log(LogStatus.FAIL,"Total Spending text did not get displayed on SpendingOverTimeCard.");
		
		if (sot.totalSpendingCurrentMonth.getText().equals(currentMonthSpending))
			quickenTest.log(LogStatus.INFO,"Total Spending text for the current month ["+currentMonthSpending+"] got displayed on SpendingOverTimeCard.");
		else
			quickenTest.log(LogStatus.INFO,"Text ["+currentMonthSpending+"] not displayed on SpendingOverTimeCard.");
		
		
		sot.navigateBackToDashboard();
		
		
		if (Verify.objExists(o.hambergerIcon))
			quickenTest.log(LogStatus.INFO,"cashflow card, back button tap > Overview screen got dispalyed.");
		else
			quickenTest.log(LogStatus.FAIL,"cashflow card, back button tap > Overview screen did not appear.");	
			
	}
	
	
	@Test(priority = 6)
	public void NetIncomeOverTimeTest() throws Exception{
		
		/*WelcomePage w = new WelcomePage();
		w.setEnvironment("stage");*/
		
		DateFormat date =  new SimpleDateFormat("MMM");
		Date date1 = new Date();
		//System.out.println(date.format(date1).toString());
		
		String currentMonthIncome = "Net Income: "+date.format(date1).toString();
		
		SignInPage signIn = new SignInPage();
		signIn.signIn();
		
		
		// overview screen
		OverviewPage o = new OverviewPage();
		
		o.tapOnNetIncomeOverTimeCard();
		
		NetIncomeOverTimePage not = new NetIncomeOverTimePage();
		
		if (Verify.objExists(not.netIncomeOverTimeHeader))
			quickenTest.log(LogStatus.INFO,"NetIncomeOverTimeCard card tap >NetIncomeOverTime screen got dispalyed.");
		else
			quickenTest.log(LogStatus.FAIL,"NetIncomeOverTimeCard tap > NetIncomeOverTime screen did not appear.");
		
		if (Verify.objExists(not.netIncomeCurrentMonth))
			quickenTest.log(LogStatus.INFO,"Net Income text got displayed on NetIncomeOverTime screen.");
		else
			quickenTest.log(LogStatus.FAIL,"Net Income text did not appear on NetIncomeOverTime screen.");
		
		if (not.netIncomeCurrentMonth.getText().equals(currentMonthIncome))
			quickenTest.log(LogStatus.INFO,"Net Income text for the current month ["+currentMonthIncome+"] got displayed on NetIncomeOverTimeCard.");
		else
			quickenTest.log(LogStatus.INFO,"Text ["+currentMonthIncome+"] not displayed on NetIncomeOverTimeCard.");
		
		
		not.navigateBackToDashboard();
			
		if (Verify.objExists(o.hambergerIcon))
			quickenTest.log(LogStatus.INFO,"cashflow card, back button tap > Overview screen got dispalyed.");
		else
			quickenTest.log(LogStatus.FAIL,"cashflow card, back button tap > Overview screen did not appear.");	
			
	}
	
	
	

}
