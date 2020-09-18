package tournament;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.OverviewPage;
import dugout.SignInPage;
import dugout.SpendingOverTimePage;
import dugout.TransactionDetailPage;
import referee.Commentary;
import referee.Verify;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;

public class SpendingOverTimeTest extends Recovery {
	
	String sUserName = "mobileautomation1@quicken.com";
	String sPassword = "Quicken@01";
	String sDataset = "OnlineAcc_Automation";
	//String sDataset = "SpendingOverTime";
	//String sManualChecking = "Checking_manual";
	String sManualSavings = "Manual_Savings";
	
	@Test(priority = 0, enabled = false)
	public void SOT1_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		SignInPage signIn = new SignInPage();
		signIn.signIn(sUserName, sPassword, sDataset);
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that by default, current month is highlighted/selected on the graph.");
		
		String sMonth = h.getLastSixMonths()[0];
		String sActual;
		
		OverviewPage op = new OverviewPage();
		op.tapOnSpendingOverTimeCard();
		
		SpendingOverTimePage sot = new SpendingOverTimePage();
		Verify.waitForObject(sot.totalSpendingCurrentMonth, 2);
		sActual = sot.getSelectedMonth();
		
		if (sActual.equals(sMonth))
			Commentary.log(LogStatus.INFO, "PASS: Spending OverTime screen, by default current month, "+sMonth+" is selected on the graph.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Spending OverTime screen by default current month, ["+sMonth+"] should be selected but "+sActual +" was seen");	
		
		sa.assertAll();
	}
	
	@Test(priority = 1, enabled = false)
	public void SOT2_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify past six months names appear on the Spending Over Time screen.");
		
		OverviewPage op = new OverviewPage();
		op.tapOnSpendingOverTimeCard();
		
		String [] lastSixMonths = h.getLastSixMonths();
		
		Integer iCount;
		
		SpendingOverTimePage sot = new SpendingOverTimePage();
		Verify.waitForObject(sot.totalSpendingCurrentMonth, 2);
		
		for (iCount=0; iCount<lastSixMonths.length; iCount++) {
			if (sot.verifyMonth(lastSixMonths[iCount]))
				Commentary.log(LogStatus.INFO, "PASS: Spending OverTime ["+lastSixMonths[iCount]+"] month appeared on the screen.");
			else
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Spending OverTime Card ["+lastSixMonths[iCount]+"] didn't appear on the screen.");		
		}
		sa.assertAll();
	}
	
	@Test(priority = 2, enabled = false)
	public void SOT3_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: To Verify that tapping on the month's graph displays the particular month's data.");
		
		OverviewPage op = new OverviewPage();
		op.tapOnSpendingOverTimeCard();
		
		String [] lastSixMonths = h.getLastSixMonths();
		Integer iCount;
		
		SpendingOverTimePage sot = new SpendingOverTimePage();
		Verify.waitForObject(sot.totalSpendingCurrentMonth, 2);
		
		for (iCount=0; iCount<lastSixMonths.length; iCount++) {
			
			sot.tapOnTheMonth(lastSixMonths[iCount]);
			
			if (sot.verifyMonth(lastSixMonths[iCount]))
				Commentary.log(LogStatus.INFO, "PASS: Spending OverTime > Tapping on the month ["+lastSixMonths[iCount]+"] displayed the month's name in Total Spending.");
			else
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Spending OverTime Card > Tapping on the month ["+lastSixMonths[iCount]+"] didn't display the month's name in Total Spending.");	
		}
		sa.assertAll();	
	}
	
	@Test(priority = 3, enabled = false)
	public void SOT4_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify tapping on the month's graph display the particular month's transactions.");
		
		Integer iCount;
		String sMonth, sActualTxnMonth;
		
		OverviewPage op = new OverviewPage();
		op.tapOnSpendingOverTimeCard();
		
		String [] lastSixMonths = h.getLastSixMonths();
		
		SpendingOverTimePage sot = new SpendingOverTimePage();
		Verify.waitForObject(sot.totalSpendingCurrentMonth, 2);
		
		for (iCount=0; iCount<lastSixMonths.length; iCount++) {
			
			sMonth = lastSixMonths[iCount];
			
			sot.tapOnTheMonth(sMonth);
			
			if (sot.getMonthStringFromFirstTxn().equals("youDontHaveAnyTxns")) { 
				
				Commentary.log(LogStatus.INFO, "PASS: Spending OverTime > ["+lastSixMonths[iCount]+"] month has no transactions. Hence month is not verified.");
				continue;
			}
			
			sActualTxnMonth = sot.getMonthStringFromFirstTxn();
			System.out.println(sActualTxnMonth);
			
			if (sActualTxnMonth.equals(sMonth))
				Commentary.log(LogStatus.INFO, "PASS: Spending OverTime > Tapped on the month ["+lastSixMonths[iCount]+"], verified the first transaction having same month.");
			else
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Spending OverTime > Tapped on the month ["+lastSixMonths[iCount]+"], First transaction having date showing different month: "+sActualTxnMonth);	
		}
		sa.assertAll();
	}
	
	@Test(priority = 4, enabled = false)
	public void SOT5_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Add expenditure transaction for the current month. Verify the amount for current month considers the amount on spending card ");
		
		Integer retval;
		Double dBeforeAmount, dAfterAmount, dAmount, dExpected;
		String payeeName = "Payee_"+h.getCurrentTime();
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualSavings);
		tRec.setAmount("5.00");
		tRec.setPayee(payeeName);
		tRec.setTransactionType("expense");
		tRec.setCategory("Parking");
		
		dAmount=h.processBalanceAmount(tRec.getAmount());
		
		// get balances from accounts card
		OverviewPage op = new OverviewPage();
		op.tapOnSpendingOverTimeCard();
		
		SpendingOverTimePage sot = new SpendingOverTimePage();
		Verify.waitForObject(sot.totalSpendingCurrentMonth, 2);
		
		dBeforeAmount = sot.getSelectedMonthAmount();
		Commentary.log(LogStatus.INFO, "PASS: Spending OverTime > Before adding the transaction, the current month total spending shows: ["+dBeforeAmount+"]");
		
		sot.backButtonOnHeader.click();
		Thread.sleep(2000);
		
		op.scrollToTop();
		op.addTransaction.click();
		TransactionDetailPage td = new TransactionDetailPage();
		Verify.waitForObject(td.buttonDone, 2);
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount: "+tRec.getAmount());
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		
		op.tapOnSpendingOverTimeCard();
		Verify.waitForObject(sot.totalSpendingCurrentMonth, 2);
		
		dAfterAmount = sot.getSelectedMonthAmount();
		dExpected=dBeforeAmount+dAmount;
		retval = Double.compare(dExpected, dAfterAmount);
			
		if (retval == 0)
			Commentary.log(LogStatus.PASS, "PASS: Expected Total spending for the current month ["+dExpected+"], the actual spending amount: "+dAfterAmount);
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Expected Total spending for the current month ["+dExpected+"], but the actual spending amount: "+dAfterAmount);
		
		sa.assertAll();
	}
	
	@Test(priority = 5, enabled = false)
	public void SOT6_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Add a future dated expenditure transaction for the current month. Verify the amount for current month considers the amount on spending card.");
		
		Integer retval;
		Double dBeforeAmount, dAfterAmount, dAmount, dExpected;
		String payeeName = "Payee_"+h.getCurrentTime();
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualSavings);
		tRec.setAmount("5.00");
		tRec.setPayee(payeeName);
		tRec.setTransactionType("expense");
		tRec.setCategory("Parking");
		tRec.setDate(h.getFutureDaysDate(1));
		
		dAmount=h.processBalanceAmount(tRec.getAmount());
		
		// get balances from accounts card
		OverviewPage op = new OverviewPage();
		op.tapOnSpendingOverTimeCard();
		
		SpendingOverTimePage sot = new SpendingOverTimePage();
		Verify.waitForObject(sot.totalSpendingCurrentMonth, 2);
		
		dBeforeAmount = sot.getSelectedMonthAmount();
		Commentary.log(LogStatus.INFO, "PASS: Spending OverTime > Before adding the transaction, the current month total spending shows: ["+dBeforeAmount+"]");
		
		sot.backButtonOnHeader.click();
		Thread.sleep(2000);
		
		op.scrollToTop();
		op.addTransaction.click();
		TransactionDetailPage td = new TransactionDetailPage();
		Verify.waitForObject(td.buttonDone, 2);
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account: ["+tRec.getAccount()+"], transaction type expense, amount: "+tRec.getAmount()+" date of the transaction: "+tRec.getDate());
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		
		op.tapOnSpendingOverTimeCard();
		Verify.waitForObject(sot.totalSpendingCurrentMonth, 2);
		
		dAfterAmount = sot.getSelectedMonthAmount();
		dExpected=dBeforeAmount+dAmount;
		retval = Double.compare(dExpected, dAfterAmount);
			
		if (retval == 0)
			Commentary.log(LogStatus.PASS, "PASS: Expected Total spending for the current month ["+dExpected+"], the actual spending amount: "+dAfterAmount);
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Expected Total spending for the current month ["+dExpected+"], but the actual spending amount: "+dAfterAmount);
		
		sa.assertAll();
	}
	
	@Test(priority = 6, enabled = false)
	public void SOT7_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Add an income transaction for the current month. Verify spending over time shouldn't consider it.");
		
		Integer retval;
		Double dBeforeAmount, dAfterAmount;
		String payeeName = "Payee_"+h.getCurrentTime();
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualSavings);
		tRec.setAmount("5.00");
		tRec.setPayee(payeeName);
		tRec.setTransactionType("income");
		tRec.setCategory("Div Income");
		
		OverviewPage op = new OverviewPage();
		op.tapOnSpendingOverTimeCard();
		
		SpendingOverTimePage sot = new SpendingOverTimePage();
		Verify.waitForObject(sot.totalSpendingCurrentMonth, 2);
		
		dBeforeAmount = sot.getSelectedMonthAmount();
		Commentary.log(LogStatus.INFO, "PASS: Spending OverTime > Before adding the transaction, the current month total spending shows: ["+dBeforeAmount+"]");
		
		sot.backButtonOnHeader.click();
		Thread.sleep(2000);
		
		op.scrollToTop();
		op.addTransaction.click();
		TransactionDetailPage td = new TransactionDetailPage();
		Verify.waitForObject(td.buttonDone, 2);
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account: ["+tRec.getAccount()+"], transaction type income, amount: "+tRec.getAmount());
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		
		op.tapOnSpendingOverTimeCard();
		Verify.waitForObject(sot.totalSpendingCurrentMonth, 2);
		
		dAfterAmount = sot.getSelectedMonthAmount();
		
		retval = Double.compare(dBeforeAmount, dAfterAmount);
			
		if (retval == 0)
			Commentary.log(LogStatus.PASS, "PASS: Expected Total spending for the current month: ["+dBeforeAmount+"], the actual spending amount: "+dAfterAmount);
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Expected Total spending for the current month: ["+dBeforeAmount+"], but the actual spending amount: "+dAfterAmount);
		
		sa.assertAll();
	}
	
}
