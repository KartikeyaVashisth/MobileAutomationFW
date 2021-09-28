package tournament;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.BankingAndCreditCardPage;
import dugout.OverviewPage;
import dugout.SignInPage;
import dugout.TransactionDetailPage;
import dugout.TransactionsPage;
import referee.Commentary;
import referee.Verify;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;

public class TodaysBalances_MultipleTransactions extends Recovery {
	
	String sUserName = "yuvaraju.boligorla@quicken.com";
	String sPassword = "Intuit!1";
	String sDataset = "TodaysBalancesTest";
	String sManualChecking = "Manual_Checking";
	String sOnlineChecking ="onl_checking1";
	String sManualCreditCard = "Manual_CC";
	String sOnlineCreditCard ="onl_cc";
	String sManualCash="Manual_cash";
	String sOnlineCash="onl_cash";
	String sManualSaving = "Manual_Savings";
	String sOnlineSaving = "onl_savings1";

	@Test(priority = 1, enabled = true)
	public void TBM1_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		SignInPage signIn = new SignInPage();
		signIn.signIn(sUserName, sPassword, sDataset);
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Add multiple[two] expense transactions for an online checking account, verify checking & total balance on overview screen accounts card.");
		
		String sChecking_before, sTotal_before, sChecking_after, sTotal_after;
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sOnlineChecking);
		tRec.setAmount("5.00");
		tRec.setTransactionType("expense");
		tRec.setDate(h.getYesterdaysDate());
		
		TransactionRecord tRec1 = new TransactionRecord();
		tRec1.setAccount(sOnlineChecking);
		tRec1.setAmount("3.00");
		tRec1.setTransactionType("expense");
		tRec1.setDate(h.getYesterdaysDate());
		
		OverviewPage op = new OverviewPage();
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		sChecking_before = op.checkingBalance.getText();
		sTotal_before = op.totalBalance.getText();
		Commentary.log(LogStatus.INFO, "Checking balance before adding the transaction ["+sChecking_before+"]");
		Commentary.log(LogStatus.INFO, "Total balance before adding the transaction ["+sTotal_before+"]");
		Double d = Double.parseDouble(tRec.getAmount())+Double.parseDouble(tRec1.getAmount());
		Double dChecking_before = h.processBalanceAmount(sChecking_before);
		Double dTotal_before = h.processBalanceAmount(sTotal_before);
		
		op.addTransaction.click();
		TransactionDetailPage td = new TransactionDetailPage();
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec);
		
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount: "+tRec.getAmount());
		
		op.addTransaction.click();
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec1);

		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec1.getAccount()+"], transaction type expense, amount: "+tRec1.getAmount());
		
		sChecking_after = op.checkingBalance.getText();
		sTotal_after = op.totalBalance.getText();
		Double dChecking_after = h.processBalanceAmount(sChecking_after);
		Double dTotal_after = h.processBalanceAmount(sTotal_after);
		
		double p = dChecking_before-d;
		System.out.println("***Expected - "+p);
		
		int checking_Balance_Compare = Double.compare(dChecking_before-d, dChecking_after);
		int total_Balance_Compare = Double.compare(dTotal_before-d, dTotal_after);
		
//		if (dChecking_before-d==dChecking_after)
		if(checking_Balance_Compare == 0)
			Commentary.log(LogStatus.INFO, "PASS: Checking balance was ["+dChecking_before+"], added expense transactions for ["+d+"], now the checking balance shows ["+dChecking_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Checking balance was ["+dChecking_before+"], added expense transactions for ["+d+"], now the checking balance shows ["+dChecking_after+"]");
		
//		if (dTotal_before-d==dTotal_after)
		if(total_Balance_Compare == 0)
			Commentary.log(LogStatus.INFO, "PASS: Total balance was ["+dTotal_before+"], added expense transactions for ["+d+"], now the total balance shows ["+dTotal_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Total balance was ["+dTotal_before+"], added expense transactions for ["+d+"], now the total balance shows ["+dTotal_after+"]");
		
		sa.assertAll();	
	}
	
	@Test(priority = 2, enabled = true)
	public void TBM2_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Add multiple[two] income transactions for a manual checking account, verify checking & total balance on overview screen accounts card.");
		
		String sChecking_before, sTotal_before, sChecking_after, sTotal_after;
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualChecking);
		tRec.setAmount("4.00");
		tRec.setTransactionType("income");
		tRec.setDate(h.getYesterdaysDate());
		
		TransactionRecord tRec1 = new TransactionRecord();
		tRec1.setAccount(sManualChecking);
		tRec1.setAmount("3.00");
		tRec1.setTransactionType("income");
		tRec1.setDate(h.getYesterdaysDate());
		
		OverviewPage op = new OverviewPage();
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		sChecking_before = op.checkingBalance.getText();
		sTotal_before = op.totalBalance.getText();
		Commentary.log(LogStatus.INFO, "Checking balance before adding the transaction ["+sChecking_before+"]");
		Commentary.log(LogStatus.INFO, "Total balance before adding the transaction ["+sTotal_before+"]");
		Double d = Double.parseDouble(tRec.getAmount())+Double.parseDouble(tRec1.getAmount());
		Double dChecking_before = h.processBalanceAmount(sChecking_before);
		Double dTotal_before = h.processBalanceAmount(sTotal_before);
		
		op.addTransaction.click();
		TransactionDetailPage td = new TransactionDetailPage();
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec);

		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type income, amount: "+tRec.getAmount());
		
		op.addTransaction.click();
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec1);

		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec1.getAccount()+"], transaction type income, amount: "+tRec1.getAmount());
		
		sChecking_after = op.checkingBalance.getText();
		sTotal_after = op.totalBalance.getText();
		Double dChecking_after = h.processBalanceAmount(sChecking_after);
		Double dTotal_after = h.processBalanceAmount(sTotal_after);
		
		double p = dChecking_before+d;
		System.out.println("***Expected -> "+p);
		
		int checking_Balance_Compare = Double.compare(dChecking_before+d, dChecking_after);
		int total_Balance_Compare = Double.compare(dTotal_before+d, dTotal_after);
		
//		if (dChecking_before+d==dChecking_after)
		if(checking_Balance_Compare == 0)
			Commentary.log(LogStatus.INFO, "PASS: Checking balance was ["+dChecking_before+"], added income transactions for ["+d+"], now the checking balance shows ["+dChecking_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Checking balance was ["+dChecking_before+"], added incomje transactions for ["+d+"], now the checking balance shows ["+dChecking_after+"]");
		
//		if (dTotal_before+d==dTotal_after)
		if(total_Balance_Compare == 0)
			Commentary.log(LogStatus.INFO, "PASS: Total balance was ["+dTotal_before+"], added income transactions for ["+d+"], now the total balance shows ["+dTotal_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Total balance was ["+dTotal_before+"], added income transactions for ["+d+"], now the total balance shows ["+dTotal_after+"]");
		
		sa.assertAll();
	}
	
	@Test(priority = 3, enabled = true)
	public void TBM3_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Add multiple[two] expense transactions for an online credit card account, verify credit card & total balance on overview screen accounts card.");
		
		String sBefore, sTotal_before, sAfter, sTotal_after;
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sOnlineCreditCard);
		tRec.setAmount("3.00");
		tRec.setTransactionType("expense");
		tRec.setDate(h.getYesterdaysDate());
		
		TransactionRecord tRec1 = new TransactionRecord();
		tRec1.setAccount(sOnlineCreditCard);
		tRec1.setAmount("2.00");
		tRec1.setTransactionType("expense");
		tRec1.setDate(h.getYesterdaysDate());
		
		OverviewPage op = new OverviewPage();
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		sBefore = op.creditBalance.getText();
		sTotal_before = op.totalBalance.getText();
		Commentary.log(LogStatus.INFO, "CreditCard balance before adding the transaction ["+sBefore+"]");
		Commentary.log(LogStatus.INFO, "Total balance before adding the transaction ["+sTotal_before+"]");
		Double d = Double.parseDouble(tRec.getAmount())+Double.parseDouble(tRec1.getAmount());
		Double dBefore = h.processBalanceAmount(sBefore);
		Double dTotal_before = h.processBalanceAmount(sTotal_before);
		
		op.addTransaction.click();
		TransactionDetailPage td = new TransactionDetailPage();
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec);

		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount: "+tRec.getAmount());
		
		op.addTransaction.click();
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec1);

		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec1.getAccount()+"], transaction type expense, amount: "+tRec1.getAmount());
		
		sAfter = op.creditBalance.getText();
		sTotal_after = op.totalBalance.getText();
		Double dAfter = h.processBalanceAmount(sAfter);
		Double dTotal_after = h.processBalanceAmount(sTotal_after);
		
		double p = dBefore-d;
		System.out.println("***Expected -> "+p);
		
		int checking_Balance_Compare = Double.compare(dBefore-d, dAfter);
		int total_Balance_Compare = Double.compare(dTotal_before-d, dTotal_after);
		
//		if (dBefore-d==dAfter)
		if(checking_Balance_Compare == 0)
			Commentary.log(LogStatus.INFO, "PASS: CC balance was ["+dBefore+"], added expense transactions for ["+d+"], now the Credit card balance shows ["+dAfter+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: CC balance was ["+dBefore+"], added expense transactions for ["+d+"], now the Credit card balance shows ["+dAfter+"]");
		
//		if (dTotal_before-d==dTotal_after)
		if(total_Balance_Compare == 0)
			Commentary.log(LogStatus.INFO, "PASS: Total balance was ["+dTotal_before+"], added expense transactions for ["+d+"], now the total balance shows ["+dTotal_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Total balance was ["+dTotal_before+"], added expense transactions for ["+d+"], now the total balance shows ["+dTotal_after+"]");
		
		sa.assertAll();		
	}
	
	@Test(priority = 4, enabled = true)
	public void TBM4_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Add multiple[two] income transactions for an online credit card account, verify credit card & total balance on overview screen accounts card.");
		
		String sBefore, sTotal_before, sAfter, sTotal_after;
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sOnlineCreditCard);
		tRec.setAmount("3.00");
		tRec.setTransactionType("income");
		tRec.setDate(h.getYesterdaysDate());
		
		TransactionRecord tRec1 = new TransactionRecord();
		tRec1.setAccount(sOnlineCreditCard);
		tRec1.setAmount("2.00");
		tRec1.setTransactionType("income");
		tRec1.setDate(h.getYesterdaysDate());
		
		OverviewPage op = new OverviewPage();
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		sBefore = op.creditBalance.getText();
		sTotal_before = op.totalBalance.getText();
		Commentary.log(LogStatus.INFO, "CreditCard balance before adding the transaction ["+sBefore+"]");
		Commentary.log(LogStatus.INFO, "Total balance before adding the transaction ["+sTotal_before+"]");
		Double d = Double.parseDouble(tRec.getAmount())+Double.parseDouble(tRec1.getAmount());
		Double dBefore = h.processBalanceAmount(sBefore);
		Double dTotal_before = h.processBalanceAmount(sTotal_before);
		
		op.addTransaction.click();
		TransactionDetailPage td = new TransactionDetailPage();
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec);

		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type Income, amount: "+tRec.getAmount());
		
		op.addTransaction.click();
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec1);

		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec1.getAccount()+"], transaction type Income, amount: "+tRec1.getAmount());
		
		sAfter = op.creditBalance.getText();
		sTotal_after = op.totalBalance.getText();
		Double dAfter = h.processBalanceAmount(sAfter);
		Double dTotal_after = h.processBalanceAmount(sTotal_after);
		
		double p = dBefore+d;
		System.out.println("***Expected -> "+p);
		
		int checking_Balance_Compare = Double.compare(dBefore+d, dAfter);
		int total_Balance_Compare = Double.compare(dTotal_before+d, dTotal_after);
		
//		if (dBefore+d==dAfter)
		if(checking_Balance_Compare == 0)
			Commentary.log(LogStatus.INFO, "PASS: CC balance was ["+dBefore+"], added income transactions for ["+d+"], now the Creditcard balance shows ["+dAfter+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: CC balance was ["+dBefore+"], added income transactions for ["+d+"], now the creditcard balance shows ["+dAfter+"]");
		
//		if (dTotal_before+d==dTotal_after)
		if(total_Balance_Compare == 0)
			Commentary.log(LogStatus.INFO, "PASS: Total balance was ["+dTotal_before+"], added Income transactions for ["+d+"], now the total balance shows ["+dTotal_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Total balance was ["+dTotal_before+"], added Income transactions for ["+d+"], now the total balance shows ["+dTotal_after+"]");
		
		sa.assertAll();	
	}
	
	@Test(priority = 5, enabled = true)
	public void TBM5_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Add multiple[two] expense transactions for an online checking account, verify account, category checking & total balance on Banking and Credit Screen.");
		
		String sChecking_before, sTotal_before, sChecking_after, sTotal_after;
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sOnlineChecking);
		tRec.setAmount("5.00");
		tRec.setTransactionType("expense");
		tRec.setDate(h.getYesterdaysDate());
		
		TransactionRecord tRec1 = new TransactionRecord();
		tRec1.setAccount(sOnlineChecking);
		tRec1.setAmount("3.00");
		tRec1.setTransactionType("expense");
		tRec1.setDate(h.getYesterdaysDate());
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		sChecking_before = bcc.getCheckingBalance();
		sTotal_before = bcc.txtTodaysBalanceAmount.getText();
		Commentary.log(LogStatus.INFO, "Checking balance before adding the transaction ["+sChecking_before+"]");
		Commentary.log(LogStatus.INFO, "Total balance before adding the transaction ["+sTotal_before+"]");
		Double d = Double.parseDouble(tRec.getAmount())+Double.parseDouble(tRec1.getAmount());
		Double dChecking_before = h.processBalanceAmount(sChecking_before);
		Double dTotal_before = h.processBalanceAmount(sTotal_before);
		
		bcc.getAccount(tRec.getAccount()).click();
	   	Thread.sleep(2000);
	   
	   	TransactionsPage txnPage = new TransactionsPage();	
	   	String sAccountBalance_before = txnPage.txtTodaysBalanceAmount.getText();
	   	Double dAccountBalance_before = h.processBalanceAmount(sAccountBalance_before);
	   	Commentary.log(LogStatus.INFO, tRec.getAccount()+" account balance before adding the transactions ["+sAccountBalance_before+"]");
	   	txnPage.addTransaction.click();
	   	
		TransactionDetailPage td = new TransactionDetailPage();
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec);
		Verify.waitForObject(txnPage.txtTodaysBalance, 2);
		if (txnPage.verifyAccount(tRec.getAccount()) == false)
	   		Commentary.log(sa, LogStatus.FAIL,"Account name verification on Transaction page failed.");
			Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount: "+tRec.getAmount());
		
		txnPage.addTransaction.click();
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec1);
		Verify.waitForObject(txnPage.txtTodaysBalance, 2);
		if (txnPage.verifyAccount(tRec.getAccount()) == false)
	   		Commentary.log(sa, LogStatus.FAIL,"Account name verification on Transaction page failed.");
			Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec1.getAccount()+"], transaction type expense, amount: "+tRec1.getAmount());
		
		String sAccountBalance_after = txnPage.txtTodaysBalanceAmount.getText();
		Double dAccountBalance_after = h.processBalanceAmount(sAccountBalance_after);
		
		txnPage.backButton.click();
		Thread.sleep(2000);
		
		sTotal_after = bcc.txtTodaysBalanceAmount.getText();
		sChecking_after = bcc.getCheckingBalance();
		Double dChecking_after = h.processBalanceAmount(sChecking_after);
		Double dTotal_after = h.processBalanceAmount(sTotal_after);
		
		double p = dChecking_before-d;
		System.out.println("***Expected -> "+p);
		
		int account_Balance_Compare = Double.compare(dAccountBalance_before-d, dAccountBalance_after);
		int checking_Balance_Compare = Double.compare(dChecking_before-d, dChecking_after);
		int total_Balance_Compare = Double.compare(dTotal_before-d, dTotal_after);
		
//		if (dAccountBalance_before-d==dAccountBalance_after)
		if(account_Balance_Compare == 0)
			Commentary.log(LogStatus.INFO, tRec.getAccount()+ " account balance was ["+dAccountBalance_before+"], added expense transactions for ["+d+"], now the checking balance shows ["+dAccountBalance_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: "+ tRec.getAccount()+" account balance was ["+dAccountBalance_before+"], added expense transactions for ["+d+"], now the checking balance shows ["+dAccountBalance_after+"]");
		
//		if (dChecking_before-d==dChecking_after)
		if(checking_Balance_Compare == 0)
			Commentary.log(LogStatus.INFO, "PASS: Checking balance was ["+dChecking_before+"], added expense transactions for ["+d+"], now the checking balance shows ["+dChecking_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Checking balance was ["+dChecking_before+"], added expense transactions for ["+d+"], now the checking balance shows ["+dChecking_after+"]");
		
//		if (dTotal_before-d==dTotal_after)
		if(total_Balance_Compare ==0)
			Commentary.log(LogStatus.INFO, "PASS: Total balance was ["+dTotal_before+"], added expense transactions for ["+d+"], now the total balance shows ["+dTotal_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Total balance was ["+dTotal_before+"], added expense transactions for ["+d+"], now the total balance shows ["+dTotal_after+"]");
		
		sa.assertAll();		
	}
	
	@Test(priority = 6, enabled = true)
	public void TBM6_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Add multiple[two] Income transactions for an online checking account, verify account, category checking & total balance on Banking and Credit Screen.");
		
		String sChecking_before, sTotal_before, sChecking_after, sTotal_after;
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sOnlineChecking);
		tRec.setAmount("5.00");
		tRec.setTransactionType("income");
		tRec.setDate(h.getYesterdaysDate());
		
		TransactionRecord tRec1 = new TransactionRecord();
		tRec1.setAccount(sOnlineChecking);
		tRec1.setAmount("3.00");
		tRec1.setTransactionType("income");
		tRec1.setDate(h.getYesterdaysDate());
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		sChecking_before = bcc.getCheckingBalance();
		sTotal_before = bcc.txtTodaysBalanceAmount.getText();
		Commentary.log(LogStatus.INFO, "Checking balance before adding the transaction ["+sChecking_before+"]");
		Commentary.log(LogStatus.INFO, "Total balance before adding the transaction ["+sTotal_before+"]");
		Double d = Double.parseDouble(tRec.getAmount())+Double.parseDouble(tRec1.getAmount());
		Double dChecking_before = h.processBalanceAmount(sChecking_before);
		Double dTotal_before = h.processBalanceAmount(sTotal_before);
		
		bcc.getAccount(tRec.getAccount()).click();
	   	Thread.sleep(2000);
	   
	   	TransactionsPage txnPage = new TransactionsPage();	
	   	String sAccountBalance_before = txnPage.txtTodaysBalanceAmount.getText();
	   	Double dAccountBalance_before = h.processBalanceAmount(sAccountBalance_before);
	   	Commentary.log(LogStatus.INFO, tRec.getAccount()+" account balance before adding the transactions ["+sAccountBalance_before+"]");
	   	txnPage.addTransaction.click();
	   	
		TransactionDetailPage td = new TransactionDetailPage();
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec);
		Verify.waitForObject(txnPage.txtTodaysBalance, 2);
		
		if (txnPage.verifyAccount(tRec.getAccount()) == false)
	   		Commentary.log(sa, LogStatus.FAIL,"Account name verification on Transaction page failed.");
		
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type income, amount: "+tRec.getAmount());
		
		txnPage.addTransaction.click();
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec1);
		Verify.waitForObject(txnPage.txtTodaysBalance, 2);
		if (txnPage.verifyAccount(tRec.getAccount()) == false)
	   		Commentary.log(sa, LogStatus.FAIL,"Account name verification on Transaction page failed.");
		
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec1.getAccount()+"], transaction type Income, amount: "+tRec1.getAmount());
		
		String sAccountBalance_after = txnPage.txtTodaysBalanceAmount.getText();
		Double dAccountBalance_after = h.processBalanceAmount(sAccountBalance_after);
		
		txnPage.backButton.click();
		Thread.sleep(2000);
		sTotal_after = bcc.txtTodaysBalanceAmount.getText();
		sChecking_after = bcc.getCheckingBalance();
		Double dChecking_after = h.processBalanceAmount(sChecking_after);
		Double dTotal_after = h.processBalanceAmount(sTotal_after);

		double p = dAccountBalance_before+d;
		System.out.println("***Expected -> "+p);
		
		int account_Balance_Compare = Double.compare(dAccountBalance_before+d, dAccountBalance_after);
		int checking_Balance_Compare = Double.compare(dChecking_before+d, dChecking_after);
		int total_Balance_Compare = Double.compare(dTotal_before+d, dTotal_after);
		
//		if (dAccountBalance_before+d==dAccountBalance_after)
		if(account_Balance_Compare == 0)
			Commentary.log(LogStatus.INFO, tRec.getAccount()+ " account balance was ["+dAccountBalance_before+"], added income transactions for ["+d+"], now the checking balance shows ["+dAccountBalance_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: "+ tRec.getAccount()+" account balance was ["+dAccountBalance_before+"], added income transactions for ["+d+"], now the checking balance shows ["+dAccountBalance_after+"]");
		
		p = dChecking_before+d;
		
//		if (dChecking_before+d==dChecking_after)
		if(checking_Balance_Compare == 0)
			Commentary.log(LogStatus.INFO, "PASS: Checking balance was ["+dChecking_before+"], added income transactions for ["+d+"], now the checking balance shows ["+dChecking_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Checking balance was ["+dChecking_before+"], added income transactions for ["+d+"], now the checking balance shows ["+dChecking_after+"]");
		
		p = dTotal_before+d;

//		if (dTotal_before+d==dTotal_after)
		if(total_Balance_Compare == 0)
			Commentary.log(LogStatus.INFO, "PASS: Total balance was ["+dTotal_before+"], added income transactions for ["+d+"], now the total balance shows ["+dTotal_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Total balance was ["+dTotal_before+"], added income transactions for ["+d+"], now the total balance shows ["+dTotal_after+"]");
		
		sa.assertAll();	
	}
	
	@Test(priority = 7, enabled = true)
	public void TBM7_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Add multiple[two] expense transactions for an online creditcard account, verify account, category credit & total balance on Banking and Credit Screen.");
		
		String sCCtype_before, sTotal_before, sChecking_after, sTotal_after;
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualCreditCard);
		tRec.setAmount("5.00");
		tRec.setTransactionType("expense");
		tRec.setDate(h.getYesterdaysDate());
		
		TransactionRecord tRec1 = new TransactionRecord();
		tRec1.setAccount(sManualCreditCard);
		tRec1.setAmount("3.00");
		tRec1.setTransactionType("expense");
		tRec1.setDate(h.getYesterdaysDate());
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		sTotal_before = bcc.txtTodaysBalanceAmount.getText();
		Commentary.log(LogStatus.INFO, "Total balance before adding the transactions ["+sTotal_before+"]");
		sCCtype_before = bcc.getCreditCardsBalance();
		Commentary.log(LogStatus.INFO, "CREDIT balance before adding the transactions ["+sCCtype_before+"]");
		Double d = Double.parseDouble(tRec.getAmount())+Double.parseDouble(tRec1.getAmount());
		Double dChecking_before = h.processBalanceAmount(sCCtype_before);
		Double dTotal_before = h.processBalanceAmount(sTotal_before);
		
		bcc.getAccount(tRec.getAccount()).click();
	   	Thread.sleep(2000);
	   
	   	TransactionsPage txnPage = new TransactionsPage();	
	   	String sAccountBalance_before = txnPage.txtTodaysBalanceAmount.getText();
	   	Double dAccountBalance_before = h.processBalanceAmount(sAccountBalance_before);
	   	Commentary.log(LogStatus.INFO, tRec.getAccount()+" account balance before adding the transactions ["+sAccountBalance_before+"]");
	   	txnPage.addTransaction.click();
	   	
		TransactionDetailPage td = new TransactionDetailPage();
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec);
		Verify.waitForObject(txnPage.txtTodaysBalance, 2);
		
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount: "+tRec.getAmount());
		
		txnPage.addTransaction.click();
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec1);
		Verify.waitForObject(txnPage.txtTodaysBalance, 2);
		
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec1.getAccount()+"], transaction type expense, amount: "+tRec1.getAmount());
		
		String sAccountBalance_after = txnPage.txtTodaysBalanceAmount.getText();
		Double dAccountBalance_after = h.processBalanceAmount(sAccountBalance_after);
		
		txnPage.navigateBackToDashboard();
		op.navigateToAcctList();
		sTotal_after = bcc.txtTodaysBalanceAmount.getText();
		sChecking_after = bcc.getCreditCardsBalance();
		
		Double dChecking_after = h.processBalanceAmount(sChecking_after);
		Double dTotal_after = h.processBalanceAmount(sTotal_after);
		
		double p = dAccountBalance_before-d;
		System.out.println("***Expected -> "+p);
		
		int account_Balance_Compare = Double.compare(dAccountBalance_before-d, dAccountBalance_after);
		int checking_Balance_Compare = Double.compare(dChecking_before-d, dChecking_after);
		int total_Balance_Compare = Double.compare(dTotal_before-d, dTotal_after);
		
//		if (dAccountBalance_before-d==dAccountBalance_after)
		if(account_Balance_Compare == 0)
			Commentary.log(LogStatus.INFO, tRec.getAccount()+ " account balance was ["+dAccountBalance_before+"], added expense transactions for ["+d+"], now the checking balance shows ["+dAccountBalance_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: "+ tRec.getAccount()+" account balance was ["+dAccountBalance_before+"], added expense transactions for ["+d+"], now the checking balance shows ["+dAccountBalance_after+"]");
		
		p = dChecking_before-d;
		
//		if (dChecking_before-d==dChecking_after)
		if(checking_Balance_Compare == 0)
			Commentary.log(LogStatus.INFO, "PASS: CreditCard balance was ["+dChecking_before+"], added expense transactions for ["+d+"], now the checking balance shows ["+dChecking_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: CreditCard balance was ["+dChecking_before+"], added expense transactions for ["+d+"], now the checking balance shows ["+dChecking_after+"]");
		
		p = dTotal_before-d;

//		if (dTotal_before-d==dTotal_after)
		if(total_Balance_Compare == 0)
			Commentary.log(LogStatus.INFO, "PASS: Total balance was ["+dTotal_before+"], added expense transactions for ["+d+"], now the total balance shows ["+dTotal_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Total balance was ["+dTotal_before+"], added expense transactions for ["+d+"], now the total balance shows ["+dTotal_after+"]");
		
		sa.assertAll();	
	}
	
}
