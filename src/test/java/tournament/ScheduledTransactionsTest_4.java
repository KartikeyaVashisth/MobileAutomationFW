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

public class ScheduledTransactionsTest_4 extends Recovery {

	String sUserName = "varsha.h@quicken.com";
	String sPassword = "Intuit!1";
	String sDataset = "ST Phase 2";
	String sManualChecking = "Manual_Checking";
	String sManualSaving = "Manual_Savings";

	@Test (priority = 33, enabled = true)
	public void ST33_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		SignInPage si = new SignInPage();
		si.signIn(sUserName, sPassword, sDataset);
		
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

		if(dTodaysBalance_WithReminderFilterOFF-d==dTodaysBalance_WithReminderFilterOFF_AfterEntering)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Today's balance with Reminder filter OFF: ["+sTodaysBalance_WithReminderFilterOFF_AfterEntering+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Today's balance with Reminder filter OFF is NOT correct.");

		if(dProjectedBalance_WithReminderFilterOFF-d==dProjectedBalance_WithReminderFilterOFF_AfterEntering)
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

		if(dTodaysBalance_WithReminderFilterOFF-d*3d==dTodaysBalance_WithReminderFilterOFF_AfterEntering)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Today's balance with Reminders filter OFF after entering reminder instances is: ["+sTodaysBalance_WithReminderFilterOFF_AfterEntering+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Today's balance with Reminders filter OFF after entering reminder instances is NOT correct.");

		if(dProjectedBalance_WithReminderFilterOFF-d*3d==dProjectedBalance_WithReminderFilterOFF_AfterEntering)
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

		if(dTodaysBalance_With7DaysReminderFilter+d==dTodaysBalance_With7DaysReminderFilter_AfterIgnoring)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Today's balance with Next 7 Days filter is "+sTodaysBalanceWith7DaysReminderFilter_AfterIgnoring+".");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Today's balance with Next 7 Days filter is NOT correct.");

		if(dProjectedBalance_With7DaysReminderFilter+d==dProjectedBalance_With7DaysReminderFilter_AfterIgnoring)
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

		if(dTodaysBalance_With7DaysReminderFilter+d*3d==dTodaysBalance_With7DaysReminderFilter_AfterIgnoring)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Today's balance with Next 7 Days filter is: ["+sTodaysBalanceWith7DaysReminderFilter_AfterIgnoring+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Today's balance with Next 7 Days filter is NOT correct.");

		if(dProjectedBalance_With7DaysReminderFilter+d*3d==dProjectedBalance_With7DaysReminderFilter_AfterIgnoring)
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

}
