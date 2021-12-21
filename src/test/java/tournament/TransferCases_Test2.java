package tournament;

import java.util.HashMap;
import java.util.List;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.AllAccountsPage;
import dugout.BankingAndCreditCardPage;
import dugout.OverviewPage;
import dugout.SettingsPage;
import dugout.SignInPage;
import dugout.TransactionDetailPage;
import dugout.TransactionsPage;
import io.appium.java_client.MobileElement;
import referee.Commentary;
import referee.Verify;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;

public class TransferCases_Test2 extends Recovery{

	String sUserName = "mobileautomation1@quicken.com";
	String sPassword = "Quicken@01";
	String sDataset = "Transfers_automation";
	String sChecking = "Checking";
	String sCreditCard ="Credit Card";
	String sManualSaving = "manual_savings";
	String sOnlineSaving = "onl_savings";
	String backButton1_ios = "Banking & Credit, back";
	String sSavings = "Savings";
	String sDataset1 = "OnlineAcc_Automation";
	String sOnlineChecking = "Checking1 XX8651";
	String sSaving = "Saving XX3867";
	String sManualSavings = "Manual_Savings";

	@Test(priority = 13, enabled = true)
	public void TR14_test() throws Exception{

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		SignInPage signIn = new SignInPage();
		signIn.signIn(sUserName, sPassword, sDataset);

		Commentary.log(LogStatus.INFO,	"["+h.getEngine()+"]: Creating two normal transaction and editing the category to transfer account and verifying MATCH functionality.");

		String transferToAcc1 = "Transfer ["+sSavings+"]";
		String payeeName = "Payee_"+h.getCurrentTime();

		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();

		TransactionsPage tp = new TransactionsPage();
		tp.tapOnAddTransactionButtonFromAllTransactionsPage();

		//Adding one normal transaction
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("39.00");
		tRec.setPayee(payeeName);
		tRec.setAccount(sChecking);
		//tRec.setCategory("");
		tRec.setTransactionType("expense");

		td.addTransaction(tRec);

		//Adding another normal transaction

		tp.addTransaction.click();
		tRec = new TransactionRecord();
		tRec.setAmount("39.00");
		tRec.setTransactionType("income");
		tRec.setPayee(payeeName);
		tRec.setAccount(sSavings);

		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec);

		tp.searchRecentTransaction(payeeName);
		Verify.waitForObject(tp.thisMonthLabel, 2);
		tp.tapOnFirstTransaction();
		Verify.waitForObject(td.dateLabel, 2);

		tRec = new TransactionRecord();
		tRec.setCategory(transferToAcc1);
		td.editTransaction(tRec);
		Thread.sleep(500);

		Verify.waitForObject(td.buttonMatch, 2);
		td.buttonMatch.click();
		Thread.sleep(2000);

		tp.searchRecentTransaction(payeeName);
		Verify.waitForObject(tp.thisMonthLabel, 3);

		int numOfTransaction = tp.getTransactionListSize();

		if (numOfTransaction==2)		
			Commentary.log(LogStatus.INFO, "PASS: Transaction is matched with the other created transaction.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Transaction is NOT matched with the other created transaction.");

		sa.assertAll();
	}

	@Test(priority = 14, enabled = true)
	public void TR15_test() throws Exception{

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO,	"["+h.getEngine()+"]: Creating two normal transaction and editing the category to transfer account and verifying DON'T MATCH functionality.");

		String transferToAcc1 = "Transfer ["+sSavings+"]";
		String payeeName = "Payee_"+h.getCurrentTime();

		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();

		TransactionsPage tp = new TransactionsPage();
		tp.tapOnAddTransactionButtonFromAllTransactionsPage();

		//Adding one normal transaction
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("55.00");
		tRec.setPayee(payeeName);
		tRec.setAccount(sChecking);
		//tRec.setCategory("");
		tRec.setTransactionType("expense");

		td.addTransaction(tRec);

		//Adding another normal transaction

		tp.addTransaction.click();
		tRec = new TransactionRecord();
		tRec.setAmount("55.00");
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

		Verify.waitForObject(td.buttonDontMatch, 2);
		td.buttonDontMatch.click();

		tp.searchRecentTransaction(payeeName);
		Verify.waitForObject(tp.thisMonthLabel, 2);

		int numOfTransaction = tp.getTransactionListSize();

		if (numOfTransaction==3)		
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Transaction isn't matched with the other created transaction after choosing Don't Match option.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Transaction matched with the other created transaction.");

		sa.assertAll();
	}

	@Test(priority = 15, enabled = true)
	public void TR16_test() throws Exception{

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO,	"["+h.getEngine()+"]: Creating a transfer transaction for current date and changing to future date and verifying the Projected and Current balance.");

		String sTotal_before,sTotal_after,sProjectedBalance_before,sProjected_after ;
		String payeeName = "Payee_"+h.getCurrentTime();

		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();

		TransactionsPage tp = new TransactionsPage();
		tp.tapOnAddTransactionButtonFromAllTransactionsPage();

		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("23.00");
		tRec.setPayee(payeeName);
		tRec.setAccount(sChecking);
		tRec.setCategory("Transfer ["+sSavings+"]");
		tRec.setTransactionType("expense");
		tRec.setDate(h.getFutureDaysDate(0));

		td.addTransaction(tRec);
		Thread.sleep(2000);
		Double d = Double.parseDouble(tRec.getAmount());

		Verify.waitForObject(tp.backButton, 2);
		tp.backButton.click();

		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		sTotal_before = bcc.getTotalBalance();
		sProjectedBalance_before = bcc.getProjectedBalance();

		Double dTotal_before = h.processBalanceAmount(sTotal_before);
		Double dProjected_before = h.processBalanceAmount(sProjectedBalance_before);

		bcc.allTransactionButton.click();

		tp.searchRecentTransaction(payeeName);
		Verify.waitForObject(tp.thisMonthLabel, 2);
		tp.tapOnFirstTransaction();
		Verify.waitForObject(td.dateLabel, 2);

		tRec = new TransactionRecord();
		tRec.setDate(h.getFutureDaysDate(5));
		td.editTransaction(tRec);

		Verify.waitForObject(tp.backButton, 2);
		tp.backButton.click();
		Thread.sleep(4000);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);

		bcc.scrollToTotalBalance();
		sTotal_after = bcc.getTotalBalance();
		Thread.sleep(1000);
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

	@Test (priority=16, enabled = true)
	public void TC17_test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: To Verify the functionality of the Online Transfer Transaction.");

		OverviewPage op = new OverviewPage();

		Verify.waitForObject(op.hambergerIcon, 2);
		op.hambergerIcon.click();
		Thread.sleep(3000);

		SettingsPage sp = new SettingsPage();
		Verify.waitForObject(sp.datasetDDButton, 2);
		sp.datasetDDButton.click();
		Thread.sleep(4000);

		sp.switchDataset(sDataset1);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);

		op.navigateToAcctList();

		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();

		bcc.selectAccount(sSaving);
		Thread.sleep(1000);

		tp.DisableRunningBalance();

		tp.tapOnFirstTransaction();
		Thread.sleep(2000);

		String transferToAcc1 = "Transfer ["+sOnlineChecking+"]";

		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setCategory(transferToAcc1);
		td.editTransaction(tRec);

		Verify.waitForObject(tp.backButton, 2);
		tp.backButton.click();
		Thread.sleep(2000);
		bcc.selectAccount(sOnlineChecking);
		Thread.sleep(2000);

		tp.DisableRunningBalance();

		tp.tapOnFirstTransaction();
		Thread.sleep(2000);
		td.VerifyTransactionPayee("Kfc Personal");

		String transferToAcc2 = "Transfer ["+sManualSavings+"]";

		tRec.setCategory(transferToAcc2);
		td.editTransaction(tRec);

		Verify.waitForObject(tp.backButton, 2);
		tp.backButton.click();
		Thread.sleep(2000);
		bcc.selectAccount(sSaving);
		Thread.sleep(2000);
		Verify.waitForObject(tp.thisMonthLabel, 2);
		tp.tapOnFirstTransaction();
		Thread.sleep(2000);

		Verify.waitForObject(td.selectedCategory, 2);
		td.VerifyTransactionCategory("Uncategorized");

		Commentary.log(LogStatus.INFO, "PASS: Category of the transaction with Payee named \"Kfc Personal\" changed to Uncategorized as Expected.");

		tRec.setCategory("Fast Food");
		td.editTransaction(tRec);

		Commentary.log(LogStatus.INFO, "Changed the category same as earlier again.");

		Verify.waitForObject(tp.backButton, 2);
		tp.backButton.click();

		bcc.selectAccount(sOnlineChecking);
		Thread.sleep(2000);
		tp.tapOnFirstTransaction();
		Thread.sleep(2000);

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

		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);

		sa.assertAll();	
	}

	@Test (priority=17, enabled = true)
	public void TC18_test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: To Verify the delete warning message of the Online Transfer Transaction.");

		OverviewPage op = new OverviewPage();

		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);

		op.navigateToAcctList();

		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();

		bcc.selectAccount(sSaving);
		Thread.sleep(1000);

		TransactionsPage tp = new TransactionsPage();
		tp.tapOnFirstTransaction();
		Thread.sleep(2000);

		String transferToAcc1 = "Transfer ["+sOnlineChecking+"]";

		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setCategory(transferToAcc1);
		td.editTransaction(tRec);

		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);

		tp.tapOnFirstTransaction();
		Thread.sleep(2000);

		Verify.waitForObject(td.dateLabel, 2);

		op.scroll_down();
		td.deleteTransaction.click();
		Thread.sleep(2000);

		if (Verify.objExists_check(td.deleteTransactionAlertButton)) {
			td.deleteTransactionAlertButton.click();
		}
		Verify.waitForObject(td.deleteWarningMessage, 2);

		String expWarningText = "You are deleting the transfer transaction \"Kfc Personal\". This will also delete the other side of the transfer in account "+sOnlineChecking+"";
		String warningMessageText = td.deleteWarningMessage.getText();

		if (Verify.objExists(td.deleteTransferTransactionWarning) || warningMessageText.equalsIgnoreCase(expWarningText) )
			Commentary.log(LogStatus.INFO, "PASS: Warning messsage is displayed on deleting online transfer transactions.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Warning messsage is NOT displayed on deleting online transfer transactions.");

		td.buttonCancel.click();

		tRec.setCategory("Fast Food");
		td.editTransaction(tRec);

		Commentary.log(LogStatus.INFO, "Changed the category same as earlier again.");

		sa.assertAll();
	}

	@Test (priority=18, enabled = true)
	public void TC19_test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: To Verify the delete warning message of the Online Transfer Transaction from the other side.");

		OverviewPage op = new OverviewPage();

		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);

		op.navigateToAcctList();

		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();

		bcc.selectAccount(sSaving);
		Thread.sleep(1000);

		TransactionsPage tp = new TransactionsPage();
		tp.tapOnFirstTransaction();
		Thread.sleep(2000);

		String transferToAcc1 = "Transfer ["+sOnlineChecking+"]";

		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setCategory(transferToAcc1);
		td.editTransaction(tRec);

		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);

		tp.tapOnFirstTransaction();
		Thread.sleep(2000);

		Verify.waitForObject(td.dateLabel, 2);

		op.scroll_down();
		td.buttonGoToOtherSide.click();
		Thread.sleep(2000);

		op.scroll_down();
		
		Verify.waitForObject(td.deleteTransaction, 1);
		td.deleteTransaction.click();
		Thread.sleep(2000);

		if (Verify.objExists_check(td.deleteTransactionAlertButton)) {
			td.deleteTransactionAlertButton.click();
		}
		Verify.waitForObject(td.deleteWarningMessage, 2);

		String expWarningText = "You are deleting the transfer transaction \"Kfc Personal\". The other side of this transfer in account [+"+sSaving+"+] is a bank downloaded transaction and will be set to \"Uncategorized\", but will not be deleted";
		String warningMessageText = td.deleteWarningMessage.getText();

		if (Verify.objExists(td.deleteTransferTransactionWarning) || warningMessageText.equalsIgnoreCase(expWarningText) )
			Commentary.log(LogStatus.INFO, "PASS: Correct Warning messsage is displayed on trying to delete from other side of online transfer transactions.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Correct Warning messsage is NOT displayed on trying to delete from other side of online transfer transactions.");

		td.buttonOK.click();
		Thread.sleep(3000);

		Commentary.log(LogStatus.INFO, "PASS: Online transfer transactions has been deleted from the other side.");

		Verify.waitForObject(td.backButtonOnViewTransactionPage, 2);
		td.backButtonOnViewTransactionPage.click();

//		bcc.selectAccount(sSaving);
		Thread.sleep(2000);
		tp.tapOnFirstTransaction();
		Thread.sleep(2000);

		Verify.waitForObject(td.selectedCategory, 2);
		td.VerifyTransactionCategory("Uncategorized");

		Commentary.log(LogStatus.INFO, "PASS: Category of the transaction with Payee named \"Kfc Personal\" changed to Uncategorized as Expected.");

		tRec.setCategory("Fast Food");
		td.editTransaction(tRec);

		Commentary.log(LogStatus.INFO, "Changed the category same as earlier again.");

		sa.assertAll();
	}
	
	
	
	@Test (priority=19, enabled = true)
	public void TC20_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Add a split transfer and verify the transaction is appearing on the other account.");

		OverviewPage op = new OverviewPage();

		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		
		op.navigateToAccounts();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		bcc.selectAccount(sSaving);
		Thread.sleep(1000);
		
		TransactionsPage tp = new TransactionsPage();
		tp.addTransaction.click();
		Thread.sleep(1000);
		
		TransactionDetailPage td = new TransactionDetailPage();
		
		String payeename = h.getCurrentTime();
		String transferToAcc1 = "Transfer ["+sSavings+"]";
		
		HashMap<Integer,String> amount = new HashMap<Integer, String>();
		HashMap<Integer,String> cat = new HashMap<Integer, String>();
		HashMap<Integer,String[]> tags = new HashMap<Integer, String[]>();
		
		cat.put(1, "Education");
		cat.put(2, transferToAcc1);
		amount.put(1, "20.00");
		amount.put(2, "10.00");
		tags.put(1, new String[] {"Tax Related"});
		tags.put(2, new String[] {"Tax Related"});
		
		
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("30.00");
		//tRec.setTransactionType("expense");
		tRec.setAccount(sSaving);
		tRec.setPayee(payeename);
		
		td.addSplit(tRec, amount, cat, tags);
		Thread.sleep(1000);
		
		
		tp.searchRecentTransaction(payeename);
		tp.tapOnFirstTransaction();
		
		td.scroll_down();
		
		if(Verify.objExists(td.buttonGoToOtherSide)) {
			
			td.buttonGoToOtherSide.click();
			
			Commentary.log(LogStatus.PASS, "PASS: Split transfer added successfully and tapping on go to other side navigated to ["+td.getTransactionCategory()+"] account");
		
		}
	}
		
		
		@Test (priority=20, enabled = true)
		public void TC21_test() throws Exception {
			
			SoftAssert sa = new SoftAssert();
			Helper h = new Helper();
			
			Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify the message if we try to delete the other side on the split transfer transaction");

			OverviewPage op = new OverviewPage();

			Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
			
			op.navigateToAccounts();
			
			BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
			bcc.selectAccount(sSaving);
			Thread.sleep(1000);
			
			TransactionsPage tp = new TransactionsPage();
			tp.addTransaction.click();
			Thread.sleep(1000);
			
			TransactionDetailPage td = new TransactionDetailPage();
			
			String payeename = h.getCurrentTime();
			String transferToAcc1 = "Transfer ["+sSavings+"]";
			
			HashMap<Integer,String> amount = new HashMap<Integer, String>();
			HashMap<Integer,String> cat = new HashMap<Integer, String>();
			HashMap<Integer,String[]> tags = new HashMap<Integer, String[]>();
			
			cat.put(1, "Education");
			cat.put(2, transferToAcc1);
			amount.put(1, "50.00");
			amount.put(2, "20.00");
			tags.put(1, new String[] {"Tax Related"});
			tags.put(2, new String[] {"Tax Related"});
			
			
			
			TransactionRecord tRec = new TransactionRecord();
			tRec.setAmount("70.00");
			//tRec.setTransactionType("expense");
			tRec.setAccount(sSaving);
			tRec.setPayee(payeename);
			
			td.addSplit(tRec, amount, cat, tags);
			Thread.sleep(1000);
			
			tp.searchRecentTransaction(payeename);
			tp.tapOnFirstTransaction();
			
			td.scroll_down();
			
			td.buttonGoToOtherSide.click();
			Thread.sleep(1000);	
			
			td.scroll_down();
			
			td.deleteTransaction.click();
			td.deleteTransactionAlertButton.click();
			
			if(Verify.objExists(td.deleteWarningMessage)) {
				
				Commentary.log(LogStatus.PASS, "deleting this side of transfer will make the other side uncategorised");
				td.acceptAlert.click();;
			}
			else {
				Commentary.log(LogStatus.FAIL, "Split delete warning message did not appear.");
			}
	}
		
		@Test (priority=21, enabled = true)
		public void TC22_test() throws Exception {
			
			SoftAssert sa = new SoftAssert();
			Helper h = new Helper();
			
			Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify split transaction with a child transfer , if we turn off the split ----> the transfer transaction should be deleted from other account as well.");

			OverviewPage op = new OverviewPage();

			Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
			
			op.navigateToAccounts();
			
			BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
			bcc.selectAccount(sSaving);
			Thread.sleep(1000);
			
			TransactionsPage tp = new TransactionsPage();
			tp.addTransaction.click();
			Thread.sleep(1000);
			
			TransactionDetailPage td = new TransactionDetailPage();
			
			String payeename = h.getCurrentTime();
			String transferToAcc1 = "Transfer ["+sSavings+"]";
			
			HashMap<Integer,String> amount = new HashMap<Integer, String>();
			HashMap<Integer,String> cat = new HashMap<Integer, String>();
			HashMap<Integer,String[]> tags = new HashMap<Integer, String[]>();
			
			cat.put(1, "Education");
			cat.put(2, transferToAcc1);
			amount.put(1, "50.00");
			amount.put(2, "20.00");
			tags.put(1, new String[] {"Tax Related"});
			tags.put(2, new String[] {"Tax Related"});
			
			
			
			TransactionRecord tRec = new TransactionRecord();
			tRec.setAmount("70.00");
			//tRec.setTransactionType("expense");
			tRec.setAccount(sSaving);
			tRec.setPayee(payeename);
			
			td.addSplit(tRec, amount, cat, tags);
			Thread.sleep(1000);
			
			tp.searchRecentTransaction(payeename);
			tp.tapOnFirstTransaction();
			
			td.splitSwitch.click();
			td.buttonSave1.click();
			
			Thread.sleep(2000);
			
			tp.backButton.click();
			
			BankingAndCreditCardPage bc = new BankingAndCreditCardPage();
			bc.allTransactionButton.click();
			Thread.sleep(1000);
			tp.searchRecentTransaction(payeename);
			
			
			AllAccountsPage aa = new AllAccountsPage();
			List<MobileElement> li = aa.getAllSearchTransactions ();
			int a = li.size();
			
			
			if(a==1) {
				Commentary.log(LogStatus.PASS, "Other part of the split child transfer got deleted");
			}
			else {
				Commentary.log(LogStatus.FAIL, "Other part of the split child transfer is not deleted");
			}
			
			
			
		
	}
}
