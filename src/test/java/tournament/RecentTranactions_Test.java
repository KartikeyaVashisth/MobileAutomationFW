package tournament;


import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import referee.Commentary;
import referee.Verify;
import support.CategoryRecord;
import support.Engine;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;
import support.UserName;
import dugout.OverviewPage;
import dugout.SignInPage;
import dugout.TransactionDetailPage;
import dugout.TransactionsPage;
import dugout.WelcomePage;
import io.appium.java_client.MobileBy;
//import dugout.RecentTransactionsPage;


public class RecentTranactions_Test extends Recovery {
	
	//String sUserName = "stagemobileautomation1@mailinator.com";
	//String sPassword = "Quicken@01";
	//String sDataset = "Recent Transactions";
	
	String sPassword = "Quicken@01";
	String sDataset = "recentTransactionAutomationSTG";
	String sChecking = "Manual_Checking";
	String backButton1_ios = "Banking & Credit, back";
	String sSavings = "manual-Savings";
	String sSeparateCehcking = "manual-Separate";
	private String[] amounts;
	private String[] payees;
	private String[] categories;
	
	
	
	
	public String getUsername_basedOnEnv() throws Exception {
		
		UserName un = new UserName();
		un.stage_ios = "recentTransaction-ios++@stage.com";
		un.stage_android = "recentTransaction-android++@stage.com";
		un.prod_ios = "";
		un.prod_android = "";
		return un.getUserName();
		
	}
	
	
	
//****** This piece of code we can enable when we want to use the Testrail integration with our project . Based on test case status the status on Testrail will get update for each testcase ******	
//	@Override
//	@BeforeTest
//	@Parameters({"host","engine","test","env","RUN_ID"})
//	public void beforeTestEnter(@Optional("readFromPropertiesFile")String host, @Optional("readFromPropertiesFile")String engine, @Optional("readFromPropertiesFile")String testName, @Optional("readFromPropertiesFile")String env, @Optional("readFromPropertiesFile")String RUN_ID) throws Exception{
//		this.testRunId.set("2330");
//		super.beforeTestEnter(host, engine, testName, env, RUN_ID);
//		
//	}
	


	@Test (priority = 1, enabled = true)
	public void RT1_Test() throws Exception {
		
		
		//this.testRunId.set("2330");
		this.testCaseId.set("534433") ;

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		String sUsername = getUsername_basedOnEnv();
		WelcomePage w = new WelcomePage();
		w.setEnvironment(h.getEnv());

		SignInPage si = new SignInPage();
		si.signIn(sUsername, sPassword, sDataset);
		

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verifying Recent Transactions card appears on dashboard.");

		OverviewPage op = new OverviewPage();
		op.tapOnRecentTransactionsCard();
		
		TransactionsPage tn = new TransactionsPage();
		
		Verify.waitForObject(tn.txtAllTransactions, 2);
		if (Verify.objExists(tn.txtAllTransactions))
			Commentary.log(LogStatus.PASS, "PASS: All Transactions header text is displayed.");			
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: All Transactions header text is not displayed.");
		
		if (Verify.waitForObject(tn.searchTransactionTxtField, 1))
			Commentary.log(LogStatus.PASS,  "PASS: Search field appears on All transactions screen.");
		else
			Commentary.log(LogStatus.FAIL, "FAIL: Search field does not appear on All transactions screen.");
		
		if (Verify.waitForObject(tn.buttonSort, 1)) {
			Commentary.log(LogStatus.PASS,  "PASS: Sort button appears on All transactions screen.");
			
		}
		else
			Commentary.log(sa,LogStatus.FAIL, "FAIL: Sort button does not appear on All transactions screen.");
		
		sa.assertAll();
		tn.backButton.click();
	}
	
	//-----------------------------------------------------
	
	@Test (priority = 2, enabled = true)
	public void RT2_Test() throws Exception {
		
		
		//this.testRunId.set("2330");
		this.testCaseId.set("953193");
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verifying 'No transactions available' message appears on Recent Transactions card.");
		
		OverviewPage op = new OverviewPage();
		
		String sCard = "Recent Transactions";
	
		if (h.getEngine().equals("android")){
			
			Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ sCard + "\").instance(0))"));
			
		}
		
		Verify.waitForObject(op.recentTransactionsCard, 1);
		if (Verify.objExists(op.recentTransactionsCard)) {
			Commentary.log(LogStatus.INFO, "Recent Transactions dashboard card is displayed.");	
			
			if (Verify.objExists(op.recentTxns_NoTxnsAvailable)) {
				Commentary.log(LogStatus.PASS,  "PASS: 'No transaction available' message is displayed.");
			}
			else {
				Commentary.log(LogStatus.FAIL, "FAIL: 'No transaction available' message was not found.");
			}				
		}	
		else {
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Recent Transactions dashboard card is not displayed.");
		
		}
		sa.assertAll();
	}
	
	//-----------------------------------------------------
	
	@Test (priority = 3, enabled = true)
	public void RT3_Test() throws Exception {
		
		//this.testRunId.set("2330");
		this.testCaseId.set("C534437");
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verifying today's transaction appears on Recent Transactions card.");	
		
		//Adding new transaction..
		
		String sPayee = "Broadband Ser Pro";
		
		OverviewPage op = new OverviewPage();
		TransactionDetailPage td = new TransactionDetailPage();

		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("5.00");
		tRec.setAccount("Manual_Checking");
		tRec.setPayee(sPayee);
		tRec.setCategory("Internet");
		tRec.setTransactionType("expense");
		tRec.setDate(h.getFutureDaysDate(0));
		h.getContext();
		
		op.addTransaction.click();
		td.addTransaction(tRec);
		Thread.sleep(2000);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount "+tRec.getAmount());		
		
		//Verifying transaction on Recent Transactions card..
		
		String sCard = "Recent Transactions";
		
		if (h.getEngine().equals("android")){
			
			Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ sCard + "\").instance(0))"));
			
		}
		
		String sPayeeRT = op.payeeNameText.getText();
		
		if (sPayeeRT.contains(sPayee))
			Commentary.log(LogStatus.PASS, "PASS: Transaction with current date is shown on Recent Transactions card");
		else
			Commentary.log(sa,LogStatus.FAIL, "FAIL: Transaction with current date is not shown on Recent Transactions card");
		
		//Delete transaction..

		op.tapOnRecentTransactionsCard();
		
		TransactionsPage tp = new TransactionsPage();
		tp.searchTransactionTxtField.click();
		tp.searchTransactionTxtField.sendKeys(sPayee);
			
		tp.tapOnFirstTransaction();
		Thread.sleep(2000);
		
		op.scroll_down();
		td.deleteTransaction.click();
		if (Verify.objExists_check(td.deleteTransactionAlertButton)) {
		td.deleteTransactionAlertButton.click();
		}
		Thread.sleep(4000);
				
		tp.searchTransactionTxtField.click();
		tp.searchTransactionTxtField.clear();
		tp.searchTransactionTxtField.sendKeys(sPayee);
		
		if (Verify.objExists(tp.txtNoResultFound))
			Commentary.log(LogStatus.INFO, "Successfully Deleted the selected transaction");
		else
			Commentary.log(sa, LogStatus.FAIL, "Unable to Delete the selected transaction");
		
		
		
		sa.assertAll();
	}
	
	//-----------------------------------------------------
	
	@Test (priority = 4, enabled = true)
	public void RT4_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verifying that transactions from last 4 days appears on Recent Transactions Card.");		
		
		//Adding new transaction..
		
		String sPayee = "Saffire";
		String sCard = "Recent Transactions";
				
		OverviewPage op = new OverviewPage();
		TransactionDetailPage td = new TransactionDetailPage();

		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("10.00");
		tRec.setAccount("Manual_Checking");
		tRec.setPayee(sPayee);
		tRec.setCategory("Toys");
		tRec.setTransactionType("expense");
		tRec.setDate(h.getPastDaysDate(4));
		h.getContext();
				
		op.addTransaction.click();
		td.addTransaction(tRec);
		Thread.sleep(2000);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount "+tRec.getAmount());		
				
		//Verifying transaction on Recent Transactions card..
		
	if (h.getEngine().equals("android")){
			
			Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ sCard + "\").instance(0))"));
			
		}
		
				
		String sPayeeRT = op.payeeNameText.getText();
				
		if (sPayeeRT.contains(sPayee))
			Commentary.log(LogStatus.PASS, "PASS: Transaction with past (4) date is shown on Recent Transactions card");
		else
			Commentary.log(sa,LogStatus.FAIL, "FAIL: Transaction with past (4) date is not shown on Recent Transactions card");
				
		//Delete transaction..

		op.tapOnRecentTransactionsCard();
				
		TransactionsPage tp = new TransactionsPage();
		tp.searchTransactionTxtField.click();
		tp.searchTransactionTxtField.sendKeys(sPayee);
					
		tp.tapOnFirstTransaction();
		Thread.sleep(2000);
				
		op.scroll_down();
		td.deleteTransaction.click();
		if (Verify.objExists_check(td.deleteTransactionAlertButton)) {
		td.deleteTransactionAlertButton.click();
		}
		Thread.sleep(1000);
						
		tp.searchTransactionTxtField.click();
		tp.searchTransactionTxtField.clear();
		tp.searchTransactionTxtField.sendKeys(sPayee);
				
		if (Verify.objExists(tp.txtNoResultFound))
			Commentary.log(LogStatus.INFO, "Successfully Deleted the selected transaction");
		else
			Commentary.log(sa, LogStatus.FAIL, "Fail:Unable to Delete the selected transaction");
		
		//tp.navigateBackToDashboard();
				
		sa.assertAll();
	}
	
	//-----------------------------------------------------
	
	@Test (priority = 5, enabled = true)
	public void RT5_Test() throws Exception {
			
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
			
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verifying that transactions from future date does not appear on Recent Transactions Card.");		
			
		//Adding new transaction..
			
		String sPayee = "Paycheck";
					
		OverviewPage op = new OverviewPage();
		TransactionDetailPage td = new TransactionDetailPage();
		CategoryRecord cr = new CategoryRecord();

		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("3000.00");
		tRec.setAccount("Manual_Checking");
		tRec.setPayee(sPayee);
		cr.setCategoryName("Net Salary");
		tRec.setTransactionType("income");
		tRec.setDate(h.getFutureDaysDate(2));
		h.getContext();
					
		op.addTransaction.click();
		td.addTransaction(tRec);
		Thread.sleep(2000);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type income, amount "+tRec.getAmount());		
					
		//Verifying transaction on Recent Transactions card..
							
		
		if (Verify.objExists(op.payeeNameText)){
			
			String sPayeeRT = op.payeeNameText.getText();
			String sDate = op.transactionDateText.getText();
			
			Commentary.log(LogStatus.INFO, "Date is: ["+sDate+"]");
			
			if (sPayeeRT.contains(sPayee)) {
				Commentary.log(sa,LogStatus.FAIL, "FAIL: Future dated transaction is showing up in Recent Transactions card");	
			}						
		}
		else {
			Commentary.log(LogStatus.PASS, "PASS: Future dated transaction does not show up in Recent Transactions Card");
		}			
					
		//Delete transaction..

		op.tapOnRecentTransactionsCard();
					
		TransactionsPage tp = new TransactionsPage();
		tp.searchTransactionTxtField.click();
		tp.searchTransactionTxtField.sendKeys(sPayee);
						
		tp.tapOnFirstTransaction();
		Thread.sleep(2000);
					
		op.scroll_down();
		td.deleteTransaction.click();
		
		if (Verify.objExists_check(td.deleteTransactionAlertButton)) {
			td.deleteTransactionAlertButton.click();
		}
		Thread.sleep(4000);
							
		tp.searchTransactionTxtField.click();
		tp.searchTransactionTxtField.clear();
		tp.searchTransactionTxtField.sendKeys(sPayee);
					
		if (Verify.objExists(tp.txtNoResultFound))
			Commentary.log(LogStatus.INFO, "Successfully Deleted the selected transaction");
		else
			Commentary.log(sa, LogStatus.FAIL, "Fail: Unable to Delete the selected transaction");
		
		
					
		sa.assertAll();
		}
		
	//-----------------------------------------------------
		
	
	@Test (priority = 6, enabled = true)
	public void RT6_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		amounts = new String[] {"33.33","44.44","55.55","22.22"};
		payees = new String[] {"First Crys","Starbuckss","Safeways","Parkings"};
		categories = new String[] {"Kids", "Coffee Shops", "Groceries","Parking"};

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verifying the maximum count of transactions on Recent Transactions Card.");
		
		//Adding new transaction..
		
		String sPayee = "Paycheck";
							
		OverviewPage op = new OverviewPage();
		TransactionDetailPage td = new TransactionDetailPage();
		
		for (int i=0;i<=3;i++) {
			
			TransactionRecord tRec = new TransactionRecord();
			tRec.setAmount(amounts[i]);
			tRec.setAccount("Manual_Checking");
			tRec.setPayee(payees[i]);
			tRec.setCategory(categories[i]);
			tRec.setTransactionType("expense");
			tRec.setDate(h.getFutureDaysDate(0));
			h.getContext();
								
			op.addTransaction.click();
			td.addTransaction(tRec);
			Thread.sleep(2000);
			Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount "+tRec.getAmount());
			
		}
		
		String sCard = "Recent Transactions";
		
		//Count of recent transactions on the card..
		
		if (h.getEngine().equals("android")){
			
			Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ sCard + "\").instance(0))"));
			
		}
		
		
				
		String sText = op.payeeNameText.getText();
		String sString = "Payee Name:";
		
		int count = op.listRecentTransactions(sText, sString);
 
        System.out.println(count);
		
		if(count == 3) {
			Commentary.log(LogStatus.PASS, "PASS: Only three transactions appeared in Recent Transactions dashboard card");
		}
		else {
			Commentary.log(LogStatus.FAIL,  "FAIL: Less than or more than three transactions appeared in Recent Transactions dashboard card.."+count);
		}
		
		//Delete transactions..
		
		op.tapOnRecentTransactionsCard();
		
		for (int j=0;j<=3;j++) {
			
			
			
			TransactionsPage tp = new TransactionsPage();
			tp.searchTransactionTxtField.click();
			tp.searchTransactionTxtField.clear();
			tp.searchTransactionTxtField.sendKeys(payees[j]);
							
			tp.tapOnFirstTransaction();
			Thread.sleep(2000);
						
			op.scroll_down();
			td.deleteTransaction.click();
			
			if (Verify.objExists_check(td.deleteTransactionAlertButton)) {
				td.deleteTransactionAlertButton.click();
			}
			Thread.sleep(1000);
								
			tp.searchTransactionTxtField.click();
			tp.searchTransactionTxtField.clear();
			tp.searchTransactionTxtField.sendKeys(payees[j]);
						
			if (Verify.objExists(tp.txtNoResultFound)) {
				Commentary.log(LogStatus.INFO, "Successfully Deleted the selected transaction with payee name ["+payees[j]+"]");
			}
			else {
				Commentary.log(sa, LogStatus.FAIL, "Fail: Unable to Delete the selected transaction with payee name ["+payees[j]+"]");
			}
			
			
		
		}

		
		sa.assertAll();
	}
	
	
	
	
	
	
}
