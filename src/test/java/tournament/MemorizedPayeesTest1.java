package tournament;

import java.util.HashMap;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.BankingAndCreditCardPage;
import dugout.CreateMemorizedPayeeDetailPage;
import dugout.MemorizedPayeesPage;
import dugout.OverviewPage;
import dugout.SettingsPage;
import dugout.SignInPage;
import dugout.TransactionDetailPage;
import dugout.TransactionsPage;
import dugout.WelcomePage;
import referee.Commentary;
import referee.Verify;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;
import support.UserName;

public class MemorizedPayeesTest1 extends Recovery {

	String sPassword_stage = "Quicken@01";
	String sDataset_stage = "MemorizedPayeesTest_Stage";
	String manualChecking = "Checking";
	String manualSaving = "Savings";

	public String getUsername_basedOnEnv() throws Exception {

		UserName un = new UserName();
		un.stage_ios = "companionregression++@stage.com";
		un.stage_android = "memorizedpayees_android++@stage.com";
		return un.getUserName();
	}

	@Test(priority = 1, enabled = true)
	public void MP1_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		String sUsername = getUsername_basedOnEnv();
		WelcomePage w = new WelcomePage();
		w.setEnvironment(h.getEnv());

		SignInPage si = new SignInPage();
		if(h.getEnv().contentEquals("stage"))
			si.signIn(sUsername, sPassword_stage, sDataset_stage);

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: C955075/76: Verify the zero data state for 'Memorized Payees'.");

		OverviewPage op = new OverviewPage();
		op.navigateToMemorizedPayees();

		MemorizedPayeesPage mp = new MemorizedPayeesPage();
		mp.deleteAlreadyPresentMemorizedPayees();

		Verify.waitForObject(mp.memorizedPayeesHeader, 1);
		if (Verify.objExists(mp.memorizedPayeesHeader))
			Commentary.log(LogStatus.INFO, "PASS: Memorized Payees Header text is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Memorized Payees Header text is not displayed.");

		if (Verify.objExists(mp.backButtonOnMemorizedPayeesPage))
			Commentary.log(LogStatus.INFO, "PASS: Back button is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Back button is not displayed.");

		if(Verify.objExists(mp.noMemorizedPayeeRulesText))
			Commentary.log(LogStatus.INFO, "PASS: \"No Memorized Payee Rules added yet text\" is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"No Memorized Payee Rules added yet text\" is NOT displayed.");

		if(Verify.objExists(mp.addMemorizedRuleButton))
			Commentary.log(LogStatus.INFO, "PASS: Add Memorized Payee button is displayed.");
		else
			Commentary.log(sa,  LogStatus.FAIL, "FAIL: Add Memorized Payee button is NOT displayed.");

		sa.assertAll();	
	}

	@Test(priority = 2, enabled = true)
	public void MP2_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: C955078: Verify that 'Transaction Type' should list both 'Payment and Deposit'.");

		OverviewPage op = new OverviewPage();
		op.navigateToMemorizedPayees();
		
		MemorizedPayeesPage mp = new MemorizedPayeesPage();
		mp.deleteAlreadyPresentMemorizedPayees();

		mp.addMemorizedRuleButton.click();
		
		CreateMemorizedPayeeDetailPage cmp = new CreateMemorizedPayeeDetailPage();
		Verify.waitForObject(cmp.type, 1);
		cmp.type.click();
		Thread.sleep(2000);
		
		if(Verify.objExists(cmp.deposit))
			Commentary.log(LogStatus.INFO, "PASS: Deposit type option is displayed while creating a new Memorized Payee.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Deposit type option is NOT displayed while creating a new Memorized Payee.");

		if(Verify.objExists(cmp.payment))
			Commentary.log(LogStatus.INFO, "PASS: Payment type option is displayed while creating a new Memorized Payee.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Payment type option is NOT displayed while creating a new Memorized Payee.");
		
		sa.assertAll();
	}

	@Test(priority = 3, enabled = true)
	public void MP3_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: C955077: Verify that a memorized payee can be added successfully with just the payee name");

		OverviewPage op = new OverviewPage();
		op.navigateToMemorizedPayees();

		MemorizedPayeesPage mp = new MemorizedPayeesPage();
		mp.deleteAlreadyPresentMemorizedPayees();

		mp.addMemorizedRuleButton.click();

		String payeeName = "Payee_"+h.getCurrentTime();
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setPayee(payeeName);

		CreateMemorizedPayeeDetailPage cmp = new CreateMemorizedPayeeDetailPage();
		cmp.createMemorizedPayee(tRec);
		
		Verify.waitForObject(mp.firstMemorizedPayeeCategoryName, 1);
		String categoryName = mp.firstMemorizedPayeeCategoryName.getText();
		
		if(categoryName.equals("Uncategorized"))
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Uncategorized is set as the category when a Memorized payee is created with Payee name only.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Uncategorized is not seen.");
		
		mp.backButtonOnMemorizedPayeesPage.click();
		Thread.sleep(1000);
		
		SettingsPage sp = new SettingsPage();
		sp.backButtonOnSettingsHeader.click();
		Thread.sleep(1000);
		
		op.addTransaction.click();
		Thread.sleep(1000);
		
		Verify.waitForObject(cmp.buttonDone, 1);
		cmp.enterPayeeNameOnAddTransactionPage(payeeName);
		
		String categoryText = cmp.enteredCategory.getText();
		
		if(categoryText.equals(categoryName))
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Uncategorized is set as the category on Add transaction page after entering Memorized payee.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Uncategorized is not set as the category on Add transaction page after entering Memorized payee.");
		
		cmp.backButtonOnAddTransactionPage.click();
		Thread.sleep(1000);
		
		op.navigateToMemorizedPayees();
		
		mp.firstMemorizedPayeeName.click();
		
		mp.deleteMemorizedPayee();
		
		sa.assertAll();
	}
	
	@Test(priority = 4, enabled = true)
	public void MP4_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: C955079: Verify the details on add transaction form for a Memorized Payee of Payment Type.");

		OverviewPage op = new OverviewPage();
		op.navigateToMemorizedPayees();

		MemorizedPayeesPage mp = new MemorizedPayeesPage();
		mp.deleteAlreadyPresentMemorizedPayees();
		
		mp.addMemorizedRuleButton.click();

		String payeeName = "Payee_"+h.getCurrentTime();

		TransactionRecord tRec = new TransactionRecord();
		tRec.setPayee(payeeName);
		tRec.setTransactionType("Payment");
		tRec.setCategory("Electronics & Software");
		tRec.setTags("Office Use");
		tRec.setNote("Printer Purchase");
		tRec.setAmount("50.00");

		CreateMemorizedPayeeDetailPage cmp = new CreateMemorizedPayeeDetailPage();
		cmp.createMemorizedPayee(tRec);
		
		mp.backButtonOnMemorizedPayeesPage.click();
		Thread.sleep(1000);
		
		SettingsPage sp = new SettingsPage();
		sp.backButtonOnSettingsHeader.click();
		Thread.sleep(1000);
		
		op.addTransaction.click();
		Thread.sleep(1000);
		
		Verify.waitForObject(cmp.buttonDone, 1);
		cmp.enterPayeeNameOnAddTransactionPage(payeeName);
		
		String categoryText = cmp.enteredCategory.getText();
		String amountValue = cmp.enteredAmount.getText().replaceAll("[^0-9.-]", "");
		String processedAmountValue = amountValue.replaceAll("-", "");
		String notesText = cmp.enteredNotes.getText();
		String tagsText = cmp.enteredTags.getText();
		
		if(categoryText.equals(tRec.getCategory()))
			Commentary.log(LogStatus.INFO, "PASS: As Expected, \"Electronics & Software\" is set as the category on Add transaction page after entering Memorized payee.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Electronics & Software\" is Not set as the category on Add transaction page after entering Memorized payee.");

		if(processedAmountValue.equals(tRec.getAmount()))
			Commentary.log(LogStatus.INFO, "PASS: Same amount is set on the the Add transaction page as per the amount of the Memorized Payee.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Same amount is NOT set on the the Add transaction page as per the amount of the Memorized Payee.");
		
		if(amountValue.startsWith("-"))
			Commentary.log(LogStatus.INFO, "PASS: Expense amount is set on the the Add transaction page when \"Payment\" type is chosen on the Memorized Payee.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Expense amount is NOT set on the the Add transaction page when \"Payment\" type is chosen on the Memorized Payee.");
		
		if(notesText.equals(tRec.getNote()))
			Commentary.log(LogStatus.INFO, "PASS: Same Notes are set on the the Add transaction page as per the Memo of the Memorized Payee.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Same Notes are NOT set on the the Add transaction page as per the Memo of the Memorized Payee.");
		
		if(tagsText.equals(tRec.getTags()))
			Commentary.log(LogStatus.INFO, "PASS: Same Tag is set on the the Add transaction page as per the Tags of the Memorized Payee.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Same Tag is NOT set on the the Add transaction page as per the Tags of the Memorized Payee.");
		
		cmp.backButtonOnAddTransactionPage.click();
		Thread.sleep(1000);
		
		op.navigateToMemorizedPayees();
		
		mp.firstMemorizedPayeeName.click();
		
		mp.deleteMemorizedPayee();
		
		sa.assertAll();
	}

	@Test(priority = 5, enabled = true)
	public void MP5_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: C955124: Verify the details on add transaction form for a Memorized Payee of Deposit Type.");

		OverviewPage op = new OverviewPage();
		op.navigateToMemorizedPayees();

		MemorizedPayeesPage mp = new MemorizedPayeesPage();
		mp.deleteAlreadyPresentMemorizedPayees();
		
		mp.addMemorizedRuleButton.click();

		String payeeName = "Payee_"+h.getCurrentTime();

		TransactionRecord tRec = new TransactionRecord();
		tRec.setPayee(payeeName);
		tRec.setTransactionType("Deposit");
		tRec.setCategory("Div Income");
		tRec.setTags("Test Tag");
		tRec.setNote("Test Note");
		tRec.setAmount("350.00");

		CreateMemorizedPayeeDetailPage cmp = new CreateMemorizedPayeeDetailPage();
		cmp.createMemorizedPayee(tRec);
		
		mp.backButtonOnMemorizedPayeesPage.click();
		Thread.sleep(1000);
		
		SettingsPage sp = new SettingsPage();
		sp.backButtonOnSettingsHeader.click();
		Thread.sleep(1000);
		
		op.addTransaction.click();
		Thread.sleep(1000);
		
		Verify.waitForObject(cmp.buttonDone, 1);
		cmp.enterPayeeNameOnAddTransactionPage(payeeName);
		
		String categoryText = cmp.enteredCategory.getText();
		String amountValue = cmp.enteredAmount.getText();
		String processedAmountValue = amountValue.replaceAll("[^0-9.-]", "");
		String notesText = cmp.enteredNotes.getText();
		String tagsText = cmp.enteredTags.getText();
		
		if(categoryText.equals(tRec.getCategory()))
			Commentary.log(LogStatus.INFO, "PASS: As Expected, \"Div Income\" is set as the category on Add transaction page after entering Memorized payee.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Div Income\" is Not set as the category on Add transaction page after entering Memorized payee.");

		if(processedAmountValue.equals(tRec.getAmount()))
			Commentary.log(LogStatus.INFO, "PASS: Same amount is set on the the Add transaction page as per the amount of the Memorized Payee.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Same amount is NOT set on the the Add transaction page as per the amount of the Memorized Payee.");
		
		if(! processedAmountValue.startsWith("-"))
			Commentary.log(LogStatus.INFO, "PASS: Income amount is set on the the Add transaction page when \"Deposit\" type is chosen on the Memorized Payee.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Income amount is NOT set on the the Add transaction page when \"Deposit\" type is chosen on the Memorized Payee.");
		
		if(notesText.equals(tRec.getNote()))
			Commentary.log(LogStatus.INFO, "PASS: Same Notes are set on the the Add transaction page as per the Memo of the Memorized Payee.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Same Notes are NOT set on the the Add transaction page as per the Memo of the Memorized Payee.");
		
		if(tagsText.equals(tRec.getTags()))
			Commentary.log(LogStatus.INFO, "PASS: Same Tag is set on the the Add transaction page as per the Tags of the Memorized Payee.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Same Tag is NOT set on the the Add transaction page as per the Tags of the Memorized Payee.");
		
		cmp.backButtonOnAddTransactionPage.click();
		Thread.sleep(1000);
		
		op.navigateToMemorizedPayees();
		
		mp.firstMemorizedPayeeName.click();
		
		mp.deleteMemorizedPayee();
		
		sa.assertAll();
	}
	
	@Test(priority = 6, enabled = true)
	public void MP6_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: C955949: Verify that the details are auto filled in the add transaction form on selecting the memorized payee with splits.");

		OverviewPage op = new OverviewPage();
		op.navigateToMemorizedPayees();

		MemorizedPayeesPage mp = new MemorizedPayeesPage();
		mp.deleteAlreadyPresentMemorizedPayees();
		
		mp.addMemorizedRuleButton.click();
		
		String payeeName = "Payee_"+h.getCurrentTime();

		HashMap<Integer,String> amount = new HashMap<Integer, String>();
		HashMap<Integer,String> cat = new HashMap<Integer, String>();
		HashMap<Integer,String[]> tags = new HashMap<Integer, String[]>();
		
		cat.put(1, "Education");
		cat.put(2, "Books & Supplies");
		amount.put(1, "50.00");
		amount.put(2, "30.00");
		tags.put(1, new String[] {"Tuitions"});
		tags.put(2, new String[] {"Stationery"});
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setPayee(payeeName);
		tRec.setTransactionType("Payment");
		tRec.setNote("Education Expenses");
		tRec.setAmount("80.00");

		CreateMemorizedPayeeDetailPage cmp = new CreateMemorizedPayeeDetailPage();
		cmp.addSplit(tRec, amount, cat, tags);
		Thread.sleep(1000);
		
		mp.backButtonOnMemorizedPayeesPage.click();
		Thread.sleep(1000);
		
		SettingsPage sp = new SettingsPage();
		sp.backButtonOnSettingsHeader.click();
		Thread.sleep(1000);
		
		op.addTransaction.click();
		Thread.sleep(1000);
		
		Verify.waitForObject(cmp.buttonDone, 1);
		cmp.enterPayeeNameOnAddTransactionPage(payeeName);
		
		boolean status = cmp.enabledSplitSwitch.isDisplayed();
		
		if(status)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, \"Split switch\" is enabled on the Add transaction page after entering Memorized payee.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Split switch\" is NOT enabled on the Add transaction page after entering Memorized payee.");
		
		if(mp.verifySplitAmountAndCategory("50.00", "Education"))
			Commentary.log(LogStatus.INFO, "PASS: As Expected, First Split is created with category as \"Education\" of amount \"$50.00\" on the Add transaction page after entering Memorized payee.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Splits is NOT created on the Add transaction page after entering Memorized payee.");
		
		if(mp.verifySplitAmountAndCategory("30.00", "Books & Supplies"))
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Second Split is created with category as \"Books & Supplies\" of amount \"$30.00\" on the Add transaction page after entering Memorized payee.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Splits is NOT created on the Add transaction page after entering Memorized payee.");
		
		cmp.backButtonOnAddTransactionPage.click();
		Thread.sleep(1000);
		
		op.navigateToMemorizedPayees();
		
		mp.firstMemorizedPayeeName.click();
		
		mp.deleteMemorizedPayee();
		
		sa.assertAll();
	}
	
	@Test(priority = 7, enabled = true)
	public void MP7_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: C955081: Verify the 'Mark as Cleared' functionality in memorized payee.");

		OverviewPage op = new OverviewPage();
		op.navigateToMemorizedPayees();

		MemorizedPayeesPage mp = new MemorizedPayeesPage();
		mp.deleteAlreadyPresentMemorizedPayees();
		
		mp.addMemorizedRuleButton.click();
		
		String payeeName = "Payee_"+h.getCurrentTime();

		TransactionRecord tRec = new TransactionRecord();
		tRec.setPayee(payeeName);
		tRec.setTransactionType("Payment");
		tRec.setCategory("Kids");
		tRec.setTags("Toys");
		tRec.setNote("Hamleys");
		tRec.setAmount("40.00");
		tRec.setmarkAsClearedValue("Enable");
		
		CreateMemorizedPayeeDetailPage cmp = new CreateMemorizedPayeeDetailPage();
		cmp.createMemorizedPayee(tRec);
		
		mp.backButtonOnMemorizedPayeesPage.click();
		Thread.sleep(1000);
		
		SettingsPage sp = new SettingsPage();
		sp.backButtonOnSettingsHeader.click();
		Thread.sleep(1000);
		
		op.addTransaction.click();
		Thread.sleep(1000);
		
		Verify.waitForObject(cmp.buttonDone, 1);
		cmp.enterPayeeNameOnAddTransactionPage(payeeName);
		
		TransactionDetailPage td = new TransactionDetailPage();
		td.selectAccount(manualChecking);
		
		Verify.waitForObject(cmp.buttonSave, 1);
		cmp.buttonSave.click();
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 1);
		
		op.navigateToAcctList();

		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		Verify.waitForObject(bcc.allTransactionButton, 1);
		bcc.allTransactionButton.click();
		
		TransactionsPage tp = new TransactionsPage();
		tp.searchRecentTransaction(payeeName);
		tp.tapOnFirstTransaction();
		
		String status = cmp.transactionStatus.getText();
		
		if(status.equals("Cleared"))
			Commentary.log(LogStatus.INFO, "PASS: As Expected, \"Cleared\" is set as the Transaction Status for the added transaction.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Cleared\" is Not set as the Transaction Status for the added transaction .");

		td.backButtonOnViewTransactionPage.click();
		tp.navigateBackToDashboard();
		
		op.navigateToMemorizedPayees();

		mp.firstMemorizedPayeeName.click();

		mp.deleteMemorizedPayee();
		
		sa.assertAll();
	}
	
	@Test(priority = 8, enabled = true)
	public void MP8_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: C955145: Verify the status of the transaction when 'mark as cleared' is turned OFF in the rule.");

		OverviewPage op = new OverviewPage();
		op.navigateToMemorizedPayees();

		MemorizedPayeesPage mp = new MemorizedPayeesPage();
		mp.deleteAlreadyPresentMemorizedPayees();
		
		mp.addMemorizedRuleButton.click();
		
		String payeeName = "Payee_"+h.getCurrentTime();

		TransactionRecord tRec = new TransactionRecord();
		tRec.setPayee(payeeName);
		tRec.setTransactionType("Payment");
		tRec.setCategory("Groceries");
		tRec.setTags("Whole Foods");
		tRec.setNote("Vegetables");
		tRec.setAmount("35.00");
		
		CreateMemorizedPayeeDetailPage cmp = new CreateMemorizedPayeeDetailPage();
		cmp.createMemorizedPayee(tRec);
		
		mp.backButtonOnMemorizedPayeesPage.click();
		Thread.sleep(1000);
		
		SettingsPage sp = new SettingsPage();
		sp.backButtonOnSettingsHeader.click();
		Thread.sleep(1000);
		
		op.addTransaction.click();
		Thread.sleep(1000);
		
		Verify.waitForObject(cmp.buttonDone, 1);
		cmp.enterPayeeNameOnAddTransactionPage(payeeName);
		
		TransactionDetailPage td = new TransactionDetailPage();
		td.selectAccount(manualChecking);
		
		Verify.waitForObject(cmp.buttonSave, 1);
		cmp.buttonSave.click();
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 1);
		
		op.navigateToAcctList();

		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		Verify.waitForObject(bcc.allTransactionButton, 1);
		bcc.allTransactionButton.click();
		
		TransactionsPage tp = new TransactionsPage();
		tp.searchRecentTransaction(payeeName);
		tp.tapOnFirstTransaction();
		
		String status = cmp.transactionStatus.getText();
		
		if(! status.equals("Cleared"))
			Commentary.log(LogStatus.INFO, "PASS: As Expected, \"Uncleared\" is set as the Transaction Status for the added transaction.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Cleared\" is set as the Transaction Status for the added transaction instead of \"Uncleared\".");

		td.backButtonOnViewTransactionPage.click();
		tp.navigateBackToDashboard();
		
		op.navigateToMemorizedPayees();

		mp.firstMemorizedPayeeName.click();

		mp.deleteMemorizedPayee();
		
		sa.assertAll();
	}

}
