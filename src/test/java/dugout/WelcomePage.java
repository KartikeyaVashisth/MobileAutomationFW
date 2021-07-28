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
			
			PageFactory.initElements(new AppiumFieldDecorator(Engine.getDriver()),this);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	}
	
	//@iOSFindBy(xpath="//*[normalize-space(@name)='Sign In']")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Sign In'`]")
	@AndroidFindBy(xpath="//*[@text='Sign In']")
	public MobileElement xpath_btnWelcomeSignIn;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='signupTutorialImage 0 See everything in one place Connect your banks to see transactions and balances for all your accounts.'`]")
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='signupTutorialImage 0']/../android.widget.TextView[@text='See everything in one place']/following-sibling::android.widget.TextView[@text='Connect your banks to see transactions and balances for all your accounts.']")
	public MobileElement welcomePageText;
			
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
	
//		if (preprod.equals("prod"))
//			return;
		Commentary.log(LogStatus.INFO, "Setting Environment to "+preprod);
		
		this.xpath_Environment.click();

		if (preprod.equals("stage")) {
			this.xpath_chkboxStageEnvironment.click();
		}
		
		else if (preprod.equals("prod")) {
			this.xpath_chkboxProductionEnvironment.click();
		}
		else
			Commentary.log(LogStatus.INFO, preprod+" is not been handled in the appium API setEnvironment.");
			//System.out.println(preprod+" is not been handled in the appium API setEnvironment.");

		Helper h = new Helper();
		if (h.getEngine().equals("ios"))
			this.linkAppConfigDone.click();
		
		// confirm if the environment dialog got disappeared or not
		Thread.sleep(1000);

		if (Verify.objExists(this.xpath_chkboxStageEnvironment))
			Engine.getDriver().navigate().back();

		System.out.println("Environment set to "+preprod);
		Commentary.log(LogStatus.INFO, "Environment set to "+preprod);
		Thread.sleep(500);	
	}
}
