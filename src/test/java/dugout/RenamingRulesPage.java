package dugout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
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
	public WebElement back;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name == 'Renaming Rules'`]")
	@AndroidFindBy(xpath="//android.view.View[normalize-space(@text)='Renaming Rules']")
	public WebElement renamingRulesHeader;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == 'No renaming rules added yet'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='No renaming rules added yet']")
	public WebElement noRenamingRulesAddedYet;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`label == \"addRenameRule\"`][-1]")
	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='addRenameRule']")
	public WebElement add;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name BEGINSWITH 'If '`]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text, 'contains') or contains(@text,'Quicken name')]")
	public WebElement firstRuleOnTheScreen;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name BEGINSWITH 'If '`]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text, 'contains') or contains(@text,'Quicken name')]")
	public WebElement firstRuleUnderMultiple;
	
	//android.widget.TextView[contains(@text,'Multiple')]
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name BEGINSWITH 'Multiple'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Multiple')]")
	public WebElement firstMultipleRule;
	
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
			return Engine.getDriver().findElement(By.xpath(xPath)).isDisplayed();
		}
		catch (Exception e) {
			return false;
		}
	}
	
	private boolean verifyRuleExists_ios(String sRenamePayeeTo) throws Exception{
		
		String iosClassChain = "**/XCUIElementTypeStaticText[`name='"+sRenamePayeeTo+"'`]";
		
		try {
			return Engine.getDriver().findElement(AppiumBy.iOSClassChain(iosClassChain)).isDisplayed();
		}
		catch (Exception e) {
			return false;
		}
	}
	
	
	public WebElement getRule (String sRenamePayeeTo) throws Exception{
		
		Helper h = new Helper();

		if (h.getEngine().equals("android")) {
			
			return getRule_android(sRenamePayeeTo);
			
		}
		
		return getRule_ios(sRenamePayeeTo);
	}
	
	
	public WebElement getRule_android (String sRenamePayeeTo) throws Exception{
		
		String xPath = "//android.widget.TextView[@text='"+sRenamePayeeTo+"']";
		return Engine.getDriver().findElement(By.xpath(xPath));
		
	}
	
	public WebElement getRule_ios (String sRenamePayeeTo) throws Exception{
		
		String iosClassChain = "**/XCUIElementTypeStaticText[`name='"+sRenamePayeeTo+"'`]";
		return Engine.getDriver().findElement(AppiumBy.iOSClassChain(iosClassChain));
		
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
		
		String iosClassChain = "**/XCUIElementTypeStaticText[`name='"+sRenamePayeeTo+"'`]";
		Engine.getDriver().findElement(AppiumBy.iOSClassChain(iosClassChain)).click();
		Thread.sleep(2000);
		
		ViewRenamingRulesPage vrp = new ViewRenamingRulesPage();
		vrp.deleteTheRule();	
	}
	
	public void deleteAllSingleRules() throws Exception{
		while (Verify.objExists(firstRuleOnTheScreen)) {
			firstRuleOnTheScreen.click();
			Thread.sleep(2000);
			ViewRenamingRulesPage vrp = new ViewRenamingRulesPage();
			vrp.deleteTheRule();	
		}
		
		while (Verify.objExists(firstRuleUnderMultiple)) {
			firstRuleUnderMultiple.click();
			Thread.sleep(2000);
			ViewRenamingRulesPage vrp = new ViewRenamingRulesPage();
			vrp.deleteTheRule();	
		}
		
	}
	
	public void deleteAllMultipleRules() throws Exception{
		
		while (Verify.objExists(firstMultipleRule)) {
			firstMultipleRule.click(); // expand the rule
			Thread.sleep(1000);
			deleteAllSingleRules();
			
		}
		
	}
	
	public void deleteAllRules() throws Exception{
		deleteAllSingleRules();
		deleteAllMultipleRules();
		deleteAllSingleRules();
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
	
	private void expand_rule_ios(String sRenamePayeeTo) throws Exception{
		//String cc = "**/XCUIElementTypeOther[`name='"+sRenamePayeeTo+"'`]/XCUIElementTypeOther/XCUIElementTypeImage";
		String cc = "**/XCUIElementTypeOther[`name='"+sRenamePayeeTo+"'`][-1]";
		Engine.getDriver().findElement(AppiumBy.iOSClassChain(cc)).click();
		Thread.sleep(1000);
		
	}
	
	private void expand_rule_android(String sRenamePayeeTo) throws Exception{
		String sXpath = "//android.widget.TextView[@text='"+sRenamePayeeTo+"']/../android.widget.ImageView";
		Engine.getDriver().findElement(By.xpath(sXpath)).click();
		Thread.sleep(1000);
		
	}
	
	public void expand_rule(String sRenamePayeeTo) throws Exception{
		
		Helper h = new Helper();
		if (h.getEngine().equals("android"))
			expand_rule_android(sRenamePayeeTo);
		else
			expand_rule_ios(sRenamePayeeTo);
		
	}
	
	private boolean verify_multipleRule_ios(String sDownloadedName) throws Exception{
		
		String cc = "**/XCUIElementTypeOther[`name contains'"+sDownloadedName+"'`][-1]";
		
		try {
			return Engine.getDriver().findElement(AppiumBy.iOSClassChain(cc)).isDisplayed();
			
		}
		catch(Exception e) {
			return false;
		}
		
	}
	
	private boolean verify_multipleRule_android(String sDownloadedName) throws Exception{
		
		
		//android.widget.TextView[contains(@text,"If 'Payee name' contains")  and  contains(@text,'Dld')]
		String sXpath = "//android.widget.TextView[contains(@text,\"If 'Payee name' contains\")  and  contains(@text,'"+sDownloadedName+"')]";
		
		try {
			return Engine.getDriver().findElement(By.xpath(sXpath)).isDisplayed();
			
		}
		catch(Exception e) {
			return false;
		}
		
		
	}
	
	public boolean verify_multipleRule (String sDownloadedName) throws Exception{
		
		Helper h = new Helper();
		if (h.getEngine().equals("android"))
			return verify_multipleRule_android(sDownloadedName);
		else
			return verify_multipleRule_ios(sDownloadedName);
		
	}
	
	public String get_matchingCriteria (String sRenamePayeeTo) throws Exception{
		
		Helper h = new Helper();
		if (h.getEngine().equals("android"))
			return get_matchingCriteria_android(sRenamePayeeTo);
		else
			return get_matchingCriteria_ios(sRenamePayeeTo);
		
	}
	
	
	private String get_matchingCriteria_ios (String sRenamePayeeTo) throws Exception{
		
		String cc = "**/XCUIElementTypeOther[`name contains'"+sRenamePayeeTo+" If'`][-1]";
		
		if (Engine.getDriver().findElement(AppiumBy.iOSClassChain(cc)).getText().toUpperCase().contains("PAYEE NAME"))
			return "Payee".toUpperCase();
		else
			return "Quicken".toUpperCase();	
		
	}
	
	private String get_matchingCriteria_android (String sRenamePayeeTo) throws Exception{
		
		String sXpath = "//android.widget.TextView[@text='"+sRenamePayeeTo+"']/../android.widget.TextView[2]";
		
		if (Engine.getDriver().findElement(By.xpath(sXpath)).getText().toUpperCase().contains("PAYEE NAME"))
			return "Payee".toUpperCase();
		else
			return "Quicken".toUpperCase();	
		
	}
	
	public String get_DownloadPayeeName (String sRenamePayeeTo) throws Exception{
		
		Helper h = new Helper();
		if (h.getEngine().equals("android"))
			return get_DownloadPayeeName_android(sRenamePayeeTo);
		else
			return get_DownloadPayeeName_ios(sRenamePayeeTo);
		
	}
	
	
	private String get_DownloadPayeeName_ios (String sRenamePayeeTo) throws Exception{
		
		String cc = "**/XCUIElementTypeOther[`name contains'"+sRenamePayeeTo+" If'`]/XCUIElementTypeStaticText";
		
		String sActual = Engine.getDriver().findElement(AppiumBy.iOSClassChain(cc)).getText();
		
		String[] arr = sActual.split("\"");
		
		return arr[arr.length-1];
		
	}
	
	private String get_DownloadPayeeName_android (String sRenamePayeeTo) throws Exception{
		
		String sXpath = "//android.widget.TextView[@text='"+sRenamePayeeTo+"']/../android.widget.TextView[2]";
		
		String sActual = Engine.getDriver().findElement(By.xpath(sXpath)).getText();
		
		String[] arr = sActual.split("\"");
		
		return arr[arr.length-1];
		
	}
	
	public void deleteMatchingCriteriaOfARule (String sRenamePayeeTo, String sMatchingCriteria) throws Exception{
		
		Helper h = new Helper();
		if (h.getEngine().equals("android"))
			deleteCriteria_android(sRenamePayeeTo,sMatchingCriteria);
		else
			deleteCriteria_ios(sRenamePayeeTo,sMatchingCriteria);
 
	}
	
	private void deleteCriteria_android(String sRenamePayeeTo, String sCriteria) throws Exception{
		
		String xPath = "//android.widget.TextView[contains(@text,'"+sCriteria+"')]";
		this.expand_rule(sRenamePayeeTo);
		Engine.getDriver().findElement(By.xpath(xPath)).click();
		Thread.sleep(2000);
		
		ViewRenamingRulesPage vrp = new ViewRenamingRulesPage();
		vrp.deleteTheRule();	
	}
	
	private void deleteCriteria_ios(String sRenamePayeeTo, String sCriteria) throws Exception{
		
		String cc = "**/XCUIElementTypeOther[`name contains '"+sCriteria+"'`][-1]";
		
		this.expand_rule(sRenamePayeeTo);
		Engine.getDriver().findElement(AppiumBy.iOSClassChain(cc)).click();
		
		ViewRenamingRulesPage vrp = new ViewRenamingRulesPage();
		vrp.deleteTheRule();	
		
			
	}
	
	
	

}
