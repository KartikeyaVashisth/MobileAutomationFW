package tournament;

import java.io.IOException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.AllAccountsPage;
import dugout.BankingAndCreditCardPage;
import dugout.OverviewPage;
import dugout.SignInPage;
import dugout.TransactionDetailPage;
import referee.Commentary;
import referee.Verify;
import support.Engine;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;

public class AccountStickiness_Test extends Recovery {
	
	String sUserName = "yuvaraju.boligorla@quicken.com";
	String sPassword = "Intuit!1";
	String sDataset = "account_stickiness";
	String sManualChecking = "Checking_manual";
	String sOnlineChecking ="checking1 XX1693";
	String sManualCreditCard = "CreditCard_manual";
	String sOnlineCreditCard ="qe1ccacct XX4781";
	String sManualCash="Cash_manual";
	
	public void openRecentTransactionsFromAccountList() throws Exception {
		
		OverviewPage op = new OverviewPage();
		op.tapOnRecentTransactionsCard();
		
		TransactionDetailPage td = new TransactionDetailPage();
		
	}
	
	
	@Test(priority = 0)
	public void AS1_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		
		Commentary.log(LogStatus.INFO, "Verifying Default Account Stickiness for a newly Signed in User");
		
		
		SignInPage signIn = new SignInPage();
		signIn.signIn(sUserName, sPassword, sDataset);
		
		
		 OverviewPage op = new OverviewPage();
		 op.addTransaction.click();
		 
		 TransactionDetailPage td = new TransactionDetailPage();
		 if (Verify.objExists(td.allowButton)) 
			 td.allowButton.click();
		 td.buttonDone.click();
		 
		 if (Verify.objExists(td.chooseAnAccount))
			 Commentary.log(sa, LogStatus.INFO,"New signIn, Overview page, tapped on add transaction, Transaction details screen - Choose an Account text present");
		 else
			 Commentary.log(sa, LogStatus.FAIL,"New signIn, Overview page, tapped on add transaction, Transaction details screen - Choose an Account text did not present");
		 
		 td.backButton.click();
		 Thread.sleep(1000);
		 
		 op.navigateToAcctList();
		 
		 BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		 bcc.invokeTransactionDetailsFromAccountListBalanceHeader();
		 
		 if (Verify.objExists(td.chooseAnAccount))
			 Commentary.log(sa, LogStatus.INFO,"Tapped on Today's BALANCE, Tapped on Add transaction, Transaction details screen - Choose an Account text present");
		 else
			 Commentary.log(sa, LogStatus.FAIL,"Tapped on Today's BALANCE, Tapped on Add transaction, Transaction details screen - Choose an Account text not present");	
		
		sa.assertAll();
	}
	
	@Test(priority = 1)
	public void AS2_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		
		Helper h = new Helper();
//		Engine.getDriver().getContext();
		
		
		OverviewPage op = new OverviewPage();
		 
		Commentary.log(LogStatus.INFO, "Verifying Default Account Stickiness for Manual Checking account");
		
		op.navigateToAcctList();
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		bcc.getAccount(sManualChecking).click();
		bcc.invokeTransactionDetails();
		
		TransactionDetailPage td = new TransactionDetailPage();
		 if (td.VerifyTransactionAccount(sManualChecking))
			 Commentary.log(sa, LogStatus.INFO,"Tapped on Manual Checking account, Tapped on Add transaction, Transaction details screen - Account is preselected");
		 else
			 Commentary.log(sa, LogStatus.FAIL,"Tapped on Manual Checking account, Tapped on Add transaction, Transaction details screen - Account is NOT preselected");	
		
		 sa.assertAll();
		
		
	}
	
	@Test(priority = 2)
	public void AS3_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		
		Helper h = new Helper();
//		Engine.getDriver().getContext();
//		if (h.getEngine().equals("android"))
//			Engine.ad.getContext();
//		else
//			Engine.iosd.getContext();
		
		OverviewPage op = new OverviewPage();
		 
		Commentary.log(LogStatus.INFO, "Verifying Default Account Stickiness for Online Checking account");
		
		op.navigateToAcctList();
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		bcc.getAccount(sOnlineChecking).click();
		bcc.invokeTransactionDetails();
		
		TransactionDetailPage td = new TransactionDetailPage();
		 if (td.VerifyTransactionAccount(sOnlineChecking))
			 Commentary.log(sa, LogStatus.INFO,"Tapped on Online Checking account, Tapped on Add transaction, Transaction details screen - Account is preselected");
		 else
			 Commentary.log(sa, LogStatus.FAIL,"Tapped on Online Checking account, Tapped on Add transaction, Transaction details screen - Account is NOT preselected");	
		
		 sa.assertAll();
	}



	@Test(priority = 3)
	public void AS4_Test() throws Exception {
	
	SoftAssert sa = new SoftAssert();
	
	
	OverviewPage op = new OverviewPage();
	 
	Commentary.log(LogStatus.INFO, "Verifying Default Account Stickiness for Manual Credit card account");
	
	op.navigateToAcctList();
	BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
	bcc.getAccount(sManualCreditCard).click();
	bcc.invokeTransactionDetails();
	
	TransactionDetailPage td = new TransactionDetailPage();
	 if (td.VerifyTransactionAccount(sManualCreditCard))
		 Commentary.log(sa, LogStatus.INFO,"Tapped on Manual CreditCard account, Tapped on Add transaction, Transaction details screen - Account is preselected");
	 else
		 Commentary.log(sa, LogStatus.FAIL,"Tapped on Manual CreditCard account, Tapped on Add transaction, Transaction details screen - Account is NOT preselected");	
	
	 sa.assertAll();
	
}
	
	@Test(priority = 4)
	public void AS5_Test() throws Exception {
	
	SoftAssert sa = new SoftAssert();
	
	Helper h = new Helper();
//	Engine.getDriver().getContext();
//	if (h.getEngine().equals("android"))
//		Engine.ad.getContext();
//	else
//		Engine.iosd.getContext();
	
	
	OverviewPage op = new OverviewPage();
	 
	Commentary.log(LogStatus.INFO, "Verifying Default Account Stickiness for Online Credit card account");
	
	op.navigateToAcctList();
	BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
	bcc.getAccount(sOnlineCreditCard).click();
	bcc.invokeTransactionDetails();
	
	TransactionDetailPage td = new TransactionDetailPage();
	 if (td.VerifyTransactionAccount(sOnlineCreditCard))
		 Commentary.log(sa, LogStatus.INFO,"Tapped on Online CreditCard account, Tapped on Add transaction, Transaction details screen - Account is preselected");
	 else
		 Commentary.log(sa, LogStatus.FAIL,"Tapped on Online CreditCard account, Tapped on Add transaction, Transaction details screen - Account is NOT preselected");	
	
	 sa.assertAll();	
}
	
	@Test(priority = 5)
	public void AS6_Test() throws Exception {
	
	SoftAssert sa = new SoftAssert();
	
	
	OverviewPage op = new OverviewPage();
	 
	Commentary.log(LogStatus.INFO, "Verifying Default Account Stickiness for manual CASH account");
	
	op.navigateToAcctList();
	BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
	bcc.getAccount(sManualCash).click();
	bcc.invokeTransactionDetails();
	
	TransactionDetailPage td = new TransactionDetailPage();
	 if (td.VerifyTransactionAccount(sManualCash))
		 Commentary.log(sa, LogStatus.INFO,"Tapped on manual CASH account, Tapped on Add transaction, Transaction details screen - Account is preselected");
	 else
		 Commentary.log(sa, LogStatus.FAIL,"Tapped on manual CASH account, Tapped on Add transaction, Transaction details screen - Account is NOT preselected");	
	
	 sa.assertAll();	
}
	
	@Test(priority = 6)
	public void AS7_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		
		Helper h = new Helper();
//		Engine.getDriver().getContext();
//		if (h.getEngine().equals("android"))
//			Engine.ad.getContext();
//		else
//			Engine.iosd.getContext();
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sOnlineChecking);
		tRec.setAmount("2.34");
		tRec.setTransactionType("income");
		
		
		
		Commentary.log(LogStatus.INFO, "Adding Transaction from overview screen, verifying account is retained for the next transaction");
		OverviewPage op = new OverviewPage(); 
		op.addTransaction.click();
		
		TransactionDetailPage td = new TransactionDetailPage();
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+sOnlineChecking+"]");
		
		 op.addTransaction.click();
		 td.buttonDone.click();
		 
		 if (td.VerifyTransactionAccount(sOnlineChecking))
			 Commentary.log(sa, LogStatus.INFO,"Tapped on Online Checking account, Tapped on Add transaction, Transaction details screen - Account is preselected");
		 else
			 Commentary.log(sa, LogStatus.FAIL,"Tapped on Online Checking account, Tapped on Add transaction, Transaction details screen - Account is NOT preselected");
		 
		 td.backButton.click();
		 Thread.sleep(1000);
		 
		 op.navigateToAcctList();
		 
		 BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		 bcc.invokeTransactionDetailsFromAccountListBalanceHeader();
		 
		 if (td.VerifyTransactionAccount(sOnlineChecking))
			 Commentary.log(sa, LogStatus.INFO,"Tapped on account card, Tapped on Today's Balance, Transaction details screen - Account is preselected");
		 else
			 Commentary.log(sa, LogStatus.FAIL,"Tapped on account card, Tapped on Today's Balance, Transaction details screen - Account is NOT preselected");	
		
		sa.assertAll();
		 
	}
	
	@Test(priority = 7)
	public void AS8_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sOnlineChecking);
		tRec.setAmount("2.34");
		tRec.setTransactionType("income");
		
		Helper h = new Helper();
//		if (h.getEngine().equals("android"))
//			Engine.ad.getContext();
//		else
//			Engine.iosd.getContext();
//		Engine.getDriver().getContext();
		
		Commentary.log(LogStatus.INFO, "ADD Transaction from overview screen, Verifying Account Stickiness from another account");
		OverviewPage op = new OverviewPage(); 
		op.addTransaction.click();
		
		TransactionDetailPage td = new TransactionDetailPage();
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+sOnlineChecking+"]");
		op.navigateToAcctList();
		 
		 BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		 bcc.getAccount(sManualCash).click();
		 bcc.invokeTransactionDetails();
			
		 if (td.VerifyTransactionAccount(sManualCash))
				Commentary.log(sa, LogStatus.INFO,"Tapped on manual CASH account, Tapped on Add transaction, Transaction details screen - Account is preselected");
		else
				Commentary.log(sa, LogStatus.FAIL,"Tapped on manual CASH account, Tapped on Add transaction, Transaction details screen - Account is NOT preselected");	
			
		sa.assertAll();	
		 
	}
	
	@Test(priority = 8)
	public void AS9_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		
		Helper h = new Helper();
//		Engine.getDriver().getContext();
//		if (h.getEngine().equals("android"))
//			Engine.ad.getContext();
//		else
//			Engine.iosd.getContext();
		
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sOnlineCreditCard);
		tRec.setAmount("2.34");
		tRec.setTransactionType("income");
		
		Commentary.log(LogStatus.INFO, "ADD Transaction from online Credit card account screen, Verifying Account Stickiness from OverView Screen");
		OverviewPage op = new OverviewPage(); 
		op.navigateToAcctList();
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		bcc.getAccount(sOnlineCreditCard).click();
		
		AllAccountsPage aap = new AllAccountsPage();
		aap.addTransaction.click();
		Thread.sleep(1000);
		
		
		TransactionDetailPage td = new TransactionDetailPage();
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+sOnlineCreditCard+"]");
		
		/*
		bcc.backButton.click();
		Thread.sleep(2000); 
		*/
		aap.navigateBackToDashboard();
		op.navigateToAcctList();
		
		bcc.txtTodaysBalance.click();
		Thread.sleep(1000);
		
		aap.addTransaction.click();
		Thread.sleep(1000);
		td.buttonDone.click();
		
		
	
		if (td.VerifyTransactionAccount(sOnlineCreditCard))
				Commentary.log(sa, LogStatus.INFO,"Tapped on today's balance, Tapped on Add transaction, Transaction details screen - Online CC Account is preselected");
		else
				Commentary.log(sa, LogStatus.FAIL,"Tapped on today's balance, Tapped on Add transaction, Transaction details screen - Online CC Account is NOT preselected");	
			
		sa.assertAll();		 
	}
	
	@Test(priority = 9)
	public void AS10_Test() throws Exception {
		
		// adding transaction for online credit card account
		// verifying account stickiness from sOnlineChecking account
		
		SoftAssert sa = new SoftAssert();
		
		Helper h = new Helper();
//		Engine.getDriver().getContext();
//		if (h.getEngine().equals("android"))
//			Engine.ad.getContext();
//		else
//			Engine.iosd.getContext();
			

		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sOnlineCreditCard);
		tRec.setAmount("2.34");
		tRec.setTransactionType("income");
		
		Commentary.log(LogStatus.INFO, "ADD Transaction from RecentTransactions, Verifying Account Stickiness from account's transactions Screen");
		OverviewPage op = new OverviewPage(); 
		op.tapOnRecentTransactionsCard();
		
		AllAccountsPage aap = new AllAccountsPage();
		aap.addTransaction.click();
		Thread.sleep(1000);
		
		TransactionDetailPage td = new TransactionDetailPage();
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+sOnlineCreditCard+"]");
		aap.backButton.click();
		Thread.sleep(1000);
		
		op.navigateToAcctList();
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		bcc.getAccount(sOnlineChecking).click();
		
		aap.addTransaction.click();
		Thread.sleep(1000);
		td.buttonDone.click();
	
		if (td.VerifyTransactionAccount(sOnlineChecking))
				Commentary.log(sa, LogStatus.INFO,"Tapped on Online Checking account, Tapped on Add transaction, Transaction details screen - Online Checking Account is preselected");
		else
				Commentary.log(sa, LogStatus.FAIL,"Tapped on nline Checking account, Tapped on Add transaction, Transaction details screen - Online Checking Account is NOT preselected");	
			
		sa.assertAll();	
		 
	}
	
}
