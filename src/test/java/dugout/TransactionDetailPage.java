package dugout;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;

import java.awt.RenderingHints.Key;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import referee.Commentary;
import referee.Verify;
import support.Engine;
import support.Helper;
import support.TransactionRecord;
import support.CategoryRecord;


public class TransactionDetailPage {

	public TransactionDetailPage () {
		try {

			PageFactory.initElements(new AppiumFieldDecorator(Engine.getDriver()),this);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	//	@iOSXCUITFindBy(iOSClassChain="**/*[`name=='Always Allow'`]")
	@iOSXCUITFindBy(iOSClassChain="**/*[`name=='Allow While Using App'`]")
	//	@AndroidFindBy(xpath="//android.widget.Button[@text='ALLOW']")
	//	@AndroidFindBy(xpath="//android.widget.Button[@text='Allow only while using the app']")
	@AndroidFindBy(xpath="//android.widget.Button[contains(@text,'using the app')]")
	public MobileElement allowButton;

	@iOSXCUITFindBy(iOSClassChain="**/*[`name=='Deny'`]")
	@AndroidFindBy(xpath="//android.widget.Button[@text='Deny']")
	public MobileElement denyButton;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name=='Add Transaction'`]")
	@AndroidFindBy(xpath="//*[@text='Add Transaction']")
	public MobileElement addTransactionTxt;

	//@iOSFindBy(xpath="//XCUIElementTypeNavigationBar[@name=\"View Transaction\"]")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name=='View Transaction'`]")
	@AndroidFindBy(xpath="//*[@text='View Transaction']")
	public MobileElement viewTransactionTxt;

	@iOSXCUITFindBy(iOSNsPredicate="type = 'XCUIElementTypeButton'")
	@AndroidFindBy(xpath="//android.widget.Button")
	public MobileElement backButton;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name='pop'`][-1]")
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='pop']")
	public MobileElement backButtonOnViewTransactionPage;

	//@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Money In\"]")
	@iOSXCUITFindBy(iOSNsPredicate= "name = 'Money In'")
	@AndroidFindBy(xpath="//*[@text='Money In']")
	public MobileElement moneyIn;

	//@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Money Out\"]")
	@iOSXCUITFindBy(iOSNsPredicate= "name = 'Money Out'")
	@AndroidFindBy(xpath="//*[@text='Money Out']")
	public MobileElement moneyOut;

	//@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Money In Money Out\"]/../XCUIElementTypeOther[contains(@name,'$')]")
	//@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name BEGINSWITH 'Money In Money Out'`]/XCUIElementTypeOther[`name BEGINSWITH 'Amount'`]")
	//@AndroidFindBy(xpath="//*[@content-desc='-$0.00']//android.widget.TextView")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name BEGINSWITH 'Amount'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text, '$')]")
	public MobileElement amount;


	//@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Date\"]")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='Date'`]")
	@AndroidFindBy(xpath="//*[@text='Date']")
	public MobileElement dateLabel;

	//@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name='Date']/../XCUIElementTypeStaticText[2]")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Date'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//*[@text='Date']/../android.widget.TextView[@index=2]")
	public MobileElement selectedDate;

	// -------------- Date picker --------------

	//@iOSFindBy(xpath="//XCUIElementTypeDatePicker")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeDatePicker")
	@AndroidFindBy(xpath="//android.widget.DatePicker")
	public MobileElement datePicker;

	//@iOSFindBy(xpath="//XCUIElementTypePickerWheel[1]")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypePickerWheel[1]")
	@AndroidFindBy(xpath="//android.widget.DatePicker//android.widget.NumberPicker[@index=0]/android.widget.EditText")
	//@AndroidFindBy(xpath="//android.widget.DatePicker//android.widget.NumberPicker[@index=0]")
	public MobileElement enterMonth;

	//@iOSFindBy(xpath="//XCUIElementTypePickerWheel[2]")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypePickerWheel[2]")
	//@AndroidFindBy(xpath="//android.widget.DatePicker//android.widget.NumberPicker[@index=1]")
	@AndroidFindBy(xpath="//android.widget.DatePicker//android.widget.NumberPicker[@index=1]/android.widget.EditText")
	public MobileElement enterDate;

	//@iOSFindBy(xpath="//XCUIElementTypePickerWheel[3]")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypePickerWheel[3]")
	@AndroidFindBy(xpath="//android.widget.DatePicker//android.widget.NumberPicker[@index=2]/android.widget.EditText")
	//@AndroidFindBy(xpath="//android.widget.DatePicker//android.widget.NumberPicker[@index=2]")
	public MobileElement enterYear;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name='Cancel'`]")
	@AndroidFindBy(xpath="//*[@text='CANCEL']")
	public MobileElement buttonCancel;

	@iOSXCUITFindBy(iOSClassChain="**/*[`name=='OK'`]")
	@AndroidFindBy(xpath="//*[@text='OK']")
	public MobileElement buttonOK;

	// -------------- Date picker --------------

	//@iOSFindBy(xpath="//XCUIElementTypeImage[@name=\"assets/Quicken/App/Images/accountIcon@2x.png\"]")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name CONTAINS \"Account\"`]")
	@AndroidFindBy(xpath="//android.widget.ScrollView//android.widget.TextView[contains(@text, 'Account')]")
	public MobileElement account;

	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Choose an Account\"]")
	@AndroidFindBy(xpath="//*[@text='Choose an Account']")
	public MobileElement chooseAnAccount;

	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Account\"]/../XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Account']/../android.widget.TextView[2]")
	public MobileElement selectedAccount;

	//@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"closeChoose an Account\"]")
	@iOSXCUITFindBy(id="closeChoose an Account")
	@AndroidFindBy(xpath="//*[@content-desc='closeChoose an Account']/android.widget.ImageView")
	public MobileElement closeChooseAccount;

	//@iOSFindBy(xpath="//XCUIElementTypeTextField[@value=\"Add Check Number\"]/")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`value='Add Check Number'`]")
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Add Check Number']")
	public MobileElement addCheckNumber;

	//@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Check Number\"]//XCUIElementTypeTextField")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Check Number'`]/**/XCUIElementTypeTextField")
	@AndroidFindBy(xpath="//*[@text='Check Number']/../android.widget.EditText")
	public MobileElement enteredCheckNumber;

	//@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Done\"]")
	//@iOSXCUITFindBy(iOSNsPredicate = "name='Done'")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Done'`][2]")
	@AndroidFindBy(xpath="//*[@text='Done']")
	//@AndroidFindBy(xpath="//*[@content-desc='Done']/android.widget.TextView")
	public MobileElement buttonDone;

	//@iOSXCUITFindBy(iOSNsPredicate = "name='Update'")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Update'`][2]")
	@AndroidFindBy(xpath="//*[@text='Update']")
	public MobileElement buttonUpdate;

	//@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Payee\"]")
	//@iOSFindBy(id="assets/Quicken/App/Images/payeeIcon@2x.png")
	@iOSXCUITFindBy(id="Payee")
	@AndroidFindBy(xpath="//*[@text='Payee']")
	public MobileElement payee;

	//@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Payee\"]/../XCUIElementTypeStaticText[2]")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Payee'`]/**/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//*[@text='Payee']/../android.widget.TextView[@index=2]")
	public MobileElement selectedpayee;

	// ------------------ Payee SCREEN ------------------

	//@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"closePayee\"]")
	@iOSXCUITFindBy(id="closePayee")
	@AndroidFindBy(xpath="//*[@content-desc='closePayee']/android.widget.ImageView")
	public MobileElement closePayee;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='closeEdit Payee'`]")
	@AndroidFindBy(xpath = "//*[@content-desc='closeEdit Payee']/android.widget.ImageView")
	public MobileElement closeEditPayee;

	//@iOSFindBy(xpath="//XCUIElementTypeTextField[@name='search payee']")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeTextField[`name == 'search payee'`]")
	//@iOSXCUITFindBy(id="search payee")
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Search Payee']")
	public MobileElement searchPayee;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeTextField[`name = 'EditPayee'`]")
	@AndroidFindBy(xpath="//android.widget.EditText[@content-desc='EditPayee']")
	public MobileElement editPayee;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Update'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Update']")
	public MobileElement updateButton;

	@iOSXCUITFindBy(id="create payee")
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc=\"create payee\"]")
	public MobileElement createPayee;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='All Payees'`][1]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='All Payees']")
	public MobileElement allPayeesLabel;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Near by'`][1]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Near by']")
	public MobileElement yelpNearByLabel;

	// ------------------ Payee SCREEN ------------------

	//@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Category\"]")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name=='Category'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Category']")
	public MobileElement category;

	//@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Categories\"]")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name=='Categories'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Categories']")
	public MobileElement categories;

	//@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Category\"]/../XCUIElementTypeStaticText[2]")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Category'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Category']/../android.widget.TextView[@index=2]")
	public MobileElement selectedCategory;

	//@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Tags\"]")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Tags'`]")
	@AndroidFindBy(xpath="//*[@text='Tags']")
	public MobileElement tags;

	//@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Tags\"]/../XCUIElementTypeStaticText[2]")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Tags'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Tags']/../android.widget.TextView[@index=2]")
	public MobileElement selectedTags;

	// ------------------ TAG SCREEN ------------------

	//@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"closeTags\"]")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='closeTags'`]")
	@AndroidFindBy(xpath="//*[@content-desc='closeTags']")
	public MobileElement closeTags;

	//@iOSFindBy(xpath="//XCUIElementTypeTextField[@name=\"search tags\"]")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`name='search tags'`]")
	@AndroidFindBy(xpath="//android.widget.EditText[@content-desc='search tags']")
	public MobileElement searchTags;

	//@iOSFindBy(xpath="//XCUIElementTypeTextField")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[1]")
	@AndroidFindBy(xpath="//*[@class='android.widget.EditText']")
	public MobileElement getTagSearchText;

	@iOSXCUITFindBy(id="create item")
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc=\"create item\"]")
	public MobileElement createTag;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name = 'Apply'`][-1]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Apply']")
	public MobileElement tagsApplyButton;

	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeTextField' AND name = 'search tags'")
	@AndroidFindBy(xpath="//android.widget.EditText[@content-desc='search tags']")
	public MobileElement searchTagTextField;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name=='Apply'`][-1]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Apply']")
	public MobileElement applyTags;

	// ------------------ TAG SCREEN ------------------

	// ------------------ Category SCREEN ------------------
	//@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"closeCategory\"]/XCUIElementTypeImage")
	//@iOSFindBy(id="closeCategories")
	@iOSXCUITFindBy(id="closeCategory")
	@AndroidFindBy(xpath="//*[@content-desc='closeCategory']/android.widget.ImageView")
	public MobileElement closeCategory;

	//@iOSFindBy(xpath="//XCUIElementTypeTextField[@value=\"Search Category\"]")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`name=='search category'`]")
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Search Category']")
	public MobileElement searchCategoryTextField;
	/*
	@iOSFindBy(xpath="//*[normalize-space(@name)='Back']")
	@AndroidFindBy(xpath="//*[@class='android.widget.EditText']")
	public MobileElement getCategorySearchText;*/

	// ------------------ Category SCREEN ------------------

	// for android getText attribute will return the entered notes
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextView")
//	@AndroidFindBy(xpath="//android.widget.TextView[@text='Tags']/../../following-sibling::android.view.ViewGroup[2]//android.widget.EditText")
	@AndroidFindBy(xpath="(//android.widget.EditText)[2]")
	public MobileElement note;

	@iOSFindBy(xpath="//XCUIElementTypeImage[@name=\"assets/Quicken/App/Images/noteIcon@2x.png\"]/..//XCUIElementTypeTextView")
	@AndroidFindBy(xpath="//android.widget.EditText[@index='2']")
	public MobileElement enteredNote;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='button Done'`][2]")
	@AndroidFindBy(xpath="//*[@text='Done']")
	public MobileElement buttonDone_OSKeyBoard;

	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Add Attachments\"]")
	@AndroidFindBy(xpath="//*[@text='Add Attachments']")
	public MobileElement addAttachment;

	@iOSFindBy(xpath="//*[normalize-space(@name)='Back']")
	@AndroidFindBy(xpath="//*[@content-desc='deleteAttachment']")
	public MobileElement deleteAttachment;

	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Library\"]")
	@AndroidFindBy(xpath="//*[@text='Library']")
	public MobileElement library;

	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Camera\"]")
	@AndroidFindBy(xpath="//*[@text='Camera']")
	public MobileElement camera;

	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@name=\"Cancel\"]")
	@AndroidFindBy(xpath="//*[@text='Cancel']")
	public MobileElement cancel;

	@iOSXCUITFindBy(id="button Save")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Save']")
	public MobileElement buttonSave;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name='save'`][-1]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Save']")
	public MobileElement buttonSave1;

	//@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Delete Transaction\"]")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name=='Delete Transaction'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Delete Transaction']")
	public MobileElement deleteTransaction;

	@iOSXCUITFindBy(id="Delete")
	@AndroidFindBy(id="android:id/button1")
	public MobileElement deleteTransactionAlertButton;

	//@iOSFindBy(xpath="//XCUIElementTypeAlert[@name=\"Warning!\"]")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeAlert[`name='Warning!'`]")
	@AndroidFindBy(id="android:id/alertTitle")
	public MobileElement deleteTransferTransactionWarning;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name BEGINSWITH 'You are'`]")
	@AndroidFindBy(id="android:id/message")
	public MobileElement deleteWarningMessage;

	@iOSFindBy(xpath="//XCUIElementTypeOther[contains(@name,'Hotel Tags Add Note Add Note')]/../XCUIElementTypeOther[contains(@name,'$')]")
	@AndroidFindBy(xpath="//android.view.ViewGroup[@index='1']/android.widget.TextView")
	public MobileElement spiltAmount1;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name=='Go to other side'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Go to other side']")
	public MobileElement buttonGoToOtherSide; 

	//@iOSXCUITFindBy(xpath="//XCUIElementTypeAlert[@name=\"Error!\"]")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name BEGINSWITH 'You'`]")
	@AndroidFindBy(id="android:id/message")
	public MobileElement errorMsgText;

	//	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeSwitch'")
	//	@AndroidFindBy(xpath="//android.widget.Switch[@content-desc=\"Split\"]")
	//	public MobileElement splitSwitch;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeSwitch[`name BEGINSWITH 'Switch Value:'`]")
	@AndroidFindBy(xpath="//android.widget.Switch[contains(@content-desc,'Switch Value:')]")
	public MobileElement splitSwitch;

	@iOSXCUITFindBy(iOSNsPredicate="type = 'XCUIElementTypeAlert'")
	@AndroidFindBy(id="android:id/alertTitle")
	public MobileElement matchingTransactionAlertMessageTxt;

	@iOSXCUITFindBy(id="Match")
	@AndroidFindBy(xpath="//android.widget.Button[@text=\"MATCH\"]")
	public MobileElement buttonMatch;

	@iOSXCUITFindBy(id="Don't Match")
	@AndroidFindBy(xpath="//android.widget.Button[@text=\"DON'T MATCH\"]")
	public MobileElement buttonDontMatch;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name BEGINSWITH 'Status'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//*[@text='Status']/../android.widget.TextView[@index=2]")
	public MobileElement transactionStatus;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name=='Status'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Status']")
	public MobileElement status;

	@iOSXCUITFindBy(iOSNsPredicate="name CONTAINS 'Reviewed' AND type = 'XCUIElementTypeStaticText'")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text, 'Reviewed')]")
	public MobileElement downloadedTransactionStatus;

	@iOSXCUITFindBy(iOSClassChain = "**/*[`name=='Apply'`][-1]")
	@AndroidFindBy(xpath="//*[@text='Apply']")
	public MobileElement applyBtn;

	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeOther' AND name = 'Update'")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Update']")
	public MobileElement update;

	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeStaticText' AND name = 'Error!'")
	@AndroidFindBy(xpath = "//android.widget.TextView(@text='Error!')")
	public MobileElement errormessage;

	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeStaticText' AND name = 'Savings'")
	@AndroidFindBy(xpath = "//android.widget.TextView(@text='Savings')")
	public MobileElement savingsText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name=='OK'`]")
	@AndroidFindBy(xpath="//android.widget.Button[@text='OK']")
	public MobileElement acceptAlert;
	
	//---------- Category On The Fly -------------
	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeStaticText' AND name = 'Create New Category'")
	@AndroidFindBy(xpath = "//android.widget.TextView(@text='Create New Category')")
	public MobileElement createNewCatLabel;
	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeStaticText' AND name = 'Category Name'")
	@AndroidFindBy(xpath = "//android.widget.TextView(@text='Category Name')")
	public MobileElement categoryNameLabel;
	
	
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name BEGINSWITH 'Category Type'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Expense')]")
	public MobileElement createCategoryType;

	
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name BEGINSWITH 'Subcategory of'`]")
	@AndroidFindBy(xpath="//*[@text='Subcategory of']/../android.widget.ImageView")
	public MobileElement subCategoryOf;
	
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name = 'Cancel'`][1]")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Cancel')]")
	public MobileElement cancelbutton;
	
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name = 'Create'`][1]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Create']")
	public MobileElement createButton;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Category Name'`]/**/XCUIElementTypeTextField")
	@AndroidFindBy(xpath="//*[@text='Category Name']/../android.widget.EditText")
	public MobileElement categoryNameTxtField;
	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeStaticText' AND name = 'Category already exists'")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Category already exists')]")
	public MobileElement categoryExistsLabel;
	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeButton' AND name = 'OK'")
	@AndroidFindBy(xpath = "//android.widget.Button(@text='OK')")
	public MobileElement okButton;
	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeStaticText' AND name = 'Discard Changes?'")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Discard Changes')]")
	public MobileElement discardChangesLabel;

	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeButton' AND name = 'Discard'")
	@AndroidFindBy(xpath="//android.widget.Button(@text = 'DISCARD')")
	public MobileElement discardButton;
	
	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeStaticText' AND name = 'Select Type'")
	@AndroidFindBy(xpath="//android.widget.TextView(@text = 'Select Type')")
	public MobileElement selectTypeLabel;
	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeOther' AND name = 'Expense'")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Expense')]")
	public MobileElement expenseTypeOption;

	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeOther' AND name = 'Income'")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Income')]")
	public MobileElement incomeTypeOption;

	
	
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name = 'Apply'`][1]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Apply')]")
	public MobileElement applyButton;

	
	//@iOSXCUITFindBy(id="closeCategory") 
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeOther' AND name = 'closeCategory' OR name = 'closeSubcategory of'")
	@AndroidFindBy(xpath="//*[@content-desc='closeCategory']/android.widget.ImageView")
	public MobileElement closeCategorySubcategoryOfIcon;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`name=='search category'`]")
	@AndroidFindBy(xpath="//android.widget.EditText")
	public MobileElement searchCategoryTextField1;
	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeOther' AND name = 'create category'")
	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='create category']")
	public MobileElement createCategory;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField")
	@AndroidFindBy(xpath="//android.widget.EditText")
	public MobileElement categoryNameTxtField1;
	
	@iOSXCUITFindBy(iOSClassChain="**/*[`name=='Auto and Travel (Rental)'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Advertising (Rental)')]")
	public MobileElement subcategoryOfCategory;
	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeOther' AND name ='Category Type Expense'")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Expense')]")
	public MobileElement selectedCategoryTypeEx;
	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeOther' AND name ='Category Type Income'")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Income')]")
	public MobileElement selectedCategoryTypeIn;
	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeOther' AND name ='Subcategory of None'")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'None')]")
	public MobileElement subcategoryNone;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='RadioButton Cash & ATM'`]/XCUIElementTypeOther[`name='RadioButton'`]")
	public MobileElement radiobuttonCashATM;




	
	
	
	
	
	
	
	
	
	
	

	public MobileElement getPayeeElement (String payee) {

		String xpath_Android = "//android.view.ViewGroup[@content-desc='"+payee+"']//android.widget.TextView";
		String xpath_IOS = "//XCUIElementTypeStaticText[@name='"+payee+"']" ;

		Helper h = new Helper();

		if (h.getEngine().equals("android")){
			try{
				return (MobileElement) Engine.getDriver().findElementByXPath(xpath_Android);
			}
			catch(Exception E){
				return null;
			}

		}
		else {
			try{
				return (MobileElement) Engine.getDriver().findElementByXPath(xpath_IOS);
			}
			catch(Exception E){
				Commentary.log(LogStatus.INFO, E.getMessage());
				return null;
			}
		}

	}

	public MobileElement getTagElement (String tagText) {

		String xpath_Android = "//android.view.ViewGroup[@content-desc='"+tagText+"']//android.widget.TextView";
		String xpath_IOS = "//XCUIElementTypeStaticText[@name='"+tagText+"']" ;

		Helper h = new Helper();

		if (h.getEngine().equals("android")){
			try{
				return (MobileElement) Engine.getDriver().findElementByXPath(xpath_Android);
			}
			catch(Exception E){
				return null;
			}

		}
		else {
			try{
				return (MobileElement) Engine.getDriver().findElementByXPath(xpath_IOS);
			}
			catch(Exception E){
				Commentary.log(LogStatus.INFO, E.getMessage());
				return null;
			}
		}

	}

	public MobileElement getCategory (String categoryText) {

		String xpath_Android = "//android.widget.TextView[@text='"+categoryText+"']";
		String xpath_IOS = "//XCUIElementTypeStaticText[@name='"+categoryText+"']" ;


		Helper h = new Helper();

		if (h.getEngine().equals("android")){
			try{
				return (MobileElement) Engine.getDriver().findElementByXPath(xpath_Android);
			}
			catch(Exception E){
				Commentary.log(LogStatus.INFO, E.getMessage());
				return null;
			}

		}
		else {
			try{
				return (MobileElement) Engine.getDriver().findElementByXPath(xpath_IOS);
			}
			catch(Exception E){
				Commentary.log(LogStatus.INFO, E.getMessage());
				return null;
			}
		}	

	}


	public MobileElement getAccount (String accountText) {

		String xpath_Android = "//android.widget.TextView[@text='"+accountText+"']";
		String xpath_IOS = "//XCUIElementTypeStaticText[@name='"+accountText+"']";

		Helper h = new Helper();

		if (h.getEngine().equals("android")){
			try{
				return (MobileElement) Engine.getDriver().findElementByXPath(xpath_Android);
			}
			catch(Exception E){
				Commentary.log(LogStatus.INFO, E.getMessage());
				return null;
			}

		}
		else {
			try {
				return (MobileElement) Engine.getDriver().findElementByXPath(xpath_IOS);
			}
			catch(Exception E){
				Commentary.log(LogStatus.INFO, E.getMessage());
				return null;
			}
		}	

	}

	public void addSplit(TransactionRecord tr, HashMap<Integer,String> amount, HashMap<Integer,String> cat, HashMap<Integer,String[]> tags) throws Exception{

		
		
		// Allow Quicken to access this device's location?
		if (Verify.objExists(this.allowButton)) {
			this.allowButton.click();
			Thread.sleep(1000);
		}
		
		
		
		if (Verify.objExists(this.buttonDone)||Verify.objExists(this.viewTransactionTxt)) {
			Commentary.log(LogStatus.INFO,"AddTransaction screen got dispalyed.");
			

			if (tr.getTransactionType() != null)
				this.enterTransactionType(tr.getTransactionType());
			
			if (tr.getAmount() != null) 
				this.enterAmount(tr.getAmount());
			
			if (tr.getAccount() != null)
				this.selectAccount(tr.getAccount());
			
			if (tr.getPayee() != null)
				this.selectPayee(tr.getPayee());
			
			
			
		this.splitSwitch.click();
		Thread.sleep(1000);

		for (int iCount=1; iCount<=cat.size(); iCount++) {
			this.enterSplitAmt(iCount, amount.get(iCount));
			this.tapOnSplitCategory(iCount);
			this.selectCategoryOnDrawer(cat.get(iCount));

			if (tags.get(iCount) != null) {
				this.tapOnSplitTag(iCount);
				this.selectTags_split(tags.get(iCount));

			}

		}
		this.buttonSave.click();
		Thread.sleep(1000);
		
	}
	}

	public void tapOnSplitTag(Integer index) throws Exception{	
		Helper h = new Helper();
		if (h.getEngine().equals("android"))
			tapOnSplitTag_android(index);
		else
			tapOnSplitTag_ios(index);
	}

	private void tapOnSplitTag_ios (Integer index) throws Exception{
		//String cc = "**/XCUIElementTypeOther[`name BEGINSWITH 'Tags:'`]["+index+"]/**/XCUIElementTypeStaticText";
		String cc = "**/XCUIElementTypeOther[`name BEGINSWITH 'Tags:'`]["+index+"]/**/XCUIElementTypeStaticText";

		Thread.sleep(1000);
		Engine.getDriver().findElement(MobileBy.iOSClassChain(cc)).click();
	}

	private void tapOnSplitTag_android (Integer index) throws Exception{
		String xpath = "(//android.view.ViewGroup[contains(@content-desc,'Tags:')])["+index+"]//android.widget.TextView";
		Engine.getDriver().findElement(By.xpath(xpath)).click();
	}

	public void selectTags_split (String[] sTag) throws Exception {

		Helper h = new Helper();

		if (h.getEngine().equals("android"))
			this.selectTags_android_new_split(sTag);
		else
			this.selectTags_ios_new_split(sTag);


	}

	private void selectTags_ios_new_split (String[] sTags) throws Exception {

//		for  (String tag:sTags){
//			String sXpath = "**/XCUIElementTypeOther[`name ENDSWITH '"+tag+"' AND visible==true`][-1]";
//			this.searchTag(tag);
//			Engine.getDriver().findElement(MobileBy.iOSClassChain(sXpath)).click();
//			Thread.sleep(500);
//		}
//
//		this.applyTags.click();
		
		for(String tag:sTags){
			Engine.getDriver().findElement((MobileBy.iOSClassChain("**/XCUIElementTypeOther[`name='Tags'`]"))).click();
			Verify.waitForObject(this.searchTags, 1);
			this.searchTags.sendKeys(tag);
			Helper h = new Helper();
			h.hideKeyBoard();
			Thread.sleep(1000);

			String createTag_xpath="**/XCUIElementTypeOther[`name='create item'`]";
			String cc = "**/XCUIElementTypeStaticText[`name='"+tag+"'`]";

			if (Verify.objExists(createTag)) {
				Engine.getDriver().findElement(MobileBy.iOSClassChain(createTag_xpath)).click();
				Commentary.log(LogStatus.INFO,"Creating Tag... "+tag);

				Engine.getDriver().findElement(MobileBy.iOSClassChain(cc)).click();
				Thread.sleep(500);
				Commentary.log(LogStatus.INFO,"Selected Tag which is just now created.. "+tag);
				h.hideKeyBoard();
				Thread.sleep(1000);
			}
			else {
				Engine.getDriver().findElement(MobileBy.iOSClassChain(cc)).click();
				Thread.sleep(500);
				Commentary.log(LogStatus.INFO,"Selected Tag which is already present.. "+tag);
				h.hideKeyBoard();
				Thread.sleep(1000);
			}
			Thread.sleep(1000);
		}
		this.tagsApplyButton.click();
		Thread.sleep(1000);	
	}

	private void selectTags_android_new_split (String[] sTags) throws Exception {

		//String sXpath;// = "//android.widget.TextView[@text='"+category+"']";
//		Integer iCount;
//
//		for  (String tag:sTags){
//			String sXpath = "//android.widget.TextView[@text='"+tag+"']";
//			this.searchTag(tag);
//			Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ tag + "\").instance(0))"));
//			Thread.sleep(1000);
//			Engine.getDriver().findElement(By.xpath(sXpath)).click();
//			Thread.sleep(1000);	
//		}
//
//		System.out.println(Engine.getDriver().getContext());
//		this.applyTags.click();
//		Thread.sleep(1000);
//		System.out.println(Engine.getDriver().getContext());	
		
		for(String tag:sTags){
			this.searchTag(tag);
			Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ tag + "\").instance(0))"));

			String createTag_xpath = "//android.widget.TextView[@content-desc='create item']";
			String cc = "//android.widget.TextView[@text='"+tag+"']";

			if (Verify.objExists(createTag)) {
				Engine.getDriver().findElement(By.xpath(createTag_xpath)).click();
				Commentary.log(LogStatus.INFO,"Creating Tag... "+tag);
				Thread.sleep(2000);

				Engine.getDriver().findElement(By.xpath(cc)).click();
				Thread.sleep(1000);
				Commentary.log(LogStatus.INFO,"Selected Tag which is just now created.. "+tag);
			}
			else {
				Engine.getDriver().findElement(By.xpath(cc)).click();
				Thread.sleep(500);
				Commentary.log(LogStatus.INFO,"Selected Tag which is already present.. "+tag);
			}
		}
		this.tagsApplyButton.click();
		Thread.sleep(1000);

	}

	public void searchTag (String sTag) throws Exception{

		this.searchTagTextField.clear();
		this.searchTagTextField.sendKeys(sTag);
		Engine.getDriver().hideKeyboard();
	}

	public void tapOnSplitCategory(Integer index) throws Exception{	
		Helper h = new Helper();
		if (h.getEngine().equals("android"))
			tapOnSplitCategory_android(index);
		else
			tapOnSplitCategory_ios(index);
	}

	private void tapOnSplitCategory_ios (Integer index) throws Exception{
		String cc = "**/XCUIElementTypeOther[`name BEGINSWITH 'Category:'`]["+index+"]/**/XCUIElementTypeStaticText";
		Engine.getDriver().findElement(MobileBy.iOSClassChain(cc)).click();
	}

	private void tapOnSplitCategory_android (Integer index) throws Exception{
		String xpath = "(//android.view.ViewGroup[contains(@content-desc,'Category:')])["+index+"]//android.widget.TextView";
		Engine.getDriver().findElement(By.xpath(xpath)).click();
	}

	public void enterSplitAmt(Integer index, String amount) throws Exception{

		Helper h = new Helper();

		if (h.getEngine().equals("android"))
			enterSplitAmount_android(index,amount);
		else
			enterSplitAmount_ios(index,amount);
	}

	public void enterSplitAmount_android(Integer index, String sAmount) throws Exception {

		String[] s = sAmount.split("");
		int iCount;

//		index++;

		String xpath = "(//android.view.ViewGroup[contains(@resource-id,'Amount:')]//android.widget.TextView)["+index+" +1]";
		Engine.getDriver().findElement(By.xpath(xpath)).click();


		for (int i = 1; i < 6; i++) {
			Engine.getDriver().findElement(By.xpath("//android.widget.ImageView[@content-desc='delete']")).click();
		}

		for(iCount=0; iCount<s.length; iCount++) {

			if (s[iCount].equals(".")) {
				// ignore
			}
			else
				Engine.getDriver().findElement(By.xpath("//*[@text='"+s[iCount]+"']")).click();	
		}

		if (Verify.objExists(this.buttonDone))
			this.buttonDone.click();
		else
			this.update.click();

		Thread.sleep(1000);
	}

	private void enterSplitAmount_ios(Integer index, String sAmount) throws Exception{

		String[] s = sAmount.split("");
		int iCount;
		Helper h = new Helper();

		String cc = "**/XCUIElementTypeOther[`name BEGINSWITH 'Amount:'`]/**/XCUIElementTypeStaticText[`name CONTAINS '$'`]["+index+"]";
		Engine.getDriver().findElement(MobileBy.iOSClassChain(cc)).click();

		for (int i = 0; i < 6; i++) {
			if (h.getEngine().equals("android"))
				Engine.getDriver().findElement(By.id("assets/Quicken/App/Images/keypadbutton_delete@2x.png")).click();
			else
				Engine.getDriver().findElement(MobileBy.iOSNsPredicateString("name = 'delete'")).click();	
		}
		Thread.sleep(1000);

		for(iCount=0; iCount<s.length; iCount++) {

			if (s[iCount].equals(".")) {
				// ignore
			}
			else
				//Engine.getDriver().findElement(By.xpath("//XCUIElementTypeStaticText[@name='"+s[iCount]+"']")).click();	
				Engine.getDriver().findElement(MobileBy.iOSClassChain("**/XCUIElementTypeStaticText[`name=='"+s[iCount]+"'`]")).click();	
		}

		if (Verify.objExists(this.buttonDone))
			this.buttonDone.click();
		else
			this.update.click();

		Thread.sleep(1000);
	}


	public void selectCategoryOnDrawer (String category) throws Exception {

		Helper h = new Helper();

		if (h.getEngine().equals("android"))
			this.selectCategoryOnDrawer_android(category);
		else
			this.selectCategoryOnDrawer_ios(category);
	}

	private void selectCategoryOnDrawer_ios (String category) throws Exception{

		String sXpath = "**/XCUIElementTypeOther[`name='RadioButton "+category+"'`]/**/XCUIElementTypeOther[`name='RadioButton'`]";
		this.searchCategory(category);
		Engine.getDriver().findElement(MobileBy.iOSClassChain(sXpath)).click();
		Thread.sleep(500);
		//this.applyBtn.click();
	}

	private void selectCategoryOnDrawer_android (String category) throws Exception{

		String sXpath = "//android.view.ViewGroup[android.widget.TextView[@text='"+category+"']]/android.view.ViewGroup[@content-desc='RadioButton']";
		this.searchCategory(category);
		Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ category + "\").instance(0))"));
		Thread.sleep(1000);
		Engine.getDriver().findElement(By.xpath(sXpath)).click();
		Thread.sleep(500);
		//this.applyBtn.click();
	}

	public void addTransaction (TransactionRecord tr) throws Exception {

		// Allow Quicken to access this device's location?
		if (Verify.objExists(this.allowButton)) {
			this.allowButton.click();
			Thread.sleep(1000);
		}
		SoftAssert sa = new SoftAssert();
		Verify.waitForObject(this.buttonDone, 1);
		if (Verify.objExists(this.buttonDone) || Verify.objExists(this.addTransactionTxt)) {
			Commentary.log(LogStatus.INFO,"Add Transaction Screen got displayed.");
			Thread.sleep(2000);

			if (tr.getAmount() != null) 
				this.enterAmount(tr.getAmount());

			if (tr.getTransactionType() != null)
				this.enterTransactionType(tr.getTransactionType());

			if (tr.getDate() != null)
				this.enterDate(tr.getDate());

			if (tr.getAccount() != null)
				this.selectAccount(tr.getAccount());

			if (tr.getPayee() != null)
				this.selectPayee(tr.getPayee());

			if (tr.getCategory() != null)
				this.selectCategory(tr.getCategory());
			
			if (tr.getCategoryRecord() != null) {
				this.selectCategoryOnTheFly(tr.getCategoryRecord());
				
			}


			if (tr.getTags() != null)
				this.selectTags(tr.getTags());

			if (tr.getNote() != null)
				this.enterNotes(tr.getNote());

			if (Verify.objExists(this.buttonSave)) {
				this.buttonSave.click();
			}
			else {
				this.buttonSave.click(); 
			} 

			Thread.sleep(8000);	
		}

		else
			Commentary.log(sa, LogStatus.FAIL,"AddTransaction screen did not appear.");

		sa.assertAll();
	}

	public void editTransaction (TransactionRecord tr) throws Exception {

		// Allow Quicken to access this device's location?
		if (Verify.objExists(this.allowButton)) {
			this.allowButton.click();
			Thread.sleep(1000);
		}
		SoftAssert sa = new SoftAssert();
		Verify.waitForObject(this.viewTransactionTxt, 1);
		if (Verify.objExists(this.viewTransactionTxt)) {
			Commentary.log(LogStatus.INFO,"EditTransaction screen got displayed.");

			if (tr.getAmount() != null)
				this.enterAmount(tr.getAmount());

			if (tr.getTransactionType() != null)
				this.enterTransactionType(tr.getTransactionType());

			if (tr.getDate() != null)
				this.enterDate(tr.getDate());

			if (tr.getAccount() != null)
				this.selectAccount(tr.getAccount());

			if (tr.getPayee() != null)
				this.selectPayee(tr.getPayee());

			if (tr.getCategory() != null)
				this.selectCategory(tr.getCategory());
			
			if (tr.getCategoryRecord() != null) {
				this.selectCategoryOnTheFly(tr.getCategoryRecord());	
			}	


			if (tr.getTags() != null)
				this.selectTags(tr.getTags());

			if (tr.getNote() != null)
				this.enterNotes(tr.getNote());

			Verify.waitForObject(buttonSave1, 1);
			if (Verify.objExists(this.buttonSave1)) {
				this.buttonSave1.click();
			}
			//			else {
			//				this.buttonSave1.click(); 
			//			}

			Thread.sleep(8000);
		}	
		else
			Commentary.log(sa, LogStatus.FAIL,"Edit Transaction screen did not appear.");
	}


	public void enterAmount(String amount) throws Exception {

		Helper h = new Helper();

		if (h.getEngine().equals("android"))
			enterTransactionAmount_android(amount);
		else
			enterTransactionAmount_ios(amount);			
	}

	public void enterTransactionType (String txnType) throws Exception {

		if (txnType.equals("income")) {
			Verify.waitForObject(moneyIn, 2);
			this.moneyIn.click();
		}
		else {
			Verify.waitForObject(moneyOut, 2);
			this.moneyOut.click();
		}
	}

	public void enterTransactionAmount_android(String amount) throws Exception {

		String[] s = amount.split("");
		int iCount;

		if (! Verify.objExists(this.viewTransactionTxt)) {
			//this.amount.click();
		}	else {
			this.amount.click();
			Thread.sleep(4000);
			for (int i = 1; i < amount.length(); i++) {
				Engine.getDriver().findElement(By.xpath("//android.widget.ImageView[@content-desc='delete']")).click();
			}
			Thread.sleep(1000);
		}

		for(iCount=0; iCount<s.length; iCount++) {

			if (s[iCount].equals(".")) {
				// ignore
			}
			else
				Engine.getDriver().findElement(By.xpath("//*[@text='"+s[iCount]+"']")).click();	
		}

		if(Verify.objExists(this.buttonDone)) {
			this.buttonDone.click();
		}
		else {
			this.buttonUpdate.click();
		}
		Thread.sleep(1000);
	}

	public void enterTransactionAmount_ios(String amount) throws Exception {

		String[] s = amount.split("");
		int iCount;

		if (! Verify.objExists(this.viewTransactionTxt)) {
			//Thread.sleep(1000);
		} else {
			this.amount.click();
			Thread.sleep(4000);
			for (int i = 1; i < amount.length(); i++) {
				//Engine.getDriver().findElement(MobileBy.AccessibilityId("assets/Quicken/App/Images/keypadbutton_delete@2x.png")).click();
				//Engine.getDriver().findElement(MobileBy.iOSClassChain("**/XCUIElementTypeImage[`name=='assets/Quicken/App/Images/keypadbutton_delete@2x.png'`]")).click();
				Engine.getDriver().findElement(MobileBy.iOSClassChain("**/XCUIElementTypeOther[`name=='delete'`][2]")).click();
			}
			Thread.sleep(1000);
		}

		for(iCount=0; iCount<s.length; iCount++) {

			if (s[iCount].equals(".")) {
				// ignore
			}
			else
				Engine.getDriver().findElement(MobileBy.iOSClassChain("**/XCUIElementTypeStaticText[`name=='"+s[iCount]+"'`]")).click();	
		}

		if(Verify.objExists(this.buttonDone)) {
			this.buttonDone.click();
		}
		else {
			this.buttonUpdate.click();
		}
		Thread.sleep(1000);
	}


	public void enterDate(String mddyyyy) throws Exception {

		Helper h = new Helper();

		if (h.getEngine().equals("android"))
			enterDate_android(mddyyyy);
		else
			enterDate_IOS(mddyyyy);
	}

	// provide date in m/dd/yyyy format
	// Ex: 1/22/2019
	public void enterDate_android(String mddyyyy) throws Exception {

		String[] a = mddyyyy.split("/");

		String month = new DateFormatSymbols().getShortMonths()[Integer.parseInt(a[0])-1].toString();
		String date = a[1];
		String year = a[2];

		if (! Verify.objExists(this.datePicker)) {
			this.dateLabel.click();
			Thread.sleep(1000);
		}

		/////////////////////////////////////////
		try {

			this.enterMonth.click();
			Thread.sleep(1000);
			this.enterMonth.clear();
			this.enterMonth.sendKeys(month);
			Thread.sleep(1000);

			this.enterDate.click();
			this.enterDate.clear();
			this.enterDate.sendKeys(date);
			Thread.sleep(1000);

			this.enterYear.click();
			this.enterYear.clear();
			this.enterYear.sendKeys(year);
			Thread.sleep(1000);


			//			TouchAction touchAction = new TouchAction(Engine.getDriver());
			//			touchAction
			//			 .press(element(this.enterMonth))
			//	         .waitAction(waitOptions(ofSeconds(1)))
			//	         .release()
			//	         .perform();
			//			this.enterMonth.sendKeys(month);
			//			
			//			
			//			TouchAction touch2 = new TouchAction(Engine.getDriver());
			//			touch2
			//			 .press(element(this.enterDate))
			//	        .waitAction(waitOptions(ofSeconds(1)))
			//	        .release()
			//	        .perform();
			//			this.enterDate.sendKeys(date);
			//			
			//			
			//			
			//			TouchAction touch3 = new TouchAction(Engine.getDriver());
			//			/*
			//			touch3.longPress(this.enterYear,1000).release();
			//			Engine.getDriver().performTouchAction(touch3);
			//			Engine.getDriver().getKeyboard().pressKey(year);
			//			Thread.sleep(1000);
			//			*/
			//			touch3
			//			 .press(element(this.enterDate))
			//	       .waitAction(waitOptions(ofSeconds(1)))
			//	       .release()
			//	       .perform();
			//			this.enterYear.sendKeys(year);
			//			Thread.sleep(1000);

		} catch (Exception e) {
			e.printStackTrace();
		}

		/////////////////////////////////////////

		this.buttonOK.click();
		Thread.sleep(1000);	
		Engine.getDriver().getContext();

	}

	public void enterDate_IOS(String mddyyyy) throws Exception {

		String[] a = mddyyyy.split("/");

		Helper h = new Helper();

		String month = new DateFormatSymbols().getMonths()[Integer.parseInt(a[0])-1].toString();

		String date = a[1];
		String year = a[2];

		if (! Verify.objExists(this.datePicker)) {
			this.dateLabel.click();
			Thread.sleep(500);
		}

		int iCount;

		for (iCount=0; iCount < 3; iCount++)
			enterMonth.sendKeys(month);
		Thread.sleep(500);
		h.getContext();

		for (iCount=0; iCount < 3; iCount++)
			enterDate.sendKeys(date);
		Thread.sleep(500);

		for (iCount=0; iCount < 2; iCount++)
			enterYear.sendKeys(year);
		h.getContext();

		this.buttonOK.click();
		Thread.sleep(500);	

	}

	public void selectAccount (String acct) throws Exception {

		Helper h = new Helper();
		h.getContext();
		Thread.sleep(1000);

		this.account.click(); //This will click on Choose an Account.
		Thread.sleep(2500);

		if (h.getEngine().equals("android")) {
			String xpath = "//android.widget.TextView[@text='"+acct+"']";
			Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ acct + "\").instance(0))"));
			Thread.sleep(1000);
			if (! Verify.objExists((MobileElement)Engine.getDriver().findElement(By.xpath(xpath)))) {
				Commentary.log(LogStatus.FAIL,"Error****** Account ["+acct+"] not found on choose account drawer");
				throw new Exception("Error****** Account ["+acct+"] not found on choose account drawer");
			}

			MobileElement desiredAccount = (MobileElement)Engine.getDriver().findElement(By.xpath(xpath));
			Verify.waitForObject(desiredAccount, 2);
			desiredAccount.click();

			Thread.sleep(500);	
		}

		else {
			//String xpath = "//XCUIElementTypeOther[@name='"+acct+"']";
			//String xpath = "**/XCUIElementTypeOther[`name=='"+acct+"'`]";
			String xpath = "**/XCUIElementTypeStaticText[`name=='"+acct+"'`][1]";
			MobileElement me = (MobileElement)Engine.getDriver().findElement(MobileBy.iOSClassChain(xpath));
			//MobileElement me = (MobileElement)Engine.getDriver().findElement(By.name(acct));
			/*
			MobileElement element = (MobileElement) Engine.getDriver().findElement(By.name(acct));
			String elementID = element.getId();
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("element", elementID);
			scrollObject.put("toVisible", "not an empty string");
			Engine.getDriver().executeScript("mobile:scroll", scrollObject);
			Thread.sleep(1000);
			if (! Verify.objExists((MobileElement)Engine.getDriver().findElement(By.xpath(xpath)))) {
				Commentary.log(LogStatus.FAIL,"Error****** Account ["+acct+"] not found on choose account");
				throw new Exception("Error****** Account ["+acct+"] not found on choose account");
			}
			 */

			/*
			MobileElement element = (MobileElement) Engine.getDriver().findElement(By.xpath("//XCUIElementTypeOther[contains(@name,'Choose an Account')]//XCUIElementTypeScrollView"));
			String parentID = element.getId();
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("element", parentID);
			scrollObject.put("name", acct);
			Engine.getDriver().executeScript("mobile:scroll", scrollObject);*/

			if (! Verify.objExists(me)) {

				try {
					JavascriptExecutor js = (JavascriptExecutor) Engine.getDriver(); 
					HashMap<String, String> scrollObject = new HashMap(); 
					scrollObject.put("direction", "up"); 
					scrollObject.put("xpath", xpath); 
					js.executeScript("mobile: swipe", scrollObject);
					Thread.sleep(500);	
				}
				catch (Exception e) {
					Commentary.log(LogStatus.FAIL, e.getMessage());
				}

			}

			Verify.waitForObject(me, 3);
			me.click();
			Thread.sleep(500);

		}

		// selecting the account will close the choose account screen
		// verify if the account got selected or not
		//		if (Verify.objExists(this.closeChooseAccount)) {
		//			this.closeChooseAccount.click();
		//			Thread.sleep(500);
		//		}
		//Commentary.log(LogStatus.FAIL,"Error****** Transaction Detail > Choose Account screen > tapping on Account ["+acct+"] did not close Choose Account screen");	
	}

	/*
	public void selectPayee_android (String payees) throws Exception {

		String sXpath = "//android.widget.TextView[@text='"+payees+"']";

		this.payee.click();
		Thread.sleep(1000);
		System.out.println(Engine.getDriver().getContext());

		if (! Verify.objExists(this.closePayee))
			Commentary.log(LogStatus.FAIL,"Error****** Transaction Detail > tapping on payee, did not open Payee selection screen");

		Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ payees + "\").instance(0))"));

		Thread.sleep(1000);
		Engine.getDriver().findElement(By.xpath(sXpath)).click();
		Thread.sleep(1000);	
	}*/

	public void selectPayee(String payees) throws Exception{

		Helper h = new Helper();

		if (h.getEngine().equals("android"))
			this.selectPayee_android(payees);
		else
			this.selectPayee_ios(payees);
	}

	public void editPayee(String payees) throws Exception{

		Helper h = new Helper();

		if (h.getEngine().equals("android"))
			this.editPayee_android(payees);
		else
			this.editPayee_ios(payees);
	}

	public void selectCategory(String category) throws Exception {

		Helper h = new Helper();

		if (h.getEngine().equals("android"))
			this.selectCategory_android(category);
		else
			this.selectCategory_ios(category);	
	}

	public void selectCategory_android (String category) throws Exception {

		//String sXpath = "//android.widget.TextView[@text='"+category+"']";
		String sXpath = "//android.widget.TextView[@text='"+category+"']/../android.view.ViewGroup[@content-desc='RadioButton']";

		if (Verify.objExists_check(this.category)) {
			this.category.click();
		} 
		else if(Verify.objExists_check(this.subCategoryOf)) {
			Thread.sleep(1000);
			this.subCategoryOf.click();	
			Thread.sleep(1000);
		}
		else {
			this.categories.click();
		}


		//Verify.waitForObject(this.closeCategory, 1);
		Verify.waitForObject(this.closeCategorySubcategoryOfIcon, 1);


		if (! Verify.objExists(this.closeCategorySubcategoryOfIcon))
			Commentary.log(LogStatus.FAIL,"Error****** Transaction Detail > tapping on payee, did not open Payee selection screen");

		this.searchCategory(category);

		Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ category + "\").instance(0))"));

		Thread.sleep(1000);
		Engine.getDriver().findElement(By.xpath(sXpath)).click();
		Thread.sleep(1000);	

		//With New app versions(5.21.0(19429)), no need to click close button of the drawer.
		//		if (Verify.objExists(this.closeCategory)) {
		//			this.closeCategory.click();
		//			Thread.sleep(1000);
		//		}

		//System.out.println(Engine.getDriver().getContext());
	}

	public void selectCategory_ios (String category) throws Exception {

		//String sXpath = "//XCUIElementTypeOther[@name='"+category+"']";
		String sXpath = "**/XCUIElementTypeOther[`name='RadioButton "+category+"'`]/XCUIElementTypeOther[`name='RadioButton'`]";
		//String sXpath = "**/XCUIElementTypeOther[`name=='RadioButton "+category+"'`]";

		//		if (this.categories.isDisplayed()) {
		//			System.out.println("*******Categories display "+this.categories.isDisplayed());
		//			this.categories.click();
		//		} else {
		//			System.out.println("*******Category display "+this.category.isDisplayed());
		//
		//			this.category.click();
		//		}
		//Engine.getDriver().findElementById("assets/Quicken/App/Images/categoryIcon@2x.png").click();
		if (Verify.objExists_check(this.category)) {
			this.category.click();
		} 
		else if(Verify.objExists_check(this.subCategoryOf)) {
			Thread.sleep(1000);
			this.subCategoryOf.click();	
			Thread.sleep(1000);
		}
		else {
			this.categories.click();
		}

		//Verify.waitForObject(this.closeCategory, 1);
		Verify.waitForObject(this.closeCategorySubcategoryOfIcon, 1);


		this.searchCategory(category);
		Engine.getDriver().findElement(MobileBy.iOSClassChain(sXpath)).click();
		Thread.sleep(1000);	

		//With New app versions(5.21.0(19429)), no need to click close button of the drawer.
		//		if (Verify.objExists(this.closeCategory)) {
		//			this.closeCategory.click();
		//			Thread.sleep(1000);
		//		}
	}

	public void searchCategory (String category) throws Exception{

		//		if (! Verify.objExists(this.buttonSave)) {
		//			this.category.click();
		//			Thread.sleep(500);
		//		}

		this.searchCategoryTextField.click();
		this.searchCategoryTextField.sendKeys(category);
		
		Thread.sleep(1000);

		Helper h = new Helper();
		
		if(h.getEngine().equals("ios")) {
		
		h.hideKeyBoard();
		}
		else {
			Commentary.log(LogStatus.INFO, "hide keyboard not required");
		}


	}

	public void selectTags (String sTag) throws Exception {

		Helper h = new Helper();

		if (h.getEngine().equals("android"))
			this.selectTags_android(sTag);
		else
			this.selectTags_ios(sTag);
	}

	public void selectTags_android (String sTag) throws Exception {

		String sXpath="//android.widget.TextView[@content-desc='Tags']";
		Engine.getDriver().findElement(By.xpath(sXpath)).click();
		Verify.waitForObject(this.searchTags, 1);
		this.searchTags.sendKeys(sTag);
		Helper h = new Helper();
		h.hideKeyBoard();
		Thread.sleep(1000);

		String createTag_xpath="//android.widget.TextView[@content-desc='create item']";
		String cc = "//android.widget.TextView[@text='"+sTag+"']";

		if (Verify.objExists(createTag)) {
			Verify.objExists((MobileElement)Engine.getDriver().findElement(By.xpath(createTag_xpath)));
			Engine.getDriver().findElement(By.xpath(createTag_xpath)).click();
			Commentary.log(LogStatus.INFO,"Creating new Tag.. "+sTag);
			
			Engine.getDriver().findElement(By.xpath(cc)).click();
			Thread.sleep(500);
			Commentary.log(LogStatus.INFO,"Selected Tag which is just now created.. "+sTag);
		}
		else {
			Engine.getDriver().findElement(By.xpath(cc)).click();
			Thread.sleep(500);
			Commentary.log(LogStatus.INFO,"Selected Tag which is already present.. "+sTag);
		}
		Thread.sleep(1000);

		this.tagsApplyButton.click();
		Thread.sleep(1000);	
	}

	public void selectTags_ios (String sTag) throws Exception {

		Engine.getDriver().findElement((MobileBy.iOSClassChain("**/XCUIElementTypeStaticText[`name='Tags'`]"))).click();
		Verify.waitForObject(this.searchTags, 1);
		this.searchTags.sendKeys(sTag);
		Helper h = new Helper();
		h.hideKeyBoard();
		Thread.sleep(1000);

		String createTag_xpath="**/XCUIElementTypeOther[`name='create item'`]";
		String cc = "**/XCUIElementTypeStaticText[`name='"+sTag+"'`]";

		if (Verify.objExists(createTag)) {
			Verify.objExists((MobileElement)Engine.getDriver().findElement(MobileBy.iOSClassChain(createTag_xpath)));
			Engine.getDriver().findElement(MobileBy.iOSClassChain(createTag_xpath)).click();
			Commentary.log(LogStatus.INFO,"Creating Tag... "+sTag);	
			
			Engine.getDriver().findElement(MobileBy.iOSClassChain(cc)).click();
			Thread.sleep(500);
			Commentary.log(LogStatus.INFO,"Selected Tag which is just now created.. "+sTag);
			h.hideKeyBoard();
			Thread.sleep(1000);
		}
		else {
			Engine.getDriver().findElement(MobileBy.iOSClassChain(cc)).click();
			Thread.sleep(500);
			Commentary.log(LogStatus.INFO,"Selected Tag which is already present.. "+sTag);
		}
		Thread.sleep(1000);

		this.tagsApplyButton.click();
		Thread.sleep(1000);	
	}

	public void enterNotes (String sNote) throws Exception {

		Helper h = new Helper();

		if (h.getEngine().equals("android"))
			this.enterNotes_android(sNote);
		else
			this.enterNotes_ios(sNote);
	}

	public void enterNotes_android (String sNote) throws Exception {

		Verify.waitForObject(this.note, 1);
		this.note.click();
		this.note.clear();
		Thread.sleep(1000);
		this.note.clear();
		this.note.sendKeys(sNote);

		this.buttonDone_OSKeyBoard.click();
		Thread.sleep(1000);
	}

	public void enterNotes_ios (String sNote) throws Exception {

		Verify.waitForObject(this.note, 1);
		this.note.click();
		this.note.clear();
		Thread.sleep(1000);
		this.note.clear();
		this.note.sendKeys(sNote);

		this.buttonDone_OSKeyBoard.click();
		Thread.sleep(1000);
	}

	public void enterCheckNumber (String sCheck) throws Exception {

		this.addCheckNumber.click();
		this.addCheckNumber.sendKeys(sCheck);

		Helper h = new Helper();
		h.hideKeyBoard();

	}

	public void createPayee (String sPayee) throws Exception {

		String sTemp = "\""+sPayee+"\"";
		String xpath;

		this.searchPayee.sendKeys(sPayee);
		Helper h = new Helper();
		h.hideKeyBoard();

		if (h.getEngine().equals("android")) {
			xpath="//android.widget.TextView[@text='Create "+sTemp+"']";
			if (! Verify.objExists((MobileElement)Engine.getDriver().findElement(By.xpath(xpath)))) {
				Commentary.log(LogStatus.FAIL,"Error****** Failed to create Payee "+sPayee);

			}
			else
			{
				Commentary.log(LogStatus.INFO,"Creating Payee .. "+sPayee);
				Engine.getDriver().findElement(By.xpath(xpath)).click();
			}

		}
		else {

			xpath="//XCUIElementTypeOther[@name='Create "+sTemp+"']";
			if (! Verify.objExists((MobileElement)Engine.getDriver().findElement(By.xpath(xpath)))) {
				Commentary.log(LogStatus.FAIL,"Error****** Failed to create Payee "+sPayee);
			}
			else
			{
				Commentary.log(LogStatus.INFO,"Creating Payee .. "+sPayee);
				Engine.getDriver().findElement(By.xpath(xpath)).click();
			}

		}

	}

	public void selectPayee_android (String payees) throws Exception {

		String sXpath = "//android.widget.TextView[@text='"+payees+"']";
		String createPayee_xpath="//android.widget.TextView[@content-desc='create payee']";
		String yelp_Payee_Xpath = "(//android.widget.ScrollView//android.view.ViewGroup/android.view.ViewGroup)[1]//following-sibling::android.view.ViewGroup//android.widget.TextView[@text='"+payees+"']";
		String allPayees_Xpath = "//android.widget.ScrollView/android.view.ViewGroup//android.widget.TextView[@text=\"All Payees\"]/../../../android.view.ViewGroup//android.widget.TextView[@text='"+payees+"']";

		this.payee.click();
		Thread.sleep(1000);

		if (! Verify.objExists(this.closePayee))
			Commentary.log(LogStatus.FAIL,"Error****** Transaction Detail > tapping on payee, did not opened Payee selection drawer.");

		this.searchPayee.sendKeys(payees);
		Helper h = new Helper();
		h.hideKeyBoard();
		Thread.sleep(3000);

		if (Verify.objExists(createPayee)) {
			try {
				Verify.objExists((MobileElement)Engine.getDriver().findElement(By.xpath(createPayee_xpath)));
				Engine.getDriver().findElement(By.xpath(createPayee_xpath)).click();
				Commentary.log(LogStatus.INFO,"Creating Payee.. "+payees);

			}
			catch (NoSuchElementException e) {
				Engine.getDriver().findElement(By.xpath(sXpath)).click();
				Commentary.log(LogStatus.INFO,"Selecting Payee.. "+payees);
			}
		}

		else {
			//String cc = "**/XCUIElementTypeOther[`name=='"+payees+"'`]";
			if(Verify.objExists(yelpNearByLabel)) {
				Engine.getDriver().findElement(By.xpath(yelp_Payee_Xpath)).click();
				Thread.sleep(500);
				Commentary.log(LogStatus.INFO,"Selected Yelp Payee.. "+payees);
			}
			else {
				Engine.getDriver().findElement(By.xpath(allPayees_Xpath)).click();
				Thread.sleep(1500);
				Commentary.log(LogStatus.INFO,"Selected Payee from All Payees List.. "+payees);
			}
		}

		Thread.sleep(1000);	
		/*
			Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ payees + "\").instance(0))"));
			Thread.sleep(1000);
			Engine.getDriver().findElement(By.xpath(sXpath)).click();
			Thread.sleep(1000);	*/	
	}

	public void selectPayee_ios (String payees) throws Exception {

		String sXpath = "**/XCUIElementTypeScrollView/XCUIElementTypeOther[`name CONTAINS '"+payees+"'`]";
		//String sTemp = "\""+payees+"\"";
		String createPayee_xpath="**/XCUIElementTypeOther[`name='create payee'`]";

		this.payee.click();
		Thread.sleep(1000);

		if (! Verify.objExists(this.closePayee))
			Commentary.log(LogStatus.FAIL,"Error****** Transaction Detail > tapping on payee, did not opened Payee selection drawer.");

		// search payee
		this.searchPayee.sendKeys(payees);
		Helper h = new Helper();
		h.hideKeyBoard();
		Thread.sleep(1000);

		if (Verify.objExists(createPayee)) {
			try {

				Verify.objExists((MobileElement)Engine.getDriver().findElement(MobileBy.iOSClassChain(createPayee_xpath)));
				Engine.getDriver().findElement(MobileBy.iOSClassChain(createPayee_xpath)).click();
				Commentary.log(LogStatus.INFO,"Creating Payee .. "+payees);

			}
			catch (NoSuchElementException e){

				Engine.getDriver().findElement(MobileBy.iOSClassChain(sXpath)).click();
				Commentary.log(LogStatus.INFO,"Selecting Payee .. "+payees);		
			}
		}

		// **/XCUIElementTypeOther[$name='Near by'$]/XCUIElementTypeOther[`name=='"+payees+"'`]
		// **/XCUIElementTypeOther[$name='All Payees'$]/XCUIElementTypeOther[`name=='"+payees+"'`]
		//			else {
		//				String cc = "**/XCUIElementTypeOther[`name=='"+payees+"'`]";
		//				Engine.getDriver().findElement(MobileBy.iOSClassChain(cc)).click();
		//				Thread.sleep(500);
		//				Commentary.log(LogStatus.INFO,"Selected Yelp Payee.. "+payees);
		//			}

		else {
			if(Verify.objExists(yelpNearByLabel)) {
				String cc = "**/XCUIElementTypeOther[$name='Near by'$]/XCUIElementTypeOther[`name=='RadioButton "+payees+"'`]";
				Engine.getDriver().findElement(MobileBy.iOSClassChain(cc)).click();
				Thread.sleep(500);
				Commentary.log(LogStatus.INFO,"Selected Yelp Payee.. "+payees);
			}
			else {
				//String cc = "**/XCUIElementTypeOther[$name='All Payees'$]/XCUIElementTypeOther[`name=='RadioButton "+payees+"'`]";
				String cc = "**/XCUIElementTypeStaticText[`name='"+payees+"'`]";
				Engine.getDriver().findElement(MobileBy.iOSClassChain(cc)).click();
				Thread.sleep(1500);
				Commentary.log(LogStatus.INFO,"Selected Payee from All Payees List.. "+payees);
			}
		}

		Thread.sleep(1000);	
	}

	public void editPayee_android(String payees) throws Exception {

		this.payee.click();
		Thread.sleep(1000);

		if (! Verify.objExists(this.closeEditPayee))
			Commentary.log(LogStatus.FAIL,"Error****** Transaction Detail > After tapping on payee, did not opened Edit Payee drawer.");

		this.editPayee.clear();
		this.editPayee.sendKeys(payees);
		this.updateButton.click();
		Thread.sleep(1000);
	}

	public void editPayee_ios(String payees) throws Exception {

		this.payee.click();
		Thread.sleep(1000);

		if (! Verify.objExists(this.closeEditPayee))
			Commentary.log(LogStatus.FAIL,"Error****** Transaction Detail > After tapping on payee, did not opened Edit Payee drawer.");

		this.editPayee.clear();
		this.editPayee.sendKeys(payees);
		this.updateButton.click();
		Thread.sleep(1000);
	}

	public void selectStatus(String status) throws Exception {
		Helper h = new Helper();
		OverviewPage op = new OverviewPage();
		if (h.getEngine().equalsIgnoreCase("ios")) {
			String cc = "**/XCUIElementTypeStaticText[`name=='"+status+"'`]";

			this.status.click();
			Thread.sleep(2500);
			MobileElement me = (MobileElement) Engine.getDriver().findElement(MobileBy.iOSClassChain(cc));
			me.click();
			Thread.sleep(1000);
			this.buttonSave1.click();
			Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		} else {
			String sXpath = "//*[@text='"+status+"']";
			this.status.click();
			Thread.sleep(2500);
			MobileElement me = (MobileElement) Engine.getDriver().findElement(MobileBy.xpath(sXpath));
			me.click();
			Thread.sleep(1000);
			this.buttonSave1.click();
			Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		}

	}

	public void verifyTransactionDetails(TransactionRecord tr) throws Exception {

		if (Verify.objExists(this.allowButton)) {
			this.allowButton.click();
			Thread.sleep(1000);
		}

		String sTemp;

		if (tr.getAmount() != null) {

			if (tr.getTransactionType().equals("expense"))
				sTemp= "-$"+tr.getAmount();
			else
				sTemp = "$"+tr.getAmount();	
		}

		this.enterAmount(tr.getAmount());

		if (tr.getTransactionType() != null)
			this.enterTransactionType(tr.getTransactionType());

		if (tr.getDate() != null)
			this.enterDate(tr.getDate());

		if (tr.getAccount() != null)
			this.selectAccount(tr.getAccount());

		if (tr.getPayee() != null)
			this.selectPayee(tr.getPayee());

		if (tr.getCategory() != null)
			this.selectCategory(tr.getCategory());

		if (tr.getTags() != null)
			this.selectTags(tr.getTags());

		if (tr.getNote() != null)
			this.enterNotes(tr.getNote());

		this.buttonSave.click();

		Thread.sleep(3000);

	}

	public String getTransactionAmount () {

		Helper h = new Helper();


		if (h.getEngine().equals("android")) {

			//		String xpath = "//android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[@index='2']//android.widget.TextView";
			//		return Engine.getDriver().findElement(By.xpath(xpath)).getText();
			return this.amount.getText();

		}
		else
			return this.amount.getText();

	}

	public String getTransactionDate() {

		return this.selectedDate.getText();

	}

	public String getTransactionAccount() {

		return this.selectedAccount.getText();
	}

	public String getTransactionPayee() {

		return this.selectedpayee.getText();
	}

	public String getTransactionCategory() {

		return this.selectedCategory.getText();
	}

	public String getTransactionCheckNumber() {
		return this.enteredCheckNumber.getText();
	}

	public String getTransactionStatus() {
		return this.transactionStatus.getText();
	}

	public String getTransactionReviewStatus() {
		return this.downloadedTransactionStatus.getText();
	}

	public String getTransactionTags() throws Exception {

		Helper h = new Helper();

		if (h.getEngine().equals("android")) 
			Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ "Tags" + "\").instance(0))"));
		else {

			// scroll into tags
			String sXpath="//XCUIElementTypeScrollView";
			MobileElement me = (MobileElement) Engine.getDriver().findElement(By.xpath(sXpath));
			String me_id = me.getId();
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("element", me_id);
			scrollObject.put("predicateString", "name == 'Tags'");
			Engine.getDriver().executeScript("mobile:scroll", scrollObject);  // scroll to the target element
			Thread.sleep(1000);	

		}

		return this.selectedTags.getText();
	}

	public String getTransactionNotes() throws Exception {

		Helper h = new Helper();	

		if (h.getEngine().equals("android")) 
			Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ "Delete Transaction" + "\").instance(0))"));
		else {
			// scroll into notes
			String sXpath="//XCUIElementTypeScrollView";
			MobileElement me = (MobileElement) Engine.getDriver().findElement(By.xpath(sXpath));
			String me_id = me.getId();
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("element", me_id);
			scrollObject.put("direction", "down");
			Engine.getDriver().executeScript("mobile:scroll", scrollObject);
			//scrollObject.put("predicateString", "name == 'Delete Transaction'");
			//Engine.getDriver().executeScript("mobile:scroll", scrollObject);  // scroll to the target element
			Thread.sleep(3000);	
		}

		return this.enteredNote.getText();

	}

	public boolean VerifyTransactionAmount (String amount, String transactionType) {

		String sTemp;

		if (transactionType.equals("expense"))
			amount= "-$"+amount;
		else
			amount = "$"+amount;

		sTemp = this.getTransactionAmount();

		if (sTemp.equals(amount)) {
			Commentary.log(LogStatus.INFO, "Amount verified successfully ["+amount+"]");
			return true;
		}

		Commentary.log(LogStatus.INFO, "Amount verification failed. Expected ["+amount+"], Actual ["+sTemp+"]");
		return false;
	}

	public boolean VerifyTransactionDate (String mddyyyy) {

		String sExpected, sActual;
		String[] a = mddyyyy.split("/");

		String sMonth = new DateFormatSymbols().getShortMonths()[Integer.parseInt(a[0])-1].toString();
		String sDate = a[1];
		String sYear = a[2];

		sYear = sYear.substring(sYear.length()-2);
		sExpected = sMonth+"/"+sDate+"/"+sYear;

		sActual = this.getTransactionDate();

		if (sActual.equals(sExpected)) {
			Commentary.log(LogStatus.INFO, "Date verified successfully ["+sExpected+"]");
			return true;
		}

		Commentary.log(LogStatus.INFO, "Date verification failed. Expected ["+sExpected+"], Actual ["+sActual+"]");

		return false;
	}

	public boolean VerifyTransactionAccount (String acct) {

		String sActual;

		sActual = this.getTransactionAccount();

		if (sActual.equals(acct)) {
			Commentary.log(LogStatus.INFO, "Account verified successfully ["+acct+"]");
			return true;
		}

		Commentary.log(LogStatus.INFO, "Account verification failed. Expected ["+acct+"], Actual ["+sActual+"]");	

		return false;
	}

	public boolean VerifyTransactionPayee (String payee) {
		SoftAssert sa = new SoftAssert();

		String sActual;
		sActual = this.getTransactionPayee();

		if (sActual.equals(payee)) {
			Commentary.log(LogStatus.INFO, "Payee verified successfully ["+payee+"]");
			return true;
		}

		Commentary.log(sa, LogStatus.FAIL, "FAIL: Payee verification failed. Expected ["+payee+"], Actual ["+sActual+"]");

		return false;
	}

	public boolean VerifyTransactionCheckNumber (String checkNumber) {

		String sActual;

		sActual = this.getTransactionCheckNumber();

		if (sActual.equals(checkNumber)) {
			Commentary.log(LogStatus.INFO, "checkNumber verified successfully ["+checkNumber+"]");
			return true;
		}

		Commentary.log(LogStatus.INFO, "checkNumber verification failed. Expected ["+checkNumber+"], Actual ["+sActual+"]");

		return false;
	}

	public boolean VerifyTransactionCategory (String sCategory) {
		SoftAssert sa = new SoftAssert();

		String sActual;
		sActual = this.getTransactionCategory();

		if (sActual.equals(sCategory)) {
			Commentary.log(LogStatus.INFO, "Category verified successfully ["+sCategory+"]");
			return true;
		}

		Commentary.log(sa, LogStatus.FAIL, "Category verification failed. Expected ["+sCategory+"], Actual ["+sActual+"]");

		return false;
	}

	public boolean VerifyTransactionTags (String[] tags) throws Exception {

		String sActual, sExpected="";
		Integer iCount;

		for (iCount=0; iCount <= tags.length; iCount++) {
			sExpected =sExpected+tags[iCount];
			if (iCount != tags.length-1)
				sExpected = sExpected+", ";

		}

		sActual = this.getTransactionTags();

		if (sActual.equals(sExpected)) {
			Commentary.log(LogStatus.INFO, "checkNumber verified successfully ["+sExpected+"]");
			return true;
		}

		Commentary.log(LogStatus.INFO, "checkNumber verification failed. Expected ["+sExpected+"], Actual ["+sActual+"]");

		return false;
	}

	public boolean VerifyTransactionNotes (String sNotes) throws Exception {

		String sActual;

		sActual = this.getTransactionNotes();

		if (sActual.equals(sNotes)) {
			Commentary.log(LogStatus.INFO, "checkNumber verified successfully ["+sNotes+"]");
			return true;
		}

		Commentary.log(LogStatus.INFO, "checkNumber verification failed. Expected ["+sNotes+"], Actual ["+sActual+"]");

		return false;
	}

	public void deleteTransation() throws Exception {
		AllAccountsPage aa = new AllAccountsPage();

		List<MobileElement> li = aa.getAllSearchTransactions ();
		Commentary.log(LogStatus.INFO, "No of Transactions appeared in the search .."+li.size());

		if (li.isEmpty())
			Commentary.log(LogStatus.ERROR, "No Transactions available on Transaction list screen");
		else
			li.get(0).click();

		Thread.sleep(1000);
		if (Verify.objExists(this.allowButton)) {
			this.allowButton.click();
			Thread.sleep(1000);
		}
		OverviewPage op = new OverviewPage();
		op.scroll_down();

		deleteTransaction.click();
		deleteTransactionAlertButton.click();
	} 
	
	
	public void scroll_down() throws Exception {
		Helper h = new Helper();
		
		if (h.getEngine().equalsIgnoreCase("android")) {
			/*
			Dimension size = Engine.getDriver().manage().window().getSize();
			int y_start = (int) (size.width * 1.20);
			int y_end = (int) (size.width * 0.03);
			int x = 380;
			Engine.getDriver().swipe(x, y_start, x, y_end, 3000);
			*/
			verticalScrollDownAndroid();
			
		} else {
			JavascriptExecutor js1 = (JavascriptExecutor) Engine.getDriver() ;
		    HashMap scrollObject = new HashMap();
		    scrollObject.put("direction", "down");
		    js1.executeScript("mobile: scroll", scrollObject);
		}
		
	}
	
    public void verticalScrollDownAndroid() throws Exception {
        Dimension size = Engine.getDriver().manage().window().getSize();
        int y_start = (int) (size.width * 1.20);
		int y_end = (int) (size.width * 0.03);
		int x = 380;
		
		TouchAction touchAction = new TouchAction(Engine.getDriver());
 
		touchAction
                .press(point(x, y_start))
                .waitAction(waitOptions(ofMillis(2000)))
                .moveTo(point(x, y_end))
                .release().perform();
    }
    
    public void selectCategoryOnTheFly(CategoryRecord categoryRecord) throws Exception {
    	
  	  
  	  Helper h = new Helper();
  	 
  	  
  	
  	if (Verify.objExists_check(this.category)) {
  		this.category.click();
  	} else {
  		this.categories.click();
  	}

  	Verify.waitForObject(this.closeCategory, 1);

  	this.searchCategory(categoryRecord.getCategoryName());
  	
  	
  	Thread.sleep(2000);
  	Verify.waitForObject(this.createCategory, 1);
  	this.createCategory.click();
  	Thread.sleep(1000);
  	
  	if (categoryRecord.getUpdate()) {
  		// Verify if user can change values on the category creation alert.
  		
  		if(categoryRecord.getUpdateCategoryName()) {
  			String categoryName = h.getCurrentTime();
  			categoryRecord.setCategoryName(categoryName);
  		}
  		
  		if(categoryRecord.getUpdateCategoryType()) {
  		//String type = categoryRecord.getCategoryType().equalsIgnoreCase("Income") ? "Expense" : "Income";
  			String type = "Income";	
  			if (categoryRecord.getCategoryType().equalsIgnoreCase("Income")) {
  				type = "Expense";
  			}

  			
			categoryRecord.setCategoryType(type);
		}
		
		
		this.updateCategoryOnTheFlyCreation(categoryRecord);
	}
		
		if(Verify.objExists(createButton))
			Thread.sleep(1000);
		createButton.click();
		
		
		this.searchCategoryTextField1.click();
		Thread.sleep(1000);
		this.searchCategoryTextField1.clear();
		
		
		Thread.sleep(2000);
		this.searchCategory(categoryRecord.getCategoryName());
		
		
		if(h.getEngine().equals("android")) {
			
			String sCat = categoryRecord.getCategoryName();
			Thread.sleep(2000);

			String ss = "//android.widget.TextView[@text='"+sCat+"']";
			
			//String ss = "//android.view.ViewGroup[@content-desc='RadioButton']/android.widget.TextView[@name = '"+sCat+"']";
			
			Engine.getDriver().findElementByXPath(ss).click();
			Thread.sleep(1000);
			
			
		}
		
		else {
			
			String sCat = categoryRecord.getCategoryName();
			Thread.sleep(2000);
		
		String cc = "**/XCUIElementTypeStaticText[`name CONTAINS '"+sCat+"'`]";
		
		Engine.getDriver().findElement(MobileBy.iOSClassChain(cc)).click();
		}
			
	// needs to revisit cleaning whenever category managementfeature is implemented for the execution
		
}
    public void updateCategoryOnTheFlyCreation(CategoryRecord categoryRecord) throws Exception {
 	   
    	
  	  if (categoryRecord.getUpdateCategoryName()) {
  		  this.categoryNameTxtField.click();
  		  this.categoryNameTxtField1.clear();
  		  Thread.sleep(1000);
  		  Commentary.log(LogStatus.INFO, "Updating Category name");
  		  this.categoryNameTxtField1.sendKeys(categoryRecord.getCategoryName());
  		  
  			//Engine.getDriver().hideKeyboard();	
  			Thread.sleep(2000);
  	  }
  	 
  	  
  	  if (categoryRecord.getUpdateCategoryType()) {
  		  this.createCategoryType.click();
  		  
  	
  		  if(categoryRecord.getCategoryType().equalsIgnoreCase("Income")) {
  			  
  			  // expense type radio button will be selected
  			  Thread.sleep(2000);
  			  this.expenseTypeOption.click();
  			  
  		  }
  		  else {
  			// income type radio button will be selected
  			  Thread.sleep(2000);
  			  this.incomeTypeOption.click();
  		  }
  		  
  		  this.applyButton.click();
  		  Thread.sleep(1000);
  			  
  	  }
  	  // && !categoryRecord.getSubCategoryOf().isEmpty()
  	  
  	  if(categoryRecord.getUpdateSubcategoryOf()) {
  		  
  		  Helper h = new Helper();
  		  
  		  if(h.getEngine().equals("ios")) {
  		
  			this.subCategoryOf.click();
  			Thread.sleep(1000);
  			//this.searchCategory(categoryRecord.getSubCategoryOf());
  			this.selectCategory(categoryRecord.getSubCategoryOf());
  		  }
  		  else {
  			  this.selectCategory(categoryRecord.getSubCategoryOf());
  		  }
  		
  		
  		}
  	  
    }
	//	return (MobileElement) Engine.getDriver().findElementByXPath(xpath_Android);

 	
}

	

