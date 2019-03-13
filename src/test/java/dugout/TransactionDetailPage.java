package dugout;

import java.text.DateFormatSymbols;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
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
	
	@iOSFindBy(xpath="//*[@name=\"Allow\"]")
	@AndroidFindBy(xpath="//android.widget.Button[@text='Allow']")
	public MobileElement allowButton;
	
	@iOSFindBy(xpath="//*[@name=\"Deny\"]")
	@AndroidFindBy(xpath="//android.widget.Button[@text='Deny']")
	public MobileElement denyButton;

	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Add Transaction\"]")
	@AndroidFindBy(xpath="//*[@text='Add Transaction']")
	public MobileElement addTransactionTxt;
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"View Transaction\"]")
	@AndroidFindBy(xpath="//*[@text='View Transaction']")
	public MobileElement viewTransactionTxt;
	
	
	@iOSFindBy(xpath="//XCUIElementTypeButton[@name=\"Back\"]")
	@AndroidFindBy(xpath="//android.widget.ImageButton")
	public MobileElement backButton;
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Money In\"]")
	@AndroidFindBy(xpath="//*[@text='Money In']")
	public MobileElement moneyIn;
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Money Out\"]")
	@AndroidFindBy(xpath="//*[@text='Money Out']")
	public MobileElement moneyOut;
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Money In Money Out\"]/../XCUIElementTypeOther[contains(@name,'$')]")
	@AndroidFindBy(xpath="//*[@content-desc='-$0.00']//android.widget.TextView")
	public MobileElement amount;
	
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Date\"]")
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
	
	@iOSFindBy(xpath="//XCUIElementTypeImage[@name=\"assets/Quicken/App/Images/accountIcon@2x.png\"]")
	@AndroidFindBy(xpath="//android.widget.ScrollView//android.widget.TextView[contains(@text, 'Account')]")
	public MobileElement account;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Choose an Account\"]")
	@AndroidFindBy(xpath="//*[@text='Choose an Account']")
	public MobileElement chooseAnAccount;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Account\"]/../XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Account']/../android.widget.TextView[2]")
	public MobileElement selectedAccount;
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"closeChoose an Account\"]")
	@AndroidFindBy(xpath="//*[@content-desc='closeChoose an Account']/android.widget.ImageView")
	public MobileElement closeChooseAccount;
	
	@iOSFindBy(xpath="//XCUIElementTypeTextField[@value=\"Add Check Number\"]/")
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Add Check Number']")
	public MobileElement addCheckNumber;
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Check Number\"]//XCUIElementTypeTextField")
	@AndroidFindBy(xpath="//*[@text='Check Number']/../android.widget.EditText")
	public MobileElement enteredCheckNumber;
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Done\"]")
	@AndroidFindBy(xpath="//*[@text='Done']")
	//@AndroidFindBy(xpath="//*[@content-desc='Done']/android.widget.TextView")
	public MobileElement buttonDone;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Payee\"]")
	@AndroidFindBy(xpath="//*[@text='Payee']")
	public MobileElement payee;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Payee\"]/../XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//*[@text='Payee']/../android.widget.TextView[@index=2]")
	public MobileElement selectedpayee;
	
	// ------------------ Payee SCREEN ------------------
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"closePayee\"]")
	@AndroidFindBy(xpath="//*[@content-desc='closePayee']/android.widget.ImageView")
	public MobileElement closePayee;
	
	@iOSFindBy(xpath="//XCUIElementTypeTextField[@name='search payee']")
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Search Payee']")
	public MobileElement searchPayee;
	
	// ------------------ Payee SCREEN ------------------
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Category\"]")
	@AndroidFindBy(xpath="//*[@text='Category']")
	public MobileElement category;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Category\"]/../XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Category']/../android.widget.TextView[@index=2]")
	public MobileElement selectedCategory;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Tags\"]")
	@AndroidFindBy(xpath="//*[@text='Tags']")
	public MobileElement tags;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Tags\"]/../XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Tags']/../android.widget.TextView[@index=2]")
	public MobileElement selectedTags;
	
	// ------------------ TAG SCREEN ------------------
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"closeTags\"]")
	@AndroidFindBy(xpath="//*[@content-desc='closeTags']")
	public MobileElement closeTags;
	
	@iOSFindBy(xpath="//XCUIElementTypeTextField[@name=\"search tags\"]")
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Search Tags']")
	public MobileElement searchTags;
	
	@iOSFindBy(xpath="//XCUIElementTypeTextField")
	@AndroidFindBy(xpath="//*[@class='android.widget.EditText']")
	public MobileElement getTagSearchText;
	
	// ------------------ TAG SCREEN ------------------
	
	// ------------------ Category SCREEN ------------------
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"closeCategory\"]/XCUIElementTypeImage")
	@AndroidFindBy(xpath="//*[@content-desc='closeCategory']/android.widget.ImageView")
	public MobileElement closeCategory;
	
	@iOSFindBy(xpath="//XCUIElementTypeTextField[@value=\"Search Category\"]")
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Search Category']")
	public MobileElement searchCategoryTextField;
	/*
	@iOSFindBy(xpath="//*[normalize-space(@name)='Back']")
	@AndroidFindBy(xpath="//*[@class='android.widget.EditText']")
	public MobileElement getCategorySearchText;*/
	
	// ------------------ Category SCREEN ------------------
	
	// for android getText attribute will return the entered notes
	@iOSFindBy(xpath="//XCUIElementTypeTextView[@name=\"Note\"]")
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Note']")
	public MobileElement note;
	
	@iOSFindBy(xpath="//XCUIElementTypeImage[@name=\"assets/Quicken/App/Images/noteIcon@2x.png\"]/..//XCUIElementTypeTextView")
	@AndroidFindBy(xpath="//android.widget.EditText[@index='2']")
	public MobileElement enteredNote;
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"button Done\"])")
	@AndroidFindBy(xpath="//*[@text='Done']")
	public MobileElement buttonDone_OSKeyBoard;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Add Attachments\"]")
	@AndroidFindBy(xpath="//*[@text='Add Attachments']")
	public MobileElement addAttachment;
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Back']")
	@AndroidFindBy(xpath="//*[@content-desc='deleteAttachment']")
	public MobileElement deleteAttachment;
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Library\"]")
	@AndroidFindBy(xpath="//*[@text='Library']")
	public MobileElement library;
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Camera\"]")
	@AndroidFindBy(xpath="//*[@text='Camera']")
	public MobileElement camera;
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Cancel\"]")
	@AndroidFindBy(xpath="//*[@text='Cancel']")
	public MobileElement cancel;
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"button Save\"]")
	@AndroidFindBy(xpath="//*[@content-desc='button Save']//android.widget.TextView")
	public MobileElement buttonSave;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"Delete Transaction\"]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Delete Transaction']")
	public MobileElement deleteTransaction;
	
	
	
	
	public MobileElement getPayeeElement (String payee) {
		
		String xpath_Android = "//android.view.ViewGroup[@content-desc='"+payee+"']//android.widget.TextView";
		String xpath_IOS = "//XCUIElementTypeStaticText[@name='"+payee+"']" ;
		
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
				Commentary.log(LogStatus.INFO, E.getMessage());
				return null;
			}
	}

	}
	
	public MobileElement getTagElement (String tagText) {
		
		String xpath_Android = "//android.view.ViewGroup[@content-desc='"+tagText+"']//android.widget.TextView";
		String xpath_IOS = "//XCUIElementTypeStaticText[@name='"+tagText+"']" ;
		
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
				Commentary.log(LogStatus.INFO, E.getMessage());
				return null;
			}
	}

	}
	
	public MobileElement getCategory (String categoryText) {
		
		String xpath_Android = "//android.widget.TextView[@text='"+categoryText+"']";
		String xpath_IOS = "//XCUIElementTypeStaticText[@name='"+categoryText+"']" ;
		
		
		Helper h = new Helper();
		
		if (h.getEngine().equals("android")){
			try{
				return (MobileElement) Engine.ad.findElementByXPath(xpath_Android);
			}
			catch(Exception E){
				Commentary.log(LogStatus.INFO, E.getMessage());
				return null;
			}
			
		}
		else {
			try{
				return (MobileElement) Engine.iosd.findElementByXPath(xpath_IOS);
			}
			catch(Exception E){
				Commentary.log(LogStatus.INFO, E.getMessage());
				return null;
			}
	}
		
		
		
	}
	
	
	public MobileElement getAccount (String accountText) {
		
		String xpath_Android = "//android.widget.TextView[@text='"+accountText+"']";
		String xpath_IOS = "//XCUIElementTypeStaticText[@name='"+accountText+"']";
		
		
		Helper h = new Helper();
		
		if (h.getEngine().equals("android")){
			try{
				return (MobileElement) Engine.ad.findElementByXPath(xpath_Android);
			}
			catch(Exception E){
				Commentary.log(LogStatus.INFO, E.getMessage());
				return null;
			}
			
		}
		else {
			try{
				return (MobileElement) Engine.iosd.findElementByXPath(xpath_IOS);
			}
			catch(Exception E){
				Commentary.log(LogStatus.INFO, E.getMessage());
				return null;
			}
	}
		
		
		
	}
	
	
	public void addTransaction (TransactionRecord tr) throws Exception {
		
		// Allow Quicken to access this device's location?
		if (Verify.objExists(this.allowButton)) {
			this.allowButton.click();
			Thread.sleep(1000);
		}
		
		if (Verify.objExists(this.buttonDone)) {
			Commentary.log(LogStatus.INFO,"AddTransaction screen got dispalyed.");
			
			
			
			
			
			if (tr.getAmount() != null) 
				this.enterAmount(tr.getAmount());
			
			if (tr.getTransactionType() != null)
				this.enterTransactionType(tr.getTransactionType());
			
			if (tr.getDate() != null)
				this.enterDate(tr.getDate());
			
			if (tr.getAccount() != null)
				this.selectAccount(tr.getAccount());
			
			if (tr.getPayee() != null)
				this.selectPayee(tr.getPayee());
			
			if (tr.getCategory() != null)
				this.selectCategory(tr.getCategory());
			
			if (tr.getTags() != null)
				this.selectTags(tr.getTags());
			
			if (tr.getNote() != null)
				this.enterNotes(tr.getNote());
			
			this.buttonSave.click();
			
			Thread.sleep(6000);
			
		}
			
		else
			Commentary.log(LogStatus.FAIL,"AddTransaction screen did not appear.");

	}
	
	public void enterAmount(String amount) throws InterruptedException {
		
		Helper h = new Helper();
		
		if (h.getEngine().equals("android"))
			enterTransactionAmount_android(amount);
		else
			enterTransactionAmount_ios(amount);
					
	}
	
	public void enterTransactionType (String txnType) {
		
		if (txnType.equals("income"))
			this.moneyIn.click();
		else
			this.moneyOut.click();
	}
	
	public void enterTransactionAmount_android(String amount) throws InterruptedException {
		
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
		Thread.sleep(1000);
		
	}
	
	public void enterTransactionAmount_ios(String amount) throws InterruptedException {
		
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
				Engine.iosd.findElement(By.xpath("//XCUIElementTypeStaticText[@name='"+s[iCount]+"']")).click();	
		}
		
		this.buttonDone.click();
		Thread.sleep(1000);
		
	}
	
	public void enterDate(String mddyyyy) throws Exception {
		
		Helper h = new Helper();
		
		if (h.getEngine().equals("android"))
			enterDate_android(mddyyyy);
		else
			enterDate_IOS(mddyyyy);
					
	}

	// provide date in m/dd/yyyy format
	// Ex: 1/22/2019
	public void enterDate_android(String mddyyyy) throws InterruptedException {
		
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
		Thread.sleep(500);
		Engine.ad.getContext();
		
		
		TouchAction touch2 = new TouchAction(Engine.ad);
		touch2.longPress(this.enterDate,1000).release();
		Engine.ad.performTouchAction(touch2);
		Engine.ad.getKeyboard().pressKey(date);
		Thread.sleep(500);
		
		
		TouchAction touch3 = new TouchAction(Engine.ad);
		touch3.longPress(this.enterYear,1000).release();
		Engine.ad.performTouchAction(touch3);
		Engine.ad.getKeyboard().pressKey(year);
		Thread.sleep(1000);
		
		this.buttonOK.click();
		Thread.sleep(1000);	
		Engine.ad.getContext();
		
	}
	
	public void enterDate_IOS(String mddyyyy) throws Exception {
		
		String[] a = mddyyyy.split("/");
		
		Helper h = new Helper();
		
		String month = new DateFormatSymbols().getShortMonths()[Integer.parseInt(a[0])-1].toString();
		String date = a[1];
		String year = a[2];
		
		if (! Verify.objExists(this.datePicker)) {
			this.dateLabel.click();
			Thread.sleep(500);
		}
		
		
		
		int iCount;
		
		for (iCount=0; iCount < 3; iCount++)
			enterMonth.sendKeys(month);
		Thread.sleep(500);
		h.getContext();
		
		for (iCount=0; iCount < 3; iCount++)
			enterDate.sendKeys(date);
		Thread.sleep(500);
		
		for (iCount=0; iCount < 2; iCount++)
			enterYear.sendKeys(year);
		h.getContext();
		
		this.buttonOK.click();
		Thread.sleep(500);	
		
	}
	
	
	public void selectAccount (String acct) throws Exception {
		
		Helper h = new Helper();
		h.getContext();
		
		this.account.click();
		
		
		
		if (h.getEngine().equals("android")) {
			String xpath = "//android.widget.TextView[@text='"+acct+"']";
			Engine.ad.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ acct + "\").instance(0))"));
			Thread.sleep(1000);
			if (! Verify.objExists((MobileElement)Engine.ad.findElement(By.xpath(xpath)))) {
				Commentary.log(LogStatus.FAIL,"Error****** Account ["+acct+"] not found on choose account");
				throw new Exception("Error****** Account ["+acct+"] not found on choose account");
			}
			
			Engine.ad.findElement(By.xpath(xpath)).click();
			
			Thread.sleep(500);
			
		}
		else {
			String xpath = "//XCUIElementTypeOther[@name='"+acct+"']";
			MobileElement me = (MobileElement)Engine.iosd.findElement(By.name(acct));
			/*
			MobileElement element = (MobileElement) Engine.iosd.findElement(By.name(acct));
			String elementID = element.getId();
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("element", elementID);
			scrollObject.put("toVisible", "not an empty string");
			Engine.iosd.executeScript("mobile:scroll", scrollObject);
			Thread.sleep(1000);
			if (! Verify.objExists((MobileElement)Engine.iosd.findElement(By.xpath(xpath)))) {
				Commentary.log(LogStatus.FAIL,"Error****** Account ["+acct+"] not found on choose account");
				throw new Exception("Error****** Account ["+acct+"] not found on choose account");
			}
			*/
			
			/*
			MobileElement element = (MobileElement) Engine.iosd.findElement(By.xpath("//XCUIElementTypeOther[contains(@name,'Choose an Account')]//XCUIElementTypeScrollView"));
			String parentID = element.getId();
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("element", parentID);
			scrollObject.put("name", acct);
			Engine.iosd.executeScript("mobile:scroll", scrollObject);*/
			
			if (! Verify.objExists(me)) {
				
				try {
					JavascriptExecutor js = (JavascriptExecutor) Engine.iosd; 
					HashMap<String, String> scrollObject = new HashMap(); 
					scrollObject.put("direction", "up"); 
					scrollObject.put("xpath", xpath); 
					js.executeScript("mobile: swipe", scrollObject);
					Thread.sleep(500);	
				}
				catch (Exception e) {
					Commentary.log(LogStatus.FAIL, e.getMessage());
				}
					
			}
			
			
			me.click();
			
		}
		
		
		
		
		// selecting the account will close the choose account screen
		// verify if the account got selected or not
		if (Verify.objExists(this.closeChooseAccount)) {
			this.closeChooseAccount.click();
			Thread.sleep(500);
		}
			//Commentary.log(LogStatus.FAIL,"Error****** Transaction Detail > Choose Account screen > tapping on Account ["+acct+"] did not close Choose Account screen");
		
		
	}
	
	/*
	public void selectPayee_android (String payees) throws Exception {
		
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
	}*/
	
	public void selectPayee (String payees) throws Exception{
		
		Helper h = new Helper();
		
		if (h.getEngine().equals("android"))
			this.selectPayee_android(payees);
		else
			this.selectPayee_ios(payees);
		
	}
	
	
	
	public void selectCategory (String category) throws Exception {
		
		Helper h = new Helper();
		
		if (h.getEngine().equals("android"))
			this.selectCategory_android(category);
		else
			this.selectCategory_ios(category);
		
		
	}
	
	public void selectCategory_android (String category) throws Exception {
		
		String sXpath = "//android.widget.TextView[@text='"+category+"']";
		
		this.category.click();
		Thread.sleep(1000);
		Engine.ad.getContext();
		
		if (! Verify.objExists(this.closeCategory))
			Commentary.log(LogStatus.FAIL,"Error****** Transaction Detail > tapping on payee, did not open Payee selection screen");
		
		this.searchCategory(category);
		
		Engine.ad.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ category + "\").instance(0))"));
		
		Thread.sleep(1000);
		Engine.ad.findElement(By.xpath(sXpath)).click();
		Thread.sleep(500);	
		
		if (Verify.objExists(this.closeCategory)) {
			this.closeCategory.click();
			Thread.sleep(1000);
		}
		
		System.out.println(Engine.ad.getContext());
	}
	
	public void selectCategory_ios (String category) throws Exception {
		
		String sXpath = "//XCUIElementTypeOther[@name='"+category+"']";
		
		this.searchCategory(category);
		Engine.iosd.findElement(By.xpath(sXpath)).click();
		Thread.sleep(500);	
		
		if (Verify.objExists(this.closeCategory)) {
			this.closeCategory.click();
			Thread.sleep(500);
		}
	}
	
	public void searchCategory (String category) throws Exception{
		
		if (! Verify.objExists(this.closeCategory)) {
			this.category.click();
			Thread.sleep(500);
		}
		
		this.searchCategoryTextField.click();
		this.searchCategoryTextField.sendKeys(category);
		
		Helper h = new Helper();
		h.hideKeyBoard();
		
		
	}
	
	public void selectTags (String[] sTag) throws Exception {
		
		Helper h = new Helper();
		
		if (h.getEngine().equals("android"))
			this.selectTags_android(sTag);
		else
			this.selectTags_ios(sTag);
		
		
	}
	
	public void selectTags_android (String[] sTag) throws Exception {
		
		//String sXpath;// = "//android.widget.TextView[@text='"+category+"']";
		Integer iCount;
		
		this.tags.click();
		Thread.sleep(1000);
		System.out.println(Engine.ad.getContext());
		
		for (iCount=0; iCount<sTag.length; iCount++) {
			
			String sXpath = "//android.widget.TextView[@text='"+sTag[iCount]+"']";
			System.out.println(Engine.ad.getContext());
			
			Engine.ad.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ sTag[iCount] + "\").instance(0))"));
			Thread.sleep(1000);
			Engine.ad.findElement(By.xpath(sXpath)).click();
			Thread.sleep(1000);	
			System.out.println(Engine.ad.getContext());
		}
		
		
		this.closeTags.click();
		Thread.sleep(1000);
		System.out.println(Engine.ad.getContext());	
	
	}
	
	public void selectTags_ios (String[] sTag) throws Exception {
		
		//String sXpath;// = "//android.widget.TextView[@text='"+category+"']";
		Integer iCount;
		
		this.tags.click();
		Thread.sleep(500);
		Engine.iosd.getContext();
		
		for (iCount=0; iCount<sTag.length; iCount++) {
			
			String sXpath = "//XCUIElementTypeOther[@name='"+sTag[iCount]+"']";
			Engine.iosd.findElement(By.xpath(sXpath)).click();
			Thread.sleep(500);	
			Engine.iosd.getContext();
		}
		
		
		this.closeTags.click();
		Thread.sleep(1000);	
	
	}
	
	public void enterNotes (String sNote) throws Exception {
		
		Helper h = new Helper();
		
		if (h.getEngine().equals("android"))
			this.enterNotes_android(sNote);
		else
			this.enterNotes_ios(sNote);
		
		
	}
	
	public void enterNotes_android (String sNote) throws Exception {
		
		// scroll into notes
		String sText = "Note";
		Engine.ad.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ sText + "\").instance(0))"));
		Thread.sleep(1000);
		this.note.click();
		this.note.clear();
		this.note.sendKeys(sNote);
		this.buttonDone_OSKeyBoard.click();
		
	}
	
	public void enterNotes_ios (String sNote) throws Exception {
		
		// scroll into notes
		String sXpath="//*[@name='Delete Transaction']";
		MobileElement me = (MobileElement) Engine.iosd.findElement(By.xpath(sXpath));
		String me_id = me.getId();
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("element", me_id);
		scrollObject.put("name", "label == 'Delete Transaction'");
		Engine.iosd.executeScript("mobile:scroll", scrollObject);  // scroll to the target element
		Thread.sleep(1000);	
		this.note.click();
		this.note.clear();
		this.note.sendKeys(sNote);
		this.buttonDone_OSKeyBoard.click();
		
	}
	
		public void enterCheckNumber (String sCheck) {
		
			this.addCheckNumber.click();
			this.addCheckNumber.sendKeys(sCheck);
			this.buttonDone_OSKeyBoard.click();
		
		}
	
		public void createPayee (String sPayee) throws Exception {
		
			String sTemp = "\""+sPayee+"\"";
			String xpath;
		
			this.searchPayee.sendKeys(sPayee);
			Helper h = new Helper();
			h.hideKeyBoard();
			
			if (h.getEngine().equals("android")) {
				xpath="//android.widget.TextView[@text='Create "+sTemp+"']";
				if (! Verify.objExists((MobileElement)Engine.ad.findElement(By.xpath(xpath)))) {
					Commentary.log(LogStatus.FAIL,"Error****** Failed to create Payee "+sPayee);
				
				}
				else
				{
					Commentary.log(LogStatus.INFO,"Creating Payee .. "+sPayee);
					Engine.ad.findElement(By.xpath(xpath)).click();
				}
				
			}
			else {
				
				xpath="//XCUIElementTypeOther[@name='Create "+sTemp+"']";
				if (! Verify.objExists((MobileElement)Engine.iosd.findElement(By.xpath(xpath)))) {
					Commentary.log(LogStatus.FAIL,"Error****** Failed to create Payee "+sPayee);
				
				}
				else
				{
					Commentary.log(LogStatus.INFO,"Creating Payee .. "+sPayee);
					Engine.iosd.findElement(By.xpath(xpath)).click();
				}
				
			}
		
			
			
	}
		
		public void selectPayee_android (String payees) throws Exception {
			
			String sXpath = "//android.widget.TextView[@text='"+payees+"']";
			String sTemp = "\""+payees+"\"";
			String createPayee_xpath="//android.widget.TextView[@text='Create "+sTemp+"']";
			
			this.payee.click();
			Thread.sleep(1000);
			System.out.println(Engine.ad.getContext());
			
			if (! Verify.objExists(this.closePayee))
				Commentary.log(LogStatus.FAIL,"Error****** Transaction Detail > tapping on payee, did not open Payee selection screen");
			
			// search payee
			this.searchPayee.sendKeys(payees);
			Helper h = new Helper();
			h.hideKeyBoard();
			Thread.sleep(1000);
			
			try {
				
				//Verify.objExists((MobileElement)Engine.ad.findElement(By.xpath(createPayee_xpath)));
				Engine.ad.findElement(By.xpath(createPayee_xpath)).click();
				Commentary.log(LogStatus.INFO,"Creating Payee .. "+payees);
				
			}
			catch (NoSuchElementException e){
				
				Engine.ad.findElement(By.xpath(sXpath)).click();
				Commentary.log(LogStatus.INFO,"Selecting Payee .. "+payees);
				
				
			}
			
			Thread.sleep(1000);	
			
			/*
			Engine.ad.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ payees + "\").instance(0))"));
			Thread.sleep(1000);
			Engine.ad.findElement(By.xpath(sXpath)).click();
			Thread.sleep(1000);	*/
			
		}
		
		public void selectPayee_ios (String payees) throws Exception {
			
			String sXpath = "//XCUIElementTypeScrollView//XCUIElementTypeOther[@name='"+payees+"']";
			String sTemp = "\""+payees+"\"";
			String createPayee_xpath="//XCUIElementTypeOther[@name='Create "+sTemp+"']";
			
			this.payee.click();
			Thread.sleep(1000);
			System.out.println(Engine.iosd.getContext());
			
			if (! Verify.objExists(this.closePayee))
				Commentary.log(LogStatus.FAIL,"Error****** Transaction Detail > tapping on payee, did not open Payee selection screen");
			
			// search payee
			this.searchPayee.sendKeys(payees);
			Helper h = new Helper();
			h.hideKeyBoard();
			Thread.sleep(1000);
			
			try {
				
				//Verify.objExists((MobileElement)Engine.ad.findElement(By.xpath(createPayee_xpath)));
				Engine.iosd.findElement(By.xpath(createPayee_xpath)).click();
				Commentary.log(LogStatus.INFO,"Creating Payee .. "+payees);
				
			}
			catch (NoSuchElementException e){
				
				Engine.iosd.findElement(By.xpath(sXpath)).click();
				Commentary.log(LogStatus.INFO,"Selecting Payee .. "+payees);
				
				
			}
			
			Thread.sleep(1000);
			
		}
		
		
		public void verifyTransactionDetails(TransactionRecord tr) throws Exception {
			
			if (Verify.objExists(this.allowButton)) {
				this.allowButton.click();
				Thread.sleep(1000);
			}
			
			String sTemp;
			
			
				
				
				
				
				
				if (tr.getAmount() != null) {
					
					if (tr.getTransactionType().equals("expense"))
						sTemp= "-$"+tr.getAmount();
					else
						sTemp = "$"+tr.getAmount();
					
					
					
					
		
					
				}
					this.enterAmount(tr.getAmount());
				
				if (tr.getTransactionType() != null)
					this.enterTransactionType(tr.getTransactionType());
				
				if (tr.getDate() != null)
					this.enterDate(tr.getDate());
				
				if (tr.getAccount() != null)
					this.selectAccount(tr.getAccount());
				
				if (tr.getPayee() != null)
					this.selectPayee(tr.getPayee());
				
				if (tr.getCategory() != null)
					this.selectCategory(tr.getCategory());
				
				if (tr.getTags() != null)
					this.selectTags(tr.getTags());
				
				if (tr.getNote() != null)
					this.enterNotes(tr.getNote());
				
				this.buttonSave.click();
				
				Thread.sleep(3000);
	
		}
		
		
 public String getTransactionAmount () {
	 
	 Helper h = new Helper();
	
		
	if (h.getEngine().equals("android")) {
		
		String xpath = "//android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[@index='3']//android.widget.TextView";
		
		return Engine.ad.findElement(By.xpath(xpath)).getText();
		
	}
	else
		return this.amount.getText();
	
	
}
 
 public String getTransactionDate() {
	 
	 return this.selectedDate.getText();
	 
 }
 
 public String getTransactionAccount() {
	 
	 return this.selectedAccount.getText();
 }
 
 public String getTransactionPayee() {
	 
	 return this.selectedpayee.getText();
 }
 
 public String getTransactionCategory() {
	 
	 return this.selectedCategory.getText();
 }
 
 public String getTransactionCheckNumber() {
	 return this.enteredCheckNumber.getText();
 }
 
 public String getTransactionTags() throws Exception {
	 
	 Helper h = new Helper();
		
		
	if (h.getEngine().equals("android")) 
		Engine.ad.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ "Tags" + "\").instance(0))"));
	else {
		
		
		
		// scroll into tags
		String sXpath="//XCUIElementTypeScrollView";
		MobileElement me = (MobileElement) Engine.iosd.findElement(By.xpath(sXpath));
		String me_id = me.getId();
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("element", me_id);
		scrollObject.put("predicateString", "name == 'Tags'");
		Engine.iosd.executeScript("mobile:scroll", scrollObject);  // scroll to the target element
		Thread.sleep(1000);	
		
		}
		
		
	
	 
	 
	 
	 return this.selectedTags.getText();
 }
 
 public String getTransactionNotes() throws Exception {
	 
	 
	 Helper h = new Helper();
		
		
	if (h.getEngine().equals("android")) 
		Engine.ad.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ "Delete Transaction" + "\").instance(0))"));
	else {
		// scroll into notes
		String sXpath="//XCUIElementTypeScrollView";
		MobileElement me = (MobileElement) Engine.iosd.findElement(By.xpath(sXpath));
		String me_id = me.getId();
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("element", me_id);
		scrollObject.put("direction", "down");
		Engine.iosd.executeScript("mobile:scroll", scrollObject);
		//scrollObject.put("predicateString", "name == 'Delete Transaction'");
		//Engine.iosd.executeScript("mobile:scroll", scrollObject);  // scroll to the target element
		Thread.sleep(3000);	
	}
		
	 return this.enteredNote.getText();
	 
 }
 
 
 	public boolean VerifyTransactionAmount (String amount, String transactionType) {
 		
 		String sTemp;
			
			if (transactionType.equals("expense"))
				amount= "-$"+amount;
			else
				amount = "$"+amount;
			
			sTemp = this.getTransactionAmount();
			
			if (sTemp.equals(amount)) {
				Commentary.log(LogStatus.INFO, "Amount verified successfully ["+amount+"]");
				return true;
			}
				
			Commentary.log(LogStatus.INFO, "Amount verification failed. Expected ["+amount+"], Actual ["+sTemp+"]");
			return false;
 	}
 	
 	public boolean VerifyTransactionDate (String mddyyyy) {
 		
 		String sExpected, sActual;
 		String[] a = mddyyyy.split("/");
		
		String sMonth = new DateFormatSymbols().getShortMonths()[Integer.parseInt(a[0])-1].toString();
		String sDate = a[1];
		String sYear = a[2];
		
		sYear = sYear.substring(sYear.length()-2);
		sExpected = sMonth+"/"+sDate+"/"+sYear;
		
 		
		sActual = this.getTransactionDate();
		
		if (sActual.equals(sExpected)) {
			Commentary.log(LogStatus.INFO, "Date verified successfully ["+sExpected+"]");
			return true;
		}
			
		Commentary.log(LogStatus.INFO, "Date verification failed. Expected ["+sExpected+"], Actual ["+sActual+"]");
 		
 		
 		return false;
 	}
 	
 	public boolean VerifyTransactionAccount (String acct) {
 		
 		String sActual;
 		
		
 		
		sActual = this.getTransactionAccount();
		
		if (sActual.equals(acct)) {
			Commentary.log(LogStatus.INFO, "Account verified successfully ["+acct+"]");
			return true;
		}
			
		Commentary.log(LogStatus.INFO, "Account verification failed. Expected ["+acct+"], Actual ["+sActual+"]");
 		
 		
 		return false;
 	}
 	
 	public boolean VerifyTransactionPayee (String payee) {
 		
 		String sActual;
 		
		
 		
		sActual = this.getTransactionPayee();
		
		if (sActual.equals(payee)) {
			Commentary.log(LogStatus.INFO, "Payee verified successfully ["+payee+"]");
			return true;
		}
			
		Commentary.log(LogStatus.INFO, "Payee verification failed. Expected ["+payee+"], Actual ["+sActual+"]");
 		
 		
 		return false;
 	}
 	
 	public boolean VerifyTransactionCheckNumber (String checkNumber) {
 		
 		String sActual;
 		
		
 		
		sActual = this.getTransactionCheckNumber();
		
		if (sActual.equals(checkNumber)) {
			Commentary.log(LogStatus.INFO, "checkNumber verified successfully ["+checkNumber+"]");
			return true;
		}
			
		Commentary.log(LogStatus.INFO, "checkNumber verification failed. Expected ["+checkNumber+"], Actual ["+sActual+"]");
 		
 		
 		return false;
 	}
 	
 	public boolean VerifyTransactionCategory (String sCategory) {
 		
 		String sActual;
 		
		
 		
		sActual = this.getTransactionCheckNumber();
		
		if (sActual.equals(sCategory)) {
			Commentary.log(LogStatus.INFO, "checkNumber verified successfully ["+sCategory+"]");
			return true;
		}
			
		Commentary.log(LogStatus.INFO, "checkNumber verification failed. Expected ["+sCategory+"], Actual ["+sActual+"]");
 		
 		
 		return false;
 	}
 
 	public boolean VerifyTransactionTags (String[] tags) throws Exception {
 		
 		String sActual, sExpected="";
 		Integer iCount;
 		
 		for (iCount=0; iCount <= tags.length; iCount++) {
 			sExpected =sExpected+tags[iCount];
 			if (iCount != tags.length-1)
 				sExpected = sExpected+", ";
 			
 		}
 			
 		sActual = this.getTransactionTags();
			
		if (sActual.equals(sExpected)) {
			Commentary.log(LogStatus.INFO, "checkNumber verified successfully ["+sExpected+"]");
			return true;
		}
			
		Commentary.log(LogStatus.INFO, "checkNumber verification failed. Expected ["+sExpected+"], Actual ["+sActual+"]");
 		
 		
 		return false;
 	}
 	
 	public boolean VerifyTransactionNotes (String sNotes) throws Exception {
 		
 		String sActual;
 		
		
 		
		sActual = this.getTransactionNotes();
		
		if (sActual.equals(sNotes)) {
			Commentary.log(LogStatus.INFO, "checkNumber verified successfully ["+sNotes+"]");
			return true;
		}
			
		Commentary.log(LogStatus.INFO, "checkNumber verification failed. Expected ["+sNotes+"], Actual ["+sActual+"]");
 		
 		
 		return false;
 	}
 
		
}
