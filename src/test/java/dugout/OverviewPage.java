package dugout;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.bcel.verifier.VerifierFactoryObserver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import referee.Commentary;
import referee.Verify;
import support.Engine;
import support.Helper;
import warroom.kEngine;

public class OverviewPage {
	public OverviewPage () {
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
	
	/*@iOSFindBy(xpath="//*[normalize-space(@name)='Overview']")
	@AndroidFindBy(xpath="//*[@text='Overview']")
	MobileElement overView;
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Sign Out']")
	@AndroidFindBy(xpath="//*[@text='Sign Out']")
	MobileElement signOut;
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Updates']")
	MobileElement updates;
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Settings']")
	@AndroidFindBy(xpath="//*[@content-desc='More options']")
	MobileElement SettingsOrMoreOptions;*/
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Banking & Credit\"]")
	@AndroidFindBy(xpath="//*[@text='Banking & Credit']")
	public MobileElement accountsCard;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name='Checking']/../XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//*[@text='Banking & Credit']/../android.widget.TextView[3]")
	public MobileElement checkingBalance;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name='Cash']/../XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//*[@text='Banking & Credit']/../android.widget.TextView[5]")
	public MobileElement cashBalance;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name='Credit']/../XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//*[@text='Banking & Credit']/../android.widget.TextView[5]")
	public MobileElement creditBalance;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name='Savings']/../XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//*[@text='Banking & Credit']/../android.widget.TextView[7]")
	public MobileElement savingsBalance;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name='Other']/../XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//*[@text='Banking & Credit']/../android.widget.TextView[11]")
	public MobileElement otherBalance;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name='Total']/../XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//*[@text='Banking & Credit']/../android.widget.TextView[9]")
	public MobileElement totalBalance;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Property and Debt Account\"]")
	@AndroidFindBy(xpath="//*[@text='Property and Debt Account']")
	public MobileElement propertyAndDebt;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Property\"]")
	@AndroidFindBy(xpath="//*[@text='Property']")
	public MobileElement propertyText;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Debt\"]")
	@AndroidFindBy(xpath="//*[@text='Debt']")
	public MobileElement debtText;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Add Transaction\"]")
	@AndroidFindBy(xpath="//*[@text='Add Transaction']")
	public MobileElement addTransaction;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Recent Transactions\"]")
	@AndroidFindBy(xpath="//*[@text='Recent Transactions']")
	public MobileElement recentTransactionsCard;
	
	/*@iOSFindBy(xpath="//*[starts-with(@name, 'Top Spending Category')]")
	@AndroidFindBy(xpath="//*[@text='Top Spending Category']")
	public MobileElement spendingCard;*/
	
	/*@iOSFindBy(xpath="//*[starts-with(@name, 'Bank Account Balances Checking Accounts')]")
	@AndroidFindBy(xpath="//android.widget.ImageView")
	public MobileElement hambergerIcon;*/
	
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Top Trending Categories\"]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Top Trending Categories']/..")
	public MobileElement topTrendingCard;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[contains(@name,'Summary')]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Summary')]")
	public MobileElement transactionSummaryCard;
	
	@iOSFindBy(xpath="//*[starts-with(@name, 'Investing Securities & Cash')]/XCUIElementTypeOther[starts-with(@name, 'Investing Securities & Cash')]")
	@AndroidFindBy(xpath="//*[@text='Investing']/..")
	public MobileElement investingCard;
	
	@iOSFindBy(xpath="//*[starts-with(@name, 'Investing Securities & Cash')]/XCUIElementTypeOther[contains(@name, 'Securities & Cash')]")
	@AndroidFindBy(xpath="//*[@text='Investing']/..//*[@text='Securities & Cash']")
	public MobileElement investingSecuritiesCashLabel;
	
	@iOSFindBy(xpath="//*[starts-with(@name, 'Investing Securities & Cash')]/XCUIElementTypeOther[contains(@name, 'Today\'s Change')]")
	@AndroidFindBy(xpath="//*[@text='Investing']/..//*[@text='Today\'s Change']")
	public MobileElement investingTodaysChange;
	
	
	// actual cards..
	@iOSFindBy(xpath="//*[starts-with(@name, 'Bank Account Balances Checking Accounts')]")
	@AndroidFindBy(xpath="//*[@text='Banking and Credit Account']/..")
	public MobileElement ac;
	
	@iOSFindBy(xpath="//*[starts-with(@name, 'Recent Transactions')]")
	@AndroidFindBy(xpath="//*[@text='Recent Transactions']/..")
	public MobileElement rt;
	
	@iOSFindBy(xpath="//*[starts-with(@name, 'Top Spending Category')]")
	@AndroidFindBy(xpath="//*[@text='Top Trending Categories']/..")
	public MobileElement ttc;
	
	@iOSFindBy(xpath="//*[starts-with(@name, 'Banking and Credit Account') and not(contains(@name, 'Recent Transactions'))]/XCUIElementTypeOther[starts-with(@name, 'Banking and Credit Account Checking')]")
	@AndroidFindBy(xpath="//*[@text='Banking and Credit Account']/..//*[@text='Checking']")
	public MobileElement accountsCard_checkingLabel;
	
	@iOSFindBy(xpath="//*[starts-with(@name, 'Banking and Credit Account') and not(contains(@name, 'Recent Transactions'))]/XCUIElementTypeOther[contains(@name, 'Savings')]")
	@AndroidFindBy(xpath="//*[@text='Banking and Credit Account']/..//*[@text='Savings']")
	public MobileElement accountsCard_savingsLabel;
	
	@iOSFindBy(xpath="//*[starts-with(@name, 'Banking and Credit Account') and not(contains(@name, 'Recent Transactions'))]/XCUIElementTypeOther[contains(@name, 'Credit')]")
	@AndroidFindBy(xpath="//*[@text='Banking and Credit Account']/..//*[@text='Credit']")
	public MobileElement accountsCard_creditLabel;
	
	
	@iOSFindBy(xpath="//*[starts-with(@name, 'Banking and Credit Account') and not(contains(@name, 'Recent Transactions'))]/XCUIElementTypeOther[contains(@name, 'Other')]")
	@AndroidFindBy(xpath="//*[@text='Banking and Credit Account']/..//*[@text='Other']")
	public MobileElement accountsCard_otherLabel;
	
	@iOSFindBy(xpath="//*[starts-with(@name, 'Banking and Credit Account') and not(contains(@name, 'Recent Transactions'))]/XCUIElementTypeOther[contains(@name, 'Total')]")
	@AndroidFindBy(xpath="//*[@text='Banking and Credit Account']/..//*[@text='Total']")
	public MobileElement accountsCard_totalLabel;
	
	@iOSFindBy(xpath="//*[starts-with(@name, 'Banking and Credit Account') and not(contains(@name, 'Recent Transactions'))]/XCUIElementTypeOther[contains(@name, 'Cash')]")
	@AndroidFindBy(xpath="//*[@text='Banking and Credit Account']/..//*[@text='Cash']")
	public MobileElement accountsCard_cashLabel;
	
	@iOSFindBy(xpath="//*[@name ='No Transaction available']")
	@AndroidFindBy(xpath="//*[@text='Recent Transactions']/../*[@text='No Transaction available']")
	public MobileElement recentTxns_NoTxnsAvaialable;
	
	//@iOSFindBy(xpath="//*[@name=concat('Top Trending Categories Last 30 days You don',\"'\",'t have any transactions.')]")
	@iOSFindBy(xpath="//*[starts-with(@name,'Top Trending Categories Last 30 day')]/XCUIElementTypeStaticText[@name=\"You don't have any transactions.\"]")
	@AndroidFindBy(xpath="//*[@text='Top Trending Categories']/../*[@text=concat('You don',\"'\",'t have any transactions.')]")
	public MobileElement tredningCard_YoudontHaveAnyTxn;
	
	//@iOSFindBy(xpath="//*[@name=concat('Spending Over Time You don',\"'\",'t have any transactions.')]")
	@iOSFindBy(xpath="//*[starts-with(@name,'Spending Over Time')]/XCUIElementTypeStaticText[@name=\"You don't have any transactions.\"]")
	@AndroidFindBy(xpath="//*[@text='Spending Over Time']/../*[@text=concat('You don',\"'\",'t have any transactions.')]")
	public MobileElement spendingOverTimeCard_YoudontHaveAnyTxn;
	
	@iOSFindBy(xpath="//*[starts-with(@name,'Net Income Over Time')]/XCUIElementTypeStaticText[@name=\"You don't have any transactions.\"]")
	@AndroidFindBy(xpath="//*[@text='Net Income Over Time']/../*[@text=concat('You don',\"'\",'t have any transactions.')]")
	public MobileElement netIncomeOverTimeCard_YoudontHaveAnyTxn;
	
	@iOSFindBy(xpath="//*[@name='Transaction Summary $0 Earned $0 Spent $0']")
	@AndroidFindBy(xpath="//*[@text='Transaction Summary']/../*[@text='$0 Earned']")
	public MobileElement txnSummary_zeroEarned;
	
	@iOSFindBy(xpath="//*[@name='Transaction Summary $0 Earned $0 Spent $0']")
	@AndroidFindBy(xpath="//*[@text='Transaction Summary']/../*[@text='$0 Spent']")
	public MobileElement txnSummary_zeroSpent;
	
	@iOSFindBy(xpath="//*[@name='Transaction Summary $0 Earned $0 Spent $0']")
	@AndroidFindBy(xpath="//*[@text='Transaction Summary']/../*[@text='$0']")
	public MobileElement txnSummary_zeroDollar;
	
	@iOSFindBy(xpath="//*[normalize-space(@name) ='Investing Track your investing accounts and holdings on your phone. Tap to learn more']")
	@AndroidFindBy(xpath="//*[@text='Investing']/../*[@text='Track your investing accounts and holdings on your phone.']")
	public MobileElement invCard_zeroDataState;
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name='navigationMenu']/XCUIElementTypeOther[@name='navigationMenu']/XCUIElementTypeOther[@name='navigationMenu']")
	@AndroidFindBy(xpath="//*[@content-desc='navigationMenu']//*[@class='android.widget.ImageView']")
	public MobileElement hambergerIcon;
	
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[starts-with(@name,'Progress halted Banking')]/XCUIElementTypeScrollView")
	@AndroidFindBy(xpath="//*[@class='android.widget.ScrollView']")
	public MobileElement scrollView;
	
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Spending Over Time\"]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Spending Over Time']")
	public MobileElement spendingOverTimeCard;
	
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Net Income Over Time\"]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Net Income Over Time']")
	public MobileElement netIncomeOverTimeCard;
	
	@iOSFindBy(xpath="//XCUIElementTypeActivityIndicator[@name=\"In progress\"]")
	@AndroidFindBy(xpath="//android.widget.ProgressBar")
	public MobileElement refreshSpinnerIcon;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Investing\"]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Investing']")
	public MobileElement investmentCard;
	
	public void scrollUptoAccountsCard() throws Exception {
		
		Integer iCount;
		
		for (iCount=0; iCount<3; iCount++) {
			
			scrollView.swipe(SwipeElementDirection.DOWN, 10, 10, 1000); 
			
			if (Verify.objExists_check(this.refreshSpinnerIcon))
				Verify.waitForObjectToDisappear(this.refreshSpinnerIcon, 1);
			
			if (Verify.objExists_check(this.accountsCard))
				return;
			
		}
		
	}
	
	
	public void tapOnRecentTransactionsCard() throws Exception {
		
		Verify.waitForObjectToDisappear(refreshSpinnerIcon, 15);
		String sCard = "Recent Transactions";
		
		Helper h = new Helper();
		if (h.getEngine().equals("android")){
			
			Engine.ad.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ sCard + "\").instance(0))"));
			
		}
		else {
			String sXpath="//*[@name='Recent Transactions']";
			MobileElement me = (MobileElement) Engine.iosd.findElement(By.xpath(sXpath));
			String me_id = me.getId();
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("element", me_id);
			scrollObject.put("predicateString", "label == 'Recent Transactions'");
			Engine.iosd.executeScript("mobile:scroll", scrollObject);  // scroll to the target element
			Thread.sleep(1000);
			
		}
		
		recentTransactionsCard.click();
		Thread.sleep(2000);
		
	}
	
	public void tapOnPropertyDebtCard() throws Exception{
		
		//Dimension size = Engine.ad.manage().window().getSize();
		//Engine.ad.swipe(size.width - 10, size.height * 6 / 8, size.width - 10, size.height / 7, 500);
		Verify.waitForObjectToDisappear(refreshSpinnerIcon, 15);
		
		Helper h = new Helper();
		if (h.getEngine().equals("ios")){
			
			String sXpath="//*[@name ='Property and Debt Account']";
			MobileElement me = (MobileElement) Engine.iosd.findElement(By.xpath(sXpath));
			String me_id = me.getId();
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("element", me_id);
			scrollObject.put("toVisible", "not an empty string");
			Engine.iosd.executeScript("mobile:scroll", scrollObject);  // scroll to the target element
			Thread.sleep(1000);
			Engine.iosd.findElement(By.name("Top Trending Categories")).click();
			Thread.sleep(1000);	
			
		}
		else {
		//if (!Verify.objExists(topTrendingCard)) {
			/*
			scrollView.swipe(SwipeElementDirection.UP, 2, 2, 1000);
			Thread.sleep(1000);	
			*/
			String sXpath="//android.widget.TextView[@text='Property and Debt Account']";
			Engine.ad.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Property and Debt Account\").instance(0))"));
			Thread.sleep(1000);
			Engine.ad.findElement(By.xpath(sXpath)).click();
			Thread.sleep(1000);
		//}
		}
	}
	
	public void tapOnTrendingCard() throws Exception{
		
		//Dimension size = Engine.ad.manage().window().getSize();
		//Engine.ad.swipe(size.width - 10, size.height * 6 / 8, size.width - 10, size.height / 7, 500);
		Verify.waitForObjectToDisappear(refreshSpinnerIcon, 15);
		
		Helper h = new Helper();
		if (h.getEngine().equals("ios")){
			
			String sXpath="//*[@name ='Top Trending Categories']";
			MobileElement me = (MobileElement) Engine.iosd.findElement(By.xpath(sXpath));
			String me_id = me.getId();
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("element", me_id);
			scrollObject.put("toVisible", "not an empty string");
			Engine.iosd.executeScript("mobile:scroll", scrollObject);  // scroll to the target element
			Thread.sleep(1000);
			Engine.iosd.findElement(By.name("Top Trending Categories")).click();
			Thread.sleep(1000);	
			
		}
		else {
		//if (!Verify.objExists(topTrendingCard)) {
			/*
			scrollView.swipe(SwipeElementDirection.UP, 2, 2, 1000);
			Thread.sleep(1000);	
			*/
			String sXpath="//android.widget.TextView[@text='Top Trending Categories']";
			Engine.ad.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Top Trending Categories\").instance(0))"));
			Thread.sleep(1000);
			Engine.ad.findElement(By.xpath(sXpath)).click();
			Thread.sleep(1000);
		//}
		}
		
		
	}

	public void scrollTillCard(String cardName) throws Exception {
		
		Verify.waitForObjectToDisappear(refreshSpinnerIcon, 15);
		
		Helper h = new Helper();
		if (h.getEngine().equals("ios")){
			
			String sXpath="//*[@name ='"+cardName+"']";
			MobileElement me = (MobileElement) Engine.iosd.findElement(By.xpath(sXpath));
			String me_id = me.getId();
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("element", me_id);
			scrollObject.put("toVisible", "not an empty string");
			Engine.iosd.executeScript("mobile:scroll", scrollObject);  // scroll to the target element
			Thread.sleep(1000);
			
			
		}
		else {

			Engine.ad.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ cardName + "\").instance(0))"));
			Thread.sleep(1000);
		}
		
	}
	
	public void quicken_scroll() throws InterruptedException {
		
		Helper h = new Helper();
		
		if (h.getEngine().equals("android")) {
			scrollView.swipe(SwipeElementDirection.UP, 2, 2, 1000);
			Thread.sleep(2000);		
		}
		else {
			
			Dimension size = Engine.iosd.manage().window().getSize();
			Engine.iosd.swipe(size.width - 10, size.height * 4 / 8, size.width - 10, size.height / 7, 500);
			Thread.sleep(2000);
			
			
		}
	}
	
	public void quicken_scroll_mobile() throws InterruptedException {
		
		Helper h = new Helper();
		
		if (h.getEngine().equals("android")) {
			Dimension size = Engine.ad.manage().window().getSize();
			Engine.ad.swipe(size.width - 10, size.height * 4 / 8, size.width - 10, size.height / 7, 500);
			Thread.sleep(1000);	
		}
		else {
			
			Dimension size = Engine.iosd.manage().window().getSize();
			Engine.iosd.swipe(size.width - 10, size.height * 4 / 8, size.width - 10, size.height / 7, 500);
			Thread.sleep(1000);
			
			
		}
	}
	
	public void tapOnTransactionSummaryCard() throws Exception{
		
		//Dimension size = Engine.ad.manage().window().getSize();
		//Engine.ad.swipe(size.width - 10, size.height * 6 / 8, size.width - 10, size.height / 7, 500);
		Verify.waitForObjectToDisappear(refreshSpinnerIcon, 15);
		Integer iCount = 0;
		
		while (iCount < 4) {
			
			quicken_scroll();
			
			iCount++;	
			
			if (Verify.objExists(transactionSummaryCard))
				break;
			
		}
		
		
		if (!Verify.objExists(transactionSummaryCard)) {
			throw new Exception ("Errr ********** Looks like scroll issue, [transactionSummaryCard] did not appear");
		}
		transactionSummaryCard.click();
		Thread.sleep(3000);
	}
	
	
	public void tapOnSpendingOverTimeCard() throws Exception{
		
		Verify.waitForObjectToDisappear(refreshSpinnerIcon, 15);
		
		
		Helper h = new Helper();
		if (h.getEngine().equals("android")){
			String sXpath="//android.widget.TextView[@text='Spending Over Time']";
			Engine.ad.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Spending Over Time\").instance(0))"));
			Thread.sleep(1000);
			Engine.ad.findElement(By.xpath(sXpath)).click();
			Thread.sleep(1000);
		
		}
		else {
			String sXpath="//*[@name='Spending Over Time']";
			MobileElement me = (MobileElement) Engine.iosd.findElement(By.xpath(sXpath));
			String me_id = me.getId();
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("element", me_id);
			scrollObject.put("predicateString", "label == 'Spending Over Time'");
			Engine.iosd.executeScript("mobile:scroll", scrollObject);  // scroll to the target element
			Thread.sleep(1000);
			Engine.iosd.findElement(By.name("Spending Over Time")).click();
			Thread.sleep(1000);
		}
	}
	
	public void tapOnNetIncomeOverTimeCard() throws Exception{
		
		Verify.waitForObjectToDisappear(refreshSpinnerIcon, 15);
		
		
		Helper h = new Helper();
		if (h.getEngine().equals("android")){
			String sXpath="//android.widget.TextView[@text='Net Income Over Time']";
			Engine.ad.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Net Income Over Time\").instance(0))"));
			Thread.sleep(1000);
			Engine.ad.findElement(By.xpath(sXpath)).click();
			Thread.sleep(1000);
		
		}
		else {
			String sXpath="//*[@name='Net Income Over Time']";
			MobileElement me = (MobileElement) Engine.iosd.findElement(By.xpath(sXpath));
			String me_id = me.getId();
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("element", me_id);
			scrollObject.put("predicateString", "label == 'Net Income Over Time'");
			Engine.iosd.executeScript("mobile:scroll", scrollObject);  // scroll to the target element
			Thread.sleep(1000);
			Engine.iosd.findElement(By.name("Net Income Over Time")).click();
			Thread.sleep(1000);
		}
	}
	
	
	public void swipeFromBottomToUp() 
    {       
      try  {
             
              
              Helper h = new Helper();
              
              if (h.getEngine().equals("android")) {
            	  scrollView.swipe(SwipeElementDirection.DOWN, 2, 2, 1000);  
              }
              else
              {
            	  /*
            	  String elementID = this.accountsCard.getId();
            	  HashMap<String, String> scrollObject = new HashMap<String, String>();
            	  scrollObject.put("direction", "down");
            	  scrollObject.put("element", elementID);
            	  Engine.iosd.executeScript("mobile: swipe", scrollObject); 
            	  */ 
              }
            	  
              
        }
           catch (Exception e) 
            {
                Commentary.log(LogStatus.FAIL, "Swipe down Failed "+e.getMessage());
            }   
                         
    }
	
	public void navigateToAcctList() throws Exception{
		
		
		if (!Verify.objExists(accountsCard)) {
			swipeFromBottomToUp();	
			Verify.waitForObjectToDisappear(refreshSpinnerIcon, 5);
		}
				
		accountsCard.click();
		Thread.sleep(2000);
	}
	
	
	public void tapOnBudgetCard() throws Exception {
		
		DateFormat date =  new SimpleDateFormat("MMMM");
		Date date1 = new Date();
		String sCard = date.format(date1).toString();
		//System.out.println(date.format(date1).toString());
		sCard = sCard +" "+"Budget";
		Verify.waitForObjectToDisappear(refreshSpinnerIcon, 15);
		
		
		Helper h = new Helper();
		if (h.getEngine().equals("android")){
			String sXpath="//android.widget.TextView[@text='"+sCard+"']";
			Engine.ad.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ sCard + "\").instance(0))"));
			Thread.sleep(1000);
			Engine.ad.findElement(By.xpath(sXpath)).click();
			Thread.sleep(1000);
		
		}
		else {
			String sXpath="//*[@name='"+sCard+"']";
			MobileElement me = (MobileElement) Engine.iosd.findElement(By.xpath(sXpath));
			String me_id = me.getId();
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("element", me_id);
			scrollObject.put("predicateString", "label == '"+sCard+"'");
			Engine.iosd.executeScript("mobile:scroll", scrollObject);  // scroll to the target element
			Thread.sleep(1000);
			Engine.iosd.findElement(By.name(sCard)).click();
			Thread.sleep(1000);
		}
		
	}
	
	public void tapOnInvestingCard() throws Exception {
		
		
		Verify.waitForObjectToDisappear(refreshSpinnerIcon, 15);
		
		
		Helper h = new Helper();
		if (h.getEngine().equals("android")){
			String sXpath="//android.widget.TextView[@text='Investing']";
			Engine.ad.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Investing\").instance(0))"));
			Thread.sleep(1000);
			Engine.ad.findElement(By.xpath(sXpath)).click();
			Thread.sleep(1000);
		
		}
		else {
			String sXpath="//*[@name='Investing']";
			MobileElement me = (MobileElement) Engine.iosd.findElement(By.xpath(sXpath));
			String me_id = me.getId();
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("element", me_id);
			scrollObject.put("predicateString", "label == 'Investing'");
			Engine.iosd.executeScript("mobile:scroll", scrollObject);  // scroll to the target element
			Thread.sleep(1000);
			Engine.iosd.findElement(By.name("Investing")).click();
			Thread.sleep(1000);
		}
		
	}
	public void scrollToTop() throws Exception{
		
		Helper h = new Helper();
		if (h.getEngine().equals("android"))
			scrollUptoAccountsCard();
		else
			scrollToTop_IOS();
			
		
	}
	
	public void scrollToTop_IOS() throws Exception{
		
		Integer iCount;
		
		for (iCount=0; iCount<3; iCount++) {
			
			Dimension size = Engine.iosd.manage().window().getSize();
			System.out.println(size.height);
			System.out.println(size.height/4);
			Engine.iosd.swipe(size.width - 10, size.height/4, size.width - 10, size.height-50, 500);
			
			if (Verify.objExists(this.refreshSpinnerIcon))
				Verify.waitForObjectToDisappear(this.refreshSpinnerIcon, 1);
			
			if (Verify.objExists(this.accountsCard))
				return;
			
		}
		
		
	}

	public void scroll_down() {
		Helper h = new Helper();
		
		if (h.getEngine().equalsIgnoreCase("android")) {
			
			Dimension size = Engine.ad.manage().window().getSize();
			int y_start = (int) (size.width * 1.20);
			int y_end = (int) (size.width * 0.03);
			int x = 380;
			Engine.ad.swipe(x, y_start, x, y_end, 3000);
		} else {
			JavascriptExecutor js1 = (JavascriptExecutor) Engine.iosd ;
		    HashMap scrollObject = new HashMap();
		    scrollObject.put("direction", "down");
		    js1.executeScript("mobile: scroll", scrollObject);
		}
		
	}
	
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
		
		String sXpath = construct_SOTmonth(shortMonth);
		System.out.println(sXpath);
		sXpath = "//XCUIElementTypeOther[contains(@name,'Spending Over Time')]//XCUIElementTypeStaticText[@name ='APR']";
		MobileElement me = (MobileElement) Engine.iosd.findElement(By.xpath(sXpath));
		String me_id = me.getId();
		System.out.println(me_id);
		me.click();
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("element", me_id);
		scrollObject.put("predicateString", "label == '"+shortMonth.toUpperCase()+"'");
		Engine.iosd.executeScript("mobile:scroll", scrollObject);  // scroll to the target element
		Thread.sleep(1000);
		
		
	}
	
	protected void spendingOverTime_Visible_Android() throws Exception {
		
		Helper h = new Helper();
		
		String shortMonth = h.getLastSixMonths()[0];
		
		String sXpath = construct_SOTmonth(shortMonth);
		//Engine.ad.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Spending Over Time\").instance(0))"));
		//Engine.ad.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"APR\").instance(0))"));
		Engine.ad.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""+shortMonth.toUpperCase()+"\").instance(1))"));
		Thread.sleep(1000);
		System.out.println(Verify.objExists((MobileElement)Engine.ad.findElement(By.xpath(sXpath))));
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
	
	public Boolean verifyMonthOnSpendingOverTime(String sMonth) {
		
		Helper h = new Helper();
		String [] shortMonths = h.getLastSixMonths();
		
		Integer iCount;
		String sXpath;
		MobileElement me;
		
		
		sXpath=construct_SOTmonth(sMonth);
		
		if (h.getEngine().equals("android"))
			return Verify.objExists((MobileElement)Engine.ad.findElement(By.xpath(sXpath)));
			
		else
			return Verify.objExists((MobileElement)Engine.iosd.findElement(By.xpath(sXpath)));
		
		
	}
	
	
	public void tapOnAddTransaction() throws Exception {
		
		Verify.waitForObjectToDisappear(refreshSpinnerIcon, 15);
		addTransaction.click();
		Thread.sleep(1000);
	}

}
