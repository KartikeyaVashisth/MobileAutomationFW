package tournament;

import java.util.HashMap;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.BankingAndCreditCardPage;
import dugout.OverviewPage;
import dugout.SettingsPage;
import dugout.SignInPage;
import dugout.SpendingTrendPage;
import dugout.TransactionDetailPage;
import dugout.TransactionSummaryPage;
import dugout.TransactionsPage;
import dugout.WelcomePage;
import referee.Commentary;
import referee.Verify;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;
import support.UserName;

public class TransactionSummaryTest2 extends Recovery {
	
	String sUserName = "yuvaraju.boligorla@quicken.com";
	String sPassword = "Quicken@01";
	String sDataset = "ProjectedBalances";
	String sDataset_stage = "TS_Test2";
	String sManualChecking = "Manual_Checking";
	String sManualSaving = "Manual_Savings";
	
	public String getUsername_basedOnEnv() throws Exception{

		UserName un = new UserName();
		un.stage_ios = "transactionsummary2_ios++@stage.com";
		un.stage_android = "transactionsummary2_android++@stage.com";
		un.prod_ios = "";
		un.prod_android = "";
		return un.getUserName();	
	}
	
	@Test (priority=10, enabled = true)
	public void TS10_Test() throws Exception {

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

	@Test (priority=16, enabled = true)
	public void TC16_ValidateForZeroDataset_TransactionSummaryCard() throws Exception {

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
	
	@Test (priority=15, enabled = true)
	public void TC15_TransactionSummaryCard() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify split category under transaction summary by adding a split transaction.");

		OverviewPage op = new OverviewPage();

		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		
		op.navigateToAllTransactions();
		
		TransactionsPage tp = new TransactionsPage();
		tp.addTransaction.click();
		Thread.sleep(1000);
		
		TransactionDetailPage td = new TransactionDetailPage();
		
		String payeename = h.getCurrentTime();
		//String transferToAcc1 = "Transfer ["+sManualSaving+"]";
		
		HashMap<Integer,String> amount = new HashMap<Integer, String>();
		HashMap<Integer,String> cat = new HashMap<Integer, String>();
		HashMap<Integer,String[]> tags = new HashMap<Integer, String[]>();
		
		cat.put(1, "Shopping");
		cat.put(2, "Travel");
		amount.put(1, "50.00");
		amount.put(2, "20.00");
		tags.put(1, new String[] {"Tax Related"});
		tags.put(2, new String[] {"Tax Related"});
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("70.00");
		//tRec.setTransactionType("expense");
		tRec.setAccount(sManualSaving);
		tRec.setPayee(payeename);
		
		td.addSplit(tRec, amount, cat, tags);
		Thread.sleep(3000);
		
		BankingAndCreditCardPage bc = new BankingAndCreditCardPage();
		tp.backButton.click();
		Thread.sleep(1000);
		//bc.backButton.click();
		
		op.navigateToMonthlySummary();
		
		TransactionSummaryPage ts = new TransactionSummaryPage();
		
		if(Verify.objExists(ts.splitCatShop)) {
			
		}
		else {
			Commentary.log(LogStatus.FAIL, "Split category is not visible");
		}
		
		if(Verify.objExists(ts.splitCatTravel)) {
			
			Commentary.log(LogStatus.PASS, "Splt category exists");
			
			Commentary.log(LogStatus.PASS, "Splt category exists");
			ts.splitCatShop.click();
			tp.tapOnFirstTransaction();
			Thread.sleep(1000);	
			td.scroll_down();
			
			td.deleteTransaction.click();
			td.deleteTransactionAlertButton.click();
			Thread.sleep(1000);
			
		}
		else {
			Commentary.log(LogStatus.FAIL, "Split category is not visible");
		}
	}
}

	
			

	
