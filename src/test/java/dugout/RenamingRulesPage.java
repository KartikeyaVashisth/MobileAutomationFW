package dugout;

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

public class RenamingRulesPage {
	
	public RenamingRulesPage () {
		try {
			PageFactory.initElements(new AppiumFieldDecorator(Engine.getDriver()),this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name == 'Settings, back'`]")
	@AndroidFindBy(xpath="//android.widget.Button[@content-desc='Settings, back']/android.widget.ImageView")
	public MobileElement back;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name == 'Renaming Rules'`]")
	@AndroidFindBy(xpath="//android.view.View[normalize-space(@text)='Renaming Rules']")
	public MobileElement renamingRulesHeader;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == 'No renaming rules added yet'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='No renaming rules added yet']")
	public MobileElement noRenamingRulesAddedYet;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`label == \"addRenameRule\"`][-1]")
	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='addRenameRule']")
	public MobileElement add;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name BEGINSWITH 'If '`]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text, 'contains') or contains(@text,'Quicken name')]")
	public MobileElement firstRuleOnTheScreen;
	
	public boolean verifyRule(String sRenamePayeeTo) throws Exception{
		
		Helper h = new Helper();

		if (h.getEngine().equals("android")) {
			
			return verifyRuleExists_android(sRenamePayeeTo);
			
		}
		
		return verifyRuleExists_ios(sRenamePayeeTo);
		
	}
	
	private boolean verifyRuleExists_android(String sRenamePayeeTo) throws Exception{
		
		String xPath = "//android.widget.TextView[@text='"+sRenamePayeeTo+"']";
		
		try {
			return Engine.getDriver().findElementByXPath(xPath).isDisplayed();
		}
		catch (Exception e) {
			return false;
		}
	}
	
	private boolean verifyRuleExists_ios(String sRenamePayeeTo) throws Exception{
		
		String iosClassChain = "**/XCUIElementTypeStaticText[@name='"+sRenamePayeeTo+"']";
		
		try {
			return Engine.getDriver().findElement(MobileBy.iOSClassChain(iosClassChain)).isDisplayed();
		}
		catch (Exception e) {
			return false;
		}
	}
	
	
	public MobileElement getRule (String sRenamePayeeTo) throws Exception{
		
		Helper h = new Helper();

		if (h.getEngine().equals("android")) {
			
			return getRule_android(sRenamePayeeTo);
			
		}
		
		return getRule_ios(sRenamePayeeTo);
	}
	
	
	public MobileElement getRule_android (String sRenamePayeeTo) throws Exception{
		
		String xPath = "//android.widget.TextView[@text='"+sRenamePayeeTo+"']";
		return Engine.getDriver().findElement(By.xpath(xPath));
		
	}
	
	public MobileElement getRule_ios (String sRenamePayeeTo) throws Exception{
		
		String iosClassChain = "**/XCUIElementTypeStaticText[@name='"+sRenamePayeeTo+"']";
		return Engine.getDriver().findElement(MobileBy.iOSClassChain(iosClassChain));
		
	}
	
	public void deleteRule (String sRenamePayeeTo) throws Exception{
		
		Helper h = new Helper();
		if (h.getEngine().equals("android"))
			deleteRule_android(sRenamePayeeTo);
		else
			deleteRule_ios(sRenamePayeeTo);
 
	}
	
	private void deleteRule_android(String sRenamePayeeTo) throws Exception{
		
		String xPath = "//android.widget.TextView[@text='"+sRenamePayeeTo+"']";
		Engine.getDriver().findElement(By.xpath(xPath)).click();
		Thread.sleep(2000);
		
		ViewRenamingRulesPage vrp = new ViewRenamingRulesPage();
		vrp.deleteTheRule();	
	}
	
	private void deleteRule_ios(String sRenamePayeeTo) throws Exception{
		
		String iosClassChain = "**/XCUIElementTypeStaticText[@name='"+sRenamePayeeTo+"']";
		Engine.getDriver().findElement(MobileBy.iOSClassChain(iosClassChain)).click();
		Thread.sleep(2000);
		
		ViewRenamingRulesPage vrp = new ViewRenamingRulesPage();
		vrp.deleteTheRule();	
	}
	
	public void deleteAllRules() throws Exception{
		
		while (Verify.objExists(firstRuleOnTheScreen)) {
			firstRuleOnTheScreen.click();
			Thread.sleep(2000);
			ViewRenamingRulesPage vrp = new ViewRenamingRulesPage();
			vrp.deleteTheRule();	
		}
		
		
		
	}
	
	public void selectRule(String sRenamePayeeTo) throws Exception{
		
		getRule(sRenamePayeeTo).click();
		
	}
	
	public void addRule(String setPayeeNameTo, String matchingCriteriaRule, String downloadedName) throws Exception{
		
		this.add.click();
		Thread.sleep(1000);
		
		createRenamingRulePage crp = new createRenamingRulePage();
		crp.addRule(setPayeeNameTo, matchingCriteriaRule, downloadedName);
	}
	
	public void editRule(String setPayeeNameTo, String matchingCriteriaRule, String downloadedName) throws Exception{
		
		createRenamingRulePage crp = new createRenamingRulePage();
		crp.addRule(setPayeeNameTo, matchingCriteriaRule, downloadedName);
	}
	
	
	
	
	
	

}
