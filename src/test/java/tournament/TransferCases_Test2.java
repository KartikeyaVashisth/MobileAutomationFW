package tournament;

import java.util.HashMap;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.BankingAndCreditCardPage;
import dugout.OverviewPage;
import dugout.SignInPage;
import dugout.TransactionDetailPage;
import dugout.TransactionsPage;
import io.appium.java_client.MobileBy;
import referee.Commentary;
import referee.Verify;
import support.Engine;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;

public class TransferCases_Test2 extends Recovery {
	

	String sUserName = "mobileautomation1@quicken.com";
	String sPassword = "Quicken@01";
	String sDataset = "Transfers_automation";
	String sChecking = "Checking";
	String sCreditCard ="Credit Card";
	String sManualSaving = "manual_savings";
	String sOnlineSaving = "onl_savings";
	String backButton1_ios = "Banking & Credit";
	String sSavings = "Savings";
	String sDataset1 = "OnlineAcc_Automation";
	String sOnlineChecking = "Checking1 XX8651";
	String sSaving = "Saving XX3867";
	String sManualSavings = "Manual_Savings";
	
	@Test(priority = 1)
	public void TR1_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		String payeename = h.getCurrentTime();
		
		SignInPage signIn = new SignInPage();
		signIn.signIn(sUserName, sPassword, sDataset);
		
		
		
		
		Commentary.log(LogStatus.INFO,	"["+h.getEngine()+"]: Creating a split transfer from Checking to Saving account and verifying balances.");
		
		String sCheckingLabel_before, sSavingLabel_before, sCheckingAccount_before, sSavingAccount_before, sCheckingLabel_after, sSavingLabel_after, sCheckingAccount_after, sSavingAccount_after ;
		String time = "Payee_"+h.getCurrentTime();
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		sCheckingLabel_before = bcc.getCheckingBalance();
		sSavingLabel_before = bcc.getSavingsBalance();
		sCheckingAccount_before = bcc.getCheckingAccountBalance();
		sSavingAccount_before = bcc.getSavingsAccountBalance();
		Double dCheckingLabel_before = h.processBalanceAmount(sCheckingLabel_before.replace("SubTotal: ", ""));
		Double dSavingLabel_before = h.processBalanceAmount(sSavingLabel_before.replace("SubTotal: ", ""));
		Double dCheckingAccount_before = h.processBalanceAmount(sCheckingAccount_before.replace("Account Balance: ", ""));
		Double dSavingAccount_before = h.processBalanceAmount(sSavingAccount_before.replace("Account Balance: ", ""));

		Verify.waitForObject(bcc.allTransactionButton, 2);
		bcc.allTransactionButton.click();
		TransactionsPage tp = new TransactionsPage();
		Verify.waitForObject(tp.addTransaction, 2);
		tp.addTransaction.click();
		
		HashMap<Integer,String> amount = new HashMap<Integer, String>();
		HashMap<Integer,String> cat = new HashMap<Integer, String>();
		HashMap<Integer,String[]> tags = new HashMap<Integer, String[]>();
		
		cat.put(1, "Transfer ["+sSavings+"]");
		cat.put(2, "Travel");
		amount.put(1, "3.00");
		amount.put(2, "6.00");
		tags.put(1, new String[] {"Tax Related"});
		tags.put(2, new String[] {"Tax Related"});
		
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		
		tRec.setAmount("20.00");
		tRec.setPayee(time);
		tRec.setAccount(sChecking);
		tRec.setCategory("Education");
		tRec.setTransactionType("expense");
		
		td.addTransaction(tRec);
		
		tp.searchRecentTransaction(tRec.getPayee());
		
		tp.tapOnFirstTransaction();;
		
		TransactionDetailPage tdp = new TransactionDetailPage();
		tdp.addSplit(amount, cat, tags);
		 
		Thread.sleep(3000);
		tp.backButton.click();
		Thread.sleep(1000);
		
		sCheckingLabel_after = bcc.getCheckingBalance();
		sSavingLabel_after = bcc.getSavingsBalance();
		sCheckingAccount_after = bcc.getCheckingAccountBalance();
		sSavingAccount_after = bcc.getSavingsAccountBalance();
		Double dCheckingLabel_after = h.processBalanceAmount(sCheckingLabel_after.replace("SubTotal: ", ""));
		Double dSavingLabel_after = h.processBalanceAmount(sSavingLabel_after.replace("SubTotal: ", ""));
		Double dCheckingAccount_after = h.processBalanceAmount(sCheckingAccount_after.replace("Account Balance: ", ""));
		Double dSavingAccount_after = h.processBalanceAmount(sSavingAccount_after.replace("Account Balance: ", ""));
		Double d1 = Double.parseDouble(tRec.getAmount());
		Double d = Double.parseDouble(amount.get(2));
		
		
		int retval = Double.compare(dCheckingLabel_after+d1, dCheckingLabel_before);
		if(retval == 0) {
			Commentary.log(LogStatus.INFO, "PASS: Checking Label balance was ["+dCheckingLabel_before+"], done expense transfer transaction of ["+tRec.getAmount()+"], now the Checking Label balance shows ["+dCheckingLabel_after+"]");
		}
		else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Checking Label balance was ["+dCheckingLabel_before+"], done expense transfer transaction of ["+tRec.getAmount()+"], now the Checking Label balance shows ["+dCheckingLabel_after+"]");
		}
		
		int retval1 = Double.compare(dSavingLabel_after-d, dSavingLabel_before);	
		if (retval1==0)
			Commentary.log(LogStatus.INFO, "PASS: Savings Label balance was ["+dSavingLabel_before+"], received transfer transaction of ["+amount.get(2)+"], now the Savings Label balance shows ["+dSavingLabel_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Savings Label balance was ["+dSavingLabel_before+"], received transfer transaction of ["+amount.get(2)+"], now the Savings Label balance shows ["+dSavingLabel_after+"]");

		int retval2 = Double.compare(dCheckingAccount_after+d1, dCheckingAccount_before);
		if (retval2==0)
			Commentary.log(LogStatus.INFO, "PASS: Checking Account balance was ["+dCheckingAccount_before+"], done expense transfer transaction of ["+tRec.getAmount()+"], now the Checking Account balance shows ["+dCheckingAccount_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Checking Account balance was ["+dCheckingAccount_before+"], done expense transfer transaction of ["+tRec.getAmount()+"], now the Checking Account balance shows ["+dCheckingAccount_after+"]");
		
		int retval3 = Double.compare(dCheckingAccount_after+d1, dCheckingAccount_before);
		if (retval3==0)
			Commentary.log(LogStatus.INFO, "PASS: Savings Account balance was ["+dSavingAccount_before+"], received transfer transaction of ["+amount.get(2)+"], now the Savings Account balance shows ["+dSavingAccount_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Savings Account balance was ["+dSavingAccount_before+"], received transfer transaction of ["+amount.get(2)+"], now the Savings Account balance shows ["+dSavingAccount_after+"]");

	
		
		
		sa.assertAll();

}
	
	
	@Test(priority = 2)
	public void TR2_test() throws Exception{

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO,	"["+h.getEngine()+"]: Verify ,for Splitted transfer transaction child transaction should not be able to edit and save transaction. ");
		
		String sChecking_before, sSaving_before, sChecking_afterDeleting, sSaving_afterDeleting ;
		String time = "Payee_"+h.getCurrentTime();
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		Verify.waitForObject(bcc.allTransactionButton, 2);
		bcc.allTransactionButton.click();
		TransactionsPage tp = new TransactionsPage();
		Verify.waitForObject(tp.addTransaction, 2);
		tp.addTransaction.click();
		
		HashMap<Integer,String> amount = new HashMap<Integer, String>();
		HashMap<Integer,String> cat = new HashMap<Integer, String>();
		HashMap<Integer,String[]> tags = new HashMap<Integer, String[]>();
		
		cat.put(1, "Transfer ["+sSavings+"]");
		cat.put(2, "Travel");
		amount.put(1, "3.00");
		amount.put(2, "6.00");
		tags.put(1, new String[] {"Tax Related"});
		tags.put(2, new String[] {"Tax Related"});
		
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		
		tRec.setAmount("20.00");
		tRec.setPayee(time);
		tRec.setAccount(sChecking);
		tRec.setCategory("Education");
		tRec.setTransactionType("expense");
		
		td.addTransaction(tRec);
		
		tp.searchRecentTransaction(tRec.getPayee());
		tp.tapOnFirstTransaction();;
		
		TransactionDetailPage tdp = new TransactionDetailPage();
		tdp.addSplit(amount, cat, tags);
		
		tp.searchRecentTransaction(tRec.getPayee());
		tp.tapOnFirstTransaction();
		
		if (h.getEngine().equals("ios")) {
			
			JavascriptExecutor js1 = (JavascriptExecutor) Engine.getDriver() ;
		    HashMap scrollObject = new HashMap();
		    scrollObject.put("direction", "down");
		    js1.executeScript("mobile: scroll", scrollObject);
		}
			
			
		else {
			
			String sText = "Go to other side";
			Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ sText + "\").instance(0))"));
		
			
		}
		
		Thread.sleep(1000);
		td.buttonGoToOtherSide.click();
		
		td.amount.click();
		td.enterAmount("12.50");
		td.buttonSave1.click();
		Thread.sleep(1000);
		
		if(Verify.objExists(td.errormessage)) {
			
			Commentary.log(LogStatus.PASS, "Other side of the split transfer can not be edited");
		}
		else {
			Commentary.log(LogStatus.FAIL, "User is able to edit the other side of the split transfer which is ot expected");
		}
		
		 
		sa.assertAll();
	}
		
	@Test(priority = 3)
	public void TR3_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO,	"["+h.getEngine()+"]: Verify ,for Splitted transfer transaction child transaction should not be able to edit and save transaction. ");
		
		String sChecking_before, sSaving_before, sChecking_afterDeleting, sSaving_afterDeleting ;
		String time = "Payee_"+h.getCurrentTime();
		
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		Verify.waitForObject(bcc.allTransactionButton, 2);
		bcc.allTransactionButton.click();
		TransactionsPage tp = new TransactionsPage();
		Verify.waitForObject(tp.addTransaction, 2);
		tp.addTransaction.click();
		
		HashMap<Integer,String> amount = new HashMap<Integer, String>();
		HashMap<Integer,String> cat = new HashMap<Integer, String>();
		HashMap<Integer,String[]> tags = new HashMap<Integer, String[]>();
		
		cat.put(1, "Transfer ["+sSavings+"]");
		cat.put(2, "Travel");
		amount.put(1, "3.00");
		amount.put(2, "6.00");
		tags.put(1, new String[] {"Tax Related"});
		tags.put(2, new String[] {"Tax Related"});
		
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		
		tRec.setAmount("20.00");
		tRec.setPayee(time);
		tRec.setAccount(sChecking);
		tRec.setCategory("Home");
		tRec.setTransactionType("expense");
		
		td.addTransaction(tRec);
		
		tp.searchRecentTransaction(tRec.getPayee());
		tp.tapOnFirstTransaction();;
		
		TransactionDetailPage tdp = new TransactionDetailPage();
		tdp.addSplit(amount, cat, tags);
		
		Thread.sleep(1000);
		tp.searchRecentTransaction(tRec.getPayee());
		tp.tapOnFirstTransaction();
		
		if (h.getEngine().equals("ios")) {
			
			JavascriptExecutor js1 = (JavascriptExecutor) Engine.getDriver() ;
		    HashMap scrollObject = new HashMap();
		    scrollObject.put("direction", "down");
		    js1.executeScript("mobile: scroll", scrollObject);
		}
			
			
		else {
			
			String sText = "Go to other side";
			Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ sText + "\").instance(0))"));
		
			
		}
		
		Thread.sleep(1000);
		td.buttonGoToOtherSide.click();
		Thread.sleep(1000);
		td.deleteTransaction.click();
		td.deleteTransactionAlertButton.click();
		if(Verify.objExists(td.errorMsgText)) {
			
			Commentary.log(LogStatus.PASS, "delteting this transaction will delete the transaction and also the category will become uncategorised for the split transaction");
		}
		else {
			Commentary.log(LogStatus.FAIL, "warning message doesnt appear");
		}
		
		
		Thread.sleep(1000);
		
		
	sa.assertAll();	
		
		
		
	}
	
	
}
