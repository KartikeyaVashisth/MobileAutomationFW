package dugout;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import referee.Verify;
import support.Engine;
import support.Helper;


public class WelcomePage {
	
	/*public WelcomePage () {
		
		try {
			PageFactory.initElements(new AppiumFieldDecorator(Engine.getDriver()),this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	public WelcomePage () {
		//PageFactory.initElements(Engine.getDriver(),this);
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
	
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Sign In']")
	@AndroidFindBy(xpath="//*[@text='Sign In']")
	public MobileElement xpath_btnWelcomeSignIn;
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='testEnvironment']") 
	@AndroidFindBy(xpath="//*[@content-desc='testEnvironment']")
	public MobileElement xpath_Environment;
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Stage']") 
	@AndroidFindBy(xpath="//*[@text='Stage']")
	public MobileElement xpath_chkboxStageEnvironment;
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Done']") 
	public MobileElement linkAppConfigDone;
	
	
	
	public void setEnvironment(String preprod) throws InterruptedException{
		
		Thread.sleep(5000);
		
		this.xpath_Environment.click();
		
		if (preprod.equals("stage")){
			this.xpath_chkboxStageEnvironment.click();
			
			if (Verify.objExists(this.linkAppConfigDone))
				this.linkAppConfigDone.click();
			
		}
		else
			System.out.println(preprod+" is not been handled in the appium API setEnvironment.");
		
		// confirm if the environment dialog got disappeared or not
		Thread.sleep(3000);
		
		if (! Verify.objExists(this.xpath_btnWelcomeSignIn))
			Engine.ad.navigate().back();
		
		System.out.println("Environment set to "+preprod);
		Thread.sleep(500);	
		
		
	}
	
	
	
	
	

}
