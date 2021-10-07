package dugout;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
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
	public MobileElement createRenamingRuleHeader;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name == 'Renaming Rules, back'`]")
	@AndroidFindBy(xpath="//android.widget.Button[@content-desc='Renaming Rules, back']")
	public MobileElement back;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == 'TIP : Rule shall be applied if downloaded payee name contains all strings in the same order.'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='TIP : Rule shall be applied if downloaded payee name contains all strings in the same order.']")
	public MobileElement ruleShallBeApplied;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField")
	@AndroidFindBy(xpath="//android.widget.EditText")
	public MobileElement payeeNameTo;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name BEGINSWITH 'Matching criteria'`]")
	@AndroidFindBy(xpath="//android.widget.Button[@content-desc='closeRule']")
	public MobileElement matchingCriteria;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[-1]")
	@AndroidFindBy(xpath="//android.widget.EditText[2]")
	public MobileElement downloadPayeeName;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name == 'Save'`][-1]")
	@AndroidFindBy(xpath="//android.widget.Button[@content-desc='Save']")
	public MobileElement save;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == 'Rule'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Rule']")
	public MobileElement ruleDrawerHeader;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name == 'closeRule'`]")
	@AndroidFindBy(xpath="//android.widget.Button[@content-desc='closeRule']")
	public MobileElement closeRule;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name == 'Apply'`][-1]")
	@AndroidFindBy(xpath="//android.widget.Button[@content-desc='Apply']")
	public MobileElement apply;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name == 'If Quicken Name is'`]")
	@AndroidFindBy(xpath="//android.widget.Button[@content-desc='If Quicken Name is']")
	public MobileElement ifQuickenNameIs;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name == 'If Payee contains'`]")
	@AndroidFindBy(xpath="//android.widget.Button[@content-desc='If Payee contains']")
	public MobileElement ifPayeeContains;
	
	public void addRule (String setPayeeNameTo, String matchingCriteriaRule, String downloadedName) throws Exception{
		
		if (! setPayeeNameTo.equals(""))
		{
			payeeNameTo.clear();
			payeeNameTo.sendKeys(setPayeeNameTo);
		}
		
		if (! matchingCriteriaRule.equals(""))
		{
			matchingCriteria.click();
			Thread.sleep(1000);
			if (matchingCriteriaRule.toUpperCase().contains("PAYEE"))
				ifPayeeContains.click();
			else
				ifQuickenNameIs.clear();
			apply.click();
			
		}
		
		if (! downloadedName.equals(""))
		{
			downloadPayeeName.clear();
			downloadPayeeName.sendKeys(downloadedName);
		}
		
		this.save.click();
		Thread.sleep(3000);
			
		
	}
	
	
}
