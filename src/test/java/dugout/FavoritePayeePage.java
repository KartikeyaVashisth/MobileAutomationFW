package dugout;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.PointerInput.MouseButton;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import referee.Commentary;
import referee.Verify;
import support.Engine;
import support.Helper;
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
    public WebElement DisplayFavoritePayee;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeSwitch[3]")
	@AndroidFindBy(xpath="//android.widget.Switch[3]")
    public WebElement FavoritePayeeSwitch;
	

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='navigationMenu'`]")
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='navigationMenu']")
    public WebElement hambergerIcon;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Settings Menu'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Settings']")
	public WebElement settingsOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='favoriteHeaderMoreAction'`]")
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='favoriteHeaderMoreAction']")
    public WebElement MoreOptionInPayeeDrawer;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='moreAction'`]")
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='moreAction']")
    public WebElement YelpInPayeeDrawer;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`label ='disableYelpPayeeView'`][3]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Turn off Yelp recommendations']")
    public WebElement TurnOffYelp;

	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Favorite Payee'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Favorite Payee']")
    public WebElement FavoritePayeeLabel;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`label ='hideFavoritePayeeView'`][3]")
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Hide favorites']")
    public WebElement HideFavorite;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Favorite Payee'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Favorite Payee']")
    public WebElement btnEnableFavoritePayee;

	//@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementStaticText[`name='Payee'`]")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label = 'Payee'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Payee']")
    public WebElement payee;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='All Payees'`][1]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='All Payees']")
	public WebElement allPayeesLabel;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Near by'`][1]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Near by']")
	public WebElement yelpNearByLabel;
	
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name CONTAINS 'Turn off Favorite Payee recommendations?'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Turn off Favorite Payee recommendations?')]")
	public WebElement HideFavoritePayeeConfMessage;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name='Yes'`]")
	@AndroidFindBy(xpath="//*[@text='YES']")
	public WebElement buttonYES;
	
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name='No'`]")
	@AndroidFindBy(xpath="//*[@text='NO']")
	public WebElement buttonNO;
	
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeButton[`name contains 'back'`]")
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='backArrow']")
	public WebElement backButton;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name ='Remove'`]")
	@AndroidFindBy(xpath="//android.widget.Button[@text='REMOVE']")
	public WebElement removeBtn;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name ='Cancel'`]")
	@AndroidFindBy(xpath="//android.widget.Button[@text='CANCEL']")
	public WebElement cancelBtn;


	
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
		    Engine.getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Settings\").instance(0))"));
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
    	
    	WebElement ele_ios = Engine.getDriver().findElement(By.xpath("//XCUIElementTypeStaticText[@name='"+payee+"']/.."));
	
				JavascriptExecutor js = (JavascriptExecutor)Engine.getDriver();
				HashMap scrollObject = new HashMap();
				scrollObject.put("direction", "left"); 
				scrollObject.put("element", ele_ios); 
				js.executeScript("mobile: swipe", scrollObject);
				Thread.sleep(500);
				
		WebElement fav_icon = Engine.getDriver().findElement(By.xpath("(//XCUIElementTypeStaticText[@name='"+payee+"']/../../../XCUIElementTypeOther/XCUIElementTypeOther)[2]"));
			
			fav_icon.click();
			
		}
 
   public void AddPayeeAsFavPayee_android(String payee) throws Exception{
	    
       WebElement startElement = Engine.getDriver().findElement(By.xpath("//android.widget.TextView[@text='"+payee+"']"));
       
       int startX = startElement.getLocation().getX()+1000;
       int startY = startElement.getLocation().getY();
       int endX = startElement.getLocation().getX();
       int endY = startElement.getLocation().getY();
       
//       new TouchAction(Engine.getDriver())
//           .press(point(startX,startY))
//           .waitAction(waitOptions(ofMillis(1000)))
//           .moveTo(point(endX, endY))
//           .release().perform();
       
       PointerInput finger1 = new PointerInput(Kind.TOUCH, "finger1");
       Sequence sequence = new Sequence(finger1, 1)
    		   .addAction(finger1.createPointerMove(Duration.ZERO, Origin.viewport(), startX, startY))
    		   .addAction(finger1.createPointerDown(MouseButton.LEFT.asArg()))
    		   .addAction(new Pause(finger1, Duration.ofMillis(200)))
    		   .addAction(finger1.createPointerMove(Duration.ofMillis(100), Origin.viewport(), endX, endY))
    		   .addAction(finger1.createPointerUp(MouseButton.LEFT.asArg()));

       Engine.getDriver().perform(Collections.singletonList(sequence));
       Thread.sleep(2000);

       WebElement fav_icon = Engine.getDriver().findElement(By.xpath("//android.widget.TextView[@text='"+payee+"']/../../..//android.widget.ImageView"));
       fav_icon.click();
   }
   
   public void RemoveFavPayee(String payee) throws Exception{
		
		Helper h = new Helper();
		
		if (h.getEngine().equals("ios"))
			RemoveFavPayee_ios(payee);
		else
			RemoveFavPayee_android(payee);
	}

   public void RemoveFavPayee_ios(String payee) throws Exception{


	   WebElement ele_ios = Engine.getDriver().findElement(By.xpath("//XCUIElementTypeStaticText[@name='"+payee+"']/.."));

	   JavascriptExecutor js = (JavascriptExecutor)Engine.getDriver();
	   HashMap scrollObject = new HashMap();
	   scrollObject.put("direction", "left"); 
	   scrollObject.put("element", ele_ios); 
	   js.executeScript("mobile: swipe", scrollObject);
	   Thread.sleep(500);

	   WebElement fav_icon = Engine.getDriver().findElement(By.xpath("(//XCUIElementTypeStaticText[@name='"+payee+"']/../../../XCUIElementTypeOther/XCUIElementTypeOther)[2]"));

	   fav_icon.click();
	   Thread.sleep(500);
	   this.removeBtn.click();
	   Thread.sleep(3000);
   }

   public void RemoveFavPayee_android(String payee) throws Exception{
 	
 
        WebElement startElement = Engine.getDriver().findElement(By.xpath("//android.widget.TextView[@text='"+payee+"']/../.."));
        
        int startX = startElement.getLocation().getX()+1000;
        int startY = startElement.getLocation().getY();
        int endX = startElement.getLocation().getX();
        int endY = startElement.getLocation().getY();
        
//        new TouchAction(Engine.getDriver())
//         .press(point(startX,startY))
//         .waitAction(waitOptions(ofMillis(1000)))
//         .moveTo(point(endX, endY))
//         .release().perform();
        
        PointerInput finger1 = new PointerInput(Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 1)
        		.addAction(finger1.createPointerMove(Duration.ZERO, Origin.viewport(), startX, startY))
        		.addAction(finger1.createPointerDown(MouseButton.LEFT.asArg()))
        		.addAction(new Pause(finger1, Duration.ofMillis(200)))
        		.addAction(finger1.createPointerMove(Duration.ofMillis(100), Origin.viewport(), endX, endY))
        		.addAction(finger1.createPointerUp(MouseButton.LEFT.asArg()));

        Engine.getDriver().perform(Collections.singletonList(sequence));
        Thread.sleep(2000);

        WebElement fav_icon = Engine.getDriver().findElement(By.xpath("//android.widget.TextView[@text='"+payee+"']/../../..//android.widget.ImageView"));

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
			Engine.getDriver().findElement(By.xpath(cc)).isDisplayed();
			
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
				Engine.getDriver().findElement(By.xpath(cc)).isDisplayed();
				
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
					Engine.getDriver().findElement(By.xpath(cc)).isDisplayed();
					
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
						Engine.getDriver().findElement(By.xpath(cc)).isDisplayed();
						
						Thread.sleep(500);
						Commentary.log(LogStatus.FAIL,"FAIL: Unable to remove Payee "+payee+" as Favorite Payee ");
					}
					else {

						Commentary.log(LogStatus.PASS,"PASS: Able to remove "+payee+" as Favorite Payee. ");
					}
				   
				      	   
		      
	   
   }
 
 
 
 
 
}
