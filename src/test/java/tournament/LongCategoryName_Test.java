package tournament;

import java.util.HashMap;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.BankingAndCreditCardPage;
import dugout.LongCategoryNamePage;
import dugout.OverviewPage;
import dugout.SignInPage;
import dugout.TransactionDetailPage;
import dugout.TransactionsPage;
import dugout.WelcomePage;
import referee.Commentary;
import referee.Verify;
import support.Engine;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;
import support.UserName;

public class LongCategoryName_Test extends Recovery {
	String sUsername = "indrajit.deshmukh+prod@quickendev.com";
	String sPassword = "Quicken@01";
	String sDataset = "Search_functionality_test";
	String sDataset_stage = "Longcategory";
	String sManualAccount = "Checking";
	
	
	public String getUsername_basedOnEnv() throws Exception{

		UserName un = new UserName();
		un.stage_ios = "longcategory_ios++@stage.com";
		un.stage_android = "longcategory_android++@stage.com";
		un.prod_ios = "quicken789@gmail.com";
		un.prod_android = "quicken789@gmail.com";
		return un.getUserName();	
	}
	
	
	@Test (priority = 1, enabled = true)
	public void LC1_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		String sUsername = getUsername_basedOnEnv();
		WelcomePage w = new WelcomePage();
		w.setEnvironment(h.getEnv());
		
		SignInPage si = new SignInPage();
		if(h.getEnv().contentEquals("stage"))
			si.signIn(sUsername, sPassword, sDataset_stage);
		else
			si.signIn(sUsername, sPassword, sDataset);
		
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify 'Show long category names' option on the Ham menu > Settings screen. ");
		
		
        LongCategoryNamePage lp = new LongCategoryNamePage();
		OverviewPage op = new  OverviewPage();
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 4);
		lp.hambergerIcon.click();
		Thread.sleep(3000);
		lp.scrollDownToSettings();
		lp.settingsOption.click();
		Thread.sleep(2000);
		
		
		if(Verify.objExists(lp.Showlongcategorynames))
			Commentary.log(sa,LogStatus.PASS, "Pass: Show long category name  option is present under the Settings section of the Hamburger menu.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Show long category name option is not present under the Settings section of the Hamburger menu.");

		sa.assertAll();
		
	}
	

		
	@Test (priority = 2, enabled = true)
	public void LC2_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		String sPayee= "Axis";
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify  enabling of  'Show long category names' option.");
		
		OverviewPage op = new  OverviewPage();
        TransactionsPage tp = new TransactionsPage();
        LongCategoryNamePage lp = new LongCategoryNamePage();
        
    	Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 4);
    	lp.EnableLongCategory();
    	tp.searchTransaction(sPayee);
    	tp.tapOnFirstTransaction();
    	lp.VerifyTransactionCategory("Bills & Utilities:Home Phone");	
    	Thread.sleep(1000);

		sa.assertAll();
    	
     }
	
	
	
	@Test (priority = 3, enabled = true)
	public void LC3_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		String sPayee= "Axis";
		String sCategory ="Home Phone";
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify disabling of  'Show long category names' option.");
		
        
		OverviewPage op = new  OverviewPage();
        TransactionsPage tp = new TransactionsPage();
        LongCategoryNamePage lp = new LongCategoryNamePage();
        
    	Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 4);
    	lp.DisableLongCategory();
    	tp.searchTransaction(sPayee);
    	tp.tapOnFirstTransaction();
    	lp.VerifydisablingofLongCategory(sCategory);
        Thread.sleep(1000);

		sa.assertAll();
    	
    }
	
	
	@Test (priority = 4, enabled = true)
	public void LC4_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		String sPayee= "Axis";
		String sCategory= "Bills & Utilities:Home Phone";
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify the category of a transaction shows as parent:child when the category is first child of the parent.");
		
        
		OverviewPage op = new  OverviewPage();
        TransactionsPage tp = new TransactionsPage();
        LongCategoryNamePage lp = new LongCategoryNamePage();
        
    	Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 4);
    	lp.EnableLongCategory();
    	tp.searchTransaction(sPayee);
    	tp.tapOnFirstTransaction();
    	lp.VerifyTransactionCategory(sCategory);
    	Thread.sleep(1000);

		sa.assertAll();
    	
    }
	
	
	@Test (priority = 5, enabled = true)
	public void LC5_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		String sCategory= "Bills & Utilities:Home Phone";
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify the category of a transaction shows as parent:child when the category is first child of the parent.");
		
        
		OverviewPage op = new  OverviewPage();
        TransactionsPage tp = new TransactionsPage();
        LongCategoryNamePage lp = new LongCategoryNamePage();
        TransactionDetailPage td = new TransactionDetailPage();
        TransactionRecord tRec = new TransactionRecord();
        
        Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
	    op.navigateToAllTransactions();
		tp.addTransaction.click();
		Thread.sleep(1000);
		
		String payeename = h.getCurrentTime();
		HashMap<Integer,String> amount = new HashMap<Integer, String>();
		HashMap<Integer,String> cat = new HashMap<Integer, String>();
		HashMap<Integer,String[]> tags = new HashMap<Integer, String[]>();
		
		cat.put(1, "Home Phone");
		cat.put(2, "Car Wash");
		amount.put(1, "50.00");
		amount.put(2, "20.00");	
		tRec.setAmount("70.00");
		tRec.setTransactionType("expense");
		tRec.setAccount(sManualAccount);
		tRec.setPayee(payeename);
		
		td.addSplit(tRec, amount, cat, tags);
		Thread.sleep(3000);
		tp.backButton.click();
		Thread.sleep(1000);
    	
    	tp.searchTransaction(payeename);
    	tp.tapOnFirstTransaction();
    	lp.VerifySplitCategory(1,sCategory);
    
    	
    	Thread.sleep(1000);

		sa.assertAll();
    	
    }
	
	
	
}
