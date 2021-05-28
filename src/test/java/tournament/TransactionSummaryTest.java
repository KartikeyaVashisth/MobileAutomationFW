package tournament;

import java.time.LocalDate;
import java.time.Month;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.OverviewPage;
import dugout.SettingsPage;
import dugout.SignInPage;
import dugout.TransactionDetailPage;
import dugout.TransactionSummaryPage;
import dugout.TransactionsPage;
import referee.Commentary;
import referee.Verify;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;

public class TransactionSummaryTest extends Recovery {

	String sUserName = "yuvaraju.boligorla@quicken.com";
	String sPassword = "Intuit!1";
	String sDataset = "ProjectedBalances";
	String sManualChecking = "Manual_Checking";
	String sManualSaving = "Manual_Savings";

	@Test (priority = 1, enabled = true)
	public void TS1_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		SignInPage si = new SignInPage();
		si.signIn(sUserName, sPassword, sDataset);

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verifying all the different elements of Transaction Summary Card.");

		OverviewPage op = new OverviewPage();
		op.tapOnTransactionSummaryCard();

		TransactionSummaryPage ts = new TransactionSummaryPage();

		Verify.waitForObject(ts.transactionSummaryHeader, 2);
		if (Verify.objExists(ts.transactionSummaryHeader))
			Commentary.log(LogStatus.INFO, "PASS: Transaction Summary Header text is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Transaction Summary Header text is not displayed.");

		if (Verify.objExists(ts.backButtonOnHeader))
			Commentary.log(LogStatus.INFO, "PASS: Back button is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Back button is not displayed.");

		if (Verify.objExists(ts.monthHeader))
			Commentary.log(LogStatus.INFO, "PASS: Month Header text is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Month Header text is not displayed.");

		if (Verify.objExists(ts.categoryTab))
			Commentary.log(LogStatus.INFO, "PASS: Category Tab is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Category Tab is not displayed.");

		if (Verify.objExists(ts.payeeTab))
			Commentary.log(LogStatus.INFO, "PASS: Payee Tab is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Payee Tab is not displayed.");

		Verify.waitForObject(ts.categoryTile, 1);
		if (Verify.objExists(ts.categoryTile))
			Commentary.log(LogStatus.INFO, "PASS: Category tile is displayed when there is a transaction added with the category.");
		else if (Verify.objExists(ts.noTransactionCategory))
			Commentary.log(LogStatus.INFO, "PASS: \"No Transactions by Category\" is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Category tile is not displayed NOR \"No Transactions by Category\" text is displayed.");

		ts.tapOnPayeeTab();

		if(Verify.objExists(ts.payeeTile))
			Commentary.log(LogStatus.INFO, "PASS: Payee tile is displayed when there is a transaction added with the Payee name.");
		else if (Verify.objExists(ts.noTransactionPayee))
			Commentary.log(LogStatus.INFO, "PASS: \"No Transactions by Payee\" is displayed.");
		else 
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Payee tile is not displayed NOR \"No Transactions by Payee\" text is displayed.");

		sa.assertAll();
	}

	@Test (priority = 2, enabled = true)
	public void TS2_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verifying that Current Month is displayed on the Transaction Summary card.");

		OverviewPage op = new OverviewPage();
		op.tapOnTransactionSummaryCard();

		LocalDate currentdate = LocalDate.now();
		Month currentMonth = currentdate.getMonth();

		TransactionSummaryPage ts = new TransactionSummaryPage();

		Verify.waitForObject(ts.monthHeader, 1);
		if(ts.monthHeader.getText().contains(currentMonth.toString())) 
			Commentary.log(LogStatus.PASS, "PASS: Current Month is displayed on the Transaction Summary card.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Current Month is NOT displayed on the Transaction Summary card.");

		if (Verify.objExists(ts.getCurrentMonthYear()))
			Commentary.log(LogStatus.INFO, "PASS: Current Month and Year text is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Current Month and Year text is NOT displayed.");

		sa.assertAll();
	}

	@Test (priority = 3, enabled = true)
	public void TS3_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify transaction are displayed under correct Category and Payee in the Transaction summary card.");

		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("5.00"); 
		tRec.setAccount(sManualChecking);
		tRec.setPayee("shop");

		TransactionsPage tp = new TransactionsPage();
		TransactionDetailPage td = new TransactionDetailPage();
		OverviewPage op = new OverviewPage();

		Verify.waitForObject(op.addTransaction, 1);
		op.addTransaction.click();
		
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);

		op.tapOnTransactionSummaryCard();

		TransactionSummaryPage ts = new TransactionSummaryPage();
		Verify.waitForObject(ts.transactionCategoryPayeeText, 2);
		String categoryName = ts.getCategoryPayeeName();

		ts.transactionCategoryPayeeText.click();
		Thread.sleep(7000);

		tp.tapOnTransation(0);
		Thread.sleep(2000);

		Verify.waitForObject(td.selectedCategory, 2);
		td.VerifyTransactionCategory(categoryName);

		Verify.waitForObject(td.backButton, 2);
		td.backButton.click();
		Thread.sleep(2000);

		td.backButton.click(); 
		Thread.sleep(2000);

		ts.payeeTab.click();
		Thread.sleep(2000);
		String payeeName = ts.getCategoryPayeeName();

		ts.transactionCategoryPayeeText.click();
		Thread.sleep(1000);

		tp.tapOnTransation(0);
		Thread.sleep(1000);
		td.VerifyTransactionPayee(payeeName);

		td.backButton.click(); 
		Thread.sleep(1000);

		sa.assertAll();
	}

	@Test (priority = 4, enabled = true)
	public void TS4_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Adding an expense transaction and validating that the Payee tile details are updated correctly on the Transaction Summary page.");

		OverviewPage op = new OverviewPage();
		op.tapOnTransactionSummaryCard();

		TransactionSummaryPage ts = new TransactionSummaryPage();
		Verify.waitForObject(ts.payeeTab, 2);
		ts.payeeTab.click();

		Verify.waitForObject(ts.payeeTile, 2);
		String sPayeeAmount_before = ts.payeeTile.getText();
		Commentary.log(LogStatus.INFO, "CategoryAmount before adding transaction-> "+sPayeeAmount_before);
		Double dPayeeAmount_before = h.processBalanceAmount(sPayeeAmount_before.replace("shop ", ""));
		Commentary.log(LogStatus.INFO, "Processed Category amount is: "+dPayeeAmount_before);

		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("5.00");
		tRec.setAccount(sManualChecking);
		tRec.setPayee("shop");
		tRec.setTransactionType("expense");

		ts.backButtonOnHeader.click();

		op.scrollToTop();		
		Verify.waitForObject(op.addTransaction, 2);
		op.addTransaction.click();
		Verify.waitForObject(td.buttonDone, 2);
		td.addTransaction(tRec);

		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount "+tRec.getAmount());

		op.tapOnTransactionSummaryCard();
		Verify.waitForObject(ts.payeeTab, 2);
		ts.payeeTab.click();

		Verify.waitForObject(ts.payeeTile, 2);
		String sPayeeAmount_after = ts.payeeTile.getText();
		Double dPayeeAmount_after = h.processBalanceAmount(sPayeeAmount_after.replace("shop ", ""));
		Double d = Double.parseDouble(tRec.getAmount());
		Commentary.log(LogStatus.INFO, "Category amount is now: "+dPayeeAmount_after);

		if (dPayeeAmount_before-d==dPayeeAmount_after)
			Commentary.log(LogStatus.INFO, "PASS: Payee tile is updated after adding expense transaction for selected payee.");
		else
			Commentary.log(sa, LogStatus.FAIL, "Payee tile is NOT updated after adding expense transaction for selected payee.");

		sa.assertAll();
	}

	@Test (priority=5, enabled = true)
	public void TS5_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Adding an expense transaction and validating that the Category tile details are updated correctly on the Transaction Summary page.");

		String payeeName = "Payee_"+h.getCurrentTime();

		OverviewPage op = new OverviewPage();
		op.tapOnTransactionSummaryCard();

		TransactionSummaryPage ts = new TransactionSummaryPage();
		Verify.waitForObject(ts.categoryTab, 1);
		ts.categoryTab.click();
		Thread.sleep(1000);
		Verify.waitForObject(ts.categoryTile, 2);

		String sCategoryAmount_before = ts.categoryTile.getText();
		Double dCategoryAmount_before = h.processBalanceAmount(sCategoryAmount_before.replace("Internet ", ""));
		Commentary.log(LogStatus.INFO, "Category amount is "+dCategoryAmount_before);

		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("10.00");
		tRec.setAccount(sManualChecking);
		tRec.setPayee(payeeName);
		tRec.setCategory("Internet");
		tRec.setTransactionType("expense");

		ts.backButtonOnHeader.click();

		op.scrollToTop();

		op.addTransaction.click();
		Verify.waitForObject(td.buttonDone, 2);
		td.addTransaction(tRec);

		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount "+tRec.getAmount());

		op.tapOnTransactionSummaryCard();
		Verify.waitForObject(ts.categoryTab, 2);
		ts.categoryTab.click();

		Verify.waitForObject(ts.categoryTile, 2);
		String sCategoryAmount_after = ts.categoryTile.getText();
		Double dCategoryAmount_after = h.processBalanceAmount(sCategoryAmount_after.replace("Internet ", ""));
		Double d = Double.parseDouble(tRec.getAmount());
		Commentary.log(LogStatus.INFO, "Category amount is "+dCategoryAmount_after);

		if (dCategoryAmount_before-d==dCategoryAmount_after)
			Commentary.log(LogStatus.INFO, "PASS: Category tile is updated after adding expense transaction for selected payee.");
		else
			Commentary.log(sa, LogStatus.FAIL, "Category tile is NOT updated after adding expense transaction for selected payee.");

		sa.assertAll();
	}

	@Test (priority = 6, enabled = true)
	public void TS6_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Deleting an expense transaction and validating that the Payee tile details are updated correctly on the Transaction Summary page.");

		OverviewPage op = new OverviewPage();
		op.tapOnTransactionSummaryCard();

		TransactionSummaryPage ts = new TransactionSummaryPage();
		Verify.waitForObject(ts.payeeTab, 2);
		ts.payeeTab.click();

		Verify.waitForObject(ts.payeeTile, 2);
		String sPayeeAmount_before = ts.payeeTile.getText();
		Commentary.log(LogStatus.INFO, "Payee Amount before adding transaction-> "+sPayeeAmount_before);
		Double dPayeeAmount_before = h.processBalanceAmount(sPayeeAmount_before.replace("shop ", ""));
		Commentary.log(LogStatus.INFO, "Processed Payee amount is: "+dPayeeAmount_before);

		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("5.00");
		tRec.setAccount(sManualChecking);
		tRec.setPayee("shop");
		tRec.setTransactionType("expense");

		ts.backButtonOnHeader.click();

		op.scrollToTop();		
		Verify.waitForObject(op.addTransaction, 2);
		op.addTransaction.click();
		Verify.waitForObject(td.buttonDone, 2);
		td.addTransaction(tRec);

		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount "+tRec.getAmount());

		Thread.sleep(3000);
		op.tapOnTransactionSummaryCard();

		Verify.waitForObject(ts.payeeTab, 1);
		ts.payeeTab.click();
		Thread.sleep(1000);

		Verify.waitForObject(ts.payeeTile, 2);
		String sPayeeAmount_afterAddingTransaction = ts.payeeTile.getText();
		Double dPayeeAmount_afterAddingTransaction = h.processBalanceAmount(sPayeeAmount_afterAddingTransaction.replace("shop ", ""));

		Commentary.log(LogStatus.INFO, "Payee amount after adding an expense transaction is now: "+dPayeeAmount_afterAddingTransaction);

		Verify.waitForObject(ts.payeeTile, 2);
		ts.payeeTile.click();
		Thread.sleep(2000);

		TransactionsPage tp = new TransactionsPage();
		
		tp.tapOnFirstTransaction();	
		Verify.waitForObject(td.category, 2);

		op.scroll_down();
		Verify.waitForObject(td.deleteTransaction, 2);
		td.deleteTransaction.click();
		Thread.sleep(1500);

		if (Verify.objExists_check(td.deleteTransactionAlertButton)) 
			td.deleteTransactionAlertButton.click();

		Thread.sleep(5000);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);

		Verify.waitForObject(ts.backButton, 1);
		ts.backButton.click();

		Verify.waitForObject(ts.payeeTile, 2);
		String sPayeeAmount_afterDeletion = ts.payeeTile.getText();
		Double dPayeeAmount_afterDeletion = h.processBalanceAmount(sPayeeAmount_afterDeletion.replace("shop ", ""));

		Commentary.log(LogStatus.INFO, "Payee amount after deletion is now: "+dPayeeAmount_afterDeletion);

		Integer compare = Double.compare(dPayeeAmount_before, dPayeeAmount_afterDeletion);

		if (compare==0)
			Commentary.log(LogStatus.INFO, "PASS: Payee Tile amount initially in the Transaction Summary card was ["+dPayeeAmount_before+"], added and then deleted the expense transaction for ["+tRec.getAmount()+"], as expected now the Payee Tile amount is seen same: ["+dPayeeAmount_afterDeletion+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Payee Tile amount initially in the Transaction Summary card was ["+dPayeeAmount_before+"], added and then deleted the expense transaction for ["+tRec.getAmount()+"], now the Payee Tile amount is seen as: ["+dPayeeAmount_afterDeletion+"]");

		sa.assertAll();
	}

	@Test (priority = 7, enabled = true)
	public void TS7_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Deleting an expense transaction and validating that the Category tile details are updated correctly on the Transaction Summary page.");

		String payeeName = "Payee_"+h.getCurrentTime();

		OverviewPage op = new OverviewPage();
		op.tapOnTransactionSummaryCard();

		TransactionSummaryPage ts = new TransactionSummaryPage();

		Verify.waitForObject(ts.categoryTile, 2);
		String sCategoryAmount_before = ts.categoryTile.getText();
		Commentary.log(LogStatus.INFO, "Category tile Amount before adding transaction-> "+sCategoryAmount_before);
		Double dCategoryAmount_before = h.processBalanceAmount(sCategoryAmount_before.replace("Internet ", ""));
		Commentary.log(LogStatus.INFO, "Processed Category amount is: "+dCategoryAmount_before);

		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("10.00");
		tRec.setAccount(sManualChecking);
		tRec.setPayee(payeeName);
		tRec.setCategory("Internet");
		tRec.setTransactionType("expense");

		ts.backButtonOnHeader.click();

		op.scrollToTop();		
		Verify.waitForObject(op.addTransaction, 2);
		op.addTransaction.click();
		Verify.waitForObject(td.buttonDone, 2);
		td.addTransaction(tRec);

		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount "+tRec.getAmount());

		Thread.sleep(3000);
		op.tapOnTransactionSummaryCard();

		Verify.waitForObject(ts.categoryTab, 1);
		ts.categoryTab.click();
		Thread.sleep(1000);

		Verify.waitForObject(ts.categoryTile, 2);
		String sCategoryAmount_afterAddingTransaction = ts.categoryTile.getText();
		Double dCategoryAmount_afterAddingTransaction = h.processBalanceAmount(sCategoryAmount_afterAddingTransaction.replace("Internet ", ""));

		Commentary.log(LogStatus.INFO, "Category tile amount after adding an expense transaction is now: "+dCategoryAmount_afterAddingTransaction);

		Verify.waitForObject(ts.categoryTile, 2);
		ts.categoryTile.click();
		Thread.sleep(2000);

		TransactionsPage tp = new TransactionsPage();
		tp.searchRecentTransaction(payeeName);
		tp.tapOnFirstTransaction();	
		Verify.waitForObject(td.category, 2);

		op.scroll_down();
		Verify.waitForObject(td.deleteTransaction, 2);
		td.deleteTransaction.click();
		Thread.sleep(1500);

		if (Verify.objExists_check(td.deleteTransactionAlertButton)) 
			td.deleteTransactionAlertButton.click();

		Thread.sleep(5000);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);

		Verify.waitForObject(ts.backButton, 1);
		ts.backButton.click();

		Verify.waitForObject(ts.categoryTile, 2);
		String sCategoryAmount_afterDeletion = ts.categoryTile.getText();
		Double dCategoryAmount_afterDeletion = h.processBalanceAmount(sCategoryAmount_afterDeletion.replace("Internet ", ""));

		Commentary.log(LogStatus.INFO, "Category tile amount after deletion is now: "+dCategoryAmount_afterDeletion);

		Integer compare = Double.compare(dCategoryAmount_before, dCategoryAmount_afterDeletion);

		if (compare==0)
			Commentary.log(LogStatus.INFO, "PASS: Category Tile amount initially in the Transaction Summary card was ["+dCategoryAmount_before+"], added and then deleted the expense transaction for ["+tRec.getAmount()+"], as expected now the Category Tile amount is seen same: ["+dCategoryAmount_afterDeletion+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Category Tile amount initially in the Transaction Summary card was ["+dCategoryAmount_before+"], added and then deleted the expense transaction for ["+tRec.getAmount()+"], now the Category Tile amount is seen as: ["+dCategoryAmount_afterDeletion+"]");

		sa.assertAll();
	}

	@Test (priority = 8, enabled = true)
	public void TS8_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Adding an expense transaction from the Transaction summary card and then Editing that expense transaction and validating that the Category tile details are updated correctly on Transaction Summary page.");

		String payeeName = "Payee_"+h.getCurrentTime();

		OverviewPage op = new OverviewPage();
		op.tapOnTransactionSummaryCard();

		TransactionSummaryPage ts = new TransactionSummaryPage();

		Verify.waitForObject(ts.categoryTile, 2);
		String sCategoryAmount_before = ts.categoryTile.getText();
		Commentary.log(LogStatus.INFO, "Category tile Amount before adding transaction-> "+sCategoryAmount_before);
		Double dCategoryAmount_before = h.processBalanceAmount(sCategoryAmount_before.replace("Internet ", ""));
		Commentary.log(LogStatus.INFO, "Processed Category amount is: "+dCategoryAmount_before);

		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("5.00");
		tRec.setAccount(sManualChecking);
		tRec.setPayee(payeeName);
		tRec.setCategory("Internet");
		tRec.setTransactionType("expense");

		Verify.waitForObject(ts.categoryTile, 2);
		ts.categoryTile.click();
		Thread.sleep(2000);

		Verify.waitForObject(ts.addTransactionButton, 1);
		ts.addTransactionButton.click();
		Verify.waitForObject(td.buttonDone, 2);

		td.addTransaction(tRec);

		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount "+tRec.getAmount());

		Verify.waitForObject(ts.backButton, 1);
		ts.backButton.click();
		Thread.sleep(2000);

		Verify.waitForObject(ts.categoryTile, 2);
		String sCategoryAmount_afterAddingTransaction = ts.categoryTile.getText();
		Double dCategoryAmount_afterAddingTransaction = h.processBalanceAmount(sCategoryAmount_afterAddingTransaction.replace("Internet ", ""));

		Commentary.log(LogStatus.INFO, "Category tile amount after adding an expense transaction is now: "+dCategoryAmount_afterAddingTransaction);

		Verify.waitForObject(ts.categoryTile, 2);
		ts.categoryTile.click();
		Thread.sleep(2000);

		TransactionsPage tp = new TransactionsPage();
		tp.searchRecentTransaction(payeeName);
		tp.tapOnFirstTransaction();	
		Verify.waitForObject(td.category, 2);

		TransactionRecord tRec1 = new TransactionRecord();
		tRec1.setAmount("10.00");

		td.editTransaction(tRec1);

		Commentary.log(LogStatus.INFO, "Transaction edited successfully.");

		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);

		Verify.waitForObject(ts.backButton, 1);
		ts.backButton.click();

		Verify.waitForObject(ts.categoryTile, 2);
		String sCategoryAmount_afterEditing = ts.categoryTile.getText();
		Double dCategoryAmount_afterEditing = h.processBalanceAmount(sCategoryAmount_afterEditing.replace("Internet ", ""));
		Double d = Double.parseDouble(tRec1.getAmount()); 

		Commentary.log(LogStatus.INFO, "Category tile amount after editing is now: "+dCategoryAmount_afterEditing);

		if (dCategoryAmount_before-d==dCategoryAmount_afterEditing)
			Commentary.log(LogStatus.INFO, "PASS: Category tile is updated correctly after editing expense transaction for selected payee.");
		else
			Commentary.log(sa, LogStatus.FAIL, "Category tile is NOT updated after editing expense transaction for selected payee.");

		sa.assertAll();
	}

	@Test (priority=9, enabled = true)
	public void TS9_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Adding an expense transaction and validating that the Spent field is updated on Transaction Summary Dashboard card.");

		String payeeName = "Payee_"+h.getCurrentTime();

		OverviewPage op = new OverviewPage();
		op.scrollToTransactionSummaryCard();

		TransactionSummaryPage ts = new TransactionSummaryPage();

		Verify.waitForObject(ts.spentAmount, 1);
		String sSpentAmount_before = ts.spentAmount.getText();
		Double dSpentAmount_before = h.processBalanceAmount(sSpentAmount_before.replace("Spent : ", ""));

		String sEarnedAmount_before = ts.earnedAmount.getText();
		Double dEarnedAmount_before = h.processBalanceAmount(sEarnedAmount_before.replace("Earned : ", ""));

		Commentary.log(LogStatus.INFO, "Original Spent amount: "+dSpentAmount_before+"");
		Commentary.log(LogStatus.INFO, "Original Earned amount: "+dEarnedAmount_before+"");

		op.scrollToTop();

		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();

		tRec.setAmount("5.00"); 
		tRec.setAccount(sManualChecking);
		tRec.setCategory("Internet");
		tRec.setPayee(payeeName);
		tRec.setTransactionType("expense");

		Double d = Double.parseDouble(tRec.getAmount());

		op.addTransaction.click();
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec);

		op.scrollToTransactionSummaryCard();

		Verify.waitForObject(ts.spentAmount, 1);
		String sSpentAmount_after = ts.spentAmount.getText();
		Double dSpentAmount_after = h.processBalanceAmount(sSpentAmount_after.replace("Spent : ", ""));

		String sEarnedAmount_after = ts.earnedAmount.getText();
		Double dEarnedAmount_after = h.processBalanceAmount(sEarnedAmount_after.replace("Earned : ", ""));

		if (dSpentAmount_before-d==dSpentAmount_after)
			Commentary.log(LogStatus.INFO, "PASS: Spent Amount initially on the Transaction Summary card was ["+dSpentAmount_before+"], added an expense transaction for ["+tRec.getAmount()+"], now the Spent amount is seen as: ["+dSpentAmount_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Spent Amount initially on the Transaction Summary card was ["+dSpentAmount_before+"], added an expense transaction for ["+tRec.getAmount()+"], now the Spent amount is seen as: ["+dSpentAmount_after+"]");

		Integer compare = Double.compare(dEarnedAmount_before, dEarnedAmount_after);

		if (compare==0)
			Commentary.log(LogStatus.INFO, "PASS: Earned Amount initially on the Transaction Summary card was ["+dEarnedAmount_before+"], added an expense transaction for ["+tRec.getAmount()+"], As Expected Earned amount remained unchanged: ["+dEarnedAmount_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Earned Amount initially on the Transaction Summary card was ["+dEarnedAmount_before+"], added an expense transaction for ["+tRec.getAmount()+"], Earned amount got changed: ["+dEarnedAmount_after+"]");

		sa.assertAll();
	}

	@Test (priority=10, enabled = true)
	public void TS10_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Adding an income transaction and validating that the Earned field is updated on Transaction Summary Dashboard card.");

		String payeeName = "Payee_"+h.getCurrentTime();

		OverviewPage op = new OverviewPage();
		op.scrollToTransactionSummaryCard();

		TransactionSummaryPage ts = new TransactionSummaryPage();

		Verify.waitForObject(ts.earnedAmount, 1);
		String sEarnedAmount_before = ts.earnedAmount.getText();
		Double dEarnedAmount_before = h.processBalanceAmount(sEarnedAmount_before.replace("Earned : ", ""));

		String sSpentAmount_before = ts.spentAmount.getText();
		Double dSpentAmount_before = h.processBalanceAmount(sSpentAmount_before.replace("Spent : ", ""));

		Commentary.log(LogStatus.INFO, "Original Earned amount: "+dEarnedAmount_before+"");
		Commentary.log(LogStatus.INFO, "Original Spent amount: "+dSpentAmount_before+"");

		op.scrollToTop();

		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();

		tRec.setAmount("5.00"); 
		tRec.setAccount(sManualChecking);
		tRec.setCategory("Internet");
		tRec.setPayee(payeeName);
		tRec.setTransactionType("income");

		Double d = Double.parseDouble(tRec.getAmount());

		op.addTransaction.click();
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec);

		op.scrollToTransactionSummaryCard();

		Verify.waitForObject(ts.earnedAmount, 1);
		String sEarnedAmount_after = ts.earnedAmount.getText();
		Double dEarnedAmount_after = h.processBalanceAmount(sEarnedAmount_after.replace("Earned : ", ""));

		String sSpentAmount_after = ts.spentAmount.getText();
		Double dSpentAmount_after = h.processBalanceAmount(sSpentAmount_after.replace("Spent : ", ""));

		if (dEarnedAmount_before+d==dEarnedAmount_after)
			Commentary.log(LogStatus.INFO, "PASS: Earned Amount initially on the Transaction Summary card was ["+dEarnedAmount_before+"], added an Income transaction for ["+tRec.getAmount()+"], now the Earned amount is seen as: ["+dEarnedAmount_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Earned Amount initially on the Transaction Summary card was ["+dEarnedAmount_before+"], added an Income transaction for ["+tRec.getAmount()+"], now the Earned amount is seen as: ["+dEarnedAmount_after+"]");

		Integer compare = Double.compare(dSpentAmount_before, dSpentAmount_after);

		if (compare==0)
			Commentary.log(LogStatus.INFO, "PASS: Spent Amount initially on the Transaction Summary card was ["+dSpentAmount_before+"], added an Income transaction for ["+tRec.getAmount()+"], As Expected Spent amount remained unchanged: ["+dSpentAmount_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Spent Amount initially on the Transaction Summary card was ["+dSpentAmount_before+"], added an Income transaction for ["+tRec.getAmount()+"], Spent amount got changed: ["+dSpentAmount_after+"]");

		sa.assertAll();
	}

	@Test (priority=11, enabled = true)
	public void TS11_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Adding a transfer transaction and validating that the Earned and Spent field is NOT updated on Transaction Summary Dashboard card.");

		String payeeName = "Payee_"+h.getCurrentTime();

		OverviewPage op = new OverviewPage();
		op.scrollToTransactionSummaryCard();

		TransactionSummaryPage ts = new TransactionSummaryPage();

		Verify.waitForObject(ts.earnedAmount, 1);
		String sEarnedAmount_before = ts.earnedAmount.getText();
		Double dEarnedAmount_before = h.processBalanceAmount(sEarnedAmount_before.replace("Earned : ", ""));

		String sSpentAmount_before = ts.spentAmount.getText();
		Double dSpentAmount_before = h.processBalanceAmount(sSpentAmount_before.replace("Spent : ", ""));

		Commentary.log(LogStatus.INFO, "Original Earned amount: "+dEarnedAmount_before+"");
		Commentary.log(LogStatus.INFO, "Original Spent amount: "+dSpentAmount_before+"");

		op.scrollToTop();

		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();

		tRec.setAmount("5.00"); 
		tRec.setAccount(sManualChecking);
		tRec.setCategory("Transfer ["+sManualSaving+"]");
		tRec.setPayee(payeeName);
		tRec.setTransactionType("expense");

		op.addTransaction.click();
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec);

		op.scrollToTransactionSummaryCard();

		Verify.waitForObject(ts.earnedAmount, 1);
		String sEarnedAmount_after = ts.earnedAmount.getText();
		Double dEarnedAmount_after = h.processBalanceAmount(sEarnedAmount_after.replace("Earned : ", ""));

		String sSpentAmount_after = ts.spentAmount.getText();
		Double dSpentAmount_after = h.processBalanceAmount(sSpentAmount_after.replace("Spent : ", ""));

		Integer earnedCompare = Double.compare(dEarnedAmount_before, dEarnedAmount_after);
		Integer spentCompare = Double.compare(dSpentAmount_before, dSpentAmount_after);

		if (earnedCompare==0)
			Commentary.log(LogStatus.INFO, "PASS: Earned Amount initially on the Transaction Summary card was ["+dEarnedAmount_before+"], added a transfer transaction for ["+tRec.getAmount()+"], as expected now the Earned amount is seen same: ["+dEarnedAmount_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Earned Amount initially on the Transaction Summary card was ["+dEarnedAmount_before+"], added a transfer transaction for ["+tRec.getAmount()+"], now the Earned amount is seen as: ["+dEarnedAmount_after+"]");

		if (spentCompare==0)
			Commentary.log(LogStatus.INFO, "PASS: Spent Amount initially on the Transaction Summary card was ["+dSpentAmount_before+"], added a transfer transaction for ["+tRec.getAmount()+"], as expected now the Spent amount is seen same: ["+dSpentAmount_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Spent Amount initially on the Transaction Summary card was ["+dSpentAmount_before+"], added a transfer transaction for ["+tRec.getAmount()+"], now the Spent amount is seen as: ["+dSpentAmount_after+"]");

		sa.assertAll();
	}

	@Test (priority=12, enabled = true)
	public void TS12_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Adding two expense transactions one with previous month date and other with future month date and validating that both the transactions are not included in this month's transaction summary.");

		String payeeName = "Payee_"+h.getCurrentTime();

		OverviewPage op = new OverviewPage();
		op.scrollToTransactionSummaryCard();

		TransactionSummaryPage ts = new TransactionSummaryPage();

		Verify.waitForObject(ts.spentAmount, 1);
		String sSpentAmount_before = ts.spentAmount.getText();
		Double dSpentAmount_before = h.processBalanceAmount(sSpentAmount_before.replace("Spent : ", ""));

		String sEarnedAmount_before = ts.earnedAmount.getText();
		Double dEarnedAmount_before = h.processBalanceAmount(sEarnedAmount_before.replace("Earned : ", ""));

		Commentary.log(LogStatus.INFO, "Original Spent amount: "+dSpentAmount_before+"");
		Commentary.log(LogStatus.INFO, "Original Earned amount: "+dEarnedAmount_before+"");

		op.scrollToTop();

		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();

		tRec.setAmount("5.00"); 
		tRec.setAccount(sManualChecking);
		tRec.setCategory("Internet");
		tRec.setPayee(payeeName);
		tRec.setTransactionType("expense");
		tRec.setDate(h.getPastDaysDate(31));

		op.addTransaction.click();
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 1);
		
		String payeeName1 = "Payee_"+h.getCurrentTime();
		
		TransactionRecord tRec1 = new TransactionRecord();
		tRec1.setAmount("5.00"); 
		tRec1.setAccount(sManualChecking);
		tRec1.setCategory("Internet");
		tRec1.setPayee(payeeName1);
		tRec1.setTransactionType("expense");
		tRec1.setDate(h.getFutureDaysDate(31));

		op.addTransaction.click();
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec1);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 1);
		
		op.scrollToTransactionSummaryCard();

		Verify.waitForObject(ts.spentAmount, 1);
		String sSpentAmount_after = ts.spentAmount.getText();
		Double dSpentAmount_after = h.processBalanceAmount(sSpentAmount_after.replace("Spent : ", ""));

		String sEarnedAmount_after = ts.earnedAmount.getText();
		Double dEarnedAmount_after = h.processBalanceAmount(sEarnedAmount_after.replace("Earned : ", ""));

		Integer compare = Double.compare(dSpentAmount_before, dSpentAmount_after);

		if (compare==0)
			Commentary.log(LogStatus.INFO, "PASS: Spent Amount initially on the Transaction Summary card was ["+dSpentAmount_before+"], added an two expense transaction for different months, As Expected Spent amount remained unchanged: ["+dSpentAmount_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Spent Amount initially on the Transaction Summary card was ["+dSpentAmount_before+"], added an expense transaction for for different months, Spent amount got changed: ["+dSpentAmount_after+"]");

		Integer compare1 = Double.compare(dEarnedAmount_before, dEarnedAmount_after);

		if (compare1==0)
			Commentary.log(LogStatus.INFO, "PASS: Earned Amount initially on the Transaction Summary card was ["+dEarnedAmount_before+"], added an expense transaction for different months, As Expected Earned amount remained unchanged: ["+dEarnedAmount_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Earned Amount initially on the Transaction Summary card was ["+dEarnedAmount_before+"], added an expense transaction for different months, Earned amount got changed: ["+dEarnedAmount_after+"]");

		sa.assertAll();
	}

	
	@Test (priority=13, enabled = true)
	public void TS13_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Deleting an expense transaction and validating that the Spent field is updated on Transaction Summary Dashboard card.");

		String payeeName = "Payee_"+h.getCurrentTime();

		OverviewPage op = new OverviewPage();
		op.scrollToTransactionSummaryCard();

		TransactionSummaryPage ts = new TransactionSummaryPage();

		Verify.waitForObject(ts.spentAmount, 1);
		String sSpentAmount_beforeAddingTransaction = ts.spentAmount.getText();
		Double dSpentAmount_beforeAddingTransaction = h.processBalanceAmount(sSpentAmount_beforeAddingTransaction.replace("Spent : ", ""));

		Commentary.log(LogStatus.INFO, "Original Spent amount: "+dSpentAmount_beforeAddingTransaction+"");

		op.scrollToTop();

		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();

		tRec.setAmount("5.00"); 
		tRec.setAccount(sManualChecking);
		tRec.setCategory("Internet");
		tRec.setPayee(payeeName);
		tRec.setTransactionType("expense");

		op.addTransaction.click();
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec);

		op.scrollToTransactionSummaryCard();

		Verify.waitForObject(ts.spentAmount, 1);
		String sSpentAmount_afterAddingTransaction = ts.spentAmount.getText();
		Double dSpentAmount_afterAddingTransaction = h.processBalanceAmount(sSpentAmount_afterAddingTransaction.replace("Spent : ", ""));

		Commentary.log(LogStatus.INFO, "Spent amount after adding the transaction: "+dSpentAmount_afterAddingTransaction+"");

		op.tapOnTransactionSummaryCard();

		Verify.waitForObject(ts.categoryTab, 1);
		ts.categoryTab.click();
		Thread.sleep(1000);
		Verify.waitForObject(ts.categoryTile, 2);

		ts.categoryTile.click();
		Thread.sleep(2000);

		TransactionsPage tp = new TransactionsPage();
		tp.searchRecentTransaction(payeeName);
		tp.tapOnFirstTransaction();	
		Verify.waitForObject(td.category, 2);

		op.scroll_down();
		Verify.waitForObject(td.deleteTransaction, 2);
		td.deleteTransaction.click();
		Thread.sleep(1500);
		if (Verify.objExists_check(td.deleteTransactionAlertButton)) {
			td.deleteTransactionAlertButton.click();
		}
		Thread.sleep(5000);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);

		Verify.waitForObject(ts.backButton, 1);
		ts.backButton.click();

		ts.navigateBackToDashboard();

		Verify.waitForObject(ts.spentAmount, 1);
		String sSpentAmount_afterDeletion = ts.spentAmount.getText();
		Double dSpentAmount_afterDeletion = h.processBalanceAmount(sSpentAmount_afterDeletion.replace("Spent : ", ""));

		Commentary.log(LogStatus.INFO, "Spent amount after deletion of the transaction: "+dSpentAmount_afterDeletion+"");

		Integer spentCompare = Double.compare(dSpentAmount_beforeAddingTransaction, dSpentAmount_afterDeletion);

		if (spentCompare==0)
			Commentary.log(LogStatus.INFO, "PASS: Spent Amount initially on the Transaction Summary card was ["+dSpentAmount_beforeAddingTransaction+"], added and then deleted the expense transaction for ["+tRec.getAmount()+"], as expected now the Spent amount is seen same: ["+dSpentAmount_afterDeletion+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Spent Amount initially on the Transaction Summary card was ["+dSpentAmount_beforeAddingTransaction+"], added a transfer transaction for ["+tRec.getAmount()+"], now the Spent amount is seen as: ["+dSpentAmount_afterDeletion+"]");

		sa.assertAll();
	}

	@Test (priority=14, enabled = true)
	public void TS14_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating that the Net Income field is calculated correctly on the Transaction Summary Dashboard card.");

		String payeeName = "Payee_"+h.getCurrentTime();

		OverviewPage op = new OverviewPage();
		op.scrollToTransactionSummaryCard();

		TransactionSummaryPage ts = new TransactionSummaryPage();
		Verify.waitForObject(ts.earnedAmount, 1);
		String sEarnedAmount_before = ts.earnedAmount.getText();
		Double dEarnedAmount_before = h.processBalanceAmount(sEarnedAmount_before.replace("Earned : ", ""));

		String sSpentAmount_before = ts.spentAmount.getText();
		Double dSpentAmount_before = h.processBalanceAmount(sSpentAmount_before.replace("Spent : ", ""));

		String sNetIncomeAmount_before = ts.netIncomeAmount.getText();
		Double dNetIncomeAmount_before = h.processBalanceAmount(sNetIncomeAmount_before.replace("Net Income : ", ""));

		Commentary.log(LogStatus.INFO, "Original Net Income amount: "+dNetIncomeAmount_before+"");
		Commentary.log(LogStatus.INFO, "Original Earned amount: "+dEarnedAmount_before+"");
		Commentary.log(LogStatus.INFO, "Original Spent amount: "+dSpentAmount_before+"");

		if(dEarnedAmount_before + dSpentAmount_before == dNetIncomeAmount_before) //Earned + Spent = Net income because Spent amount is already shown with negative sign.
			Commentary.log(LogStatus.INFO, "PASS: Net Income is calculated correctly i.e. Earned amount minus Spent amount.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Net Income is NOT calculated correctly.");

		op.scrollToTop();

		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("5.00"); 
		tRec.setAccount(sManualChecking);
		tRec.setCategory("Internet");
		tRec.setPayee(payeeName);
		tRec.setTransactionType("expense");

		op.addTransaction.click();
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec);

		op.scrollToTransactionSummaryCard();

		Verify.waitForObject(ts.earnedAmount, 1);
		String sEarnedAmount_afterAddingTransaction = ts.earnedAmount.getText();
		Double dEarnedAmount_afterAddingTransaction = h.processBalanceAmount(sEarnedAmount_afterAddingTransaction.replace("Earned : ", ""));

		String sSpentAmount_afterAddingTransaction = ts.spentAmount.getText();
		Double dSpentAmount_afterAddingTransaction = h.processBalanceAmount(sSpentAmount_afterAddingTransaction.replace("Spent : ", ""));

		String sNetIncomeAmount_afterAddingTransaction = ts.netIncomeAmount.getText();
		Double dNetIncomeAmount_afterAddingTransaction = h.processBalanceAmount(sNetIncomeAmount_afterAddingTransaction.replace("Net Income : ", ""));

		Commentary.log(LogStatus.INFO, "Net Income amount: "+dNetIncomeAmount_afterAddingTransaction+"");
		Commentary.log(LogStatus.INFO, "Earned amount: "+dEarnedAmount_afterAddingTransaction+"");
		Commentary.log(LogStatus.INFO, "Spent amount: "+dSpentAmount_afterAddingTransaction+"");

		if(dEarnedAmount_afterAddingTransaction + dSpentAmount_afterAddingTransaction == dNetIncomeAmount_afterAddingTransaction)
			Commentary.log(LogStatus.INFO, "PASS: Net Income is calculated correctly i.e. Earned amount minus Spent amount.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Net Income is NOT calculated correctly.");

		sa.assertAll();
	}

	@Test (priority=15, enabled = true)
	public void TC15_ValidateForZeroDataset_TransactionSummaryCard() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating message is displayed on Transaction Summary Card in case of no transactions.");

		OverviewPage op = new OverviewPage();

		Verify.waitForObject(op.hambergerIcon, 2);
		op.hambergerIcon.click();
		Thread.sleep(3000);

		SettingsPage sp = new SettingsPage();
		Verify.waitForObject(sp.datasetDDButton, 2);
		sp.datasetDDButton.click();
		Thread.sleep(4000);

		sp.switchDataset("ZeroDataSet");

		op.tapOnTransactionSummaryCard();

		TransactionSummaryPage ts = new TransactionSummaryPage();

		Verify.waitForObject(ts.noTransactionCategory, 2);
		String actText_summaryDetailsScreen = ts.noTransactionCategory.getText();

		if (actText_summaryDetailsScreen.equals("No Transactions by Category"))
			Commentary.log(LogStatus.INFO, "PASS: Transaction Summary page > Correct message is displayed in case user has no transactions.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Transaction Summary page > Message is not displayed in case user has no transactions.");

		ts.payeeTab.click();

		Verify.waitForObject(ts.noTransactionPayee, 2);
		String actText_noTxnPayee = ts.noTransactionPayee.getText();

		if (actText_noTxnPayee.equals("No Transactions by Payee"))
			Commentary.log(LogStatus.INFO, "PASS: Transaction Summary page > Correct message is displayed in case user has no transactions.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Transaction Summary page > Message is not displayed in case user has no transactions.");

		sa.assertAll();
	}

}
