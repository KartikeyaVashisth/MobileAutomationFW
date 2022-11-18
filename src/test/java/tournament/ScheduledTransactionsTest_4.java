package tournament;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

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
import io.appium.java_client.MobileElement;
import referee.Commentary;
import referee.Verify;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;
import support.UserName;

public class ScheduledTransactionsTest_4 extends Recovery {

	String sUserName = "quicken789@gmail.com";
	String sPassword = "Quicken@01";
	String sDataset = "ST Phase 2";
	String sDataset_stage = "ST_4";
	String sManualChecking = "Manual_Checking";
	String sManualSaving = "Manual_Savings";

	public String getUsername_basedOnEnv() throws Exception{

		UserName un = new UserName();
		un.stage_ios = "st_4_ios++@stage.com";
		un.stage_android = "st_4_android++@stage.com";
		un.prod_ios = "quicken789@gmail.com";
		un.prod_android = "quicken789@gmail.com";
		return un.getUserName();	
	}

	@Test (priority = 27, enabled = true)
	public void ST27_Test() throws Exception {

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

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating by Editing the Reminder instance after selecting \"Edit this instance\" option.");

		String sTodaysBalance_With7DaysReminderFilter_BeforeEditingReminderInstance, sProjectedBalance_With7DaysReminderFilter_BeforeEditingReminderInstance;
		String sTodaysBalance_With7DaysReminderFilter_AfterEditingReminderInstance, sProjectedBalance_With7DaysReminderFilter_AfterEditingReminderInstance;

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
		tRec.setDate(h.getPastDaysDate(4));
		tRec.setFrequency("Weekly");

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

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalance_With7DaysReminderFilter_BeforeEditingReminderInstance = bcc.txtTodaysBalanceAmount.getText();

		bcc.scrollToProjectedBalance();
		sProjectedBalance_With7DaysReminderFilter_BeforeEditingReminderInstance = bcc.txtProjectedBalanceAmount.getText();

		Double dTodaysBalance_With7DaysReminderFilter_BeforeEditingReminderInstance = h.processBalanceAmount(sTodaysBalance_With7DaysReminderFilter_BeforeEditingReminderInstance);
		Double dProjectedBalance_With7DaysReminderFilter_BeforeEditingReminderInstance = h.processBalanceAmount(sProjectedBalance_With7DaysReminderFilter_BeforeEditingReminderInstance);

		bcc.backButton.click();
		Thread.sleep(1000);
		bcc.backButton.click();
		Thread.sleep(1000);

		op.tapOnBillsAndIncomeCard();

		bi.selectingDueNewToOldOption();
		
		tp.tapOnTransation(1); //Tapping on Next Reminder Instance.

		bi.tapOnEditThisInstanceOption();

		Verify.waitForObject(bi.editReminderInstanceHeaderText, 1);
		if(Verify.objExists(bi.editReminderInstanceHeaderText))
			Commentary.log(LogStatus.INFO, "PASS: Edit Reminder Instance page is displayed.");
		else 
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Edit Reminder Instance page is NOT displayed.");

		if(Verify.objExists(bi.editReminderInstanceInfoText))
			Commentary.log(LogStatus.INFO, "PASS: Edit Reminder Instance Info text is seen.");
		else 
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Edit Reminder Instance Info text is NOT seen.");

		if(bi.payeeNameOnEditReminderInstancePage.getText().equals(reminderName))
			Commentary.log(LogStatus.INFO, "PASS: Correct Payee name is seen on Edit Reminder Instance page.");
		else 
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Correct Payee name is NOT seen on Edit Reminder Instance page.");

		TransactionRecord tRec1 = new TransactionRecord();

		tRec1.setAmount("10.00");
		tRec1.setDate(h.getFutureDaysDate(1)); //If the date is not set correctly, we might see "Next regularly scheduled occurrence" pop up and TC will fail.

		bi.editReminderInstance(tRec1);

		Verify.waitForObject(bi.backButton, 1);
		bi.backButton.click();
		Thread.sleep(1000);

		op.scrollToTop();

		op.navigateToAcctList();

		bcc.selectAccount(sManualChecking);

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();

		if(Verify.objExists(tp.next7DaysReminderFilter)) {
			tp.next7DaysReminderFilter.click();
			tp.buttonApply.click();
			Thread.sleep(6000);
		}

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalance_With7DaysReminderFilter_AfterEditingReminderInstance = bcc.txtTodaysBalanceAmount.getText();

		bcc.scrollToProjectedBalance();
		sProjectedBalance_With7DaysReminderFilter_AfterEditingReminderInstance = bcc.txtProjectedBalanceAmount.getText();

		Double dTodaysBalance_With7DaysReminderFilter_AfterEditingReminderInstance = h.processBalanceAmount(sTodaysBalance_With7DaysReminderFilter_AfterEditingReminderInstance);
		Double dProjectedBalance_With7DaysReminderFilter_AfterEditingReminderInstance = h.processBalanceAmount(sProjectedBalance_With7DaysReminderFilter_AfterEditingReminderInstance);

		Double d = Double.parseDouble(tRec1.getAmount());
		
		Integer todaysBalanceCompare_With7DaysReminderFilter_AfterEditingReminderInstance = Double.compare(dTodaysBalance_With7DaysReminderFilter_BeforeEditingReminderInstance + d*2d, dTodaysBalance_With7DaysReminderFilter_AfterEditingReminderInstance);
		Integer projectedBalanceCompare_With7DaysReminderFilter_AfterEditingReminderInstance = Double.compare(dProjectedBalance_With7DaysReminderFilter_BeforeEditingReminderInstance + d, dProjectedBalance_With7DaysReminderFilter_AfterEditingReminderInstance);

//		if(dTodaysBalance_With7DaysReminderFilter_BeforeEditingReminderInstance + d*2d == dTodaysBalance_With7DaysReminderFilter_AfterEditingReminderInstance)
		if(todaysBalanceCompare_With7DaysReminderFilter_AfterEditingReminderInstance == 0)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Today's balance after editing Reminder Instance is: "+sTodaysBalance_With7DaysReminderFilter_AfterEditingReminderInstance+".");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Today's balance after editing Reminder Instance is NOT correct.");

//		if(dProjectedBalance_With7DaysReminderFilter_BeforeEditingReminderInstance + d == dProjectedBalance_With7DaysReminderFilter_AfterEditingReminderInstance)
		if(projectedBalanceCompare_With7DaysReminderFilter_AfterEditingReminderInstance == 0)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Projected balance after editing Reminder Instance is: "+sProjectedBalance_With7DaysReminderFilter_AfterEditingReminderInstance+".");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected balance after editing Reminder Instance is NOT correct.");

		bcc.backButton.click();
		Thread.sleep(1000);
		bcc.backButton.click();
		Thread.sleep(1000);

		op.tapOnBillsAndIncomeCard();

		bi.deleteSeries();

		sa.assertAll();
	}

	@Test (priority = 28, enabled = true)
	public void ST28_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating the date related Information pop up message by editing the Reminder instance with date after \"Next regularly scheduled occurrence\".");

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
		tRec.setDate(h.getPastDaysDate(9));
		tRec.setFrequency("Weekly");

		bi.addNewReminder(tRec);
		Verify.waitForObject(bi.thisMonthHeaderText, 1);
		
		TransactionsPage tp = new TransactionsPage();
		tp.tapOnTransation(2);

		bi.tapOnEditThisInstanceOption();

		TransactionRecord tRec1 = new TransactionRecord();

		tRec1.setAmount("10.00");
		tRec1.setDate(h.getFutureDaysDate(0));

		bi.editReminderInstance(tRec1);
		Thread.sleep(2000);

		if(Verify.objExists(bi.nextRegularlyScheduledOccurrenceInfoPopUp))
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Next Regularly scheduled occurrence pop up is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: As Expected, Next Regularly scheduled occurrence pop up is NOT displayed.");

		bi.buttonOK.click();
		Thread.sleep(1000);
		bi.closeEditReminderInstanceButton.click();

		Verify.waitForObject(bi.backButtonOnEnterTransactionPage, 2);
		bi.backButtonOnEnterTransactionPage.click();
		Thread.sleep(1000);

		bi.deleteSeries();

		sa.assertAll();
	}

	@Test (priority = 29, enabled = true)
	public void ST29_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating by Editing the Reminder Series with \"Edit this and future instances\" option and saving it.");

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
		tRec.setFrequency("Weekly");

		bi.addNewReminder(tRec);
		
		bi.selectingDueNewToOldOption();
		Verify.waitForObject(bi.thisMonthHeaderText, 1);

		TransactionsPage tp = new TransactionsPage();
		tp.tapOnFirstTransaction();

		bi.tapOnEditThisAndFutureInstancesOption();

		String reminderName1 = "Reminder_"+h.getCurrentTime();

		TransactionRecord tRec1 = new TransactionRecord();
		tRec1.setPayee(reminderName1);
		tRec1.setAmount("10.00");
		tRec1.setFrequency("Monthly");
		tRec1.setCategory("Entertainment");
		tRec1.setEndAfterNumberOfReminders("5");

		bi.editReminderSeries(tRec1);

		bi.tapOnSeriesTab();

		bi.reminderName.click();
		Verify.waitForObject(bi.reminderSeriesNameOnViewSeries, 1);

		String reminderSeriesNameOnViewSeries = bi.reminderSeriesNameOnViewSeries.getText();
		String amountOnViewSeriesPage = bi.reminderSeriesAmountOnViewSeries.getText();
		String editedReminderSeriesFrequencyOnViewSeries = bi.reminderSeriesFrequencyOnViewSeries.getText();
		String editedSubstringFrequencyValue = editedReminderSeriesFrequencyOnViewSeries.substring(6);
		String typeViewSeriesPage = bi.reminderTypeViewSeriesPage.getText();
		String categoryValueViewSeriesPage = bi.categoryTypeViewSeriesPage.getText();
		String endAfterViewSeriesPage = bi.endAfterViewSeriesPage.getText();
		String substringEndAfterViewSeriesPage = endAfterViewSeriesPage.substring(0, 1);
		String fromAccountViewSeriesPage = bi.fromAccountViewSeriesPage.getText();

		if(reminderSeriesNameOnViewSeries.equals(tRec1.getPayee()))
			Commentary.log(LogStatus.INFO, "PASS: Reminder Name is matched successfully with the Reminder name on View Series Page.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Reminder Name is NOT matched with the Reminder name on View Series Page.");

		if(amountOnViewSeriesPage.equals("$10.00"))
			Commentary.log(LogStatus.INFO, "PASS: 'Amount' field value seen on View Series Page is same as actual the set Amount.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Amount' field value seen on View Series Page is NOT same as the actual set Amount.");

		if(editedSubstringFrequencyValue.equals("Month"))
			Commentary.log(LogStatus.INFO, "PASS: 'Frequency' field value on View Series Page is same as the set Frequency field.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Frequency' field value on View Series Page is NOT same as the set Frequency field.");

		if(typeViewSeriesPage.equals("Bill"))
			Commentary.log(LogStatus.INFO, "PASS: Reminder type is Bill which is expected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Reminder is Not of Bill type.");

		if(substringEndAfterViewSeriesPage.equals(tRec1.getEndAfterNumberOfReminders()))
			Commentary.log(LogStatus.INFO, "PASS: 'End After' field value on View Series Page is same as the entered End After No. of Reminders field.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'End After' field value on View Series Page is not same as the entered End After No. of Reminders field.");

		if(categoryValueViewSeriesPage.equals(tRec1.getCategory()))
			Commentary.log(LogStatus.INFO, "PASS: 'Category' field value on View Series Page is same as the entered Category field.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Category' field value on View Series Page is not same as the entered Category field.");

		if(fromAccountViewSeriesPage.equals(tRec.getFromAccount())) 
			Commentary.log(LogStatus.INFO, "PASS: 'From Account' Name on View Series Page is same as the entered 'From Account' name.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'From Account' Name on View Series Page is not same as the entered 'From Account' name.");

		bi.deleteSeriesFromEditButton();

		sa.assertAll();
	}

	@Test (priority = 30, enabled = true)
	public void ST30_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating by Deleting the Reminder Series after selecting \"Edit this and future instances\" option.");

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
		tRec.setFrequency("Weekly");

		bi.addNewReminder(tRec);
		
		bi.selectingDueNewToOldOption();
		Verify.waitForObject(bi.thisMonthHeaderText, 1);
		
		TransactionsPage tp = new TransactionsPage();
		tp.tapOnFirstTransaction();

		bi.tapOnEditThisAndFutureInstancesOption();

		bi.tapOnDeleteSeries();

		Verify.waitForObject(bi.deleteReminderSeriesAlertButton, 1);
		bi.deleteReminderSeriesAlertButton.click();
		Thread.sleep(2000);

		Commentary.log(LogStatus.INFO, "Successfully deleted the Reminder series.");

		sa.assertAll();
	}

	@Test (priority = 31, enabled = true)
	public void ST31_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating by Deleting the Reminder Series after selecting \"Delete this and future instances\" option.");

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
		
		bi.selectingDueNewToOldOption();
		Verify.waitForObject(bi.thisMonthHeaderText, 1);
		
		TransactionsPage tp = new TransactionsPage();
		tp.tapOnFirstTransaction();

		bi.tapOnDeleteThisAndFutureInstances();

		Verify.waitForObject(bi.deleteThisAndFutureInstancesInfoPopUp, 1);
		if(Verify.objExists(bi.deleteThisAndFutureInstancesInfoPopUp)) 
			Commentary.log(LogStatus.INFO, "PASS: Delete confirmation alert pop up is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Delete confirmation alert pop up is NOT displayed.");

		bi.deleteReminderSeriesAlertButton.click();
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 1);

		Verify.waitForObject(bi.noRemindersDueNext7Days, 1);
		if(Verify.objExists(bi.noRemindersDueNext7Days))
			Commentary.log(LogStatus.INFO, "PASS: Correct message is displayed when there are no Scheduled Reminders for Next 7 Days.");
		else 
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Correct message is NOT displayed when there are no Scheduled Reminders.");

		sa.assertAll();
	}

	@Test (priority = 32, enabled = true)
	public void ST32_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating by adding a New Bill Reminder Series of \"Monthly\" frequency with Day set as \"Last\", verifying last date on the Next 90 Days Reminder filter chips.");

		OverviewPage op = new OverviewPage();
		op.tapOnBillsAndIncomeCard();

		BillsAndIncomePage bi = new BillsAndIncomePage();
		bi.deleteAlreadyPresentReminderSeries();

		bi.addNewReminderSeries("Bill");

		String reminderName = "Reminder_"+h.getCurrentTime();

		TransactionRecord tRec = new TransactionRecord();
		tRec.setPayee(reminderName);
		tRec.setAmount("5.00");
		tRec.setFromAccount(sManualChecking);
		tRec.setDay("Last");

		bi.addNewReminder(tRec);

		bi.tapOnSeriesTab();

		Verify.waitForObject(bi.reminderDate, 1);
		String NextDueOnDate = bi.reminderDate.getText();

		LocalDate currentdate = LocalDate.now();
		Month currentMonth = currentdate.getMonth();

		Month nextMonth = currentMonth.plus(1);
		String nextMonthSubstring = nextMonth.toString().substring(0, 3);

		Month nextToNextMonth = currentMonth.plus(2);
		String nextToNextMonthSubstring = nextToNextMonth.toString().substring(0, 3);

		if(currentMonth.toString().equalsIgnoreCase("January")) {
			if(NextDueOnDate.contains("January 31"))
				Commentary.log(LogStatus.INFO, "31st day of the January Month is selected.");
			else
				Commentary.log(sa, LogStatus.FAIL, "31st day of the January Month is NOT selected.");
		}

		if(currentMonth.toString().equalsIgnoreCase("February")) {
			if(NextDueOnDate.contains("February 28"))
				Commentary.log(LogStatus.INFO, "28th day of the February Month is selected.");
			else
				Commentary.log(sa, LogStatus.FAIL, "28th day of the February Month is NOT selected.");
		}

		if(currentMonth.toString().equalsIgnoreCase("March")) {
			if(NextDueOnDate.contains("March 31"))
				Commentary.log(LogStatus.INFO, "31st day of the March Month is selected.");
			else
				Commentary.log(sa, LogStatus.FAIL, "31st day of the March Month is NOT selected.");
		}

		if(currentMonth.toString().equalsIgnoreCase("April")) {
			if(NextDueOnDate.contains("April 30"))
				Commentary.log(LogStatus.INFO, "30th day of the April Month is selected.");
			else
				Commentary.log(sa, LogStatus.FAIL, "30th day of the April Month is NOT selected.");
		}

		if(currentMonth.toString().equalsIgnoreCase("May")) {
			if(NextDueOnDate.contains("May 31"))
				Commentary.log(LogStatus.INFO, "31st day of the May Month is selected.");
			else
				Commentary.log(sa, LogStatus.FAIL, "31st day of the May Month is NOT selected.");
		}

		if(currentMonth.toString().equalsIgnoreCase("June")) {
			if(NextDueOnDate.contains("June 30"))
				Commentary.log(LogStatus.INFO, "30th day of the June Month is selected.");
			else
				Commentary.log(sa, LogStatus.FAIL, "30th day of the June Month is NOT selected.");
		}

		if(currentMonth.toString().equalsIgnoreCase("July")) {
			if(NextDueOnDate.contains("July 31"))
				Commentary.log(LogStatus.INFO, "31st day of the July Month is selected.");
			else
				Commentary.log(sa, LogStatus.FAIL, "31st day of the July Month is NOT selected.");
		}

		if(currentMonth.toString().equalsIgnoreCase("August")) {
			if(NextDueOnDate.contains("August 31"))
				Commentary.log(LogStatus.INFO, "31st day of the August Month is selected.");
			else
				Commentary.log(sa, LogStatus.FAIL, "31st day of the August Month is NOT selected.");
		}

		if(currentMonth.toString().equalsIgnoreCase("September")) {
			if(NextDueOnDate.contains("September 30"))
				Commentary.log(LogStatus.INFO, "30th day of the September Month is selected.");
			else
				Commentary.log(sa, LogStatus.FAIL, "30th day of the September Month is NOT selected.");
		}

		if(currentMonth.toString().equalsIgnoreCase("October")) {
			if(NextDueOnDate.contains("October 31"))
				Commentary.log(LogStatus.INFO, "31st day of the October Month is selected.");
			else
				Commentary.log(sa, LogStatus.FAIL, "31st day of the October Month is NOT selected.");
		}

		if(currentMonth.toString().equalsIgnoreCase("November")) {
			if(NextDueOnDate.contains("November 30"))
				Commentary.log(LogStatus.INFO, "30th day of the November Month is selected.");
			else
				Commentary.log(sa, LogStatus.FAIL, "30th day of the November Month is NOT selected.");
		}

		if(currentMonth.toString().equalsIgnoreCase("December")) {
			if(NextDueOnDate.contains("December 31"))
				Commentary.log(LogStatus.INFO, "31st day of the December Month is selected.");
			else
				Commentary.log(sa, LogStatus.FAIL, "31st day of the December Month is NOT selected.");
		}

		bi.remindersTab.click();
		Thread.sleep(1000);

		bi.scrolltoNext90DaysFilter();
		Thread.sleep(1000);

		bi.next90Days.click();
		Thread.sleep(2000);

		List<MobileElement> allReminderEntriesDate = bi.getAllReminderEntriesDate();

		if(allReminderEntriesDate.get(0).getText().toUpperCase().contains(nextToNextMonthSubstring))
			bi.validateLastDayOfMonth(nextToNextMonthSubstring, 0);

		if(allReminderEntriesDate.get(1).getText().toUpperCase().contains(nextMonthSubstring))
			bi.validateLastDayOfMonth(nextMonthSubstring, 1);

		bi.deleteSeries();

		sa.assertAll();
	}

}
