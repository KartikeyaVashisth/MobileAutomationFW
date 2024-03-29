package tournament;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.BankingAndCreditCardPage;
import dugout.BillsAndIncomePage;
import dugout.OverviewPage;
import dugout.SettingsPage;
import dugout.SignInPage;
import dugout.TransactionsPage;
import dugout.WelcomePage;
import referee.Commentary;
import referee.Verify;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;
import support.UserName;

public class ScheduledTransactionsTest_5 extends Recovery {

	String sUserName = "quicken789@gmail.com";
	String sPassword = "Quicken@01";
	String sDataset = "ST Phase 2";
	String sDataset_stage = "ST_5";
	String sManualChecking = "Manual_Checking";
	String sManualSaving = "Manual_Savings";

	public String getUsername_basedOnEnv() throws Exception{

		UserName un = new UserName();
		un.stage_ios = "st_5_ios++@stage.com";
		un.stage_android = "st_5_android++@stage.com";
		un.prod_ios = "quicken789@gmail.com";
		un.prod_android = "quicken789@gmail.com";
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

	@Test (priority = 33, enabled = true)
	public void ST33_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		String sUsername = getUsername_basedOnEnv();
		WelcomePage w = new WelcomePage();
		w.setEnvironment(h.getEnv());

		SignInPage si = new SignInPage();
		if(h.getEnv().contentEquals("stage"))
			si.signIn(sUsername, sPassword, sDataset_stage);
		else
			si.signIn(sUsername, sPassword, sDataset);
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Entering the Bill reminder instance from the accounts page and verifying the changes in Today's and Projected Balances of Accounts afterwards.");

		String sTodaysBalance_WithReminderFilterOFF, sProjectedBalance_WithReminderFilterOFF;
		String sTodaysBalanceWith7DaysReminderFilter, sProjectedBalanceWith7DaysReminderFilter, sTodaysBalance_WithReminderFilterOFF_AfterEntering, sProjectedBalance_WithReminderFilterOFF_AfterEntering;
		String sTodaysBalanceWith7DaysReminderFilter_AfterEntering, sProjectedBalanceWith7DaysReminderFilter_AfterEntering;

		OverviewPage op = new OverviewPage();
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();

		op.tapOnBillsAndIncomeCard();

		BillsAndIncomePage bi = new BillsAndIncomePage();
		bi.deleteAlreadyPresentReminderSeries();

		bi.addNewReminderSeries("Bill");

		String reminderName = "Reminder_"+h.getCurrentTime();

		TransactionRecord tRec = new TransactionRecord();
		tRec.setPayee(reminderName);
		tRec.setAmount("20.00");
		tRec.setFromAccount(sManualChecking);
		tRec.setFrequency("Weekly");
		tRec.setCategory("Entertainment");
		tRec.setEndAfterNumberOfReminders("5");

		bi.addNewReminder(tRec);

		Verify.waitForObject(bi.backButton, 1);
		bi.backButton.click();
		Thread.sleep(1000);

		op.scrollToTop();
		
		SettingsPage sp = new SettingsPage();
		sp.setDefaultAccountBalancePreference("Today's");

		op.navigateToAcctList();

		bcc.selectAccount(sManualChecking);

		TransactionsPage tp = new TransactionsPage();

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();

		if(Verify.objExists(tp.dontShowReminderFilter)) {
			tp.dontShowReminderFilter.click();
			tp.buttonApply.click();
			Thread.sleep(6000);
		}

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalance_WithReminderFilterOFF = bcc.txtTodaysBalanceAmount.getText();

		bcc.scrollToProjectedBalance();
		Verify.waitForObject(bcc.txtProjectedBalanceAmount, 1);
		sProjectedBalance_WithReminderFilterOFF = bcc.txtProjectedBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance without Reminder filter: ["+sTodaysBalance_WithReminderFilterOFF+"]");
		Commentary.log(LogStatus.INFO, "Projected balance without Reminder filter: ["+sProjectedBalance_WithReminderFilterOFF+"]");

		Double dTodaysBalance_WithReminderFilterOFF = h.processBalanceAmount(sTodaysBalance_WithReminderFilterOFF);
		Double dProjectedBalance_WithReminderFilterOFF = h.processBalanceAmount(sProjectedBalance_WithReminderFilterOFF);

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		Verify.waitForObject(tp.next7DaysReminderFilter, 1);
		tp.next7DaysReminderFilter.click();
		tp.buttonApply.click();
		Thread.sleep(6000);

		sProjectedBalanceWith7DaysReminderFilter = bcc.txtProjectedBalanceAmount.getText();

		bcc.scrollToTotalBalance();

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalanceWith7DaysReminderFilter = bcc.txtTodaysBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance with Next 7 Days filter: ["+sTodaysBalanceWith7DaysReminderFilter+"]");
		Commentary.log(LogStatus.INFO, "Projected balance with Next 7 Days filter: ["+sProjectedBalanceWith7DaysReminderFilter+"]");

		Double dTodaysBalance_With7DaysReminderFilter = h.processBalanceAmount(sTodaysBalanceWith7DaysReminderFilter);
		Double dProjectedBalance_With7DaysReminderFilter = h.processBalanceAmount(sProjectedBalanceWith7DaysReminderFilter);

		tp.searchRecentTransaction(reminderName);
		tp.tapOnFirstTransaction();

		bi.tapOnEnterOption();

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalanceWith7DaysReminderFilter_AfterEntering = bcc.txtTodaysBalanceAmount.getText();

		bcc.scrollToProjectedBalance();

		Verify.waitForObject(bcc.txtProjectedBalanceAmount, 1);
		sProjectedBalanceWith7DaysReminderFilter_AfterEntering = bcc.txtProjectedBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance after Entering the reminder with Next 7 Days filter: ["+sTodaysBalanceWith7DaysReminderFilter_AfterEntering+"]");
		Commentary.log(LogStatus.INFO, "Projected balance after Entering the reminder with Next 7 Days filter: ["+sProjectedBalanceWith7DaysReminderFilter_AfterEntering+"]");

		Double dTodaysBalance_With7DaysReminderFilter_AfterEntering = h.processBalanceAmount(sTodaysBalanceWith7DaysReminderFilter_AfterEntering);
		Double dProjectedBalance_With7DaysReminderFilter_AfterEntering = h.processBalanceAmount(sProjectedBalanceWith7DaysReminderFilter_AfterEntering);

		Integer todaysBalanceCompare_With7DaysReminderFilter_AfterEntering = Double.compare(dTodaysBalance_With7DaysReminderFilter, dTodaysBalance_With7DaysReminderFilter_AfterEntering);
		Integer projectedBalanceCompare_7DaysReminderFilter_AfterEntering = Double.compare(dProjectedBalance_With7DaysReminderFilter, dProjectedBalance_With7DaysReminderFilter_AfterEntering);

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		Verify.waitForObject(tp.dontShowReminderFilter, 1);
		tp.dontShowReminderFilter.click();
		tp.buttonApply.click();
		Thread.sleep(6000);

		Verify.waitForObject(bcc.txtProjectedBalanceAmount, 1);
		sProjectedBalance_WithReminderFilterOFF_AfterEntering = bcc.txtProjectedBalanceAmount.getText();

		bcc.scrollToTotalBalance();

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalance_WithReminderFilterOFF_AfterEntering = bcc.txtTodaysBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance after Entering the Reminder with Show Reminder filter Off: ["+sTodaysBalance_WithReminderFilterOFF_AfterEntering+"]");
		Commentary.log(LogStatus.INFO, "Projected balance after Entering the Reminder with Show Reminder filter Off: ["+sProjectedBalance_WithReminderFilterOFF_AfterEntering+"]");

		Double dTodaysBalance_WithReminderFilterOFF_AfterEntering = h.processBalanceAmount(sTodaysBalance_WithReminderFilterOFF_AfterEntering);
		Double dProjectedBalance_WithReminderFilterOFF_AfterEntering = h.processBalanceAmount(sProjectedBalance_WithReminderFilterOFF_AfterEntering);

		Double d = Double.parseDouble(tRec.getAmount());
		
		int todaysBalanceCompare_WithReminderFilterOFF_AfterEntering = Double.compare(dTodaysBalance_WithReminderFilterOFF-d, dTodaysBalance_WithReminderFilterOFF_AfterEntering);
		int projectedBalanceCompare_WithReminderFilterOFF_AfterEntering = Double.compare(dProjectedBalance_WithReminderFilterOFF-d, dProjectedBalance_WithReminderFilterOFF_AfterEntering);

//		if(dTodaysBalance_WithReminderFilterOFF-d==dTodaysBalance_WithReminderFilterOFF_AfterEntering)
		if(todaysBalanceCompare_WithReminderFilterOFF_AfterEntering == 0)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Today's balance with Reminder filter OFF: ["+sTodaysBalance_WithReminderFilterOFF_AfterEntering+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Today's balance with Reminder filter OFF is NOT correct.");

//		if(dProjectedBalance_WithReminderFilterOFF-d==dProjectedBalance_WithReminderFilterOFF_AfterEntering)
		if(projectedBalanceCompare_WithReminderFilterOFF_AfterEntering == 0)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Projected balance with Reminder filter OFF is: ["+sProjectedBalance_WithReminderFilterOFF_AfterEntering+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected balance with Reminder filter OFF is NOT correct.");

		if(todaysBalanceCompare_With7DaysReminderFilter_AfterEntering==0)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Today's balance after entering the reminder with Next 7 Days filter is: ["+sTodaysBalanceWith7DaysReminderFilter_AfterEntering+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Today's balance after entering the reminder with Next 7 Days filter is NOT correct.");

		if(projectedBalanceCompare_7DaysReminderFilter_AfterEntering==0)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Projected balance after entering the reminder with Next 7 Days filter is: ["+sProjectedBalanceWith7DaysReminderFilter_AfterEntering+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected balance after entering the reminder with Next 7 Days filter is NOT correct.");

		bcc.backButton.click();
		Thread.sleep(1000);
		bcc.backButton.click();
		Thread.sleep(1000);

		op.tapOnBillsAndIncomeCard();

		bi.deleteSeries();

		sa.assertAll();
	}

	@Test (priority = 34, enabled = true)
	public void ST34_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating \"Enter all previous instances\" option from the accounts page and verifying the changes in Today's and Projected Balances of accounts afterwards.");

		String sTodaysBalance_WithReminderFilterOFF, sProjectedBalance_WithReminderFilterOFF;
		String sTodaysBalanceWith7DaysReminderFilter, sProjectedBalanceWith7DaysReminderFilter, sTodaysBalance_WithReminderFilterOFF_AfterEntering, sProjectedBalance_WithReminderFilterOFF_AfterEntering;
		String sTodaysBalanceWith7DaysReminderFilter_AfterEntering, sProjectedBalanceWith7DaysReminderFilter_AfterEntering;

		OverviewPage op = new OverviewPage();
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();

		op.tapOnBillsAndIncomeCard();

		BillsAndIncomePage bi = new BillsAndIncomePage();
		bi.deleteAlreadyPresentReminderSeries();

		bi.addNewReminderSeries("Bill");

		String reminderName = "Reminder_"+h.getCurrentTime();

		TransactionRecord tRec = new TransactionRecord();
		tRec.setPayee(reminderName);
		tRec.setAmount("20.00");
		tRec.setFromAccount(sManualChecking);
		tRec.setDate(h.getPastDaysDate(14));
		tRec.setFrequency("Weekly");
		tRec.setEndAfterNumberOfReminders("3");

		bi.addNewReminder(tRec);

		Verify.waitForObject(bi.backButton, 1);
		bi.backButton.click();
		Thread.sleep(1000);

		op.scrollToTop();
		
		SettingsPage sp = new SettingsPage();
		sp.setDefaultAccountBalancePreference("Today's");

		op.navigateToAcctList();

		bcc.selectAccount(sManualChecking);

		TransactionsPage tp = new TransactionsPage();

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();

		if(Verify.objExists(tp.dontShowReminderFilter)) {
			tp.dontShowReminderFilter.click();
			tp.buttonApply.click();
			Thread.sleep(6000);
		}

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalance_WithReminderFilterOFF = bcc.txtTodaysBalanceAmount.getText();

		bcc.scrollToProjectedBalance();
		sProjectedBalance_WithReminderFilterOFF = bcc.txtProjectedBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance with Show Reminder filter Off: ["+sTodaysBalance_WithReminderFilterOFF+"]");
		Commentary.log(LogStatus.INFO, "Projected balance with Show Reminder filter Off: ["+sProjectedBalance_WithReminderFilterOFF+"]");

		Double dTodaysBalance_WithReminderFilterOFF = h.processBalanceAmount(sTodaysBalance_WithReminderFilterOFF);
		Double dProjectedBalance_WithReminderFilterOFF = h.processBalanceAmount(sProjectedBalance_WithReminderFilterOFF);

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		Verify.waitForObject(tp.next7DaysReminderFilter, 1);
		tp.next7DaysReminderFilter.click();
		tp.buttonApply.click();
		Thread.sleep(6000);

		sProjectedBalanceWith7DaysReminderFilter = bcc.txtProjectedBalanceAmount.getText();

		bcc.scrollToTotalBalance();

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalanceWith7DaysReminderFilter = bcc.txtTodaysBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance with Next 7 Days filter : ["+sTodaysBalanceWith7DaysReminderFilter+"]");
		Commentary.log(LogStatus.INFO, "Projected balance with Next 7 Days filter : ["+sProjectedBalanceWith7DaysReminderFilter+"]");

		Double dTodaysBalance_With7DaysReminderFilter = h.processBalanceAmount(sTodaysBalanceWith7DaysReminderFilter);
		Double dProjectedBalance_With7DaysReminderFilter = h.processBalanceAmount(sProjectedBalanceWith7DaysReminderFilter);

		tp.searchRecentTransaction(reminderName);
		tp.tapOnFirstTransaction();

		bi.tapOnEnterAllPreviousInstancesOption();

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalanceWith7DaysReminderFilter_AfterEntering = bcc.txtTodaysBalanceAmount.getText();

		bcc.scrollToProjectedBalance();

		sProjectedBalanceWith7DaysReminderFilter_AfterEntering = bcc.txtProjectedBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance after Entering all previous reminders instances with Next 7 Days filter: ["+sTodaysBalanceWith7DaysReminderFilter_AfterEntering+"]");
		Commentary.log(LogStatus.INFO, "Projected balance after Entering all previous reminders instances with Next 7 Days filter: ["+sProjectedBalanceWith7DaysReminderFilter_AfterEntering+"]");

		Double dTodaysBalance_With7DaysReminderFilter_AfterEntering = h.processBalanceAmount(sTodaysBalanceWith7DaysReminderFilter_AfterEntering);
		Double dProjectedBalance_With7DaysReminderFilter_AfterEntering = h.processBalanceAmount(sProjectedBalanceWith7DaysReminderFilter_AfterEntering);

		Integer todaysBalanceCompare_With7DaysReminderFilter_AfterEntering = Double.compare(dTodaysBalance_With7DaysReminderFilter, dTodaysBalance_With7DaysReminderFilter_AfterEntering);
		Integer projectedBalanceCompare_7DaysReminderFilter_AfterEntering = Double.compare(dProjectedBalance_With7DaysReminderFilter, dProjectedBalance_With7DaysReminderFilter_AfterEntering);

		if(todaysBalanceCompare_With7DaysReminderFilter_AfterEntering==0)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Today's balance after entering the reminders instances with Next 7 Days filter is: ["+sTodaysBalanceWith7DaysReminderFilter_AfterEntering+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Today's balance after entering the reminders instances with Next 7 Days filter is NOT correct.");

		if(projectedBalanceCompare_7DaysReminderFilter_AfterEntering==0)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Projected balance after entering the reminders instances with Next 7 Days filter is: ["+sProjectedBalanceWith7DaysReminderFilter_AfterEntering+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected balance after entering the reminders instances with Next 7 Days filter is NOT correct.");

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		Verify.waitForObject(tp.dontShowReminderFilter, 1);
		tp.dontShowReminderFilter.click();
		tp.buttonApply.click();
		Thread.sleep(6000);

		Verify.waitForObject(bcc.txtProjectedBalanceAmount, 1);
		sProjectedBalance_WithReminderFilterOFF_AfterEntering = bcc.txtProjectedBalanceAmount.getText();

		bcc.scrollToTotalBalance();

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalance_WithReminderFilterOFF_AfterEntering = bcc.txtTodaysBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance after Entering the Reminders instances with Show Reminder filter Off: ["+sTodaysBalance_WithReminderFilterOFF_AfterEntering+"]");
		Commentary.log(LogStatus.INFO, "Projected balance after Entering the Reminders instances with Show Reminder filter Off: ["+sProjectedBalance_WithReminderFilterOFF_AfterEntering+"]");

		Double dTodaysBalance_WithReminderFilterOFF_AfterEntering = h.processBalanceAmount(sTodaysBalance_WithReminderFilterOFF_AfterEntering);
		Double dProjectedBalance_WithReminderFilterOFF_AfterEntering = h.processBalanceAmount(sProjectedBalance_WithReminderFilterOFF_AfterEntering);

		Double d = Double.parseDouble(tRec.getAmount());
		
		int todaysBalanceCompare_WithReminderFilterOFF_AfterEntering = Double.compare(dTodaysBalance_WithReminderFilterOFF-d*3d, dTodaysBalance_WithReminderFilterOFF_AfterEntering);
		int projectedBalanceCompare_WithReminderFilterOFF_AfterEntering = Double.compare(dProjectedBalance_WithReminderFilterOFF-d*3d, dProjectedBalance_WithReminderFilterOFF_AfterEntering);

//		if(dTodaysBalance_WithReminderFilterOFF-d*3d==dTodaysBalance_WithReminderFilterOFF_AfterEntering)
		if(todaysBalanceCompare_WithReminderFilterOFF_AfterEntering == 0)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Today's balance with Reminders filter OFF after entering reminder instances is: ["+sTodaysBalance_WithReminderFilterOFF_AfterEntering+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Today's balance with Reminders filter OFF after entering reminder instances is NOT correct.");

//		if(dProjectedBalance_WithReminderFilterOFF-d*3d==dProjectedBalance_WithReminderFilterOFF_AfterEntering)
		if(projectedBalanceCompare_WithReminderFilterOFF_AfterEntering == 0)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Projected balance with Reminders filter OFF after entering reminder instances is: ["+sProjectedBalance_WithReminderFilterOFF_AfterEntering+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected balance with Reminders filter OFF after entering reminder instances is NOT correct.");

		bcc.backButton.click();
		Thread.sleep(1000);
		bcc.backButton.click();
		Thread.sleep(1000);

		op.tapOnBillsAndIncomeCard();

		bi.deleteSeries();

		sa.assertAll();
	}

	@Test (priority = 35, enabled = true)
	public void ST35_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Ignoring the Bill reminder instance from the account page and verifying the changes in Today's and Projected Balances of accounts afterwards.");

		String sTodaysBalance_WithReminderFilterOFF, sProjectedBalance_WithReminderFilterOFF;
		String sTodaysBalanceWith7DaysReminderFilter, sProjectedBalanceWith7DaysReminderFilter, sTodaysBalance_WithReminderFilterOFF_AfterIgnoring, sProjectedBalance_WithReminderFilterOFF_AfterIgnoring;
		String sTodaysBalanceWith7DaysReminderFilter_AfterIgnoring, sProjectedBalanceWith7DaysReminderFilter_AfterIgnoring;

		OverviewPage op = new OverviewPage();
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();

		op.tapOnBillsAndIncomeCard();

		BillsAndIncomePage bi = new BillsAndIncomePage();
		bi.deleteAlreadyPresentReminderSeries();

		bi.addNewReminderSeries("Bill");

		String reminderName = "Reminder_"+h.getCurrentTime();

		TransactionRecord tRec = new TransactionRecord();
		tRec.setPayee(reminderName);
		tRec.setAmount("20.00");
		tRec.setFromAccount(sManualChecking);
		tRec.setCategory("Entertainment");

		bi.addNewReminder(tRec);

		Verify.waitForObject(bi.backButton, 1);
		bi.backButton.click();
		Thread.sleep(1000);

		op.scrollToTop();

		SettingsPage sp = new SettingsPage();
		sp.setDefaultAccountBalancePreference("Today's");
		
		op.navigateToAcctList();

		bcc.selectAccount(sManualChecking);

		TransactionsPage tp = new TransactionsPage();

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();

		if(Verify.objExists(tp.dontShowReminderFilter)) {
			tp.dontShowReminderFilter.click();
			tp.buttonApply.click();
			Thread.sleep(6000);
		}

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalance_WithReminderFilterOFF = bcc.txtTodaysBalanceAmount.getText();

		bcc.scrollToProjectedBalance();
		sProjectedBalance_WithReminderFilterOFF = bcc.txtProjectedBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance with Show Reminder filter Off: ["+sTodaysBalance_WithReminderFilterOFF+"]");
		Commentary.log(LogStatus.INFO, "Projected balance with Show Reminder filter Off: ["+sProjectedBalance_WithReminderFilterOFF+"]");

		Double dTodaysBalance_WithReminderFilterOFF = h.processBalanceAmount(sTodaysBalance_WithReminderFilterOFF);
		Double dProjectedBalance_WithReminderFilterOFF = h.processBalanceAmount(sProjectedBalance_WithReminderFilterOFF);

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		Verify.waitForObject(tp.next7DaysReminderFilter, 1);
		tp.next7DaysReminderFilter.click();
		tp.buttonApply.click();
		Thread.sleep(6000);

		sProjectedBalanceWith7DaysReminderFilter = bcc.txtProjectedBalanceAmount.getText();

		bcc.scrollToTotalBalance();

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalanceWith7DaysReminderFilter = bcc.txtTodaysBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance with 7 Days Reminder filter is: ["+sTodaysBalanceWith7DaysReminderFilter+"]");
		Commentary.log(LogStatus.INFO, "Projected balance with 7 Days Reminder filter is: ["+sProjectedBalanceWith7DaysReminderFilter+"]");

		Double dTodaysBalance_With7DaysReminderFilter = h.processBalanceAmount(sTodaysBalanceWith7DaysReminderFilter);
		Double dProjectedBalance_With7DaysReminderFilter = h.processBalanceAmount(sProjectedBalanceWith7DaysReminderFilter);

		tp.searchRecentTransaction(reminderName);
		tp.tapOnFirstTransaction();

		bi.tapOnIgnoreThisInstanceOption();

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalanceWith7DaysReminderFilter_AfterIgnoring = bcc.txtTodaysBalanceAmount.getText();

		bcc.scrollToProjectedBalance();

		Verify.waitForObject(bcc.txtProjectedBalanceAmount, 1);
		sProjectedBalanceWith7DaysReminderFilter_AfterIgnoring = bcc.txtProjectedBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance after ignoring the reminder with Next 7 Days filter : ["+sTodaysBalanceWith7DaysReminderFilter_AfterIgnoring+"]");
		Commentary.log(LogStatus.INFO, "Projected balance after ignoring the reminder with Next 7 Days filter : ["+sProjectedBalanceWith7DaysReminderFilter_AfterIgnoring+"]");

		Double dTodaysBalance_With7DaysReminderFilter_AfterIgnoring = h.processBalanceAmount(sTodaysBalanceWith7DaysReminderFilter_AfterIgnoring);
		Double dProjectedBalance_With7DaysReminderFilter_AfterIgnoring = h.processBalanceAmount(sProjectedBalanceWith7DaysReminderFilter_AfterIgnoring);

		Double d = Double.parseDouble(tRec.getAmount());
		
		int todaysBalanceCompare_With7DaysReminderFilter_AfterIgnoring = Double.compare(dTodaysBalance_With7DaysReminderFilter+d, dTodaysBalance_With7DaysReminderFilter_AfterIgnoring);
		int projectedBalanceCompare_With7DaysReminderFilter_AfterIgnoring = Double.compare(dProjectedBalance_With7DaysReminderFilter+d, dProjectedBalance_With7DaysReminderFilter_AfterIgnoring);

//		if(dTodaysBalance_With7DaysReminderFilter+d==dTodaysBalance_With7DaysReminderFilter_AfterIgnoring)
		if(todaysBalanceCompare_With7DaysReminderFilter_AfterIgnoring == 0)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Today's balance with Next 7 Days filter is "+sTodaysBalanceWith7DaysReminderFilter_AfterIgnoring+".");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Today's balance with Next 7 Days filter is NOT correct.");

//		if(dProjectedBalance_With7DaysReminderFilter+d==dProjectedBalance_With7DaysReminderFilter_AfterIgnoring)
		if(projectedBalanceCompare_With7DaysReminderFilter_AfterIgnoring == 0)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Projected balance with Next 7 Days filter is "+sProjectedBalanceWith7DaysReminderFilter_AfterIgnoring+".");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected balance with Next 7 Days filter is NOT correct.");

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		Verify.waitForObject(tp.dontShowReminderFilter, 1);
		tp.dontShowReminderFilter.click();
		tp.buttonApply.click();
		Thread.sleep(6000);

		Verify.waitForObject(bcc.txtProjectedBalanceAmount, 1);
		sProjectedBalance_WithReminderFilterOFF_AfterIgnoring = bcc.txtProjectedBalanceAmount.getText();

		bcc.scrollToTotalBalance();

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalance_WithReminderFilterOFF_AfterIgnoring = bcc.txtTodaysBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance after Ignoring the Reminder with Show Reminder filter Off: ["+sTodaysBalance_WithReminderFilterOFF_AfterIgnoring+"]");
		Commentary.log(LogStatus.INFO, "Projected balance after Ignoring the Reminder with Show Reminder filter Off: ["+sProjectedBalance_WithReminderFilterOFF_AfterIgnoring+"]");

		Double dTodaysBalance_WithReminderFilterOFF_AfterIgnoring = h.processBalanceAmount(sTodaysBalance_WithReminderFilterOFF_AfterIgnoring);
		Double dProjectedBalance_WithReminderFilterOFF_AfterIgnoring = h.processBalanceAmount(sProjectedBalance_WithReminderFilterOFF_AfterIgnoring);

		Integer todaysBalanceCompare_WithReminderFilterOFF_AfterIgnoring = Double.compare(dTodaysBalance_WithReminderFilterOFF, dTodaysBalance_WithReminderFilterOFF_AfterIgnoring);
		Integer projectedBalanceCompare_WithReminderFilterOFF_AfterIgnoring = Double.compare(dProjectedBalance_WithReminderFilterOFF, dProjectedBalance_WithReminderFilterOFF_AfterIgnoring);

		if(todaysBalanceCompare_WithReminderFilterOFF_AfterIgnoring==0)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Today's balance after ignoring the reminder is "+sTodaysBalance_WithReminderFilterOFF_AfterIgnoring+".");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Today's balance after ignoring the reminder is NOT correct.");

		if(projectedBalanceCompare_WithReminderFilterOFF_AfterIgnoring==0)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Projected balance after ignoring the reminder is "+sProjectedBalance_WithReminderFilterOFF_AfterIgnoring+".");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected balance after ignoring the reminder is NOT correct.");

		bcc.backButton.click();
		Thread.sleep(1000);
		bcc.backButton.click();
		Thread.sleep(1000);

		op.tapOnBillsAndIncomeCard();

		bi.deleteSeries();

		sa.assertAll();
	}

	@Test (priority = 36, enabled = true)
	public void ST36_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating \"Ignore all previous instances\" option from the accounts page and verifying the changes in Today's and Projected Balances of Accounts afterwards.");

		String sTodaysBalance_WithReminderFilterOFF, sProjectedBalance_WithReminderFilterOFF;
		String sTodaysBalanceWith7DaysReminderFilter, sProjectedBalanceWith7DaysReminderFilter, sTodaysBalance_WithReminderFilterOFF_AfterIgnoring, sProjectedBalance_WithReminderFilterOFF_AfterIgnoring;
		String sTodaysBalanceWith7DaysReminderFilter_AfterIgnoring, sProjectedBalanceWith7DaysReminderFilter_AfterIgnoring;

		OverviewPage op = new OverviewPage();
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();

		op.tapOnBillsAndIncomeCard();

		BillsAndIncomePage bi = new BillsAndIncomePage();
		bi.deleteAlreadyPresentReminderSeries();

		bi.addNewReminderSeries("Bill");

		String reminderName = "Reminder_"+h.getCurrentTime();

		TransactionRecord tRec = new TransactionRecord();
		tRec.setPayee(reminderName);
		tRec.setAmount("20.00");
		tRec.setFromAccount(sManualChecking);
		tRec.setDate(h.getPastDaysDate(14));
		tRec.setFrequency("Weekly");
		tRec.setEndAfterNumberOfReminders("3");

		bi.addNewReminder(tRec);

		Verify.waitForObject(bi.backButton, 1);
		bi.backButton.click();
		Thread.sleep(1000);

		op.scrollToTop();
		
		SettingsPage sp = new SettingsPage();
		sp.setDefaultAccountBalancePreference("Today's");

		op.navigateToAcctList();

		bcc.selectAccount(sManualChecking);

		TransactionsPage tp = new TransactionsPage();

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();

		if(Verify.objExists(tp.dontShowReminderFilter)) {
			tp.dontShowReminderFilter.click();
			tp.buttonApply.click();
			Thread.sleep(6000);
		}

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalance_WithReminderFilterOFF = bcc.txtTodaysBalanceAmount.getText();

		bcc.scrollToProjectedBalance();
		sProjectedBalance_WithReminderFilterOFF = bcc.txtProjectedBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance with Show Reminder filter Off: ["+sTodaysBalance_WithReminderFilterOFF+"]");
		Commentary.log(LogStatus.INFO, "Projected balance with Show Reminder filter Off: ["+sProjectedBalance_WithReminderFilterOFF+"]");

		Double dTodaysBalance_WithReminderFilterOFF = h.processBalanceAmount(sTodaysBalance_WithReminderFilterOFF);
		Double dProjectedBalance_WithReminderFilterOFF = h.processBalanceAmount(sProjectedBalance_WithReminderFilterOFF);

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		Verify.waitForObject(tp.next7DaysReminderFilter, 1);
		tp.next7DaysReminderFilter.click();
		tp.buttonApply.click();
		Thread.sleep(6000);

		sProjectedBalanceWith7DaysReminderFilter = bcc.txtProjectedBalanceAmount.getText();

		bcc.scrollToTotalBalance();

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalanceWith7DaysReminderFilter = bcc.txtTodaysBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance with 7 Days Reminder filter is: ["+sTodaysBalanceWith7DaysReminderFilter+"]");
		Commentary.log(LogStatus.INFO, "Projected balance with 7 Days Reminder filter is: ["+sProjectedBalanceWith7DaysReminderFilter+"]");

		Double dTodaysBalance_With7DaysReminderFilter = h.processBalanceAmount(sTodaysBalanceWith7DaysReminderFilter);
		Double dProjectedBalance_With7DaysReminderFilter = h.processBalanceAmount(sProjectedBalanceWith7DaysReminderFilter);

		tp.searchRecentTransaction(reminderName);
		tp.tapOnFirstTransaction();

		bi.tapOnIgnoreAllPreviousInstancesOption();

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalanceWith7DaysReminderFilter_AfterIgnoring = bcc.txtTodaysBalanceAmount.getText();

		bcc.scrollToProjectedBalance();

		sProjectedBalanceWith7DaysReminderFilter_AfterIgnoring = bcc.txtProjectedBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance after ignoring the reminders instances with Next 7 Days filter: ["+sTodaysBalanceWith7DaysReminderFilter_AfterIgnoring+"]");
		Commentary.log(LogStatus.INFO, "Projected balance after ignoring the reminders instances with Next 7 Days filter: ["+sProjectedBalanceWith7DaysReminderFilter_AfterIgnoring+"]");

		Double dTodaysBalance_With7DaysReminderFilter_AfterIgnoring = h.processBalanceAmount(sTodaysBalanceWith7DaysReminderFilter_AfterIgnoring);
		Double dProjectedBalance_With7DaysReminderFilter_AfterIgnoring = h.processBalanceAmount(sProjectedBalanceWith7DaysReminderFilter_AfterIgnoring);

		Double d = Double.parseDouble(tRec.getAmount());
		
		int todaysBalanceCompare_With7DaysReminderFilter_AfterIgnoring = Double.compare(dTodaysBalance_With7DaysReminderFilter+d*3d, dTodaysBalance_With7DaysReminderFilter_AfterIgnoring);
		int projectedBalanceCompare_With7DaysReminderFilter_AfterIgnoring = Double.compare(dProjectedBalance_With7DaysReminderFilter+d*3d, dProjectedBalance_With7DaysReminderFilter_AfterIgnoring);

//		if(dTodaysBalance_With7DaysReminderFilter+d*3d==dTodaysBalance_With7DaysReminderFilter_AfterIgnoring)
		if(todaysBalanceCompare_With7DaysReminderFilter_AfterIgnoring == 0)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Today's balance with Next 7 Days filter is: ["+sTodaysBalanceWith7DaysReminderFilter_AfterIgnoring+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Today's balance with Next 7 Days filter is NOT correct.");

//		if(dProjectedBalance_With7DaysReminderFilter+d*3d==dProjectedBalance_With7DaysReminderFilter_AfterIgnoring)
		if(projectedBalanceCompare_With7DaysReminderFilter_AfterIgnoring == 0)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Projected balance with Next 7 Days filter is: ["+sProjectedBalanceWith7DaysReminderFilter_AfterIgnoring+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected balance with Next 7 Days filter is NOT correct.");

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		Verify.waitForObject(tp.dontShowReminderFilter, 1);
		tp.dontShowReminderFilter.click();
		tp.buttonApply.click();
		Thread.sleep(6000);

		Verify.waitForObject(bcc.txtProjectedBalanceAmount, 1);
		sProjectedBalance_WithReminderFilterOFF_AfterIgnoring = bcc.txtProjectedBalanceAmount.getText();

		bcc.scrollToTotalBalance();

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalance_WithReminderFilterOFF_AfterIgnoring = bcc.txtTodaysBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance after Ignoring the Reminders instances with Show Reminder filter Off: ["+sTodaysBalance_WithReminderFilterOFF_AfterIgnoring+"]");
		Commentary.log(LogStatus.INFO, "Projected balance after Ignoring the Reminders instances with Show Reminder filter Off: ["+sProjectedBalance_WithReminderFilterOFF_AfterIgnoring+"]");

		Double dTodaysBalance_WithReminderFilterOFF_AfterIgnoring = h.processBalanceAmount(sTodaysBalance_WithReminderFilterOFF_AfterIgnoring);
		Double dProjectedBalance_WithReminderFilterOFF_AfterIgnoring = h.processBalanceAmount(sProjectedBalance_WithReminderFilterOFF_AfterIgnoring);

		Integer todaysBalanceCompare_WithReminderFilterOFF_AfterIgnoring = Double.compare(dTodaysBalance_WithReminderFilterOFF, dTodaysBalance_WithReminderFilterOFF_AfterIgnoring);
		Integer projectedBalanceCompare_WithReminderFilterOFF_AfterIgnoring = Double.compare(dProjectedBalance_WithReminderFilterOFF, dProjectedBalance_WithReminderFilterOFF_AfterIgnoring);

		if(todaysBalanceCompare_WithReminderFilterOFF_AfterIgnoring==0)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Today's balance after ignoring the reminders instances with Show Reminders filter OFF is: ["+sTodaysBalance_WithReminderFilterOFF_AfterIgnoring+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Today's balance after ignoring the reminders instances with Show Reminders filter OFF is NOT correct.");

		if(projectedBalanceCompare_WithReminderFilterOFF_AfterIgnoring==0)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Projected balance after ignoring the reminders instances with Show Reminders filter OFF is: ["+sProjectedBalance_WithReminderFilterOFF_AfterIgnoring+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected balance after ignoring the reminders instances with Show Reminders filter OFF is NOT correct.");

		bcc.backButton.click();
		Thread.sleep(1000);
		bcc.backButton.click();
		Thread.sleep(1000);

		op.tapOnBillsAndIncomeCard();

		bi.deleteSeries();

		sa.assertAll();
	}

	@Test (priority = 37, enabled = true)
	public void ST37_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating by deleting the Reminder Series from accounts page after selecting \"Delete this and future instances\" option.");

		OverviewPage op = new OverviewPage();
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();

		op.tapOnBillsAndIncomeCard();

		BillsAndIncomePage bi = new BillsAndIncomePage();
		bi.deleteAlreadyPresentReminderSeries();

		bi.addNewReminderSeries("Bill");

		String reminderName = "Reminder_"+h.getCurrentTime();

		TransactionRecord tRec = new TransactionRecord();
		tRec.setPayee(reminderName);
		tRec.setAmount("20.00");
		tRec.setFromAccount(sManualChecking);

		bi.addNewReminder(tRec);

		Verify.waitForObject(bi.backButton, 1);
		bi.backButton.click();
		Thread.sleep(1000);

		op.scrollToTop();

		SettingsPage sp = new SettingsPage();
		sp.setDefaultAccountBalancePreference("Today's");
		
		op.navigateToAcctList();

		bcc.selectAccount(sManualChecking);

		TransactionsPage tp = new TransactionsPage();

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();

		if(Verify.objExists(tp.next7DaysReminderFilter)) {
			tp.next7DaysReminderFilter.click();
			tp.buttonApply.click();
			Thread.sleep(4000);
		}

		tp.searchRecentTransaction(reminderName);
		tp.tapOnFirstTransaction();

		bi.tapOnDeleteThisAndFutureInstances();

		Verify.waitForObject(bi.deleteThisAndFutureInstancesInfoPopUp, 1);
		if(Verify.objExists(bi.deleteThisAndFutureInstancesInfoPopUp)) 
			Commentary.log(LogStatus.INFO, "PASS: Delete confirmation alert pop up is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Delete confirmation alert pop up is NOT displayed.");

		bi.deleteReminderSeriesAlertButton.click();
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 1);

		bcc.backButton.click();
		Thread.sleep(1000);
		bcc.backButton.click();
		Thread.sleep(1000);

		op.tapOnBillsAndIncomeCard();

		Verify.waitForObject(bi.noRemindersDueNext7Days, 1);
		if(Verify.objExists(bi.noRemindersDueNext7Days))
			Commentary.log(LogStatus.INFO, "PASS: Correct message is displayed when there are no Scheduled Reminders for Next 7 Days.");
		else 
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Correct message is NOT displayed when there are no Scheduled Reminders.");

		bi.tapOnSeriesTab();

		Verify.waitForObject(bi.youHaveNoScheduledReminders, 1);

		if (Verify.objExists(bi.youHaveNoScheduledReminders))
			Commentary.log(LogStatus.INFO, "PASS: \"You have no Scheduled Reminders\" message is displayed when there is no Reminder series.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"You have no Scheduled Reminders\" message is not displayed.");

		sa.assertAll();
	}
	
	@Test (priority = 38, enabled = true)
	public void ST38_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating \"Due Old to New\" and \"Due New to Old\" option.");

		OverviewPage op = new OverviewPage();

		op.tapOnBillsAndIncomeCard();

		BillsAndIncomePage bi = new BillsAndIncomePage();
		bi.deleteAlreadyPresentReminderSeries();

		bi.addNewReminderSeries("Bill");

		String reminderName = "Reminder_"+h.getCurrentTime();

		TransactionRecord tRec = new TransactionRecord();
		tRec.setPayee(reminderName);
		tRec.setAmount("20.00");
		tRec.setFromAccount(sManualChecking);
		tRec.setDate(h.getPastDaysDate(60));
		tRec.setEndAfterNumberOfReminders("3");

		bi.addNewReminder(tRec);
		
		bi.selectingDueOldToNewOption();
		
		LocalDate currentdate = LocalDate.now();
		Month currentMonth = currentdate.getMonth();
		String currentMonthSubstring = currentMonth.toString().substring(0,3);

		Month previousMonth = currentMonth.minus(1);
		String previousMonthSubstring = previousMonth.toString().substring(0, 3);

		Month previousToPreviousMonth = currentMonth.minus(2);
		String previousToPreviousMonthSubstring = previousToPreviousMonth.toString().substring(0, 3);
		
		List<WebElement> li = bi.getAllReminderEntriesDate();
		Commentary.log(LogStatus.INFO, "No of Reminder instances appeared in the search .."+li.size());

		String dateOfFirstReminderInstance = li.get(0).getText();
		String monthSubstringForDateOfFirstReminderInstance = dateOfFirstReminderInstance.toString().substring(0, 3);

		String dateOfSecondReminderInstance = li.get(1).getText();
		String monthSubstringForDateOfSecondReminderInstance = dateOfSecondReminderInstance.toString().substring(0, 3);
		
		String dateOfThirdReminderInstance = li.get(2).getText();
		String monthSubstringForDateOfThirdReminderInstance = dateOfThirdReminderInstance.toString().substring(0, 3);

		if(monthSubstringForDateOfFirstReminderInstance.equalsIgnoreCase(previousToPreviousMonthSubstring))
			Commentary.log(LogStatus.INFO, "Previous to Previous month date is seen first when filter Due Old to New is selected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "Previous to Previous month date is NOT seen first when filter Due Old to New is selected.");
		
		if(monthSubstringForDateOfSecondReminderInstance.equalsIgnoreCase(previousMonthSubstring))
			Commentary.log(LogStatus.INFO, "Previous month date is seen second when filter Due Old to New is selected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "Previous month date is NOT seen second when filter Due Old to New is selected.");

		if(monthSubstringForDateOfThirdReminderInstance.equalsIgnoreCase(currentMonthSubstring) || dateOfThirdReminderInstance.equalsIgnoreCase("Today") || dateOfThirdReminderInstance.equalsIgnoreCase("Yesterday") || dateOfThirdReminderInstance.equalsIgnoreCase("Tomorrow"))
			Commentary.log(LogStatus.INFO, "Current month date is seen third when filter Due Old to New is selected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "Current month date is NOT seen third when filter Due Old to New is selected.");
		
		bi.selectingDueNewToOldOption();

		List<WebElement> li1 = bi.getAllReminderEntriesDate();
		
		String dateOfFirstReminderInstance_NewToOldFilter = li1.get(0).getText();
		
		String dateOfSecondReminderInstance_NewToOldFilter = li1.get(1).getText();
		String monthSubstringForDateOfSecondReminderInstance_NewToOldFilter = dateOfSecondReminderInstance_NewToOldFilter.toString().substring(0, 3);
		
		String dateOfThirdReminderInstance_NewToOldFilter = li1.get(2).getText();
		String monthSubstringForDateOfThirdReminderInstance_NewToOldFilter = dateOfThirdReminderInstance_NewToOldFilter.toString().substring(0, 3);

		if(dateOfFirstReminderInstance_NewToOldFilter.equalsIgnoreCase("Today") || dateOfThirdReminderInstance.equalsIgnoreCase("Yesterday") || dateOfFirstReminderInstance_NewToOldFilter.equalsIgnoreCase("Tomorrow"))
			Commentary.log(LogStatus.INFO, "Current month date is seen first when filter Due New to Old is selected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "Current month date is NOT seen first when filter Due New to Old is selected.");
		
		if(monthSubstringForDateOfSecondReminderInstance_NewToOldFilter.toString().equalsIgnoreCase(previousMonthSubstring))
			Commentary.log(LogStatus.INFO, "Previous month date is seen second when filter Due New to Old is selected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "Previous month date is NOT seen second when filter Due New to Old is selected.");
		
		if(monthSubstringForDateOfThirdReminderInstance_NewToOldFilter.toString().equalsIgnoreCase(previousToPreviousMonthSubstring))
			Commentary.log(LogStatus.INFO, "Previous to Previous month date is seen third when filter Due New to Old is selected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "Previous to Previous month date is NOT seen third when filter Due New to Old is selected.");		
		
		bi.deleteSeries();

		sa.assertAll();
	}
	
	@Test (priority = 39, enabled = true)
	public void ST39_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating \"Biller Name\" option.");

		OverviewPage op = new OverviewPage();

		op.tapOnBillsAndIncomeCard();

		BillsAndIncomePage bi = new BillsAndIncomePage();
		bi.deleteAlreadyPresentReminderSeries();

		bi.addNewReminderSeries("Bill");

		String reminderName = "Reminder_"+h.getCurrentTime();

		TransactionRecord tRec = new TransactionRecord();
		tRec.setPayee(reminderName);
		tRec.setAmount("20.00");
		tRec.setFromAccount(sManualChecking);

		bi.addNewReminder(tRec);
		
		bi.addNewReminderSeries("Bill");
		
		String reminderName2 = "A_"+h.getCurrentTime();

		TransactionRecord tRec1 = new TransactionRecord();
		tRec1.setPayee(reminderName2);
		tRec1.setAmount("10.00");
		tRec1.setFromAccount(sManualChecking);

		bi.addNewReminder(tRec1);
		
		bi.selectingBillerNameOption();
		
		List<WebElement> li = bi.getAllSectionHeaders();
		
		String firstSectionHeaderText = li.get(0).getText();
		String secondSectionHeaderText = li.get(1).getText();
		
		if(firstSectionHeaderText.equalsIgnoreCase("A")) 
			Commentary.log(LogStatus.INFO, "As Expected, Text of First Section Header is \"A\".");
		else
			Commentary.log(sa, LogStatus.FAIL, "Text of First Section Header is NOT \"A\".");
		
		if(secondSectionHeaderText.equalsIgnoreCase("R")) 
			Commentary.log(LogStatus.INFO, "As Expected, Text of Second Section Header is \"R\".");
		else
			Commentary.log(sa, LogStatus.FAIL, "Text of Second Section Header is NOT \"R\".");

		bi.deleteAlreadyPresentReminderSeries();

		sa.assertAll();
	}

}
