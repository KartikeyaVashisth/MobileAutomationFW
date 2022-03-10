package dugout;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import support.Engine;

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
	public MobileElement searchCategoryTextField;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Cash & ATM'`]")
	@AndroidFindBy(xpath="//*[@text='Cash & ATM']")
	public MobileElement cashAtmParentCat;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Parentcat'`]")
	@AndroidFindBy(xpath="//*[@text='Parentcat']")
	public MobileElement parentcatCategory;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Firstlevelcat'`]")
	@AndroidFindBy(xpath="//*[@text='Firstlevelcat']")
	public MobileElement firstLevelCatCategory;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'addCategoryButton'`][-1]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='addCategoryButton']")
	public MobileElement addCategoryButton;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Manage Categories'`]")
	@AndroidFindBy(xpath = "//android.view.View[contains(@text,'Manage Categories')]")
	public MobileElement manageCategoriesLabel;
	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeOther' AND name = 'create category'")
	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='create category']")
	public MobileElement createCategory;
	
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeButton[`name='Go back'`]")
	@AndroidFindBy(xpath="//android.widget.Button[@content-desc='Settings, back']")
	public MobileElement backButton;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'edit'`][-1]")
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc = 'edit']")
	public MobileElement editbutton;
	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeOther' AND name = 'Edit Category'")
	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Edit Category']")
	public MobileElement editCategoryLabel;
	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeOther' AND name = 'Expense'")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Expense')]")
	public MobileElement expenseTypeOption;

	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeOther' AND name = 'Income'")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Income')]")
	public MobileElement incomeTypeOption;
	
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name = 'Apply'`][1]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Apply')]")
	public MobileElement applyButton;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField")
	@AndroidFindBy(xpath="//android.widget.EditText")
	public MobileElement categoryNameTxtField1;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`name=='search category'`]")
	@AndroidFindBy(xpath="//android.widget.EditText")
	public MobileElement searchCategoryTextField1;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Category Name'`]/**/XCUIElementTypeTextField")
	@AndroidFindBy(xpath="//*[@text='Category Name']/../android.widget.EditText")
	public MobileElement categoryNameTxtField;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Delete'`][-1]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Delete']")
	public MobileElement deleteButton;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Update'`][-1]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Update']")
	public MobileElement updateButton;
	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeStaticText' AND name = 'Category already exists'")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Category already exists')]")
	public MobileElement categoryExistsLabel;
	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeButton' AND name = 'OK'")
	@AndroidFindBy(xpath = "//android.widget.Button(@text='OK')")
	public MobileElement okButton;
	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeStaticText' AND name CONTAINS 'Delete'")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Delete')]")
	public MobileElement deleteCategorylabel;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name = 'Continue'`]")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='CONTINUE']")
	public MobileElement continueButton;
	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeStaticText' AND name CONTAINS 'This category is currently in use'")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'This category is currently unused')]")
	public MobileElement catCurrentlyInUseText;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Reassign & delete'`][-1]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Reassign & delete']")
	public MobileElement reassignDeleteButton;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Cancel Reassign & delete'`][-1]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Cancel']")
	public MobileElement cancelReassignDeleteButton;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Reassigning to'`][-1]")
	@AndroidFindBy(xpath="//android.widget.ImageView[@index=2]")
	public MobileElement reassigningTo;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
