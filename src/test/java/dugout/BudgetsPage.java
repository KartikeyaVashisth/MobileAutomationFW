package dugout;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import referee.Commentary;
import referee.Verify;
import support.Engine;
import support.Helper;

public class BudgetsPage {
	
	public BudgetsPage () {
		try {
			
			PageFactory.initElements(new AppiumFieldDecorator(Engine.getDriver()),this);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Personal Income'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Personal Income']")
	public WebElement personalIncome;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Personal Expenses'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Personal Expenses']")
	public WebElement personalExpenses;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Business Expenses'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Business Expenses']")
	public WebElement businessExpenses;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Business Income'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Business Income']")
	public WebElement businessIncome;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Rental Property Expenses'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Rental Property Expenses']")
	public WebElement rentalPropertyExpenses;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Rental Property Income'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Rental Property Income']")
	public WebElement rentalPropertyIncome;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Home Phone'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Home Phone']")
	public WebElement homePhoneCategory;
	
	@iOSXCUITFindBy(iOSNsPredicate = "name = 'In progress'")
	@AndroidFindBy(xpath="//android.widget.ProgressBar")
	public WebElement refreshSpinnerIcon;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`label ='calendar'`][3]")
    @AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='calendar']")
    public WebElement budgetViewOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`label ='closeBudgets'`][3]")
    @AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='closeBudgets']")
    public WebElement closeBudgetViewOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Monthly'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Monthly']")
    public WebElement monthlyView;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Quarterly'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Quarterly']")
    public WebElement quarterlyView;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Year to Date'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Year to Date']")
    public WebElement yeartoDateView;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Apply'`][2]")
	@AndroidFindBy(xpath="//*[@text='Apply']")
	public WebElement applybudgetView;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`label ='VIEW'`]")
    @AndroidFindBy(xpath="//android.widget.TextView[@text='VIEW']")
    public WebElement viewSwipeOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`label ='EDIT'`]")
    @AndroidFindBy(xpath="//android.widget.TextView[@text='EDIT']")
    public WebElement editSwipeOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`label ='backPress'`][3]")
    @AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='backPress']")
    public WebElement backButton;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Select Budget'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Select Budget']")
    public WebElement selectBudgetScreen;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`label ='Apply'`][2]")
    @AndroidFindBy(xpath="//*[@text='Apply']")
    public WebElement applyButton;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Edit Budget'`]")
	@AndroidFindBy(xpath="//*[@text='Edit Budget']")
    public WebElement editBudgetScreen;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='button Save'`]")
	@AndroidFindBy(xpath="//*[@text='Save']")
    public WebElement saveButton;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Update Budget Amount'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Update Budget Amount']")
    public WebElement updateBudgetAmountscreen;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Save'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Save']")
    public WebElement saveUpdateBudgetAmount;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Cancel'`]")
	@AndroidFindBy(xpath="//*[@text='Cancel']")
	public WebElement cancelUpdateBudgetAmount;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name contains'back'`]")
    @AndroidFindBy(xpath="//android.widget.Button[contains(@content-desc,'back')]")
    public WebElement backButtonEditBudgetscreen;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains'Budget'`][-1]")
    //@AndroidFindBy(xpath="(//android.widget.ImageView[@index='1'])[1]")
	@AndroidFindBy(xpath="(//android.widget.TextView[contains(@text,'Budget')])[1]")
    public WebElement budgetdropdwon;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains'Parking'`][-2]/**/XCUIElementTypeStaticText[`name contains'out of'`]")
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Auto & Transport:Car Wash']/..//android.widget.TextView[contains(@text,'out of')]")
    public WebElement allocatebudgetamount;
	
	@iOSXCUITFindBy(id="Search Transactions")
	@AndroidFindBy(xpath="//android.widget.AutoCompleteTextView[@text='Search Transactions']")
	public WebElement searchTransactionTextBox;
	
	@iOSXCUITFindBy(iOSClassChain ="**/XCUIElementTypeOther[`label = 'delete'`][2]")
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='delete']")
	public WebElement deletekey;
	
	@iOSXCUITFindBy(iOSClassChain ="**/XCUIElementTypeOther[`name contains'$'`][-4]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'$')][-3]")
	public WebElement clickonEditbudgetscreen;
	
	@iOSXCUITFindBy(iOSClassChain ="**/XCUIElementTypeStaticText[`name contains'0'`][2]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'$')][2]")
	public WebElement clickonamount;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name contains'back'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'back')]")
	public WebElement backbutton1;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains'Rollover'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Rollover')]")
	public WebElement rollovertext;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains'YTD'`][-2]")
	@AndroidFindBy(xpath="//android.view.View[contains(@text,'YTD')]")
	public WebElement YTDLabel;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains'Q3'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Q3')]")
	public WebElement Quarterlylabel;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains'YTD: 1\'`][-2]")
	@AndroidFindBy(xpath="//android.view.View[contains(@text,'YTD: 1\')]")
	public WebElement YTDrange;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains'Q1'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Q1')]")
	public WebElement Q1Quarter;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains'Q2'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Q2')]")
	public WebElement Q2Quarter;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains'Q3'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Q3')]")
	public WebElement Q3Quarter;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains'Q4'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Q4')]")
	public WebElement Q4Quarter;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains'Track your budget on your phone'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Track your budget on your phone')]")
	public WebElement zerostateforbudget;
	
	public void tapOnBudgetCard() throws Exception {
		
		DateFormat date =  new SimpleDateFormat("MMMM");
		Date date1 = new Date();
		String sCard = date.format(date1).toString();
		//System.out.println(date.format(date1).toString());
		sCard = sCard +" "+"Budget";
		Verify.waitForObjectToDisappear(refreshSpinnerIcon, 2);
		
		Helper h = new Helper();
		if (h.getEngine().equals("android")){
			String sXpath="//android.widget.TextView[@text='"+sCard+"']";
			Engine.getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ sCard +"\").instance(0))"));
			Thread.sleep(1000);
			Engine.getDriver().findElement(By.xpath(sXpath)).click();
			Thread.sleep(1000);
		}
		else {
			//String sXpath="//*[@name='"+sCard+"']";
			String cc="**/*[`name=='"+sCard+"'`]";
			WebElement me = (WebElement) Engine.getDriver().findElement(AppiumBy.iOSClassChain(cc));
			//String me_id = me.getid();
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			//scrollObject.put("element", me_id);
			scrollObject.put("predicateString", "label == '"+sCard+"'");
			scrollObject.put("direction", "down");
//			Engine.getDriver().executeScript("mobile:scroll", scrollObject);  // scroll to the target element
			Engine.getDriver().executeScript("mobile:swipe", scrollObject);
			Thread.sleep(1000);
			Engine.getDriver().findElement(By.name(sCard)).click();
			Thread.sleep(1000);
		}
		
	}
	
	
	public boolean verify_budgetHeader() throws Exception {
		
		DateFormat date =  new SimpleDateFormat("MMMM");
		Date date1 = new Date();
		String sCard = date.format(date1).toString();
		sCard = sCard +" "+"Budget";
		
		Helper h = new Helper();
		if (h.getEngine().equals("android")){
			String sXpath="//android.view.View[@text='"+sCard+"']";
			return Engine.getDriver().findElement(By.xpath(sXpath)).isDisplayed();
		
		}
		else {
			return Engine.getDriver().findElement(By.name(sCard)).isDisplayed();
		}
		
	}
	
	
	public void swipe_budgetcategory(String category) throws Exception {
		Helper h = new Helper();

		if (h.getEngine().equalsIgnoreCase("android")) {
			swipe_android(category);
		} else {
			//WebElement ele_ios = Engine.getDriver().findElementByXPath("*//XCUIElementTypeCell");
			swipe_ios(category);
		}
	}
	
	
	public void swipe_ios(String category) throws Exception {

    	//WebElement ele_ios = Engine.getDriver().findElement(By.xpath("//XCUIElementTypeStaticText[@name='"+category+"']"));
    	WebElement ele_ios = Engine.getDriver().findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`name='"+category+"'`]"));

    	
				JavascriptExecutor js = (JavascriptExecutor)Engine.getDriver();
				HashMap scrollObject = new HashMap();
				scrollObject.put("direction", "left"); 
				scrollObject.put("element", ele_ios); 
				js.executeScript("mobile: swipe", scrollObject);
				Thread.sleep(5000);
	}
	

	public void swipe_android(String category) throws Exception {
		
		 WebElement startElement = Engine.getDriver().findElement(By.xpath("//android.widget.TextView[@text='"+category+"']/../.."));
			
		   int startX = startElement.getLocation().getX() + (startElement.getSize().getWidth() / 2);
	       int startY = startElement.getLocation().getY() + (startElement.getSize().getHeight() / 2);
	       int endX = startElement.getLocation().getX() + (startElement.getSize().getWidth() / 6);
	       int endY = startElement.getLocation().getY() + (startElement.getSize().getHeight() / 6);
	      // new TouchAction(Engine.getDriver())
	      ///     .press(point(startX,startY))
	      //     .waitAction(waitOptions(ofMillis(4000)))
	     //      .moveTo(point(endX, endY))
	     //      .release().perform();
	     //  Thread.sleep(1000);
	       
	       PointerInput finger1 = new PointerInput(Kind.TOUCH, "finger1");
	       Sequence sequence = new Sequence(finger1, 1)
					.addAction(finger1.createPointerMove(Duration.ZERO, Origin.viewport(), startX, startY))
					.addAction(finger1.createPointerDown(MouseButton.LEFT.asArg()))
					.addAction(new Pause(finger1, Duration.ofMillis(200)))
					.addAction(finger1.createPointerMove(Duration.ofMillis(100), Origin.viewport(), endX, endY))
					.addAction(finger1.createPointerUp(MouseButton.LEFT.asArg()));
			
			Engine.getDriver().perform(Collections.singletonList(sequence));
			Thread.sleep(2000);
	}
	
	
   public void clickonBudgetCategory (String category) throws Exception {
		
		Helper h = new Helper();
		
		if (h.getEngine().equals("android"))
			this.clickonBudgetCategory_android(category);
		else
			this.clickonBudgetCategory_ios(category);
	}
   
	
	public void clickonBudgetCategory_android (String category) throws Exception {
		
		String sXpath = "//android.widget.TextView[@text='"+category+"']";
		//Engine.getDriver().findElement(AppiumBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ category + "\").instance(0))"));
		
		Thread.sleep(1000);
		Engine.getDriver().findElement(By.xpath(sXpath)).click();
		Thread.sleep(500);	
	
	}
	
	
	public void clickonBudgetCategory_ios (String category) throws Exception {
		
		String sXpath = "//XCUIElementTypeStaticText[@name='"+category+"']";
		

		Engine.getDriver().findElement(By.xpath(sXpath)).click();
		Thread.sleep(500);	
		
		
	}
	
	
	 public void selectBudget (String budgetName) throws Exception {
			
		Helper h = new Helper();
			
		if (h.getEngine().equals("android"))
			this.selectBudget_android(budgetName);
		else
			this.selectBudget_ios(budgetName);
		}
	   
		
	public void selectBudget_android (String budgetName) throws Exception {
			
		   String sXpath = "//android.widget.TextView[@text='"+budgetName+"']";
		   //Engine.getDriver().findElement(AppiumBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ category + "\").instance(0))"));
		   this.budgetdropdwon.click();
		  Thread.sleep(1000);
		   Engine.getDriver().findElement(By.xpath(sXpath)).click();
		   Thread.sleep(500);	
		   this.applyButton.click();
		   Thread.sleep(1000);
		}
		
		
	public void selectBudget_ios (String budgetName) throws Exception {
			
			String sXpath = "//XCUIElementTypeStaticText[@name='"+budgetName+"']";
			
			this.budgetdropdwon.click();
			Thread.sleep(1000);
			Engine.getDriver().findElement(By.xpath(sXpath)).click();
			Thread.sleep(500);	
			this.applyButton.click();
			
			
		}
		
	
   public void changeBudgetAmount (String category, String amount ) throws Exception {
				
				Helper h = new Helper();
				
				if (h.getEngine().equals("android"))
					this.changeBudgetAmount_android(category, amount);
				else
					this.changeBudgetAmount_ios(category, amount);
			}
		   
			
	public void changeBudgetAmount_android (String category, String amount) throws Exception {
				
				TransactionDetailPage td = new TransactionDetailPage();
				String sXpath = "//android.widget.TextView[@text='"+category+"']/..//android.widget.TextView[contains(@text,'out of')]";
				
				Engine.getDriver().findElement(By.xpath(sXpath)).click();
				for (int i = 0; i<2; i++)
			 	this.deletekey.click();
			 	Thread.sleep(500);
				
			    td.enterAmount(amount);
			    Commentary.log(LogStatus.INFO, "Budget amount: ["+amount+"] updated successfully for the category ");
			    Thread.sleep(500);
			
			}
			
			
	public void changeBudgetAmount_ios (String category, String amount) throws Exception {
				
				TransactionDetailPage td = new TransactionDetailPage();
				
				WebElement budgetamount = Engine.getDriver().findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeOther[`name contains'"+category+"'`][-2]/**/XCUIElementTypeStaticText[`name contains'out of'`]"));
			    
				//WebElement budgetamount = Engine.getDriver().findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`name contains'of'`]"));
			    budgetamount.click();
			    Thread.sleep(500);
			    

		 		for (int i = 0; i<2; i++)
		 		this.deletekey.click();
		 		Thread.sleep(500);
		 		td.enterAmount(amount);
		 		Commentary.log(LogStatus.INFO, "Allocated Budget amount: ["+amount+"] updated successfully for the category ");
		
				
				Thread.sleep(1000);	
			}
			
			
      public void changechildCategoryBudgetAmount (String childcategory, String amount ) throws Exception {
				
				Helper h = new Helper();
				
				if (h.getEngine().equals("android"))
					this.changechildCategoryBudgetAmount_android(childcategory, amount);
				else
					this.changechildCategoryBudgetAmount_ios(childcategory, amount);
			}
		   
			
	 public void changechildCategoryBudgetAmount_android (String childcategory, String amount) throws Exception {
				
				TransactionDetailPage td = new TransactionDetailPage();
				String sXpath = "//android.widget.TextView[@text='"+childcategory+"']/..//android.widget.TextView[contains(@text,'of')]";
				
				Engine.getDriver().findElement(By.xpath(sXpath)).click();
				for (int i = 0; i<2; i++)
			 	this.deletekey.click();
			 	Thread.sleep(500);
				
			    td.enterAmount(amount);
			    Commentary.log(LogStatus.INFO, "Budget amount: ["+amount+"] updated successfully for the category ");
			    Thread.sleep(500);
			
			}
			
			
	 public void changechildCategoryBudgetAmount_ios (String childcategory, String amount) throws Exception {
				
				TransactionDetailPage td = new TransactionDetailPage();
				
				//WebElement budgetamount = Engine.getDriver().findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`name contains'"+childcategory+"'`][-2]/**/XCUIElementTypeStaticText[`name contains'out of'`]"));
				WebElement budgetamount = Engine.getDriver().findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`name contains'of'`][2]"));
				//WebElement budgetamount = Engine.getDriver().findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name="+childcategory+"]/..//XCUIElementTypeStaticText[contains(@name,'of')]"));
				
				budgetamount.click();
			    Thread.sleep(500);
			    
			   //WebElement deletekey = Engine.getDriver().findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeOther[`label = 'delete'`][2]"));
		 		for (int i = 0; i<2; i++)
		 		this.deletekey.click();
		 		Thread.sleep(500);
		 		td.enterAmount(amount);
		 		Commentary.log(LogStatus.INFO, "Allocated Budget amount: ["+amount+"] updated successfully for the category ");
				Thread.sleep(1000);	
			}
			
	 public void verifyBudgetAmount (String category, String amount) throws Exception {
					
				Helper h = new Helper();
					
				if (h.getEngine().equals("android"))
					this.verifyBudgetAmount_android(category, amount);
				else
					this.verifyBudgetAmount_ios(category, amount);
				}
			   
				
	  public boolean verifyBudgetAmount_android (String category, String amount) throws Exception {
					
				String sTemp;
				String sXpath = "//android.widget.TextView[@text='"+category+"']/..//android.widget.TextView[contains(@text,'out of')]";
					
				sTemp = Engine.getDriver().findElement(By.xpath(sXpath)).getText();
				String sBudgetamount=sTemp.substring(14);
				   
				if (sBudgetamount.equals(amount)) {
					Commentary.log(LogStatus.PASS, "PASS: Budget amount verified successfully. Expected ["+amount+"], Actual ["+sBudgetamount+"]");
					return true;
				}

					Commentary.log(LogStatus.FAIL, "FAIL: Budget amount verification failed. Expected ["+amount+"], Actual ["+sBudgetamount+"]");
					return false;
				
				}
				
				
		public boolean verifyBudgetAmount_ios (String category, String amount) throws Exception {
					
				String sTemp;
				WebElement budgetamount = Engine.getDriver().findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeOther[`name contains'"+category+"'`][-2]/**/XCUIElementTypeStaticText[`name contains'out of'`]"));
				
				//budgetamount.click();
				sTemp = budgetamount.getText();
			    String sBudgetamount=sTemp.substring(13);
				   
				if (sBudgetamount.equals(amount)) {
					Commentary.log(LogStatus.PASS, "PASS: Budget amount verified successfully. Expected ["+amount+"], Actual ["+sBudgetamount+"]");
					return true;
					}
				else
					Commentary.log(LogStatus.FAIL, "FAIL: Budget amount verification failed. Expected ["+amount+"], Actual ["+sBudgetamount+"]");
					return false;
				  
				
				
				}
		
				
        public void verifyBudgetAmountforChildcategory (String childcategory, String amount) throws Exception {
					
					Helper h = new Helper();
					
					if (h.getEngine().equals("android"))
						this.verifyBudgetAmountforChildcategory_android(childcategory, amount);
					else
						this.verifyBudgetAmountforChildcategory_ios(childcategory, amount);
				}
			   
				
	  public boolean verifyBudgetAmountforChildcategory_android (String childcategory, String amount) throws Exception {
					
				String sTemp;
				String sXpath = "//android.widget.TextView[@text='"+childcategory+"']/..//android.widget.TextView[contains(@text,'of')]";
					
				sTemp = Engine.getDriver().findElement(By.xpath(sXpath)).getText();
				String sBudgetamount=sTemp.substring(8);
				   
				if (sBudgetamount.equals(amount)) {
					Commentary.log(LogStatus.PASS, "PASS: Budget amount verified successfully. Expected ["+amount+"], Actual ["+sBudgetamount+"]");
					return true;
					}
				else
					Commentary.log(LogStatus.FAIL, "FAIL: Budget amount verification failed. Expected ["+amount+"], Actual ["+sBudgetamount+"]");
					return false;
				
				}
				
				
		 public boolean verifyBudgetAmountforChildcategory_ios (String childcategory, String amount) throws Exception {
					
					
				String sTemp;
				WebElement budgetamount = Engine.getDriver().findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`name contains'of'`][2]"));
					
					
				//budgetamount.click();
				sTemp = budgetamount.getText();
				String sBudgetamount=sTemp.substring(8);
				Thread.sleep(500);
				
				if (sBudgetamount.equals(amount)) {
					Commentary.log(LogStatus.PASS, "PASS: Budget amount verified successfully. Expected ["+amount+"], Actual ["+sBudgetamount+"]");
					return true;
					}
				else
					Commentary.log(LogStatus.FAIL, "FAIL: Budgte amount verification failed. Expected ["+amount+"], Actual ["+sBudgetamount+"]");
					return false;
				  
				
				
				}
				
				
	 public void verifyTransactionBudgetAmount (String category, String TransAmount) throws Exception {
				
				Helper h = new Helper();
					
				if (h.getEngine().equals("android"))
					this.verifyTransactionBudgetAmount_Android(category, TransAmount);
				else
					this.verifyTransactionBudgetAmount_ios(category, TransAmount);
				}
			   
				
	  public boolean verifyTransactionBudgetAmount_Android (String category, String TransAmount) throws Exception {
					
				String sTemp;
				String sXpath = "//android.widget.TextView[@text='"+category+"']/..//android.widget.TextView[contains(@text,'out of')]";
					
				sTemp = Engine.getDriver().findElement(By.xpath(sXpath)).getText();
				String sBudgetamount=sTemp.substring(1,3)+".00";
				   
				if (sBudgetamount.equals(TransAmount)) {
					Commentary.log(LogStatus.PASS, "PASS: Budget data verified successfully after adding new transaction from mobile.  Expected ["+TransAmount+"], Actual ["+sBudgetamount+"]");
					return true;
				}

					Commentary.log(LogStatus.FAIL, "FAIL:Budget data verification failed. Expected ["+TransAmount+"], Actual ["+sBudgetamount+"]");
					return false;
				
				}
				
				
		public boolean verifyTransactionBudgetAmount_ios (String category, String TransAmount) throws Exception {
					
				String sTemp;
				WebElement budgetamount = Engine.getDriver().findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeOther[`name contains'"+category+"'`][-2]/**/XCUIElementTypeStaticText[`name contains'out of'`]"));
				
				//budgetamount.click();
				sTemp = budgetamount.getText();
			    String sBudgetamount=sTemp.substring(1,3)+".00";
				   
				if (sBudgetamount.equals(TransAmount)) {
					Commentary.log(LogStatus.PASS, "PASS:  Budget data verified successfully after adding new transaction from mobile. Expected ["+TransAmount+"], Actual ["+sBudgetamount+"]");
					return true;
					}
				else
					Commentary.log(LogStatus.FAIL, "FAIL: Budget data verification failed. Expected ["+TransAmount+"], Actual ["+sBudgetamount+"]");
					return false;
				  
				
				
				}
		
	

}

