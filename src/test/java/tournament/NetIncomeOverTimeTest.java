package tournament;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.NetIncomeOverTimePage;
import dugout.OverviewPage;
import dugout.SignInPage;
import dugout.SpendingOverTimePage;
import dugout.TransactionDetailPage;
import referee.Commentary;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;

public class NetIncomeOverTimeTest extends Recovery {
	
	String sUserName = "mobileautomation1@quicken.com";
	String sPassword = "Quicken@01";
	//String sDataset = "NetIncomeOverTime";
	String sDataset = "OnlineAcc_Automation";
	String sManualChecking = "Manual_Savings";
	
	@Test(priority = 0)
	public void NIOT1_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		String sMonth = h.getLastSixMonths()[0];
		String sActual;
		Commentary.log(LogStatus.INFO, "Verify by default current month is highlighted/selected on the graph");
		
		SignInPage signIn = new SignInPage();
		signIn.signIn(sUserName, sPassword, sDataset);
		
		// get balances from accounts card
		OverviewPage op = new OverviewPage();
		op.tapOnNetIncomeOverTimeCard();
		
		NetIncomeOverTimePage not = new NetIncomeOverTimePage();
		sActual = not.getSelectedMonth();
		
		if (sActual.equals(sMonth))
			Commentary.log(LogStatus.INFO, "PASS: NetIncome OverTime screen, by default current month "+sMonth+" is selected on the graph");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: NetIncome OverTime screen by default current month ["+sMonth+"] should be selected but "+sActual +" was seen");	
		
		sa.assertAll();
		
	}
	
	@Test(priority = 1)
	public void NIOT2_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		Commentary.log(LogStatus.INFO, "Verify past six months names appear on the NetIncome Over Time screen");
		
		SignInPage signIn = new SignInPage();
		//signIn.signIn(sUserName, sPassword, sDataset);
		
		// get balances from accounts card
		OverviewPage op = new OverviewPage();
		op.tapOnNetIncomeOverTimeCard();
		
		String [] lastSixMonths = h.getLastSixMonths();
		
		Integer iCount;
		
		NetIncomeOverTimePage not = new NetIncomeOverTimePage();
		
		for (iCount=0; iCount<lastSixMonths.length; iCount++) {
			if (not.verifyMonth(lastSixMonths[iCount]))
				Commentary.log(LogStatus.INFO, "PASS: NetIncome OverTime ["+lastSixMonths[iCount]+"] month appeared on the screen");
			else
				Commentary.log(sa, LogStatus.FAIL, "FAIL: NetIncome OverTime Card ["+lastSixMonths[iCount]+"] didn't appear on the screen");	
			
		}
		
		sa.assertAll();
		
	}
	
	@Test(priority = 2)
	public void NIOT3_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		Commentary.log(LogStatus.INFO, "Verify tapping on the month's graph display the particular month's data");
		
		SignInPage signIn = new SignInPage();
		//signIn.signIn(sUserName, sPassword, sDataset);
		
		// get balances from accounts card
		OverviewPage op = new OverviewPage();
		op.tapOnNetIncomeOverTimeCard();
		
		String [] lastSixMonths = h.getLastSixMonths();
		
		Integer iCount;
		
		NetIncomeOverTimePage not = new NetIncomeOverTimePage();
		
		for (iCount=0; iCount<lastSixMonths.length; iCount++) {
			
			not.tapOnTheMonth(lastSixMonths[iCount]);
			
			if (not.verifyMonth(lastSixMonths[iCount]))
				Commentary.log(LogStatus.INFO, "PASS: NetIncome OverTime > tapping on the month ["+lastSixMonths[iCount]+"] displayed the month's name in total spending");
			else
				Commentary.log(sa, LogStatus.FAIL, "FAIL: NetIncome OverTime Card > tapping on the month ["+lastSixMonths[iCount]+"] didn't display the month's name in total spending");	
			
		}
		
		sa.assertAll();	
	}
	
	@Test(priority = 3)
	public void NIOT4_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Integer iCount;
		String sMonth, sActualTxnMonth;
		Helper h = new Helper();
		Commentary.log(LogStatus.INFO, "Verify tapping on the month's graph display the particular month's transactions");
		
		SignInPage signIn = new SignInPage();
		//signIn.signIn(sUserName, sPassword, sDataset);
		
		// get balances from accounts card
		OverviewPage op = new OverviewPage();
		op.tapOnNetIncomeOverTimeCard();
		
		String [] lastSixMonths = h.getLastSixMonths();
		
		
		
		NetIncomeOverTimePage not = new NetIncomeOverTimePage();
		
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
				Commentary.log(LogStatus.INFO, "PASS: NetIncome OverTime > tapped on the month ["+lastSixMonths[iCount]+"], verified the first transaction having same month");
			else
				Commentary.log(LogStatus.FAIL, "FAIL: NetIncome OverTime > tapped on the month ["+lastSixMonths[iCount]+"], First transaction having date showing different month "+sActualTxnMonth);
				
		}
		
		sa.assertAll();
		
	}
	
	@Test(priority = 4)
	public void NIOT5_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Integer retval;
		Double dBeforeAmount, dAfterAmount, dAmount, dExpected;
		Helper h = new Helper();
		Commentary.log(LogStatus.INFO, "Add expenditure transaction for the current month. Verify the amount for current month considers the amount on NetIncomeOverTime ");
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualChecking);
		tRec.setAmount("5.00");
		tRec.setTransactionType("expense");
		tRec.setCategory("Parking");
		h.getContext();
		dAmount=h.processBalanceAmount(tRec.getAmount());
		
//		SignInPage signIn = new SignInPage();
//		signIn.signIn(sUserName, sPassword, sDataset);
		
		// get balances from accounts card
		OverviewPage op = new OverviewPage();
		op.tapOnNetIncomeOverTimeCard();
		
		NetIncomeOverTimePage not = new NetIncomeOverTimePage();
		dBeforeAmount = not.getSelectedMonthAmount();
		Commentary.log(LogStatus.INFO, "PASS: NetIncomeOverTime  > Before adding the transaction, the current month total spending shows ["+dBeforeAmount+"]");
		
		not.backButtonOnHeader.click();
		Thread.sleep(2000);
		
		op.scrollToTop();
		op.addTransaction.click();
		TransactionDetailPage td = new TransactionDetailPage();
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount"+tRec.getAmount());
		
		op.tapOnNetIncomeOverTimeCard();
		dAfterAmount = not.getSelectedMonthAmount();
		dExpected=dBeforeAmount-dAmount;
		retval = Double.compare(dExpected, dAfterAmount);
		
		System.out.println("Expected: "+dExpected+" Actual amount: "+dAfterAmount);
		Commentary.log(LogStatus.INFO, "Expected NetIncome for the current month ["+dExpected+"], the actual NetIncome amount "+dAfterAmount);
		
		if (retval == 0)
			Commentary.log(LogStatus.PASS, "PASS: Expected Total NetIncome for the current month ["+dExpected+"], the actual NetIncome amount "+dAfterAmount);
		else
			Commentary.log(LogStatus.FAIL, "FAIL: Expected Total NetIncome for the current month ["+dExpected+"], but the actual NetIncome amount "+dAfterAmount);
		
		sa.assertAll();
		
	}
	
	@Test(priority = 5)
	public void NIOT6_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Integer retval;
		Double dBeforeAmount, dAfterAmount, dAmount, dExpected;
		Helper h = new Helper();
		Commentary.log(LogStatus.INFO, "Add a future dated expenditure transaction for the current month. Verify the amount for current month considers the amount on NetIncomeOverTime ");
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualChecking);
		tRec.setAmount("5.00");
		tRec.setTransactionType("expense");
		tRec.setCategory("Parking");
		tRec.setDate(h.getFutureDaysDate(1));
		h.getContext();
		dAmount=h.processBalanceAmount(tRec.getAmount());
		
//		SignInPage signIn = new SignInPage();
//		signIn.signIn(sUserName, sPassword, sDataset);
		
		// get balances from accounts card
		OverviewPage op = new OverviewPage();
		op.tapOnNetIncomeOverTimeCard();
		
		NetIncomeOverTimePage not = new NetIncomeOverTimePage();
		dBeforeAmount = not.getSelectedMonthAmount();
		Commentary.log(LogStatus.INFO, "PASS: NetIncome OverTime > Before adding the transaction, the current month total spending shows ["+dBeforeAmount+"]");
		
		not.backButtonOnHeader.click();
		Thread.sleep(2000);
		
		op.scrollToTop();
		op.addTransaction.click();
		TransactionDetailPage td = new TransactionDetailPage();
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount "+tRec.getAmount()+" date of the transaction "+tRec.getDate());
		
		op.tapOnNetIncomeOverTimeCard();
		dAfterAmount = not.getSelectedMonthAmount();
		dExpected=dBeforeAmount-dAmount;
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
	public void NIOT7_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Integer retval;
		Double dBeforeAmount, dAfterAmount, dAmount, dExpected;
		Helper h = new Helper();
		Commentary.log(LogStatus.INFO, "Add an income transaction for the current month. Verify NetIncome over time should consider it.");
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualChecking);
		tRec.setAmount("5.00");
		tRec.setTransactionType("income");
		tRec.setCategory("Net Salary");
		h.getContext();
		dAmount = h.processBalanceAmount(tRec.getAmount());
		
		// get balances from accounts card
		OverviewPage op = new OverviewPage();
		op.tapOnNetIncomeOverTimeCard();
		
		NetIncomeOverTimePage not = new NetIncomeOverTimePage();
		dBeforeAmount = not.getSelectedMonthAmount();
		Commentary.log(LogStatus.INFO, "PASS: NetIncome OverTime > Before adding the transaction, the current month total NetIncome shows ["+dBeforeAmount+"]");
		
		not.backButtonOnHeader.click();
		Thread.sleep(2000);
		
		op.scrollToTop();
		op.addTransaction.click();
		TransactionDetailPage td = new TransactionDetailPage();
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type income, amount"+tRec.getAmount());
		
		op.tapOnNetIncomeOverTimeCard();
		dAfterAmount = not.getSelectedMonthAmount();
		dExpected = dBeforeAmount+dAmount;
		
		retval = Double.compare(dExpected, dAfterAmount);
		
		System.out.println("Expected: "+dExpected+" Actual amount: "+dAfterAmount);
		Commentary.log(LogStatus.INFO, "Expected Total NetIncome for the current month ["+dExpected+"], the actual NetIncome amount "+dAfterAmount);
		
		if (retval == 0)
			Commentary.log(LogStatus.PASS, "PASS: Expected Total NetIncome for the current month ["+dExpected+"], the actual spending amount "+dAfterAmount);
		else
			Commentary.log(LogStatus.FAIL, "FAIL: Expected Total NetIncome for the current month ["+dExpected+"], but the actual NetIncome amount "+dAfterAmount);
		
		sa.assertAll();
		
	}
	
	@Test(priority = 7)
	public void NIOT8_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Integer retval;
		Double dBeforeAmount, dAfterAmount, dAmount, dExpected;
		Helper h = new Helper();
		Commentary.log(LogStatus.INFO, "Add a Future Dated income transaction for the current month. Verify NetIncome over time should consider it.");
		
		TransactionRecord tRec = new TransactionRecord();
		tRec.setAccount(sManualChecking);
		tRec.setAmount("5.00");
		tRec.setTransactionType("income");
		tRec.setCategory("Net Salary");
		tRec.setDate(h.getFutureDaysDate(1));
		h.getContext();
		dAmount = h.processBalanceAmount(tRec.getAmount());
		
		// get balances from accounts card
		OverviewPage op = new OverviewPage();
		op.tapOnNetIncomeOverTimeCard();
		
		NetIncomeOverTimePage not = new NetIncomeOverTimePage();
		dBeforeAmount = not.getSelectedMonthAmount();
		Commentary.log(LogStatus.INFO, "PASS: NetIncome OverTime > Before adding the transaction, the current month total NetIncome shows ["+dBeforeAmount+"]");
		
		not.backButtonOnHeader.click();
		Thread.sleep(2000);
		
		op.scrollToTop();
		op.addTransaction.click();
		TransactionDetailPage td = new TransactionDetailPage();
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type income, amount"+tRec.getAmount());
		
		op.tapOnNetIncomeOverTimeCard();
		dAfterAmount = not.getSelectedMonthAmount();
		dExpected = dBeforeAmount+dAmount;
		
		retval = Double.compare(dExpected, dAfterAmount);
		
		System.out.println("Expected: "+dExpected+" Actual amount: "+dAfterAmount);
		Commentary.log(LogStatus.INFO, "Expected Total NetIncome for the current month ["+dExpected+"], the actual NetIncome amount "+dAfterAmount);
		
		if (retval == 0)
			Commentary.log(LogStatus.PASS, "PASS: Expected Total NetIncome for the current month ["+dExpected+"], the actual spending amount "+dAfterAmount);
		else
			Commentary.log(LogStatus.FAIL, "FAIL: Expected Total NetIncome for the current month ["+dExpected+"], but the actual NetIncome amount "+dAfterAmount);
		
		sa.assertAll();
		
	}

}
