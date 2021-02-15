package dugout;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import referee.Verify;
import support.Engine;
import support.Helper;

public class NetWorthPage {

	public NetWorthPage() {
		try {
			Helper h = new Helper();
			if(h.getEngine().equals("android")) 
				PageFactory.initElements(new AppiumFieldDecorator(Engine.ad), this);
			else 
				PageFactory.initElements(new AppiumFieldDecorator(Engine.iosd), this);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name='Back'`]")
	@AndroidFindBy(xpath="//android.widget.ImageButton")
	public MobileElement backButton;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name BEGINSWITH 'As of'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[starts-with(@text, 'As of')]")
	public MobileElement dateHeaderText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name='NetWorthInfo Button'`]")
	@AndroidFindBy(xpath="//androidx.appcompat.widget.LinearLayoutCompat/android.widget.TextView")
	public MobileElement netWorthInfoButton;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name ='NET WORTH'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='NET WORTH']")
	public MobileElement netWorthText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'NET WORTH'`]/XCUIElementTypeStaticText[`name contains '$'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='NET WORTH']/../android.widget.TextView[contains(@text,'$')]")
	public MobileElement netWorthAmount;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name ='Assets'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Assets']")
	public MobileElement assetsTab;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Assets'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Assets']/../android.widget.TextView[2]")
	public MobileElement assetsTabAmount;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name ='Liabilities'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Liabilities']")
	public MobileElement liabilitiesTab;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Liabilities'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Liabilities']/../android.widget.TextView[2]")
	public MobileElement liabilitiesTabAmount;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='Account Type: BANKING'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='BANKING']")
	public MobileElement bankingLabel;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Account Type: BANKING'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='BANKING']/../android.widget.TextView[contains(@text,'$')]")
	public MobileElement bankingAmount;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Account Name: Manual_Checking Account Balance: '`][-1]/**/XCUIElementTypeStaticText[-1]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Manual_Checking']/../android.widget.TextView[contains(@text,'$')]")
	public MobileElement manualCheckingAccountAmount;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='Account Type: OTHER ASSETS'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='OTHER ASSETS']")
	public MobileElement otherAssetsLabel;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Account Type: OTHER ASSETS'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='OTHER ASSETS']/../android.widget.TextView[contains(@text,'$')]")
	public MobileElement otherAssetsAmount;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='Account Type: INVESTMENTS'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='INVESTMENTS']")
	public MobileElement investmentsLabel;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Account Type: INVESTMENTS'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='INVESTMENTS']/../android.widget.TextView[contains(@text,'$')]")
	public MobileElement investmentsAmount;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='Account Type: CREDIT CARDS'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='CREDIT CARDS']")
	public MobileElement creditCardsLabel;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Account Type: CREDIT CARDS'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='CREDIT CARDS']/../android.widget.TextView[contains(@text,'$')]")
	public MobileElement creditCardsAmount;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='Account Type: OTHER LIABILITIES'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='OTHER LIABILITIES']")
	public MobileElement OtherLiabilitesLabel;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Account Type: OTHER LIABILITIES'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='OTHER LIABILITIES']/../android.widget.TextView[contains(@text,'$')]")
	public MobileElement otherLiabilitiesAmount;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name='Net Worth Info'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Net Worth Info']")
	public MobileElement netWorthInfoHeaderText;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeNavigationBar[`name='Net Worth Info'`]/XCUIElementTypeButton[`name='Close Button'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Net Worth Info']/../android.widget.ImageButton")
	public MobileElement netWorthInfoDrawerCloseButton;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='How do I get my actual net worth?'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='How do I get my actual net worth?']")
	public MobileElement netWorthInfoQuestion1;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='Your net worth is your assets minus your liabilities. You can get your actual net worth by syncing your checking, credit cards, investments, loans, savings, 401k, and other accounts. The sum of these accounts is your net worth.'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Your net worth is your assets minus your liabilities. You can get your actual net worth by syncing your checking, credit cards, investments, loans, savings, 401k, and other accounts. The sum of these accounts is your net worth.']")
	public MobileElement netWorthInfoAnswer1;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='How is my net worth calculated?'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='How is my net worth calculated?']")
	public MobileElement netWorthInfoQuestion2;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='Your net worth is calculated by subtracting your liabilities from your assets. Your assets include your checking, savings, investments, and 401k accounts. Your liabilities include any credit cards, car loans, mortgages, or other debts.'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Your net worth is calculated by subtracting your liabilities from your assets. Your assets include your checking, savings, investments, and 401k accounts. Your liabilities include any credit cards, car loans, mortgages, or other debts.']")
	public MobileElement netWorthInfoAnswer2;

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
			Engine.ad.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"INVESTMENTS\").instance(0))"));
			Thread.sleep(1000);
		}
		else {
			String cc="**/*[`name=='Account Type: INVESTMENTS'`]";
			MobileElement me = (MobileElement) Engine.iosd.findElement(MobileBy.iOSClassChain(cc));
			String me_id = me.getId();
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("element", me_id);
			scrollObject.put("toVisible", "not an empty string");
			Engine.iosd.executeScript("mobile:scroll", scrollObject);  // scroll to the target element
			Thread.sleep(1000);
		}
	}
}