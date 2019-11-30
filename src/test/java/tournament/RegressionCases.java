package tournament;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import com.relevantcodes.extentreports.LogStatus;
import com.saucelabs.saucerest.SauceREST;

import dugout.BankingAndCreditCardPage;
import dugout.InvestingPage;
import dugout.NetIncomeOverTimePage;
import dugout.OverviewPage;
import dugout.SettingsPage;
import dugout.SignInPage;
import dugout.SpendingOverTimePage;
import dugout.SpendingTrendPage;
import dugout.TransactionDetailPage;
import dugout.TransactionSummaryPage;
import dugout.TransactionsPage;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import referee.Commentary;
import referee.*;
import support.Engine;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;

public class RegressionCases extends Recovery {
	
	String sUserName = "yuvaraju.boligorla@quicken.com";
	String sPassword = "Intuit!1";
	//String sDataset = "TodaysBalancesTest";
	String sDataset = "ProjectedBalances";
	String sManualChecking = "Manual_Checking";
	String sOnlineChecking ="onl_checking1";
	String sManualCreditCard = "Manual_CC";
	String sOnlineCreditCard ="onl_cc";
	String sManualCash="Manual_cash";
	String sOnlineCash="onl_cash";
	String sManualSaving = "Manual_Savings";
	String sOnlineSaving = "onl_savings1";
	String backButton1_ios = "Banking & Credit";
	String s;

	
	// Add Transaction
	@Test(priority = 0)
	public void TC1_ValidateAddTransaction() throws Exception {
		String sChecking_before, sTotal_before, sChecking_after,sTotal_after ;
		SignInPage si = new SignInPage();
		si.signIn(sUserName, sPassword, sDataset);
		SoftAssert sa = new SoftAssert();

		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "Add an expense transaction for an manual checking account, verify checking & total balance on overview screen accounts card");
		String time = h.getCurrentTime();
		
		
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("5.00");
		tRec.setAccount(sManualChecking);
		tRec.setCategory("Internet");
		tRec.setPayee(time);
		tRec.setTransactionType("expense");
		tRec.setDate(h.getFutureDaysDate(0));
		h.getContext();
		
		OverviewPage op = new OverviewPage();
		sChecking_before = op.checkingBalance.getText();
		sTotal_before = op.totalBalance.getText();
		Commentary.log(LogStatus.INFO, "Checking balance before adding the transaction ["+sChecking_before+"]");
		Commentary.log(LogStatus.INFO, "Total balance before adding the transaction ["+sTotal_before+"]");
		Double d = Double.parseDouble(tRec.getAmount());
		Double dChecking_before = h.processBalanceAmount(sChecking_before);
		Double dTotal_before = h.processBalanceAmount(sTotal_before);
		
		op.addTransaction.click();
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount "+tRec.getAmount());
		
		
		h.getContext();
		sChecking_after = op.checkingBalance.getText();
		sTotal_after = op.totalBalance.getText();
		Double dChecking_after = h.processBalanceAmount(sChecking_after);
		Double dTotal_after = h.processBalanceAmount(sTotal_after);
		
		if (dChecking_before-d==dChecking_after)
			Commentary.log(LogStatus.INFO, "PASS: Checking balance was ["+dChecking_before+"], added expense transaction for ["+tRec.getAmount()+"], now the checking balance shows ["+dChecking_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Checking balance was ["+dChecking_before+"], added expense transaction for ["+tRec.getAmount()+"], now the checking balance shows ["+dChecking_after+"]");
		
		if (dTotal_before-d==dTotal_after)
			Commentary.log(LogStatus.INFO, "PASS: Total balance was ["+dTotal_before+"], added expense transaction for ["+tRec.getAmount()+"], now the total balance shows ["+dTotal_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Total balance was ["+dTotal_before+"], added expense transaction for ["+tRec.getAmount()+"], now the total balance shows ["+dTotal_after+"]");
		
		sa.assertAll();	
	
		
	}
	// Edit Transaction
	
	@Test(priority=1)
	public void TC2_ValidateEditTrasaction() throws Exception {
		
		String sChecking_before, sTotal_before, sChecking_after,sTotal_after ;
		Commentary.log(LogStatus.INFO, "EDIT an expense transaction for an manual checking account, verify checking & total balance on overview screen accounts card");


		TransactionsPage tp = new TransactionsPage();
		TransactionRecord tRec = new TransactionRecord();
		Helper h = new Helper();
	
		OverviewPage op = new OverviewPage();
		sChecking_before = op.checkingBalance.getText();
		sTotal_before = op.totalBalance.getText();
		Commentary.log(LogStatus.INFO, "Checking balance before adding the transaction ["+sChecking_before+"]");
		Commentary.log(LogStatus.INFO, "Total balance before adding the transaction ["+sTotal_before+"]");
		
		TransactionDetailPage td = new TransactionDetailPage();
		tp.searchTransaction("Internet");
		tp.tapOnFirstTransation();		
		SoftAssert sa = new SoftAssert();

		tRec.setAmount("10.00");
		tRec.setAccount(sManualChecking);
		tRec.setCategory("Hotel");
		tRec.setPayee("shop");
		tRec.setTransactionType("expense");
		tRec.setDate(h.getYesterdaysDate());
		Double txnAmount_before = h.processBalanceAmount(td.getTransactionAmount());
		System.out.println(txnAmount_before);
		td.addTransaction(tRec);
		h.getContext();
		
		System.out.println("Actual trans amount "+txnAmount_before);
		
		Double d = Double.parseDouble(tRec.getAmount());
		Double dChecking_before = h.processBalanceAmount(sChecking_before);
		Double dTotal_before = h.processBalanceAmount(sTotal_before);
		
		Double d_After = d+txnAmount_before;
		//System.out.println("D_AFTER "+ d_After);
		
		//td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type income, amount "+ tRec.getAmount());
		
		SettingsPage sp = new SettingsPage();
		
		sp.selectBack(backButton1_ios);
		sp.selectBack("Back");
		Thread.sleep(5000);
		
		sChecking_after = op.checkingBalance.getText();
		sTotal_after = op.totalBalance.getText();
		Double dChecking_after = h.processBalanceAmount(sChecking_after);
		Double dTotal_after = h.processBalanceAmount(sTotal_after);
		
		System.out.println("CHECKING BEFORE "+dChecking_before);
		System.out.println("CHECKING AFTER "+dChecking_after);
		//System.out.println("EXPECTED  "+(dChecking_before-d_After));
		
		
		
		if (dChecking_before-d_After==dChecking_after)
			Commentary.log(LogStatus.INFO, "PASS: Checking balance was ["+dChecking_before+"], added expense transaction for ["+tRec.getAmount()+"], now the checking balance shows ["+dChecking_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Checking balance was ["+dChecking_before+"], added expense transaction for ["+tRec.getAmount()+"], now the checking balance shows ["+dChecking_after+"]");
		
		if (dTotal_before-d_After==dTotal_after)
			Commentary.log(LogStatus.INFO, "PASS: Total balance was ["+dTotal_before+"], added expense transaction for ["+tRec.getAmount()+"], now the total balance shows ["+dTotal_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Total balance was ["+dTotal_before+"], added expense transaction for ["+tRec.getAmount()+"], now the total balance shows ["+dTotal_after+"]");
		
		sa.assertAll();	
		
	}
	//Verify Delete Transaction
	@Test(priority = 3)
	public void TC3_ValidateDeleteTransaction() throws Exception {
		Commentary.log(LogStatus.INFO, "Delete an expense transaction for an manual savings account, verify checking & total balance on overview screen accounts card");
		Helper h = new Helper();
		SoftAssert sa = new SoftAssert();
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		//bcc.txtTodaysBalance.click();
		bcc.allTransactionButton.click();
		
		TransactionsPage tp = new TransactionsPage();
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		String payeeName = h.getCurrentTime();
		
		tRec.setAmount("5.00");
		tRec.setAccount(sManualChecking);
		tRec.setCategory("Internet");
		tRec.setPayee(payeeName);
		tRec.setTransactionType("expense");
		h.getContext();
				
		tp.addTransaction.click();
		td.addTransaction(tRec);
		Thread.sleep(2000);
		
		tp.searchTransactionTxtField.click();
		tp.searchTransactionTxtField.sendKeys(payeeName);
		
		
		//assertTrue(tp.getPayeeName().getText().equals(payeeName), "Created Transaction is not displayed");	
		tp.tapOnFirstTransation();
		Thread.sleep(2000);
		
		op.scroll_down();
		td.deleteTransaction.click();
		if (Verify.objExists_check(td.deleteTransactionAlertButton)) {
		td.deleteTransactionAlertButton.click();
		}
		Thread.sleep(4000);
		
		
		tp.searchTransactionTxtField.click();
		tp.searchTransactionTxtField.clear();
		tp.searchTransactionTxtField.sendKeys(payeeName);
		
		if (Verify.objExists(tp.txtNoResultFound))
			Commentary.log(LogStatus.INFO, "PASS: Successfully Deleted the selected transaction");
		else
			Commentary.log(sa, LogStatus.FAIL, "Unable to Delete the selected transaction");
		
		sa.assertAll();		
		
	}	
	
	@Test(priority = 4)
	public void TC4_ValidateSwipe_Category() throws Exception {
	Commentary.log(LogStatus.INFO, "Verify Swipe gesture on transaction and change the category and check the transaction details");
		Helper h = new Helper();
		
		if (h.getEngine().equalsIgnoreCase("ios")) {
		OverviewPage o = new OverviewPage();
		o.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		//bcc.txtTodaysBalance.click();
		bcc.allTransactionButton.click();
		
		TransactionsPage tp = new TransactionsPage();
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		SoftAssert sa = new SoftAssert();
		String payeeName = h.getCurrentTime();
		
		tRec.setAmount("5.00");
		tRec.setAccount(sManualChecking);
		tRec.setCategory("Internet");
		tRec.setPayee(payeeName);
		tRec.setTransactionType("expense");
		h.getContext();
		
		tp.addTransaction.click();
		td.addTransaction(tRec);
		Thread.sleep(2000);
		
		tp.searchTransactionTxtField.click();
		tp.searchTransactionTxtField.sendKeys(payeeName);
		
		String afterPayeeName = tp.getPayeeName().getText();
		
		if (afterPayeeName.equals(payeeName)) 
			Commentary.log(LogStatus.INFO, "Payee is created and transaction is saved successfully");
		 else 
			Commentary.log(LogStatus.INFO, "Payee is Not created");
		
		
		tp.swipe_left();
		tp.btnCategory.click();
		
		tp.selectCategorySwipe("Mobile Phone");
		Thread.sleep(3000);
		
		tp.tapOnFirstTransation();
		
		if ((td.getCategory("Mobile Phone").getText()).equals("Mobile Phone"))
			Commentary.log(sa, LogStatus.PASS, "PASS: Successfully updated the category");
		else
			Commentary.log(sa, LogStatus.FAIL, "Unable to update the category");
		
		sa.assertAll();
		} else {
			Commentary.log(LogStatus.INFO, "Automation blocked for Android for Swipe feature");
		}
		
	}
	@Test(priority = 5)
	public void TC5_ValidateSwipe_Delete() throws Exception {
	Commentary.log(LogStatus.INFO, "Verify Swipe gesture on transaction and delete the transaction and check the transaction is deleted after searching");
		Helper h = new Helper();
		if (h.getEngine().equalsIgnoreCase("ios")) {
		OverviewPage o = new OverviewPage();
		o.navigateToAcctList();
		
		Commentary.log(LogStatus.INFO, "Validating Delete tranction from swipe gesture options");
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		//bcc.txtTodaysBalance.click();
		bcc.allTransactionButton.click();
		TransactionsPage tp = new TransactionsPage();
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		SoftAssert sa = new SoftAssert();
		String payeeName = h.getCurrentTime();
		
		tRec.setAmount("5.00");
		tRec.setAccount(sManualChecking);
		tRec.setCategory("Internet");
		tRec.setPayee(payeeName);
		tRec.setTransactionType("expense");
		h.getContext();
		
		tp.addTransaction.click();
		td.addTransaction(tRec);
		Thread.sleep(2000);
		
		tp.searchTransactionTxtField.click();
		tp.searchTransactionTxtField.sendKeys(payeeName);
		h.hideKeyBoard();
		
		tp.swipe_left();
		tp.btnDelete.click();
		//td.deleteTransactionAlertButton.click();
		Thread.sleep(3000);
		
		if (Verify.objExists(tp.txtNoResultFound))
			Commentary.log(LogStatus.INFO, "Successfully deleted the transaction");
		else
			Commentary.log(sa, LogStatus.FAIL, "Unable to delete the selected transaction");
		
		tp.searchTransactionTxtField.click();
		tp.searchTransactionTxtField.clear();
		tp.searchTransactionTxtField.sendKeys(payeeName);
		Thread.sleep(2000);
		
		if (Verify.objExists(tp.txtNoResultFound))
			Commentary.log(LogStatus.INFO, "PASS: Successfully deleted the transaction");
		else
			Commentary.log(sa, LogStatus.FAIL, "Unable to delete the selected transaction");
		
		sa.assertAll();
		} else {
			Commentary.log(LogStatus.INFO, "Automation blocked for Android for Swipe feature");
		}
	}

	
/*	@Test(priority = 6)
	public void TC6_ValidateSorting() throws Exception {
	//	Commentary.log(LogStatus.INFO, "Validate sorting of transaction list by amount and date");

		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		bcc.txtTodaysBalance.click();
		
		TransactionsPage tp = new TransactionsPage();
		Thread.sleep(3000);
		//tp.buttonSort.click();
		
		//tp.verifySorting();
		tp.getTransactionAmountValue_android();
		
	}*/
	@Test (priority = 7)
	public void TC7_ValidateHamburgerMenuOptions ()throws Exception {
		
		OverviewPage op = new OverviewPage();
		Commentary.log(LogStatus.INFO, "Validating hamburger menu options");
		
		op.hambergerIcon.click();
		//Thread.sleep(20000);
		SoftAssert sa = new SoftAssert();
		SettingsPage sp = new SettingsPage();
		Helper h = new Helper();
			
				if(sp.verifyQuickenID(sUserName))
					Commentary.log(LogStatus.INFO, "PASS: Quicken ID is displayed");
				else
					Commentary.log(sa, LogStatus.FAIL, "Quicken ID is NOT displayed");
				
				if (Verify.objExists(sp.closeButton))
					Commentary.log(LogStatus.INFO, "PASS: Close button is displayed");
				else 
					Commentary.log(sa, LogStatus.FAIL, "Close button is NOT displayed");
				
				if (Verify.objExists(sp.datasetDDButton))
					Commentary.log(LogStatus.INFO, "PASS: Dataset DD button button is displayed");
				else
					Commentary.log(sa, LogStatus.FAIL, "Dataset DD button button is NOT displayed");
				
				if (Verify.objExists(sp.accountTxt))
					Commentary.log(LogStatus.INFO, "PASS: Account Text is displayed");
				else
					Commentary.log(sa, LogStatus.FAIL, "Account Text is NOT displayed");
				
				if (Verify.objExists(sp.PasscodeTxt))
					Commentary.log(LogStatus.INFO, "PASS: Passcode text is displayed");
				else
					Commentary.log(sa, LogStatus.FAIL, "Passcode text is NOT displayed");
				
				if (Verify.objExists(sp.ManageAlertsTxt))
					Commentary.log(LogStatus.INFO, "PASS: Manage Alert text is displayed");
				else
					Commentary.log(sa, LogStatus.FAIL, "Manage Alert text is NOT displayed");
				
				if (Verify.objExists(sp.HelpLegalTxt))
					Commentary.log(LogStatus.INFO, "PASS: Help & Legal text is displayed");
				else
					Commentary.log(sa, LogStatus.FAIL, "Help & Legal text is NOT displayed");

				
				if (Verify.objExists(sp.logout))
					Commentary.log(LogStatus.INFO, "PASS: Logout button is displayed");
				else
					Commentary.log(sa, LogStatus.FAIL, "Logout button is NOT displayed");
				
				if (h.getEngine().equalsIgnoreCase("Android")) {
					if (Verify.objExists(sp.FeedbackTxt))
						Commentary.log(LogStatus.INFO, "PASS: Feedback Text is displayed");
					else
						Commentary.log(sa, LogStatus.FAIL, "Feedback Text is NOT displayed");
					} else {
						Commentary.log(LogStatus.INFO, "PASS: Feedback options is not supported for IOS Simulator");
					}
			
		sa.assertAll();
		
	}
	@Test(priority = 8)
	public void TC8_ValidateAccountMenu() throws Exception {
		
		Commentary.log(LogStatus.INFO, "Validating Account menu options and also verifying the details on selecting all user accounts");
		
		OverviewPage op = new OverviewPage();
		op.hambergerIcon.click();
		
		SettingsPage sp = new SettingsPage();
		sp.accountTxt.click();
		Thread.sleep(2000);
		
		MobileElement manualCheckingAccount = sp.getAccountElement(sManualChecking);
		MobileElement manualCCAccount = sp.getAccountElement(sManualCreditCard);
		MobileElement manualSavingsAccount = sp.getAccountElement(sManualSaving);
		
		SoftAssert sa = new SoftAssert();
		if (Verify.objExists(manualCheckingAccount))
			Commentary.log(LogStatus.INFO, "PASS: Manual Checking account is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Manual Checking account is NOT displayed");

		if (Verify.objExists(manualCCAccount))
			Commentary.log(LogStatus.INFO, "PASS: Manual CC account is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Manual CC account is NOT displayed");

		if (Verify.objExists(manualSavingsAccount))
			Commentary.log(LogStatus.INFO, "PASS: Manual Savings account is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Manual Savings account is NOT displayed");


		//Verify Account Details
		manualCheckingAccount.click();
		Thread.sleep(2000);
		MobileElement manualCheckingAccount1 = sp.getAccountElement(sManualChecking);
		MobileElement accountType = sp.getAccountElement("CHECKING");
		MobileElement accountStatus = sp.getTextView("Active");
	
		if (Verify.objExists(manualCheckingAccount1))
			Commentary.log(LogStatus.INFO, "PASS: Manual Checking account details are displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Manual Checking account details are NOT displayed");


		if (Verify.objExists(accountType))
			Commentary.log(LogStatus.INFO, "PASS: Manual Checking Account Type details are displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Manual Checking Account Type details are NOT displayed");


		if (Verify.objExists(accountStatus))
			Commentary.log(LogStatus.INFO, "PASS: Manual Checking Account Status is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Manual Checking Account Status is NOT displayed");


		
		sp.selectBack("Accounts");
		Thread.sleep(2000);
		
		manualCCAccount.click();
		Thread.sleep(2000);
		MobileElement manualCCAccount1 = sp.getAccountElement(sManualCreditCard);
		MobileElement accountType_manual = sp.getAccountElement("CREDIT_CARD");
		MobileElement accountStatus1 = sp.getTextView("Active");
		if (Verify.objExists(manualCCAccount1))
			Commentary.log(LogStatus.INFO, "PASS: Manual CC account details are displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Manual CC account details are NOT displayed");
		
		if (Verify.objExists(accountType_manual))
			Commentary.log(LogStatus.INFO, "PASS: Manaul CC Account Type details are displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Manaul CC Account Type details are NOT displayed");

		if (Verify.objExists(accountStatus1))
			Commentary.log(LogStatus.INFO, "PASS: Manaul CC Account Status is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Manaul CC Account Status is NOT displayed");


		sp.selectBack("Accounts");
		Thread.sleep(2000);
		
		manualSavingsAccount.click();
		Thread.sleep(2000);
		MobileElement manualSavingsAccount1 = sp.getAccountElement(sManualSaving);
		MobileElement accountType_savings = sp.getAccountElement("SAVINGS");
		MobileElement accountStatus2 = sp.getTextView("Active");

		
		if (Verify.objExists(manualSavingsAccount1))
			Commentary.log(LogStatus.INFO, "PASS: Savings account details are displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Savings account details are NOT displayed");
		
		if (Verify.objExists(accountType_savings))
			Commentary.log(LogStatus.INFO, "PASS: Savings Account Type details are displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Savings Account Type details are NOT displayed");
		
		if (Verify.objExists(accountStatus2))
			Commentary.log(LogStatus.INFO, "PASS: Savings Account Status is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Savings Account Status is NOT displayed");
		
		sa.assertAll();	

		
	}
	@Test(priority = 9)
	public void TC9_ValidatePasscodeFingerprintMenu() throws Exception {
	
		Commentary.log(LogStatus.INFO, "Validating menu options for Passcode and Fingerprint menu");
		OverviewPage op = new OverviewPage();
		op.hambergerIcon.click();
		Thread.sleep(4000);
		
		SettingsPage sp = new SettingsPage();
		sp.PasscodeTxt.click();
		Thread.sleep(2000);
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		if (Verify.objExists(sp.PasscodeHeaderTxt))
			Commentary.log(LogStatus.INFO, "PASS: Header text is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Header text is NOT displayed");
		if (Verify.objExists(sp.getAccountElement("Use Quicken Passcode")))
			Commentary.log(LogStatus.INFO, "PASS: Passcode text is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Passcode text is NOT displayed");
		
		if (h.getEngine().equalsIgnoreCase("ios")){
			if (Verify.objExists(sp.getTextView("Use Touch ID")))
				Commentary.log(LogStatus.INFO, "PASS: Touch ID text is displayed");
			else
				Commentary.log(sa, LogStatus.FAIL, "Touch ID text is NOT displayed");
			
			sa.assertAll();
		}	
	}
	@Test(priority = 10)
	public void TC10_ValidateManageAlerts() throws Exception {
		Commentary.log(LogStatus.INFO, "Validating menu options for Manage Alerts menu");

		OverviewPage op = new OverviewPage();
		op.hambergerIcon.click();
		
		SettingsPage sp = new SettingsPage();
		sp.ManageAlertsTxt.click();
		Thread.sleep(2000);
		SoftAssert sa = new SoftAssert();
		
		if (Verify.objExists(sp.ManageAlertsHeaderTxt))
			Commentary.log(LogStatus.INFO, "PASS: Manage Alert Header text is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Header Alert header text is NOT displayed");
		
		if (Verify.objExists(sp.getTextView("New Charge - Quicken Card (Mobile Only)")))
			Commentary.log(LogStatus.INFO, "PASS: New charge message text is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "New charge message text is NOT displayed");
		
		if (Verify.objExists(sp.getTextView("Push Notification")))
			Commentary.log(LogStatus.INFO, "PASS: Push Notification text is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Push Notification text is NOT displayed");
		
		sa.assertAll();

		
	}
	@Test(priority = 11)
	public void TC11_ValidateHelpLegal() throws Exception {
		Commentary.log(LogStatus.INFO, "Validating menu options for Help and Legal menu");

		OverviewPage op = new OverviewPage();
		op.hambergerIcon.click();
		
		SettingsPage sp = new SettingsPage();
		sp.HelpLegalTxt.click();
		Thread.sleep(2000);
		
		SoftAssert sa = new SoftAssert();
		
		MobileElement help = sp.getTextView("Help");
		MobileElement link_SupportWebsite = sp.getTextView("Support Website");
		MobileElement link_Acknowledgements = sp.getTextView("Acknowledgements");
		MobileElement link_LicenseAgreement = sp.getTextView("License Agreement");
		MobileElement link_Privacy = sp.getTextView("Privacy");
		
		if (Verify.objExists(sp.HelpLegalHeaderTxt))
			Commentary.log(LogStatus.INFO, "PASS: Help Legal text is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Help Legal text is NOT displayed");
		
		if (Verify.objExists(help))
			Commentary.log(LogStatus.INFO, "PASS: Help text is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Help text is NOT displayed");
		
		if (Verify.objExists(link_SupportWebsite))
			Commentary.log(LogStatus.INFO, "PASS: Support Website Link is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Support Website Link is NOT displayed");
		
		if (Verify.objExists(sp.getTextView("Legal")))
			Commentary.log(LogStatus.INFO, "PASS: Legal text is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Legal text is NOT displayed");
		
		if (Verify.objExists(link_Acknowledgements))
			Commentary.log(LogStatus.INFO, "PASS: Acknowledgements link is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Acknowledgements link is NOT displayed");
		
		if (Verify.objExists(link_LicenseAgreement))
			Commentary.log(LogStatus.INFO, "PASS: License Agreement link is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "License Agreement link is NOT displayed");
		
		if (Verify.objExists(link_Privacy))
			Commentary.log(LogStatus.INFO, "PASS: Privacy link is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Privacy link is NOT displayed");
		
		sa.assertAll();
		
		/*	
		link_SupportWebsite.click();
		Thread.sleep(10000);
		
		sa.assertAll();
	
		MobileElement menuOptionButton = (MobileElement) Engine.ad.findElementById("support-nav-toggle");
		referee.Verify.waitForObject(menuOptionButton, 10000);
		menuOptionButton.click();
		
		sp.selectBack();
		
		link_Acknowledgements.click();
		Thread.sleep(5000);
		
		MobileElement menuOptionButton1 = (MobileElement) Engine.ad.findElementsByClassName("navbar-toggle collapsed");
		referee.Verify.waitForObject(menuOptionButton1, 10000);
		menuOptionButton1.click();


		//referee.Verify.waitForObject(me, wttime)
	*/
	}
//	@Test(priority = 12)
//	public void TC12_ValidateDatasetPicker  () throws Exception {
//		Commentary.log(LogStatus.INFO, "Validating by changing existing Dataset after login and verify that account are loaded");
//
//		OverviewPage op = new OverviewPage();
//		op.hambergerIcon.click();
//		Thread.sleep(1000);
//		
//		SettingsPage sp = new SettingsPage();		
//		sp.datasetDDButton.click();
//		Thread.sleep(1000);
//		
//		MobileElement dataset1 = sp.getTextView("Appium_TodaysBalanceTest");
//		MobileElement dataset2 = sp.getTextView("TodaysBalancesTest");
//		MobileElement dataset3 = sp.getTextView("budgets");
//		MobileElement dataset4 = sp.getTextView("account_stickiness");
//		MobileElement dataset5 = sp.getTextView("lab2_1_yuv");
//		MobileElement dataset6 = sp.getTextView("yuv_123");
//		
//		
//		SoftAssert sa = new SoftAssert();
//		
//		if (Verify.objExists(dataset1))
//			Commentary.log(LogStatus.INFO, "PASS: Dataset1 is displayed");
//		else
//			Commentary.log(sa, LogStatus.FAIL, "Dataset1 is NOT displayed");
//		
//		if (Verify.objExists(dataset2))
//			Commentary.log(LogStatus.INFO, "PASS: Dataset2 is displayed");
//		else
//			
//			Commentary.log(sa, LogStatus.FAIL, "Dataset2 is NOT displayed");
//		
//		if (Verify.objExists(dataset3))
//			Commentary.log(LogStatus.INFO, "PASS: Dataset3 is displayed");
//		else
//			Commentary.log(sa, LogStatus.FAIL, "Dataset3 is NOT displayed");
//		
//		if (Verify.objExists(dataset4))
//			Commentary.log(LogStatus.INFO, "PASS: Dataset4 is displayed");
//		else
//			Commentary.log(sa, LogStatus.FAIL, "Dataset4 is NOT displayed");
//		
//		if (Verify.objExists(dataset5))
//			Commentary.log(LogStatus.INFO, "PASS: Dataset5 is displayed");
//		else
//			Commentary.log(sa, LogStatus.FAIL, "Dataset5 is NOT displayed");
//		
////		if (Verify.objExists(dataset6))
////			Commentary.log(LogStatus.INFO, "PASS: Dataset6 is displayed");
////		else
////			Commentary.log(sa, LogStatus.FAIL, "Dataset6 is NOT displayed");
//		
//		dataset1.click();
//		Thread.sleep(2000);
//		
//		referee.Verify.waitForObject(op.hambergerIcon, 3000);
//		op.hambergerIcon.click();
//		Thread.sleep(1000);
//		
//		
//		sa.assertTrue(sp.verifyCloudAccountName("Appium_TodaysBalanceTest"), "Incorrect dataset is selected");
//		sp.datasetDDButton.click();
//		MobileElement dataset7 = sp.getTextView("ProjectedBalances");
//
//		Thread.sleep(2000);	
//
//		dataset7.click();
//		Thread.sleep(2000);
//		op.hambergerIcon.click();
//		Thread.sleep(1000);
//		
//		sa.assertTrue(sp.verifyCloudAccountName("ProjectedBalances"), "Incorrect dataset is selected");
//		sa.assertAll();
//
//	}
	
	
	@Test (priority=13)
	public void TC15_VerifyTransactionSummary() throws Exception {
		
		Commentary.log(LogStatus.INFO, "Validating Transaction Summary details");
		OverviewPage op = new OverviewPage();
		op.tapOnTransactionSummaryCard();
		SoftAssert sa = new SoftAssert();
		
		TransactionSummaryPage ts = new TransactionSummaryPage();
		
		if (Verify.objExists(ts.backButtonOnHeader))
			Commentary.log(LogStatus.INFO, "PASS: Back button is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Back button is not displayed");
		
		if (Verify.objExists(ts.transactionSummaryHeader))
			Commentary.log(LogStatus.INFO, "PASS: Transaction Summary header is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Transaction Summary is not displayed");

		if (Verify.objExists(ts.categoryTab))
			Commentary.log(LogStatus.INFO, "PASS: Category button is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Category button is not displayed");
		
		if (Verify.objExists(ts.payeeTab))
			Commentary.log(LogStatus.INFO, "PASS: Payee button is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Payee button is not displayed");
		
		if (Verify.objExists(ts.getCurrentMonthYear()))
			Commentary.log(LogStatus.INFO, "PASS: Current month and year text is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Current month and year text is Not displayed");
			
		sa.assertAll();
		
		
	}
	@Test (priority=14)
	public void TC15_VerifyTransactionSummary_PayeeScreen() throws Exception {
		Commentary.log(LogStatus.INFO, "Adding an expense transaction and validating that the Payee details are updated on Transaction Summary page");
		OverviewPage op = new OverviewPage();
		Helper h = new Helper();
		op.tapOnTransactionSummaryCard();
		SoftAssert sa = new SoftAssert();
		
		TransactionSummaryPage ts = new TransactionSummaryPage();
		ts.payeeTab.click();
		
		String sCategoryAmount_before = ts.payeeTile.getText();
		System.out.println("sCategoryAmount_before----->>> "+sCategoryAmount_before);
		Double dCategoryAmount_before = h.processBalanceAmount(sCategoryAmount_before.replace("walmart ", ""));
		System.out.println("Category amount is "+dCategoryAmount_before);
		
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("5.00");
		tRec.setAccount(sManualChecking);
		tRec.setPayee("walmart");
		tRec.setTransactionType("expense");
		h.getContext();
		
		ts.backButtonOnHeader.click();
		
		op.scrollToTop();		
		
		op.addTransaction.click();
		td.addTransaction(tRec);
		
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount "+tRec.getAmount());
		
		op.tapOnTransactionSummaryCard();
		ts.payeeTab.click();
		
		String sCategoryAmount_after = ts.payeeTile.getText();
		Double dCategoryAmount_after = h.processBalanceAmount(sCategoryAmount_after.replace("walmart ", ""));
		Double d = Double.parseDouble(tRec.getAmount());
		System.out.println("Category amount is "+dCategoryAmount_after);
		
		if (dCategoryAmount_after+d==dCategoryAmount_before)
			Commentary.log(LogStatus.INFO, "PASS: Payee tile is updated after adding expense transaction for selected payee");
		else
			Commentary.log(sa, LogStatus.FAIL, "Payee tile is NOT updated after adding expense transaction for selected payee");
		
		sa.assertAll();
		
	}
	@Test (priority=15)
	public void TC15_VerifyTransactionSummary_CategoryScreen() throws Exception {
		Commentary.log(LogStatus.INFO, "Adding an expense transaction and validating that the Category details are updated on Transaction Summary page");
		OverviewPage op = new OverviewPage();
		Helper h = new Helper();
		op.tapOnTransactionSummaryCard();
		SoftAssert sa = new SoftAssert();
		
		TransactionSummaryPage ts = new TransactionSummaryPage();
		ts.categoryTab.click();
		
		String sCategoryAmount_before = ts.categoryTile.getText();
		Double dCategoryAmount_before = h.processBalanceAmount(sCategoryAmount_before.replace("Internet ", ""));
		System.out.println("Category amount is "+dCategoryAmount_before);
		
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("10.00");
		tRec.setAccount(sManualChecking);
		//tRec.setPayee("walmart");
		tRec.setCategory("Internet");
		tRec.setTransactionType("expense");
		h.getContext();
		
		ts.backButtonOnHeader.click();
		
		op.scrollToTop();
		
		op.addTransaction.click();
		td.addTransaction(tRec);
		
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount "+tRec.getAmount());
		
		op.tapOnTransactionSummaryCard();
		ts.categoryTab.click();
		
		String sCategoryAmount_after = ts.categoryTile.getText();
		Double dCategoryAmount_after = h.processBalanceAmount(sCategoryAmount_after.replace("Internet ", ""));
		Double d = Double.parseDouble(tRec.getAmount());
		System.out.println("Category amount is "+dCategoryAmount_after);
		
		if (dCategoryAmount_after+d==dCategoryAmount_before)
			Commentary.log(LogStatus.INFO, "PASS: Category tile is updated after adding expense transaction for selected payee");
		else
			Commentary.log(sa, LogStatus.FAIL, "Category tile is NOT updated after adding expense transaction for selected payee");
		
		sa.assertAll();
		
	}
	@Test (priority=16)
	public void TC15_SpendingTrendCard() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Commentary.log(LogStatus.INFO, "Verify Category amounts on trending screen should appear in descending order");
			
		// get balances from accounts card
		OverviewPage op = new OverviewPage();
		op.tapOnTrendingCard();
		
		SpendingTrendPage st = new SpendingTrendPage();
		
		Double d1 = st.getAmount();
		st.scrollCategory();
		Double d2 = st.getAmount();
		
		st.scrollCategory();
		Double d3 = st.getAmount();
		
		st.scrollCategory();
		Double d4 = st.getAmount();
				
		if (d1 >= d2 )
			Commentary.log(LogStatus.INFO, "PASS: Total for First Category  ["+d1+"]  is greater than or equal to second category ["+d2+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "Total for First Category  ["+d1+"]  should be greater than or equal to second category ["+d2+"]");
		
		
		if (d2 >= d3)
			Commentary.log(LogStatus.INFO, "PASS: Total for second Category  ["+d2+"]  is greater than or equal to third category ["+d3+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "Total for second Category  ["+d2+"]  should be greater than or equal to third category ["+d3+"]");
		
		if (d3 >= d4)
			Commentary.log(LogStatus.INFO, "PASS: Total for second Category  ["+d3+"]  is greater than or equal to third category ["+d4+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "Total for second Category  ["+d3+"]  should be greater than or equal to third category ["+d4+"]");
		
		sa.assertAll();
			
	}
	@Test(priority=17)
	public void TC16_VerifyFilterForSpendingTrendCard() throws Exception {
		SoftAssert sa = new SoftAssert();
		Commentary.log(LogStatus.INFO, "Verify filter chips on trending screen should appear and selecting it reflects the category");
			
		// get balances from accounts card
		OverviewPage op = new OverviewPage();
		op.tapOnTrendingCard();
		
		SpendingTrendPage st = new SpendingTrendPage();
		
		if (Verify.objExists(st.last30Days))
			Commentary.log(sa, LogStatus.INFO, "Pass: Last 30 days filter chip is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Fail: Last 30 days filter chip is Not displayed");
		st.last30Days.click();
		
		if (Verify.objExists(st.thisMonth))
			Commentary.log(sa, LogStatus.INFO, "Pass: This month filter chip is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Fail: This month filter chip is Not displayed");
		st.thisMonth.click();
		
		if (Verify.objExists(st.lastMonth))
			Commentary.log(sa, LogStatus.INFO, "Pass: Last month filter chip is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Fail: Last month filter chip is Not displayed");
		st.lastMonth.click();
		
		if (Verify.objExists(st.monthToDate))
			Commentary.log(sa, LogStatus.INFO, "Pass: Month to date filter chip is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Fail: Month to date filter chip is Not displayed");
		st.monthToDate.click();
		
		st.scrollFilter();
		
		if (Verify.objExists(st.yearToDate))
			Commentary.log(sa, LogStatus.INFO, "Pass: Year to date filter chip is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Fail: Year to date filter chip is Not displayed");
		st.yearToDate.click();
		
		sa.assertAll();
	}
	
	@Test (priority=18)
	public void VerifySpendingOverTimeCard () throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		String sMonth = h.getLastSixMonths()[0];
		String sActual;
		Commentary.log(LogStatus.INFO, "Verify by default current month is highlighted/selected on the graph");
		
		
		// get balances from accounts card
		OverviewPage op = new OverviewPage();
		op.tapOnSpendingOverTimeCard();
		
		SpendingOverTimePage sot = new SpendingOverTimePage();
		sActual = sot.getSelectedMonth();
		
		if (sActual.equals(sMonth))
			Commentary.log(LogStatus.INFO, "PASS: Spending OverTime screen, by default current month "+sMonth+" is selected on the graph");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Spending OverTime screen by default current month ["+sMonth+"] should be selected but "+sActual +" was seen");	
		
		sa.assertAll();
		
	}
	
	@Test(priority = 19)
	public void VerifySpendingOverTimeCard_LastSixMonth() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		Commentary.log(LogStatus.INFO, "Verify past six months names appear on the Spending Over Time screen");
		
		// get balances from accounts card
		OverviewPage op = new OverviewPage();
		op.tapOnSpendingOverTimeCard();
		
		String [] lastSixMonths = h.getLastSixMonths();
		
		Integer iCount;
		
		SpendingOverTimePage sot = new SpendingOverTimePage();
		
		for (iCount=0; iCount<lastSixMonths.length; iCount++) {
			if (sot.verifyMonth(lastSixMonths[iCount]))
				Commentary.log(LogStatus.INFO, "PASS: Spending OverTime ["+lastSixMonths[iCount]+"] month appeared on the screen");
			else
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Spending OverTime Card ["+lastSixMonths[iCount]+"] didn't appear on the screen");	
			
		}
		
		sa.assertAll();
		
	}
	
	@Test(priority = 20)
	public void VerifySpendingOverTimeCard_MonthlyDisplay() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		Commentary.log(LogStatus.INFO, "Verify tapping on the month's graph display the particular month's data");
		
		
		// get balances from accounts card
		OverviewPage op = new OverviewPage();
		op.tapOnSpendingOverTimeCard();
		
		String [] lastSixMonths = h.getLastSixMonths();
		
		Integer iCount;
		
		SpendingOverTimePage sot = new SpendingOverTimePage();
		
		for (iCount=0; iCount<lastSixMonths.length; iCount++) {
			
			sot.tapOnTheMonth(lastSixMonths[iCount]);
			
			if (sot.verifyMonth(lastSixMonths[iCount]))
				Commentary.log(LogStatus.INFO, "PASS: Spending OverTime > tapping on the month ["+lastSixMonths[iCount]+"] displayed the month's name in total spending");
			else
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Spending OverTime Card > tapping on the month ["+lastSixMonths[iCount]+"] didn't display the month's name in total spending");	
			
		}
		
		sa.assertAll();	
	}
	
	
	
	@Test(priority = 21)
	public void VerifyNetIncomeOverTimeCard() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		String sMonth = h.getLastSixMonths()[0];
		String sActual;
		Commentary.log(LogStatus.INFO, "Verify by default current month is highlighted/selected on the graph");
		
		// get balances from accounts card
		OverviewPage op = new OverviewPage();
		op.tapOnNetIncomeOverTimeCard();
		
		NetIncomeOverTimePage not = new NetIncomeOverTimePage();
		sActual = not.getSelectedMonth();
		
		if (sActual.equals(sMonth))
			Commentary.log(LogStatus.INFO, "PASS: NetIncome OverTime screen, by default current month "+sMonth+" is selected on the graph");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: NetIncome OverTime screen by default current month ["+sMonth+"] should be selected but "+sActual +" was seen");	
		
		sa.assertAll();
		
	}
	
	@Test(priority = 22)
	public void VerifyNetIncomeOverTimeCard_LastSixMonth() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		Commentary.log(LogStatus.INFO, "Verify past six months names appear on the NetIncome Over Time screen");
		
		// get balances from accounts card
		OverviewPage op = new OverviewPage();
		op.tapOnNetIncomeOverTimeCard();
		
		String [] lastSixMonths = h.getLastSixMonths();
		
		Integer iCount;
		
		NetIncomeOverTimePage not = new NetIncomeOverTimePage();
		
		for (iCount=0; iCount<lastSixMonths.length; iCount++) {
			if (not.verifyMonth(lastSixMonths[iCount]))
				Commentary.log(LogStatus.INFO, "PASS: NetIncome OverTime ["+lastSixMonths[iCount]+"] month appeared on the screen");
			else
				Commentary.log(sa, LogStatus.FAIL, "FAIL: NetIncome OverTime Card ["+lastSixMonths[iCount]+"] didn't appear on the screen");	
			
		}
		
		sa.assertAll();
		
	}
	
//	@Test(priority = 23)
//	public void VerifyNetIncomeOverTimeCard_MonthlyDisplay() throws Exception {
//		
//		SoftAssert sa = new SoftAssert();
//		Helper h = new Helper();
//		Commentary.log(LogStatus.INFO, "Verify tapping on the month's graph display the particular month's data");
//		
//		// get balances from accounts card
//		OverviewPage op = new OverviewPage();
//		op.tapOnNetIncomeOverTimeCard();
//		
//		String [] lastSixMonths = h.getLastSixMonths();
//		
//		Integer iCount;
//		
//		NetIncomeOverTimePage not = new NetIncomeOverTimePage();
//		
//		for (iCount=0; iCount<lastSixMonths.length; iCount++) {
//			
//			not.tapOnTheMonth(lastSixMonths[iCount]);
//			
//			if (not.verifyMonth(lastSixMonths[iCount]))
//				Commentary.log(LogStatus.INFO, "PASS: NetIncome OverTime > tapping on the month ["+lastSixMonths[iCount]+"] displayed the month's name in total spending");
//			else
//				Commentary.log(sa, LogStatus.FAIL, "FAIL: NetIncome OverTime Card > tapping on the month ["+lastSixMonths[iCount]+"] didn't display the month's name in total spending");	
//			
//		}
//		
//		sa.assertAll();	
//	}
	
	@Test(priority=24)
	public void ValidateBalancesOnAccountCard() throws Exception{
		
		String sChecking, sCredit, sSaving, sTotal;
		Commentary.log(LogStatus.INFO, "Verify balances on accounts card");
		OverviewPage op = new OverviewPage();
		sChecking = op.checkingBalance.getText();
		sCredit = op.creditBalance.getText();
		sSaving = op.savingsBalance.getText();
		sTotal = op.totalBalance.getText();
		
		Helper h = new Helper();
		SoftAssert sa = new SoftAssert();
		Double dChecking = h.processBalanceAmount(sChecking);
		Double dCredit = h.processBalanceAmount(sCredit);
		Double dSaving = h.processBalanceAmount(sSaving);
		double dTotal = h.processBalanceAmount(sTotal);

		Double eTotal = (double) Math.round((dChecking+dCredit+dSaving)*100);
		eTotal = eTotal/100;
		
		if (eTotal==dTotal)
			Commentary.log(LogStatus.INFO, "PASS: Overview Page > Balances and Total amount are correct");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Overview Page > Balances and Total amount is incorrect");
		
		//Navigate to Account cards
		op.navigateToAcctList();
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		
		
		sChecking = bcc.checkingBalance.getText();
		sCredit = bcc.creditCardBalance.getText();
		sSaving = bcc.savingsBalance.getText();
		sTotal = bcc.txtTodaysBalanceAmount.getText();
		
		dChecking = h.processBalanceAmount(sChecking.replace("SubTotal: ", ""));
		dCredit = h.processBalanceAmount(sCredit.replace("SubTotal: ", ""));
		dSaving = h.processBalanceAmount(sSaving.replace("SubTotal: ", ""));
		dTotal = h.processBalanceAmount(sTotal);
		
		eTotal = (double) Math.round((dChecking+dCredit+dSaving)*100);
		eTotal = eTotal/100;

		if (eTotal==dTotal)
			Commentary.log(LogStatus.INFO, "PASS: Account card Page > Balances and Total amount are correct");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Account card Page > Balances and Total amount is incorrect");
		
		sa.assertAll();
	}
	
	@Test (priority=25)
	public void TC14_ValidateInvestmentCard() throws Exception {
		Commentary.log(LogStatus.INFO, "Validating Investment Card details");
		OverviewPage op = new OverviewPage();
		op.hambergerIcon.click();
		Thread.sleep(1000);
		
		SettingsPage sp = new SettingsPage();
		sp.datasetDDButton.click();
		MobileElement investmentDataset = sp.getTextView("investment_auto");
		investmentDataset.click();
		Thread.sleep(3000);
		
		op.tapOnInvestingCard();
		SoftAssert sa = new SoftAssert();
		
		InvestingPage ip = new InvestingPage();
		
		if (Verify.objExists(ip.investingHeader))
			Commentary.log(LogStatus.INFO, "Investment Header text is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Investment Header text is NOT displayed");
		
		if (Verify.objExists(ip.securitiesTab))
			Commentary.log(LogStatus.INFO, "Security Tab is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Security Tab is NOT displayed");
		
		if (Verify.objExists(ip.accountsTab))
			Commentary.log(LogStatus.INFO, "Accounts Tab is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Accounts Tab is NOT displayed");
		
		if (Verify.objExists(ip.watchlistTab))
			Commentary.log(LogStatus.INFO, "Watchlist Tab is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Watchlist Tab is NOT displayed");
		
		if (Verify.objExists(ip.totalValueLabel))
			Commentary.log(LogStatus.INFO, "Total Value label is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Total Value label is NOT displayed");
		
		if (Verify.objExists(ip.cashbalancesLabel))
			Commentary.log(LogStatus.INFO, "Cash Balances label is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Cash Balances label is NOT displayed");
		
		if (Verify.objExists(ip.backButtonOnHeader))
			Commentary.log(LogStatus.INFO, "Back Button is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Back Button is NOT displayed");
		
		if (Verify.objExists(ip.securitiesByCompanyNameLabel))
			Commentary.log(LogStatus.INFO, "Security by company label is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Security by company label is NOT displayed");
		
		if (Verify.objExists(ip.lastSyncedFooter))
			Commentary.log(LogStatus.INFO, "Last Synced Footer is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Last Synced Footer is NOT displayed");
		
		sa.assertAll();
		
	}
	@Test (priority=26)
	public void TC14_ValidateForZeroDatset_RecentTxnCard() throws Exception {
		Commentary.log(LogStatus.INFO, "Validating message displayed for all Card in case of zero dataset");
		OverviewPage op = new OverviewPage();
		SoftAssert sa = new SoftAssert();
		
		op.hambergerIcon.click();
		Thread.sleep(1000);
		
		SettingsPage sp = new SettingsPage();
		sp.datasetDDButton.click();
		MobileElement investmentDataset = sp.getTextView("ZeroDataSet");
		investmentDataset.click();
		Thread.sleep(3000);
		
		op.scrollTillCard("Top Trending Categories");
		
		String actText_TxnOverviewPage = op.recentTxns_NoTxnsAvaialable.getText();
		
		if (actText_TxnOverviewPage.equals("No Transaction available"))
			Commentary.log(LogStatus.INFO, "PASS: OverviewPage RecentTransaction card > Correct message is displayed in case user has no recent transactions");
		else
			Commentary.log(sa, LogStatus.FAIL, "OverviewPage RecentTransaction card > Message is not displayed in case user has no recent transactions");
		//Navigate to Recent Transaction card
		op.tapOnRecentTransactionsCard();
		
		TransactionsPage tp = new TransactionsPage();
		String actText_txnDetailsScreen = tp.noTransactionText.getText();
				
		if (actText_txnDetailsScreen.equals("You don't have any transactions."))
			Commentary.log(LogStatus.INFO, "PASS: Recent Transaction page > Correct message is displayed in case user has no recent transactions");
		else
			Commentary.log(sa, LogStatus.FAIL, "Recent Transaction page > Message is not displayed in case user has no recent transactions");
		
		
		sa.assertAll();
		
	}
//	@Test (priority=27)
//	public void TC14_ValidateForZeroDatset_TrendingCard() throws Exception {
//		Commentary.log(LogStatus.INFO, "Validating message displayed on Trending Card in case of no transactions");
//		SoftAssert sa = new SoftAssert();
//		
//		OverviewPage op = new OverviewPage();
//		Helper h = new Helper();
//		if (h.getEngine().equalsIgnoreCase("android")){
//			op.scrollTillCard("Top Trending Categories");
//			}
//		else {
//			op.scrollTillCard("Spending Over Time");
//			}
//		String actText_TxnTrendingPage = op.tredningCard_YoudontHaveAnyTxn.getText();
//		
//		if (actText_TxnTrendingPage.equals("You don't have any transactions."))
//			Commentary.log(LogStatus.INFO, "PASS: Trending card > Correct message is displayed in case user has no transactions");
//		else
//			Commentary.log(sa, LogStatus.FAIL, "Trending card > Message is not displayed in case user has no transactions");
//		
//		//Navigate to Trending card
//		op.tapOnTrendingCard();
//		
//		SpendingTrendPage stp = new SpendingTrendPage();
//
//		String actText_trendindDetailScreen = stp.youDontHaveAnyTxns.getText();
//				
//		if (actText_trendindDetailScreen.equals("You don't have any transactions."))
//			Commentary.log(LogStatus.INFO, "PASS: Trending page > Correct message is displayed in case user has no transactions");
//		else
//			Commentary.log(sa, LogStatus.FAIL, "Trending page > Message is not displayed in case user has no transactions");
//		
//		
//		sa.assertAll();
//		
//	}
//	@Test (priority=28)
//	public void TC14_ValidateForZeroDatset_SpendingOverTimeCard() throws Exception {
//		Commentary.log(LogStatus.INFO, "Validating message displayed on Spending Over Time Card in case of no transactions");
//		SoftAssert sa = new SoftAssert();
//		
//		OverviewPage op = new OverviewPage();
//		op.scrollTillCard("Net Income Over Time");
//		
//		String actText_SpendingOverTimePage = op.spendingOverTimeCard_YoudontHaveAnyTxn.getText();
//		
//		if (actText_SpendingOverTimePage.equals("You don't have any transactions."))
//			Commentary.log(LogStatus.INFO, "PASS: Spending Over Time card > Correct message is displayed in case user has no transactions");
//		else
//			Commentary.log(sa, LogStatus.FAIL, "Spending Over Time card > Message is not displayed in case user has no transactions");
//		
//		//Navigate to Trending card
//		op.tapOnSpendingOverTimeCard();
//		
//		SpendingOverTimePage sot = new SpendingOverTimePage();
//
//		String actText_sotDetailsScreen = sot.youDontHaveAnyTxns.getText();
//				
//		if (actText_sotDetailsScreen.equals("You don't have any transactions."))
//			Commentary.log(LogStatus.INFO, "PASS: Spending Over Time page > Correct message is displayed in case user has no transactions");
//		else
//			Commentary.log(sa, LogStatus.FAIL, "Spending Over Time page > Message is not displayed in case user has no transactions");
//		
//		
//		sa.assertAll();
//		
//	}
	@Test (priority=29)
	public void TC14_ValidateForZeroDatset_NetIncomeOverTimeCard() throws Exception {
		Commentary.log(LogStatus.INFO, "Validating message displayed on Net Income Over Time Card in case of no transactions");
		SoftAssert sa = new SoftAssert();
		
		OverviewPage op = new OverviewPage();
			
		//Navigate to Trending card
		op.tapOnNetIncomeOverTimeCard();
		
		NetIncomeOverTimePage not = new NetIncomeOverTimePage();

		String actText_notDetailsScreen = not.youDontHaveAnyTxns.getText();
				
		if (actText_notDetailsScreen.equals("You don't have any transactions."))
			Commentary.log(LogStatus.INFO, "PASS: Net Income Over Time page > Correct message is displayed in case user has no transactions");
		else
			Commentary.log(sa, LogStatus.FAIL, "Net Income Over Time page > Message is not displayed in case user has no transactions");
		
		
		sa.assertAll();
		
	}
	@Test (priority=30)
	public void TC14_ValidateForZeroDatset_TransactionSummaryCard() throws Exception {
		Commentary.log(LogStatus.INFO, "Validating message displayed on Transaction Summary Card in case of no transactions");
		SoftAssert sa = new SoftAssert();
		
		OverviewPage op = new OverviewPage();
			
		//Navigate to Transaction Summary card
		op.tapOnTransactionSummaryCard();
		
		TransactionSummaryPage ts = new TransactionSummaryPage();

		String actText_summaryDetailsScreen = ts.noTransactionCategory.getText();
				
		if (actText_summaryDetailsScreen.equals("No Transactions by Category"))
			Commentary.log(LogStatus.INFO, "PASS: Transaction Summary page > Correct message is displayed in case user has no transactions");
		else
			Commentary.log(sa, LogStatus.FAIL, "Transaction Summary page > Message is not displayed in case user has no transactions");
		
		ts.payeeTab.click();
		
		String actText_noTxnPayee = ts.noTransactionPayee.getText();
		
		if (actText_noTxnPayee.equals("No Transactions by Payee"))
			Commentary.log(LogStatus.INFO, "PASS: Transaction Summary page > Correct message is displayed in case user has no transactions");
		else
			Commentary.log(sa, LogStatus.FAIL, "Transaction Summary page > Message is not displayed in case user has no transactions");
		
		sa.assertAll();
		
	}
/*	
	public static void buildUpload () throws IOException {
	SauceREST r = new SauceREST("kalyan_grandhi", "10fde941-0bec-4273-bca6-c7c827f36234");
    File f = new File("/Users/vgupta/Downloads/Quicken(36.16645.4012)-Release.app.zip");
    String response = r.uploadFile(f, "Quicken(36.16645.4012)-Release.app.zip", true);
    System.out.println("Sauce Upload Response -->> "+response);
    System.out.println("Completed..uploading build to SAUCE storage");
	} 

	public static void main(String[] args) throws IOException {
		buildUpload();
    } 
*/
}



