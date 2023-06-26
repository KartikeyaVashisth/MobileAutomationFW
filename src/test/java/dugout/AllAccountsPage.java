package dugout;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ContextAware;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import referee.Commentary;
import referee.Verify;
import support.Engine;
import support.Helper;

public class AllAccountsPage {
	
	public AllAccountsPage () {
		try {
					
					PageFactory.initElements(new AppiumFieldDecorator(Engine.getDriver()),this);
			} catch (Exception e) {
				
				e.printStackTrace();
			}				
	}
	
	//@iOSFindBy(xpath="//*[contains(@name,'All Transactions')]")
	//@iOSXCUITBy(iOSClassChain = "**/XCUIElementTypeNavigationBar/XCUIElementTypeOther[`name == 'All Transactions'`]")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'All Transactions'`]")
	@AndroidFindBy(xpath="//android.view.View[@text='All Transactions']")
	public WebElement textAllTransactions;
	
	//@iOSFindBy(xpath="//XCUIElementTypeButton[@name='Back' or @name=\"Banking & Credit\"]")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeButton[`name='Go back'`]")
	@AndroidFindBy(xpath="//android.widget.Button[@content-desc='Go back']")
	public WebElement backButton;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'All Transactions'`]")
	@AndroidFindBy(xpath="//android.view.View[@text='All Transactions']")
	public WebElement allTransactionsHeader;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText[`name='topLeftLabel'`]")
	@AndroidFindBy(xpath="//android.widget.LinearLayout[contains(@resource-id,'list_row')]/android.widget.RelativeLayout/..")
	public WebElement allTransactions;
	
	//@iOSFindBy(xpath="//XCUIElementTypeSearchField[@name=\"Search Transactions\"]")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeSearchField[`name='Search Transactions'`]")
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Search Transactions']")
	public WebElement searchTransactionTxtField;
	
	//@iOSFindBy(xpath="//XCUIElementTypeButton[@name=\"sort icon\"]")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name='sort icon'`]")
	@AndroidFindBy(xpath="//android.widget.ImageButton[contains(@resource-id,'sortBtn')]")
	public WebElement sortTransaction;
	
	//@iOSFindBy(xpath="//XCUIElementTypeButton[@name=\"Clear text\"]")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name='Clear text'`]")
	@AndroidFindBy(xpath="//android.widget.ImageView[contains(@resource-id,'search_close_btn')]")
	public WebElement closeSearch;
	
	//@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"No Results Found\"]")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='No Results Found'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='No Results Found']")
	public WebElement noResultsFound;
	
//	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Add Transaction\"]")
//	@AndroidFindBy(xpath="//android.widget.Button[@text='Allow']")
//	public WebElement allowButton;
//	
//	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Add Transaction\"]")
//	@AndroidFindBy(xpath="//android.widget.Button[@text='Deny']")
//	public WebElement denyButton;
	
	//@iOSFindBy(xpath="//XCUIElementTypeButton[@name=\"fab\"]")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name='fab'`]")
	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.quicken.qm2014:id/fab']")
	public WebElement addTransaction;
	
	
	public void navigateBackToDashboard() throws Exception {
		
		backButton.click();
		Thread.sleep(3000);
		
//		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
//		if (Verify.objExists(bcc.backButton)) {
//			bcc.backButton.click();
//			Thread.sleep(2000);
//		}
	}
	
	public void searchTransaction(String searchString) throws Exception{
		
		if (Verify.objExists(this.closeSearch))
			this.closeSearch.click();
		
		searchTransactionTxtField.clear();
		searchTransactionTxtField.sendKeys(searchString);
		
		Helper h = new Helper();
		h.hideKeyBoard();
		Thread.sleep(1000);
	}
	
	public boolean verifyTransaction (String searchString) throws Exception{
		
		searchTransaction(searchString);
		
		List<WebElement> li = getAllSearchTransactions ();
		
		Commentary.log(LogStatus.INFO, "No of transactions found "+li.size());
		
		if (li.isEmpty())
			return false;
		else
			return true;
	}
	
	public List<WebElement> getAllSearchTransactions() throws Exception{
		
		 Helper h = new Helper();
		 if (h.getEngine().equals("android"))
			 return getAllSearchTransactions_android();
		 else
			 return getAllSearchTransactions_ios();		
	}
	
	public List<WebElement> getAllSearchTransactions_android () throws Exception{
		
		String sXpath = "//android.widget.LinearLayout[contains(@resource-id,'list_row')]/android.widget.RelativeLayout/..";
		List <WebElement> me = null;

		((ContextAware) Engine.getDriver()).getContext();
		Verify.waitForObject(this.allTransactions, 2);
		me = Engine.getDriver().findElements(By.xpath(sXpath));
		
		return me;
	}
	
	public List<WebElement> getAllSearchTransactions_ios () throws Exception{
		
//		String sXpath = "**/XCUIElementTypeTable/XCUIElementTypeCell";
		String sXpath = "**/XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText[`name='topLeftLabel'`]";
		List <WebElement> me = null;
		
		Verify.waitForObject(this.allTransactions, 2);
		me = Engine.getDriver().findElements(AppiumBy.iOSClassChain(sXpath));
		
		return me;
	}
	
	public void tapOnFirstTransactionFromSearchResult() throws Exception{
		
		List<WebElement> li = getAllSearchTransactions ();
		Commentary.log(LogStatus.INFO, "No of Transactions appeared in the search .."+li.size());
		
		if (li.isEmpty())
			Commentary.log(LogStatus.ERROR, "No Transactions available on Transaction list screen");
		else
			li.get(0).click();
		
		Thread.sleep(1000);
		
		TransactionDetailPage tr = new TransactionDetailPage();
		
		if (Verify.objExists(tr.allowButton)) {
			tr.allowButton.click();
			Thread.sleep(1000);
		}
		
	}
	
	public Double getRunningBalancefromTransaction (int transactionNumber) throws Exception {
		Double dTransactionAmount = null;
		Helper h = new Helper();
		
		if (h.getEngine().equalsIgnoreCase("android")) {
			transactionNumber = transactionNumber-1;
			List <WebElement> me = null;
			String locator = "//android.widget.LinearLayout[contains(@resource-id,'list_row')]/android.widget.RelativeLayout/..";
			me = Engine.getDriver().findElements(By.xpath(locator));
			WebElement me1 = me.get(transactionNumber).findElement(By.xpath("//android.widget.TextView[@resource-id='com.quicken.qm2014:id/list_row_date']"));
			String sTransactionAmount = me1.getText();
			dTransactionAmount = h.processBalanceAmount(sTransactionAmount.replace("$", ""));
		} 
		else {
			String locator = "**/XCUIElementTypeStaticText[`name=='bottomRightLabel'`]["+transactionNumber+"]";
			WebElement me = (WebElement) Engine.getDriver().findElement(AppiumBy.iOSClassChain(locator));
			String sTransactionAmount = me.getAttribute("value");
			dTransactionAmount = h.processBalanceAmount(sTransactionAmount.replace("$", ""));
		}
		return dTransactionAmount;
	}
	
	public Double getTransactionAmount(int transactionNumber) throws Exception {
		Double dAmount = null;
		Helper h = new Helper();
		
		if (h.getEngine().equalsIgnoreCase("android")) {
//			transactionNumber = transactionNumber-1;
//			List <WebElement> me = null;
//			String locator = "//android.widget.LinearLayout[contains(@resource-id,'list_row')]/android.widget.RelativeLayout/..";
//			me = Engine.getDriver().findElements(By.xpath(locator));
//			WebElement me1 = me.get(transactionNumber).findElement(By.xpath("//android.widget.TextView[@resource-id='com.quicken.qm2014:id/list_row_amount']"));
			WebElement me1 = Engine.getDriver().findElement(By.xpath("(//android.widget.TextView[@resource-id='com.quicken.qm2014:id/list_row_amount'])["+transactionNumber+"]"));
			String sTransactionAmount = me1.getText();
			dAmount = h.processBalanceAmount(sTransactionAmount.replace("$", ""));
		} 
		else {
			String locator = "**/XCUIElementTypeStaticText[`name=='topRightLabel'`]["+transactionNumber+"]";
			WebElement me = (WebElement) Engine.getDriver().findElement(AppiumBy.iOSClassChain(locator));
			String sTransactionAmount = me.getAttribute("value");
			dAmount = h.processBalanceAmount(sTransactionAmount.replace("$", ""));
		}
		return dAmount;	
	}
	
	public String getTransactionDate (int transactionNumber) throws Exception {
		String sTransactionAmount = null;
		Helper h = new Helper();
		Thread.sleep(1000);
		
		if (h.getEngine().equalsIgnoreCase("android")) {
//			transactionNumber = transactionNumber-1;
//			List <WebElement> me = null;
//			String locator = "//android.widget.LinearLayout[contains(@resource-id,'list_row')]/android.widget.RelativeLayout/..";
//			me = Engine.getDriver().findElements(By.xpath(locator));
//			WebElement me1 = me.get(transactionNumber).findElement(By.xpath("//android.widget.TextView[@resource-id='com.quicken.qm2014:id/list_row_date']"));
			WebElement me1 = Engine.getDriver().findElement(By.xpath("(//android.widget.TextView[@resource-id='com.quicken.qm2014:id/list_row_date'])["+transactionNumber+"]"));
			sTransactionAmount = me1.getText();
		} 
		else {
			String locator = "**/XCUIElementTypeStaticText[`name=='bottomRightLabel'`]["+transactionNumber+"]";
			WebElement me = (WebElement) Engine.getDriver().findElement(AppiumBy.iOSClassChain(locator));
			sTransactionAmount = me.getText();
		}
		return sTransactionAmount;
	}
}