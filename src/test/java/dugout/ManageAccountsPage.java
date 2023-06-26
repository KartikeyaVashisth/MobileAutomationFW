package dugout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import referee.Verify;
import support.Engine;
import support.Helper;

public class ManageAccountsPage {

	public ManageAccountsPage() {

		try {
			PageFactory.initElements(new AppiumFieldDecorator(Engine.getDriver()), this);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Manage Accounts'`]")
	@AndroidFindBy(xpath = "//android.view.View[@text='Manage Accounts']")
	private WebElement manageAccountsText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'FI three dot menu'`][-1]")
	@AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,'FI three dot menu')]")
	private WebElement FIThreeDotsMenu;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Delete financial institution'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Delete financial institution']")
	private WebElement deleteFIOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Cancel'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Cancel']")
	private WebElement cancelOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Rename account'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Rename account']")
	private WebElement renameAccountOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Keep this account separate'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Keep this account separate']")
	private WebElement keepAccountSeparateOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Hide in account list'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Hide in account list']")
	private WebElement hideInAccountListOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Delete account'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Delete account']")
	private WebElement deleteAccountOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name BEGINSWITH 'Account Name'`]/**/XCUIElementTypeTextField")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Account Name']/following-sibling::android.widget.EditText")
	private WebElement renameAccountNameTextField;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Update'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Update']")
	private WebElement updateOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Other'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Other']")
	public WebElement otherFIText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'closeRename account'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='closeRename account']")
	private WebElement closeRenameAccountButton;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Keep account separate'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Keep account separate']")
	private WebElement keepAccountSeparatePopUpText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Keep Separate'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Keep Separate']")
	private WebElement keepSeparateButton;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Hide account'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Hide account']")
	private WebElement hideAccountPopUpText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Hide'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Hide']")
	private WebElement hideButton;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Delete account'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Delete account']")
	private WebElement deleteAccountPopUpText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Delete financial institution'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Delete financial institution']")
	private WebElement deleteFinancialInstitutionPopUpText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Delete'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Delete']")
	private WebElement deleteButton;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name = 'Settings, back'`]")
	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Go back']")
	private WebElement backButtonOnManageAccountsPage;


	public void chooseOption(String optionName) throws Exception {

		Helper h = new Helper();
		if (h.getEngine().equals("android")){
			String xpath = "//android.widget.TextView[@text='"+optionName+"']";
			Engine.getDriver().findElement(By.xpath(xpath)).click();
			Thread.sleep(1000);
		}
		else {
			String xpath = "**/XCUIElementTypeOther[`name='"+optionName+"'`]";
			Engine.getDriver().findElement(AppiumBy.iOSClassChain(xpath)).click();
			Thread.sleep(1000);
		}
	}

	public void renameAccount(String newAccountName) throws Exception {

		this.renameAccountNameTextField.click();
		this.renameAccountNameTextField.clear();
		this.renameAccountNameTextField.sendKeys(newAccountName);
		this.updateOption.click();
		Thread.sleep(5000);

	}

	public String getPreviousAccountNameText() {
		return renameAccountNameTextField.getText();
	}

	public void keepAccountSeparate() throws Exception {

		Verify.waitForObject(keepAccountSeparatePopUpText, 1);
		this.keepSeparateButton.click();
		Thread.sleep(1000);	
	}

	public void hideAccount() throws Exception {

		Verify.waitForObject(hideAccountPopUpText, 1);
		this.hideButton.click();
		Thread.sleep(1000);	
	}

	public void deleteAccount() throws Exception {

		Verify.waitForObject(deleteAccountPopUpText, 1);
		this.deleteButton.click();
		Thread.sleep(1000);	
	}

	public void deleteFIInstitution() throws Exception {

		Verify.waitForObject(deleteFinancialInstitutionPopUpText, 1);
		this.deleteButton.click();
		Thread.sleep(1000);	
	}

	public void clickingThreeDotsOfParticularAccount(String accountName) throws Exception {

		Helper h = new Helper();
		if (h.getEngine().equals("android")){
			String xpath = "//android.widget.TextView[@text='Account three dot menu: "+accountName+"']";
			Engine.getDriver().findElement(By.xpath(xpath)).click();
		}
		else {
			String xpath = "**/XCUIElementTypeOther[`name = 'Account three dot menu: "+accountName+"'`]";
			Engine.getDriver().findElement(AppiumBy.iOSClassChain(xpath)).click();
		}
	}
	
	public void clickingThreeDotsOfParticularFI(String FIName) throws Exception {

		Helper h = new Helper();
		if (h.getEngine().equals("android")){
			String xpath = "//android.widget.TextView[@text='FI three dot menu: "+FIName+"']";
			Engine.getDriver().findElement(By.xpath(xpath)).click();
		}
		else {
			String xpath = "**/XCUIElementTypeOther[`name = 'FI three dot menu: "+FIName+"'`]";
			Engine.getDriver().findElement(AppiumBy.iOSClassChain(xpath)).click();
		}
	}
	
	public String accountNameGetText() {
		
		String accountName = renameAccountNameTextField.getText();
		return accountName;
	}
	
	public void goBack() throws Exception {
		Verify.waitForObject(backButtonOnManageAccountsPage, 1);
		backButtonOnManageAccountsPage.click();
	}
	
	public boolean isAccountSeparated(String accountName) throws Exception {
		
		Helper h = new Helper();
		if (h.getEngine().equals("android")){
			String xpath = "(//android.widget.TextView[@text='Separate'])[1]/..//android.widget.TextView[@text='"+accountName+"']";
			return Engine.getDriver().findElement(By.xpath(xpath)).isDisplayed();
		}
		else {
			String xpath = "**/XCUIElementTypeOther[`name contains 'Separate'`]/**/XCUIElementTypeOther[`name='"+accountName+"'`]";
			return Engine.getDriver().findElement(AppiumBy.iOSClassChain(xpath)).isDisplayed();
		}
	}
	
	public boolean isAccountHidden(String accountName) throws Exception {
		
		Helper h = new Helper();
		if (h.getEngine().equals("android")){
			String xpath = "//android.widget.TextView[@text='"+accountName+"']/../android.widget.ImageView";
			return Engine.getDriver().findElement(By.xpath(xpath)).isDisplayed();
		}
		else {
			String xpath = "**/XCUIElementTypeOther[`name='"+accountName+"'`]/**/XCUIElementTypeImage";
			return Engine.getDriver().findElement(AppiumBy.iOSClassChain(xpath)).isDisplayed();
		}
	}
}
