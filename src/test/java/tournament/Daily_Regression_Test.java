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
import dugout.FiltersPage;
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

	@Test(priority = 1, enabled = true)
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
	@Test(priority=2, enabled = true)
	public void TC2_ValidateEditTransaction() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: EDITING an expense transaction for an manual checking account, verify checking & total balance on overview screen accounts card.");

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

	@Test(priority = 3, enabled = true)
	public void TC3_ValidateDeleteTransaction() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Delete an expense transaction for an manual savings account, verify checking & total balance on overview screen accounts card.");

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
			Commentary.log(LogStatus.INFO, "PASS: Successfully Deleted the selected transaction.");
		else
			Commentary.log(sa, LogStatus.FAIL, "Unable to Delete the selected transaction.");

		sa.assertAll();		
	}	

	@Test (priority = 4, enabled = true)
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
			Commentary.log(LogStatus.INFO, "PASS: Back button is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "Back button is not displayed.");

		if (Verify.objExists(ts.monthlySummaryHeader))
			Commentary.log(LogStatus.INFO, "PASS: Monthly Summary header is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "Monthly Summary header is not displayed.");

		if (Verify.objExists(ts.categoryTab))
			Commentary.log(LogStatus.INFO, "PASS: Category button is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "Category button is not displayed.");

		if (Verify.objExists(ts.payeeTab))
			Commentary.log(LogStatus.INFO, "PASS: Payee button is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "Payee button is not displayed.");

		if (Verify.objExists(ts.getCurrentMonthYear()))
			Commentary.log(LogStatus.INFO, "PASS: Current month and year text is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "Current month and year text is Not displayed.");

		sa.assertAll();
	}

	@Test(priority=5, enabled = true)
	public void TC5_VerifyFilterForSpendingTrendCard() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify filter chips on trending screen should appear and selecting it reflects the category");

		SpendingTrendPage st = new SpendingTrendPage();
		st.navigateToTopTrendingCategoriesCard();

		if (Verify.objExists(st.last30Days))
			Commentary.log(sa, LogStatus.INFO, "Pass: Last 30 days filter chip is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "Fail: Last 30 days filter chip is Not displayed.");
		st.last30Days.click();

		if (Verify.objExists(st.thisMonth))
			Commentary.log(sa, LogStatus.INFO, "Pass: This Month filter chip is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "Fail: This Month filter chip is Not displayed.");
		st.thisMonth.click();

		if (Verify.objExists(st.lastMonth))
			Commentary.log(sa, LogStatus.INFO, "Pass: Last Month filter chip is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "Fail: Last Month filter chip is Not displayed.");
		st.lastMonth.click();

		if (Verify.objExists(st.last3Months))
			Commentary.log(sa, LogStatus.INFO, "Pass: Last 3 Months filter chip is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "Fail: Last 3 Months filter chip is Not displayed.");

		st.scrollFilter();

		if (Verify.objExists(st.last6Months))
			Commentary.log(sa, LogStatus.INFO, "Pass: Last 6 Months filter chip is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "Fail: Last 6 Months filter chip is Not displayed.");
		st.last6Months.click();

		if (Verify.objExists(st.monthToDate))
			Commentary.log(sa, LogStatus.INFO, "Pass: Month to Date filter chip is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "Fail: Month to Date filter chip is Not displayed.");
		st.monthToDate.click();

		if (Verify.objExists(st.yearToDate))
			Commentary.log(sa, LogStatus.INFO, "Pass: Year to Date filter chip is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "Fail: Year to Date filter chip is Not displayed.");
		st.yearToDate.click();

		if (Verify.objExists(st.custom))
			Commentary.log(sa, LogStatus.INFO, "Pass: Custom filter chip is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "Fail: Custom filter chip is Not displayed.");

		sa.assertAll();
	}

	@Test (priority=6, enabled = false)
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

	@Test(priority=7, enabled = false)
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

	@Test(priority=8, enabled = false)
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

	@Test(priority=9, enabled = false)
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

	@Test(priority=10, enabled = true)
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

	@Test (priority=11, enabled = true)
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

	@Test(priority=12, enabled = true)
	public void TC12_ValidateRunningBalanceDefault() throws Exception { 

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that by default the Show Running balance toggle should be ON and \"Newest to Oldest\" should be selected.");

		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();

		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();

		bcc.selectAccount(sManualSaving);
		Thread.sleep(1000);

		if (tp.isRunningBalanceEnabled())
			Commentary.log(LogStatus.INFO, "PASS: Running Balance is enabled by default.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Running Balance is NOT enabled by default.");

		tp.buttonClose.click();
		tp.buttonSort.click();
		Thread.sleep(1000);

		if (Verify.objExists(tp.defaultSortByFilterSelected))
			Commentary.log(LogStatus.INFO, "PASS: Filter as \"Newest to Oldest\" is selected by default.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Filter as \"Newest to Oldest\" is NOT selected by default.");

		sa.assertAll();
	}

	@Test(priority=13, enabled = true)
	public void TC13_ValidateRunningBalanceCalculation() throws Exception { 

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO,	"["+h.getEngine()+"]: Verify Balance calculation for filter combination \"Newest to Oldest\" + \"Show Running Balance\" set to ON");

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
		
		FiltersPage fp = new FiltersPage();

		fp.selectFilter("Newest to Oldest");
		fp.clickShowResultsButton();

		if (tp.isRunningBalanceEnabled()) {
			Commentary.log(LogStatus.INFO, "Running balance is enabled by default.");
			tp.buttonApply.click();
			Thread.sleep(2000);
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is NOT enabled by default.");
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
	@Test(priority=14, enabled = true)
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
		
		FiltersPage fp = new FiltersPage();

		fp.selectFilter("Newest to Oldest");
		fp.clickShowResultsButton();

		if (tp.isRunningBalanceEnabled()) {
			Commentary.log(LogStatus.INFO, "Running balance is enabled by default.");
			tp.buttonApply.click();
			Thread.sleep(2000);
		} else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Running balance is NOT enabled by default.");
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

	@Test (priority=15, enabled = true)
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

	@Test (priority=16, enabled = true)
	public void TC16_VerifyTransactionSummary_PayeeScreen() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Adding an expense transaction and validating that the Payee details are updated on Transaction Summary page.");

		OverviewPage op = new OverviewPage();
		op.tapOnTransactionSummaryCard();

		TransactionSummaryPage ts = new TransactionSummaryPage();
		Verify.waitForObject(ts.payeeTab, 1);
		ts.payeeTab.click();

		Verify.waitForObject(ts.payeeTile, 1);
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
		
		Verify.waitForObject(op.addTransaction, 1);
		op.addTransaction.click();
		
		td.addTransaction(tRec);

		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount "+tRec.getAmount());

		Thread.sleep(3000);
		op.tapOnTransactionSummaryCard();
		Verify.waitForObject(ts.payeeTab, 1);
		ts.payeeTab.click();

		Verify.waitForObject(ts.payeeTile, 1);
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

	@Test (priority=17, enabled = true)
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
		Verify.waitForObject(ts.categoryTile, 1);

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
		Verify.waitForObject(ts.categoryTab, 1);
		ts.categoryTab.click();

		Verify.waitForObject(ts.categoryTile, 1);
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

	}