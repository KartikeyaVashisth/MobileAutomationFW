package tournament;

import support.Recovery;

import java.math.BigDecimal;
import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.AllAccountsPage;
import dugout.BankingAndCreditCardPage;
import dugout.InvestingPage;
import dugout.NetIncomeOverTimePage;
import dugout.OverviewPage;
import dugout.SettingsPage;
import dugout.SignInPage;
import dugout.SpendingOverTimePage;
import dugout.SpendingTrendPage;
import dugout.TransactionDetailPage;
import dugout.TransactionSummaryPage;
import dugout.TransactionsPage;
import dugout.WelcomePage;
import io.appium.java_client.MobileElement;
import referee.Commentary;
import referee.Verify;
import support.Engine;
import support.Helper;
import support.Recovery;
import support.Recovery;
import support.TransactionRecord;
import support.UserName;

public class Daily_Regression_Test extends Recovery {
	//String sUserName = "yuvaraju.boligorla@quicken.com";
	String sPassword = "Intuit!1";
	String sDataset = "ProjectedBalances";
	String sPassword_stage = "Quicken@01";
	String sDataset_stage = "CompanionRegressionSuite";
	String sManualChecking = "Manual_Checking";
	String sOnlineChecking ="onl_checking1";
	String sManualCreditCard = "Manual_CC";
	String sOnlineCreditCard ="onl_cc";
	String sManualCash="Manual_cash";
	String sOnlineCash="onl_cash";
	String sManualSaving = "Manual_Savings";
	String sOnlineSaving = "onl_savings1";
	String backButton1_ios = "Banking & Credit, back";
	String statusCleared = "Cleared";
	String statusUnCleared = "Uncleared";
	String filterNewToOld = "Date New to Old";
	String filterOldToNew = "Date Old to New";

	public String getUsername_basedOnEnv() throws Exception{

		UserName un = new UserName();
		un.stage_ios = "companionregression++@stage.com";
		un.stage_android = "companionregression++@stage.com";
		un.prod_ios = "yuvaraju.boligorla@quicken.com";
		un.prod_android = "yuvaraju.boligorla@quicken.com";
		return un.getUserName();	
	}

	@Test(priority = 0, enabled = true)
	public void TC1_ValidateAddTransaction() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		String sUsername = getUsername_basedOnEnv();
		WelcomePage w = new WelcomePage();
		w.setEnvironment(h.getEnv());

		SignInPage si = new SignInPage();
		if(h.getEnv().contentEquals("stage"))
			si.signIn(sUsername, sPassword_stage, sDataset_stage);
		else
			si.signIn(sUsername, sPassword, sDataset);

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Add an expense transaction for an manual checking account, verify checking & total balance on overview screen accounts card");

		String sChecking_before, sTotal_before, sChecking_after,sTotal_after;
		String payeeName = "Payee_"+h.getCurrentTime();

		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		//td.allowButton.click(); 
		tRec.setAmount("5.00"); 
		tRec.setAccount(sManualChecking);
		tRec.setCategory("Internet");
		tRec.setPayee(payeeName);
		tRec.setTransactionType("expense");
		tRec.setDate(h.getFutureDaysDate(0));

		OverviewPage op = new OverviewPage();
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 1);
		sChecking_before = op.checkingBalance.getText();
		sTotal_before = op.totalBalance.getText();
		Commentary.log(LogStatus.INFO, "Checking balance before adding the transaction ["+sChecking_before+"]");
		Commentary.log(LogStatus.INFO, "Total balance before adding the transaction ["+sTotal_before+"]");
//		Double d = Double.parseDouble(tRec.getAmount());
		
//		Double dChecking_before = h.processBalanceAmount(sChecking_before);
//		Double dTotal_before = h.processBalanceAmount(sTotal_before);
		String processedChecking_before = sChecking_before.replaceAll("[^0-9.-]", "");
		String processedTotal_before = sTotal_before.replaceAll("[^0-9.-]", "");
		
		BigDecimal checking_before = new BigDecimal(processedChecking_before);
		BigDecimal total_before = new BigDecimal(processedTotal_before);
		BigDecimal diff = new BigDecimal(tRec.getAmount());

		op.addTransaction.click();
		Thread.sleep(1000);
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount "+tRec.getAmount());

		sChecking_after = op.checkingBalance.getText();
		sTotal_after = op.totalBalance.getText();
		String processedChecking_after = sChecking_after.replaceAll("[^0-9.-]", "");
		String processedTotal_after = sTotal_after.replaceAll("[^0-9.-]", "");
//		Double dChecking_after = h.processBalanceAmount(sChecking_after);
//		Double dTotal_after = h.processBalanceAmount(sTotal_after);

		BigDecimal checking_after = new BigDecimal(processedChecking_after);
		BigDecimal total_after = new BigDecimal(processedTotal_after);
		
//		if (dChecking_before-d==dChecking_after)
		if((checking_before.subtract(diff)).equals(checking_after))
			Commentary.log(LogStatus.INFO, "PASS: Checking balance was ["+checking_before+"], added expense transaction for ["+tRec.getAmount()+"], now the checking balance shows ["+checking_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Checking balance was ["+checking_before+"], added expense transaction for ["+tRec.getAmount()+"], now the checking balance shows ["+checking_after+"]");

//		if (dTotal_before-d==dTotal_after)
		if((total_before.subtract(diff)).equals(total_after))
			Commentary.log(LogStatus.INFO, "PASS: Total balance was ["+total_before+"], added expense transaction for ["+tRec.getAmount()+"], now the total balance shows ["+total_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Total balance was ["+total_before+"], added expense transaction for ["+tRec.getAmount()+"], now the total balance shows ["+total_after+"]");

		sa.assertAll();	
	}

	// Edit Transaction
	@Test(priority=1, enabled = true)
	public void TC2_ValidateEditTransaction() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: EDITING an expense transaction for an manual checking account, verify checking & total balance on overview screen accounts card");

		String sChecking_before, sTotal_before, sChecking_after,sTotal_after ;
		String payeeName = "Payee_"+h.getCurrentTime();

		OverviewPage op = new OverviewPage();
		sChecking_before = op.checkingBalance.getText();
		sTotal_before = op.totalBalance.getText();
		Commentary.log(LogStatus.INFO, "Checking balance before adding and editing the transaction ["+sChecking_before+"]");
		Commentary.log(LogStatus.INFO, "Total balance before adding and editing the transaction ["+sTotal_before+"]");
		
		op.navigateToAcctList();

		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		Verify.waitForObject(bcc.allTransactionButton, 1);
		bcc.allTransactionButton.click();

		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("5.00"); 
		tRec.setAccount(sManualChecking);
		tRec.setCategory("Internet");
		tRec.setPayee(payeeName);
		tRec.setTransactionType("expense");
		tRec.setDate(h.getFutureDaysDate(0));

		TransactionsPage tp = new TransactionsPage();
		TransactionDetailPage td = new TransactionDetailPage();

		Verify.waitForObject(tp.addTransaction, 2);
		tp.addTransaction.click();
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);

		tp.searchRecentTransaction(payeeName);
		tp.tapOnFirstTransaction();	
		Verify.waitForObject(td.category, 2);
		
		if(h.getEngine().equals("android")) {
			Double txnAmount_before = h.processBalanceAmount(td.getTransactionAmount());
			Commentary.log(LogStatus.INFO, "Original transaction amount: "+txnAmount_before+"");
		} else {
			Double txnAmount_before = h.processBalanceAmount(td.getTransactionAmount().replace("Amount: ", ""));
			Commentary.log(LogStatus.INFO, "Original transaction amount: "+txnAmount_before+"");
		}

		tRec.setAmount("10.00");
		tRec.setAccount(sManualChecking);
		tRec.setCategory("Hotel");
		tRec.setPayee("shop");
		tRec.setTransactionType("expense");
		tRec.setDate(h.getYesterdaysDate());

		td.editTransaction(tRec);

//		Double d = Double.parseDouble(tRec.getAmount()); //value of d = 10
//		Double dChecking_before = h.processBalanceAmount(sChecking_before);
//		Double dTotal_before = h.processBalanceAmount(sTotal_before);

		String processedChecking_before = sChecking_before.replaceAll("[^0-9.-]", "");
		String processedTotal_before = sTotal_before.replaceAll("[^0-9.-]", "");
		
		BigDecimal checking_before = new BigDecimal(processedChecking_before);
		BigDecimal total_before = new BigDecimal(processedTotal_before);

		BigDecimal diff = new BigDecimal(tRec.getAmount());
		
		//Double d_After = d+txnAmount_before; //value of d_after = 10+(-5) = 5

		Commentary.log(LogStatus.INFO, "Transaction edited successfully for the account ["+tRec.getAccount()+"], transaction type Expense, amount "+ tRec.getAmount());

		SettingsPage sp = new SettingsPage();

		sp.selectBack(backButton1_ios);
		Thread.sleep(2000);
		sp.selectBack("Go back");
		Thread.sleep(5000);

		Verify.waitForObject(op.totalBalance, 1);
		sChecking_after = op.checkingBalance.getText();
		sTotal_after = op.totalBalance.getText();
//		Double dChecking_after = h.processBalanceAmount(sChecking_after);
//		Double dTotal_after = h.processBalanceAmount(sTotal_after);
		
		String processedChecking_after = sChecking_after.replaceAll("[^0-9.-]", "");
		String processedTotal_after = sTotal_after.replaceAll("[^0-9.-]", "");
		
		BigDecimal checking_after = new BigDecimal(processedChecking_after);
		BigDecimal total_after = new BigDecimal(processedTotal_after);

		Commentary.log(LogStatus.INFO, "CHECKING BEFORE: "+checking_before);
		Commentary.log(LogStatus.INFO, "CHECKING AFTER: "+checking_after);

//		if (dChecking_before-d==dChecking_after)
		if((checking_before.subtract(diff)).equals(checking_after))
			Commentary.log(LogStatus.INFO, "PASS: Checking balance was ["+checking_before+"], edited expense transaction for ["+tRec.getAmount()+"], now the checking balance shows ["+checking_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Checking balance was ["+checking_before+"], edited expense transaction for ["+tRec.getAmount()+"], now the checking balance shows ["+checking_after+"]");

//		if (dTotal_before-d==dTotal_after)
		if((total_before.subtract(diff)).equals(total_after))
			Commentary.log(LogStatus.INFO, "PASS: Total balance was ["+total_before+"], edited expense transaction for ["+tRec.getAmount()+"], now the total balance shows ["+total_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Total balance was ["+total_before+"], edited expense transaction for ["+tRec.getAmount()+"], now the total balance shows ["+total_after+"]");

		sa.assertAll();
	}

	//Verify Delete Transaction
	@Test(priority = 2, enabled = true)
	public void TC3_ValidateDeleteTransaction() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Delete an expense transaction for an manual savings account, verify checking & total balance on overview screen accounts card");

		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();

		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		Verify.waitForObject(bcc.allTransactionButton, 1);
		bcc.allTransactionButton.click();

		TransactionsPage tp = new TransactionsPage();
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		String payeeName = "Payee_"+h.getCurrentTime();

		tRec.setAmount("5.00");
		tRec.setAccount(sManualChecking);
		tRec.setCategory("Internet");
		tRec.setPayee(payeeName);
		tRec.setTransactionType("expense");

		Verify.waitForObject(tp.addTransaction, 2);
		tp.addTransaction.click();
		
		td.addTransaction(tRec);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);

		tp.searchRecentTransaction(payeeName);
		tp.tapOnFirstTransaction();

		Verify.waitForObject(td.category, 2);
		op.scroll_down();
		Verify.waitForObject(td.deleteTransaction, 2);
		td.deleteTransaction.click();
		Thread.sleep(1500);
		if (Verify.objExists_check(td.deleteTransactionAlertButton)) {
			td.deleteTransactionAlertButton.click();
		}
		Thread.sleep(5000);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		//tp.searchRecentTransaction(payeeName); //Commenting out to speed up the execution.

		if (Verify.objExists(tp.txtNoResultFound))
			Commentary.log(LogStatus.INFO, "PASS: Successfully Deleted the selected transaction");
		else
			Commentary.log(sa, LogStatus.FAIL, "Unable to Delete the selected transaction");

		sa.assertAll();		
	}	

	@Test (priority = 3, enabled = true)
	public void TC4_VerifyTransactionSummary() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating Transaction Summary details");

		OverviewPage op = new OverviewPage();
		op.tapOnTransactionSummaryCard();

		TransactionSummaryPage ts = new TransactionSummaryPage();
		Verify.waitForObject(ts.backButtonOnHeader, 2);
		Verify.waitForObject(ts.categoryTab, 2);

		if (Verify.objExists(ts.backButtonOnHeader))
			Commentary.log(LogStatus.INFO, "PASS: Back button is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Back button is not displayed");

		if (Verify.objExists(ts.monthlySummaryHeader))
			Commentary.log(LogStatus.INFO, "PASS: Monthly Summary header is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Monthly Summary header is not displayed");

		if (Verify.objExists(ts.categoryTab))
			Commentary.log(LogStatus.INFO, "PASS: Category button is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Category button is not displayed");

		if (Verify.objExists(ts.payeeTab))
			Commentary.log(LogStatus.INFO, "PASS: Payee button is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Payee button is not displayed");

		if (Verify.objExists(ts.getCurrentMonthYear()))
			Commentary.log(LogStatus.INFO, "PASS: Current month and year text is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Current month and year text is Not displayed");

		sa.assertAll();
	}

	@Test(priority=4, enabled = true)
	public void TC5_VerifyFilterForSpendingTrendCard() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify filter chips on trending screen should appear and selecting it reflects the category");

		//get balances from accounts card
		OverviewPage op = new OverviewPage();
		op.tapOnTrendingCard();

		SpendingTrendPage st = new SpendingTrendPage();

		if (Verify.objExists(st.last30Days))
			Commentary.log(sa, LogStatus.INFO, "Pass: Last 30 days filter chip is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Fail: Last 30 days filter chip is Not displayed");
		st.last30Days.click();

		if (Verify.objExists(st.thisMonth))
			Commentary.log(sa, LogStatus.INFO, "Pass: This month filter chip is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Fail: This month filter chip is Not displayed");
		st.thisMonth.click();

		if (Verify.objExists(st.lastMonth))
			Commentary.log(sa, LogStatus.INFO, "Pass: Last month filter chip is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Fail: Last month filter chip is Not displayed");
		st.lastMonth.click();

		if (Verify.objExists(st.monthToDate))
			Commentary.log(sa, LogStatus.INFO, "Pass: Month to date filter chip is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Fail: Month to date filter chip is Not displayed");
		st.monthToDate.click();

		for(int i=1 ;i<=3;i++) { 
			st.scrollFilter();
		}

		if (Verify.objExists(st.yearToDate))
			Commentary.log(sa, LogStatus.INFO, "Pass: Year to date filter chip is displayed");
		else
			Commentary.log(sa, LogStatus.FAIL, "Fail: Year to date filter chip is Not displayed");
		st.yearToDate.click();

		sa.assertAll();
	}

	@Test (priority=5, enabled = true)
	public void TC6_VerifySpendingOverTimeCard () throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify by default current month is highlighted/selected on the graph");

		String sMonth = h.getLastSixMonths()[0];
		String sActual;

		OverviewPage op = new OverviewPage();
		op.tapOnSpendingOverTimeCard();

		SpendingOverTimePage sot = new SpendingOverTimePage();
		Verify.waitForObject(sot.totalSpendingCurrentMonth, 2);
		sActual = sot.getSelectedMonth();

		if (sActual.equals(sMonth))
			Commentary.log(LogStatus.INFO, "PASS: Spending OverTime screen, by default current month "+sMonth+" is selected on the graph");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Spending OverTime screen by default current month ["+sMonth+"] should be selected but "+sActual +" was seen");	

		sa.assertAll();
	}

	@Test(priority = 6, enabled = true)
	public void TC7_VerifySpendingOverTimeCard_LastSixMonth() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify past six months names appear on the Spending Over Time screen");

		OverviewPage op = new OverviewPage();
		op.tapOnSpendingOverTimeCard();

		String [] lastSixMonths = h.getLastSixMonths();

		int iCount;

		SpendingOverTimePage sot = new SpendingOverTimePage();
		Verify.waitForObject(sot.totalSpendingCurrentMonth, 2);

		for (iCount=0; iCount<lastSixMonths.length; iCount++) {
			if (sot.verifyMonth(lastSixMonths[iCount]))
				Commentary.log(LogStatus.INFO, "PASS: Spending OverTime ["+lastSixMonths[iCount]+"] month appeared on the screen");
			else
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Spending OverTime Card ["+lastSixMonths[iCount]+"] didn't appear on the screen");	
		}

		sa.assertAll();
	}

	@Test(priority = 7, enabled = true)
	public void TC8_VerifyNetIncomeOverTimeCard() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify by default current month is highlighted/selected on the graph");

		String sMonth = h.getLastSixMonths()[0];
		String sActual;

		OverviewPage op = new OverviewPage();
		op.tapOnNetIncomeOverTimeCard();

		NetIncomeOverTimePage not = new NetIncomeOverTimePage();
		Verify.waitForObject(not.netIncomeCurrentMonth, 2);
		sActual = not.getSelectedMonth();

		if (sActual.equals(sMonth))
			Commentary.log(LogStatus.INFO, "PASS: NetIncome OverTime screen, by default current month "+sMonth+" is selected on the graph");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: NetIncome OverTime screen by default current month ["+sMonth+"] should be selected but "+sActual +" was seen");	

		sa.assertAll();
	}

	@Test(priority = 8, enabled = true)
	public void TC9_VerifyNetIncomeOverTimeCard_LastSixMonth() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify past six months names appear on the NetIncome Over Time screen");

		OverviewPage op = new OverviewPage();
		op.tapOnNetIncomeOverTimeCard();

		String [] lastSixMonths = h.getLastSixMonths();

		int iCount;

		NetIncomeOverTimePage not = new NetIncomeOverTimePage();
		Verify.waitForObject(not.netIncomeCurrentMonth, 2);

		for (iCount=0; iCount<lastSixMonths.length; iCount++) {
			if (not.verifyMonth(lastSixMonths[iCount]))
				Commentary.log(LogStatus.INFO, "PASS: NetIncome OverTime ["+lastSixMonths[iCount]+"] month appeared on the screen");
			else
				Commentary.log(sa, LogStatus.FAIL, "FAIL: NetIncome OverTime Card ["+lastSixMonths[iCount]+"] didn't appear on the screen");	
		}

		sa.assertAll();
	}

	@Test(priority=9, enabled = true)
	public void TC10_ValidateBalancesOnAccountCard() throws Exception{

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify balances on accounts card");

		String sChecking, sCredit, sSaving, sTotal;
		OverviewPage op = new OverviewPage();
		sChecking = op.checkingBalance.getText();
		sCredit = op.creditBalance.getText();
		sSaving = op.savingsBalance.getText();
		sTotal = op.totalBalance.getText();

		Double dChecking = h.processBalanceAmount(sChecking);
		Double dCredit = h.processBalanceAmount(sCredit);
		Double dSaving = h.processBalanceAmount(sSaving);

		double dTotal = h.processBalanceAmount(sTotal);

		Double eTotal = (double) Math.round((dChecking+dCredit+dSaving)*100);
		eTotal = eTotal/100;

		if (eTotal==dTotal)
			Commentary.log(LogStatus.INFO, "PASS: Overview Page > Balances and Total amount are correct");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Overview Page > Balances and Total amount is incorrect");

		//Navigate to Account cards
		op.navigateToAcctList();
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();

		Verify.waitForObject(bcc.checkingBalance, 2);
		sChecking = bcc.checkingBalance.getText();
		sCredit = bcc.creditCardBalance.getText();
		sSaving = bcc.savingsBalance.getText();
		sTotal = bcc.txtTodaysBalanceAmount.getText();

		dChecking = h.processBalanceAmount(sChecking.replace("SubTotal: ", ""));
		dCredit = h.processBalanceAmount(sCredit.replace("SubTotal: ", ""));
		dSaving = h.processBalanceAmount(sSaving.replace("SubTotal: ", ""));
		dTotal = h.processBalanceAmount(sTotal);

		eTotal = (double) Math.round((dChecking+dCredit+dSaving)*100);
		eTotal = eTotal/100;

		if (eTotal==dTotal)
			Commentary.log(LogStatus.INFO, "PASS: Account card Page > Balances and Total amount are correct");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Account card Page > Balances and Total amount is incorrect");

		sa.assertAll();
	}

	@Test (priority = 10, enabled = true)
	public void TC11_ValidateTransferTranscation() throws Exception	{

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO,	"["+h.getEngine()+"]: Creating a transfer from Checking to Saving account and verifying balances");

		String sChecking_before,sSaving_after, sSaving_before, sChecking_after ;
		String payeeName = "Payee_"+h.getCurrentTime();

		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();

		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		Verify.waitForObject(bcc.checkingBalance, 2);
		sChecking_before = bcc.getCheckingBalance();
		sSaving_before = bcc.getSavingsBalance();
		Double dChecking_before = h.processBalanceAmount(sChecking_before.replaceAll("[^0-9.-]", ""));
		Double dSaving_before = h.processBalanceAmount(sSaving_before.replaceAll("[^0-9.-]", ""));
		
//		String processedChecking_before = sChecking_before.replaceAll("[^0-9.-]", "");
//		String processedSaving_before = sSaving_before.replaceAll("[^0-9.-]", "");
		
//		BigDecimal checking_before = new BigDecimal(processedChecking_before);
//		BigDecimal saving_before = new BigDecimal(processedSaving_before);

		bcc.allTransactionButton.click();
		TransactionsPage tp = new TransactionsPage();
		
		Verify.waitForObject(tp.addTransaction, 2);
		tp.addTransaction.click();

		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("20.00");
		tRec.setPayee(payeeName);
		tRec.setAccount(sManualChecking);
		tRec.setCategory("Transfer ["+sManualSaving+"]");
		tRec.setTransactionType("expense");

		td.addTransaction(tRec);
		Verify.waitForObject(tp.backButton, 1);
		tp.backButton.click();

		Verify.waitForObject(bcc.checkingBalance, 2);
		sChecking_after = bcc.getCheckingBalance();
		sSaving_after = bcc.getSavingsBalance();
		Double dChecking_after = h.processBalanceAmount(sChecking_after.replaceAll("[^0-9.-]", ""));
		Double dSaving_after = h.processBalanceAmount(sSaving_after.replaceAll("[^0-9.-]", ""));
		Double d = Double.parseDouble(tRec.getAmount());
		
//		String processedChecking_after = sChecking_after.replaceAll("[^0-9.-]", "");
//		String processedSaving_after = sSaving_after.replaceAll("[^0-9.-]", "");
		
//		BigDecimal checking_after = new BigDecimal(processedChecking_after);
//		BigDecimal saving_after = new BigDecimal(processedSaving_after);
		
//		BigDecimal diff = new BigDecimal(tRec.getAmount());

		int checking_compare = Double.compare(dChecking_after+d, dChecking_before);
		int savings_compare = Double.compare(dSaving_after-d, dSaving_before);
		
//		if (dChecking_after+d==dChecking_before)
		if(checking_compare == 0)
			Commentary.log(LogStatus.INFO, "PASS: Checking balance was ["+dChecking_before+"], added transfer transaction for ["+tRec.getAmount()+"], now the checking balance shows ["+dChecking_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Checking balance was ["+dChecking_before+"], added transfer transaction for ["+tRec.getAmount()+"], now the checking balance shows ["+dChecking_after+"]");

//		if (dSaving_after-d==dSaving_before)
		if(savings_compare == 0)
			Commentary.log(LogStatus.INFO, "PASS: Savings balance was ["+dSaving_before+"], added transfer transaction for ["+tRec.getAmount()+"], now the savings balance shows ["+dSaving_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Savings balance was ["+dSaving_before+"], added transfer transaction for ["+tRec.getAmount()+"], now the savings balance shows ["+dSaving_after+"]");
		
		sa.assertAll();
	}

	@Test(priority = 11, enabled = true)
	public void TC12_ValidateRunningBalanceDefault() throws Exception { 

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that by default the Show Running balance toggle should be ON and \"Date New to old\" should be selected.");

		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();

		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();

		bcc.selectAccount(sManualSaving);
		Thread.sleep(1000);

		// Verify Show running balance options is displayed in Account detail screen.

		if (tp.isRunningBalanceEnabled())
			Commentary.log(LogStatus.INFO, "PASS: Running Balance is enabled by default");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Running Balance is NOT enabled by default");

		tp.buttonClose.click();
		tp.buttonSort.click();
		Thread.sleep(1000);

		//Verify that default filter is set to Date New to Old
		if (Verify.objExists(tp.defaultfilterSelected))
			Commentary.log(LogStatus.INFO, "PASS: Filter as \"Date New to old\" is selected by default");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Filter as \"Date New to old\" is NOT selected by default");

		sa.assertAll();
	}

	@Test(priority = 12, enabled = true)
	public void TC13_ValidateRunningBalanceCalculation() throws Exception { 

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO,	"["+h.getEngine()+"]: Verify Balance calculation for filter combination \"Date new to old\" + \"Show Running Balance\" set to ON");

		Double dFirstRunningBalance, dSecondRunningBalance, dThirdRunningBalance, dFourthRunningBalance, dFirstTxnAmount, dSecondTxnAmount, dThirdTxnAmount;

		OverviewPage op = new OverviewPage();
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();
		AllAccountsPage aa = new AllAccountsPage();
		
		op.navigateToAcctList();
		bcc.selectAccount(sManualSaving);
		
		Verify.waitForObject(tp.buttonSort, 2);
		tp.buttonSort.click();
		Thread.sleep(1000);
		
		tp.selectSortFilterOption(filterNewToOld);

		if (tp.isRunningBalanceEnabled()) {
			Commentary.log(LogStatus.INFO, "Running balance is enabled by default");
			tp.buttonApply.click();
			Thread.sleep(2000);
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is NOT enabled by default");
			tp.buttonApply.click();
			Thread.sleep(1000);
		}

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
			//if (iFourthRunningBalance-iThirdTxnAmount==iThirdRunningBalance) 
			if (dFourthRunningBalance-iThirdTxnAmount==dThirdRunningBalance) {
				Commentary.log(LogStatus.INFO, "PASS: Running balance is ["+dFourthRunningBalance+"] and transaction amount is ["+dThirdTxnAmount+"], calculated Running balance is ["+dThirdRunningBalance+"]");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is ["+dFourthRunningBalance+"] and transaction amount is ["+dThirdTxnAmount+"], calculated Running balance is ["+dThirdRunningBalance+"]");
			}
		}
		else {
			//if (iFourthRunningBalance+iThirdTxnAmount==iThirdRunningBalance) 
			if (dFourthRunningBalance+dThirdTxnAmount==dThirdRunningBalance) {
				Commentary.log(LogStatus.INFO, "PASS: Running balance is ["+dFourthRunningBalance+"] and transaction amount is ["+dThirdTxnAmount+"], calculated Running balance is ["+dThirdRunningBalance+"]");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is ["+dFourthRunningBalance+"] and transaction amount is ["+dThirdTxnAmount+"], calculated Running balance is ["+dThirdRunningBalance+"]");
			}
		}

		if (dSecondTxnAmount<0) {
			//if (iThirdRunningBalance-iSecondTxnAmount==iSecondRunningBalance) 
			if (dThirdRunningBalance-iSecondTxnAmount==dSecondRunningBalance) {
				Commentary.log(LogStatus.INFO, "PASS: Running balance is ["+dThirdRunningBalance+"] and transaction amount is ["+dSecondTxnAmount+"], calculated Running balance is ["+dSecondRunningBalance+"]");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is ["+dThirdRunningBalance+"] and transaction amount is ["+dSecondTxnAmount+"], calculated Running balance is ["+dSecondRunningBalance+"]");
			}
		}
		else {
			//if (iThirdRunningBalance+iSecondTxnAmount==iSecondRunningBalance) 
			if (dThirdRunningBalance+dSecondTxnAmount==dSecondRunningBalance) {
				Commentary.log(LogStatus.INFO, "PASS: Running balance is ["+dThirdRunningBalance+"] and transaction amount is ["+dSecondTxnAmount+"], calculated Running balance is ["+dSecondRunningBalance+"]");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is ["+dThirdRunningBalance+"] and transaction amount is ["+dSecondTxnAmount+"], calculated Running balance is ["+dSecondRunningBalance+"]");
			}
		}

		if (dFirstTxnAmount<0) {
			//if (iSecondRunningBalance-iFirstTxnAmount==iFirstRunningBalance) 
			if (dSecondRunningBalance-iFirstTxnAmount==dFirstRunningBalance) {
				Commentary.log(LogStatus.INFO, "PASS: Running balance is ["+dSecondRunningBalance+"] and transaction amount is ["+dFirstTxnAmount+"], calculated Running balance is ["+dFirstRunningBalance+"]");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is ["+dSecondRunningBalance+"] and transaction amount is ["+dFirstTxnAmount+"], calculated Running balance is ["+dFirstRunningBalance+"]");
			}
		}
		else {
			//if (iSecondRunningBalance+iFirstTxnAmount==iFirstRunningBalance) 
			if (dSecondRunningBalance+dFirstTxnAmount==dFirstRunningBalance) {
				Commentary.log(LogStatus.INFO, "PASS: Running balance is ["+dSecondRunningBalance+"] and transaction amount is ["+dFirstTxnAmount+"], calculated Running balance is ["+dFirstRunningBalance+"]");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is ["+dSecondRunningBalance+"] and transaction amount is ["+dFirstTxnAmount+"], calculated Running balance is ["+dFirstRunningBalance+"]");
			}
		}

		sa.assertAll();
	}

	/**
	 * This TC is expected to fail when Running balances have Negative values, This has been written because it helped finding Jira NM-5055.
	 **/
	@Test(priority = 13, enabled = true)
	public void TC14_ValidateRunningBalanceCalculation() throws Exception { 

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO,	"["+h.getEngine()+"]: Verify Balance calculation for filter combination \"Date new to old\" + \"Show Running Balance\" set to ON");

		Double dFirstRunningBalance, dSecondRunningBalance, dThirdRunningBalance, dFourthRunningBalance, dFirstTxnAmount, dSecondTxnAmount, dThirdTxnAmount;

		OverviewPage op = new OverviewPage();
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();
		AllAccountsPage aa = new AllAccountsPage();
		
		op.navigateToAcctList();
		bcc.selectAccount(sManualSaving);

		Verify.waitForObject(tp.buttonSort, 1);
		tp.buttonSort.click();
		Thread.sleep(1000);
		
		tp.selectSortFilterOption(filterNewToOld);

		if (tp.isRunningBalanceEnabled()) {
			Commentary.log(LogStatus.INFO, "Running balance is enabled by default");
			tp.buttonApply.click();
			Thread.sleep(2000);
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is NOT enabled by default");
			tp.buttonApply.click();
			Thread.sleep(1000);
		}

		dFirstRunningBalance= aa.getRunningBalancefromTransaction(1);
		dFirstTxnAmount = aa.getTransactionAmount(1);
		Double iFirstRunningBalance = Math.abs(dFirstRunningBalance); //This will negate the value if there is negative running balance value.
		Double iFirstTxnAmount = Math.abs(dFirstTxnAmount);

		dSecondRunningBalance= aa.getRunningBalancefromTransaction(2);
		dSecondTxnAmount = aa.getTransactionAmount(2);
		Double iSecondRunningBalance = Math.abs(dSecondRunningBalance);
		Double iSecondTxnAmount = Math.abs(dSecondTxnAmount);

		dThirdRunningBalance= aa.getRunningBalancefromTransaction(3);
		dThirdTxnAmount = aa.getTransactionAmount(3);
		Double iThirdRunningBalance = Math.abs(dThirdRunningBalance);
		Double iThirdTxnAmount = Math.abs(dThirdTxnAmount);

		dFourthRunningBalance= aa.getRunningBalancefromTransaction(4);
		Double iFourthRunningBalance = Math.abs(dFourthRunningBalance);

		if (dThirdTxnAmount<0) {
			if (iFourthRunningBalance-iThirdTxnAmount==iThirdRunningBalance) {
				Commentary.log(LogStatus.INFO, "PASS: Running balance is ["+dFourthRunningBalance+"] and transaction amount is ["+dThirdTxnAmount+"], calculated Running balance is ["+dThirdRunningBalance+"]");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is ["+dFourthRunningBalance+"] and transaction amount is ["+dThirdTxnAmount+"], calculated Running balance is ["+dThirdRunningBalance+"]");
			}
		}
		else {
			if (iFourthRunningBalance+iThirdTxnAmount==iThirdRunningBalance) {
				Commentary.log(LogStatus.INFO, "PASS: Running balance is ["+dFourthRunningBalance+"] and transaction amount is ["+dThirdTxnAmount+"], calculated Running balance is ["+dThirdRunningBalance+"]");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is ["+dFourthRunningBalance+"] and transaction amount is ["+dThirdTxnAmount+"], calculated Running balance is ["+dThirdRunningBalance+"]");
			}
		}

		if (dSecondTxnAmount<0) {
			if (iThirdRunningBalance-iSecondTxnAmount==iSecondRunningBalance) {
				Commentary.log(LogStatus.INFO, "PASS: Running balance is ["+dThirdRunningBalance+"] and transaction amount is ["+dSecondTxnAmount+"], calculated Running balance is ["+dSecondRunningBalance+"]");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is ["+dThirdRunningBalance+"] and transaction amount is ["+dSecondTxnAmount+"], calculated Running balance is ["+dSecondRunningBalance+"]");
			}
		}
		else {
			if (iThirdRunningBalance+iSecondTxnAmount==iSecondRunningBalance) {
				Commentary.log(LogStatus.INFO, "PASS: Running balance is ["+dThirdRunningBalance+"] and transaction amount is ["+dSecondTxnAmount+"], calculated Running balance is ["+dSecondRunningBalance+"]");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is ["+dThirdRunningBalance+"] and transaction amount is ["+dSecondTxnAmount+"], calculated Running balance is ["+dSecondRunningBalance+"]");
			}
		}

		if (dFirstTxnAmount<0) {
			if (iSecondRunningBalance-iFirstTxnAmount==iFirstRunningBalance) {
				Commentary.log(LogStatus.INFO, "PASS: Running balance is ["+dSecondRunningBalance+"] and transaction amount is ["+dFirstTxnAmount+"], calculated Running balance is ["+dFirstRunningBalance+"]");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is ["+dSecondRunningBalance+"] and transaction amount is ["+dFirstTxnAmount+"], calculated Running balance is ["+dFirstRunningBalance+"]");
			}
		}
		else {
			if (iSecondRunningBalance+iFirstTxnAmount==iFirstRunningBalance) {
				Commentary.log(LogStatus.INFO, "PASS: Running balance is ["+dSecondRunningBalance+"] and transaction amount is ["+dFirstTxnAmount+"], calculated Running balance is ["+dFirstRunningBalance+"]");
			} else {
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is ["+dSecondRunningBalance+"] and transaction amount is ["+dFirstTxnAmount+"], calculated Running balance is ["+dFirstRunningBalance+"]");
			}
		}

		sa.assertAll();
	}

	@Test (priority=14, enabled = true)
	public void TC15_VerifyTransactionSummary_TransactionDetails() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify transaction are displayed under correct category and payee in Transaction summary card ");

		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("5.00"); 
		tRec.setAccount(sManualChecking);
		tRec.setPayee("shop");

		TransactionsPage tp = new TransactionsPage();
		TransactionDetailPage td = new TransactionDetailPage();
		OverviewPage op = new OverviewPage();

		Verify.waitForObject(op.addTransaction, 1);
		op.addTransaction.click();
		
		td.addTransaction(tRec);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);

		op.tapOnTransactionSummaryCard();

		TransactionSummaryPage ts = new TransactionSummaryPage();
		Verify.waitForObject(ts.transactionCategoryPayeeText, 2);
		String categoryName = ts.getCategoryName();

		ts.transactionCategoryPayeeText.click();
		Thread.sleep(7000);

		tp.tapOnTransation(0);
		Thread.sleep(2000);

		Verify.waitForObject(td.selectedCategory, 2);
		td.VerifyTransactionCategory(categoryName);

		Verify.waitForObject(td.backButtonOnViewTransactionPage, 1);
		td.backButtonOnViewTransactionPage.click();
		Thread.sleep(2000);

		tp.tapOnTransation(1);
		Thread.sleep(2000);
		
		Verify.waitForObject(td.selectedCategory, 2);
		td.VerifyTransactionCategory(categoryName);

		Verify.waitForObject(td.backButtonOnViewTransactionPage, 1);
		td.backButtonOnViewTransactionPage.click(); 
		Thread.sleep(2000);

		td.backButton.click(); 
		Thread.sleep(2000);

		ts.payeeTab.click();
		Thread.sleep(2000);
		String payeeName = ts.getPayeeName();

		ts.transactionCategoryPayeeText.click();
		Thread.sleep(1000);

		tp.tapOnTransation(0);
		Thread.sleep(1000);
		td.VerifyTransactionPayee(payeeName);

		td.backButtonOnViewTransactionPage.click(); 
		Thread.sleep(1000);

		tp.tapOnTransation(1);
		Thread.sleep(1000);
		td.VerifyTransactionPayee(payeeName);

		td.backButtonOnViewTransactionPage.click(); 
		Thread.sleep(1000);

		sa.assertAll();
	}

	@Test (priority = 15, enabled = true)
	public void TC16_VerifyTransactionSummary_PayeeScreen() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Adding an expense transaction and validating that the Payee details are updated on Transaction Summary page.");

		OverviewPage op = new OverviewPage();
		op.tapOnTransactionSummaryCard();

		TransactionSummaryPage ts = new TransactionSummaryPage();
		Verify.waitForObject(ts.payeeTab, 2);
		ts.payeeTab.click();

		Verify.waitForObject(ts.payeeTile, 2);
		String sPayeeAmount_before = ts.payeeTile.getText();
		Commentary.log(LogStatus.INFO, "Payee tile amount before adding transaction-> "+sPayeeAmount_before);
		Double dPayeeAmount_before = h.processBalanceAmount(sPayeeAmount_before.replaceAll("[^0-9.-]", ""));
		Commentary.log(LogStatus.INFO, "Processed Payee tile amount is: "+dPayeeAmount_before);

		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("5.00");
		tRec.setAccount(sManualChecking);
		tRec.setPayee("shop");
		tRec.setTransactionType("expense");

		Verify.waitForObject(ts.backButtonOnHeader, 1);
		ts.backButtonOnHeader.click();

		op.scrollToTop();		
		
		Verify.waitForObject(op.addTransaction, 2);
		op.addTransaction.click();
		
		td.addTransaction(tRec);

		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount "+tRec.getAmount());

		Thread.sleep(3000);
		op.tapOnTransactionSummaryCard();
		Verify.waitForObject(ts.payeeTab, 2);
		ts.payeeTab.click();

		Verify.waitForObject(ts.payeeTile, 2);
		String sPayeeAmount_after = ts.payeeTile.getText();
		Double dPayeeAmount_after = h.processBalanceAmount(sPayeeAmount_after.replaceAll("[^0-9.-]", ""));
		Commentary.log(LogStatus.INFO, "Payee tile amount is now: "+dPayeeAmount_after);
		
		Double d = Double.parseDouble(tRec.getAmount());
		
		int payeeTile_Compare = Double.compare(dPayeeAmount_before-d, dPayeeAmount_after);
		
		if(payeeTile_Compare == 0)
			Commentary.log(LogStatus.INFO, "PASS: Payee tile is updated after adding expense transaction for selected payee.");
		else
			Commentary.log(sa, LogStatus.FAIL, "Payee tile is NOT updated after adding expense transaction for selected payee.");

		sa.assertAll();
	}

	@Test (priority=16, enabled = true)
	public void TC17_VerifyTransactionSummary_CategoryScreen() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Adding an expense transaction and validating that the Category details are updated on Transaction Summary page.");

		String payeeName = "Payee_"+h.getCurrentTime();

		OverviewPage op = new OverviewPage();
		op.tapOnTransactionSummaryCard();

		TransactionSummaryPage ts = new TransactionSummaryPage();
		Thread.sleep(4000);
		ts.categoryTab.click();
		Thread.sleep(1000);
		Verify.waitForObject(ts.categoryTile, 2);

		String sCategoryAmount_before = ts.categoryTile.getText();
		Double dCategoryAmount_before = h.processBalanceAmount(sCategoryAmount_before.replaceAll("[^0-9.-]", ""));
		Commentary.log(LogStatus.INFO, "Category tile amount is: "+dCategoryAmount_before);

		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("10.00");
		tRec.setAccount(sManualChecking);
		tRec.setPayee(payeeName);
		tRec.setCategory("Internet");
		tRec.setTransactionType("expense");

		Verify.waitForObject(ts.backButtonOnHeader, 1);
		ts.backButtonOnHeader.click();

		op.scrollToTop();

		Verify.waitForObject(op.addTransaction, 1);
		op.addTransaction.click();
		
		td.addTransaction(tRec);

		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount "+tRec.getAmount());

		op.tapOnTransactionSummaryCard();
		Verify.waitForObject(ts.categoryTab, 5);
		ts.categoryTab.click();

		Verify.waitForObject(ts.categoryTile, 5);
		String sCategoryAmount_after = ts.categoryTile.getText();
		Double dCategoryAmount_after = h.processBalanceAmount(sCategoryAmount_after.replaceAll("[^0-9.-]", ""));
		Commentary.log(LogStatus.INFO, "Category tile amount is now: "+dCategoryAmount_after);
		
		Double d = Double.parseDouble(tRec.getAmount());

		int categoryTile_Compare = Double.compare(dCategoryAmount_before-d, dCategoryAmount_after);
		
		if(categoryTile_Compare == 0)
			Commentary.log(LogStatus.INFO, "PASS: Category tile is updated after adding expense transaction for selected payee.");
		else
			Commentary.log(sa, LogStatus.FAIL, "Category tile is NOT updated after adding expense transaction for selected payee.");

		sa.assertAll();
	}

	//	@Test(priority = 17, enabled = true)
	//	public void TC18_ValidateSwipe_Category() throws Exception {
	//
	//		SoftAssert sa = new SoftAssert();
	//		Helper h = new Helper();
	//		
	//		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify Swipe gesture on transaction and change the category and check the transaction details");
	//
	//		if (h.getEngine().equalsIgnoreCase("ios")) {
	//		OverviewPage o = new OverviewPage();
	//		o.navigateToAcctList();
	//
	//		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
	//		//bcc.txtTodaysBalance.click();
	//		bcc.allTransactionButton.click();
	//
	//		TransactionsPage tp = new TransactionsPage();
	//		TransactionDetailPage td = new TransactionDetailPage();
	//		TransactionRecord tRec = new TransactionRecord();
	//
	//		String payeeName = "Payee_"+h.getCurrentTime();
	//
	//		tRec.setAmount("5.00");
	//		tRec.setAccount(sManualChecking);
	//		tRec.setCategory("Internet");
	//		tRec.setPayee(payeeName);
	//		tRec.setTransactionType("expense");
	//
	//		Verify.waitForObject(tp.addTransaction, 3);
	//		tp.addTransaction.click();
	//		Verify.waitForObject(td.buttonDone, 5);
	//		td.addTransaction(tRec);
	//		Thread.sleep(2000);
	//
	//		Verify.waitForObject(tp.searchTransactionTxtField, 3);
	//		tp.searchTransactionTxtField.click();
	//		tp.searchTransactionTxtField.sendKeys(payeeName);
	//		Thread.sleep(3000);
	//
	//		Verify.waitForObject(tp.getPayeeName(), 2);
	//		String afterPayeeName = tp.getPayeeName().getText();
	//
	//		if (afterPayeeName.equals(payeeName)) 
	//			Commentary.log(LogStatus.INFO, "Payee is created and transaction is saved successfully");
	//		else 
	//			Commentary.log(LogStatus.INFO, "Payee is Not created");
	//
	//
	//		tp.swipe_left();
	//		Verify.waitForObject(tp.btnCategorize, 2); //Swipe Gestures options are not getting inspected.
	//		tp.btnCategorize.click();
	//
	//		tp.selectCategorySwipe("Mobile Phone");
	//		Thread.sleep(3000);
	//
	//		tp.tapOnFirstTransaction();
	//
	//		if ((td.getCategory("Mobile Phone").getText()).equals("Mobile Phone"))
	//			Commentary.log(sa, LogStatus.PASS, "PASS: Successfully updated the category");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Unable to update the category");
	//
	//		sa.assertAll();
	//	} else {
	//		Commentary.log(LogStatus.INFO, "Automation blocked for Android for Swipe feature");
	//	}
	//}
	//	
	//@Test(priority = 18, enabled = true)
	//public void TC19_ValidateSwipe_Delete() throws Exception {
	//
	//	SoftAssert sa = new SoftAssert();
	//	Helper h = new Helper();
	//
	//	Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify Swipe gesture on transaction and delete the transaction and check the transaction is deleted after searching");
	//
	//	if (h.getEngine().equalsIgnoreCase("ios")) {
	//		OverviewPage o = new OverviewPage();
	//		o.navigateToAcctList();
	//
	//		Commentary.log(LogStatus.INFO, "Validating Delete transaction from swipe gesture options");
	//
	//		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
	//		//bcc.txtTodaysBalance.click();
	//		bcc.allTransactionButton.click();
	//		TransactionsPage tp = new TransactionsPage();
	//		TransactionDetailPage td = new TransactionDetailPage();
	//		TransactionRecord tRec = new TransactionRecord();
	//
	//		String payeeName = "Payee_"+h.getCurrentTime();
	//
	//		tRec.setAmount("5.00");
	//		tRec.setAccount(sManualChecking);
	//		tRec.setCategory("Internet");
	//		tRec.setPayee(payeeName);
	//		tRec.setTransactionType("expense");
	//
	//		tp.addTransaction.click();
	//		td.addTransaction(tRec);
	//		Thread.sleep(2000);
	//
	//		tp.searchTransactionTxtField.click();
	//		tp.searchTransactionTxtField.sendKeys(payeeName);
	//		h.hideKeyBoard();
	//
	//		tp.swipe_left();
	//		tp.btnDelete.click();
	//		//td.deleteTransactionAlertButton.click();
	//		Thread.sleep(3000);
	//
	//		if (Verify.objExists(tp.txtNoResultFound))
	//			Commentary.log(LogStatus.INFO, "Successfully deleted the transaction");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Unable to delete the selected transaction");
	//
	//		tp.searchTransactionTxtField.click();
	//		tp.searchTransactionTxtField.clear();
	//		tp.searchTransactionTxtField.sendKeys(payeeName);
	//		Thread.sleep(2000);
	//
	//		if (Verify.objExists(tp.txtNoResultFound))
	//			Commentary.log(LogStatus.INFO, "PASS: Successfully deleted the transaction");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Unable to delete the selected transaction");
	//
	//		sa.assertAll();
	//	} else {
	//		Commentary.log(LogStatus.INFO, "Automation blocked for Android for Swipe feature");
	//	}
	//}

	//	@Test (priority = 19, enabled = true)
	//	public void TC20_ValidateHamburgerMenuOptions ()throws Exception {
	//
	//		SoftAssert sa = new SoftAssert();
	//		Helper h = new Helper();
	//
	//		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating hamburger menu options");
	//
	//		OverviewPage op = new OverviewPage();
	//		op.hambergerIcon.click();
	//
	//		SettingsPage sp = new SettingsPage();
	//
	//		Verify.waitForObject(sp.datasetDDButton, 3);
	//		Verify.waitForObject(sp.accountTxt, 3);
	////		Verify.waitForObject(sp.PasscodeTxt, 3);
	////		Verify.waitForObject(sp.ManageAlertsTxt, 3);
	////		Verify.waitForObject(sp.HelpLegalTxt, 3);
	////		Verify.waitForObject(sp.logout, 3);
	//
	//		if(sp.verifyQuickenID(sUserName))
	//			Commentary.log(LogStatus.INFO, "PASS: Quicken ID is displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Quicken ID is NOT displayed");
	//
	//		if (Verify.objExists(sp.closeButton))
	//			Commentary.log(LogStatus.INFO, "PASS: Close button is displayed");
	//		else 
	//			Commentary.log(sa, LogStatus.FAIL, "Close button is NOT displayed");
	//
	//		if (Verify.objExists(sp.datasetDDButton))
	//			Commentary.log(LogStatus.INFO, "PASS: Dataset DD button button is displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Dataset DD button button is NOT displayed");
	//
	//		if (Verify.objExists(sp.accountTxt))
	//			Commentary.log(LogStatus.INFO, "PASS: Account Text is displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Account Text is NOT displayed");
	//
	//		if (Verify.objExists(sp.PasscodeTxt))
	//			Commentary.log(LogStatus.INFO, "PASS: Passcode text is displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Passcode text is NOT displayed");
	//
	//		if (Verify.objExists(sp.ManageAlertsTxt))
	//			Commentary.log(LogStatus.INFO, "PASS: Manage Alert text is displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Manage Alert text is NOT displayed");
	//
	//		if (Verify.objExists(sp.HelpLegalTxt))
	//			Commentary.log(LogStatus.INFO, "PASS: Help & Legal text is displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Help & Legal text is NOT displayed");
	//
	//		if (Verify.objExists(sp.logout))
	//			Commentary.log(LogStatus.INFO, "PASS: Logout button is displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Logout button is NOT displayed");
	//
	//		if (h.getEngine().equalsIgnoreCase("Android")) {
	//			if (Verify.objExists(sp.FeedbackTxt))
	//				Commentary.log(LogStatus.INFO, "PASS: Feedback Text is displayed");
	//			else
	//				Commentary.log(sa, LogStatus.FAIL, "Feedback Text is NOT displayed");
	//		} else {
	//			Commentary.log(LogStatus.INFO, "PASS: Feedback options is not supported for IOS Simulator");
	//		}
	//
	//		sa.assertAll();
	//
	//	}
	//
	//	@Test(priority = 20, enabled = true)
	//	public void TC21_ValidateAccountMenu() throws Exception {
	//
	//		SoftAssert sa = new SoftAssert();
	//		Helper h = new Helper();
	//		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating Account menu options and also verifying the details on selecting all user accounts");
	//
	//		OverviewPage op = new OverviewPage();
	//		op.hambergerIcon.click();
	//		Thread.sleep(3000);
	//
	//		SettingsPage sp = new SettingsPage();
	//		Verify.waitForObject(sp.accountTxt, 2);
	//		sp.accountTxt.click();
	//
	////		Verify.waitForObject(sp.getAccountElement(sManualChecking), 3);
	////		Verify.waitForObject(sp.getAccountElement(sManualCreditCard), 3);
	//		Verify.waitForObject(sp.getAccountElement(sManualSaving), 3);
	//
	//		if (Verify.objExists(sp.getAccountElement(sManualChecking)))
	//			Commentary.log(LogStatus.INFO, "PASS: Manual Checking account is displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Manual Checking account is NOT displayed");
	//
	//		if (Verify.objExists(sp.getAccountElement(sManualCreditCard)))
	//			Commentary.log(LogStatus.INFO, "PASS: Manual CC account is displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Manual CC account is NOT displayed");
	//
	//		if (Verify.objExists(sp.getAccountElement(sManualSaving)))
	//			Commentary.log(LogStatus.INFO, "PASS: Manual Savings account is displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Manual Savings account is NOT displayed");
	//
	//		//Verify Account Details
	//		sp.getAccountElement(sManualChecking).click();
	//
	//		Verify.waitForObject(sp.getAccountElement(sManualChecking), 3);
	//		MobileElement manualCheckingAccount1 = sp.getAccountElement(sManualChecking);
	//		MobileElement accountType = sp.getAccountElement("CHECKING");
	//		MobileElement accountStatus = sp.getTextView("Active");
	//
	//		if (Verify.objExists(manualCheckingAccount1))
	//			Commentary.log(LogStatus.INFO, "PASS: Manual Checking account details are displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Manual Checking account details are NOT displayed");
	//
	//
	//		if (Verify.objExists(accountType))
	//			Commentary.log(LogStatus.INFO, "PASS: Manual Checking Account Type details are displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Manual Checking Account Type details are NOT displayed");
	//
	//
	//		if (Verify.objExists(accountStatus))
	//			Commentary.log(LogStatus.INFO, "PASS: Manual Checking Account Status is displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Manual Checking Account Status is NOT displayed");
	//
	//		sp.selectBack("Accounts");
	//		Thread.sleep(2000);
	//
	//		sp.getAccountElement(sManualCreditCard).click();
	//		Thread.sleep(2000);
	//
	//		Verify.waitForObject(sp.getAccountElement(sManualCreditCard), 3);
	//		MobileElement manualCCAccount1 = sp.getAccountElement(sManualCreditCard);
	//		MobileElement accountType_manual = sp.getAccountElement("CREDIT_CARD");
	//		MobileElement accountStatus1 = sp.getTextView("Active");
	//		if (Verify.objExists(manualCCAccount1))
	//			Commentary.log(LogStatus.INFO, "PASS: Manual CC account details are displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Manual CC account details are NOT displayed");
	//
	//		if (Verify.objExists(accountType_manual))
	//			Commentary.log(LogStatus.INFO, "PASS: Manual CC Account Type details are displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Manual CC Account Type details are NOT displayed");
	//
	//		if (Verify.objExists(accountStatus1))
	//			Commentary.log(LogStatus.INFO, "PASS: Manual CC Account Status is displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Manual CC Account Status is NOT displayed");
	//
	//		sp.selectBack("Accounts");
	//		Thread.sleep(2000);
	//
	//		sp.getAccountElement(sManualSaving).click();
	//		Thread.sleep(2000);
	//
	//		Verify.waitForObject(sp.getAccountElement(sManualSaving), 3);
	//		MobileElement manualSavingsAccount1 = sp.getAccountElement(sManualSaving);
	//		MobileElement accountType_savings = sp.getAccountElement("SAVINGS");
	//		MobileElement accountStatus2 = sp.getTextView("Active");
	//
	//		if (Verify.objExists(manualSavingsAccount1))
	//			Commentary.log(LogStatus.INFO, "PASS: Savings account details are displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Savings account details are NOT displayed");
	//
	//		if (Verify.objExists(accountType_savings))
	//			Commentary.log(LogStatus.INFO, "PASS: Savings Account Type details are displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Savings Account Type details are NOT displayed");
	//
	//		if (Verify.objExists(accountStatus2))
	//			Commentary.log(LogStatus.INFO, "PASS: Savings Account Status is displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Savings Account Status is NOT displayed");
	//
	//		sa.assertAll();	
	//
	//	}
	//
	//	@Test(priority = 21, enabled = true)
	//	public void TC22_ValidatePasscodeFingerprintMenu() throws Exception {
	//
	//		SoftAssert sa = new SoftAssert();
	//		Helper h = new Helper();
	//
	//		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating menu options for Passcode and Fingerprint menu");
	//
	//		OverviewPage op = new OverviewPage();
	//		op.hambergerIcon.click();
	//
	//		SettingsPage sp = new SettingsPage();
	//		Verify.waitForObject(sp.PasscodeTxt, 3);
	//		sp.PasscodeTxt.click();
	//
	//		Verify.waitForObject(sp.getAccountElement("Use Quicken Passcode"), 3);
	//
	//		if (Verify.objExists(sp.PasscodeHeaderTxt))
	//			Commentary.log(LogStatus.INFO, "PASS: Header text is displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Header text is NOT displayed");
	//		if (Verify.objExists(sp.getAccountElement("Use Quicken Passcode")))
	//			Commentary.log(LogStatus.INFO, "PASS: Passcode text is displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Passcode text is NOT displayed");
	//
	////		if (h.getEngine().equalsIgnoreCase("ios")){
	////			if (Verify.objExists(sp.getTextView("Use Touch ID")))
	////				Commentary.log(LogStatus.INFO, "PASS: Touch ID text is displayed");
	////			else
	////				Commentary.log(sa, LogStatus.FAIL, "Touch ID text is NOT displayed");			   
	////		}	
	//		sa.assertAll();
	//
	//	}
	//
	//	@Test(priority = 22, enabled = true)
	//	public void TC23_ValidateManageAlerts() throws Exception {
	//
	//		SoftAssert sa = new SoftAssert();
	//		Helper h = new Helper();
	//
	//		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating menu options for Manage Alerts menu");
	//
	//		OverviewPage op = new OverviewPage();
	//		op.hambergerIcon.click();
	//
	//		SettingsPage sp = new SettingsPage();
	//		Verify.waitForObject(sp.ManageAlertsTxt, 3);
	//		sp.ManageAlertsTxt.click();
	//
	//		Verify.waitForObject(sp.ManageAlertsHeaderTxt, 2);
	//		Verify.waitForObject(sp.getTextView("New Charge - Quicken Card (Mobile Only)"), 2);
	//
	//		if (Verify.objExists(sp.ManageAlertsHeaderTxt))
	//			Commentary.log(LogStatus.INFO, "PASS: Manage Alert Header text is displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Header Alert header text is NOT displayed");
	//
	//		if (Verify.objExists(sp.getTextView("New Charge - Quicken Card (Mobile Only)")))
	//			Commentary.log(LogStatus.INFO, "PASS: New charge message text is displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "New charge message text is NOT displayed");
	//
	//		Verify.waitForObject(sp.getTextView("Push Notification"), 3);
	//		if (Verify.objExists(sp.getTextView("Push Notification")))
	//			Commentary.log(LogStatus.INFO, "PASS: Push Notification text is displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Push Notification text is NOT displayed");
	//
	//		sa.assertAll();
	//
	//	}
	//
	//	@Test(priority = 23, enabled = true)
	//	public void TC24_ValidateHelpLegal() throws Exception {
	//
	//		SoftAssert sa = new SoftAssert();
	//		Helper h = new Helper();
	//		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating menu options for Help and Legal menu");
	//
	//		OverviewPage op = new OverviewPage();
	//		op.hambergerIcon.click();
	//
	//		SettingsPage sp = new SettingsPage();
	//		Verify.waitForObject(sp.HelpLegalTxt, 3);
	//		sp.HelpLegalTxt.click();
	//		Thread.sleep(3000);
	//		Verify.waitForObject(sp.getTextView("Help"), 3);
	//		Verify.waitForObject(sp.getTextView("Support Website"), 3);
	//		Verify.waitForObject(sp.getTextView("Acknowledgements"), 3);
	//		Verify.waitForObject(sp.getTextView("License Agreement"), 3);
	//		Verify.waitForObject(sp.getTextView("Privacy"), 3);
	//
	//		MobileElement help = sp.getTextView("Help");
	//		MobileElement link_SupportWebsite = sp.getTextView("Support Website");
	//		MobileElement link_Acknowledgements = sp.getTextView("Acknowledgements");
	//		MobileElement link_LicenseAgreement = sp.getTextView("License Agreement");
	//		MobileElement link_Privacy = sp.getTextView("Privacy");
	//
	//		if (Verify.objExists(sp.HelpLegalHeaderTxt))
	//			Commentary.log(LogStatus.INFO, "PASS: Help Legal text is displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Help Legal text is NOT displayed");
	//
	//		if (Verify.objExists(help))
	//			Commentary.log(LogStatus.INFO, "PASS: Help text is displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Help text is NOT displayed");
	//
	//		if (Verify.objExists(link_SupportWebsite))
	//			Commentary.log(LogStatus.INFO, "PASS: Support Website Link is displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Support Website Link is NOT displayed");
	//
	//		if (Verify.objExists(sp.getTextView("Legal")))
	//			Commentary.log(LogStatus.INFO, "PASS: Legal text is displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Legal text is NOT displayed");
	//
	//		if (Verify.objExists(link_Acknowledgements))
	//			Commentary.log(LogStatus.INFO, "PASS: Acknowledgements link is displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Acknowledgements link is NOT displayed");
	//
	//		if (Verify.objExists(link_LicenseAgreement))
	//			Commentary.log(LogStatus.INFO, "PASS: License Agreement link is displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "License Agreement link is NOT displayed");
	//
	//		if (Verify.objExists(link_Privacy))
	//			Commentary.log(LogStatus.INFO, "PASS: Privacy link is displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Privacy link is NOT displayed");
	//
	//		sa.assertAll();
	//
	//	}
	//
	//	@Test (priority=24, enabled = true)
	//	public void TC25_VerifyTransactionSummary_CategoryScreen() throws Exception {
	//
	//		SoftAssert sa = new SoftAssert();
	//		Helper h = new Helper();
	//
	//		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Adding an expense transaction and validating that the Category details are updated on Transaction Summary page");
	//
	//		OverviewPage op = new OverviewPage();
	//		op.tapOnTransactionSummaryCard();
	//
	//		TransactionSummaryPage ts = new TransactionSummaryPage();
	//		Verify.waitForObject(ts.categoryTab, 2);
	//		ts.categoryTab.click();
	//
	//		Verify.waitForObject(ts.categoryTile, 3);
	//		String sCategoryAmount_before = ts.categoryTile.getText();
	//		Double dCategoryAmount_before = h.processBalanceAmount(sCategoryAmount_before.replace("Internet ", ""));
	//		Commentary.log(LogStatus.INFO, "Category amount is "+dCategoryAmount_before);
	//
	//		TransactionDetailPage td = new TransactionDetailPage();
	//		TransactionRecord tRec = new TransactionRecord();
	//		String payeeName = "Payee_"+h.getCurrentTime();
	//		
	//		tRec.setAmount("10.00");
	//		tRec.setAccount(sManualChecking);
	//		tRec.setPayee(payeeName);
	//		tRec.setCategory("Internet");
	//		tRec.setTransactionType("expense");
	//
	//		ts.backButtonOnHeader.click();
	//
	//		op.scrollToTop();
	//
	//		op.addTransaction.click();
	//		Verify.waitForObject(td.buttonDone, 2);
	//		td.addTransaction(tRec);
	//
	//		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount "+tRec.getAmount());
	//
	//		op.tapOnTransactionSummaryCard();
	//		Verify.waitForObject(ts.categoryTab, 2);
	//		ts.categoryTab.click();
	//
	//		Verify.waitForObject(ts.categoryTile, 5);
	//		String sCategoryAmount_after = ts.categoryTile.getText();
	//		Double dCategoryAmount_after = h.processBalanceAmount(sCategoryAmount_after.replace("Internet ", ""));
	//		Double d = Double.parseDouble(tRec.getAmount());
	//		Commentary.log(LogStatus.INFO, "Category amount now is "+dCategoryAmount_after);
	//
	//		if (dCategoryAmount_after+d==dCategoryAmount_before)
	//			Commentary.log(LogStatus.INFO, "PASS: Category tile is updated after adding expense transaction for selected payee");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Category tile is NOT updated after adding expense transaction for selected payee");
	//
	//		sa.assertAll();
	//
	//	}
	//
	//	@Test (priority = 25, enabled = true)
	//	public void TC26_ValidateAddingTransactionWithYelpPayee() throws Exception {
	//
	//		//GPS Coordinates set on the simulator Latitude : 37.785834 Longitude: -122.406417 to search Yelp Payee "Enough Tea & Coffee".
	//		SoftAssert sa = new SoftAssert();
	//		Helper h = new Helper();
	//
	//		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Adding a transaction with Payee selected from Yelp Results and later verifying the Payee Name from the added transaction.");
	//
	//		OverviewPage op = new OverviewPage();
	//		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 5);
	//
	//		TransactionsPage tp = new TransactionsPage();
	//		TransactionDetailPage td = new TransactionDetailPage();
	//		TransactionRecord tRec = new TransactionRecord();
	//		String payeeName = "Enough Tea & Coffee";
	//
	//		tRec.setAmount("5.00");
	//		tRec.setAccount(sManualChecking);
	//		tRec.setCategory("Restaurants");
	//		tRec.setPayee(payeeName);
	//		tRec.setTransactionType("expense");
	//
	//		Verify.waitForObject(op.addTransaction, 2);
	//		op.addTransaction.click();
	//		Verify.waitForObject(td.buttonDone, 2);
	//		td.addTransaction(tRec);
	//		Thread.sleep(2000);
	//		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount "+tRec.getAmount()+"], Transaction Payee Name: "+tRec.getPayee());
	//
	//		op.navigateToAcctList();
	//
	//		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
	//		Verify.waitForObject(bcc.allTransactionButton, 2);
	//		bcc.allTransactionButton.click();
	//
	//		Verify.waitForObject(tp.searchTransactionTxtField, 2);
	//		tp.searchRecentTransaction(payeeName);
	//		Verify.waitForObject(tp.thisMonthLabel, 2);
	//
	//		tp.tapOnFirstTransaction();
	//		Verify.waitForObject(td.payee, 2);
	//
	//		td.VerifyTransactionPayee(payeeName);
	//		td.backButton.click();	
	//
	//		sa.assertAll();
	//	}
	//
	//	@Test (priority=26, enabled = true)
	//	public void TC27_ValidateInvestmentCard() throws Exception {
	//
	//		SoftAssert sa = new SoftAssert();
	//		Helper h = new Helper();
	//		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating Investment Card details");
	//		
	//		OverviewPage op = new OverviewPage();
	//		op.hambergerIcon.click();
	//		Thread.sleep(1000);
	//
	//		SettingsPage sp = new SettingsPage();
	//		Verify.waitForObject(sp.datasetDDButton, 2);
	//		sp.datasetDDButton.click();
	//		Thread.sleep(4000);
	////		Verify.waitForObject(sp.getTextView("InvestmentDataset_Automation"), 5);
	////		MobileElement investmentDataset = sp.getTextView("InvestmentDataset_Automation");
	////		investmentDataset.click();
	//
	//		sp.switchDataset("InvestmentDataset_Automation");
	//
	//		op.tapOnInvestingCard();
	//
	//		InvestingPage ip = new InvestingPage();
	//		Verify.waitForObject(ip.securitiesTab, 2);
	//		Verify.waitForObject(ip.watchlistTab, 2);
	//		Verify.waitForObject(ip.lastSyncedFooter, 2);
	//
	//		if (Verify.objExists(ip.investingHeader))
	//			Commentary.log(LogStatus.INFO, "Investing Header text is displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Investing Header text is NOT displayed");
	//
	//		if (Verify.objExists(ip.securitiesTab))
	//			Commentary.log(LogStatus.INFO, "Securities Tab is displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Securities Tab is NOT displayed");
	//
	//		if (Verify.objExists(ip.accountsTab))
	//			Commentary.log(LogStatus.INFO, "Accounts Tab is displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Accounts Tab is NOT displayed");
	//
	//		if (Verify.objExists(ip.watchlistTab))
	//			Commentary.log(LogStatus.INFO, "Watch List Tab is displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Watch List Tab is NOT displayed");
	//
	//		if (Verify.objExists(ip.totalValueLabel))
	//			Commentary.log(LogStatus.INFO, "Total Value label is displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Total Value label is NOT displayed");
	//
	//		if (Verify.objExists(ip.cashbalancesLabel))
	//			Commentary.log(LogStatus.INFO, "Cash Balances label is displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Cash Balances label is NOT displayed");
	//
	//		if (Verify.objExists(ip.backButtonOnHeader))
	//			Commentary.log(LogStatus.INFO, "Back Button is displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Back Button is NOT displayed");
	//
	//		if (Verify.objExists(ip.securitiesByCompanyNameLabel))
	//			Commentary.log(LogStatus.INFO, "Security by company label is displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Security by company label is NOT displayed");
	//
	//		if (Verify.objExists(ip.lastSyncedFooter))
	//			Commentary.log(LogStatus.INFO, "Last Synced Footer is displayed");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Last Synced Footer is NOT displayed");
	//
	//		sa.assertAll();
	//
	//	}
	//
	//	@Test (priority=27, enabled = true)
	//	public void TC28_ValidateForZeroDataset_RecentTxnCard() throws Exception {
	//
	//		SoftAssert sa = new SoftAssert();
	//		Helper h = new Helper();
	//
	//		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating message displayed for all Card in case of zero dataset");
	//		OverviewPage op = new OverviewPage();
	//
	//		op.hambergerIcon.click();
	//		Thread.sleep(3000);
	//
	//		SettingsPage sp = new SettingsPage();
	//		Verify.waitForObject(sp.datasetDDButton, 3);
	//		sp.datasetDDButton.click();
	//		Thread.sleep(4000);
	////		Verify.waitForObject(sp.getTextView("ZeroDataSet"), 5); //Failing here bcoz getTextView method has /XCUIElementTypeStaticText whereas it needs XCUIElementTypeOther but changing it here will cause to fail TC21.
	////		MobileElement ZeroDataset = sp.getTextView("ZeroDataSet");
	////		String sXpath = "**/XCUIElementTypeOther[`name='ZeroDataSet'`]"; //working 
	////		Engine.iosd.findElementByIosClassChain(sXpath).click();
	////		ZeroDataset.click();
	//		sp.switchDataset("ZeroDataSet");
	//
	//		op.scrollTillCard("Recent Transactions");
	//
	//		String actText_TxnOverviewPage = op.recentTxns_NoTxnsAvailable.getText();
	//
	//		if (actText_TxnOverviewPage.equals("No Transaction available"))
	//			Commentary.log(LogStatus.INFO, "PASS: OverviewPage RecentTransaction card > Correct message is displayed in case user has no recent transactions");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "OverviewPage RecentTransaction card > Message is not displayed in case user has no recent transactions");
	//		//Navigate to Recent Transaction card
	//		op.tapOnRecentTransactionsCard();
	//
	//		TransactionsPage tp = new TransactionsPage();
	//		String actText_txnDetailsScreen = tp.noTransactionText.getText();
	//
	//		if (actText_txnDetailsScreen.equals("You don't have any transactions."))
	//			Commentary.log(LogStatus.INFO, "PASS: Recent Transaction page > Correct message is displayed in case user has no recent transactions");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Recent Transaction page > Message is not displayed in case user has no recent transactions");
	//
	//		sa.assertAll();
	//
	//	}
	//
	//	@Test (priority=28, enabled = true)
	//	public void TC29_ValidateForZeroDataset_NetIncomeOverTimeCard() throws Exception {
	//
	//		SoftAssert sa = new SoftAssert();
	//		Helper h = new Helper();
	//
	//		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating message displayed on Net Income Over Time Card in case of no transactions");
	//
	//		OverviewPage op = new OverviewPage();
	//		op.tapOnNetIncomeOverTimeCard();
	//
	//		NetIncomeOverTimePage not = new NetIncomeOverTimePage();
	//
	//		Verify.waitForObject(not.youDontHaveAnyTxns, 2);
	//		String actText_notDetailsScreen = not.youDontHaveAnyTxns.getText();
	//
	//		if (actText_notDetailsScreen.equals("You don't have any transactions."))
	//			Commentary.log(LogStatus.INFO, "PASS: Net Income Over Time page > Correct message is displayed in case user has no transactions");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Net Income Over Time page > Message is not displayed in case user has no transactions");
	//
	//		sa.assertAll();
	//
	//	}
	//
	//	@Test (priority=29, enabled = true)
	//	public void TC30_ValidateForZeroDataset_TransactionSummaryCard() throws Exception {
	//
	//		SoftAssert sa = new SoftAssert();
	//		Helper h = new Helper();
	//		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating message displayed on Transaction Summary Card in case of no transactions");
	//
	//		OverviewPage op = new OverviewPage();
	//		//Navigate to Transaction Summary card
	//		op.tapOnTransactionSummaryCard();
	//
	//		TransactionSummaryPage ts = new TransactionSummaryPage();
	//
	//		Verify.waitForObject(ts.noTransactionCategory, 2);
	//		String actText_summaryDetailsScreen = ts.noTransactionCategory.getText();
	//
	//		if (actText_summaryDetailsScreen.equals("No Transactions by Category"))
	//			Commentary.log(LogStatus.INFO, "PASS: Transaction Summary page > Correct message is displayed in case user has no transactions");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Transaction Summary page > Message is not displayed in case user has no transactions");
	//
	//		ts.payeeTab.click();
	//
	//		Verify.waitForObject(ts.noTransactionPayee, 2);
	//		String actText_noTxnPayee = ts.noTransactionPayee.getText();
	//
	//		if (actText_noTxnPayee.equals("No Transactions by Payee"))
	//			Commentary.log(LogStatus.INFO, "PASS: Transaction Summary page > Correct message is displayed in case user has no transactions");
	//		else
	//			Commentary.log(sa, LogStatus.FAIL, "Transaction Summary page > Message is not displayed in case user has no transactions");
	//
	//		sa.assertAll();
	//
	//	}
}