package dugout;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.events.api.general.AlertEventListener;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import referee.Commentary;
import referee.Verify;
import support.Engine;
import support.Helper;

public class BankingAndCreditCardPage {

	public BankingAndCreditCardPage () {
		try {
			
			PageFactory.initElements(new AppiumFieldDecorator(Engine.getDriver()),this);
		} catch (Exception e) {
		
		e.printStackTrace();
		}
	}

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeButton[`name contains 'back'`]")
	@AndroidFindBy(xpath="//*[@class='android.widget.Button']")
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

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name=='PROJECTED BALANCE'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"PROJECTED BALANCE\"]")
	public MobileElement txtProjectedBalance;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains \"PROJECTED BALANCE\"`]/XCUIElementTypeStaticText[`name contains '$'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"PROJECTED BALANCE\"]/../android.widget.TextView[contains(@text,'$')]") //android.widget.TextView[@text=\"PROJECTED BALANCE\"]/../android.widget.TextView[2]
	public MobileElement txtProjectedBalanceAmount;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name CONTAINS 'Checking'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"CHECKING\"]/../android.widget.TextView[2]")
	public MobileElement checkingBalance;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name CONTAINS 'Checking Account Balance'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"Checking\"]/../android.widget.TextView[2]")
	public MobileElement checkingAccountBalance;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name CONTAINS 'Credit Cards'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"CREDIT CARDS\"]/../android.widget.TextView[2]")
	public MobileElement creditCardBalance;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name CONTAINS 'Cash'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"CASH\"]/../android.widget.TextView[2]")
	public MobileElement cashBalance;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name CONTAINS 'Savings'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"SAVINGS\"]/../android.widget.TextView[2]")
	public MobileElement savingsBalance;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name CONTAINS 'Savings Account Balance'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"Savings\"]/../android.widget.TextView[2]")
	public MobileElement savingsAccountBalance;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name=='All Transactions'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"All Transactions\"]")
	public MobileElement allTransactionButton;

	//ONLINE BALANCE
	//\"TODAY'S BALANCE\

	//	@iOSFindBy(xpath="//XCUIElementTypeStaticText[contains(@name,\"TODAY'S BALANCE\")]/../XCUIElementTypeStaticText[contains(@name,'$')]")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains \"TODAY'S BALANCE\"`]/XCUIElementTypeStaticText[`name contains '$'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"TODAY'S BALANCE\"]/../android.widget.TextView[contains(@text,'$')]")
	public MobileElement txtTodaysBalanceAmount;

	@iOSFindBy(xpath="//XCUIElementTypeStaticText[contains(@name,\"TODAY'S BALANCE\")]/../XCUIElementTypeStaticText[contains(@name,'$')]")
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
//		String xPathForAcct_IOS = "//XCUIElementTypeStaticText[normalize-space(@name)='Account Name: "+acctName+"']";
		String xPathForAcct_IOS = "**/XCUIElementTypeStaticText[`name='Account Name: "+acctName+"'`]";
		
		Commentary.log(LogStatus.INFO, "Finding Account ["+acctName+"]");
		Helper h = new Helper();

		if (h.getEngine().equals("android")){
			try	{
				Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ acctName + "\").instance(0))"));
				Thread.sleep(1000);
				return (MobileElement) Engine.getDriver().findElementByXPath(xPathForAcct);
			}
			catch(Exception e){
				return null;
			}
		}

		else {

			try {
//				if (!Verify.objExists((MobileElement)Engine.getDriver().findElement(By.xpath(xPathForAcct_IOS))))
//					scrollToAccount(acctName);
//				return (MobileElement) Engine.getDriver().findElementByXPath(xPathForAcct_IOS);
				if (!Verify.objExists(Engine.getDriver().findElement(MobileBy.iOSClassChain(xPathForAcct_IOS))))
					scrollToAccount(acctName);
				return Engine.getDriver().findElement(MobileBy.iOSClassChain(xPathForAcct_IOS));
			}
			catch(Exception e){
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
		Helper h = new Helper();
		// scroll to the account

		if (h.getEngine().equalsIgnoreCase("android")) {
			this.scrollToAccount("CHECKING");
		} else {
			this.scrollToAccount("Account Type: Checking");
		}

		return this.checkingBalance.getText().replace("SubTotal: ", "");
	}

	public String getCheckingAccountBalance() throws Exception {
		Helper h = new Helper();
		// scroll to the account

		if (h.getEngine().equalsIgnoreCase("android")) {
			this.scrollToAccount("CHECKING");
		} else {
			this.scrollToAccount("Account Name: Checking");
		}

		return this.checkingAccountBalance.getText().replace("Account Balance: ", "");
	}

	public String getCreditCardsBalance() throws Exception {

		// scroll to the account
		this.scrollToAccount("Credit Cards");

		return this.creditCardBalance.getText().replace("SubTotal: ", "");
	}

	public String getCashBalance() throws Exception {

		// scroll to the account
		this.scrollToAccount("Cash");

		return this.cashBalance.getText().replace("SubTotal: ", "");
	}

	public String getSavingsBalance() throws Exception {

		// scroll to the account
		this.scrollToAccount("Savings");

		return this.savingsBalance.getText().replace("SubTotal: ", "");
	}

	public String getSavingsAccountBalance() throws Exception {

		// scroll to the account
		this.scrollToAccount("Savings");

		return this.savingsAccountBalance.getText().replace("Account Balance: ", "");
	}

	public String getAccountBalance_android (String acct) throws Exception {

		String xpath = "//*[@text='"+acct+"']/../*[contains(@text, '$')]";

		this.scrollToAccount(acct);

		return Engine.getDriver().findElement(By.xpath(xpath)).getText();
	}

	public String getAccountBalance_IOS (String acct) throws Exception {

		String xpath = "**/XCUIElementTypeOther[`name contains 'Account Name: "+acct+"'`]/XCUIElementTypeStaticText[`name contains '$'`]";

		this.scrollToAccount(acct);

		return Engine.getDriver().findElement(MobileBy.iOSClassChain(xpath)).getText();
	}

	public String getAccountBalance(String acct) throws Exception {

		Helper h = new Helper();

		if (h.getEngine().equals("android"))
			return getAccountBalance_android (acct);
		else
			return getAccountBalance_IOS (acct);
	}

	public String getTotalBalance() throws Exception{

		Verify.waitForObject(txtTodaysBalanceAmount, 2);
		String totalBalance = txtTodaysBalanceAmount.getText();

		return totalBalance;
	}

	public String getProjectedBalance() throws Exception {

		scrollToProjectedBalance();

		Verify.waitForObject(txtProjectedBalanceAmount, 2);
		String projectedBalance = txtProjectedBalanceAmount.getText();

		return projectedBalance;
	}

	public void scrollToAccount(String acctType) throws Exception {

		Helper h = new Helper();
		if (h.getEngine().equals("android")){
			String sXpath="//android.widget.TextView[@text='"+acctType+"']";
			Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ acctType + "\").instance(0))"));
			Thread.sleep(1000);
		}
		else {
			String sXpath="**/XCUIElementTypeStaticText[`name CONTAINS '"+acctType+"'`]";
			String sLabel ="name == '"+acctType+"'";
			MobileElement me = (MobileElement) Engine.getDriver().findElement(MobileBy.iOSClassChain(sXpath));
			String me_id = me.getId();
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("element", me_id);
			scrollObject.put("toVisible", "not an empty string");
			Engine.getDriver().executeScript("mobile:scroll", scrollObject);  // scroll to the target element
			Thread.sleep(1000);
		}

	}

	/* swipes from todaysbalance to projected balance
	 make sure the screen stays on todaysbalance before calling this method */

	public void scrollToProjectedBalance() throws Exception {

		Helper h = new Helper();

		if (h.getEngine().equals("android"))
			scrollToProjectedBalance_android();
		else
			scrollToProjectedBalance_ios();		
	}

	public void scrollToProjectedBalance_android() throws Exception{

		Dimension size = this.txtActualBalance.getSize();

		int x_start=(int)(size.width*0.95);
		int x_end=(int)(size.width*0.05);

		int y=this.txtActualBalance.getRect().getY();
		
		//int y=this.txtTodaysBalanceAmount.getRect().getY()+150;

		//		Integer i;
		//        for (i = 0; i<2; i++) {
		//        	Engine.ad.swipe(x_start,y,x_end,y,4000);
		//        	Thread.sleep(1000);
		//        }	
		TouchAction touchAction = new TouchAction(Engine.getDriver());

		for(int i=1 ;i<=2;i++) { 
			touchAction.press(point(x_start, y)).waitAction(waitOptions(ofMillis(4000))).moveTo(point(x_end, y)).release().perform();
		}
	}

	public void scrollToProjectedBalance_ios() throws Exception{

		MobileElement element = this.txtTodaysBalance;
		String elementID = element.getId();
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("element", elementID); // Only for ‘scroll in element’
		scrollObject.put("direction", "left");
		Engine.getDriver().executeScript("mobile:swipe", scrollObject);
		Thread.sleep(1000);

		Verify.waitForObject(this.txtOnlineBalance, 1);
		MobileElement element1 = this.txtOnlineBalance;
		String elementID1 = element1.getId();
		HashMap<String, String> scrollObject1 = new HashMap<String, String>();
		scrollObject1.put("element", elementID1); // Only for ‘scroll in element’
		scrollObject1.put("direction", "left");
		Engine.getDriver().executeScript("mobile:swipe", scrollObject1);
		Thread.sleep(1000);

		Verify.waitForObject(this.txtProjectedBalance, 1);
		MobileElement element2 = this.txtProjectedBalance;
		String elementID2 = element2.getId();
		HashMap<String, String> scrollObject2 = new HashMap<String, String>();
		scrollObject2.put("element", elementID2); // Only for ‘scroll in element’
		scrollObject2.put("direction", "left");
		Engine.getDriver().executeScript("mobile:swipe", scrollObject2);
		Thread.sleep(1000);

		//	        Helper h = new Helper();
		//	        h.getContext();

		//	        TransactionsPage tp = new TransactionsPage();
		//	        System.out.println("eeeee....");
		//	        tp.backButton.click();
		//        	Thread.sleep(1000);

		/*
			MobileElement element = (MobileElement)Engine.iosd.findElement(By.name("TODAY'S BALANCE"));
	        String elementID = element.getId();
	        HashMap<String, String> scrollObject = new HashMap<String, String>();
	        scrollObject.put("element", elementID); // Only for ‘scroll in element’
	        scrollObject.put("direction", "right");
	        Engine.iosd.executeScript("mobile:scroll", scrollObject);

	        MobileElement element1 = (MobileElement)Engine.iosd.findElement(By.name("ONLINE BALANCE"));
	        String elementID1 = element1.getId();
	        HashMap<String, String> scrollObject1 = new HashMap<String, String>();
	        scrollObject1.put("element", elementID1); // Only for ‘scroll in element’
	        scrollObject1.put("direction", "right");
	        Engine.iosd.executeScript("mobile:scroll", scrollObject1);
		 */
	}

	public void scrollToTotalBalance() throws Exception {

		Helper h = new Helper();

		if (h.getEngine().equals("android"))
			scrollToTotalBalance_android();
		else
			scrollToTotalBalance_ios();		
	}

	public void scrollToTotalBalance_android() throws Exception{

		Dimension size = this.txtProjectedBalance.getSize();

		int x_start=(int)(size.width*0.05);
		int x_end=(int)(size.width*0.95);

		int y=this.txtProjectedBalance.getRect().getY();

		TouchAction touchAction = new TouchAction(Engine.getDriver());

		for(int i=1 ;i<=2;i++) { 
			touchAction.press(point(x_start, y)).waitAction(waitOptions(ofMillis(4000))).moveTo(point(x_end, y)).release().perform();
		}
	}

	public void scrollToTotalBalance_ios() throws Exception{		

		MobileElement element = this.txtProjectedBalance;
		String elementID = element.getId();
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("element", elementID); // Only for ‘scroll in element’
		scrollObject.put("direction", "right");
		Engine.getDriver().executeScript("mobile:swipe", scrollObject);
		Thread.sleep(1000);

		Verify.waitForObject(this.txtOnlineBalance, 1);
		MobileElement element1 = this.txtOnlineBalance;
		String elementID1 = element1.getId();
		HashMap<String, String> scrollObject1 = new HashMap<String, String>();
		scrollObject1.put("element", elementID1); // Only for ‘scroll in element’
		scrollObject1.put("direction", "right");
		Engine.getDriver().executeScript("mobile:swipe", scrollObject1);
		Thread.sleep(1000);

		Verify.waitForObject(this.txtTodaysBalance, 1);
		MobileElement element2 = this.txtTodaysBalance;
		String elementID2 = element2.getId();
		HashMap<String, String> scrollObject2 = new HashMap<String, String>();
		scrollObject2.put("element", elementID2); // Only for ‘scroll in element’
		scrollObject2.put("direction", "right");
		Engine.getDriver().executeScript("mobile:swipe", scrollObject2);
		Thread.sleep(1000);
	}

	public void selectAccount(String acctName) throws Exception {

		Helper h = new Helper();
		if (h.getEngine().equals("android")){
			String sXpath="//android.widget.TextView[@text='"+acctName+"']";
			this.scrollToAccount(acctName);
			Verify.waitForObject((MobileElement) Engine.getDriver().findElement(MobileBy.xpath(sXpath)), 2);
			Engine.getDriver().findElement(MobileBy.xpath(sXpath)).click();
			Thread.sleep(5000);
		}
		else {
			String sXpath="**/XCUIElementTypeStaticText[`name CONTAINS '"+acctName+"'`]";
			MobileElement me = (MobileElement) Engine.getDriver().findElement(MobileBy.iOSClassChain(sXpath));
			me.click();
			Thread.sleep(3000);
		}

	}
}
