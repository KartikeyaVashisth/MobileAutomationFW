package dugout;

import org.eclipse.jetty.util.annotation.ManagedObject;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Passcode & Fingerprint\"])[3]")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Passcode']")
	public MobileElement passcode;
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Help & Legal\"])[3]")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Help & Legal']")
	public MobileElement helpAndLegal;
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Accounts\"])")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Accounts']")
	public MobileElement accounts;
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Manage Alerts\"])[3]")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Manage Alerts']")
	public MobileElement manageAlerts;
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"logOutButton\"]")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Logout']")
	public MobileElement logout;
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"closeButton\"])[2]")
	@AndroidFindBy(xpath="//*[@content-desc='backButton']//*[@class='android.widget.ImageView']")
	public MobileElement close;
	
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
				System.out.println("watch outttt...");
				System.out.println(Engine.iosd.findElementByXPath(xPathForIOS).isDisplayed());
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
				System.out.println("*******");
				System.out.println(Engine.ad.findElementByXPath(xPathForControl).getText());
				System.out.println("*******");
				return Engine.ad.findElementByXPath(xPathForControl).isDisplayed();
				
			}
			catch(Exception E){
				return false;
			}
			
		}
		else {
			System.out.println("*******");
			System.out.println(Engine.iosd.findElementByXPath(xPathForIOS).getText());
			System.out.println("*******");
			return Engine.iosd.findElementByXPath(xPathForIOS).isDisplayed();
			
		}
		
		
	}

}
