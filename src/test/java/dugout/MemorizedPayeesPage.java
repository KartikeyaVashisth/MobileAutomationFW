package dugout;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import referee.Commentary;
import referee.Verify;
import support.Engine;
import support.Helper;

public class MemorizedPayeesPage {

	public MemorizedPayeesPage() {
		try {
			PageFactory.initElements(new AppiumFieldDecorator(Engine.getDriver()), this);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Memorized Payees'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Memorized Payees']")
	public MobileElement memorizedPayeesHeader;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name = 'Settings, back'`]")
	@AndroidFindBy(xpath="//android.widget.Button[@content-desc='Settings, back']")
	public MobileElement backButtonOnMemorizedPayeesPage;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='No memorized payee rules added yet'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='No memorized payee rules added yet']")
	public MobileElement noMemorizedPayeeRulesText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`label ='addMemorizedRule'`][-1]")
	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='addMemorizedRule']")
	public MobileElement addMemorizedRuleButton;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name = 'Memorized Payees, back'`]")
	@AndroidFindBy(xpath="//android.widget.Button[@content-desc='Memorized Payees, back']")
	public MobileElement backButtonOnCreateMemorizedPayeePage;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Create Memorized Payee'`]")
	@AndroidFindBy(xpath="//android.view.View[normalize-space(@text)='Create Memorized Payee']")
	public MobileElement createMemorizedPayeeHeader;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Mark as Cleared'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Mark as Cleared']")
	public MobileElement markAsClearedText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Mark as Cleared'`]/XCUIElementTypeSwitch")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Mark as Cleared']/following::android.widget.Switch")
	public MobileElement switchMarkAsCleared;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Lock Payee'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Lock Payee']")
	public MobileElement lockPayeeText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Lock Payee'`]/XCUIElementTypeStaticText[`name = 'Lock the payee to leave this rule unchanged when the payee is edited in transaction'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Lock Payee']/following::android.widget.TextView[1]")
	public MobileElement lockPayeeDescription;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Lock Payee'`]/XCUIElementTypeSwitch")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Lock Payee']/following::android.widget.Switch")
	public MobileElement switchLockPayee;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Add Memorized Payee'`]")
	@AndroidFindBy(xpath="//android.view.View[normalize-space(@text)='Add Memorized Payee']")
	public MobileElement addMemorizedPayeeButton;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[1]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@content-desc,'Memorized Payee:')]")
	public MobileElement firstMemorizedPayeeName;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@content-desc,'Memorized Payee:')]/following-sibling::android.widget.TextView")
	public MobileElement firstMemorizedPayeeCategoryName;

	// -------------- View Memorized Payee Page -------------------------

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'View Memorized Payee'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='View Series']")
	public MobileElement viewMemorizedPayeeHeaderText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name = 'Memorized Payees, back'`]")
	@AndroidFindBy(xpath="//android.widget.Button[@content-desc='Memorized Payees, back']")
	public MobileElement backButtonOnViewMemorizedPayeePage;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='save'`][-1]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Save']")
	public MobileElement saveButtonOnViewMemorizedPayeePage;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Payee Name'`][-1]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Payee Name']/following-sibling::android.widget.TextView")
	public MobileElement payeeNameOnViewMemorizedPayeePage;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Type'`][-1]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Type']/following-sibling::android.widget.TextView")
	public MobileElement typeOnViewMemorizedPayeePage;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Category'`][-1]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Category']/following-sibling::android.widget.TextView")
	public MobileElement categoryOnViewMemorizedPayeePage;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Tags'`][-1]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Tags']/following-sibling::android.widget.TextView")
	public MobileElement tagsOnViewMemorizedPayeePage;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Memo'`]/**/XCUIElementTypeTextView")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Memo']/following-sibling::android.widget.EditText")
	public MobileElement memoOnViewMemorizedPayeePage;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Amount'`][-1]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Amount']/following-sibling::android.widget.TextView")
	public MobileElement amountOnViewMemorizedPayeePage;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Delete Memorized Payee'`][-1]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Delete Memorized Payee']")
	public MobileElement deleteMemorizedPayeeButton;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name = 'Delete'`]")
	@AndroidFindBy(xpath="//android.widget.Button[@text='DELETE']")
	public MobileElement delete;

	public void deleteMemorizedPayee() throws Exception {

		Verify.waitForObject(this.deleteMemorizedPayeeButton, 1);
		this.deleteMemorizedPayeeButton.click();
		Thread.sleep(2000);

		if (Verify.objExists(this.delete))
			this.delete.click();
		Thread.sleep(4000);
	}

	public void deleteAlreadyPresentMemorizedPayees() throws Exception {

		OverviewPage op = new OverviewPage();

		List<MobileElement> li = this.getAllSearchMemorizedPayees();
		Commentary.log(LogStatus.INFO, "No of Memorized Payees appeared in the search..."+li.size());
		int listSize = li.size();

		if (li.isEmpty())
			Commentary.log(LogStatus.INFO, "No Memorized Payees present.");
		else {
			for(int i=1; i<=listSize; i++) {
				Verify.waitForObject(this.firstMemorizedPayeeName, 1);
				this.firstMemorizedPayeeName.click();
				Thread.sleep(1000);
				this.deleteMemorizedPayeeButton.click();
				Verify.waitForObject(this.delete, 1);
				this.delete.click();
				Thread.sleep(2000);
				Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 1);
			}
		}
	}

	public List<MobileElement> getAllSearchMemorizedPayees() throws Exception {

		Helper h = new Helper();
		if (h.getEngine().equals("android"))
			return getAllSearchMemorizedPayees_android();
		else
			return getAllSearchMemorizedPayees_ios();		
	}

	private List<MobileElement> getAllSearchMemorizedPayees_android() throws Exception {

		String sXpath = "//android.widget.ImageView[@content-desc='Memorized Rule Arrow Icon']";
		List <MobileElement> me = null;

		me = Engine.getDriver().findElements(By.xpath(sXpath));

		return me;
	}

	private List<MobileElement> getAllSearchMemorizedPayees_ios() throws Exception {

		String sXpath = "**/XCUIElementTypeOther[`name='Memorized Rule Arrow Icon'`]";
		List <MobileElement> me = null;
		me = Engine.getDriver().findElements(MobileBy.iOSClassChain(sXpath));

		return me;
	}
	
	public boolean verifySplitAmountAndCategory(String amount, String categoryName) throws Exception {
		
		Helper h = new Helper();
		if(h.getEngine().equals("android"))
			return android_verifySplitAmountAndCategory(amount,categoryName);
		else
			return iOS_verifySplitAmountAndCategory(amount,categoryName);
	}
	
	private boolean iOS_verifySplitAmountAndCategory(String amount, String categoryName) throws Exception {
		
		try {
			return Engine.getDriver().findElement(MobileBy.iOSClassChain("**/XCUIElementTypeOther[`name contains '"+amount+"'`][-4]/**/XCUIElementTypeOther[`name = 'Category: "+categoryName+"'`]")).isDisplayed();	
		}
		catch(NoSuchElementException e) {
			return false;
		}
	}
	
	private boolean android_verifySplitAmountAndCategory(String amount, String categoryName) throws Exception  {

		try {
			return Engine.getDriver().findElement(By.xpath("//android.view.ViewGroup[contains(@content-desc,'"+amount+"')]/../..//android.view.ViewGroup[@content-desc='Category: "+categoryName+"']")).isDisplayed();	
		}
		catch(NoSuchElementException e) {
			return false;
		}
	}
}
