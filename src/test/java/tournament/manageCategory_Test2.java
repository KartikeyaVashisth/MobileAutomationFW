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
import referee.Commentary;
import referee.Verify;
import support.CategoryRecord;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;
import support.UserName;

public class manageCategory_Test2 extends Recovery{
	
	String sPassword = "Quicken@01";
	String sDataset = "manageCategoryautomation";
	String sChecking = "manual-Checking";
	String backButton1_ios = "Banking & Credit, back";
	String sSavings = "manual-Savings";



public String getUsername_basedOnEnv() throws Exception {
	
	UserName un = new UserName();
	un.stage_ios = "managecategory-ios++@stage.com";
	un.stage_android = "managecategory-android1++@stage.com";
	un.prod_ios = "";
	un.prod_android = "";
	return un.getUserName();
	
}

//****** This piece of code we can enable when we want to use the Testrail integration with our project . Based on test case status the status on Testrail will get update for each testcase ******	
//@Override
//@BeforeTest
//@Parameters({"host","engine","test","env","RUN_ID"})
//public void beforeTestEnter(@Optional("readFromPropertiesFile")String host, @Optional("readFromPropertiesFile")String engine, @Optional("readFromPropertiesFile")String testName, @Optional("readFromPropertiesFile")String env, @Optional("readFromPropertiesFile")String RUN_ID) throws Exception{
//	this.testRunId.set("2330");
//	super.beforeTestEnter(host, engine, testName, env, RUN_ID);
//	
//}


@Test(priority = 8, enabled = true)
public void TR8_test() throws Exception {
	
	SoftAssert sa = new SoftAssert();
	Helper h = new Helper();
	
	String sUsername = getUsername_basedOnEnv();
	WelcomePage w = new WelcomePage();
	w.setEnvironment(h.getEnv());

	SignInPage si = new SignInPage();
	si.signIn(sUsername, sPassword, sDataset);
	
	Thread.sleep(1000);
	
	
	Commentary.log(LogStatus.INFO, "Verify that Manage category option under setting section");
	
	String catName = h.getCurrentTime();
	
	Commentary.log(LogStatus.INFO, "Verify that until user enters any category name the Create button won't get enabled.");
	

	OverviewPage op = new OverviewPage();
	op.navigateToManageCategories();
			
	Thread.sleep(1000);
	
	ManageCategories mgc = new ManageCategories();
	mgc.addCategoryButton.click();
	Thread.sleep(1000);
	
	if(mgc.createButton.isDisplayed()) {
		
		Commentary.log(LogStatus.PASS, "Create category option is not enabled");
	}
	else {
		Commentary.log(LogStatus.FAIL, "Create category option is enabled");
	}
	
	Thread.sleep(1000);
	
	mgc.categoryNameTxtField1.sendKeys(catName);
	h.hideKeyBoard();

	
	if(mgc.createButton.isDisplayed()) {
		
		Commentary.log(LogStatus.PASS, "After enatering Category name Create button got enabled");
	}
	else {
		Commentary.log(sa, LogStatus.FAIL, "Create button is still disabled");
	}
	
	sa.assertAll();
	
}

@Test(priority = 9, enabled = false)
public void TR9_test() throws Exception {
	
	SoftAssert sa = new SoftAssert();
	Helper h = new Helper();
	
	String catName = h.getCurrentTime();
	
	Commentary.log(LogStatus.INFO, "Verify that Duplicate name should not be allowed for category/Sub-category.");
	
	CategoryRecord cr = new CategoryRecord();
	cr.setCategoryName(catName);
	cr.setCategoryType("Expense");
	cr.setSubCategoryOf(null);
	
	
	
	OverviewPage op = new OverviewPage();
	op.navigateToManageCategories();
			
	Thread.sleep(1000);
	
	ManageCategories mgc = new ManageCategories();
	mgc.createCategory(cr);
	Thread.sleep(1000);
	
	mgc.addCategoryButton.click();
	
	Thread.sleep(1000);
	mgc.categoryNameTxtField1.sendKeys(cr.getCategoryName());
	h.hideKeyBoard();
	Thread.sleep(1000);
	
	mgc.createButton.click();
	
	if(Verify.objExists(mgc.categoryExistsLabel)) {
		
		Commentary.log(LogStatus.PASS, "Duplicate Categoery/subcategory can not be created");
	}
	else {
		
		Commentary.log(sa, LogStatus.FAIL, "Duplicate category got created");
	}
	
	sa.assertAll();
	
}

@Test(priority = 10, enabled = false)
public void TR10_test() throws Exception {
	
	SoftAssert sa = new SoftAssert();
	Helper h = new Helper();
	
	String catName = h.getCurrentTime();
	
	Commentary.log(LogStatus.INFO, "Verify that while deleting a category/subcategory, app presents warning to confirm if no transaction is associated with that category.");
	
	
	
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
	
	mgc.categoryAvailability(cr.getCategoryName());
	
	Thread.sleep(1000);
	mgc.editbutton.click();
	Thread.sleep(1000);
	mgc.deleteButton.click();
	//Thread.sleep(1000);

	if(Verify.objExists(mgc.deleteCategorylabel)) {
		
		Commentary.log(LogStatus.PASS, "Delete warning message appeared and no transaction are added with that category/sub-category");
	}
	else
	{
		Commentary.log(sa, LogStatus.FAIL, "Delete warning message did not appeared.");
	}

	sa.assertAll();
}

@Test(priority = 11, enabled = false)
public void TR11_test() throws Exception {
	
	SoftAssert sa = new SoftAssert();
	Helper h = new Helper();
	
	
	Commentary.log(LogStatus.INFO, "Verify that while creating category the Sub-category Of field By default will be None.");
	
	OverviewPage op = new OverviewPage();
	op.navigateToManageCategories();
			
	Thread.sleep(1000);
	ManageCategories mgc = new ManageCategories();
	mgc.addCategoryButton.click();
	
	if(Verify.objExists(mgc.defaultSubcategoryValue)) {
		
		Commentary.log(LogStatus.PASS, "Default value for subcategoryOf is 'None'");
	}
	else {
		Commentary.log(sa, LogStatus.FAIL, "Default value for subcategoryOf is not showing 'None'");
	}

	sa.assertAll();
	
}


@Test(priority = 12, enabled = false)
public void TR12_test() throws Exception {
	
	SoftAssert sa = new SoftAssert();
	Helper h = new Helper();
	String catName = h.getCurrentTime();
	
	Commentary.log(LogStatus.INFO, "Verify that while editing any category, user be able to change the Category Type Field");
	
	
	CategoryRecord cr = new CategoryRecord();
	cr.setCategoryName(catName);
	cr.setCategoryType("Expense");
	cr.setSubCategoryOf(null);
	
	OverviewPage op = new OverviewPage();
	op.navigateToManageCategories();
	ManageCategories mgc = new ManageCategories();
	
	mgc.createCategory(cr);
	Thread.sleep(1000);
	
	mgc.searchCategoryTextField.sendKeys(cr.getCategoryName());
	
	mgc.categoryAvailability(cr.getCategoryName());
	
	mgc.editbutton.click();
	Thread.sleep(1000);
	
	mgc.createCategoryType.click();
	Thread.sleep(1000);
	mgc.incomeTypeOption.click();
	Thread.sleep(1000);
	mgc.applyButton.click();
	
	mgc.updateButton.click();
	
	if(Verify.objExists(mgc.updateTextlabel)) {
		Commentary.log(LogStatus.INFO, "Update text is avilable");
		mgc.continueUpdateButton.click();
	}
	else {
		Commentary.log(LogStatus.INFO, "update text for category did not appear.");
	}
	Thread.sleep(1000);
	
	
	mgc.searchCategoryTextField.sendKeys(cr.getCategoryName());
	
	mgc.categoryAvailability(cr.getCategoryName());
	
	mgc.editbutton.click();
	Thread.sleep(1000);
	
	if(Verify.objExists(mgc.categoryTypeValueIncome)) {
		Commentary.log(LogStatus.PASS, "Category Type got updated from expense to income");
	}
	else {
		Commentary.log(sa, LogStatus.FAIL, "Category Type did not get updated");
	}
	
	sa.assertAll();
	
}	
	
	@Test(priority = 13, enabled = false)
	public void TR13_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		String catName = h.getCurrentTime();
		
		Commentary.log(LogStatus.INFO, "Verify that while editing any subcategory, user should not be able to change the Category Type Field.");
		
		
		CategoryRecord cr = new CategoryRecord();
		cr.setCategoryName(catName);
		cr.setCategoryType("Income");
		cr.setSubCategoryOf("Div Income");
		
		OverviewPage op = new OverviewPage();
		op.navigateToManageCategories();
		ManageCategories mgc = new ManageCategories();
		
		mgc.createCategory(cr);
		Thread.sleep(1000);
		
		mgc.searchCategoryTextField.sendKeys(cr.getCategoryName());
		
		mgc.categoryAvailability(cr.getCategoryName());
		
		mgc.editbutton.click();
		Thread.sleep(1000);
		
		
		if(mgc.categoryTypeValueIncome.isDisplayed()) {
			Commentary.log(LogStatus.PASS, "Category Type option is disabled");
		}
		else {
			Commentary.log(sa, LogStatus.FAIL, "Category Type option is enabled");
		}
		

		
		sa.assertAll();
		
}
	
	@Test(priority = 14, enabled = true)
	public void TR14_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		String catName = h.getCurrentTime();
		
		Commentary.log(LogStatus.INFO, "verify that changing the the Type of parent category will change the type of all Subcategories, all should effect the transactions.");
		
		
		CategoryRecord cr = new CategoryRecord();
		cr.setCategoryName(catName);
		cr.setCategoryType("Expense");
		cr.setSubCategoryOf("parentcatCategory");
		
		OverviewPage op = new OverviewPage();
		op.navigateToManageCategories();
		ManageCategories mgc = new ManageCategories();
		
		mgc.createCategory(cr);
		Thread.sleep(1000);
		
		mgc.searchCategoryTextField.sendKeys(cr.getSubCategoryOf());
		
		mgc.parentcatCategory.click();
		Thread.sleep(1000);
		mgc.editbutton.click();
		Thread.sleep(1000);
		mgc.createCategoryType.click();
		Thread.sleep(1000);
		mgc.incomeTypeOption.click();
		Thread.sleep(1000);
		mgc.applyButton.click();
		
		mgc.updateButton.click();
		Thread.sleep(1000);
		mgc.continueButton.click();
		
		Thread.sleep(1000);
		
		mgc.searchCategoryTextField.sendKeys(cr.getCategoryName());
		
		mgc.categoryAvailability(cr.getCategoryName());
		
		mgc.editbutton.click();
		Thread.sleep(1000);
		
		if(Verify.objExists(mgc.categoryTypeValueIncome)) {
			Commentary.log(LogStatus.PASS, "Category Type got updated from expense to income with the Parent category type changed");
			
			
			
		}
		else {
			Commentary.log(sa, LogStatus.FAIL, "Category Type did not get updated after updating the Parent category ");
		}
		
		Thread.sleep(1000);
		
		mgc.deleteButton.click();
		Thread.sleep(1000);
		mgc.continueButton.click();
		Thread.sleep(1000);
		mgc.searchCategoryTextField.sendKeys(cr.getSubCategoryOf());
		
		mgc.parentcatCategory.click();
		
		mgc.editbutton.click();
		
		mgc.incomeTypeOption.click();
		Thread.sleep(1000);
		mgc.expenseTypeOption.click();
		Thread.sleep(1000);
		mgc.applyButton.click();
		
		mgc.updateButton.click();
		Thread.sleep(1000);
		mgc.continueButton.click();
		
		Thread.sleep(1000);
		

		sa.assertAll();
		
	
		
		
		
		
		
}

}