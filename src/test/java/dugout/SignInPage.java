package dugout;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumDriver;
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
	ExtentTest quickenTest;
	Helper support = new Helper();
	AndroidDriver ad;
	
	
	
	public SignInPage () {
		quickenTest = Recovery.quickenTest;
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
	
	
	
	@AndroidFindBy(xpath="//*[@content-desc='Quicken ID or Email']") // RN updated
	@iOSFindBy(xpath="//*[@name='Quicken ID or Email']")
	public MobileElement emailID;
	
	@AndroidFindBy(xpath="//*[@content-desc ='Password']")
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name='Password']")
	public MobileElement lblPassword;
	
	@AndroidFindBy(xpath="//input[@id='ius-password']")
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name='Password']")
	public MobileElement password;
	
	@AndroidFindBy(xpath="//*[@content-desc='SIGN IN']")
	@iOSFindBy(xpath="//*[@name='SIGN IN']")
	public MobileElement btnSignIn;
	
	@AndroidFindBy(xpath="//*[@text='DONE']")
	@iOSFindBy(xpath="//*[@name='doneButton']")
	public MobileElement btnDone;
	
	@AndroidFindBy(xpath="//*[contains(@text,'synced more than one')]")
	@iOSFindBy(xpath="//*[contains(@name,'synced more than one')]")
	public MobileElement txtYouHaveSynced;
	
	@iOSFindBy(xpath="//*[contains(@name,'synced more than one')]")
	@AndroidFindBy(xpath="//android.widget.ProgressBar")
	public MobileElement refreshSpinnerIcon;
	
	public boolean signIn() throws Exception{
		
		ExtentTest quickenTest = Recovery.quickenTest;
		WelcomePage w = new WelcomePage();
		
		Thread.sleep(5000);
		w.xpath_btnWelcomeSignIn.click();
		System.out.println("Waiting for SignInWidget");
		//((JavascriptExecutor)Engine.ad).executeScript("sauce: break"); 
		
		if (! Verify.waitForObject(emailID, 8))
			quickenTest.log(LogStatus.ERROR,"SignIn widget not loaded");
		System.out.println("SignInWidget appeared");
		
		/*Thread.sleep(75000);
		System.out.println("tttttt");
		if (!Verify.objExists(emailID))
			Thread.sleep(35000);*/
		
		if (!Verify.objExists(emailID))
			quickenTest.log(LogStatus.ERROR,"emailID not identified");
			
		
		emailID.sendKeys(support.getUsername());
		lblPassword.click();
		Thread.sleep(1000);
		lblPassword.sendKeys(support.getPassword());
		if (support.getEngine().equals("android"))
			Engine.ad.hideKeyboard();
		Thread.sleep(1000);
		
		btnSignIn.click();
		quickenTest.log(LogStatus.INFO,"Clicked on SignIn button");
		Thread.sleep(2000);
		
		selectDataset(support.getDatasetName());
			
		return true;
	}
	
	public boolean signIn(String username, String password, String dataset) throws Exception{
		
		ExtentTest quickenTest = Recovery.quickenTest;
		WelcomePage w = new WelcomePage();
		
		Thread.sleep(5000);
		w.xpath_btnWelcomeSignIn.click();
		
		if (! Verify.waitForObject(emailID, 8))
			quickenTest.log(LogStatus.ERROR,"SignIn widget not loaded");
		
		emailID.sendKeys(Keys.CONTROL+"a");
		emailID.sendKeys(Keys.DELETE);
		emailID.sendKeys(username);
		lblPassword.click();
		Thread.sleep(1000);
		
		lblPassword.sendKeys(password);
		if (support.getEngine().equals("android"))
			Engine.ad.hideKeyboard();
		Thread.sleep(1000);
		
		btnSignIn.click();
		quickenTest.log(LogStatus.INFO,"Clicked on SignIn button");
		Thread.sleep(5000);
		
		if (!dataset.equals(""))
			selectDataset(dataset);
		
		Verify.waitForObjectToDisappear(refreshSpinnerIcon, 30)	;
			
		return true;
	}
	
	public boolean selectDataset(String bundle) throws Exception{
		ExtentTest quickenTest = Recovery.quickenTest;
		String xpath;
		
		if (! Verify.waitForObject(txtYouHaveSynced, 4))
		{
			quickenTest.log(LogStatus.ERROR,"Dataset Selection Screen Did not appear");
			return false;
		}
		
		// verify whether dataset screen appeared or not
		
		quickenTest.log(LogStatus.INFO,"Dataset selection screen appeared");
		
			if (support.getEngine().equals("android")){
				xpath = "//android.view.ViewGroup[@content-desc='"+bundle+"']";
				System.out.println(Engine.ad.findElement(By.xpath(xpath)).isDisplayed());
				Engine.ad.findElement(By.xpath(xpath)).click();
				
				btnDone.click();
				Thread.sleep(10000);
				OverviewPage o = new OverviewPage();
				Verify.waitForObjectToDisappear(refreshSpinnerIcon, 30)	;
				
				if (! Verify.waitForObject(o.hambergerIcon, 8))
					quickenTest.log(LogStatus.ERROR,"Overview Screen Did not appear after dataset selection.");
				
				return true;
			}
		
			else{
				xpath = "//XCUIElementTypeOther[@name='"+bundle+"']";
				Engine.iosd.findElement(By.xpath(xpath)).click();
				btnDone.click();
				Thread.sleep(5000);
				OverviewPage o = new OverviewPage();
				Verify.waitForObjectToDisappear(refreshSpinnerIcon, 30)	;
				if (! Verify.waitForObject(o.hambergerIcon, 8))
					quickenTest.log(LogStatus.ERROR,"Overview Screen Did not appear after dataset selection.");
				return true;
				
			}
		
		
		
		
		
		
			
		
		
	}

}
