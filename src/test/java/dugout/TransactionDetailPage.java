package dugout;

import java.text.DateFormatSymbols;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import referee.Commentary;
import referee.Verify;
import support.Engine;
import support.Helper;
import support.TransactionRecord;

public class TransactionDetailPage {
	
	public TransactionDetailPage () {
		try {
			Helper h = new Helper();
			if (h.getEngine().equals("android"))
				PageFactory.initElements(new AppiumFieldDecorator(Engine.ad),this);
			else
				PageFactory.initElements(new AppiumFieldDecorator(Engine.iosd),this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Add Transaction\"]")
	@AndroidFindBy(xpath="//android.widget.Button[@text='Allow']")
	public MobileElement allowButton;
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Add Transaction\"]")
	@AndroidFindBy(xpath="//android.widget.Button[@text='Deny']")
	public MobileElement denyButton;

	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Add Transaction\"]")
	@AndroidFindBy(xpath="//*[@text='Add Transaction']")
	public MobileElement addTransactionTxt;
	
	
	@iOSFindBy(xpath="//XCUIElementTypeButton[@name=\"Back\"]")
	@AndroidFindBy(xpath="//*[@text='Add Transaction']/../android.widget.ImageButton")
	public MobileElement backButton;
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Money In\"]")
	@AndroidFindBy(xpath="//*[@text='Money In']")
	public MobileElement moneyIn;
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Money Out\"]")
	@AndroidFindBy(xpath="//*[@text='Money Out']")
	public MobileElement moneyOut;
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[contains(@name,'$0.00')]")
	@AndroidFindBy(xpath="//*[@content-desc='-$0.00']//android.widget.TextView")
	public MobileElement amount;
	
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[starts-with(@name,'Date ')]/XCUIElementTypeStaticText")
	@AndroidFindBy(xpath="//*[@text='Date']")
	public MobileElement dateLabel;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name='Date']/../XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//*[@text='Date']/../android.widget.TextView[@index=2]")
	public MobileElement selectedDate;
	
	// -------------- Date picker --------------
	
	@iOSFindBy(xpath="//XCUIElementTypeDatePicker")
	@AndroidFindBy(xpath="//android.widget.DatePicker")
	public MobileElement datePicker;
	
	@iOSFindBy(xpath="//XCUIElementTypePickerWheel[1]")
	@AndroidFindBy(xpath="//android.widget.DatePicker//android.widget.NumberPicker[@index=0]/android.widget.EditText")
	public MobileElement enterMonth;
	
	@iOSFindBy(xpath="//XCUIElementTypePickerWheel[2]")
	@AndroidFindBy(xpath="//android.widget.DatePicker//android.widget.NumberPicker[@index=1]/android.widget.EditText")
	public MobileElement enterDate;
	
	@iOSFindBy(xpath="//XCUIElementTypePickerWheel[3]")
	@AndroidFindBy(xpath="//android.widget.DatePicker//android.widget.NumberPicker[@index=2]/android.widget.EditText")
	public MobileElement enterYear;
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Cancel\"]")
	@AndroidFindBy(xpath="//*[@text='Cancel']")
	public MobileElement buttonCancel;
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"OK\"]")
	@AndroidFindBy(xpath="//*[@text='OK']")
	public MobileElement buttonOK;
	
	// -------------- Date picker --------------
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Choose Account\"]")
	@AndroidFindBy(xpath="//android.widget.ScrollView//android.widget.TextView[@text='Choose Account']")
	public MobileElement account;
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Choose Account\"]")
	@AndroidFindBy(xpath="//*[@text='Choose Account']")
	public MobileElement chooseAccount;
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Back']")
	@AndroidFindBy(xpath="//*[@text='Account']/../android.widget.TextView[@index=2]")
	public MobileElement selectedAccount;
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Back']")
	@AndroidFindBy(xpath="//*[@content-desc='closeChoose Account']/android.widget.ImageView")
	public MobileElement closeChooseAccount;
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Back']")
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Add Check Number']")
	public MobileElement addCheckNumber;
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Back']")
	@AndroidFindBy(xpath="//*[@text='Check Number']/../android.widget.EditText")
	public MobileElement enteredCheckNumber;
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Back']")
	@AndroidFindBy(xpath="//*[@content-desc='Done']/android.widget.TextView")
	public MobileElement buttonDone;
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Back']")
	@AndroidFindBy(xpath="//*[@text='Payee']")
	public MobileElement payee;
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Back']")
	@AndroidFindBy(xpath="//*[@text='Payee']/../android.widget.TextView[@index=2]")
	public MobileElement selectedpayee;
	
	// ------------------ Payee SCREEN ------------------
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Back']")
	@AndroidFindBy(xpath="//*[@content-desc='closePayee']/android.widget.ImageView")
	public MobileElement closePayee;
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Back']")
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Search Payee']")
	public MobileElement searchPayee;
	
	// ------------------ Payee SCREEN ------------------
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Back']")
	@AndroidFindBy(xpath="//*[@text='Category']")
	public MobileElement category;
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Back']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Category']/../android.widget.TextView[@index=2]")
	public MobileElement selectedCategory;
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Back']")
	@AndroidFindBy(xpath="//*[@text='Tags']")
	public MobileElement tags;
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Back']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Tags']/../android.widget.TextView[@index=2]")
	public MobileElement selectedTags;
	
	// ------------------ TAG SCREEN ------------------
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Back']")
	@AndroidFindBy(xpath="//*[@content-desc='closeTags']")
	public MobileElement closeTags;
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Back']")
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Search Tags']")
	public MobileElement searchTags;
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Back']")
	@AndroidFindBy(xpath="//*[@class='android.widget.EditText']")
	public MobileElement getTagSearchText;
	
	// ------------------ TAG SCREEN ------------------
	
	// ------------------ Category SCREEN ------------------
	@iOSFindBy(xpath="//*[normalize-space(@name)='Back']")
	@AndroidFindBy(xpath="//*[@content-desc='closeCategory']/android.widget.ImageView")
	public MobileElement closeCategory;
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Back']")
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Search category']")
	public MobileElement searchCategory;
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Back']")
	@AndroidFindBy(xpath="//*[@class='android.widget.EditText']")
	public MobileElement getCategorySearchText;
	
	// ------------------ Category SCREEN ------------------
	
	// for android getText attribute will return the entered notes
	@iOSFindBy(xpath="//*[normalize-space(@name)='Back']")
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Note']")
	public MobileElement note;
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Back']")
	@AndroidFindBy(xpath="//*[@text='Done']")
	public MobileElement buttonDone_OSKeyBoard;
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Back']")
	@AndroidFindBy(xpath="//*[@text='Add Attachments']")
	public MobileElement addAttachment;
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Back']")
	@AndroidFindBy(xpath="//*[@content-desc='deleteAttachment']")
	public MobileElement deleteAttachment;
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Back']")
	@AndroidFindBy(xpath="//*[@text='Library']")
	public MobileElement library;
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Back']")
	@AndroidFindBy(xpath="//*[@text='Camera']")
	public MobileElement camera;
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Back']")
	@AndroidFindBy(xpath="//*[@text='Cancel']")
	public MobileElement cancel;
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Back']")
	@AndroidFindBy(xpath="//*[@content-desc='button Save']/android.widget.TextView")
	public MobileElement buttonSave;
	
	
	public MobileElement getPayeeElement (String payee) {
		
		String xpath_Android = "//android.view.ViewGroup[@content-desc='"+payee+"']//android.widget.TextView";
		String xpath_IOS = "//Method:getTagElement_IOS_Declaration_Not_Taken";
		
		Helper h = new Helper();
		
		if (h.getEngine().equals("android")){
			try{
				return (MobileElement) Engine.ad.findElementByXPath(xpath_Android);
			}
			catch(Exception E){
				return null;
			}
			
		}
		else {
			try{
				return (MobileElement) Engine.iosd.findElementByXPath(xpath_IOS);
			}
			catch(Exception E){
				return null;
			}
	}

	}
	
	public MobileElement getTagElement (String tagText) {
		
		String xpath_Android = "//android.view.ViewGroup[@content-desc='"+tagText+"']//android.widget.TextView";
		String xpath_IOS = "//Method:getTagElement_IOS_Declaration_Not_Taken";
		
		Helper h = new Helper();
		
		if (h.getEngine().equals("android")){
			try{
				return (MobileElement) Engine.ad.findElementByXPath(xpath_Android);
			}
			catch(Exception E){
				return null;
			}
			
		}
		else {
			try{
				return (MobileElement) Engine.iosd.findElementByXPath(xpath_IOS);
			}
			catch(Exception E){
				return null;
			}
	}

	}
	
	public MobileElement getCategory (String categoryText) {
		
		String xpath_Android = "//android.widget.TextView[@text='"+categoryText+"']";
		String xpath_IOS = "//Method:getCategory_IOS_Declaration_Not_Taken";
		
		
		Helper h = new Helper();
		
		if (h.getEngine().equals("android")){
			try{
				return (MobileElement) Engine.ad.findElementByXPath(xpath_Android);
			}
			catch(Exception E){
				System.out.println(E.getMessage());
				return null;
			}
			
		}
		else {
			try{
				return (MobileElement) Engine.iosd.findElementByXPath(xpath_IOS);
			}
			catch(Exception E){
				System.out.println(E.getMessage());
				return null;
			}
	}
		
		
		
	}
	
	
	public MobileElement getAccount (String accountText) {
		
		String xpath_Android = "//android.widget.TextView[@text='"+accountText+"']";
		String xpath_IOS = "//Method:accountText_IOS_Declaration_Not_Taken";
		
		
		Helper h = new Helper();
		
		if (h.getEngine().equals("android")){
			try{
				return (MobileElement) Engine.ad.findElementByXPath(xpath_Android);
			}
			catch(Exception E){
				System.out.println(E.getMessage());
				return null;
			}
			
		}
		else {
			try{
				return (MobileElement) Engine.iosd.findElementByXPath(xpath_IOS);
			}
			catch(Exception E){
				System.out.println(E.getMessage());
				return null;
			}
	}
		
		
		
	}
	
	
	public void addTransaction (TransactionRecord tr) throws InterruptedException {
		
		if (Verify.objExists(this.addTransactionTxt)) {
			Commentary.log(LogStatus.INFO,"AddTransaction screen got dispalyed.");
			
			// Allow Quicken to access this device's location?
			if (Verify.objExists(this.allowButton)) {
				this.allowButton.click();
				Thread.sleep(1000);
			}
			
			enterTransactionAmount(tr.getAmount());
			
			
			
		
			
			
			
		}
			
		else
			Commentary.log(LogStatus.FAIL,"AddTransaction screen did not appear.");

	}
	
	public void enterTransactionAmount(String amount) throws InterruptedException {
		
		String[] s = amount.split("");
		int iCount;
		
		
		if (! Verify.objExists(this.buttonDone)) {
			this.amount.click();
			Thread.sleep(1000);
		}
		
		for(iCount=0; iCount<s.length; iCount++) {
			
			if (s[iCount].equals(".")) {
				// ignore
			}
			else
				Engine.ad.findElement(By.xpath("//*[@text='"+s[iCount]+"']")).click();	
		}
		
		this.buttonDone.click();
		
	}


	// provide date in m/dd/yyyy format
	// Ex: 1/22/2019
	public void enterDate(String mddyyyy) throws InterruptedException {
		
		String[] a = mddyyyy.split("/");
		
		String month = new DateFormatSymbols().getShortMonths()[Integer.parseInt(a[0])-1].toString();
		String date = a[1];
		String year = a[2];
		
		if (! Verify.objExists(this.datePicker)) {
			this.dateLabel.click();
			Thread.sleep(1000);
		}
		
		TouchAction touch = new TouchAction(Engine.ad);
		touch.longPress(this.enterMonth,1000).release();
		Engine.ad.performTouchAction(touch);
		Engine.ad.getKeyboard().pressKey(month);
		Thread.sleep(2000);
		System.out.println(Engine.ad.getContext());
		
		TouchAction touch2 = new TouchAction(Engine.ad);
		touch2.longPress(this.enterDate,1000).release();
		Engine.ad.performTouchAction(touch2);
		Engine.ad.getKeyboard().pressKey(date);
		Thread.sleep(2000);
		System.out.println(Engine.ad.getContext());
		
		TouchAction touch3 = new TouchAction(Engine.ad);
		touch3.longPress(this.enterYear,1000).release();
		Engine.ad.performTouchAction(touch3);
		Engine.ad.getKeyboard().pressKey(year);
		Thread.sleep(2000);
		System.out.println(Engine.ad.getContext());
		
		this.buttonOK.click();
		Thread.sleep(2000);		
		
	}
	
	
	public void selectAccount (String acct) throws InterruptedException {
		
		String xpath = "//android.widget.TextView[@text='"+acct+"']";
		
		if (! Verify.objExists(this.closeChooseAccount))
			this.account.click();
		
		
		if (! Verify.objExists((MobileElement)Engine.ad.findElement(By.xpath(xpath))))
			Commentary.log(LogStatus.FAIL,"Error****** Account ["+acct+"] not found on choose account");
		
		Engine.ad.findElement(By.xpath(xpath)).click();
		
		Thread.sleep(2000);
		
		// selecting the account will close the choose account screen
		// verify if the account got selected or not
		if (Verify.objExists(this.closeChooseAccount))
			Commentary.log(LogStatus.FAIL,"Error****** Transaction Detail > Choose Account screen > tapping on Account ["+acct+"] did not close Choose Account screen");
		else
			Commentary.log(LogStatus.INFO,"Transaction Detail > Choose Account screen > tapping on Account ["+acct+"] closed Choose Account screen");
		
	}
	
	
	public void selectPayee (String payees) throws Exception {
		
		String sXpath = "//android.widget.TextView[@text='"+payees+"']";
		
		this.payee.click();
		Thread.sleep(1000);
		System.out.println(Engine.ad.getContext());
		
		if (! Verify.objExists(this.closePayee))
			Commentary.log(LogStatus.FAIL,"Error****** Transaction Detail > tapping on payee, did not open Payee selection screen");
		
		Engine.ad.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ payees + "\").instance(0))"));
		
		Thread.sleep(1000);
		Engine.ad.findElement(By.xpath(sXpath)).click();
		Thread.sleep(1000);	
	}
	
	public void selectCategory (String category) throws Exception {
		
		String sXpath = "//android.widget.TextView[@text='"+category+"']";
		
		this.category.click();
		Thread.sleep(1000);
		System.out.println(Engine.ad.getContext());
		
		if (! Verify.objExists(this.closeCategory))
			Commentary.log(LogStatus.FAIL,"Error****** Transaction Detail > tapping on payee, did not open Payee selection screen");
		
		Engine.ad.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ category + "\").instance(0))"));
		
		Thread.sleep(1000);
		Engine.ad.findElement(By.xpath(sXpath)).click();
		Thread.sleep(1000);	
		System.out.println(Engine.ad.getContext());
	}
	
	public void selectTags (String[] sTag) throws Exception {
		
		//String sXpath;// = "//android.widget.TextView[@text='"+category+"']";
		Integer iCount;
		
		this.tags.click();
		Thread.sleep(1000);
		System.out.println(Engine.ad.getContext());
		
		for (iCount=0; iCount<sTag.length; iCount++) {
			
			String sXpath = "//android.widget.TextView[@text='"+sTag[iCount]+"']";
			
			Engine.ad.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ sTag[iCount] + "\").instance(0))"));
			Thread.sleep(1000);
			Engine.ad.findElement(By.xpath(sXpath)).click();
			Thread.sleep(1000);	
		}
		
		
		this.closeTags.click();
		Thread.sleep(1000);
		System.out.println(Engine.ad.getContext());	
	
	}
	
	public void enterNotes (String sNote) throws Exception {
		
		// scroll into notes
		String sText = "Note";
		Engine.ad.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ sText + "\").instance(0))"));
		Thread.sleep(1000);
		this.note.click();
		this.note.sendKeys(sNote);
		this.buttonDone_OSKeyBoard.click();
		
	}
	
	public void enterCheckNumber (String sCheck) {
		
		this.addCheckNumber.click();
		this.addCheckNumber.sendKeys(sCheck);
		this.buttonDone_OSKeyBoard.click();
		
	}
}
