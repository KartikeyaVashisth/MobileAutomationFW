package tournament;

import java.util.HashMap;
import java.util.List;

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
import io.appium.java_client.MobileElement;
import referee.Commentary;
import referee.Verify;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;
import support.UserName;

public class MemorizedPayeesTest2 extends Recovery {
	
	String sPassword_stage = "Quicken@01";
	String sDataset_stage = "MemorizedPayeesTest_Stage";
	String manualChecking = "Checking";
	String manualSaving = "Savings";

	public String getUsername_basedOnEnv() throws Exception {

		UserName un = new UserName();
		un.stage_ios = "memorizedpayees2_ios++@stage.com";
		un.stage_android = "memorizedpayees2_android++@stage.com";
		return un.getUserName();
	}

	@Test(priority = 9, enabled = true)
	public void MP9_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		String sUsername = getUsername_basedOnEnv();
		WelcomePage w = new WelcomePage();
		w.setEnvironment(h.getEnv());

		SignInPage si = new SignInPage();
		if(h.getEnv().contentEquals("stage"))
			si.signIn(sUsername, sPassword_stage, sDataset_stage);

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: C955082: Verify the 'Lock payee' turned ON functionality in memorized payee.");

		OverviewPage op = new OverviewPage();
		op.navigateToMemorizedPayees();

		MemorizedPayeesPage mp = new MemorizedPayeesPage();
		mp.deleteAlreadyPresentMemorizedPayees();
		
		mp.addMemorizedRuleButton.click();

		String payeeName = "Payee_"+h.getCurrentTime();

		TransactionRecord tRec = new TransactionRecord();
		tRec.setPayee(payeeName);
		tRec.setCategory("Pharmacy");
		tRec.setTags("Medicines");
		tRec.setNote("Walgreens");
		tRec.setAmount("25.00");
		tRec.setLockPayeeValue("enable");

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
		
		td.enterAmount("45.00");
		td.selectCategory("Shopping");
		td.enterNotes("CVS Pharmacy");
		
		Verify.waitForObject(cmp.buttonSave1, 1);
		if (Verify.objExists(cmp.buttonSave1)) 
			cmp.buttonSave1.click();
		
		tp.navigateBackToDashboard();
		
		op.navigateToMemorizedPayees();

		mp.firstMemorizedPayeeName.click();

		String categoryNameOnViewMemorizedPayee = mp.categoryOnViewMemorizedPayeePage.getText();
		String amountOnViewMemorizedPayee = mp.amountOnViewMemorizedPayeePage.getText().replace("$", "");
		String notesText = mp.memoOnViewMemorizedPayeePage.getText();
		
		if(categoryNameOnViewMemorizedPayee.equals("Pharmacy"))
			Commentary.log(LogStatus.INFO, "PASS: As Expected, \"Pharmacy\" remained as the category in the Memorized Payee.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Pharmacy\" category has been changed to \"Shopping\" in the Memorized Payee.");

		if(amountOnViewMemorizedPayee.equals("25.00"))
			Commentary.log(LogStatus.INFO, "PASS: As Expected, \"25.00\" remained as the Amount in the Memorized Payee.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"25.00\" amount is changed to \"45.00\" Amount in the Memorized Payee.");

		if(notesText.equals("Walgreens"))
			Commentary.log(LogStatus.INFO, "PASS: As Expected, \"Walgreens\" remains as the Notes in the Memorized Payee.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Walgreens\" notes has been changed to \"CVS Pharmacy\".");

		mp.deleteMemorizedPayee();
		
		sa.assertAll();
	}
	
	@Test(priority = 10, enabled = true)
	public void MP10_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: C955083: Verify the 'Lock payee' turned OFF functionality in memorized payee.");

		OverviewPage op = new OverviewPage();
		op.navigateToMemorizedPayees();

		MemorizedPayeesPage mp = new MemorizedPayeesPage();
		mp.deleteAlreadyPresentMemorizedPayees();
		
		mp.addMemorizedRuleButton.click();

		String payeeName = "Payee_"+h.getCurrentTime();

		TransactionRecord tRec = new TransactionRecord();
		tRec.setPayee(payeeName);
		tRec.setCategory("Food & Dining");
		tRec.setTags("Italian Food");
		tRec.setNote("Olive's Kitchen");
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
		
		td.enterAmount("55.00");
		td.selectCategory("Shopping");
		td.enterTransactionType("expense");
		td.enterNotes("Galleria Mall");
		
		Verify.waitForObject(cmp.buttonSave1, 1);
		if (Verify.objExists(cmp.buttonSave1)) 
			cmp.buttonSave1.click();
		
		tp.navigateBackToDashboard();
		
		op.navigateToMemorizedPayees();

		mp.firstMemorizedPayeeName.click();

		String categoryNameOnViewMemorizedPayee = mp.categoryOnViewMemorizedPayeePage.getText();
		String amountOnViewMemorizedPayee = mp.amountOnViewMemorizedPayeePage.getText().replaceAll("[^0-9.-]", "");
		String processedAmountOnViewMemorizedPayee = amountOnViewMemorizedPayee.replace("-", "");
		String notesText = mp.memoOnViewMemorizedPayeePage.getText();
		
		if(categoryNameOnViewMemorizedPayee.equals("Shopping"))
			Commentary.log(LogStatus.INFO, "PASS: As Expected, \"Shopping\" is set as the category in the Memorized Payee now.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Food\" category has been unchanged in the Memorized Payee.");
		
		if(amountOnViewMemorizedPayee.startsWith("-"))
			Commentary.log(LogStatus.INFO, "PASS: As Expected, \"Expense\" is set as the type in the Memorized Payee now.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Income\" type is still unchanged in the Memorized Payee.");

		if(processedAmountOnViewMemorizedPayee.equals("55.00"))
			Commentary.log(LogStatus.INFO, "PASS: As Expected, \"55.00\" is set as the Amount in the Memorized Payee now.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"35.00\" amount is still unchanged Amount in the Memorized Payee.");

		if(notesText.equals("Galleria Mall"))
			Commentary.log(LogStatus.INFO, "PASS: As Expected, \"Galleria Mall\" is set as the Notes in the Memorized Payee now.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Olive's Kitchen\" note has been unchanged in the Memorized Payee.");

		mp.deleteMemorizedPayee();
		
		sa.assertAll();
	}
	
	@Test(priority = 11, enabled = true)
	public void MP11_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: C955948: Verify that the sign of the amount changes when transaction 'type' is changed during add/edit rule.");

		OverviewPage op = new OverviewPage();
		op.navigateToMemorizedPayees();

		MemorizedPayeesPage mp = new MemorizedPayeesPage();
		mp.deleteAlreadyPresentMemorizedPayees();
		
		mp.addMemorizedRuleButton.click();
		
		String payeeName = "Payee_"+h.getCurrentTime();

		TransactionRecord tRec = new TransactionRecord();
		tRec.setPayee(payeeName);
		tRec.setTransactionType("Payment");
		tRec.setAmount("15.00");

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
		
		String amountValue = cmp.enteredAmount.getText().replaceAll("[^0-9.-]", "");
		
		if(amountValue.contains("-"))
			Commentary.log(LogStatus.INFO, "PASS: Expense amount is set on the the Add transaction page when \"Payment\" type is chosen on the Memorized Payee.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Expense amount is NOT set on the the Add transaction page when \"Payment\" type is chosen on the Memorized Payee.");
		
		cmp.backButtonOnAddTransactionPage.click();
		Thread.sleep(1000);
		
		op.navigateToMemorizedPayees();
		
		mp.firstMemorizedPayeeName.click();
		
		cmp.enterTransactionType("Deposit");
		
		Verify.waitForObject(mp.saveButtonOnViewMemorizedPayeePage, 1);
		mp.saveButtonOnViewMemorizedPayeePage.click();
		
		Verify.waitForObject(mp.firstMemorizedPayeeName, 1);
		mp.backButtonOnMemorizedPayeesPage.click();
		
		sp.backButtonOnSettingsHeader.click();
		Thread.sleep(1000);
		
		op.addTransaction.click();
		Thread.sleep(1000);
		
		Verify.waitForObject(cmp.buttonDone, 1);
		cmp.enterPayeeNameOnAddTransactionPage(payeeName);
		
		Verify.waitForObject(cmp.enteredAmount, 1);
		String amountValue_AfterEdit = cmp.enteredAmount.getText().replaceAll("[^0-9.-]", "");
		
		if(! amountValue_AfterEdit.contains("-"))
			Commentary.log(LogStatus.INFO, "PASS: Income amount is set on the the Add transaction page when \"Deposit\" type is chosen on the Memorized Payee.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Income amount is NOT set on the the Add transaction page when \"Deposit\" type is chosen on the Memorized Payee.");
		
		cmp.backButtonOnAddTransactionPage.click();
		Thread.sleep(1000);
		
		op.navigateToMemorizedPayees();
		
		mp.firstMemorizedPayeeName.click();
		
		mp.deleteMemorizedPayee();
		
		sa.assertAll();
	}
	
	@Test(priority = 12, enabled = true)
	public void MP12_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: C955148: Verify that a new memorized rule is created when a transaction is added with new payee.");

		OverviewPage op = new OverviewPage();
		op.navigateToMemorizedPayees();

		MemorizedPayeesPage mp = new MemorizedPayeesPage();
		mp.deleteAlreadyPresentMemorizedPayees();
		
		mp.backButtonOnMemorizedPayeesPage.click();
		Thread.sleep(1000);
		
		SettingsPage sp = new SettingsPage();
		sp.backButtonOnSettingsHeader.click();
		Thread.sleep(1000);
		
		String payeeName = "Payee_"+h.getCurrentTime();
		
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("12.00"); 
		tRec.setAccount(manualChecking);
		tRec.setCategory("Internet");
		tRec.setPayee(payeeName);
		tRec.setTransactionType("expense");
		tRec.setTags("ACT Broadband");
		tRec.setNote("Good Service");
		
		Verify.waitForObject(op.addTransaction, 1);
		op.addTransaction.click();
		Thread.sleep(1000);
		
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec);
		
		Verify.waitForObject(op.addTransaction, 1);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+manualChecking+"], transaction type expense, amount: "+tRec.getAmount());

		op.navigateToMemorizedPayees();
		
		mp.firstMemorizedPayeeName.click();
		
		Verify.waitForObject(mp.deleteMemorizedPayeeButton, 1);
		String memorizedPayeeName = mp.payeeNameOnViewMemorizedPayeePage.getText();
		String type = mp.typeOnViewMemorizedPayeePage.getText();
		String category = mp.categoryOnViewMemorizedPayeePage.getText();
		String amount = mp.amountOnViewMemorizedPayeePage.getText().replaceAll("[^0-9.]", "");
		String tag = mp.tagsOnViewMemorizedPayeePage.getText();
		String memo = mp.memoOnViewMemorizedPayeePage.getText();
		
		if(memorizedPayeeName.contentEquals(payeeName))
			Commentary.log(LogStatus.INFO, "PASS: Memorized rule is created on the fly with the exact same Payee Name as entered on the transaction.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Memorized rule is NOT created on the fly with the exact same Payee Name as entered on the transaction.");
		
		if(type.contentEquals("Payment"))
			Commentary.log(LogStatus.INFO, "PASS: Memorized rule is created of the \"Payment\" type as expense was entered on the transaction.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Memorized rule is NOT created of the \"Payment\" type when expense was entered on the transaction.");
		
		if(category.contentEquals("Internet"))
			Commentary.log(LogStatus.INFO, "PASS: Memorized rule is created with the same Category Name as entered on the transaction.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Memorized rule is NOT created with the same Category Name as entered on the transaction.");
		
		if(amount.contentEquals(tRec.getAmount()))
			Commentary.log(LogStatus.INFO, "PASS: Memorized rule is created with the same Amount as entered on the transaction.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Memorized rule is NOT created with the same Amount as entered on the transaction.");
		
		if(tag.contentEquals(tRec.getTags()))
			Commentary.log(LogStatus.INFO, "PASS: Memorized rule is created with the same Tag as entered on the transaction.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Memorized rule is NOT created with the same Tag as entered on the transaction.");
		
		if(memo.contentEquals(tRec.getNote()))
			Commentary.log(LogStatus.INFO, "PASS: Memorized rule is created with the same Note as entered on the transaction.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Memorized rule is NOT created with the same Note as entered on the transaction.");
		
		mp.deleteMemorizedPayee();
		
		sa.assertAll();
	}
	
	@Test(priority = 13, enabled = true)
	public void MP13_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: C955089: Verify that a new memorized payee rule is created when an existing tranaction tied to a rule is edited for payee name.");
	
		OverviewPage op = new OverviewPage();
		op.navigateToMemorizedPayees();

		MemorizedPayeesPage mp = new MemorizedPayeesPage();
		mp.deleteAlreadyPresentMemorizedPayees();
		
		mp.backButtonOnMemorizedPayeesPage.click();
		Thread.sleep(1000);
		
		SettingsPage sp = new SettingsPage();
		sp.backButtonOnSettingsHeader.click();
		Thread.sleep(1000);
		
		Verify.waitForObject(op.addTransaction, 1);
		op.addTransaction.click();
		Thread.sleep(1000);
		
		String payeeName = "Payee_"+h.getCurrentTime();
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("24.00"); 
		tRec.setAccount(manualChecking);
		tRec.setCategory("Education");
		tRec.setPayee(payeeName);
		tRec.setTransactionType("expense");
		
		TransactionDetailPage td = new TransactionDetailPage();
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec);
		
		Verify.waitForObject(op.addTransaction, 1);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+manualChecking+"], transaction type expense, amount: "+tRec.getAmount());

		op.navigateToAcctList();

		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		Verify.waitForObject(bcc.allTransactionButton, 1);
		bcc.allTransactionButton.click();
		
		TransactionsPage tp = new TransactionsPage();
		tp.searchRecentTransaction(payeeName);
		tp.tapOnFirstTransaction();
		
		String newPayeeName = "Payee_"+h.getCurrentTime();
		
		td.selectPayee(newPayeeName);
		
		CreateMemorizedPayeeDetailPage cmp = new CreateMemorizedPayeeDetailPage();
		Verify.waitForObject(cmp.buttonSave1, 1);
		if (Verify.objExists(cmp.buttonSave1)) 
			cmp.buttonSave1.click();
		
		tp.navigateBackToDashboard();
		
		op.navigateToMemorizedPayees();
		
		List<MobileElement> li = mp.getAllSearchMemorizedPayees();
		Commentary.log(LogStatus.INFO, "No of Memorized Payees appeared in the search..."+li.size());
		int listSize = li.size();
		
		if(listSize==2)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, New memorized rule got created when transaction is edited with the new payee name.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: New memorized rule did NOT got created when transaction is edited with the new payee name.");
		
		mp.deleteAlreadyPresentMemorizedPayees();
		
		sa.assertAll();
	}
	
	@Test(priority = 14, enabled = true)
	public void MP14_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: C955946: Verify that the Memorized Payee Rule is not created when a transaction is added without payee name.");
	
		OverviewPage op = new OverviewPage();
		op.navigateToMemorizedPayees();

		MemorizedPayeesPage mp = new MemorizedPayeesPage();
		mp.deleteAlreadyPresentMemorizedPayees();
		
		mp.backButtonOnMemorizedPayeesPage.click();
		Thread.sleep(1000);
		
		SettingsPage sp = new SettingsPage();
		sp.backButtonOnSettingsHeader.click();
		Thread.sleep(1000);
		
		Verify.waitForObject(op.addTransaction, 1);
		op.addTransaction.click();
		Thread.sleep(1000);
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("15.00"); 
		tRec.setAccount(manualChecking);
		tRec.setCategory("Education");
		tRec.setTransactionType("expense");
		
		TransactionDetailPage td = new TransactionDetailPage();
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec);
		
		Verify.waitForObject(op.addTransaction, 1);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+manualChecking+"], transaction type expense, amount: "+tRec.getAmount());

		op.navigateToMemorizedPayees();
		
		if(mp.noMemorizedPayeeRulesText.isDisplayed())
			Commentary.log(LogStatus.INFO, "PASS: As Expected, No Memorized Payee got created when a transaction is added without a Payee name.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Memorized Payee got created even when a transaction is added without a Payee name.");
		
		sa.assertAll();
	}
	
	@Test(priority = 15, enabled = true)
	public void MP15_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: C955703: Verify that the rule is updated for splits during add/edit transaction.");

		OverviewPage op = new OverviewPage();
		op.navigateToMemorizedPayees();
		
		MemorizedPayeesPage mp = new MemorizedPayeesPage();
		mp.deleteAlreadyPresentMemorizedPayees();
		
		mp.backButtonOnMemorizedPayeesPage.click();
		Thread.sleep(1000);
		
		SettingsPage sp = new SettingsPage();
		sp.backButtonOnSettingsHeader.click();
		Thread.sleep(1000);
		
		Verify.waitForObject(op.addTransaction, 1);
		op.addTransaction.click();
		Thread.sleep(1000);
		
		String payeeName = "Payee_"+h.getCurrentTime();
		
		HashMap<Integer,String> amount = new HashMap<Integer, String>();
		HashMap<Integer,String> cat = new HashMap<Integer, String>();
		HashMap<Integer,String[]> tags = new HashMap<Integer, String[]>();
		
		cat.put(1, "Doctor");
		cat.put(2, "Auto & Transport");
		amount.put(1, "50.00");
		amount.put(2, "30.00");
		tags.put(1, new String[] {"Orthopedics"});
		tags.put(2, new String[] {"Gasoline"});
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("80.00");
		tRec.setAccount(manualChecking);
		tRec.setPayee(payeeName);
		
		TransactionDetailPage td = new TransactionDetailPage();
		td.addSplit(tRec, amount, cat, tags);
		Thread.sleep(1000);

		op.navigateToMemorizedPayees();

		mp.firstMemorizedPayeeName.click();
		
		CreateMemorizedPayeeDetailPage cmp = new CreateMemorizedPayeeDetailPage();
		boolean status = cmp.enabledSplitSwitch.isDisplayed();

		if(status)
			Commentary.log(LogStatus.INFO, "PASS: As Expected, \"Split switch\" is enabled on the Memorized Payee Rule page.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Split switch\" is NOT enabled on the  Memorized Payee Rule page.");

		if(mp.verifySplitAmountAndCategory("50.00", "Doctor"))
			Commentary.log(LogStatus.INFO, "PASS: As Expected, First Split is created with category as \"Doctor\" of amount \"$50.00\" on the Memorized payee Rule Page.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Splits is NOT created on the Memorized payee Rule Page.");

		if(mp.verifySplitAmountAndCategory("30.00", "Auto & Transport"))
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Second Split is created with category as \"Auto & Transport\" of amount \"$30.00\" on the Memorized payee Rule Page.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Splits is NOT created on the Memorized payee Rule Page.");
		
		mp.deleteMemorizedPayee();
		
		sa.assertAll();
	}
}
