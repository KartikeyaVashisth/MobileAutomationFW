package tournament;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.BankingAndCreditCardPage;
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

public class TransferCases_Test1 extends Recovery {
	
	//String sUserName = "mobileautomation1@quicken.com";
	String sPassword = "Quicken@01";
	//String sDataset = "Transfers_automation";
	String sDataset = "stg-transfer-automation";
	String sChecking = "manual_Checking";
	String sCreditCard ="manual_Credit Card";
	String sManualSaving = "manual_savings";
	//String sOnlineSaving = "onl_savings";
	String backButton1_ios = "Banking & Credit, back";
	String sSavings = "Savings";
	//String sDataset1 = "OnlineAcc_Automation";
	//String sOnlineChecking = "Checking1 XX8651";
	//String sSaving = "Saving XX3867";
	//String sManualSavings = "Savings";
	
	
public String getUsername_basedOnEnv() throws Exception {
		
		UserName un = new UserName();
		un.stage_ios = "stg-transfer-ios++@stage.com";
		un.stage_android = "stg-transfer-android++@stage.com";
		un.prod_ios = "";
		un.prod_android = "";
		return un.getUserName();
		
	}
	
	@Test(priority = 0, enabled = true)
	public void TR1_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		String sUsername = getUsername_basedOnEnv();
		WelcomePage w = new WelcomePage();
		w.setEnvironment(h.getEnv());
		
		SignInPage signIn = new SignInPage();
		signIn.signIn(sUsername, sPassword, sDataset);
		
		Commentary.log(LogStatus.INFO,	"["+h.getEngine()+"]: Creating a transfer from Checking to Saving account and verifying balances.");
		
		String sCheckingLabel_before, sSavingLabel_before, sCheckingAccount_before, sSavingAccount_before, sCheckingLabel_after, sSavingLabel_after, sCheckingAccount_after, sSavingAccount_after ;
		String time = "Payee_"+h.getCurrentTime();
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		sCheckingLabel_before = bcc.getCheckingBalance();
		sSavingLabel_before = bcc.getSavingsBalance();
		sCheckingAccount_before = bcc.getCheckingAccountBalance();
		sSavingAccount_before = bcc.getSavingsAccountBalance();
		Double dCheckingLabel_before = h.processBalanceAmount(sCheckingLabel_before.replace("SubTotal: ", ""));
		Double dSavingLabel_before = h.processBalanceAmount(sSavingLabel_before.replace("SubTotal: ", ""));
		Double dCheckingAccount_before = h.processBalanceAmount(sCheckingAccount_before.replace("Account Balance: ", ""));
		Double dSavingAccount_before = h.processBalanceAmount(sSavingAccount_before.replace("Account Balance: ", ""));

		Verify.waitForObject(bcc.allTransactionButton, 2);
		bcc.allTransactionButton.click();
		TransactionsPage tp = new TransactionsPage();
		Verify.waitForObject(tp.addTransaction, 2);
		tp.addTransaction.click();
		
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("20.00");
		tRec.setPayee(time);
		tRec.setAccount(sChecking);
		tRec.setCategory("Transfer ["+sSavings+"]");
		tRec.setTransactionType("expense");
		
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec);
		Verify.waitForObject(tp.backButton, 1);
		tp.backButton.click();
		
		sCheckingLabel_after = bcc.getCheckingBalance();
		sSavingLabel_after = bcc.getSavingsBalance();
		sCheckingAccount_after = bcc.getCheckingAccountBalance();
		sSavingAccount_after = bcc.getSavingsAccountBalance();
		Double dCheckingLabel_after = h.processBalanceAmount(sCheckingLabel_after.replace("SubTotal: ", ""));
		Double dSavingLabel_after = h.processBalanceAmount(sSavingLabel_after.replace("SubTotal: ", ""));
		Double dCheckingAccount_after = h.processBalanceAmount(sCheckingAccount_after.replace("Account Balance: ", ""));
		Double dSavingAccount_after = h.processBalanceAmount(sSavingAccount_after.replace("Account Balance: ", ""));
		Double d = Double.parseDouble(tRec.getAmount());
		
		int checkingLabelCompare = Double.compare(dCheckingLabel_after+d, dCheckingLabel_before);
		int savingLabelCompare = Double.compare(dSavingLabel_after-d, dSavingLabel_before);
		int checkingAccountCompare = Double.compare(dCheckingAccount_after+d, dCheckingAccount_before);
		int savingAccountCompare = Double.compare(dSavingAccount_after-d, dSavingAccount_before);
		
//		if (dCheckingLabel_after+d==dCheckingLabel_before)
		if(checkingLabelCompare == 0)
			Commentary.log(LogStatus.INFO, "PASS: Checking Label balance was ["+dCheckingLabel_before+"], done expense transfer transaction of ["+tRec.getAmount()+"], now the Checking Label balance shows ["+dCheckingLabel_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Checking Label balance was ["+dCheckingLabel_before+"], done expense transfer transaction of ["+tRec.getAmount()+"], now the Checking Label balance shows ["+dCheckingLabel_after+"]");
		
//		if (dSavingLabel_after-d==dSavingLabel_before)
		if(savingLabelCompare == 0)
			Commentary.log(LogStatus.INFO, "PASS: Savings Label balance was ["+dSavingLabel_before+"], received transfer transaction of ["+tRec.getAmount()+"], now the Savings Label balance shows ["+dSavingLabel_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Savings Label balance was ["+dSavingLabel_before+"], received transfer transaction of ["+tRec.getAmount()+"], now the Savings Label balance shows ["+dSavingLabel_after+"]");

//		if (dCheckingAccount_after+d==dCheckingAccount_before)
		if(checkingAccountCompare == 0)
			Commentary.log(LogStatus.INFO, "PASS: Checking Account balance was ["+dCheckingAccount_before+"], done expense transfer transaction of ["+tRec.getAmount()+"], now the Checking Account balance shows ["+dCheckingAccount_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Checking Account balance was ["+dCheckingAccount_before+"], done expense transfer transaction of ["+tRec.getAmount()+"], now the Checking Account balance shows ["+dCheckingAccount_after+"]");
		
//		if (dSavingAccount_after-d==dSavingAccount_before)
		if(savingAccountCompare == 0)
			Commentary.log(LogStatus.INFO, "PASS: Savings Account balance was ["+dSavingAccount_before+"], received transfer transaction of ["+tRec.getAmount()+"], now the Savings Account balance shows ["+dSavingAccount_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Savings Account balance was ["+dSavingAccount_before+"], received transfer transaction of ["+tRec.getAmount()+"], now the Savings Account balance shows ["+dSavingAccount_after+"]");

		sa.assertAll();
	}
	
	@Test(priority = 1, enabled = true)
	public void TR2_test() throws Exception{

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO,	"["+h.getEngine()+"]: Creating and Editing a transfer from Checking to Saving account and verifying balances.");
		
		String sChecking_before,sSaving_after, sSaving_before, sChecking_after, sChecking_after_editing, sSaving_after_editing ;
		String time = "Payee_"+h.getCurrentTime();
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		sChecking_before = bcc.getCheckingBalance();
		sSaving_before = bcc.getSavingsBalance();
		Double dChecking_before = h.processBalanceAmount(sChecking_before.replace("SubTotal: ", ""));
		Double dSaving_before = h.processBalanceAmount(sSaving_before.replace("SubTotal: ", ""));

		TransactionsPage tp = new TransactionsPage();
		tp.tapOnAddTransactionButtonFromAllTransactionsPage();
		
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		
		tRec.setAmount("100.00");
		tRec.setPayee(time);
		tRec.setAccount(sChecking);
		tRec.setCategory("Transfer ["+sSavings+"]");
		tRec.setTransactionType("expense");
		
		td.addTransaction(tRec);
		
		Verify.waitForObject(tp.backButton, 2);
		tp.backButton.click();
		
		sChecking_after = bcc.getCheckingBalance();
		sSaving_after = bcc.getSavingsBalance();
		Double dChecking_after = h.processBalanceAmount(sChecking_after.replace("SubTotal: ", ""));
		Double dSaving_after = h.processBalanceAmount(sSaving_after.replace("SubTotal: ", ""));
		Double d = Double.parseDouble(tRec.getAmount());
		
		int checkingCompare = Double.compare(dChecking_after+d, dChecking_before);
		int savingCompare = Double.compare(dSaving_after-d, dSaving_before);
		
//		if (dChecking_after+d==dChecking_before)
		if(checkingCompare == 0)
			Commentary.log(LogStatus.INFO, "PASS: Checking balance was ["+dChecking_before+"], done expense transfer transaction of ["+tRec.getAmount()+"], now the checking balance shows ["+dChecking_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Checking balance was ["+dChecking_before+"], done expense transfer transaction of ["+tRec.getAmount()+"], now the checking balance shows ["+dChecking_after+"]");
		
//		if (dSaving_after-d==dSaving_before)
		if(savingCompare == 0)
			Commentary.log(LogStatus.INFO, "PASS: Savings balance was ["+dSaving_before+"], received transfer transaction of ["+tRec.getAmount()+"], now the savings balance shows ["+dSaving_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Savings balance was ["+dSaving_before+"], received transfer transaction of ["+tRec.getAmount()+"], now the savings balance shows ["+dSaving_after+"]");
				
		tp.searchTransaction(time);
		Verify.waitForObject(tp.thisMonthLabel, 2);
		tp.tapOnFirstTransaction();
		Verify.waitForObject(td.dateLabel, 2);
		
		tRec = new TransactionRecord();
		Double txnAmount_before = h.processBalanceAmount(td.getTransactionAmount().replace("Amount: ", ""));
		Commentary.log(LogStatus.INFO, "Transaction amount before "+txnAmount_before);
		tRec.setAmount("10.00");
		td.editTransaction(tRec);
		
		d = Double.parseDouble(tRec.getAmount());		
		
		SettingsPage sp = new SettingsPage();
		
		sp.selectBack(backButton1_ios);
		
		Verify.waitForObject(bcc.checkingBalance, 2);
		sChecking_after_editing = bcc.checkingBalance.getText();
		sSaving_after_editing = bcc.savingsBalance.getText();
		Double dSaving_after_editing = h.processBalanceAmount(sSaving_after_editing.replace("SubTotal: ", ""));
		Double dChecking_after_editing = h.processBalanceAmount(sChecking_after_editing.replace("SubTotal: ", ""));		
		
		int checking_afterEditingCompare = Double.compare(dChecking_after_editing+d, dChecking_after);
		int saving_afterEditingCompare = Double.compare(dSaving_after_editing-d, dSaving_after);
		
//		if (dChecking_after_editing+d==dChecking_after)
		if(checking_afterEditingCompare == 0)
			Commentary.log(LogStatus.INFO, "PASS: Checking balance was ["+dChecking_after+"], edited transfer transaction to ["+tRec.getAmount()+"], now the checking balance shows ["+dChecking_after_editing+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Checking balance was ["+dChecking_after+"], edited transfer transaction to ["+tRec.getAmount()+"], now the checking balance shows ["+dChecking_after_editing+"]");
		
//		if (dSaving_after_editing-d==dSaving_after)
		if(saving_afterEditingCompare == 0)
			Commentary.log(LogStatus.INFO, "PASS: Savings balance was ["+dSaving_after+"], received updated transfer transaction of ["+tRec.getAmount()+"], now the savings balance shows ["+dSaving_after_editing+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Savings balance was ["+dSaving_after+"], received updated transfer transaction of ["+tRec.getAmount()+"], now the savings balance shows ["+dSaving_after_editing+"]");
	
		sa.assertAll();
	}
	
	@Test(priority = 2, enabled = true)
	public void TR3_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
	
		Commentary.log(LogStatus.INFO,	"["+h.getEngine()+"]: Creating a transfer transaction and Verifying deleting of transfer transaction with Warning message.");
		
		String payeeName = "Payee_"+h.getCurrentTime();
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		TransactionsPage tp = new TransactionsPage();
		tp.tapOnAddTransactionButtonFromAllTransactionsPage();
		
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("10.00");
		tRec.setPayee(payeeName);
		tRec.setAccount(sChecking);
		tRec.setCategory("Transfer ["+sSavings+"]");
		tRec.setTransactionType("expense");
		
		td.addTransaction(tRec);
		Thread.sleep(2000);
		
		tp.searchRecentTransaction(payeeName);
		Verify.waitForObject(tp.thisMonthLabel, 2);
		tp.tapOnFirstTransaction();
		Thread.sleep(2000);
		
//		Verify.waitForObject(td.selectedpayee, 2);
		Verify.waitForObject(td.selectedAccount, 2);
		String Selectedpayee = td.getTransactionPayee();
		String selectedAccount = td.getTransactionAccount();
		
		op.scroll_down();
		td.buttonGoToOtherSide.click();
		Verify.waitForObject(td.viewTransactionTxt, 2);
		Verify.waitForObject(td.dateLabel, 2);
		
		op.scroll_down();
		td.deleteTransaction.click();
		Thread.sleep(2000);
		
		if (Verify.objExists_check(td.deleteTransactionAlertButton)) {
			td.deleteTransactionAlertButton.click();
			}
		Verify.waitForObject(td.deleteWarningMessage, 2);
		
		String expWarningText = "You are deleting the transfer transaction "+"\""+Selectedpayee+"\""+". This will also delete the other side of the transfer in account "+selectedAccount+"";
		String warningMessageText = td.deleteWarningMessage.getText();
		
		if (Verify.objExists(td.deleteTransferTransactionWarning) || warningMessageText.equalsIgnoreCase(expWarningText) ) {
			Commentary.log(LogStatus.INFO, "PASS: Warning messsage is displayed on deleting transfer transactions.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Warning messsage is NOT displayed on deleting transfer transactions.");
		}
		
		sa.assertAll();	
	}
	
	@Test(priority = 3, enabled = true)
	public void TR4_test() throws Exception{

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO,	"["+h.getEngine()+"]: Creating a transfer transaction and Verifying the balances after deletion of transfer transaction.");
		
		String sChecking_before, sSaving_before, sChecking_afterDeleting, sSaving_afterDeleting ;
		String payeeName = "Payee_"+h.getCurrentTime();
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		sChecking_before = bcc.getCheckingBalance();
		sSaving_before = bcc.getSavingsBalance();
		Double dChecking_before = h.processBalanceAmount(sChecking_before.replace("SubTotal: ", ""));
		Double dSaving_before = h.processBalanceAmount(sSaving_before.replace("SubTotal: ", ""));

		TransactionsPage tp = new TransactionsPage();
		tp.tapOnAddTransactionButtonFromAllTransactionsPage();
		
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("10.00");
		tRec.setPayee(payeeName);
		tRec.setAccount(sChecking);
		tRec.setCategory("Transfer ["+sSavings+"]");
		tRec.setTransactionType("expense");
		
		td.addTransaction(tRec);
		Verify.waitForObject(tp.searchTransactionTxtField, 2);
		
		tp.searchRecentTransaction(payeeName);
		
		Verify.waitForObject(tp.thisMonthLabel, 2);
		tp.tapOnFirstTransaction();
		Verify.waitForObject(td.dateLabel, 2);
		
		op.scroll_down();
		td.deleteTransaction.click();
		
		if (Verify.objExists_check(td.deleteTransactionAlertButton)) {
		td.deleteTransactionAlertButton.click();
		}
		
		if (Verify.objExists_check(td.deleteTransferTransactionWarning)) {
			Commentary.log(LogStatus.INFO, "PASS: Warning messsage is displayed on deleting transfer transactions.");
			td.buttonOK.click();
		} else {
			Commentary.log(LogStatus.FAIL, "FAIL: Warning messsage is NOT displayed on deleting transfer transactions.");
		}
		
		Verify.waitForObject(tp.searchTransactionTxtField, 2);
		
		tp.searchRecentTransaction(payeeName);

		Verify.waitForObject(tp.txtNoResultFound, 2);
		
		if (Verify.objExists(tp.txtNoResultFound))
			Commentary.log(LogStatus.INFO, "PASS: Successfully Deleted the selected transaction.");
		else
			Commentary.log(sa, LogStatus.FAIL, "Unable to Delete the selected transaction.");
		
		Verify.waitForObject(tp.backButton, 2);
		tp.backButton.click();
		
		sChecking_afterDeleting = bcc.getCheckingBalance();
		sSaving_afterDeleting = bcc.getSavingsBalance();
		Double dChecking_afterDeleting = h.processBalanceAmount(sChecking_afterDeleting.replace("SubTotal: ", ""));
		Double dSaving_afterDeleting = h.processBalanceAmount(sSaving_afterDeleting.replace("SubTotal: ", ""));
		
		Integer balanceCompare_Checking = Double.compare(dChecking_before, dChecking_afterDeleting);
		Integer balanceCompare_Savings = Double.compare(dSaving_before, dSaving_afterDeleting);
		
		if (balanceCompare_Checking==0)
			Commentary.log(LogStatus.INFO, "PASS: Checking balance earlier was ["+dChecking_before+"], now the checking balance after addition/deletion operation of the same transfer transaction shows ["+dChecking_afterDeleting+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Checking balance earlier was ["+dChecking_before+"], now the checking balance after addition/deletion operation of the same transfer transaction shows ["+dChecking_afterDeleting+"]");
		
		if (balanceCompare_Savings==0)
			Commentary.log(LogStatus.INFO, "PASS: Savings balance earlier was ["+dSaving_before+"], now the Savings balance after addition/deletion operation of the same transfer transaction shows ["+dSaving_afterDeleting+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Savings balance earlier was ["+dSaving_before+"], now the Savings balance after addition/deletion operation of the same transfer transaction shows ["+dSaving_afterDeleting+"]");
	
		sa.assertAll();
	}
		
	@Test(priority = 4, enabled = true)
	public void TR5_test() throws Exception{

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO,	"["+h.getEngine()+"]: Creating a transfer transaction and editing the amount and verifying the changes on both the transfer transactions.");
		
		String payeeName = "Payee_"+h.getCurrentTime();
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		TransactionsPage tp = new TransactionsPage();
		tp.tapOnAddTransactionButtonFromAllTransactionsPage();
		
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("10.00");
		tRec.setPayee(payeeName);
		tRec.setAccount(sChecking);
		tRec.setCategory("Transfer ["+sSavings+"]");
		tRec.setTransactionType("expense");
		
		td.addTransaction(tRec);
		
		tp.searchRecentTransaction(payeeName);
		
		Verify.waitForObject(tp.thisMonthLabel, 2);
		tp.tapOnFirstTransaction();
		Thread.sleep(2000);
		
		tRec = new TransactionRecord();
		
		tRec.setAmount("100.00");
		
		td.editTransaction(tRec);
		Thread.sleep(3000);
		
		tp.tapOnFirstTransaction();
		Verify.waitForObject(td.viewTransactionTxt, 2);
		Verify.waitForObject(td.dateLabel, 2);
		
		Double firstSideAmount = h.processBalanceAmount(td.getTransactionAmount().replace("Amount: ", ""));
		
		if (firstSideAmount.equals(-100.00)) {
			Commentary.log(LogStatus.INFO, "PASS: Amount is updated on first side(expense side) of the transfer transaction.");

			Thread.sleep(2000);
			op.scroll_down();
			Verify.waitForObject(td.buttonGoToOtherSide, 1);
			td.buttonGoToOtherSide.click();

			Verify.waitForObject(td.viewTransactionTxt, 1);
			Verify.waitForObject(td.dateLabel, 1);

			Double secondSideAmount = h.processBalanceAmount(td.getTransactionAmount().replace("Amount: ", ""));

			if (secondSideAmount.equals(100.00))
				Commentary.log(LogStatus.INFO, "PASS: Amount is updated on second side(income side) of the transfer transaction.");
			else
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Amount is NOT updated on second side(income side) of transfer transaction.");
		}
		
		else if (firstSideAmount.equals(100.00)) {
			Commentary.log(LogStatus.INFO, "PASS: Amount is updated on first side(income side) of the transfer transaction.");

			Thread.sleep(2000);
			op.scroll_down();
			Verify.waitForObject(td.buttonGoToOtherSide, 1);
			td.buttonGoToOtherSide.click();

			Verify.waitForObject(td.viewTransactionTxt, 1);
			Verify.waitForObject(td.dateLabel, 1);

			Double secondSideAmount = h.processBalanceAmount(td.getTransactionAmount().replace("Amount: ", ""));

			if (secondSideAmount.equals(-100.00))
				Commentary.log(LogStatus.INFO, "PASS: Amount is updated on second side(expense side) of the transfer transaction.");
			else
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Amount is NOT updated on second side(expense side) of transfer transaction.");
		}
		
		sa.assertAll();		
	}
	
	@Test(priority = 5, enabled = true)
	public void TR6_test() throws Exception{
	
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO,	"["+h.getEngine()+"]: Creating a transfer transaction and changing the date and verify.");
		
		String time = "Payee_"+h.getCurrentTime();
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		TransactionsPage tp = new TransactionsPage();
		tp.tapOnAddTransactionButtonFromAllTransactionsPage();
		
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("100.00");
		tRec.setPayee(time);
		tRec.setAccount(sChecking);
		tRec.setCategory("Transfer ["+sSavings+"]");
		tRec.setTransactionType("expense");
		//tRec.setDate(h.getFutureDaysDate(0));
		
		td.addTransaction(tRec);
		
		tp.searchRecentTransaction(time);
		Verify.waitForObject(tp.thisMonthLabel, 2);
		tp.tapOnFirstTransaction();
		Verify.waitForObject(td.dateLabel, 2);
		
		tRec = new TransactionRecord();
		tRec.setDate(h.getFutureDaysDate(2));
		
		td.editTransaction(tRec);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		
		String date = h.getDateformat(2);
		
		tp.tapOnFirstTransaction();
		Verify.waitForObject(td.dateLabel, 2);
		
		String actDate = td.getTransactionDate();
		
		if (date.equalsIgnoreCase(actDate))
			Commentary.log(LogStatus.INFO, "PASS: Date expected ["+date+"] and actual is ["+actDate+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Date expected ["+date+"] and actual is ["+actDate+"]");
		
		op.scroll_down();
		td.buttonGoToOtherSide.click();
		Verify.waitForObject(td.viewTransactionTxt, 2);
		Verify.waitForObject(td.dateLabel, 2);
	
		if (date.equalsIgnoreCase(actDate))
			Commentary.log(LogStatus.INFO, "PASS: Date expected ["+date+"] and actual is ["+actDate+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Date expected ["+date+"] and actual is ["+actDate+"]");
		
		sa.assertAll();	
	}
	
	@Test(priority = 6, enabled = true)
	public void TR7_test() throws Exception{

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO,	"["+h.getEngine()+"]: Creating a transfer transaction and editing the account and verifying the error message.");
		
		String payeeName = "Payee_"+h.getCurrentTime();
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		TransactionsPage tp = new TransactionsPage();
		tp.tapOnAddTransactionButtonFromAllTransactionsPage();
		
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("1.00");
		tRec.setPayee(payeeName);
		tRec.setAccount(sChecking);
		tRec.setCategory("Transfer ["+sSavings+"]");
		tRec.setTransactionType("expense");
		
		td.addTransaction(tRec);
		
		tp.searchRecentTransaction(payeeName);
		Verify.waitForObject(tp.thisMonthLabel, 2);
		tp.tapOnFirstTransaction();
		Verify.waitForObject(td.dateLabel, 2);		
		
		tRec = new TransactionRecord();
		tRec.setAccount(sCreditCard);
		td.editTransaction(tRec);
		Thread.sleep(2000);
		
		String actMessage = td.errorMsgText.getText().replace("\n", "");
		String expMessage = "You cannot change the account of a transfer transaction";
		
		if (actMessage.equalsIgnoreCase(expMessage))
			Commentary.log(LogStatus.INFO, "PASS: Error message is displayed and account is unchanged.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Error message is NOT displayed.");
		
		sa.assertAll();
	}
	
	@Test(priority = 7, enabled = true)
	public void TR8_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO,	"["+h.getEngine()+"]: Creating a transfer transaction and editing the payee of one side and verifying the other part of transfer.");
		
		String payeeName = "Payee_"+h.getCurrentTime();
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		TransactionsPage tp = new TransactionsPage();
		tp.tapOnAddTransactionButtonFromAllTransactionsPage();
		
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("11.00");
		tRec.setPayee(payeeName);
		tRec.setAccount(sChecking);
		tRec.setCategory("Transfer ["+sCreditCard+"]");
		tRec.setTransactionType("expense");
		
		td.addTransaction(tRec);
		
		tp.searchRecentTransaction(payeeName);
		Verify.waitForObject(tp.thisMonthLabel, 2);
		tp.tapOnFirstTransaction();
		Verify.waitForObject(td.dateLabel, 2);		
		
		tRec = new TransactionRecord();
		String payeeName_updated = "Payee_"+h.getCurrentTime();
		tRec.setPayee(payeeName_updated);
		td.editTransaction(tRec);
		Thread.sleep(2000);
		
		tp.searchRecentTransaction(payeeName_updated);
		
		tp.tapOnFirstTransaction();
		Verify.waitForObject(td.dateLabel, 2);
		
		String actPayee = td.getTransactionPayee();
		
		if (actPayee.equalsIgnoreCase(payeeName_updated))
			Commentary.log(LogStatus.INFO, "PASS: Payee is unchanged for other side of transfer.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Test failed as payee is changed on both side of transfer.");
		
		op.scroll_down();
		td.buttonGoToOtherSide.click();
		Verify.waitForObject(td.viewTransactionTxt, 2);
		Verify.waitForObject(td.dateLabel, 2);
		
		actPayee = td.getTransactionPayee();

		if (actPayee.equalsIgnoreCase(payeeName))
			Commentary.log(LogStatus.INFO, "PASS: Payee is changed only for one side of transfer.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Test failed as payee is changed on both side of transfer.");
		
		sa.assertAll();
	}
	
	@Test(priority = 8, enabled = true)
	public void TR9_test() throws Exception{

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO,	"["+h.getEngine()+"]: Creating a transfer transaction and breaking the transfer and verifiying the account balance.");
		
		String sChecking_before, sTotal_before, sSaving_after, sSaving_before, sChecking_after, sTotal_after ;
		String payeeName = "Payee_"+h.getCurrentTime();
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		sChecking_before = bcc.getCheckingBalance();
		sSaving_before = bcc.getSavingsBalance();
		sTotal_before = bcc.getTotalBalance();
		Double dChecking_before = h.processBalanceAmount(sChecking_before.replace("SubTotal: ", ""));
		Double dSaving_before = h.processBalanceAmount(sSaving_before.replace("SubTotal: ", ""));
		Double dTotal_before = h.processBalanceAmount(sTotal_before);
		//Double dCredit_before = h.processBalanceAmount(sCredit_before);

		TransactionsPage tp = new TransactionsPage();
		tp.tapOnAddTransactionButtonFromAllTransactionsPage();
		
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("26.00");
		tRec.setPayee(payeeName);
		tRec.setAccount(sChecking);
		tRec.setCategory("Transfer ["+sSavings+"]");
		tRec.setTransactionType("expense");
		
		td.addTransaction(tRec);
		Thread.sleep(2000);
		Double d = Double.parseDouble(tRec.getAmount());

		tp.searchRecentTransaction(payeeName);
		Verify.waitForObject(tp.thisMonthLabel, 2);
		tp.tapOnFirstTransaction();
		Verify.waitForObject(td.dateLabel, 2);
		
		tRec = new TransactionRecord();
		tRec.setCategory("Internet");
		td.editTransaction(tRec);
		Verify.waitForObject(tp.backButton, 2);
		tp.backButton.click();
		
		sChecking_after = bcc.getCheckingBalance();
		sSaving_after = bcc.getSavingsBalance();
		sTotal_after = bcc.getTotalBalance();
		Double dChecking_after = h.processBalanceAmount(sChecking_after.replace("SubTotal: ", ""));
		Double dSaving_after = h.processBalanceAmount(sSaving_after.replace("SubTotal: ", ""));
		Double dTotal_after = h.processBalanceAmount(sTotal_after);
		
		int savingCompare = Double.compare(dSaving_after-d, dSaving_before);
		int totalCompare = Double.compare(dTotal_after-d, dTotal_before);
		
		Commentary.log(LogStatus.INFO, "diff amount is "+d);

		Commentary.log(LogStatus.INFO, "after total amount "+(dTotal_after+d));

		if (dChecking_after.equals(dChecking_before))
			Commentary.log(LogStatus.INFO, "PASS: Checking balance was ["+dChecking_before+"], after breaking the transfer, checking balance is unchanged and shows ["+dChecking_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Checking balance was ["+dChecking_before+"], after breaking the transfer, checking balance is changed and shows ["+dChecking_after+"]");
		
//		if (dSaving_after-d==dSaving_before)
		if(savingCompare == 0)
			Commentary.log(LogStatus.INFO, "PASS: Savings balance was ["+dSaving_before+"], after changing the category and breaking the transfer for amount ["+d+"], now the savings balance shows ["+dSaving_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Savings balance was ["+dSaving_before+"], after changing the category and breaking the transfer for amount ["+d+"], now the savings balance shows ["+dSaving_after+"]");
		
//		if (dTotal_after-d==dTotal_before)
		if(totalCompare == 0)
			Commentary.log(LogStatus.INFO, "PASS: Total balance was ["+dTotal_before+"], after breaking the transfer, total balance shows ["+dTotal_after+"]");
		else 
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Total balance was ["+dTotal_before+"], after breaking the transfer, total balance shows ["+dTotal_after+"]");
		
		sa.assertAll();	
	}
	
	@Test(priority = 9, enabled = true)
	public void TR10_test() throws Exception{

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO,	"["+h.getEngine()+"]: Creating a transfer transaction and breaking the transfer and verifying that one transaction is removed.");
		
		String payeeName = "Payee_"+h.getCurrentTime();
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		TransactionsPage tp = new TransactionsPage();
		tp.tapOnAddTransactionButtonFromAllTransactionsPage();
		
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("37.00");
		tRec.setPayee(payeeName);
		tRec.setAccount(sChecking);
		tRec.setCategory("Transfer ["+sSavings+"]");
		tRec.setTransactionType("expense");
		
		td.addTransaction(tRec);
		Thread.sleep(2000);

		tp.searchRecentTransaction(payeeName);
		Verify.waitForObject(tp.thisMonthLabel, 2);
		tp.tapOnFirstTransaction();
		Verify.waitForObject(td.dateLabel, 2);
		
		tRec = new TransactionRecord();
		tRec.setCategory("Mobile Phone");
		td.editTransaction(tRec);
		Thread.sleep(3000);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		
		tp.tapOnFirstTransaction();
		Verify.waitForObject(td.dateLabel, 2);
		op.scroll_down();
		if (!(Verify.objExists(td.buttonGoToOtherSide)))
			Commentary.log(LogStatus.INFO, "PASS: Go to Other side of the transaction option is successfully removed after canceling the transfer transaction.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Go to Other side of the transaction option is NOT removed after canceling the transfer transaction.");
		
		sa.assertAll();
	}
	
	@Test(priority = 10, enabled = true)
	public void TR11_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO,	"["+h.getEngine()+"]: Creating a transfer transaction and editing the transfer account to any other and verifying the list for transfer transactions.");
		
		String transferToAcc1 = "Transfer ["+sCreditCard+"]";
		String transferToAcc2 = "Transfer ["+sSavings+"]";
		String payeeName = "Payee_"+h.getCurrentTime();
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		TransactionsPage tp = new TransactionsPage();
		tp.tapOnAddTransactionButtonFromAllTransactionsPage();
		
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("16.00");
		tRec.setPayee(payeeName);
		tRec.setAccount(sChecking);
		tRec.setCategory(transferToAcc1);
		tRec.setTransactionType("expense");
		
		td.addTransaction(tRec);
		
		tp.searchRecentTransaction(payeeName);
		Verify.waitForObject(tp.thisMonthLabel, 2);
		tp.tapOnFirstTransaction();
		Verify.waitForObject(td.dateLabel, 2);
		
		op.scroll_down();
		td.buttonGoToOtherSide.click();
		Verify.waitForObject(td.viewTransactionTxt, 2);
		Verify.waitForObject(td.dateLabel, 2);
		
		tRec = new TransactionRecord();
		tRec.setCategory(transferToAcc2);
		td.editTransaction(tRec);
		
		Verify.waitForObject(td.backButtonOnViewTransactionPage, 1);
		td.backButtonOnViewTransactionPage.click();
		
		tp.searchRecentTransaction(payeeName);
		Verify.waitForObject(tp.thisMonthLabel, 2);
		tp.tapOnFirstTransaction();
		Verify.waitForObject(td.dateLabel, 2);
		
		String actCategory = td.getTransactionCategory();
		
		if (!(actCategory.equalsIgnoreCase(transferToAcc1)))
			Commentary.log(LogStatus.INFO, "PASS: Transfer To account is correct.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Transfer To account is Incorrect.");
		
		op.scroll_down();
		td.buttonGoToOtherSide.click();
		Verify.waitForObject(td.viewTransactionTxt, 2);
		Verify.waitForObject(td.dateLabel, 2);
		
		actCategory = td.getTransactionCategory();

		if (!(actCategory.equalsIgnoreCase(transferToAcc1)))
			Commentary.log(LogStatus.INFO, "PASS: Transfer To account is correct for other side of the transfer.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Transfer To account is Incorrect.");
		
		sa.assertAll();
	}
	
	@Test(priority = 11, enabled = true)
	public void TR12_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO,	"["+h.getEngine()+"]: Creating a transfer transaction and editing the transfer type to Expense for +ve side of transfer transaction.");
		
		String transferToAcc1 = "Transfer ["+sCreditCard+"]";
		String payeeName = "Payee_"+h.getCurrentTime();
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		TransactionsPage tp = new TransactionsPage();
		tp.tapOnAddTransactionButtonFromAllTransactionsPage();
		
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("17.50");
		tRec.setPayee(payeeName);
		tRec.setAccount(sChecking);
		tRec.setCategory(transferToAcc1);
		tRec.setTransactionType("expense");
		
		td.addTransaction(tRec);
		
		tp.searchRecentTransaction(payeeName);
		Verify.waitForObject(tp.thisMonthLabel, 2);
		tp.tapOnFirstTransaction();
		Verify.waitForObject(td.dateLabel, 2);	
		
		tRec = new TransactionRecord();
		tRec.setTransactionType("expense");
		td.editTransaction(tRec);
		Thread.sleep(500);
				
		tp.tapOnFirstTransaction();
		Verify.waitForObject(td.dateLabel, 2);
		
		String txnAmount = td.getTransactionAmount().replace("Amount: ", "");
		
		if (txnAmount.contains("-$"))
			Commentary.log(LogStatus.INFO, "PASS: Amount is changed from Income to Expense value.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Amount is NOT changed from Income to Expense value.");
		
		Thread.sleep(2000);
		op.scroll_down();
		Verify.waitForObject(td.buttonGoToOtherSide, 2);
		td.buttonGoToOtherSide.click();
		Verify.waitForObject(td.viewTransactionTxt, 2);
		Verify.waitForObject(td.dateLabel, 2);
		
		txnAmount = td.getTransactionAmount().replace("Amount: ", "");

		if (!(txnAmount.contains("-$")))
			Commentary.log(LogStatus.INFO, "PASS: Amount is changed from Expense to Income value.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Amount is NOT changed from Expense to Income value.");
		
		sa.assertAll();
	}
	
	@Test(priority = 12, enabled = true)
	public void TR13_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO,	"["+h.getEngine()+"]: Creating two normal transaction and editing the category to transfer account and verifying the alert message displayed for transaction match.");
		
		String transferToAcc1 = "Transfer ["+sSavings+"]";
		String payeeName = "Payee_"+h.getCurrentTime();
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		TransactionsPage tp = new TransactionsPage();
		tp.tapOnAddTransactionButtonFromAllTransactionsPage();
		
		//Adding one normal transaction
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("29.00");
		tRec.setPayee(payeeName);
		tRec.setAccount(sChecking);
		//tRec.setCategory("");
		tRec.setTransactionType("expense");
		
		td.addTransaction(tRec);
		
		//Adding another normal transaction
		
		tp.addTransaction.click();
		tRec = new TransactionRecord();
		tRec.setAmount("29.00");
		tRec.setTransactionType("income");
		tRec.setPayee(payeeName);
		tRec.setAccount(sSavings);
		
		td.addTransaction(tRec);
		
		tp.searchRecentTransaction(payeeName);
		Verify.waitForObject(tp.thisMonthLabel, 2);
		tp.tapOnFirstTransaction();
		Verify.waitForObject(td.dateLabel, 2);
		
		tRec = new TransactionRecord();
		tRec.setCategory(transferToAcc1);
		td.editTransaction(tRec);
		Thread.sleep(500);
		
		Verify.waitForObject(td.matchingTransactionAlertMessageTxt, 2);
		if (Verify.objExists(td.matchingTransactionAlertMessageTxt))		
			Commentary.log(LogStatus.INFO, "PASS: Matching Transaction Alert message is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Matching Transaction Alert message is NOT displayed.");
		
		String actAlertText = td.matchingTransactionAlertMessageTxt.getText();
		String expAlertText = "Matching transaction found";
		
		if (actAlertText.equals(expAlertText))
			Commentary.log(LogStatus.INFO, "PASS: Matching Transaction Alert message is correct.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Matching Transaction Alert message is NOT correct.");
		
		if (Verify.objExists(td.buttonMatch))
			Commentary.log(LogStatus.INFO, "PASS: MATCH button is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: MATCH button is NOT displayed.");
		
		if (Verify.objExists(td.buttonDontMatch))
			Commentary.log(LogStatus.INFO, "PASS: DON'T MATCH button is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: DON'T MATCH button is NOT displayed.");
		
		sa.assertAll();
	}
	
//	@Test(priority = 13, enabled = true)
//	public void TR14_test() throws Exception{
//		
//		SoftAssert sa = new SoftAssert();
//		Helper h = new Helper();
//		
//		Commentary.log(LogStatus.INFO,	"["+h.getEngine()+"]: Creating two normal transaction and editing the category to transfer account and verifying MATCH functionality.");
//		
//		String transferToAcc1 = "Transfer ["+sSavings+"]";
//		String payeeName = "Payee_"+h.getCurrentTime();
//		
//		OverviewPage op = new OverviewPage();
//		op.navigateToAcctList();
//		
//		TransactionsPage tp = new TransactionsPage();
//		tp.tapOnAddTransactionButtonFromAllTransactionsPage();
//		
//		//Adding one normal transaction
//		TransactionDetailPage td = new TransactionDetailPage();
//		TransactionRecord tRec = new TransactionRecord();
//		tRec.setAmount("39.00");
//		tRec.setPayee(payeeName);
//		tRec.setAccount(sChecking);
//		//tRec.setCategory("");
//		tRec.setTransactionType("expense");
//		
//		td.addTransaction(tRec);
//		
//		//Adding another normal transaction
//		
//		tp.addTransaction.click();
//		tRec = new TransactionRecord();
//		tRec.setAmount("39.00");
//		tRec.setTransactionType("income");
//		tRec.setPayee(payeeName);
//		tRec.setAccount(sSavings);
//		
//		td.addTransaction(tRec);
//		
//		tp.searchRecentTransaction(payeeName);
//		Verify.waitForObject(tp.thisMonthLabel, 3);
//		tp.tapOnFirstTransaction();
//		Verify.waitForObject(td.dateLabel, 2);
//		
//		tRec = new TransactionRecord();
//		tRec.setCategory(transferToAcc1);
//		td.editTransaction(tRec);
//		Thread.sleep(500);
//		
//		Verify.waitForObject(td.buttonMatch, 2);
//		td.buttonMatch.click();
//		Thread.sleep(2000);
//		
//		tp.searchRecentTransaction(payeeName);
//		Verify.waitForObject(tp.thisMonthLabel, 3);
//		
//		int numOfTransaction = tp.getTransactionListSize();
//		
//		if (numOfTransaction==2)		
//			Commentary.log(LogStatus.INFO, "PASS: Transaction is matched with the other created transaction.");
//		else
//			Commentary.log(sa, LogStatus.FAIL, "FAIL: Transaction is NOT matched with the other created transaction.");
//		
//		sa.assertAll();
//	}
//	
//	@Test(priority = 14, enabled = true)
//	public void TR15_test() throws Exception{
//		
//		SoftAssert sa = new SoftAssert();
//		Helper h = new Helper();
//		
//		Commentary.log(LogStatus.INFO,	"["+h.getEngine()+"]: Creating two normal transaction and editing the category to transfer account and verifying DON'T MATCH functionality.");
//		
//		String transferToAcc1 = "Transfer ["+sSavings+"]";
//		String payeeName = "Payee_"+h.getCurrentTime();
//		
//		OverviewPage op = new OverviewPage();
//		op.navigateToAcctList();
//		
//		TransactionsPage tp = new TransactionsPage();
//		tp.tapOnAddTransactionButtonFromAllTransactionsPage();
//		
//		//Adding one normal transaction
//		TransactionDetailPage td = new TransactionDetailPage();
//		TransactionRecord tRec = new TransactionRecord();
//		tRec.setAmount("55.00");
//		tRec.setPayee(payeeName);
//		tRec.setAccount(sChecking);
//		//tRec.setCategory("");
//		tRec.setTransactionType("expense");
//		
//		td.addTransaction(tRec);
//		
//		//Adding another normal transaction
//		
//		tp.addTransaction.click();
//		tRec = new TransactionRecord();
//		tRec.setAmount("55.00");
//		tRec.setTransactionType("income");
//		tRec.setPayee(payeeName);
//		tRec.setAccount(sSavings);
//		
//		td.addTransaction(tRec);
//		
//		tp.searchRecentTransaction(payeeName);
//		Verify.waitForObject(tp.thisMonthLabel, 3);
//		tp.tapOnFirstTransaction();
//		Verify.waitForObject(td.dateLabel, 2);
//		
//		tRec = new TransactionRecord();
//		tRec.setCategory(transferToAcc1);
//		td.editTransaction(tRec);
//		Thread.sleep(500);
//		
//		Verify.waitForObject(td.buttonDontMatch, 2);
//		td.buttonDontMatch.click();
//		
//		tp.searchRecentTransaction(payeeName);
//		Verify.waitForObject(tp.thisMonthLabel, 3);
//		
//		int numOfTransaction = tp.getTransactionListSize();
//		
//		if (numOfTransaction==3)		
//			Commentary.log(LogStatus.INFO, "PASS: As Expected, Transaction isn't matched with the other created transaction after choosing Don't Match option.");
//		else
//			Commentary.log(sa, LogStatus.FAIL, "FAIL: Transaction matched with the other created transaction.");
//		
//		sa.assertAll();
//	}
//	
//	@Test(priority = 15, enabled = true)
//	public void TR16_test() throws Exception{
//
//		SoftAssert sa = new SoftAssert();
//		Helper h = new Helper();
//		
//		Commentary.log(LogStatus.INFO,	"["+h.getEngine()+"]: Creating a transfer transaction for current date and changing to future date and verifying the Projected and Current balance.");
//		
//		String sTotal_before,sTotal_after,sProjectedBalance_before,sProjected_after ;
//		String payeeName = "Payee_"+h.getCurrentTime();
//		
//		OverviewPage op = new OverviewPage();
//		op.navigateToAcctList();
//		
//		TransactionsPage tp = new TransactionsPage();
//		tp.tapOnAddTransactionButtonFromAllTransactionsPage();
//		
//		TransactionDetailPage td = new TransactionDetailPage();
//		TransactionRecord tRec = new TransactionRecord();
//		tRec.setAmount("23.00");
//		tRec.setPayee(payeeName);
//		tRec.setAccount(sChecking);
//		tRec.setCategory("Transfer ["+sSavings+"]");
//		tRec.setTransactionType("expense");
//		tRec.setDate(h.getFutureDaysDate(0));
//		
//		td.addTransaction(tRec);
//		Thread.sleep(2000);
//		Double d = Double.parseDouble(tRec.getAmount());
//		
//		Verify.waitForObject(tp.backButton, 2);
//		tp.backButton.click();
//		
//		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
//		sTotal_before = bcc.getTotalBalance();
//		sProjectedBalance_before = bcc.getProjectedBalance();
//		
//		Double dTotal_before = h.processBalanceAmount(sTotal_before);
//		Double dProjected_before = h.processBalanceAmount(sProjectedBalance_before);
//		
//		bcc.allTransactionButton.click();
//		
//		tp.searchRecentTransaction(payeeName);
//		Verify.waitForObject(tp.thisMonthLabel, 3);
//		tp.tapOnFirstTransaction();
//		Verify.waitForObject(td.dateLabel, 2);
//		
//		tRec = new TransactionRecord();
//		tRec.setDate(h.getFutureDaysDate(5));
//		td.editTransaction(tRec);
//		
//		Verify.waitForObject(tp.backButton, 2);
//		tp.backButton.click();
//		Thread.sleep(4000);
//		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
//		
//		bcc.scrollToTotalBalance();
//		sTotal_after = bcc.getTotalBalance();
//		Thread.sleep(1000);
//		sProjected_after = bcc.getProjectedBalance();
//		
//		Double dTotal_after = h.processBalanceAmount(sTotal_after);
//		Double dProjected_after = h.processBalanceAmount(sProjected_after);
//	
//		if (dTotal_after.equals(dTotal_before))
//			Commentary.log(LogStatus.INFO, "PASS: Total balance was ["+dTotal_before+"], after changing the transfer date for transaction amount ["+d+"] to future date, total balance is ["+dTotal_after+"]");
//		else
//			Commentary.log(sa, LogStatus.FAIL, "FAIL: Total balance was ["+dTotal_before+"], after changing the transfer date for transaction amount ["+d+"] to future date, total balance is ["+dTotal_after+"]");
//		
//		if (dProjected_after.equals(dProjected_before))
//			Commentary.log(LogStatus.INFO, "PASS: Projected balance was ["+dProjected_before+"], after changing the transfer date for transaction amount ["+d+"], now the projected balance is ["+dProjected_after+"]");
//		else
//			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected balance was ["+dProjected_before+"], after changing the transfer date for transaction amount ["+d+"], now the projected balance is ["+dProjected_after+"]");
//
//		sa.assertAll();	
//	}
//	
//	@Test (priority=16, enabled = true)
//	public void TC17_test() throws Exception {
//
//		SoftAssert sa = new SoftAssert();
//		Helper h = new Helper();
//
//		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: To Verify the functionality of the Online Transfer Transaction.");
//		
//		OverviewPage op = new OverviewPage();
//	
//		Verify.waitForObject(op.hambergerIcon, 2);
//		op.hambergerIcon.click();
//		Thread.sleep(3000);
//
//		SettingsPage sp = new SettingsPage();
//		Verify.waitForObject(sp.datasetDDButton, 2);
//		sp.datasetDDButton.click();
//		Thread.sleep(4000);
//
//		sp.switchDataset(sDataset1);
//		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
//		
//		op.navigateToAcctList();
//		
//		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
//		TransactionsPage tp = new TransactionsPage();
//		
//		bcc.selectAccount(sSaving);
//		Thread.sleep(1000);
//		
//		tp.DisableRunningBalance();
//		
//		tp.tapOnFirstTransaction();
//		Thread.sleep(2000);
//		
//		String transferToAcc1 = "Transfer ["+sOnlineChecking+"]";
//		
//		TransactionDetailPage td = new TransactionDetailPage();
//		TransactionRecord tRec = new TransactionRecord();
//		tRec.setCategory(transferToAcc1);
//		td.editTransaction(tRec);
//		
//		Verify.waitForObject(tp.backButton, 2);
//		tp.backButton.click();
//		Thread.sleep(2000);
//		bcc.selectAccount(sOnlineChecking);
//		Thread.sleep(2000);
//		
//		tp.DisableRunningBalance();
//		
//		tp.tapOnFirstTransaction();
//		Thread.sleep(2000);
//		td.VerifyTransactionPayee("Kfc Personal");
//
//		String transferToAcc2 = "Transfer ["+sManualSavings+"]";
//		
//		tRec.setCategory(transferToAcc2);
//		td.editTransaction(tRec);
//		
//		Verify.waitForObject(tp.backButton, 2);
//		tp.backButton.click();
//		Thread.sleep(2000);
//		bcc.selectAccount(sSaving);
//		Thread.sleep(2000);
//		Verify.waitForObject(tp.thisMonthLabel, 2);
//		tp.tapOnFirstTransaction();
//		Thread.sleep(2000);
//		
//		Verify.waitForObject(td.selectedCategory, 2);
//		td.VerifyTransactionCategory("Uncategorized");
//		
//		Commentary.log(LogStatus.INFO, "PASS: Category of the transaction with Payee named \"Kfc Personal\" changed to Uncategorized as Expected.");
//		
//		tRec.setCategory("Fast Food");
//		td.editTransaction(tRec);
//		
//		Commentary.log(LogStatus.INFO, "Changed the category same as earlier again.");
//		
//		Verify.waitForObject(tp.backButton, 2);
//		tp.backButton.click();
//		
//		bcc.selectAccount(sOnlineChecking);
//		Thread.sleep(2000);
//		tp.tapOnFirstTransaction();
//		Thread.sleep(2000);
//		
//		op.scroll_down();
//		td.deleteTransaction.click();
//		
//		if (Verify.objExists_check(td.deleteTransactionAlertButton)) {
//		td.deleteTransactionAlertButton.click();
//		}
//		
//		if (Verify.objExists_check(td.deleteTransferTransactionWarning)) {
//			Commentary.log(LogStatus.INFO, "PASS: Warning messsage is displayed on deleting transfer transactions.");
//			td.buttonOK.click();
//		} else {
//			Commentary.log(LogStatus.FAIL, "FAIL: Warning messsage is NOT displayed on deleting transfer transactions.");
//		}
//		
//		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
//		
//		sa.assertAll();	
//	}
//	
//	@Test (priority=17, enabled = true)
//	public void TC18_test() throws Exception {
//
//		SoftAssert sa = new SoftAssert();
//		Helper h = new Helper();
//
//		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: To Verify the delete warning message of the Online Transfer Transaction.");
//		
//		OverviewPage op = new OverviewPage();
//	
//		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
//		
//		op.navigateToAcctList();
//		
//		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
//		
//		bcc.selectAccount(sSaving);
//		Thread.sleep(1000);
//		
//		TransactionsPage tp = new TransactionsPage();
//		tp.tapOnFirstTransaction();
//		Thread.sleep(2000);
//		
//		String transferToAcc1 = "Transfer ["+sOnlineChecking+"]";
//		
//		TransactionDetailPage td = new TransactionDetailPage();
//		TransactionRecord tRec = new TransactionRecord();
//		tRec.setCategory(transferToAcc1);
//		td.editTransaction(tRec);
//		
//		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
//		
//		tp.tapOnFirstTransaction();
//		Thread.sleep(2000);
//		
//		Verify.waitForObject(td.dateLabel, 2);
//		
//		op.scroll_down();
//		td.deleteTransaction.click();
//		Thread.sleep(2000);
//		
//		if (Verify.objExists_check(td.deleteTransactionAlertButton)) {
//			td.deleteTransactionAlertButton.click();
//		}
//		Verify.waitForObject(td.deleteWarningMessage, 2);
//		
//		String expWarningText = "You are deleting the transfer transaction \"Kfc Personal\". This will also delete the other side of the transfer in account "+sOnlineChecking+"";
//		String warningMessageText = td.deleteWarningMessage.getText();
//		
//		if (Verify.objExists(td.deleteTransferTransactionWarning) || warningMessageText.equalsIgnoreCase(expWarningText) )
//			Commentary.log(LogStatus.INFO, "PASS: Warning messsage is displayed on deleting online transfer transactions.");
//		else
//			Commentary.log(sa, LogStatus.FAIL, "FAIL: Warning messsage is NOT displayed on deleting online transfer transactions.");
//		
//		td.buttonCancel.click();
//		
//		tRec.setCategory("Fast Food");
//		td.editTransaction(tRec);
//		
//		Commentary.log(LogStatus.INFO, "Changed the category same as earlier again.");
//		
//		sa.assertAll();
//	}
//	
//	@Test (priority=18, enabled = true)
//	public void TC19_test() throws Exception {
//
//		SoftAssert sa = new SoftAssert();
//		Helper h = new Helper();
//
//		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: To Verify the delete warning message of the Online Transfer Transaction from the other side.");
//		
//		OverviewPage op = new OverviewPage();
//	
//		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
//		
//		op.navigateToAcctList();
//		
//		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
//		
//		bcc.selectAccount(sSaving);
//		Thread.sleep(1000);
//		
//		TransactionsPage tp = new TransactionsPage();
//		tp.tapOnFirstTransaction();
//		Thread.sleep(2000);
//		
//		String transferToAcc1 = "Transfer ["+sOnlineChecking+"]";
//		
//		TransactionDetailPage td = new TransactionDetailPage();
//		TransactionRecord tRec = new TransactionRecord();
//		tRec.setCategory(transferToAcc1);
//		td.editTransaction(tRec);
//		
//		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
//		
//		tp.tapOnFirstTransaction();
//		Thread.sleep(2000);
//		
//		Verify.waitForObject(td.dateLabel, 2);
//		
//		op.scroll_down();
//		td.buttonGoToOtherSide.click();
//		Thread.sleep(2000);
//		
//		op.scroll_down();
//		td.deleteTransaction.click();
//		Thread.sleep(2000);
//		
//		if (Verify.objExists_check(td.deleteTransactionAlertButton)) {
//			td.deleteTransactionAlertButton.click();
//		}
//		Verify.waitForObject(td.deleteWarningMessage, 2);
//		
//		String expWarningText = "You are deleting the transfer transaction \"Kfc Personal\". The other side of this transfer in account [+"+sSaving+"+] is a bank downloaded transaction and will be set to \"Uncategorized\", but will not be deleted";
//		String warningMessageText = td.deleteWarningMessage.getText();
//		
//		if (Verify.objExists(td.deleteTransferTransactionWarning) || warningMessageText.equalsIgnoreCase(expWarningText) )
//			Commentary.log(LogStatus.INFO, "PASS: Correct Warning messsage is displayed on trying to delete from other side of online transfer transactions.");
//		else
//			Commentary.log(sa, LogStatus.FAIL, "FAIL: Correct Warning messsage is NOT displayed on trying to delete from other side of online transfer transactions.");
//		
//		td.buttonOK.click();
//		
//		Commentary.log(LogStatus.INFO, "PASS: Online transfer transactions has been deleted from the other side.");
//		
//		Verify.waitForObject(tp.backButton, 2);
//		tp.backButton.click();
//		
//		bcc.selectAccount(sSaving);
//		Thread.sleep(2000);
//		tp.tapOnFirstTransaction();
//		Thread.sleep(2000);
//		
//		Verify.waitForObject(td.selectedCategory, 2);
//		td.VerifyTransactionCategory("Uncategorized");
//		
//		Commentary.log(LogStatus.INFO, "PASS: Category of the transaction with Payee named \"Kfc Personal\" changed to Uncategorized as Expected.");
//		
//		tRec.setCategory("Fast Food");
//		td.editTransaction(tRec);
//		
//		Commentary.log(LogStatus.INFO, "Changed the category same as earlier again.");
//		
//		sa.assertAll();
//	}

}