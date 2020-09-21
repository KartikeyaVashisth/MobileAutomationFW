package dugout;

import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import referee.Commentary;
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
			e.printStackTrace();
		}	
	}
	
	//@iOSFindBy(xpath="//*[normalize-space(@name)='Sign In']")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Sign In'`][2]")
	@AndroidFindBy(xpath="//*[@text='Sign In']")
	public MobileElement xpath_btnWelcomeSignIn;
	
	//@iOSFindBy(xpath="//*[normalize-space(@name)='testEnvironment']")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='testEnvironment'`]")
	@AndroidFindBy(xpath="//*[@content-desc='testEnvironment']")
	public MobileElement xpath_Environment;
	
	//@iOSFindBy(xpath="//*[normalize-space(@name)='Stage']")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Stage'`][2]")
	@AndroidFindBy(xpath="//*[@text='Stage']")
	public MobileElement xpath_chkboxStageEnvironment;
	
	//@iOSFindBy(xpath="//*[normalize-space(@name)='Production']")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Production'`][2]")
	@AndroidFindBy(xpath="//*[@text='Production']")
	public MobileElement xpath_chkboxProductionEnvironment;
	
	//@iOSFindBy(xpath="//*[normalize-space(@name)='Done']")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name='Done'`]")
	public MobileElement linkAppConfigDone;
	
	public void setEnvironment(String preprod) throws Exception{
	
	
	public void setEnvironment(String preprod) throws InterruptedException{
		
		Commentary.log(LogStatus.INFO, "Setting Environment to "+preprod);
		
		if (preprod.equals("prod"))
			return;
		
		this.xpath_Environment.click();

		if (preprod.equals("stage")) {
			this.xpath_chkboxStageEnvironment.click();
		}
		
		if (preprod.equals("stage")){
			this.xpath_chkboxStageEnvironment.click();
			
			Helper h = new Helper();
			
			if (h.getEngine().equals("ios"))
				this.linkAppConfigDone.click();
			
		}
		else
			Commentary.log(LogStatus.INFO, preprod+" is not been handled in the appium API setEnvironment.");
			//System.out.println(preprod+" is not been handled in the appium API setEnvironment.");

		Helper h = new Helper();
		if (h.getEngine().equals("ios"))
			this.linkAppConfigDone.click();
		
		// confirm if the environment dialog got disappeared or not
		Thread.sleep(1000);
		
		/*
		if (! Verify.objExists(this.xpath_btnWelcomeSignIn))
			Engine.ad.navigate().back();
		*/
		System.out.println("Environment set to "+preprod);
		Thread.sleep(500);	
	}
}
