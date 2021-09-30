
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

/**
 * @author Kartik
 * 
 * Review/Not Review and Clear/Unclear/Reconciled Test scripts
 *
 */

public class ReviewNotReview_Test extends Recovery {

	String sUserName = "mobileautomation1@quicken.com";
	String sPassword = "Quicken@01";
	String sDataset = "OnlineAcc_Automation";
	String sOnlineChecking = "Checking1 XX8651";
	String sSaving = "Saving XX3867";
	String sManualSaving = "Manual_Savings";
	String backButton1_ios = "Banking & Credit";
	String statusCleared = "Cleared";
	String statusUnCleared = "Uncleared";
	String statusReconciled = "Reconciled";
	String statusReviewed = "Reviewed";
	String statusNotReviewed = "Not Reviewed";

	@Test(priority = 0, enabled = true)
	public void TR1_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		SignInPage signIn = new SignInPage();
		signIn.signIn(sUserName, sPassword, sDataset);
		
		Commentary.log(LogStatus.INFO,	"["+h.getEngine()+"]: Verify Transaction status on changing from Uncleared to Cleared and vice-versa.");
		
		String payeeName = "Payee_"+h.getCurrentTime();

		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();

		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();
		bcc.selectAccount(sManualSaving);
		tp.DisableRunningBalance();
		Verify.waitForObject(tp.addTransaction, 2);
		tp.addTransaction.click();

		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("2.00");
		tRec.setPayee(payeeName);
		tRec.setAccount(sManualSaving);
		tRec.setTransactionType("expense");

		td.addTransaction(tRec);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		
		tp.searchRecentTransaction(payeeName);
		tp.tapOnFirstTransaction();
		Verify.waitForObject(td.dateLabel, 2);

		td.selectStatus(statusCleared);
		
		tp.searchRecentTransaction(payeeName);
		tp.tapOnFirstTransaction();
		Verify.waitForObject(td.dateLabel, 2);

		String actStatus = td.getTransactionStatus();

		if (actStatus.equals(statusCleared)) {
			Commentary.log(LogStatus.INFO, "PASS: Transaction Status is changed from Uncleared to Cleared.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Transaction Status is NOT changed from Uncleared to Cleared.");
		}

		// Again changing from Cleared to Uncleared
		td.selectStatus(statusUnCleared);
		
		tp.searchRecentTransaction(payeeName);
		tp.tapOnFirstTransaction();
		Verify.waitForObject(td.dateLabel, 2);

		actStatus = td.getTransactionStatus();

		if (actStatus.equals(statusUnCleared)) {
			Commentary.log(LogStatus.INFO, "PASS: Transaction Status is changed from Cleared to Uncleared.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Transaction Status is NOT changed from Cleared to Uncleared.");
		}
		
		sa.assertAll();
	}

	@Test(priority = 1, enabled = true)
	public void TR2_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify Transaction status on changing from Uncleared to Reconcile and vice-versa.");

		String payeeName = "Payee_"+h.getCurrentTime();

		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();

		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();
		bcc.selectAccount(sManualSaving);
		tp.DisableRunningBalance();
		Verify.waitForObject(tp.addTransaction, 2);
		tp.addTransaction.click();

		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("2.00");
		tRec.setPayee(payeeName);
		tRec.setAccount(sManualSaving);
		tRec.setTransactionType("expense");

		td.addTransaction(tRec);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);

		tp.searchRecentTransaction(payeeName);
		tp.tapOnFirstTransaction();
		Verify.waitForObject(td.dateLabel, 2);

		td.selectStatus(statusReconciled);

		tp.searchRecentTransaction(payeeName);
		tp.tapOnFirstTransaction();
		Verify.waitForObject(td.dateLabel, 2);

		String actStatus = td.getTransactionStatus();

		if (actStatus.equals(statusReconciled)) {
			Commentary.log(LogStatus.INFO, "PASS: Transaction Status is changed from Uncleared to Reconcile.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Transaction Status is NOT changed from Uncleared to Reconcile.");
		}

		// Again changing from Reconcile to Uncleared
		td.selectStatus(statusUnCleared);

		tp.searchRecentTransaction(payeeName);
		tp.tapOnFirstTransaction();
		Verify.waitForObject(td.dateLabel, 2);

		actStatus = td.getTransactionStatus();

		if (actStatus.equals(statusUnCleared)) {
			Commentary.log(LogStatus.INFO, "PASS: Transaction Status is changed from Reconcile to Uncleared.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Transaction Status is NOT changed from Reconcile to Uncleared.");
		}
		
		sa.assertAll();
	}

	@Test(priority = 2, enabled = true)
	public void TR3_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO,	"["+h.getEngine()+"]: Verify Transaction status on changing status from UnCleared to Reconcile and then from Reconciled to Cleared.");

		String payeeName = "Payee_"+h.getCurrentTime();

		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();

		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();
		bcc.selectAccount(sManualSaving);
		tp.DisableRunningBalance();
		Verify.waitForObject(tp.addTransaction, 2);
		tp.addTransaction.click();

		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("2.00");
		tRec.setPayee(payeeName);
		tRec.setAccount(sManualSaving);
		tRec.setTransactionType("expense");

		td.addTransaction(tRec);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);

		tp.searchRecentTransaction(payeeName);
		tp.tapOnFirstTransaction();
		Verify.waitForObject(td.dateLabel, 2);

		td.selectStatus(statusReconciled);

		tp.searchRecentTransaction(payeeName);
		tp.tapOnFirstTransaction();
		Verify.waitForObject(td.dateLabel, 2);

		String actStatus = td.getTransactionStatus();

		if (actStatus.equals(statusReconciled)) {
			Commentary.log(LogStatus.INFO, "PASS: Transaction Status is changed from Uncleared to Reconcile.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Transaction Status is NOT changed from Uncleared to Reconcile.");
		}

		// Again changing from Reconcile to Uncleared
		td.selectStatus(statusCleared);

		tp.searchRecentTransaction(payeeName);
		tp.tapOnFirstTransaction();
		Verify.waitForObject(td.dateLabel, 2);

		actStatus = td.getTransactionStatus();

		if (actStatus.equals(statusCleared)) {
			Commentary.log(LogStatus.INFO, "PASS: Transaction Status is changed from Reconciled to Cleared.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Transaction Status is NOT changed from Reconciled to Cleared.");
		}
		
		sa.assertAll();
	}

	@Test(priority = 3, enabled = true)
	public void TR4_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO,	"["+h.getEngine()+"]: Verify that adding a transaction is always be in Uncleared status.");

		String payeeName = "Payee_"+h.getCurrentTime();

		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();

		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();
		bcc.selectAccount(sManualSaving);
		tp.DisableRunningBalance();
		Verify.waitForObject(tp.addTransaction, 2);
		tp.addTransaction.click();

		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("2.00");
		tRec.setPayee(payeeName);
		tRec.setAccount(sManualSaving);
		tRec.setTransactionType("expense");
		
		td.addTransaction(tRec);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);

		tp.searchRecentTransaction(payeeName);
		tp.tapOnFirstTransaction();
		Verify.waitForObject(td.dateLabel, 2);

		String actTransactionStatus = td.getTransactionStatus();

		if (actTransactionStatus.equals(statusUnCleared)) {
			Commentary.log(LogStatus.INFO, "PASS: Default transaction status is set as Uncleared.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Default transaction status is NOT set as Uncleared.");
		}
		
		sa.assertAll();
	}

	@Test(priority = 4, enabled = true)
	public void TR5_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO,	"["+h.getEngine()+"]: Verify adding Manual transaction and check if it has option to mark it review/not-reviewed.");

		String payeeName = "Payee_"+h.getCurrentTime();

		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();

		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();
		bcc.selectAccount(sManualSaving);
		tp.DisableRunningBalance();
		Verify.waitForObject(tp.addTransaction, 2);
		tp.addTransaction.click();

		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("2.00");
		tRec.setPayee(payeeName);
		tRec.setAccount(sManualSaving);
		tRec.setTransactionType("expense");

		td.addTransaction(tRec);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);

		tp.searchRecentTransaction(payeeName);
		tp.tapOnFirstTransaction();
		Verify.waitForObject(td.dateLabel, 2);

		op.scroll_down();

		if (!Verify.objExists(td.downloadedTransactionStatus)) {
			Commentary.log(LogStatus.INFO, "PASS: Review/Not-review option is not displayed for manually entered transaction.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Review/Not-review option is displayed for manually entered transaction.");
		}
		
		sa.assertAll();
	}

	@Test(priority = 5, enabled = true)
	public void TR6_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify changing the transaction status from Not-Reviewed to Reviewed and vice-versa");

		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();

		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();
		bcc.selectAccount(sSaving);
		tp.DisableRunningBalance();

		TransactionDetailPage td = new TransactionDetailPage();
		
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 1);
		tp.tapOnFirstTransaction();

		op.scroll_down();

		String currentStatus = td.downloadedTransactionStatus.getText();
		td.downloadedTransactionStatus.click();
		Thread.sleep(3000);
		Verify.waitForObject(td.downloadedTransactionStatus, 2);
		String actStatus = td.downloadedTransactionStatus.getText();

		if (currentStatus.equals(statusNotReviewed)) {

			if (actStatus.equals(statusReviewed)) {
				Commentary.log(LogStatus.INFO, "PASS: Transaction status is updated from Not Reviewed to Reviewed.");
			}
			else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Transaction status is NOT updated from Not Reviewed to Reviewed.");
			}

		} else {
			if (actStatus.equals(statusNotReviewed)) {
				Commentary.log(LogStatus.INFO, "PASS: Transaction status is updated from Reviewed to Not Reviewed.");
			}
			else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Transaction status is NOT updated from Reviewed to Not Reviewed.");
			}
		}

		sa.assertAll();
	}

	@Test(priority = 6, enabled = true)
	public void TR7_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify changing the transaction status from Reviewed to Not Reviewed and vice-versa.");

		String actStatus = null;
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();

		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();
		bcc.selectAccount(sSaving);
		tp.DisableRunningBalance();

		TransactionDetailPage td = new TransactionDetailPage();

		tp.tapOnFirstTransaction();

		op.scroll_down();

		String currentStatus = td.downloadedTransactionStatus.getText();
		if (currentStatus.equals(statusReviewed)) {
			td.downloadedTransactionStatus.click();
			Thread.sleep(3000);
			Verify.waitForObject(td.downloadedTransactionStatus, 2);
			actStatus = td.downloadedTransactionStatus.getText();
		}
		else {
			Commentary.log(LogStatus.INFO, "Selected Transaction is Not Reviewed.");
			actStatus = td.downloadedTransactionStatus.getText();
		}

		if (actStatus.equals(statusNotReviewed)) {
			Commentary.log(LogStatus.INFO, "PASS: Transaction status is updated from Reviewed to Not reviewed.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Transaction status is NOT updated from Reviewed to Not reviewed.");
		}

		sa.assertAll();
	}

	@Test(priority = 7, enabled = true)
	public void TR8_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify filter list for transaction status.");

		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();

		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();
		bcc.selectAccount(sSaving);
		
		Verify.waitForObject(tp.buttonSort, 2);
		tp.buttonSort.click();

		if (Verify.objExists(tp.filterNotReviewed)) {
			Commentary.log(LogStatus.INFO, "PASS: \"Filter By\" option is displayed for viewing Not Reviewed transactions.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Filter By\" option is NOT displayed for viewing Not Reviewed transactions.");
		}

		sa.assertAll();
	}
	
	@Test(priority = 8, enabled = true)
	public void TR9_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO,	"["+h.getEngine()+"]: Verify filter list functionality for transaction status.");

		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();

		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();
		bcc.selectAccount(sSaving);
		
		Verify.waitForObject(tp.buttonSort, 2);
		tp.buttonSort.click();

		if (Verify.objExists(tp.filterNotReviewed)) {
			Commentary.log(LogStatus.INFO, "PASS: Filter By option is displayed for viewing Not Reviewed transactions.");
		} else {
			Commentary.log(LogStatus.FAIL, "FAIL: Filter By option is NOT displayed for viewing Not Reviewed transactions.");
		}

		tp.selectSortFilterOption("Not Reviewed");
		Thread.sleep(3000);

		if (Verify.objExists(tp.filterNotReviewedButton)) {
			Commentary.log(LogStatus.INFO, "PASS: NOT Reviewed filter button is displayed.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: NOT Reviewed filter button is NOT displayed.");
		}

		if (Verify.objExists(tp.buttonClearFilter)) {
			Commentary.log(LogStatus.INFO, "PASS: Cross icon is present on the Button to Clear Filters.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Cross icon is NOT present on the Button to Clear Filters.");
		}

		if (Verify.objExists(tp.buttonMarkAllReviewed)) {
			Commentary.log(LogStatus.INFO, "PASS: Button is displayed for Mark All as Reviewed.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Button is NOT displayed for Mark All as Reviewed.");
		}

		sa.assertAll();
	}
	
	@Test(priority = 9, enabled = true)
	public void TR10_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify functionality for Clear Filter button.");

		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();

		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();
		bcc.selectAccount(sSaving);
		
		Verify.waitForObject(tp.buttonSort, 2);
		tp.buttonSort.click();
		Thread.sleep(1000);

		tp.selectSortFilterOption("Not Reviewed");
		Thread.sleep(3000);

		if (Verify.objExists(tp.filterNotReviewedButton)) {
			Commentary.log(LogStatus.INFO, "PASS: NOT Reviewed filter button is displayed.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: NOT Reviewed filter button is NOT displayed.");
		}

		if (Verify.objExists(tp.buttonClearFilter)) {
			Commentary.log(LogStatus.INFO, "PASS: Cross icon is present on the Button to Clear Filters.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Cross icon is NOT present on the Button to Clear Filters.");
		}

		if (Verify.objExists(tp.buttonMarkAllReviewed)) {
			Commentary.log(LogStatus.INFO, "PASS: Button is displayed for Mark All as Reviewed.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Button is NOT displayed for Mark All as Reviewed.");
		}

		tp.buttonClearFilter.click();

		if (!Verify.objExists(tp.filterNotReviewedButton)) {
			Commentary.log(LogStatus.INFO, "PASS: After clicking cross icon of clearing filter > Not Reviewed filter button is not displayed.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: After clicking cross icon of clearing filter > Not Reviewed filter button is still displayed.");
		}

		if (!Verify.objExists(tp.buttonMarkAllReviewed)) {
			Commentary.log(LogStatus.INFO, "PASS: Clear filter > Button not displayed for mark all as Reviewed.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Cler filter > Button still displayed for mark all as Reviewed.");
		}

		sa.assertAll();
	}

	@Test(priority = 10, enabled = true)
	public void TR11_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify swipe feature to mark the transaction as Reviewed/Not Reviewed.");

		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();

		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();
		bcc.selectAccount(sSaving);
		tp.DisableRunningBalance();
		Thread.sleep(2000);
		TransactionDetailPage td = new TransactionDetailPage();

		tp.tapOnTransation(1);
		Thread.sleep(2000);

		op.scroll_down();

		String currentStatus = td.getTransactionReviewStatus();
		Commentary.log(LogStatus.INFO, "Current Transaction Status is: "+currentStatus);

		Verify.waitForObject(td.backButtonOnViewTransactionPage, 1);
		td.backButtonOnViewTransactionPage.click();
		Thread.sleep(2000);

		tp.swipe_right();
		Thread.sleep(2000);

		tp.tapOnTransation(1);
		Thread.sleep(2000);
		op.scroll_down();
		
		Verify.waitForObject(td.downloadedTransactionStatus, 2);
		String actStatus = td.getTransactionReviewStatus();

		if (!currentStatus.equalsIgnoreCase(actStatus)) {
			Commentary.log(LogStatus.INFO, "PASS: Status is changed after swipe operation which is now: "+actStatus);
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Status is NOT changed after swipe operation which is now: "+actStatus);
		}
		sa.assertAll();
	}

	@Test(priority = 11, enabled = true)
	public void TR12_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify alert Message displayed after select to Mark all transactions as Reviewed.");

		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();

		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();
		bcc.selectAccount(sSaving);
		
		Verify.waitForObject(tp.buttonSort, 2);
		tp.buttonSort.click();

		if (Verify.objExists(tp.filterNotReviewed)) {
			Commentary.log(LogStatus.INFO, "PASS: Filter By option is displayed for viewing Not Reviewed transactions.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Filter By option is NOT displayed for viewing Not Reviewed transactions.");
		}

		tp.selectSortFilterOption(statusNotReviewed);
		tp.buttonMarkAllReviewed.click();
		Thread.sleep(500);
		if (Verify.objExists(tp.alertMarkAllReviewed)) {
			Commentary.log(LogStatus.INFO, "PASS: Alert Message is displayed for Marking all transaction as Reviewed.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Alert Message is NOT displayed for Marking all transaction as Reviewed.");
		}
		sa.assertAll();
	}

	@Test(priority = 12, enabled = true)
	public void TR13_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify transaction list after applying filter for viewing 'Not Reviewed' transactions.");

		String actTrxStatus, notReviewedText = "Not Reviewed";

		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();

		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();
		TransactionDetailPage td = new TransactionDetailPage();
		bcc.allTransactionButton.click();
		Thread.sleep(1000);
		
		Verify.waitForObject(tp.buttonSort, 2);
		tp.buttonSort.click();
		Thread.sleep(1000);
		tp.selectSortFilterOption(notReviewedText);

		//Verify that first transaction
		tp.tapOnTransation(0);

		op.scroll_down();
		actTrxStatus = td.getTransactionReviewStatus();

		if (actTrxStatus.equalsIgnoreCase(notReviewedText)) {
			Commentary.log(LogStatus.INFO, "PASS: Filter Apply > Not Reviewed > First transaction status correct.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Filter Apply > Not Reviewed > First transaction status is incorrect.");
		}

		td.backButtonOnViewTransactionPage.click();
		Thread.sleep(500);

		//Verify that second transaction
		tp.tapOnTransation(1);

		op.scroll_down();
		actTrxStatus = td.getTransactionReviewStatus();

		if (actTrxStatus.equalsIgnoreCase(notReviewedText)) {
			Commentary.log(LogStatus.INFO, "PASS: Filter Apply > Not Reviewed > Second transaction status correct.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Filter Apply > Not Reviewed > Second transaction status is incorrect.");
		}

		td.backButtonOnViewTransactionPage.click();
		Thread.sleep(500);

		//Verify that third transaction
		tp.tapOnTransation(2);

		op.scroll_down();
		actTrxStatus = td.getTransactionReviewStatus();

		if (actTrxStatus.equalsIgnoreCase(notReviewedText)) {
			Commentary.log(LogStatus.INFO, "PASS: Filter Apply > Not Reviewed > Third transaction status correct.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Filter Apply > Not Reviewed > Third transaction status is incorrect.");
		}

		td.backButtonOnViewTransactionPage.click();
		Thread.sleep(500);

		//Verify that fourth transaction
		tp.tapOnTransation(3);

		op.scroll_down();
		actTrxStatus = td.getTransactionReviewStatus();

		if (actTrxStatus.equalsIgnoreCase(notReviewedText)) {
			Commentary.log(LogStatus.INFO, "PASS: Filter Apply > Not Reviewed > Fourth transaction status correct.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Filter Apply > Not Reviewed > Fourth transaction status is incorrect.");
		}

		td.backButtonOnViewTransactionPage.click();
		Thread.sleep(500);

	}
}
