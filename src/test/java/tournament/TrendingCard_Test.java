package tournament;

import java.util.HashMap;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.OverviewPage;
import dugout.SettingsPage;
import dugout.SignInPage;
import dugout.SpendingTrendPage;
import dugout.TransactionDetailPage;
import dugout.TransactionsPage;
import dugout.WelcomePage;
import referee.Commentary;
import referee.Verify;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;
import support.UserName;

public class TrendingCard_Test extends Recovery {

	//String sUserName = "kalyan.grandhi@quicken.com";
	//String sPassword = "Intuit!1";
	//String sDataset = "Budget_RollOver_Income";
	String sPassword = "Quicken@01";
	String sDataset = "topTrending-automation";
	String sManualChecking = "manual_Checking";
	String sSavings = "manual_Savings";
	String sCredit = "manual_Credit";
	
	
	
public String getUsername_basedOnEnv() throws Exception {
		
		UserName un = new UserName();
		un.stage_ios = "stg-toptrending-ios++@stage.com";
		un.stage_android = "stg-toptrending-android++@stage.com";
		un.prod_ios = "";
		un.prod_android = "";
		return un.getUserName();
		
	}

	@Test(priority = 1, enabled = true)
	public void TC1_test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		String sUsername = getUsername_basedOnEnv();
		WelcomePage w = new WelcomePage();
		w.setEnvironment(h.getEnv());
		
		SignInPage signIn = new SignInPage();
		signIn.signIn(sUsername, sPassword, sDataset);

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Add a transaction for a category, date last 30 days and verify the amount reflects on trending card Category Total.");

		Double before, after, amount;
		String sCategory;

		OverviewPage op = new OverviewPage();
//		op.tapOnTrendingCard();

		SpendingTrendPage st = new SpendingTrendPage();
		st.navigateToTopTrendingCategoriesCard();
		
		Verify.waitForObject(st.last30Days, 2);
		st.last30Days.click();
		Thread.sleep(3000);

		Verify.waitForObject(st.youDontHaveAnyTxns, 1);
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

//		op.tapOnTrendingCard();
		st.navigateToTopTrendingCategoriesCard();
		
		Verify.waitForObject(st.last30Days, 2);
		st.last30Days.click();
		Thread.sleep(1000);
		
		after = st.getAmount();
		amount = h.processBalanceAmount(tRec.getAmount());

		Commentary.log(LogStatus.INFO, "Category ["+sCategory+"] total amount for last 30 days: ["+after+"]");
		
		int compare = Double.compare(before+amount, after);
		
//		if (before+amount==after)
		if(compare == 0)
			Commentary.log(LogStatus.INFO, "PASS: Total balance was ["+before+"], added expense transactions for ["+amount+"], now the category total balance shows ["+after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: The Category balance was ["+before+"], added expense transactions for ["+amount+"], now the category total balance shows ["+after+"]");

		sa.assertAll();	
	}
	
	@Test (priority = 2, enabled = true)
	public void TC2_test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		OverviewPage op = new OverviewPage();
		op.navigateToSpendingByCategory();
		
		Thread.sleep(1000);
		
		SpendingTrendPage st= new SpendingTrendPage();
		st.deleteSpendingTrendTransactions();
		Thread.sleep(1000);
		
	}
	
	
	@Test(priority = 3, enabled = true)
	public void TC3_test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		SpendingTrendPage st = new SpendingTrendPage();
		
		Double before, after, amount1;
		String sCategory;
		

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify the split category and Split amount for trending card after adding a split transaction.");

		String payeename = h.getCurrentTime();
		
		HashMap<Integer,String> amount = new HashMap<Integer, String>();
		HashMap<Integer,String> cat = new HashMap<Integer, String>();
		HashMap<Integer,String[]> tags = new HashMap<Integer, String[]>();
		
		OverviewPage op = new OverviewPage();
		op.navigateToSpendingByCategory();
		
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
		
		st.backButtonOnHeader.click();
		Thread.sleep(1000);
		
		cat.put(1, sCategory);
		cat.put(2, "Education");
		amount.put(1, "12.00");
		amount.put(2, "10.00");
		tags.put(1, new String[] {"Tax Related"});
		tags.put(2, new String[] {"Tax Related"});
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("30.00");
		//tRec.setTransactionType("expense");
		tRec.setAccount(sManualChecking);
		tRec.setPayee(payeename);
		
		if(Verify.objExists(op.addTransaction))
			op.addTransaction.click();
		
		TransactionDetailPage td = new TransactionDetailPage();
		td.addSplit(tRec, amount, cat, tags);
		Thread.sleep(1000);
		
		op.navigateToSpendingByCategory();
	
		String splitCategory = st.categoryName1.getText().replace("Total for ", "");
		System.out.println(splitCategory);
		
		if(splitCategory.equals(sCategory))	{
			Commentary.log(LogStatus.PASS, "split category appeared");
		}
		
		else {
			Commentary.log(LogStatus.FAIL, "Split category did not appear");
		}
		
		if(Verify.objExists(st.amount1)) {
			Commentary.log(LogStatus.PASS, "Split category amount is visible ["+st.amount1.getText()+"]");
		}
		else {
			
			Commentary.log(LogStatus.FAIL, "Amount  did not appear");
		}
		
		Thread.sleep(1000);
		st.scrollCategory();
		Thread.sleep(3000);
		System.out.println(st.categoryName1.getText());
		if(Verify.objExists(st.categoryName1)) {
			Commentary.log(LogStatus.PASS, "Second split child category appeared");
		}
		else {
			Commentary.log(LogStatus.FAIL, "Second split category did not appear");
		}
		
		st.backButtonOnHeader.click();
		Thread.sleep(1000);
		
		op.navigateToAllTransactions();
		TransactionsPage tp = new TransactionsPage();
		tp.searchRecentTransaction(payeename);
		tp.tapOnFirstTransaction();
		
		td.scroll_down();
		td.deleteTransaction.click();
		td.deleteTransactionAlertButton.click();
		Thread.sleep(1000);		
}
	
	@Test(priority = 4, enabled = true)
	public void TC4_test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Add a transaction for a category, date Current month and verify the amount reflects on trending card Category Total.");

		Double before, after, amount;
		String sCategory;

		OverviewPage op = new OverviewPage();
//		op.tapOnTrendingCard();

		SpendingTrendPage st = new SpendingTrendPage();
		st.navigateToTopTrendingCategoriesCard();
		
		Verify.waitForObject(st.thisMonth, 2);
		st.thisMonth.click();
		Thread.sleep(3000);

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

//		op.tapOnTrendingCard();
		st.navigateToTopTrendingCategoriesCard();
		
		Verify.waitForObject(st.thisMonth, 2);
		st.thisMonth.click();
		Thread.sleep(1000);
		
		after = st.getAmount();
		amount = h.processBalanceAmount(tRec.getAmount());

		Commentary.log(LogStatus.INFO, "Category ["+sCategory+"] total amount for This Month filter: ["+after+"]");
		
		int compare = Double.compare(before+amount, after);
		
//		if (before+amount==after)
		if(compare == 0)
			Commentary.log(LogStatus.INFO, "PASS: Total balance was ["+before+"], added expense transactions for ["+amount+"], now the category total balance shows ["+after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Category balance was ["+before+"], added expense transactions for ["+amount+"], now the category total balance shows ["+after+"]");

		sa.assertAll();	
	}

	@Test(priority = 5, enabled = true)
	public void TC5_test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Add a transaction for a category, dated last month and verify the amount reflects on trending card Category Total.");

		Double before, after, amount;
		String sCategory;

		OverviewPage op = new OverviewPage();
//		op.tapOnTrendingCard();

		SpendingTrendPage st = new SpendingTrendPage();
		st.navigateToTopTrendingCategoriesCard();
		
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

//		op.tapOnTrendingCard();
		st.navigateToTopTrendingCategoriesCard();
		
		Verify.waitForObject(st.lastMonth, 2);
		st.lastMonth.click();
		Thread.sleep(2000);
		
		after = st.getAmount();
		amount = h.processBalanceAmount(tRec.getAmount());

		Commentary.log(LogStatus.INFO, "Category ["+sCategory+"] total amount for LastMonth Filter: ["+after+"]");
		
		int compare = Double.compare(before+amount, after);
		
//		if (before+amount==after)
		if(compare == 0)
			Commentary.log(LogStatus.INFO, "PASS: Total balance was ["+before+"], added expense transactions for ["+amount+"], now the category total balance shows ["+after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Category balance was ["+before+"], added expense transactions for ["+amount+"], now the category total balance shows ["+after+"]");

		sa.assertAll();	
	}

	@Test(priority = 6, enabled = true)
	public void TC6_test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Add a transaction for a category, dated MonthToDate and verify the amount reflects on trending card Category Total.");

		Double before, after, amount;
		String sCategory;

		OverviewPage op = new OverviewPage();
//		op.tapOnTrendingCard();

		SpendingTrendPage st = new SpendingTrendPage();
		st.navigateToTopTrendingCategoriesCard();
		
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

//		op.tapOnTrendingCard();
		st.navigateToTopTrendingCategoriesCard();
		
		Verify.waitForObject(st.monthToDate, 2);
		st.monthToDate.click();
		Thread.sleep(1000);
		after = st.getAmount();
		amount = h.processBalanceAmount(tRec.getAmount());

		Commentary.log(LogStatus.INFO, "Category ["+sCategory+"] total amount for MonthToDate: ["+after+"]");

		int compare = Double.compare(before+amount, after);
		
//		if (before+amount==after)
		if(compare == 0)
			Commentary.log(LogStatus.INFO, "PASS: Total balance was ["+before+"], added expense transactions for ["+amount+"], now the category total balance shows ["+after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Category balance was ["+before+"], added expense transactions for ["+amount+"], now the category total balance shows ["+after+"]");

		sa.assertAll();	
	}

	@Test(priority = 7, enabled = true)
	public void TC7_test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Add a transaction for a category, dated yearToDate and verify the amount reflects on trending card Year To Date filter's Category Total.");

		Double before, after, amount;
		String sCategory;

		OverviewPage op = new OverviewPage();
//		op.tapOnTrendingCard();

		SpendingTrendPage st = new SpendingTrendPage();
		st.navigateToTopTrendingCategoriesCard();
		
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

//		op.tapOnTrendingCard();
		st.navigateToTopTrendingCategoriesCard();
		
		st.scrollFilter();
		Verify.waitForObject(st.yearToDate, 2);
		st.yearToDate.click();
		Thread.sleep(500);
		
		after = st.getAmount();
		amount = h.processBalanceAmount(tRec.getAmount());

		Commentary.log(LogStatus.INFO, "Category ["+sCategory+"] total amount for YearToDate: ["+after+"]");
		
		int compare = Double.compare(before+amount, after);
		
//		if (before+amount==after)
		if(compare == 0)
			Commentary.log(LogStatus.INFO, "PASS: Total balance was ["+before+"], added expense transactions for ["+amount+"], now the category total balance shows ["+after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Category balance was ["+before+"], added expense transactions for ["+amount+"], now the category total balance shows ["+after+"]");

		sa.assertAll();		
	}

	@Test(priority = 8, enabled = true)
	public void TC8_test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Add a future dated transaction for a category, and verify it doesn't get reflected in MonthToDate Filter.");

		Double before, after, amount;
		String sCategory;

		OverviewPage op = new OverviewPage();
//		op.tapOnTrendingCard();

		SpendingTrendPage st = new SpendingTrendPage();
		st.navigateToTopTrendingCategoriesCard();
		
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

//		op.tapOnTrendingCard();
		st.navigateToTopTrendingCategoriesCard();
		
		Verify.waitForObject(st.monthToDate, 2);
		st.monthToDate.click();
		Thread.sleep(500);
		after = st.getAmount();
		amount = h.processBalanceAmount(tRec.getAmount());

		Commentary.log(LogStatus.INFO, "Category ["+sCategory+"] total amount for MonthToDate shown on the screen: ["+after+"]");

		Integer balanceCompare = Double.compare(before, after);
		System.out.println(balanceCompare);
		
		if(balanceCompare == 0)
			Commentary.log(LogStatus.INFO, "PASS: Total balance was ["+before+"], added future dated expense transactions for ["+amount+"], now the category total balance shows ["+after+"]. MonthToDate did not include future dated transaction.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Category balance was ["+before+"], added expense transactions for ["+amount+"], now the category total balance shows ["+after+"].  MonthToDate should not include future dated transactions.");

		sa.assertAll();	
	}

	@Test(priority = 9, enabled = true)
	public void TC9_test() throws Exception {

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
//		op.tapOnTrendingCard();

		SpendingTrendPage st = new SpendingTrendPage();
		st.navigateToTopTrendingCategoriesCard();
		
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

//		op.tapOnTrendingCard();
		st.navigateToTopTrendingCategoriesCard();
		
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
	
	@Test(priority = 10, enabled = true)
	public void TC10_test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify Category amounts on trending screen should appear in descending order.");

//		OverviewPage op = new OverviewPage();
//		op.tapOnTrendingCard();

		SpendingTrendPage st = new SpendingTrendPage();
		st.navigateToTopTrendingCategoriesCard();

		Double d1 = st.getAmount();
		st.scrollCategory();
		Double d2 = st.getAmount();
		Thread.sleep(1000);
		st.scrollCategory();
		Double d3 = st.getAmount();

		if (d1 >= d2 )
			Commentary.log(LogStatus.INFO, "PASS: Total for First Category  ["+d1+"]  is greater than or equal to second category ["+d2+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Total for First Category  ["+d1+"]  should be greater than or equal to second category ["+d2+"]");

		if (d2 >= d3)
			Commentary.log(LogStatus.INFO, "PASS: Total for second Category  ["+d2+"]  is greater than or equal to third category ["+d3+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Total for second Category  ["+d2+"]  should be greater than or equal to third category ["+d3+"]");

		sa.assertAll();	
	}
	
//	@Test(priority = 9, enabled = true)
//	public void TC9_test() throws Exception {
//
//		SoftAssert sa = new SoftAssert();
//		Helper h = new Helper();
//		
//		SpendingTrendPage st = new SpendingTrendPage();
//		
//		Double before, after, amount1;
//		String sCategory;
//		
//
//		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify the split category and Split amount for trending card after adding a split transaction.");
//
//		String payeename = h.getCurrentTime();
//		
//		HashMap<Integer,String> amount = new HashMap<Integer, String>();
//		HashMap<Integer,String> cat = new HashMap<Integer, String>();
//		HashMap<Integer,String[]> tags = new HashMap<Integer, String[]>();
//		
//		OverviewPage op = new OverviewPage();
//		op.navigateToSpendingByCategory();
//		
//		if (Verify.objExists(st.youDontHaveAnyTxns)) {
//			sCategory = "Entertainment";
//			before = h.processBalanceAmount("0.0");
//			Commentary.log(LogStatus.INFO, "There are no transactions for [MonthToDate]. Hence adding transaction for category [Entertainment] & validating it.");	
//		}
//		else {
//			sCategory = st.getCategory();
//			before = st.getAmount();
//			Commentary.log(LogStatus.INFO, "Category ["+sCategory+"] total amount for MonthToDate Filter is: ["+before+"]");	
//		}
//		
//		st.backButtonOnHeader.click();
//		Thread.sleep(1000);
//		
//		
//		
//		cat.put(1, sCategory);
//		cat.put(2, "Education");
//		amount.put(1, "12.00");
//		amount.put(2, "10.00");
//		tags.put(1, new String[] {"Tax Related"});
//		tags.put(2, new String[] {"Tax Related"});
//		
//		TransactionRecord tRec = new TransactionRecord();
//		tRec.setAmount("30.00");
//		//tRec.setTransactionType("expense");
//		tRec.setAccount(sManualChecking);
//		tRec.setPayee(payeename);
//		
//		
//		
//		
//		if(Verify.objExists(op.addTransaction))
//			op.addTransaction.click();
//		
//		TransactionDetailPage td = new TransactionDetailPage();
//		td.addSplit(tRec, amount, cat, tags);
//		Thread.sleep(1000);
//		
//		op.navigateToSpendingByCategory();
//	
//		String splitCategory = st.categoryName1.getText().replace("Total for ", "");
//		System.out.println(splitCategory);
//		
//		if(splitCategory.equals(sCategory))	{
//			
//			Commentary.log(LogStatus.PASS, "split category appeared");
//			
//		}
//		
//		else {
//			Commentary.log(LogStatus.FAIL, "Split category did not appear");
//		}
//		
//		if(Verify.objExists(st.amount1)) {
//			Commentary.log(LogStatus.PASS, "Split category amount is visible ["+st.amount1.getText()+"]");
//		}
//		else {
//			
//			Commentary.log(LogStatus.FAIL, "Amount  did not appear");
//			
//		}
//		
//		Thread.sleep(1000);
//		st.scrollCategory();
//		Thread.sleep(3000);
//		System.out.println(st.categoryName1.getText());
//		if(Verify.objExists(st.categoryName1)) {
//			Commentary.log(LogStatus.PASS, "Second split child category appeared");
//		}
//		else {
//			
//			Commentary.log(LogStatus.FAIL, "Second split category did not appear");
//			
//		}
//		
//		st.backButtonOnHeader.click();
//		Thread.sleep(1000);
//		
//		op.navigateToAllTransactions();
//		TransactionsPage tp = new TransactionsPage();
//		tp.searchRecentTransaction(payeename);
//		tp.tapOnFirstTransaction();
//		td.deleteTransation();
//		Thread.sleep(1000);		
//		
//
//}
}
