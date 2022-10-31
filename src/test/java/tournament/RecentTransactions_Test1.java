package tournament;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.OverviewPage;
import dugout.SignInPage;
import dugout.TransactionDetailPage;
import dugout.TransactionsPage;
import dugout.WelcomePage;
import io.appium.java_client.MobileBy;
import referee.Commentary;
import referee.Verify;
import support.Engine;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;
import support.UserName;

public class RecentTransactions_Test1 extends Recovery{	
	
	
	String sPassword = "Quicken@01";
	String sDataset = "recentTransactionAutomationSTG1";
	String sChecking = "Manual_Checking";
	String backButton1_ios = "Banking & Credit, back";
	String sSavings = "manual-Savings";
	String sSeparateCehcking = "manual-Separate";
	private String[] amounts;
	private String[] payees;
	private String[] categories;
	
	
	public String getUsername_basedOnEnv() throws Exception {
		
		UserName un = new UserName();
		un.stage_ios = "recentTransaction-ios1++@stage.com";
		un.stage_android = "recentTransaction-android1++@stage.com";
		un.prod_ios = "";
		un.prod_android = "";
		return un.getUserName();
		
	}
	

	@Test (priority = 1, enabled = true)
	public void RT7_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		String sUsername = getUsername_basedOnEnv();
		WelcomePage w = new WelcomePage();
		w.setEnvironment(h.getEnv());

		SignInPage si = new SignInPage();
		si.signIn(sUsername, sPassword, sDataset);
		
		

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verifying that separate account transaction should not appear under recent transaction");
		
		String sPayee = "Saffire";
		String sCard = "Recent Transactions";
				
		OverviewPage op = new OverviewPage();
		TransactionDetailPage td = new TransactionDetailPage();

		TransactionRecord tRec = new TransactionRecord();
		tRec.setAmount("10.00");
		tRec.setAccount("manual-Separate");
		tRec.setPayee(sPayee);
		tRec.setCategory("Toys");
		tRec.setTransactionType("expense");
		tRec.setDate(h.getPastDaysDate(0));
		h.getContext();
				
		op.addTransaction.click();
		td.addTransaction(tRec);
		Thread.sleep(2000);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount "+tRec.getAmount());		
		
		
		if (h.getEngine().equals("android")){
			
			Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ sCard + "\").instance(0))"));
			//Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\" Recent Transactions \").instance(0))"));
			
			
		}
		else {
			
			op.scroll_down();
			
		}
		//op.tapOnRecentTransactionsCard();
		
		
		TransactionsPage tp = new TransactionsPage();
		
		if(Verify.objExists(op.recentTxns_NoTxnsAvailable)) {
			
			Commentary.log(LogStatus.PASS, "Separate account transactions are not appering under recent transaction");
			
		}
		else {
			Commentary.log(sa, LogStatus.FAIL, "Seaparte account transactions are appearing under recent transaction");
			
		}
		
		sa.assertAll();
		
	}
	
	
	@Test (priority = 2, enabled = true)
	public void RT8_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		amounts = new String[] {"11.11","12.22","13.33","14.44"};
		payees = new String[] {"Macys","Insurances","Pet Foods","Cares"};
		categories = new String[] {"Shopping", "Auto Insurance", "Pets","Personal Care"};
		int[] days = new int[] {0,1,4,5};

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verifying the priority of transactions on the recent transactions card.");
		
		//Adding new transaction..
							
		OverviewPage op = new OverviewPage();
		TransactionDetailPage td = new TransactionDetailPage();
//		int count = 0;
		for (int i=0;i<=3;i++) {
			
			TransactionRecord tRec = new TransactionRecord();
			tRec.setAmount(amounts[i]);
			tRec.setAccount("Manual_Checking");
			tRec.setPayee(payees[i]);
			tRec.setCategory(categories[i]);
			tRec.setTransactionType("expense");
			tRec.setDate(h.getPastDaysDate(days[i]));
			h.getContext();
								
			op.addTransaction.click();
			td.addTransaction(tRec);
			Thread.sleep(2000);
			Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount "+tRec.getAmount());
				
			//Verify if the transaction appears on the recent transaction card..
			
			String sCard = "Recent Transactions";
			
			//Count of recent transactions on the card..
			
			if (h.getEngine().equals("android")){
				
				Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ sCard + "\").instance(0))"));
				//Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\" Recent Transactions \").instance(0))"));
			
			}
			
			String sText = op.payeeNameText.getText();
			String sString = payees[i];
			
			 System.out.println(sText);
			 System.out.println(sString);
			
//			boolean isPayeeMatched = sText.equals(sString);
//			System.out.println(isPayeeMatched);
			 
			 int count = op.listRecentTransactions(sText, sString);
			
//			if (isPayeeMatched) count++;
	    
	        Thread.sleep(1000);
	        if(count==1 && (days[i]<=4)) {
				Commentary.log(sa,LogStatus.PASS, "PASS: Transaction which is ["+days[i]+"] days past from 'current date' appeared in Recent Transactions dashboard card");
			}
			else {
				Commentary.log(sa, LogStatus.FAIL,  "Transaction with ["+days[i]+"] days past from 'current date' did not appear in Recent Transactions dashboard card..");
			}	
	        
			
		}
		
		//Delete transactions for current date and past one day.
		
		for (int j=0;j<=1;j++) {
		
			//op.tapOnRecentTransactionsCard();
			op.navigateToAllTransactions();
			
		
			TransactionsPage tp = new TransactionsPage();
			tp.searchTransactionTxtField.click();
			tp.searchTransactionTxtField.sendKeys(payees[j]);
						
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
			tp.searchTransactionTxtField.sendKeys(payees[j]);
					
			if (Verify.objExists(tp.txtNoResultFound))
				Commentary.log(LogStatus.INFO, "Successfully Deleted the selected transaction with payee name ["+payees[j]+"]");
			else
				Commentary.log(sa, LogStatus.FAIL, "Fail: Unable to Delete the selected transaction with payee name ["+payees[j]+"]");
		
			h.hideKeyBoard();
			tp.backButton.click();	
			
			//Verify if the transaction appears on the recent transaction card..
			
			String sText = op.payeeNameText.getText();
			String sString = payees[j];
			
			int count = op.listRecentTransactions(sText, sString);
	 
	        System.out.println(count);
	        
	        if(count == 1){
				Commentary.log(sa, LogStatus.FAIL, "FAIL: Transaction which is ["+days[j]+"] days past from 'current date' appeared in Recent Transactions dashboard card after delete operation");
			}
			else {
				Commentary.log(LogStatus.PASS,  "PASS: Transaction with ["+days[j]+"] days past from 'current date' did not appear in Recent Transactions dashboard card after delete operation..");
			}			
			
		}
		
		//Verify the total no. of transactions at the end on the recent transactions card..
		
		String sText = op.payeeNameText.getText();
		String sString = "Payee Name:";
		
		
		
		int count = op.listRecentTransactions(sText, sString);
 
        System.out.println(count);
        
        if(count == 2){
			Commentary.log(LogStatus.PASS, "PASS: Transaction which is more than 5 days in the past did not appear in Recent Transactions dashboard card even if there is a place left");
		}
		else {
			Commentary.log(sa, LogStatus.FAIL,  "FAIL: Transaction which is more than 5 days seems to be appearing in the Recent Transactions dashboard card.");
		}	
        
        // Delete other two remaining transactions..
        
        op.tapOnRecentTransactionsCard();
        
        for (int j=2;j<=3;j++) {
    		
			
		
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
					
			if (Verify.objExists(tp.txtNoResultFound))
				Commentary.log(LogStatus.INFO, "Successfully Deleted the selected transaction with payee name ["+payees[j]+"]");
			else
				Commentary.log(sa, LogStatus.FAIL, "Fail: Unable to Delete the selected transaction with payee name ["+payees[j]+"]");
		
			
			}
	
	
        sa.assertAll();
	
	}
}
	


