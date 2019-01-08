package tournament;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import dugout.AllAccountsPage;
import dugout.BankingAndCreditCardPage;
import dugout.InvestingPage;
import dugout.OverviewPage;
import dugout.SettingsPage;
import dugout.SignInPage;
import dugout.SpendingTrendPage;
import dugout.TransactionSummaryPage;
import dugout.TransactionsPage;
import dugout.WelcomePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.pagefactory.AndroidFindBy;
import referee.Commentary;
import referee.ExtentManager;
import referee.Verify;
import support.Engine;
import support.Helper;
import support.Recovery;

public class pract2_Test extends Recovery {
	
	ExtentReports rep = ExtentManager.initializeRepObject();
	ExtentTest quickenTest = Recovery.quickenTest;
	
	 @AndroidFindBy(xpath="//*[@class='android.widget.ScrollView']")
	 private MobileElement scrollView;
	
	
	
	
	@Test(priority = 0)
	public void verifyAccountTest() throws Exception {
		ExtentTest quickenTest = Recovery.quickenTest;
		
		//ExtentTest quickenTest = Recovery.quickenTest;
		//((JavascriptExecutor)Engine.ad).executeScript("sauce:job-name=verifyAccount"); 
		
		String acctToVerify = "Checking";
		Helper helper = new Helper();
		
		WelcomePage w = new WelcomePage();
		w.setEnvironment("stage");
		
		SignInPage signIn = new SignInPage();
		signIn.signIn();
		/*quickenTest.log(LogStatus.INFO,"SignIn Successful");
		quickenTest.log(LogStatus.INFO,"MFA - Enter manually, UAA wrapper not deployed :-(");
		System.out.println("get MFA...");
		helper.waitForRefresh(5000);*/
	    
	    OverviewPage op = new OverviewPage();
	    op.accountsCard.click();
	    helper.waitForRefresh(2000);
	    
	   
	    /*
	    // verify if checking account Exists or not
	    BankingAndCreditCardPage acctListPage = new BankingAndCreditCardPage();
		
		if (acctListPage.getAccount(acctToVerify).getText().trim().equals(acctToVerify))
			quickenTest.log(LogStatus.INFO,"Checking account exists");
		else
			quickenTest.log(LogStatus.FAIL,"Checking account verification failed");
		
		// tap on the account
	   	acctListPage.getAccount("Checking").click();
	   	
	   	// verify account name on the transactionsPage
	   	TransactionsPage txnPage = new TransactionsPage();
	   	if (txnPage.verifyAccount(acctToVerify) == true)
	   		quickenTest.log(LogStatus.INFO,"Account name verification on Transaction page Pass.");
	   	else
	   		quickenTest.log(LogStatus.FAIL,"Account name verification on Transaction page failed.");
	   	
	   	// get back to overview screen
	   
	   /*	txnPage.backButton.click();
	   	helper.waitForRefresh(2000);
	   	acctListPage.backButton.click();
		helper.waitForRefresh(2000);*/
	   //	txnPage.navigateBackToDashboard();
	   	
	    
	}
	
	/*
	@Test(priority = 1)
	public void settingsPageTest() throws Exception{
		
		ExtentTest quickenTest = Recovery.quickenTest;
		String userID = "stage++test@qa.com";
		String cloudName="KF83";
		Helper helper = new Helper();
		
		//((JavascriptExecutor)Engine.ad).executeScript("sauce:job-name=settingsPage"); 
		
		
		OverviewPage op = new OverviewPage();
		if (! Verify.objExists(op.hambergerIcon)){
			WelcomePage w = new WelcomePage();
			w.setEnvironment("stage");
			SignInPage signIn = new SignInPage();
			signIn.signIn();
			
		}
			
		op.hambergerIcon.click();
		helper.waitForRefresh(2000);
		
		SettingsPage settingsPage = new SettingsPage();
		
		if (settingsPage.verifyQuickenID(userID))
			quickenTest.log(LogStatus.INFO,"PASS: QuickenID ["+userID+"] verified successfully!");
		else
			quickenTest.log(LogStatus.FAIL,"FAIL: QuickenID ["+userID+"] verification Failed.");
		
		if (settingsPage.verifyCloudAccountName(cloudName))
			quickenTest.log(LogStatus.INFO,"PASS: cloud name ["+cloudName+"] verified successfully!");
		else
			quickenTest.log(LogStatus.FAIL,"FAIL: cloud name ["+cloudName+"] verification Failed.");
		
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
		
		settingsPage.close.click();
		Thread.sleep(1000);
		
	}*/
	/*
	@Test(priority = 2)
	public void verifyOverviewScreen() throws Exception{
		
		
		ExtentTest quickenTest = Recovery.quickenTest;
		
		
		 OverviewPage op = new OverviewPage();
		 
		 if (! Verify.objExists(op.hambergerIcon)){
			 	WelcomePage w = new WelcomePage();
				w.setEnvironment("stage");
				SignInPage signIn = new SignInPage();
				signIn.signIn();
				Thread.sleep(15000);
			}
		 
		 if ( Verify.objExists(op.accountsCard))
			 System.out.println("Accounts card appeared");
		 else
			 quickenTest.log(LogStatus.FAIL,"Accounts card did not appear.");
		 
		 if ( Verify.objExists(op.recentTransactionsCard))
			 quickenTest.log(LogStatus.FAIL,"RecentTransactions card appeared.");
		 else
			 quickenTest.log(LogStatus.FAIL,"RecentTransactions card did not appear.");
		 
		 Dimension size = Engine.ad.manage().window().getSize();
		 System.out.println("scrolldown....");
		 Engine.ad.swipe(size.width - 10, size.height * 6 / 8, size.width - 10, size.height / 7, 500);
		 System.out.println("scrolldown....");
		 Thread.sleep(3000);
		 
		 if ( Verify.objExists(op.spendingCard))
			 quickenTest.log(LogStatus.INFO,"Spending card appeared.");
		 else
			 quickenTest.log(LogStatus.FAIL,"Spending card did not appear.");
		 Thread.sleep(3000);
		 
		 
		
	}*/
	/*
	@Test(priority = 2)
	public void RecentTransactionsTest() throws Exception{
		ExtentTest quickenTest = Recovery.quickenTest;
		//((JavascriptExecutor)Engine.ad).executeScript("sauce:job-name=RecentTransactions"); 
		// overview screen
		OverviewPage o = new OverviewPage();
		if (! Verify.objExists(o.hambergerIcon)){
			WelcomePage w = new WelcomePage();
			w.setEnvironment("stage");
			SignInPage signIn = new SignInPage();
			signIn.signIn();
			Thread.sleep(15000);
		}
		
		
		o.recentTransactionsCard.click();
		Thread.sleep(5000);
		
		AllAccountsPage aap = new AllAccountsPage();
		if (Verify.objExists(aap.textAllAccounts))
			quickenTest.log(LogStatus.INFO,"RecentTransactions card tap > All accounts screen got dispalyed.");
		else
			quickenTest.log(LogStatus.FAIL,"RecentTransactions card tap > All accounts screen did not appear.");
		
		aap.navigateBackToDashboard();
		
		
		if (Verify.objExists(o.recentTransactionsCard))
			quickenTest.log(LogStatus.INFO,"RecentTransactions card, back button tap > Overview screen got dispalyed.");
		else
			quickenTest.log(LogStatus.FAIL,"RecentTransactions card, back button tap > Overview screen did not appear.");	
		Thread.sleep(3000);
		
		
	}*/
	
	/*
	@Test(priority = 3)
	public void TrendingCategoriesTest() throws Exception{
		ExtentTest quickenTest = Recovery.quickenTest;
		
		// overview screen
		OverviewPage o = new OverviewPage();
		if (! Verify.objExists(o.hambergerIcon)){
			WelcomePage w = new WelcomePage();
			w.setEnvironment("stage");
			SignInPage signIn = new SignInPage();
			signIn.signIn();
			
		}
		
		
		// overview screen
		Dimension size = Engine.ad.manage().window().getSize();
		Engine.ad.swipe(size.width - 10, size.height * 6 / 8, size.width - 10, size.height / 7, 500);
		Thread.sleep(3000);
		
		// sometimes scroll gets stuck when refresh is going on
		if (!Verify.objExists(o.topTrendingCard))
			Engine.ad.swipe(size.width - 10, size.height * 6 / 8, size.width - 10, size.height / 7, 500);
			
		
		o.topTrendingCard.click();
		Thread.sleep(5000);
		
		SpendingTrendPage st = new SpendingTrendPage();
		if (Verify.objExists(st.spendingTrendHeader))
			quickenTest.log(LogStatus.INFO,"SpendingTrend card tap >SpendingTrend screen got dispalyed.");
		else
			quickenTest.log(LogStatus.FAIL,"SpendingTrend card tap > SpendingTrend screen did not appear.");
		
		st.navigateBackToDashboard();
		
		
		if (Verify.objExists(o.accountsCard))
			quickenTest.log(LogStatus.INFO,"SpendingTrend card, back button tap > Overview screen got dispalyed.");
		else
			quickenTest.log(LogStatus.FAIL,"SpendingTrend card, back button tap > Overview screen did not appear.");	
		
	}
	
	
	@Test(priority = 4)
	public void cashflowTest() throws Exception{
		
		ExtentTest quickenTest = Recovery.quickenTest;
		
		// overview screen
		OverviewPage o = new OverviewPage();
		if (! Verify.objExists(o.hambergerIcon)){
			WelcomePage w = new WelcomePage();
			w.setEnvironment("stage");
			SignInPage signIn = new SignInPage();
			signIn.signIn();
			
		}
		
		
		
		
		Dimension size = Engine.ad.manage().window().getSize();
		Engine.ad.swipe(size.width - 10, size.height * 6 / 8, size.width - 10, size.height / 7, 500);
		Thread.sleep(3000);
		
		o.transactionSummaryCard.click();
		Thread.sleep(5000);
		
		TransactionSummaryPage cashflow = new TransactionSummaryPage();
		
		if (Verify.objExists(cashflow.transactionSummaryHeader))
			quickenTest.log(LogStatus.INFO,"cashflow card tap >Transaction summary screen got dispalyed.");
		else
			quickenTest.log(LogStatus.FAIL,"cashflow card tap > cashflow/Transaction summary screen did not appear.");
		
		cashflow.navigateBackToDashboard();
		
		if (Verify.objExists(o.accountsCard))
			quickenTest.log(LogStatus.INFO,"cashflow card, back button tap > Overview screen got dispalyed.");
		else
			quickenTest.log(LogStatus.FAIL,"cashflow card, back button tap > Overview screen did not appear.");	
			
	}*/
	/*
	@Test(priority = 5)
	public void InvestingTest() throws Exception{
		
		ExtentTest quickenTest = Recovery.quickenTest;
		
		// overview screen
		OverviewPage o = new OverviewPage();
		if (! Verify.objExists(o.hambergerIcon)){
			WelcomePage w = new WelcomePage();
			w.setEnvironment("stage");
			SignInPage signIn = new SignInPage();
			signIn.signIn();
			
		}
		
		
		
		Dimension size = Engine.ad.manage().window().getSize();
		Engine.ad.swipe(size.width - 10, size.height * 6 / 8, size.width - 10, size.height / 8, 500);
		Engine.ad.swipe(size.width - 10, size.height * 6 / 8, size.width - 10, size.height / 8, 500);
		Engine.ad.swipe(size.width - 10, size.height * 6 / 8, size.width - 10, size.height / 8, 500);
		Thread.sleep(3000);
		
		o.investingCard.click();
		Thread.sleep(5000);
		
		InvestingPage inv = new InvestingPage();
		
		
		if (Verify.objExists(inv.investingHeader))
			Commentary.log(LogStatus.INFO,"investing card tap >Investing page got dispalyed.");
		else
			Commentary.log(LogStatus.FAIL,"investing card tap > Investing page did not appear.");
		
		if (Verify.objExists(inv.securitiesTab))
			Commentary.log(LogStatus.INFO,"investing card, securities tab got dispalyed.");
		else
			Commentary.log(LogStatus.FAIL,"investing card, securities tab did not appear.");
		
		if (Verify.objExists(inv.accountsTab))
			Commentary.log(LogStatus.INFO,"investing card, accounts tab got dispalyed.");
		else
			Commentary.log(LogStatus.FAIL,"investing card, accounts tab did not appear.");
		
		if (Verify.objExists(inv.todaysChangeLabel))
			Commentary.log(LogStatus.INFO,"investing card, today's change label got dispalyed.");
		else
			Commentary.log(LogStatus.FAIL,"investing card, today's change label did not appear.");
		
		if (Verify.objExists(inv.totalValueLabel))
			Commentary.log(LogStatus.INFO,"investing card, total value label got dispalyed.");
		else
			Commentary.log(LogStatus.FAIL,"investing card, total value label did not appear.");
		
		if (Verify.objExists(inv.cashbalancesLabel))
			Commentary.log(LogStatus.INFO,"investing card, cash balances label got dispalyed.");
		else
			Commentary.log(LogStatus.FAIL,"investing card, cash balances label did not appear.");
		
		if (Verify.objExists(inv.securitiesByCompanyNameLabel))
			Commentary.log(LogStatus.INFO,"investing card, default option securitiesByCompanyNameLabel got dispalyed.");
		else
			Commentary.log(LogStatus.FAIL,"investing card, default option securitiesByCompanyNameLabel did not appear.");
		
		inv.accountsTab.click();
		Thread.sleep(3000);
		
		if (Verify.objExists(inv.brokerageAccountNameLabel))
			Commentary.log(LogStatus.INFO,"investing card > Accounts tab,  brokerage acct got dispalyed.");
		else
			Commentary.log(LogStatus.FAIL,"investing card > Accounts tab,  brokerage acct did not appear.");
		
		if (Verify.objExists(inv.fiveHoldingsNameLabel))
			Commentary.log(LogStatus.INFO,"investing card > Accounts tab,  brokerage acct has 5 holdings .");
		else
			Commentary.log(LogStatus.FAIL,"investing card > Accounts tab,  brokerage acct should show 5 holdings");
		
		inv.navigateBackToDashboard();
		
		if (Verify.objExists(o.accountsCard))
			Commentary.log(LogStatus.INFO,"Investing card, back button tap > Overview screen got dispalyed.");
		else
			Commentary.log(LogStatus.FAIL,"Investing card, back button tap > Overview screen did not appear.");	
	}*/
	/*
	@Test(priority = 6)
	public void accountsCardLabelWithNoCashVerifyTest() throws Exception{
		
		ExtentTest quickenTest = Recovery.quickenTest;
		
		// overview screen
		OverviewPage o = new OverviewPage();
		if (! Verify.objExists(o.hambergerIcon)){
			WelcomePage w = new WelcomePage();
			w.setEnvironment("stage");
			SignInPage signIn = new SignInPage();
			signIn.signIn();
			
		}
		
		
		
		
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
				
		
	}*/
	/*
	@Test(priority = 7)
	public void accountsCardLabelWithCashAcctVerifyTest() throws Exception{
		ExtentTest quickenTest = Recovery.quickenTest;
		
		WelcomePage w = new WelcomePage();
		w.setEnvironment("stage");
		
		SignInPage signIn = new SignInPage();
		signIn.signIn("stage++test2@qa.com","Intuit!1","");
		
		
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
	
	@Test(priority = 8)
	public void RecentTransactionsZeroDataStateTest() throws Exception{
		ExtentTest quickenTest = Recovery.quickenTest;
		
		WelcomePage w = new WelcomePage();
		w.setEnvironment("stage");
		
		SignInPage signIn = new SignInPage();
		signIn.signIn("stage++test2@qa.com","Intuit!1","");
		
		
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
	
	@Test(priority = 9)
	public void TrendingCategoriesZeroDataCardTest() throws Exception{
		ExtentTest quickenTest = Recovery.quickenTest;
		
		WelcomePage w = new WelcomePage();
		w.setEnvironment("stage");
		
		SignInPage signIn = new SignInPage();
		signIn.signIn("stage++test2@qa.com","Intuit!1","");
		
		
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
		
		o.scrollUptoAccountsCard();
	}
	
	@Test(priority = 10)
	public void TransactionSummaryZeroDataCardTest() throws Exception{
		ExtentTest quickenTest = Recovery.quickenTest;
		
		WelcomePage w = new WelcomePage();
		w.setEnvironment("stage");
		
		SignInPage signIn = new SignInPage();
		signIn.signIn("stage++test2@qa.com","Intuit!1","");
		
		
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
		
		o.scrollUptoAccountsCard();
		
	}
	
	@Test(priority = 11)
	public void InvestingZeroDataStateTest() throws Exception{
		ExtentTest quickenTest = Recovery.quickenTest;
		
		WelcomePage w = new WelcomePage();
		w.setEnvironment("stage");
		
		SignInPage signIn = new SignInPage();
		signIn.signIn("stage++test2@qa.com","Intuit!1","");
		
		
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
		
		o.scrollUptoAccountsCard();
	}*/
	/*
	@Test(priority = 11)
	public void InvestingZeroDataState_Test() throws Exception{
		ExtentTest quickenTest = Recovery.quickenTest;
		
		WelcomePage w = new WelcomePage();
		w.setEnvironment("stage");
		
		SignInPage signIn = new SignInPage();
		signIn.signIn("stage++test2@qa.com","Intuit!1","");
		
		
		// overview screen
		OverviewPage o = new OverviewPage();
		Thread.sleep(12000);
		Dimension size = Engine.ad.manage().window().getSize();
		Engine.ad.swipe(size.width - 10, size.height * 6 / 8, size.width - 10, size.height / 8, 500);
		Engine.ad.swipe(size.width - 10, size.height * 6 / 8, size.width - 10, size.height / 8, 500);
		Thread.sleep(3000);
		
		int startX = size.width - 10;
		int startY = (int)(size.height*0.90);
		
		// swipe up
		Engine.ad.swipe(size.width - 10, size.height / 8, 500, size.width - 10, size.height * 6 / 8);
		Thread.sleep(5000);
		Engine.ad.swipe(size.width - 10, size.height / 8, 500, size.width - 10, size.height * 6 / 8);
		
		
	}
	
	@Test
	public void tt_Test() throws Exception{
		WelcomePage w = new WelcomePage();
		w.setEnvironment("stage");
		
		SignInPage signIn = new SignInPage();
		signIn.signIn("stage++test2@qa.com","Intuit!1","");
		
		Thread.sleep(5000);
		Dimension size1 = Engine.ad.manage().window().getSize();
		Engine.ad.swipe(size1.width - 10, size1.height * 6 / 8, size1.width - 10, size1.height / 8, 500);
		System.out.println("width-10"+size1.width);
		System.out.println("height*6/8"+size1.height * 6 / 8);
		System.out.println("height*8"+size1.height / 8);
		System.out.println("first swipe done....");
		Thread.sleep(10000);
		
		Dimension size = Engine.ad.findElementByXPath("//*[@class='android.widget.ScrollView']").getSize();
		System.out.println(Verify.objExists(scrollView));
		
		OverviewPage op = new OverviewPage();
		Dimension s = op.scrollView.getSize();
		System.out.println(s.getHeight());
		
		
		
		
		int starty = (int) (size.height); 
		System.out.println("startY"+starty);
		int endy = (int) (size.height * 0.20); 
		System.out.println("endY"+endy);
		int startx = size.width / 2; 
		Engine.ad.swipe(startx, endy, startx, starty, 3000); 
		Thread.sleep(5000);
		System.out.println("test1");
		Engine.ad.swipe(startx, starty, startx, endy, 3000); 
		System.out.println("test2");
		Thread.sleep(5000);
		
		size1 = Engine.ad.manage().window().getSize();
		 starty = (int) (size.height); 
		System.out.println("startY"+starty);
		 endy = (int) (size.height * 0.20); 
		System.out.println("endY"+endy);
		 startx = size.width / 2;
		 
		 Engine.ad.swipe(startx, endy, startx, starty, 3000); 
		Thread.sleep(5000);
		System.out.println("test3");
		Engine.ad.swipe(startx, starty, startx, endy, 3000); 
		System.out.println("test4");
		Thread.sleep(5000);
		
		System.out.println("test5");
		Engine.ad.swipe(size1.width - 10, size1.height / 8,  size1.width - 10, size1.height * 6 / 8, 500);
		Thread.sleep(5000);
		
		System.out.println("test6");
		Engine.ad.swipe(size1.width - 10, size.height, startx, starty, 3000);
		Thread.sleep(5000);
		//Engine.ad.swipe(SwipeElementDirection.UP,2,2,10000);
		
		
		System.out.println(op.scrollView.getSize().getHeight());
		//System.out.println(Verify.objExists(me));
		//Engine.ad.
		
		//Dimension size3 = scrollView.getSize();
		op.scrollView.swipe(SwipeElementDirection.DOWN, 10, 10, 1000);
		Thread.sleep(5000);
		System.out.println("test1");
		
		op.scrollView.swipe(SwipeElementDirection.DOWN, 10, 10, 1000);
		Thread.sleep(5000);
		System.out.println("test2");
		
		op.scrollView.swipe(SwipeElementDirection.DOWN, 10, 10, 1000);
		Thread.sleep(5000);
		System.out.println("test2");
		
		op.scrollView.swipe(SwipeElementDirection.DOWN, startx-10, startx-10, 1000);
		Thread.sleep(5000);
		System.out.println("test2");
		
		op.scrollView.swipe(SwipeElementDirection.UP, 5, 5, 1000);
		Thread.sleep(2000);
		System.out.println("test3");
		
		
		
		
	}*/
	/*
	@Test(priority = 0)
	public void verifyAccountTest1() throws Exception {
		ExtentTest quickenTest = Recovery.quickenTest;
		
		//ExtentTest quickenTest = Recovery.quickenTest;
		//((JavascriptExecutor)Engine.ad).executeScript("sauce:job-name=verifyAccount"); 
		
		String acctToVerify = "Checking";
		Helper helper = new Helper();
		
		WelcomePage w = new WelcomePage();
		w.setEnvironment("stage");
		
		SignInPage signIn = new SignInPage();
		signIn.signIn();
		
	    
	    OverviewPage op = new OverviewPage();
	    op.accountsCard.click();
	    helper.waitForRefresh(2000);
	    
	   
	    
	    // verify if checking account Exists or not
	    BankingAndCreditCardPage acctListPage = new BankingAndCreditCardPage();
		
		acctListPage.backButton.click();
	   	
	   	Thread.sleep(3000);
	   	
	    
	}*/
	
	

}
