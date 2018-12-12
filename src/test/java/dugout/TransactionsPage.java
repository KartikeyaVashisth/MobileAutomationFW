package dugout;

import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import support.Engine;
import support.Helper;
import support.Recovery;

public class TransactionsPage {
	ExtentTest quickenTest = Recovery.quickenTest;
	
	public TransactionsPage () {
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
	
	// coded for android. IOS behavior not clearly known
	public Boolean verifyAccount(String acctName) throws Exception{
			
			String xPathForAcct = "//android.view.ViewGroup/android.widget.TextView[normalize-space(@text)='"+acctName+"']";
			String xPathForAcct_IOS ="//XCUIElementTypeNavigationBar/XCUIElementTypeOther[normalize-space(@name)='"+acctName+"']";
			
			Helper h = new Helper();
			
			if (h.getEngine().equals("android")){
				try{
					Engine.ad.findElementByXPath(xPathForAcct).isDisplayed();
					return true;
				}
				catch(Exception E){
					return false;
				}
				
			}
			else {
				try{
					Engine.iosd.findElementByXPath(xPathForAcct_IOS).isDisplayed();
					return true;
				}
				catch(Exception E){
					return false;
				}
				
				
			}
			
			
			/*try{
				 Engine.ad.findElementByXPath(xPathForAcct).isDisplayed();
				 return true;
			}
			catch(Exception E){
				return false;
			}*/
			
		}
	
	public void navigateBackToDashboard() throws Exception{
		
		backButton.click();
		Thread.sleep(5000);
		
		AllAccountsPage ap = new AllAccountsPage();
		ap.navigateBackToDashboard();
		
	}
	
	@iOSFindBy(xpath="//XCUIElementTypeButton[@name='Banking & Credit']")
	@AndroidFindBy(xpath="//*[@class='android.widget.ImageButton']")
	public MobileElement backButton;
	
	

}
