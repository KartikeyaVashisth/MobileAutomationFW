package dugout;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import referee.Commentary;
import referee.Verify;
import support.CategoryRecord;
import support.Engine;
import support.Helper;
import support.TransactionRecord;

public class ManageCategories {
	
	public ManageCategories () {
		try {
			
			PageFactory.initElements(new AppiumFieldDecorator(Engine.getDriver()),this);
		} catch (Exception e) {
			
			e.printStackTrace();
		}	
	}
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`name=='search category'`]")
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Search Category']")
	public WebElement searchCategoryTextField;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Entertainment'`]")
	//@AndroidFindBy(xpath="//*[@text='Cash & ATM']")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Entertainment']")
	public WebElement cashAtmParentCat;
	
	//@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='RadioButton parentcatCategory'`][-1]")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='RadioButton parentcatCategory'`]/XCUIElementTypeOther[`name='RadioButton'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text = 'parentcatCategory']/../android.view.ViewGroup[@content-desc = 'RadioButton']")
	//@AndroidFindBy(xpath="//*[@text='Parentcat']")
	public WebElement parentcatCategory;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Firstlevelcat'`]")
	@AndroidFindBy(xpath="//*[@text='Firstlevelcat']")
	public WebElement firstLevelCatCategory;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'addCategoryButton'`][-1]")
	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='addCategoryButton']")
	public WebElement addCategoryButton;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Manage Categories'`]")
	@AndroidFindBy(xpath = "//android.view.View[contains(@text,'Manage Categories')]")
	public WebElement manageCategoriesLabel;
	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeOther' AND name = 'create category'")
	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='create category']")
	public WebElement createCategory;
	
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeButton[`name='Settings, back'`]")
	@AndroidFindBy(xpath="//android.widget.Button[@content-desc='Settings, back']")
	public WebElement backButton;
	
	//@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'edit'`][-1]")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='edit'`]/**/XCUIElementTypeOther")
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc = 'edit']")
	public WebElement editbutton;
	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeOther' AND name = 'Edit Category'")
	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Edit Category']")
	public WebElement editCategoryLabel;
	
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name BEGINSWITH 'Category Type'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Expense']")
	public WebElement createCategoryType;
	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeOther' AND name = 'Expense'")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Expense']")
	public WebElement expenseTypeOption;

	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeOther' AND name = 'Income'")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Income']")
	public WebElement incomeTypeOption;
	
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name = 'Apply'`][1]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text= 'Apply']")
	public WebElement applyButton;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField")
	@AndroidFindBy(xpath="//android.widget.EditText")
	public WebElement categoryNameTxtField1;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`name=='search category'`]")
	@AndroidFindBy(xpath="//android.widget.EditText")
	public WebElement searchCategoryTextField1;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Category Name'`]/**/XCUIElementTypeTextField")
	@AndroidFindBy(xpath="//*[@text='Category Name']/../android.widget.EditText")
	public WebElement categoryNameTxtField;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Delete'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Delete']")
	public WebElement deleteButton;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Update'`][-1]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Update']")
	public WebElement updateButton;
	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeStaticText' AND name = 'Category already exists'")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Category already exists')]")
	public WebElement categoryExistsLabel;
	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeButton' AND name = 'OK'")
	@AndroidFindBy(xpath = "//android.widget.Button(@text='OK')")
	public WebElement okButton;
	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeStaticText' AND name CONTAINS 'Delete'")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Delete')]")
	public WebElement deleteCategorylabel;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name = 'Continue'`]")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='CONTINUE']")
	public WebElement continueButton;
	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeStaticText' AND name CONTAINS 'This category is currently in use'")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'This category is currently in use.')]")
	public WebElement catCurrentlyInUseText;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Reassign & delete'`][-1]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Reassign & delete']")
	public WebElement reassignDeleteButton;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Cancel Reassign & delete'`][-1]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Cancel']")
	public WebElement cancelReassignDeleteButton;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Reassigning to'`][-1]")
	//@AndroidFindBy(xpath="//android.widget.ImageView[@index=2]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Reassigning to']/../android.widget.TextView[2]")
	public WebElement reassigningTo;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Subcategory of'`][-1]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Subcategory of']/../android.widget.TextView[2]")
	public WebElement subCategoryOf;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Create'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Create']")
	public WebElement createButton;
	
	// check this xpath
	//@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'create'`][-1]")
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeStaticText' AND name = 'Create New Category'")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Create')]")
	public WebElement createCategoryLabel;
	
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'closeChoose Category'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc = 'closeChoose Category']")
	public WebElement closeChooseCategory;
	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeStaticText' AND name = 'A-test-reassignCategory'")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text = 'A-test-reassignCategory']")
	public WebElement sampleReassignCategory;
	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeButton' AND name = 'Continue'")
	@AndroidFindBy(xpath = "//android.widget.Button[@text = 'CONTINUE']")
	public WebElement continueUpdateButton;
	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeButton' AND name = 'Cancel'")
	@AndroidFindBy(xpath = "//android.widget.Button[@text = 'CANCEL']")
	public WebElement cancelUpdateButton;
	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeStaticText' AND name contains 'Previous transactions'")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Previous transactions')]")
	public WebElement updateTextlabel;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Subcategory of None'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text = 'None']")
	public WebElement defaultSubcategoryValue;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Category Type Income'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Income']")
	public WebElement categoryTypeValueIncome;
	
	
	
	
	
	
	
	
	
	
	public void createCategory (CategoryRecord cr ) throws Exception {
		
		
		
		if(Verify.objExists(manageCategoriesLabel)){
			
			this.addCategoryButton.click();
			Thread.sleep(1000);
		}
		
		
		
		categoryNameTxtField1.sendKeys(cr.getCategoryName());
		Thread.sleep(1000);
		
		Verify.waitForObject(createCategoryType, 2);
		createCategoryType.click();
		
		if(cr.getCategoryType()=="Expense") {
			
			expenseTypeOption.click();
		}
		else {
			incomeTypeOption.click();
		}
		
		Verify.waitForObject(applyButton, 2);
		applyButton.click();
		
		
		//subCategoryOf.click();
		
		Thread.sleep(2000);
		//searchCategoryTextField.sendKeys(cr.getSubCategoryOf());
		Thread.sleep(1000);
		if(cr.getSubCategoryOf() != null) {
			
		TransactionDetailPage td = new TransactionDetailPage();
		td.selectCategory(cr.getSubCategoryOf());
		
		Thread.sleep(1000);
		}
		
		this.createButton.click();
		Thread.sleep(1000);	
		
	}
	
	
	
/*	public void createdCategoryValidation(CategoryRecord cr) throws Exception {
		
		TransactionDetailPage td = new TransactionDetailPage();
		
		
		td.searchCategory(cr.getCategoryName());
		
	      AllAccountsPage aa = new AllAccountsPage();
			
			List<WebElement> li = aa.getAllSearchTransactions ();
			Commentary.log(LogStatus.INFO, "No of Transactions appeared in the search .."+li.size());
			
			if (li.isEmpty())
				Commentary.log(LogStatus.FAIL, "Category was not created successfully");
			else
				Commentary.log(LogStatus.PASS, "Category got created successfully");
		
	}*/
	
	
	
	
	public void categoryAvailability(String category) throws Exception {

		Helper h = new Helper();
		SoftAssert sa = new SoftAssert();

		if (h.getEngine().equals("android")) {
			
			String sXpath = "//android.widget.TextView[@text = '"+category+"']";
			
			if (Verify.objExists((WebElement)Engine.getDriver().findElement(By.xpath(sXpath)))) {
				
			
				Engine.getDriver().findElement(By.xpath(sXpath)).click();
				Engine.getDriver().findElement(By.xpath(sXpath)).click();
				
				if(Verify.objExists(editbutton)) {
					Commentary.log(LogStatus.INFO, "Edit button is visible");
				}
				else {
					Engine.getDriver().findElement(By.xpath(sXpath)).click();
				}
				Commentary.log(LogStatus.PASS, "Category got created or updated");
			}
			else {
				Commentary.log(sa, LogStatus.FAIL, "Category did not get created or updated");
			}
						
				
			}

		
	
		
		else {
			String sXpath = "**/XCUIElementTypeOther[`name='RadioButton "+category+"'`]";
			
			if(Verify.objExists(Engine.getDriver().findElement(AppiumBy.iOSClassChain(sXpath)))) {
				
				Engine.getDriver().findElement(AppiumBy.iOSClassChain(sXpath)).click();
				
				Engine.getDriver().findElement(AppiumBy.iOSClassChain(sXpath)).click();
				
				if(Verify.objExists(editbutton)) {
					Commentary.log(LogStatus.INFO, "Edit button is visible");
				}
				else {
					Engine.getDriver().findElement(AppiumBy.iOSClassChain(sXpath)).click();
				}
				
				
	Commentary.log(LogStatus.PASS, "Category got created or updated");
			}
			else {
				Commentary.log(sa, LogStatus.FAIL, "Category did not get created or updated");
			}
				
		}
		
		sa.assertAll();
}	
	
	
	
	public void deleteCategory() throws Exception {
		
		
		Helper h = new Helper();
		
		Thread.sleep(1000);
		this.editbutton.click();
		Thread.sleep(1000);
		this.deleteButton.click();
		//Thread.sleep(1000);
		
		if(Verify.objExists(deleteCategorylabel)) {
			
			continueButton.click();
			Thread.sleep(1000);
		}
		else {
			
			Commentary.log(LogStatus.INFO, "Delete category alert did not appeared.");
			
		}	
	}
	
	
public void reassignAndDeleteCategory(CategoryRecord cr) throws Exception {
		
		
		Helper h = new Helper();
		
		Thread.sleep(1000);
		this.editbutton.click();
		Thread.sleep(1000);
		this.deleteButton.click();
		
		Thread.sleep(1000);
		reassigningTo.click();
		
		if(cr.getreassignTo()==true) {
			
			if (h.getEngine().equals("android")) {
				
				Engine.getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"A-test-reassignCategory\").instance(0))"));
				
				Thread.sleep(1000);
				sampleReassignCategory.click();
			}
			else {
				
				JavascriptExecutor js = (JavascriptExecutor) Engine.getDriver(); 
				HashMap<String, String> scrollObject = new HashMap(); 
				scrollObject.put("direction", "up"); 
				
				Thread.sleep(1000);
				sampleReassignCategory.click();
				}
			}
		
		else {
			
			closeChooseCategory.click();
		}
		
		
		
		if(Verify.objExists(catCurrentlyInUseText)) {
			
			reassignDeleteButton.click();
			Thread.sleep(1000);
		}
		else {
			
			Commentary.log(LogStatus.INFO, "Delete category alert did not appeared.");
			}	
	}

public void updateCategory(CategoryRecord cr ) throws Exception {
	
	Helper h = new Helper();
	
	Thread.sleep(1000);
	this.editbutton.click();
	Thread.sleep(1000);
	
	categoryNameTxtField1.clear();
	
	categoryNameTxtField1.sendKeys(cr.getCategoryName());
	
	createCategoryType.click();
	
//	if(cr.getCategoryType()=="Expense") {
//		
//		expenseTypeOption.click();
//	}
//	else {
//		incomeTypeOption.click();
//	}
	
	Thread.sleep(1000);
	TransactionDetailPage td = new TransactionDetailPage();
	td.selectCategory(cr.getSubCategoryOf());
	
	Thread.sleep(1000);
	
	this.updateButton.click();
	
	if(Verify.objExists(this.updateTextlabel)) {
		Commentary.log(LogStatus.INFO, "Update text is avilable");
		continueUpdateButton.click();
	}
	else {
		Commentary.log(LogStatus.INFO, "update text for category did not appear.");
	}
	Thread.sleep(1000);
	
	
}
}
