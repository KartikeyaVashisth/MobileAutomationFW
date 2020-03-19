package tournament;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.OverviewPage;
import dugout.SignInPage;
import dugout.SpendingOverTimePage;
import dugout.TransactionDetailPage;
import referee.Commentary;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;

public class SpendingOverTimeTest extends Recovery {
	
	String sUserName = "mobileautomation1@quicken.com";
	String sPassword = "Quicken@01";
	String sDataset = "OnlineAcc_Automation";
	//String sDataset = "SpendingOverTime";
	//String sManualChecking = "Checking_manual";
	String sManualChecking = "Manual_Savings";
	
	
	@Test(priority = 0)
	public void SOT1_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		String sMonth = h.getLastSixMonths()[0];
		String sActual;
		Commentary.log(LogStatus.INFO, "Verify by default current month is highlighted/selected on the graph");
		
		SignInPage signIn = new SignInPage();
		signIn.signIn(sUserName, sPassword, sDataset);
		
		// get balances from accounts card
		OverviewPage op = new OverviewPage();
		op.tapOnSpendingOverTimeCard();
		
		SpendingOverTimePage sot = new SpendingOverTimePage();
		sActual = sot.getSelectedMonth();
		
		if (sActual.equals(sMonth))
			Commentary.log(LogStatus.INFO, "PASS: Spending OverTime screen, by default current month "+sMonth+" is selected on the graph");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Spending OverTime screen by default current month ["+sMonth+"] should be selected but "+sActual +" was seen");	
		
		sa.assertAll();
		
	}
	
	@Test(priority = 1)
	public void SOT2_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		Commentary.log(LogStatus.INFO, "Verify past six months names appear on the Spending Over Time screen");
		
		SignInPage signIn = new SignInPage();
		//signIn.signIn(sUserName, sPassword, sDataset);
		
		// get balances from accounts card
		OverviewPage op = new OverviewPage();
		op.tapOnSpendingOverTimeCard();
		
		String [] lastSixMonths = h.getLastSixMonths();
		
		Integer iCount;
		
		SpendingOverTimePage sot = new SpendingOverTimePage();
		
		for (iCount=0; iCount<lastSixMonths.length; iCount++) {
			if (sot.verifyMonth(lastSixMonths[iCount]))
				Commentary.log(LogStatus.INFO, "PASS: Spending OverTime ["+lastSixMonths[iCount]+"] month appeared on the screen");
			else
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Spending OverTime Card ["+lastSixMonths[iCount]+"] didn't appear on the screen");	
			
		}
		
		sa.assertAll();
		
	}
	
	@Test(priority = 2)
	public void SOT3_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		Commentary.log(LogStatus.INFO, "Verify tapping on the month's graph display the particular month's data");
		
		SignInPage signIn = new SignInPage();
		//signIn.signIn(sUserName, sPassword, sDataset);
		
		// get balances from accounts card
		OverviewPage op = new OverviewPage();
		op.tapOnSpendingOverTimeCard();
		
		String [] lastSixMonths = h.getLastSixMonths();
		
		Integer iCount;
		
		SpendingOverTimePage sot = new SpendingOverTimePage();
		
		for (iCount=0; iCount<lastSixMonths.length; iCount++) {
			
			sot.tapOnTheMonth(lastSixMonths[iCount]);
			
			if (sot.verifyMonth(lastSixMonths[iCount]))
				Commentary.log(LogStatus.INFO, "PASS: Spending OverTime > tapping on the month ["+lastSixMonths[iCount]+"] displayed the month's name in total spending");
			else
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Spending OverTime Card > tapping on the month ["+lastSixMonths[iCount]+"] didn't display the month's name in total spending");	
			
		}
		
		sa.assertAll();	
	}
	
	@Test(priority = 3)
	public void SOT4_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Integer iCount;
		String sMonth, sActualTxnMonth;
		Helper h = new Helper();
		Commentary.log(LogStatus.INFO, "Verify tapping on the month's graph display the particular month's transactions");
		
		SignInPage signIn = new SignInPage();
		//signIn.signIn(sUserName, sPassword, sDataset);
		
		// get balances from accounts card
		OverviewPage op = new OverviewPage();
		op.tapOnSpendingOverTimeCard();
		
		String [] lastSixMonths = h.getLastSixMonths();
		
		
		
		SpendingOverTimePage sot = new SpendingOverTimePage();
		
		for (iCount=0; iCount<lastSixMonths.length; iCount++) {
			
			sMonth = lastSixMonths[iCount];
			
			sot.tapOnTheMonth(sMonth);
			
			if (sot.getMonthStringFromFirstTxn().equals("youDontHaveAnyTxns")) { 
				
				Commentary.log(LogStatus.INFO, "PASS: Spending OverTime > ["+lastSixMonths[iCount]+"] month has no transactions. Hence month is not verified");
				continue;
			}
			sActualTxnMonth = sot.getMonthStringFromFirstTxn();
			
			System.out.println(sActualTxnMonth);
			if (sActualTxnMonth.equals(sMonth))
				Commentary.log(LogStatus.INFO, "PASS: Spending OverTime > tapped on the month ["+lastSixMonths[iCount]+"], verified the first transaction having same month");
			else
				Commentary.log(LogStatus.FAIL, "FAIL: Spending OverTime > tapped on the month ["+lastSixMonths[iCount]+"], First transaction having date showing different month "+sActualTxnMonth);
				
		}
		
		sa.assertAll();
		
	}
	
	@Test(priority = 4)
	public void SOT5_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Integer retval;
		Double dBeforeAmount, dAfterAmount, dAmount, dExpected;
		Helper h = new Helper();
		Commentary.log(LogStatus.INFO, "Add expenditure transaction for the current month. Verify the amount for current month considers the amount on spending card ");
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualChecking);
		tRec.setAmount("5.00");
		tRec.setTransactionType("expense");
		tRec.setCategory("Parking");
		h.getContext();
		dAmount=h.processBalanceAmount(tRec.getAmount());
		
		// get balances from accounts card
		OverviewPage op = new OverviewPage();
		op.tapOnSpendingOverTimeCard();
		
		SpendingOverTimePage sot = new SpendingOverTimePage();
		dBeforeAmount = sot.getSelectedMonthAmount();
		Commentary.log(LogStatus.INFO, "PASS: Spending OverTime > Before adding the transaction, the current month total spending shows ["+dBeforeAmount+"]");
		
		sot.backButtonOnHeader.click();
		Thread.sleep(2000);
		
		op.scrollToTop();
		op.addTransaction.click();
		TransactionDetailPage td = new TransactionDetailPage();
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount"+tRec.getAmount());
		
		op.tapOnSpendingOverTimeCard();
		dAfterAmount = sot.getSelectedMonthAmount();
		dExpected=dBeforeAmount+dAmount;
		retval = Double.compare(dExpected, dAfterAmount);
		
		System.out.println("Expected: "+dExpected+" Actual amount: "+dAfterAmount);
		Commentary.log(LogStatus.INFO, "Expected Total spending for the current month ["+dExpected+"], the actual spending amount "+dAfterAmount);
		
		if (retval == 0)
			Commentary.log(LogStatus.PASS, "PASS: Expected Total spending for the current month ["+dExpected+"], the actual spending amount "+dAfterAmount);
		else
			Commentary.log(LogStatus.FAIL, "FAIL: Expected Total spending for the current month ["+dExpected+"], but the actual spending amount "+dAfterAmount);
		
		sa.assertAll();
		
	}
	
	@Test(priority = 5)
	public void SOT6_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Integer retval;
		Double dBeforeAmount, dAfterAmount, dAmount, dExpected;
		Helper h = new Helper();
		Commentary.log(LogStatus.INFO, "Add a future dated expenditure transaction for the current month. Verify the amount for current month considers the amount on spending card ");
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualChecking);
		tRec.setAmount("5.00");
		tRec.setTransactionType("expense");
		tRec.setCategory("Parking");
		tRec.setDate(h.getFutureDaysDate(1));
		h.getContext();
		dAmount=h.processBalanceAmount(tRec.getAmount());
		
		// get balances from accounts card
		OverviewPage op = new OverviewPage();
		op.tapOnSpendingOverTimeCard();
		
		SpendingOverTimePage sot = new SpendingOverTimePage();
		dBeforeAmount = sot.getSelectedMonthAmount();
		Commentary.log(LogStatus.INFO, "PASS: Spending OverTime > Before adding the transaction, the current month total spending shows ["+dBeforeAmount+"]");
		
		sot.backButtonOnHeader.click();
		Thread.sleep(2000);
		
		op.scrollToTop();
		op.addTransaction.click();
		TransactionDetailPage td = new TransactionDetailPage();
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount "+tRec.getAmount()+" date of the transaction "+tRec.getDate());
		
		op.tapOnSpendingOverTimeCard();
		dAfterAmount = sot.getSelectedMonthAmount();
		dExpected=dBeforeAmount+dAmount;
		retval = Double.compare(dExpected, dAfterAmount);
		
		System.out.println("Expected: "+dExpected+" Actual amount: "+dAfterAmount);
		Commentary.log(LogStatus.INFO, "Expected Total spending for the current month ["+dExpected+"], the actual spending amount "+dAfterAmount);
		
		if (retval == 0)
			Commentary.log(LogStatus.PASS, "PASS: Expected Total spending for the current month ["+dExpected+"], the actual spending amount "+dAfterAmount);
		else
			Commentary.log(LogStatus.FAIL, "FAIL: Expected Total spending for the current month ["+dExpected+"], but the actual spending amount "+dAfterAmount);
		
		sa.assertAll();
		
	}
	
	@Test(priority = 6)
	public void SOT7_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Integer retval;
		Double dBeforeAmount, dAfterAmount;
		Helper h = new Helper();
		Commentary.log(LogStatus.INFO, "Add an income transaction for the current month. Verify spending over time should n't consider it.");
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualChecking);
		tRec.setAmount("5.00");
		tRec.setTransactionType("income");
		tRec.setCategory("Div Income");
		h.getContext();
		
		// get balances from accounts card
		OverviewPage op = new OverviewPage();
		op.tapOnSpendingOverTimeCard();
		
		SpendingOverTimePage sot = new SpendingOverTimePage();
		dBeforeAmount = sot.getSelectedMonthAmount();
		Commentary.log(LogStatus.INFO, "PASS: Spending OverTime > Before adding the transaction, the current month total spending shows ["+dBeforeAmount+"]");
		
		sot.backButtonOnHeader.click();
		Thread.sleep(2000);
		
		op.scrollToTop();
		op.addTransaction.click();
		TransactionDetailPage td = new TransactionDetailPage();
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type income, amount"+tRec.getAmount());
		
		op.tapOnSpendingOverTimeCard();
		dAfterAmount = sot.getSelectedMonthAmount();
		
		retval = Double.compare(dBeforeAmount, dAfterAmount);
		
		System.out.println("Expected: "+dBeforeAmount+" Actual amount: "+dAfterAmount);
		Commentary.log(LogStatus.INFO, "Expected Total spending for the current month ["+dBeforeAmount+"], the actual spending amount "+dAfterAmount);
		
		if (retval == 0)
			Commentary.log(LogStatus.PASS, "PASS: Expected Total spending for the current month ["+dBeforeAmount+"], the actual spending amount "+dAfterAmount);
		else
			Commentary.log(LogStatus.FAIL, "FAIL: Expected Total spending for the current month ["+dBeforeAmount+"], but the actual spending amount "+dAfterAmount);
		
		sa.assertAll();
		
	}
	
	
	
	
	
	
	
	
	
}
