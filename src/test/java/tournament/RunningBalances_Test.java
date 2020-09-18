/**
 * 
 */
package tournament;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.AllAccountsPage;
import dugout.BankingAndCreditCardPage;
import dugout.OverviewPage;
import dugout.SignInPage;
import dugout.TransactionDetailPage;
import dugout.TransactionSummaryPage;
import dugout.TransactionsPage;
import referee.Commentary;
import referee.Verify;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;

/**
 * @author Kartik
 * 
 * Running Balances Test scripts
 *
 */
public class RunningBalances_Test extends Recovery {
	
	String sUserName = "mobileautomation1@quicken.com";
	String sPassword = "Quicken@01";
	String sDataset = "OnlineAcc_Automation";
	String sOnlineChecking = "Checking1 XX8651";
	String sSaving = "Saving XX3867";
	String sManualSaving = "Manual_Savings";
	String backButton1_ios = "Banking & Credit";
	String filterLowToHigh = "Amount Low to High"; 
	String filterHighToLow = "Amount High to Low";
	String filterNewToOld = "Date New to Old";
	String filterOldToNew = "Date Old to New";
	String filterPendingCleared = "Pending to Cleared";
	String filterNotReviewed = "Not Reviewed";
	String filter7days = "Next 7 Days";
	String filter14days = "Next 14 Days";
	String filter30days = "Next 30 Days";
	String filter90days = "Next 90 Days";
	String filterDontShowReminder = "Don't show reminders";
	
	
	@Test(priority = 0, enabled = false)
	public void RB1_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		SignInPage signIn = new SignInPage();
		signIn.signIn(sUserName, sPassword, sDataset);
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that Running Balance option should not appear on the All Transactions screen.");
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();
		
		bcc.allTransactionButton.click();
		Thread.sleep(1000);
		
		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		
		// Verify Show running balance option should not be displayed on All Transaction screen
		if (!Verify.objExists(tp.headerTransactionDetail)) {
			Commentary.log(LogStatus.INFO, "PASS: All Transaction > Show running balance is not displayed.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: All Transaction > Show running balance is displayed.");
		}
		
		if (!Verify.objExists(tp.switchShowRunningBalanceText)) {
			Commentary.log(LogStatus.INFO, "PASS: All Transaction > Show running balance switch is not displayed.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: All Transaction > Show running balance switch is displayed.");
		}
		sa.assertAll();
	}
	
	@Test(priority = 1, enabled = false)
	public void RB2_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that Running Balance option should appear on Account detail screen & should not appear on the All Transactions screen.");
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();
		
		bcc.selectAccount(sManualSaving);
		Thread.sleep(1000);
		
		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		
		// Verify Show running balance options is displayed on Account detail screen.
		if (Verify.objExists(tp.headerTransactionDetail)) {
			Commentary.log(LogStatus.INFO, "PASS: Manual_Savings Account Detail screen > All Transaction > Show running balance is displayed.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Manual_Savings Account Detail screen > All Transaction > Show running balance is not displayed.");
		}
		tp.buttonClose.click();
		
		tp.navigateBackToDashboard();
		
		op.tapOnRecentTransactionsCard();
		
		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		
		// Verify Show running balance option should NOT be displayed in Recent Transaction card.
		if (!Verify.objExists(tp.headerTransactionDetail)) {
			Commentary.log(LogStatus.INFO, "PASS: Recent Transaction card > All Transaction > Show running balance is not displayed.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Recent Transaction card > All Transaction > Show running balance is displayed.");
		}
		tp.buttonClose.click();
		bcc.backButton.click();
		
		op.tapOnTransactionSummaryCard();
		TransactionSummaryPage ts = new TransactionSummaryPage();
		ts.payeeTab.click();
		ts.firstRecordInList.click();
		
		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		
		// Verify Show running balance option should NOT be displayed on the Show Reminder Filter option of the transaction in Transaction Summary card.
		
		if (!Verify.objExists(tp.headerTransactionDetail)) {
			Commentary.log(LogStatus.INFO, "PASS: Transaction Summary card > All Transaction > Show running balance is not displayed.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Transaction Summary card > All Transaction > Show running balance is displayed.");
		}
	}
	
	@Test(priority = 2, enabled = false)
	public void RB3_Test() throws Exception { 
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that by default the Show Running balance toggle should be ON and \"Date New to old\" should be selected by default.");
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();
		
		bcc.selectAccount(sManualSaving);
		Thread.sleep(1000);
		
		// Verify Show running balance options is displayed in Account detail screen.
		if (tp.isRunningBalanceEnabled()) {
			Commentary.log(LogStatus.INFO, "PASS: Running Balance is enabled by default.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Running Balance is NOT enabled by default.");
		}
		
		tp.buttonClose.click();
		Verify.waitForObject(tp.buttonSort, 1);
		tp.buttonSort.click();
		Thread.sleep(1000);
		
		//Verify that default filter is set to Date New to Old
		if (Verify.objExists(tp.defaultfilterSelected)) {
			Commentary.log(LogStatus.INFO, "PASS: Filter for Date New to old is selected by default.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Filter for Date New to old is NOT selected by default.");
		}
		sa.assertAll();
	}
	
	@Test(priority = 3, enabled = false)
	public void RB4_Test() throws Exception { 
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that Running balance should appear only when filters are set to \"Date New to Old\" or \"Date Old to New\" ");
		
		String firstRowRunningBalance, secondRowRunningBalance, thirdRowRunningBalance;
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		AllAccountsPage aa = new AllAccountsPage();
		TransactionsPage tp = new TransactionsPage();
		
		bcc.selectAccount(sManualSaving);
		Thread.sleep(1000);
		
		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		tp.selectSortFilterOption(filterDontShowReminder);
		
		Verify.waitForObject(tp.buttonSort, 1);
		tp.buttonSort.click();
		
		//Verify running balance for filter Amount Low to High
		tp.selectSortFilterOption(filterLowToHigh);
		
		firstRowRunningBalance = aa.getTransactionDate(1);
	    secondRowRunningBalance= aa.getTransactionDate(2);
	    thirdRowRunningBalance= aa.getTransactionDate(3);
		
	    if (!(firstRowRunningBalance.contains("$")) & !(secondRowRunningBalance.contains("$")) & !(thirdRowRunningBalance.contains("$"))) {
			Commentary.log(LogStatus.INFO, "PASS: Filter Applied > Amount Low to high > Running balance is not displayed.");
			
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Filter Applied > Amount Low to high > Running balance is displayed.");
		}
		
		//Verify running balance for filter Amount High to Low
	    Verify.waitForObject(tp.buttonSort, 1);
		tp.buttonSort.click();
		tp.selectSortFilterOption(filterHighToLow);
		
		firstRowRunningBalance = aa.getTransactionDate(1);
	    secondRowRunningBalance= aa.getTransactionDate(2);
	    thirdRowRunningBalance= aa.getTransactionDate(3);
				
	    if (!(firstRowRunningBalance.contains("$")) & !(secondRowRunningBalance.contains("$")) & !(thirdRowRunningBalance.contains("$"))) {
			Commentary.log(LogStatus.INFO, "PASS: Filter Applied > Amount High to Low > Running balance is not displayed.");
					
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Filter Applied > Amount High to Low > Running balance is displayed.");
		}
				
		//Verify running balance for filter Date New to Old
	    Verify.waitForObject(tp.buttonSort, 1);
		tp.buttonSort.click();
		tp.selectSortFilterOption(filterNewToOld);
		
		firstRowRunningBalance = aa.getTransactionDate(1);
	    secondRowRunningBalance= aa.getTransactionDate(2);
	    thirdRowRunningBalance= aa.getTransactionDate(3);
				
	    if (firstRowRunningBalance.contains("$") & secondRowRunningBalance.contains("$") & thirdRowRunningBalance.contains("$")) {
			Commentary.log(LogStatus.INFO, "PASS: Filter Applied > Amount New to Old > Running balance is displayed.");
					
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Filter Applied > Amount New to Old > Running balance is NOT displayed.");
		}
		
		//Verify running balance for filter Date Old to New
	    Verify.waitForObject(tp.buttonSort, 1);
		tp.buttonSort.click();
		tp.selectSortFilterOption(filterOldToNew);
		
		firstRowRunningBalance = aa.getTransactionDate(1);
	    secondRowRunningBalance= aa.getTransactionDate(2);
	    thirdRowRunningBalance= aa.getTransactionDate(3);
				
	    if (firstRowRunningBalance.contains("$") & secondRowRunningBalance.contains("$") & thirdRowRunningBalance.contains("$")) {
			Commentary.log(LogStatus.INFO, "PASS: Filter Applied > Date Old to New > Running balance is displayed.");
					
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Filter Applied > Date Old to New > Running balance is NOT displayed.");
		}
	
		//Verify running balance for filter Pending to Cleared
	    Verify.waitForObject(tp.buttonSort, 1);
		tp.buttonSort.click();
		tp.selectSortFilterOption(filterPendingCleared);
		
		firstRowRunningBalance = aa.getTransactionDate(1);
	    secondRowRunningBalance= aa.getTransactionDate(2);
	    thirdRowRunningBalance= aa.getTransactionDate(3);
				
	    if (!(firstRowRunningBalance.contains("$")) & !(secondRowRunningBalance.contains("$")) & !(thirdRowRunningBalance.contains("$"))) {
			Commentary.log(LogStatus.INFO, "PASS: Filter Applied > Pending to Cleared > Running balance is not displayed.");			
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Filter Applied > Pending to Cleared > Running balance is displayed.");
		}
	    
		sa.assertAll();
	}
	
	@Test(priority = 4, enabled = false)
	public void RB5_Test() throws Exception { 
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify Balance calculation for filter combination \"Date new to old\" + \"Show Running Balance\" set to ON");
		
		Double dFirstRunningBalance, dSecondRunningBalance, dThirdRunningBalance, dFourthRunningBalance, dFirstTxnAmount, dSecondTxnAmount, dThirdTxnAmount;

		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();
		AllAccountsPage aa = new AllAccountsPage();
		bcc.selectAccount(sManualSaving);
		Thread.sleep(1000);
		
		Verify.waitForObject(tp.buttonSort, 1);
		tp.buttonSort.click();
		Thread.sleep(1000);
		
		tp.selectSortFilterOption(filterNewToOld);
		tp.EnableRunningBalance();
		//tp.buttonShowReminder.click();
//		if (tp.isRunningBalanceEnabled()) {
//			Commentary.log(LogStatus.INFO, "Running balance is enabled by default");
//			tp.buttonApply.click();
//			Thread.sleep(1000);
//			
//		} else {
//			Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is NOT enabled by default");
//		}
		
		dFirstRunningBalance= aa.getRunningBalancefromTransaction(1);
		dFirstTxnAmount = aa.getTransactionAmount(1);
		//Double iFirstRunningBalance = Math.abs(dFirstRunningBalance); //This will negate the value if there is negative running balance value.
		Double iFirstTxnAmount = Math.abs(dFirstTxnAmount);
		
		dSecondRunningBalance= aa.getRunningBalancefromTransaction(2);
		dSecondTxnAmount = aa.getTransactionAmount(2);
		//Double iSecondRunningBalance = Math.abs(dSecondRunningBalance);
		Double iSecondTxnAmount = Math.abs(dSecondTxnAmount);
		
		dThirdRunningBalance= aa.getRunningBalancefromTransaction(3);
		dThirdTxnAmount = aa.getTransactionAmount(3);
		//Double iThirdRunningBalance = Math.abs(dThirdRunningBalance);
		Double iThirdTxnAmount = Math.abs(dThirdTxnAmount);
		
		dFourthRunningBalance= aa.getRunningBalancefromTransaction(4);
		//Double iFourthRunningBalance = Math.abs(dFourthRunningBalance);
		
		//if (dThirdTxnAmount<0 & dFourthRunningBalance>0) {
		if (dThirdTxnAmount<0) {
			//if (iFourthRunningBalance-iThirdTxnAmount==iThirdRunningBalance) {
			if (dFourthRunningBalance-iThirdTxnAmount==dThirdRunningBalance) {
				Commentary.log(LogStatus.INFO, "PASS: Running balance is ["+dFourthRunningBalance+"] and transaction amount is ["+dThirdTxnAmount+"], calculated Running balance is ["+dThirdRunningBalance+"]");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is ["+dFourthRunningBalance+"] and transaction amount is ["+dThirdTxnAmount+"], calculated Running balance is ["+dThirdRunningBalance+"]");
			}
		}
		else {
			if (dFourthRunningBalance+iThirdTxnAmount==dThirdRunningBalance) {
				Commentary.log(LogStatus.INFO, "PASS: Running balance is ["+dFourthRunningBalance+"] and transaction amount is ["+dThirdTxnAmount+"], calculated Running balance is ["+dThirdRunningBalance+"]");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is ["+dFourthRunningBalance+"] and transaction amount is ["+dThirdTxnAmount+"], calculated Running balance is ["+dThirdRunningBalance+"]");
			}
		}
		
		//if (dSecondTxnAmount<0 & dThirdRunningBalance>0) {
		if (dSecondTxnAmount<0) {
			if (dThirdRunningBalance-iSecondTxnAmount==dSecondRunningBalance) {
				Commentary.log(LogStatus.INFO, "PASS: Running balance is ["+dThirdRunningBalance+"] and transaction amount is ["+dSecondTxnAmount+"], calculated Running balance is ["+dSecondRunningBalance+"]");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is ["+dThirdRunningBalance+"] and transaction amount is ["+dSecondTxnAmount+"], calculated Running balance is ["+dSecondRunningBalance+"]");
			}
		}
		else {
			if (dThirdRunningBalance+iSecondTxnAmount==dSecondRunningBalance) {
				Commentary.log(LogStatus.INFO, "PASS: Running balance is ["+dThirdRunningBalance+"] and transaction amount is ["+dSecondTxnAmount+"], calculated Running balance is ["+dSecondRunningBalance+"]");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is ["+dThirdRunningBalance+"] and transaction amount is ["+dSecondTxnAmount+"], calculated Running balance is ["+dSecondRunningBalance+"]");
			}
		}
		
		//if (dFirstTxnAmount<0 & dSecondRunningBalance>0) {
		if (dFirstTxnAmount<0) {	
			if (dSecondRunningBalance-iFirstTxnAmount==dFirstRunningBalance) {
				Commentary.log(LogStatus.INFO, "PASS: Running balance is ["+dSecondRunningBalance+"] and transaction amount is ["+dFirstTxnAmount+"], calculated Running balance is ["+dFirstRunningBalance+"]");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is ["+dSecondRunningBalance+"] and transaction amount is ["+dFirstTxnAmount+"], calculated Running balance is ["+dFirstRunningBalance+"]");
			}
		}
		else {
			if (dSecondRunningBalance+iFirstTxnAmount==dFirstRunningBalance) {
				Commentary.log(LogStatus.INFO, "PASS: Running balance is ["+dSecondRunningBalance+"] and transaction amount is ["+dFirstTxnAmount+"], calculated Running balance is ["+dFirstRunningBalance+"]");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is ["+dSecondRunningBalance+"] and transaction amount is ["+dFirstTxnAmount+"], calculated Running balance is ["+dFirstRunningBalance+"]");
			}
		}
		sa.assertAll();
	}
	
	@Test(priority = 5, enabled = false)
	public void RB6_Test() throws Exception { 
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that for filter combination \"Date new to old\" + \"Show Running Balance\" set to OFF, Running balance should not appear, only dates should appear.");
		
		String firstTransactionDate, secondTransactionDate, thirdTransactionDate;
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
	
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();
		AllAccountsPage aa = new AllAccountsPage();
		bcc.selectAccount(sManualSaving);
		Thread.sleep(1000);
		
		Verify.waitForObject(tp.buttonSort, 1);
		tp.buttonSort.click();
		Thread.sleep(1000);
		tp.selectSortFilterOption(filterNewToOld);
		
		tp.DisableRunningBalance();
		
		firstTransactionDate= aa.getTransactionDate(1);
		secondTransactionDate= aa.getTransactionDate(2);
		thirdTransactionDate= aa.getTransactionDate(3);
		
		if ((!firstTransactionDate.contains("$")) & (!secondTransactionDate.contains("$")) & (!thirdTransactionDate.contains("$"))) {
			Commentary.log(LogStatus.INFO, "PASS: Running balances is disabled and Dates are appearing.");
		} else {
			Commentary.log(sa,LogStatus.FAIL, "FAIL: Running balances are displayed.");
		}
		sa.assertAll();
	}
	
	@Test(priority = 6, enabled = false)
	public void RB7_Test() throws Exception { 
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify balance for filter combination \"Date old to new\" + \"Show Running Balance\" set to ON, balance calculation after each transaction should be correct.");
		
		Double dFirstRunningBalance, dSecondRunningBalance, dThirdRunningBalance, dFourthRunningBalance, dFirstTxnAmount, dSecondTxnAmount, dThirdTxnAmount, dFourthTxnAmount;

		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();
		AllAccountsPage aa = new AllAccountsPage();
		bcc.selectAccount(sManualSaving);
		Thread.sleep(1000);
		//sTotalBalance = h.processBalanceAmount(bcc.getTotalBalance().replace("$", ""));
		Verify.waitForObject(tp.buttonSort, 1);
		tp.buttonSort.click();
		Thread.sleep(1000);
		tp.selectSortFilterOption(filterOldToNew);
		tp.EnableRunningBalance();
		
		//tp.buttonShowReminder.click();
//		if (tp.isRunningBalanceEnabled()) {
//			Commentary.log(LogStatus.INFO, "Running balance is enabled by default");
//			tp.buttonApply.click();
//			Thread.sleep(2000);
//		} else {
//			Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is NOT enabled by default");
//		}
		
		dFirstRunningBalance= aa.getRunningBalancefromTransaction(1);
		dFirstTxnAmount = aa.getTransactionAmount(1);
		//Double iFirstRunningBalance = Math.abs(dFirstRunningBalance);
		Double iFirstTxnAmount = Math.abs(dFirstTxnAmount);
		
		dSecondRunningBalance= aa.getRunningBalancefromTransaction(2);
		dSecondTxnAmount = aa.getTransactionAmount(2);
		//Double iSecondRunningBalance = Math.abs(dSecondRunningBalance);
		Double iSecondTxnAmount = Math.abs(dSecondTxnAmount);
		
		dThirdRunningBalance= aa.getRunningBalancefromTransaction(3);
		dThirdTxnAmount = aa.getTransactionAmount(3);
		//Double iThirdRunningBalance = Math.abs(dThirdRunningBalance);
		Double iThirdTxnAmount = Math.abs(dThirdTxnAmount);
		
		dFourthRunningBalance= aa.getRunningBalancefromTransaction(4);
		dFourthTxnAmount = aa.getTransactionAmount(4);
		//Double iFourthRunningBalance = Math.abs(dFourthRunningBalance);
		Double iFourthTxnAmount = Math.abs(dFourthTxnAmount);
	
		//if (dSecondTxnAmount<0 & dFirstRunningBalance>0) {
		if (dSecondTxnAmount<0) {
			if (dFirstRunningBalance-iSecondTxnAmount==dSecondRunningBalance) {
				Commentary.log(LogStatus.INFO, "PASS: Running balance is ["+dFirstRunningBalance+"] and transaction amount is ["+dSecondTxnAmount+"], calculated Running balance is ["+dSecondRunningBalance+"]");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is ["+dFirstRunningBalance+"] and transaction amount is ["+dSecondTxnAmount+"], calculated Running balance is ["+dSecondRunningBalance+"]");
			}
		}
		else {
			if (dFirstRunningBalance+iSecondTxnAmount==dSecondRunningBalance) {
				Commentary.log(LogStatus.INFO, "PASS: Running balance is ["+dFirstRunningBalance+"] and transaction amount is ["+dSecondTxnAmount+"], calculated Running balance is ["+dSecondRunningBalance+"]");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is ["+dFirstRunningBalance+"] and transaction amount is ["+dSecondTxnAmount+"], calculated Running balance is ["+dSecondRunningBalance+"]");
			}
		}
		
		//if (dThirdTxnAmount<0 & dSecondRunningBalance >0) {
		if (dThirdTxnAmount<0) {
			if (dSecondRunningBalance-iThirdTxnAmount==dThirdRunningBalance) {
				Commentary.log(LogStatus.INFO, "PASS: Running balance is ["+dSecondRunningBalance+"] and transaction amount is ["+dThirdTxnAmount+"], calculated Running balance is ["+dThirdRunningBalance+"]");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is ["+dSecondRunningBalance+"] and transaction amount is ["+dThirdTxnAmount+"], calculated Running balance is ["+dThirdRunningBalance+"]");
			}
		}
		else {
			if (dSecondRunningBalance+iThirdTxnAmount==dThirdRunningBalance) {
				Commentary.log(LogStatus.INFO, "PASS: Running balance is ["+dSecondRunningBalance+"] and transaction amount is ["+dThirdTxnAmount+"], calculated Running balance is ["+dThirdRunningBalance+"]");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is ["+dSecondRunningBalance+"] and transaction amount is ["+dThirdTxnAmount+"], calculated Running balance is ["+dThirdRunningBalance+"]");
			}
		}
		
		//if (dFourthTxnAmount<0 & dThirdRunningBalance>0) {
		if (dFourthTxnAmount<0) {
			if (dThirdRunningBalance-iFourthTxnAmount==dFourthRunningBalance) {
				Commentary.log(LogStatus.INFO, "PASS: Running balance is ["+dThirdRunningBalance+"] and transaction amount is ["+dFourthTxnAmount+"], calculated Running balance is ["+dFourthRunningBalance+"]");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is ["+dThirdRunningBalance+"] and transaction amount is ["+dFourthTxnAmount+"], calculated Running balance is ["+dFourthRunningBalance+"]");
			}
		}
		else {
			if (dThirdRunningBalance+iFourthTxnAmount==dFourthRunningBalance) {
				Commentary.log(LogStatus.INFO, "PASS: Running balance is ["+dThirdRunningBalance+"] and transaction amount is ["+dFourthTxnAmount+"], calculated Running balance is ["+dFourthRunningBalance+"]");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is ["+dThirdRunningBalance+"] and transaction amount is ["+dFourthTxnAmount+"], calculated Running balance is ["+dFourthRunningBalance+"]");
			}
		}
		sa.assertAll();
	}
	
	@Test(priority = 7, enabled = false)
	public void RB8_Test() throws Exception { 
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that for filter combination \"Date old to new\" + \"Show Running balance\" set to OFF, Running balance should not appear, only dates should appear.");
		
		String firstTransactionDate, secondTransactionDate, thirdTransactionDate;
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
	
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();
		AllAccountsPage aa = new AllAccountsPage();
		bcc.selectAccount(sManualSaving);
		Thread.sleep(1000);
		
		Verify.waitForObject(tp.buttonSort, 1);
		tp.buttonSort.click();
		Thread.sleep(1000);
		tp.selectSortFilterOption(filterOldToNew);
		
		tp.DisableRunningBalance();
		
		firstTransactionDate= aa.getTransactionDate(1);
		secondTransactionDate= aa.getTransactionDate(2);
		thirdTransactionDate= aa.getTransactionDate(3);
		
		if ((!firstTransactionDate.contains("$")) & (!secondTransactionDate.contains("$")) & (!thirdTransactionDate.contains("$"))) {
			Commentary.log(LogStatus.INFO, "PASS: Running balances is disabled and Dates are appearing.");
		} else {
			Commentary.log(sa,LogStatus.FAIL, "FAIL: Running balances are displayed.");
		}
		sa.assertAll();
	}
	
	@Test(priority = 8, enabled = false)
	public void RB9_Test() throws Exception {  
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that for filter combination \"Date new to old\" + \"Show Running balance\" set to ON + Not reviewed. Only Not reviewed transaction should appear and also running balance should appear under the transactions.");
		
		String firstRowRunningBalance, secondRowRunningBalance, thirdRowRunningBalance;
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
	
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();
		AllAccountsPage aa = new AllAccountsPage();
		bcc.selectAccount(sSaving);
		Thread.sleep(1000);
		
		Verify.waitForObject(tp.buttonSort, 1);
		tp.buttonSort.click();
		Thread.sleep(1000);
		tp.selectSortFilterOption(filterNewToOld);
		Verify.waitForObject(tp.buttonSort, 1);
		tp.buttonSort.click();
		Thread.sleep(1000);
		tp.selectSortFilterOption(filterNotReviewed);
		
		Thread.sleep(1000);
		
		tp.EnableRunningBalance();
		
		firstRowRunningBalance = aa.getTransactionDate(1);
		secondRowRunningBalance= aa.getTransactionDate(2);
		thirdRowRunningBalance= aa.getTransactionDate(3);
		
		if ((firstRowRunningBalance.contains("$")) & (secondRowRunningBalance.contains("$")) & (thirdRowRunningBalance.contains("$"))) {
			Commentary.log(LogStatus.INFO, "PASS: Running balances are displayed");
		} else {
			Commentary.log(sa,LogStatus.FAIL, "FAIL: Running balances are NOT displayed");
		}
		
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
	
	@Test(priority = 9, enabled = false)
	public void RB10_Test() throws Exception {  
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that for filter combination \"Date new to old\" + \"Show Running balance\" set to OFF + Not reviewed. Only Not reviewed transaction should appear and also running balance should appear under the transactions.");
		
		String firstRowRunningBalance, secondRowRunningBalance, thirdRowRunningBalance;
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
	
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();
		AllAccountsPage aa = new AllAccountsPage();
		bcc.selectAccount(sSaving);
		Thread.sleep(1000);
		
		Verify.waitForObject(tp.buttonSort, 1);
		tp.buttonSort.click();
		Thread.sleep(1000);
		tp.selectSortFilterOption(filterOldToNew);
		Verify.waitForObject(tp.buttonSort, 1);
		tp.buttonSort.click();
		Thread.sleep(1000);
		tp.selectSortFilterOption(filterNotReviewed);
		Thread.sleep(1000);
		
		tp.DisableRunningBalance();
		
		firstRowRunningBalance = aa.getTransactionDate(1);
		secondRowRunningBalance= aa.getTransactionDate(2);
		thirdRowRunningBalance= aa.getTransactionDate(3);
		
		if (!(firstRowRunningBalance.contains("$")) & !(secondRowRunningBalance.contains("$")) & !(thirdRowRunningBalance.contains("$"))) {
			Commentary.log(LogStatus.INFO, "PASS: Running balances are displayed");
		} else {
			Commentary.log(sa,LogStatus.FAIL, "FAIL: Running balances are NOT displayed");
		}
		
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
	
	@Test(priority = 10, enabled = false)
	public void RB11_Test() throws Exception {  
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that for filter combination \"Date old to new\" + \"Show Running balance\" set to ON + Not reviewed. Only Not reviewed transaction should appear and also running balance should appear under the transactions.");
		
		String firstRowRunningBalance, secondRowRunningBalance, thirdRowRunningBalance;
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
	
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();
		AllAccountsPage aa = new AllAccountsPage();
		bcc.selectAccount(sSaving);
		Thread.sleep(1000);
		
		Verify.waitForObject(tp.buttonSort, 1);
		tp.buttonSort.click();
		Thread.sleep(1000);
		tp.selectSortFilterOption(filterOldToNew);
		Verify.waitForObject(tp.buttonSort, 1);
		tp.buttonSort.click();
		Thread.sleep(1000);
		tp.selectSortFilterOption(filterNotReviewed);
		
		tp.EnableRunningBalance();
		
		Thread.sleep(1000);
		
		firstRowRunningBalance = aa.getTransactionDate(1);
		secondRowRunningBalance= aa.getTransactionDate(2);
		thirdRowRunningBalance= aa.getTransactionDate(3);
		
		if ((firstRowRunningBalance.contains("$")) & (secondRowRunningBalance.contains("$")) & (thirdRowRunningBalance.contains("$"))) {
			Commentary.log(LogStatus.INFO, "PASS: Running balances are displayed");
		} else {
			Commentary.log(sa,LogStatus.FAIL, "FAIL: Running balances are NOT displayed");
		}
		
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
	
	@Test(priority = 11, enabled = false)
	public void RB12_Test() throws Exception {  
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that for filter combination \"Date old to new\" + Show Running balance OFF + Not reviewed. All Not reviewed transaction should appear in Old to new date order and also running balance should Not appear under the transactions, date should appear");
		
		String firstRowRunningBalance, secondRowRunningBalance, thirdRowRunningBalance;
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
	
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();
		AllAccountsPage aa = new AllAccountsPage();
		bcc.selectAccount(sSaving);
		Thread.sleep(1000);
		
		Verify.waitForObject(tp.buttonSort, 1);
		tp.buttonSort.click();
		Thread.sleep(1000);
		tp.selectSortFilterOption(filterOldToNew);
		Verify.waitForObject(tp.buttonSort, 1);
		tp.buttonSort.click();
		Thread.sleep(1000);
		tp.selectSortFilterOption(filterNotReviewed);
		Thread.sleep(1000);
		
		tp.DisableRunningBalance();
		
		firstRowRunningBalance = aa.getTransactionDate(1);
		secondRowRunningBalance= aa.getTransactionDate(2);
		thirdRowRunningBalance= aa.getTransactionDate(3);
		
		if (!(firstRowRunningBalance.contains("$")) & !(secondRowRunningBalance.contains("$")) & !(thirdRowRunningBalance.contains("$"))) {
			Commentary.log(LogStatus.INFO, "PASS: Running balances are displayed");
		} else {
			Commentary.log(sa,LogStatus.FAIL, "FAIL: Running balances are NOT displayed");
		}
		
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
	
	@Test(priority = 12, enabled = false)
	public void RB13_Test() throws Exception { 
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that for Show Running balance is OFF, any sorting filter should appear with dates under the transactions.");
		
		String firstRowRunningBalance, secondRowRunningBalance, thirdRowRunningBalance;

		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();
		AllAccountsPage aa = new AllAccountsPage();
		bcc.selectAccount(sManualSaving);
		Thread.sleep(1000);
		
		tp.DisableRunningBalance();
		
		Verify.waitForObject(tp.buttonSort, 1);
		tp.buttonSort.click();
		
		//Verify running balance for filter Amount Low to High
		tp.selectSortFilterOption(filterLowToHigh);
		
		firstRowRunningBalance = aa.getTransactionDate(1);
		secondRowRunningBalance= aa.getTransactionDate(2);
		thirdRowRunningBalance= aa.getTransactionDate(3);
		
		if (!(firstRowRunningBalance.contains("$")) & !(secondRowRunningBalance.contains("$")) & !(thirdRowRunningBalance.contains("$"))) {
			Commentary.log(LogStatus.INFO, "PASS: Running Balance set to OFF > Filter Applied > Amount Low to high > Running balance is not displayed.");	
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Running Balance set to OFF > Filter Applied > Amount Low to high > Running balance is displayed.");
		}
		
		//Verify running balance for filter Amount High to Low
		Verify.waitForObject(tp.buttonSort, 1);
		tp.buttonSort.click();
		tp.selectSortFilterOption(filterHighToLow);
		
		firstRowRunningBalance = aa.getTransactionDate(1);
		secondRowRunningBalance= aa.getTransactionDate(2);
		thirdRowRunningBalance= aa.getTransactionDate(3);
				
		if (!(firstRowRunningBalance.contains("$")) & !(secondRowRunningBalance.contains("$")) & !(thirdRowRunningBalance.contains("$"))) {
			Commentary.log(LogStatus.INFO, "PASS: Running Balance set to OFF > Filter Applied > Amount High to Low > Running balance is not displayed.");	
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Running Balance set to OFF > Filter Applied > Amount High to Low > Running balance is displayed.");
		}
				
		//Verify running balance for filter Date New to Old
		Verify.waitForObject(tp.buttonSort, 1);
		tp.buttonSort.click();
		tp.selectSortFilterOption(filterNewToOld);
				
		firstRowRunningBalance = aa.getTransactionDate(1);
		secondRowRunningBalance= aa.getTransactionDate(2);
		thirdRowRunningBalance= aa.getTransactionDate(3);
				
		if (!(firstRowRunningBalance.contains("$")) & !(secondRowRunningBalance.contains("$")) & !(thirdRowRunningBalance.contains("$"))) {
			Commentary.log(LogStatus.INFO, "PASS: Running Balance set to OFF > Filter Applied > Amount New to Old > Running balance is not displayed.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Rnuning Balance set to OFF > Filter Applied > Amount New to Old > Running balance is displayed.");
		}
		
		//Verify running balance for filter Date Old to New
		Verify.waitForObject(tp.buttonSort, 1);
		tp.buttonSort.click();
		tp.selectSortFilterOption(filterOldToNew);
				
		firstRowRunningBalance = aa.getTransactionDate(1);
		secondRowRunningBalance= aa.getTransactionDate(2);
		thirdRowRunningBalance= aa.getTransactionDate(3);
				
		if (!(firstRowRunningBalance.contains("$")) & !(secondRowRunningBalance.contains("$")) & !(thirdRowRunningBalance.contains("$"))) {
			Commentary.log(LogStatus.INFO, "PASS: Running Balance set to OFF > Filter Applied > Amount Old to New > Running balance is not displayed.");	
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Running Balance set to OFF > Filter Applied > Amount Old to New > Running balance is displayed.");
		}
	
		//Verify running balance for filter Pending to Cleared
		Verify.waitForObject(tp.buttonSort, 1);
		tp.buttonSort.click();
		tp.selectSortFilterOption(filterPendingCleared);
		
		firstRowRunningBalance = aa.getTransactionDate(1);
		secondRowRunningBalance= aa.getTransactionDate(2);
		thirdRowRunningBalance= aa.getTransactionDate(3);
				
		if (!(firstRowRunningBalance.contains("$")) & !(secondRowRunningBalance.contains("$")) & !(thirdRowRunningBalance.contains("$"))) {
			Commentary.log(LogStatus.INFO, "PASS: Running Balance set to OFF > Filter Applied > Pending to Cleared > Running balance filter is not displayed.");			
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Running Balance set to OFF > Filter Applied > Pending to Cleared > Running balance filter is displayed.");
		}
		
		sa.assertAll();
	}
	
	@Test(priority = 13, enabled = false)
	public void RB14_Test() throws Exception { 
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify Running balance for filter combination Date new to old + Show Running balance ON + 7/14/30/90 days reminder");
		
		String firstRowRunningBalance, secondRowRunningBalance, thirdRowRunningBalance;

		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();
		AllAccountsPage aa = new AllAccountsPage();
		bcc.selectAccount(sManualSaving);
		Thread.sleep(1000);
		
		Verify.waitForObject(tp.buttonSort, 1);
		tp.buttonSort.click();
		tp.selectSortFilterOption(filterNewToOld);
		
		//Verify running balance for filter to show reminder for 7 days
		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		tp.selectSortFilterOption(filter7days);
		
		tp.EnableRunningBalance();
		
		firstRowRunningBalance = aa.getTransactionDate(1);
		
		if (firstRowRunningBalance.contains("$")) {
			Commentary.log(LogStatus.INFO, "PASS: Running Balance set to ON > Reminder Next 7 days > Date New to Old > Running balance is displayed.");
			
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Running Balance set to ON > Reminder Next 7 days > Date New to Old > Running balance is NOT displayed.");
		}
		
		//Verify running balance for filter to show reminder for 14 days
		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		tp.selectSortFilterOption(filter14days);
		
		firstRowRunningBalance = aa.getTransactionDate(1);
				
		if (firstRowRunningBalance.contains("$")) {
			Commentary.log(LogStatus.INFO, "PASS: Running Balance set to ON > Reminder Next 14 days > Date New to Old > Running balance is displayed.");
			
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Running Balance set to ON > Reminder Next 7 days > Date New to Old > Running balance is NOT displayed..");
		}
				
		//Verify running balance for filter to show reminder for 30 days
		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		tp.selectSortFilterOption(filter30days);
		
		firstRowRunningBalance = aa.getTransactionDate(1);
			
		if (firstRowRunningBalance.contains("$")) {
			Commentary.log(LogStatus.INFO, "PASS: Running Balance set to ON > Reminder Next 30 days > Date New to Old > Running balance is displayed.");
			
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Running Balance set to ON > Reminder Next 30 days > Date New to Old > Running balance is NOT displayed");
		}
		
		//Verify running balance for filter to show reminder for 90 days
		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		tp.selectSortFilterOption(filter90days);
		
		firstRowRunningBalance = aa.getTransactionDate(1);
				
		if (firstRowRunningBalance.contains("$")) {
			Commentary.log(LogStatus.INFO, "PASS: Running Balance set to ON > Reminder Next 90 days > Date New to Old > Running balance is displayed.");
			
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Running Balance set to ON > Reminder Next 90 days > Date New to Old > Running balance is NOT displayed.");
		}
		
		//Verify running balance for filter to don't show reminders
		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		tp.selectSortFilterOption(filterDontShowReminder);
		
		firstRowRunningBalance = aa.getTransactionDate(1);
		secondRowRunningBalance = aa.getTransactionDate(2);
		thirdRowRunningBalance = aa.getTransactionDate(3);
		
		if (firstRowRunningBalance.contains("$") & secondRowRunningBalance.contains("$") & thirdRowRunningBalance.contains("$")) {
			Commentary.log(LogStatus.INFO, "PASS: Running Balance set to ON > Don't show reminders > Date New to Old > Running balance is displayed.");
			
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Running Balance set to ON > Don't show reminders > Date New to Old > Running balance is NOT displayed.");
		}
		
		sa.assertAll();
	}
	
	@Test(priority = 14, enabled = false)
	public void RB15_Test() throws Exception { 

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify Running balance for filter combination Date new to old + Show Running balance OFF + 7/14/30/90 days reminder");
		
		String firstRowRunningBalance, secondRowRunningBalance, thirdRowRunningBalance;
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();
		AllAccountsPage aa = new AllAccountsPage();
		bcc.selectAccount(sManualSaving);
		Thread.sleep(1000);
		
		tp.buttonSort.click();
		tp.selectSortFilterOption(filterNewToOld);
		
		tp.DisableRunningBalance();
		
		//Verify running balance for filter to show reminder for 7 days
		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		tp.selectSortFilterOption(filter7days);
		
		firstRowRunningBalance = aa.getTransactionDate(1);
		
		if (!firstRowRunningBalance.contains("$")) {
			Commentary.log(LogStatus.INFO, "PASS: Running Balance set to OFF > Reminder Next 7 days > Date New to Old > Running balance is not displayed.");	
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Running Balance set to OFF > Reminder Next 7 days > Date New to Old > Running balance is displayed.");
		}
		
		//Verify running balance for filter to show reminder for 14 days
		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		tp.selectSortFilterOption(filter14days);
		
		firstRowRunningBalance = aa.getTransactionDate(1);
				
		if (!firstRowRunningBalance.contains("$")) {
			Commentary.log(LogStatus.INFO, "PASS: Running Balance set to OFF > Reminder Next 14 days > Date New to Old > Running balance is not displayed.");	
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Running Balance set to OFF > Reminder Next 7 days > Date New to Old > Running balance is displayed.");
		}
				
		//Verify running balance for filter to show reminder for 30 days
		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		tp.selectSortFilterOption(filter30days);
		
		firstRowRunningBalance = aa.getTransactionDate(1);
				
		if (!firstRowRunningBalance.contains("$")) {
			Commentary.log(LogStatus.INFO, "PASS: Running Balance set to OFF > Reminder Next 30 days > Date New to Old > Running balance is not displayed.");	
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Running Balance set to OFF > Reminder Next 30 days > Date New to Old > Running balance is displayed.");
		}
		
		//Verify running balance for filter to show reminder for 90 days
		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		tp.selectSortFilterOption(filter90days);
		
		firstRowRunningBalance = aa.getTransactionDate(1);
				
		if (!firstRowRunningBalance.contains("$")) {
			Commentary.log(LogStatus.INFO, "PASS: Running Balance set to OFF > Reminder Next 90 days > Date New to Old > Running balance is not displayed.");	
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Running Balance set to OFF > Reminder Next 90 days > Date New to Old > Running balance is displayed.");
		}
		
		//Verify running balance for filter to Don't show reminders
		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		tp.selectSortFilterOption(filterDontShowReminder);
				
		firstRowRunningBalance = aa.getTransactionDate(1);
		secondRowRunningBalance = aa.getTransactionDate(2);
		thirdRowRunningBalance = aa.getTransactionDate(3);
				
		if (!firstRowRunningBalance.contains("$") & !secondRowRunningBalance.contains("$") & !thirdRowRunningBalance.contains("$")) {
			Commentary.log(LogStatus.INFO, "PASS: Running Balance set to OFF > Don't show reminders > Date New to Old > Running balance is not displayed.");			
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Running Balance set to OFF > Don't show reminders > Date New to Old > Running balance is displayed.");
		}
		sa.assertAll();
	}
	
	@Test(priority = 15, enabled = false)
	public void RB16_Test() throws Exception { 
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that Setting \"Pending to cleared\" from \"date new to old\" while running balance is turn ON, Should not show running balance, it should show dates under the transaction.");
		
		String firstRowRunningBalance, secondRowRunningBalance, thirdRowRunningBalance;

		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();
		AllAccountsPage aa = new AllAccountsPage();
		bcc.selectAccount(sManualSaving);
		Thread.sleep(1000);
				
		//Verify running balance for filter Date New to Old
		Verify.waitForObject(tp.buttonSort, 1);
		tp.buttonSort.click();
		tp.selectSortFilterOption(filterNewToOld);
		
		tp.EnableRunningBalance();
				
		firstRowRunningBalance = aa.getTransactionDate(1);
		secondRowRunningBalance= aa.getTransactionDate(2);
		thirdRowRunningBalance= aa.getTransactionDate(3);
				
		if (firstRowRunningBalance.contains("$") & secondRowRunningBalance.contains("$") & thirdRowRunningBalance.contains("$")) {
			Commentary.log(LogStatus.INFO, "PASS: Running Balance set to ON > Filter Applied > Amount New to Old > Running balance is displayed");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Running Balance set to ON > Filter Applied > Amount New to Old > Running balance is NOT displayed");
		}
		
		//Verify running balance for filter Pending to Cleared
		Verify.waitForObject(tp.buttonSort, 1);
		tp.buttonSort.click();
		tp.selectSortFilterOption(filterPendingCleared);
		
		firstRowRunningBalance = aa.getTransactionDate(1);
		secondRowRunningBalance= aa.getTransactionDate(2);
		thirdRowRunningBalance= aa.getTransactionDate(3);
				
		if (!firstRowRunningBalance.contains("$") & !secondRowRunningBalance.contains("$") & !thirdRowRunningBalance.contains("$")) {
			Commentary.log(LogStatus.INFO, "PASS: Filter Applied > Pending to Cleared > Running balance is not displayed");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Filter Applied > Pending to Cleared > Running balance is displayed");
		}
		sa.assertAll();
	}
	
	@Test(priority = 16, enabled = false)
	public void RB17_Test() throws Exception { 

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify display for Running Balance in case - running balance Apply + show 90 days reminder apply AND set combination is date new to Old + dont show reminder");
		
		String firstRowRunningBalance, secondRowRunningBalance, thirdRowRunningBalance;
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();
		AllAccountsPage aa = new AllAccountsPage();
		bcc.selectAccount(sManualSaving);
		Thread.sleep(1000);
		
		//Verify running balance for filter Date New to Old
		Verify.waitForObject(tp.buttonSort, 1);
		tp.buttonSort.click();
		tp.selectSortFilterOption(filterNewToOld);
		
		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		tp.selectSortFilterOption(filter90days);
		
		//tp.buttonShowReminder.click();
		tp.EnableRunningBalance();
//		if (tp.isRunningBalanceEnabled()) {
//			System.out.println("Running Balance is enabled");
//			tp.buttonApply.click();
//			Thread.sleep(1000);
//		} else {
//			//Enable Running Balance
//			tp.EnableRunningBalance();
//		}
				
		firstRowRunningBalance = aa.getTransactionDate(1);
		secondRowRunningBalance= aa.getTransactionDate(2);
		thirdRowRunningBalance= aa.getTransactionDate(3);
				
		if (firstRowRunningBalance.contains("$") & secondRowRunningBalance.contains("$") & thirdRowRunningBalance.contains("$")) {
			Commentary.log(LogStatus.INFO, "PASS: Running Balance set to ON > Filter Applied > Amount New to Old + Show 90 days reminder > Running balance is displayed");	
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Running Balance set to ON > Filter Applied > Amount New to Old + Show 90 days reminder > Running balance is NOT displayed");
		}
		
		//Verify running balance after applying filter to don't show reminder
		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();
		tp.selectSortFilterOption(filterDontShowReminder);
		
		firstRowRunningBalance = aa.getTransactionDate(1);
		secondRowRunningBalance= aa.getTransactionDate(2);
		thirdRowRunningBalance= aa.getTransactionDate(3);
				
		if (firstRowRunningBalance.contains("$") & secondRowRunningBalance.contains("$") & thirdRowRunningBalance.contains("$")) {
			Commentary.log(LogStatus.INFO, "PASS: Filter Applied > Don't show reminder > Running balance is displayed");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Filter Applied > Don't show reminder > Running balance filter is NOT displayed");
		}
		sa.assertAll();
	}
	
	@Test(priority = 17, enabled = false)
	public void RB18_Test() throws Exception { 

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that if running balance is visible in transaction list then on the left hand side date should be visible under Month label.");
		
		String firstTransactionDate, dateLabel;
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();
		AllAccountsPage aa = new AllAccountsPage();
		bcc.selectAccount(sManualSaving);
		Thread.sleep(1000);
		
		tp.DisableRunningBalance();
		
		firstTransactionDate = aa.getTransactionDate(1);
		
		tp.EnableRunningBalance();
		
		dateLabel = tp.firstBannerTransactionDate.getText();
		
		if (firstTransactionDate.equals(dateLabel)) {
			Commentary.log(LogStatus.INFO, "PASS: Date label is displayed when running balance is enabled");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Date label is NOT displayed when running balance is enabled");
		}
		sa.assertAll();
	}
	
	@Test(priority = 18, enabled = false)
	public void RB19_Test() throws Exception { 

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that if running balance is visible in transaction list then on the left hand side date should be visible under Month label.");
		
		String firstTransactionDate, dateLabel;
		
		String payeeName = "Payee_"+h.getCurrentTime();
		
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("5.00");
		tRec.setAccount(sManualSaving);
		tRec.setCategory("Internet");
		tRec.setPayee(payeeName);
		tRec.setTransactionType("expense");
		//tRec.setDate(h.getFutureDaysDate(2));
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();
		bcc.selectAccount(sManualSaving);
		Thread.sleep(1000);
		
		tp.addTransaction.click();
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount "+tRec.getAmount());
		
		tp.searchRecentTransaction(payeeName);
		
		tp.DisableRunningBalance();
		tp.tapOnFirstTransaction();
		Verify.waitForObject(td.category, 2);
		
		firstTransactionDate = td.getTransactionDate();
		firstTransactionDate = h.convertDateFormat(firstTransactionDate);
		
		td.backButton.click(); 
		Thread.sleep(1000);
		
		tp.EnableRunningBalance();
		
		dateLabel = tp.firstBannerTransactionDate.getText();
		// Verify for today's date transaction
		if (firstTransactionDate.equals(dateLabel)) {
			Commentary.log(LogStatus.INFO, "PASS: Current date Transaction is displayed under current date.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Current date Transaction is NOT displayed under current date.");
		}
		
		tp.DisableRunningBalance();
		tp.tapOnFirstTransaction();
		
		// Edit Transaction date to past date
		tRec = new TransactionRecord();
		tRec.setDate(h.getPastDaysDate(2));
		td.editTransaction(tRec);

		tp.tapOnFirstTransaction();
		
		firstTransactionDate = td.getTransactionDate();
		firstTransactionDate = h.convertDateFormat(firstTransactionDate);
		
		td.backButton.click(); 
		Thread.sleep(1000);
		
		tp.EnableRunningBalance();
		
		dateLabel = tp.firstBannerTransactionDate.getText();
		// Verify for past date transaction
		if (firstTransactionDate.equals(dateLabel)) {
			Commentary.log(LogStatus.INFO, "PASS: Past Dated Transaction is displayed under past date.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Past Dated Transaction is NOT displayed under past date.");
		}
		
		tp.DisableRunningBalance();
		tp.tapOnFirstTransaction();
		
		// Edit Transaction date to future date
		tRec = new TransactionRecord();
		tRec.setDate(h.getFutureDaysDate(2));
		td.editTransaction(tRec);
		
		tp.tapOnFirstTransaction();
		
		firstTransactionDate = td.getTransactionDate();
		firstTransactionDate = h.convertDateFormat(firstTransactionDate);
		
		td.backButton.click(); 
		Thread.sleep(1000);
		
		tp.EnableRunningBalance();
		
		dateLabel = tp.firstBannerTransactionDate.getText();
		// Verify for future date transaction
		if (firstTransactionDate.equals(dateLabel)) {
			Commentary.log(LogStatus.INFO, "PASS: Future dated Transaction is displayed under future date.");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Future dated Transaction is NOT displayed under future date.");
		}		
		sa.assertAll();
	}
	
	@Test(priority = 19, enabled = false)
	public void RB20_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify date format after enabling and disabling running balance flag");
		
		String firstTransactionDate, dateLabel ;
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();
		AllAccountsPage aa = new AllAccountsPage();
		bcc.selectAccount(sManualSaving);
		Thread.sleep(1000);
		
		tp.EnableRunningBalance();
		
		//firstTransactionDate = aa.getTransactionDate(1);
		dateLabel = tp.firstBannerTransactionDate.getText();
		
		// Verify date format when running balance is enabled
		if (h.isValidDateFormat(dateLabel,"MMM dd, YYYY")) {
			Commentary.log(LogStatus.INFO, "PASS: Running Balance is enabled > Expected date format MMM dd, YYYY and actual date is ["+dateLabel+"]");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Running Balance is enabled > Expected date format MMM dd, YYYY and actual date is ["+dateLabel+"]");
		}
		
		tp.DisableRunningBalance();
		
		firstTransactionDate = aa.getTransactionDate(1);
		
		// Verify date format when running balance is disabled
		if (h.isValidDateFormat(firstTransactionDate,"MMM dd, YYYY")|| firstTransactionDate.equals("Tomorrow")|| firstTransactionDate.equals("Yesterday")|| firstTransactionDate.equals("Today")) {
			Commentary.log(LogStatus.INFO, "PASS: Running Balance is disabled > Expected date format MMM dd, YYYY and actual date is ["+firstTransactionDate+"]");
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Running Balance is disabled > Expected date format MMM dd, YYYY and actual date is ["+firstTransactionDate+"]");
		}
		sa.assertAll();
	}
	
	@Test(priority = 20, enabled = false)
	public void RB21_Test() throws Exception {
		 
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify the running balance when amount of the transactions is modified.");
		
		Double dFirstRunningBalance, dSecondRunningBalance, dThirdRunningBalance, dFourthRunningBalance, dFirstTxnAmount, dSecondTxnAmount, dThirdTxnAmount ;

		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();
		AllAccountsPage aa = new AllAccountsPage();
		bcc.selectAccount(sManualSaving);
		Thread.sleep(1000);
		
		Verify.waitForObject(tp.buttonSort, 1);
		tp.buttonSort.click();
		Thread.sleep(1000);
		tp.selectSortFilterOption(filterNewToOld);
		tp.EnableRunningBalance();
//		if (tp.isRunningBalanceEnabled()) {
//			Commentary.log(LogStatus.INFO, "Running balance is enabled by default");
//			tp.buttonApply.click();
//			Thread.sleep(1000);
//			
//		} else {
//			Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is NOT enabled by default");
//		}
		
		dFirstRunningBalance= aa.getRunningBalancefromTransaction(1);
		dFirstTxnAmount = aa.getTransactionAmount(1);
		//Double iFirstRunningBalance = Math.abs(dFirstRunningBalance);
		Double iFirstTxnAmount = Math.abs(dFirstTxnAmount);
		
		dSecondRunningBalance= aa.getRunningBalancefromTransaction(2);
		dSecondTxnAmount = aa.getTransactionAmount(2);
		//Double iSecondRunningBalance = Math.abs(dSecondRunningBalance);
		Double iSecondTxnAmount = Math.abs(dSecondTxnAmount);
		
		dThirdRunningBalance= aa.getRunningBalancefromTransaction(3);
		dThirdTxnAmount = aa.getTransactionAmount(3);
		//Double iThirdRunningBalance = Math.abs(dThirdRunningBalance);
		Double iThirdTxnAmount = Math.abs(dThirdTxnAmount);
		
		dFourthRunningBalance= aa.getRunningBalancefromTransaction(4);
		//Double iFourthRunningBalance = Math.abs(dFourthRunningBalance);
		
		if (dThirdTxnAmount<0) {
			if (dFourthRunningBalance-iThirdTxnAmount==dThirdRunningBalance) {
				Commentary.log(LogStatus.INFO, "PASS: Running balance is ["+dFourthRunningBalance+"] and transaction amount is ["+dThirdTxnAmount+"], calculated Running balance is ["+dThirdRunningBalance+"]");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is ["+dFourthRunningBalance+"] and transaction amount is ["+dThirdTxnAmount+"], calculated Running balance is ["+dThirdRunningBalance+"]");
			}
		}
		else {
			if (dFourthRunningBalance+iThirdTxnAmount==dThirdRunningBalance) {
				Commentary.log(LogStatus.INFO, "PASS: Running balance is ["+dFourthRunningBalance+"] and transaction amount is ["+dThirdTxnAmount+"], calculated Running balance is ["+dThirdRunningBalance+"]");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is ["+dFourthRunningBalance+"] and transaction amount is ["+dThirdTxnAmount+"], calculated Running balance is ["+dThirdRunningBalance+"]");
			}
		}
		
		if (dSecondTxnAmount<0) {
			if (dThirdRunningBalance-iSecondTxnAmount==dSecondRunningBalance) {
				Commentary.log(LogStatus.INFO, "PASS: Running balance is ["+dThirdRunningBalance+"] and transaction amount is ["+dSecondTxnAmount+"], calculated Running balance is ["+dSecondRunningBalance+"]");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is ["+dThirdRunningBalance+"] and transaction amount is ["+dSecondTxnAmount+"], calculated Running balance is ["+dSecondRunningBalance+"]");
			}
		}
		else {
			if (dThirdRunningBalance+iSecondTxnAmount==dSecondRunningBalance) {
				Commentary.log(LogStatus.INFO, "PASS: Running balance is ["+dThirdRunningBalance+"] and transaction amount is ["+dSecondTxnAmount+"], calculated Running balance is ["+dSecondRunningBalance+"]");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is ["+dThirdRunningBalance+"] and transaction amount is ["+dSecondTxnAmount+"], calculated Running balance is ["+dSecondRunningBalance+"]");
			}
		}
		
		if (dFirstTxnAmount<0) {
			if (dSecondRunningBalance-iFirstTxnAmount==dFirstRunningBalance) {
				Commentary.log(LogStatus.INFO, "PASS: Running balance is ["+dSecondRunningBalance+"] and transaction amount is ["+dFirstTxnAmount+"], calculated Running balance is ["+dFirstRunningBalance+"]");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is ["+dSecondRunningBalance+"] and transaction amount is ["+dFirstTxnAmount+"], calculated Running balance is ["+dFirstRunningBalance+"]");
			}
		}
		else {
			if (dSecondRunningBalance+iFirstTxnAmount==dFirstRunningBalance) {
				Commentary.log(LogStatus.INFO, "PASS: Running balance is ["+dSecondRunningBalance+"] and transaction amount is ["+dFirstTxnAmount+"], calculated Running balance is ["+dFirstRunningBalance+"]");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is ["+dSecondRunningBalance+"] and transaction amount is ["+dFirstTxnAmount+"], calculated Running balance is ["+dFirstRunningBalance+"]");
			}
		}
		
		tp.DisableRunningBalance();
		tp.tapOnFirstTransaction();
		
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		
		tRec.setAmount("55.00");
		if (td.getTransactionAmount().contains("-$")) {
			tRec.setTransactionType("income");
		} else {
			tRec.setTransactionType("expense");
		}
		
		td.editTransaction(tRec);
		
		tp.EnableRunningBalance();
		
		dFirstRunningBalance= aa.getRunningBalancefromTransaction(1);
		dFirstTxnAmount = aa.getTransactionAmount(1);
		//iFirstRunningBalance = Math.abs(dFirstRunningBalance);
		iFirstTxnAmount = Math.abs(dFirstTxnAmount);
		
		dSecondRunningBalance= aa.getRunningBalancefromTransaction(2);
		dSecondTxnAmount = aa.getTransactionAmount(2);
		//iSecondRunningBalance = Math.abs(dSecondRunningBalance);
		iSecondTxnAmount = Math.abs(dSecondTxnAmount);
		
		dThirdRunningBalance= aa.getRunningBalancefromTransaction(3);
		dThirdTxnAmount = aa.getTransactionAmount(3);
		//iThirdRunningBalance = Math.abs(dThirdRunningBalance);
		iThirdTxnAmount = Math.abs(dThirdTxnAmount);
		
		dFourthRunningBalance= aa.getRunningBalancefromTransaction(4);
		//iFourthRunningBalance = Math.abs(dFourthRunningBalance);
		
		if (dThirdTxnAmount<0) {
			if (dFourthRunningBalance-iThirdTxnAmount==dThirdRunningBalance) {
				Commentary.log(LogStatus.INFO, "PASS: Running balance is ["+dFourthRunningBalance+"] and transaction amount is ["+dThirdTxnAmount+"], calculated Running balance is ["+dThirdRunningBalance+"]");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is ["+dFourthRunningBalance+"] and transaction amount is ["+dThirdTxnAmount+"], calculated Running balance is ["+dThirdRunningBalance+"]");
			}
		}
		else {
			if (dFourthRunningBalance+iThirdTxnAmount==dThirdRunningBalance) {
				Commentary.log(LogStatus.INFO, "PASS: Running balance is ["+dFourthRunningBalance+"] and transaction amount is ["+dThirdTxnAmount+"], calculated Running balance is ["+dThirdRunningBalance+"]");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is ["+dFourthRunningBalance+"] and transaction amount is ["+dThirdTxnAmount+"], calculated Running balance is ["+dThirdRunningBalance+"]");
			}
		}
		
		if (dSecondTxnAmount<0) {
			if (dThirdRunningBalance-iSecondTxnAmount==dSecondRunningBalance) {
				Commentary.log(LogStatus.INFO, "PASS: Running balance is ["+dThirdRunningBalance+"] and transaction amount is ["+dSecondTxnAmount+"], calculated Running balance is ["+dSecondRunningBalance+"]");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is ["+dThirdRunningBalance+"] and transaction amount is ["+dSecondTxnAmount+"], calculated Running balance is ["+dSecondRunningBalance+"]");
			}
		}
		else {
			if (dThirdRunningBalance+iSecondTxnAmount==dSecondRunningBalance) {
				Commentary.log(LogStatus.INFO, "PASS: Running balance is ["+dThirdRunningBalance+"] and transaction amount is ["+dSecondTxnAmount+"], calculated Running balance is ["+dSecondRunningBalance+"]");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is ["+dThirdRunningBalance+"] and transaction amount is ["+dSecondTxnAmount+"], calculated Running balance is ["+dSecondRunningBalance+"]");
			}
		}
		
		if (dFirstTxnAmount<0) {
			if (dSecondRunningBalance-iFirstTxnAmount==dFirstRunningBalance) {
				Commentary.log(LogStatus.INFO, "PASS: Running balance is ["+dSecondRunningBalance+"] and transaction amount is ["+dFirstTxnAmount+"], calculated Running balance is ["+dFirstRunningBalance+"]");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is ["+dSecondRunningBalance+"] and transaction amount is ["+dFirstTxnAmount+"], calculated Running balance is ["+dFirstRunningBalance+"]");
			}
		}
		else {
			if (dSecondRunningBalance+iFirstTxnAmount==dFirstRunningBalance) {
				Commentary.log(LogStatus.INFO, "PASS: Running balance is ["+dSecondRunningBalance+"] and transaction amount is ["+dFirstTxnAmount+"], calculated Running balance is ["+dFirstRunningBalance+"]");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is ["+dSecondRunningBalance+"] and transaction amount is ["+dFirstTxnAmount+"], calculated Running balance is ["+dFirstRunningBalance+"]");
			}
		}
		sa.assertAll();
	
	}
}
