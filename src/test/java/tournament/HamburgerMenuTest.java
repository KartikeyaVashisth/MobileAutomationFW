package tournament;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.AllAccountsPage;
import dugout.BankingAndCreditCardPage;
import dugout.BillsAndIncomePage;
import dugout.BudgetsPage;
import dugout.InvestingPage;
import dugout.NetIncomeOverTimePage;
import dugout.NetWorthPage;
import dugout.OverviewPage;
import dugout.SettingsPage;
import dugout.SignInPage;
import dugout.SpendingOverTimePage;
import dugout.SpendingTrendPage;
import dugout.TransactionSummaryPage;
import referee.Commentary;
import referee.Verify;
import support.Helper;
import support.Recovery;

public class HamburgerMenuTest extends Recovery {
	
	String sUserName = "varsha.h@quicken.com";
	String sPassword = "Intuit!1";
	String sDataset = "ST Phase 2";
	
	@Test(priority=1, enabled = true)
	public void HM1_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		SignInPage si = new SignInPage();
		si.signIn(sUserName, sPassword, sDataset);
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verifying all the different elements of Hamburger Menu.");

		OverviewPage op = new OverviewPage();
		Verify.waitForObject(op.hambergerIcon, 1);
		op.hambergerIcon.click();
		
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
	
	@Test(priority=2, enabled = true)
	public void HM2_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verifying Dashboard option on the Hamburger Menu.");
		
		SettingsPage sp = new SettingsPage();
		sp.clickOnDashboardOption();
		
		OverviewPage op = new OverviewPage();
		
		Verify.waitForObject(op.hambergerIcon, 1);
		if(Verify.objExists(op.hambergerIcon) && Verify.objExists(op.addTransaction) && !Verify.objExists(sp.versionNumber))
			Commentary.log(LogStatus.INFO, "PASS: Dashboard page is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Quicken App's Dashboard page is not displayed.");
		
		sa.assertAll();
	}
	
	@Test(priority=3, enabled = true)
	public void HM3_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verifying Accounts option on the Hamburger Menu.");
		
		SettingsPage sp = new SettingsPage();
		sp.clickOnAccountsOption();
		
		BankingAndCreditCardPage bcp = new BankingAndCreditCardPage();
		OverviewPage op = new OverviewPage();
		
		Verify.waitForObject(bcp.txtTodaysBalanceAmount, 1);
		if(Verify.objExists(bcp.txtTodaysBalanceAmount) && Verify.objExists(bcp.allTransactionButton) && Verify.objExists(bcp.checkingBalance) && !Verify.objExists(op.hambergerIcon))
			Commentary.log(LogStatus.INFO, "PASS: Banking and Credit Card is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Banking and Credit Card is NOT displayed.");
		
		sa.assertAll();
	}
	
	@Test(priority=4, enabled = true)
	public void HM4_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verifying All Transactions option on the Hamburger Menu.");
		
		SettingsPage sp = new SettingsPage();
		sp.clickOnAllTransactionsOption();
		
		AllAccountsPage ap = new AllAccountsPage();
		OverviewPage op = new OverviewPage();
		
		Verify.waitForObject(ap.allTransactionsHeader, 1);
		if(Verify.objExists(ap.allTransactionsHeader) && Verify.objExists(ap.searchTransactionTxtField) && !Verify.objExists(op.hambergerIcon))
			Commentary.log(LogStatus.INFO, "PASS: All Transactions page is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL:  All Transactions page is NOT displayed.");
		
		sa.assertAll();
	}

	@Test(priority=5, enabled = true)
	public void HM5_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verifying Bills option on the Hamburger Menu.");
		
		SettingsPage sp = new SettingsPage();
		sp.clickOnBillsOption();
		
		BillsAndIncomePage bi = new BillsAndIncomePage();
		OverviewPage op = new OverviewPage();
		
		Verify.waitForObject(bi.billsAndIncomeHeaderText, 1);
		if(Verify.objExists(bi.billsAndIncomeHeaderText) && Verify.objExists(bi.remindersTab) && !Verify.objExists(op.hambergerIcon))
			Commentary.log(LogStatus.INFO, "PASS: Bills & Income page is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Bills & Income page is NOT displayed.");
		
		sa.assertAll();
	}
	
	@Test(priority=6, enabled = true)
	public void HM6_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verifying Budgets option on the Hamburger Menu.");

		SettingsPage sp = new SettingsPage();
		sp.clickOnBudgetsOption();
		
		BudgetsPage bp = new BudgetsPage();
		OverviewPage op = new OverviewPage();
		
//		Verify.waitForObject(bp., 1);
//		if(Verify.objExists(bp.) && Verify.objExists(bp.) && !Verify.objExists(op.hambergerIcon))
//			Commentary.log(LogStatus.INFO, "PASS: Budgets page is displayed.");
//		else
//			Commentary.log(sa, LogStatus.FAIL, "FAIL:  Budgets page is NOT displayed.");
		
		sa.assertAll();
	}
	
	@Test(priority=7, enabled = true)
	public void HM7_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verifying Investments option on the Hamburger Menu.");
		
		SettingsPage sp = new SettingsPage();
		sp.clickOnInvestingOption();
		
		InvestingPage ip = new InvestingPage();
		OverviewPage op = new OverviewPage();
		
		Verify.waitForObject(ip.securitiesTab, 1);
		if(Verify.objExists(ip.investingHeader) && Verify.objExists(ip.securitiesTab) && Verify.objExists(ip.accountsTab) && !Verify.objExists(op.hambergerIcon))
			Commentary.log(LogStatus.INFO, "PASS: Investments page is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Investments page is NOT displayed.");
		
		sa.assertAll();
	}
	
	@Test(priority=8, enabled = true)
	public void HM8_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verifying Reports option on the Hamburger Menu.");

		SettingsPage sp = new SettingsPage();
		sp.clickOnReportsOption();
		
		Verify.waitForObject(sp.monthlySummaryOption, 1);
		if(Verify.objExists(sp.monthlySummaryOption))
			Commentary.log(LogStatus.INFO, "PASS: \"Monthly Summary\" option is displayed under Reports section.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Monthly Summary\" option is NOT displayed under Reports section.");
		
		if(Verify.objExists(sp.netIncomeOption))
			Commentary.log(LogStatus.INFO, "PASS: \"Net Income\" option is displayed under Reports section.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Net Income\" option is NOT displayed under Reports section.");
		
		if(Verify.objExists(sp.netWorthOption))
			Commentary.log(LogStatus.INFO, "PASS: \"Net Worth\" option is displayed under Reports section.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Net Worth\" option is NOT displayed under Reports section.");
		
		if(Verify.objExists(sp.spendingByCategoryOption))
			Commentary.log(LogStatus.INFO, "PASS: \"Spending by Category\" option is displayed under Reports section.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Spending by Category\" option is NOT displayed under Reports section.");
		
		if(Verify.objExists(sp.spendingOverTimeOption))
			Commentary.log(LogStatus.INFO, "PASS: \"Spending Over Time\" option is displayed under Reports section.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Spending Over Time\" option is NOT displayed under Reports section.");
		
		sp.monthlySummaryOption.click();
		Thread.sleep(2000);
		
		TransactionSummaryPage ts = new TransactionSummaryPage();
		OverviewPage op = new OverviewPage();
		
		Verify.waitForObject(ts.categoryTab, 1);
		if(Verify.objExists(ts.monthlySummaryHeader) && Verify.objExists(ts.categoryTab) && !Verify.objExists(op.hambergerIcon))
			Commentary.log(LogStatus.INFO, "PASS: Monthly Transaction Summary page is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Monthly Transaction Summary page is NOT displayed.");
		
		sp.backButtonOnHeaders.click();
		sp.clickOnReportsOption();
		
		Verify.waitForObject(sp.netIncomeOption, 1);
		sp.netIncomeOption.click();
		Thread.sleep(1000);
		
		NetIncomeOverTimePage not = new NetIncomeOverTimePage();
		
		Verify.waitForObject(not.netIncomeCurrentMonth, 1);
		if(Verify.objExists(not.netIncomeOverTimeHeader) && Verify.objExists(not.netIncomeCurrentMonth) && !Verify.objExists(op.hambergerIcon))
			Commentary.log(LogStatus.INFO, "PASS: Net Income by Month Time page is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Net Income by Month page is NOT displayed.");
		
		sp.backButtonOnHeaders.click();
		sp.clickOnReportsOption();
		
		Verify.waitForObject(sp.netWorthOption, 1);
		sp.netWorthOption.click();
		Thread.sleep(1000);
		
		NetWorthPage nw = new NetWorthPage();
		
		Verify.waitForObject(nw.netWorthText, 1);
		if(Verify.objExists(nw.netWorthText) && Verify.objExists(nw.assetsTab) && !Verify.objExists(op.hambergerIcon))
			Commentary.log(LogStatus.INFO, "PASS: Net Worth page is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Net Worth page is NOT displayed.");
		
		sp.backButtonOnHeaders.click();
		sp.clickOnReportsOption();
		
		Verify.waitForObject(sp.spendingByCategoryOption, 1);
		sp.spendingByCategoryOption.click();
		Thread.sleep(1000);
		
		SpendingTrendPage st = new SpendingTrendPage();
		
		Verify.waitForObject(st.last30Days, 1);
		if(Verify.objExists(st.spendingTrendHeader) && Verify.objExists(st.last30Days) && !Verify.objExists(op.hambergerIcon))
			Commentary.log(LogStatus.INFO, "PASS: Spending By Category page is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Spending By Category page is NOT displayed.");
		
		sp.backButtonOnHeaders.click();
		sp.clickOnReportsOption();
		
		Verify.waitForObject(sp.spendingOverTimeOption, 1);
		sp.spendingOverTimeOption.click();
		Thread.sleep(1000);
		
		SpendingOverTimePage sot = new SpendingOverTimePage();
		
		Verify.waitForObject(sot.totalSpendingCurrentMonth, 2);
		if(Verify.objExists(sot.spendingOverTimeHeader) && Verify.objExists(sot.totalSpendingCurrentMonth) && !Verify.objExists(op.hambergerIcon))
			Commentary.log(LogStatus.INFO, "PASS: Spending Over Time page is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Spending Over Time page is NOT displayed.");
		
		sa.assertAll();
	}
	
	@Test(priority=9, enabled = true)
	public void HM9_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verifying Profile option on the Hamburger Menu.");
		
		SettingsPage sp = new SettingsPage();
		sp.clickOnProfileOption();
		
		Verify.waitForObject(sp.PasscodeTxt, 1);
		if(Verify.objExists(sp.PasscodeTxt))
			Commentary.log(LogStatus.INFO, "PASS: \"Passcode\" option is displayed under Profile section.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Passcode\" option is NOT displayed under Profile section.");
		
		if(Verify.objExists(sp.HelpTxt))
			Commentary.log(LogStatus.INFO, "PASS: \"Help\" option is displayed under Profile section.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Help\" option is NOT displayed under Profile section.");
		
		if(Verify.objExists(sp.legalTxt))
			Commentary.log(LogStatus.INFO, "PASS: \"Legal\" option is displayed under Profile section.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Legal\" option is NOT displayed under Profile section.");
		
		sp.PasscodeTxt.click();
		Thread.sleep(2000);
		
		OverviewPage op = new OverviewPage();
		
		Verify.waitForObject(sp.useDevicePasscodeTxt, 1);
		if(Verify.objExists(sp.PasscodeHeaderTxt) && Verify.objExists(sp.useDevicePasscodeTxt) && !Verify.objExists(op.hambergerIcon))
			Commentary.log(LogStatus.INFO, "PASS: Passcode page is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Passcode page is NOT displayed.");
		
		sp.backButtonOnPasscodeHeader.click();
		Thread.sleep(2000);
		
		Verify.waitForObject(sp.HelpTxt, 1);
		sp.HelpTxt.click();
		Thread.sleep(2000);
		
		Verify.waitForObject(sp.supportOption, 1);
		if(Verify.objExists(sp.supportOption))
			Commentary.log(LogStatus.INFO, "PASS: Support Option is displayed under Help section.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Support Option is NOT displayed under Help section.");
		
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
	
	@Test(priority=10, enabled = true)
	public void HM10_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verifying Settings option on the Hamburger Menu.");
		
		SettingsPage sp = new SettingsPage();
		sp.clickOnSettingsOption();
		
		Verify.waitForObject(sp.customizeDashboardOption, 1);
		if(Verify.objExists(sp.customizeDashboardOption))
			Commentary.log(LogStatus.INFO, "PASS: \"Customize Dashboard\" option is displayed under Settings.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Customize Dashboard\" option is NOT displayed under Settings.");
		
		if(Verify.objExists(sp.accountsManagementOption))
			Commentary.log(LogStatus.INFO, "PASS: \"Accounts Management\" option is displayed under Settings.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Accounts Management\" option is NOT displayed under Settings.");
		
		if(Verify.objExists(sp.manageAlerts))
			Commentary.log(LogStatus.INFO, "PASS: \"Manage Alerts\" option is displayed under Settings.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Manage Alerts\" option is NOT displayed under Settings.");
		
		if(Verify.objExists(sp.rulesOption))
			Commentary.log(LogStatus.INFO, "PASS: \"Rules\" option is displayed under Settings.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Rules\" option is NOT displayed under Settings.");
		
		if(Verify.objExists(sp.tagsOption))
			Commentary.log(LogStatus.INFO, "PASS: \"Tags\" option is displayed under Settings.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Tags\" option is NOT displayed under Settings.");
		
		if(Verify.objExists(sp.displayYelpRecommendationsText) && Verify.objExists(sp.switchDisplayYelp))
			Commentary.log(LogStatus.INFO, "PASS: \"Display Yelp Recommendation\" option is displayed under Settings.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Display Yelp Recommendation\" option is NOT displayed under Settings.");
		
		if(Verify.objExists(sp.showLongCategoryNamesText) && Verify.objExists(sp.switchShowLongCategoryNames))
			Commentary.log(LogStatus.INFO, "PASS: \"Show long category names\" option is displayed under Settings.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Show long category names\" option is NOT displayed under Settings.");
		
		if(Verify.objExists(sp.displayFavoritePayeesText) && Verify.objExists(sp.switchDisplayFavoritePayees))
			Commentary.log(LogStatus.INFO, "PASS: \"Display Favorite Payees\" option is displayed under Settings.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Display Favorite Payees\" option is NOT displayed under Settings.");
		
		sa.assertAll();
	}
	
	@Test(priority=11, enabled = true)
	public void HM11_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verifying Renaming Rules option on the Hamburger Menu.");
		
		SettingsPage sp = new SettingsPage();
		sp.clickOnSettingsOption();
		
		Verify.waitForObject(sp.rulesOption, 1);
		sp.rulesOption.click();
		Thread.sleep(1000);
		
		Verify.waitForObject(sp.renamingRulesOption, 1);
		if(Verify.objExists(sp.renamingRulesOption))
			Commentary.log(LogStatus.INFO, "PASS: \"Renaming Rules\" option is displayed under Rules.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Renaming Rules\" option is NOT displayed under Rules.");
		
		sp.renamingRulesOption.click();
		
		Verify.waitForObject(sp.renamingRulesHeaderText, 1);
		if(Verify.objExists(sp.renamingRulesHeaderText) && Verify.objExists(sp.addRenamingRulesButton))
			Commentary.log(LogStatus.INFO, "PASS: Renaming Rules page is displayed and add Renaming rules button is present.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Either Renaming Rules page is NOT displayed or add renaming rules button is not present.");
		
		sa.assertAll();
	}
	
	@Test(priority=12, enabled = true)
	public void HM12_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verifying Tags option on the Hamburger Menu.");
		
		SettingsPage sp = new SettingsPage();
		sp.clickOnSettingsOption();
		
		Verify.waitForObject(sp.tagsOption, 1);
		sp.tagsOption.click();
		Thread.sleep(1000);
		
		Verify.waitForObject(sp.tagsHeaderText, 1);
		if(Verify.objExists(sp.tagsHeaderText) && Verify.objExists(sp.createNewTagOption))
			Commentary.log(LogStatus.INFO, "PASS: Tags page is displayed and Create New Tag button is present.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Either Tags page is NOT displayed or Create New Tag button is not present.");
		
		sa.assertAll();
	}
		
}
