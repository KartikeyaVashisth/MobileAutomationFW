package tournament;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.NetIncomeOverTimePage;
import dugout.OverviewPage;
import dugout.SignInPage;
import dugout.TransactionDetailPage;
import referee.Commentary;
import referee.Verify;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;

public class NetIncomeOverTimeTest extends Recovery {
	
	String sUserName = "mobileautomation1@quicken.com";
	String sPassword = "Quicken@01";
	//String sDataset = "NetIncomeOverTime";
	String sDataset = "OnlineAcc_Automation";
	String sManualSavings = "Manual_Savings";
	
	@Test(priority = 0, enabled = true)
	public void NIOT1_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		SignInPage signIn = new SignInPage();
		signIn.signIn(sUserName, sPassword, sDataset);
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that by default current month is highlighted/selected on the graph.");
		
		String sMonth = h.getLastSixMonths()[0];
		String sActual;
		
		OverviewPage op = new OverviewPage();
		op.tapOnNetIncomeOverTimeCard();
		
		NetIncomeOverTimePage not = new NetIncomeOverTimePage();
		Verify.waitForObject(not.netIncomeCurrentMonth, 2);
		
		sActual = not.getSelectedMonth();
		
		if (sActual.equals(sMonth))
			Commentary.log(LogStatus.INFO, "PASS: NetIncome OverTime screen, by default current month, "+sMonth+" is selected on the graph.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: NetIncome OverTime screen, by default current month, ["+sMonth+"] should be selected but "+sActual +" was seen.");	
		sa.assertAll();	
	}
	
	@Test(priority = 1, enabled = true)
	public void NIOT2_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that past six months names appear on the Net Income By Month Card.");
		
		OverviewPage op = new OverviewPage();
		op.tapOnNetIncomeOverTimeCard();
		
		String [] lastSixMonths = h.getLastSixMonths();
		Integer iCount;
		
		NetIncomeOverTimePage not = new NetIncomeOverTimePage();
		Verify.waitForObject(not.netIncomeCurrentMonth, 2);
		
		for (iCount=0; iCount<lastSixMonths.length; iCount++) {
			if (not.verifyMonth(lastSixMonths[iCount]))
				Commentary.log(LogStatus.INFO, "PASS: NetIncome OverTime ["+lastSixMonths[iCount]+"] month appeared on the page.");
			else
				Commentary.log(sa, LogStatus.FAIL, "FAIL: NetIncome OverTime Card ["+lastSixMonths[iCount]+"] didn't appeared on the page.");		
		}
		sa.assertAll();	
	}
	
	@Test(priority = 2, enabled = true)
	public void NIOT3_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that tapping on the month's graph displays the particular month's data.");
		
		OverviewPage op = new OverviewPage();
		op.tapOnNetIncomeOverTimeCard();
		
		String [] lastSixMonths = h.getLastSixMonths();
		Integer iCount;
		
		NetIncomeOverTimePage not = new NetIncomeOverTimePage();
		Verify.waitForObject(not.netIncomeCurrentMonth, 2);
		
		for (iCount=0; iCount<lastSixMonths.length; iCount++) {
			
			not.tapOnTheMonth(lastSixMonths[iCount]);
			
			if (not.verifyMonth(lastSixMonths[iCount]))
				Commentary.log(LogStatus.INFO, "PASS: NetIncome OverTime > Tapping on the month ["+lastSixMonths[iCount]+"] displayed the month's name in Total Spending.");
			else
				Commentary.log(sa, LogStatus.FAIL, "FAIL: NetIncome OverTime Card > Tapping on the month ["+lastSixMonths[iCount]+"] didn't display the month's name in Total Spending.");	
		}
		sa.assertAll();	
	}
	
	@Test(priority = 3, enabled = true)
	public void NIOT4_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that tapping on the Month's graph displays the particular month's transactions.");
		
		Integer iCount;
		String sMonth, sActualTxnMonth;
		
		OverviewPage op = new OverviewPage();
		op.tapOnNetIncomeOverTimeCard();
		
		String [] lastSixMonths = h.getLastSixMonths();
		
		NetIncomeOverTimePage not = new NetIncomeOverTimePage();
		Verify.waitForObject(not.netIncomeCurrentMonth, 2);
		
		for (iCount=0; iCount<lastSixMonths.length; iCount++) {
			
			sMonth = lastSixMonths[iCount];
			
			not.tapOnTheMonth(sMonth);
			
			if (not.getMonthStringFromFirstTxn().equals("youDontHaveAnyTxns")) { 
				
				Commentary.log(LogStatus.INFO, "PASS: Spending OverTime > ["+lastSixMonths[iCount]+"] month has no transactions. Hence month is not verified");
				continue;
			}
			
			sActualTxnMonth = not.getMonthStringFromFirstTxn();
			System.out.println(sActualTxnMonth);
			
			if (sActualTxnMonth.equals(sMonth))
				Commentary.log(LogStatus.INFO, "PASS: NetIncome OverTime > tapped on the month ["+lastSixMonths[iCount]+"], verified the first transaction having same month.");
			else
				Commentary.log(sa, LogStatus.FAIL, "FAIL: NetIncome OverTime > tapped on the month ["+lastSixMonths[iCount]+"], First transaction having date showing different month "+sActualTxnMonth);		
		}
		sa.assertAll();
	}
	
	@Test(priority = 4, enabled = true)
	public void NIOT5_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Add expenditure transaction for the current month. Verify the amount for current month considers the amount on Net Income By Month card.");
		
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
		
		OverviewPage op = new OverviewPage();
		op.tapOnNetIncomeOverTimeCard();
		
		NetIncomeOverTimePage not = new NetIncomeOverTimePage();
		Verify.waitForObject(not.netIncomeCurrentMonth, 2);
		
		dBeforeAmount = not.getSelectedMonthAmount();
		Commentary.log(LogStatus.INFO, "PASS: NetIncomeOverTime  > Before adding the transaction, the current month total spending shows: ["+dBeforeAmount+"]");
		
		not.backButtonOnHeader.click();
		Thread.sleep(2000);
		
		op.scrollToTop();
		op.addTransaction.click();
		TransactionDetailPage td = new TransactionDetailPage();
		Verify.waitForObject(td.buttonDone, 2);
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account: ["+tRec.getAccount()+"], transaction type expense, amount: "+tRec.getAmount());
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		
		op.tapOnNetIncomeOverTimeCard();
		Verify.waitForObject(not.netIncomeCurrentMonth, 2);
		
		dAfterAmount = not.getSelectedMonthAmount();
		dExpected=dBeforeAmount-dAmount;
		retval = Double.compare(dExpected, dAfterAmount);
		
		if (retval == 0)
			Commentary.log(LogStatus.PASS, "PASS: Expected Total NetIncome for the current month: ["+dExpected+"], the actual NetIncome amount: "+dAfterAmount);
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Expected Total NetIncome for the current month: ["+dExpected+"], but the actual NetIncome amount: "+dAfterAmount);
		
		sa.assertAll();
	}
	
	@Test(priority = 5, enabled = true)
	public void NIOT6_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Add a future dated expenditure transaction for the current month. Verify the amount for current month considers the amount on Net Income By Month card.");
		
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
		
		OverviewPage op = new OverviewPage();
		op.tapOnNetIncomeOverTimeCard();
		
		NetIncomeOverTimePage not = new NetIncomeOverTimePage();
		Verify.waitForObject(not.netIncomeCurrentMonth, 2);
		
		dBeforeAmount = not.getSelectedMonthAmount();
		Commentary.log(LogStatus.INFO, "PASS: NetIncome OverTime > Before adding the transaction, the current month total spending shows: ["+dBeforeAmount+"]");
		
		not.backButtonOnHeader.click();
		Thread.sleep(2000);
		
		op.scrollToTop();
		op.addTransaction.click();
		TransactionDetailPage td = new TransactionDetailPage();
		Verify.waitForObject(td.buttonDone, 2);
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account: ["+tRec.getAccount()+"], transaction type expense, amount: "+tRec.getAmount()+" date of the transaction: "+tRec.getDate());
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		
		op.tapOnNetIncomeOverTimeCard();
		Verify.waitForObject(not.netIncomeCurrentMonth, 2);
		
		dAfterAmount = not.getSelectedMonthAmount();
		dExpected=dBeforeAmount-dAmount;
		retval = Double.compare(dExpected, dAfterAmount);
			
		if (retval == 0)
			Commentary.log(LogStatus.PASS, "PASS: Expected Total spending for the current month: ["+dExpected+"], the actual spending amount: "+dAfterAmount);
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Expected Total spending for the current month: ["+dExpected+"], but the actual spending amount: "+dAfterAmount);
		
		sa.assertAll();
	}
	
	@Test(priority = 6, enabled = true)
	public void NIOT7_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Add an Income transaction for the current month. Verify Net Income By Month card should consider it.");
		
		Integer retval;
		Double dBeforeAmount, dAfterAmount, dAmount, dExpected;
		String payeeName = "Payee_"+h.getCurrentTime();
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualSavings);
		tRec.setAmount("5.00");
		tRec.setPayee(payeeName);
		tRec.setTransactionType("income");
		tRec.setCategory("Net Salary");
		
		dAmount = h.processBalanceAmount(tRec.getAmount());
		
		OverviewPage op = new OverviewPage();
		op.tapOnNetIncomeOverTimeCard();
		
		NetIncomeOverTimePage not = new NetIncomeOverTimePage();
		Verify.waitForObject(not.netIncomeCurrentMonth, 2);
		
		dBeforeAmount = not.getSelectedMonthAmount();
		Commentary.log(LogStatus.INFO, "PASS: NetIncome OverTime > Before adding the transaction, the current month total NetIncome shows: ["+dBeforeAmount+"]");
		
		not.backButtonOnHeader.click();
		Thread.sleep(2000);
		
		op.scrollToTop();
		op.addTransaction.click();
		TransactionDetailPage td = new TransactionDetailPage();
		Verify.waitForObject(td.buttonDone, 2);
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account: ["+tRec.getAccount()+"], transaction type Income, amount: "+tRec.getAmount());
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		
		op.tapOnNetIncomeOverTimeCard();
		Verify.waitForObject(not.netIncomeCurrentMonth, 2);
		
		dAfterAmount = not.getSelectedMonthAmount();
		dExpected = dBeforeAmount+dAmount;
		
		retval = Double.compare(dExpected, dAfterAmount);
			
		if (retval == 0)
			Commentary.log(LogStatus.PASS, "PASS: Expected Total NetIncome for the current month: ["+dExpected+"], the actual spending amount: "+dAfterAmount);
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Expected Total NetIncome for the current month: ["+dExpected+"], but the actual NetIncome amount: "+dAfterAmount);
		
		sa.assertAll();
	}
	
	@Test(priority = 7, enabled = true)
	public void NIOT8_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Add a Future Dated income transaction for the current month. Verify Net Income By Month card should consider it.");
		
		Integer retval;
		Double dBeforeAmount, dAfterAmount, dAmount, dExpected;
		String payeeName = "Payee_"+h.getCurrentTime();
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualSavings);
		tRec.setAmount("5.00");
		tRec.setPayee(payeeName);
		tRec.setTransactionType("income");
		tRec.setCategory("Net Salary");
		tRec.setDate(h.getFutureDaysDate(1));
		
		dAmount = h.processBalanceAmount(tRec.getAmount());
		
		OverviewPage op = new OverviewPage();
		op.tapOnNetIncomeOverTimeCard();
		
		NetIncomeOverTimePage not = new NetIncomeOverTimePage();
		Verify.waitForObject(not.netIncomeCurrentMonth, 2);
		
		dBeforeAmount = not.getSelectedMonthAmount();
		Commentary.log(LogStatus.INFO, "PASS: NetIncome OverTime > Before adding the transaction, the current month Total NetIncome shows: ["+dBeforeAmount+"]");
		
		not.backButtonOnHeader.click();
		Thread.sleep(2000);
		
		op.scrollToTop();
		op.addTransaction.click();
		TransactionDetailPage td = new TransactionDetailPage();
		Verify.waitForObject(td.buttonDone, 2);
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account: ["+tRec.getAccount()+"], transaction type Income, Amount: "+tRec.getAmount());
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		
		op.tapOnNetIncomeOverTimeCard();
		Verify.waitForObject(not.netIncomeCurrentMonth, 2);
		
		dAfterAmount = not.getSelectedMonthAmount();
		dExpected = dBeforeAmount+dAmount;
		
		retval = Double.compare(dExpected, dAfterAmount);
		
		if (retval == 0)
			Commentary.log(LogStatus.PASS, "PASS: Expected Total NetIncome for the current month: ["+dExpected+"], the actual Spending Amount: "+dAfterAmount);
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Expected Total NetIncome for the current month: ["+dExpected+"], but the actual NetIncome Amount: "+dAfterAmount);
		
		sa.assertAll();
	}
}
