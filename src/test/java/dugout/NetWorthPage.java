package dugout;

import java.util.HashMap;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import referee.Verify;
import support.Engine;
import support.Helper;

public class NetWorthPage {

	public NetWorthPage() {
		try {
			
			PageFactory.initElements(new AppiumFieldDecorator(Engine.getDriver()),this);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name='Go back'`]")
	@AndroidFindBy(xpath="//android.widget.Button")
	public WebElement backButton;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name BEGINSWITH 'As of'`]")
	@AndroidFindBy(xpath="//android.view.View[starts-with(@text, 'As of')]")
	public WebElement dateHeaderText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='netWorthInfo'`][-1]")
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='netWorthInfo']")
	public WebElement netWorthInfoButton;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name ='NET WORTH'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='NET WORTH']")
	public WebElement netWorthText;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains 'Net Worth:'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Net Worth:')]")
	public WebElement netWorthAmountOnDashboard;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'NET WORTH'`]/XCUIElementTypeStaticText[`name contains '$'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='NET WORTH']/../android.widget.TextView[contains(@text,'$')]")
	public WebElement netWorthAmount;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name ='Assets'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Assets']")
	public WebElement assetsTab;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Assets'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Assets']/../android.widget.TextView[2]")
	public WebElement assetsTabAmount;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name ='Liabilities'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Liabilities']")
	public WebElement liabilitiesTab;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Liabilities'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Liabilities']/../android.widget.TextView[2]")
	public WebElement liabilitiesTabAmount;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='Account Type: BANKING'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='BANKING']")
	public WebElement bankingLabel;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Account Type: BANKING'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='BANKING']/../android.widget.TextView[contains(@text,'$')]")
	public WebElement bankingAmount;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Account Name: Manual_Checking Account Balance: '`][-1]/**/XCUIElementTypeStaticText[-1]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Manual_Checking']/../android.widget.TextView[contains(@text,'$')]")
	public WebElement manualCheckingAccountAmount;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='Account Type: OTHER ASSETS'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='OTHER ASSETS']")
	public WebElement otherAssetsLabel;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Account Type: OTHER ASSETS'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='OTHER ASSETS']/../android.widget.TextView[contains(@text,'$')]")
	public WebElement otherAssetsAmount;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='Account Type: INVESTMENTS'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='INVESTMENTS']")
	public WebElement investmentsLabel;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Account Type: INVESTMENTS'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='INVESTMENTS']/../android.widget.TextView[contains(@text,'$')]")
	public WebElement investmentsAmount;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='Account Type: CREDIT CARDS'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='CREDIT CARDS']")
	public WebElement creditCardsLabel;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Account Type: CREDIT CARDS'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='CREDIT CARDS']/../android.widget.TextView[contains(@text,'$')]")
	public WebElement creditCardsAmount;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='Account Type: OTHER LIABILITIES'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='OTHER LIABILITIES']")
	public WebElement OtherLiabilitesLabel;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Account Type: OTHER LIABILITIES'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='OTHER LIABILITIES']/../android.widget.TextView[contains(@text,'$')]")
	public WebElement otherLiabilitiesAmount;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name='Net Worth Info'`]")
	@AndroidFindBy(xpath="//android.view.View[@text='Net Worth Info']")
	public WebElement netWorthInfoHeaderText;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name='closeNewWorthInfo'`]")
	@AndroidFindBy(xpath="//android.view.View[@text='Net Worth Info']/..//android.widget.ImageView")
	public WebElement netWorthInfoDrawerCloseButton;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='How do I get my actual net worth?'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='How do I get my actual net worth?']")
	public WebElement netWorthInfoQuestion1;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='Your net worth is your assets minus your liabilities. You can get your actual net worth by syncing your checking, credit cards, investments, loans, savings, 401k, and other accounts. The sum of these accounts is your net worth.'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Your net worth is your assets minus your liabilities. You can get your actual net worth by syncing your checking, credit cards, investments, loans, savings, 401k, and other accounts. The sum of these accounts is your net worth.']")
	public WebElement netWorthInfoAnswer1;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='How is my net worth calculated?'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='How is my net worth calculated?']")
	public WebElement netWorthInfoQuestion2;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='Your net worth is calculated by subtracting your liabilities from your assets. Your assets include your checking, savings, investments, and 401k accounts. Your liabilities include any credit cards, car loans, mortgages, or other debts.'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Your net worth is calculated by subtracting your liabilities from your assets. Your assets include your checking, savings, investments, and 401k accounts. Your liabilities include any credit cards, car loans, mortgages, or other debts.']")
	public WebElement netWorthInfoAnswer2;

	public void tapOnLiabilitesTab() throws Exception {

		Verify.waitForObject(this.liabilitiesTab, 1);
		this.liabilitiesTab.click();
		Thread.sleep(1000);
	}

	public void tapOnAssetsTab() throws Exception {

		Verify.waitForObject(this.assetsTab, 1);
		this.assetsTab.click();
		Thread.sleep(1000);
	}

	public void scrollToInvestmentsLabel() throws Exception {

		Helper h = new Helper();
		if (h.getEngine().equals("android")){
			String sXpath="//android.widget.TextView[@text='INVESTMENTS']";
			Engine.getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"INVESTMENTS\").instance(0))"));
			Thread.sleep(1000);
		}
		else {
			String cc="**/*[`name=='Account Type: INVESTMENTS'`]";
			WebElement me = Engine.getDriver().findElement(AppiumBy.iOSClassChain(cc));
//			String me_id = me.getId();
			HashMap<String, String> scrollObject = new HashMap<String, String>();
//			scrollObject.put("element", me_id);
			scrollObject.put("toVisible", "not an empty string");
			Engine.getDriver().executeScript("mobile:scroll", scrollObject);  // scroll to the target element
			Thread.sleep(1000);
		}
	}
	
	public void scrollToOtherAssetsLabel() throws Exception {

		Helper h = new Helper();
		if (h.getEngine().equals("android")){
			String sXpath="//android.widget.TextView[@text='OTHER ASSETS']";
			Engine.getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"OTHER ASSETS\").instance(0))"));
			Thread.sleep(1000);
		}
		else {
			String cc="**/*[`name=='Account Type: OTHER ASSETS'`]";
			WebElement me = Engine.getDriver().findElement(AppiumBy.iOSClassChain(cc));
//			String me_id = me.getId();
			HashMap<String, String> scrollObject = new HashMap<String, String>();
//			scrollObject.put("element", me_id);
			scrollObject.put("toVisible", "not an empty string");
			Engine.getDriver().executeScript("mobile:scroll", scrollObject);  // scroll to the target element
			Thread.sleep(1000);
		}
	}
	
	public void navigateToNetWorthCard() throws Exception {
		
		SettingsPage sp = new SettingsPage();
		sp.clickOnReportsOption();

		Verify.waitForObject(sp.netWorthOption, 1);
		sp.netWorthOption.click();
		Thread.sleep(1000);
	}
}