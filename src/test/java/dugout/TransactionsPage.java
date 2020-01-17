package dugout;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import referee.Commentary;
import referee.Verify;
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
		Thread.sleep(3000);
		
		AllAccountsPage ap = new AllAccountsPage();
		ap.navigateBackToDashboard();
		
	}
	
	public void searchTransaction(String searchString) throws Exception{
		
		OverviewPage o = new OverviewPage();
		o.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		bcc.allTransactionButton.click();
		
		
		searchTransactionTxtField.clear();
		searchTransactionTxtField.sendKeys(searchString);
		
		Helper h = new Helper();
		h.hideKeyBoard();
		Thread.sleep(1000);
		
	
	}
	public void tapOnFirstTransation() throws Exception {
		AllAccountsPage aa = new AllAccountsPage();
				
		List<MobileElement> li = aa.getAllSearchTransactions ();
		Commentary.log(LogStatus.INFO, "No of Transactions appeared in the search .."+li.size());
		
		if (li.isEmpty())
			Commentary.log(LogStatus.ERROR, "No Transactions available on Transaction list screen");
		else
			li.get(0).click();
		
		Thread.sleep(1000);
//		if (Verify.objExists(this.allowButton)) {
//			this.allowButton.click();
//			Thread.sleep(1000);
//		}
		
		
	} 
	public int getTransactionListSize() throws Exception {
		AllAccountsPage aa = new AllAccountsPage();
		
		List<MobileElement> li = aa.getAllSearchTransactions ();
		Commentary.log(LogStatus.INFO, "No of Transactions appeared in the search .."+li.size());
		int listSize = li.size();
		return listSize;
		
	}
	
	public void swipe_left() throws Exception {
		Helper h = new Helper();

		if (h.getEngine().equalsIgnoreCase("android")) {
			swipe_android();
		} else {
			WebElement ele_ios = Engine.iosd.findElementByXPath("*//XCUIElementTypeCell");
			swipe_ios(ele_ios);
		}
	}
	
	public void swipe_ios(WebElement ele) {

		JavascriptExecutor js1 = (JavascriptExecutor) Engine.iosd;
		HashMap scrollObject = new HashMap();
		scrollObject.put("direction", "left");
		scrollObject.put("element", ele);
		js1.executeScript("mobile: swipe", scrollObject);

	}

	public void swipe_android() throws Exception {
		Dimension size = Engine.ad.manage().window().getSize();
		System.out.println(size);
		int startx = (int) (size.width * 0.80);
		int endx = (int) (size.width * 0.20);
		int starty = size.height / 2;
		
		TouchAction touchAction = new TouchAction(Engine.getDriver());
 
		touchAction
                .press(point(startx, starty))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(point(startx, starty))
                .release().perform();
		//Engine.ad.swipe(startx, starty, endx, starty, 2000);
	}
	
	public void selectCategorySwipe (String category) throws Exception {
		
		Helper h = new Helper();
		
		if (h.getEngine().equals("android"))
			this.selectCategorySwipe_android(category);
		else
			this.selectCategorySwipe_ios(category);
		
		
	}
	public void selectCategorySwipe_android (String category) throws Exception {
		
		String sXpath = "//android.widget.TextView[@text='"+category+"']";
		TransactionDetailPage td = new TransactionDetailPage();
		td.searchCategory(category);
		
		Engine.ad.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ category + "\").instance(0))"));
		
		Thread.sleep(1000);
		Engine.ad.findElement(By.xpath(sXpath)).click();
		Thread.sleep(500);	
		
		if (Verify.objExists(this.buttonSave)) {
			this.buttonSave.click();
			Thread.sleep(1000);
		}
		
		System.out.println(Engine.ad.getContext());
	}
	
	public void selectCategorySwipe_ios (String category) throws Exception {
		
		String sXpath = "//XCUIElementTypeOther[@name='"+category+"']";
		
		TransactionDetailPage td = new TransactionDetailPage();
		td.searchCategory(category);
		Engine.iosd.findElement(By.xpath(sXpath)).click();
		Thread.sleep(500);	
		
		if (Verify.objExists(this.buttonSave)) {
			this.buttonSave.click();
			Thread.sleep(500);
		}
	}
	
	public MobileElement getPayeeName() {
		String xpath_IOS = "//XCUIElementTypeStaticText[@name='topLeftLabel']";
		String xpath_Android = "//android.widget.TextView[@resource-id='com.quicken.qm2014:id/list_row_category']"; 
		
		Helper h = new Helper();
		
		if (h.getEngine().equalsIgnoreCase("android")) {
			return (MobileElement) Engine.ad.findElementByXPath(xpath_Android);
		} else {
			return (MobileElement) Engine.iosd.findElementByXPath(xpath_IOS);

		}
	}
	
	public boolean verifySorting() {
		//String xPath_Android = "";
		//String xPath_IOS = "//*[@name='topRightLabel'])[1]";
		
		boolean flag = false;
		String[] act,exp;
		AllAccountsPage aa = new AllAccountsPage();
		Helper h = new Helper();
		//WebElement element = Engine.iosd.findElementByXPath(xPath_IOS);
		List<MobileElement> actList = null,expList=null;
		try {
			actList = aa.getAllSearchTransactions ();
			String[] actRes = new String[actList.size()];
			for (int i = 1; i <= actList.size(); i++) {
				
				actRes[i]= getTransactionAmountValue(i).getText();
				
				System.out.println("i" + i + " is " + actRes[i]);
				}
			 Arrays.sort(actRes);
			 System.out.println("sorted list---> "+Arrays.toString(actRes));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Commentary.log(LogStatus.INFO, "No of Transactions appeared in the search .."+actList.size());
		return false;
		
	}
	public WebElement getTransactionAmountValue( int i) {
		Helper h = new Helper();
		if (h.getEngine().equalsIgnoreCase("android")){
			WebElement element = Engine.ad.findElementById("com.quicken.qm2014:id/listView");
			//element.get
			return element;	
		} else {
			WebElement element = Engine.iosd.findElementByXPath("(//XCUIElementTypeStaticText[@name='topRightLabel'])["+i+"]");
			return element;	
			
		}
		
	}
	public WebElement getTransactionAmountValue_android() {
		Helper h = new Helper();
//		List list1=Engine.ad.findElements(By.xpath("//android.support.v7.widget.RecyclerView[contains(@resource-id,'com.quicken.qm2014:id/listView')]"));
		List list1=Engine.ad.findElements(By.id("com.quicken.qm2014:id/listView"));

		System.out.println(list1.size());
		//System.out.println(s.length);
		for(int j=0;j<list1.size();j++)
		{
			System.out.println("index is "+ j);
		  //          System.out.println(list1.get(j).getText());
		    //       System.out.println(list1.get(j));
		      //     list1.get(j).click();
		          // Thread.sleep(5000);
		       }
		return null;
		
	}
	
	public void selectSortFilterOption(String filterBy) throws InterruptedException {
		Helper h = new Helper();
		if (h.getEngine().equalsIgnoreCase("ios")) {
			if (filterBy =="Pending to Cleared" ) {
				String locater = "//XCUIElementTypeStaticText[@name=\"Pending to Cleared \"]";
				MobileElement me = (MobileElement) Engine.iosd.findElement(MobileBy.xpath(locater));
				me.click();
				buttonApply.click();
				Thread.sleep(2000);
			}
			else {
			//String sXpath = "name="+filterBy+" and type='XCUIElementTypeStaticText'";
			String locater = "**/XCUIElementTypeStaticText[`name=="+"\""+filterBy+"\""+"`]";
			MobileElement me = (MobileElement) Engine.iosd.findElement(MobileBy.iOSClassChain(locater));
			me.click();
			buttonApply.click();
			Thread.sleep(2000);
			}
		} else {
			if (filterBy == "Pending to Cleared") {
				Thread.sleep(1000);
				String sXpath = "//android.view.ViewGroup[5]//android.widget.TextView";
				MobileElement me = (MobileElement) Engine.ad.findElement(By.xpath(sXpath));
				me.click();
				buttonApply.click();
				Thread.sleep(3000);
			} else {
				Thread.sleep(1000);
				String sXpath = "//android.widget.TextView[@text="+"\""+filterBy+"\""+"]";
				MobileElement me = (MobileElement) Engine.ad.findElement(By.xpath(sXpath));
				me.click();
				buttonApply.click();
				Thread.sleep(3000);
			}
		}
		
	}
	
	@iOSFindBy(xpath="//XCUIElementTypeButton[@name='Banking & Credit']")
	@AndroidFindBy(xpath="//*[@class='android.widget.ImageButton']")
	public MobileElement backButton;
	
	@iOSFindBy(xpath="//*[@name='All Transactions']")
	@AndroidFindBy(xpath="//*[@text='All Transactions']")
	public MobileElement txtAllTransactions;
	
	//@iOSFindBy(xpath="//XCUIElementTypeButton[@name=\"fab\"]")
	@iOSXCUITFindBy(iOSNsPredicate= "name = 'fab'")
	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.quicken.qm2014:id/fab']")
	public MobileElement addTransaction;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[contains(@name,\"TODAY'S BALANCE\")]/../XCUIElementTypeStaticText[contains(@name,'$')]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"TODAY'S BALANCE\"]/../android.widget.TextView[contains(@text,'$')]")
	public MobileElement txtTodaysBalanceAmount;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[contains(@name,\"TODAY'S BALANCE\")]")
	@AndroidFindBy(xpath="//*[@text=\"TODAY'S BALANCE\"]")
	public MobileElement txtTodaysBalance;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"PROJECTED BALANCE\"]/../*[contains(@name, '$')]")
	@AndroidFindBy(xpath="//*[@text=\"PROJECTED BALANCE\"]/../*[contains(@text, '$')]")
	public MobileElement txtProjectedBalanceAmount;
	
	@AndroidFindBy(id="android:id/search_src_text")
	@iOSXCUITFindBy(iOSNsPredicate= "name = 'Search Transactions'")
	//@iOSFindBy(xpath="//XCUIElementTypeSearchField[@name='Search Transactions']")
	public MobileElement searchTransactionTxtField;
	
	//@iOSFindBy(xpath="//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeButton[1]")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeButton[1]")
	@AndroidFindBy(xpath="@text='NotTaken'")//TBD
	public MobileElement btnCategory;
	
	@iOSFindBy(xpath="//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeButton[2]")
	@AndroidFindBy(xpath="@text='NotTaken'")//TBD
	public MobileElement btnSplit;
	
	@iOSFindBy(xpath="//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeButton[3]")
	@AndroidFindBy(xpath="@text='NotTaken'")//TBD
	public MobileElement btnDelete;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name='No Results Found']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='No Results Found']")
	public MobileElement txtNoResultFound;
	
	@iOSFindBy(id="Save")
	@AndroidFindBy(id="@text='NotTaken'")//TBD
	public MobileElement buttonSave;
	
	@iOSFindBy(id="sort icon")
	@AndroidFindBy(id="com.quicken.qm2014:id/sortBtn")
	public MobileElement buttonSort;
	
	@iOSFindBy(xpath="//*[@name='Apply']")
	@AndroidFindBy(xpath="//*[text='Apply']")
	public MobileElement buttonApply;
	
	@iOSXCUITFindBy(iOSNsPredicate="type = 'XCUIElementTypeImage' AND visible = 1")
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"close\"]/android.widget.ImageView")
	public MobileElement buttonClose;
	
	@iOSFindBy(id="You don't have any transactions.")
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.quicken.qm2014:id/no_data']")
	public MobileElement noTransactionText;
	
	@iOSXCUITFindBy(id = "transactionSettings")
	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.quicken.qm2014:id/reminderBtn']")
	public MobileElement buttonShowReminder;
	
	@iOSXCUITFindBy(iOSNsPredicate = "name = 'Transaction Detail'")
	@AndroidFindBy(xpath="//android.widget.TextView[@text ='Transaction Detail']")
	public MobileElement headerTransactionDetail;
	
	@iOSXCUITFindBy(iOSNsPredicate = "name = 'Show Running Balance'")
	@AndroidFindBy(xpath="//android.widget.TextView[@text ='Show Running Balance']")
	public MobileElement switchShowRunningBalanceText;
	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeSwitch'")
	@AndroidFindBy(xpath="//android.widget.Switch")
	public MobileElement switchRunningBalance;
	
	//----------------- Filter & Sort --------------
	
	@iOSXCUITFindBy(iOSNsPredicate="name='Not Reviewed' and type='XCUIElementTypeStaticText'")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Not Reviewed']")
	public MobileElement filterNotReviewed;
	
	@iOSXCUITFindBy(iOSNsPredicate="name='NOT REVIEWED' and type='XCUIElementTypeStaticText'")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Not Reviewed']")
	public MobileElement filterNotReviewedHeader;
	
	@iOSXCUITFindBy(iOSNsPredicate="name='Clear filters' and type='XCUIElementTypeButton'")
	@AndroidFindBy(xpath="//android.widget.Button[@text='Clear filters']")
	public MobileElement buttonClearFilter;
	
	@iOSXCUITFindBy(iOSNsPredicate="name='Mark all as Reviewed' and type='XCUIElementTypeButton'")
	@AndroidFindBy(xpath="//android.widget.Button[@text='Mark all as Reviewed']")
	public MobileElement buttonMarkAllReviewed;
	
	@iOSXCUITFindBy(iOSNsPredicate="name BEGINSWITH 'Are you sure you want to mark' and type='XCUIElementTypeStaticText'")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text, 'Are you sure you want to mark')]")
	public MobileElement alertMarkAllReviewed;
	
	@iOSFindBy(xpath="//*[@name='Yes']")
	@AndroidFindBy(xpath="//*[@text='Yes']")
	public MobileElement buttonYes;
	
	@iOSFindBy(xpath="//*[@name='No']")
	@AndroidFindBy(xpath="//*[@text='No']")
	public MobileElement buttonNo;
	
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name=='Date New to Old CheckIcon'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text = 'Date New to Old']/../android.widget.ImageView[@content-desc = 'CheckIcon']")
	public MobileElement defaultfilterSelected;
}

