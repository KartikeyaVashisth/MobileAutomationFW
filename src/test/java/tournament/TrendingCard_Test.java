package tournament;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.OverviewPage;
import dugout.SignInPage;
import dugout.SpendingTrendPage;
import dugout.TransactionDetailPage;
import referee.Commentary;
import referee.Verify;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;

public class TrendingCard_Test extends Recovery {

	String sUserName = "kalyan.grandhi@quicken.com";
	String sPassword = "Intuit!1";
	String sDataset = "Budget_RollOver_Income";
	String sManualChecking = "Checking";

	@Test(priority = 1, enabled = false)
	public void TC1_test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		SignInPage signIn = new SignInPage();
		signIn.signIn(sUserName, sPassword, sDataset);

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Add a transaction for a category, date last 30 days and verify the amount reflects on trending card Category Total.");

		Double before, after, amount;
		String sCategory;

		OverviewPage op = new OverviewPage();
		op.tapOnTrendingCard();

		SpendingTrendPage st = new SpendingTrendPage();
		Verify.waitForObject(st.last30Days, 2);
		st.last30Days.click();
		Thread.sleep(1000);

		if (Verify.objExists(st.youDontHaveAnyTxns)) {
			sCategory = "Auto & Transport";
			before = h.processBalanceAmount("0.0");
			Commentary.log(LogStatus.INFO, "There are no transactions for [Last30Days]. Hence adding transaction for category [Auto & Transport] & validating it.");	
		}
		else {
			sCategory = st.getCategory();
			before = st.getAmount();
			Commentary.log(LogStatus.INFO, "Category ["+sCategory+"] total amount for Last 30 Days: ["+before+"]");	
		}

		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualChecking);
		tRec.setAmount("5.00");
		tRec.setCategory(sCategory);
		tRec.setDate(h.getPastDaysDate(2));

		st.navigateBackToDashboard();
		op.scrollToTop();

		op.addTransaction.click();
		
		TransactionDetailPage td = new TransactionDetailPage();
		
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount: "+tRec.getAmount());

		op.tapOnTrendingCard();
		Verify.waitForObject(st.last30Days, 2);
		st.last30Days.click();
		Thread.sleep(1000);
		
		after = st.getAmount();
		amount = h.processBalanceAmount(tRec.getAmount());

		Commentary.log(LogStatus.INFO, "Category ["+sCategory+"] total amount for last 30 days: ["+after+"]");

		if (before+amount==after)
			Commentary.log(LogStatus.INFO, "PASS: Total balance was ["+before+"], added expense transactions for ["+amount+"], now the category total balance shows ["+after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: The Category balance was ["+before+"], added expense transactions for ["+amount+"], now the category total balance shows ["+after+"]");

		sa.assertAll();	
	}

	@Test(priority = 2, enabled = false)
	public void TC2_test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Add a transaction for a category, date Current month and verify the amount reflects on trending card Category Total.");

		Double before, after, amount;
		String sCategory;

		OverviewPage op = new OverviewPage();
		op.tapOnTrendingCard();

		SpendingTrendPage st = new SpendingTrendPage();
		Verify.waitForObject(st.thisMonth, 2);
		st.thisMonth.click();
		Thread.sleep(1000);

		if (Verify.objExists(st.youDontHaveAnyTxns)) {
			sCategory = "Auto & Transport";
			before = h.processBalanceAmount("0.0");
			Commentary.log(LogStatus.INFO, "There are no transactions for [Current Month]. Hence adding transaction for category [Auto & Transport] & validating it.");	
		}
		else {
			sCategory = st.getCategory();
			before = st.getAmount();
			Commentary.log(LogStatus.INFO, "Category ["+sCategory+"] total amount for This Month: ["+before+"]");	
		}

		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualChecking);
		tRec.setAmount("5.00");
		tRec.setCategory(sCategory);
		tRec.setDate(h.getFutureDaysDate(0));

		st.navigateBackToDashboard();
		op.scrollToTop();

		op.addTransaction.click();
		
		TransactionDetailPage td = new TransactionDetailPage();
		
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount: "+tRec.getAmount());

		op.tapOnTrendingCard();
		Verify.waitForObject(st.thisMonth, 2);
		st.thisMonth.click();
		Thread.sleep(1000);
		
		after = st.getAmount();
		amount = h.processBalanceAmount(tRec.getAmount());

		Commentary.log(LogStatus.INFO, "Category ["+sCategory+"] total amount for This Month filter: ["+after+"]");

		if (before+amount==after)
			Commentary.log(LogStatus.INFO, "PASS: Total balance was ["+before+"], added expense transactions for ["+amount+"], now the category total balance shows ["+after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Category balance was ["+before+"], added expense transactions for ["+amount+"], now the category total balance shows ["+after+"]");

		sa.assertAll();	
	}

	@Test(priority = 3, enabled = false)
	public void TC3_test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Add a transaction for a category, dated last month and verify the amount reflects on trending card Category Total.");

		Double before, after, amount;
		String sCategory;

		OverviewPage op = new OverviewPage();
		op.tapOnTrendingCard();

		SpendingTrendPage st = new SpendingTrendPage();
		Verify.waitForObject(st.lastMonth, 2);
		st.lastMonth.click();
		Thread.sleep(1000);

		if (Verify.objExists(st.youDontHaveAnyTxns)) {
			sCategory = "Auto & Transport";
			before = h.processBalanceAmount("0.0");
			Commentary.log(LogStatus.INFO, "There are no transactions for [Last month]. Hence adding transaction for category [Auto & Transport] & validating it.");	
		}
		else {
			sCategory = st.getCategory();
			before = st.getAmount();
			Commentary.log(LogStatus.INFO, "Category ["+sCategory+"] total amount for LastMonth filter: ["+before+"]");	
		}

		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualChecking);
		tRec.setAmount("3.00");
		tRec.setCategory(sCategory);
		tRec.setDate(h.getLastMonthsDate());

		st.navigateBackToDashboard();
		op.scrollToTop();

		op.addTransaction.click();
		
		TransactionDetailPage td = new TransactionDetailPage();
		
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount: "+tRec.getAmount());

		op.tapOnTrendingCard();
		Verify.waitForObject(st.lastMonth, 2);
		st.lastMonth.click();
		Thread.sleep(1000);
		
		after = st.getAmount();
		amount = h.processBalanceAmount(tRec.getAmount());

		Commentary.log(LogStatus.INFO, "Category ["+sCategory+"] total amount for LastMonth Filter: ["+after+"]");

		if (before+amount==after)
			Commentary.log(LogStatus.INFO, "PASS: Total balance was ["+before+"], added expense transactions for ["+amount+"], now the category total balance shows ["+after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Category balance was ["+before+"], added expense transactions for ["+amount+"], now the category total balance shows ["+after+"]");

		sa.assertAll();	
	}

	@Test(priority = 4, enabled = false)
	public void TC4_test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Add a transaction for a category, dated MonthToDate and verify the amount reflects on trending card Category Total.");

		Double before, after, amount;
		String sCategory;

		OverviewPage op = new OverviewPage();
		op.tapOnTrendingCard();

		SpendingTrendPage st = new SpendingTrendPage();
		st.scrollFilter();
		Verify.waitForObject(st.monthToDate, 2);
		st.monthToDate.click();
		Thread.sleep(100);

		if (Verify.objExists(st.youDontHaveAnyTxns)) {
			sCategory = "Auto & Transport";
			before = h.processBalanceAmount("0.0");
			Commentary.log(LogStatus.INFO, "There are no transactions for [MonthToDate]. Hence adding transaction for category [Auto & Transport] & validating it.");	
		}
		else {
			sCategory = st.getCategory();
			before = st.getAmount();
			Commentary.log(LogStatus.INFO, "Category ["+sCategory+"] total amount for MonthToDate Filter ["+before+"]");	
		}

		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualChecking);
		tRec.setAmount("2.00");
		tRec.setCategory(sCategory);
		tRec.setDate(h.getFutureDaysDate(0));

		st.navigateBackToDashboard();
		op.scrollToTop();

		op.addTransaction.click();
		
		TransactionDetailPage td = new TransactionDetailPage();
		
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount: "+tRec.getAmount());

		op.tapOnTrendingCard();
		Verify.waitForObject(st.monthToDate, 2);
		st.monthToDate.click();
		Thread.sleep(1000);
		after = st.getAmount();
		amount = h.processBalanceAmount(tRec.getAmount());

		Commentary.log(LogStatus.INFO, "Category ["+sCategory+"] total amount for MonthToDate: ["+after+"]");

		if (before+amount==after)
			Commentary.log(LogStatus.INFO, "PASS: Total balance was ["+before+"], added expense transactions for ["+amount+"], now the category total balance shows ["+after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Category balance was ["+before+"], added expense transactions for ["+amount+"], now the category total balance shows ["+after+"]");

		sa.assertAll();	
	}

	@Test(priority = 5, enabled = false)
	public void TC5_test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Add a transaction for a category, dated yearToDate and verify the amount reflects on trending card Year To Date filter's Category Total.");

		Double before, after, amount;
		String sCategory;

		OverviewPage op = new OverviewPage();
		op.tapOnTrendingCard();

		SpendingTrendPage st = new SpendingTrendPage();
		st.scrollFilter();
		Verify.waitForObject(st.yearToDate, 2);
		st.yearToDate.click();
		Thread.sleep(500);

		if (Verify.objExists(st.youDontHaveAnyTxns)) {
			sCategory = "Auto & Transport";
			before = h.processBalanceAmount("0.0");
			Commentary.log(LogStatus.INFO, "There are no transactions for [yearToDate]. Hence adding transaction for category [Auto & Transport] & validating it.");	
		}
		else {
			sCategory = st.getCategory();
			before = st.getAmount();
			Commentary.log(LogStatus.INFO, "Category ["+sCategory+"] total amount for Year To Date: ["+before+"]");	
		}

		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualChecking);
		tRec.setAmount("1.00");
		tRec.setCategory(sCategory);
		tRec.setDate(h.getFutureDaysDate(0));

		st.navigateBackToDashboard();
		op.scrollToTop();

		op.addTransaction.click();
		
		TransactionDetailPage td = new TransactionDetailPage();
		
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount: "+tRec.getAmount());

		op.tapOnTrendingCard();
		st.scrollFilter();
		Verify.waitForObject(st.yearToDate, 2);
		st.yearToDate.click();
		Thread.sleep(500);
		
		after = st.getAmount();
		amount = h.processBalanceAmount(tRec.getAmount());

		Commentary.log(LogStatus.INFO, "Category ["+sCategory+"] total amount for YearToDate: ["+after+"]");

		if (before+amount==after)
			Commentary.log(LogStatus.INFO, "PASS: Total balance was ["+before+"], added expense transactions for ["+amount+"], now the category total balance shows ["+after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Category balance was ["+before+"], added expense transactions for ["+amount+"], now the category total balance shows ["+after+"]");

		sa.assertAll();		
	}

	@Test(priority = 6, enabled = false)
	public void TC6_test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Add a future dated transaction for a category, and verify it doesn't get reflected in MonthToDate Filter.");

		Double before, after, amount;
		String sCategory;

		OverviewPage op = new OverviewPage();
		op.tapOnTrendingCard();

		SpendingTrendPage st = new SpendingTrendPage();
		st.scrollFilter();
		Verify.waitForObject(st.monthToDate, 2);
		st.monthToDate.click();
		Thread.sleep(1000);

		if (Verify.objExists(st.youDontHaveAnyTxns)) {
			sCategory = "Entertainment";
			before = h.processBalanceAmount("0.0");
			Commentary.log(LogStatus.INFO, "There are no transactions for [MonthToDate]. Hence adding transaction for category [Entertainment] & validating it.");	
		}
		else {
			sCategory = st.getCategory();
			before = st.getAmount();
			Commentary.log(LogStatus.INFO, "Category ["+sCategory+"] total amount for MonthToDate Filter is: ["+before+"]");	
		}

		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualChecking);
		tRec.setAmount("6.00");
		tRec.setCategory(sCategory);
		tRec.setDate(h.getFutureDaysDate(1));

		st.navigateBackToDashboard();
		op.scrollToTop();

		op.addTransaction.click();
		TransactionDetailPage td = new TransactionDetailPage();
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount: "+tRec.getAmount());		

		op.tapOnTrendingCard();
		Verify.waitForObject(st.monthToDate, 2);
		st.monthToDate.click();
		Thread.sleep(500);
		after = st.getAmount();
		amount = h.processBalanceAmount(tRec.getAmount());

		Commentary.log(LogStatus.INFO, "Category ["+sCategory+"] total amount for MonthToDate shown on the screen: ["+after+"]");

		Integer balanceCompare = Double.compare(before, after);
		System.out.println(balanceCompare);

		if (balanceCompare==0)
			Commentary.log(LogStatus.INFO, "PASS: Total balance was ["+before+"], added future dated expense transactions for ["+amount+"], now the category total balance shows ["+after+"]. MonthToDate did not include future dated transaction.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Category balance was ["+before+"], added expense transactions for ["+amount+"], now the category total balance shows ["+after+"].  MonthToDate should not include future dated transactions.");

		sa.assertAll();	
	}

	@Test(priority = 7, enabled = false)
	public void TC7_test() throws Exception {

		/*This Testcase handles two scenarios
		 * 
		 * Scenario1: If there are no transactions for the filter
		 * If user adds an income transaction for the expense category for the filter, still the screen shows You don't have any transactions.
		 * 
		 * 
		 * Scenario2: If there are transactions
		 * If user adds an income transaction for the expense category for the filter, then filter should display Expense-Income amount on the screen.
		 * 
		 */

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Add an income transaction for a category, date last 30 days and verify the amount reflects on trending card Category Total.");

		Double before, after, amount;
		String sCategory;
		Boolean bHaveTxns = true;

		// get balances from accounts card
		OverviewPage op = new OverviewPage();
		op.tapOnTrendingCard();

		SpendingTrendPage st = new SpendingTrendPage();
		Verify.waitForObject(st.last30Days, 2);
		st.last30Days.click();
		Thread.sleep(1000);

		if (Verify.objExists(st.youDontHaveAnyTxns)) {
			sCategory = "Auto & Transport";
			before = h.processBalanceAmount("0.0");
			bHaveTxns = false;
			Commentary.log(LogStatus.INFO, "There are no transactions for [Last30Days]. Hence adding income transaction for category [Auto & Transport] & validating it.");	
		}
		else {
			sCategory = st.getCategory();
			before = st.getAmount();
			Commentary.log(LogStatus.INFO, "Category ["+sCategory+"] total amount for last 30 days: ["+before+"]");	
		}

		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualChecking);
		tRec.setAmount("1.00");
		tRec.setTransactionType("income");
		tRec.setCategory(sCategory);
		tRec.setDate(h.getPastDaysDate(2));

		st.navigateBackToDashboard();
		op.scrollToTop();

		op.addTransaction.click();
		TransactionDetailPage td = new TransactionDetailPage();
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type INCOME, amount: "+tRec.getAmount());

		op.tapOnTrendingCard();
		Verify.waitForObject(st.last30Days, 2);
		st.last30Days.click();
		Thread.sleep(1000);
		
		after = st.getAmount();
		amount = h.processBalanceAmount(tRec.getAmount());
		
		Commentary.log(LogStatus.INFO, "Category ["+sCategory+"] total amount for last 30 days: ["+after+"]");

		if (!bHaveTxns) {
			Integer balanceCompare = Double.compare(before, after);
			System.out.println(balanceCompare);

			if (balanceCompare==0)
				Commentary.log(LogStatus.INFO, "PASS: Total balance was ["+before+"], added Income transaction for ["+amount+"], now the category total balance shows ["+after+"]");
			else
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Total balance was ["+before+"], added Income transaction for ["+amount+"], now the category total balance shows ["+after+"]");		
		}
		else if (before >= amount) {
			if (before-amount==after)
				Commentary.log(LogStatus.INFO, "PASS: Category Total balance was ["+before+"], added Income transaction for ["+amount+"], now the category total balance shows ["+after+"]");
			else
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Category balance was ["+before+"], added Income transaction for ["+amount+"], now the category total balance shows ["+after+"]");	
		}

		sa.assertAll();	
	}
	
	@Test(priority = 8, enabled = false)
	public void TC8_test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify Category amounts on trending screen should appear in descending order.");

		OverviewPage op = new OverviewPage();
		op.tapOnTrendingCard();

		SpendingTrendPage st = new SpendingTrendPage();

		Double d1 = st.getAmount();
		st.scrollCategory();
		Double d2 = st.getAmount();
		Thread.sleep(1000);
		st.scrollCategory();
		Double d3 = st.getAmount();

		if (d1 >= d2 )
			Commentary.log(LogStatus.INFO, "PASS: Total for First Category  ["+d1+"]  is greater than or equal to second category ["+d2+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "Total for First Category  ["+d1+"]  should be greater than or equal to second category ["+d2+"]");

		if (d2 >= d3)
			Commentary.log(LogStatus.INFO, "PASS: Total for second Category  ["+d2+"]  is greater than or equal to third category ["+d3+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "Total for second Category  ["+d2+"]  should be greater than or equal to third category ["+d3+"]");

		sa.assertAll();	
	}

}
