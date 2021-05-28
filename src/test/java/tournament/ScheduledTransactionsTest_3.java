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
import dugout.SignInPage;
import dugout.TransactionsPage;
import io.appium.java_client.MobileElement;
import referee.Commentary;
import referee.Verify;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;

public class ScheduledTransactionsTest_3 extends Recovery {

	String sUserName = "varsha.h@quicken.com";
	String sPassword = "Intuit!1";
	String sDataset = "ST Phase 2";
	String sManualChecking = "Manual_Checking";
	String sManualSaving = "Manual_Savings";

	@Test (priority = 21, enabled = true)
	public void ST21_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		SignInPage si = new SignInPage();
		si.signIn(sUserName, sPassword, sDataset);
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Editing the Bill reminder series and verifying that correct number of instances are updated with the change of frequency.");

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
		tRec.setCategory("Entertainment");
		tRec.setEndAfterNumberOfReminders("5");

		bi.addNewReminder(tRec);
		
		Verify.waitForObject(bi.thisMonthHeaderText, 1);
		int numOfTransactionNext7Days = bi.getTransactionListSize();

		if (numOfTransactionNext7Days==1) {		
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Only 1 transaction is seen on the Next 7 days filter chip.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: "+numOfTransactionNext7Days+" transaction is seen on the Next 7 days filter chip.");
		}

		bi.next14Days.click();
		Thread.sleep(1000);
		int numOfTransactionNext14Days = bi.getTransactionListSize();

		if (numOfTransactionNext14Days==2) {		
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Only 2 transactions are seen on the Next 14 days filter chip.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: "+numOfTransactionNext14Days+" transaction is seen on the Next 14 days filter chip.");
		}

		bi.next30Days.click();
		Thread.sleep(1000);
		int numOfTransactionNext30Days = bi.getTransactionListSize();

		if (numOfTransactionNext30Days==5) {		
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Only 5 transactions are seen on the Next 30 days filter chip.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: "+numOfTransactionNext30Days+" transaction is seen on the Next 30 days filter chip.");
		}

		bi.scrolltoNext90DaysFilter();

		bi.next90Days.click();
		Thread.sleep(1000);
		int numOfTransactionNext90Days = bi.getTransactionListSize();

		if (numOfTransactionNext90Days==5) {		
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Only 5 transactions are seen on the Next 90 days filter chip.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: "+numOfTransactionNext90Days+" transaction is seen on the Next 90 days filter chip.");
		}

		bi.next12Months.click();
		Thread.sleep(1000);
		int numOfTransactionNext12Months = bi.getTransactionListSize();

		if (numOfTransactionNext12Months==5) {		
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Only 5 transactions are seen on the Next 12 Months filter chip.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: "+numOfTransactionNext12Months+" transaction is seen on the Next 12 Months filter chip.");
		}

		bi.tapOnSeriesTab();

		Verify.waitForObject(bi.reminderName, 1);
		bi.reminderName.click();
		Verify.waitForObject(bi.reminderSeriesNameOnViewSeries, 1);

		String reminderSeriesFrequencyOnViewSeries = bi.reminderSeriesFrequencyOnViewSeries.getText();
		String substringFrequencyValue = reminderSeriesFrequencyOnViewSeries.substring(6);

		if(substringFrequencyValue.equals("Week"))
			Commentary.log(LogStatus.INFO, "'Frequency' value on View Series Page is same as the chosen Frequency field.");

		bi.editButton.click();

		Verify.waitForObject(bi.editSeriesHeaderText, 1);
		TransactionRecord tRec1 = new TransactionRecord();
		tRec1.setFrequency("Monthly");
		tRec1.setEndAfterNumberOfReminders("6");

		bi.editReminderSeries(tRec1);

		Verify.waitForObject(bi.reminderSeriesFrequencyOnViewSeries, 2);
		String editedReminderSeriesFrequencyOnViewSeries = bi.reminderSeriesFrequencyOnViewSeries.getText();
		String editedSubstringFrequencyValue = editedReminderSeriesFrequencyOnViewSeries.substring(6);

		if(editedSubstringFrequencyValue.equals("Month"))
			Commentary.log(LogStatus.INFO, "'Frequency' value on View Series Page is same as the edited Frequency field.");

		bi.backButtonOfEditSeries.click();

		Verify.waitForObject(bi.remindersTab, 1);
		bi.remindersTab.click();

		Verify.waitForObject(bi.thisMonthHeaderText, 1);
		int numOfTransactionNext7Days_MonthlyFrequency = bi.getTransactionListSize();

		if (numOfTransactionNext7Days_MonthlyFrequency==1) {		
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Only 1 transaction is seen on the Next 7 days filter chip.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: "+numOfTransactionNext7Days_MonthlyFrequency+" transaction is seen on the Next 7 days filter chip.");
		}

		bi.next14Days.click();
		Thread.sleep(1000);
		int numOfTransactionNext14Days_MonthlyFrequency = bi.getTransactionListSize();

		if (numOfTransactionNext14Days_MonthlyFrequency==1) {		
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Only 1 transaction is seen on the Next 14 days filter chip.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: "+numOfTransactionNext14Days_MonthlyFrequency+" transaction is seen on the Next 14 days filter chip.");
		}

		bi.next30Days.click();
		Thread.sleep(1000);
		int numOfTransactionNext30Days_MonthlyFrequency = bi.getTransactionListSize();

		if (numOfTransactionNext30Days_MonthlyFrequency==1) {		
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Only 1 transaction is seen on the Next 30 days filter chip.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: "+numOfTransactionNext30Days_MonthlyFrequency+" transaction is seen on the Next 30 days filter chip.");
		}

		bi.scrolltoNext90DaysFilter();

		bi.next90Days.click();
		Thread.sleep(1000);
		int numOfTransactionNext90Days_MonthlyFrequency = bi.getTransactionListSize();

		if (numOfTransactionNext90Days_MonthlyFrequency==3) {		
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Only 3 transactions are seen on the Next 90 days filter chip.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: "+numOfTransactionNext90Days_MonthlyFrequency+" transaction is seen on the Next 90 days filter chip.");
		}

		bi.next12Months.click();
		Thread.sleep(1000);
		int numOfTransactionNext12Months_MonthlyFrequency = bi.getTransactionListSize();

		if (numOfTransactionNext12Months_MonthlyFrequency==6) {		
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Only 6 transactions are seen on the Next 12 Months filter chip.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: "+numOfTransactionNext12Months_MonthlyFrequency+" transaction is seen on the Next 12 Months filter chip.");
		}

		bi.deleteSeries();

		sa.assertAll();
	}

	@Test (priority = 22, enabled = true)
	public void ST22_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Entering the Bill reminder instance and Verifying the changes in Today's and Projected Balances of Accounts afterwards.");

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

		op.navigateToAcctList();

		bcc.selectAccount(sManualChecking);

		TransactionsPage tp = new TransactionsPage();

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();

		if(Verify.objExists(tp.dontShowReminderFilter)) {
			tp.dontShowReminderFilter.click();
			tp.buttonApply.click();
			Thread.sleep(4000);
		}

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalance_WithReminderFilterOFF = bcc.txtTodaysBalanceAmount.getText();

		bcc.scrollToProjectedBalance();
		sProjectedBalance_WithReminderFilterOFF = bcc.txtProjectedBalanceAmount.getText();

		Double dTodaysBalance_WithReminderFilterOFF = h.processBalanceAmount(sTodaysBalance_WithReminderFilterOFF);
		Double dProjectedBalance_WithReminderFilterOFF = h.processBalanceAmount(sProjectedBalance_WithReminderFilterOFF);

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		Verify.waitForObject(tp.next7DaysReminderFilter, 1);
		tp.next7DaysReminderFilter.click();
		tp.buttonApply.click();
		Thread.sleep(4000);

		sProjectedBalanceWith7DaysReminderFilter = bcc.txtProjectedBalanceAmount.getText();

		bcc.scrollToTotalBalance();

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalanceWith7DaysReminderFilter = bcc.txtTodaysBalanceAmount.getText();

		Double dTodaysBalance_With7DaysReminderFilter = h.processBalanceAmount(sTodaysBalanceWith7DaysReminderFilter);
		Double dProjectedBalance_With7DaysReminderFilter = h.processBalanceAmount(sProjectedBalanceWith7DaysReminderFilter);

		bcc.backButton.click();
		Thread.sleep(1000);
		bcc.backButton.click();
		Thread.sleep(1000);

		op.tapOnBillsAndIncomeCard();

		tp.tapOnFirstTransaction();

		bi.tapOnEnterOption();

		Verify.waitForObject(bi.noRemindersDueNext7Days, 2);
		if(Verify.objExists(bi.noRemindersDueNext7Days))
			Commentary.log(LogStatus.INFO, "PASS: Correct message is displayed when there are no Scheduled Reminders for Next 7 Days.");
		else 
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Correct message is NOT displayed when there are no Scheduled Reminders.");

		Verify.waitForObject(bi.backButton, 1);
		bi.backButton.click();
		Thread.sleep(1000);

		op.scrollToTop();

		op.navigateToAcctList();

		bcc.selectAccount(sManualChecking);

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();

		if(Verify.objExists(tp.dontShowReminderFilter)) {
			tp.dontShowReminderFilter.click();
			tp.buttonApply.click();
			Thread.sleep(4000);
		}

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalance_WithReminderFilterOFF_AfterEntering = bcc.txtTodaysBalanceAmount.getText();

		bcc.scrollToProjectedBalance();
		sProjectedBalance_WithReminderFilterOFF_AfterEntering = bcc.txtProjectedBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance after Entering the Reminder with Show Reminder filter Off: ["+sTodaysBalance_WithReminderFilterOFF_AfterEntering+"]");
		Commentary.log(LogStatus.INFO, "Projected balance after Entering the Reminder with Show Reminder filter Off: ["+sProjectedBalance_WithReminderFilterOFF_AfterEntering+"]");

		Double dTodaysBalance_WithReminderFilterOFF_AfterEntering = h.processBalanceAmount(sTodaysBalance_WithReminderFilterOFF_AfterEntering);
		Double dProjectedBalance_WithReminderFilterOFF_AfterEntering = h.processBalanceAmount(sProjectedBalance_WithReminderFilterOFF_AfterEntering);

		Double d = Double.parseDouble(tRec.getAmount());

		if(dTodaysBalance_WithReminderFilterOFF-d==dTodaysBalance_WithReminderFilterOFF_AfterEntering)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Today's balance with Reminder filter OFF: ["+sTodaysBalance_WithReminderFilterOFF_AfterEntering+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Today's balance with Reminder filter OFF is NOT correct.");

		if(dProjectedBalance_WithReminderFilterOFF-d==dProjectedBalance_WithReminderFilterOFF_AfterEntering)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Projected balance with Reminder filter OFF is: ["+sProjectedBalance_WithReminderFilterOFF_AfterEntering+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected balance with Reminder filter OFF is NOT correct.");

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		Verify.waitForObject(tp.next7DaysReminderFilter, 1);
		tp.next7DaysReminderFilter.click();
		tp.buttonApply.click();
		Thread.sleep(4000);

		sProjectedBalanceWith7DaysReminderFilter_AfterEntering = bcc.txtProjectedBalanceAmount.getText();

		bcc.scrollToTotalBalance();

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalanceWith7DaysReminderFilter_AfterEntering = bcc.txtTodaysBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance after Entering the reminder with Next 7 Days filter: ["+sTodaysBalanceWith7DaysReminderFilter_AfterEntering+"]");
		Commentary.log(LogStatus.INFO, "Projected balance after Entering the reminder with Next 7 Days filter: ["+sProjectedBalanceWith7DaysReminderFilter_AfterEntering+"]");

		Double dTodaysBalance_With7DaysReminderFilter_AfterEntering = h.processBalanceAmount(sTodaysBalanceWith7DaysReminderFilter_AfterEntering);
		Double dProjectedBalance_With7DaysReminderFilter_AfterEntering = h.processBalanceAmount(sProjectedBalanceWith7DaysReminderFilter_AfterEntering);

		Integer todaysBalanceCompare_With7DaysReminderFilter_AfterEntering = Double.compare(dTodaysBalance_With7DaysReminderFilter, dTodaysBalance_With7DaysReminderFilter_AfterEntering);
		Integer projectedBalanceCompare_7DaysReminderFilter_AfterEntering = Double.compare(dProjectedBalance_With7DaysReminderFilter, dProjectedBalance_With7DaysReminderFilter_AfterEntering);

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

	@Test (priority = 23, enabled = true)
	public void ST23_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Entering the Bill reminder instance beyond 7 days and verifying the changes in Today's and Projected Balances of Accounts afterwards.");

		String sTodaysBalance_WithReminderFilterOFF, sProjectedBalance_WithReminderFilterOFF;
		String sTodaysBalanceWith7DaysReminderFilter, sProjectedBalanceWith7DaysReminderFilter, sTodaysBalance_With14DaysReminderFilter, sProjectedBalance_With14DaysReminderFilter;
		String sTodaysBalance_WithReminderFilterOFF_AfterEntering, sProjectedBalance_WithReminderFilterOFF_AfterEntering;
		String sTodaysBalanceWith7DaysReminderFilter_AfterEntering, sProjectedBalanceWith7DaysReminderFilter_AfterEntering, sTodaysBalanceWith14DaysReminderFilter_AfterEntering, sProjectedBalanceWith14DaysReminderFilter_AfterEntering;

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
		tRec.setDate(h.getFutureDaysDate(9));
		tRec.setFrequency("Weekly");

		bi.addNewReminder(tRec);

		Verify.waitForObject(bi.backButton, 1);
		bi.backButton.click();
		Thread.sleep(1000);

		op.scrollToTop();

		op.navigateToAcctList();

		bcc.selectAccount(sManualChecking);

		TransactionsPage tp = new TransactionsPage();

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();

		if(Verify.objExists(tp.dontShowReminderFilter)) {
			tp.dontShowReminderFilter.click();
			tp.buttonApply.click();
			Thread.sleep(4000);
		}

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalance_WithReminderFilterOFF = bcc.txtTodaysBalanceAmount.getText();

		bcc.scrollToProjectedBalance();
		sProjectedBalance_WithReminderFilterOFF = bcc.txtProjectedBalanceAmount.getText();

		Double dTodaysBalance_WithReminderFilterOFF = h.processBalanceAmount(sTodaysBalance_WithReminderFilterOFF);
		Double dProjectedBalance_WithReminderFilterOFF = h.processBalanceAmount(sProjectedBalance_WithReminderFilterOFF);

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		Verify.waitForObject(tp.next7DaysReminderFilter, 1);
		tp.next7DaysReminderFilter.click();
		tp.buttonApply.click();
		Thread.sleep(4000);

		sProjectedBalanceWith7DaysReminderFilter = bcc.txtProjectedBalanceAmount.getText();

		bcc.scrollToTotalBalance();

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalanceWith7DaysReminderFilter = bcc.txtTodaysBalanceAmount.getText();

		Double dTodaysBalance_With7DaysReminderFilter = h.processBalanceAmount(sTodaysBalanceWith7DaysReminderFilter);
		Double dProjectedBalance_With7DaysReminderFilter = h.processBalanceAmount(sProjectedBalanceWith7DaysReminderFilter);

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		Verify.waitForObject(tp.next14DaysReminderFilter, 1);
		tp.next14DaysReminderFilter.click();
		tp.buttonApply.click();
		Thread.sleep(4000);

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalance_With14DaysReminderFilter= bcc.txtTodaysBalanceAmount.getText();

		bcc.scrollToProjectedBalance();
		sProjectedBalance_With14DaysReminderFilter = bcc.txtProjectedBalanceAmount.getText();

		Double dTodaysBalance_With14DaysReminderFilter = h.processBalanceAmount(sTodaysBalance_With14DaysReminderFilter);
		Double dProjectedBalance_With14DaysReminderFilter = h.processBalanceAmount(sProjectedBalance_With14DaysReminderFilter);

		bcc.backButton.click();
		Thread.sleep(1000);
		bcc.backButton.click();
		Thread.sleep(1000);

		op.tapOnBillsAndIncomeCard();

		Verify.waitForObject(bi.noRemindersDueNext7Days, 2);
		if(Verify.objExists(bi.noRemindersDueNext7Days))
			Commentary.log(LogStatus.INFO, "PASS: Correct message is displayed when there are no Scheduled Reminders for Next 7 Days.");
		else 
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Correct message is NOT displayed when there are no Scheduled Reminders.");

		bi.next14Days.click();
		Thread.sleep(1000);

		tp.tapOnFirstTransaction();

		bi.tapOnEnterOption();

		Verify.waitForObject(bi.backButton, 1);
		bi.backButton.click();
		Thread.sleep(1000);

		op.scrollToTop();

		op.navigateToAcctList();

		bcc.selectAccount(sManualChecking);

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();

		if(Verify.objExists(tp.dontShowReminderFilter)) {
			tp.dontShowReminderFilter.click();
			tp.buttonApply.click();
			Thread.sleep(4000);
		}

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalance_WithReminderFilterOFF_AfterEntering = bcc.txtTodaysBalanceAmount.getText();

		bcc.scrollToProjectedBalance();
		sProjectedBalance_WithReminderFilterOFF_AfterEntering = bcc.txtProjectedBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance after Entering the Reminder with Show Reminder filter Off: ["+sTodaysBalance_WithReminderFilterOFF_AfterEntering+"]");
		Commentary.log(LogStatus.INFO, "Projected balance after Entering the Reminder with Show Reminder filter Off: ["+sProjectedBalance_WithReminderFilterOFF_AfterEntering+"]");

		Double dTodaysBalance_WithReminderFilterOFF_AfterEntering = h.processBalanceAmount(sTodaysBalance_WithReminderFilterOFF_AfterEntering);
		Double dProjectedBalance_WithReminderFilterOFF_AfterEntering = h.processBalanceAmount(sProjectedBalance_WithReminderFilterOFF_AfterEntering);

		Integer todaysBalanceCompare_WithReminderFilterOFF_AfterEntering = Double.compare(dTodaysBalance_WithReminderFilterOFF, dTodaysBalance_WithReminderFilterOFF_AfterEntering);

		Double d = Double.parseDouble(tRec.getAmount());

		if(todaysBalanceCompare_WithReminderFilterOFF_AfterEntering==0)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Today's balance with Reminder filter OFF after entering is: ["+sTodaysBalance_WithReminderFilterOFF_AfterEntering+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Today's balance with Reminder filter OFF after entering is NOT correct.");

		if(dProjectedBalance_WithReminderFilterOFF-d==dProjectedBalance_WithReminderFilterOFF_AfterEntering)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Projected balance with Reminder filter OFF after entering is: ["+sProjectedBalance_WithReminderFilterOFF_AfterEntering+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected balance with Reminder filter OFF after entering is NOT correct.");

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		Verify.waitForObject(tp.next7DaysReminderFilter, 1);
		tp.next7DaysReminderFilter.click();
		tp.buttonApply.click();
		Thread.sleep(4000);

		sProjectedBalanceWith7DaysReminderFilter_AfterEntering = bcc.txtProjectedBalanceAmount.getText();

		bcc.scrollToTotalBalance();

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalanceWith7DaysReminderFilter_AfterEntering = bcc.txtTodaysBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance after Entering the reminder with Next 7 Days filter : ["+sTodaysBalanceWith7DaysReminderFilter_AfterEntering+"]");
		Commentary.log(LogStatus.INFO, "Projected balance after Entering the reminder with Next 7 Days filter : ["+sProjectedBalanceWith7DaysReminderFilter_AfterEntering+"]");

		Double dTodaysBalance_With7DaysReminderFilter_AfterEntering = h.processBalanceAmount(sTodaysBalanceWith7DaysReminderFilter_AfterEntering);
		Double dProjectedBalance_With7DaysReminderFilter_AfterEntering = h.processBalanceAmount(sProjectedBalanceWith7DaysReminderFilter_AfterEntering);

		Integer todaysBalanceCompare_With7DaysReminderFilter_AfterEntering = Double.compare(dTodaysBalance_With7DaysReminderFilter, dTodaysBalance_With7DaysReminderFilter_AfterEntering);

		if(todaysBalanceCompare_With7DaysReminderFilter_AfterEntering==0)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Today's balance after entering the reminder with Next 7 Days filter is: "+sTodaysBalanceWith7DaysReminderFilter_AfterEntering+".");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Today's balance after entering the reminder with Next 7 Days filter is NOT correct.");

		if(dProjectedBalance_With7DaysReminderFilter-d==dProjectedBalance_With7DaysReminderFilter_AfterEntering)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Projected balance after entering the reminder with Next 7 Days filter is: "+sProjectedBalanceWith7DaysReminderFilter_AfterEntering+".");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected balance after entering the reminder with Next 7 Days filter is NOT correct.");

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		Verify.waitForObject(tp.next14DaysReminderFilter, 1);
		tp.next14DaysReminderFilter.click();
		tp.buttonApply.click();
		Thread.sleep(4000);

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalanceWith14DaysReminderFilter_AfterEntering = bcc.txtTodaysBalanceAmount.getText();

		bcc.scrollToProjectedBalance();

		Verify.waitForObject(bcc.txtProjectedBalanceAmount, 1);
		sProjectedBalanceWith14DaysReminderFilter_AfterEntering = bcc.txtProjectedBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance after Entering the reminder with Next 14 Days filter: ["+sTodaysBalanceWith14DaysReminderFilter_AfterEntering+"]");
		Commentary.log(LogStatus.INFO, "Projected balance after Entering the reminder with Next 14 Days filter: ["+sProjectedBalanceWith14DaysReminderFilter_AfterEntering+"]");

		Double dTodaysBalance_With14DaysReminderFilter_AfterEntering = h.processBalanceAmount(sTodaysBalanceWith14DaysReminderFilter_AfterEntering);
		Double dProjectedBalance_With14DaysReminderFilter_AfterEntering = h.processBalanceAmount(sProjectedBalanceWith14DaysReminderFilter_AfterEntering);

		Integer todaysBalanceCompare_With14DaysReminderFilter_AfterEntering = Double.compare(dTodaysBalance_With14DaysReminderFilter, dTodaysBalance_With14DaysReminderFilter_AfterEntering);
		Integer projectedBalanceCompare_14DaysReminderFilter_AfterEntering = Double.compare(dProjectedBalance_With14DaysReminderFilter, dProjectedBalance_With14DaysReminderFilter_AfterEntering);

		if(todaysBalanceCompare_With14DaysReminderFilter_AfterEntering==0)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Today's balance after entering the reminder with Next 14 Days filter is: ["+sTodaysBalanceWith14DaysReminderFilter_AfterEntering+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Today's balance after entering the reminder with Next 14 Days filter is NOT correct.");

		if(projectedBalanceCompare_14DaysReminderFilter_AfterEntering==0)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Projected balance after entering the reminder with Next 14 Days filter is: ["+sProjectedBalanceWith14DaysReminderFilter_AfterEntering+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected balance after entering the reminder with Next 14 Days filter is NOT correct.");

		bcc.backButton.click();
		Thread.sleep(1000);
		bcc.backButton.click();
		Thread.sleep(1000);

		op.tapOnBillsAndIncomeCard();

		bi.deleteSeries();

		sa.assertAll();
	}

	@Test (priority = 24, enabled = true)
	public void ST24_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating \"Enter all previous instances\" option and Verifying the changes in Today's and Projected Balances of Accounts afterwards.");

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

		op.navigateToAcctList();

		bcc.selectAccount(sManualChecking);

		TransactionsPage tp = new TransactionsPage();

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();

		if(Verify.objExists(tp.dontShowReminderFilter)) {
			tp.dontShowReminderFilter.click();
			tp.buttonApply.click();
			Thread.sleep(4000);
		}

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalance_WithReminderFilterOFF = bcc.txtTodaysBalanceAmount.getText();

		bcc.scrollToProjectedBalance();
		sProjectedBalance_WithReminderFilterOFF = bcc.txtProjectedBalanceAmount.getText();

		Double dTodaysBalance_WithReminderFilterOFF = h.processBalanceAmount(sTodaysBalance_WithReminderFilterOFF);
		Double dProjectedBalance_WithReminderFilterOFF = h.processBalanceAmount(sProjectedBalance_WithReminderFilterOFF);

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		Verify.waitForObject(tp.next7DaysReminderFilter, 1);
		tp.next7DaysReminderFilter.click();
		tp.buttonApply.click();
		Thread.sleep(4000);

		sProjectedBalanceWith7DaysReminderFilter = bcc.txtProjectedBalanceAmount.getText();

		bcc.scrollToTotalBalance();

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalanceWith7DaysReminderFilter = bcc.txtTodaysBalanceAmount.getText();

		Double dTodaysBalance_With7DaysReminderFilter = h.processBalanceAmount(sTodaysBalanceWith7DaysReminderFilter);
		Double dProjectedBalance_With7DaysReminderFilter = h.processBalanceAmount(sProjectedBalanceWith7DaysReminderFilter);

		bcc.backButton.click();
		Thread.sleep(1000);
		bcc.backButton.click();
		Thread.sleep(1000);

		op.tapOnBillsAndIncomeCard();

		tp.tapOnFirstTransaction();

		bi.tapOnEnterAllPreviousInstancesOption();

		Verify.waitForObject(bi.noRemindersDueNext7Days, 2);
		if(Verify.objExists(bi.noRemindersDueNext7Days))
			Commentary.log(LogStatus.INFO, "PASS: Correct message is displayed when there are no Scheduled Reminders for Next 7 Days.");
		else 
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Correct message is NOT displayed when there are no Scheduled Reminders.");

		Verify.waitForObject(bi.backButton, 1);
		bi.backButton.click();
		Thread.sleep(1000);

		op.scrollToTop();

		op.navigateToAcctList();

		bcc.selectAccount(sManualChecking);

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();

		if(Verify.objExists(tp.dontShowReminderFilter)) {
			tp.dontShowReminderFilter.click();
			tp.buttonApply.click();
			Thread.sleep(5000);
		}

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalance_WithReminderFilterOFF_AfterEntering = bcc.txtTodaysBalanceAmount.getText();

		bcc.scrollToProjectedBalance();
		sProjectedBalance_WithReminderFilterOFF_AfterEntering = bcc.txtProjectedBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance after Entering the Reminders with Show Reminder filter Off: ["+sTodaysBalance_WithReminderFilterOFF_AfterEntering+"]");
		Commentary.log(LogStatus.INFO, "Projected balance after Entering the Reminders with Show Reminder filter Off: ["+sProjectedBalance_WithReminderFilterOFF_AfterEntering+"]");

		Double dTodaysBalance_WithReminderFilterOFF_AfterEntering = h.processBalanceAmount(sTodaysBalance_WithReminderFilterOFF_AfterEntering);
		Double dProjectedBalance_WithReminderFilterOFF_AfterEntering = h.processBalanceAmount(sProjectedBalance_WithReminderFilterOFF_AfterEntering);

		Double d = Double.parseDouble(tRec.getAmount());

		if(dTodaysBalance_WithReminderFilterOFF-d*3d==dTodaysBalance_WithReminderFilterOFF_AfterEntering)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Today's balance after entering with Reminders filter OFF is: ["+sTodaysBalance_WithReminderFilterOFF_AfterEntering+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Today's balance after entering with Reminders filter OFF is NOT correct.");

		if(dProjectedBalance_WithReminderFilterOFF-d*3d==dProjectedBalance_WithReminderFilterOFF_AfterEntering)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Projected balance after entering with Reminders filter OFF is: ["+sProjectedBalance_WithReminderFilterOFF_AfterEntering+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected balance after entering with Reminders filter OFF is NOT correct.");

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		Verify.waitForObject(tp.next7DaysReminderFilter, 1);
		tp.next7DaysReminderFilter.click();
		tp.buttonApply.click();
		Thread.sleep(4000);

		sProjectedBalanceWith7DaysReminderFilter_AfterEntering = bcc.txtProjectedBalanceAmount.getText();

		bcc.scrollToTotalBalance();

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalanceWith7DaysReminderFilter_AfterEntering = bcc.txtTodaysBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance after Entering the reminders with Next 7 Days filter: ["+sTodaysBalanceWith7DaysReminderFilter_AfterEntering+"]");
		Commentary.log(LogStatus.INFO, "Projected balance after Entering the reminders with Next 7 Days filter: ["+sProjectedBalanceWith7DaysReminderFilter_AfterEntering+"]");

		Double dTodaysBalance_With7DaysReminderFilter_AfterEntering = h.processBalanceAmount(sTodaysBalanceWith7DaysReminderFilter_AfterEntering);
		Double dProjectedBalance_With7DaysReminderFilter_AfterEntering = h.processBalanceAmount(sProjectedBalanceWith7DaysReminderFilter_AfterEntering);

		Integer todaysBalanceCompare_With7DaysReminderFilter_AfterEntering = Double.compare(dTodaysBalance_With7DaysReminderFilter, dTodaysBalance_With7DaysReminderFilter_AfterEntering);
		Integer projectedBalanceCompare_7DaysReminderFilter_AfterEntering = Double.compare(dProjectedBalance_With7DaysReminderFilter, dProjectedBalance_With7DaysReminderFilter_AfterEntering);

		if(todaysBalanceCompare_With7DaysReminderFilter_AfterEntering==0)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Today's balance after entering the reminders with Next 7 Days filter is: ["+sTodaysBalanceWith7DaysReminderFilter_AfterEntering+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Today's balance after entering the reminders with Next 7 Days filter is NOT correct.");

		if(projectedBalanceCompare_7DaysReminderFilter_AfterEntering==0)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Projected balance after entering the reminders with Next 7 Days filter is: ["+sProjectedBalanceWith7DaysReminderFilter_AfterEntering+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected balance after entering the reminders with Next 7 Days filter is NOT correct.");

		bcc.backButton.click();
		Thread.sleep(1000);
		bcc.backButton.click();
		Thread.sleep(1000);

		op.tapOnBillsAndIncomeCard();

		bi.deleteSeries();

		sa.assertAll();
	}

	@Test (priority = 25, enabled = true)
	public void ST25_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Ignoring the Bill reminder instance and Verifying the changes in Today's and Projected Balances of Accounts afterwards.");

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

		op.navigateToAcctList();

		bcc.selectAccount(sManualChecking);

		TransactionsPage tp = new TransactionsPage();

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();

		if(Verify.objExists(tp.dontShowReminderFilter)) {
			tp.dontShowReminderFilter.click();
			tp.buttonApply.click();
			Thread.sleep(4000);
		}

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalance_WithReminderFilterOFF = bcc.txtTodaysBalanceAmount.getText();

		bcc.scrollToProjectedBalance();
		sProjectedBalance_WithReminderFilterOFF = bcc.txtProjectedBalanceAmount.getText();

		Double dTodaysBalance_WithReminderFilterOFF = h.processBalanceAmount(sTodaysBalance_WithReminderFilterOFF);
		Double dProjectedBalance_WithReminderFilterOFF = h.processBalanceAmount(sProjectedBalance_WithReminderFilterOFF);

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		Verify.waitForObject(tp.next7DaysReminderFilter, 1);
		tp.next7DaysReminderFilter.click();
		tp.buttonApply.click();
		Thread.sleep(4000);

		sProjectedBalanceWith7DaysReminderFilter = bcc.txtProjectedBalanceAmount.getText();

		bcc.scrollToTotalBalance();

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalanceWith7DaysReminderFilter = bcc.txtTodaysBalanceAmount.getText();

		Double dTodaysBalance_With7DaysReminderFilter = h.processBalanceAmount(sTodaysBalanceWith7DaysReminderFilter);
		Double dProjectedBalance_With7DaysReminderFilter = h.processBalanceAmount(sProjectedBalanceWith7DaysReminderFilter);

		bcc.backButton.click();
		Thread.sleep(1000);
		bcc.backButton.click();
		Thread.sleep(1000);

		op.tapOnBillsAndIncomeCard();

		tp.tapOnFirstTransaction();

		bi.tapOnIgnoreThisInstanceOption();

		Verify.waitForObject(bi.noRemindersDueNext7Days, 2);
		if(Verify.objExists(bi.noRemindersDueNext7Days))
			Commentary.log(LogStatus.INFO, "PASS: Correct message is displayed when there are no Scheduled Reminders for Next 7 Days.");
		else 
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Correct message is NOT displayed when there are no Scheduled Reminders.");

		Verify.waitForObject(bi.backButton, 1);
		bi.backButton.click();
		Thread.sleep(1000);

		op.scrollToTop();

		op.navigateToAcctList();

		bcc.selectAccount(sManualChecking);

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();

		if(Verify.objExists(tp.dontShowReminderFilter)) {
			tp.dontShowReminderFilter.click();
			tp.buttonApply.click();
			Thread.sleep(5000);
		}

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalance_WithReminderFilterOFF_AfterIgnoring = bcc.txtTodaysBalanceAmount.getText();

		bcc.scrollToProjectedBalance();
		sProjectedBalance_WithReminderFilterOFF_AfterIgnoring = bcc.txtProjectedBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance after Ignoring the Reminder with Show Reminder filter Off: ["+sTodaysBalance_WithReminderFilterOFF_AfterIgnoring+"]");
		Commentary.log(LogStatus.INFO, "Projected balance after Ignoring the Reminder with Show Reminder filter Off: ["+sProjectedBalance_WithReminderFilterOFF_AfterIgnoring+"]");

		Double dTodaysBalance_WithReminderFilterOFF_AfterIgnoring = h.processBalanceAmount(sTodaysBalance_WithReminderFilterOFF_AfterIgnoring);
		Double dProjectedBalance_WithReminderFilterOFF_AfterIgnoring = h.processBalanceAmount(sProjectedBalance_WithReminderFilterOFF_AfterIgnoring);

		Integer todaysBalanceCompare_WithReminderFilterOFF_AfterIgnoring = Double.compare(dTodaysBalance_WithReminderFilterOFF, dTodaysBalance_WithReminderFilterOFF_AfterIgnoring);
		Integer projectedBalanceCompare_WithReminderFilterOFF_AfterIgnoring = Double.compare(dProjectedBalance_WithReminderFilterOFF, dProjectedBalance_WithReminderFilterOFF_AfterIgnoring);

		Double d = Double.parseDouble(tRec.getAmount());

		if(todaysBalanceCompare_WithReminderFilterOFF_AfterIgnoring==0)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Today's balance after ignoring the reminder is: ["+sTodaysBalance_WithReminderFilterOFF_AfterIgnoring+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Today's balance after ignoring the reminder is NOT correct.");

		if(projectedBalanceCompare_WithReminderFilterOFF_AfterIgnoring==0)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Projected balance after ignoring the reminder is: ["+sProjectedBalance_WithReminderFilterOFF_AfterIgnoring+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected balance after ignoring the reminder is NOT correct.");

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		Verify.waitForObject(tp.next7DaysReminderFilter, 1);
		tp.next7DaysReminderFilter.click();
		tp.buttonApply.click();
		Thread.sleep(4000);

		sProjectedBalanceWith7DaysReminderFilter_AfterIgnoring = bcc.txtProjectedBalanceAmount.getText();

		bcc.scrollToTotalBalance();

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalanceWith7DaysReminderFilter_AfterIgnoring = bcc.txtTodaysBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance after ignoring the reminder with Next 7 Days filter: ["+sTodaysBalanceWith7DaysReminderFilter_AfterIgnoring+"]");
		Commentary.log(LogStatus.INFO, "Projected balance after ignoring the reminder with Next 7 Days filter: ["+sProjectedBalanceWith7DaysReminderFilter_AfterIgnoring+"]");

		Double dTodaysBalance_With7DaysReminderFilter_AfterIgnoring = h.processBalanceAmount(sTodaysBalanceWith7DaysReminderFilter_AfterIgnoring);
		Double dProjectedBalance_With7DaysReminderFilter_AfterIgnoring = h.processBalanceAmount(sProjectedBalanceWith7DaysReminderFilter_AfterIgnoring);

		if(dTodaysBalance_With7DaysReminderFilter+d==dTodaysBalance_With7DaysReminderFilter_AfterIgnoring)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Today's balance with Next 7 Days filter is: ["+sTodaysBalanceWith7DaysReminderFilter_AfterIgnoring+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Today's balance with Next 7 Days filter is NOT correct.");

		if(dProjectedBalance_With7DaysReminderFilter+d==dProjectedBalance_With7DaysReminderFilter_AfterIgnoring)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Projected balance with Next 7 Days filter is: ["+sProjectedBalanceWith7DaysReminderFilter_AfterIgnoring+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected balance with Next 7 Days filter is NOT correct.");

		bcc.backButton.click();
		Thread.sleep(1000);
		bcc.backButton.click();
		Thread.sleep(1000);

		op.tapOnBillsAndIncomeCard();

		bi.deleteSeries();

		sa.assertAll();
	}

	@Test (priority = 26, enabled = true)
	public void ST26_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating \"Ignore all previous instances\" option and Verifying the changes in Today's and Projected Balances of Accounts afterwards.");

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

		op.navigateToAcctList();

		bcc.selectAccount(sManualChecking);

		TransactionsPage tp = new TransactionsPage();

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();

		if(Verify.objExists(tp.dontShowReminderFilter)) {
			tp.dontShowReminderFilter.click();
			tp.buttonApply.click();
			Thread.sleep(4000);
		}

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalance_WithReminderFilterOFF = bcc.txtTodaysBalanceAmount.getText();

		bcc.scrollToProjectedBalance();
		sProjectedBalance_WithReminderFilterOFF = bcc.txtProjectedBalanceAmount.getText();

		Double dTodaysBalance_WithReminderFilterOFF = h.processBalanceAmount(sTodaysBalance_WithReminderFilterOFF);
		Double dProjectedBalance_WithReminderFilterOFF = h.processBalanceAmount(sProjectedBalance_WithReminderFilterOFF);

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		Verify.waitForObject(tp.next7DaysReminderFilter, 1);
		tp.next7DaysReminderFilter.click();
		tp.buttonApply.click();
		Thread.sleep(4000);

		sProjectedBalanceWith7DaysReminderFilter = bcc.txtProjectedBalanceAmount.getText();

		bcc.scrollToTotalBalance();

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalanceWith7DaysReminderFilter = bcc.txtTodaysBalanceAmount.getText();

		Double dTodaysBalance_With7DaysReminderFilter = h.processBalanceAmount(sTodaysBalanceWith7DaysReminderFilter);
		Double dProjectedBalance_With7DaysReminderFilter = h.processBalanceAmount(sProjectedBalanceWith7DaysReminderFilter);

		bcc.backButton.click();
		Thread.sleep(1000);
		bcc.backButton.click();
		Thread.sleep(1000);

		op.tapOnBillsAndIncomeCard();

		tp.tapOnFirstTransaction();

		bi.tapOnIgnoreAllPreviousInstancesOption();

		Verify.waitForObject(bi.noRemindersDueNext7Days, 2);
		if(Verify.objExists(bi.noRemindersDueNext7Days))
			Commentary.log(LogStatus.INFO, "PASS: Correct message is displayed when there are no Scheduled Reminders for Next 7 Days.");
		else 
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Correct message is NOT displayed when there are no Scheduled Reminders.");

		Verify.waitForObject(bi.backButton, 1);
		bi.backButton.click();
		Thread.sleep(1000);

		op.scrollToTop();

		op.navigateToAcctList();

		bcc.selectAccount(sManualChecking);

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();

		if(Verify.objExists(tp.dontShowReminderFilter)) {
			tp.dontShowReminderFilter.click();
			tp.buttonApply.click();
			Thread.sleep(5000);
		}

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalance_WithReminderFilterOFF_AfterIgnoring = bcc.txtTodaysBalanceAmount.getText();

		bcc.scrollToProjectedBalance();
		sProjectedBalance_WithReminderFilterOFF_AfterIgnoring = bcc.txtProjectedBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance after Ignoring the Reminders instances with Show Reminder filter Off: ["+sTodaysBalance_WithReminderFilterOFF_AfterIgnoring+"]");
		Commentary.log(LogStatus.INFO, "Projected balance after Ignoring the Reminders instances with Show Reminder filter Off: ["+sProjectedBalance_WithReminderFilterOFF_AfterIgnoring+"]");

		Double dTodaysBalance_WithReminderFilterOFF_AfterIgnoring = h.processBalanceAmount(sTodaysBalance_WithReminderFilterOFF_AfterIgnoring);
		Double dProjectedBalance_WithReminderFilterOFF_AfterIgnoring = h.processBalanceAmount(sProjectedBalance_WithReminderFilterOFF_AfterIgnoring);

		Integer todaysBalanceCompare_WithReminderFilterOFF_AfterIgnoring = Double.compare(dTodaysBalance_WithReminderFilterOFF, dTodaysBalance_WithReminderFilterOFF_AfterIgnoring);
		Integer projectedBalanceCompare_WithReminderFilterOFF_AfterIgnoring = Double.compare(dProjectedBalance_WithReminderFilterOFF, dProjectedBalance_WithReminderFilterOFF_AfterIgnoring);

		Double d = Double.parseDouble(tRec.getAmount());

		if(todaysBalanceCompare_WithReminderFilterOFF_AfterIgnoring==0)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Today's balance after ignoring the reminders instances with Show Reminders filter OFF is: ["+sTodaysBalance_WithReminderFilterOFF_AfterIgnoring+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Today's balance after ignoring the reminders instances with Show Reminders filter OFF is NOT correct.");

		if(projectedBalanceCompare_WithReminderFilterOFF_AfterIgnoring==0)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Projected balance after ignoring the reminders instances with Show Reminders filter OFF is: ["+sProjectedBalance_WithReminderFilterOFF_AfterIgnoring+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected balance after ignoring the reminders instances with Show Reminders filter OFF is NOT correct.");

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		Verify.waitForObject(tp.next7DaysReminderFilter, 1);
		tp.next7DaysReminderFilter.click();
		tp.buttonApply.click();
		Thread.sleep(4000);

		sProjectedBalanceWith7DaysReminderFilter_AfterIgnoring = bcc.txtProjectedBalanceAmount.getText();

		bcc.scrollToTotalBalance();

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalanceWith7DaysReminderFilter_AfterIgnoring = bcc.txtTodaysBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance after ignoring the reminders with Next 7 Days filter: ["+sTodaysBalanceWith7DaysReminderFilter_AfterIgnoring+"]");
		Commentary.log(LogStatus.INFO, "Projected balance after ignoring the reminders with Next 7 Days filter: ["+sProjectedBalanceWith7DaysReminderFilter_AfterIgnoring+"]");

		Double dTodaysBalance_With7DaysReminderFilter_AfterIgnoring = h.processBalanceAmount(sTodaysBalanceWith7DaysReminderFilter_AfterIgnoring);
		Double dProjectedBalance_With7DaysReminderFilter_AfterIgnoring = h.processBalanceAmount(sProjectedBalanceWith7DaysReminderFilter_AfterIgnoring);

		if(dTodaysBalance_With7DaysReminderFilter+d*3d==dTodaysBalance_With7DaysReminderFilter_AfterIgnoring)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Today's balance with Next 7 Days filter is: ["+sTodaysBalanceWith7DaysReminderFilter_AfterIgnoring+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Today's balance with Next 7 Days filter is NOT correct.");

		if(dProjectedBalance_With7DaysReminderFilter+d*3d==dProjectedBalance_With7DaysReminderFilter_AfterIgnoring)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Projected balance with Next 7 Days filter is: ["+sProjectedBalanceWith7DaysReminderFilter_AfterIgnoring+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected balance with Next 7 Days filter is NOT correct.");

		bcc.backButton.click();
		Thread.sleep(1000);
		bcc.backButton.click();
		Thread.sleep(1000);

		op.tapOnBillsAndIncomeCard();

		bi.deleteSeries();

		sa.assertAll();
	}

	@Test (priority = 27, enabled = true)
	public void ST27_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

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

		if(dTodaysBalance_With7DaysReminderFilter_BeforeEditingReminderInstance + d*2d == dTodaysBalance_With7DaysReminderFilter_AfterEditingReminderInstance)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Today's balance after editing Reminder Instance is: "+sTodaysBalance_With7DaysReminderFilter_AfterEditingReminderInstance+".");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Today's balance after editing Reminder Instance is NOT correct.");

		if(dProjectedBalance_With7DaysReminderFilter_BeforeEditingReminderInstance + d == dProjectedBalance_With7DaysReminderFilter_AfterEditingReminderInstance)
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

		Verify.waitForObject(bcc.backButton, 1);
		bcc.backButton.click();
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
		Verify.waitForObject(bi.thisMonthHeaderText, 2);
		
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
