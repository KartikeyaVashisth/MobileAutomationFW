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
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import referee.Commentary;
import referee.Verify;
import support.Engine;
import support.Helper;

public class OverviewPage {
	public OverviewPage () {
		try {
			
			PageFactory.initElements(new AppiumFieldDecorator(Engine.getDriver()),this);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	/*@iOSXCUITFindBy(xpath="//*[normalize-space(@name)='Overview']")
	@AndroidFindBy(xpath="//*[@text='Overview']")
	WebElement overView;
	
	@iOSXCUITFindBy(xpath="//*[normalize-space(@name)='Sign Out']")
	@AndroidFindBy(xpath="//*[@text='Sign Out']")
	WebElement signOut;
	
	@iOSXCUITFindBy(xpath="//*[normalize-space(@name)='Updates']")
	WebElement updates;
	
	@iOSXCUITFindBy(xpath="//*[normalize-space(@name)='Settings']")
	@AndroidFindBy(xpath="//*[@content-desc='More options']")
	WebElement SettingsOrMoreOptions;*/
	
//	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Banking & Credit\"]")
	@iOSXCUITFindBy(iOSNsPredicate = "name = 'Banking & Credit'")
	@AndroidFindBy(xpath="//*[@text='Banking & Credit']")
	public WebElement accountsCard;
	
//	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name CONTAINS 'Checking'`]/XCUIElementTypeStaticText[3]")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Checking'`]/XCUIElementTypeStaticText[`name contains '$'`]")
//	@AndroidFindBy(xpath="//*[@text='Banking & Credit']/../android.widget.TextView[3]")
	@AndroidFindBy(xpath="//*[@text='Checking']/following-sibling::android.widget.TextView[1]")
	public WebElement checkingBalance;
	
//	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name CONTAINS 'Cash'`]/XCUIElementTypeStaticText[3]")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Cash'`]/XCUIElementTypeStaticText[`name contains '$'`]")
//	@AndroidFindBy(xpath="//*[@text='Banking & Credit']/../android.widget.TextView[5]")
	@AndroidFindBy(xpath="//*[@text='Cash']/following-sibling::android.widget.TextView[1]")
	public WebElement cashBalance;
	
//	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name CONTAINS 'Credit'`]/XCUIElementTypeStaticText[-1]")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Credit'`]/XCUIElementTypeStaticText[`name contains '$'`]")
//	@AndroidFindBy(xpath="//*[@text='Banking & Credit']/../android.widget.TextView[5]")
	@AndroidFindBy(xpath="//*[@text='Credit']/following-sibling::android.widget.TextView[1]")
	public WebElement creditBalance;
	
//	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name CONTAINS 'Savings'`]/XCUIElementTypeStaticText[3]")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Savings'`]/XCUIElementTypeStaticText[`name contains '$'`]")
//	@AndroidFindBy(xpath="//*[@text='Banking & Credit']/../android.widget.TextView[7]")
	@AndroidFindBy(xpath="//*[@text='Savings']/following-sibling::android.widget.TextView[1]")
	public WebElement savingsBalance;
	
//	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name CONTAINS 'Other'`]/XCUIElementTypeStaticText[3]")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Other'`]/XCUIElementTypeStaticText[`name contains '$'`]")
//	@AndroidFindBy(xpath="//*[@text='Banking & Credit']/../android.widget.TextView[11]")
	@AndroidFindBy(xpath="//*[@text='Other']/following-sibling::android.widget.TextView[1]")
	public WebElement otherBalance;
	
//	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name CONTAINS 'Total'`]/XCUIElementTypeStaticText[3]")
//	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Checking'`]/XCUIElementTypeOther[`name contains 'Total'`]/XCUIElementTypeStaticText[`name contains '$'`]")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Banking & Credit'`][-2]/**/XCUIElementTypeOther[`name contains 'Total'`][-1]/XCUIElementTypeStaticText[`name contains '$'`]")
//	@AndroidFindBy(xpath="//*[@text='Banking & Credit']/../android.widget.TextView[9]")
	@AndroidFindBy(xpath="//*[@text='Total']/following-sibling::android.widget.TextView[1]")
	public WebElement totalBalance;
	
//	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Property and Debt Account\"]")
	@iOSXCUITFindBy(iOSNsPredicate = "name = 'Property and Debt Account'")
	@AndroidFindBy(xpath="//*[@text='Property and Debt Account']")
	public WebElement propertyAndDebt;
	
//	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Property\"]")
	@iOSXCUITFindBy(iOSNsPredicate = "name = 'Property'")
	@AndroidFindBy(xpath="//*[@text='Property']")
	public WebElement propertyText;
	
//	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Debt\"]")
	@iOSXCUITFindBy(iOSNsPredicate = "name = 'Debt'")
	@AndroidFindBy(xpath="//*[@text='Debt']")
	public WebElement debtText;
	
//	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Add Transaction\"]")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == 'Add Transaction'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Add Transaction']")
	public WebElement addTransaction;
	
//	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Recent Transactions\"]")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Recent Transactions'`]")
	@AndroidFindBy(xpath="//*[@text='Recent Transactions']")
	public WebElement recentTransactionsCard;
	
	/*@iOSXCUITFindBy(xpath="//*[starts-with(@name, 'Top Spending Category')]")
	@AndroidFindBy(xpath="//*[@text='Top Spending Category']")
	public WebElement spendingCard;*/
	
	/*@iOSXCUITFindBy(xpath="//*[starts-with(@name, 'Bank Account Balances Checking Accounts')]")
	@AndroidFindBy(xpath="//android.widget.ImageView")
	public WebElement hambergerIcon;*/
	
	
//	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Top Trending Categories\"]")
	@iOSXCUITFindBy(iOSNsPredicate = "name = 'Top Trending Categories'")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Top Trending Categories']/..")
	public WebElement topTrendingCard;
	
//	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[contains(@name,'Summary')]")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[$name CONTAINS 'Summary'$]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Summary')]")
	public WebElement transactionSummaryCard;
	
	@iOSXCUITFindBy(xpath="//*[starts-with(@name, 'Investing Securities & Cash')]/XCUIElementTypeOther[starts-with(@name, 'Investing Securities & Cash')]")
	@AndroidFindBy(xpath="//*[@text='Investing']/..")
	public WebElement investingCard;
	
	@iOSXCUITFindBy(xpath="//*[starts-with(@name, 'Investing Securities & Cash')]/XCUIElementTypeOther[contains(@name, 'Securities & Cash')]")
	@AndroidFindBy(xpath="//*[@text='Investing']/..//*[@text='Securities & Cash']")
	public WebElement investingSecuritiesCashLabel;
	
	@iOSXCUITFindBy(xpath="//*[starts-with(@name, 'Investing Securities & Cash')]/XCUIElementTypeOther[contains(@name, 'Today\'s Change')]")
	@AndroidFindBy(xpath="//*[@text='Investing']/..//*[@text='Today\'s Change']")
	public WebElement investingTodaysChange;
	
	
	// actual cards..
	@iOSXCUITFindBy(xpath="//*[starts-with(@name, 'Bank Account Balances Checking Accounts')]")
	@AndroidFindBy(xpath="//*[@text='Banking and Credit Account']/..")
	public WebElement ac;
	
	@iOSXCUITFindBy(xpath="//*[starts-with(@name, 'Recent Transactions')]")
	@AndroidFindBy(xpath="//*[@text='Recent Transactions']/..")
	public WebElement rt;
	
	@iOSXCUITFindBy(xpath="//*[starts-with(@name, 'Top Spending Category')]")
	@AndroidFindBy(xpath="//*[@text='Top Trending Categories']/..")
	public WebElement ttc;
	
	@iOSXCUITFindBy(xpath="//*[starts-with(@name, 'Banking and Credit Account') and not(contains(@name, 'Recent Transactions'))]/XCUIElementTypeOther[starts-with(@name, 'Banking and Credit Account Checking')]")
	@AndroidFindBy(xpath="//*[@text='Banking and Credit Account']/..//*[@text='Checking']")
	public WebElement accountsCard_checkingLabel;
	
	@iOSXCUITFindBy(xpath="//*[starts-with(@name, 'Banking and Credit Account') and not(contains(@name, 'Recent Transactions'))]/XCUIElementTypeOther[contains(@name, 'Savings')]")
	@AndroidFindBy(xpath="//*[@text='Banking and Credit Account']/..//*[@text='Savings']")
	public WebElement accountsCard_savingsLabel;
	
	@iOSXCUITFindBy(xpath="//*[starts-with(@name, 'Banking and Credit Account') and not(contains(@name, 'Recent Transactions'))]/XCUIElementTypeOther[contains(@name, 'Credit')]")
	@AndroidFindBy(xpath="//*[@text='Banking and Credit Account']/..//*[@text='Credit']")
	public WebElement accountsCard_creditLabel;
	
	
	@iOSXCUITFindBy(xpath="//*[starts-with(@name, 'Banking and Credit Account') and not(contains(@name, 'Recent Transactions'))]/XCUIElementTypeOther[contains(@name, 'Other')]")
	@AndroidFindBy(xpath="//*[@text='Banking and Credit Account']/..//*[@text='Other']")
	public WebElement accountsCard_otherLabel;
	
	@iOSXCUITFindBy(xpath="//*[starts-with(@name, 'Banking and Credit Account') and not(contains(@name, 'Recent Transactions'))]/XCUIElementTypeOther[contains(@name, 'Total')]")
	@AndroidFindBy(xpath="//*[@text='Banking and Credit Account']/..//*[@text='Total']")
	public WebElement accountsCard_totalLabel;
	
	@iOSXCUITFindBy(xpath="//*[starts-with(@name, 'Banking and Credit Account') and not(contains(@name, 'Recent Transactions'))]/XCUIElementTypeOther[contains(@name, 'Cash')]")
	@AndroidFindBy(xpath="//*[@text='Banking and Credit Account']/..//*[@text='Cash']")
	public WebElement accountsCard_cashLabel;
	
//	@iOSXCUITFindBy(xpath="//*[@name ='No Transaction available']")
	@iOSXCUITFindBy(iOSClassChain="**/*[`name =='No Transaction available'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Recent Transactions']/../android.widget.TextView[@text='No Transaction available']")
	public WebElement recentTxns_NoTxnsAvailable;
	
//	@iOSXCUITFindBy(xpath="//*[@name=concat('Top Trending Categories Last 30 days You don',\"'\",'t have any transactions.')]")
	@iOSXCUITFindBy(xpath="//*[starts-with(@name,'Top Trending Categories Last 30 day')]/XCUIElementTypeStaticText[@name=\"You don't have any transactions.\"]")
	@AndroidFindBy(xpath="//*[@text='Top Trending Categories']/../*[@text=concat('You don',\"'\",'t have any transactions.')]")
	public WebElement tredningCard_YoudontHaveAnyTxn;
	
//	@iOSXCUITFindBy(xpath="//*[@name=concat('Spending Over Time You don',\"'\",'t have any transactions.')]")
	@iOSXCUITFindBy(xpath="//*[starts-with(@name,'Spending Over Time')]/XCUIElementTypeStaticText[@name=\"You don't have any transactions.\"]")
	@AndroidFindBy(xpath="//*[@text='Spending Over Time']/../*[@text=concat('You don',\"'\",'t have any transactions.')]")
	public WebElement spendingOverTimeCard_YoudontHaveAnyTxn;
	
	@iOSXCUITFindBy(xpath="//*[starts-with(@name,'Net Income Over Time')]/XCUIElementTypeStaticText[@name=\"You don't have any transactions.\"]")
	@AndroidFindBy(xpath="//*[@text='Net Income Over Time']/../*[@text=concat('You don',\"'\",'t have any transactions.')]")
	public WebElement netIncomeOverTimeCard_YoudontHaveAnyTxn;
	
	@iOSXCUITFindBy(xpath="//*[@name='Transaction Summary $0 Earned $0 Spent $0']")
	@AndroidFindBy(xpath="//*[@text='Transaction Summary']/../*[@text='$0 Earned']")
	public WebElement txnSummary_zeroEarned;
	
	@iOSXCUITFindBy(xpath="//*[@name='Transaction Summary $0 Earned $0 Spent $0']")
	@AndroidFindBy(xpath="//*[@text='Transaction Summary']/../*[@text='$0 Spent']")
	public WebElement txnSummary_zeroSpent;
	
	@iOSXCUITFindBy(xpath="//*[@name='Transaction Summary $0 Earned $0 Spent $0']")
	@AndroidFindBy(xpath="//*[@text='Transaction Summary']/../*[@text='$0']")
	public WebElement txnSummary_zeroDollar;
	
	@iOSXCUITFindBy(xpath="//*[normalize-space(@name) ='Investing Track your investing accounts and holdings on your phone. Tap to learn more']")
	@AndroidFindBy(xpath="//*[@text='Investing']/../*[@text='Track your investing accounts and holdings on your phone.']")
	public WebElement invCard_zeroDataState;
	
//	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@name='navigationMenu']/XCUIElementTypeOther[@name='navigationMenu']/XCUIElementTypeOther[@name='navigationMenu']")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='navigationMenu'`]")
//	@AndroidFindBy(xpath="//*[@content-desc='navigationMenu']//*[@class='android.widget.ImageView']")
	@AndroidFindBy(accessibility  = "navigationMenu")
	public WebElement hambergerIcon;
	
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[starts-with(@name,'Progress halted Banking')]/XCUIElementTypeScrollView")
	@AndroidFindBy(xpath="//*[@class='android.widget.ScrollView']")
	public WebElement scrollView;
	
	
//	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Spending Over Time\"]")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Spending Over Time'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Spending Over Time']")
	public WebElement spendingOverTimeCard;
	
	
//	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Net Income Over Time\"]")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Net Income by Month'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Net Income Over Time']")
	public WebElement netIncomeOverTimeCard;
	
//	@iOSXCUITFindBy(xpath="//XCUIElementTypeActivityIndicator[@name=\"In progress\"]")
	@iOSXCUITFindBy(iOSNsPredicate = "name = 'In progress'")
	@AndroidFindBy(xpath="//android.widget.ProgressBar")
	public WebElement refreshSpinnerIcon;
	
//	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Investing\"]")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Investing'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Investing']")
	public WebElement investmentCard;
	
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`label contains 'Payee Name:'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@content-desc,'Payee Name')]")
	public WebElement payeeNameText;
	
	//@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name contains 'Transaction Date:'`]")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Transaction Date:'`][-1]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@content-desc,'Transaction Date:')]")
	public WebElement transactionDateText;
	
//	public void scrollUptoAccountsCard() throws Exception {
//		
//		Integer iCount;
//		
//		for (iCount=0; iCount<3; iCount++) {
//			
//			scrollView.swipe(SwipeElementDirection.DOWN, 10, 10, 1000); 
//			
//			if (Verify.objExists_check(this.refreshSpinnerIcon))
//				Verify.waitForObjectToDisappear(this.refreshSpinnerIcon, 1);
//			
//			if (Verify.objExists_check(this.accountsCard))
//				return;
//			
//		}
//		
//	}
	
	
	public void tapOnRecentTransactionsCard() throws Exception {
		
		Verify.waitForObjectToDisappear(refreshSpinnerIcon, 2);
		String sCard = "Recent Transactions";
		
		Helper h = new Helper();
		if (h.getEngine().equals("android")){
			
			Engine.getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ sCard + "\").instance(0))"));
			
		}
		else {
			//String sXpath="//*[@name='Recent Transactions']";
//			String cc="**/XCUIElementTypeStaticText[`name='Recent Transactions'`]";
//			WebElement me = Engine.getDriver().findElement(AppiumBy.iOSClassChain(cc));
//			String me_id = me.getId();
//			HashMap<String, String> scrollObject = new HashMap<String, String>();
//			scrollObject.put("element", me_id);
////			scrollObject.put("predicateString", "label == 'Recent Transactions'");
//			scrollObject.put("toVisible", "not an empty string");
//			scrollObject.put("direction", "down");
//			Engine.getDriver().executeScript("mobile:scroll", scrollObject);  // scroll to the target element
//			Thread.sleep(1000);
			
			 if(Engine.getDriver() instanceof IOSDriver){
	                HashMap<String, String> scrollObject = new HashMap<String, String>();
	                scrollObject.put("predicateString", "value ='Recent Transactions'");
	                scrollObject.put("toVisible", "true");
	                scrollObject.put("direction", "up");
	                (Engine.getDriver()).executeScript("mobile: swipe", scrollObject);
	                Thread.sleep(1000);
	            }
		}
		this.recentTransactionsCard.click();
		Thread.sleep(2000);
	}
	
	public void tapOnPropertyDebtCard() throws Exception{
		
		Verify.waitForObjectToDisappear(refreshSpinnerIcon, 2);

		Helper h = new Helper();
		if (h.getEngine().equals("ios")){

			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("predicateString", "value ='Property and Debt'");
			scrollObject.put("toVisible", "true");
			scrollObject.put("direction", "up");
			(Engine.getDriver()).executeScript("mobile: swipe", scrollObject);
			Thread.sleep(1000);
			Engine.getDriver().findElement(By.name("Property and Debt")).click();
			Thread.sleep(1000);	
		}
		else {
			String sXpath="//android.widget.TextView[@text='Property and Debt']";
			Engine.getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Property and Debt\").instance(0))"));
			Thread.sleep(1000);
			Engine.getDriver().findElement(By.xpath(sXpath)).click();
			Thread.sleep(1000);
		}
	}
	
	public void tapOnTrendingCard() throws Exception{
		
		Verify.waitForObjectToDisappear(refreshSpinnerIcon, 2);
		
		Helper h = new Helper();
		if (h.getEngine().equals("ios")){
			
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("predicateString", "value ='Top Trending Categories'");
			scrollObject.put("toVisible", "true");
			scrollObject.put("direction", "up");
			(Engine.getDriver()).executeScript("mobile: swipe", scrollObject);
			Thread.sleep(2000);
			Engine.getDriver().findElement(By.name("Top Trending Categories")).click();
			Thread.sleep(2000);	
			
		}
		else {
			String sXpath="//android.widget.TextView[@text='Top Trending Categories']";
			Engine.getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Top Trending Categories\").instance(0))"));
			Thread.sleep(1000);
			Engine.getDriver().findElement(By.xpath(sXpath)).click();
			Thread.sleep(2000);
		}
		
		Verify.waitForObjectToDisappear(refreshSpinnerIcon, 3);
	}

	public void scrollTillCard(String cardName) throws Exception {
		
		Verify.waitForObjectToDisappear(refreshSpinnerIcon, 3);
		
		Helper h = new Helper();
		if (h.getEngine().equals("ios")){
			
			//String sXpath="//*[@name ='"+cardName+"']";
//			String cc="**/*[`name =='"+cardName+"'`]";
//			WebElement me = Engine.getDriver().findElement(AppiumBy.iOSClassChain(cc));
//			String me_id = me.getId();
//			HashMap<String, String> scrollObject = new HashMap<String, String>();
//			scrollObject.put("element", me_id);
//			scrollObject.put("toVisible", "not an empty string");
//			Engine.getDriver().executeScript("mobile:scroll", scrollObject);  // scroll to the target element
//			Thread.sleep(1000);
			
			HashMap<String, String> scrollObject = new HashMap<String, String>();
            scrollObject.put("predicateString", "value ='"+cardName+"'");
            scrollObject.put("toVisible", "true");
            scrollObject.put("direction", "up");
            ((IOSDriver) Engine.getDriver()).executeScript("mobile: swipe", scrollObject);
            Thread.sleep(1000);
		}
		
		else {
			Engine.getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ cardName + "\").instance(0))"));
			Thread.sleep(1000);
		}
		
	}
	
	//Adding new method for scrolling----------------------------------------------------
	
	public void scroll_down() throws Exception {
		Helper h = new Helper();
		
		if (h.getEngine().equalsIgnoreCase("android")) {
			/*
			Dimension size = Engine.getDriver().manage().window().getSize();
			int y_start = (int) (size.width * 1.20);
			int y_end = (int) (size.width * 0.03);
			int x = 380;
			Engine.getDriver().swipe(x, y_start, x, y_end, 3000);
			*/
			verticalScrollDownAndroid();
		} else {
			JavascriptExecutor js1 = (JavascriptExecutor) Engine.getDriver() ;
		    HashMap scrollObject = new HashMap();
		    scrollObject.put("direction", "down");
		    js1.executeScript("mobile: scroll", scrollObject);
		}
		
	}
	
    public void verticalScrollDownAndroid() throws Exception {
        Dimension size = Engine.getDriver().manage().window().getSize();
        int y_start = (int) (size.width * 1.20);
		int y_end = (int) (size.width * 0.03);
		int x = 380;
		
//		TouchAction touchAction = new TouchAction(Engine.getDriver());
// 
//		touchAction
//                .press(point(x, y_start))
//                .waitAction(waitOptions(ofMillis(2000)))
//                .moveTo(point(x, y_end))
//                .release().perform();
		
		PointerInput finger1 = new PointerInput(Kind.TOUCH, "finger1");
		Sequence sequence = new Sequence(finger1, 1)
				.addAction(finger1.createPointerMove(Duration.ZERO, Origin.viewport(), x, y_start)) 
				.addAction(finger1.createPointerDown(MouseButton.LEFT.asArg())) 
				.addAction(new Pause(finger1, Duration.ofMillis(200)))
				.addAction(finger1.createPointerMove(Duration.ofMillis(100), Origin.viewport(), x, y_end)) 
				.addAction(finger1.createPointerUp(MouseButton.LEFT.asArg()));
		
		Engine.getDriver().perform(Collections.singletonList(sequence));
		Thread.sleep(1000);
    }
    
//    public void verticalScrollUpAndroid() throws Exception {
//    	
//        Dimension size = Engine.getDriver().manage().window().getSize();
//         
//        int y_start = (int) (size.height * 0.9);
//		int y_end = (int) (size.height * 0.4);
//		int x = (int) (size.width * 0.50);
//		
//		TouchAction touchAction = new TouchAction(Engine.getDriver());
// 
//		touchAction
//                .press(point(x, y_end))
//                .waitAction(waitOptions(ofMillis(1000)))
//                .moveTo(point(x, y_start))
//                .release().perform();
//    }
	
    //-------------------------------------------------------------------------------------
	
//	public void quicken_scroll() throws InterruptedException {
//		
//		Helper h = new Helper();
//		
//		if (h.getEngine().equals("android")) {
//			scrollView.swipe(SwipeElementDirection.UP, 2, 2, 1000);
//			Thread.sleep(2000);		
//		}
//		else {
//			
//			Dimension size = Engine.getDriver().manage().window().getSize();
//			Engine.getDriver().swipe(size.width - 10, size.height * 4 / 8, size.width - 10, size.height / 7, 500);
//			Thread.sleep(2000);
//			
//			
//		}
//	}
//
//	public void quicken_scroll_mobile() throws InterruptedException {
//		
//		Helper h = new Helper();
//		
//		if (h.getEngine().equals("android")) {
//			Dimension size = Engine.getDriver().manage().window().getSize();
//			Engine.getDriver().swipe(size.width - 10, size.height * 4 / 8, size.width - 10, size.height / 7, 500);
//			Thread.sleep(1000);	
//		}
//		else {
//			
//			Dimension size = Engine.getDriver().manage().window().getSize();
//			Engine.getDriver().swipe(size.width - 10, size.height * 4 / 8, size.width - 10, size.height / 7, 500);
//			Thread.sleep(1000);
//			
//			
//		}
//	}
	
	public void tapOnTransactionSummaryCard() throws Exception{
		
		Verify.waitForObjectToDisappear(refreshSpinnerIcon, 2);
		
		Helper h = new Helper();
		String currentMonth = h.getCurrentMonth();
		
		if (h.getEngine().equals("android")){
			String sXpath="//android.widget.TextView[@text='"+currentMonth+" Summary']";
			Engine.getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Summary\").instance(0))"));
			Thread.sleep(1000);
			Engine.getDriver().findElement(By.xpath(sXpath)).click();
			Thread.sleep(5000);
		
		}
		else {
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("predicateString", "value ='"+currentMonth+" Summary'");
			scrollObject.put("toVisible", "true");
			scrollObject.put("direction", "up");
			(Engine.getDriver()).executeScript("mobile: swipe", scrollObject);
			Thread.sleep(1000);
			transactionSummaryCard.click();
			Thread.sleep(1000);
		}
	}
	
	public void tapOnBillsAndIncomeCard() throws Exception{

		Verify.waitForObjectToDisappear(refreshSpinnerIcon, 2);

		Helper h = new Helper();
		if (h.getEngine().equals("android")){
			String sXpath="//android.widget.TextView[@text='Bills & Income']";
			Engine.getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Bills & Income\").instance(0))"));
			Thread.sleep(1000);
			Engine.getDriver().findElement(By.xpath(sXpath)).click();
			Thread.sleep(2000);
		}
		else {
//			String cc="**/*[`name=='Bills & Income'`]";
//			WebElement me = Engine.getDriver().findElement(AppiumBy.iOSClassChain(cc));
//			String me_id = me.getId();
//			HashMap<String, String> scrollObject = new HashMap<String, String>();
//			scrollObject.put("element", me_id);
////			scrollObject.put("predicateString", "label == 'Bills & Income'");
//			scrollObject.put("toVisible", "not an empty string");
//			Engine.getDriver().executeScript("mobile:scroll", scrollObject);  // scroll to the target element
//			Thread.sleep(1000);
			if(Engine.getDriver().findElement(By.name("Bills & Income")).isDisplayed()) {
				Engine.getDriver().findElement(By.name("Bills & Income")).click();
			}else {
				HashMap<String, String> scrollObject = new HashMap<String, String>();
				scrollObject.put("predicateString", "value ='Bills & Income'");
				scrollObject.put("toVisible", "true");
				scrollObject.put("direction", "up");
				(Engine.getDriver()).executeScript("mobile: swipe", scrollObject);
				Thread.sleep(1000);

				Engine.getDriver().findElement(By.name("Bills & Income")).click();
				Thread.sleep(2000);
			}
		}
	}
	
	public void tapOnNetWorthCard() throws Exception{

		Verify.waitForObjectToDisappear(refreshSpinnerIcon, 2);

		Helper h = new Helper();
		if (h.getEngine().equals("android")){
			String sXpath="//android.widget.TextView[@text='Net Worth']";
			Engine.getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Net Worth\").instance(0))"));
			Thread.sleep(1000);
			Engine.getDriver().findElement(By.xpath(sXpath)).click();
			Thread.sleep(2000);
			Verify.waitForObjectToDisappear(this.refreshSpinnerIcon, 1);
			Thread.sleep(12000);
		}
		else {
			
			if(Engine.getDriver().findElement(By.name("Net Worth")).isDisplayed()) {
				Engine.getDriver().findElement(By.name("Net Worth")).click();
			}else {
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("predicateString", "value ='Net Worth'");
			scrollObject.put("toVisible", "true");
			scrollObject.put("direction", "up");
			(Engine.getDriver()).executeScript("mobile: swipe", scrollObject);
			Thread.sleep(1000);
			
			Engine.getDriver().findElement(By.name("Net Worth")).click();
			Thread.sleep(2000);
			Verify.waitForObjectToDisappear(this.refreshSpinnerIcon, 1);
			Thread.sleep(10000);
			}
		}
	}

	public void tapOnSpendingOverTimeCard() throws Exception{
		
		Verify.waitForObjectToDisappear(refreshSpinnerIcon, 3);

		Helper h = new Helper();
		if (h.getEngine().equals("android")){
			String sXpath="//android.widget.TextView[@text='Spending Over Time']";
			Engine.getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Spending Over Time\").instance(0))"));
			Thread.sleep(1000);
			Engine.getDriver().findElement(By.xpath(sXpath)).click();
			Thread.sleep(2000);
		}
		else {
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("predicateString", "value ='Spending Over Time'");
			scrollObject.put("toVisible", "true");
			scrollObject.put("direction", "up");
			(Engine.getDriver()).executeScript("mobile: swipe", scrollObject);
			Thread.sleep(1000);
			
			Engine.getDriver().findElement(By.name("Spending Over Time")).click();
			Thread.sleep(1000);
		}
	}
	
	public void tapOnNetIncomeOverTimeCard() throws Exception{
		
		Verify.waitForObjectToDisappear(refreshSpinnerIcon, 2);

		Helper h = new Helper();
		if (h.getEngine().equals("android")){
			String sXpath="//android.widget.TextView[@text='Net Income by Month']";
			Engine.getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Net Income by Month\").instance(0))"));
			Thread.sleep(1000);
			Engine.getDriver().findElement(By.xpath(sXpath)).click();
			Thread.sleep(2000);
		}
		else {
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("predicateString", "value ='Net Income by Month'");
			scrollObject.put("toVisible", "true");
			scrollObject.put("direction", "up");
			(Engine.getDriver()).executeScript("mobile: swipe", scrollObject);
			Thread.sleep(1000);
			
			Engine.getDriver().findElement(By.name("Net Income by Month")).click();
			Thread.sleep(1000);
		}
	}
	
	public void swipeFromBottomToUp() 
    {       
     try  {
//             
//              
//              Helper h = new Helper();
//              
//              if (h.getEngine().equals("android")) {
//            	  scrollView.swipe(SwipeElementDirection.DOWN, 2, 2, 1000);  
//              }
//              else
//              {
//            	  /*
//            	  String elementID = this.accountsCard.getId();
//            	  HashMap<String, String> scrollObject = new HashMap<String, String>();
//            	  scrollObject.put("direction", "down");
//            	  scrollObject.put("element", elementID);
//            	  Engine.getDriver().executeScript("mobile: swipe", scrollObject); 
//            	  */ 
//              }
            Dimension size = Engine.getDriver().manage().window().getSize();
            int y_start = (int) (size.width * 1.20);
      		int y_end = (int) (size.width * 0.03);
      		int x = 380;
      		
//      		TouchAction touchAction = new TouchAction(Engine.getDriver());
//       
//      		touchAction
//                      .press(point(x, y_end))
//                      .waitAction(waitOptions(ofMillis(1000)))
//                      .moveTo(point(x, y_start))
//                      .release().perform(); 
      		
      		PointerInput finger1 = new PointerInput(Kind.TOUCH, "finger1");
    		Sequence sequence = new Sequence(finger1, 1)
    				.addAction(finger1.createPointerMove(Duration.ZERO, Origin.viewport(), x, y_start)) 
    				.addAction(finger1.createPointerDown(MouseButton.LEFT.asArg())) 
    				.addAction(new Pause(finger1, Duration.ofMillis(200)))
    				.addAction(finger1.createPointerMove(Duration.ofMillis(100), Origin.viewport(), x, y_end)) 
    				.addAction(finger1.createPointerUp(MouseButton.LEFT.asArg()));
    		
    		Engine.getDriver().perform(Collections.singletonList(sequence));
    		Thread.sleep(1000);
              
        }
           catch (Exception e) 
            {
                Commentary.log(LogStatus.FAIL, "Swipe down Failed "+e.getMessage());
            }   
                         
    }
	
	public void navigateToAcctList() throws Exception{
		
		if (!Verify.objExists(accountsCard)) {
			swipeFromBottomToUp();	
			Verify.waitForObjectToDisappear(refreshSpinnerIcon, 2);
		}	
		accountsCard.click();
		Thread.sleep(10000);
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		Verify.waitForObject(bcc.allTransactionButton, 1);
	}
	
	public void tapOnBudgetCard() throws Exception {
		
		DateFormat date =  new SimpleDateFormat("MMMM");
		Date date1 = new Date();
		String sCard = date.format(date1).toString();
		
		sCard = sCard +" "+"Budget";
		Verify.waitForObjectToDisappear(refreshSpinnerIcon, 2);
		
		Helper h = new Helper();
		if (h.getEngine().equals("android")){
			String sXpath="//android.widget.TextView[@text='"+sCard+"']";
			Engine.getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ sCard + "\").instance(0))"));
			Thread.sleep(1000);
			Engine.getDriver().findElement(By.xpath(sXpath)).click();
			Thread.sleep(1000);
		}
		else {
			//String sXpath="//*[@name='"+sCard+"']";
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("predicateString", "value ='"+sCard+"'");
			scrollObject.put("toVisible", "true");
			scrollObject.put("direction", "up");
			(Engine.getDriver()).executeScript("mobile: swipe", scrollObject);
			Thread.sleep(1000);
			
			Engine.getDriver().findElement(By.name(sCard)).click();
			Thread.sleep(1000);
		}
		
	}
	
	public void tapOnInvestingCard() throws Exception {
		
		Verify.waitForObjectToDisappear(refreshSpinnerIcon, 2);
		
		Helper h = new Helper();
		if (h.getEngine().equals("android")){
			String sXpath="//android.widget.TextView[@text='Investments']";
			Engine.getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Investments\").instance(0))"));
			Thread.sleep(1000);
			Engine.getDriver().findElement(By.xpath(sXpath)).click();
			Thread.sleep(1000);
		}
		else {
			String cc="**/XCUIElementTypeOther[`name='Investments'`]";
			
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("predicateString", "value ='Net Income by Month'");
			scrollObject.put("toVisible", "true");
			scrollObject.put("direction", "up");
			(Engine.getDriver()).executeScript("mobile: swipe", scrollObject); 
			Thread.sleep(1000);
			
			Engine.getDriver().findElement(AppiumBy.iOSClassChain(cc)).click();
			Thread.sleep(1000);
		}
	}
	
	public void scrollToTop() throws Exception{
		
		Helper h = new Helper();
//		if (h.getEngine().equals("android"))
//			scrollUptoAccountsCard();
//			
//		else
//			scrollToTop_IOS();

		if (h.getEngine().equals("android")){
			String sXpath="//android.widget.TextView[@text='Banking & Credit']";
			Engine.getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Banking & Credit\").instance(0))"));
			Thread.sleep(1000);
		}
		else {
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("predicateString", "value ='Banking & Credit");
			scrollObject.put("toVisible", "true");
			scrollObject.put("direction", "down");
			(Engine.getDriver()).executeScript("mobile: swipe", scrollObject);
			Thread.sleep(1000);
		}
	}
	
//	public void scrollToTop_IOS() throws Exception{
//		
//		Integer iCount;
//		
//		for (iCount=0; iCount<3; iCount++) {
//			
//			Dimension size = Engine.getDriver().manage().window().getSize();
//			System.out.println(size.height);
//			System.out.println(size.height/4);
//			Engine.getDriver().swipe(size.width - 10, size.height/4, size.width - 10, size.height-50, 500);
//			
//			if (Verify.objExists(this.refreshSpinnerIcon))
//				Verify.waitForObjectToDisappear(this.refreshSpinnerIcon, 1);
//			
//			if (Verify.objExists(this.accountsCard))
//				return;
//			
//		}		
//	}

//	public void scroll_down() {
//		Helper h = new Helper();
//		
//		if (h.getEngine().equalsIgnoreCase("android")) {
//			
//			Dimension size = Engine.getDriver().manage().window().getSize();
//			int y_start = (int) (size.width * 1.20);
//			int y_end = (int) (size.width * 0.03);
//			int x = 380;
//			Engine.getDriver().swipe(x, y_start, x, y_end, 3000);
//		} else {
//			JavascriptExecutor js1 = (JavascriptExecutor) Engine.iosd ;
//		    HashMap scrollObject = new HashMap();
//		    scrollObject.put("direction", "down");
//		    js1.executeScript("mobile: scroll", scrollObject);
//		}
//		
//	}
//	
	public void scrollToSpendingCard() throws Exception {
		
		Helper h = new Helper();
		
		if (h.getEngine().equals("android"))
			spendingOverTime_Visible_Android();
		else
			spendingOverTime_Visible_IOS();	
	}
	
	protected void spendingOverTime_Visible_IOS() throws Exception {
		
		Helper h = new Helper();
		
		String shortMonth = h.getLastSixMonths()[0];
//		String sXpath = construct_SOTmonth(shortMonth);
//		sXpath = "//XCUIElementTypeOther[contains(@name,'Spending Over Time')]//XCUIElementTypeStaticText[@name ='APR']";
//		WebElement me = Engine.getDriver().findElement(By.xpath(sXpath));
		
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("predicateString", "value ='"+shortMonth.toUpperCase()+"'");
		scrollObject.put("toVisible", "true");
		scrollObject.put("direction", "up");
		(Engine.getDriver()).executeScript("mobile: swipe", scrollObject); 
		Thread.sleep(1000);
	}
	
	protected void spendingOverTime_Visible_Android() throws Exception {
		
		Helper h = new Helper();
		
		String shortMonth = h.getLastSixMonths()[0];
		
		String sXpath = construct_SOTmonth(shortMonth);
		//Engine.getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Spending Over Time\").instance(0))"));
		//Engine.getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"APR\").instance(0))"));
		Engine.getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""+shortMonth.toUpperCase()+"\").instance(1))"));
		Thread.sleep(1000);
		//System.out.println(Verify.objExists((WebElement)Engine.getDriver().findElement(By.xpath(sXpath))));
		Thread.sleep(1000);	
	}
	
	protected String construct_SOTmonth(String shortMonth) {
		
		Helper h = new Helper();
		String xpath;
		
		if (h.getEngine().equals("android"))
			xpath = "//*[@text='Spending Over Time']/..//*[@text='"+shortMonth.toUpperCase()+"']";
		else
			xpath = "//*[@name='Spending Over Time']/..//*[@name='"+shortMonth.toUpperCase()+"']";
			
		return xpath;
	}
	
	public Boolean verifyMonthOnSpendingOverTime(String sMonth) throws Exception {
		
		Helper h = new Helper();
		String [] shortMonths = h.getLastSixMonths();
		
		Integer iCount;
		String sXpath;
		WebElement me;
		
		sXpath=construct_SOTmonth(sMonth);
		
		if (h.getEngine().equals("android"))
			return Verify.objExists(Engine.getDriver().findElement(By.xpath(sXpath)));
		else
			return Verify.objExists(Engine.getDriver().findElement(By.xpath(sXpath)));
	}
	
	public void tapOnAddTransaction() throws Exception {
		
		Verify.waitForObjectToDisappear(refreshSpinnerIcon, 2);
		addTransaction.click();
		Thread.sleep(1000);
	}
	
	public void scrollToTransactionSummaryCard() throws Exception{
		
		Verify.waitForObjectToDisappear(refreshSpinnerIcon, 2);
		Integer iCount = 0;
		
		Helper h = new Helper();
		String currentMonth = h.getCurrentMonth();
		
		if (h.getEngine().equals("android")){
//			String sXpath="//android.widget.TextView[@text='"+currentMonth+" Summary']";
//			Engine.getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Summary\").instance(0))"));
			Engine.getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Budget\").instance(0))"));
			Thread.sleep(1000);
//			Engine.getDriver().findElement(By.xpath(sXpath)).click();
//			Thread.sleep(3000);
		}
		else {
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("predicateString", "value ='"+currentMonth+" Summary'");
			scrollObject.put("toVisible", "true");
			scrollObject.put("direction", "up");
			(Engine.getDriver()).executeScript("mobile: swipe", scrollObject);
			Thread.sleep(1000);
			//Engine.getDriver().findElement(By.name("'"+currentMonth+" Summary'")).click();
//			transactionSummaryCard.click();
//			Thread.sleep(1000);
		}
	}


	public void navigateToDashboard() throws Exception {

		SettingsPage sp = new SettingsPage();
		sp.clickOnDashboardOption();
		Thread.sleep(1000);
	}

	public void navigateToAccounts() throws Exception {

		SettingsPage sp = new SettingsPage();
		sp.clickOnAccountsOption();
		Thread.sleep(1000);
	}

	public void navigateToAllTransactions() throws Exception {

		SettingsPage sp = new SettingsPage();
		sp.clickOnAllTransactionsOption();
		Thread.sleep(1000);

	}
	
	public void navigateToBills() throws Exception {
		
		SettingsPage sp = new SettingsPage();
		sp.clickOnBillsOption();
		Thread.sleep(1000);
			
		}
	
	public void navigateToInvesting() throws Exception {
		
		SettingsPage sp = new SettingsPage();
		sp.clickOnInvestingOption();
		Thread.sleep(1000);
			
		}
	
	public void navigateToReports() throws Exception {
		
		SettingsPage sp = new SettingsPage();
		sp.clickOnReportsOption();
		Thread.sleep(1000);
			
		}
	
	public void navigateToMonthlySummary() throws Exception {
		
		SettingsPage sp = new SettingsPage();
		sp.clickOnMonthlySummaryOption();
		Thread.sleep(1000);
			
		}
	
	public void navigateToNetIncome() throws Exception {
		
		SettingsPage sp = new SettingsPage();
		sp.clickOnNetIncomeOption();
		Thread.sleep(1000);
			
		}
	
	public void navigateToNetWorth() throws Exception {
		
		SettingsPage sp = new SettingsPage();
		sp.clickOnNetWorthOption();
		Thread.sleep(1000);
			
		}
	
	public void navigateToSpendingByCategory() throws Exception {
		
		SettingsPage sp = new SettingsPage();
		sp.clickOnSpendingByCategoryOption();
		Thread.sleep(1000);
			
		}
	
	public void navigateToSpendingOverTime() throws Exception {
		
		SettingsPage sp = new SettingsPage();
		sp.clickOnSpendingOverTimeOption();
		Thread.sleep(1000);
			
		}
	
	public void navigateToProfile() throws Exception {
		
		SettingsPage sp = new SettingsPage();
		sp.clickOnProfileOption();
		Thread.sleep(1000);
			
		}
	
	public void navigateToSettings() throws Exception {
		
		SettingsPage sp = new SettingsPage();
		sp.clickOnSettingsOption();
		Thread.sleep(1000);
			
		}
	
	public void navigateToCustomizeDashboard() throws Exception {
		
		SettingsPage sp = new SettingsPage();
		sp.clickOnCustomizeDashboardOption();
		Thread.sleep(1000);
			
		}
	
	public void navigateToManageCategories() throws Exception {
		
		SettingsPage sp = new SettingsPage();
		sp.clickOnManageCategoriesOption();
		Thread.sleep(1000);
			
		}

	public void navigateToAccountManagement() throws Exception {
		
		SettingsPage sp = new SettingsPage();
		sp.clickOnAccountsManagementOption();
		Thread.sleep(1000);
			
		}
	
	public void navigateToManageAlerts() throws Exception {
		
		SettingsPage sp = new SettingsPage();
		sp.clickOnManageAlertsOption();
		Thread.sleep(1000);
			
		}

	public void navigateToRenamingRules() throws Exception{

		SettingsPage sp = new SettingsPage();
		sp.clickOnSettingsOption();

		sp.rulesOption.click();
		Thread.sleep(1000);
		sp.renamingRulesOption.click();
		Thread.sleep(1000);
	}

	public void navigateToMemorizedPayees() throws Exception{

		SettingsPage sp = new SettingsPage();
		sp.clickOnSettingsOption();

		sp.rulesOption.click();
		Thread.sleep(1000);
		sp.memorizedPayeesOption.click();
		Thread.sleep(1000);
	}

	
	public boolean isEmpty (String s) {
		   return s == null || s.length() == 0;
	}
	
	public int listRecentTransactions(String sText, String sString) {
		
		 if (isEmpty(sText) || isEmpty(sString)) {
	            return 0;
	        }

	        int i = 0, count = 0;
	        while (true)
	        {
	            i = sText.indexOf(sString, i);
	            if (i != -1)
	            {
	                count ++;
	                i += sString.length();
	            }
	            else {
	                break;
	            }
	        }	
			
			return count;
			
	}
	
	


}
