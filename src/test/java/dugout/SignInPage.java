package dugout;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
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
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
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
		try {
			
			PageFactory.initElements(new AppiumFieldDecorator(Engine.getDriver()),this);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	//@AndroidFindBy(xpath="//*[@content-desc='Quicken ID or Email']") // RN updated
	//@AndroidFindBy(xpath="//*[@content-desc='Quicken ID (email address)']") 
	@AndroidFindBy(xpath="//*[@content-desc='Quicken ID (email address)' or @text='Quicken ID (email address)']") 
	//@iOSFindBy(xpath="//*[@name='Quicken ID (email address)']")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name=='Quicken ID (email address)'`]")
	public MobileElement emailID;


	//@AndroidFindBy(xpath="//*[@content-desc='Quicken ID or Email']") // RN updated
	@AndroidFindBy(xpath="//android.widget.EditText[@password='false']")
	@iOSXCUITFindBy(className = "XCUIElementTypeTextField")
	public MobileElement userName;

	@AndroidFindBy(xpath="//*[@content-desc ='Password' or @text ='Password']")
	//@AndroidFindBy(xpath="//*[@text='Password']")
	//@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name='Password']")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[$name=='Password'$]")
	public MobileElement lblPassword;

	@AndroidFindBy(xpath="//android.widget.EditText[2]")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeSecureTextField")
	public MobileElement password;

	@AndroidFindBy(xpath="//*[@content-desc='SIGN IN' or @text='SIGN IN']")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[$name=='SIGN IN'$]")
	public MobileElement btnSignIn;
	
	@AndroidFindBy(xpath="//android.view.View[@text='Do you need help signing in?']/../android.view.View[@text='Invalid Quicken ID or password.']")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Invalid Quicken ID or password.'`]")
	public MobileElement invalidCredentialsText;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id='username']")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[$name='Quicken ID (email address)'$]/**/XCUIElementTypeTextField")
	public MobileElement usernameTextField;

	@AndroidFindBy(xpath="//*[@text='Done']")
	//	@iOSFindBy(xpath="//*[@name='doneButton']")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'doneButton'`]")
	public MobileElement btnDone;

	@AndroidFindBy(xpath="//*[contains(@text,'synced more than one')]")
	@iOSFindBy(xpath="//*[contains(@name,'synced more than one')]")
	public MobileElement txtYouHaveSynced;

	@iOSFindBy(xpath="//*[contains(@name,'synced more than one')]")
	@AndroidFindBy(xpath="//android.widget.ProgressBar")
	public MobileElement refreshSpinnerIcon;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='dataSetArrow'`]")
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc=\"dataSetArrow\"]")
	public MobileElement dataSetArrow;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='navigationMenu'`]")
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"navigationMenu\"]//*[@class='android.widget.ImageView']")
	public MobileElement navigationMenu;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains \"navigationMenu\"`]/**/XCUIElementTypeStaticText[3]")
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"navigationMenu\"]/../android.widget.TextView")
	public MobileElement datasetNameOnDashboard;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Cancel Icon'`]")
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc=\"Cancel Icon\"]")
	public MobileElement cancelIcon;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeScrollView[1]")
	@AndroidFindBy(xpath="//android.widget.ScrollView//android.view.ViewGroup/android.widget.ImageView")
	public MobileElement datasetSelectionListScreen;
	
	public boolean signIn() throws Exception{

		ExtentTest quickenTest = Recovery.quickenTest;
		WelcomePage w = new WelcomePage();

		Thread.sleep(5000);
		w.xpath_btnWelcomeSignIn.click();
		//System.out.println("Waiting for SignInWidget");
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

//		if (!Verify.objExists(emailID))
//			quickenTest.log(LogStatus.ERROR,"emailID not identified");

		emailID.click();
		Thread.sleep(1000);
		userName.clear();

		emailID.sendKeys(support.getUsername());
		lblPassword.click();
		Thread.sleep(1000);
		this.password.sendKeys(support.getPassword());
		if (support.getEngine().equals("android"))
			Engine.getDriver().hideKeyboard();
		Thread.sleep(1000);

		btnSignIn.click();
		Thread.sleep(2000);

		selectDataset(support.getDatasetName());

		return true;
	}

	public boolean signIn(String username, String password, String dataset) throws Exception{

		ExtentTest quickenTest = Recovery.quickenTest;
		WelcomePage w = new WelcomePage();

		Thread.sleep(3000);
		w.xpath_btnWelcomeSignIn.click();

//		if (! Verify.waitForObject(emailID, 3))
//			quickenTest.log(LogStatus.ERROR,"SignIn widget not loaded");

		//Commentary.log(LogStatus.INFO, "SignInWidget appeared");

		emailID.click();
		Thread.sleep(1000);
		userName.clear();
		Thread.sleep(1000);
		userName.clear();
		userName.sendKeys(username);//Runtime.getRuntime().exec("adb shell input text "+username+"");
		lblPassword.click();
		Thread.sleep(1000);

		this.password.sendKeys(password); //this keyword used to differentiate between local and global variable.
		//lbl.sendKeys(password);
		if (support.getEngine().equals("android"))
			Engine.getDriver().hideKeyboard();
		Thread.sleep(1000);

		btnSignIn.click();
		//quickenTest.log(LogStatus.INFO,"Clicked on SignIn button");
		Thread.sleep(5000);

		selectDataset(dataset);

		Verify.waitForObjectToDisappear(refreshSpinnerIcon, 2)	;

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
				System.out.println(Engine.getDriver().findElement(By.xpath(xpath)).isDisplayed());
				Engine.getDriver().findElement(By.xpath(xpath)).click();

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
				Engine.getDriver().findElement(By.xpath(xpath)).click();
				btnDone.click();
				Thread.sleep(5000);
				OverviewPage o = new OverviewPage();
				Verify.waitForObjectToDisappear(refreshSpinnerIcon, 30)	;
				if (! Verify.waitForObject(o.hambergerIcon, 8))
					quickenTest.log(LogStatus.ERROR,"Overview Screen Did not appear after dataset selection.");
				return true;

			}			
	}*/

	//	OLD Method for Signing In before Recently used Dataset.
	/*	public boolean selectDataset(String bundle) throws Exception{
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

				Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ bundle + "\").instance(0))"));
				xpath = "//android.view.ViewGroup[@content-desc='"+bundle+"']";
				txtDataSet = (MobileElement) Engine.getDriver().findElement(By.xpath(xpath));
				Thread.sleep(1000);

				Commentary.log(LogStatus.INFO, Engine.getDriver().findElement(By.xpath(xpath)).isDisplayed()+" "+bundle); //true ProjectedBalances will be printed.
				System.out.println(Engine.getDriver().findElement(By.xpath(xpath)).isDisplayed()); //true
				Engine.getDriver().findElement(By.xpath(xpath)).click();
				Commentary.log(LogStatus.INFO, "Clicked on Dataset name "+bundle);

				btnDone.click();
				Thread.sleep(10000);
				OverviewPage o = new OverviewPage();
				Verify.waitForObjectToDisappear(refreshSpinnerIcon, 30)	;

				if (! Verify.waitForObject(o.hambergerIcon, 3))
					quickenTest.log(LogStatus.ERROR,"Overview Screen did not appear after dataset selection.");

				return true;
			}

			else{
				MobileElement me = (MobileElement) Engine.getDriver().findElement(By.xpath("//XCUIElementTypeScrollView"));
				String me_id = me.getId();
				HashMap<String, String> scrollObject = new HashMap<String, String>();
				scrollObject.put("element", me_id);
				scrollObject.put("predicateString", "label == '"+bundle+"'");
				Engine.getDriver().executeScript("mobile:scroll", scrollObject);  // scroll to the target element
				Thread.sleep(1000);

				xpath = "//XCUIElementTypeOther[@name='"+bundle+"']";
				Engine.getDriver().findElement(By.xpath(xpath)).click();
				btnDone.click();
				Thread.sleep(5000);
				OverviewPage o = new OverviewPage();
				Verify.waitForObjectToDisappear(refreshSpinnerIcon, 2)	;
				if (! Verify.waitForObject(o.hambergerIcon, 2))
					quickenTest.log(LogStatus.ERROR,"Overview Screen Did not appear after dataset selection.");
				return true;	
			}		
	} */

	public boolean selectDataset(String bundle) throws Exception {

		ExtentTest quickenTest = Recovery.quickenTest;

		if (support.getEngine().equals("android")) {
			OverviewPage op = new OverviewPage();
			Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
			Thread.sleep(6000);
			
			if(Verify.objExists(cancelIcon)) {
				cancelIcon.click();
			}
			
			Verify.waitForObject(datasetNameOnDashboard, 2);
//			String datasetNameOverviewPage = Engine.getDriver().findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"navigationMenu\"]/../android.widget.TextView")).getText();
			String datasetNameOverviewPage = this.datasetNameOnDashboard.getText();
			
			if(Verify.objExists(cancelIcon)) {
				cancelIcon.click();
			}

			if(!datasetNameOverviewPage.equals(bundle)) {
				Verify.waitForObject(navigationMenu, 2);
				navigationMenu.click();
				
				if(Verify.objExists(cancelIcon)) {
					cancelIcon.click();
				}
				
				Verify.waitForObject(dataSetArrow, 2);
				dataSetArrow.click();
				Thread.sleep(5000);
				Verify.waitForObject(this.datasetSelectionListScreen, 2);
				MobileElement xpath_Android = (MobileElement)Engine.getDriver().findElement(By.xpath("//android.widget.TextView[@text='"+bundle+"']"));
				xpath_Android.click();
				Thread.sleep(500);
				Verify.waitForObject(this.btnDone, 1);
				btnDone.click();

				Verify.waitForObjectToDisappear(refreshSpinnerIcon, 2);

//				if (! Verify.waitForObject(op.hambergerIcon, 2))
//					quickenTest.log(LogStatus.ERROR,"Overview Screen did not appear after dataset selection.");
			}
			return true;
		} 

		else {
			OverviewPage op = new OverviewPage();
			Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
			Thread.sleep(5000);
			Verify.waitForObject(datasetNameOnDashboard, 2);
//			String datasetNameOverviewPage = Engine.getDriver().findElementByIosClassChain("**/XCUIElementTypeOther[`name contains \"navigationMenu\"`]/**/XCUIElementTypeStaticText[3]").getText();
			String datasetNameOverviewPage = this.datasetNameOnDashboard.getText();
			
			if(Verify.objExists(cancelIcon)) {
				cancelIcon.click();
			}

			if(!datasetNameOverviewPage.equals(bundle)) {
				Verify.waitForObject(navigationMenu, 2);
				navigationMenu.click();
				
				if(Verify.objExists(cancelIcon)) {
					cancelIcon.click();
				}
				
				Verify.waitForObject(dataSetArrow, 2);
				dataSetArrow.click();
				Thread.sleep(2000);
				Verify.waitForObject(this.datasetSelectionListScreen, 2);
				MobileElement xpath_ios = (MobileElement)Engine.getDriver().findElement(MobileBy.iOSClassChain("**/XCUIElementTypeOther[`name='"+bundle+"'`][2]"));
				xpath_ios.click();
				Thread.sleep(500);
				Verify.waitForObject(this.btnDone, 1);
				btnDone.click();

				Verify.waitForObjectToDisappear(refreshSpinnerIcon, 2);

//				if (! Verify.waitForObject(op.hambergerIcon, 2))
//					quickenTest.log(LogStatus.ERROR,"Overview Screen did not appear after dataset selection.");
			}
			return true;
		}

	}
}
