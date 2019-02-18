package dugout;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import referee.Commentary;
import referee.Verify;
import support.Engine;
import support.Helper;

public class BankingAndCreditCardPage {
	
	public BankingAndCreditCardPage () {
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
	
	@iOSFindBy(xpath="//XCUIElementTypeNavigationBar/XCUIElementTypeButton")
	@AndroidFindBy(xpath="//*[@class='android.widget.ImageButton']")
	public MobileElement backButton;
	
	@iOSFindBy(xpath="//*[starts-with(@name, 'Bank Account Balances Checking Accounts')]")
	@AndroidFindBy(xpath="//*[@text='Checking']")
	public MobileElement checkingAccount;
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"scroller 0\"]")
	@AndroidFindBy(xpath="//*[@content-desc=\"scroller 0\"]")
	public MobileElement select_todaysBalance;
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"scroller 1\"]")
	@AndroidFindBy(xpath="//*[@content-desc=\"scroller 1\"]")
	public MobileElement select_OnlineBalance;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"TODAY'S BALANCE\"]")
	@AndroidFindBy(xpath="//*[@text=\"TODAY'S BALANCE\"]")
	public MobileElement txtTodaysBalance;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"ONLINE BALANCE\"]")
	@AndroidFindBy(xpath="//*[@text=\"ONLINE BALANCE\"]")
	public MobileElement txtOnlineBalance;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"CHECKING\"]/../XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"CHECKING\"]/../android.widget.TextView[2]")
	public MobileElement checkingBalance;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"CREDIT CARDS\"]/../XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"CREDIT CARDS\"]/../android.widget.TextView[2]")
	public MobileElement creditCardBalance;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"CASH\"]/../XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"CASH\"]/../android.widget.TextView[2]")
	public MobileElement cashBalance;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"SAVINGS\"]/../XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"SAVINGS\"]/../android.widget.TextView[2]")
	public MobileElement savingsBalance;
	
	//ONLINE BALANCE
	//\"TODAY'S BALANCE\
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[contains(@name,\"TODAY'S BALANCE\")]/../XCUIElementTypeStaticText[contains(@name,'$')]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"TODAY'S BALANCE\"]/../android.widget.TextView[contains(@text,'$')]")
	public MobileElement txtTodaysBalanceAmount;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[contains(@name,\"ONLINE BALANCE\")]/../XCUIElementTypeStaticText[contains(@name,'$')]")
	@AndroidFindBy(xpath="//*[@text=\"TODAY'S BALANCE\"]")
	public MobileElement txtActualBalance;
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"scroller 2\"]")
	@AndroidFindBy(xpath="//*[@content-desc=\"scroller 2\"]")
	public MobileElement select_ProjectedBalance;
	
	@iOSFindBy(xpath="//XCUIElementTypeButton[@name=\"fab\"]")
	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.quicken.qm2014:id/fab']")
	public MobileElement addTransaction;
	
	
	public MobileElement getAccount(String acctName) throws Exception{
		
		String xPathForAcct = "//android.view.ViewGroup/android.widget.TextView[normalize-space(@text)='"+acctName+"']";
		String xPathForAcct_IOS = "//XCUIElementTypeStaticText[normalize-space(@name)='"+acctName+"']";
		//"//android.widget.ScrollView//android.view.ViewGroup//android.widget.TextView[normalize-space(@text)='"+acctName+"']";
		Commentary.log(LogStatus.INFO, "Finding Account ["+acctName+"]");
		Helper h = new Helper();
		
		if (h.getEngine().equals("android")){
			try{
				Engine.ad.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ acctName + "\").instance(0))"));
				Thread.sleep(1000);
				return (MobileElement) Engine.ad.findElementByXPath(xPathForAcct);
			}
			catch(Exception E){
				return null;
			}
			
		}
		else {
			try{
				return (MobileElement) Engine.iosd.findElementByXPath(xPathForAcct_IOS);
			}
			catch(Exception E){
				return null;
			}
			
			
		}
		
		
	}
	
	/*public String getAccountBalance(String acctName){
		
		if (getAccount(acctName)==null){
			System.out.println(acctName+" does not exist");
			return null;
		}
		
	}*/
	
	public void invokeTransactionDetailsFromAccountListBalanceHeader() throws Exception {
		
		txtTodaysBalance.click();
		Thread.sleep(2000);
		
		AllAccountsPage aap = new AllAccountsPage();
		aap.addTransaction.click();
		Thread.sleep(1000);
		
		TransactionDetailPage td = new TransactionDetailPage();
		td.buttonDone.click();
		Thread.sleep(1000);
		
	}
	
	public void invokeTransactionDetails() throws Exception{
		
		AllAccountsPage aap = new AllAccountsPage();
		aap.addTransaction.click();
		Thread.sleep(1000);
		
		TransactionDetailPage td = new TransactionDetailPage();
		
		if (Verify.objExists(td.allowButton)) {
			td.allowButton.click();
			Thread.sleep(1000);
		}
		
		td.buttonDone.click();
		Thread.sleep(1000);
		
	}
	
	public String getCheckingBalance() throws Exception {
		
		// scroll to the account
		this.scrollToAccount("CHECKING");
		
		return this.checkingBalance.getText();
	
	}
	
	public String getCreditCardsBalance() throws Exception {
		
		// scroll to the account
		this.scrollToAccount("CREDIT CARDS");
		
		return this.creditCardBalance.getText();
	
	}
	
	public String getCashBalance() throws Exception {
		
		// scroll to the account
		this.scrollToAccount("CASH");
		
		return this.cashBalance.getText();
	
	}
	
	public String getSavingsBalance() throws Exception {
		
		// scroll to the account
		this.scrollToAccount("SAVINGS");
		
		return this.savingsBalance.getText();
	
	}
	
	public void scrollToAccount(String acctType) throws Exception {
		
		Helper h = new Helper();
		if (h.getEngine().equals("android")){
			String sXpath="//android.widget.TextView[@text='"+acctType+"']";
			Engine.ad.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ acctType + "\").instance(0))"));
			Thread.sleep(1000);
		}
		else {
			String sXpath="//XCUIElementTypeStaticText[@name='"+acctType+"']";
			String sLabel ="name == '"+acctType+"'";
			MobileElement me = (MobileElement) Engine.iosd.findElement(By.xpath(sXpath));
			String me_id = me.getId();
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("element", me_id);
			scrollObject.put("toVisible", "not an empty string");
			Engine.iosd.executeScript("mobile:scroll", scrollObject);  // scroll to the target element
			Thread.sleep(1000);
	}
	
	}
}
