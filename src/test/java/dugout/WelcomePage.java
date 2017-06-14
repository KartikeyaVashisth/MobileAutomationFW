package dugout;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;


public class WelcomePage {
	
	public WelcomePage (MobileDriver<MobileElement> driver){
		PageFactory.initElements(driver,this);	
	}
	
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Sign In']")
	@AndroidFindBy(xpath="//*[@resource-id='com.quicken.qm2014:id/qk_welcome_skip']")
	MobileElement xpath_btnWelcomeSignIn;
	
	

}
