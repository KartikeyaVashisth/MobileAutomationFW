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

public class ProjectedBalances extends Recovery {
	
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
	public void PB1_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "Add a past dated expense transaction for a manual checking account, verify total projected balance & checking projected balance");
		String sChecking_before, sProjected_before, sProjectedChecking_after, sProjectedTotal_after;
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualChecking);
		tRec.setAmount("5.00");
		tRec.setTransactionType("expense");
		tRec.setDate(h.getYesterdaysDate());
		h.getContext();
		
		
		SignInPage signIn = new SignInPage();
		signIn.signIn(sUserName, sPassword, sDataset);
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		// get the projected balance
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		bcc.scrollToProjectedBalance();
		sProjected_before = bcc.txtProjectedBalanceAmount.getText();
		sChecking_before = bcc.getCheckingBalance();
		Commentary.log(LogStatus.INFO, "Checking Projected balance before adding the transaction ["+sChecking_before+"]");
		Commentary.log(LogStatus.INFO, "Total Projected balance before adding the transaction ["+sProjected_before+"]");
		Double d = Double.parseDouble(tRec.getAmount());
		Double dChecking_before = h.processBalanceAmount(sChecking_before);
		Double dProjected_before = h.processBalanceAmount(sProjected_before);
		
		bcc.getAccount(tRec.getAccount()).click();
		Thread.sleep(2000);
		
		TransactionsPage tp = new TransactionsPage();
		tp.addTransaction.click();
		
		TransactionDetailPage td = new TransactionDetailPage();
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount"+tRec.getAmount());
		h.getContext();
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 5);
		
		tp.backButton.click();
		Thread.sleep(1000);
		sProjectedTotal_after = bcc.txtProjectedBalanceAmount.getText();
		sProjectedChecking_after = bcc.getCheckingBalance();
		Double dProjectedChecking_after = h.processBalanceAmount(sProjectedChecking_after);
		Double dProjectedTotal_after = h.processBalanceAmount(sProjectedTotal_after);
		
		if (dChecking_before-d==dProjectedChecking_after)
			Commentary.log(LogStatus.INFO, "Projected Checking balance was ["+dChecking_before+"], added past dated expense transaction for ["+tRec.getAmount()+"], now the checking balance shows ["+dProjectedChecking_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected Checking balance was ["+dChecking_before+"], added past dated expense transaction for ["+tRec.getAmount()+"], now the checking balance shows ["+dProjectedChecking_after+"]");
		
		if (dProjected_before-d==dProjectedTotal_after)
			Commentary.log(LogStatus.INFO, "Projected Total balance was ["+dProjected_before+"], added past dated expense transaction for ["+tRec.getAmount()+"], now the total balance shows ["+dProjectedTotal_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected Total balance was ["+dProjected_before+"], added past dated expense transaction for ["+tRec.getAmount()+"], now the total balance shows ["+dProjectedTotal_after+"]");
		
		sa.assertAll();	
		
	}
	
	@Test(priority = 1)
	public void PB2_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "Add a future dated expense transaction for a manual checking account, verify total projected balance & checking projected balance");
		String sChecking_before, sProjected_before, sProjectedChecking_after, sProjectedTotal_after;
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualChecking);
		tRec.setAmount("5.00");
		tRec.setTransactionType("expense");
		tRec.setDate(h.getFutureDaysDate(4));
		h.getContext();
		
		/*
		SignInPage signIn = new SignInPage();
		signIn.signIn(sUserName, sPassword, sDataset);*/
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		// get the projected balance
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		bcc.scrollToProjectedBalance();
		sProjected_before = bcc.txtProjectedBalanceAmount.getText();
		sChecking_before = bcc.getCheckingBalance();
		Commentary.log(LogStatus.INFO, "Checking Projected balance before adding the transaction ["+sChecking_before+"]");
		Commentary.log(LogStatus.INFO, "Total Projected balance before adding the transaction ["+sProjected_before+"]");
		Double d = Double.parseDouble(tRec.getAmount());
		Double dChecking_before = h.processBalanceAmount(sChecking_before);
		Double dProjected_before = h.processBalanceAmount(sProjected_before);
		
		bcc.getAccount(tRec.getAccount()).click();
		Thread.sleep(2000);
		
		TransactionsPage tp = new TransactionsPage();
		tp.addTransaction.click();
		
		TransactionDetailPage td = new TransactionDetailPage();
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount"+tRec.getAmount());
		h.getContext();
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 5);
		
		tp.backButton.click();
		Thread.sleep(1000);
		sProjectedTotal_after = bcc.txtProjectedBalanceAmount.getText();
		sProjectedChecking_after = bcc.getCheckingBalance();
		Double dProjectedChecking_after = h.processBalanceAmount(sProjectedChecking_after);
		Double dProjectedTotal_after = h.processBalanceAmount(sProjectedTotal_after);
		
		if (dChecking_before-d==dProjectedChecking_after)
			Commentary.log(LogStatus.INFO, "Projected Checking balance was ["+dChecking_before+"], added future dated expense transaction for ["+tRec.getAmount()+"], now the Projected checking balance shows ["+dProjectedChecking_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected Checking balance was ["+dChecking_before+"], added future dated expense transaction for ["+tRec.getAmount()+"], now the Projected checking balance shows ["+dProjectedChecking_after+"]");
		
		if (dProjected_before-d==dProjectedTotal_after)
			Commentary.log(LogStatus.INFO, "Projected Total balance was ["+dProjected_before+"], added future dated expense transaction for ["+tRec.getAmount()+"], now the Projected total balance shows ["+dProjectedTotal_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected Total balance was ["+dProjected_before+"], added future dated expense transaction for ["+tRec.getAmount()+"], now the Projected total balance shows ["+dProjectedTotal_after+"]");
		
		sa.assertAll();	
		
	}
	
	@Test(priority = 2)
	public void PB3_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "Add a past dated income transaction for a manual checking account, verify total projected balance & checking projected balance");
		String sChecking_before, sProjected_before, sProjectedChecking_after, sProjectedTotal_after;
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualChecking);
		tRec.setAmount("5.00");
		tRec.setTransactionType("income");
		tRec.setDate(h.getYesterdaysDate());
		h.getContext();
		
		/*
		SignInPage signIn = new SignInPage();
		signIn.signIn(sUserName, sPassword, sDataset);*/
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		// get the projected balance
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		bcc.scrollToProjectedBalance();
		sProjected_before = bcc.txtProjectedBalanceAmount.getText();
		sChecking_before = bcc.getCheckingBalance();
		Commentary.log(LogStatus.INFO, "Checking Projected balance before adding the transaction ["+sChecking_before+"]");
		Commentary.log(LogStatus.INFO, "Total Projected balance before adding the transaction ["+sProjected_before+"]");
		Double d = Double.parseDouble(tRec.getAmount());
		Double dChecking_before = h.processBalanceAmount(sChecking_before);
		Double dProjected_before = h.processBalanceAmount(sProjected_before);
		
		bcc.getAccount(tRec.getAccount()).click();
		Thread.sleep(2000);
		
		TransactionsPage tp = new TransactionsPage();
		tp.addTransaction.click();
		
		TransactionDetailPage td = new TransactionDetailPage();
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount"+tRec.getAmount());
		h.getContext();
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 5);
		
		tp.backButton.click();
		Thread.sleep(1000);
		sProjectedTotal_after = bcc.txtProjectedBalanceAmount.getText();
		sProjectedChecking_after = bcc.getCheckingBalance();
		Double dProjectedChecking_after = h.processBalanceAmount(sProjectedChecking_after);
		Double dProjectedTotal_after = h.processBalanceAmount(sProjectedTotal_after);
		
		if (dChecking_before+d==dProjectedChecking_after)
			Commentary.log(LogStatus.INFO, "Projected Checking balance was ["+dChecking_before+"], added past dated income transaction for ["+tRec.getAmount()+"], now the Projected checking balance shows ["+dProjectedChecking_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected Checking balance was ["+dChecking_before+"], added past dated income transaction for ["+tRec.getAmount()+"], now the Projected checking balance shows ["+dProjectedChecking_after+"]");
		
		if (dProjected_before+d==dProjectedTotal_after)
			Commentary.log(LogStatus.INFO, "Projected Total balance was ["+dProjected_before+"], added past dated income transaction for ["+tRec.getAmount()+"], now the Projected total balance shows ["+dProjectedTotal_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected Total balance was ["+dProjected_before+"], added past dated income transaction for ["+tRec.getAmount()+"], now the Projected total balance shows ["+dProjectedTotal_after+"]");
		
		sa.assertAll();	
		
	}
	
	@Test(priority = 3)
	public void PB4_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "Add a future dated income transaction for a manual checking account, verify total projected balance & checking projected balance");
		String sChecking_before, sProjected_before, sProjectedChecking_after, sProjectedTotal_after;
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualChecking);
		tRec.setAmount("5.00");
		tRec.setTransactionType("income");
		tRec.setDate(h.getFutureDaysDate(4));
		h.getContext();
		
		/*
		SignInPage signIn = new SignInPage();
		signIn.signIn(sUserName, sPassword, sDataset);*/
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		// get the projected balance
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		bcc.scrollToProjectedBalance();
		sProjected_before = bcc.txtProjectedBalanceAmount.getText();
		sChecking_before = bcc.getCheckingBalance();
		Commentary.log(LogStatus.INFO, "Checking Projected balance before adding the transaction ["+sChecking_before+"]");
		Commentary.log(LogStatus.INFO, "Total Projected balance before adding the transaction ["+sProjected_before+"]");
		Double d = Double.parseDouble(tRec.getAmount());
		Double dChecking_before = h.processBalanceAmount(sChecking_before);
		Double dProjected_before = h.processBalanceAmount(sProjected_before);
		
		bcc.getAccount(tRec.getAccount()).click();
		Thread.sleep(2000);
		
		TransactionsPage tp = new TransactionsPage();
		tp.addTransaction.click();
		
		TransactionDetailPage td = new TransactionDetailPage();
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount"+tRec.getAmount());
		h.getContext();
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 5);
		
		tp.backButton.click();
		Thread.sleep(1000);
		sProjectedChecking_after = bcc.getCheckingBalance();
		sProjectedTotal_after = bcc.txtProjectedBalanceAmount.getText();
		Double dProjectedChecking_after = h.processBalanceAmount(sProjectedChecking_after);
		Double dProjectedTotal_after = h.processBalanceAmount(sProjectedTotal_after);
		
		if (dChecking_before+d==dProjectedChecking_after)
			Commentary.log(LogStatus.INFO, "Projected Checking balance was ["+dChecking_before+"], added future dated income transaction for ["+tRec.getAmount()+"], now the Projected checking balance shows ["+dProjectedChecking_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected Checking balance was ["+dChecking_before+"], added future income expense transaction for ["+tRec.getAmount()+"], now the Projected checking balance shows ["+dProjectedChecking_after+"]");
		
		if (dProjected_before+d==dProjectedTotal_after)
			Commentary.log(LogStatus.INFO, "Projected Total balance was ["+dProjected_before+"], added future dated income transaction for ["+tRec.getAmount()+"], now the Projected total balance shows ["+dProjectedTotal_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected Total balance was ["+dProjected_before+"], added future dated income transaction for ["+tRec.getAmount()+"], now the Projected total balance shows ["+dProjectedTotal_after+"]");
		
		sa.assertAll();	
		
	}
	
	@Test(priority = 4)
	public void PB5_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "Add a past dated expense transaction for a manual credit card account, verify total projected balance & CreditCard projected balance");
		String sCC_before, sProjected_before, sProjectedCC_after, sProjectedTotal_after;
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualCreditCard);
		tRec.setAmount("5.00");
		tRec.setTransactionType("expense");
		tRec.setDate(h.getYesterdaysDate());
		h.getContext();
		
		/*
		SignInPage signIn = new SignInPage();
		signIn.signIn(sUserName, sPassword, sDataset);*/
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		// get the projected balance
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		bcc.scrollToProjectedBalance();
		sProjected_before = bcc.txtProjectedBalanceAmount.getText();
		sCC_before = bcc.getCreditCardsBalance();
		Commentary.log(LogStatus.INFO, "CreditCard Projected balance before adding the transaction ["+sCC_before+"]");
		Commentary.log(LogStatus.INFO, "Total Projected balance before adding the transaction ["+sProjected_before+"]");
		Double d = Double.parseDouble(tRec.getAmount());
		Double dCC_before = h.processBalanceAmount(sCC_before);
		Double dProjected_before = h.processBalanceAmount(sProjected_before);
		
		bcc.getAccount(tRec.getAccount()).click();
		Thread.sleep(2000);
		
		TransactionsPage tp = new TransactionsPage();
		tp.addTransaction.click();
		
		TransactionDetailPage td = new TransactionDetailPage();
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount"+tRec.getAmount());
		h.getContext();
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 5);
		
		tp.backButton.click();
		Thread.sleep(1000);
		sProjectedCC_after = bcc.getCreditCardsBalance();
		bcc.backButton.click();
		Thread.sleep(1000);
		op.navigateToAcctList();
		sProjectedTotal_after = bcc.txtProjectedBalanceAmount.getText();
		Double dProjectedChecking_after = h.processBalanceAmount(sProjectedCC_after);
		Double dProjectedTotal_after = h.processBalanceAmount(sProjectedTotal_after);
		
		if (dCC_before-d==dProjectedChecking_after)
			Commentary.log(LogStatus.INFO, "Projected CreditCard balance was ["+dCC_before+"], added past dated expense transaction for ["+tRec.getAmount()+"], now the Projected CreditCard balance shows ["+dProjectedChecking_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected CreditCard balance was ["+dCC_before+"], added past dated expense transaction for ["+tRec.getAmount()+"], now the Projected CreditCard balance shows ["+dProjectedChecking_after+"]");
		
		if (dProjected_before-d==dProjectedTotal_after)
			Commentary.log(LogStatus.INFO, "Projected Total balance was ["+dProjected_before+"], added past dated expense transaction for ["+tRec.getAmount()+"], now the Projected total balance shows ["+dProjectedTotal_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected Total balance was ["+dProjected_before+"], added past dated expense transaction for ["+tRec.getAmount()+"], now the Projected total balance shows ["+dProjectedTotal_after+"]");
		
		sa.assertAll();	
		
	}
	
	@Test(priority = 5)
	public void PB6_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "Add a future dated expense transaction for a manual credit card account, verify total projected balance & CreditCard projected balance");
		String sCC_before, sProjected_before, sProjectedCC_after, sProjectedTotal_after;
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualCreditCard);
		tRec.setAmount("5.00");
		tRec.setTransactionType("expense");
		tRec.setDate(h.getFutureDaysDate(4));
		h.getContext();
		
		/*
		SignInPage signIn = new SignInPage();
		signIn.signIn(sUserName, sPassword, sDataset);*/
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		// get the projected balance
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		bcc.scrollToProjectedBalance();
		sProjected_before = bcc.txtProjectedBalanceAmount.getText();
		sCC_before = bcc.getCreditCardsBalance();
		Commentary.log(LogStatus.INFO, "CreditCard Projected balance before adding the transaction ["+sCC_before+"]");
		Commentary.log(LogStatus.INFO, "Total Projected balance before adding the transaction ["+sProjected_before+"]");
		Double d = Double.parseDouble(tRec.getAmount());
		Double dChecking_before = h.processBalanceAmount(sCC_before);
		Double dProjected_before = h.processBalanceAmount(sProjected_before);
		
		bcc.getAccount(tRec.getAccount()).click();
		Thread.sleep(2000);
		
		TransactionsPage tp = new TransactionsPage();
		tp.addTransaction.click();
		
		TransactionDetailPage td = new TransactionDetailPage();
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount"+tRec.getAmount());
		h.getContext();
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 5);
		
		tp.backButton.click();
		Thread.sleep(1000);
		sProjectedCC_after = bcc.getCreditCardsBalance();
		bcc.backButton.click();
		Thread.sleep(1000);
		op.navigateToAcctList();
		sProjectedTotal_after = bcc.txtProjectedBalanceAmount.getText();
		
		Double dProjectedCC_after = h.processBalanceAmount(sProjectedCC_after);
		Double dProjectedTotal_after = h.processBalanceAmount(sProjectedTotal_after);
		
		if (dChecking_before-d==dProjectedCC_after)
			Commentary.log(LogStatus.INFO, "Projected CreditCard balance was ["+dChecking_before+"], added Future dated expense transaction for ["+tRec.getAmount()+"], now the Projected CreditCard balance shows ["+dProjectedCC_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected CreditCard balance was ["+dChecking_before+"], added Future dated expense transaction for ["+tRec.getAmount()+"], now the Projected CreditCard balance shows ["+dProjectedCC_after+"]");
		
		if (dProjected_before-d==dProjectedTotal_after)
			Commentary.log(LogStatus.INFO, "Projected Total balance was ["+dProjected_before+"], added Future dated expense transaction for ["+tRec.getAmount()+"], now the Projected total balance shows ["+dProjectedTotal_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected Total balance was ["+dProjected_before+"], added Future dated expense transaction for ["+tRec.getAmount()+"], now the Projected total balance shows ["+dProjectedTotal_after+"]");
		
		sa.assertAll();	
		
	}
	
	@Test(priority = 6)
	public void PB7_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "Add a past dated income transaction for a manual credit card account, verify total projected balance & CreditCard projected balance");
		String sCC_before, sProjected_before, sProjectedCC_after, sProjectedTotal_after;
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualCreditCard);
		tRec.setAmount("5.00");
		tRec.setTransactionType("income");
		tRec.setDate(h.getYesterdaysDate());
		h.getContext();
		
		/*
		SignInPage signIn = new SignInPage();
		signIn.signIn(sUserName, sPassword, sDataset);*/
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		// get the projected balance
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		bcc.scrollToProjectedBalance();
		sProjected_before = bcc.txtProjectedBalanceAmount.getText();
		sCC_before = bcc.getCreditCardsBalance();
		Commentary.log(LogStatus.INFO, "CreditCard Projected balance before adding the transaction ["+sCC_before+"]");
		Commentary.log(LogStatus.INFO, "Total Projected balance before adding the transaction ["+sProjected_before+"]");
		Double d = Double.parseDouble(tRec.getAmount());
		Double dCC_before = h.processBalanceAmount(sCC_before);
		Double dProjected_before = h.processBalanceAmount(sProjected_before);
		
		bcc.getAccount(tRec.getAccount()).click();
		Thread.sleep(2000);
		
		TransactionsPage tp = new TransactionsPage();
		tp.addTransaction.click();
		
		TransactionDetailPage td = new TransactionDetailPage();
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount"+tRec.getAmount());
		h.getContext();
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 5);
		
		tp.backButton.click();
		Thread.sleep(1000);
		sProjectedCC_after = bcc.getCreditCardsBalance();
		bcc.backButton.click();
		Thread.sleep(1000);
		op.navigateToAcctList();
		sProjectedTotal_after = bcc.txtProjectedBalanceAmount.getText();
		Double dProjectedCC_after = h.processBalanceAmount(sProjectedCC_after);
		Double dProjectedTotal_after = h.processBalanceAmount(sProjectedTotal_after);
		
		if (dCC_before+d==dProjectedCC_after)
			Commentary.log(LogStatus.INFO, "Projected CreditCard balance was ["+dCC_before+"], added past dated income transaction for ["+tRec.getAmount()+"], now the Projected CreditCard balance shows ["+dProjectedCC_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected CreditCard balance was ["+dCC_before+"], added past dated income transaction for ["+tRec.getAmount()+"], now the Projected CreditCard balance shows ["+dProjectedCC_after+"]");
		
		if (dProjected_before+d==dProjectedTotal_after)
			Commentary.log(LogStatus.INFO, "Projected Total balance was ["+dProjected_before+"], added past dated income transaction for ["+tRec.getAmount()+"], now the Projected total balance shows ["+dProjectedTotal_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected Total balance was ["+dProjected_before+"], added past dated income transaction for ["+tRec.getAmount()+"], now the Projected total balance shows ["+dProjectedTotal_after+"]");
		
		sa.assertAll();	
		
	}
	
	@Test(priority = 7)
	public void PB8_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "Add a future dated income transaction for a manual credit card account, verify total projected balance & CreditCard projected balance");
		String sCC_before, sProjected_before, sProjectedCC_after, sProjectedTotal_after;
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualCreditCard);
		tRec.setAmount("5.00");
		tRec.setTransactionType("income");
		tRec.setDate(h.getFutureDaysDate(4));
		h.getContext();
		
		/*
		SignInPage signIn = new SignInPage();
		signIn.signIn(sUserName, sPassword, sDataset);*/
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		// get the projected balance
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		bcc.scrollToProjectedBalance();
		sProjected_before = bcc.txtProjectedBalanceAmount.getText();
		sCC_before = bcc.getCreditCardsBalance();
		Commentary.log(LogStatus.INFO, "CreditCard Projected balance before adding the transaction ["+sCC_before+"]");
		Commentary.log(LogStatus.INFO, "Total Projected balance before adding the transaction ["+sProjected_before+"]");
		Double d = Double.parseDouble(tRec.getAmount());
		Double dCC_before = h.processBalanceAmount(sCC_before);
		Double dProjected_before = h.processBalanceAmount(sProjected_before);
		
		bcc.getAccount(tRec.getAccount()).click();
		Thread.sleep(2000);
		
		TransactionsPage tp = new TransactionsPage();
		tp.addTransaction.click();
		
		TransactionDetailPage td = new TransactionDetailPage();
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount"+tRec.getAmount());
		h.getContext();
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 5);
		
		tp.backButton.click();
		Thread.sleep(1000);
		sProjectedCC_after = bcc.getCreditCardsBalance();
		bcc.backButton.click();
		Thread.sleep(1000);
		op.navigateToAcctList();
		sProjectedTotal_after = bcc.txtProjectedBalanceAmount.getText();
		
		Double dProjectedCC_after = h.processBalanceAmount(sProjectedCC_after);
		Double dProjectedTotal_after = h.processBalanceAmount(sProjectedTotal_after);
		
		if (dCC_before+d==dProjectedCC_after)
			Commentary.log(LogStatus.INFO, "Projected CreditCard balance was ["+dCC_before+"], added future dated income transaction for ["+tRec.getAmount()+"], now the Projected CreditCard balance shows ["+dProjectedCC_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected CreditCard balance was ["+dCC_before+"], added future dated income transaction for ["+tRec.getAmount()+"], now the Projected CreditCard balance shows ["+dProjectedCC_after+"]");
		
		if (dProjected_before+d==dProjectedTotal_after)
			Commentary.log(LogStatus.INFO, "Projected Total balance was ["+dProjected_before+"], added future dated income transaction for ["+tRec.getAmount()+"], now the Projected total balance shows ["+dProjectedTotal_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected Total balance was ["+dProjected_before+"], added future dated income transaction for ["+tRec.getAmount()+"], now the Projected total balance shows ["+dProjectedTotal_after+"]");
		
		sa.assertAll();	
		
	}
	
	@Test(priority = 8)
	public void PB9_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "Add a past dated expense transaction for a manual checking account, verify total projected balance of the account's transactions and BCC screen");
		String sProjected_AcctBalance_AccountTxnScreen, sProjected_AcctBalance_bcc, sProjectedAcctBalanceBCC_after, sProjectedAcctBalanceTxnScreen_after;
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualChecking);
		tRec.setAmount("5.00");
		tRec.setTransactionType("expense");
		tRec.setDate(h.getYesterdaysDate());
		h.getContext();
		
		/*
		SignInPage signIn = new SignInPage();
		signIn.signIn(sUserName, sPassword, sDataset);*/
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		// get the projected balance
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		bcc.scrollToProjectedBalance();
		
		sProjected_AcctBalance_bcc = bcc.getAccountBalance(tRec.getAccount());
		Commentary.log(LogStatus.INFO, "BCC screen, Account Projected balance before adding the transaction ["+sProjected_AcctBalance_bcc+"]");
		
		bcc.getAccount(tRec.getAccount()).click();
		Thread.sleep(2000);
		
		TransactionsPage tp = new TransactionsPage();
		sProjected_AcctBalance_AccountTxnScreen = tp.txtProjectedBalanceAmount.getText();
		Commentary.log(LogStatus.INFO, "CheckingAccount's transactions page > Projected balance before adding the transaction on ["+sProjected_AcctBalance_AccountTxnScreen+"]");
		
		Double d = Double.parseDouble(tRec.getAmount());
		Double dChecking_before_txnScreen = h.processBalanceAmount(sProjected_AcctBalance_AccountTxnScreen);
		Double dChecking_before_bccScreen = h.processBalanceAmount(sProjected_AcctBalance_bcc);
		
		tp.addTransaction.click();
		TransactionDetailPage td = new TransactionDetailPage();
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount"+tRec.getAmount());
		h.getContext();
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 5);
		
		sProjectedAcctBalanceTxnScreen_after = tp.txtProjectedBalanceAmount.getText();
		
		tp.backButton.click();
		Thread.sleep(1000);
		
		sProjectedAcctBalanceBCC_after = bcc.getAccountBalance(tRec.getAccount());
		Double dProjectedCheckingBCCScreen_after = h.processBalanceAmount(sProjectedAcctBalanceBCC_after);
		Double dProjectedAcctBalanceTxnScreen_after = h.processBalanceAmount(sProjectedAcctBalanceTxnScreen_after);
		
		System.out.println("****");
		System.out.println(dChecking_before_txnScreen-d);
		System.out.println(dProjectedAcctBalanceTxnScreen_after);
		System.out.println(dChecking_before_txnScreen-d==dProjectedAcctBalanceTxnScreen_after);
		System.out.println("****");
		
		if (dChecking_before_txnScreen-d==dProjectedAcctBalanceTxnScreen_after)
			Commentary.log(LogStatus.INFO, "Checking Account's Projected balance was ["+dChecking_before_txnScreen+"], added past dated expense transaction for ["+tRec.getAmount()+"], now the projected balance on the screen shows ["+dProjectedAcctBalanceTxnScreen_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected Checking balance was ["+dChecking_before_txnScreen+"], added past dated expense transaction for ["+tRec.getAmount()+"], now the projected balance on the screen shows ["+dProjectedAcctBalanceTxnScreen_after+"]");
		
		if (dChecking_before_bccScreen-d==dProjectedCheckingBCCScreen_after)
			Commentary.log(LogStatus.INFO, "Checking Account's Projected balance on BCC screen was ["+dChecking_before_bccScreen+"], added past dated expense transaction for ["+tRec.getAmount()+"], now the projected balance on the screen shows ["+dProjectedCheckingBCCScreen_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Checking Account's Projected balance on BCC screen was ["+dChecking_before_bccScreen+"], added past dated expense transaction for ["+tRec.getAmount()+"], now the projected balance on the screen shows ["+dProjectedCheckingBCCScreen_after+"]");
		
		sa.assertAll();	
		
	}
	
	@Test(priority = 9)
	public void PB10_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "Add a future dated expense transaction for a manual Credit Card account, verify total projected balance of the account on account's transactions and BCC screen");
		String sProjected_AcctBalance_AccountTxnScreen, sProjected_AcctBalance_bcc, sProjectedAcctBalanceBCC_after, sProjectedAcctBalanceTxnScreen_after;
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualCreditCard);
		tRec.setAmount("5.00");
		tRec.setTransactionType("expense");
		tRec.setDate(h.getFutureDaysDate(4));
		h.getContext();
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		// get the projected balance
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		bcc.scrollToProjectedBalance();
		
		sProjected_AcctBalance_bcc = bcc.getAccountBalance(tRec.getAccount());
		Commentary.log(LogStatus.INFO, "Credit Card Account's Projected balance before adding the transaction on BCC screen ["+sProjected_AcctBalance_bcc+"]");
		
		bcc.getAccount(tRec.getAccount()).click();
		Thread.sleep(2000);
		
		TransactionsPage tp = new TransactionsPage();
		sProjected_AcctBalance_AccountTxnScreen = tp.txtProjectedBalanceAmount.getText();
		Commentary.log(LogStatus.INFO, "Credit Card Account's Projected balance before adding the transaction on account's transactions page ["+sProjected_AcctBalance_AccountTxnScreen+"]");
		
		Double d = Double.parseDouble(tRec.getAmount());
		Double dChecking_before_txnScreen = h.processBalanceAmount(sProjected_AcctBalance_AccountTxnScreen);
		Double dChecking_before_bccScreen = h.processBalanceAmount(sProjected_AcctBalance_bcc);
		
		tp.addTransaction.click();
		TransactionDetailPage td = new TransactionDetailPage();
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount"+tRec.getAmount());
		h.getContext();
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 5);
		
		sProjectedAcctBalanceTxnScreen_after = tp.txtProjectedBalanceAmount.getText();
		
		tp.backButton.click();
		Thread.sleep(1000);
		
		sProjectedAcctBalanceBCC_after = bcc.getAccountBalance(tRec.getAccount());
		Double dProjectedCheckingBCCScreen_after = h.processBalanceAmount(sProjectedAcctBalanceBCC_after);
		Double dProjectedAcctBalanceTxnScreen_after = h.processBalanceAmount(sProjectedAcctBalanceTxnScreen_after);
		
		if (dChecking_before_txnScreen-d==dProjectedAcctBalanceTxnScreen_after)
			Commentary.log(LogStatus.INFO, "CreditCard Account's Projected balance was ["+dChecking_before_txnScreen+"], added past dated expense transaction for ["+tRec.getAmount()+"], now the projected balance on the screen shows ["+dProjectedAcctBalanceTxnScreen_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: CreditCard Account's Projected balance was ["+dChecking_before_txnScreen+"], added past dated expense transaction for ["+tRec.getAmount()+"], now the projected balance on the screen shows ["+dProjectedAcctBalanceTxnScreen_after+"]");
		
		if (dChecking_before_bccScreen-d==dProjectedCheckingBCCScreen_after)
			Commentary.log(LogStatus.INFO, "CreditCard Account's Projected balance on BCC screen was ["+dChecking_before_bccScreen+"], added past dated expense transaction for ["+tRec.getAmount()+"], now the projected balance on the screen shows ["+dProjectedCheckingBCCScreen_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: CreditCard Account's Projected balance on BCC screen was ["+dChecking_before_bccScreen+"], added past dated expense transaction for ["+tRec.getAmount()+"], now the projected balance on the screen shows ["+dProjectedCheckingBCCScreen_after+"]");
		
		sa.assertAll();	
		
	}
	
	

}