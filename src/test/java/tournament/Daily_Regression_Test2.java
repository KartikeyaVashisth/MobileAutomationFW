package tournament;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.BankingAndCreditCardPage;
import dugout.InvestingPage;
import dugout.NetIncomeOverTimePage;
import dugout.OverviewPage;
import dugout.SettingsPage;
import dugout.SpendingOverTimePage;
import dugout.TransactionDetailPage;
import dugout.TransactionSummaryPage;
import dugout.TransactionsPage;
import io.appium.java_client.MobileElement;
import referee.Commentary;
import referee.Verify;
import support.Engine;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;

public class Daily_Regression_Test2 extends Recovery{

	String sUserName = "yuvaraju.boligorla@quicken.com";
	String sPassword = "Intuit!1";
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
	String statusCleared = "Cleared";
	String statusUnCleared = "Uncleared";
	String filterNewToOld = "Date New to Old";
	String filterOldToNew = "Date Old to New";

	@Test (priority = 19, enabled = true)
	public void TC20_ValidateHamburgerMenuOptions ()throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating hamburger menu options");

		OverviewPage op = new OverviewPage();
		op.hambergerIcon.click();

		SettingsPage sp = new SettingsPage();

		Verify.waitForObject(sp.datasetDDButton, 2);
		Verify.waitForObject(sp.accountTxt, 2);

		if(sp.verifyQuickenID(sUserName))
			Commentary.log(LogStatus.INFO, "PASS: Quicken ID is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Quicken ID is NOT displayed");

		if (Verify.objExists(sp.closeButton))
			Commentary.log(LogStatus.INFO, "PASS: Close button is displayed");
		else 
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Close button is NOT displayed");

		if (Verify.objExists(sp.datasetDDButton))
			Commentary.log(LogStatus.INFO, "PASS: Dataset DD button button is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Dataset DD button button is NOT displayed");

		if (Verify.objExists(sp.accountTxt))
			Commentary.log(LogStatus.INFO, "PASS: Account Text is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Account Text is NOT displayed");

		if (Verify.objExists(sp.PasscodeTxt))
			Commentary.log(LogStatus.INFO, "PASS: Passcode text is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Passcode text is NOT displayed");

		if (Verify.objExists(sp.ManageAlertsTxt))
			Commentary.log(LogStatus.INFO, "PASS: Manage Alert text is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Manage Alert text is NOT displayed");

		if (Verify.objExists(sp.HelpLegalTxt))
			Commentary.log(LogStatus.INFO, "PASS: Help & Legal text is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Help & Legal text is NOT displayed");

		if (Verify.objExists(sp.logout))
			Commentary.log(LogStatus.INFO, "PASS: Logout button is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Logout button is NOT displayed");

		if (h.getEngine().equalsIgnoreCase("Android")) {
			if (Verify.objExists(sp.FeedbackTxt))
				Commentary.log(LogStatus.INFO, "PASS: Feedback Text is displayed");
			else
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Feedback Text is NOT displayed");
		} else {
			Commentary.log(LogStatus.INFO, "PASS: Feedback options is not supported for IOS Simulator");
		}

		sa.assertAll();
	}

	@Test(priority = 20, enabled = true)
	public void TC21_ValidateAccountMenu() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating Account menu options and also verifying the details on selecting all user accounts");

		OverviewPage op = new OverviewPage();
		op.hambergerIcon.click();
		Thread.sleep(3000);

		SettingsPage sp = new SettingsPage();
		Verify.waitForObject(sp.accountTxt, 2);
		sp.accountTxt.click();

		Verify.waitForObject(sp.getAccountElement(sManualSaving), 3);

		if (Verify.objExists(sp.getAccountElement(sManualChecking)))
			Commentary.log(LogStatus.INFO, "PASS: Manual Checking account is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Manual Checking account is NOT displayed");

		if (Verify.objExists(sp.getAccountElement(sManualCreditCard)))
			Commentary.log(LogStatus.INFO, "PASS: Manual CC account is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Manual CC account is NOT displayed");

		if (Verify.objExists(sp.getAccountElement(sManualSaving)))
			Commentary.log(LogStatus.INFO, "PASS: Manual Savings account is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Manual Savings account is NOT displayed");

		//Verify Account Details
		sp.getAccountElement(sManualChecking).click();

		Verify.waitForObject(sp.getAccountElement(sManualChecking), 3);
		MobileElement manualCheckingAccount1 = sp.getAccountElement(sManualChecking);
		MobileElement accountType = sp.getAccountElement("CHECKING");
		MobileElement accountStatus = sp.getTextView("Active");

		if (Verify.objExists(manualCheckingAccount1))
			Commentary.log(LogStatus.INFO, "PASS: Manual Checking account details are displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Manual Checking account details are NOT displayed");


		if (Verify.objExists(accountType))
			Commentary.log(LogStatus.INFO, "PASS: Manual Checking Account Type details are displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Manual Checking Account Type details are NOT displayed");


		if (Verify.objExists(accountStatus))
			Commentary.log(LogStatus.INFO, "PASS: Manual Checking Account Status is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Manual Checking Account Status is NOT displayed");

		sp.selectBack("Accounts");
		Thread.sleep(2000);

		sp.getAccountElement(sManualCreditCard).click();
		Thread.sleep(2000);

		Verify.waitForObject(sp.getAccountElement(sManualCreditCard), 3);
		MobileElement manualCCAccount1 = sp.getAccountElement(sManualCreditCard);
		MobileElement accountType_manual = sp.getAccountElement("CREDIT_CARD");
		MobileElement accountStatus1 = sp.getTextView("Active");
		if (Verify.objExists(manualCCAccount1))
			Commentary.log(LogStatus.INFO, "PASS: Manual CC account details are displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Manual CC account details are NOT displayed");

		if (Verify.objExists(accountType_manual))
			Commentary.log(LogStatus.INFO, "PASS: Manual CC Account Type details are displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Manual CC Account Type details are NOT displayed");

		if (Verify.objExists(accountStatus1))
			Commentary.log(LogStatus.INFO, "PASS: Manual CC Account Status is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Manual CC Account Status is NOT displayed");

		sp.selectBack("Accounts");
		Thread.sleep(2000);

		sp.getAccountElement(sManualSaving).click();
		Thread.sleep(2000);

		Verify.waitForObject(sp.getAccountElement(sManualSaving), 3);
		MobileElement manualSavingsAccount1 = sp.getAccountElement(sManualSaving);
		MobileElement accountType_savings = sp.getAccountElement("SAVINGS");
		MobileElement accountStatus2 = sp.getTextView("Active");

		if (Verify.objExists(manualSavingsAccount1))
			Commentary.log(LogStatus.INFO, "PASS: Savings account details are displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Savings account details are NOT displayed");

		if (Verify.objExists(accountType_savings))
			Commentary.log(LogStatus.INFO, "PASS: Savings Account Type details are displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Savings Account Type details are NOT displayed");

		if (Verify.objExists(accountStatus2))
			Commentary.log(LogStatus.INFO, "PASS: Savings Account Status is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Savings Account Status is NOT displayed");

		sa.assertAll();	
	}

	@Test(priority = 21, enabled = true)
	public void TC22_ValidatePasscodeFingerprintMenu() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating menu options for Passcode and Fingerprint menu");

		OverviewPage op = new OverviewPage();
		op.hambergerIcon.click();

		SettingsPage sp = new SettingsPage();
		Verify.waitForObject(sp.PasscodeTxt, 2);
		sp.PasscodeTxt.click();

		Verify.waitForObject(sp.getAccountElement("Use Quicken Passcode"), 2);

		if (Verify.objExists(sp.PasscodeHeaderTxt))
			Commentary.log(LogStatus.INFO, "PASS: Header text is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Header text is NOT displayed");

		if (Verify.objExists(sp.getAccountElement("Use Quicken Passcode")))
			Commentary.log(LogStatus.INFO, "PASS: Passcode text is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Passcode text is NOT displayed");

		//			if (h.getEngine().equalsIgnoreCase("ios")){
		//				if (Verify.objExists(sp.getTextView("Use Touch ID")))
		//					Commentary.log(LogStatus.INFO, "PASS: Touch ID text is displayed");
		//				else
		//					Commentary.log(sa, LogStatus.FAIL, "Touch ID text is NOT displayed");			   
		//			}	
		sa.assertAll();
	}

	@Test(priority = 22, enabled = true)
	public void TC23_ValidateManageAlerts() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating menu options for Manage Alerts menu");

		OverviewPage op = new OverviewPage();
		op.hambergerIcon.click();

		SettingsPage sp = new SettingsPage();
		Verify.waitForObject(sp.ManageAlertsTxt, 2);
		sp.ManageAlertsTxt.click();

		Verify.waitForObject(sp.ManageAlertsHeaderTxt, 2);
		Verify.waitForObject(sp.getTextView("New Charge - Quicken Card (Mobile Only)"), 2);

		if (Verify.objExists(sp.ManageAlertsHeaderTxt))
			Commentary.log(LogStatus.INFO, "PASS: Manage Alerts Header text is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Manage Alerts header text is NOT displayed");

		if (Verify.objExists(sp.getTextView("New Charge - Quicken Card (Mobile Only)")))
			Commentary.log(LogStatus.INFO, "PASS: New charge message text is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: New charge message text is NOT displayed");

		Verify.waitForObject(sp.getTextView("Push Notification"), 2);
		if (Verify.objExists(sp.getTextView("Push Notification")))
			Commentary.log(LogStatus.INFO, "PASS: Push Notification text is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Push Notification text is NOT displayed");

		sa.assertAll();
	}

	@Test(priority = 23, enabled = true)
	public void TC24_ValidateHelpLegal() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating menu options for Help and Legal menu");

		OverviewPage op = new OverviewPage();
		op.hambergerIcon.click();

		SettingsPage sp = new SettingsPage();
		Verify.waitForObject(sp.HelpLegalTxt, 3);
		sp.HelpLegalTxt.click();
		Thread.sleep(3000);
		Verify.waitForObject(sp.getTextView("Help"), 3);
		Verify.waitForObject(sp.getTextView("Support Website"), 3);
		Verify.waitForObject(sp.getTextView("Acknowledgements"), 3);
		Verify.waitForObject(sp.getTextView("License Agreement"), 3);
		Verify.waitForObject(sp.getTextView("Privacy"), 3);

		MobileElement help = sp.getTextView("Help");
		MobileElement link_SupportWebsite = sp.getTextView("Support Website");
		MobileElement link_Acknowledgements = sp.getTextView("Acknowledgements");
		MobileElement link_LicenseAgreement = sp.getTextView("License Agreement");
		MobileElement link_Privacy = sp.getTextView("Privacy");

		if (Verify.objExists(sp.HelpLegalHeaderTxt))
			Commentary.log(LogStatus.INFO, "PASS: Help Legal text is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Help Legal text is NOT displayed");

		if (Verify.objExists(help))
			Commentary.log(LogStatus.INFO, "PASS: Help text is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Help text is NOT displayed");

		if (Verify.objExists(link_SupportWebsite))
			Commentary.log(LogStatus.INFO, "PASS: Support Website Link is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Support Website Link is NOT displayed");

		if (Verify.objExists(sp.getTextView("Legal")))
			Commentary.log(LogStatus.INFO, "PASS: Legal text is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Legal text is NOT displayed");

		if (Verify.objExists(link_Acknowledgements))
			Commentary.log(LogStatus.INFO, "PASS: Acknowledgements link is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Acknowledgements link is NOT displayed");

		if (Verify.objExists(link_LicenseAgreement))
			Commentary.log(LogStatus.INFO, "PASS: License Agreement link is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: License Agreement link is NOT displayed");

		if (Verify.objExists(link_Privacy))
			Commentary.log(LogStatus.INFO, "PASS: Privacy link is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Privacy link is NOT displayed");

		sa.assertAll();
	}

	@Test (priority=24, enabled = true)
	public void TC25_VerifyTransactionSummary_CategoryScreen() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Adding an expense transaction and validating that the Category details are updated on Transaction Summary page");

		OverviewPage op = new OverviewPage();
		op.tapOnTransactionSummaryCard();

		TransactionSummaryPage ts = new TransactionSummaryPage();
		Verify.waitForObject(ts.categoryTab, 2);
		ts.categoryTab.click();

		Verify.waitForObject(ts.categoryTile, 2);
		String sCategoryAmount_before = ts.categoryTile.getText();
		Double dCategoryAmount_before = h.processBalanceAmount(sCategoryAmount_before.replace("Internet ", ""));
		Commentary.log(LogStatus.INFO, "Category amount is "+dCategoryAmount_before);

		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		String payeeName = "Payee_"+h.getCurrentTime();

		tRec.setAmount("10.00");
		tRec.setAccount(sManualChecking);
		tRec.setPayee(payeeName);
		tRec.setCategory("Internet");
		tRec.setTransactionType("expense");

		ts.backButtonOnHeader.click();

		op.scrollToTop();

		op.addTransaction.click();
		Verify.waitForObject(td.buttonDone, 2);
		td.addTransaction(tRec);

		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount "+tRec.getAmount());

		op.tapOnTransactionSummaryCard();
		Verify.waitForObject(ts.categoryTab, 2);
		ts.categoryTab.click();

		Verify.waitForObject(ts.categoryTile, 2);
		String sCategoryAmount_after = ts.categoryTile.getText();
		Double dCategoryAmount_after = h.processBalanceAmount(sCategoryAmount_after.replace("Internet ", ""));
		Double d = Double.parseDouble(tRec.getAmount());
		Commentary.log(LogStatus.INFO, "Category amount now is "+dCategoryAmount_after);

		if (dCategoryAmount_after+d==dCategoryAmount_before)
			Commentary.log(LogStatus.INFO, "PASS: Category tile is updated after adding expense transaction for selected payee");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Category tile is NOT updated after adding expense transaction for selected payee");

		sa.assertAll();
	}

	@Test (priority = 25, enabled = true)
	public void TC26_ValidateAddingTransactionWithYelpPayee() throws Exception {

		//GPS Coordinates set on the simulator Latitude : 37.785834 Longitude: -122.406417 to search Yelp Payee "Enough Tea & Coffee".
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Adding a transaction with Payee selected from Yelp Results and later verifying the Payee Name from the added transaction.");

		OverviewPage op = new OverviewPage();
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);

		TransactionsPage tp = new TransactionsPage();
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		String payeeName = "Enough Tea & Coffee";

		tRec.setAmount("5.00");
		tRec.setAccount(sManualChecking);
		tRec.setCategory("Restaurants");
		tRec.setPayee(payeeName);
		tRec.setTransactionType("expense");

		Verify.waitForObject(op.addTransaction, 2);
		op.addTransaction.click();
		Verify.waitForObject(td.buttonDone, 2);
		td.addTransaction(tRec);
		Thread.sleep(2000);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount "+tRec.getAmount()+"], Transaction Payee Name: "+tRec.getPayee());

		op.navigateToAcctList();

		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		Verify.waitForObject(bcc.allTransactionButton, 2);
		bcc.allTransactionButton.click();

		Verify.waitForObject(tp.searchTransactionTxtField, 2);
		tp.searchRecentTransaction(payeeName);
		Verify.waitForObject(tp.thisMonthLabel, 2);

		tp.tapOnFirstTransaction();
		Verify.waitForObject(td.payee, 2);

		td.VerifyTransactionPayee(payeeName);
		td.backButton.click();	

		sa.assertAll();
	}

	@Test (priority=26, enabled = true)
	public void TC27_ValidateInvestmentCard() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating Investment Card details");

		OverviewPage op = new OverviewPage();
		op.hambergerIcon.click();
		Thread.sleep(1000);

		SettingsPage sp = new SettingsPage();
		Verify.waitForObject(sp.datasetDDButton, 2);
		sp.datasetDDButton.click();
		Thread.sleep(4000);

		sp.switchDataset("InvestmentDataset_Automation");

		op.tapOnInvestingCard();

		InvestingPage ip = new InvestingPage();
		Verify.waitForObject(ip.securitiesTab, 2);
		Verify.waitForObject(ip.watchlistTab, 2);
		Verify.waitForObject(ip.lastSyncedFooter, 2);

		if (Verify.objExists(ip.investingHeader))
			Commentary.log(LogStatus.INFO, "PASS: Investing Header text is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Investing Header text is NOT displayed");

		if (Verify.objExists(ip.securitiesTab))
			Commentary.log(LogStatus.INFO, "PASS: Securities Tab is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Securities Tab is NOT displayed");

		if (Verify.objExists(ip.accountsTab))
			Commentary.log(LogStatus.INFO, "PASS: Accounts Tab is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Accounts Tab is NOT displayed");

		if (Verify.objExists(ip.watchlistTab))
			Commentary.log(LogStatus.INFO, "PASS: Watch List Tab is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Watch List Tab is NOT displayed");

		if (Verify.objExists(ip.totalValueLabel))
			Commentary.log(LogStatus.INFO, "PASS: Total Value label is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Total Value label is NOT displayed");

		if (Verify.objExists(ip.cashbalancesLabel))
			Commentary.log(LogStatus.INFO, "PASS: Cash Balances label is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Cash Balances label is NOT displayed");

		if (Verify.objExists(ip.backButtonOnHeader))
			Commentary.log(LogStatus.INFO, "PASS: Back Button is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Back Button is NOT displayed");

		if (Verify.objExists(ip.securitiesByCompanyNameLabel))
			Commentary.log(LogStatus.INFO, "PASS: Security by company label is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Security by company label is NOT displayed");

		if (Verify.objExists(ip.lastSyncedFooter))
			Commentary.log(LogStatus.INFO, "PASS: Last Synced Footer is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Last Synced Footer is NOT displayed");

		sa.assertAll();
	}

	@Test (priority=27, enabled = true)
	public void TC28_ValidateForZeroDataset_RecentTxnCard() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating message displayed for all Card in case of zero dataset");
		OverviewPage op = new OverviewPage();

		Verify.waitForObject(op.hambergerIcon, 2);
		op.hambergerIcon.click();
		Thread.sleep(3000);

		SettingsPage sp = new SettingsPage();
		Verify.waitForObject(sp.datasetDDButton, 3);
		sp.datasetDDButton.click();
		Thread.sleep(4000);

		sp.switchDataset("ZeroDataSet");

		op.scrollTillCard("Recent Transactions");

		String actText_TxnOverviewPage = op.recentTxns_NoTxnsAvailable.getText();

		if (actText_TxnOverviewPage.equals("No Transaction available"))
			Commentary.log(LogStatus.INFO, "PASS: OverviewPage RecentTransaction card > Correct message is displayed in case user has no recent transactions");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: OverviewPage RecentTransaction card > Message is not displayed in case user has no recent transactions");
		//Navigate to Recent Transaction card
		op.tapOnRecentTransactionsCard();

		TransactionsPage tp = new TransactionsPage();
		String actText_txnDetailsScreen = tp.noTransactionText.getText();

		if (actText_txnDetailsScreen.equals("You don't have any transactions."))
			Commentary.log(LogStatus.INFO, "PASS: Recent Transaction page > Correct message is displayed in case user has no recent transactions");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Recent Transaction page > Message is not displayed in case user has no recent transactions");

		sa.assertAll();
	}

	@Test (priority=28, enabled = true)
	public void TC29_ValidateForZeroDataset_NetIncomeOverTimeCard() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating message displayed on Net Income Over Time Card in case of no transactions.");

		OverviewPage op = new OverviewPage();
		op.tapOnNetIncomeOverTimeCard();

		NetIncomeOverTimePage not = new NetIncomeOverTimePage();

		Verify.waitForObject(not.youDontHaveAnyTxns, 1);
		String actText_notDetailsScreen = not.youDontHaveAnyTxns.getText();

		if (actText_notDetailsScreen.equals("You don't have any transactions."))
			Commentary.log(LogStatus.INFO, "PASS: Net Income Over Time page > Correct message is displayed in case user has no transactions.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Net Income Over Time page > Message is not displayed in case user has no transactions.");

		Verify.waitForObject(not.backButtonOnHeader, 1);
		not.backButtonOnHeader.click();
		
		op.tapOnSpendingOverTimeCard();
		
		SpendingOverTimePage sot = new SpendingOverTimePage();
		Verify.waitForObject(sot.youDontHaveAnyTxns, 1);
		String actText_sotDetailsScreen = sot.youDontHaveAnyTxns.getText();

		if (actText_sotDetailsScreen.equals("You don't have any transactions."))
			Commentary.log(LogStatus.INFO, "PASS: Spending Over Time page > Correct message is displayed in case user has no transactions.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Spending Over Time page > Message is not displayed in case user has no transactions.");
		
		sa.assertAll();
	}

	@Test (priority=29, enabled = true)
	public void TC30_ValidateForZeroDataset_TransactionSummaryCard() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating message displayed on Transaction Summary Card in case of no transactions");

		OverviewPage op = new OverviewPage();
		op.tapOnTransactionSummaryCard();

		TransactionSummaryPage ts = new TransactionSummaryPage();

		Verify.waitForObject(ts.noTransactionCategory, 2);
		String actText_summaryDetailsScreen = ts.noTransactionCategory.getText();

		if (actText_summaryDetailsScreen.equals("No Transactions by Category"))
			Commentary.log(LogStatus.INFO, "PASS: Transaction Summary page > Correct message is displayed in case user has no transactions");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Transaction Summary page > Message is not displayed in case user has no transactions");

		ts.payeeTab.click();

		Verify.waitForObject(ts.noTransactionPayee, 2);
		String actText_noTxnPayee = ts.noTransactionPayee.getText();

		if (actText_noTxnPayee.equals("No Transactions by Payee"))
			Commentary.log(LogStatus.INFO, "PASS: Transaction Summary page > Correct message is displayed in case user has no transactions");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Transaction Summary page > Message is not displayed in case user has no transactions");

		sa.assertAll();
	}
}
