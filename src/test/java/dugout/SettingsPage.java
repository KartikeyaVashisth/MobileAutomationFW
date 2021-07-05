package dugout;

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
	@iOSXCUITFindBy(iOSNsPredicate= "name == 'closeButton'")
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='closeButton']")
	public MobileElement closeButton;

	@iOSXCUITFindBy(iOSNsPredicate= "name == 'logOutButton'")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Logout']")
	public MobileElement logoutButton;

	@iOSXCUITFindBy(id = "dataSetArrow")
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='dataSetArrow']")
	public MobileElement datasetDDButton;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'doneButton'`]")
	@AndroidFindBy(xpath="//*[@text='Done']")
	public MobileElement btnDone;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == 'Accounts'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Accounts']")
	public MobileElement accountTxt;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == 'Passcode'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Passcode']")
	public MobileElement PasscodeTxt;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == 'Passcode'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Passcode']")
	public MobileElement PasscodeHeaderTxt;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == 'Settings'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Settings']")
	public MobileElement settingsOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == 'Settings'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Settings']")
	public MobileElement settingsHeaderText;

	@iOSXCUITFindBy(accessibility="Back")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Settings']/../android.widget.ImageButton")
	public MobileElement backButtonOnHeader;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == 'Display Yelp Recommendations'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Display Yelp Recommendations']")
	public MobileElement displayYelpRecommendationsText;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Display Yelp Recommendations'`]/XCUIElementTypeStaticText[`name = 'Displays nearby payees recommended by Yelp when you are adding payees for your transactions'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Display Yelp Recommendations']/following::android.widget.TextView")
	public MobileElement displayYelpDescription;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Display Yelp Recommendations'`][-2]/XCUIElementTypeSwitch")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Display Yelp Recommendations']/following::android.widget.Switch")
	public MobileElement switchDisplayYelp;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name == 'Manage Alerts'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Manage Alerts']")
	public MobileElement ManageAlertsTxt;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Manage Alerts'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Manage Alerts']")
	public MobileElement ManageAlertsHeaderTxt;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == 'Help & Legal'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Help & Legal']")
	public MobileElement HelpLegalTxt;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Help & Legal'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Help & Legal']")
	public MobileElement HelpLegalHeaderTxt;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Feedback'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Feedback']")
	public MobileElement FeedbackTxt;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Use Quicken Passcode'`]")
	@AndroidFindBy(xpath="//android.widget.Switch/../android.widget.TextView[@text=\"Use Quicken Passcode\"]")
	public MobileElement quickenPasscodeTxt;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Use Touch ID'`]")
	@AndroidFindBy(xpath="TBD")
	public MobileElement useTouchIDTxt;


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

}
