package dugout;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import referee.Verify;
import support.Engine;

public class MFApage {
	
	public MFApage () {
		try {
			
			PageFactory.initElements(new AppiumFieldDecorator(Engine.getDriver()),this);
		} catch (Exception e) {
			
			e.printStackTrace();
		}	
	}
	
	@AndroidFindBy(xpath="//*[@content-desc='Email or Quicken ID']") 
	@iOSXCUITFindBy(xpath="//XCUIElementTypeTextField") // RN updated
	public WebElement inputMFA;
	
	//XCUIElementTypeStaticText[@name="We sent you a code to verify your identity. This helps keep your account safe."]
	@AndroidFindBy(xpath="//*[@content-desc='Email or Quicken ID']") 
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name='We sent you a code to verify your identity. This helps keep your account safe.']") // RN updated
	public WebElement txtWeSentYouACode;
	
	@AndroidFindBy(xpath="//*[@content-desc='Email or Quicken ID']") 
	@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[@name='SUBMIT']") // RN updated
	public WebElement btnSubmit;
	
	public boolean handleMFA() throws InterruptedException{
		
		if (! Verify.objExists(txtWeSentYouACode))
			return false;
		
		inputMFA.sendKeys("");
		btnSubmit.click();
		Thread.sleep(3000);
		return true;
		
		
	}

}
