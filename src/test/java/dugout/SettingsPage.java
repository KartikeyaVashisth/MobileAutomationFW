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
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import referee.Commentary;
import referee.Verify;
import support.Engine;
import support.Helper;

public class SettingsPage {
	
	public SettingsPage () {
		try {
			Helper h = new Helper();
			if (h.getEngine().equals("android"))
				PageFactory.initElements(new AppiumFieldDecorator(Engine.ad),this);
			else
				PageFactory.initElements(new AppiumFieldDecorator(Engine.iosd),this);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
//	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Passcode & Fingerprint\"])[3]")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == 'Passcode'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Passcode']")
	public MobileElement passcode;
	
//	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Help & Legal\"])[3]")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == 'Help & Legal'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Help & Legal']")
	public MobileElement helpAndLegal;
	
//	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Accounts\"])")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == 'Accounts'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Accounts']")
	public MobileElement accounts;
	
//	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Manage Alerts\"])[3]")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == 'Manage Alerts'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Manage Alerts']")
	public MobileElement manageAlerts;
	
//	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"logOutButton\"]")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name='logOutButton'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Sign Out']")
	public MobileElement logout;
	
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name='Sign Out'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Sign Out']")
	public MobileElement signOutConfirmationBtn;
	
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name='Cancel'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Cancel']")
	public MobileElement cancelBtn;
	
//	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"closeButton\"])[2]")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='closeButton'`]")
	@AndroidFindBy(xpath="//*[@content-desc='backButton']//*[@class='android.widget.ImageView']")
	public MobileElement close;
	
	//Hamburger menu options
//		@iOSFindBy(xpath="//*[@name='closeButton']")
		@iOSXCUITFindBy(iOSNsPredicate= "name == 'closeButton'")
		@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='closeButton']")
		public MobileElement closeButton;
		
//		@iOSFindBy(id="logOutButton")
		@iOSXCUITFindBy(iOSNsPredicate= "name == 'logOutButton'")
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Logout']")
		public MobileElement logoutButton;
		
		@iOSXCUITFindBy(id = "dataSetArrow")
//		@iOSXCUITFindBy(accessibility = "dataSetArrow")
//		@iOSFindBy(xpath="//XCUIElementTypeImage[@name=\"dataSetArrow\"]")
		@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='dataSetArrow']")
		public MobileElement datasetDDButton;
		
		@AndroidFindBy(xpath="//*[@text='Done']")
//		@iOSFindBy(xpath="//*[@name='doneButton']")
		@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'doneButton'`]")
		public MobileElement btnDone;
		
//		@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Accounts\"]")
		@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == 'Accounts'`]")
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Accounts']")
		public MobileElement accountTxt;
		
//		@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Passcode & Fingerprint\"]")
		@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == 'Passcode'`]")
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Passcode']")
		public MobileElement PasscodeTxt;
		
//		@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Passcode & Fingerprint\"]")
		@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name == 'Passcode'`]")
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Passcode']")
		public MobileElement PasscodeHeaderTxt;
		
//		@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Manage Alerts\"]")
		@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == 'Manage Alerts'`]")
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Manage Alerts']")
		public MobileElement ManageAlertsTxt;
		
//		@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Manage Alerts\"]")
		@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Manage Alerts'`]")
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Manage Alerts']")
		public MobileElement ManageAlertsHeaderTxt;
		
//		@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Help & Legal\"]")
		@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == 'Help & Legal'`]")
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Help & Legal']")
		public MobileElement HelpLegalTxt;
		
//		@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Help & Legal\"]")
		@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Help & Legal'`]")
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Help & Legal']")
		public MobileElement HelpLegalHeaderTxt;
		
//		@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Feedback\"]")
		@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Feedback'`]")
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Feedback']")
		public MobileElement FeedbackTxt;
		
//		@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Use Quicken Passcode\"]")
		@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Use Quicken Passcode'`]")
		@AndroidFindBy(xpath="//android.widget.Switch/../android.widget.TextView[@text=\"Use Quicken Passcode\"]")
		public MobileElement quickenPasscodeTxt;
		
//		@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Use Touch ID\"]")
		@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Use Touch ID'`]")
		@AndroidFindBy(xpath="TBD")
		public MobileElement useTouchIDTxt;
		
		
		public MobileElement getTextView(String ele) {
			Helper h = new Helper();
			if (h.getEngine().equalsIgnoreCase("ios")) {
				try {
					MobileElement me =  (MobileElement) Engine.iosd.findElement(MobileBy.iOSClassChain("**/XCUIElementTypeStaticText[`name='"+ele+"'`]"));
					return me;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			} else {
				try {
					MobileElement me =  (MobileElement) Engine.ad.findElement(By.xpath("//android.widget.TextView[@text='"+ele+"']"));	
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
				 return Engine.ad.findElementByXPath(xPathForControl).isDisplayed();
			}
			catch(Exception E){
				return false;
			}
			
		}
		else {
			try{
//				System.out.println("watch outttt...");
//				System.out.println(Engine.iosd.findElementByXPath(xPathForIOS).isDisplayed());
				 return Engine.iosd.findElementByXPath(xPathForIOS).isDisplayed();
			}
			catch(Exception E){
				return false;
			}
		}
	}
	
	public boolean verifyCloudAccountName(String cloudName){
		 
		String xPathForControl = "//android.widget.TextView[@text='"+cloudName+"']";
		String xPathForIOS = "//XCUIElementTypeStaticText[@name='"+cloudName+"']";
		
		//String xPathForControl = "//android.widget.TextView[starts-with(@text,'Cloud Account Name:')]";
		
		Helper h = new Helper();
		
		if (h.getEngine().equals("android")) {
			try{
//				System.out.println("*******");
//				System.out.println(Engine.ad.findElementByXPath(xPathForControl).getText());
//				System.out.println("*******");
				return Engine.ad.findElementByXPath(xPathForControl).isDisplayed();
			}
			catch(Exception E){
				return false;
			}
			
		}
		else {
//			System.out.println("*******");
//			System.out.println(Engine.iosd.findElementByXPath(xPathForIOS).getText());
//			System.out.println("*******");
			return Engine.iosd.findElementByXPath(xPathForIOS).isDisplayed();	
		}
	}
	
	public MobileElement getAccountElement (String accountName) {
		
		String xpath_Android = "//android.widget.TextView[@text='"+accountName+"']";
		String xpath_IOS = "**/XCUIElementTypeStaticText[`name=='"+accountName+"'`]" ;
		
		Helper h = new Helper();
		
		if (h.getEngine().equals("android")){
			try{
				MobileElement me = (MobileElement) Engine.ad.findElementByXPath(xpath_Android);
				return me;
			}
			catch(Exception E){
				return null;
			}
			
		}
		else {
			try{
				MobileElement me = (MobileElement) Engine.iosd.findElement(MobileBy.iOSClassChain(xpath_IOS));
				return (MobileElement) Engine.iosd.findElement(MobileBy.iOSClassChain(xpath_IOS));
			}
			catch(Exception E){
				Commentary.log(LogStatus.INFO, E.getMessage());
				return null;
			}
	}

	}
	@SuppressWarnings("deprecation")
	public void selectBack (String bckButton) {
		Helper h = new Helper();
		if (h.getEngine().equalsIgnoreCase("android")){
			//MobileElement backButton_android = (MobileElement) Engine.ad.findElementByXPath("//android.widget.ImageButton[@index=0]");
			//Engine.ad.pressKeyCode(AndroidKeyCode.BACK);
			((AndroidDriver<MobileElement>) Engine.ad).pressKeyCode(AndroidKeyCode.BACK);
			//backButton_android.click();
		} else {
			MobileElement backButton_ios = (MobileElement) Engine.iosd.findElementByAccessibilityId(bckButton);
			backButton_ios.click();
		}
	}
	public void switchDataset (String datasetname) throws Exception {
		String xpath_Android = "//android.widget.TextView[@text='"+datasetname+"']";
		String classChain_xpath_IOS = "**/XCUIElementTypeOther[`name='"+datasetname+"'`][2]";
		
		Helper h = new Helper();
		if (h.getEngine().equalsIgnoreCase("ios")) {
			MobileElement me_ios = (MobileElement) Engine.iosd.findElementByIosClassChain(classChain_xpath_IOS);
			Verify.waitForObject(me_ios, 2);
			me_ios.click();
			Thread.sleep(500);
			btnDone.click();
		} else {
			MobileElement me_android = (MobileElement) Engine.ad.findElementByXPath(xpath_Android);
			Verify.waitForObject(me_android, 2);
			me_android.click();
			Thread.sleep(500);
			btnDone.click();
		}
		Thread.sleep(3000);
	}
}
