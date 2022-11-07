package dugout;

import java.util.HashMap;

//import org.eclipse.jetty.util.annotation.ManagedObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
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
	public MobileElement passcode;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == 'Help & Legal'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Help & Legal']")
	public MobileElement helpAndLegal;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == 'Accounts'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Accounts']")
	public MobileElement accounts;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == 'Manage Alerts'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Manage Alerts']")
	public MobileElement manageAlerts;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name='logOutButton'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Sign Out']")
	public MobileElement logout;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name='Sign Out'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Sign Out']")
	public MobileElement signOutConfirmationBtn;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name='Cancel'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Cancel']")
	public MobileElement cancelBtn;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='closeButton'`]")
	@AndroidFindBy(xpath="//*[@content-desc='backButton']//*[@class='android.widget.ImageView']")
	public MobileElement close;

	//Hamburger menu options
	@iOSXCUITFindBy(iOSNsPredicate= "name = 'closeButton'")
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='closeButton']")
	public MobileElement closeButton;
	
	@iOSXCUITFindBy(iOSClassChain= "**/XCUIElementTypeOther[`name contains 'logOutButton'`]/XCUIElementTypeStaticText")
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='logOutButton']/../android.widget.TextView[contains(@text,'(')]")
	public MobileElement versionNumber;

	@iOSXCUITFindBy(iOSNsPredicate= "name = 'logOutButton'")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Logout']")
	public MobileElement logoutButton;

//	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Cloud Account name Menu'`]")
//	@AndroidFindBy(xpath="//android.widget.TextView[@text='Cloud Account name']")
//	public MobileElement cloudAccountNameOption;
	
//	@iOSXCUITFindBy(id = "dataSetArrow")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Expand Icon'`][-1]")
//	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='dataSetArrow']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Help']/../following-sibling::android.view.ViewGroup[1]")
	public MobileElement datasetDDButton;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'doneButton'`]")
	@AndroidFindBy(xpath="//*[@text='Done']")
	public MobileElement btnDone;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name ='Dashboard Menu'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Dashboard']")
	public MobileElement dashboardOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Accounts Menu'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Accounts']")
	public MobileElement accountTxt;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'All Transactions Menu'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='All Transactions']")
	public MobileElement allTransactionsOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Bills Menu'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Bills']")
	public MobileElement billsOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Budgets Menu'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Budgets']")
	public MobileElement budgetsOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Investments Menu'`]")
	@AndroidFindBy(accessibility = "Investments Menu")
	public MobileElement investingOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Reports'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Reports']")
	public MobileElement reportsOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Monthly Summary'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Monthly Summary']")
	public MobileElement monthlySummaryOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Net Income'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Net Income']")
	public MobileElement netIncomeOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Net Worth'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Net Worth']")
	public MobileElement netWorthOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Spending by Category'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Spending by Category']")
	public MobileElement spendingByCategoryOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Spending Over Time'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Spending Over Time']")
	public MobileElement spendingOverTimeOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name='Go back'`]")
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='backArrow']")
	public MobileElement backButtonOnHeaders;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Profile Menu'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Profile']")
	public MobileElement profileOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Profile'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Profile']")
	public MobileElement ProfileHeaderTxt;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Go back Profile'`][-1]/**/XCUIElementTypeButton[`name='Go back'`]")
	@AndroidFindBy(xpath="//android.view.View[@text='Profile']/../android.widget.Button[@content-desc='Go back']")
	public MobileElement backButtonOnProfileHeader;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains 'Your name:'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@content-desc,'Your name:')]")
	public MobileElement accountNameUnderProfileOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains 'Your email:'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@content-desc,'Your email:')]")
	public MobileElement accountIDUnderProfileOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains 'Subscription Expires on :'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@content-desc,'Subscription Expires on :')]")
	public MobileElement subscriptionDetailsUnderProfileOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Passcode Menu'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Passcode']")
	public MobileElement PasscodeTxt;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Passcode'`]")
	@AndroidFindBy(xpath="//android.view.View[@text='Passcode']")
	public MobileElement PasscodeHeaderTxt;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name = 'Profile, back'`]")
	@AndroidFindBy(xpath="//android.widget.Button[@content-desc='Profile, back']")
	public MobileElement backButtonOnPasscodeHeader;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Use Device Passcode'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Use Quicken Passcode']")
	public MobileElement useDevicePasscodeTxt;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Legal'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Legal']")
	public MobileElement legalTxt;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Acknowledgements'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Acknowledgements']")
	public MobileElement acknowledgementsOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'License Agreement'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='License Agreement']")
	public MobileElement licenceAgreementOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Privacy'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Privacy']")
	public MobileElement privacyOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Help & Legal'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Help & Legal']")
	public MobileElement HelpLegalHeaderTxt;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Settings Menu'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Settings']")
	public MobileElement settingsOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Help'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Help']")
	public MobileElement helpOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Chat with support'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Chat with support']")
	public MobileElement chatWithsupportOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'FAQ'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='FAQ']")
	public MobileElement FAQOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Report an issue'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Report an issue']")
	public MobileElement reportAnIssueOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Visit support site'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Visit support site']")
	public MobileElement visitSupportSiteOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Settings'`]")
	@AndroidFindBy(xpath="//android.view.View[@text='Settings']")
	public MobileElement settingsHeaderText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name='Go back'`]")
	@AndroidFindBy(xpath="//android.view.View[@text='Settings']/../android.view.ViewGroup[@content-desc='backArrow']")
	public MobileElement backButtonOnSettingsHeader;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Display Yelp Recommendations'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Display Yelp Recommendations']")
	public MobileElement displayYelpRecommendationsText;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Display Yelp Recommendations'`]/XCUIElementTypeStaticText[`name = \"Display Yelp's recommendations of nearby payees when you add payees to your transactions\"`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Display Yelp Recommendations']/following::android.widget.TextView[@text=\"Display Yelp's recommendations of nearby payees when you add payees to your transactions\"]")
	public MobileElement displayYelpDescription;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Display Yelp Recommendations'`]/XCUIElementTypeSwitch")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Display Yelp Recommendations']/following::android.widget.Switch")
	public MobileElement switchDisplayYelp;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Show long category names'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Show long category names']")
	public MobileElement showLongCategoryNamesText;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Show long category names'`]/XCUIElementTypeStaticText[`name = 'Display long form of categories in transaction and details'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Show long category names']/following::android.widget.TextView[1]")
	public MobileElement showLongCategoryNamesDescription;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Show long category names'`]/XCUIElementTypeSwitch")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Show long category names']/following::android.widget.Switch")
	public MobileElement switchShowLongCategoryNames;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Display Favorite Payees'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Display Favorite Payees']")
	public MobileElement displayFavoritePayeesText;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Display Favorite Payees'`]/XCUIElementTypeStaticText[`name = 'Displays favorite payees when you are adding payees for your transactions'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Display Favorite Payees']/following::android.widget.TextView[1]")
	public MobileElement displayFavoritePayeesDescription;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Display Favorite Payees'`]/XCUIElementTypeSwitch")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Display Favorite Payees']/following::android.widget.Switch")
	public MobileElement switchDisplayFavoritePayees;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Refresh Data Use Refresh Data to resolve issues with missing transactions, categories and other data.'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Refresh Data']/following-sibling::android.widget.TextView[@text='Use Refresh Data to resolve issues with missing transactions, categories and other data.']")
	public MobileElement refreshData;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Account Balance Preference'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Account Balance Preference']")
	public MobileElement accountBalancePreferenceOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Account Balance Preference'`][-1]")
	@AndroidFindBy(xpath="//android.view.View[@text='Account Balance Preference']")
	private MobileElement accountBalancePreferenceHeaderText;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Choose the default balance to use on the dashboard cards.'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Choose the default balance to use on the dashboard cards.']")
	private MobileElement accountBalancePreferenceDescriptionText;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name = 'Settings, back'`]")
	@AndroidFindBy(xpath="//android.view.View[@text='Account Balance Preference']/..//android.widget.ImageView")
	private MobileElement backButtonOnAccountBalancePreferenceHeader;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = \"RadioButton Today's Balance\"`][-1]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"Today's Balance\"]")
	private MobileElement todaysBalanceOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'RadioButton Online Balance'`][-1]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Online Balance']")
	private MobileElement onlineBalanceOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'RadioButton Projected Balance'`][-1]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Projected Balance']")
	private MobileElement projectedBalanceOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Customize Dashboard'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Customize Dashboard']")
	public MobileElement customizeDashboardOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Manage Accounts'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Manage Accounts']")
	public MobileElement accountsManagementOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Manage Categories'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Manage Categories']")
	public MobileElement manageCategoriesOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Manage Alerts'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Manage Alerts']")
	public MobileElement ManageAlertsTxt;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Manage Alerts'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Manage Alerts']")
	public MobileElement ManageAlertsHeaderTxt;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Rules'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Rules']")
	public MobileElement rulesOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Renaming Rules'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Renaming Rules']")
	public MobileElement renamingRulesOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Renaming Rules'`]")
	@AndroidFindBy(xpath="//android.view.View[@text='Renaming Rules']")
	public MobileElement renamingRulesHeaderText;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'No renaming rules added yet'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='No renaming rules added yet']")
	public MobileElement noRenamingRulesText;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name = 'Settings, back'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Renaming Rules']/../android.widget.ImageButton")
	public MobileElement backButtonOnRenamingRulesHeader;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[-4]")
	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='addRenameRule']")
	public MobileElement addRenamingRulesButton;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Memorized Payees'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Memorized Payees']")
	public MobileElement memorizedPayeesOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Tags'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Tags']")
	public MobileElement tagsOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Tags'`]")
	@AndroidFindBy(xpath="//android.view.View[@text='Tags']")
	public MobileElement tagsHeaderText;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name = 'Settings, back'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Tags']/../android.widget.ImageButton")
	public MobileElement backButtonOnTagsHeader;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Create New Tag'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Create New Tag']")
	public MobileElement createNewTagOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Use Quicken Passcode'`]")
	@AndroidFindBy(xpath="//android.widget.Switch/../android.widget.TextView[@text=\"Use Quicken Passcode\"]")
	public MobileElement quickenPasscodeTxt;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Use Touch ID'`]")
	@AndroidFindBy(xpath="TBD")
	public MobileElement useTouchIDTxt;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Feedback'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Feedback']")
	public MobileElement FeedbackTxt;
	




	public MobileElement getTextView(String ele) {
		Helper h = new Helper();
		if (h.getEngine().equalsIgnoreCase("ios")) {
			try {
				MobileElement me =  (MobileElement) Engine.getDriver().findElement(MobileBy.iOSClassChain("**/XCUIElementTypeStaticText[`name='"+ele+"'`]"));
				return me;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			try {
				MobileElement me =  (MobileElement) Engine.getDriver().findElement(By.xpath("//android.widget.TextView[@text='"+ele+"']"));	
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
				return Engine.getDriver().findElementByXPath(xPathForControl).isDisplayed();
			}
			catch(Exception E){
				return false;
			}

		}
		else {
			try{
				return Engine.getDriver().findElementByXPath(xPathForIOS).isDisplayed();
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
				return Engine.getDriver().findElementByXPath(xPathForControl).isDisplayed();
			}
			catch(Exception E){
				return false;
			}

		}
		else {
			//			System.out.println("*******");
			//			System.out.println(Engine.getDriver().findElementByXPath(xPathForIOS).getText());
			//			System.out.println("*******");
			return Engine.getDriver().findElementByXPath(xPathForIOS).isDisplayed();	
		}
	}

	public MobileElement getAccountElement (String accountName) {

		String xpath_Android = "//android.widget.TextView[@text='"+accountName+"']";
		String xpath_IOS = "**/XCUIElementTypeStaticText[`name=='"+accountName+"'`]" ;

		Helper h = new Helper();

		if (h.getEngine().equals("android")){
			try{
				MobileElement me = (MobileElement) Engine.getDriver().findElementByXPath(xpath_Android);
				return me;
			}
			catch(Exception E){
				return null;
			}

		}
		else {
			try{
				MobileElement me = (MobileElement) Engine.getDriver().findElement(MobileBy.iOSClassChain(xpath_IOS));
				return (MobileElement) Engine.getDriver().findElement(MobileBy.iOSClassChain(xpath_IOS));
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
			//MobileElement backButton_android = (MobileElement) Engine.getDriver().findElementByXPath("//android.widget.ImageButton[@index=0]");
			//Engine.getDriver().pressKeyCode(AndroidKeyCode.BACK);
			((AndroidDriver<MobileElement>) Engine.getDriver()).pressKeyCode(AndroidKeyCode.BACK);
			//backButton_android.click();
		} else {
			MobileElement backButton_ios = (MobileElement) Engine.getDriver().findElementByAccessibilityId(bckButton);
			backButton_ios.click();
		}
	}
	
	public void switchDataset (String datasetname) throws Exception {
		String xpath_Android = "//android.widget.TextView[@text='"+datasetname+"']";
		String classChain_xpath_IOS = "**/XCUIElementTypeOther[`name='"+datasetname+"'`][2]";

		Helper h = new Helper();
		if (h.getEngine().equalsIgnoreCase("ios")) {
			MobileElement me_ios = (MobileElement) Engine.getDriver().findElement(MobileBy.iOSClassChain(classChain_xpath_IOS));
			Verify.waitForObject(me_ios, 2);
			me_ios.click();
			Thread.sleep(500);
			btnDone.click();
		} else {
			MobileElement me_android = (MobileElement) Engine.getDriver().findElementByXPath(xpath_Android);
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
		Verify.waitForObject(st.accountsManagementOption, 1);
		st.accountsManagementOption.click();
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
		Verify.waitForObject(sp.accountIDUnderProfileOption, 1);
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
			Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Refresh Data\").instance(0))"));
			op.verticalScrollDownAndroid();
		} else {
			String id = this.refreshData.getId();
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("element", id);
			scrollObject.put("toVisible", "not an empty string");
			scrollObject.put("direction", "down");
			Engine.getDriver().executeScript("mobile:swipe", scrollObject);
			Thread.sleep(1000);
			
		}
	}

}
