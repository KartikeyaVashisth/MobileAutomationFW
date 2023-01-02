package dugout;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import referee.Commentary;
import referee.Verify;
import support.Engine;
import support.Helper;
import support.TransactionRecord;

public class CreateMemorizedPayeeDetailPage {

	public CreateMemorizedPayeeDetailPage () {
		try {

			PageFactory.initElements(new AppiumFieldDecorator(Engine.getDriver()),this);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Create Memorized Payee'`]")
	@AndroidFindBy(xpath="//android.view.View[normalize-space(@text)='Create Memorized Payee']")
	public MobileElement createMemorizedPayeeHeader;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Add Memorized Payee'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Add Memorized Payee']")
	public MobileElement addMemorizedPayeeButton;
	
	@iOSXCUITFindBy(id="Payee Name")
	@AndroidFindBy(xpath="//*[@text='Payee Name']")
	public MobileElement payeeName;
	
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeTextField[`name = 'search payee'`]")
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Search Payee']")
	public MobileElement searchPayee;
	
	@iOSXCUITFindBy(id="create payee")
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc=\"create payee\"]")
	public MobileElement createPayee;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='All Payees'`][1]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='All Payees']")
	public MobileElement allPayeesLabel;
	
	@iOSXCUITFindBy(id="closePayee")
	@AndroidFindBy(xpath="//*[@content-desc='closePayee']/android.widget.ImageView")
	public MobileElement closePayee;
	
	@iOSXCUITFindBy(id="Type")
	@AndroidFindBy(xpath="//*[@text='Type']")
	public MobileElement type;

	@iOSXCUITFindBy(id="Deposit")
	@AndroidFindBy(xpath="//*[@text='Deposit']")
	public MobileElement deposit;
	
	@iOSXCUITFindBy(id="Payment")
	@AndroidFindBy(xpath="//*[@text='Payment']")
	public MobileElement payment;
	
	@iOSXCUITFindBy(id="Apply")
	@AndroidFindBy(xpath="//*[@text='Apply']")
	public MobileElement applyButton;
	
	@iOSXCUITFindBy(id="closeTransaction Type")
	@AndroidFindBy(xpath="//*[@content-desc='closeTransaction Type']/android.widget.ImageView")
	public MobileElement closeTransactionTypeDrawer;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Category'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Category']")
	public MobileElement category;
	
	@iOSXCUITFindBy(id="closeCategory")
	@AndroidFindBy(xpath="//*[@content-desc='closeCategory']/android.widget.ImageView")
	public MobileElement closeCategory;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`name='search category'`]")
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Search Category']")
	public MobileElement searchCategoryTextField;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`name='search tags'`]")
	@AndroidFindBy(xpath="//android.widget.EditText[@content-desc='search tags']")
	public MobileElement searchTags;
	
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name = 'Apply'`][-1]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Apply']")
	public MobileElement tagsApplyButton;
	
	@iOSXCUITFindBy(id="create item")
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc='create item']")
	public MobileElement createTag;
	
	@iOSFindBy(xpath="//XCUIElementTypeTextView[@name='Memo']")
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Memo']")
	public MobileElement memo;
	
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeButton[`name='Done'`]")
	@AndroidFindBy(xpath="//*[@text='Done']")
	public MobileElement doneButton;
	
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name BEGINSWITH 'Amount'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Amount']/following-sibling::android.widget.TextView")
	public MobileElement amount;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Done'`][2]")
	@AndroidFindBy(xpath="//*[@text='Done']")
	public MobileElement buttonDone;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name beginswith 'Split'`]/XCUIElementTypeSwitch")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Split']/following::android.widget.Switch")
	public MobileElement switchSplit;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Mark as Cleared'`]/XCUIElementTypeSwitch")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Mark as Cleared']/following::android.widget.Switch")
	public MobileElement switchMarkAsCleared;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Lock Payee'`]/XCUIElementTypeSwitch")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Lock Payee']/following::android.widget.Switch")
	public MobileElement switchLockPayee;
	
	// -------------- Add Transaction Page -------------------------
	
	@iOSXCUITFindBy(iOSClassChain="**/*[`name=='Allow While Using App'`]")
	@AndroidFindBy(xpath="//android.widget.Button[contains(@text,'using the app')]")
	public MobileElement allowButton;
	
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name=='Add Transaction'`]")
	@AndroidFindBy(xpath="//*[@text='Add Transaction']")
	public MobileElement addTransactionTxt;
	
	@iOSXCUITFindBy(id="Payee")
	@AndroidFindBy(xpath="//*[@text='Payee']")
	public MobileElement payeeOnAddTransactionPage;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Amount: '`][-1]")
	@AndroidFindBy(xpath="(//android.widget.TextView[contains(@text,'$')])[1]")
	public MobileElement enteredAmount;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Account'`][-1]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Account']/../android.widget.TextView[@index=2]")
	public MobileElement enteredAccount;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Categories'`][-1]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Categories']/../android.widget.TextView[@index=2]")
	public MobileElement enteredCategory;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Tags'`][-1]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Tags']/../android.widget.TextView[@index=2]")
	public MobileElement enteredTags;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextView")
	@AndroidFindBy(xpath="//android.widget.EditText")
	public MobileElement enteredNotes;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeSwitch[`name='Switch Value: true'`]")
	@AndroidFindBy(xpath="//android.widget.Switch[@content-desc='Switch Value: true']")
	public MobileElement enabledSplitSwitch;
	
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name='pop'`][-1]")
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='pop']")
	public MobileElement backButtonOnAddTransactionPage;
	
	@iOSXCUITFindBy(id="button Save")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Save']")
	public MobileElement buttonSave;
	
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name BEGINSWITH 'Status'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//*[@text='Status']/../android.widget.TextView[@index=2]")
	public MobileElement transactionStatus;
	
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name='save'`][-1]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Save']")
	public MobileElement buttonSave1;
	
	public void createMemorizedPayee (TransactionRecord tr) throws Exception {

		SoftAssert sa = new SoftAssert();
		Verify.waitForObject(this.addMemorizedPayeeButton, 1);
		if (Verify.objExists(this.addMemorizedPayeeButton) || Verify.objExists(this.createMemorizedPayeeHeader)) {
			Commentary.log(LogStatus.INFO,"Create Memorized Payee Page got displayed.");
			Thread.sleep(2000);

			if (tr.getPayee() != null)
				this.selectPayee(tr.getPayee());

			if (tr.getTransactionType() != null)
				this.enterTransactionType(tr.getTransactionType());

			if (tr.getCategory() != null)
				this.selectCategory(tr.getCategory());

			if (tr.getTags() != null)
				this.selectTags(tr.getTags());

			if (tr.getNote() != null)
				this.enterNotes(tr.getNote());
			
			if (tr.getAmount() != null) 
				this.enterAmount(tr.getAmount());
			
			if (tr.getMarkAsClearedValue() != null)
				this.enableMarkAsClearedSwitch(tr.getMarkAsClearedValue());
			
			if (tr.getLockPayeeValue() != null)
				this.enableLockPayeeSwitch(tr.getLockPayeeValue());

			if (Verify.objExists(this.addMemorizedPayeeButton)) {
				this.addMemorizedPayeeButton.click();
			}
			else {
				this.addMemorizedPayeeButton.click(); 
			} 

			Thread.sleep(4000);	
		}

		else
			Commentary.log(sa, LogStatus.FAIL,"Create Memorized Payee page did not appear.");

		sa.assertAll();
	}
	
	public void selectPayee(String payees) throws Exception{

		Helper h = new Helper();

		if (h.getEngine().equals("android"))
			this.selectPayee_android(payees);
		else
			this.selectPayee_ios(payees);
	}
	
	private void selectPayee_android (String payees) throws Exception {

		String createPayee_xpath="//android.widget.TextView[@content-desc='create payee']";
		String allPayees_Xpath = "//android.widget.ScrollView/android.view.ViewGroup//android.widget.TextView[@text=\"All Payees\"]/../../../android.view.ViewGroup//android.widget.TextView[@text='"+payees+"']";

		this.payeeName.click();
		Thread.sleep(1000);

		if (! Verify.objExists(this.closePayee))
			Commentary.log(LogStatus.FAIL,"Error****** Transaction Detail > tapping on payee, did not opened Payee selection drawer.");

		this.searchPayee.sendKeys(payees);
		Helper h = new Helper();
		h.hideKeyBoard();
		Thread.sleep(3000);

		if (Verify.objExists(createPayee)) {
			try {
				Verify.objExists((MobileElement)Engine.getDriver().findElement(By.xpath(createPayee_xpath)));
				Engine.getDriver().findElement(By.xpath(createPayee_xpath)).click();
				Commentary.log(LogStatus.INFO,"Creating Payee.. "+payees);
			}
			catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}

		else {
			Engine.getDriver().findElement(By.xpath(allPayees_Xpath)).click();
			Thread.sleep(1500);
			Commentary.log(LogStatus.INFO,"Selected Payee from All Payees List.. "+payees);
		}

		Thread.sleep(1000);	
	}

	private void selectPayee_ios (String payees) throws Exception {

		String createPayee_xpath="**/XCUIElementTypeOther[`name='create payee'`]";

		this.payeeName.click();
		Thread.sleep(1000);

		if (! Verify.objExists(this.closePayee))
			Commentary.log(LogStatus.FAIL,"Error: On Create Memorized Payee Page > Tapped on Payee but it didn't opened Payee selection drawer.");

		this.searchPayee.sendKeys(payees);
		Helper h = new Helper();
		h.hideKeyBoard();
		Thread.sleep(1000);

		if (Verify.objExists(createPayee)) {
			try {
				Verify.objExists((MobileElement)Engine.getDriver().findElement(MobileBy.iOSClassChain(createPayee_xpath)));
				Engine.getDriver().findElement(MobileBy.iOSClassChain(createPayee_xpath)).click();
				Commentary.log(LogStatus.INFO,"Creating Payee .. "+payees);

			}
			catch (NoSuchElementException e){
				e.printStackTrace();
			}
		}

		else {
			String cc = "**/XCUIElementTypeStaticText[`name='"+payees+"'`]";
			Engine.getDriver().findElement(MobileBy.iOSClassChain(cc)).click();
			Thread.sleep(1500);
			Commentary.log(LogStatus.INFO,"Selected Payee from All Payees List.. "+payees);
		}

		Thread.sleep(1000);	
	}
	
	public void enterTransactionType (String txnType) throws Exception {

		if (txnType.equals("Payment")) {
			Verify.waitForObject(this.type, 1);
			this.type.click();
			this.payment.click();
			this.applyButton.click();
			Thread.sleep(1000);
		}
		else {
			Verify.waitForObject(type, 1);
			this.type.click();
			this.deposit.click();
			this.applyButton.click();
			Thread.sleep(1000);
		}
	}
	
	public void selectCategory(String category) throws Exception {

		Helper h = new Helper();

		if (h.getEngine().equals("android"))
			this.selectCategory_android(category);
		else
			this.selectCategory_ios(category);	
	}
	
	private void selectCategory_android (String category) throws Exception {

		String sXpath = "//android.widget.TextView[@text='"+category+"']/../android.view.ViewGroup[@content-desc='RadioButton']";

		if (Verify.objExists_check(this.category))
			this.category.click();

		Verify.waitForObject(this.closeCategory, 1);
		if (! Verify.objExists(this.closeCategory))
			Commentary.log(LogStatus.FAIL,"Error****** Transaction Detail > Tapping on Category, did not open Category selection screen");

		this.searchCategory(category);

		Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ category + "\").instance(0))"));

		Thread.sleep(1000);
		Engine.getDriver().findElement(By.xpath(sXpath)).click();
		Thread.sleep(1000);	
	}

	private void selectCategory_ios (String category) throws Exception {

		String sXpath = "**/XCUIElementTypeOther[`name='RadioButton "+category+"'`]/XCUIElementTypeOther[`name='RadioButton'`]";

		if (Verify.objExists_check(this.category)) 
			this.category.click();

		Verify.waitForObject(this.closeCategory, 1);
		this.searchCategory(category);
		Engine.getDriver().findElement(MobileBy.iOSClassChain(sXpath)).click();
		Thread.sleep(1000);	
	}
	
	public void searchCategory (String category) throws Exception{

		this.searchCategoryTextField.click();
		this.searchCategoryTextField.sendKeys(category);

		Helper h = new Helper();
		h.hideKeyBoard();
	}

	public void selectTags (String sTag) throws Exception {

		Helper h = new Helper();

		if (h.getEngine().equals("android"))
			this.selectTags_android(sTag);
		else
			this.selectTags_ios(sTag);
	}

	private void selectTags_android (String sTag) throws Exception {

		String sXpath="//android.widget.TextView[@text='Tags']";
		Engine.getDriver().findElement(By.xpath(sXpath)).click();
		Verify.waitForObject(this.searchTags, 1);
		this.searchTags.sendKeys(sTag);
		Helper h = new Helper();
		h.hideKeyBoard();
		Thread.sleep(1000);

		String createTag_xpath="//android.widget.TextView[@content-desc='create item']";

		if (Verify.objExists(createTag)) {
			Verify.objExists((MobileElement)Engine.getDriver().findElement(By.xpath(createTag_xpath)));
			Engine.getDriver().findElement(By.xpath(createTag_xpath)).click();
			Commentary.log(LogStatus.INFO,"Creating new Tag.. "+sTag);
			
			String cc = "//android.widget.TextView[@text='"+sTag+"']";
			Engine.getDriver().findElement(By.xpath(cc)).click();
			Thread.sleep(500);
			Commentary.log(LogStatus.INFO,"Selected Tag which is just now created.. "+sTag);
			h.hideKeyBoard();
			Thread.sleep(1000);
		}
		else {
			String cc = "//android.widget.TextView[@text='"+sTag+"']";
			Engine.getDriver().findElement(By.xpath(cc)).click();
			Thread.sleep(500);
			Commentary.log(LogStatus.INFO,"Selected Tag which is already present.. "+sTag);
			h.hideKeyBoard();
			Thread.sleep(1000);
		}
		Thread.sleep(1000);

		this.tagsApplyButton.click();
		Thread.sleep(1000);	
	}

	private void selectTags_ios (String sTag) throws Exception {

		Engine.getDriver().findElement((MobileBy.iOSClassChain("**/XCUIElementTypeOther[`name='Tags'`]"))).click();
		Verify.waitForObject(this.searchTags, 1);
		this.searchTags.sendKeys(sTag);
		Helper h = new Helper();
		h.hideKeyBoard();
		Thread.sleep(1000);

		String createTag_xpath="**/XCUIElementTypeOther[`name='create item'`]";

		if (Verify.objExists(createTag)) {
//			Verify.objExists((MobileElement)Engine.getDriver().findElement(MobileBy.iOSClassChain(createTag_xpath)));
			Engine.getDriver().findElement(MobileBy.iOSClassChain(createTag_xpath)).click();
			Commentary.log(LogStatus.INFO,"Creating Tag... "+sTag);
			
			String cc = "**/XCUIElementTypeStaticText[`name='"+sTag+"'`]";
			Engine.getDriver().findElement(MobileBy.iOSClassChain(cc)).click();
			Thread.sleep(500);
			Commentary.log(LogStatus.INFO,"Selected Tag which is just now created.. "+sTag);
			h.hideKeyBoard();
			Thread.sleep(1000);
		}
		else {
			String cc = "**/XCUIElementTypeStaticText[`name='"+sTag+"'`]";
			Engine.getDriver().findElement(MobileBy.iOSClassChain(cc)).click();
			Thread.sleep(500);
			Commentary.log(LogStatus.INFO,"Selected Tag which is already present.. "+sTag);
			h.hideKeyBoard();
			Thread.sleep(1000);
		}
		Thread.sleep(1000);

		this.tagsApplyButton.click();
		Thread.sleep(1000);	
	}

	public void enterNotes (String sNote) throws Exception {

		Helper h = new Helper();

		if (h.getEngine().equals("android"))
			this.enterNotes_android(sNote);
		else
			this.enterNotes_ios(sNote);
	}

	private void enterNotes_android (String sNote) throws Exception {

		Verify.waitForObject(this.memo, 1);
		this.memo.click();
		this.memo.clear();
		this.memo.sendKeys(sNote);
		Thread.sleep(1000);
		
		Helper h = new Helper();
		h.hideKeyBoard();
		Thread.sleep(1000);
//		this.doneButton.click();
		this.amount.click();
	}

	private void enterNotes_ios (String sNote) throws Exception {
	
		Verify.waitForObject(this.memo, 1);
		this.memo.click();
		this.memo.clear();
		this.memo.sendKeys(sNote);
		Thread.sleep(1000);
		
		this.doneButton.click();
	}

	public void enterAmount(String amount) throws Exception {

		Helper h = new Helper();

		if (h.getEngine().equals("android"))
			enterMemorizedPayeeAmount_android(amount);
		else
			enterMemorizedPayeeAmount_ios(amount);			
	}

	private void enterMemorizedPayeeAmount_android(String amount) throws Exception {

		String[] s = amount.split("");
		int iCount;

		Verify.waitForObject(this.amount, 1);
		this.amount.click();
		Thread.sleep(1000);
		Verify.waitForObject(this.buttonDone, 1);
		
		for (int i = 1; i < amount.length(); i++) {
			Engine.getDriver().findElement(By.xpath("//android.widget.ImageView[@content-desc='delete']")).click();

			Thread.sleep(1000);
		}

		for(iCount=0; iCount<s.length; iCount++) {

			if (s[iCount].equals(".")) {
				// ignore
			}
			else
				Engine.getDriver().findElement(By.xpath("//*[@text='"+s[iCount]+"']")).click();	
		}

		if(Verify.objExists(this.buttonDone))
			this.buttonDone.click();

		Thread.sleep(1000);
	}

	private void enterMemorizedPayeeAmount_ios(String amount) throws Exception {

		String[] s = amount.split("");
		int iCount;

		Verify.waitForObject(this.amount, 1);
		this.amount.click();
		Thread.sleep(1000);
		
		for (int i = 1; i < amount.length(); i++) {
			Engine.getDriver().findElement(MobileBy.iOSClassChain("**/XCUIElementTypeOther[`name=='delete'`][2]")).click();
		}
		Thread.sleep(1000);

		for(iCount=0; iCount<s.length; iCount++) {

			if (s[iCount].equals(".")) {
				// ignore
			}
			else
				Engine.getDriver().findElement(MobileBy.iOSClassChain("**/XCUIElementTypeStaticText[`name=='"+s[iCount]+"'`]")).click();	
		}

		if(Verify.objExists(this.buttonDone))
			this.buttonDone.click();

		Thread.sleep(1000);
	}
	
	public void enableMarkAsClearedSwitch(String value) throws Exception {
		
		if(value.equalsIgnoreCase("Enable")) {
			this.switchMarkAsCleared.click();
			Thread.sleep(1000);
		}
	}
	
	public void enableLockPayeeSwitch(String value) throws Exception {
		
		if(value.equalsIgnoreCase("Enable")) {
			this.switchLockPayee.click();
			Thread.sleep(1000);
		}
	}
	
	public void enterPayeeNameOnAddTransactionPage(String payeeName) throws Exception {

		if (Verify.objExists(this.allowButton)) {
			this.allowButton.click();
			Thread.sleep(1000);
		}

		Verify.waitForObject(this.buttonDone, 1);
		if (Verify.objExists(this.buttonDone) || Verify.objExists(this.addTransactionTxt)) {
			Commentary.log(LogStatus.INFO,"Add Transaction Screen got displayed.");
			Thread.sleep(2000);

			this.buttonDone.click();
			Thread.sleep(1000);

			this.payeeOnAddTransactionPage.click();
			Thread.sleep(1000);

			if (! Verify.objExists(this.closePayee))
				Commentary.log(LogStatus.FAIL,"Error****** Transaction Detail > tapping on payee, did not opened Payee selection drawer.");

			this.searchPayee.sendKeys(payeeName);
			Helper h = new Helper();
			h.hideKeyBoard();
			Thread.sleep(3000);

			if (h.getEngine().equals("android"))
				this.enteringPayeeNameOnAddTransactionPage_android(payeeName);
			else
				this.enteringPayeeNameOnAddTransactionPage_ios(payeeName);
		}
	}
	
	private void enteringPayeeNameOnAddTransactionPage_ios (String payeeName) throws Exception {

		String createPayee_xpath="**/XCUIElementTypeOther[`name='create payee'`]";
		String allPayees = "**/XCUIElementTypeStaticText[`name='"+payeeName+"'`]";

		if (Verify.objExists(createPayee)) {
			Verify.objExists((MobileElement)Engine.getDriver().findElement(MobileBy.iOSClassChain(createPayee_xpath)));
			Engine.getDriver().findElement(MobileBy.iOSClassChain(createPayee_xpath)).click();
			Commentary.log(LogStatus.INFO,"Entered Payee.. "+payeeName);
		}
		else {
			Engine.getDriver().findElement(MobileBy.iOSClassChain(allPayees)).click();
			Thread.sleep(1500);
			Commentary.log(LogStatus.INFO,"Selected Payee from All Payees List.. "+payeeName);
		}
		Thread.sleep(1000);	
	}
	
	private void enteringPayeeNameOnAddTransactionPage_android (String payeeName) throws Exception {

//		if (Verify.objExists(this.allowButton)) {
//			this.allowButton.click();
//			Thread.sleep(1000);
//		}
//		
//		SoftAssert sa = new SoftAssert();
//		Verify.waitForObject(this.buttonDone, 1);
//		if (Verify.objExists(this.buttonDone) || Verify.objExists(this.addTransactionTxt)) {
//			Commentary.log(LogStatus.INFO,"Add Transaction Screen got displayed.");
//			Thread.sleep(2000);
//
//			this.buttonDone.click();
//			Thread.sleep(1000);
//
//			this.payeeOnAddTransactionPage.click();
//			Thread.sleep(1000);
//
//			if (! Verify.objExists(this.closePayee))
//				Commentary.log(LogStatus.FAIL,"Error****** Transaction Detail > tapping on payee, did not opened Payee selection drawer.");
//
//			this.searchPayee.sendKeys(payeeName);
//			Helper h = new Helper();
//			h.hideKeyBoard();
//			Thread.sleep(3000);
			
		String createPayee_xpath="//android.widget.TextView[@content-desc='create payee']";
		String allPayees_Xpath = "//android.widget.ScrollView/android.view.ViewGroup//android.widget.TextView[@text=\"All Payees\"]/../../../android.view.ViewGroup//android.widget.TextView[@text='"+payeeName+"']";

		if (Verify.objExists(createPayee)) {
			Verify.objExists((MobileElement)Engine.getDriver().findElement(By.xpath(createPayee_xpath)));
			Engine.getDriver().findElement(By.xpath(createPayee_xpath)).click();
			Commentary.log(LogStatus.INFO,"Entered Payee.. "+payeeName);
		}
		else {
			Engine.getDriver().findElement(By.xpath(allPayees_Xpath)).click();
			Thread.sleep(1500);
			Commentary.log(LogStatus.INFO,"Selected Payee from All Payees List.. "+payeeName);
		}
		Thread.sleep(1000);	
	}
	
	public void addSplit(TransactionRecord tr, HashMap<Integer,String> amount, HashMap<Integer,String> cat, HashMap<Integer,String[]> tags) throws Exception{

		if (Verify.objExists(this.allowButton)) {
			this.allowButton.click();
			Thread.sleep(1000);
		}

		Verify.waitForObject(this.addMemorizedPayeeButton, 1);
		if (Verify.objExists(this.addMemorizedPayeeButton) || Verify.objExists(this.createMemorizedPayeeHeader)) {
			Commentary.log(LogStatus.INFO,"Create Memorized Payee Page got displayed.");
			Thread.sleep(2000);

			if (tr.getPayee() != null)
				this.selectPayee(tr.getPayee());
			
			if (tr.getTransactionType() != null)
				this.enterTransactionType(tr.getTransactionType());
			
			if (tr.getNote() != null)
				this.enterNotes(tr.getNote());

			if (tr.getAmount() != null) 
				this.enterAmount(tr.getAmount());

			this.switchSplit.click();
			Thread.sleep(1000);

			for (int iCount=1; iCount<=cat.size(); iCount++) {
				this.enterSplitAmt(iCount, amount.get(iCount));
				this.tapOnSplitCategory(iCount);
				this.selectCategoryOnDrawer(cat.get(iCount));

				if (tags.get(iCount) != null) {
					this.tapOnSplitTag(iCount);
					this.selectTags_split(tags.get(iCount));
				}
			}
			this.addMemorizedPayeeButton.click();
			Thread.sleep(1000);
		}
	}

	public void tapOnSplitTag(Integer index) throws Exception{	
		Helper h = new Helper();
		if (h.getEngine().equals("android"))
			tapOnSplitTag_android(index);
		else
			tapOnSplitTag_ios(index);
	}

	private void tapOnSplitTag_ios(Integer index) throws Exception{

		String cc = "**/XCUIElementTypeOther[`name BEGINSWITH 'Tags:'`]["+index+"]/**/XCUIElementTypeStaticText";

		Thread.sleep(1000);
		Engine.getDriver().findElement(MobileBy.iOSClassChain(cc)).click();
	}

	private void tapOnSplitTag_android(Integer index) throws Exception{
		String xpath = "(//android.view.ViewGroup[contains(@content-desc,'Tags:')])["+index+"]//android.widget.TextView";
		Engine.getDriver().findElement(By.xpath(xpath)).click();
	}

	public void selectTags_split(String[] sTag) throws Exception {

		Helper h = new Helper();

		if (h.getEngine().equals("android"))
			this.selectTags_android_new_split(sTag);
		else
			this.selectTags_ios_new_split(sTag);
	}

	private void selectTags_ios_new_split(String[] sTags) throws Exception {

		for(String tag:sTags){
			Engine.getDriver().findElement((MobileBy.iOSClassChain("**/XCUIElementTypeOther[`name='Tags'`]"))).click();
			Verify.waitForObject(this.searchTags, 1);
			this.searchTags.sendKeys(tag);
			Helper h = new Helper();
			h.hideKeyBoard();
			Thread.sleep(1000);

			String createTag_xpath="**/XCUIElementTypeOther[`name='create item'`]";
			String cc = "**/XCUIElementTypeStaticText[`name='"+tag+"'`]";

			if (Verify.objExists(createTag)) {
				Engine.getDriver().findElement(MobileBy.iOSClassChain(createTag_xpath)).click();
				Commentary.log(LogStatus.INFO,"Creating Tag... "+tag);

				Engine.getDriver().findElement(MobileBy.iOSClassChain(cc)).click();
				Thread.sleep(500);
				Commentary.log(LogStatus.INFO,"Selected Tag which is just now created.. "+tag);
				h.hideKeyBoard();
				Thread.sleep(1000);
			}
			else {
				Engine.getDriver().findElement(MobileBy.iOSClassChain(cc)).click();
				Thread.sleep(500);
				Commentary.log(LogStatus.INFO,"Selected Tag which is already present.. "+tag);
				h.hideKeyBoard();
				Thread.sleep(1000);
			}
			Thread.sleep(1000);
		}
		this.tagsApplyButton.click();
		Thread.sleep(1000);	
	}

	private void selectTags_android_new_split(String[] sTags) throws Exception {

		for(String tag:sTags){
			this.searchTag(tag);
			Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ tag + "\").instance(0))"));

			String createTag_xpath = "//android.widget.TextView[@content-desc='create item']";
			String cc = "//android.widget.TextView[@text='"+tag+"']";

			if (Verify.objExists(createTag)) {
				Engine.getDriver().findElement(By.xpath(createTag_xpath)).click();
				Commentary.log(LogStatus.INFO,"Creating Tag... "+tag);
				Thread.sleep(2000);

				Engine.getDriver().findElement(By.xpath(cc)).click();
				Thread.sleep(1000);
				Commentary.log(LogStatus.INFO,"Selected Tag which is just now created.. "+tag);
			}
			else {
				Engine.getDriver().findElement(By.xpath(cc)).click();
				Thread.sleep(500);
				Commentary.log(LogStatus.INFO,"Selected Tag which is already present.. "+tag);
			}
		}
		this.tagsApplyButton.click();
		Thread.sleep(1000);
	}

	public void searchTag(String sTag) throws Exception{

		this.searchTags.clear();
		this.searchTags.sendKeys(sTag);
		Engine.getDriver().hideKeyboard();
	}

	public void tapOnSplitCategory(Integer index) throws Exception{	
		
		Helper h = new Helper();
		
		if (h.getEngine().equals("android"))
			tapOnSplitCategory_android(index);
		else
			tapOnSplitCategory_ios(index);
	}

	private void tapOnSplitCategory_ios(Integer index) throws Exception{
		String cc = "**/XCUIElementTypeOther[`name BEGINSWITH 'Category:'`]["+index+"]/**/XCUIElementTypeStaticText[1]";
		Engine.getDriver().findElement(MobileBy.iOSClassChain(cc)).click();
	}

	private void tapOnSplitCategory_android(Integer index) throws Exception{
		String xpath = "(//android.view.ViewGroup[contains(@content-desc,'Category:')])["+index+"]//android.widget.TextView";
		Engine.getDriver().findElement(By.xpath(xpath)).click();
	}

	public void enterSplitAmt(Integer index, String amount) throws Exception{

		Helper h = new Helper();

		if (h.getEngine().equals("android"))
			enterSplitAmount_android(index,amount);
		else
			enterSplitAmount_ios(index,amount);
	}

	public void enterSplitAmount_android(Integer index, String sAmount) throws Exception {

		String[] s = sAmount.split("");
		int iCount;

		String xpath = "(//android.view.ViewGroup[contains(@resource-id,'Amount:')]//android.widget.TextView)["+index+"]";
		Engine.getDriver().findElement(By.xpath(xpath)).click();

		for (int i = 1; i < 6; i++) {
			Engine.getDriver().findElement(By.xpath("//android.widget.ImageView[@content-desc='delete']")).click();
		}

		for(iCount=0; iCount<s.length; iCount++) {

			if (s[iCount].equals(".")) {
				// ignore
			}
			else
				Engine.getDriver().findElement(By.xpath("//*[@text='"+s[iCount]+"']")).click();	
		}

		if (Verify.objExists(this.buttonDone))
			this.buttonDone.click();

		Thread.sleep(1000);
	}

	private void enterSplitAmount_ios(Integer index, String sAmount) throws Exception{

		String[] s = sAmount.split("");
		int iCount;
		Helper h = new Helper();

		String cc = "**/XCUIElementTypeOther[`name BEGINSWITH 'Amount:'`]/**/XCUIElementTypeStaticText[`name CONTAINS '$'`]["+index+"]";
		Engine.getDriver().findElement(MobileBy.iOSClassChain(cc)).click();

		for (int i = 0; i < 6; i++) {
			if (h.getEngine().equals("android"))
				Engine.getDriver().findElement(By.id("assets/Quicken/App/Images/keypadbutton_delete@2x.png")).click();
			else
				Engine.getDriver().findElement(MobileBy.iOSNsPredicateString("name = 'delete'")).click();	
		}
		Thread.sleep(1000);

		for(iCount=0; iCount<s.length; iCount++) {

			if (s[iCount].equals(".")) {
				// ignore
			}
			else	
				Engine.getDriver().findElement(MobileBy.iOSClassChain("**/XCUIElementTypeStaticText[`name=='"+s[iCount]+"'`]")).click();	
		}

		if (Verify.objExists(this.buttonDone))
			this.buttonDone.click();

		Thread.sleep(1000);
	}

	public void selectCategoryOnDrawer (String category) throws Exception {

		Helper h = new Helper();

		if (h.getEngine().equals("android"))
			this.selectCategoryOnDrawer_android(category);
		else
			this.selectCategoryOnDrawer_ios(category);
	}

	private void selectCategoryOnDrawer_ios (String category) throws Exception{

		String sXpath = "**/XCUIElementTypeOther[`name='RadioButton "+category+"'`]/**/XCUIElementTypeOther[`name='RadioButton'`]";
		this.searchCategory(category);
		Engine.getDriver().findElement(MobileBy.iOSClassChain(sXpath)).click();
		Thread.sleep(500);
	}

	private void selectCategoryOnDrawer_android (String category) throws Exception{

		String sXpath = "//android.view.ViewGroup[android.widget.TextView[@text='"+category+"']]/android.view.ViewGroup[@content-desc='RadioButton']";
		this.searchCategory(category);
		Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ category + "\").instance(0))"));
		Thread.sleep(1000);
		Engine.getDriver().findElement(By.xpath(sXpath)).click();
		Thread.sleep(500);
	}

}
