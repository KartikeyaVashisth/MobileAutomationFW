package dugout;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.HidesKeyboard;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import support.Engine;

public class createRenamingRulePage {
	
	public createRenamingRulePage () {
		try {
			
			PageFactory.initElements(new AppiumFieldDecorator(Engine.getDriver()),this);
		} catch (Exception e) {
			
			e.printStackTrace();
		}	
	}
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name == 'Create Renaming Rule'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Create Renaming Rule']")
	public WebElement createRenamingRuleHeader;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name == 'Renaming Rules, back'`]")
	@AndroidFindBy(xpath="//android.widget.Button[@content-desc='Renaming Rules, back']")
	public WebElement back;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == 'TIP : Rule shall be applied if downloaded payee name contains all strings in the same order.'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='TIP : Rule shall be applied if downloaded payee name contains all strings in the same order.']")
	public WebElement ruleShallBeApplied;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField")
	@AndroidFindBy(xpath="//android.widget.EditText")
	public WebElement payeeNameTo;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name BEGINSWITH 'Matching criteria'`]")
	@AndroidFindBy(xpath="//android.widget.Button[@content-desc='closeRule']")
	public WebElement matchingCriteria;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[-1]")
	@AndroidFindBy(xpath="//android.widget.EditText[2]")
	public WebElement downloadPayeeName;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name == 'Save' or name == 'save'`][-1]")
	@AndroidFindBy(xpath="//android.widget.Button[@content-desc='Save']")
	public WebElement save;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == 'Rule'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Rule']")
	public WebElement ruleDrawerHeader;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name == 'closeRule'`]")
	@AndroidFindBy(xpath="//android.widget.Button[@content-desc='closeRule']")
	public WebElement closeRule;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name == 'Apply'`][-1]")
	@AndroidFindBy(xpath="//android.widget.Button[@content-desc='Apply']")
	public WebElement apply;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name == 'If Quicken Name is'`]")
	@AndroidFindBy(xpath="//android.widget.Button[@content-desc='If Quicken Name is']")
	public WebElement ifQuickenNameIs;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name == 'If Payee contains'`]")
	@AndroidFindBy(xpath="//android.widget.Button[@content-desc='If Payee contains']")
	public WebElement ifPayeeContains;
	
	public void addRule (String setPayeeNameTo, String matchingCriteriaRule, String downloadedName) throws Exception{
		
		if (! setPayeeNameTo.equals(""))
		{
			payeeNameTo.clear();
			payeeNameTo.sendKeys(setPayeeNameTo);
			((HidesKeyboard) Engine.getDriver()).hideKeyboard();
		}
		
		if (! matchingCriteriaRule.equals(""))
		{
			matchingCriteria.click();
			Thread.sleep(1000);
			if (matchingCriteriaRule.toUpperCase().contains("PAYEE"))
				ifPayeeContains.click();
			else
				ifQuickenNameIs.click();
			apply.click();
			
		}
		
		if (! downloadedName.equals(""))
		{
			downloadPayeeName.clear();
			downloadPayeeName.sendKeys(downloadedName);
			((HidesKeyboard) Engine.getDriver()).hideKeyboard();
		}
		
		this.save.click();
		Thread.sleep(3000);
			
		
	}
	
	
}
