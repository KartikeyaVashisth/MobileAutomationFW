package dugout;

import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import referee.Commentary;
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
		td.buttonDone.click();
		Thread.sleep(1000);
		
	}
	
	
	
	

}
