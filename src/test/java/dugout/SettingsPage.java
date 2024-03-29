package dugout;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;

//import org.eclipse.jetty.util.annotation.ManagedObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.PointerInput.MouseButton;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import referee.Commentary;
import referee.Verify;
import support.Engine;
import support.Helper;

public class SettingsPage {

	public SettingsPage () {
		try {
			PageFactory.initElements(new AppiumFieldDecorator(Engine.getDriver()),this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == 'Passcode'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Passcode']")
	public WebElement passcode;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == 'Help & Legal'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Help & Legal']")
	public WebElement helpAndLegal;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == 'Accounts'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Accounts']")
	public WebElement accounts;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == 'Manage Alerts'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Manage Alerts']")
	public WebElement manageAlerts;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name='logOutButton'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Sign Out']")
	public WebElement logout;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name='Sign Out'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Sign Out']")
	public WebElement signOutConfirmationBtn;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name='Cancel'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Cancel']")
	public WebElement cancelBtn;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='closeButton'`]")
	@AndroidFindBy(xpath="//*[@content-desc='backButton']//*[@class='android.widget.ImageView']")
	public WebElement close;

	//Hamburger menu options
	@iOSXCUITFindBy(iOSNsPredicate= "name = 'closeButton'")
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='closeButton']")
	public WebElement closeButton;
	
	@iOSXCUITFindBy(iOSClassChain= "**/XCUIElementTypeOther[`name contains 'logOutButton'`]/XCUIElementTypeStaticText")
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='logOutButton']/../android.widget.TextView[contains(@text,'(')]")
	public WebElement versionNumber;

	@iOSXCUITFindBy(iOSNsPredicate= "name = 'logOutButton'")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Logout']")
	public WebElement logoutButton;

//	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Cloud Account name Menu'`]")
//	@AndroidFindBy(xpath="//android.widget.TextView[@text='Cloud Account name']")
//	public WebElement cloudAccountNameOption;
	
//	@iOSXCUITFindBy(id = "dataSetArrow")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Expand Icon'`][-1]")
//	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='dataSetArrow']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Help']/../following-sibling::android.view.ViewGroup[1]")
	public WebElement datasetDDButton;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'doneButton'`]")
	@AndroidFindBy(xpath="//*[@text='Done']")
	public WebElement btnDone;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name ='Dashboard Menu'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Dashboard']")
	public WebElement dashboardOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Accounts Menu'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Accounts']")
	public WebElement accountTxt;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'All Transactions Menu'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='All Transactions']")
	public WebElement allTransactionsOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Bills Menu'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Bills']")
	public WebElement billsOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains 'Budgets'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Budgets']")
	public WebElement budgetsOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Investments Menu'`]")
	@AndroidFindBy(accessibility = "Investments Menu")
	public WebElement investingOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Reports'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Reports']")
	public WebElement reportsOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Monthly Summary'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Monthly Summary']")
	public WebElement monthlySummaryOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Net Income'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Net Income']")
	public WebElement netIncomeOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Net Worth'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Net Worth']")
	public WebElement netWorthOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Spending by Category'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Spending by Category']")
	public WebElement spendingByCategoryOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Spending Over Time'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Spending Over Time']")
	public WebElement spendingOverTimeOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name='Go back'`]")
	@AndroidFindBy(xpath="//android.widget.Button[@content-desc='Go back']")
	public WebElement backButtonOnHeaders;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Profile Menu'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Profile']")
	public WebElement profileOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Profile'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Profile']")
	public WebElement ProfileHeaderTxt;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Go back Profile'`][-1]/**/XCUIElementTypeButton[`name='Go back'`]")
	@AndroidFindBy(xpath="//android.view.View[@text='Profile']/../android.widget.Button[@content-desc='Go back']")
	public WebElement backButtonOnProfileHeader;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains 'Your name:'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@content-desc,'Your name:')]")
	public WebElement accountNameUnderProfileOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains 'Your email:'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@content-desc,'Your email:')]")
	public WebElement accountIDUnderProfileOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains 'Subscription Expires on :'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@content-desc,'Subscription Expires on :')]")
	public WebElement subscriptionDetailsUnderProfileOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Passcode Menu'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Passcode']")
	public WebElement PasscodeTxt;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Passcode'`]")
	@AndroidFindBy(xpath="//android.view.View[@text='Passcode']")
	public WebElement PasscodeHeaderTxt;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name = 'Profile, back'`]")
	@AndroidFindBy(xpath="//android.widget.Button[@content-desc='Profile, back']")
	public WebElement backButtonOnPasscodeHeader;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Use Device Passcode'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Use Quicken Passcode']")
	public WebElement useDevicePasscodeTxt;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Legal'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Legal']")
	public WebElement legalTxt;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Acknowledgements'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Acknowledgements']")
	public WebElement acknowledgementsOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'License Agreement'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='License Agreement']")
	public WebElement licenceAgreementOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Privacy'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Privacy']")
	public WebElement privacyOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Help & Legal'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Help & Legal']")
	public WebElement HelpLegalHeaderTxt;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Settings Menu'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Settings']")
	public WebElement settingsOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Help'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Help']")
	public WebElement helpOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Chat with support'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Chat with support']")
	public WebElement chatWithsupportOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'FAQ'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='FAQ']")
	public WebElement FAQOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Report an issue'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Report an issue']")
	public WebElement reportAnIssueOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Visit support site'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Visit support site']")
	public WebElement visitSupportSiteOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Settings'`]")
	@AndroidFindBy(xpath="//android.view.View[@text='Settings']")
	public WebElement settingsHeaderText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name='Go back'`]")
	@AndroidFindBy(xpath="//android.view.View[@text='Settings']/../android.view.ViewGroup[@content-desc='backArrow']")
	public WebElement backButtonOnSettingsHeader;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Display Yelp Recommendations'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Display Yelp Recommendations']")
	public WebElement displayYelpRecommendationsText;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Display Yelp Recommendations'`]/XCUIElementTypeStaticText[`name = \"Display Yelp's recommendations of nearby payees when you add payees to your transactions\"`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Display Yelp Recommendations']/following::android.widget.TextView[@text=\"Display Yelp's recommendations of nearby payees when you add payees to your transactions\"]")
	public WebElement displayYelpDescription;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Display Yelp Recommendations'`]/XCUIElementTypeSwitch")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Display Yelp Recommendations']/following::android.widget.Switch")
	public WebElement switchDisplayYelp;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Show long category names'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Show long category names']")
	public WebElement showLongCategoryNamesText;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Show long category names'`]/XCUIElementTypeStaticText[`name = 'Display long form of categories in transaction and details'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Show long category names']/following::android.widget.TextView[1]")
	public WebElement showLongCategoryNamesDescription;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Show long category names'`]/XCUIElementTypeSwitch")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Show long category names']/following::android.widget.Switch")
	public WebElement switchShowLongCategoryNames;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Display Favorite Payees'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Display Favorite Payees']")
	public WebElement displayFavoritePayeesText;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Display Favorite Payees'`]/XCUIElementTypeStaticText[`name = 'Displays favorite payees when you are adding payees for your transactions'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Display Favorite Payees']/following::android.widget.TextView[1]")
	public WebElement displayFavoritePayeesDescription;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Display Favorite Payees'`]/XCUIElementTypeSwitch")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Display Favorite Payees']/following::android.widget.Switch")
	public WebElement switchDisplayFavoritePayees;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Display Real-Time Quotes'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Display Real-Time Quotes']")
	public WebElement displayRealTimeQuotesText;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Display Real-Time Quotes'`]/XCUIElementTypeStaticText[`name = 'Displays real-time quotes for your investments'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Display Real-Time Quotes']/following::android.widget.TextView[1]")
	public WebElement displayRealTimeQuotesDescription;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Display Real-Time Quotes'`]/XCUIElementTypeSwitch")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Display Real-Time Quotes']/following::android.widget.Switch")
	public WebElement switchDisplayRealTimeQuotes;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Refresh Data Use Refresh Data to resolve issues with missing transactions, categories and other data.'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Refresh Data']/following-sibling::android.widget.TextView[@text='Use Refresh Data to resolve issues with missing transactions, categories and other data.']")
	public WebElement refreshData;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Account Balance Preference'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Account Balance Preference']")
	public WebElement accountBalancePreferenceOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Account Balance Preference'`][-1]")
	@AndroidFindBy(xpath="//android.view.View[@text='Account Balance Preference']")
	private WebElement accountBalancePreferenceHeaderText;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Choose the default balance to use on the dashboard cards.'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Choose the default balance to use on the dashboard cards.']")
	private WebElement accountBalancePreferenceDescriptionText;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name = 'Settings, back'`]")
	@AndroidFindBy(xpath="//android.view.View[@text='Account Balance Preference']/..//android.widget.ImageView")
	private WebElement backButtonOnAccountBalancePreferenceHeader;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = \"RadioButton Today's Balance\"`][-1]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"Today's Balance\"]")
	private WebElement todaysBalanceOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'RadioButton Online Balance'`][-1]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Online Balance']")
	private WebElement onlineBalanceOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'RadioButton Projected Balance'`][-1]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Projected Balance']")
	private WebElement projectedBalanceOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Customize Dashboard'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Customize Dashboard']")
	public WebElement customizeDashboardOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Manage Accounts'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Manage Accounts']")
	public WebElement manageAccountsOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Manage Categories'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Manage Categories']")
	public WebElement manageCategoriesOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Manage Alerts'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Manage Alerts']")
	public WebElement ManageAlertsTxt;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Manage Alerts'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Manage Alerts']")
	public WebElement ManageAlertsHeaderTxt;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Rules'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Rules']")
	public WebElement rulesOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Renaming Rules'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Renaming Rules']")
	public WebElement renamingRulesOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Renaming Rules'`]")
	@AndroidFindBy(xpath="//android.view.View[@text='Renaming Rules']")
	public WebElement renamingRulesHeaderText;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'No renaming rules added yet'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='No renaming rules added yet']")
	public WebElement noRenamingRulesText;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name = 'Settings, back'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Renaming Rules']/../android.widget.ImageButton")
	public WebElement backButtonOnRenamingRulesHeader;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[-4]")
	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='addRenameRule']")
	public WebElement addRenamingRulesButton;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Memorized Payees'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Memorized Payees']")
	public WebElement memorizedPayeesOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Tags'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Tags']")
	public WebElement tagsOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Tags'`]")
	@AndroidFindBy(xpath="//android.view.View[@text='Tags']")
	public WebElement tagsHeaderText;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name = 'Settings, back'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Tags']/../android.widget.ImageButton")
	public WebElement backButtonOnTagsHeader;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Create New Tag'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Create New Tag']")
	public WebElement createNewTagOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Use Quicken Passcode'`]")
	@AndroidFindBy(xpath="//android.widget.Switch/../android.widget.TextView[@text=\"Use Quicken Passcode\"]")
	public WebElement quickenPasscodeTxt;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Use Touch ID'`]")
	@AndroidFindBy(xpath="TBD")
	public WebElement useTouchIDTxt;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Feedback'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Feedback']")
	public WebElement FeedbackTxt;
	




	public WebElement getTextView(String ele) {
		Helper h = new Helper();
		if (h.getEngine().equalsIgnoreCase("ios")) {
			try {
				WebElement me =  (WebElement) Engine.getDriver().findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`name='"+ele+"'`]"));
				return me;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			try {
				WebElement me =  (WebElement) Engine.getDriver().findElement(By.xpath("//android.widget.TextView[@text='"+ele+"']"));	
				return me;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	public boolean verifyQuickenID(String quickenID){

		String xPathForControl = "//android.widget.TextView[normalize-space(@text)='"+quickenID+"']";
		String xPathForIOS = "//XCUIElementTypeStaticText[normalize-space(@name)='"+quickenID+"']";

		Helper h = new Helper();

		if (h.getEngine().equals("android")) {
			try{
				return Engine.getDriver().findElement(By.xpath(xPathForControl)).isDisplayed();
			}
			catch(Exception E){
				return false;
			}

		}
		else {
			try{
				return Engine.getDriver().findElement(By.xpath(xPathForIOS)).isDisplayed();
			}
			catch(Exception E){
				return false;
			}
		}
	}

	public boolean verifyCloudAccountName(String cloudName) throws Exception{

		String xPathForControl = "//android.widget.TextView[@text='"+cloudName+"']";
		String xPathForIOS = "//XCUIElementTypeStaticText[@name='"+cloudName+"']";

		//String xPathForControl = "//android.widget.TextView[starts-with(@text,'Cloud Account Name:')]";

		Helper h = new Helper();

		if (h.getEngine().equals("android")) {
			try{
				//				System.out.println("*******");
				//				System.out.println(Engine.getDriver().findElementByXPath(xPathForControl).getText());
				//				System.out.println("*******");
				return Engine.getDriver().findElement(By.xpath(xPathForControl)).isDisplayed();
			}
			catch(Exception E){
				return false;
			}

		}
		else {
			//			System.out.println("*******");
			//			System.out.println(Engine.getDriver().findElementByXPath(xPathForIOS).getText());
			//			System.out.println("*******");
			return Engine.getDriver().findElement(By.xpath(xPathForIOS)).isDisplayed();	
		}
	}

	public WebElement getAccountElement (String accountName) {

		String xpath_Android = "//android.widget.TextView[@text='"+accountName+"']";
		String xpath_IOS = "**/XCUIElementTypeStaticText[`name=='"+accountName+"'`]" ;

		Helper h = new Helper();

		if (h.getEngine().equals("android")){
			try{
				WebElement me = Engine.getDriver().findElement(By.xpath(xpath_Android));
				return me;
			}
			catch(Exception E){
				return null;
			}

		}
		else {
			try{
				WebElement me = Engine.getDriver().findElement(AppiumBy.iOSClassChain(xpath_IOS));
				return me;
			}
			catch(Exception E){
				Commentary.log(LogStatus.INFO, E.getMessage());
				return null;
			}
		}

	}
	@SuppressWarnings("deprecation")
	public void selectBack (String bckButton) throws Exception {
		Helper h = new Helper();
		if (h.getEngine().equalsIgnoreCase("android")){
			WebElement backButton_android = Engine.getDriver().findElement(By.xpath("//android.widget.ImageButton[@index=0]"));
			//Engine.getDriver().pressKeyCode(AndroidKeyCode.BACK);
//			Engine.getDriver().pressKeyCodeCommand(AndroidKeyCode.BACK);
			backButton_android.click();
		} else {
			WebElement backButton_ios = Engine.getDriver().findElement(By.xpath(bckButton));
			backButton_ios.click();
		}
	}
	
	public void switchDataset (String datasetname) throws Exception {
		String xpath_Android = "//android.widget.TextView[@text='"+datasetname+"']";
		String classChain_xpath_IOS = "**/XCUIElementTypeOther[`name='"+datasetname+"'`][2]";

		Helper h = new Helper();
		if (h.getEngine().equalsIgnoreCase("ios")) {
			WebElement me_ios = Engine.getDriver().findElement(AppiumBy.iOSClassChain(classChain_xpath_IOS));
			Verify.waitForObject(me_ios, 2);
			me_ios.click();
			Thread.sleep(500);
			btnDone.click();
		} else {
			WebElement me_android = Engine.getDriver().findElement(By.xpath(xpath_Android));
			Verify.waitForObject(me_android, 2);
			me_android.click();
			Thread.sleep(500);
			btnDone.click();
		}
		Thread.sleep(3000);
	}
	
	public boolean isDisplayYelpEnabled() throws Exception {
		Helper h = new Helper();
		
		if (h.getEngine().equalsIgnoreCase("android")) {
			Verify.waitForObject(switchDisplayYelp, 1);
			if (this.switchDisplayYelp.getText().equalsIgnoreCase("ON")) {
				return true;
			} else {
				return false;
			}
		} else { //For iOS
			Verify.waitForObject(switchDisplayYelp, 1);
			if (this.switchDisplayYelp.getAttribute("value").equals("1")) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	public void enableDisplayYelpOption() throws Exception {
		
		if (isDisplayYelpEnabled()) {
			Commentary.log(LogStatus.INFO, "Display Yelp Recommendations option is already Enabled.");
			Thread.sleep(2000);
		} else {
			this.switchDisplayYelp.click();
			Commentary.log(LogStatus.INFO, "Display Yelp Recommendations option is Enabled now.");
			Thread.sleep(2000);
		}
		
	}
	
	public void disableDisplayYelpOption() throws Exception {
		
		if (isDisplayYelpEnabled()) {
			this.switchDisplayYelp.click();
			Commentary.log(LogStatus.INFO, "Display Yelp Recommendations option is Disabled now.");
			Thread.sleep(2000);
		} else {
			Commentary.log(LogStatus.INFO, "Display Yelp Recommendations option is already Disabled.");
			Thread.sleep(2000);
		}
	}
	
	public boolean isDisplayFavoritePayeesEnabled() throws Exception {
		Helper h = new Helper();
		
		if (h.getEngine().equalsIgnoreCase("android")) {
			Verify.waitForObject(switchDisplayFavoritePayees, 1);
			if (this.switchDisplayFavoritePayees.getText().equalsIgnoreCase("ON")) {
				return true;
			} else {
				return false;
			}
		} else { //For iOS
			Verify.waitForObject(switchDisplayFavoritePayees, 1);
			if (this.switchDisplayFavoritePayees.getAttribute("value").equals("1")) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	public void enableDisplayFavoritePayeesOption() throws Exception {
		
		if (isDisplayFavoritePayeesEnabled()) {
			Commentary.log(LogStatus.INFO, "Display Favorite Payees option is already Enabled.");
			Thread.sleep(2000);
		} else {
			this.switchDisplayFavoritePayees.click();
			Commentary.log(LogStatus.INFO, "Display Favorite Payees option is Enabled now.");
			Thread.sleep(2000);
		}
		
	}
	
	public void disableDisplayFavoritePayeesOption() throws Exception {
		
		if (isDisplayFavoritePayeesEnabled()) {
			this.switchDisplayFavoritePayees.click();
			Commentary.log(LogStatus.INFO, "Display Favorite Payees option is Disabled now.");
			Thread.sleep(2000);
		} else {
			Commentary.log(LogStatus.INFO, "Display Favorite Payees option is already Disabled.");
			Thread.sleep(2000);
		}
	}
	
	public boolean isDisplayRealTimeQuotesEnabled() throws Exception {
		Helper h = new Helper();
		
		if (h.getEngine().equalsIgnoreCase("android")) {
			Verify.waitForObject(switchDisplayRealTimeQuotes, 1);
			if (this.switchDisplayRealTimeQuotes.getText().equalsIgnoreCase("ON")) {
				return true;
			} else {
				return false;
			}
		} else { //For iOS
			Verify.waitForObject(switchDisplayRealTimeQuotes, 1);
			if (this.switchDisplayRealTimeQuotes.getAttribute("value").equals("1")) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	public void enableDisplayRealTimeQuotesOption() throws Exception {
		
		if (isDisplayRealTimeQuotesEnabled()) {
			Commentary.log(LogStatus.INFO, "Display Real-Time Quotes option is already Enabled.");
			Thread.sleep(2000);
		} else {
			this.switchDisplayRealTimeQuotes.click();
			Commentary.log(LogStatus.INFO, "Display Real-Time Quotes option is Enabled now.");
			Thread.sleep(2000);
		}
		
	}
	
	public void disableDisplayRealTimeQuotesOption() throws Exception {
		
		if (isDisplayRealTimeQuotesEnabled()) {
			this.switchDisplayRealTimeQuotes.click();
			Commentary.log(LogStatus.INFO, "Display Real-Time Quotes option is Disabled now.");
			Thread.sleep(2000);
		} else {
			Commentary.log(LogStatus.INFO, "Display Real-Time Quotes option is already Disabled.");
			Thread.sleep(2000);
		}
	}
	
	public boolean isShowLongCategoryNamesEnabled() throws Exception {
		Helper h = new Helper();
		
		if (h.getEngine().equalsIgnoreCase("android")) {
			Verify.waitForObject(switchShowLongCategoryNames, 1);
			if (this.switchShowLongCategoryNames.getText().equalsIgnoreCase("ON")) {
				return true;
			} else {
				return false;
			}
		} else { //For iOS
			Verify.waitForObject(switchShowLongCategoryNames, 1);
			if (this.switchShowLongCategoryNames.getAttribute("value").equals("1")) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	public void enableShowLongCategoryNamesOption() throws Exception {
		
		if (isShowLongCategoryNamesEnabled()) {
			Commentary.log(LogStatus.INFO, "Show Long Categories names option is already Enabled.");
			Thread.sleep(2000);
		} else {
			this.switchShowLongCategoryNames.click();
			Commentary.log(LogStatus.INFO, "Show Long Categories names option is Enabled now.");
			Thread.sleep(2000);
		}
		
	}
	
	public void disableShowLongCategoryNamesOption() throws Exception {
		
		if (isShowLongCategoryNamesEnabled()) {
			this.switchShowLongCategoryNames.click();
			Commentary.log(LogStatus.INFO, "Show Long Categories names option is Disabled now.");
			Thread.sleep(2000);
		} else {
			Commentary.log(LogStatus.INFO, "Show Long Categories names option is already Disabled.");
			Thread.sleep(2000);
		}
	}
	
	public void clickOnDashboardOption() throws Exception {
		
		OverviewPage op = new OverviewPage();
		Verify.waitForObject(op.hambergerIcon, 1);
		op.hambergerIcon.click();
		
		Verify.waitForObject(this.dashboardOption, 1);
		this.dashboardOption.click();
		Thread.sleep(2000);
	}
	
	public void clickOnAccountsOption() throws Exception {
		
		OverviewPage op = new OverviewPage();
		Verify.waitForObject(op.hambergerIcon, 1);
		op.hambergerIcon.click();
		
		Verify.waitForObject(this.accountTxt, 1);
		this.accountTxt.click();
		Thread.sleep(2000);
	}

	public void clickOnAllTransactionsOption() throws Exception {

		OverviewPage op = new OverviewPage();
		Verify.waitForObject(op.hambergerIcon, 1);
		op.hambergerIcon.click();

		Verify.waitForObject(this.allTransactionsOption, 1);
		this.allTransactionsOption.click();
		Thread.sleep(2000);
	}
	
	public void clickOnBillsOption() throws Exception {

		OverviewPage op = new OverviewPage();
		Verify.waitForObject(op.hambergerIcon, 1);
		op.hambergerIcon.click();

		Verify.waitForObject(this.billsOption, 1);
		this.billsOption.click();
		Thread.sleep(2000);
	}
	
	public void clickOnBudgetsOption() throws Exception {

		OverviewPage op = new OverviewPage();
		Verify.waitForObject(op.hambergerIcon, 1);
		op.hambergerIcon.click();

		Verify.waitForObject(this.budgetsOption, 1);
		this.budgetsOption.click();
		Thread.sleep(2000);
	}
	
	public void clickOnInvestingOption() throws Exception {

		OverviewPage op = new OverviewPage();
		Verify.waitForObject(op.hambergerIcon, 1);
		op.hambergerIcon.click();

		Verify.waitForObject(this.investingOption, 1);
		this.investingOption.click();
		Thread.sleep(2000);
	}
	
	public void clickOnReportsOption() throws Exception {

		OverviewPage op = new OverviewPage();
		Verify.waitForObject(op.hambergerIcon, 1);
		op.hambergerIcon.click();

		Verify.waitForObject(this.reportsOption, 1);
		this.reportsOption.click();
		Thread.sleep(2000);
	}
	
	
	
	public void clickOnProfileOption() throws Exception {

		OverviewPage op = new OverviewPage();
		Verify.waitForObject(op.hambergerIcon, 1);
		op.hambergerIcon.click();

		Verify.waitForObject(this.profileOption, 1);
		this.profileOption.click();
		Thread.sleep(2000);
	}
	
	public void clickOnSettingsOption() throws Exception {

		OverviewPage op = new OverviewPage();
		Verify.waitForObject(op.hambergerIcon, 1);
		op.hambergerIcon.click();

		Verify.waitForObject(this.settingsOption, 1);
		this.settingsOption.click();
		Thread.sleep(2000);
	}
	
	public void clickOnSpendingByCategoryOption() throws Exception {
		
		SettingsPage sp = new SettingsPage();
		sp.clickOnReportsOption();
		Verify.waitForObject(sp.spendingByCategoryOption, 1);
		sp.spendingByCategoryOption.click();
		Thread.sleep(2000);
	}
	
	public void clickOnMonthlySummaryOption() throws Exception {
		
		SettingsPage sp = new SettingsPage();
		sp.clickOnReportsOption();
		Verify.waitForObject(sp.monthlySummaryOption, 1);
		sp.monthlySummaryOption.click();
		Thread.sleep(2000);
	}
	
	
	public void clickOnNetIncomeOption() throws Exception {
		
		SettingsPage sp = new SettingsPage();
		sp.clickOnReportsOption();
		Verify.waitForObject(sp.netIncomeOption, 1);
		sp.netIncomeOption.click();
		Thread.sleep(2000);
	}
	
	public void clickOnNetWorthOption() throws Exception {
		
		SettingsPage sp = new SettingsPage();
		sp.clickOnReportsOption();
		Verify.waitForObject(sp.netWorthOption, 1);
		sp.netWorthOption.click();
		Thread.sleep(2000);
	}
	
	public void clickOnSpendingOverTimeOption() throws Exception {
		
		SettingsPage sp = new SettingsPage();
		sp.clickOnReportsOption();
		Verify.waitForObject(sp.spendingOverTimeOption, 1);
		sp.spendingOverTimeOption.click();
		Thread.sleep(2000);
	}
	
	public void clickOnCustomizeDashboardOption() throws Exception {
		
		SettingsPage sp = new SettingsPage();
		clickOnSettingsOption();
		Verify.waitForObject(sp.accountIDUnderProfileOption, 1);
		sp.customizeDashboardOption.click();
		Thread.sleep(1000);
	
	}
	
	public void clickOnAccountsManagementOption() throws Exception {
		
		SettingsPage st = new SettingsPage();
		clickOnSettingsOption();
		Verify.waitForObject(st.manageAccountsOption, 1);
		st.manageAccountsOption.click();
		Thread.sleep(1000);
	
	}
	
	public void clickOnManageAlertsOption() throws Exception {
		
		SettingsPage sp = new SettingsPage();
		clickOnSettingsOption();
		Verify.waitForObject(sp.manageAlerts, 1);
		sp.manageAlerts.click();
		Thread.sleep(1000);
	
	}
	
	public void clickOnManageCategoriesOption() throws Exception {
		
		SettingsPage sp = new SettingsPage();
		clickOnSettingsOption();
		Verify.waitForObject(sp.manageCategoriesOption, 1);
		sp.manageCategoriesOption.click();
		Thread.sleep(1000);
	
	}
	
	/**
	 * This method will help setting the Account Balance Preference.
	 * @param preferenceOption
	 * @throws Exception
	 */
		
	public void setDefaultAccountBalancePreference(String preferenceOption) throws Exception {
		
		SettingsPage sp = new SettingsPage();
		clickOnSettingsOption();
		Verify.waitForObject(sp.accountBalancePreferenceOption, 1);
		sp.accountBalancePreferenceOption.click();
		Thread.sleep(1000);

		if(preferenceOption.equalsIgnoreCase("Today's")) {
			sp.todaysBalanceOption.click();
			Thread.sleep(500);
		}
		
		if(preferenceOption.equalsIgnoreCase("Online")) {
			sp.onlineBalanceOption.click();
			Thread.sleep(500);
		}
		
		if(preferenceOption.equalsIgnoreCase("Projected")) {
			sp.projectedBalanceOption.click();
			Thread.sleep(500);
		}
		
		sp.backButtonOnAccountBalancePreferenceHeader.click();
		Verify.waitForObject(sp.settingsHeaderText, 1);
		
		sp.backButtonOnSettingsHeader.click();
		Thread.sleep(1000);
	}
	
	/** 
	 * This method will scroll down the page to make Refresh Data option visible.
	 * @throws Exception
	 */
	public void scrollToRefreshDataOption() throws Exception {
		
		Helper h = new Helper();
		OverviewPage op = new OverviewPage();
		
		if(h.getEngine().equalsIgnoreCase("android")) {
			Engine.getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Refresh Data\").instance(0))"));
			op.verticalScrollDownAndroid();
		} else {
//			String id = this.refreshData.getId();
//			HashMap<String, String> scrollObject = new HashMap<String, String>();
//			scrollObject.put("element", id);
//			scrollObject.put("toVisible", "not an empty string");
//			scrollObject.put("direction", "down");
//			Engine.getDriver().executeScript("mobile:scroll", scrollObject);
//			Thread.sleep(1000);
			
			Verify.waitForObject(this.displayFavoritePayeesText, 1);
			Point location = this.displayFavoritePayeesText.getLocation();

			int x_start=location.getX();
			int y_start=location.getY();
			int y_end=300;
			
			PointerInput finger1 = new PointerInput(Kind.TOUCH, "finger1");
			Sequence sequence = new Sequence(finger1, 1)
					.addAction(finger1.createPointerMove(Duration.ZERO, Origin.viewport(), x_start, y_start)) 
					.addAction(finger1.createPointerDown(MouseButton.LEFT.asArg())) 
					.addAction(new Pause(finger1, Duration.ofMillis(200)))
					.addAction(finger1.createPointerMove(Duration.ofMillis(100), Origin.viewport(), x_start, y_end)) 
					.addAction(finger1.createPointerUp(MouseButton.LEFT.asArg()));
			
			Engine.getDriver().perform(Collections.singletonList(sequence));
			Thread.sleep(2000);
			
		}
	}

}
