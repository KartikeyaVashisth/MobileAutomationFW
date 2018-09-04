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
	
	
	@iOSFindBy(xpath="//*[@name='SIGN IN']")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Passcode']")
	public MobileElement passcode;
	
	@iOSFindBy(xpath="//*[@name='SIGN IN']")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Help & Legal']")
	public MobileElement helpAndLegal;
	
	@iOSFindBy(xpath="//*[@name='SIGN IN']")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Accounts']")
	public MobileElement accounts;
	
	@iOSFindBy(xpath="//*[@name='SIGN IN']")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Log Out']")
	public MobileElement logout;
	
	@iOSFindBy(xpath="//*[@name='SIGN IN']")
	@AndroidFindBy(xpath="//*[@content-desc='backButton']//*[@class='android.widget.ImageView']")
	public MobileElement close;
	
	public boolean verifyQuickenID(String quickenID){
		
		String xPathForControl = "//android.widget.TextView[normalize-space(@text)='"+quickenID+"']";
		
		try{
			 return Engine.ad.findElementByXPath(xPathForControl).isDisplayed();
		}
		catch(Exception E){
			return false;
		}
	}
	
	public boolean verifyCloudAccountName(String cloudName){
		 
		
		String xPathForControl = "//android.widget.TextView[@text='Cloud Account Name: "+cloudName+"']";
		
		//String xPathForControl = "//android.widget.TextView[starts-with(@text,'Cloud Account Name:')]";
		
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

}
