package dugout;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import referee.Verify;
import support.Engine;
import support.Helper;
import support.Recovery;

public class SignInPage {
	ExtentTest quickenTest = Recovery.quickenTest;
	Helper support = new Helper();
	
	
	public SignInPage () throws Exception{
		//PageFactory.initElements(Engine.getDriver(),this);
		PageFactory.initElements(new AppiumFieldDecorator(Engine.getDriver()),this);
	}
	
	
	
	@AndroidFindBy(xpath="//input[@id='ius-userid']")
	@iOSFindBy(xpath="//input[@id='ius-userid']")
	public MobileElement emailID;
	
	@AndroidFindBy(xpath="//input[@id='ius-password']")
	@iOSFindBy(xpath="//input[@id='ius-password']")
	public MobileElement password;
	
	@AndroidFindBy(xpath="//button[@id='ius-sign-in-submit-btn']")
	@iOSFindBy(xpath="//button[@id='ius-sign-in-submit-btn']")
	public MobileElement btnSignIn;
	
	public boolean signIn() throws Exception{
		
		WelcomePage w = new WelcomePage(Engine.getDriver());
		System.out.println("lllllllllllllllllllllllll");
	
		if (Verify.objExists(w.xpath_btnWelcomeSignIn) )
			w.xpath_btnWelcomeSignIn.click();
		
		if (Verify.objExists(emailID)){
			quickenTest.log(LogStatus.ERROR,"Failed to SignIn as SignIn page did not appear");
			return false;
		}
		
		
		emailID.sendKeys(support.getUsername());
		password.sendKeys(support.getPassword());
		btnSignIn.click();
		quickenTest.log(LogStatus.INFO,"Clicked on SignIn button");
		
		return true;
	}

}
