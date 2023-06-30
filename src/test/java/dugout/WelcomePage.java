package dugout;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import referee.Commentary;
import referee.Verify;
import support.Engine;
import support.Helper;

public class WelcomePage {

	public WelcomePage() {
		
		try {
			PageFactory.initElements(new AppiumFieldDecorator(Engine.getDriver()),this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Sign In'`]")
	@AndroidFindBy(xpath="//*[@text='Sign In']")
	public WebElement xpath_btnWelcomeSignIn;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='signupTutorialImage 0 See everything in one place Connect your banks to see transactions and balances for all your accounts.'`]")
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='signupTutorialImage 0']/../android.widget.TextView[@text='See everything in one place']/following-sibling::android.widget.TextView[@text='Connect your banks to see transactions and balances for all your accounts.']")
	public WebElement welcomePageText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='testEnvironment'`]")
	@AndroidFindBy(xpath="//*[@content-desc='testEnvironment']")
	public WebElement xpath_Environment;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Stage'`][2]")
	@AndroidFindBy(xpath="//*[@text='Stage']")
	public WebElement xpath_chkboxStageEnvironment;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Production'`][2]")
	@AndroidFindBy(xpath="//*[@text='Production']")
	public WebElement xpath_chkboxProductionEnvironment;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='done'`][-1]")
	public WebElement linkAppConfigDone;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name='Allow'`]")
	public WebElement allowButton;

	public void setEnvironment(String preprod) throws Exception{

		if(Verify.objExists(this.allowButton)) {
			this.allowButton.click();
			Thread.sleep(2000);
		}

		Commentary.log(LogStatus.INFO, "Setting Environment to "+preprod);

		Verify.waitForObject(this.xpath_Environment, 1);
		this.xpath_Environment.click();

		if (preprod.equals("stage")) {
			this.xpath_chkboxStageEnvironment.click();
		}

		else if (preprod.equals("prod")) {
			this.xpath_chkboxProductionEnvironment.click();
		}
		else
			Commentary.log(LogStatus.INFO, preprod+" is not been handled in the appium API setEnvironment.");

		Helper h = new Helper();
		if (h.getEngine().equals("ios"))
			this.linkAppConfigDone.click();

		Thread.sleep(1000);

		if (Verify.objExists(this.xpath_chkboxStageEnvironment))
			Engine.getDriver().navigate().back();

		Commentary.log(LogStatus.INFO, "Environment set to "+preprod);
		Thread.sleep(500);	
	}
}
