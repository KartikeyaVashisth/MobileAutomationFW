package dugout;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import support.Engine;
import support.Helper;

public class InvestingPage {
	
	public InvestingPage () {
		try {
			
			PageFactory.initElements(new AppiumFieldDecorator(Engine.getDriver()),this);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	//@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Investing\"]")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name='Investments'`]")
	@AndroidFindBy(xpath="//android.view.View[@text='Investments']")
	public MobileElement investingHeader;
	
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeButton[`name='Go back'`]")
	@AndroidFindBy(xpath="//*[@text='Investments']/../*[@class='android.widget.Button']")
	public MobileElement backButtonOnHeader;
	
	//@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"button Securities\"]")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name='button Holdings'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Holdings']")
	public MobileElement holdingsTab;
	
	//@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"button Accounts\"]")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name='button Accounts'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Accounts']")
	public MobileElement accountsTab;
	
	//@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"button Watch List\"]")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name='button Watch List'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Watch List']")
	public MobileElement watchlistTab;
	
	//@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Today's Change\"]")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name==\"Today's Change\"`]")
	//@AndroidFindBy(xpath="//*[@text='Today\'s Change']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text=concat('Today',\"'\",'s Change')]")
	public MobileElement todaysChangeLabel;
	
	//@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Total Value\"]")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name==\"Total Value\"`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Total Value']")
	public MobileElement totalValueLabel;
	
	//@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Cash Balances\"]")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name==\"Cash Balances\"`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Cash Balances']")
	public MobileElement cashbalancesLabel;
	
	//@iOSFindBy(xpath="//*[starts-with(@name, 'Securities by Company Name')]")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name=='SECURITIES BY COMPANY NAME'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='SECURITIES BY COMPANY NAME']")
	public MobileElement securitiesByCompanyNameLabel;
	
//	@iOSFindBy(xpath="//*[starts-with(@name, 'Bank Account Balances Checking Accounts')]")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name BEGINSWITH 'Bank Account Balances Checking Accounts'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Brokerage1']")
	public MobileElement brokerageAccountNameLabel;
	
//	@iOSFindBy(xpath="//*[starts-with(@name, 'Bank Account Balances Checking Accounts')]")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name BEGINSWITH 'Bank Account Balances Checking Accounts'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Brokerage']/../android.widget.TextViewe[@text='1 holding']")
	public MobileElement fiveHoldingsNameLabel;
	
//	@iOSFindBy(xpath="//*[starts-with(@name, 'Last synced from Quicken Desktop')]")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name BEGINSWITH 'Last synced from Quicken Desktop'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[starts-with(@text,'Last synced from Quicken Desktop')]")
	public MobileElement lastSyncedFooter;
	
	public void navigateBackToDashboard() throws Exception{
		
		backButtonOnHeader.click();
		Thread.sleep(5000);
		
	}

}
