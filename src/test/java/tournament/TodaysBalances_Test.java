package tournament;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.BankingAndCreditCardPage;
import dugout.OverviewPage;
import dugout.SignInPage;
import dugout.TransactionDetailPage;
import io.appium.java_client.MobileElement;
import referee.Commentary;
import referee.Verify;
import support.Engine;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;


public class TodaysBalances_Test extends Recovery {
	
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
	
	@Test(priority = 0)
	public void TB1_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		
		String sOverviewChecking, sOverviewCash, sOverviewCC, sOverviewSavingsBalance, sOverviewOtherBalance, sTotal;
		String sChecking, sCash, sCC, sSavings, sOther, sTodays;
		
		Commentary.log(LogStatus.INFO, "Verifying balances on accounts card matching with account list screen");
		
		SignInPage signIn = new SignInPage();
		signIn.signIn(sUserName, sPassword, sDataset);
		
		// get balances from accounts card
		OverviewPage op = new OverviewPage();
		sOverviewChecking = op.checkingBalance.getText();
		sOverviewCash = op.cashBalance.getText();
		sOverviewCC = op.creditBalance.getText();
		sOverviewSavingsBalance = op.savingsBalance.getText();
		//sOverviewOtherBalance = op.otherBalance.getText();
		sTotal = op.totalBalance.getText();
		
		op.navigateToAcctList();
		
		Helper h = new Helper();
		h.getContext();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		sTodays = bcc.txtTodaysBalanceAmount.getText();
		sChecking=bcc.getCheckingBalance();
		sCash=bcc.getCashBalance();
		sCC=bcc.getCreditCardsBalance();
		h.getContext();
		sSavings=bcc.getSavingsBalance();
		
		
		if (sOverviewChecking.equals(sChecking))
			Commentary.log(LogStatus.INFO, "Checking balance on accounts card ["+sOverviewChecking+"]  matches with account list screen balance ["+sChecking+"]");
		else
			Commentary.log(sa,LogStatus.FAIL, "FAIL: Checking balance on accounts card ["+sOverviewChecking+"], Checking balance on account list screen ["+sChecking+"]");
		
		if (sOverviewCash.equals(sCash))
			Commentary.log(LogStatus.INFO, "Cash balance on accounts card ["+sOverviewCash+"]  matches with account list screen balance ["+sCash+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Cash balance on accounts card ["+sOverviewCash+"], Cash balance on account list screen ["+sCash+"]");
		
		if (sOverviewCC.equals(sCC))
			Commentary.log(LogStatus.INFO, "CreditCard balance on accounts card ["+sOverviewCC+"]  matches with account list screen balance ["+sCC+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: CreditCard balance on accounts card ["+sOverviewCC+"], CreditCard balance on account list screen ["+sCC+"]");
		
		if (sOverviewSavingsBalance.equals(sSavings))
			Commentary.log(LogStatus.INFO, "Savings balance on accounts card ["+sOverviewSavingsBalance+"]  matches with account list screen balance ["+sSavings+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Savings balance on accounts card ["+sOverviewSavingsBalance+"], Savings balance on account list screen ["+sSavings+"]");
		
		if (sTotal.equals(sTodays))
			Commentary.log(LogStatus.INFO, "Total balance on accounts card ["+sTotal+"]  matches with account list screen balance ["+sTodays+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Total balance on accounts card ["+sTotal+"], Today's balance on account list screen ["+sTodays+"]");
		
		sa.assertAll();	
		
	}
	
	@Test(priority = 1)
	public void TB2_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "Add an expense transaction for a manual checking account, verify checking & total balance on overview screen accounts card");
		String sChecking_before, sTotal_before, sChecking_after, sTotal_after;
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualChecking);
		tRec.setAmount("5.00");
		tRec.setTransactionType("expense");
		tRec.setDate(h.getYesterdaysDate());
		h.getContext();
		
		OverviewPage op = new OverviewPage();
		sChecking_before = op.checkingBalance.getText();
		sTotal_before = op.totalBalance.getText();
		Commentary.log(LogStatus.INFO, "Checking balance before adding the transaction ["+sChecking_before+"]");
		Commentary.log(LogStatus.INFO, "Total balance before adding the transaction ["+sTotal_before+"]");
		Double d = Double.parseDouble(tRec.getAmount());
		Double dChecking_before = h.processBalanceAmount(sChecking_before);
		Double dTotal_before = h.processBalanceAmount(sTotal_before);
		
		op.addTransaction.click();
		TransactionDetailPage td = new TransactionDetailPage();
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount"+tRec.getAmount());
		
		h.getContext();
		sChecking_after = op.checkingBalance.getText();
		sTotal_after = op.totalBalance.getText();
		Double dChecking_after = h.processBalanceAmount(sChecking_after);
		Double dTotal_after = h.processBalanceAmount(sTotal_after);
		
		if (dChecking_before-d==dChecking_after)
			Commentary.log(LogStatus.INFO, "Checking balance was ["+dChecking_before+"], added expense transaction for ["+tRec.getAmount()+"], now the checking balance shows ["+dChecking_after+"]");
		else
			Commentary.log(LogStatus.FAIL, "FAIL: Checking balance was ["+dChecking_before+"], added expense transaction for ["+tRec.getAmount()+"], now the checking balance shows ["+dChecking_after+"]");
		
		if (dTotal_before-d==dTotal_after)
			Commentary.log(LogStatus.INFO, "Total balance was ["+dTotal_before+"], added expense transaction for ["+tRec.getAmount()+"], now the total balance shows ["+dTotal_after+"]");
		else
			Commentary.log(LogStatus.FAIL, "FAIL: Total balance was ["+dTotal_before+"], added expense transaction for ["+tRec.getAmount()+"], now the total balance shows ["+dTotal_after+"]");
		
		sa.assertAll();	
		
	}
	
	@Test(priority = 2)
	public void TB3_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "Add an income transaction for a manual checking account, verify checking & total balance on overview screen accounts card");
		String sChecking_before, sTotal_before, sChecking_after, sTotal_after;
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualChecking);
		tRec.setAmount("5.00");
		tRec.setTransactionType("income");
		tRec.setDate(h.getYesterdaysDate());
		h.getContext();
		
		
		OverviewPage op = new OverviewPage();
		sChecking_before = op.checkingBalance.getText();
		sTotal_before = op.totalBalance.getText();
		Commentary.log(LogStatus.INFO, "Checking balance before adding the transaction ["+sChecking_before+"]");
		Commentary.log(LogStatus.INFO, "Total balance before adding the transaction ["+sTotal_before+"]");
		Double d = Double.parseDouble(tRec.getAmount());
		Double dChecking_before = h.processBalanceAmount(sChecking_before);
		Double dTotal_before = h.processBalanceAmount(sTotal_before);
		
		op.addTransaction.click();
		TransactionDetailPage td = new TransactionDetailPage();
		td.addTransaction(tRec);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type income, amount"+tRec.getAmount());
		
		h.getContext();
		sChecking_after = op.checkingBalance.getText();
		sTotal_after = op.totalBalance.getText();
		Double dChecking_after = h.processBalanceAmount(sChecking_after);
		Double dTotal_after = h.processBalanceAmount(sTotal_after);
		
		if (dChecking_before+d==dChecking_after)
			Commentary.log(LogStatus.INFO, "Checking balance was ["+dChecking_before+"], added income transaction for ["+tRec.getAmount()+"], now the checking balance shows ["+dChecking_after+"]");
		else
			Commentary.log(LogStatus.FAIL, "FAIL: Checking balance was ["+dChecking_before+"], added income transaction for ["+tRec.getAmount()+"], now the checking balance shows ["+dChecking_after+"]");
		
		if (dTotal_before+d==dTotal_after)
			Commentary.log(LogStatus.INFO, "Total balance was ["+dTotal_before+"], added income transaction for ["+tRec.getAmount()+"], now the total balance shows ["+dTotal_after+"]");
		else
			Commentary.log(LogStatus.FAIL, "FAIL: Total balance was ["+dTotal_before+"], added income transaction for ["+tRec.getAmount()+"], now the total balance shows ["+dTotal_after+"]");
		
		sa.assertAll();	
		
	}
	
	
	@Test(priority = 3)
	public void TB4_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "Add an expense transaction for a manual Credit card account, verify Credit card & total balance on overview screen accounts card");
		String sCC_before, sTotal_before, sCC_after, sTotal_after;
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualCreditCard);
		tRec.setAmount("5.00");
		tRec.setTransactionType("expense");
		tRec.setDate(h.getYesterdaysDate());
		h.getContext();
		
		OverviewPage op = new OverviewPage();
		sCC_before = op.creditBalance.getText();
		sTotal_before = op.totalBalance.getText();
		Commentary.log(LogStatus.INFO, "Credit Card balance before adding the transaction ["+sCC_before+"]");
		Commentary.log(LogStatus.INFO, "Total balance before adding the transaction ["+sTotal_before+"]");
		Double d = Double.parseDouble(tRec.getAmount());
		Double dCC_before = h.processBalanceAmount(sCC_before);
		Double dTotal_before = h.processBalanceAmount(sTotal_before);
		
		op.addTransaction.click();
		TransactionDetailPage td = new TransactionDetailPage();
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount"+tRec.getAmount());
		
		h.getContext();
		sCC_after = op.creditBalance.getText();
		sTotal_after = op.totalBalance.getText();
		Double dCC_after = h.processBalanceAmount(sCC_after);
		Double dTotal_after = h.processBalanceAmount(sTotal_after);
		
		if (dCC_before-d==dCC_after)
			Commentary.log(LogStatus.INFO, "CC balance was ["+dCC_before+"], added expense transaction for ["+tRec.getAmount()+"], now the CC balance shows ["+dCC_after+"]");
		else
			Commentary.log(LogStatus.FAIL, "FAIL: CC balance was ["+dCC_before+"], added expense transaction for ["+tRec.getAmount()+"], now the CC balance shows ["+dCC_after+"]");
		
		if (dTotal_before-d==dTotal_after)
			Commentary.log(LogStatus.INFO, "Total balance was ["+dTotal_before+"], added expense transaction for ["+tRec.getAmount()+"], now the total balance shows ["+dTotal_after+"]");
		else
			Commentary.log(LogStatus.FAIL, "FAIL: Total balance was ["+dTotal_before+"], added expense transaction for ["+tRec.getAmount()+"], now the total balance shows ["+dTotal_after+"]");
		
		sa.assertAll();	
		
	}
	
	
	
	@Test(priority = 4)
	public void TB5_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "Add an income transaction for a manual Credit card account, verify credit cards & total balance on overview screen accounts card");
		String sCC_before, sTotal_before, sCC_after, sTotal_after;
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualCreditCard);
		tRec.setAmount("5.00");
		tRec.setTransactionType("income");
		tRec.setDate(h.getYesterdaysDate());
		h.getContext();
		
		
		OverviewPage op = new OverviewPage();
		sCC_before = op.creditBalance.getText();
		sTotal_before = op.totalBalance.getText();
		Commentary.log(LogStatus.INFO, "Credit Card balance before adding the transaction ["+sCC_before+"]");
		Commentary.log(LogStatus.INFO, "Total balance before adding the transaction ["+sTotal_before+"]");
		Double d = Double.parseDouble(tRec.getAmount());
		Double dCC_before = h.processBalanceAmount(sCC_before);
		Double dTotal_before = h.processBalanceAmount(sTotal_before);
		
		op.addTransaction.click();
		TransactionDetailPage td = new TransactionDetailPage();
		h.getContext();
		td.addTransaction(tRec);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type income, amount"+tRec.getAmount());
		
		h.getContext();
		sCC_after = op.creditBalance.getText();
		sTotal_after = op.totalBalance.getText();
		Double dCC_after = h.processBalanceAmount(sCC_after);
		Double dTotal_after = h.processBalanceAmount(sTotal_after);
		
		if (dCC_before+d==dCC_after)
			Commentary.log(LogStatus.INFO, "CC balance was ["+dCC_before+"], added income transaction for ["+tRec.getAmount()+"], now the CC balance shows ["+dCC_after+"]");
		else
			Commentary.log(LogStatus.FAIL, "FAIL: CC balance was ["+dCC_before+"], added income transaction for ["+tRec.getAmount()+"], now the CC balance shows ["+dCC_after+"]");
		
		if (dTotal_before+d==dTotal_after)
			Commentary.log(LogStatus.INFO, "Total balance was ["+dTotal_before+"], added income transaction for ["+tRec.getAmount()+"], now the total balance shows ["+dTotal_after+"]");
		else
			Commentary.log(LogStatus.FAIL, "FAIL: Total balance was ["+dTotal_before+"], added income transaction for ["+tRec.getAmount()+"], now the total balance shows ["+dTotal_after+"]");
		
		sa.assertAll();	
		
	}
	
	@Test(priority = 5)
	public void TB6_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "Add an expense transaction for a manual Cash account, verify Cash & total balance on overview screen accounts card");
		String sBefore, sTotal_before, sAfter, sTotal_after;
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualCash);
		tRec.setAmount("5.00");
		tRec.setTransactionType("expense");
		tRec.setDate(h.getYesterdaysDate());
		h.getContext();
		
		OverviewPage op = new OverviewPage();
		sBefore = op.cashBalance.getText();
		sTotal_before = op.totalBalance.getText();
		Commentary.log(LogStatus.INFO, "Balance before adding the transaction ["+sBefore+"]");
		Commentary.log(LogStatus.INFO, "Total balance before adding the transaction ["+sTotal_before+"]");
		Double d = Double.parseDouble(tRec.getAmount());
		Double dBefore = h.processBalanceAmount(sBefore);
		Double dTotal_before = h.processBalanceAmount(sTotal_before);
		
		op.addTransaction.click();
		TransactionDetailPage td = new TransactionDetailPage();
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount"+tRec.getAmount());
		
		h.getContext();
		sAfter = op.cashBalance.getText();
		sTotal_after = op.totalBalance.getText();
		Double dAfter = h.processBalanceAmount(sAfter);
		Double dTotal_after = h.processBalanceAmount(sTotal_after);
		
		if (dBefore-d==dAfter)
			Commentary.log(LogStatus.INFO, "Account balance was ["+dBefore+"], added expense transaction for ["+tRec.getAmount()+"], now CASH balance shows ["+dAfter+"]");
		else
			Commentary.log(LogStatus.FAIL, "FAIL: Account balance was ["+dBefore+"], added expense transaction for ["+tRec.getAmount()+"], now CASH balance shows ["+dAfter+"]");
		
		if (dTotal_before-d==dTotal_after)
			Commentary.log(LogStatus.INFO, "Total balance was ["+dTotal_before+"], added expense transaction for ["+tRec.getAmount()+"], now the total balance shows ["+dTotal_after+"]");
		else
			Commentary.log(LogStatus.FAIL, "FAIL: Total balance was ["+dTotal_before+"], added expense transaction for ["+tRec.getAmount()+"], now the total balance shows ["+dTotal_after+"]");
		
		sa.assertAll();	
		
	}
	
	@Test(priority = 6)
	public void TB7_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "Add an income transaction for a manual CASH account, verify CASH & total balance on overview screen accounts card");
		String sBefore, sTotal_before, sAfter, sTotal_after;
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualCash);
		tRec.setAmount("5.00");
		tRec.setTransactionType("income");
		tRec.setDate(h.getYesterdaysDate());
		h.getContext();
		
		
		OverviewPage op = new OverviewPage();
		sBefore = op.cashBalance.getText();
		sTotal_before = op.totalBalance.getText();
		Commentary.log(LogStatus.INFO, "CASH balance before adding the transaction ["+sBefore+"]");
		Commentary.log(LogStatus.INFO, "Total balance before adding the transaction ["+sTotal_before+"]");
		Double d = Double.parseDouble(tRec.getAmount());
		Double dBefore = h.processBalanceAmount(sBefore);
		Double dTotal_before = h.processBalanceAmount(sTotal_before);
		
		op.addTransaction.click();
		TransactionDetailPage td = new TransactionDetailPage();
		td.addTransaction(tRec);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type income, amount"+tRec.getAmount());
		
		h.getContext();
		sAfter = op.cashBalance.getText();
		sTotal_after = op.totalBalance.getText();
		Double dAfter = h.processBalanceAmount(sAfter);
		Double dTotal_after = h.processBalanceAmount(sTotal_after);
		
		if (dBefore+d==dAfter)
			Commentary.log(LogStatus.INFO, "CASH balance was ["+dBefore+"], added income transaction for ["+tRec.getAmount()+"], now the CASH balance shows ["+dAfter+"]");
		else
			Commentary.log(LogStatus.FAIL, "FAIL: CASH balance was ["+dBefore+"], added income transaction for ["+tRec.getAmount()+"], now the CASH balance shows ["+dAfter+"]");
		
		if (dTotal_before+d==dTotal_after)
			Commentary.log(LogStatus.INFO, "Total balance was ["+dTotal_before+"], added income transaction for ["+tRec.getAmount()+"], now the total balance shows ["+dTotal_after+"]");
		else
			Commentary.log(LogStatus.FAIL, "FAIL: Total balance was ["+dTotal_before+"], added income transaction for ["+tRec.getAmount()+"], now the total balance shows ["+dTotal_after+"]");
		
		sa.assertAll();	
		
	}
	
	@Test(priority = 7)
	public void TB8_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "Add an expense transaction for a manual SAVING account, verify SAVINGS & total balance on overview screen accounts card");
		String sBefore, sTotal_before, sAfter, sTotal_after;
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualSaving);
		tRec.setAmount("5.00");
		tRec.setTransactionType("expense");
		tRec.setDate(h.getYesterdaysDate());
		h.getContext();
		
		OverviewPage op = new OverviewPage();
		sBefore = op.savingsBalance.getText();
		sTotal_before = op.totalBalance.getText();
		Commentary.log(LogStatus.INFO, "Savings Balance before adding the transaction ["+sBefore+"]");
		Commentary.log(LogStatus.INFO, "Total balance before adding the transaction ["+sTotal_before+"]");
		Double d = Double.parseDouble(tRec.getAmount());
		Double dBefore = h.processBalanceAmount(sBefore);
		Double dTotal_before = h.processBalanceAmount(sTotal_before);
		
		op.addTransaction.click();
		TransactionDetailPage td = new TransactionDetailPage();
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount"+tRec.getAmount());
		
		h.getContext();
		sAfter = op.savingsBalance.getText();
		sTotal_after = op.totalBalance.getText();
		Double dAfter = h.processBalanceAmount(sAfter);
		Double dTotal_after = h.processBalanceAmount(sTotal_after);
		
		if (dBefore-d==dAfter)
			Commentary.log(LogStatus.INFO, "Account balance was ["+dBefore+"], added expense transaction for ["+tRec.getAmount()+"], now SAVINGS balance shows ["+dAfter+"]");
		else
			Commentary.log(LogStatus.FAIL, "FAIL: Account balance was ["+dBefore+"], added expense transaction for ["+tRec.getAmount()+"], now SAVINGS balance shows ["+dAfter+"]");
		
		if (dTotal_before-d==dTotal_after)
			Commentary.log(LogStatus.INFO, "Total balance was ["+dTotal_before+"], added expense transaction for ["+tRec.getAmount()+"], now the total balance shows ["+dTotal_after+"]");
		else
			Commentary.log(LogStatus.FAIL, "FAIL: Total balance was ["+dTotal_before+"], added expense transaction for ["+tRec.getAmount()+"], now the total balance shows ["+dTotal_after+"]");
		
		sa.assertAll();	
		
	}
	
	@Test(priority = 8)
	public void TB9_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "Add an income transaction for a manual SAVINGS account, verify SAVINGS & total balance on overview screen accounts card");
		String sBefore, sTotal_before, sAfter, sTotal_after;
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualSaving);
		tRec.setAmount("5.00");
		tRec.setTransactionType("income");
		tRec.setDate(h.getYesterdaysDate());
		h.getContext();
		
		
		OverviewPage op = new OverviewPage();
		sBefore = op.savingsBalance.getText();
		sTotal_before = op.totalBalance.getText();
		Commentary.log(LogStatus.INFO, "SAVINGS balance before adding the transaction ["+sBefore+"]");
		Commentary.log(LogStatus.INFO, "Total balance before adding the transaction ["+sTotal_before+"]");
		Double d = Double.parseDouble(tRec.getAmount());
		Double dBefore = h.processBalanceAmount(sBefore);
		Double dTotal_before = h.processBalanceAmount(sTotal_before);
		
		op.addTransaction.click();
		TransactionDetailPage td = new TransactionDetailPage();
		td.addTransaction(tRec);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type income, amount"+tRec.getAmount());
		
		h.getContext();
		sAfter = op.savingsBalance.getText();
		sTotal_after = op.totalBalance.getText();
		Double dAfter = h.processBalanceAmount(sAfter);
		Double dTotal_after = h.processBalanceAmount(sTotal_after);
		
		if (dBefore+d==dAfter)
			Commentary.log(LogStatus.INFO, "SAVINGS balance was ["+dBefore+"], added income transaction for ["+tRec.getAmount()+"], now the SAVINGS balance shows ["+dAfter+"]");
		else
			Commentary.log(LogStatus.FAIL, "FAIL: SAVINGS balance was ["+dBefore+"], added income transaction for ["+tRec.getAmount()+"], now the SAVINGS balance shows ["+dAfter+"]");
		
		if (dTotal_before+d==dTotal_after)
			Commentary.log(LogStatus.INFO, "Total balance was ["+dTotal_before+"], added income transaction for ["+tRec.getAmount()+"], now the total balance shows ["+dTotal_after+"]");
		else
			Commentary.log(LogStatus.FAIL, "FAIL: Total balance was ["+dTotal_before+"], added income transaction for ["+tRec.getAmount()+"], now the total balance shows ["+dTotal_after+"]");
		
		sa.assertAll();	
		
	}

}
