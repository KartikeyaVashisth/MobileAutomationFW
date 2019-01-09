package dugout;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.bcel.verifier.VerifierFactoryObserver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
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
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Banking and Credit Account\"]")
	@AndroidFindBy(xpath="//*[@text='Banking and Credit Account']")
	public MobileElement accountsCard;
	
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
	
	@iOSFindBy(xpath="//*[@name ='Recent Transactions No Transaction available')]")
	@AndroidFindBy(xpath="//*[@text='Recent Transactions']/../*[@text='No Transaction available']")
	public MobileElement recentTxns_NoTxnsAvaialable;
	
	@iOSFindBy(xpath="//*[@name=concat('Top Trending Categories Last 30 days You don',\"'\",'t have any transactions.')]")
	@AndroidFindBy(xpath="//*[@text='Top Trending Categories']/../*[@text=concat('You don',\"'\",'t have any transactions.')]")
	public MobileElement tredningCard_YoudontHaveAnyTxn;
	
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
	
	//(//XCUIElementTypeOther[@name="navigationMenu"])[3]
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
	
	public void scrollUptoAccountsCard() throws Exception {
		
		scrollView.swipe(SwipeElementDirection.DOWN, 10, 10, 1000);
		Thread.sleep(3000);
		scrollView.swipe(SwipeElementDirection.DOWN, 10, 10, 1000);
		Thread.sleep(3000);
		scrollView.swipe(SwipeElementDirection.DOWN, 10, 10, 1000);
		Thread.sleep(7000);
		
	}
	
	/*
	public void tapOnRecentTransactionsCard() throws Exception {
		
		Verify.waitForObjectToDisappear(refreshSpinnerIcon, 15);
		
		if (!Verify.objExists(recentTransactionsCard)) {
			scrollView.swipe(SwipeElementDirection.UP, 1, 1, 1000);
			Thread.sleep(1000);	
		}
		
		//scrollView.swipe(SwipeElementDirection.UP, 10, 10, 1000);
		//Thread.sleep(1000);
		
		recentTransactionsCard.click();
		Thread.sleep(3000);
		
	}*/
	
	public void tapOnRecentTransactionsCard() throws Exception {
		
		Verify.waitForObjectToDisappear(refreshSpinnerIcon, 15);
		String sCard = "Recent Transactions";
		
		Helper h = new Helper();
		if (h.getEngine().equals("android")){
			
			Engine.ad.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ sCard + "\").instance(0))"));
			
		}
		
		/*if (!Verify.objExists(recentTransactionsCard)) {
			scrollView.swipe(SwipeElementDirection.UP, 1, 1, 1000);
			Thread.sleep(1000);	
		}*/
		
		//scrollView.swipe(SwipeElementDirection.UP, 10, 10, 1000);
		//Thread.sleep(1000);
		
		recentTransactionsCard.click();
		Thread.sleep(3000);
		
	}
	
	public void tapOnTrendingCard() throws Exception{
		
		//Dimension size = Engine.ad.manage().window().getSize();
		//Engine.ad.swipe(size.width - 10, size.height * 6 / 8, size.width - 10, size.height / 7, 500);
		Verify.waitForObjectToDisappear(refreshSpinnerIcon, 15);
		
		Helper h = new Helper();
		if (h.getEngine().equals("ios")){
			if (topTrendingCard.getAttribute("visible").equals("false")) {
				
				Dimension size = Engine.iosd.manage().window().getSize();
				Engine.iosd.swipe(size.width - 10, size.height * 4 / 8, size.width - 10, size.height / 7, 500);
				Thread.sleep(3000);
				
			}
				
			
		}
		else {
		if (!Verify.objExists(topTrendingCard)) {
			scrollView.swipe(SwipeElementDirection.UP, 2, 2, 1000);
			Thread.sleep(1000);	
		}
		}
		
		topTrendingCard.click();
		Thread.sleep(3000);
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
			Thread.sleep(3000);
			
			
		}
	}
	
	public void quicken_scroll_mobile() throws InterruptedException {
		
		Helper h = new Helper();
		
		if (h.getEngine().equals("android")) {
			Dimension size = Engine.ad.manage().window().getSize();
			Engine.ad.swipe(size.width - 10, size.height * 4 / 8, size.width - 10, size.height / 7, 500);
			Thread.sleep(3000);	
		}
		else {
			
			Dimension size = Engine.iosd.manage().window().getSize();
			Engine.iosd.swipe(size.width - 10, size.height * 4 / 8, size.width - 10, size.height / 7, 500);
			Thread.sleep(3000);
			
			
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
		
		//Dimension size = Engine.ad.manage().window().getSize();
		//Engine.ad.swipe(size.width - 10, size.height * 6 / 8, size.width - 10, size.height / 7, 500);
		
		Integer iCount = 0;
		Verify.waitForObjectToDisappear(refreshSpinnerIcon, 15);
		while (iCount < 4) {
			
			quicken_scroll();
			
			iCount++;	
			
			if (Verify.objExists(spendingOverTimeCard))
				break;
			
		}
		
		
		if (!Verify.objExists(spendingOverTimeCard)) {
			throw new Exception ("Errr ********** Looks like scroll issue, [spendingOverTimeCard] did not appear");
		}
		spendingOverTimeCard.click();
		Thread.sleep(10000);
	}
	
	public void tapOnNetIncomeOverTimeCard() throws Exception{
		
		//Dimension size = Engine.ad.manage().window().getSize();
		//Engine.ad.swipe(size.width - 10, size.height * 6 / 8, size.width - 10, size.height / 7, 500);
		
		Integer iCount = 0;
		Verify.waitForObjectToDisappear(refreshSpinnerIcon, 15);
		while (iCount < 5) {
			
			System.out.println(Verify.objExists(netIncomeOverTimeCard));
			
			if (!Verify.objExists(netIncomeOverTimeCard)) {
				/*scrollView.swipe(SwipeElementDirection.UP, 1, 1, 1000);
				Thread.sleep(2000);	*/
				
				quicken_scroll_mobile();
			}
			
			iCount++;	
			
			if (Verify.objExists(netIncomeOverTimeCard))
				break;
			
		}
		
		
		if (!Verify.objExists(netIncomeOverTimeCard)) {
			throw new Exception ("Errr ********** Looks like scroll issue, [netIncomeOverTimeCard] did not appear");
		}
		netIncomeOverTimeCard.click();
		Thread.sleep(5000);
	}
	
	/*public void tapOnTransactionSummaryCard() throws Exception{
		
		//Dimension size = Engine.ad.manage().window().getSize();
		//Engine.ad.swipe(size.width - 10, size.height * 6 / 8, size.width - 10, size.height / 7, 500);
		Verify.waitForObjectToDisappear(refreshSpinnerIcon, 15);
		Integer iCount = 0;
		
		while (iCount < 3) {
			
			if (!Verify.objExists(transactionSummaryCard)) {
				scrollView.swipe(SwipeElementDirection.UP, 2, 2, 1000);
				Thread.sleep(2000);	
			}
			
			iCount++;	
			
			if (Verify.objExists(transactionSummaryCard))
				break;
			
		}
		
		
		if (!Verify.objExists(transactionSummaryCard)) {
			throw new Exception ("Errr ********** Looks like scroll issue, [transactionSummaryCard] did not appear");
		}
		transactionSummaryCard.click();
		Thread.sleep(3000);
	}*/
	
	public void navigateToAcctList() throws Exception{
		
		accountsCard.click();
	   Thread.sleep(3000);
	}
	
	/*public void tapOnSpendingOverTimeCard() throws Exception{
		
		//Dimension size = Engine.ad.manage().window().getSize();
		//Engine.ad.swipe(size.width - 10, size.height * 6 / 8, size.width - 10, size.height / 7, 500);
		
		Integer iCount = 0;
		Verify.waitForObjectToDisappear(refreshSpinnerIcon, 15);
		while (iCount < 3) {
			
			if (!Verify.objExists(spendingOverTimeCard)) {
				scrollView.swipe(SwipeElementDirection.UP, 2, 2, 1000);
				Thread.sleep(4000);	
			}
			
			iCount++;	
			
			if (Verify.objExists(spendingOverTimeCard))
				break;
			
		}
		
		
		if (!Verify.objExists(spendingOverTimeCard)) {
			throw new Exception ("Errr ********** Looks like scroll issue, [spendingOverTimeCard] did not appear");
		}
		spendingOverTimeCard.click();
		Thread.sleep(10000);
	}*/
	
	/*public void tapOnNetIncomeOverTimeCard() throws Exception{
		
		//Dimension size = Engine.ad.manage().window().getSize();
		//Engine.ad.swipe(size.width - 10, size.height * 6 / 8, size.width - 10, size.height / 7, 500);
		
		Integer iCount = 0;
		Verify.waitForObjectToDisappear(refreshSpinnerIcon, 15);
		while (iCount < 3) {
			
			System.out.println(Verify.objExists(netIncomeOverTimeCard));
			
			if (!Verify.objExists(netIncomeOverTimeCard)) {
				/*scrollView.swipe(SwipeElementDirection.UP, 1, 1, 1000);
				Thread.sleep(2000);	*/
				
			/*	Dimension size = Engine.ad.manage().window().getSize();
				Engine.ad.swipe(size.width - 10, size.height * 4 / 8, size.width - 10, size.height / 7, 500);
				Thread.sleep(3000);
			}
			
			iCount++;	
			
			if (Verify.objExists(netIncomeOverTimeCard))
				break;
			
		}
		
		
		if (!Verify.objExists(netIncomeOverTimeCard)) {
			throw new Exception ("Errr ********** Looks like scroll issue, [netIncomeOverTimeCard] did not appear");
		}
		netIncomeOverTimeCard.click();
		Thread.sleep(5000);
	}*/
	
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
	
	
	
	
	
	
	

}
