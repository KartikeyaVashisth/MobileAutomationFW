package tournament;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.BillsAndIncomePage;
import dugout.OverviewPage;
import dugout.SignInPage;
import referee.Commentary;
import referee.Verify;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;

public class ScheduledTransactionsTest_1 extends Recovery {

	String sUserName = "varsha.h@quicken.com";
	String sPassword = "Intuit!1";
	String sDataset = "ST Phase 2";
	String sManualChecking = "Manual_Checking";
	String sManualSaving = "Manual_Savings";

	@Test (priority = 1, enabled = true)
	public void ST1_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		SignInPage si = new SignInPage();
		si.signIn(sUserName, sPassword, sDataset);

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verifying all the different elements of Bills and Income Card without any reminder series.");

		OverviewPage op = new OverviewPage();
		op.tapOnBillsAndIncomeCard();

		BillsAndIncomePage bi = new BillsAndIncomePage();
		bi.deleteAlreadyPresentReminderSeries();

		Verify.waitForObject(bi.addNewReminderButton, 2);
		if (Verify.objExists(bi.billsAndIncomeHeaderText))
			Commentary.log(LogStatus.INFO, "PASS: Bills and Income Header text is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Bills and Income Header text is not displayed.");

		if (Verify.objExists(bi.backButton))
			Commentary.log(LogStatus.INFO, "PASS: Back button is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Back button is not displayed.");

		if (Verify.objExists(bi.remindersTab))
			Commentary.log(LogStatus.INFO, "PASS: Reminders Tab is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Reminders Tab is not displayed.");

		if (Verify.objExists(bi.seriesTab))
			Commentary.log(LogStatus.INFO, "PASS: Series Tab is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Series Tab is not displayed.");

		if (Verify.objExists(bi.addNewReminderButton))
			Commentary.log(LogStatus.INFO, "PASS: Add Reminder Button is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Add Reminder Button is not displayed.");

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

	@Test (priority = 2, enabled = true)
	public void ST2_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating by adding a New Bill Reminder Series of \"Weekly\" frequency, verifying Reminder filter chips, Verification on View Series page and Deleting the Reminder Series.");

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
		tRec.setDate(h.getFutureDaysDate(0));
		tRec.setFrequency("Weekly");
		tRec.setEvery("3");
		tRec.setCategory("Entertainment");
		tRec.setEndAfterNumberOfReminders("3");

		bi.addNewReminder(tRec);

		Verify.waitForObject(bi.next7Days, 2);

		if (Verify.objExists(bi.next7Days))
			Commentary.log(LogStatus.INFO, "PASS: Next 7 Days chip is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Next 7 Days chip is not displayed.");

		if (Verify.objExists(bi.next14Days))
			Commentary.log(LogStatus.INFO, "PASS: Next 14 Days chip is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Next 14 Days chip is not displayed.");

		if (Verify.objExists(bi.next30Days))
			Commentary.log(LogStatus.INFO, "PASS: Next 30 Days chip is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Next 30 Days chip is not displayed.");

		bi.scrolltoNext90DaysFilter();

		if (Verify.objExists(bi.next90Days))
			Commentary.log(LogStatus.INFO, "PASS: Next 90 Days chip is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Next 90 Days chip is not displayed.");

		if (Verify.objExists(bi.next12Months))
			Commentary.log(LogStatus.INFO, "PASS: Next 12 Months chip is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Next 12 Months chip is not displayed.");

		if (Verify.objExists(bi.thisMonthHeaderText))
			Commentary.log(LogStatus.INFO, "PASS: This Month header text is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: This Month header text is not displayed.");

		if (Verify.objExists(bi.addNewReminderButton))
			Commentary.log(LogStatus.INFO, "PASS: Add Reminder Button is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Add Reminder Button is not displayed.");

		bi.tapOnSeriesTab();

		if (Verify.objExists(bi.searchSeriesTxtField))
			Commentary.log(LogStatus.INFO, "PASS: Search series text field is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Search series text field is not displayed.");

		if (Verify.objExists(bi.seriesHeaderText))
			Commentary.log(LogStatus.INFO, "PASS: Series header text field is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Series header text field is not displayed.");

		if(Verify.objExists(bi.reminderName))
			Commentary.log(LogStatus.INFO, "PASS: New Bill Reminder Series has been successfully created.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: New Bill Reminder Series has not been created.");

		bi.tapOnReminderSeries();

		String reminderSeriesNameOnViewSeries = bi.reminderSeriesNameOnViewSeries.getText();
		String reminderSeriesAmountOnViewSeries = bi.reminderSeriesAmountOnViewSeries.getText();
		String substringAmountValueOnViewSeries = reminderSeriesAmountOnViewSeries.substring(1);
		String reminderSeriesFrequencyOnViewSeries = bi.reminderSeriesFrequencyOnViewSeries.getText();
		String substringFrequencyValue = reminderSeriesFrequencyOnViewSeries.substring(6,7);
		String typeViewSeriesPage = bi.reminderTypeViewSeriesPage.getText();
		String categoryValueViewSeriesPage = bi.categoryTypeViewSeriesPage.getText();
		String endAfterViewSeriesPage = bi.endAfterViewSeriesPage.getText();
		String substringEndAfterViewSeriesPage = endAfterViewSeriesPage.substring(0, 1);
		String fromAccountViewSeriesPage = bi.fromAccountViewSeriesPage.getText();

		if(reminderSeriesNameOnViewSeries.equals(tRec.getPayee())) 
			Commentary.log(LogStatus.INFO, "PASS: Reminder Name is matched successfully with the Reminder name on View Series Page.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Reminder Name is NOT matched with the Reminder name on View Series Page.");

		if(substringAmountValueOnViewSeries.equals(tRec.getAmount())) 
			Commentary.log(LogStatus.INFO, "PASS: Reminder series amount is matched successfully with the Reminder amount on View Series Page.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Reminder series amount is NOT matched with the Reminder amount on View Series Page.");

		if(substringFrequencyValue.equals(tRec.getEvery())) 
			Commentary.log(LogStatus.INFO, "PASS: 'Every' field value on View Series Page is same as the entered Every field.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Every' field value on View Series Page is not same as the entered Every field.");

		if(typeViewSeriesPage.equals("Bill")) 
			Commentary.log(LogStatus.INFO, "PASS: Reminder type is Bill which is expected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Reminder is Not of Bill type.");

		if(categoryValueViewSeriesPage.equals(tRec.getCategory())) 
			Commentary.log(LogStatus.INFO, "PASS: 'Category' field value on View Series Page is same as the entered Category field.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Category' field value on View Series Page is not same as the entered Category field.");

		if(substringEndAfterViewSeriesPage.equals(tRec.getEndAfterNumberOfReminders())) 
			Commentary.log(LogStatus.INFO, "PASS: 'End After' field value on View Series Page is same as the entered End After No. of Reminders field.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'End After' field value on View Series Page is not same as the entered End After No. of Reminders field.");

		if(fromAccountViewSeriesPage.equals(tRec.getFromAccount())) 
			Commentary.log(LogStatus.INFO, "PASS: 'From Account' Name on View Series Page is same as the entered 'From Account' name.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'From Account' Name on View Series Page is not same as the entered 'From Account' name.");

		if(!Verify.objExists(bi.tagsViewSeriesPage)) 
			Commentary.log(LogStatus.INFO, "PASS: 'Tags' is not displayed as expected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Tags' field is displayed and is not expected.");

		if(!Verify.objExists(bi.notesViewSeriesPage)) 
			Commentary.log(LogStatus.INFO, "PASS: 'Note' is not displayed as expected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Note' field is displayed and is not expected.");

		bi.editButton.click();
		bi.tapOnDeleteSeries();

		Verify.waitForObject(bi.deleteReminderSeriesWarningMessage, 1);
		if(Verify.objExists(bi.deleteReminderSeriesWarningMessage))
			Commentary.log(LogStatus.INFO, "PASS: Delete confirmation alert message is displayed before deleting Bill Reminder Series.");
		else 
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Delete confirmation alert messsage is NOT displayed before deleting Bill Reminder Series.");

		Verify.waitForObject(bi.deleteReminderSeriesAlertButton, 1);
		bi.deleteReminderSeriesAlertButton.click();
		Thread.sleep(2000);

		Verify.waitForObject(bi.youHaveNoScheduledReminders, 1);
		if(Verify.objExists(bi.youHaveNoScheduledReminders))
			Commentary.log(LogStatus.INFO, "PASS: Correct message is displayed when there are no Scheduled Reminders.");
		else 
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Correct message is NOT displayed when there are no Scheduled Reminders.");	

		sa.assertAll();
	}

	@Test (priority = 3, enabled = true)
	public void ST3_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating by adding a New Income Reminder Series of \"Twice a Month\" frequency, Verification on View Series page with Tags & Notes field and Deleting the Reminder Series.");

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
		tRec.setFrequency("Twice a month");
		tRec.setFirstOn("8");
		tRec.setSecondOn("24");
		tRec.setCategory("Car Wash");
		tRec.setEndOnDate(h.getFutureDaysDate(30));
		tRec.setTags("test tag");
		tRec.setNote("Testing Notes.");

		bi.addNewReminder(tRec);

		bi.tapOnSeriesTab();

		if(Verify.objExists(bi.reminderName))
			Commentary.log(LogStatus.INFO, "PASS: New Income Reminder Series has been successfully created.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: New Income Reminder Series has not been created.");

		bi.tapOnReminderSeries();

		String reminderSeriesNameOnViewSeries = bi.reminderSeriesNameOnViewSeries.getText();
		String reminderSeriesFrequencyOnViewSeries = bi.reminderSeriesFrequencyOnViewSeries.getText();
		String typeViewSeriesPage = bi.reminderTypeViewSeriesPage.getText();
		String categoryValueViewSeriesPage = bi.categoryTypeViewSeriesPage.getText();
		//	String endAfterViewSeriesPage = bi.endDateViewSeriesPage.getText();
		String toAccountViewSeriesPage = bi.toAccountViewSeriesPage.getText();

		if(reminderSeriesNameOnViewSeries.equals(tRec.getPayee())) 
			Commentary.log(LogStatus.INFO, "PASS: Reminder Name is matched successfully with the Reminder name on View Series Page.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Reminder Name is NOT matched with the Reminder name on View Series Page.");

		if(reminderSeriesFrequencyOnViewSeries.equalsIgnoreCase(tRec.getFrequency()))
			Commentary.log(LogStatus.INFO, "PASS: 'Frequency' field value on View Series Page is same as the set Frequency field.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Frequency' field value on View Series Page is NOT same as the set Frequency field.");

		if(typeViewSeriesPage.equals("Income")) 
			Commentary.log(LogStatus.INFO, "PASS: Reminder type is Income which is expected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Reminder is NOT of Income type.");

		if(categoryValueViewSeriesPage.equals(tRec.getCategory())) 
			Commentary.log(LogStatus.INFO, "PASS: 'Category' field value on View Series Page is same as the entered Category field.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Category' field value on View Series Page is not same as the entered Category field.");

		//	if(substringEndAfterViewSeriesPage.equals(tRec.getEndAfterNumberOfReminders())) 
		//		Commentary.log(LogStatus.INFO, "PASS: 'End After' field value on View Series Page is same as the entered End After No. of Reminders field.");
		//	else
		//		Commentary.log(sa, LogStatus.FAIL, "FAIL: 'End After' field value on View Series Page is not same as the entered End After No. of Reminders field.");

		if(toAccountViewSeriesPage.equals(tRec.getToAccount())) 
			Commentary.log(LogStatus.INFO, "PASS: 'To Account' Name on View Series Page is same as the entered 'To Account' name.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'To Account' Name on View Series Page is not same as the entered 'To Account' name.");

		if(Verify.objExists(bi.tagsViewSeriesPage))
			Commentary.log(LogStatus.INFO, "PASS: 'Tags' field is displayed as expected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Tags' field is NOT displayed and is not expected.");

		if(Verify.objExists(bi.notesViewSeriesPage))
			Commentary.log(LogStatus.INFO, "PASS: 'Note' field is displayed as expected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Note' field is NOT displayed and is not expected.");

		bi.editButton.click();
		bi.tapOnDeleteSeries();

		Verify.waitForObject(bi.deleteReminderSeriesWarningMessage, 1);
		if(Verify.objExists(bi.deleteReminderSeriesWarningMessage))
			Commentary.log(LogStatus.INFO, "PASS: Delete confirmation alert message is displayed before deleting Income Reminder Series.");
		else 
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Delete confirmation alert messsage is NOT displayed before deleting Income Reminder Series.");

		Verify.waitForObject(bi.deleteReminderSeriesAlertButton, 1);
		bi.deleteReminderSeriesAlertButton.click();
		Thread.sleep(2000);

		Verify.waitForObject(bi.youHaveNoScheduledReminders, 1);
		if(Verify.objExists(bi.youHaveNoScheduledReminders))
			Commentary.log(LogStatus.INFO, "PASS: Correct message is displayed when there are no Scheduled Reminders.");
		else 
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Correct message is NOT displayed when there are no Scheduled Reminders.");	

		sa.assertAll();
	}

	@Test (priority = 4, enabled = true)
	public void ST4_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating by adding a New Transfer Reminder Series of \"Monthly\" frequency, Verification on View Series page and Deleting the Reminder Series.");

		OverviewPage op = new OverviewPage();
		op.tapOnBillsAndIncomeCard();

		BillsAndIncomePage bi = new BillsAndIncomePage();
		bi.deleteAlreadyPresentReminderSeries();

		bi.addNewReminderSeries("Transfer");

		String reminderName = "Reminder_"+h.getCurrentTime();

		TransactionRecord tRec = new TransactionRecord();
		tRec.setPayee(reminderName);
		tRec.setAmount("20.00");
		tRec.setFromAccount(sManualChecking);
		tRec.setToAccount(sManualSaving);
		tRec.setFrequency("Monthly");
		tRec.setEvery("2");
		tRec.setDay("16");
		tRec.setEndAfterNumberOfReminders("5");

		bi.addNewReminder(tRec);
		Thread.sleep(5000);
		
		bi.tapOnSeriesTab();

		if(Verify.objExists(bi.reminderName))
			Commentary.log(LogStatus.INFO, "PASS: New Transfer Reminder Series has been successfully created.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: New Transfer Reminder Series has not been created.");

		bi.tapOnReminderSeries();

		String reminderSeriesNameOnViewSeries = bi.reminderSeriesNameOnViewSeries.getText();
		String reminderSeriesFrequencyOnViewSeries = bi.reminderSeriesFrequencyOnViewSeries.getText();
		String substringFrequencyValue = reminderSeriesFrequencyOnViewSeries.substring(6,7);
		String typeViewSeriesPage = bi.reminderTypeViewSeriesPage.getText();
		String endAfterViewSeriesPage = bi.endAfterViewSeriesPage.getText();
		String substringEndAfterViewSeriesPage = endAfterViewSeriesPage.substring(0, 1);
		String toAccountViewSeriesPage = bi.toAccountViewSeriesPage.getText();
		String fromAccountViewSeriesPage = bi.fromAccountViewSeriesPage.getText();

		if(reminderSeriesNameOnViewSeries.equals(tRec.getPayee())) 
			Commentary.log(LogStatus.INFO, "PASS: Reminder Name is matched successfully with the Reminder name on View Series Page.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Reminder Name is NOT matched with the Reminder name on View Series Page.");

		if(substringFrequencyValue.equals(tRec.getEvery())) 
			Commentary.log(LogStatus.INFO, "PASS: 'Frequency' field value on View Series Page is same as the set Frequency field.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Frequency' field value on View Series Page is NOT same as the set Frequency field.");

		if(typeViewSeriesPage.equals("Transfer")) 
			Commentary.log(LogStatus.INFO, "PASS: Reminder type is Transfer which is expected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Reminder is NOT of Transfer type.");

		if(substringEndAfterViewSeriesPage.equals(tRec.getEndAfterNumberOfReminders())) 
			Commentary.log(LogStatus.INFO, "PASS: 'End After' field value on View Series Page is same as the entered End After No. of Reminders field.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'End After' field value on View Series Page is not same as the entered End After No. of Reminders field.");

		if(toAccountViewSeriesPage.equals(tRec.getToAccount())) 
			Commentary.log(LogStatus.INFO, "PASS: 'To Account' Name on View Series Page is same as the entered 'To Account' name.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'To Account' Name on View Series Page is not same as the entered 'To Account' name.");

		if(fromAccountViewSeriesPage.equals(tRec.getFromAccount())) 
			Commentary.log(LogStatus.INFO, "PASS: 'From Account' Name on View Series Page is same as the entered 'From Account' name.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'From Account' Name on View Series Page is not same as the entered 'From Account' name.");

		if(!Verify.objExists(bi.tagsViewSeriesPage)) 
			Commentary.log(LogStatus.INFO, "PASS: 'Tags' is not displayed as expected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Tags' field is displayed and is not expected.");

		if(!Verify.objExists(bi.notesViewSeriesPage)) 
			Commentary.log(LogStatus.INFO, "PASS: 'Note' is not displayed as expected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Note' field is displayed and is not expected.");

		bi.editButton.click();
		bi.tapOnDeleteSeries();

		Verify.waitForObject(bi.deleteReminderSeriesWarningMessage, 1);
		if(Verify.objExists(bi.deleteReminderSeriesWarningMessage)) 
			Commentary.log(LogStatus.INFO, "PASS: Delete confirmation alert message is displayed before deleting Transfer Reminder Series.");
		else 
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Delete confirmation alert messsage is NOT displayed before deleting Transfer Reminder Series.");

		Verify.waitForObject(bi.deleteReminderSeriesAlertButton, 1);
		bi.deleteReminderSeriesAlertButton.click();
		Thread.sleep(2000);

		Verify.waitForObject(bi.youHaveNoScheduledReminders, 1);
		if(Verify.objExists(bi.youHaveNoScheduledReminders))
			Commentary.log(LogStatus.INFO, "PASS: Correct message is displayed when there are no Scheduled Reminders.");
		else 
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Correct message is NOT displayed when there are no Scheduled Reminders.");	

		sa.assertAll();
	}

	@Test (priority = 5, enabled = true)
	public void ST5_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating by adding a New Bill Reminder Series with \"Bi-Weekly\" frequency and Verification on View Series page.");

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
		tRec.setFrequency("Bi-weekly");
		tRec.setEvery2WeeksOn("Friday");
		tRec.setCategory("Entertainment");
		tRec.setEndAfterNumberOfReminders("3");

		bi.addNewReminder(tRec);

		bi.tapOnSeriesTab();

		if(Verify.objExists(bi.reminderName))
			Commentary.log(LogStatus.INFO, "PASS: New Bill Reminder Series with Bi-weekly frequency has been successfully created.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: New Bill Reminder Series has not been created.");

		bi.tapOnReminderSeries();

		String reminderSeriesNameOnViewSeries = bi.reminderSeriesNameOnViewSeries.getText();
		String reminderSeriesFrequencyOnViewSeries = bi.reminderSeriesFrequencyOnViewSeries.getText();
		String typeViewSeriesPage = bi.reminderTypeViewSeriesPage.getText();
		String categoryValueViewSeriesPage = bi.categoryTypeViewSeriesPage.getText();
		String endAfterViewSeriesPage = bi.endAfterViewSeriesPage.getText();
		String substringEndAfterViewSeriesPage = endAfterViewSeriesPage.substring(0, 1);
		String fromAccountViewSeriesPage = bi.fromAccountViewSeriesPage.getText();

		if(reminderSeriesNameOnViewSeries.equals(tRec.getPayee()))
			Commentary.log(LogStatus.INFO, "PASS: Reminder Name is matched successfully with the Reminder name on View Series Page.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Reminder Name is NOT matched with the Reminder name on View Series Page.");

		if(reminderSeriesFrequencyOnViewSeries.equalsIgnoreCase(tRec.getFrequency()))
			Commentary.log(LogStatus.INFO, "PASS: 'Frequency' field value on View Series Page is same as the set Frequency field.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Frequency' field value on View Series Page is NOT same as the set Frequency field.");

		if(typeViewSeriesPage.equals("Bill"))
			Commentary.log(LogStatus.INFO, "PASS: Reminder type is Bill which is expected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Reminder is Not of Bill type.");

		if(categoryValueViewSeriesPage.equals(tRec.getCategory()))
			Commentary.log(LogStatus.INFO, "PASS: 'Category' field value on View Series Page is same as the entered Category field.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Category' field value on View Series Page is not same as the entered Category field.");

		if(substringEndAfterViewSeriesPage.equals(tRec.getEndAfterNumberOfReminders()))
			Commentary.log(LogStatus.INFO, "PASS: 'End After' field value on View Series Page is same as the entered End After No. of Reminders field.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'End After' field value on View Series Page is not same as the entered End After No. of Reminders field.");

		if(fromAccountViewSeriesPage.equals(tRec.getFromAccount())) 
			Commentary.log(LogStatus.INFO, "PASS: 'From Account' Name on View Series Page is same as the entered 'From Account' name.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'From Account' Name on View Series Page is not same as the entered 'From Account' name.");

		if(!Verify.objExists(bi.tagsViewSeriesPage)) 
			Commentary.log(LogStatus.INFO, "PASS: 'Tags' is not displayed as expected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Tags' field is displayed and is not expected.");

		if(!Verify.objExists(bi.notesViewSeriesPage)) 
			Commentary.log(LogStatus.INFO, "PASS: 'Note' is not displayed as expected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Note' field is displayed and is not expected.");

		bi.deleteSeriesFromEditButton();

		sa.assertAll();
	}

	@Test (priority = 6, enabled = true)
	public void ST6_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating by adding a New Bill Reminder Series with \"Quarterly\" frequency and Verification on View Series page.");

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

		bi.tapOnSeriesTab();

		if(Verify.objExists(bi.reminderName))
			Commentary.log(LogStatus.INFO, "PASS: New Bill Reminder Series with Quarterly frequency has been successfully created.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: New Bill Reminder Series has not been created.");

		bi.tapOnReminderSeries();

		String reminderSeriesNameOnViewSeries = bi.reminderSeriesNameOnViewSeries.getText();
		String reminderSeriesFrequencyOnViewSeries = bi.reminderSeriesFrequencyOnViewSeries.getText();
		String substringFrequencyValue = reminderSeriesFrequencyOnViewSeries.substring(6,13);
		String typeViewSeriesPage = bi.reminderTypeViewSeriesPage.getText();
		String categoryValueViewSeriesPage = bi.categoryTypeViewSeriesPage.getText();
		String endDateViewSeriesPage = bi.endDateViewSeriesPage.getText();
		String fromAccountViewSeriesPage = bi.fromAccountViewSeriesPage.getText();

		if(reminderSeriesNameOnViewSeries.equals(tRec.getPayee())) 
			Commentary.log(LogStatus.INFO, "PASS: Reminder Name is matched successfully with the Reminder name on View Series Page.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Reminder Name is NOT matched with the Reminder name on View Series Page.");

		if(substringFrequencyValue.equals("Quarter")) 
			Commentary.log(LogStatus.INFO, "PASS: 'Frequency' field value on View Series Page is same as the set Frequency field.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Frequency' field value on View Series Page is NOT same as the set Frequency field.");

		if(typeViewSeriesPage.equals("Bill")) 
			Commentary.log(LogStatus.INFO, "PASS: Reminder type is Bill which is expected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Reminder is Not of Bill type.");

		if(categoryValueViewSeriesPage.equals(tRec.getCategory())) 
			Commentary.log(LogStatus.INFO, "PASS: 'Category' field value on View Series Page is same as the entered Category field.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Category' field value on View Series Page is not same as the entered Category field.");

		if(endDateViewSeriesPage.equals("No end date")) 
			Commentary.log(LogStatus.INFO, "PASS: 'No end date' is shown on View Series page as End date was not selected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'No end date' is NOT shown on View Series page.");

		if(fromAccountViewSeriesPage.equals(tRec.getFromAccount())) 
			Commentary.log(LogStatus.INFO, "PASS: 'From Account' Name on View Series Page is same as the entered 'From Account' name.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'From Account' Name on View Series Page is not same as the entered 'From Account' name.");

		if(!Verify.objExists(bi.tagsViewSeriesPage)) 
			Commentary.log(LogStatus.INFO, "PASS: 'Tags' is not displayed as expected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Tags' field is displayed and is not expected.");

		if(!Verify.objExists(bi.notesViewSeriesPage)) 
			Commentary.log(LogStatus.INFO, "PASS: 'Note' is not displayed as expected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Note' field is displayed and is not expected.");

		bi.deleteSeriesFromEditButton();

		sa.assertAll();
	}

	@Test (priority = 7, enabled = true)
	public void ST7_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating by adding a New Bill Reminder Series with \"Yearly\" frequency and Verification on View Series page.");

		OverviewPage op = new OverviewPage();
		op.tapOnBillsAndIncomeCard();

		BillsAndIncomePage bi = new BillsAndIncomePage();
		bi.deleteAlreadyPresentReminderSeries();

		bi.addNewReminderSeries("Bill");

		String reminderName = "Reminder_"+h.getCurrentTime();

		TransactionRecord tRec = new TransactionRecord();
		tRec.setPayee(reminderName);
		tRec.setAmount("7.00");
		tRec.setFromAccount(sManualChecking);
		tRec.setFrequency("Yearly");
		tRec.setCategory("Entertainment");

		bi.addNewReminder(tRec);

		bi.tapOnSeriesTab();

		if(Verify.objExists(bi.reminderName))
			Commentary.log(LogStatus.INFO, "PASS: New Bill Reminder Series with Yearly frequency has been successfully created.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: New Bill Reminder Series has not been created.");

		bi.tapOnReminderSeries();

		String reminderSeriesNameOnViewSeries = bi.reminderSeriesNameOnViewSeries.getText();
		String reminderSeriesFrequencyOnViewSeries = bi.reminderSeriesFrequencyOnViewSeries.getText();
		String substringFrequencyValue = reminderSeriesFrequencyOnViewSeries.substring(6);
		String typeViewSeriesPage = bi.reminderTypeViewSeriesPage.getText();
		String categoryValueViewSeriesPage = bi.categoryTypeViewSeriesPage.getText();
		String endDateViewSeriesPage = bi.endDateViewSeriesPage.getText();
		String fromAccountViewSeriesPage = bi.fromAccountViewSeriesPage.getText();

		if(reminderSeriesNameOnViewSeries.equals(tRec.getPayee())) 
			Commentary.log(LogStatus.INFO, "PASS: Reminder Name is matched successfully with the Reminder name on View Series Page.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Reminder Name is NOT matched with the Reminder name on View Series Page.");

		if(substringFrequencyValue.equals("Year")) 
			Commentary.log(LogStatus.INFO, "PASS: 'Frequency' field value on View Series Page is same as the set Frequency field.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Frequency' field value on View Series Page is NOT same as the set Frequency field.");

		if(typeViewSeriesPage.equals("Bill")) 
			Commentary.log(LogStatus.INFO, "PASS: Reminder type is Bill which is expected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Reminder is Not of Bill type.");

		if(categoryValueViewSeriesPage.equals(tRec.getCategory())) 
			Commentary.log(LogStatus.INFO, "PASS: 'Category' field value on View Series Page is same as the entered Category field.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Category' field value on View Series Page is not same as the entered Category field.");

		if(endDateViewSeriesPage.equals("No end date")) 
			Commentary.log(LogStatus.INFO, "PASS: 'No end date' is shown on View Series page as End date was not selected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'No end date' is NOT shown on View Series page.");

		if(fromAccountViewSeriesPage.equals(tRec.getFromAccount())) 
			Commentary.log(LogStatus.INFO, "PASS: 'From Account' Name on View Series Page is same as the entered 'From Account' name.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'From Account' Name on View Series Page is not same as the entered 'From Account' name.");

		if(!Verify.objExists(bi.tagsViewSeriesPage)) 
			Commentary.log(LogStatus.INFO, "PASS: 'Tags' is not displayed as expected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Tags' field is displayed and is not expected.");

		if(!Verify.objExists(bi.notesViewSeriesPage)) 
			Commentary.log(LogStatus.INFO, "PASS: 'Note' is not displayed as expected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Note' field is displayed and is not expected.");

		bi.deleteSeriesFromEditButton();

		sa.assertAll();
	}

	@Test (priority = 8, enabled = true)
	public void ST8_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating by adding a New Bill Reminder Series with \"Twice a year\" frequency and Verification on View Series page.");

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
		tRec.setFrequency("Twice a year");
		tRec.setCategory("Entertainment");

		bi.addNewReminder(tRec);

		bi.tapOnSeriesTab();

		if(Verify.objExists(bi.reminderName))
			Commentary.log(LogStatus.INFO, "PASS: New Bill Reminder Series with 'Twice a year' frequency has been successfully created.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: New Bill Reminder Series has not been created.");

		bi.tapOnReminderSeries();

		String reminderSeriesNameOnViewSeries = bi.reminderSeriesNameOnViewSeries.getText();
		String reminderSeriesFrequencyOnViewSeries = bi.reminderSeriesFrequencyOnViewSeries.getText();
		String typeViewSeriesPage = bi.reminderTypeViewSeriesPage.getText();
		String categoryValueViewSeriesPage = bi.categoryTypeViewSeriesPage.getText();
		String endDateViewSeriesPage = bi.endDateViewSeriesPage.getText();
		String fromAccountViewSeriesPage = bi.fromAccountViewSeriesPage.getText();

		if(reminderSeriesNameOnViewSeries.equals(tRec.getPayee())) 
			Commentary.log(LogStatus.INFO, "PASS: Reminder Name is matched successfully with the Reminder name on View Series Page.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Reminder Name is NOT matched with the Reminder name on View Series Page.");

		if(reminderSeriesFrequencyOnViewSeries.equalsIgnoreCase(tRec.getFrequency())) 
			Commentary.log(LogStatus.INFO, "PASS: 'Frequency' field value on View Series Page is same as the set Frequency field.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Frequency' field value on View Series Page is NOT same as the set Frequency field.");

		if(typeViewSeriesPage.equals("Bill")) 
			Commentary.log(LogStatus.INFO, "PASS: Reminder type is Bill which is expected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Reminder is Not of Bill type.");

		if(categoryValueViewSeriesPage.equals(tRec.getCategory())) 
			Commentary.log(LogStatus.INFO, "PASS: 'Category' field value on View Series Page is same as the entered Category field.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Category' field value on View Series Page is not same as the entered Category field.");

		if(endDateViewSeriesPage.equals("No end date")) 
			Commentary.log(LogStatus.INFO, "PASS: 'No end date' is shown on View Series page as End date was not selected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'No end date' is NOT shown on View Series page.");

		if(fromAccountViewSeriesPage.equals(tRec.getFromAccount())) 
			Commentary.log(LogStatus.INFO, "PASS: 'From Account' Name on View Series Page is same as the entered 'From Account' name.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'From Account' Name on View Series Page is not same as the entered 'From Account' name.");

		if(!Verify.objExists(bi.tagsViewSeriesPage)) 
			Commentary.log(LogStatus.INFO, "PASS: 'Tags' is not displayed as expected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Tags' field is displayed and is not expected.");

		if(!Verify.objExists(bi.notesViewSeriesPage)) 
			Commentary.log(LogStatus.INFO, "PASS: 'Note' is not displayed as expected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Note' field is displayed and is not expected.");

		bi.deleteSeriesFromEditButton();

		sa.assertAll();
	}

	@Test (priority = 9, enabled = true)
	public void ST9_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating by adding a New Bill Reminder Series with \"Only Once\" frequency and Verification on View Series page.");

		OverviewPage op = new OverviewPage();
		op.tapOnBillsAndIncomeCard();

		BillsAndIncomePage bi = new BillsAndIncomePage();
		bi.deleteAlreadyPresentReminderSeries();

		bi.addNewReminderSeries("Bill");

		String reminderName = "Reminder_"+h.getCurrentTime();

		TransactionRecord tRec = new TransactionRecord();
		tRec.setPayee(reminderName);
		tRec.setAmount("3.00");
		tRec.setFromAccount(sManualChecking);
		tRec.setFrequency("Only once");
		tRec.setCategory("Entertainment");

		bi.addNewReminder(tRec);

		bi.tapOnSeriesTab();

		if(Verify.objExists(bi.reminderName))
			Commentary.log(LogStatus.INFO, "PASS: New Bill Reminder Series with 'Only Once' frequency has been successfully created.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: New Bill Reminder Series has not been created.");

		bi.tapOnReminderSeries();

		String reminderSeriesNameOnViewSeries = bi.reminderSeriesNameOnViewSeries.getText();
		String reminderSeriesFrequencyOnViewSeries = bi.reminderSeriesFrequencyOnViewSeries.getText();
		String typeViewSeriesPage = bi.reminderTypeViewSeriesPage.getText();
		String categoryValueViewSeriesPage = bi.categoryTypeViewSeriesPage.getText();
		String fromAccountViewSeriesPage = bi.fromAccountViewSeriesPage.getText();

		if(reminderSeriesNameOnViewSeries.equals(tRec.getPayee())) 
			Commentary.log(LogStatus.INFO, "PASS: Reminder Name is matched successfully with the Reminder name on View Series Page.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Reminder Name is NOT matched with the Reminder name on View Series Page.");

		if(reminderSeriesFrequencyOnViewSeries.equals("Once")) 
			Commentary.log(LogStatus.INFO, "PASS: 'Frequency' field value on View Series Page is same as the set Frequency field.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Frequency' field value on View Series Page is NOT same as the set Frequency field.");

		if(typeViewSeriesPage.equals("Bill")) 
			Commentary.log(LogStatus.INFO, "PASS: Reminder type is Bill which is expected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Reminder is Not of Bill type.");

		if(categoryValueViewSeriesPage.equals(tRec.getCategory())) 
			Commentary.log(LogStatus.INFO, "PASS: 'Category' field value on View Series Page is same as the entered Category field.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Category' field value on View Series Page is not same as the entered Category field.");

		if(!Verify.objExists(bi.endDateViewSeriesPage)) 
			Commentary.log(LogStatus.INFO, "PASS: 'End Date' is not displayed as expected for Only Once frequency.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'End Date' is displayed and is not expected on View Series page.");

		if(fromAccountViewSeriesPage.equals(tRec.getFromAccount())) 
			Commentary.log(LogStatus.INFO, "PASS: 'From Account' Name on View Series Page is same as the entered 'From Account' name.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'From Account' Name on View Series Page is not same as the entered 'From Account' name.");

		if(!Verify.objExists(bi.tagsViewSeriesPage)) 
			Commentary.log(LogStatus.INFO, "PASS: 'Tags' is not displayed as expected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Tags' field is displayed and is not expected.");

		if(!Verify.objExists(bi.notesViewSeriesPage)) 
			Commentary.log(LogStatus.INFO, "PASS: 'Note' is not displayed as expected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Note' field is displayed and is not expected.");

		bi.deleteSeriesFromEditButton();

		sa.assertAll();
	}

	@Test (priority = 10, enabled = true)
	public void ST10_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating by adding a New Bill Reminder Series with \"To Pay Estimated Taxes\" frequency and Verification on View Series page.");

		OverviewPage op = new OverviewPage();
		op.tapOnBillsAndIncomeCard();

		BillsAndIncomePage bi = new BillsAndIncomePage();
		bi.deleteAlreadyPresentReminderSeries();

		bi.addNewReminderSeries("Bill");

		String reminderName = "Reminder_"+h.getCurrentTime();

		TransactionRecord tRec = new TransactionRecord();
		tRec.setPayee(reminderName);
		tRec.setAmount("15.00");
		tRec.setFromAccount(sManualChecking);
		tRec.setFrequency("To Pay Estimated Taxes");
		tRec.setCategory("Tax");

		bi.addNewReminder(tRec);

		bi.tapOnSeriesTab();

		if(Verify.objExists(bi.reminderName))
			Commentary.log(LogStatus.INFO, "PASS: New Bill Reminder Series with 'To Pay Estimated Taxes' frequency has been successfully created.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: New Bill Reminder Series has not been created.");

		bi.tapOnReminderSeries();

		String reminderSeriesNameOnViewSeries = bi.reminderSeriesNameOnViewSeries.getText();
		String reminderSeriesFrequencyOnViewSeries = bi.reminderSeriesFrequencyOnViewSeries.getText();
		String typeViewSeriesPage = bi.reminderTypeViewSeriesPage.getText();
		String categoryValueViewSeriesPage = bi.categoryTypeViewSeriesPage.getText();
		String endDateViewSeriesPage = bi.endDateViewSeriesPage.getText();
		String fromAccountViewSeriesPage = bi.fromAccountViewSeriesPage.getText();

		if(reminderSeriesNameOnViewSeries.equals(tRec.getPayee())) 
			Commentary.log(LogStatus.INFO, "PASS: Reminder Name is matched successfully with the Reminder name on View Series Page.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Reminder Name is NOT matched with the Reminder name on View Series Page.");

		if(reminderSeriesFrequencyOnViewSeries.equals(tRec.getFrequency())) 
			Commentary.log(LogStatus.INFO, "PASS: 'Frequency' field value on View Series Page is same as the set Frequency field.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Frequency' field value on View Series Page is NOT same as the set Frequency field.");

		if(typeViewSeriesPage.equals("Bill")) 
			Commentary.log(LogStatus.INFO, "PASS: Reminder type is Bill which is expected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Reminder is Not of Bill type.");

		if(categoryValueViewSeriesPage.equals(tRec.getCategory())) 
			Commentary.log(LogStatus.INFO, "PASS: 'Category' field value on View Series Page is same as the entered Category field.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Category' field value on View Series Page is not same as the entered Category field.");

		if(endDateViewSeriesPage.equals("No end date")) 
			Commentary.log(LogStatus.INFO, "PASS: 'No end date' is shown on View Series page as End date was not selected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'No end date' is NOT shown on View Series page.");

		if(fromAccountViewSeriesPage.equals(tRec.getFromAccount())) 
			Commentary.log(LogStatus.INFO, "PASS: 'From Account' Name on View Series Page is same as the entered 'From Account' name.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'From Account' Name on View Series Page is not same as the entered 'From Account' name.");

		if(!Verify.objExists(bi.tagsViewSeriesPage)) 
			Commentary.log(LogStatus.INFO, "PASS: 'Tags' is not displayed as expected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Tags' field is displayed and is not expected.");

		if(!Verify.objExists(bi.notesViewSeriesPage)) 
			Commentary.log(LogStatus.INFO, "PASS: 'Note' is not displayed as expected.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Note' field is displayed and is not expected.");

		bi.deleteSeriesFromEditButton();

		sa.assertAll();
	}

	@Test (priority = 11, enabled = true)
	public void ST11_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating the confirmation pop up message on trying to abort the add new reminder series operation.");

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

		if (Verify.objExists(bi.addNewReminderHeaderText) || Verify.objExists(bi.buttonAddReminder)) {
			Commentary.log(LogStatus.INFO,"Add New Reminder Screen got displayed.");
			Thread.sleep(2000);

			if (tRec.getPayee() != null)
				bi.selectPayee(tRec.getPayee());

			if (tRec.getAmount() != null) 
				bi.enterAmount(tRec.getAmount());

			if (tRec.getFromAccount() != null)
				bi.selectFromAccount(tRec.getFromAccount());
		}

		Verify.waitForObject(bi.backButtonOnAddReminderPage, 1);
		bi.backButtonOnAddReminderPage.click();
		Thread.sleep(1000);

		Verify.waitForObject(bi.abortReminderSeriesWarningMessage, 1);
		if (Verify.objExists(bi.abortReminderSeriesWarningMessage))
			Commentary.log(LogStatus.INFO, "PASS: Abort confirmation alert message is displayed on trying to abort the creation of new Reminder Series.");
		else 
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Abort confirmation alert messsage is NOT displayed on trying to abort the creation of new Reminder Series.");

		sa.assertAll();
	}
}
