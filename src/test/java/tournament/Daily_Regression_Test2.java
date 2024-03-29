package tournament;

import java.math.BigDecimal;

import org.openqa.selenium.WebElement;
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
	
	
	//****** This piece of code we can enable when we want to use the Testrail integration with our project . Based on test case status the status on Testrail will get update for each testcase ******	
//	@Override
//	@BeforeTest
//	@Parameters({"host","engine","test","env","RUN_ID"})
//	public void beforeTestEnter(@Optional("readFromPropertiesFile")String host, @Optional("readFromPropertiesFile")String engine, @Optional("readFromPropertiesFile")String testName, @Optional("readFromPropertiesFile")String env, @Optional("readFromPropertiesFile")String RUN_ID) throws Exception{
//		this.testRunId.set("2330");
//		super.beforeTestEnter(host, engine, testName, env, RUN_ID);
//		
//	}
	@Test (priority = 20, enabled = true)
	public void TC20_ValidateHamburgerMenuOptions ()throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating hamburger menu options.");

		OverviewPage op = new OverviewPage();
		Verify.waitForObject(op.hambergerIcon, 1);
		op.hambergerIcon.click();

		SettingsPage sp = new SettingsPage();

		Verify.waitForObject(sp.dashboardOption, 2);
		Verify.waitForObject(sp.accountTxt, 2);

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

		if (Verify.objExists(sp.budgetsOption))
			Commentary.log(LogStatus.INFO, "PASS: Budgets option is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Budgets option is NOT displayed.");

		if (Verify.objExists(sp.investingOption))
			Commentary.log(LogStatus.INFO, "PASS: Investing option is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Investing option is NOT displayed.");

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

		if (h.getEngine().equalsIgnoreCase("Android")) {
			if (Verify.objExists(sp.FeedbackTxt))
				Commentary.log(LogStatus.INFO, "PASS: Feedback Text is displayed.");
			else
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Feedback Text is NOT displayed.");
		} else {
			Commentary.log(LogStatus.INFO, "PASS: Feedback options is not supported for IOS Simulator.");
		}

		sa.assertAll();
	}

	@Test(priority = 21, enabled = false)
	public void TC21_ValidateAccountsManagement() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating Account menu options and also verifying the details on selecting all user accounts");

		SettingsPage sp = new SettingsPage();

		sp.clickOnSettingsOption();

		Verify.waitForObject(sp.manageAccountsOption, 1);
		if(Verify.objExists(sp.manageAccountsOption))
			Commentary.log(LogStatus.INFO, "PASS: \"Manage Accounts\" option is displayed under Settings.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Manage Accounts\" option is NOT displayed under Settings.");

		sp.manageAccountsOption.click();

		Verify.waitForObject(sp.getAccountElement(sManualSaving), 1);

		if (Verify.objExists(sp.getAccountElement(sManualChecking)))
			Commentary.log(LogStatus.INFO, "PASS: Manual Checking account is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Manual Checking account is NOT displayed.");

		if (Verify.objExists(sp.getAccountElement(sManualCreditCard)))
			Commentary.log(LogStatus.INFO, "PASS: Manual CC account is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Manual CC account is NOT displayed.");

		if (Verify.objExists(sp.getAccountElement(sManualSaving)))
			Commentary.log(LogStatus.INFO, "PASS: Manual Savings account is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Manual Savings account is NOT displayed.");

		//Verify Account Details
		sp.getAccountElement(sManualChecking).click();

		Verify.waitForObject(sp.getAccountElement(sManualChecking), 2);
		WebElement manualCheckingAccount1 = sp.getAccountElement(sManualChecking);
		WebElement accountType = sp.getAccountElement("Checking");
		WebElement accountStatus = sp.getTextView("Active");

		if (Verify.objExists(manualCheckingAccount1))
			Commentary.log(LogStatus.INFO, "PASS: Manual Checking account details are displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Manual Checking account details are NOT displayed.");

		if (Verify.objExists(accountType))
			Commentary.log(LogStatus.INFO, "PASS: Manual Checking Account Type details are displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Manual Checking Account Type details are NOT displayed.");

		if (Verify.objExists(accountStatus))
			Commentary.log(LogStatus.INFO, "PASS: Manual Checking Account Status is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Manual Checking Account Status is NOT displayed.");

		sp.selectBack("Accounts, back");
		Thread.sleep(2000);

		sp.getAccountElement(sManualCreditCard).click();
		Thread.sleep(2000);

		Verify.waitForObject(sp.getAccountElement(sManualCreditCard), 2);
		WebElement manualCCAccount1 = sp.getAccountElement(sManualCreditCard);
//		WebElement accountType_manual = sp.getAccountElement("CREDIT_CARD");
		WebElement accountStatus1 = sp.getTextView("Active");
		if (Verify.objExists(manualCCAccount1))
			Commentary.log(LogStatus.INFO, "PASS: Manual CC account details are displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Manual CC account details are NOT displayed.");

//		if (Verify.objExists(accountType_manual))
//			Commentary.log(LogStatus.INFO, "PASS: Manual CC Account Type details are displayed.");
//		else
//			Commentary.log(sa, LogStatus.FAIL, "FAIL: Manual CC Account Type details are NOT displayed.");

		if (Verify.objExists(accountStatus1))
			Commentary.log(LogStatus.INFO, "PASS: Manual CC Account Status is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Manual CC Account Status is NOT displayed.");

		sp.selectBack("Accounts, back");
		Thread.sleep(2000);

		sp.getAccountElement(sManualSaving).click();
		Thread.sleep(2000);

		Verify.waitForObject(sp.getAccountElement(sManualSaving), 2);
		WebElement manualSavingsAccount1 = sp.getAccountElement(sManualSaving);
		WebElement accountType_savings = sp.getAccountElement("Savings");
		WebElement accountStatus2 = sp.getTextView("Active");

		if (Verify.objExists(manualSavingsAccount1))
			Commentary.log(LogStatus.INFO, "PASS: Savings account details are displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Savings account details are NOT displayed.");

		if (Verify.objExists(accountType_savings))
			Commentary.log(LogStatus.INFO, "PASS: Savings Account Type details are displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Savings Account Type details are NOT displayed.");

		if (Verify.objExists(accountStatus2))
			Commentary.log(LogStatus.INFO, "PASS: Savings Account Status is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Savings Account Status is NOT displayed.");

		sa.assertAll();	
	}

	@Test(priority = 22, enabled = true)
	public void TC22_ValidateProfileMenu() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating Profile menu options.");

		SettingsPage sp = new SettingsPage();

		sp.clickOnProfileOption();

		Verify.waitForObject(sp.PasscodeTxt, 1);
		if(Verify.objExists(sp.PasscodeTxt))
			Commentary.log(LogStatus.INFO, "PASS: \"Passcode\" option is displayed under Profile section.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Passcode\" option is NOT displayed under Profile section.");

		if(Verify.objExists(sp.legalTxt))
			Commentary.log(LogStatus.INFO, "PASS: \"Legal\" option is displayed under Profile section.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Legal\" option is NOT displayed under Profile section.");

		Verify.waitForObject(sp.PasscodeTxt, 1);
		sp.PasscodeTxt.click();

		Verify.waitForObject(sp.useDevicePasscodeTxt, 1);
		if(Verify.objExists(sp.PasscodeHeaderTxt) && Verify.objExists(sp.useDevicePasscodeTxt))
			Commentary.log(LogStatus.INFO, "PASS: Passcode page is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Passcode page is NOT displayed.");

		sp.backButtonOnPasscodeHeader.click();
		Thread.sleep(2000);

		Verify.waitForObject(sp.legalTxt, 1);
		sp.legalTxt.click();
		Thread.sleep(2000);

		Verify.waitForObject(sp.acknowledgementsOption, 1);
		if(Verify.objExists(sp.acknowledgementsOption))
			Commentary.log(LogStatus.INFO, "PASS: Acknowledgements option is displayed under Legal section.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Acknowledgements option is NOT displayed under Legal section.");

		if(Verify.objExists(sp.licenceAgreementOption))
			Commentary.log(LogStatus.INFO, "PASS: Licence agreeement option is displayed under Legal section.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Licence agreeement option is NOT displayed under Legal section.");

		if(Verify.objExists(sp.privacyOption))
			Commentary.log(LogStatus.INFO, "PASS: Privacy option is displayed under Legal section.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Privacy option is NOT displayed under Legal section.");

		sa.assertAll();
	}

	@Test(priority = 23, enabled = true)
	public void TC23_ValidateSettingsOption() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating Settings menu options.");

		SettingsPage sp = new SettingsPage();
		sp.clickOnSettingsOption();

		Verify.waitForObject(sp.settingsHeaderText, 2);
		if (Verify.objExists(sp.settingsHeaderText))
			Commentary.log(LogStatus.INFO, "PASS: Settings Header text is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Settings header text is NOT displayed.");

		Verify.waitForObject(sp.accountBalancePreferenceOption, 1);
		if(Verify.objExists(sp.accountBalancePreferenceOption))
			Commentary.log(LogStatus.INFO, "PASS: \"Account Balance Preference\" option is displayed under Settings.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Account Balance Preference\" option is NOT displayed under Settings.");

		if(Verify.objExists(sp.customizeDashboardOption))
			Commentary.log(LogStatus.INFO, "PASS: \"Customize Dashboard\" option is displayed under Settings.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Customize Dashboard\" option is NOT displayed under Settings.");

		if(Verify.objExists(sp.manageAccountsOption))
			Commentary.log(LogStatus.INFO, "PASS: \"Manage Accounts\" option is displayed under Settings.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Manage Accounts\" option is NOT displayed under Settings.");

		if(Verify.objExists(sp.manageCategoriesOption))
			Commentary.log(LogStatus.INFO, "PASS: \"Manage Categories\" option is displayed under Settings.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Manage Categories \" option is NOT displayed under Settings.");

		if (Verify.objExists(sp.ManageAlertsTxt))
			Commentary.log(LogStatus.INFO, "PASS: Manage Alerts option is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Manage Alerts option is NOT displayed.");

		if(Verify.objExists(sp.rulesOption))
			Commentary.log(LogStatus.INFO, "PASS: \"Rules\" option is displayed under Settings.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Rules\" option is NOT displayed under Settings.");

		if(Verify.objExists(sp.tagsOption))
			Commentary.log(LogStatus.INFO, "PASS: \"Tags\" option is displayed under Settings.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Tags\" option is NOT displayed under Settings.");

		if (Verify.objExists(sp.displayYelpRecommendationsText))
			Commentary.log(LogStatus.INFO, "PASS: 'Display Yelp Recommendation' text is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Display Yelp Recommendation' text is NOT displayed.");

		if (Verify.objExists(sp.displayYelpDescription))
			Commentary.log(LogStatus.INFO, "PASS: 'Display Yelp Recommendation' description text is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Display Yelp Recommendation' description text is NOT displayed.");

		if (Verify.objExists(sp.switchDisplayYelp))
			Commentary.log(LogStatus.INFO, "PASS: 'Display Yelp Recommendation' toggle button is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Display Yelp Recommendation' toggle button is NOT displayed.");

		if (sp.isDisplayYelpEnabled())
			Commentary.log(LogStatus.INFO, "PASS: 'Display Yelp Recommendation' option is enabled by default.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Display Yelp Recommendation' option is seen disabled by default.");

		if (Verify.objExists(sp.showLongCategoryNamesText))
			Commentary.log(LogStatus.INFO, "PASS: 'Show Long Category Names' text is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Show Long Category Names' text is NOT displayed.");

		if (Verify.objExists(sp.showLongCategoryNamesDescription))
			Commentary.log(LogStatus.INFO, "PASS: 'Show Long Category Names' description text is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Show Long Category Names' description text is NOT displayed.");

		if (Verify.objExists(sp.switchShowLongCategoryNames))
			Commentary.log(LogStatus.INFO, "PASS: 'Show Long Category Names' toggle button is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Show Long Category Names' toggle button is NOT displayed.");

		if (!sp.isShowLongCategoryNamesEnabled())
			Commentary.log(LogStatus.INFO, "PASS: 'Show Long Category Names' option is disabled by default.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Show Long Category Names' option is enabled by default.");

		if (Verify.objExists(sp.displayFavoritePayeesText))
			Commentary.log(LogStatus.INFO, "PASS: 'Display favorite payees' Recommendation text is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Display favorite payees' Recommendation text is NOT displayed.");

		if (Verify.objExists(sp.displayFavoritePayeesDescription))
			Commentary.log(LogStatus.INFO, "PASS: 'Display favorite payees' description text is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Display favorite payees' description text is NOT displayed.");

		if (Verify.objExists(sp.switchDisplayFavoritePayees))
			Commentary.log(LogStatus.INFO, "PASS: 'Display favorite payees' toggle button is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Display favorite payees' toggle button is NOT displayed.");

		if (sp.isDisplayFavoritePayeesEnabled())
			Commentary.log(LogStatus.INFO, "PASS: 'Display Favorite Payees' option is enabled by default.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Display Favorite Payees' option is seen disabled by default.");

		sp.scrollToRefreshDataOption();

		if (Verify.objExists(sp.displayRealTimeQuotesText))
			Commentary.log(LogStatus.INFO, "PASS: 'Display Real-Time Quotes' text is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Display Real-Time Quotes' text is NOT displayed.");

		if (Verify.objExists(sp.displayRealTimeQuotesDescription))
			Commentary.log(LogStatus.INFO, "PASS: 'Display Real-Time Quotes' description text is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Display Real-Time Quotes' description text is NOT displayed.");

		if (Verify.objExists(sp.switchDisplayRealTimeQuotes))
			Commentary.log(LogStatus.INFO, "PASS: 'Display Real-Time Quotes' toggle button is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Display Real-Time Quotes' toggle button is NOT displayed.");

		if (!sp.isDisplayRealTimeQuotesEnabled())
			Commentary.log(LogStatus.INFO, "PASS: 'Display Real-Time Quotes' option is disabled by default.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Display Real-Time Quotes' option is enabled by default.");

		if (Verify.objExists(sp.refreshData))
			Commentary.log(LogStatus.INFO, "PASS: 'Refresh Data' option is present.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Refresh Data' option is not present.");

		sp.ManageAlertsTxt.click();

		if (Verify.objExists(sp.ManageAlertsHeaderTxt))
			Commentary.log(LogStatus.INFO, "PASS: Manage Alerts Header text is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Manage Alerts header text is NOT displayed.");

		Verify.waitForObject(sp.getTextView("New Charge - Quicken Card (Mobile Only)"), 1);
		if (Verify.objExists(sp.getTextView("New Charge - Quicken Card (Mobile Only)")))
			Commentary.log(LogStatus.INFO, "PASS: New charge message text is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: New charge message text is NOT displayed.");

		Verify.waitForObject(sp.getTextView("Push Notification"), 1);
		if (Verify.objExists(sp.getTextView("Push Notification")))
			Commentary.log(LogStatus.INFO, "PASS: Push Notification text is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Push Notification text is NOT displayed.");

		sa.assertAll();
	}

	@Test (priority=25, enabled = true)
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
		Double dCategoryAmount_before = h.processBalanceAmount(sCategoryAmount_before.replaceAll("[^0-9.-]", ""));
		Commentary.log(LogStatus.INFO, "Category amount is: "+dCategoryAmount_before);

		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		String payeeName = "Payee_"+h.getCurrentTime();

		tRec.setAmount("10.00");
		tRec.setAccount(sManualChecking);
		tRec.setPayee(payeeName);
		tRec.setCategory("Internet");
		tRec.setTransactionType("expense");

		ts.backButtonOnHeader.click();
		Thread.sleep(2000);

		op.scrollToTop();

		Verify.waitForObject(op.addTransaction, 1);
		op.addTransaction.click();
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec);

		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount "+tRec.getAmount());

		op.tapOnTransactionSummaryCard();
		Verify.waitForObject(ts.categoryTab, 2);
		ts.categoryTab.click();

		Verify.waitForObject(ts.categoryTile, 2);
		String sCategoryAmount_after = ts.categoryTile.getText();
		Double dCategoryAmount_after = h.processBalanceAmount(sCategoryAmount_after.replaceAll("[^0-9.-]", ""));
		Commentary.log(LogStatus.INFO, "Category amount now is: "+dCategoryAmount_after);

		Double d = Double.parseDouble(tRec.getAmount());

		int categoryAmount_Compare = Double.compare(dCategoryAmount_after+d, dCategoryAmount_before);

		if(categoryAmount_Compare == 0)
			Commentary.log(LogStatus.INFO, "PASS: Category tile is updated after adding expense transaction for selected payee.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Category tile is NOT updated after adding expense transaction for selected payee.");

		sa.assertAll();
	}

	@Test (priority = 26, enabled = true)
	public void TC26_ValidateAddingTransactionWithYelpPayee() throws Exception {

		//GPS Coordinates set on the simulator Latitude : 37.785834 Longitude: -122.406417 to search Yelp Payee "Enough Tea & Coffee".
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Adding a transaction with Payee selected from Yelp Results and later verifying the Payee Name from the added transaction.");

		OverviewPage op = new OverviewPage();
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);

		SettingsPage sp = new SettingsPage();
		sp.clickOnSettingsOption();
		
		Verify.waitForObject(sp.switchDisplayYelp, 1);
		sp.enableDisplayYelpOption();
		
		sp.backButtonOnSettingsHeader.click();
		Thread.sleep(2000);
		
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

		sa.assertAll();
	}

	@Test (priority=27, enabled = true)
	public void TC27_ValidateInvestmentCard() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating Investment Card details");

		OverviewPage op = new OverviewPage();
		Verify.waitForObject(op.hambergerIcon, 1);
		op.hambergerIcon.click();
		Thread.sleep(1000);

		SettingsPage sp = new SettingsPage();
		Verify.waitForObject(sp.datasetDDButton, 2);
		sp.datasetDDButton.click();
		Thread.sleep(4000);

		if(h.getEnv().contentEquals("prod"))
			sp.switchDataset("InvestmentDataset_Automation");
		else
			sp.switchDataset("InvestmentDataset_Automation_Stage");

		op.tapOnInvestingCard();

		InvestingPage ip = new InvestingPage();
		Verify.waitForObject(ip.holdingsTab, 2);
		Verify.waitForObject(ip.watchlistTab, 2);
		Verify.waitForObject(ip.lastSyncedFooter, 2);

		if (Verify.objExists(ip.investingHeader))
			Commentary.log(LogStatus.INFO, "PASS: Investing Header text is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Investing Header text is NOT displayed.");

		if (Verify.objExists(ip.holdingsTab))
			Commentary.log(LogStatus.INFO, "PASS: Holdings Tab is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Holdings Tab is NOT displayed.");

		if (Verify.objExists(ip.accountsTab))
			Commentary.log(LogStatus.INFO, "PASS: Accounts Tab is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Accounts Tab is NOT displayed.");

		if (Verify.objExists(ip.watchlistTab))
			Commentary.log(LogStatus.INFO, "PASS: Watch List Tab is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Watch List Tab is NOT displayed.");

		if (Verify.objExists(ip.marketValueLabel))
			Commentary.log(LogStatus.INFO, "PASS: Market Value & Today's Change label is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Market Value & Today's Change label is NOT displayed.");

		if (Verify.objExists(ip.cashbalancesLabel))
			Commentary.log(LogStatus.INFO, "PASS: Cash Balances label is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Cash Balances label is NOT displayed.");

		if (Verify.objExists(ip.backButtonOnHeader))
			Commentary.log(LogStatus.INFO, "PASS: Back Button is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Back Button is NOT displayed.");

		if (Verify.objExists(ip.lastSyncedFooter))
			Commentary.log(LogStatus.INFO, "PASS: Last Synced Footer is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Last Synced Footer is NOT displayed.");

		sa.assertAll();
	}

	@Test (priority=28, enabled = true)
	public void TC28_ValidateForZeroDataset_RecentTxnCard() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating message displayed for all Card in case of zero dataset");
		OverviewPage op = new OverviewPage();

		Verify.waitForObject(op.hambergerIcon, 2);
		op.hambergerIcon.click();
		Thread.sleep(3000);

		SettingsPage sp = new SettingsPage();
		Verify.waitForObject(sp.datasetDDButton, 2);
		sp.datasetDDButton.click();
		Thread.sleep(4000);

		if(h.getEnv().contentEquals("prod"))
			sp.switchDataset("ZeroDataSet");
		else
			sp.switchDataset("ZeroDataSet_Stage");

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

	@Test (priority=29, enabled = true)
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

	@Test (priority=30, enabled = true)
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
