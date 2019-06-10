package dugout;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import referee.Commentary;
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
	
	
	
	//@AndroidFindBy(xpath="//*[@content-desc='Quicken ID or Email']") // RN updated
	@AndroidFindBy(xpath="//*[@content-desc='Quicken ID (email address)']") 
	@iOSFindBy(xpath="//*[@name='Quicken ID (email address)']")
	public MobileElement emailID;
	
	
	//@AndroidFindBy(xpath="//*[@content-desc='Quicken ID or Email']") // RN updated
	@AndroidFindBy(xpath="//android.widget.EditText[@password='false']")
	@iOSFindBy(xpath="//XCUIElementTypeTextField")
	public MobileElement userName;
	
	@AndroidFindBy(xpath="//*[@content-desc ='Password']")
	//@AndroidFindBy(xpath="//*[@text='Password']")
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name='Password']")
	public MobileElement lblPassword;
	
	//@AndroidFindBy(xpath="//input[@id='ius-password']")
	@AndroidFindBy(xpath="//android.widget.EditText[@password='true']")
	@iOSFindBy(xpath="//XCUIElementTypeSecureTextField")
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
			Commentary.log(LogStatus.ERROR, "SignIn widget not loaded");
			//quickenTest.log(LogStatus.ERROR,"SignIn widget not loaded");
		//System.out.println("SignInWidget appeared");
		Commentary.log(LogStatus.INFO, "SignInWidget appeared");
		
		
		/*Thread.sleep(75000);
		System.out.println("tttttt");
		if (!Verify.objExists(emailID))
			Thread.sleep(35000);*/
		
		if (!Verify.objExists(emailID))
			quickenTest.log(LogStatus.ERROR,"emailID not identified");
		
		emailID.click();
		Thread.sleep(1000);
		userName.clear();
		
		
		
			
		
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
		
		Commentary.log(LogStatus.INFO, "SignInWidget appeared");
		
		emailID.click();
		Thread.sleep(1000);
		userName.clear();
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
		
		
		selectDataset(dataset);
		
		Verify.waitForObjectToDisappear(refreshSpinnerIcon, 30)	;
			
		return true;
	}
	
	/* the below method doesn't scroll for the dataset
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
		
		
	}*/
	
	public boolean selectDataset(String bundle) throws Exception{
		ExtentTest quickenTest = Recovery.quickenTest;
		String xpath;
		Integer iCount;
		MobileElement txtDataSet;
		
		if (! Verify.waitForObject(txtYouHaveSynced, 4))
		{
			quickenTest.log(LogStatus.ERROR,"Dataset Selection Screen Did not appear");
			return false;
		}
		
		// verify whether dataset screen appeared or not
		
		quickenTest.log(LogStatus.INFO,"Dataset selection screen appeared");
		
			if (support.getEngine().equals("android")){
				
				Engine.ad.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ bundle + "\").instance(0))"));
				xpath = "//android.view.ViewGroup[@content-desc='"+bundle+"']";
				txtDataSet = (MobileElement) Engine.ad.findElement(By.xpath(xpath));
				Thread.sleep(1000);
				
				Commentary.log(LogStatus.INFO, Engine.ad.findElement(By.xpath(xpath)).isDisplayed()+" "+bundle);
				System.out.println(Engine.ad.findElement(By.xpath(xpath)).isDisplayed());
				Engine.ad.findElement(By.xpath(xpath)).click();
				Commentary.log(LogStatus.INFO, "Clicked on Dataset name "+bundle);
				
				btnDone.click();
				Thread.sleep(10000);
				OverviewPage o = new OverviewPage();
				Verify.waitForObjectToDisappear(refreshSpinnerIcon, 30)	;
				
				if (! Verify.waitForObject(o.hambergerIcon, 8))
					quickenTest.log(LogStatus.ERROR,"Overview Screen Did not appear after dataset selection.");
				
				return true;
			}
		
			else{
				
				MobileElement me = (MobileElement) Engine.iosd.findElement(By.xpath("//XCUIElementTypeScrollView"));
				String me_id = me.getId();
				HashMap<String, String> scrollObject = new HashMap<String, String>();
				scrollObject.put("element", me_id);
				scrollObject.put("predicateString", "label == '"+bundle+"'");
				Engine.iosd.executeScript("mobile:scroll", scrollObject);  // scroll to the target element
				Thread.sleep(1000);
				
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
