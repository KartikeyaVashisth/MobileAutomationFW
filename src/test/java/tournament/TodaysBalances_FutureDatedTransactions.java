package tournament;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.OverviewPage;
import dugout.SignInPage;
import dugout.TransactionDetailPage;
import referee.Commentary;
import referee.Verify;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;

public class TodaysBalances_FutureDatedTransactions extends Recovery {
	
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
	
	@Test(priority = 1)
	public void TBFT1_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "Add a Future Dated expense transaction for a manual checking account, verify checking & total balance on overview screen accounts card");
		String sChecking_before, sTotal_before, sChecking_after, sTotal_after;
		
		SignInPage signIn = new SignInPage();
		signIn.signIn(sUserName, sPassword, sDataset);
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualChecking);
		tRec.setAmount("5.00");
		tRec.setTransactionType("expense");
		tRec.setDate(h.getFutureDaysDate(2));
		h.getContext();
		
		OverviewPage op = new OverviewPage();
		sChecking_before = op.checkingBalance.getText();
		sTotal_before = op.totalBalance.getText();
		Commentary.log(LogStatus.INFO, "Checking balance before adding FutureDated transaction ["+sChecking_before+"]");
		Commentary.log(LogStatus.INFO, "Total balance before adding FutureDated transaction ["+sTotal_before+"]");
		Double d = Double.parseDouble(tRec.getAmount());
		Double dChecking_before = h.processBalanceAmount(sChecking_before);
		Double dTotal_before = h.processBalanceAmount(sTotal_before);
		
		op.addTransaction.click();
		TransactionDetailPage td = new TransactionDetailPage();
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount"+tRec.getAmount());
		
		Verify.waitForObject(op.addTransaction, 5);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 5);
		h.getContext();
		sChecking_after = op.checkingBalance.getText();
		sTotal_after = op.totalBalance.getText();
		Double dChecking_after = h.processBalanceAmount(sChecking_after);
		Double dTotal_after = h.processBalanceAmount(sTotal_after);
		
		Integer balanceCompare = Double.compare(dChecking_before, dChecking_after);
		System.out.println(balanceCompare);
		Integer totalCompare = Double.compare(dTotal_before, dTotal_after);
		System.out.println(totalCompare);
		
		
		if (balanceCompare==0)
			Commentary.log(LogStatus.INFO, "Checking balance was ["+dChecking_before+"], added Future Dated expense transaction for ["+tRec.getAmount()+"], now the checking balance shows ["+dChecking_after+"]");
		else
			Commentary.log(LogStatus.FAIL, "FAIL: Checking balance was ["+dChecking_before+"], added Future Dated expense transaction for ["+tRec.getAmount()+"], now the checking balance shows ["+dChecking_after+"]");
		
		if (totalCompare==0)
			Commentary.log(LogStatus.INFO, "Total balance was ["+dTotal_before+"], added Future Dated expense transaction for ["+tRec.getAmount()+"], now the total balance shows ["+dTotal_after+"]");
		else
			Commentary.log(LogStatus.FAIL, "FAIL: Total balance was ["+dTotal_before+"], added Future Dated expense transaction for ["+tRec.getAmount()+"], now the total balance shows ["+dTotal_after+"]");
		
		sa.assertAll();	
		
	}
	
	@Test(priority = 2)
	public void TBFT2_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "Add a futureDated income transaction for a manual checking account, verify checking & total balance on overview screen accounts card");
		String sChecking_before, sTotal_before, sChecking_after, sTotal_after;
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualChecking);
		tRec.setAmount("5.00");
		tRec.setTransactionType("income");
		tRec.setDate(h.getFutureDaysDate(2));
		h.getContext();
		
		
		OverviewPage op = new OverviewPage();
		sChecking_before = op.checkingBalance.getText();
		sTotal_before = op.totalBalance.getText();
		Commentary.log(LogStatus.INFO, "Checking balance before adding FutureDated transaction ["+sChecking_before+"]");
		Commentary.log(LogStatus.INFO, "Total balance before adding FutureDated transaction ["+sTotal_before+"]");
		Double d = Double.parseDouble(tRec.getAmount());
		Double dChecking_before = h.processBalanceAmount(sChecking_before);
		Double dTotal_before = h.processBalanceAmount(sTotal_before);
		
		op.addTransaction.click();
		TransactionDetailPage td = new TransactionDetailPage();
		td.addTransaction(tRec);
		Verify.waitForObject(op.addTransaction, 5);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 5);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type income, amount"+tRec.getAmount());
		
		h.getContext();
		sChecking_after = op.checkingBalance.getText();
		sTotal_after = op.totalBalance.getText();
		Double dChecking_after = h.processBalanceAmount(sChecking_after);
		Double dTotal_after = h.processBalanceAmount(sTotal_after);
		
		Integer balanceCompare = Double.compare(dChecking_before, dChecking_after);
		System.out.println(balanceCompare);
		Integer totalCompare = Double.compare(dTotal_before, dTotal_after);
		System.out.println(totalCompare);
		
		if (balanceCompare==0)
			Commentary.log(LogStatus.INFO, "Checking balance was ["+dChecking_before+"], added FutureDated income transaction for ["+tRec.getAmount()+"], now the checking balance shows ["+dChecking_after+"]");
		else
			Commentary.log(LogStatus.FAIL, "FAIL: Checking balance was ["+dChecking_before+"], added FutureDated income transaction for ["+tRec.getAmount()+"], now the checking balance shows ["+dChecking_after+"]");
		
		if (totalCompare==0)
			Commentary.log(LogStatus.INFO, "Total balance was ["+dTotal_before+"], added FutureDated income transaction for ["+tRec.getAmount()+"], now the total balance shows ["+dTotal_after+"]");
		else
			Commentary.log(LogStatus.FAIL, "FAIL: Total balance was ["+dTotal_before+"], added FutureDated income transaction for ["+tRec.getAmount()+"], now the total balance shows ["+dTotal_after+"]");
		
		sa.assertAll();	
		
	}
	
	@Test(priority = 3)
	public void TBFT3_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "Add multiple[two] FutureDated expense transactions for an online checking account, verify checking & total balance on overview screen accounts card");
		String sChecking_before, sTotal_before, sChecking_after, sTotal_after;
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sOnlineChecking);
		tRec.setAmount("5.00");
		tRec.setTransactionType("expense");
		tRec.setDate(h.getFutureDaysDate(2));
		h.getContext();
		
		TransactionRecord tRec1 = new TransactionRecord();
		tRec1.setAccount(sOnlineChecking);
		tRec1.setAmount("3.00");
		tRec1.setTransactionType("expense");
		tRec1.setDate(h.getFutureDaysDate(2));
		
		OverviewPage op = new OverviewPage();
		sChecking_before = op.checkingBalance.getText();
		sTotal_before = op.totalBalance.getText();
		Commentary.log(LogStatus.INFO, "Checking balance before adding the transaction ["+sChecking_before+"]");
		Commentary.log(LogStatus.INFO, "Total balance before adding the transaction ["+sTotal_before+"]");
		Double d = Double.parseDouble(tRec.getAmount())+Double.parseDouble(tRec1.getAmount());
		Double dChecking_before = h.processBalanceAmount(sChecking_before);
		Double dTotal_before = h.processBalanceAmount(sTotal_before);
		
		op.addTransaction.click();
		TransactionDetailPage td = new TransactionDetailPage();
		td.addTransaction(tRec);
		Verify.waitForObject(op.addTransaction, 5);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 5);
		Commentary.log(LogStatus.INFO, "FutureDated Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount"+tRec.getAmount());
		h.getContext();
		
		Verify.waitForObject(op.addTransaction, 5);
		op.addTransaction.click();
		td.addTransaction(tRec1);
		Verify.waitForObject(op.addTransaction, 5);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 5);
		Commentary.log(LogStatus.INFO, "FutureDated Transaction added successfully for the account ["+tRec1.getAccount()+"], transaction type expense, amount"+tRec1.getAmount());
		h.getContext();
		
		Verify.waitForObject(op.addTransaction, 5);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 5);
		
		sChecking_after = op.checkingBalance.getText();
		sTotal_after = op.totalBalance.getText();
		Double dChecking_after = h.processBalanceAmount(sChecking_after);
		Double dTotal_after = h.processBalanceAmount(sTotal_after);
		
		Integer balanceCompare = Double.compare(dChecking_before, dChecking_after);
		System.out.println(balanceCompare);
		Integer totalCompare = Double.compare(dTotal_before, dTotal_after);
		System.out.println(totalCompare);
		
		
		if (balanceCompare==0)
			Commentary.log(LogStatus.INFO, "Checking balance was ["+dChecking_before+"], added FutureDated expense transactions for ["+d+"], now the checking balance shows ["+dChecking_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Checking balance was ["+dChecking_before+"], added FutureDated expense transactions for ["+d+"], now the checking balance shows ["+dChecking_after+"]");
		
		if (totalCompare==0)
			Commentary.log(LogStatus.INFO, "Total balance was ["+dTotal_before+"], added FutureDated expense transactions for ["+d+"], now the total balance shows ["+dTotal_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Total balance was ["+dTotal_before+"], added FutureDated expense transactions for ["+d+"], now the total balance shows ["+dTotal_after+"]");
		
		sa.assertAll();	
		
	}
	
	@Test(priority = 4)
	public void TBFT4_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "Add multiple[two] FutureDated income transactions for a manual checking account, verify checking & total balance on overview screen accounts card");
		String sChecking_before, sTotal_before, sChecking_after, sTotal_after;
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualChecking);
		tRec.setAmount("4.00");
		tRec.setTransactionType("income");
		tRec.setDate(h.getFutureDaysDate(2));
		h.getContext();
		
		TransactionRecord tRec1 = new TransactionRecord();
		tRec1.setAccount(sManualChecking);
		tRec1.setAmount("3.00");
		tRec1.setTransactionType("income");
		tRec1.setDate(h.getFutureDaysDate(2));
		
		OverviewPage op = new OverviewPage();
		sChecking_before = op.checkingBalance.getText();
		sTotal_before = op.totalBalance.getText();
		Commentary.log(LogStatus.INFO, "Checking balance before adding the transaction ["+sChecking_before+"]");
		Commentary.log(LogStatus.INFO, "Total balance before adding the transaction ["+sTotal_before+"]");
		Double d = Double.parseDouble(tRec.getAmount())+Double.parseDouble(tRec1.getAmount());
		Double dChecking_before = h.processBalanceAmount(sChecking_before);
		Double dTotal_before = h.processBalanceAmount(sTotal_before);
		
		op.addTransaction.click();
		TransactionDetailPage td = new TransactionDetailPage();
		td.addTransaction(tRec);
		Verify.waitForObject(op.addTransaction, 5);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 5);
		Commentary.log(LogStatus.INFO, "FutureDated Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type income, amount"+tRec.getAmount());
		h.getContext();
		
		Verify.waitForObject(op.addTransaction, 5);
		op.addTransaction.click();
		td.addTransaction(tRec1);
		Verify.waitForObject(op.addTransaction, 5);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 5);
		Commentary.log(LogStatus.INFO, "FutureDated Transaction added successfully for the account ["+tRec1.getAccount()+"], transaction type income, amount"+tRec1.getAmount());
		h.getContext();
		
		Verify.waitForObject(op.addTransaction, 5);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 5);
		
		sChecking_after = op.checkingBalance.getText();
		sTotal_after = op.totalBalance.getText();
		Double dChecking_after = h.processBalanceAmount(sChecking_after);
		Double dTotal_after = h.processBalanceAmount(sTotal_after);
		
		Integer balanceCompare = Double.compare(dChecking_before, dChecking_after);
		System.out.println(balanceCompare);
		Integer totalCompare = Double.compare(dTotal_before, dTotal_after);
		System.out.println(totalCompare);
		
		
		
		if (balanceCompare==0)
			Commentary.log(LogStatus.INFO, "Checking balance was ["+dChecking_before+"], added FutureDated income transactions for ["+d+"], now the checking balance shows ["+dChecking_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Checking balance was ["+dChecking_before+"], added FutureDated income transactions for ["+d+"], now the checking balance shows ["+dChecking_after+"]");
		
		if (totalCompare==0)
			Commentary.log(LogStatus.INFO, "Total balance was ["+dTotal_before+"], added FutureDated income transactions for ["+d+"], now the total balance shows ["+dTotal_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Total balance was ["+dTotal_before+"], added FutureDated income transactions for ["+d+"], now the total balance shows ["+dTotal_after+"]");
		
		sa.assertAll();	
		
	}
	
	@Test(priority = 5)
	public void TBFT5_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "Add multiple[two] FutureDated expense transactions for an online credit card account, verify credit card & total balance on overview screen accounts card");
		String sBefore, sTotal_before, sAfter, sTotal_after;
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sOnlineCreditCard);
		tRec.setAmount("3.00");
		tRec.setTransactionType("expense");
		tRec.setDate(h.getFutureDaysDate(2));
		h.getContext();
		
		TransactionRecord tRec1 = new TransactionRecord();
		tRec1.setAccount(sOnlineCreditCard);
		tRec1.setAmount("2.00");
		tRec1.setTransactionType("expense");
		tRec1.setDate(h.getFutureDaysDate(2));
		
		OverviewPage op = new OverviewPage();
		sBefore = op.creditBalance.getText();
		sTotal_before = op.totalBalance.getText();
		Commentary.log(LogStatus.INFO, "CreditCard balance before adding FutureDated Expense transactions ["+sBefore+"]");
		Commentary.log(LogStatus.INFO, "Total balance before adding FutureDated Expense transactions ["+sTotal_before+"]");
		Double d = Double.parseDouble(tRec.getAmount())+Double.parseDouble(tRec1.getAmount());
		Double dBefore = h.processBalanceAmount(sBefore);
		Double dTotal_before = h.processBalanceAmount(sTotal_before);
		
		op.addTransaction.click();
		TransactionDetailPage td = new TransactionDetailPage();
		td.addTransaction(tRec);
		Verify.waitForObject(op.addTransaction, 5);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 5);
		Commentary.log(LogStatus.INFO, "FutureDated Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount"+tRec.getAmount());
		h.getContext();
		
		Verify.waitForObject(op.addTransaction, 5);
		op.addTransaction.click();
		td.addTransaction(tRec1);
		Verify.waitForObject(op.addTransaction, 5);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 5);
		Commentary.log(LogStatus.INFO, "FutureDated Transaction added successfully for the account ["+tRec1.getAccount()+"], transaction type expense, amount"+tRec1.getAmount());
		h.getContext();
		
		Verify.waitForObject(op.addTransaction, 5);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 5);
		
		sAfter = op.creditBalance.getText();
		sTotal_after = op.totalBalance.getText();
		Double dAfter = h.processBalanceAmount(sAfter);
		Double dTotal_after = h.processBalanceAmount(sTotal_after);
		
		Integer balanceCompare = Double.compare(dBefore, dAfter);
		System.out.println(balanceCompare);
		Integer totalCompare = Double.compare(dTotal_before, dTotal_after);
		System.out.println(totalCompare);
		
		
		if (balanceCompare==0)
			Commentary.log(LogStatus.INFO, "CC balance was ["+dBefore+"], added FutureDated expense transactions for ["+d+"], now the Credit card balance shows ["+dAfter+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: CC balance was ["+dBefore+"], added FutureDated expense transactions for ["+d+"], now the Credit card balance shows ["+dAfter+"]");
		
		if (totalCompare==0)
			Commentary.log(LogStatus.INFO, "Total balance was ["+dTotal_before+"], added FutureDated expense transactions for ["+d+"], now the total balance shows ["+dTotal_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Total balance was ["+dTotal_before+"], added FutureDated expense transactions for ["+d+"], now the total balance shows ["+dTotal_after+"]");
		
		sa.assertAll();	
		
	}
	
	@Test(priority = 6)
	public void TBFT6_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "Add multiple[two] FutureDated income transactions for an online credit card account, verify credit card & total balance on overview screen accounts card");
		String sBefore, sTotal_before, sAfter, sTotal_after;
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sOnlineCreditCard);
		tRec.setAmount("3.00");
		tRec.setTransactionType("income");
		tRec.setDate(h.getFutureDaysDate(2));
		h.getContext();
		
		TransactionRecord tRec1 = new TransactionRecord();
		tRec1.setAccount(sOnlineCreditCard);
		tRec1.setAmount("2.00");
		tRec1.setTransactionType("income");
		tRec1.setDate(h.getFutureDaysDate(2));
		
		OverviewPage op = new OverviewPage();
		sBefore = op.creditBalance.getText();
		sTotal_before = op.totalBalance.getText();
		Commentary.log(LogStatus.INFO, "CreditCard balance before adding FutureDated transaction ["+sBefore+"]");
		Commentary.log(LogStatus.INFO, "Total balance before adding FutureDated transaction ["+sTotal_before+"]");
		Double d = Double.parseDouble(tRec.getAmount())+Double.parseDouble(tRec1.getAmount());
		Double dBefore = h.processBalanceAmount(sBefore);
		Double dTotal_before = h.processBalanceAmount(sTotal_before);
		
		op.addTransaction.click();
		TransactionDetailPage td = new TransactionDetailPage();
		td.addTransaction(tRec);
		Verify.waitForObject(op.addTransaction, 5);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 5);
		Commentary.log(LogStatus.INFO, "FutureDated Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type Income, amount"+tRec.getAmount());
		h.getContext();
		
		Verify.waitForObject(op.addTransaction, 5);
		op.addTransaction.click();
		td.addTransaction(tRec1);
		Verify.waitForObject(op.addTransaction, 5);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 5);
		Commentary.log(LogStatus.INFO, "FutureDated Transaction added successfully for the account ["+tRec1.getAccount()+"], transaction type Income, amount"+tRec1.getAmount());
		h.getContext();
		
		Verify.waitForObject(op.addTransaction, 5);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 5);
		
		sAfter = op.creditBalance.getText();
		sTotal_after = op.totalBalance.getText();
		Double dAfter = h.processBalanceAmount(sAfter);
		Double dTotal_after = h.processBalanceAmount(sTotal_after);
		
		Integer balanceCompare = Double.compare(dBefore, dAfter);
		System.out.println(balanceCompare);
		Integer totalCompare = Double.compare(dTotal_before, dTotal_after);
		System.out.println(totalCompare);
		
		
		if (balanceCompare==0)
			Commentary.log(LogStatus.INFO, "CC balance was ["+dBefore+"], added FutureDated income transactions for ["+d+"], now the Creditcard balance shows ["+dAfter+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: CC balance was ["+dBefore+"], added FutureDated income transactions for ["+d+"], now the creditcard balance shows ["+dAfter+"]");
		
		if (totalCompare==0)
			Commentary.log(LogStatus.INFO, "Total balance was ["+dTotal_before+"], added FutureDated Income transactions for ["+d+"], now the total balance shows ["+dTotal_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Total balance was ["+dTotal_before+"], added FutureDated Income transactions for ["+d+"], now the total balance shows ["+dTotal_after+"]");
		
		sa.assertAll();	
		
	}

}
