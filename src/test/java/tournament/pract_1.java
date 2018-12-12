package tournament;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import dugout.AllAccountsPage;
import dugout.BankingAndCreditCardPage;
import dugout.InvestingPage;
import dugout.MFApage;
import dugout.OverviewPage;
import dugout.SettingsPage;
import dugout.SignInPage;
import dugout.SpendingTrendPage;
import dugout.TransactionSummaryPage;
import dugout.TransactionsPage;
import dugout.WelcomePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import referee.ErrorUtil;
import referee.ExtentManager;
import referee.Verify;
import support.Engine;
import support.Helper;
import support.Recovery;

public class pract_1 extends Recovery {
	
	ExtentReports rep = ExtentManager.initializeRepObject();
	
	
	/*@Test
	public void tt1() {
		ExtentTest quickenTest = Recovery.quickenTest;
		System.out.println("in test case.....");
		quickenTest.log(LogStatus.INFO,"tt1");
		Assert.assertEquals(true, false);
	}
	
	@Test
	public void tt2() {
		ExtentTest quickenTest = Recovery.quickenTest;
		System.out.println("in test case.....");
		quickenTest.log(LogStatus.INFO,"tt2");
		Assert.assertEquals(true, true);
		
		try{
			Assert.assertEquals(true, false);	
			
		}
		catch (Throwable t){
			System.out.println("ddddddddddddddddd");
			System.out.println(t.getMessage());
			ErrorUtil.addVerificationFailure(t);
		}
	}*/
	
	
	@Test(priority = 0)
	public void verifyAccount() throws Exception {
		
		//ExtentTest quickenTest = Recovery.quickenTest;
		String acctToVerify = "AllReconciledTransactions";
		Helper helper = new Helper();
		
		WelcomePage w = new WelcomePage();
		
		SignInPage signIn = new SignInPage();
		signIn.signIn();
	    
	    OverviewPage op = new OverviewPage();
	    op.accountsCard.click();
	    helper.waitForRefresh(3000);
	    
	   
	    
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
	   	Engine.ad.navigate().back();
	   	helper.waitForRefresh(2000);
	   	acctListPage.backButton.click();
	   	
	    
	}
	
	@Test(priority = 1)
	public void settingsPage() throws Exception{
		
		//ExtentTest quickenTest = Recovery.quickenTest;
		String userID = "kalyan.grandhi@quicken.com";
		String cloudName="Transaction_Status";
		Helper helper = new Helper();
		
		WelcomePage w = new WelcomePage();
		
		SignInPage signIn = new SignInPage();
		signIn.signIn();
		
		
		OverviewPage op = new OverviewPage();
		op.hambergerIcon.click();
		helper.waitForRefresh(2000);
		
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
	
	@Test
	public void RecentTransactions() throws Exception{
		
		/*WelcomePage w = new WelcomePage();
		w.setEnvironment("stage");*/
		
		SignInPage signIn = new SignInPage();
		signIn.signIn();
		
		
		// overview screen
		OverviewPage o = new OverviewPage();
		//Thread.sleep(12000);
		
		o.tapOnRecentTransactionsCard();
		//o.recentTransactionsCard.click();
		//Thread.sleep(5000);
		
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
	
	/*@Test
	public void onRun() throws Exception{
		
		WelcomePage w = new WelcomePage();
		w.setEnvironment("stage");
		
		SignInPage signIn = new SignInPage();
		signIn.signIn();
		
		
		// overview screen
		
		OverviewPage o = new OverviewPage();
		Thread.sleep(12000);
		
		o.recentTransactionsCard.click();
		Thread.sleep(5000);
		
		AllAccountsPage aap = new AllAccountsPage();
		if (aap.textAllAccounts.isDisplayed())
			System.out.println("All accounts page got displayed");
		else
			System.out.println("errrrrrrrrrrrrrrrrrrrrrrrr all accounts text not got displayed");
		
		aap.backButton.click();
		Thread.sleep(8000);
		
		/*int x1 = o.rt.getLocation().getX();
		int y1 = o.rt.getLocation().getY();
		int x = o.ac.getLocation().getX();
		int y = o.ac.getLocation().getY();
		Engine.ad.swipe(x, y, x1, y1, 3);
		System.out.println("eeeeeeeeeeee");
		Thread.sleep(4000);
		Engine.ad.swipe(x, y, x1, y1, 3);
		System.out.println("eeeeeeeeeeee");*/
		
		/*Dimension size = Engine.ad.manage().window().getSize();
		System.out.println(size.width-10);
		System.out.println("height - "+size.height);
		System.out.println(size.height * 6 / 8);
		System.out.println(size.height / 8);
		
		Engine.ad.swipe(size.width - 10, size.height * 6 / 8, size.width - 10, size.height / 8, 500);
		Thread.sleep(20000);*/
		
		
		
		
		//Engine.ad.scr
		//Engine.ad.swipe();
		
		//((AppiumDriver) Engine.ad).scrollToExact(“ss”);
		
		/*WelcomePage w = new WelcomePage();
		w.xpath_Environment.click();
		Thread.sleep(500);
		w.xpath_chkboxStageEnvironment.click();
		Thread.sleep(500);
		
		try{
			w.xpath_btnWelcomeSignIn.isDisplayed();
			System.out.println("btn displayed...:(");
			
		}
		catch(Exception e)
		{
			System.out.println("still env options available...:(");
			Engine.ad.navigate().back();
		}*/
		
		
			
		
		
		
		
		
		/*System.out.println(w.xpath_signInWidget.getClass());
		System.out.println(w.xpath_signInWidget.getTagName());
		System.out.println(w.xpath_signInWidget.getId());
		
		
		w.xpath_signInWidget.click();
		w.xpath_signInWidget.click();
		w.xpath_signInWidget.click();
		w.xpath_signInWidget.click();
		System.out.println("eeeeeeeeeeee");
		Thread.sleep(5000);
		//w.xpath_btnWelcomeSignIn.click();	
	}*/
	
	@Test
	public void TrendingCategories() throws Exception{
		
		/*WelcomePage w = new WelcomePage();
		w.setEnvironment("stage");*/
		
		SignInPage signIn = new SignInPage();
		signIn.signIn();
		
		
		// overview screen
		OverviewPage o = new OverviewPage();
		o.tapOnTrendingCard();
		/*Dimension size = Engine.ad.manage().window().getSize();
		Engine.ad.swipe(size.width - 10, size.height * 6 / 8, size.width - 10, size.height / 7, 500);
		Thread.sleep(3000);
		
		o.topTrendingCard.click();
		Thread.sleep(5000);*/
		
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
	
	@Test
	public void TransactionwSummary() throws Exception{
		
		/*WelcomePage w = new WelcomePage();
		w.setEnvironment("stage");*/
		
		SignInPage signIn = new SignInPage();
		signIn.signIn();
		
		
		// overview screen
		OverviewPage o = new OverviewPage();
		o.tapOnTransactionSummaryCard();
		/*Thread.sleep(12000);
		Dimension size = Engine.ad.manage().window().getSize();
		Engine.ad.swipe(size.width - 10, size.height * 6 / 8, size.width - 10, size.height / 7, 500);
		Thread.sleep(3000);
		
		o.transactionSummaryCard.click();
		Thread.sleep(5000);*/
		
		TransactionSummaryPage cashflow = new TransactionSummaryPage();
		
		if (Verify.objExists(cashflow.transactionSummaryHeader))
			quickenTest.log(LogStatus.INFO,"cashflow card tap >Transaction summary screen got dispalyed.");
		else
			quickenTest.log(LogStatus.FAIL,"cashflow card tap > cashflow/Transaction summary screen did not appear.");
		
		cashflow.navigateBackToDashboard();
		
		if (Verify.objExists(o.hambergerIcon))
			quickenTest.log(LogStatus.INFO,"cashflow card, back button tap > Overview screen got dispalyed.");
		else
			quickenTest.log(LogStatus.FAIL,"cashflow card, back button tap > Overview screen did not appear.");	
			
	}
	
	@Test
	public void verifyOverviewScreen() throws Exception{
		
		SignInPage signIn = new SignInPage();
		signIn.signIn();
		
		 OverviewPage op = new OverviewPage();
		 
		 if ( Verify.objExists(op.accountsCard))
			 System.out.println("Accounts card appeared");
		 else
			 quickenTest.log(LogStatus.FAIL,"Accounts card did not appear.");
		 
		 if ( Verify.objExists(op.recentTransactionsCard))
			 System.out.println("RecentTransactions card appeared");
		 else
			 quickenTest.log(LogStatus.FAIL,"RecentTransactions card did not appear.");
		 
		 /*if ( Verify.objExists(op.spendingCard))
			 System.out.println("Spending card appeared");
		 else
			 quickenTest.log(LogStatus.FAIL,"Spending card did not appear.");*/
		
	}
	
	
	@Test
	public void tt() throws Exception{
		
		System.out.println("ssssss");
		
		try {
			
			Engine.ad.close();
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		Thread.sleep(8000);
		
		
		/*Engine.ad.pressKeyCode(187);
		Thread.sleep(8000);*/
		
		/*SignInPage signIn = new SignInPage();
		signIn.signIn();*/
		
		/*JavascriptExecutor js = (JavascriptExecutor) Engine.ad;
		
		Map<String, Object> params = new HashMap();
		params.put("bundleId", "com.quicken.qm2014");
		try {
			final boolean wasRunningBefore = (Boolean)js.executeScript("mobile: TERMINATE_APP", params);
			//teerminateApp
		}
		catch (Exception e) {
			
			System.out.println("^^^^^^^^^^^^^^^");
			System.out.println(e.getMessage().toString());
			System.out.println("^^^^^^^^^^^^^^^");
			
		}*/
		
		
		System.out.println("tttttttt");

		
		
		
	}
	
	
	
	
	
	
	
	@Test
	public void Investing() throws Exception{
		
		/*WelcomePage w = new WelcomePage();
		w.setEnvironment("stage");*/
		
		SignInPage signIn = new SignInPage();
		signIn.signIn();
		
		
		// overview screen
		OverviewPage o = new OverviewPage();
		Thread.sleep(12000);
		Dimension size = Engine.ad.manage().window().getSize();
		Engine.ad.swipe(size.width - 10, size.height * 6 / 8, size.width - 10, size.height / 8, 500);
		Engine.ad.swipe(size.width - 10, size.height * 6 / 8, size.width - 10, size.height / 8, 500);
		Thread.sleep(3000);
		
		o.investingCard.click();
		Thread.sleep(5000);
		
		
		InvestingPage inv = new InvestingPage();
		
		if (Verify.objExists(inv.investingHeader))
			quickenTest.log(LogStatus.INFO,"investing card tap >Investing page got dispalyed.");
		else
			quickenTest.log(LogStatus.FAIL,"investing card tap > Investing page did not appear.");
		
		if (Verify.objExists(inv.securitiesTab))
			quickenTest.log(LogStatus.INFO,"investing card, securities tab got dispalyed.");
		else
			quickenTest.log(LogStatus.FAIL,"investing card, securities tab did not appear.");
		
		if (Verify.objExists(inv.accountsTab))
			quickenTest.log(LogStatus.INFO,"investing card, accounts tab got dispalyed.");
		else
			quickenTest.log(LogStatus.FAIL,"investing card, accounts tab did not appear.");
		
		if (Verify.objExists(inv.todaysChangeLabel))
			quickenTest.log(LogStatus.INFO,"investing card, today's change label got dispalyed.");
		else
			quickenTest.log(LogStatus.FAIL,"investing card, today's change label did not appear.");
		
		if (Verify.objExists(inv.totalValueLabel))
			quickenTest.log(LogStatus.INFO,"investing card, total value label got dispalyed.");
		else
			quickenTest.log(LogStatus.FAIL,"investing card, total value label did not appear.");
		
		if (Verify.objExists(inv.cashbalancesLabel))
			quickenTest.log(LogStatus.INFO,"investing card, cash balances label got dispalyed.");
		else
			quickenTest.log(LogStatus.FAIL,"investing card, cash balances label did not appear.");
		
		if (Verify.objExists(inv.securitiesByCompanyNameLabel))
			quickenTest.log(LogStatus.INFO,"investing card, default option securitiesByCompanyNameLabel got dispalyed.");
		else
			quickenTest.log(LogStatus.FAIL,"investing card, default option securitiesByCompanyNameLabel did not appear.");
		
		inv.accountsTab.click();
		Thread.sleep(5000);
		
		if (Verify.objExists(inv.brokerageAccountNameLabel))
			quickenTest.log(LogStatus.INFO,"investing card > Accounts tab,  brokerage acct got dispalyed.");
		else
			quickenTest.log(LogStatus.FAIL,"investing card > Accounts tab,  brokerage acct did not appear.");
		
		if (Verify.objExists(inv.fiveHoldingsNameLabel))
			quickenTest.log(LogStatus.INFO,"investing card > Accounts tab,  brokerage acct has 5 holdings .");
		else
			quickenTest.log(LogStatus.FAIL,"investing card > Accounts tab,  brokerage acct should show 5 holdings");
		
		inv.backButtonOnHeader.click();
		Thread.sleep(5000);
		
		if (Verify.objExists(o.accountsCard))
			quickenTest.log(LogStatus.INFO,"Investing card, back button tap > Overview screen got dispalyed.");
		else
			quickenTest.log(LogStatus.FAIL,"Investing card, back button tap > Overview screen did not appear.");	
	}
	
	@Test
	public void accountsCardLabelWithNoCashVerify() throws Exception{
		
		WelcomePage w = new WelcomePage();
		w.setEnvironment("stage");
		
		SignInPage signIn = new SignInPage();
		signIn.signIn();
		
		
		// overview screen
		OverviewPage o = new OverviewPage();
		Thread.sleep(12000);
		
		if (Verify.objExists(o.accountsCard))
			quickenTest.log(LogStatus.INFO,"Overview screen got dispalyed after signIn.");
		else
			quickenTest.log(LogStatus.FAIL,"Overview screen did not appear after signIn.");	
		
		if (Verify.objExists(o.accountsCard_checkingLabel))
			quickenTest.log(LogStatus.INFO,"Accounts card > Checking label got dispalyed.");
		else
			quickenTest.log(LogStatus.FAIL,"Accounts card > Checking label did not appear.");	
	
		if (Verify.objExists(o.accountsCard_savingsLabel))
			quickenTest.log(LogStatus.INFO,"Accounts card > Savings label got dispalyed.");
		else
			quickenTest.log(LogStatus.FAIL,"Accounts card > Savings label did not appear.");
		
		if (Verify.objExists(o.accountsCard_creditLabel))
			quickenTest.log(LogStatus.INFO,"Accounts card > Credit label got dispalyed.");
		else
			quickenTest.log(LogStatus.FAIL,"Accounts card > Credit label did not appear.");	
		
		if (Verify.objExists(o.accountsCard_otherLabel))
			quickenTest.log(LogStatus.INFO,"Accounts card > Other label got dispalyed.");
		else
			quickenTest.log(LogStatus.FAIL,"Accounts card > Other label did not appear.");	
		
		if (Verify.objExists(o.accountsCard_totalLabel))
			quickenTest.log(LogStatus.INFO,"Accounts card > Total label got dispalyed.");
		else
			quickenTest.log(LogStatus.FAIL,"Accounts card > Total label did not appear.");
		
		if (Verify.objExists(o.accountsCard_cashLabel))
			quickenTest.log(LogStatus.FAIL,"Accounts card > Cash label should not appear for the user.");	
		else
			quickenTest.log(LogStatus.INFO,"Accounts card > Cash label is not present, no cash accts for the user.");
				
		
	}
	
	@Test
	public void accountsCardLabelWithCashAcctVerify() throws Exception{
		
		WelcomePage w = new WelcomePage();
		w.setEnvironment("stage");
		
		SignInPage signIn = new SignInPage();
		signIn.signIn("stage++test2@qa.com","Intuit!1", "");
		
		
		// overview screen
		OverviewPage o = new OverviewPage();
		Thread.sleep(15000);
		
		if (Verify.objExists(o.accountsCard))
			quickenTest.log(LogStatus.INFO,"Overview screen got dispalyed after signIn.");
		else
			quickenTest.log(LogStatus.FAIL,"Overview screen did not appear after signIn.");	
		
		if (Verify.objExists(o.accountsCard_checkingLabel))
			quickenTest.log(LogStatus.INFO,"Accounts card > Checking label got dispalyed.");
		else
			quickenTest.log(LogStatus.FAIL,"Accounts card > Checking label did not appear.");	
	
		if (Verify.objExists(o.accountsCard_savingsLabel))
			quickenTest.log(LogStatus.INFO,"Accounts card > Savings label got dispalyed.");
		else
			quickenTest.log(LogStatus.FAIL,"Accounts card > Savings label did not appear.");
		
		if (Verify.objExists(o.accountsCard_creditLabel))
			quickenTest.log(LogStatus.INFO,"Accounts card > Credit label got dispalyed.");
		else
			quickenTest.log(LogStatus.FAIL,"Accounts card > Credit label did not appear.");	
		
		if (Verify.objExists(o.accountsCard_otherLabel))
			quickenTest.log(LogStatus.INFO,"Accounts card > Other label got dispalyed.");
		else
			quickenTest.log(LogStatus.FAIL,"Accounts card > Other label did not appear.");	
		
		if (Verify.objExists(o.accountsCard_totalLabel))
			quickenTest.log(LogStatus.INFO,"Accounts card > Total label got dispalyed.");
		else
			quickenTest.log(LogStatus.FAIL,"Accounts card > Total label did not appear.");
		
		if (Verify.objExists(o.accountsCard_cashLabel))
			quickenTest.log(LogStatus.INFO,"Accounts card > Cash label appeared, cash acct was setup for the user.");	
		else
			quickenTest.log(LogStatus.FAIL,"Accounts card > Cash label is not present, cash accts were setup for the user.");	
	}
	
	@Test
	public void RecentTransactionsZeroDataState() throws Exception{
		
		WelcomePage w = new WelcomePage();
		w.setEnvironment("stage");
		
		SignInPage signIn = new SignInPage();
		signIn.signIn("stage++test2@qa.com","Intuit!1", "");
		
		
		// overview screen
		OverviewPage o = new OverviewPage();
		Thread.sleep(12000);
		
		if (Verify.objExists(o.recentTransactionsCard))
			quickenTest.log(LogStatus.INFO,"RecentTransactions card got dispalyed on overview screen.");
		else
			quickenTest.log(LogStatus.FAIL,"RecentTransactions card did not appear on overview screen.");
		
		if (Verify.objExists(o.recentTxns_NoTxnsAvaialable))
			quickenTest.log(LogStatus.INFO,"RecentTransactions card, [No Transactions available] got dispalyed");
		else
			quickenTest.log(LogStatus.FAIL,"RecentTransactions card [No Transactions available] did not appear");	
		
	}
	
	@Test
	public void TrendingCategoriesZeroDataCard() throws Exception{
		
		WelcomePage w = new WelcomePage();
		w.setEnvironment("stage");
		
		SignInPage signIn = new SignInPage();
		signIn.signIn("stage++test2@qa.com","Intuit!1", "");
		
		
		// overview screen
		OverviewPage o = new OverviewPage();
		Thread.sleep(12000);
		Dimension size = Engine.ad.manage().window().getSize();
		Engine.ad.swipe(size.width - 10, size.height * 6 / 8, size.width - 10, size.height / 7, 500);
		Thread.sleep(3000);
		
		if (Verify.objExists(o.topTrendingCard))
			quickenTest.log(LogStatus.INFO,"Trending card got dispalyed on overview screen.");
		else
			quickenTest.log(LogStatus.FAIL,"Trending card did not appear on overview screen.");
		
		
		if (Verify.objExists(o.tredningCard_YoudontHaveAnyTxn))
			quickenTest.log(LogStatus.INFO,"[You dont have any transaction] got dispalyed on trending card.");
		else
			quickenTest.log(LogStatus.FAIL,"[You dont have any transaction] did not appear on trending card.");	
	}
	
	@Test
	public void TransactionSummaryZeroDataCard() throws Exception{
		
		WelcomePage w = new WelcomePage();
		w.setEnvironment("stage");
		
		SignInPage signIn = new SignInPage();
		signIn.signIn("stage++test2@qa.com","Intuit!1", "");
		
		
		// overview screen
		OverviewPage o = new OverviewPage();
		Thread.sleep(12000);
		Dimension size = Engine.ad.manage().window().getSize();
		Engine.ad.swipe(size.width - 10, size.height * 6 / 8, size.width - 10, size.height / 7, 500);
		Thread.sleep(3000);
		
		if (Verify.objExists(o.transactionSummaryCard))
			quickenTest.log(LogStatus.INFO,"Transaction summary card got dispalyed on overview screen.");
		else
			quickenTest.log(LogStatus.FAIL,"Transaction summary card did not appear on overview screen.");
		
		
		if (Verify.objExists(o.txnSummary_zeroSpent))
			quickenTest.log(LogStatus.INFO,"[$0 spent] got dispalyed on cashflow card.");
		else
			quickenTest.log(LogStatus.FAIL,"[$0 spent] did not appear on cashflow card.");
		
		if (Verify.objExists(o.txnSummary_zeroEarned))
			quickenTest.log(LogStatus.INFO,"[$0 Earned] got dispalyed on cashflow card.");
		else
			quickenTest.log(LogStatus.FAIL,"[$0 Earned] did not appear on cashflow card.");
		
		if (Verify.objExists(o.txnSummary_zeroDollar))
			quickenTest.log(LogStatus.INFO,"[$0] got dispalyed on cashflow card.");
		else
			quickenTest.log(LogStatus.FAIL,"[$0] did not appear on cashflow card.");
		
	}
	
	@Test
	public void InvestingZeroDataState() throws Exception{
		
		WelcomePage w = new WelcomePage();
		w.setEnvironment("stage");
		
		SignInPage signIn = new SignInPage();
		signIn.signIn("stage++test2@qa.com","Intuit!1", "");
		
		
		// overview screen
		OverviewPage o = new OverviewPage();
		Thread.sleep(12000);
		Dimension size = Engine.ad.manage().window().getSize();
		Engine.ad.swipe(size.width - 10, size.height * 6 / 8, size.width - 10, size.height / 8, 500);
		Engine.ad.swipe(size.width - 10, size.height * 6 / 8, size.width - 10, size.height / 8, 500);
		Thread.sleep(3000);
		
		if (Verify.objExists(o.invCard_zeroDataState))
			quickenTest.log(LogStatus.INFO,"[Track your investing accounts and holdings on your phone.] got dispalyed on investing card.");
		else
			quickenTest.log(LogStatus.FAIL,"[Track your investing accounts and holdings on your phone.] did not appear on investing card.");
		
	}
	

}
