package tournament;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.BankingAndCreditCardPage;
import dugout.BillsAndIncomePage;
import dugout.OverviewPage;
import dugout.SignInPage;
import dugout.TransactionsPage;
import referee.Commentary;
import referee.Verify;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;

public class ScheduledTransactionsTest_2 extends Recovery {

	String sUserName = "varsha.h@quicken.com";
	String sPassword = "Intuit!1";
	String sDataset = "ST Phase 2";
	String sManualChecking = "Manual_Checking";
	String sManualSaving = "Manual_Savings";

	@Test (priority = 12, enabled = true)
	public void ST12_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		SignInPage si = new SignInPage();
		si.signIn(sUserName, sPassword, sDataset);

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Tapping on the search bar and enter the reminder's payee name or amount to search for the results.");

		OverviewPage op = new OverviewPage();
		op.tapOnBillsAndIncomeCard();

		BillsAndIncomePage bi = new BillsAndIncomePage();
		bi.deleteAlreadyPresentReminderSeries();

		bi.addNewReminderSeries("Bill");

		String reminderName = "Reminder_"+h.getCurrentTime();

		TransactionRecord tRec = new TransactionRecord();
		tRec.setPayee(reminderName);
		tRec.setAmount("8.00");
		tRec.setFromAccount(sManualChecking);
		tRec.setFrequency("Weekly");
		tRec.setCategory("Entertainment");

		bi.addNewReminder(tRec);

		bi.tapOnSeriesTab();

		if(Verify.objExists(bi.reminderName))
			Commentary.log(LogStatus.INFO, "PASS: New Bill Reminder Series with Weekly frequency has been successfully created.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: New Bill Reminder Series has not been created.");

		bi.searchRecentReminderSeries(reminderName);

		Verify.waitForObject(bi.seriesHeaderText, 2);

		if(!Verify.objExists(bi.youHaveNoScheduledReminders))
			Commentary.log(LogStatus.INFO, "PASS: Search result is displayed when searched with Reminder Name.");
		else
			Commentary.log(LogStatus.FAIL, "FAIL: Search result is NOT displayed when searched with Reminder Name.");

		Verify.waitForObject(bi.clearSearchSeriesCrossButton, 1);
		bi.clearSearchSeriesCrossButton.click();

		bi.searchRecentReminderSeries(tRec.getAmount());

		if(!Verify.objExists(bi.youHaveNoScheduledReminders))
			Commentary.log(LogStatus.INFO, "PASS: Search result is displayed when searched with Amount.");
		else
			Commentary.log(LogStatus.FAIL, "FAIL: Search result is NOT displayed when searched with Amount.");

		bi.tapOnReminderSeries();

		bi.deleteSeriesFromEditButton();

		sa.assertAll();
	}

	@Test (priority = 13, enabled = true)
	public void ST13_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating the number of reminders for Bill Reminder Series in different reminder filter chips for 'Weekly' Frequency.");

		OverviewPage op = new OverviewPage();
		op.tapOnBillsAndIncomeCard();

		BillsAndIncomePage bi = new BillsAndIncomePage();
		bi.deleteAlreadyPresentReminderSeries();

		bi.addNewReminderSeries("Bill");

		String reminderName = "Reminder_"+h.getCurrentTime();

		TransactionRecord tRec = new TransactionRecord();
		tRec.setPayee(reminderName);
		tRec.setAmount("8.00");
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

		bi.deleteSeries();

		sa.assertAll();
	}

	@Test (priority = 14, enabled = true)
	public void ST14_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating the number of reminders for Income Reminder Series in different reminder filter chips for 'Monthly' Frequency.");

		OverviewPage op = new OverviewPage();
		op.tapOnBillsAndIncomeCard();

		BillsAndIncomePage bi = new BillsAndIncomePage();
		bi.deleteAlreadyPresentReminderSeries();

		bi.addNewReminderSeries("Income");

		String reminderName = "Reminder_"+h.getCurrentTime();

		TransactionRecord tRec = new TransactionRecord();
		tRec.setPayee(reminderName);
		tRec.setAmount("10.00");
		tRec.setToAccount(sManualSaving);
		tRec.setFrequency("Monthly");
		tRec.setCategory("Car Wash");
		tRec.setEndOnDate(h.getFutureDaysDate(90));

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

		if (numOfTransactionNext14Days==1) {		
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Only 1 transaction is seen on the Next 14 days filter chip.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: "+numOfTransactionNext14Days+" transaction is seen on the Next 14 days filter chip.");
		}

		bi.next30Days.click();
		Thread.sleep(1000);
		int numOfTransactionNext30Days = bi.getTransactionListSize();

		if (numOfTransactionNext30Days==1) {		
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Only 1 transaction is seen on the Next 30 days filter chip.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: "+numOfTransactionNext30Days+" transaction is seen on the Next 30 days filter chip.");
		}

		bi.scrolltoNext90DaysFilter();

		bi.next90Days.click();
		Thread.sleep(1000);
		int numOfTransactionNext90Days = bi.getTransactionListSize();

		if (numOfTransactionNext90Days==3) {		
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Only 3 transactions are seen on the Next 90 days filter chip.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: "+numOfTransactionNext90Days+" transaction is seen on the Next 90 days filter chip.");
		}

		bi.next12Months.click();
		Thread.sleep(1000);
		int numOfTransactionNext12Months = bi.getTransactionListSize();

		if (numOfTransactionNext12Months==3) {		
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Only 3 transactions are seen on the Next 12 Months filter chip.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: "+numOfTransactionNext12Months+" transaction is seen on the Next 12 Months filter chip.");
		}

		bi.deleteSeries();

		sa.assertAll();
	}

	@Test (priority = 15, enabled = true)
	public void ST15_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating the number of reminders for Transfer Reminder Series in different reminder filter chips for 'Only Once' Frequency.");

		OverviewPage op = new OverviewPage();
		op.tapOnBillsAndIncomeCard();

		BillsAndIncomePage bi = new BillsAndIncomePage();
		bi.deleteAlreadyPresentReminderSeries();

		bi.addNewReminderSeries("Transfer");

		String reminderName = "Reminder_"+h.getCurrentTime();

		TransactionRecord tRec = new TransactionRecord();
		tRec.setPayee(reminderName);
		tRec.setAmount("3.00");
		tRec.setFromAccount(sManualChecking);
		tRec.setToAccount(sManualSaving);
		tRec.setFrequency("Only once");

		bi.addNewReminder(tRec);

		Verify.waitForObject(bi.thisMonthHeaderText, 1);
		int numOfTransactionNext7Days = bi.getTransactionListSize();

		if (numOfTransactionNext7Days==2)	
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Only 2 transactions are seen on the Next 7 days filter chip.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: "+numOfTransactionNext7Days+" transaction is seen on the Next 7 days filter chip.");

		bi.next14Days.click();
		Thread.sleep(1000);
		int numOfTransactionNext14Days = bi.getTransactionListSize();

		if (numOfTransactionNext14Days==2)		
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Only 2 transactions are seen on the Next 14 days filter chip.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: "+numOfTransactionNext14Days+" transaction is seen on the Next 14 days filter chip.");

		bi.next30Days.click();
		Thread.sleep(1000);
		int numOfTransactionNext30Days = bi.getTransactionListSize();

		if (numOfTransactionNext30Days==2)		
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Only 2 transactions are seen on the Next 30 days filter chip.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: "+numOfTransactionNext30Days+" transaction is seen on the Next 30 days filter chip.");

		bi.scrolltoNext90DaysFilter();

		bi.next90Days.click();
		Thread.sleep(1000);
		int numOfTransactionNext90Days = bi.getTransactionListSize();

		if (numOfTransactionNext90Days==2)		
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Only 2 transactions are seen on the Next 90 days filter chip.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: "+numOfTransactionNext90Days+" transaction is seen on the Next 90 days filter chip.");

		bi.next12Months.click();
		Thread.sleep(1000);
		int numOfTransactionNext12Months = bi.getTransactionListSize();

		if (numOfTransactionNext12Months==2)		
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Only 2 transactions are seen on the Next 12 Months filter chip.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: "+numOfTransactionNext12Months+" transaction is seen on the Next 12 Months filter chip.");

		bi.deleteSeries();

		sa.assertAll();
	}

	@Test (priority = 16, enabled = true)
	public void ST16_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating the number of reminders for Bill Reminder Series in different reminder filter chips for 'Quarterly' Frequency.");

		OverviewPage op = new OverviewPage();
		op.tapOnBillsAndIncomeCard();

		BillsAndIncomePage bi = new BillsAndIncomePage();
		bi.deleteAlreadyPresentReminderSeries();

		bi.addNewReminderSeries("Bill");

		String reminderName = "Reminder_"+h.getCurrentTime();

		TransactionRecord tRec = new TransactionRecord();
		tRec.setPayee(reminderName);
		tRec.setAmount("9.00");
		tRec.setFromAccount(sManualChecking);
		tRec.setFrequency("Quarterly");
		tRec.setCategory("Entertainment");

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

		if (numOfTransactionNext14Days==1) {		
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Only 1 transaction is seen on the Next 14 days filter chip.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: "+numOfTransactionNext14Days+" transaction is seen on the Next 14 days filter chip.");
		}

		bi.next30Days.click();
		Thread.sleep(1000);
		int numOfTransactionNext30Days = bi.getTransactionListSize();

		if (numOfTransactionNext30Days==1) {		
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Only 1 transaction is seen on the Next 30 days filter chip.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: "+numOfTransactionNext30Days+" transaction is seen on the Next 30 days filter chip.");
		}

		bi.scrolltoNext90DaysFilter();

		bi.next90Days.click();
		Thread.sleep(1000);
		int numOfTransactionNext90Days = bi.getTransactionListSize();

		if (numOfTransactionNext90Days==1) {		
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Only 1 transaction is seen on the Next 90 days filter chip.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: "+numOfTransactionNext90Days+" transaction is seen on the Next 90 days filter chip.");
		}

		bi.next12Months.click();
		Thread.sleep(1000);
		int numOfTransactionNext12Months = bi.getTransactionListSize();

		if (numOfTransactionNext12Months==4) {		
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Only 4 transactions are seen on the Next 12 Months filter chip.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: "+numOfTransactionNext12Months+" transaction is seen on the Next 12 Months filter chip.");
		}

		bi.deleteSeries();

		sa.assertAll();
	}

	@Test(priority=17, enabled = true)
	public void ST17_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating the number of reminders in different reminder filter chips for 'Monthly' Frequency starting beyond 7 Days.");

		OverviewPage op = new OverviewPage();
		op.tapOnBillsAndIncomeCard();

		BillsAndIncomePage bi = new BillsAndIncomePage();
		bi.deleteAlreadyPresentReminderSeries();

		bi.addNewReminderSeries("Bill");

		String reminderName = "Reminder_"+h.getCurrentTime();

		TransactionRecord tRec = new TransactionRecord();
		tRec.setPayee(reminderName);
		tRec.setAmount("8.00");
		tRec.setFromAccount(sManualChecking);
		tRec.setDate(h.getFutureDaysDate(9));
		tRec.setCategory("Entertainment");
		tRec.setEndAfterNumberOfReminders("5");

		bi.addNewReminder(tRec);

		Verify.waitForObject(bi.thisMonthHeaderText, 1);
		int numOfTransactionNext7Days = bi.getTransactionListSize();

		if (numOfTransactionNext7Days==0) {		
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Zero transaction is seen on the Next 7 days filter chip.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: "+numOfTransactionNext7Days+" transaction is seen on the Next 7 days filter chip.");
		}

		if(Verify.objExists(bi.noRemindersDueNext7Days))
			Commentary.log(LogStatus.INFO, "PASS: Correct message is displayed when there are no Scheduled Reminders for Next 7 Days.");
		else 
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Correct message is displayed when there are no Scheduled Reminders.");

		bi.next14Days.click();
		Thread.sleep(2000);
		int numOfTransactionNext14Days = bi.getTransactionListSize();

		if (numOfTransactionNext14Days==1) {		
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Only 1 transaction is seen on the Next 14 days filter chip.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: "+numOfTransactionNext14Days+" transaction is seen on the Next 14 days filter chip.");
		}

		bi.next30Days.click();
		Thread.sleep(2000);
		int numOfTransactionNext30Days = bi.getTransactionListSize();

		if (numOfTransactionNext30Days==1) {		
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Only 1 transaction is seen on the Next 30 days filter chip.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: "+numOfTransactionNext30Days+" transaction is seen on the Next 30 days filter chip.");
		}

		bi.scrolltoNext90DaysFilter();

		bi.next90Days.click();
		Thread.sleep(1000);
		int numOfTransactionNext90Days = bi.getTransactionListSize();

		if (numOfTransactionNext90Days==3) {		
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Only 3 transactions are seen on the Next 90 days filter chip.");
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

		bi.deleteSeries();

		sa.assertAll();
	}

	@Test (priority = 18, enabled = true)
	public void ST18_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verifying the changes in Today's and Projected Balances of Accounts after creating Bill Reminder Series.");

		String sTodaysBalance, sProjectedBalance, sTodaysBalance_WithReminderFilterOFF, sProjectedBalance_WithReminderFilterOFF, sTodaysBalanceWith7DaysReminderFilter, sProjectedBalanceWith7DaysReminderFilter, sTodaysBalanceWith14DaysReminderFilter, sProjectedBalanceWith14DaysReminderFilter;

		OverviewPage op = new OverviewPage();
		op.tapOnBillsAndIncomeCard();

		BillsAndIncomePage bi = new BillsAndIncomePage();
		bi.deleteAlreadyPresentReminderSeries();

		Verify.waitForObject(bi.backButton, 1);
		bi.backButton.click();
		Thread.sleep(1000);

		op.scrollToTop();
		op.navigateToAcctList();

		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		bcc.selectAccount(sManualChecking);

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalance = bcc.txtTodaysBalanceAmount.getText();

		bcc.scrollToProjectedBalance();
		sProjectedBalance = bcc.txtProjectedBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance before creating the Reminder Series: ["+sTodaysBalance+"]");
		Commentary.log(LogStatus.INFO, "Projected balance before creating the Reminder Series: ["+sProjectedBalance+"]");

		Double dTodaysBalance = h.processBalanceAmount(sTodaysBalance);
		Double dProjectedBalance = h.processBalanceAmount(sProjectedBalance);

		bcc.backButton.click();
		Thread.sleep(1000);
		bcc.backButton.click();
		Thread.sleep(1000);

		op.tapOnBillsAndIncomeCard();

		//BillsAndIncomePage bi = new BillsAndIncomePage();

		bi.addNewReminderSeries("Bill");

		String reminderName = "Reminder_"+h.getCurrentTime();

		TransactionRecord tRec = new TransactionRecord();
		tRec.setPayee(reminderName);
		tRec.setAmount("10.00");
		tRec.setFromAccount(sManualChecking);
		tRec.setFrequency("Weekly");
		tRec.setEndAfterNumberOfReminders("5");

		bi.addNewReminder(tRec);
		Verify.waitForObject(bi.thisMonthHeaderText, 1);

		Verify.waitForObject(bi.backButton, 1);
		bi.backButton.click();
		Thread.sleep(2000);

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

		Commentary.log(LogStatus.INFO, "Today's balance after creating the Reminder Series with Show Reminder filter Off: ["+sTodaysBalance_WithReminderFilterOFF+"]");
		Commentary.log(LogStatus.INFO, "Projected balance after creating the Reminder Series with Show Reminder filter Off: ["+sProjectedBalance_WithReminderFilterOFF+"]");

		Double dTodaysBalance_WithReminderFilterOFF = h.processBalanceAmount(sTodaysBalance_WithReminderFilterOFF);
		Double dProjectedBalance_WithReminderFilterOFF = h.processBalanceAmount(sProjectedBalance_WithReminderFilterOFF);

		Integer todaysBalanceCompare_WithReminderFilterOFF = Double.compare(dTodaysBalance, dTodaysBalance_WithReminderFilterOFF);
		Integer projectedBalanceCompare_WithReminderFilterOFF = Double.compare(dProjectedBalance, dProjectedBalance_WithReminderFilterOFF);

		if(todaysBalanceCompare_WithReminderFilterOFF==0)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Today's balance with Show Reminders filter OFF is same as before and after creating reminder series.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Today's balance with Show Reminders filter OFF is NOT same as before and after creating reminder series.");

		if(projectedBalanceCompare_WithReminderFilterOFF==0)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Projected balance with Show Reminders filter OFF is same as before and after creating reminder series.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected balance with Show Reminders filter OFF is NOT same as before and after creating reminder series.");

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

		Commentary.log(LogStatus.INFO, "Today's balance after creating the Reminder Series with Next 7 Days filter : ["+sTodaysBalance_WithReminderFilterOFF+"]");
		Commentary.log(LogStatus.INFO, "Projected balance after creating the Reminder Series with Next 7 Days filter : ["+sProjectedBalance_WithReminderFilterOFF+"]");

		Double dTodaysBalance_With7DaysReminderFilter = h.processBalanceAmount(sTodaysBalanceWith7DaysReminderFilter);
		Double dProjectedBalance_With7DaysReminderFilter = h.processBalanceAmount(sProjectedBalanceWith7DaysReminderFilter);

		Double d = Double.parseDouble(tRec.getAmount());

		if(dTodaysBalance-d==dTodaysBalance_With7DaysReminderFilter)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Today's balance with Next 7 Days filter is "+sTodaysBalanceWith7DaysReminderFilter+".");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Today's balance with Next 7 Days filter is NOT correct.");

		if(dProjectedBalance-d==dProjectedBalance_With7DaysReminderFilter)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Projected balance with Next 7 Days filter is "+sProjectedBalanceWith7DaysReminderFilter+".");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected balance with Next 7 Days filter is NOT correct.");

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		Verify.waitForObject(tp.next14DaysReminderFilter, 1);
		tp.next14DaysReminderFilter.click();
		tp.buttonApply.click();
		Thread.sleep(4000);

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalanceWith14DaysReminderFilter = bcc.txtTodaysBalanceAmount.getText();

		bcc.scrollToProjectedBalance();
		sProjectedBalanceWith14DaysReminderFilter = bcc.txtProjectedBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance after creating the Reminder Series with Next 14 Days filter : ["+sTodaysBalanceWith14DaysReminderFilter+"]");
		Commentary.log(LogStatus.INFO, "Projected balance after creating the Reminder Series with Next 14 Days filter : ["+sProjectedBalanceWith14DaysReminderFilter+"]");

		Double dTodaysBalance_With14DaysReminderFilter = h.processBalanceAmount(sTodaysBalanceWith14DaysReminderFilter);
		Double dProjectedBalance_With14DaysReminderFilter = h.processBalanceAmount(sProjectedBalanceWith14DaysReminderFilter);

		if(dTodaysBalance-d==dTodaysBalance_With14DaysReminderFilter)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Today's balance with Next 14 Days filter is "+sTodaysBalanceWith14DaysReminderFilter+".");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Today's balance with Next 14 Days filter is NOT correct.");

		if(dProjectedBalance-d*2d==dProjectedBalance_With14DaysReminderFilter)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Projected balance with Next 14 Days filter is "+sProjectedBalanceWith14DaysReminderFilter+".");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected balance with Next 14 Days filter is NOT correct.");

		tp.buttonShowReminder.click();
		Verify.waitForObject(tp.dontShowReminderFilter, 1);
		tp.dontShowReminderFilter.click();
		tp.buttonApply.click();
		Thread.sleep(2000);

		bcc.backButton.click();
		Thread.sleep(1000);
		bcc.backButton.click();
		Thread.sleep(1000);

		op.tapOnBillsAndIncomeCard();

		bi.deleteSeries();

		sa.assertAll();
	}

	@Test (priority = 19, enabled = true)
	public void ST19_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verifying the changes in Today's and Projected Balances of Accounts after creating Income Reminder Series.");

		String sTodaysBalance, sProjectedBalance, sTodaysBalance_WithReminderFilterOFF, sProjectedBalance_WithReminderFilterOFF, sTodaysBalanceWith7DaysReminderFilter, sProjectedBalanceWith7DaysReminderFilter, sTodaysBalanceWith12MonthsReminderFilter, sProjectedBalanceWith12MonthsReminderFilter;

		OverviewPage op = new OverviewPage();
		op.tapOnBillsAndIncomeCard();

		BillsAndIncomePage bi = new BillsAndIncomePage();
		bi.deleteAlreadyPresentReminderSeries();

		Verify.waitForObject(bi.backButton, 1);
		bi.backButton.click();
		Thread.sleep(1000);

		op.scrollToTop();

		op.navigateToAcctList();

		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		bcc.selectAccount(sManualSaving);

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalance = bcc.txtTodaysBalanceAmount.getText();

		bcc.scrollToProjectedBalance();
		sProjectedBalance = bcc.txtProjectedBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance before creating the Reminder Series: ["+sTodaysBalance+"]");
		Commentary.log(LogStatus.INFO, "Projected balance before creating the Reminder Series: ["+sProjectedBalance+"]");

		Double dTodaysBalance = h.processBalanceAmount(sTodaysBalance);
		Double dProjectedBalance = h.processBalanceAmount(sProjectedBalance);

		bcc.backButton.click();
		Thread.sleep(1000);
		bcc.backButton.click();
		Thread.sleep(1000);

		op.tapOnBillsAndIncomeCard();

		bi.addNewReminderSeries("Income");

		String reminderName = "Reminder_"+h.getCurrentTime();

		TransactionRecord tRec = new TransactionRecord();
		tRec.setPayee(reminderName);
		tRec.setAmount("10.00");
		tRec.setToAccount(sManualSaving);

		bi.addNewReminder(tRec);
		Verify.waitForObject(bi.thisMonthHeaderText, 1);

		Verify.waitForObject(bi.backButton, 1);
		bi.backButton.click();
		Thread.sleep(1000);

		op.scrollToTop();

		op.navigateToAcctList();

		bcc.selectAccount(sManualSaving);

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

		Commentary.log(LogStatus.INFO, "Today's balance after creating the Reminder Series with Show Reminder filter Off: ["+sTodaysBalance_WithReminderFilterOFF+"]");
		Commentary.log(LogStatus.INFO, "Projected balance after creating the Reminder Series with Show Reminder filter Off: ["+sProjectedBalance_WithReminderFilterOFF+"]");

		Double dTodaysBalance_WithReminderFilterOFF = h.processBalanceAmount(sTodaysBalance_WithReminderFilterOFF);
		Double dProjectedBalance_WithReminderFilterOFF = h.processBalanceAmount(sProjectedBalance_WithReminderFilterOFF);

		Integer todaysBalanceCompare_WithReminderFilterOFF = Double.compare(dTodaysBalance, dTodaysBalance_WithReminderFilterOFF);
		Integer projectedBalanceCompare_WithReminderFilterOFF = Double.compare(dProjectedBalance, dProjectedBalance_WithReminderFilterOFF);

		if(todaysBalanceCompare_WithReminderFilterOFF==0)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Today's balance with Show Reminders filter OFF is same as before and after creating reminder series.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Today's balance with Show Reminders filter OFF is NOT same as before and after creating reminder series.");

		if(projectedBalanceCompare_WithReminderFilterOFF==0)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Projected balance with Show Reminders filter OFF is same as before and after creating reminder series.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected balance with Show Reminders filter OFF is NOT same as before and after creating reminder series.");

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

		Commentary.log(LogStatus.INFO, "Today's balance after creating the Reminder Series with Next 7 Days filter : ["+sTodaysBalance_WithReminderFilterOFF+"]");
		Commentary.log(LogStatus.INFO, "Projected balance after creating the Reminder Series with Next 7 Days filter : ["+sProjectedBalance_WithReminderFilterOFF+"]");

		Double dTodaysBalance_With7DaysReminderFilter = h.processBalanceAmount(sTodaysBalanceWith7DaysReminderFilter);
		Double dProjectedBalance_With7DaysReminderFilter = h.processBalanceAmount(sProjectedBalanceWith7DaysReminderFilter);

		Double d = Double.parseDouble(tRec.getAmount());

		if(dTodaysBalance+d==dTodaysBalance_With7DaysReminderFilter)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Today's balance with Next 7 Days filter is "+sTodaysBalanceWith7DaysReminderFilter+".");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Today's balance with Next 7 Days filter is NOT correct.");

		if(dProjectedBalance+d==dProjectedBalance_With7DaysReminderFilter)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Projected balance with Next 7 Days filter is "+sProjectedBalanceWith7DaysReminderFilter+".");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected balance with Next 7 Days filter is NOT correct.");

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		Verify.waitForObject(tp.next12MonthsReminderFilter, 1);
		tp.next12MonthsReminderFilter.click();
		tp.buttonApply.click();
		Thread.sleep(4000);

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalanceWith12MonthsReminderFilter = bcc.txtTodaysBalanceAmount.getText();

		bcc.scrollToProjectedBalance();
		sProjectedBalanceWith12MonthsReminderFilter = bcc.txtProjectedBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance after creating the Reminder Series with Next 12 Months filter : ["+sTodaysBalanceWith12MonthsReminderFilter+"]");
		Commentary.log(LogStatus.INFO, "Projected balance after creating the Reminder Series with Next 12 Months filter : ["+sProjectedBalanceWith12MonthsReminderFilter+"]");

		Double dTodaysBalance_With12MonthsReminderFilter = h.processBalanceAmount(sTodaysBalanceWith12MonthsReminderFilter);
		Double dProjectedBalance_With12MonthsReminderFilter = h.processBalanceAmount(sProjectedBalanceWith12MonthsReminderFilter);

		if(dTodaysBalance+d==dTodaysBalance_With12MonthsReminderFilter)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Today's balance with Next 12 Months filter is "+sTodaysBalanceWith12MonthsReminderFilter+".");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Today's balance with Next 12 Months filter is NOT correct.");

		if(dProjectedBalance+d*12d==dProjectedBalance_With12MonthsReminderFilter)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Projected balance with Next 12 Months filter is "+sProjectedBalanceWith12MonthsReminderFilter+".");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected balance with Next 12 Months filter is NOT correct.");

		tp.buttonShowReminder.click();
		Verify.waitForObject(tp.dontShowReminderFilter, 1);
		tp.dontShowReminderFilter.click();
		tp.buttonApply.click();
		Thread.sleep(2000);

		bcc.backButton.click();
		Thread.sleep(1000);
		bcc.backButton.click();
		Thread.sleep(1000);

		op.tapOnBillsAndIncomeCard();

		bi.deleteSeries();

		sa.assertAll();
	}

	@Test (priority = 20, enabled = true)
	public void ST20_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verifying the changes in Today's and Projected Balances of Accounts after creating Transfer Reminder Series.");

		String sTodaysBalance_CheckingAccount, sProjectedBalance_CheckingAccount, sTodaysBalance_SavingsAccount, sProjectedBalance_SavingsAccount; 
		String sTodaysBalance_CheckingAccount_WithReminderFilterOFF, sProjectedBalance_CheckingAccount_WithReminderFilterOFF, sTodaysBalance_SavingsAccount_WithReminderFilterOFF, sProjectedBalance_SavingsAccount_WithReminderFilterOFF; 
		String sTodaysBalanceWith7DaysReminderFilter_CheckingAccount, sProjectedBalanceWith7DaysReminderFilter_CheckingAccount, sTodaysBalanceWith7DaysReminderFilter_SavingsAccount, sProjectedBalanceWith7DaysReminderFilter_SavingsAccount;
		String sTodaysBalanceWith12MonthsReminderFilter_CheckingAccount, sProjectedBalanceWith12MonthsReminderFilter_CheckingAccount, sTodaysBalanceWith12MonthsReminderFilter_SavingsAccount, sProjectedBalanceWith12MonthsReminderFilter_SavingsAccount;

		OverviewPage op = new OverviewPage();
		op.tapOnBillsAndIncomeCard();

		BillsAndIncomePage bi = new BillsAndIncomePage();
		bi.deleteAlreadyPresentReminderSeries();

		Verify.waitForObject(bi.backButton, 1);
		bi.backButton.click();
		Thread.sleep(1000);

		op.scrollToTop();

		op.navigateToAcctList();

		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		bcc.selectAccount(sManualChecking);

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalance_CheckingAccount = bcc.txtTodaysBalanceAmount.getText();

		bcc.scrollToProjectedBalance();
		sProjectedBalance_CheckingAccount = bcc.txtProjectedBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance of Checking Account before creating the Reminder Series: ["+sTodaysBalance_CheckingAccount+"]");
		Commentary.log(LogStatus.INFO, "Projected balance of Checking Account before creating the Reminder Series: ["+sProjectedBalance_CheckingAccount+"]");

		Double dTodaysBalance_CheckingAccount = h.processBalanceAmount(sTodaysBalance_CheckingAccount);
		Double dProjectedBalance_CheckingAccount = h.processBalanceAmount(sProjectedBalance_CheckingAccount);

		bcc.backButton.click();
		Thread.sleep(1000);

		bcc.selectAccount(sManualSaving);

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalance_SavingsAccount = bcc.txtTodaysBalanceAmount.getText();

		bcc.scrollToProjectedBalance();
		sProjectedBalance_SavingsAccount = bcc.txtProjectedBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance of Savings Account before creating the Reminder Series: ["+sTodaysBalance_SavingsAccount+"]");
		Commentary.log(LogStatus.INFO, "Projected balance of Savings Account before creating the Reminder Series: ["+sProjectedBalance_SavingsAccount+"]");

		Double dTodaysBalance_SavingsAccount = h.processBalanceAmount(sTodaysBalance_SavingsAccount);
		Double dProjectedBalance_SavingsAccount = h.processBalanceAmount(sProjectedBalance_SavingsAccount);

		bcc.backButton.click();
		Thread.sleep(1000);
		bcc.backButton.click();
		Thread.sleep(1000);

		op.tapOnBillsAndIncomeCard();

		bi.addNewReminderSeries("Transfer");

		String reminderName = "Reminder_"+h.getCurrentTime();

		TransactionRecord tRec = new TransactionRecord();
		tRec.setPayee(reminderName);
		tRec.setAmount("10.00");
		tRec.setFromAccount(sManualChecking);
		tRec.setToAccount(sManualSaving);
		tRec.setFrequency("Quarterly");

		bi.addNewReminder(tRec);
		Verify.waitForObject(bi.thisMonthHeaderText, 1);

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
		sTodaysBalance_CheckingAccount_WithReminderFilterOFF = bcc.txtTodaysBalanceAmount.getText();

		bcc.scrollToProjectedBalance();
		sProjectedBalance_CheckingAccount_WithReminderFilterOFF = bcc.txtProjectedBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance of Checking Account after creating the Reminder Series with Show Reminder filter Off: ["+sTodaysBalance_CheckingAccount_WithReminderFilterOFF+"]");
		Commentary.log(LogStatus.INFO, "Projected balance of Checking Account after creating the Reminder Series with Show Reminder filter Off: ["+sProjectedBalance_CheckingAccount_WithReminderFilterOFF+"]");

		Double dTodaysBalance_CheckingAccount_WithReminderFilterOFF = h.processBalanceAmount(sTodaysBalance_CheckingAccount_WithReminderFilterOFF);
		Double dProjectedBalance_CheckingAccount_WithReminderFilterOFF = h.processBalanceAmount(sProjectedBalance_CheckingAccount_WithReminderFilterOFF);

		Integer todaysBalanceCompare_CheckingAccount_WithReminderFilterOFF = Double.compare(dTodaysBalance_CheckingAccount, dTodaysBalance_CheckingAccount_WithReminderFilterOFF);
		Integer projectedBalanceCompare_CheckingAccount_WithReminderFilterOFF = Double.compare(dProjectedBalance_CheckingAccount, dProjectedBalance_CheckingAccount_WithReminderFilterOFF);

		if(todaysBalanceCompare_CheckingAccount_WithReminderFilterOFF==0)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Today's balance of Checking Account with Show Reminders filter OFF is same as before and after creating reminder series.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Today's balance of Checking Account with Show Reminders filter OFF is NOT same as before and after creating reminder series.");

		if(projectedBalanceCompare_CheckingAccount_WithReminderFilterOFF==0)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Projected balance of Checking Account with Show Reminders filter OFF is same as before and after creating reminder series.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected balance of Checking Account with Show Reminders filter OFF is NOT same as before and after creating reminder series.");

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		Verify.waitForObject(tp.next7DaysReminderFilter, 1);
		tp.next7DaysReminderFilter.click();
		tp.buttonApply.click();
		Thread.sleep(4000);

		sProjectedBalanceWith7DaysReminderFilter_CheckingAccount = bcc.txtProjectedBalanceAmount.getText();

		bcc.scrollToTotalBalance();

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalanceWith7DaysReminderFilter_CheckingAccount = bcc.txtTodaysBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance of Checking Account after creating the Reminder Series with Next 7 Days filter : ["+sTodaysBalanceWith7DaysReminderFilter_CheckingAccount+"]");
		Commentary.log(LogStatus.INFO, "Projected balance of Checking Account after creating the Reminder Series with Next 7 Days filter : ["+sProjectedBalanceWith7DaysReminderFilter_CheckingAccount+"]");

		Double dTodaysBalanceWith7DaysReminderFilter_CheckingAccount = h.processBalanceAmount(sTodaysBalanceWith7DaysReminderFilter_CheckingAccount);
		Double dProjectedBalanceWith7DaysReminderFilter_CheckingAccount = h.processBalanceAmount(sProjectedBalanceWith7DaysReminderFilter_CheckingAccount);

		Double d = Double.parseDouble(tRec.getAmount());

		if(dTodaysBalance_CheckingAccount-d==dTodaysBalanceWith7DaysReminderFilter_CheckingAccount)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Today's balance of Checking Account with Next 7 Days filter is "+sTodaysBalanceWith7DaysReminderFilter_CheckingAccount+".");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Today's balance of Checking Account with Next 7 Days filter is NOT correct.");

		if(dProjectedBalance_CheckingAccount-d==dProjectedBalanceWith7DaysReminderFilter_CheckingAccount)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Projected balance of Checking Account with Next 7 Days filter is "+sProjectedBalanceWith7DaysReminderFilter_CheckingAccount+".");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected balance of Checking Account with Next 7 Days filter is NOT correct.");

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		Verify.waitForObject(tp.next12MonthsReminderFilter, 1);
		tp.next12MonthsReminderFilter.click();
		tp.buttonApply.click();
		Thread.sleep(4000);

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalanceWith12MonthsReminderFilter_CheckingAccount = bcc.txtTodaysBalanceAmount.getText();

		bcc.scrollToProjectedBalance();
		sProjectedBalanceWith12MonthsReminderFilter_CheckingAccount = bcc.txtProjectedBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance of Checking Account after creating the Reminder Series with Next 12 Months filter : ["+sTodaysBalanceWith12MonthsReminderFilter_CheckingAccount+"]");
		Commentary.log(LogStatus.INFO, "Projected balance of Checking Account after creating the Reminder Series with Next 12 Months filter : ["+sProjectedBalanceWith12MonthsReminderFilter_CheckingAccount+"]");

		Double dTodaysBalanceWith12MonthsReminderFilter_CheckingAccount = h.processBalanceAmount(sTodaysBalanceWith12MonthsReminderFilter_CheckingAccount);
		Double dProjectedBalanceWith12MonthsReminderFilter_CheckingAccount = h.processBalanceAmount(sProjectedBalanceWith12MonthsReminderFilter_CheckingAccount);

		if(dTodaysBalance_CheckingAccount-d==dTodaysBalanceWith12MonthsReminderFilter_CheckingAccount)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Today's balance of Checking Account with Next 12 Months filter is "+sTodaysBalanceWith12MonthsReminderFilter_CheckingAccount+".");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Today's balance of Checking Account with Next 12 Months filter is NOT correct.");

		if(dProjectedBalance_CheckingAccount-d*4d==dProjectedBalanceWith12MonthsReminderFilter_CheckingAccount)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Projected balance of Checking Account with Next 12 Months filter is "+sProjectedBalanceWith12MonthsReminderFilter_CheckingAccount+".");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected balance of Checking Account with Next 12 Months filter is NOT correct.");

		tp.buttonShowReminder.click();
		Verify.waitForObject(tp.dontShowReminderFilter, 1);
		tp.dontShowReminderFilter.click();
		tp.buttonApply.click();
		Thread.sleep(2000);

		bcc.backButton.click();
		Thread.sleep(1000);

		bcc.selectAccount(sManualSaving);

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();

		if(Verify.objExists(tp.dontShowReminderFilter)) {
			tp.dontShowReminderFilter.click();
			tp.buttonApply.click();
			Thread.sleep(4000);
		}

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalance_SavingsAccount_WithReminderFilterOFF = bcc.txtTodaysBalanceAmount.getText();

		bcc.scrollToProjectedBalance();
		sProjectedBalance_SavingsAccount_WithReminderFilterOFF = bcc.txtProjectedBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance of Savings Account after creating the Reminder Series with Show Reminder filter Off: ["+sTodaysBalance_SavingsAccount_WithReminderFilterOFF+"]");
		Commentary.log(LogStatus.INFO, "Projected balance of Savings Account after creating the Reminder Series with Show Reminder filter Off: ["+sProjectedBalance_SavingsAccount_WithReminderFilterOFF+"]");

		Double dTodaysBalance_SavingsAccount_WithReminderFilterOFF = h.processBalanceAmount(sTodaysBalance_SavingsAccount_WithReminderFilterOFF);
		Double dProjectedBalance_SavingsAccount_WithReminderFilterOFF = h.processBalanceAmount(sProjectedBalance_SavingsAccount_WithReminderFilterOFF);

		Integer todaysBalanceCompare_SavingsAccount_WithReminderFilterOFF = Double.compare(dTodaysBalance_SavingsAccount, dTodaysBalance_SavingsAccount_WithReminderFilterOFF);
		Integer projectedBalanceCompare_SavingsAccount_WithReminderFilterOFF = Double.compare(dProjectedBalance_SavingsAccount, dProjectedBalance_SavingsAccount_WithReminderFilterOFF);

		if(todaysBalanceCompare_SavingsAccount_WithReminderFilterOFF==0)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Today's balance of Savings Account with Show Reminders filter OFF is same as before and after creating reminder series.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Today's balance of Savings Account with Show Reminders filter OFF is NOT same as before and after creating reminder series.");

		if(projectedBalanceCompare_SavingsAccount_WithReminderFilterOFF==0)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Projected balance of Savings Account with Show Reminders filter OFF is same as before and after creating reminder series.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected balance of Savings Account with Show Reminders filter OFF is NOT same as before and after creating reminder series.");

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		Verify.waitForObject(tp.next7DaysReminderFilter, 1);
		tp.next7DaysReminderFilter.click();
		tp.buttonApply.click();
		Thread.sleep(4000);

		sProjectedBalanceWith7DaysReminderFilter_SavingsAccount = bcc.txtProjectedBalanceAmount.getText();

		bcc.scrollToTotalBalance();

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalanceWith7DaysReminderFilter_SavingsAccount = bcc.txtTodaysBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance of Savings Account after creating the Reminder Series with Next 7 Days filter : ["+sTodaysBalanceWith7DaysReminderFilter_SavingsAccount+"]");
		Commentary.log(LogStatus.INFO, "Projected balance of Savings Account after creating the Reminder Series with Next 7 Days filter : ["+sProjectedBalanceWith7DaysReminderFilter_SavingsAccount+"]");

		Double dTodaysBalanceWith7DaysReminderFilter_SavingsAccount = h.processBalanceAmount(sTodaysBalanceWith7DaysReminderFilter_SavingsAccount);
		Double dProjectedBalanceWith7DaysReminderFilter_SavingsAccount = h.processBalanceAmount(sProjectedBalanceWith7DaysReminderFilter_SavingsAccount);

		if(dTodaysBalance_SavingsAccount+d==dTodaysBalanceWith7DaysReminderFilter_SavingsAccount)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Today's balance of Savings Account with Next 7 Days filter is "+sTodaysBalanceWith7DaysReminderFilter_SavingsAccount+".");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Today's balance of Savings Account with Next 7 Days filter is NOT correct.");

		if(dProjectedBalance_SavingsAccount+d==dProjectedBalanceWith7DaysReminderFilter_SavingsAccount)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Projected balance of Savings Account with Next 7 Days filter is "+sProjectedBalanceWith7DaysReminderFilter_SavingsAccount+".");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected balance of Savings Account with Next 7 Days filter is NOT correct.");

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		Verify.waitForObject(tp.next12MonthsReminderFilter, 1);
		tp.next12MonthsReminderFilter.click();
		tp.buttonApply.click();
		Thread.sleep(4000);

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		sTodaysBalanceWith12MonthsReminderFilter_SavingsAccount = bcc.txtTodaysBalanceAmount.getText();

		bcc.scrollToProjectedBalance();
		Verify.waitForObject(bcc.txtProjectedBalanceAmount, 1);
		sProjectedBalanceWith12MonthsReminderFilter_SavingsAccount = bcc.txtProjectedBalanceAmount.getText();

		Commentary.log(LogStatus.INFO, "Today's balance of Savings Account after creating the Reminder Series with Next 12 Months filter : ["+sTodaysBalanceWith12MonthsReminderFilter_SavingsAccount+"]");
		Commentary.log(LogStatus.INFO, "Projected balance of Savings Account after creating the Reminder Series with Next 12 Months filter : ["+sProjectedBalanceWith12MonthsReminderFilter_SavingsAccount+"]");

		Double dTodaysBalanceWith12MonthsReminderFilter_SavingsAccount = h.processBalanceAmount(sTodaysBalanceWith12MonthsReminderFilter_SavingsAccount);
		Double dProjectedBalanceWith12MonthsReminderFilter_SavingsAccount = h.processBalanceAmount(sProjectedBalanceWith12MonthsReminderFilter_SavingsAccount);

		if(dTodaysBalance_SavingsAccount+d==dTodaysBalanceWith12MonthsReminderFilter_SavingsAccount)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Today's balance of Savings Account with Next 12 Months filter is "+sTodaysBalanceWith12MonthsReminderFilter_SavingsAccount+".");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Today's balance of Savings Account with Next 12 Months filter is NOT correct.");

		if(dProjectedBalance_SavingsAccount+d*4d==dProjectedBalanceWith12MonthsReminderFilter_SavingsAccount)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Projected balance of Savings Account with Next 12 Months filter is "+sProjectedBalanceWith12MonthsReminderFilter_SavingsAccount+".");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected balance of Savings Account with Next 12 Months filter is NOT correct.");

		tp.buttonShowReminder.click();
		Verify.waitForObject(tp.dontShowReminderFilter, 1);
		tp.dontShowReminderFilter.click();
		tp.buttonApply.click();
		Thread.sleep(2000);

		bcc.backButton.click();
		Thread.sleep(1000);
		bcc.backButton.click();
		Thread.sleep(1000);

		op.tapOnBillsAndIncomeCard();

		bi.deleteSeries();

		sa.assertAll();
	}

}
