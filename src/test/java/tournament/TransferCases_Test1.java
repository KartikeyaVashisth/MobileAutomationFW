package tournament;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.BankingAndCreditCardPage;
import dugout.OverviewPage;
import dugout.SettingsPage;
import dugout.SignInPage;
import dugout.TransactionDetailPage;
import dugout.TransactionsPage;
import io.appium.java_client.touch.TapOptions;
import referee.Commentary;
import referee.Verify;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;

public class TransferCases_Test1 extends Recovery {
	
	String sUserName = "mobileautomation1@quicken.com";
	String sPassword = "Quicken@01";
	String sDataset = "Transfers_automation";
	String sChecking = "Checking";
	String sCreditCard ="Credit Card";
	String sManualSaving = "manual_savings";
	String sOnlineSaving = "onl_savings";
	String backButton1_ios = "Banking & Credit";
	String sSavings = "Savings";
	
	@Test(priority = 0)
	public void TR1_test() throws Exception{
		String sChecking_before,sSaving_after, sSaving_before, sChecking_after ;

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO,	"Creating a transfer from Checking to Saving account and verifying balances");
		SignInPage signIn = new SignInPage();
		signIn.signIn(sUserName, sPassword, sDataset);
		String time = h.getCurrentTime();
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		sChecking_before = bcc.getCheckingBalance();
		sSaving_before = bcc.getSavingsBalance();
		Double dChecking_before = h.processBalanceAmount(sChecking_before.replace("SubTotal: ", ""));
		Double dSaving_before = h.processBalanceAmount(sSaving_before.replace("SubTotal: ", ""));

		bcc.allTransactionButton.click();
		TransactionsPage tp = new TransactionsPage();
		tp.addTransaction.click();
		
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("20.00");
		tRec.setPayee(time);
		tRec.setAccount(sChecking);
		tRec.setCategory("Transfer [Savings]");
		tRec.setTransactionType("expense");
		h.getContext();
		td.addTransaction(tRec);
		
		tp.backButton.click();
		sChecking_after = bcc.getCheckingBalance();
		sSaving_after = bcc.getSavingsBalance();
		Double dChecking_after = h.processBalanceAmount(sChecking_after.replace("SubTotal: ", ""));
		Double dSaving_after = h.processBalanceAmount(sSaving_after.replace("SubTotal: ", ""));
		Double d = Double.parseDouble(tRec.getAmount());
		
		if (dChecking_after+d==dChecking_before)
			Commentary.log(LogStatus.INFO, "PASS: Checking balance was ["+dChecking_before+"], added transfer transaction for ["+tRec.getAmount()+"], now the checking balance shows ["+dChecking_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Checking balance was ["+dChecking_before+"], added transfer transaction for ["+tRec.getAmount()+"], now the checking balance shows ["+dChecking_after+"]");
		
		if (dSaving_after-d==dSaving_before)
			Commentary.log(LogStatus.INFO, "PASS: Savings balance was ["+dSaving_before+"], added transfer transaction for ["+tRec.getAmount()+"], now the savings balance shows ["+dSaving_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Savings balance was ["+dSaving_before+"], added transfer transaction for ["+tRec.getAmount()+"], now the savings balance shows ["+dSaving_after+"]");
		
		//sa.assertAll();
		
	
		sa.assertAll();
	
	}
	
	@Test(priority = 1)
	public void TR2_test() throws Exception{
		String sChecking_before,sSaving_after, sSaving_before, sChecking_after ;

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO,	"Creating and Editing a transfer from Checking to Saving account and verifying balances");
		String time = h.getCurrentTime();
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		sChecking_before = bcc.getCheckingBalance();
		sSaving_before = bcc.getSavingsBalance();
		Double dChecking_before = h.processBalanceAmount(sChecking_before.replace("SubTotal: ", ""));
		Double dSaving_before = h.processBalanceAmount(sSaving_before.replace("SubTotal: ", ""));

		bcc.allTransactionButton.click();
		TransactionsPage tp = new TransactionsPage();
		tp.addTransaction.click();
		
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("100.00");
		tRec.setPayee(time);
		tRec.setAccount(sChecking);
		tRec.setCategory("Transfer [Savings]");
		tRec.setTransactionType("expense");
		h.getContext();
		td.addTransaction(tRec);
		
		tp.backButton.click();
		sChecking_after = bcc.getCheckingBalance();
		sSaving_after = bcc.getSavingsBalance();
		Double dChecking_after = h.processBalanceAmount(sChecking_after.replace("SubTotal: ", ""));
		Double dSaving_after = h.processBalanceAmount(sSaving_after.replace("SubTotal: ", ""));
		Double d = Double.parseDouble(tRec.getAmount());
		
		if (dChecking_after+d==dChecking_before)
			Commentary.log(LogStatus.INFO, "PASS: Checking balance was ["+dChecking_before+"], added transfer transaction for ["+tRec.getAmount()+"], now the checking balance shows ["+dChecking_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Checking balance was ["+dChecking_before+"], added transfer transaction for ["+tRec.getAmount()+"], now the checking balance shows ["+dChecking_after+"]");
		
		if (dSaving_after-d==dSaving_before)
			Commentary.log(LogStatus.INFO, "PASS: Savings balance was ["+dSaving_before+"], added transfer transaction for ["+tRec.getAmount()+"], now the savings balance shows ["+dSaving_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Savings balance was ["+dSaving_before+"], added transfer transaction for ["+tRec.getAmount()+"], now the savings balance shows ["+dSaving_after+"]");
				
		tp.searchTransaction(time);
		tp.tapOnFirstTransation();		
		
		tRec = new TransactionRecord();
		Double txnAmount_before = h.processBalanceAmount(td.getTransactionAmount().replace("Amount: ", ""));
		System.out.println("Transaction amount before "+txnAmount_before);
		tRec.setAmount("10.00");
		td.editTransaction(tRec);
		h.getContext();
		
		d = Double.parseDouble(tRec.getAmount());		
		
		SettingsPage sp = new SettingsPage();
		
		sp.selectBack(backButton1_ios);
		
		sChecking_after = bcc.checkingBalance.getText();
		sSaving_after = bcc.savingsBalance.getText();
		dSaving_after = h.processBalanceAmount(sSaving_after.replace("SubTotal: ", ""));
		dChecking_after = h.processBalanceAmount(sChecking_after.replace("SubTotal: ", ""));		
		
		if (dChecking_after+d==dChecking_before)
			Commentary.log(LogStatus.INFO, "PASS: Checking balance was ["+dChecking_before+"], added transfer transaction for ["+tRec.getAmount()+"], now the checking balance shows ["+dChecking_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Checking balance was ["+dChecking_before+"], added transfer transaction for ["+tRec.getAmount()+"], now the checking balance shows ["+dChecking_after+"]");
		
		if (dSaving_before+d==dSaving_after)
			Commentary.log(LogStatus.INFO, "PASS: Savings balance was ["+dSaving_before+"], added transfer transaction for ["+tRec.getAmount()+"], now the savings balance shows ["+dSaving_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Savings balance was ["+dSaving_before+"], added transfer transaction for ["+tRec.getAmount()+"], now the savings balance shows ["+dSaving_after+"]");
	
		
		sa.assertAll();
		
	}
	
	@Test(priority = 2)
	public void TR3_test() throws Exception{
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
	
		Commentary.log(LogStatus.INFO,	"Creating a transfer transaction and Verifying deleting of transfer transaction with Warning message");
		String payeeName = h.getCurrentTime();
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();

		bcc.allTransactionButton.click();
		TransactionsPage tp = new TransactionsPage();
		tp.addTransaction.click();
		
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("10.00");
		tRec.setPayee(payeeName);
		tRec.setAccount(sChecking);
		tRec.setCategory("Transfer [Savings]");
		tRec.setTransactionType("expense");
		h.getContext();
		td.addTransaction(tRec);
		Thread.sleep(2000);
		
		tp.searchTransactionTxtField.click();
		tp.searchTransactionTxtField.sendKeys(payeeName);
		
		tp.tapOnFirstTransation();
		Thread.sleep(2000);
		
		String Selectedpayee = td.getTransactionPayee();
		String selectedAccount = td.getTransactionAccount();
		
		op.scroll_down();
		td.buttonGoToOtherSide.click();
		Thread.sleep(2000);
		
		op.scroll_down();
		td.deleteTransaction.click();
		Thread.sleep(2000);
		
		if (Verify.objExists_check(td.deleteTransactionAlertButton)) {
			td.deleteTransactionAlertButton.click();
			}
		Thread.sleep(4000);
		
		String expWarningText = "You are deleting the transfer transaction "+"\""+Selectedpayee+"\""+". This will also delete the other side of the transfer in account "+selectedAccount+"";
		String warningMessageText = td.deleteWarningMessage.getText();
		
		if (Verify.objExists(td.deleteTransferTransactionWarning) || warningMessageText.equalsIgnoreCase(expWarningText) ) {
			
			Commentary.log(LogStatus.INFO, "PASS: Warning messsage is displayed on deleting transfer transactions.");
			}
		else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Warning messsage is NOT displayed on deleting transfer transactions.");
			}
		
		
		sa.assertAll();
		
	}
	@Test(priority = 3)
	public void TR4_test() throws Exception{
		String sChecking_before, sSaving_before ;

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO,	"Creating a transfer transaction and Verifying the balances after deleting of transfer transaction");
		String payeeName = h.getCurrentTime();
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		sChecking_before = bcc.getCheckingBalance();
		sSaving_before = bcc.getSavingsBalance();
		Double dChecking_before = h.processBalanceAmount(sChecking_before.replace("SubTotal: ", ""));
		Double dSaving_before = h.processBalanceAmount(sSaving_before.replace("SubTotal: ", ""));
		//Double dCredit_before = h.processBalanceAmount(sCredit_before);

		bcc.allTransactionButton.click();
		TransactionsPage tp = new TransactionsPage();
		tp.addTransaction.click();
		
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("10.00");
		tRec.setPayee(payeeName);
		tRec.setAccount(sChecking);
		tRec.setCategory("Transfer [Savings]");
		tRec.setTransactionType("expense");
		h.getContext();
		td.addTransaction(tRec);
		Thread.sleep(2000);
		
		tp.searchTransactionTxtField.click();
		tp.searchTransactionTxtField.sendKeys(payeeName);
		
		tp.tapOnFirstTransation();
		Thread.sleep(2000);
		
		op.scroll_down();
		td.deleteTransaction.click();
		
		if (Verify.objExists_check(td.deleteTransactionAlertButton)) {
		td.deleteTransactionAlertButton.click();
		}
		
		if (Verify.objExists_check(td.deleteTransferTransactionWarning)) {
			Commentary.log(LogStatus.INFO, "PASS: Warning messsage is displayed on deleting transfer transactions.");
			td.buttonOK.click();
		}
		else {
			Commentary.log(LogStatus.FAIL, "FAIL: Warning messsage is NOT displayed on deleting transfer transactions.");
		}
		Thread.sleep(4000);
		
		tp.searchTransactionTxtField.click();
		tp.searchTransactionTxtField.clear();
		tp.searchTransactionTxtField.sendKeys(payeeName);
		
		if (Verify.objExists(tp.txtNoResultFound))
			Commentary.log(LogStatus.INFO, "PASS: Successfully Deleted the selected transaction");
		else
			Commentary.log(sa, LogStatus.FAIL, "Unable to Delete the selected transaction");
		
		sa.assertAll();
		
	}
		
	@Test(priority = 4)
	public void TR5_test() throws Exception{

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO,	"Creating a transfer transaction and editing the amount and verifying the changes on both the transfer transactions");
		String payeeName = h.getCurrentTime();
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();


		bcc.allTransactionButton.click();
		TransactionsPage tp = new TransactionsPage();
		tp.addTransaction.click();
		
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("10.00");
		tRec.setPayee(payeeName);
		tRec.setAccount(sChecking);
		tRec.setCategory("Transfer [Savings]");
		tRec.setTransactionType("expense");
		h.getContext();
		td.addTransaction(tRec);
		
		tp.searchTransactionTxtField.click();
		tp.searchTransactionTxtField.clear();
		tp.searchTransactionTxtField.sendKeys(payeeName);
		
		tp.tapOnFirstTransation();		
		
		tRec = new TransactionRecord();
		
		tRec.setAmount("100.00");
		
		td.editTransaction(tRec);
		h.getContext();
		
		tp.tapOnFirstTransation();
		
		Double firstSideAmount = h.processBalanceAmount(td.getTransactionAmount().replace("Amount: ", ""));
			
		if (firstSideAmount.equals(100.00)) {
			Commentary.log(LogStatus.INFO, "PASS: Amount is updated on first side of transfer");
		}
		else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Amount is NOT updated on first side of transfer");
		}
		
		op.scroll_down();
		td.buttonGoToOtherSide.click();
		Thread.sleep(2000);
		
		Double secondSideAmount = h.processBalanceAmount(td.getTransactionAmount().replace("Amount: ", ""));
		
		if (secondSideAmount.equals(-100.00)) {
			Commentary.log(LogStatus.INFO, "PASS: Amount is updated on second side of transfer");
		}
		else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Amount is NOT updated on second side of transfer");
		}
		
		sa.assertAll();
		
		
	}
	@Test(priority = 5)
	public void TR6_test() throws Exception{
	
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO,	"Creating a transfer transaction and changing the date and verify");
		String time = h.getCurrentTime();
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();

		bcc.allTransactionButton.click();
		TransactionsPage tp = new TransactionsPage();
		tp.addTransaction.click();
		
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("100.00");
		tRec.setPayee(time);
		tRec.setAccount(sChecking);
		tRec.setCategory("Transfer [Savings]");
		tRec.setTransactionType("expense");
		//tRec.setDate(h.getFutureDaysDate(0));
		h.getContext();
		td.addTransaction(tRec);
		
		
		tp.searchTransaction(time);
		tp.tapOnFirstTransation();		
		
		tRec = new TransactionRecord();
		
		tRec.setDate(h.getFutureDaysDate(2));
		
		td.editTransaction(tRec);
		h.getContext();
		
		String date = h.getDateformat(2);
		
		tp.tapOnFirstTransation();
		
		String actDate = td.getTransactionDate();
		
		if (date.equalsIgnoreCase(actDate)) {
			Commentary.log(LogStatus.INFO, "PASS: Date is updated on first side of transfer");
		}
		else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Date is NOT updated on first side of transfer");
		}
		
		op.scroll_down();
		td.buttonGoToOtherSide.click();
		Thread.sleep(2000);
	
		if (date.equalsIgnoreCase(actDate)) {
			Commentary.log(LogStatus.INFO, "PASS: Date is updated on second side of transfer");
		}
		else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Date is NOT updated on second side of transfer");
		}
		
		sa.assertAll();
		
	}
	@Test(priority = 6)
	public void TR7_test() throws Exception{

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO,	"Creating a transfer transaction and editing the account and verifying the error message");
		String payeeName = h.getCurrentTime();
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();

		bcc.allTransactionButton.click();
		TransactionsPage tp = new TransactionsPage();
		tp.addTransaction.click();
		
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("1.00");
		tRec.setPayee(payeeName);
		tRec.setAccount(sChecking);
		tRec.setCategory("Transfer [Savings]");
		tRec.setTransactionType("expense");
		h.getContext();
		td.addTransaction(tRec);
		
		tp.searchTransactionTxtField.click();
		tp.searchTransactionTxtField.clear();
		tp.searchTransactionTxtField.sendKeys(payeeName);
		
		tp.tapOnFirstTransation();		
		
		tRec = new TransactionRecord();
		tRec.setAccount(sCreditCard);
		td.editTransaction(tRec);
		Thread.sleep(2000);
		
	
		String actMessage = td.errorMsgText.getText().replace("\n", "");
		String expMessage = "You cannot change the account of a transfer transaction";
		
		if (actMessage.equalsIgnoreCase(expMessage)) {
			Commentary.log(LogStatus.INFO, "PASS: Error message is displayed and account is unchanged");
			}
		else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Error message is NOT displayed");
		}
		
		sa.assertAll();
	}
	
	@Test(priority = 7)
	public void TR8_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO,	"Creating a transfer transaction and editing the payee of one side and verifying the other part of transfer");
		String payeeName = h.getCurrentTime();
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();

		bcc.allTransactionButton.click();
		TransactionsPage tp = new TransactionsPage();
		tp.addTransaction.click();
		
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("11.00");
		tRec.setPayee(payeeName);
		tRec.setAccount(sChecking);
		tRec.setCategory("Transfer [Credit Card]");
		tRec.setTransactionType("expense");
		h.getContext();
		td.addTransaction(tRec);
		
		tp.searchTransactionTxtField.click();
		tp.searchTransactionTxtField.clear();
		tp.searchTransactionTxtField.sendKeys(payeeName);
		
		tp.tapOnFirstTransation();		
		
		tRec = new TransactionRecord();
		String payeeName_updated = h.getCurrentTime();
		tRec.setPayee(payeeName_updated);
		td.editTransaction(tRec);
		Thread.sleep(2000);
		
		tp.searchTransactionTxtField.click();
		tp.searchTransactionTxtField.clear();
		tp.searchTransactionTxtField.sendKeys(payeeName_updated);
		
		tp.tapOnFirstTransation();
		String actPayee = td.getTransactionPayee();
		
		
		if (actPayee.equalsIgnoreCase(payeeName_updated)) {
			Commentary.log(LogStatus.INFO, "PASS: Payee is unchanged for other side of transfer");
			}
		else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Test failed as payee is changed on both side of transfer");
		}
		
		op.scroll_down();
		td.buttonGoToOtherSide.click();
		Thread.sleep(2000);
		actPayee = td.getTransactionPayee();

		if (actPayee.equalsIgnoreCase(payeeName)) {
			Commentary.log(LogStatus.INFO, "PASS: Payee is changed only for one side of transfer");
			}
		else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Test failed as payee is changed on both side of transfer");
		}
		
		sa.assertAll();
	}
	@Test(priority = 8)
	public void TR9_test() throws Exception{
		String sChecking_before, sTotal_before,sSaving_after, sSaving_before, sChecking_after,sTotal_after ;

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO,	"Creating a transfer transaction and breaking the transfer and verifiying the account balance");
		String payeeName = h.getCurrentTime();
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

		bcc.allTransactionButton.click();
		TransactionsPage tp = new TransactionsPage();
		tp.addTransaction.click();
		
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("26.00");
		tRec.setPayee(payeeName);
		tRec.setAccount(sChecking);
		tRec.setCategory("Transfer [Savings]");
		tRec.setTransactionType("expense");
		h.getContext();
		td.addTransaction(tRec);
		Thread.sleep(2000);
		Double d = Double.parseDouble(tRec.getAmount());

		
		tp.searchTransactionTxtField.click();
		tp.searchTransactionTxtField.clear();
		tp.searchTransactionTxtField.sendKeys(payeeName);
		
		tp.tapOnFirstTransation();
		tRec = new TransactionRecord();
		tRec.setCategory("Internet");
		td.editTransaction(tRec);
		
		tp.backButton.click();
		
		sChecking_after = bcc.getCheckingBalance();
		sSaving_after = bcc.getSavingsBalance();
		sTotal_after = bcc.getTotalBalance();
		Double dChecking_after = h.processBalanceAmount(sChecking_after.replace("SubTotal: ", ""));
		Double dSaving_after = h.processBalanceAmount(sSaving_after.replace("SubTotal: ", ""));
		Double dTotal_after = h.processBalanceAmount(sTotal_after);
		
		System.out.println("d amount is "+d);

		System.out.println("after total amount "+(dTotal_after+d));

		if (dChecking_after.equals(dChecking_before))
			Commentary.log(LogStatus.INFO, "PASS: Checking balance was ["+dChecking_before+"], after breaking the transfer, checking balance is unchanged and shows ["+dChecking_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Checking balance was ["+dChecking_before+"], after breaking the transfer, checking balance is changed and shows ["+dChecking_after+"]");
		
		if (dSaving_after-d==dSaving_before)
			Commentary.log(LogStatus.INFO, "PASS: Savings balance was ["+dSaving_before+"], after changing the category and breaking the transfer for amount ["+d+"], now the savings balance shows ["+dSaving_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Savings balance was ["+dSaving_before+"], after changing the category and breaking the transfer for amount ["+d+"], now the savings balance shows ["+dSaving_after+"]");
		
		if (dTotal_after-d==dTotal_before) {
			Commentary.log(LogStatus.INFO, "PASS: Total balance was ["+dTotal_before+"], after breaking the transfer, total balance is unchanged and shows ["+dTotal_after+"]");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Total balance was ["+dTotal_before+"], after breaking the transfer, total balance is changed and shows ["+dTotal_after+"]");
		}
		sa.assertAll();
		
	}
	@Test(priority = 9)
	public void TR10_test() throws Exception{

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO,	"Creating a transfer transaction and breaking the transfer and verifiying that one transaction is removed");
		String payeeName = h.getCurrentTime();
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();

		bcc.allTransactionButton.click();
		TransactionsPage tp = new TransactionsPage();
		tp.addTransaction.click();
		
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("37.00");
		tRec.setPayee(payeeName);
		tRec.setAccount(sChecking);
		tRec.setCategory("Transfer [Savings]");
		tRec.setTransactionType("expense");
		h.getContext();
		td.addTransaction(tRec);
		Thread.sleep(2000);

		
		tp.searchTransactionTxtField.click();
		tp.searchTransactionTxtField.clear();
		tp.searchTransactionTxtField.sendKeys(payeeName);
		
		tp.tapOnFirstTransation();
		tRec = new TransactionRecord();
		tRec.setCategory("Mobile Phone");
		td.editTransaction(tRec);
		
		Thread.sleep(1000);
		tp.tapOnFirstTransation();
		op.scroll_down();
		if (!(Verify.objExists(td.buttonGoToOtherSide))) {
			Commentary.log(LogStatus.INFO, "PASS: Other side of the transaction is successfully removed after breaking the transafer transaction");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Other side of the transaction is NOT removed after breaking the transfer transaction");
		}
		sa.assertAll();
		
	}
	@Test(priority = 10)
	public void TR11_test() throws Exception{
		String transferToAcc1 = "Transfer [Credit Card]";
		String transferToAcc2 = "Transfer [Savings]";
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO,	"Creating a transfer transaction and editing the transfer account to any other and verifying the list for transfer transactions");
		String payeeName = h.getCurrentTime();
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();

		bcc.allTransactionButton.click();
		TransactionsPage tp = new TransactionsPage();
		tp.addTransaction.click();
		
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("16.00");
		tRec.setPayee(payeeName);
		tRec.setAccount(sChecking);
		tRec.setCategory(transferToAcc1);
		tRec.setTransactionType("expense");
		h.getContext();
		td.addTransaction(tRec);
		
		tp.searchTransactionTxtField.click();
		tp.searchTransactionTxtField.clear();
		tp.searchTransactionTxtField.sendKeys(payeeName);
		
		tp.tapOnFirstTransation();	
		op.scroll_down();
		td.buttonGoToOtherSide.click();
		Thread.sleep(1000);
		
		tRec = new TransactionRecord();
		tRec.setCategory(transferToAcc2);
		td.editTransaction(tRec);
		Thread.sleep(500);
		td.backButton.click();
		
		tp.searchTransactionTxtField.click();
		tp.searchTransactionTxtField.clear();
		tp.searchTransactionTxtField.sendKeys(payeeName);
		
		tp.tapOnFirstTransation();
		
		String actCategory = td.getTransactionCategory();
		
		
		if (!(actCategory.equalsIgnoreCase(transferToAcc1))) {
			Commentary.log(LogStatus.INFO, "PASS: Transfer To account is correct");
			}
		else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Transfer To account is Incorrect");
		}
		
		op.scroll_down();
		td.buttonGoToOtherSide.click();
		Thread.sleep(1000);
		actCategory = td.getTransactionCategory();

		if (!(actCategory.equalsIgnoreCase(transferToAcc1))) {
			Commentary.log(LogStatus.INFO, "PASS: Transfer To account is correct for other side of the transfer");
			}
		else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Transfer To account is Incorrect");
		}
		
		sa.assertAll();
	}
	@Test(priority = 11)
	public void TR12_test() throws Exception{
		String transferToAcc1 = "Transfer [Credit Card]";
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO,	"Creating a transfer transaction and editing the transfer account to any other and verifying the list for transfer transactions");
		String payeeName = h.getCurrentTime();
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();

		bcc.allTransactionButton.click();
		TransactionsPage tp = new TransactionsPage();
		tp.addTransaction.click();
		
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("17.50");
		tRec.setPayee(payeeName);
		tRec.setAccount(sChecking);
		tRec.setCategory(transferToAcc1);
		tRec.setTransactionType("expense");
		h.getContext();
		td.addTransaction(tRec);
		
		tp.searchTransactionTxtField.click();
		tp.searchTransactionTxtField.clear();
		tp.searchTransactionTxtField.sendKeys(payeeName);
		
		tp.tapOnFirstTransation();	
		
		
		tRec = new TransactionRecord();
		tRec.setTransactionType("expense");
		td.editTransaction(tRec);
		Thread.sleep(500);
				
		tp.tapOnFirstTransation();
		
		String txnAmount = td.getTransactionAmount().replace("Amount: ", "");
		
		
		if (txnAmount.contains("-$")) {
			Commentary.log(LogStatus.INFO, "PASS: Amount is changed from expense to income value");
			}
		else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Amount is NOT changed from expense to income value");
		}
		
		op.scroll_down();
		td.buttonGoToOtherSide.click();
		Thread.sleep(1000);
		txnAmount = td.getTransactionAmount().replace("Amount: ", "");

		if (!(txnAmount.contains("-$"))) {
			Commentary.log(LogStatus.INFO, "PASS: Amount is changed from income to expense value");
		}
	else {
		Commentary.log(sa, LogStatus.FAIL, "FAIL: Amount is NOT changed from income to expense value");
	}
		
		sa.assertAll();
	}
	@Test(priority = 12)
	public void TR13_test() throws Exception{
		
		String transferToAcc1 = "Transfer [Savings]";
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO,	"Creating two normal transaction and editing the category to transfer account and verifying the alert message displayed for transaction match");
		String payeeName = h.getCurrentTime();
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();

		bcc.allTransactionButton.click();
		TransactionsPage tp = new TransactionsPage();
		tp.addTransaction.click();
		//Adding one normal transaction
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("29.00");
		tRec.setPayee(payeeName);
		tRec.setAccount(sChecking);
		//tRec.setCategory("");
		tRec.setTransactionType("expense");
		h.getContext();
		td.addTransaction(tRec);
		
		//Adding another normal transaction
		
		tp.addTransaction.click();
		tRec = new TransactionRecord();
		tRec.setAmount("29.00");
		tRec.setTransactionType("income");
		tRec.setPayee(payeeName);
		tRec.setAccount(sSavings);
		h.getContext();
		td.addTransaction(tRec);
		
		tp.searchTransactionTxtField.click();
		tp.searchTransactionTxtField.clear();
		tp.searchTransactionTxtField.sendKeys(payeeName);
		
		tp.tapOnFirstTransation();	
		
		tRec = new TransactionRecord();
		tRec.setCategory(transferToAcc1);
		td.editTransaction(tRec);
		Thread.sleep(500);
		
		String actAlertText = td.matchingTransactionAlertMessageTxt.getText();
		String expAlertText = "Matching transaction found";
		
		if (Verify.objExists(td.matchingTransactionAlertMessageTxt)) {		
			Commentary.log(LogStatus.INFO, "PASS: Matching Transaction Alert message is displayed");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Matching Transaction Alert message is NOT displayed");
		}
		
		if (actAlertText.equals(expAlertText)) {
			Commentary.log(LogStatus.INFO, "PASS: Matching Transaction Alert message is correct");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Matching Transaction Alert message is NOT correct");
		}
		
		if (Verify.objExists(td.buttonMatch)) {
			Commentary.log(LogStatus.INFO, "PASS: Match button is displayed");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Match button is NOT displayed");
		}
		
		if (Verify.objExists(td.buttonDontMatch)) {
			Commentary.log(LogStatus.INFO, "PASS: Don't Match button is displayed");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Don't Match button is NOT displayed");
		}
		
		sa.assertAll();
	}
	@Test(priority = 13)
	public void TR14_test() throws Exception{
		
		String transferToAcc1 = "Transfer [Savings]";
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO,	"Creating two normal transaction and editing the category to transfer account and verifying MATCH functionality");
		String payeeName = h.getCurrentTime();
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();

		bcc.allTransactionButton.click();
		TransactionsPage tp = new TransactionsPage();
		tp.addTransaction.click();
		//Adding one normal transaction
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("39.00");
		tRec.setPayee(payeeName);
		tRec.setAccount(sChecking);
		//tRec.setCategory("");
		tRec.setTransactionType("expense");
		h.getContext();
		td.addTransaction(tRec);
		
		//Adding another normal transaction
		
		tp.addTransaction.click();
		tRec = new TransactionRecord();
		tRec.setAmount("39.00");
		tRec.setTransactionType("income");
		tRec.setPayee(payeeName);
		tRec.setAccount(sSavings);
		h.getContext();
		td.addTransaction(tRec);
		
		tp.searchTransactionTxtField.click();
		tp.searchTransactionTxtField.clear();
		tp.searchTransactionTxtField.sendKeys(payeeName);
		
		tp.tapOnFirstTransation();	
		
		tRec = new TransactionRecord();
		tRec.setCategory(transferToAcc1);
		td.editTransaction(tRec);
		Thread.sleep(500);
		
		td.buttonMatch.click();
		Thread.sleep(2000);
		
		tp.searchTransactionTxtField.click();
		tp.searchTransactionTxtField.clear();
		tp.searchTransactionTxtField.sendKeys(payeeName);
		
		int numOfTransaction = tp.getTransactionListSize();
		
		if (numOfTransaction==2) {		
			Commentary.log(LogStatus.INFO, "PASS: Transaction is matched with the other normal transaction");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Transaction is NOT matched with the other normal transaction");
		}
		
		
		
		sa.assertAll();
	}
	@Test(priority = 14)
	public void TR15_test() throws Exception{
		
		String transferToAcc1 = "Transfer [Savings]";
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO,	"Creating two normal transaction and editing the category to transfer account and verifying DON'T MATCH functionality");
		String payeeName = h.getCurrentTime();
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();

		bcc.allTransactionButton.click();
		TransactionsPage tp = new TransactionsPage();
		tp.addTransaction.click();
		//Adding one normal transaction
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("55.00");
		tRec.setPayee(payeeName);
		tRec.setAccount(sChecking);
		//tRec.setCategory("");
		tRec.setTransactionType("expense");
		h.getContext();
		td.addTransaction(tRec);
		
		//Adding another normal transaction
		
		tp.addTransaction.click();
		tRec = new TransactionRecord();
		tRec.setAmount("35.00");
		tRec.setTransactionType("income");
		tRec.setPayee(payeeName);
		tRec.setAccount(sSavings);
		h.getContext();
		td.addTransaction(tRec);
		
		tp.searchTransactionTxtField.click();
		tp.searchTransactionTxtField.clear();
		tp.searchTransactionTxtField.sendKeys(payeeName);
		
		tp.tapOnFirstTransation();	
		
		tRec = new TransactionRecord();
		tRec.setCategory(transferToAcc1);
		td.editTransaction(tRec);
		Thread.sleep(500);
		
		td.buttonDontMatch.click();
		Thread.sleep(2000);
		
		tp.searchTransactionTxtField.click();
		tp.searchTransactionTxtField.clear();
		tp.searchTransactionTxtField.sendKeys(payeeName);
		
		int numOfTransaction = tp.getTransactionListSize();
		
		if (numOfTransaction==3) {		
			Commentary.log(LogStatus.INFO, "PASS: Transaction is matched with the other normal transaction");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Transaction is NOT matched with the other normal transaction");
		}
		
		
		
		sa.assertAll();
	}
	@Test(priority = 15)
	public void TR16_test() throws Exception{
		String sTotal_before,sTotal_after,sProjectedBalance_before,sProjected_after ;

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO,	"Creating a transfer transaction for current date and changing to future date and verifying the Projected and Current balance");
		String payeeName = h.getCurrentTime();
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		
		bcc.allTransactionButton.click();
		TransactionsPage tp = new TransactionsPage();
		tp.addTransaction.click();
		
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("23.00");
		tRec.setPayee(payeeName);
		tRec.setAccount(sChecking);
		tRec.setCategory("Transfer [Savings]");
		tRec.setTransactionType("expense");
		tRec.setDate(h.getFutureDaysDate(0));
		h.getContext();
		td.addTransaction(tRec);
		Thread.sleep(2000);
		Double d = Double.parseDouble(tRec.getAmount());
		
		tp.backButton.click();
		sTotal_before = bcc.getTotalBalance();
		sProjectedBalance_before = bcc.getProjectedBalance();
		
		Double dTotal_before = h.processBalanceAmount(sTotal_before);
		Double dProjected_before = h.processBalanceAmount(sProjectedBalance_before);
		
		bcc.allTransactionButton.click();
		tp.searchTransactionTxtField.click();
		tp.searchTransactionTxtField.clear();
		tp.searchTransactionTxtField.sendKeys(payeeName);
		
		tp.tapOnFirstTransation();
		tRec = new TransactionRecord();
		tRec.setDate(h.getFutureDaysDate(5));
		td.editTransaction(tRec);
		
		tp.backButton.click();
		
		sTotal_after = bcc.getTotalBalance();
		sProjected_after = bcc.getProjectedBalance();
		
		Double dTotal_after = h.processBalanceAmount(sTotal_after);
		Double dProjected_after = h.processBalanceAmount(sProjected_after);
		


		if (dTotal_after.equals(dTotal_before))
			Commentary.log(LogStatus.INFO, "PASS: Total balance was ["+dTotal_before+"], after changing the transfer date for transaction amount ["+d+"] to future date, total balance is ["+dTotal_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Total balance was ["+dTotal_before+"], after changing the transfer date for transaction amount ["+d+"] to future date, total balance is ["+dTotal_after+"]");
		
		if (dProjected_after.equals(dProjected_before))
			Commentary.log(LogStatus.INFO, "PASS: Projected balance was ["+dProjected_before+"], after changing the transfer date for transaction amount ["+d+"], now the projected balance is ["+dProjected_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Projected balance was ["+dProjected_before+"], after changing the transfer date for transaction amount ["+d+"], now the projected balance is ["+dProjected_after+"]");
		

		sa.assertAll();
		
	}
}