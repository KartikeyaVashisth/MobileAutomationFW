package dugout;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.HidesKeyboard;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import referee.Verify;
import support.Engine;
import support.Helper;

public class TagManagementPage {

	public TagManagementPage () {
		try {
			PageFactory.initElements(new AppiumFieldDecorator(Engine.getDriver()),this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Tags'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Tags']")
	public WebElement tagsOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Tags'`]")
	@AndroidFindBy(xpath= "//android.widget.TextView[@text='Tags']//*[@class='android.widget.ImageView']")
	public WebElement tagsImage;

	@iOSXCUITFindBy(iOSNsPredicate = "name = 'In progress'")
	@AndroidFindBy(xpath="//android.widget.ProgressBar")
	public WebElement refreshSpinnerIcon;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='navigationMenu'`]")
	//@AndroidFindBy(xpath="//*[@content-desc='navigationMenu']//*[@class='android.widget.ImageView']")
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='navigationMenu']")
	public WebElement hambergerIcon;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Settings Menu'`]")
	//@AndroidFindBy(xpath="//android.widget.TextView[@content-desc=\"Settings Menu\"]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Settings']")
	public WebElement settingsOption;

	@iOSXCUITFindBy(iOSNsPredicate = "name = 'Tags'")
	@AndroidFindBy(xpath="//android.view.View[@text='Tags']")
	public WebElement tagsHeader;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Tag Deleted'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Tag Deleted']")
	public WebElement tagDeleted;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Tag Created'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Tag Created']")
	public WebElement tagCreated;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Tag Updated'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Tag Updated']")
	public WebElement tagUpdated;

	//@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeTextField'")
	@iOSXCUITFindBy(accessibility = "Create new tag Container")
	@AndroidFindBy(xpath="//android.widget.EditText")
	public WebElement tagTextField;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Create New Tag'`][-1]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Create New Tag']")
	public WebElement createNewTagBtn;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Create'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Create']")
	public WebElement createBtn;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Update'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Update']")
	public WebElement updateBtn;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name='Cancel'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Cancel']")
	public WebElement cancelBtn;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Edit'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Edit']")
	public WebElement editBtn;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name ='Delete'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Delete']")
	public WebElement deleteBtn;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Discard'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Discard']")
	public WebElement discardBtn;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Continue'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Continue']")
	public WebElement continueBtn;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Tag Name Container'`]")
	@AndroidFindBy(xpath="//android.widget.EditText")
	public WebElement editTagTextField;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name CONTAINS 'already exists.'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'already exists.')]")
	public WebElement alreadyExists;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name ='Delete'`]")
	@AndroidFindBy(xpath="//android.widget.Button[@text='DELETE']")
	public WebElement deleteConfirmationBtn;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='This tag is applied to 1 or more transactions.'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='This tag is applied to 1 or more transactions.')]")
	public WebElement thisTagIsAppliedTo;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Do you want to delete this tag?'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Do you want to delete this tag?']")
	public WebElement areYouSureYouWant;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeAlert[`name='Do you want to delete these tags?'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Do you want to delete these tags?']")
	public WebElement multipletagdeletealert;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Cancel Delete'`]/XCUIElementTypeOther[`name='Delete'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='DELETE']")
	public WebElement deleteMultipleTags;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Advanced'`]")
	@AndroidFindBy(xpath= "//android.widget.TextView[@text='Advanced']")
	public WebElement advancedoption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`label = 'Description Container'`]")
	@AndroidFindBy(xpath= "//android.widget.EditText[contains(@content-desc,'Description: ')]")
	public WebElement tagDescription;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Copy number Container'`]")
	@AndroidFindBy(xpath= "//android.widget.EditText[contains(@content-desc,'Copy number: ')]")
	public WebElement copyNumber;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label = 'Description'`]")
	@AndroidFindBy(xpath= "//android.widget.TextView[@text ='Description']")
	public WebElement tagDescriptionText;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeButton[`name contains 'back'`]")
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='backArrow']")
	public WebElement backButton;


	public void navigateToSettingMemu() throws Exception{

		hambergerIcon.click();
		Thread.sleep(2000);
		//this.scrollDownToSettings();

		settingsOption.click();
		Thread.sleep(1000);
	}

	public void navigateToTags() throws Exception{

		hambergerIcon.click();
		Thread.sleep(2000);
		//this.scrollDownToSettings();

		settingsOption.click();
		Thread.sleep(1000);
		tagsOption.click();
		Thread.sleep(1000);
	}

	public void scrollDownToSettings () throws Exception{

		Helper h = new Helper();

		if (h.getEngine().equals("android")) {
			Engine.getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Settings\").instance(0))"));
			Thread.sleep(1000);
		}

		else {
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("direction", "down");
			scrollObject.put("toVisible", "not an empty string");
			Engine.getDriver().executeScript("mobile:scroll", scrollObject);
			Thread.sleep(1000);

		}
	}

	public WebElement getTag(String tag) throws Exception{

		Helper h = new Helper();
		if (h.getEngine().equals("ios"))
			return getTag_ios(tag);
		else
			return getTag_android(tag);
	}

	private WebElement getTag_ios(String tag) throws Exception{

		String cc = "**/XCUIElementTypeStaticText[`name='Tag name: "+tag+"'`]";

		try {
			return Engine.getDriver().findElement(AppiumBy.iOSClassChain(cc));

		}
		catch (Exception e) {
			return null;
		}
	}
	/*
	 * Clean-up method
	 * This method deletes automation created tags which starts with prefix auto_
	 */
	public void deleteAutomationTags() throws Exception{

		while (true) {

			WebElement me = getAutomationTags();
			if (me == null)
				break;

			me.click();

			this.deleteBtn.click();

			Thread.sleep(1000);
			if(Verify.objExists_check(areYouSureYouWant)) 
				this.deleteConfirmationBtn.click();

			Thread.sleep(2000);
		}
	}

	public void deleteMultipleAutomationTags_old() throws Exception{

		while (true) {

			WebElement me = getAutomationTags();
			if (me == null)
				break;

			me.click();
		}

		this.deleteBtn.click();

		Thread.sleep(1000);
		if(Verify.objExists_check(areYouSureYouWant)) 
			this.deleteConfirmationBtn.click();

		Thread.sleep(2000);
	}

	public WebElement getAutomationTags() throws Exception{

		Helper h = new Helper();

		if (h.getEngine().equals("ios"))
			return getAutomationTag_ios();
		else
			return getAutoTag_android();

	}

	private WebElement getAutomationTag_ios() throws Exception{


		String cc = "**/XCUIElementTypeOther[`name contains 'auto_'`][-1]";

		try {
			return Engine.getDriver().findElement(AppiumBy.iOSClassChain(cc));

		}
		catch (Exception e) {
			return null;
		}
	}

	private WebElement getAutoTag_android() throws Exception{


		String cc = "//android.widget.TextView[contains(@text,'auto_')]";

		try {
			return Engine.getDriver().findElement(By.xpath(cc));

		}
		catch (Exception e) {
			return null;
		}
	}

	public void deleteAllTags() throws Exception{

		List<WebElement> allTags = getAllTags();

		if (allTags.size() > 0) {

			for (WebElement tag:allTags) 
				tag.click();
		}

		this.deleteBtn.click();

		Thread.sleep(1000);
		if(Verify.objExists_check(areYouSureYouWant)) 
			this.deleteConfirmationBtn.click();

		Thread.sleep(2000);
	}


	public List<WebElement> getAllTags() throws Exception{

		Helper h = new Helper();

		if (h.getEngine().equals("ios"))
			return getAllTags_ios();
		else
			return getAllTags_android();

	}
	private List<WebElement> getAllTags_ios() throws Exception{


		String cc = "**/XCUIElementTypeStaticText[`name contains 'Tag name: '`]";

		try {
			return Engine.getDriver().findElements(AppiumBy.iOSClassChain(cc));

		}
		catch (Exception e) {
			return null;
		}
	}

	private List<WebElement> getAllTags_android() throws Exception{


		String cc = "//android.widget.TextView[contains(@text,'Tag name: ')]";

		try {
			return Engine.getDriver().findElements(By.xpath(cc));

		}
		catch (Exception e) {
			return null;
		}
	}

	private WebElement getTag_android(String tag) throws Exception{


		String cc = "//android.widget.TextView[@text='"+tag+"']";

		try {
			return Engine.getDriver().findElement(By.xpath(cc));

		}
		catch (Exception e) {
			return null;
		}
	}

	public void deleteTag (String tag) throws Exception{

		this.getTag(tag).click();

		this.deleteBtn.click();

		if(Verify.objExists_check(areYouSureYouWant)) 
			this.deleteConfirmationBtn.click();

		Verify.waitForObjectToDisappear(this.tagDeleted, 4);
	}

	public void deleteUsedTag (String tag) throws Exception{

		this.getTag(tag).click();

		this.deleteBtn.click();

		Thread.sleep(1000);

		if(Verify.objExists_check(areYouSureYouWant)) 
			this.deleteConfirmationBtn.click();

		//if (Verify.objExists(this.tagDeleted))
		//Commentary.log(LogStatus.PASS, "Tag Deleted Successfully");

		Verify.waitForObjectToDisappear(this.refreshSpinnerIcon, 2);

	}

	public void createTag (String tag) throws Exception{

		Verify.waitForObjectToDisappear(refreshSpinnerIcon, 4);
		this.createNewTagBtn.click();
		this.tagTextField.sendKeys(tag);
		((HidesKeyboard) Engine.getDriver()).hideKeyboard();
		Thread.sleep(500);
		this.createBtn.click();
		Verify.waitForObjectToDisappear(tagCreated, 4);
		//Thread.sleep(4000);

	}

	public void createTagwithAdvanced (String tag, String tagDescri, String number) throws Exception{

		this.createNewTagBtn.click();
		this.tagTextField.sendKeys(tag);
		((HidesKeyboard) Engine.getDriver()).hideKeyboard();
		Thread.sleep(2000);
		this.advancedoption.click();;
		Thread.sleep(1000);
		this.tagDescription.sendKeys(tagDescri);
		((HidesKeyboard) Engine.getDriver()).hideKeyboard();
		this.copyNumber.sendKeys(number);
		this.tagDescriptionText.click();
		Thread.sleep(500);
		this.createBtn.click();
		Thread.sleep(4000);

	}

	public boolean verifyTag(String tag) throws Exception{

		try {
			this.getTag(tag).isDisplayed();
			return true;
		}
		catch (Exception e) {
			return false;
		}

	}

	public void selectTag(String tag) throws Exception{

		this.getTag(tag).click();
	}

	public void editTag(String tag, String renameTo) throws Exception{

		Helper h = new Helper();
		selectTag(tag);
		this.editBtn.click();
		Thread.sleep(1000);
		if (h.getEngine().equals("ios")) {
			this.editTagTextField.click();
			WebElement me = Engine.getDriver().findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeKey[`label = 'delete'`]"));

			for (int i = 1; i<14; i++)
				me.click();

			Thread.sleep(1000);
			this.editTagTextField.sendKeys(renameTo);
			Thread.sleep(2000);

		}

		else {

			this.editTagTextField.click();
			Thread.sleep(2000);
			this.editTagTextField.clear();
			this.editTagTextField.sendKeys(renameTo);
			Thread.sleep(1000);

		}

		this.updateBtn.click();


		Thread.sleep(3000);

	}

	public void editAdvancedTag(String tag, String renameDescri, String renameCopyNo) throws Exception{

		Helper h = new Helper();
		selectTag(tag);
		this.editBtn.click();
		Thread.sleep(2000);
		this.advancedoption.click();
		Thread.sleep(1000);
		if (h.getEngine().equals("ios")) {
			Thread.sleep(1000);
			this.tagDescription.click();
			WebElement me = Engine.getDriver().findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeKey[`label = 'delete'`]"));

			for (int i = 1; i<14; i++)
				me.click();

			Thread.sleep(1000);
			this.tagDescription.sendKeys(renameDescri);
			Thread.sleep(1000);

			this.copyNumber.click();
			Thread.sleep(1000);
			WebElement deletekey = Engine.getDriver().findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeKey[`label = 'Delete'`]"));
			//for (int i = 1; i<2; i++)
			deletekey.click();
			Thread.sleep(1000);
			this.copyNumber.sendKeys(renameCopyNo);
			Thread.sleep(1000);

		}

		else {
			Thread.sleep(1000);
			this.tagDescription.click();
			Thread.sleep(1000);
			this.tagDescription.clear();
			this.tagDescription.sendKeys(renameDescri);
			Thread.sleep(1000);
			this.tagDescriptionText.click();
			this.copyNumber.click();
			Thread.sleep(1000);
			this.copyNumber.clear();
			this.copyNumber.sendKeys(renameCopyNo);
		}

		this.updateBtn.click();
		Thread.sleep(3000);

	}

	public void deleteTagConfirmationMessage (String tag) throws Exception{

		this.getTag(tag).click();

		this.deleteBtn.click();
	}

	public void backtoDashboardScreen () throws Exception{

		this.backButton.click();
		Thread.sleep(1000);

		this.backButton.click();
	}

	public void deleteMultipleAutomationTags() throws Exception{

		List<WebElement> listOfEle = getAllAutomationTags();

		for (WebElement me:listOfEle) {
			me.click();
			Thread.sleep(1000);
		}

		this.deleteBtn.click();

		Thread.sleep(1000);
		if(Verify.objExists_check(multipletagdeletealert)) 
			this.deleteConfirmationBtn.click();

		Thread.sleep(2000);

	}

	public List<WebElement> getAllAutomationTags() throws Exception{

		Helper h = new Helper();

		if (h.getEngine().equals("ios"))
			return getAllAutomationTag_ios();
		else
			return getAllAutoTag_android();

	}

	private List<WebElement> getAllAutomationTag_ios() throws Exception{

		String cc = "**/XCUIElementTypeStaticText[`name contains 'auto_'`]";
		try {
			return Engine.getDriver().findElements(AppiumBy.iOSClassChain(cc));

		}
		catch (Exception e) {
			return null;
		}
	}

	private List<WebElement> getAllAutoTag_android() throws Exception{

		String cc = "//android.widget.TextView[contains(@content-desc,'Tag name: auto_')]";

		try {
			return Engine.getDriver().findElements(By.xpath(cc));

		}
		catch (Exception e) {
			return null;
		}
	}

}

