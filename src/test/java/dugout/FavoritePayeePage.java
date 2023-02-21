package dugout;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.PageFactory;

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

public class FavoritePayeePage {
	
	public FavoritePayeePage () {
		try {
			PageFactory.initElements(new AppiumFieldDecorator(Engine.getDriver()), this);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Display Favorite Payees'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Display Favorite Payees']")
    public MobileElement DisplayFavoritePayee;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeSwitch[3]")
	@AndroidFindBy(xpath="//android.widget.Switch[3]")
    public MobileElement FavoritePayeeSwitch;
	

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='navigationMenu'`]")
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='navigationMenu']")
    public MobileElement hambergerIcon;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Settings Menu'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Settings']")
	public MobileElement settingsOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='favoriteHeaderMoreAction'`]")
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='favoriteHeaderMoreAction']")
    public MobileElement MoreOptionInPayeeDrawer;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='moreAction'`]")
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='moreAction']")
    public MobileElement YelpInPayeeDrawer;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`label ='disableYelpPayeeView'`][3]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Turn off Yelp recommendations']")
    public MobileElement TurnOffYelp;

	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Favorite Payee'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Favorite Payee']")
    public MobileElement FavoritePayeeLabel;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`label ='hideFavoritePayeeView'`][3]")
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Hide favorites']")
    public MobileElement HideFavorite;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Favorite Payee'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Favorite Payee']")
    public MobileElement btnEnableFavoritePayee;

	//@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementStaticText[`name='Payee'`]")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label = 'Payee'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Payee']")
    public MobileElement payee;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='All Payees'`][1]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='All Payees']")
	public MobileElement allPayeesLabel;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Near by'`][1]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Near by']")
	public MobileElement yelpNearByLabel;
	
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name CONTAINS 'Turn off Favorite Payee recommendations?'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Turn off Favorite Payee recommendations?')]")
	public MobileElement HideFavoritePayeeConfMessage;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name='Yes'`]")
	@AndroidFindBy(xpath="//*[@text='YES']")
	public MobileElement buttonYES;
	
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name='No'`]")
	@AndroidFindBy(xpath="//*[@text='NO']")
	public MobileElement buttonNO;
	
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeButton[`name contains 'back'`]")
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='backArrow']")
	public MobileElement backButton;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name ='Remove'`]")
	@AndroidFindBy(xpath="//android.widget.Button[@text='REMOVE']")
	public MobileElement removeBtn;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name ='Cancel'`]")
	@AndroidFindBy(xpath="//android.widget.Button[@text='CANCEL']")
	public MobileElement cancelBtn;


	
    public void EnablefavoritPayee() throws Exception{
		
		hambergerIcon.click();
		Thread.sleep(2000);
		//this.scrollDownToSettings();
		Helper h = new Helper();
		
		settingsOption.click();
		Thread.sleep(1000);
		if (h.getEngine().equals("android")){
		    if(FavoritePayeeSwitch.getAttribute("checked").equals("false")) {
			   FavoritePayeeSwitch.click();
		    }
		}
		else {
			if(FavoritePayeeSwitch.getAttribute("value").equals("0")) {
				FavoritePayeeSwitch.click();
		    }
		}
		
		
		this.backButton.click();	
		Thread.sleep(1000);
	 }

    public void DisablefavoritPayee() throws Exception{
		
		hambergerIcon.click();
		Thread.sleep(2000);
		//this.scrollDownToSettings();
		Helper h = new Helper();
		
		settingsOption.click();
		Thread.sleep(1000);
		if (h.getEngine().equals("android")){
		    if(FavoritePayeeSwitch.getAttribute("checked").equals("true")) {
			   FavoritePayeeSwitch.click();
		    }
		}
		else {
			if(FavoritePayeeSwitch.getAttribute("value").equals("1")) {
				FavoritePayeeSwitch.click();
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

    public void HideFavoritePayeeFromPayeeDrawer() throws Exception{
    	
    	
    	TransactionDetailPage td = new TransactionDetailPage();
        OverviewPage op = new  OverviewPage();
    	
    	op.addTransaction.click();
    	
        td.enterAmount("10.00");
    	Thread.sleep(3000);
    	//Engine.getDriver().hideKeyboard();
    	this.payee.click();
    	this.MoreOptionInPayeeDrawer.click();
    	this.HideFavorite.click();
    	Thread.sleep(1000);
    	this.buttonYES.click();
    	Thread.sleep(3000);
 
    	
    }
    
  public void AddPayeeAsFavPayee(String payee) throws Exception{
 		
 		Helper h = new Helper();
 		
 		if (h.getEngine().equals("ios"))
 		  AddPayeeAsFavPayee_ios(payee);
 		else
 		  AddPayeeAsFavPayee_android(payee);
 		
 	}
    
   public void AddPayeeAsFavPayee_ios(String payee) throws Exception{
    	
    	MobileElement ele_ios = Engine.getDriver().findElementByXPath("//XCUIElementTypeStaticText[@name='"+payee+"']/..");
	
				JavascriptExecutor js = (JavascriptExecutor)Engine.getDriver();
				HashMap scrollObject = new HashMap();
				scrollObject.put("direction", "left"); 
				scrollObject.put("element", ele_ios); 
				js.executeScript("mobile: swipe", scrollObject);
				Thread.sleep(500);
				
		MobileElement fav_icon = Engine.getDriver().findElementByXPath("(//XCUIElementTypeStaticText[@name='"+payee+"']/../../../XCUIElementTypeOther/XCUIElementTypeOther)[2]");
			
			fav_icon.click();
			
		}
 
   public void AddPayeeAsFavPayee_android(String payee) throws Exception{
	    
   	
   
       MobileElement startElement = Engine.getDriver().findElementByXPath("//android.widget.TextView[@text='"+payee+"']/../..");
		
	   int startX = startElement.getLocation().getX() + (startElement.getSize().getWidth() / 2);
       int startY = startElement.getLocation().getY() + (startElement.getSize().getHeight() / 2);
       int endX = startElement.getLocation().getX() + (startElement.getSize().getWidth() / 3);
       int endY = startElement.getLocation().getY() + (startElement.getSize().getHeight() / 2);
       new TouchAction(Engine.getDriver())
           .press(point(startX,startY))
           .waitAction(waitOptions(ofMillis(1000)))
           .moveTo(point(endX, endY))
           .release().perform();
   	
 
		MobileElement fav_icon = Engine.getDriver().findElementByXPath("//android.widget.TextView[@text='"+payee+"']/../../..//android.widget.ImageView");
			
			fav_icon.click();
		  	//Thread.sleep(3000);
			
		}
   
   
   public void RemoveFavPayee(String payee) throws Exception{
		
		Helper h = new Helper();
		
		if (h.getEngine().equals("ios"))
			RemoveFavPayee_ios(payee);
		else
			RemoveFavPayee_android(payee);
		
	}

    
   
    public void RemoveFavPayee_ios(String payee) throws Exception{
    	
    	
    	MobileElement ele_ios = Engine.getDriver().findElementByXPath("//XCUIElementTypeStaticText[@name='"+payee+"']/..");
	
				JavascriptExecutor js = (JavascriptExecutor)Engine.getDriver();
				HashMap scrollObject = new HashMap();
				scrollObject.put("direction", "left"); 
				scrollObject.put("element", ele_ios); 
				js.executeScript("mobile: swipe", scrollObject);
				Thread.sleep(500);
				
		MobileElement fav_icon = Engine.getDriver().findElementByXPath("(//XCUIElementTypeStaticText[@name='"+payee+"']/../../../XCUIElementTypeOther/XCUIElementTypeOther)[2]");
			
			fav_icon.click();
			Thread.sleep(500);
			this.removeBtn.click();
			Thread.sleep(3000);
			
		}
    
 
 
   public void RemoveFavPayee_android(String payee) throws Exception{
 	
 
        MobileElement startElement = Engine.getDriver().findElementByXPath("//android.widget.TextView[@text='"+payee+"']/../..");
		
		int startX = startElement.getLocation().getX() + (startElement.getSize().getWidth() / 2);
        int startY = startElement.getLocation().getY() + (startElement.getSize().getHeight() / 2);
        int endX = startElement.getLocation().getX() + (startElement.getSize().getWidth() / 3);
        int endY = startElement.getLocation().getY() + (startElement.getSize().getHeight() / 2);
        new TouchAction(Engine.getDriver())
         .press(point(startX,startY))
         .waitAction(waitOptions(ofMillis(1000)))
         .moveTo(point(endX, endY))
         .release().perform();
 	

		MobileElement fav_icon = Engine.getDriver().findElementByXPath("//android.widget.TextView[@text='"+payee+"']/../../..//android.widget.ImageView");
			
			fav_icon.click();
			Thread.sleep(2000);
			this.removeBtn.click();
			Thread.sleep(3000);
						
		}
   
   public void verifyFavPayee(String payee) throws Exception{
		
		Helper h = new Helper();
		
		if (h.getEngine().equals("ios"))
			verifyFavPayee_ios(payee);
		else
			verifyFavPayee_android(payee);
		
	}

   
   public void verifyFavPayee_ios(String payee) throws Exception{
	   
	   TransactionDetailPage td = new TransactionDetailPage();
	   
	    this.payee.click();
	    td.searchPayee.sendKeys(payee);
		Helper h = new Helper();
		h.hideKeyBoard();
		Thread.sleep(3000);
	   
	   if(Verify.objExists(FavoritePayeeLabel)) {
			String cc = "//XCUIElementTypeStaticText[@name='"+payee+"']/..";
			Engine.getDriver().findElementByXPath(cc).isDisplayed();
			
			Thread.sleep(500);
			Commentary.log(LogStatus.PASS,"PASS: Showing Payee "+payee+" as Favorite Payee ");
		}
		else {

			Commentary.log(LogStatus.FAIL,"FAIL: Not showing Payee "+payee+" as Favorite Payee. ");
		}
	   
   }
	   
	   
	public void verifyFavPayee_android(String payee) throws Exception{
		
		 TransactionDetailPage td = new TransactionDetailPage();
		   
		    td.searchPayee.click();
		    td.searchPayee.sendKeys(payee);
			Helper h = new Helper();
			h.hideKeyBoard();
			Thread.sleep(3000);
		   
		   
		   if(Verify.objExists(FavoritePayeeLabel)) {
				String cc = "//android.widget.TextView[@text='"+payee+"']/../..";
				Engine.getDriver().findElementByXPath(cc).isDisplayed();
				
				Thread.sleep(500);
				Commentary.log(LogStatus.PASS,"PASS: Showing Payee "+payee+" as Favorite Payee ");
			}
			else {

				Commentary.log(LogStatus.FAIL,"FAIL: Not showing Payee "+payee+" as Favorite Payee. ");
			}
		   
	   }
	
	
   public void verifyRemoveFavPayee(String payee) throws Exception{
				
				Helper h = new Helper();
				
				if (h.getEngine().equals("ios"))
					verifyRemoveFavPayee_ios(payee);
				else
					verifyRemoveFavPayee_android(payee);
				
			}

		   
		   public void verifyRemoveFavPayee_ios(String payee) throws Exception{
			   
			   TransactionDetailPage td = new TransactionDetailPage();
			   
			    this.payee.click();
			    td.searchPayee.sendKeys(payee);
				Helper h = new Helper();
				h.hideKeyBoard();
				Thread.sleep(3000);
			   
			   if(Verify.objExists(FavoritePayeeLabel)) {
					String cc = "//XCUIElementTypeStaticText[@name='"+payee+"']/..";
					Engine.getDriver().findElementByXPath(cc).isDisplayed();
					
					Thread.sleep(500);
					Commentary.log(LogStatus.FAIL,"FAIL: Unable to remove Payee "+payee+" as Favorite Payee ");
				}
				else {

					Commentary.log(LogStatus.PASS,"PASS: Able to remove "+payee+" as Favorite Payee. ");
				}
			   
		   }
			   
			   
			public void verifyRemoveFavPayee_android(String payee) throws Exception{
				
				 TransactionDetailPage td = new TransactionDetailPage();
				   
				    td.searchPayee.click();
				    td.searchPayee.sendKeys(payee);
					Helper h = new Helper();
					h.hideKeyBoard();
					Thread.sleep(3000);
				   
				   
				   if(Verify.objExists(FavoritePayeeLabel)) {
						String cc = "//android.widget.TextView[@text='"+payee+"']/../..";
						Engine.getDriver().findElementByXPath(cc).isDisplayed();
						
						Thread.sleep(500);
						Commentary.log(LogStatus.FAIL,"FAIL: Unable to remove Payee "+payee+" as Favorite Payee ");
					}
					else {

						Commentary.log(LogStatus.PASS,"PASS: Able to remove "+payee+" as Favorite Payee. ");
					}
				   
				      	   
		      
	   
   }
 
 
 
 
 
}
