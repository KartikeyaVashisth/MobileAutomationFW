package dugout;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import referee.Commentary;
import referee.Verify;
import support.Engine;
import support.Helper;

public class AllAccountsPage {
	
	public AllAccountsPage () {
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
	
	@iOSFindBy(xpath="//*[contains(@name,'All Transactions')]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='All Transactions']")
	public MobileElement textAllTransactions;
	
	@iOSFindBy(xpath="//XCUIElementTypeButton[@name='Back']")
	@AndroidFindBy(xpath="//*[@class='android.widget.ImageButton']")
	public MobileElement backButton;
	
	@iOSFindBy(xpath="//XCUIElementTypeSearchField[@name=\"Search Transactions\"]")
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Search Transactions']")
	public MobileElement searchTransactionTxtField;
	
	@iOSFindBy(xpath="//XCUIElementTypeButton[@name=\"sort icon\"]")
	@AndroidFindBy(xpath="//android.widget.ImageButton[contains(@resource-id,'sortBtn')]")
	public MobileElement sortTransaction;
	
	@iOSFindBy(xpath="//XCUIElementTypeButton[@name=\"Clear text\"]")
	@AndroidFindBy(xpath="//android.widget.ImageView[contains(@resource-id,'search_close_btn')]")
	public MobileElement closeSearch;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"No Results Found\"]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='No Results Found']")
	public MobileElement noResultsFound;
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Add Transaction\"]")
	@AndroidFindBy(xpath="//android.widget.Button[@text='Allow']")
	public MobileElement allowButton;
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Add Transaction\"]")
	@AndroidFindBy(xpath="//android.widget.Button[@text='Deny']")
	public MobileElement denyButton;
	
	@iOSFindBy(xpath="//XCUIElementTypeButton[@name=\"fab\"]")
	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.quicken.qm2014:id/fab']")
	public MobileElement addTransaction;
	
	public void navigateBackToDashboard() throws Exception {
		
		backButton.click();
		Thread.sleep(3000);
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
		
		List<MobileElement> li = getAllSearchTransactions ();
		
		Commentary.log(LogStatus.INFO, "No of transactions found "+li.size());
		
		if (li.isEmpty())
			return false;
		else
			return true;
		
		
	}
	
	public List<MobileElement> getAllSearchTransactions() throws Exception{
		
		 Helper h = new Helper();
		 if (h.getEngine().equals("android"))
			 return getAllSearchTransactions_android();
		 else
			 return getAllSearchTransactions_ios();		
	}
	
	public List<MobileElement> getAllSearchTransactions_android () throws Exception{
		
		String sXpath = "//android.widget.LinearLayout[contains(@resource-id,'list_row')]/android.widget.RelativeLayout/..";
		List <MobileElement> me = null;

		Engine.ad.getContext();
		me = Engine.ad.findElements(By.xpath(sXpath));
		System.out.println(me.size());
		
		return me;
	}
	
	public List<MobileElement> getAllSearchTransactions_ios () throws Exception{
		
		
	
		String sXpath = "//XCUIElementTypeTable/XCUIElementTypeCell";
		List <MobileElement> me = null;
		Engine.iosd.getContext();
		me = Engine.iosd.findElements(By.xpath(sXpath));
		System.out.println(me.size());
		
		return me;
	}
	
	
	
	public void tapOnFirstTransactionFromSearchResult() throws Exception{
		
		
		
		List<MobileElement> li = getAllSearchTransactions ();
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
	

}
