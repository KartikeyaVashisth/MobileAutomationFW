package dugout;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import referee.Verify;
import support.Engine;

public class ViewRenamingRulesPage {
	
	public ViewRenamingRulesPage () {
		try {
			
			PageFactory.initElements(new AppiumFieldDecorator(Engine.getDriver()),this);
		} catch (Exception e) {
			
			e.printStackTrace();
		}			
	}
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name == 'Renaming Rules, back'`]")
	@AndroidFindBy(xpath="//android.widget.Button[@content-desc='Renaming Rules, back']")
	public WebElement back;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name == 'Create Renaming Rule'`]")
	@AndroidFindBy(xpath="//android.view.View[@text='Create Renaming Rule']")
	public WebElement createRenamingRuleHeader;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField")
	@AndroidFindBy(xpath="//android.widget.EditText[1]")
	public WebElement payeeNameTo;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[-1]")
	@AndroidFindBy(xpath="//android.widget.EditText[2]")
	public WebElement downloadPayeeName;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name BEGINSWITH 'Matching criteria'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[starts-with(@text,'If Quicken') or starts-with(@text,'If Payee')]")
	public WebElement matchingCriteria;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name == 'If Quicken Name is'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text = 'If Quicken Name is']")
	public WebElement ifQuickenNameIs;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name == 'If Payee contains'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text = 'If Payee contains']")
	public WebElement ifPayeeContains;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name == 'Apply'`][-1]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text = 'Apply']")
	public WebElement applyBtn;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name == 'Save'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text = 'Save']")
	public WebElement saveBtn;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name == 'Delete Rule'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Delete Rule']")
	public WebElement deleteRule;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name == 'Delete'`]")
	@AndroidFindBy(xpath="//android.widget.Button[normalize-space(@text)='Delete']")
	public WebElement delete;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name == 'Cancel'`]")
	@AndroidFindBy(xpath="//android.widget.Button[normalize-space(@text)='Cancel']")
	public WebElement cancel;
	
	
	
	public void deleteTheRule() throws Exception{
		
		this.deleteRule.click();
		Thread.sleep(2000);
		
		if (Verify.objExists(this.delete))
			this.delete.click();
		Thread.sleep(4000);
		
	}
	
	public void editRule (String setPayeeNameTo, String matchingCriteriaRule, String downloadedName) throws Exception{
		
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
			applyBtn.click();
			
		}
		
		if (! downloadedName.equals(""))
		{
			downloadPayeeName.clear();
			downloadPayeeName.sendKeys(downloadedName);
		}
		
		this.saveBtn.click();
		Thread.sleep(3000);
			
		
	}

	
	

}
