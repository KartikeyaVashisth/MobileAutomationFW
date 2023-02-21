package dugout;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.List;

import org.apache.tools.ant.taskdefs.Exit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import referee.Commentary;
import referee.Verify;
import support.Engine;
import support.Helper;
import support.TransactionRecord;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

public class LongCategoryNamePage {
	
	public LongCategoryNamePage () {
		try {
			PageFactory.initElements(new AppiumFieldDecorator(Engine.getDriver()), this);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Show long category names'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Show long category names']")
    public MobileElement Showlongcategorynames;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeSwitch[2]")
	@AndroidFindBy(xpath="//android.widget.Switch[2]")
    public MobileElement LongCategorySwitch;
	

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='navigationMenu'`]")
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='navigationMenu']")
    public MobileElement hambergerIcon;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Settings Menu'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Settings']")
	public MobileElement settingsOption;

	
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeButton[`name contains 'back'`]")
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='backArrow']")
	public MobileElement backButton;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Category'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Category']/../android.widget.TextView[@index=2]")
	public MobileElement selectedCategory;
	
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Category'`][1]/**/XCUIElementTypeStaticText")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Category']/../android.widget.TextView[@index=2]")
	public MobileElement selectedSplitCategory;
	
	
    public void EnableLongCategory() throws Exception{
		
		hambergerIcon.click();
		Thread.sleep(2000);
		//this.scrollDownToSettings();
		Helper h = new Helper();
		
		settingsOption.click();
		Thread.sleep(1000);
		if (h.getEngine().equals("android")){
		    if(LongCategorySwitch.getAttribute("checked").equals("false")) {
		    	LongCategorySwitch.click();
		    }
		}
		else {
			if(LongCategorySwitch.getAttribute("value").equals("0")) {
				LongCategorySwitch.click();
		    }
		}
		
		
		this.backButton.click();	
		Thread.sleep(1000);
	 }
    

    public void DisableLongCategory() throws Exception{
		
		hambergerIcon.click();
		Thread.sleep(2000);
		//this.scrollDownToSettings();
		Helper h = new Helper();
		
		settingsOption.click();
		Thread.sleep(1000);
		if (h.getEngine().equals("android")){
		    if(LongCategorySwitch.getAttribute("checked").equals("true")) {
		    	LongCategorySwitch.click();
		    }
		}
		else {
			if(LongCategorySwitch.getAttribute("value").equals("1")) {
				LongCategorySwitch.click();
		    }
		}
		this.backButton.click();
		
		Thread.sleep(1000);
	 }
    

    public void navigateToSettingMemu() throws Exception{
		
 		hambergerIcon.click();
 		Thread.sleep(2000);
 		//this.scrollDownToSettings();
 		
 		settingsOption.click();
 		Thread.sleep(1000);

 	  }
 	


    public void scrollDownToSettings () throws Exception{
	
	    Helper h = new Helper();
	
	    if (h.getEngine().equals("android")) {
		    Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Settings\").instance(0))"));
		    Thread.sleep(1000);
	     }
	
	    else {
		
		     HashMap<String, String> scrollObject = new HashMap<String, String>();
		     scrollObject.put("direction", "down");
		     scrollObject.put("toVisible", "not an empty string");
		     Engine.getDriver().executeScript("mobile:scroll", scrollObject);
		     Thread.sleep(1000);
		
	         }
        }
    
    
    public String getTransactionCategory() {

		return this.selectedCategory.getText();
	}
    
    
    public boolean VerifyTransactionCategory (String sCategory) {
    	
		SoftAssert sa = new SoftAssert();

		String sActual;
		sActual = this.getTransactionCategory();

		if (sActual.equals(sCategory)) {
			Commentary.log(LogStatus.INFO, "Category verified successfully ["+sCategory+"]");
			Commentary.log(LogStatus.PASS, "Pass:Showing long category name to the transactions");
			return true;
		}

		Commentary.log(sa, LogStatus.FAIL, "Category verification failed. Expected ["+sCategory+"], Actual ["+sActual+"]");
		Commentary.log(LogStatus.FAIL, "Fail: Not Showing long category name to the transactions");

		return false;
	}
    
    
   
     public void VerifySplitCategory (Integer index, String sCategory) throws Exception{	
    	 
 		Helper h = new Helper();
 	
 		if (h.getEngine().equals("android"))
 			VerifySplitCategory_android(index, sCategory);
 		   
 		else
 			VerifySplitCategory_ios(index, sCategory);
 	}
     

 	private boolean VerifySplitCategory_ios (Integer index, String sCategory) throws Exception{
 		
 		SoftAssert sa = new SoftAssert();
 		String cc = "**/XCUIElementTypeOther[`name BEGINSWITH 'Category:'`]["+index+"]/**/XCUIElementTypeStaticText";
 		String sActual;
		sActual = Engine.getDriver().findElement(MobileBy.iOSClassChain(cc)).getText();
		
		if (sActual.equals(sCategory)) {
			Commentary.log(LogStatus.INFO, "Category verified successfully ["+sCategory+"]");
			Commentary.log(LogStatus.PASS, "Pass:Showing long category name to the transactions");
			return true;
		}

		Commentary.log(sa, LogStatus.FAIL, "Category verification failed. Expected ["+sCategory+"], Actual ["+sActual+"]");
		Commentary.log(LogStatus.FAIL, "Fail: Not Showing long category name to the transactions");
        return false;
		
 	}
 	

 	private boolean VerifySplitCategory_android (Integer index, String sCategory) throws Exception{
 		
 		SoftAssert sa = new SoftAssert();
 		String xpath = "(//android.view.ViewGroup[contains(@content-desc,'Category:')])["+index+"]//android.widget.TextView";
 		String sActual;
		sActual = Engine.getDriver().findElement(By.xpath(xpath)).getText();
		
		if (sActual.equals(sCategory)) {
			Commentary.log(LogStatus.INFO, "Category verified successfully ["+sCategory+"]");
			Commentary.log(LogStatus.PASS, "Pass:Showing long category name to the transactions");
			return true;
		}

		Commentary.log(sa, LogStatus.FAIL, "Category verification failed. Expected ["+sCategory+"], Actual ["+sActual+"]");
		Commentary.log(LogStatus.FAIL, "Fail: Not Showing long category name to the transactions");
        return false;
 	}
    
    
    public boolean VerifydisablingofLongCategory (String sCategory) {
    	
  		SoftAssert sa = new SoftAssert();
  		String sActual;
  		sActual = this.getTransactionCategory();

  		if (sActual.equals(sCategory)) {
  			Commentary.log(LogStatus.INFO, "Category verified successfully ["+sCategory+"]");
  			Commentary.log(LogStatus.PASS, "Pass: Not Showing long category name to the transactions");
  			return true;
  		}

  		Commentary.log(sa, LogStatus.FAIL, "Category verification failed. Expected ["+sCategory+"], Actual ["+sActual+"]");
  		Commentary.log(LogStatus.FAIL, "Fail: showing long category name to the transactions");

  		return false;
  	}

	   
}
 
 
 
 
 
