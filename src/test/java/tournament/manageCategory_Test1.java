package tournament;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.logging.Logs;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.AllAccountsPage;
import dugout.BankingAndCreditCardPage;
import dugout.ManageCategories;
import dugout.OverviewPage;
import dugout.SettingsPage;
import dugout.SignInPage;
import dugout.TransactionDetailPage;
import dugout.TransactionsPage;
import dugout.WelcomePage;
import io.appium.java_client.MobileElement;
import referee.Commentary;
import referee.Verify;
import support.CategoryRecord;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;
import support.UserName;

public class manageCategory_Test1 extends Recovery{
	
	String sPassword = "Quicken@01";
	String sDataset = "manageCategoryautomation";
	String sChecking = "manual-Checking";
	String backButton1_ios = "Banking & Credit, back";
	String sSavings = "manual-Savings";



public String getUsername_basedOnEnv() throws Exception {
	
	UserName un = new UserName();
	un.stage_ios = "managecategory++@stage.com";
	un.stage_android = "managecategory-android++@stage.com";
	un.prod_ios = "";
	un.prod_android = "";
	return un.getUserName();
	
}

	@Test(priority = 1 , enabled = true)
	public void TR1_test() throws Exception{

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		String sUsername = getUsername_basedOnEnv();
		WelcomePage w = new WelcomePage();
		w.setEnvironment(h.getEnv());

		SignInPage si = new SignInPage();
		si.signIn(sUsername, sPassword, sDataset);
		
		Thread.sleep(1000);
		
		Commentary.log(LogStatus.INFO, "Sign in is successfull");
		
	}
	
	@Test(priority = 2 , enabled = true)
	public void TR20_test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "Verify that Manage category option under setting section");
		
		Thread.sleep(1000);
		OverviewPage op = new OverviewPage();
		Thread.sleep(1000);
		op.navigateToSettings();
		SettingsPage sp = new SettingsPage();
		
		if(Verify.objExists(sp.manageCategories)) {
			
			Commentary.log(LogStatus.PASS, "Manage category option is available under the settings section");
			
		}
		else {
			
			Commentary.log(LogStatus.FAIL, "manage category option is not available under settings section");
		}
		
	}
	
	@Test(priority = 3, enabled = true)
	public void TR3_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		String catName = h.getCurrentTime();
		
		Thread.sleep(1000);
		
		Commentary.log(LogStatus.INFO, "Verify the add new category and validate the created category");
		
		CategoryRecord cr = new CategoryRecord();
		cr.setCategoryName(catName);
		cr.setCategoryType("Expense");
		cr.setSubCategoryOf("Entertainment");
		
		OverviewPage op = new OverviewPage();
		op.navigateToManageCategories();
				
		Thread.sleep(1000);
		
		ManageCategories mgc = new ManageCategories();
		mgc.createCategory(cr);
		Thread.sleep(1000);
		
		mgc.searchCategoryTextField.sendKeys(cr.getCategoryName());
		
		mgc.CategoryAvailability(cr.getCategoryName());
		
	}
	
	
	@Test(priority = 4, enabled = true)
	public void TR4_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		String catName = h.getCurrentTime();
		
		Thread.sleep(1000);
		
		Commentary.log(LogStatus.INFO, "Verify category deletion ");
		
		CategoryRecord cr = new CategoryRecord();
		cr.setCategoryName(catName);
		cr.setCategoryType("Expense");
		cr.setSubCategoryOf("Entertainment");
		
		OverviewPage op = new OverviewPage();
		op.navigateToManageCategories();
				
		Thread.sleep(1000);
		
		ManageCategories mgc = new ManageCategories();
		mgc.createCategory(cr);
		Thread.sleep(1000);
		
		mgc.searchCategoryTextField.sendKeys(cr.getCategoryName());
		
		mgc.CategoryAvailability(cr.getCategoryName());
		
		mgc.deleteCategory();
		Thread.sleep(1000);
		
		mgc.searchCategoryTextField.sendKeys(cr.getCategoryName());
		
		if(Verify.objExists(mgc.createCategoryLabel)) {
			Commentary.log(LogStatus.PASS, "Category got deleted");
		}
		else {
			Commentary.log(LogStatus.FAIL, "Category still visible");
		}
		
	}
	
	@Test(priority = 5, enabled = true)
	public void TR5_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		String catName = h.getCurrentTime();
		
		Thread.sleep(1000);
		
		Commentary.log(LogStatus.INFO, "Verify category deletion and reassign to some other category ");
		
		CategoryRecord cr = new CategoryRecord();
		cr.setCategoryName(catName);
		cr.setCategoryType("Expense");
		cr.setSubCategoryOf("Entertainment");
		cr.setreassignTo(true);
		
		OverviewPage op = new OverviewPage();
		op.navigateToManageCategories();
				
		Thread.sleep(1000);
		
		ManageCategories mgc = new ManageCategories();
		mgc.createCategory(cr);
		Thread.sleep(1000);
		
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		
		tRec.setAmount("5.00");
		tRec.setAccount(sChecking);
		tRec.setCategory(cr.getCategoryName());
		tRec.setTransactionType("expense");
		
		mgc.backButton.click();
		Thread.sleep(1000);
		SettingsPage st  =  new SettingsPage();
		st.backButtonOnHeaders.click();
		Thread.sleep(1000);
		
		OverviewPage ov = new OverviewPage();
		ov.addTransaction.click();
		td.addTransaction(tRec);
		Thread.sleep(1000);
		
		ov.navigateToManageCategories();
		Thread.sleep(1000);
		
		mgc.searchCategoryTextField.sendKeys(cr.getCategoryName());
		
		mgc.CategoryAvailability(cr.getCategoryName());
		
		mgc.reassignAndDeleteCategory(cr);
		Thread.sleep(1000);
		
		mgc.searchCategoryTextField.sendKeys(cr.getCategoryName());
		
		if(Verify.objExists(mgc.createCategoryLabel)) {
			Commentary.log(LogStatus.PASS, "Category got deleted");
		}
		else {
			Commentary.log(LogStatus.FAIL, "Category still visible");
		}
		}
	
	@Test(priority = 6, enabled = true)
	public void TR6_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		String catName = h.getCurrentTime();
		
		Thread.sleep(1000);
		
		Commentary.log(LogStatus.INFO, "Verify category updation ");
		
		CategoryRecord cr = new CategoryRecord();
		cr.setCategoryName(catName);
		cr.setCategoryType("Expense");
		cr.setSubCategoryOf("Entertainment");
		
		
		OverviewPage op = new OverviewPage();
		op.navigateToManageCategories();
				
		Thread.sleep(1000);
		
		ManageCategories mgc = new ManageCategories();
		mgc.createCategory(cr);
		Thread.sleep(1000);
		
	 mgc.searchCategoryTextField.sendKeys(cr.getCategoryName());
		
		mgc.CategoryAvailability(cr.getCategoryName());
		
		CategoryRecord cr1 = new CategoryRecord();
		cr1.setCategoryName(catName);
		//cr1.setCategoryType("Income");
		cr1.setSubCategoryOf("Education");
		
		mgc.updateCategory(cr1);
		
		Thread.sleep(1000);
		
		mgc.searchCategoryTextField.sendKeys(cr1.getCategoryName());
		
		mgc.CategoryAvailability(cr1.getCategoryName());
		
	}
	
	@Test(priority = 7, enabled = true)
	public void TR7_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		String catName = h.getCurrentTime();
		
		Commentary.log(LogStatus.INFO, "Verify that manage categories list will show the parent child tree structure.");
		
		
		CategoryRecord cr = new CategoryRecord();
		cr.setCategoryName(catName);
		cr.setCategoryType("Expense");
		cr.setSubCategoryOf("Entertainment");
		
		
		OverviewPage op = new OverviewPage();
		op.navigateToManageCategories();
				
		Thread.sleep(1000);
		
		ManageCategories mgc = new ManageCategories();
		mgc.createCategory(cr);
		Thread.sleep(1000);
		
		 mgc.searchCategoryTextField.sendKeys(cr.getCategoryName());
			
		 mgc.CategoryAvailability(cr.getCategoryName());
		 
	 if(Verify.objExists(mgc.cashAtmParentCat)) {
		 Commentary.log(LogStatus.PASS, "Child and Parent category is visible");
	 }
	 else {
		 Commentary.log(LogStatus.FAIL, "Parent category is not visible");
	 }
		
		}
	
	
	@Test(priority = 8, enabled = true)
	public void TR8_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "Verify that tapping on the Add category button presents a pop-up with Options.");
		
		OverviewPage op = new OverviewPage();
		op.navigateToManageCategories();
				
		Thread.sleep(1000);
		
		ManageCategories mgc = new ManageCategories();
		mgc.addCategoryButton.click();
		Thread.sleep(1000);
		
		if(Verify.objExists(mgc.createCategoryLabel)) {
			
			Commentary.log(LogStatus.PASS, "Create category pop-up appeared with required fields");
		}
		else {
			Commentary.log(LogStatus.FAIL, "Create category pop-up did not appeared");
		}
	
	}
	
	
	
	
	
}