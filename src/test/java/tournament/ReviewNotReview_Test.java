
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
 * @author vgupta
 * 
 * Review/Not Review and Clear/Unclear/Reconciled Test scripts
 *
 */

public class ReviewNotReview_Test extends Recovery {

	String sUserName = "mobileautomation1@quicken.com";
	String sPassword = "Quicken@01";
	String sDataset = "OnlineAcc_Automation";
	String sOnlineChecking = "Checking1 XX8651";
	String sManualSaving = "Manual_Savings";
	String backButton1_ios = "Banking & Credit";
	String statusCleared = "Cleared";
	String statusUnCleared = "Uncleared";
	String statusReconciled = "Reconciled";
	String statusReviwed = "Reviewed";
	String statusNotReviwed = "Not Reviewed";

	@Test(priority = 0)
	public void TR1_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO,	"Verify Transaction status from changing to Unclear from clear and vice-versa");
		SignInPage signIn = new SignInPage();
		signIn.signIn(sUserName, sPassword, sDataset);
		String payeeName = h.getCurrentTime();
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();
		bcc.selectAccount(sManualSaving);
		tp.DisableRunningBalance();
		tp.addTransaction.click();
		
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("2.00");
		tRec.setPayee(payeeName);
		tRec.setAccount(sManualSaving);
		tRec.setTransactionType("expense");
		h.getContext();
		td.addTransaction(tRec);
		
		tp.searchTransactionTxtField.click();
		tp.searchTransactionTxtField.clear();
		tp.searchTransactionTxtField.sendKeys(payeeName);
		h.hideKeyBoard();
		//tp.searchTransaction(payeeName);
		
		tp.tapOnFirstTransation();
		
		td.selectStatus(statusCleared);
		Thread.sleep(2000);
		
		//tp.searchTransaction(payeeName);
		tp.searchTransactionTxtField.click();
		tp.searchTransactionTxtField.clear();
		tp.searchTransactionTxtField.sendKeys(payeeName);
		//h.hideKeyBoard();
		tp.tapOnFirstTransation();
		
		String actStatus = td.getTransactionStatus();
		
		if (actStatus.equals(statusCleared)) {
			Commentary.log(LogStatus.INFO, "PASS: Transaction Status is changed from Uncleared to Cleared");
			
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Transaction Status is NOT changed from Uncleared to Cleared");
		}
		
		// Again changing from Cleared to Uncleared
		
		td.selectStatus(statusUnCleared);
		
	//	tp.searchTransaction(payeeName);
		tp.searchTransactionTxtField.click();
		tp.searchTransactionTxtField.clear();
		tp.searchTransactionTxtField.sendKeys(payeeName);
		//h.hideKeyBoard();
		
		tp.tapOnFirstTransation();
		
		actStatus = td.getTransactionStatus();
		
		if (actStatus.equals(statusUnCleared)) {
			Commentary.log(LogStatus.INFO, "PASS: Transaction Status is changed from Cleared to Uncleared");
			
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Transaction Status is NOT changed from Cleared to Uncleared");
		}
		sa.assertAll();
	}
	
		@Test(priority = 1)
		public void TR2_Test() throws Exception {

			SoftAssert sa = new SoftAssert();
			Helper h = new Helper();
			
			Commentary.log(LogStatus.INFO,	"Verify Transaction status from changing to Unclear from Reconcile and vice-versa");

			String payeeName = h.getCurrentTime();
			
			OverviewPage op = new OverviewPage();
			op.navigateToAcctList();
			
			BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
			TransactionsPage tp = new TransactionsPage();
			bcc.selectAccount(sManualSaving);
			tp.DisableRunningBalance();
			tp.addTransaction.click();
			
			TransactionDetailPage td = new TransactionDetailPage();
			TransactionRecord tRec = new TransactionRecord();
			tRec.setAmount("2.00");
			tRec.setPayee(payeeName);
			tRec.setAccount(sManualSaving);
			tRec.setTransactionType("expense");
			h.getContext();
			td.addTransaction(tRec);
			
//			tp.searchTransaction(payeeName);
			tp.searchTransactionTxtField.click();
			tp.searchTransactionTxtField.sendKeys(payeeName);
			h.hideKeyBoard();
		
			tp.tapOnFirstTransation();
			
			td.selectStatus(statusReconciled);
			
	//		tp.searchTransaction(payeeName);
			tp.searchTransactionTxtField.click();
			tp.searchTransactionTxtField.clear();
			tp.searchTransactionTxtField.sendKeys(payeeName);
			
			tp.tapOnFirstTransation();
			
			String actStatus = td.getTransactionStatus();
			
			if (actStatus.equals(statusReconciled)) {
				Commentary.log(LogStatus.INFO, "PASS: Transaction Status is changed from Uncleared to Reconcile");
				
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Transaction Status is NOT changed from Uncleared to Reconcile");
			}
			
			// Again changing from Reconcile to Uncleared
			
			td.selectStatus(statusUnCleared);
			
//			tp.searchTransaction(payeeName);
			tp.searchTransactionTxtField.click();
			tp.searchTransactionTxtField.clear();
			tp.searchTransactionTxtField.sendKeys(payeeName);
			
			tp.tapOnFirstTransation();
			
			actStatus = td.getTransactionStatus();
			
			if (actStatus.equals(statusUnCleared)) {
				Commentary.log(LogStatus.INFO, "PASS: Transaction Status is changed from Cleared to Uncleared");
				
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Transaction Status is NOT changed from Cleared to Uncleared");
			}
			sa.assertAll();
		}

		@Test(priority = 2)
		public void TR3_Test() throws Exception {

			SoftAssert sa = new SoftAssert();
			Helper h = new Helper();
			
			Commentary.log(LogStatus.INFO,	"Verify Transaction status from changing to Clear from Reconcile and vice-versa");

			String payeeName = h.getCurrentTime();
			
			OverviewPage op = new OverviewPage();
			op.navigateToAcctList();
			
			BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
			TransactionsPage tp = new TransactionsPage();
			bcc.selectAccount(sManualSaving);
			tp.DisableRunningBalance();
			tp.addTransaction.click();
			
			TransactionDetailPage td = new TransactionDetailPage();
			TransactionRecord tRec = new TransactionRecord();
			tRec.setAmount("2.00");
			tRec.setPayee(payeeName);
			tRec.setAccount(sManualSaving);
			tRec.setTransactionType("expense");
			h.getContext();
			td.addTransaction(tRec);
			
//			tp.searchTransaction(payeeName);
			tp.searchTransactionTxtField.click();
			tp.searchTransactionTxtField.sendKeys(payeeName);
			h.hideKeyBoard();
			//tp.searchTransactionTxtField.sendKeys(payeeName);
			tp.tapOnFirstTransation();
			
			td.selectStatus(statusReconciled);
			
//			tp.searchTransaction(payeeName);
			tp.searchTransactionTxtField.click();
			tp.searchTransactionTxtField.clear();
			tp.searchTransactionTxtField.sendKeys(payeeName);
			
			tp.tapOnFirstTransation();
			
			String actStatus = td.getTransactionStatus();
			
			if (actStatus.equals(statusReconciled)) {
				Commentary.log(LogStatus.INFO, "PASS: Transaction Status is changed from Uncleared to Reconcile");
				
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Transaction Status is NOT changed from Uncleared to Reconcile");
			}
			
			// Again changing from Reconcile to Uncleared
			
			td.selectStatus(statusCleared);
			
//			tp.searchTransaction(payeeName);
			tp.searchTransactionTxtField.click();
			tp.searchTransactionTxtField.clear();
			tp.searchTransactionTxtField.sendKeys(payeeName);
			
			tp.tapOnFirstTransation();
			
			actStatus = td.getTransactionStatus();
			
			if (actStatus.equals(statusCleared)) {
				Commentary.log(LogStatus.INFO, "PASS: Transaction Status is changed from Cleared to Uncleared");
				
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Transaction Status is NOT changed from Cleared to Uncleared");
			}
			sa.assertAll();
		}

		@Test(priority = 3)
		public void TR4_Test() throws Exception {
			SoftAssert sa = new SoftAssert();
			Helper h = new Helper();
			
			Commentary.log(LogStatus.INFO,	"Verify that adding a transaction is always be in Uncleared status");

			String payeeName = h.getCurrentTime();
			
			OverviewPage op = new OverviewPage();
			op.navigateToAcctList();
			
			BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
			TransactionsPage tp = new TransactionsPage();
			bcc.selectAccount(sManualSaving);
			tp.DisableRunningBalance();
			tp.addTransaction.click();
			
			TransactionDetailPage td = new TransactionDetailPage();
			TransactionRecord tRec = new TransactionRecord();
			tRec.setAmount("2.00");
			tRec.setPayee(payeeName);
			tRec.setAccount(sManualSaving);
			tRec.setTransactionType("expense");
			h.getContext();
			td.addTransaction(tRec);
			
//			tp.searchTransaction(payeeName);
			tp.searchTransactionTxtField.click();
			tp.searchTransactionTxtField.sendKeys(payeeName);
			h.hideKeyBoard();
			
			tp.tapOnFirstTransation();
			
			String actTransactionStatus = td.getTransactionStatus();
						
			if (actTransactionStatus.equals(statusUnCleared)) {
				Commentary.log(LogStatus.INFO, "PASS: Default transaction status is set to Unclear");
				
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Default transaction status is NOT set to Unclear");
			}
			sa.assertAll();
		}

		@Test(priority = 4)
		public void TR5_Test() throws Exception {
			SoftAssert sa = new SoftAssert();
			Helper h = new Helper();
			
			Commentary.log(LogStatus.INFO,	"Verify adding Manual transaction and check if it has option to mark it review/not-reviewed");

			String payeeName = h.getCurrentTime();
			
			OverviewPage op = new OverviewPage();
			op.navigateToAcctList();
			
			BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
			TransactionsPage tp = new TransactionsPage();
			bcc.selectAccount(sManualSaving);
			tp.DisableRunningBalance();
			tp.addTransaction.click();
			
			TransactionDetailPage td = new TransactionDetailPage();
			TransactionRecord tRec = new TransactionRecord();
			tRec.setAmount("2.00");
			tRec.setPayee(payeeName);
			tRec.setAccount(sManualSaving);
			tRec.setTransactionType("expense");
			h.getContext();
			td.addTransaction(tRec);
			
//			tp.searchTransaction(payeeName);
			tp.searchTransactionTxtField.click();
			tp.searchTransactionTxtField.sendKeys(payeeName);
			h.hideKeyBoard();
			
			tp.tapOnFirstTransation();
			
			op.scroll_down();
			
			if (!Verify.objExists(td.downloadedTransactionStatus)) {
			
				Commentary.log(LogStatus.INFO, "PASS: Review/Not-review option is not displayed for manually entered transaction");
				
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Review/Not-review option is displayed for manually entered transaction");
			}
			sa.assertAll();
		}

		@Test(priority = 5)
		public void TR6_Test() throws Exception {
			SoftAssert sa = new SoftAssert();
			
			Commentary.log(LogStatus.INFO,	"Verify changing the transaction status from review to not-reviwed and vica-versa");
			
			OverviewPage op = new OverviewPage();
			op.navigateToAcctList();
			
			BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
			TransactionsPage tp = new TransactionsPage();
			bcc.selectAccount(sOnlineChecking);
			tp.DisableRunningBalance();
			
			TransactionDetailPage td = new TransactionDetailPage();
			
			tp.tapOnFirstTransation();
			
			op.scroll_down();
			
			String currentStatus = td.downloadedTransactionStatus.getText();
			td.downloadedTransactionStatus.click();
			Thread.sleep(2000);
			
			String actStatus = td.downloadedTransactionStatus.getText();
			
			if (currentStatus.equals(statusNotReviwed)) {
			
				if (actStatus.equals(statusReviwed)) {
					Commentary.log(LogStatus.INFO, "PASS: Transaction status is updated from Not reviewed to review");
				}
				else {
					Commentary.log(sa, LogStatus.FAIL, "FAIL: Transaction status is NOT updated from Not reviewed to review");

				}
				
				
			} else {
				if (actStatus.equals(statusNotReviwed)) {
					Commentary.log(LogStatus.INFO, "PASS: Transaction status is updated from Reviewed to Not reviewed");
				}
				else {
					Commentary.log(sa, LogStatus.FAIL, "FAIL: Transaction status is NOT updated from Reviewed to Not reviewed");

				}
			}
			
			sa.assertAll();
		}
		@Test(priority = 6)
		public void TR7_Test() throws Exception {
			String actStatus = null;
			SoftAssert sa = new SoftAssert();
			
			Commentary.log(LogStatus.INFO,	"Verify changing the transaction status from review to not-reviwed and vica-versa");
			
			OverviewPage op = new OverviewPage();
			op.navigateToAcctList();
			
			BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
			TransactionsPage tp = new TransactionsPage();
			bcc.selectAccount(sOnlineChecking);
			tp.DisableRunningBalance();
			
			TransactionDetailPage td = new TransactionDetailPage();
			
			tp.tapOnFirstTransation();
			
			op.scroll_down();
			
			String currentStatus = td.downloadedTransactionStatus.getText();
			if (currentStatus.equals(statusReviwed)) {
				td.downloadedTransactionStatus.click();
				Thread.sleep(2000);
				actStatus = td.downloadedTransactionStatus.getText();
			}
			else {
				Commentary.log(LogStatus.INFO, "Selected Transaction is Not Reviewed");
				actStatus = td.downloadedTransactionStatus.getText();
			}
			
			if (actStatus.equals(statusNotReviwed)) {
			
				Commentary.log(LogStatus.INFO, "PASS: Transaction status is updated from Reviewed to Not reviewed");
				
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Transaction status is NOT updated from Reviewed to Not reviewed");

			}
			
			sa.assertAll();
		}
		
		@Test(priority = 7)
		public void TR8_Test() throws Exception {
			
			SoftAssert sa = new SoftAssert();
			
			Commentary.log(LogStatus.INFO,	"Verify filter list for transaction status");
			
			OverviewPage op = new OverviewPage();
			op.navigateToAcctList();
			
			BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
			TransactionsPage tp = new TransactionsPage();
			bcc.selectAccount(sOnlineChecking);
			
			tp.buttonSort.click();
			
			if (Verify.objExists(tp.filterNotReviewed)) {
			
				Commentary.log(LogStatus.INFO, "PASS: Filter By option is displayed for viewing Not Reviewed transactions");
				
			} else {
				
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Filter By option is NOT displayed for viewing Not Reviewed transactions");

			}
			
			sa.assertAll();
		}
		@Test(priority = 8)
		public void TR9_Test() throws Exception {
			
			SoftAssert sa = new SoftAssert();
			
			Commentary.log(LogStatus.INFO,	"Verify filter list functionality for transaction status");
			
			OverviewPage op = new OverviewPage();
			op.navigateToAcctList();
			
			BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
			TransactionsPage tp = new TransactionsPage();
			bcc.selectAccount(sOnlineChecking);
			
			tp.buttonSort.click();
			
			if (Verify.objExists(tp.filterNotReviewed)) {
			
				Commentary.log(LogStatus.INFO, "PASS: Filter By option is displayed for viewing Not Reviewed transactions");
				
			} else {
				
				Commentary.log(LogStatus.FAIL, "FAIL: Filter By option is NOT displayed for viewing Not Reviewed transactions");

			}
			
			tp.selectSortFilterOption("Not Reviewed");
			
			if (Verify.objExists(tp.filterNotReviewedHeader)) {
				Commentary.log(LogStatus.INFO, "PASS: Header displayed as NOT REVIEWED");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Header is NOT displayed as NOT REVIEWED");
			}
			
			if (Verify.objExists(tp.buttonClearFilter)) {
				Commentary.log(LogStatus.INFO, "PASS: Button is displayed for Clear Filters");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Button is NOT displayed for Clear Filter");
			}
			
			if (Verify.objExists(tp.buttonMarkAllReviewed)) {
				Commentary.log(LogStatus.INFO, "PASS: Button is displayed for Mark All as Reviewed");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Button is NOT displayed for Mark All as Reviewed");
			}
			
			sa.assertAll();
		}
		@Test(priority = 9)
		public void TR10_Test() throws Exception {
			
			SoftAssert sa = new SoftAssert();
			
			Commentary.log(LogStatus.INFO,	"Verify functionality for Clear Filter button");
			
			OverviewPage op = new OverviewPage();
			op.navigateToAcctList();
			
			BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
			TransactionsPage tp = new TransactionsPage();
			bcc.selectAccount(sOnlineChecking);
			
			tp.buttonSort.click();
			Thread.sleep(1000);
			
			tp.selectSortFilterOption("Not Reviewed");
			
			if (Verify.objExists(tp.filterNotReviewedHeader)) {
				Commentary.log(LogStatus.INFO, "PASS: Header displayed as NOT REVIEWED");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Header is NOT displayed as NOT REVIEWED");
			}
			
			if (Verify.objExists(tp.buttonClearFilter)) {
				Commentary.log(LogStatus.INFO, "PASS: Button is displayed for Clear Filters");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Button is NOT displayed for Clear Filter");
			}
			
			if (Verify.objExists(tp.buttonMarkAllReviewed)) {
				Commentary.log(LogStatus.INFO, "PASS: Button is displayed for Mark All as Reviewed");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Button is NOT displayed for Mark All as Reviewed");
			}
			
			tp.buttonClearFilter.click();
			
			if (!Verify.objExists(tp.filterNotReviewedHeader)) {
				Commentary.log(LogStatus.INFO, "PASS: Clear filter > Header not diplayed");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Clear filter > Header is diplayed");
			}
			
			if (!Verify.objExists(tp.buttonClearFilter)) {
				Commentary.log(LogStatus.INFO, "PASS: Clear filter > Button not displayed for clear filter");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Cler filter > Button still displayed for Clear Filter");
			}
			
			if (!Verify.objExists(tp.buttonMarkAllReviewed)) {
				Commentary.log(LogStatus.INFO, "PASS: Clear filter > Button not displayed for mark all as reviewed");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Cler filter > Button still displayed for mark all as reviewed");
			}
			
			sa.assertAll();
		}
		@Test(priority = 10)
		public void TR11_Test() throws Exception {
			SoftAssert sa = new SoftAssert();
			
			Commentary.log(LogStatus.INFO,	"Verify swipe feature to mark the transaction as reviewed/not-reviewed");
			
			OverviewPage op = new OverviewPage();
			op.navigateToAcctList();
			
			BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
			TransactionsPage tp = new TransactionsPage();
			bcc.selectAccount(sOnlineChecking);
			tp.DisableRunningBalance();
			Thread.sleep(2000);
			TransactionDetailPage td = new TransactionDetailPage();
			
			tp.tapOnTransation(0);
			Thread.sleep(2000);
			
			op.scroll_down();
			
			String currentStatus = td.downloadedTransactionStatus.getText();
			
			td.backButton.click();
			Thread.sleep(1000);
			
			tp.swipe_right();
			Thread.sleep(1000);
			
			tp.tapOnTransation(0);
			Thread.sleep(2000);
			op.scroll_down();
			
			String actStatus = td.getTransactionReviewStatus();
			
			if (!currentStatus.equalsIgnoreCase(actStatus)) {
				Commentary.log(LogStatus.INFO, "PASS: Status is changed after swipe operation");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Status is NOT changed after swipe operation");
			}
			sa.assertAll();
		}
		
		@Test(priority = 11)
		public void TR12_Test() throws Exception {
			
			SoftAssert sa = new SoftAssert();
			
			Commentary.log(LogStatus.INFO,	"Verify alert Message displayed after select to Mark all transactions as Reviewed");
			
			OverviewPage op = new OverviewPage();
			op.navigateToAcctList();
			
			BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
			TransactionsPage tp = new TransactionsPage();
			bcc.selectAccount(sOnlineChecking);
			
			tp.buttonSort.click();
			
			if (Verify.objExists(tp.filterNotReviewed)) {
			
				Commentary.log(LogStatus.INFO, "PASS: Filter By option is displayed for viewing Not Reviewed transactions");
				
			} else {
				
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Filter By option is NOT displayed for viewing Not Reviewed transactions");

			}
			
			tp.selectSortFilterOption(statusNotReviwed);
			tp.buttonMarkAllReviewed.click();
			Thread.sleep(500);
			if (Verify.objExists(tp.alertMarkAllReviewed)) {
				Commentary.log(LogStatus.INFO, "PASS: Alert Message is displayed for Marking all transaction as Reviewed");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Alert Message is NOT displayed for Marking all transaction as Reviewed");
			}
			sa.assertAll();
		}
		
		@Test(priority = 12)
		public void TR13_Test() throws Exception {
			String actTrxStatus, notReviewedText = "Not Reviewed";
			SoftAssert sa = new SoftAssert();
			
			Commentary.log(LogStatus.INFO,	"Verify transaction list after applying filter for viewing 'Not Reviewed' transactions");
			
			OverviewPage op = new OverviewPage();
			op.navigateToAcctList();
			
			BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
			TransactionsPage tp = new TransactionsPage();
			TransactionDetailPage td = new TransactionDetailPage();
			bcc.allTransactionButton.click();
			Thread.sleep(1000);
			
			tp.buttonSort.click();
			Thread.sleep(1000);
			tp.selectSortFilterOption(notReviewedText);
			
			//Verify that first transaction
			tp.tapOnTransation(2);
			
			op.scroll_down();
			actTrxStatus = td.getTransactionReviewStatus();
			
			if (actTrxStatus.equalsIgnoreCase(notReviewedText)) {
				Commentary.log(LogStatus.INFO, "PASS: Filter Apply > Not Reviewed > First transaction status correct");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Filter Apply > Not Reviewed > First transaction status is incorrect");
			}
			
			td.backButton.click();
			Thread.sleep(500);
			
			//Verify that second transaction
			tp.tapOnTransation(3);
			
			op.scroll_down();
			actTrxStatus = td.getTransactionReviewStatus();
			
			if (actTrxStatus.equalsIgnoreCase(notReviewedText)) {
				Commentary.log(LogStatus.INFO, "PASS: Filter Apply > Not Reviewed > Second transaction status correct");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Filter Apply > Not Reviewed > Second transaction status is incorrect");
			}
			
			td.backButton.click();
			Thread.sleep(500);
			
			//Verify that third transaction
			tp.tapOnTransation(4);
			
			op.scroll_down();
			actTrxStatus = td.getTransactionReviewStatus();
			
			if (actTrxStatus.equalsIgnoreCase(notReviewedText)) {
				Commentary.log(LogStatus.INFO, "PASS: Filter Apply > Not Reviewed > Third transaction status correct");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Filter Apply > Not Reviewed > Third transaction status is incorrect");
			}
			
			td.backButton.click();
			Thread.sleep(500);
			
			//Verify that fourth transaction
			tp.tapOnTransation(5);
			
			op.scroll_down();
			actTrxStatus = td.getTransactionReviewStatus();
			
			if (actTrxStatus.equalsIgnoreCase(notReviewedText)) {
				Commentary.log(LogStatus.INFO, "PASS: Filter Apply > Not Reviewed > Fourth transaction status correct");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Filter Apply > Not Reviewed > Fourth transaction status is incorrect");
			}
			
			td.backButton.click();
			Thread.sleep(500);
			
			//Verify that fifth transaction
			tp.tapOnTransation(4);
			
			op.scroll_down();
			actTrxStatus = td.getTransactionReviewStatus();
			
			if (actTrxStatus.equalsIgnoreCase(notReviewedText)) {
				Commentary.log(LogStatus.INFO, "PASS: Filter Apply > Not Reviewed > Fifth transaction status correct");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Filter Apply > Not Reviewed > Fifth transaction status is incorrect");
			}
			
			td.backButton.click();
			Thread.sleep(500);
		}
}
