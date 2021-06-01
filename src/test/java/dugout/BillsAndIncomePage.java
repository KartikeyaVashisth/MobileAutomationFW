package dugout;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

import java.text.DateFormatSymbols;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import referee.Commentary;
import referee.Verify;
import support.Engine;
import support.Helper;
import support.TransactionRecord;

public class BillsAndIncomePage {

	public BillsAndIncomePage() {
			try {
				
				PageFactory.initElements(new AppiumFieldDecorator(Engine.getDriver()),this);
			} catch (Exception e) {
			
				e.printStackTrace();
			}
	}

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name ='Bills & Income'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Bills & Income']")
	public MobileElement billsAndIncomeHeaderText;

	@iOSXCUITFindBy(iOSNsPredicate="type = 'XCUIElementTypeButton'")
	@AndroidFindBy(xpath="//android.widget.ImageButton")
	public MobileElement backButton;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='You don't have any transactions.'`]")
	@AndroidFindBy(xpath="//*[@text='You don't have any transactions.']")
	public MobileElement youDontHaveAnyTxns;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='addTransactionButton'`]")
	@AndroidFindBy(xpath="//*[@content-desc='addTransactionButton']")
	public MobileElement addNewReminderButton;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name ='Bill reminder'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Bill reminder']")
	public MobileElement addNewBillReminder;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name ='Income reminder'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Income reminder']")
	public MobileElement addNewIncomeReminder;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name ='Transfer reminder'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Transfer reminder']")
	public MobileElement addNewTransferReminder;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='cancelBtn'`]")
	@AndroidFindBy(xpath="//*[@content-desc='cancelBtn']")
	public MobileElement cancelBtn;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='okBtn'`]")
	@AndroidFindBy(xpath="//*[@text='Next']")
	public MobileElement nextBtn;

	@iOSXCUITFindBy(iOSClassChain="**/*[`name=='Always Allow'`]")
	@AndroidFindBy(xpath="//android.widget.Button[@text='ALLOW']")
	public MobileElement allowButton;

	// -------------- Reminders Tab --------------

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name ='Reminders'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Reminders']")
	public MobileElement remindersTab;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name = 'Next 7 Days'`]")
	@AndroidFindBy(xpath="//*[@text='Next 7 Days']")
	public MobileElement next7Days;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name = 'Next 14 Days'`]")
	@AndroidFindBy(xpath="//*[@text='Next 14 Days']")
	public MobileElement next14Days;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name = 'Next 30 Days'`]")
	@AndroidFindBy(xpath="//*[@text='Next 30 Days']")
	public MobileElement next30Days;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name = 'Next 90 Days'`]")
	@AndroidFindBy(xpath="//*[@text='Next 90 Days']")
	public MobileElement next90Days;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name = 'Next 12 Months'`]")
	@AndroidFindBy(xpath="//*[@text='Next 12 Months']")
	public MobileElement next12Months;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`value ='THIS MONTH'`]")
	@AndroidFindBy(xpath="//*[@text='THIS MONTH']")
	public MobileElement thisMonthHeaderText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='noReminderText'`]")
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='noReminderDataImage']/following-sibling::android.widget.TextView")
	public MobileElement noRemindersDueNext7Days;

	// -------------- Series Tab --------------

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name ='Series'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Series']")
	public MobileElement seriesTab;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`name='Search Bar'`]")
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Search Series']")
	public MobileElement searchSeriesTxtField;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Search Bar'`]/XCUIElementTypeOther[2]")
	@AndroidFindBy(xpath="//android.widget.EditText[@content-desc='Search Bar']/..//android.widget.ImageView")
	public MobileElement clearSearchSeriesCrossButton;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name ='SERIES'`]")
	@AndroidFindBy(xpath="//*[@text='SERIES']")
	public MobileElement seriesHeaderText;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name contains 'Payee Name'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@content-desc, 'Payee Name')]")
	public MobileElement reminderName;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name contains 'Amount'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@content-desc, 'Amount')]")
	public MobileElement reminderAmount;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name contains 'Transaction Date'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@content-desc, 'Transaction Date')]")
	public MobileElement reminderDate;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name contains 'Transaction Frequency'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@content-desc, 'Transaction Frequency')]")
	public MobileElement reminderFrequency;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='noSeriesText'`]")
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='noSeriesDataImage']/following-sibling::android.widget.TextView")
	public MobileElement youHaveNoScheduledReminders;

	// -------------- Add new Reminder(Bill/Income/Transfer) Series Page --------------

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name BEGINSWITH 'Add'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Bills & Income')]")
	public MobileElement addNewReminderHeaderText;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='Payee Name'`]")
	@AndroidFindBy(xpath="//*[@text='Payee Name']")
	public MobileElement payeeName;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='From'`]")
	@AndroidFindBy(xpath="//*[@text='From']")
	public MobileElement from;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='Description'`]")
	@AndroidFindBy(xpath="//*[@text='Description']")
	public MobileElement description;

	@iOSXCUITFindBy(id="closePayee")
	@AndroidFindBy(xpath="//*[@content-desc='closePayee']/android.widget.ImageView")
	public MobileElement closePayee;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeTextField[`name = 'search payee'`]")
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Search Payee']")
	public MobileElement searchPayee;

	@iOSXCUITFindBy(id="create payee")
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc=\"create payee\"]")
	public MobileElement createPayee;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='Amount'`]")
	@AndroidFindBy(xpath="//*[@text='Amount']")
	public MobileElement amount;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='Done'`][2]")
	@AndroidFindBy(xpath="//*[@text='Done']")
	public MobileElement buttonDone;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='From Account'`]")
	@AndroidFindBy(xpath="//*[@text='From Account']")
	public MobileElement fromAccount;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='To Account'`]")
	@AndroidFindBy(xpath="//*[@text='To Account']")
	public MobileElement toAccount;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='Due Next On'`]")
	@AndroidFindBy(xpath="//*[@text='Due Next On']")
	public MobileElement dueNextOn;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeDatePicker")
	@AndroidFindBy(xpath="//android.widget.DatePicker")
	public MobileElement datePicker;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypePickerWheel[1]")
	@AndroidFindBy(xpath="//android.widget.DatePicker//android.widget.NumberPicker[@index=0]/android.widget.EditText")
	public MobileElement enterMonth;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypePickerWheel[2]")
	@AndroidFindBy(xpath="//android.widget.DatePicker//android.widget.NumberPicker[@index=1]/android.widget.EditText")
	public MobileElement enterDate;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypePickerWheel[3]")
	@AndroidFindBy(xpath="//android.widget.DatePicker//android.widget.NumberPicker[@index=2]/android.widget.EditText")
	public MobileElement enterYear;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name='Cancel'`]")
	@AndroidFindBy(xpath="//*[@text='CANCEL']")
	public MobileElement buttonCancel;

	@iOSXCUITFindBy(iOSClassChain="**/*[`name=='OK'`]")
	@AndroidFindBy(xpath="//*[@text='OK']")
	public MobileElement buttonOK;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name='okBtn'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc='okBtn']")
	public MobileElement okBtn;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='Frequency'`]")
	@AndroidFindBy(xpath="//*[@text='Frequency']")
	public MobileElement frequency;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='Every'`]")
	@AndroidFindBy(xpath="//*[@text='Every']")
	public MobileElement every;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='Every 2 weeks on'`]")
	@AndroidFindBy(xpath="//*[@text='Every 2 weeks on']")
	public MobileElement everyTwoWeeksOn;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='First On'`]")
	@AndroidFindBy(xpath="//*[@text='First On']")
	public MobileElement firstOn;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='Second On'`]")
	@AndroidFindBy(xpath="//*[@text='Second On']")
	public MobileElement secondOn;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='Quarter'`]")
	@AndroidFindBy(xpath="//*[@text='Quarter']")
	public MobileElement quarter;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='Date'`]")
	@AndroidFindBy(xpath="//*[@text='Date']")
	public MobileElement date;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='Dates'`]")
	@AndroidFindBy(xpath="//*[@text='Dates']")
	public MobileElement dates;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='Day'`]")
	@AndroidFindBy(xpath="//*[@text='Day']")
	public MobileElement day;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='End Date'`]")
	@AndroidFindBy(xpath="//*[@text='End Date']")
	public MobileElement endDate;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name='End on (date)'`]/XCUIElementTypeStaticText[`name='End on (date)'`]")
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='closeEnd on (date)']/../android.widget.TextView[@text='End on (date)']")
	public MobileElement endOnDrawerText;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Radio'`]/XCUIElementTypeStaticText[1]")
	@AndroidFindBy(xpath="(//android.widget.ScrollView//android.widget.TextView)[1]")
	public MobileElement noEndDateOption;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Radio'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="(//android.widget.ScrollView//android.widget.TextView)[2]")
	public MobileElement EndOnDateOption;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Radio'`]/XCUIElementTypeStaticText[3]")
	@AndroidFindBy(xpath="(//android.widget.ScrollView//android.widget.TextView)[3]")
	public MobileElement endAfterNumberOfRemindersOption;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='Last Reminder Date'`]")
	@AndroidFindBy(xpath="//*[@text='Last Reminder Date']")
	public MobileElement lastReminderDate;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='End Reminders After'`]")
	@AndroidFindBy(xpath="//*[@text='End Reminders After']")
	public MobileElement endRemindersAfter;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='Category'`]")
	@AndroidFindBy(xpath="//*[@text='Category']")
	public MobileElement category;

	@iOSXCUITFindBy(id="closeCategories")
	@AndroidFindBy(xpath="//*[@content-desc='closeCategories']/android.widget.ImageView")
	public MobileElement closeCategory;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`name=='search category'`]")
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Search Category']")
	public MobileElement searchCategoryTextField;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='Tags'`]")
	@AndroidFindBy(xpath="//*[@text='Tags']")
	public MobileElement tags;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'closeTags'`]/**/XCUIElementTypeStaticText[`name='Tags'`]")
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='closeTags']/../android.widget.TextView[@text='Tags']")
	public MobileElement tagsDrawerText;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeTextField[`name = 'search tags'`]")
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Search Tags']")
	public MobileElement searchTags;

	@iOSXCUITFindBy(id="create tag")
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc=\"create tag\"]")
	public MobileElement createTag;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name = 'Apply'`][-1]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Apply']")
	public MobileElement tagsApplyButton;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='closeTags'`]")
	@AndroidFindBy(xpath="//*[@content-desc='closeTags']")
	public MobileElement closeTags;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='Note'`]")
	@AndroidFindBy(xpath="//*[@text='Note']")
	public MobileElement note;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeTextView[`name='addNotes Note'`]")
	@AndroidFindBy(xpath="//android.widget.EditText[@content-desc='addNotes']")
	public MobileElement editNotes;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name='Frequency'`]/XCUIElementTypeStaticText[`name='Frequency'`]")
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='closeFrequency']/../android.widget.TextView[@text='Frequency']")
	public MobileElement frequencyDrawerText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='closeFrequency'`]")
	@AndroidFindBy(xpath="//*[@content-desc='closeFrequency']")
	public MobileElement closeFrequency;

	//	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Radio'`]/XCUIElementTypeStaticText[1]")
	//	@AndroidFindBy(xpath="(//android.widget.ScrollView//android.widget.TextView)[1]")
	//	public MobileElement weeklyFrequency;
	//	
	//	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Radio'`]/XCUIElementTypeStaticText[2]")
	//	@AndroidFindBy(xpath="(//android.widget.ScrollView//android.widget.TextView)[2]")
	//	public MobileElement biweeklyFrequency;
	//	
	//	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Radio'`]/XCUIElementTypeStaticText[3]")
	//	@AndroidFindBy(xpath="(//android.widget.ScrollView//android.widget.TextView)[3]")
	//	public MobileElement twiceAMonthFrequency;
	//	
	//	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Radio'`]/XCUIElementTypeStaticText[4]")
	//	@AndroidFindBy(xpath="(//android.widget.ScrollView//android.widget.TextView)[4]")
	//	public MobileElement monthlyFrequency;
	//	
	//	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Radio'`]/XCUIElementTypeStaticText[5]")
	//	@AndroidFindBy(xpath="(//android.widget.ScrollView//android.widget.TextView)[5]")
	//	public MobileElement quarterlyFrequency;
	//	
	//	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Radio'`]/XCUIElementTypeStaticText[6]")
	//	@AndroidFindBy(xpath="(//android.widget.ScrollView//android.widget.TextView)[6]")
	//	public MobileElement yearlyFrequency;
	//	
	//	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Radio'`]/XCUIElementTypeStaticText[7]")
	//	@AndroidFindBy(xpath="(//android.widget.ScrollView//android.widget.TextView)[7]")
	//	public MobileElement twiceAYearFrequency;
	//	
	//	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Radio'`]/XCUIElementTypeStaticText[8]")
	//	@AndroidFindBy(xpath="(//android.widget.ScrollView//android.widget.TextView)[8]")
	//	public MobileElement onlyOnceFrequency;
	//	
	//	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Radio'`]/XCUIElementTypeStaticText[9]")
	//	@AndroidFindBy(xpath="(//android.widget.ScrollView//android.widget.TextView)[9]")
	//	public MobileElement toPayEstimatedTaxesFrequency;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name='button Add Reminder'`]")
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='button Add Reminder']")
	public MobileElement buttonAddReminder;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='Delete Series'`]")
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='Delete Series']")
	public MobileElement deleteSeries;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name = 'Are you sure you want to delete this reminder series?'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Are you sure you want to delete this reminder series?']")
	public MobileElement deleteReminderSeriesWarningMessage;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name BEGINSWITH 'Are you sure you want to discard all changes?'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Are you sure you want to discard all changes?']")
	public MobileElement abortReminderSeriesWarningMessage;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeButton[`name='Delete'`]")
	@AndroidFindBy(id="android:id/button1")
	public MobileElement deleteReminderSeriesAlertButton;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeButton[`name='Cancel'`]")
	@AndroidFindBy(id="android:id/button2")
	public MobileElement cancelAlertButton;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeButton[1]")
	@AndroidFindBy(xpath="//android.widget.ImageButton")
	public MobileElement backButtonOfEditSeries;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Edit Series'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Edit Series']")
	public MobileElement editSeriesHeaderText;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeButton[`name='save'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='SAVE']")
	public MobileElement saveOptionEditSeries;

	// -------------- View Series Page --------------

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='View Series'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='View Series']")
	public MobileElement viewSeriesHeaderText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name='Bills & Income'`]")
	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='Navigate Up']")
	public MobileElement backButtonOfViewSeriesPage;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name='edit'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='EDIT']")
	public MobileElement editButton;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains '$'`]/XCUIElementTypeStaticText[1]")
	@AndroidFindBy(xpath="//android.widget.ScrollView//android.widget.TextView[@index='0']")
	public MobileElement reminderSeriesNameOnViewSeries;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains '$'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.ScrollView//android.widget.TextView[contains(@text, '$')]")
	public MobileElement reminderSeriesAmountOnViewSeries;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Next'`]/XCUIElementTypeStaticText[1]")
	@AndroidFindBy(xpath="//android.widget.ScrollView//android.widget.TextView[contains(@text, 'Next')]")
	public MobileElement nextReminderDateOnViewSeries;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Next'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.ScrollView//android.widget.TextView[@index='3']")
	public MobileElement reminderSeriesFrequencyOnViewSeries;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Type'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//*[@text='Type']/following-sibling::android.widget.TextView[1]")
	public MobileElement reminderTypeViewSeriesPage;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Category'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//*[@text='Category']/following-sibling::android.widget.TextView[1]")
	public MobileElement categoryTypeViewSeriesPage;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'End Date'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//*[@text='End Date']/following-sibling::android.widget.TextView[1]")
	public MobileElement endDateViewSeriesPage;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'End After'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//*[@text='End After']/following-sibling::android.widget.TextView[1]")
	public MobileElement endAfterViewSeriesPage;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'From Account'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//*[@text='From Account']/following-sibling::android.widget.TextView[1]")
	public MobileElement fromAccountViewSeriesPage;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'To Account'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//*[@text='To Account']/following-sibling::android.widget.TextView[1]")
	public MobileElement toAccountViewSeriesPage;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Tags'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//*[@text='Tags']/following-sibling::android.widget.TextView[1]")
	public MobileElement tagsViewSeriesPage;

	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name contains 'Notes'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//*[@text='Notes']/following-sibling::android.widget.TextView[1]")
	public MobileElement notesViewSeriesPage;

	// ------------------ Enter Transaction Page & More Actions Option ---------------------

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Enter Transaction'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Enter Transaction']")
	public MobileElement enterTransactionHeaderText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Enter'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Enter']")
	public MobileElement enterOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Enter all previous instances'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Enter all previous instances']")
	public MobileElement enterAllPreviousInstancesOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='More Actions'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='More Actions']")
	public MobileElement moreActionsOption;

	@iOSXCUITFindBy(id = "Ignore this instance")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Ignore this instance']")
	public MobileElement ignoreThisInstanceOption;

	@iOSXCUITFindBy(id = "Ignore all previous instances")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Ignore all previous instances']")
	public MobileElement ignoreAllPreviousInstancesOption;

	@iOSXCUITFindBy(id = "Edit this instance")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Edit this instance']")
	public MobileElement editThisInstanceOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Edit Reminder Instance'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Edit Reminder Instance']")
	public MobileElement editReminderInstanceHeaderText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeNavigationBar[`name='Edit Reminder Instance'`]/XCUIElementTypeButton")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Edit Reminder Instance']/../android.widget.ImageButton")
	public MobileElement closeEditReminderInstanceButton;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Your changes will only be applied to this reminder instance. All future reminders will continue to use your original reminder date and amount.'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Your changes will only be applied to this reminder instance. All future reminders will continue to use your original reminder date and amount.']")
	public MobileElement editReminderInstanceInfoText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Payee'`]/XCUIElementTypeStaticText[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Payee']/../android.widget.TextView[2]")
	public MobileElement payeeNameOnEditReminderInstancePage;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='The date you entered for this reminder is later than its next regularly scheduled occurrence. Enter a date that is before the next reminder or ignore this reminder.'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='The date you entered for this reminder is later than its next regularly scheduled occurrence. Enter a date that is before the next reminder or ignore this reminder.']")
	public MobileElement nextRegularlyScheduledOccurrenceInfoPopUp;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Save'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Save']")
	public MobileElement saveOptionOnEditReminderInstancePage;

	@iOSXCUITFindBy(id = "Edit this and future instances")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Edit this and future instances']")
	public MobileElement editThisAndFutureInstancesOption;

	@iOSXCUITFindBy(id = "Delete this and future instances")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Delete this and future instances']")
	public MobileElement deleteThisAndFutureInstancesOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Do you want to delete this and future instances? Previously recorded transactions will not be deleted.'`]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Do you want to delete this and future instances? Previously recorded transactions will not be deleted.']")
	public MobileElement deleteThisAndFutureInstancesInfoPopUp;

	public void addNewReminderSeries(String type) throws Exception {

		Verify.waitForObject(this.addNewReminderButton, 2);
		this.addNewReminderButton.click();

		if(type.equalsIgnoreCase("Bill")) {
			Verify.waitForObject(this.addNewBillReminder, 2);
			this.addNewBillReminder.click();
		}

		if(type.equalsIgnoreCase("Income")) {
			Verify.waitForObject(this.addNewIncomeReminder, 2);
			this.addNewIncomeReminder.click();
		}

		if(type.equalsIgnoreCase("Transfer")) {
			Verify.waitForObject(this.addNewTransferReminder, 2);
			this.addNewTransferReminder.click();
		}

		this.nextBtn.click();
		Thread.sleep(1000);
	}

	public void addNewReminder(TransactionRecord tr) throws Exception {

		SoftAssert sa = new SoftAssert();

		if (Verify.objExists(this.allowButton)) {
			this.allowButton.click();
			Thread.sleep(1000);
		}

		if (Verify.objExists(this.addNewReminderHeaderText) || Verify.objExists(this.buttonAddReminder)) {
			Commentary.log(LogStatus.INFO,"Add New Reminder Screen got displayed.");
			Thread.sleep(2000);

			if (tr.getPayee() != null)
				this.selectPayee(tr.getPayee());

			if (tr.getAmount() != null) 
				this.enterAmount(tr.getAmount());

			if (tr.getFromAccount() != null)
				this.selectFromAccount(tr.getFromAccount());

			if (tr.getToAccount() != null)
				this.selectToAccount(tr.getToAccount());

			if (tr.getDate() != null)
				this.enterDate(tr.getDate());

			if (tr.getFrequency() != null)
				this.selectFrequency(tr.getFrequency());

			if (tr.getEvery() != null)
				this.selectEvery(tr.getEvery());

			if (tr.getDay() != null)
				this.selectDay(tr.getDay());

			if (tr.getEvery2WeeksOn() != null)
				this.selectEvery2WeeksOn(tr.getEvery2WeeksOn());

			if (tr.getFirstOn() != null)
				this.selectFirstOn(tr.getFirstOn());

			if (tr.getSecondOn() != null)
				this.selectSecondOn(tr.getSecondOn());

			if (tr.getYearlyDate() != null)
				this.selectYearlyDate(tr.getYearlyDate());

			if (tr.getFirstOnDate() != null)
				this.selectFirstOnDate(tr.getFirstOnDate());

			if (tr.getSecondOnDate() != null)
				this.selectSecondOnDate(tr.getSecondOnDate());

			if (tr.getEndOnDate() != null)
				this.selectEndOnDate(tr.getEndOnDate());

			if (tr.getEndAfterNumberOfReminders() != null)
				this.selectEndAfterNumberOfReminders(tr.getEndAfterNumberOfReminders());

			if (tr.getCategory() != null)
				this.selectCategory(tr.getCategory());

			if (tr.getTags() != null)
				this.selectTags(tr.getTags());

			if (tr.getNote() != null)
				this.enterNotes(tr.getNote());

			if (Verify.objExists(this.buttonAddReminder)) 
				this.buttonAddReminder.click();

			Thread.sleep(7000);

			OverviewPage op = new OverviewPage();
			Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);

			Commentary.log(LogStatus.INFO,"Added a new reminder series successfully.");
		}
		else
			Commentary.log(sa, LogStatus.FAIL,"Add New Reminder screen did not appear.");

		sa.assertAll();
	}

	public void editReminderSeries (TransactionRecord tr) throws Exception {

		SoftAssert sa = new SoftAssert();

		if (Verify.objExists(this.allowButton)) {
			this.allowButton.click();
			Thread.sleep(1000);
		}

		if (Verify.objExists(this.editSeriesHeaderText)) {
			Commentary.log(LogStatus.INFO,"Edit Reminder Series Screen got displayed.");
			Thread.sleep(2000);

			if (tr.getPayee() != null)
				this.selectPayee(tr.getPayee());

			if (tr.getAmount() != null) 
				this.enterAmount(tr.getAmount());

			if (tr.getFromAccount() != null)
				this.selectFromAccount(tr.getFromAccount());

			if (tr.getToAccount() != null)
				this.selectToAccount(tr.getToAccount());

			if (tr.getDate() != null)
				this.enterDate(tr.getDate());

			if (tr.getFrequency() != null)
				this.selectFrequency(tr.getFrequency());

			if (tr.getEvery() != null)
				this.selectEvery(tr.getEvery());

			if (tr.getDay() != null)
				this.selectDay(tr.getDay());

			if (tr.getEvery2WeeksOn() != null)
				this.selectEvery2WeeksOn(tr.getEvery2WeeksOn());

			if (tr.getFirstOn() != null)
				this.selectFirstOn(tr.getFirstOn());

			if (tr.getSecondOn() != null)
				this.selectSecondOn(tr.getSecondOn());

			if (tr.getYearlyDate() != null)
				this.selectYearlyDate(tr.getYearlyDate());

			if (tr.getFirstOnDate() != null)
				this.selectFirstOnDate(tr.getFirstOnDate());

			if (tr.getSecondOnDate() != null)
				this.selectSecondOnDate(tr.getSecondOnDate());

			if (tr.getEndOnDate() != null)
				this.selectEndOnDate(tr.getEndOnDate());

			if (tr.getEndAfterNumberOfReminders() != null)
				this.selectEndAfterNumberOfReminders(tr.getEndAfterNumberOfReminders());

			if (tr.getCategory() != null)
				this.selectCategory(tr.getCategory());

			if (tr.getTags() != null)
				this.selectTags(tr.getTags());

			if (tr.getNote() != null)
				this.enterNotes(tr.getNote());

			Verify.waitForObject(saveOptionEditSeries, 1);
			if (Verify.objExists(this.saveOptionEditSeries)) 
				this.saveOptionEditSeries.click();

			Thread.sleep(3000);

			OverviewPage op = new OverviewPage();
			Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);

			Commentary.log(LogStatus.INFO,"Edited Reminder series successfully.");
		}
		else
			Commentary.log(sa, LogStatus.FAIL,"Edit Reminder Series screen did not appear.");

		sa.assertAll();
	}

	public void editReminderInstance(TransactionRecord tr) throws Exception {

		SoftAssert sa = new SoftAssert();

		if (Verify.objExists(this.editReminderInstanceHeaderText)) {
			//	Commentary.log(LogStatus.INFO,"Edit Reminder Instance page is displayed.");
			Thread.sleep(2000);

			if (tr.getAmount() != null) 
				this.enterAmount(tr.getAmount());

			if (tr.getDate() != null)
				this.enterDate(tr.getDate());

			Verify.waitForObject(saveOptionOnEditReminderInstancePage, 1);
			if (Verify.objExists(this.saveOptionOnEditReminderInstancePage)) 
				this.saveOptionOnEditReminderInstancePage.click();

			Thread.sleep(3000);

			OverviewPage op = new OverviewPage();
			Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);

			Commentary.log(LogStatus.INFO,"Edited Reminder instance successfully.");
		}
		else
			Commentary.log(sa, LogStatus.FAIL,"Edit Reminder Instance page did not appear.");

		sa.assertAll();
	}

	public void enterAmount(String amount) throws Exception {

		Helper h = new Helper();

		if (h.getEngine().equals("android"))
			enterTransactionAmount_android(amount);
		else
			enterTransactionAmount_ios(amount);			
	}

	public void enterTransactionAmount_android(String amount) throws Exception {

		String[] s = amount.split("");
		int iCount;

		if (Verify.objExists(this.editSeriesHeaderText) || Verify.objExists(this.editReminderInstanceHeaderText)) {
			this.amount.click();
			Thread.sleep(4000);
			for (int i = 1; i < amount.length(); i++) {
				Engine.getDriver().findElement(By.xpath("//android.widget.ImageView[@content-desc='delete']")).click();
			}
			Thread.sleep(1000);
		}
		else {
			this.amount.click();
			Thread.sleep(4000);
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

	public void enterTransactionAmount_ios(String amount) throws Exception {

		String[] s = amount.split("");
		int iCount;

		if (Verify.objExists(this.editSeriesHeaderText) || Verify.objExists(this.editReminderInstanceHeaderText)) {
			this.amount.click();
			Thread.sleep(4000);
			for (int i = 1; i < amount.length(); i++) {
				Engine.getDriver().findElement(MobileBy.iOSClassChain("**/XCUIElementTypeOther[`name='delete'`][2]")).click();
			}
			Thread.sleep(1000);
		}
		else {
			this.amount.click();
			Thread.sleep(4000);
		}

		for(iCount=0; iCount<s.length; iCount++) {

			if (s[iCount].equals(".")) {
				// ignore
			}
			else
				Engine.getDriver().findElement(MobileBy.iOSClassChain("**/XCUIElementTypeStaticText[`name='"+s[iCount]+"'`]")).click();	
		}

		if(Verify.objExists(this.buttonDone))
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
	// Ex: 10/05/2020
	public void enterDate_android(String mddyyyy) throws InterruptedException {

		String[] a = mddyyyy.split("/");

		String month = new DateFormatSymbols().getShortMonths()[Integer.parseInt(a[0])-1].toString();
		String date = a[1];
		String year = a[2];

		if (! Verify.objExists(this.datePicker)) {

			if(Verify.objExists(this.dueNextOn))
				this.dueNextOn.click();
			else
				this.date.click();

			Thread.sleep(1000);
		}

		try {
			Verify.waitForObject(this.enterMonth, 1);
			this.enterMonth.click();
			Thread.sleep(1000);
			this.enterMonth.clear();
			this.enterMonth.sendKeys(month);
			Thread.sleep(1000);

			this.enterDate.click();
			this.enterDate.clear();
			this.enterDate.sendKeys(date);
			Thread.sleep(1000);

			this.enterYear.click();
			this.enterYear.clear();
			this.enterYear.sendKeys(year);
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.buttonOK.click();
		Thread.sleep(1000);	
	}

	public void enterDate_IOS(String mddyyyy) throws Exception {

		String[] a = mddyyyy.split("/");

		String month = new DateFormatSymbols().getMonths()[Integer.parseInt(a[0])-1].toString();
		String date = a[1];
		String year = a[2];

		if (! Verify.objExists(this.datePicker)) {

			if(Verify.objExists(this.dueNextOn))
				this.dueNextOn.click();
			else
				this.date.click();

			Thread.sleep(500);
		}

		int iCount;

		for (iCount=0; iCount < 3; iCount++)
			enterMonth.sendKeys(month);
		Thread.sleep(500);

		for (iCount=0; iCount < 3; iCount++)
			enterDate.sendKeys(date);
		Thread.sleep(500);

		for (iCount=0; iCount < 2; iCount++)
			enterYear.sendKeys(year);

		this.buttonOK.click();
		Thread.sleep(500);	
	}

	public void selectCategory(String category) throws Exception {

		Helper h = new Helper();

		if (h.getEngine().equals("android"))
			this.selectCategory_android(category);
		else
			this.selectCategory_ios(category);	
	}

	public void selectCategory_android (String category) throws Exception {

		String sXpath = "//android.widget.TextView[@text='"+category+"']/../android.view.ViewGroup[@content-desc='RadioButton']";

		if (Verify.objExists_check(this.category)) {
			this.category.click();
		}

		Thread.sleep(1000);

		this.searchCategory(category);

		Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ category + "\").instance(0))"));

		Thread.sleep(1000);
		Engine.getDriver().findElement(By.xpath(sXpath)).click();
		Thread.sleep(1000);	

	}

	public void selectCategory_ios (String category) throws Exception {

		String sXpath = "**/XCUIElementTypeOther[`name='RadioButton "+category+"'`]/XCUIElementTypeOther[`name='RadioButton'`]";

		if (Verify.objExists_check(this.category)) {
			this.category.click();
		}

		Thread.sleep(1000);

		this.searchCategory(category);
		Engine.getDriver().findElement(MobileBy.iOSClassChain(sXpath)).click();
		Thread.sleep(1000);	
	}

	public void searchCategory (String category) throws Exception{

		Verify.waitForObject(searchCategoryTextField, 1);
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

	public void selectTags_android (String sTag) throws Exception {

		tapOnTags();

		Verify.waitForObject(this.searchTags, 1);
		this.searchTags.sendKeys(sTag);
		Helper h = new Helper();
		h.hideKeyBoard();
		Thread.sleep(1000);

		String createTag_xpath="//android.widget.TextView[@content-desc='create tag']";

		if (Verify.objExists(createPayee)) {
			Verify.objExists((MobileElement)Engine.getDriver().findElement(By.xpath(createTag_xpath)));
			Engine.getDriver().findElement(By.xpath(createTag_xpath)).click();
			Commentary.log(LogStatus.INFO,"Creating new Tag.. "+sTag);
		}
		else {
			String cc = "//android.widget.TextView[@text='"+sTag+"']";
			Engine.getDriver().findElement(By.xpath(cc)).click();
			Thread.sleep(500);
			Commentary.log(LogStatus.INFO,"Selected Tag which is already present.. "+sTag);
		}
		Thread.sleep(1000);

		this.tagsApplyButton.click();
		Thread.sleep(2000);	
	}

	public void selectTags_ios (String sTag) throws Exception {

		tapOnTags();

		Verify.waitForObject(this.searchTags, 1);
		this.searchTags.sendKeys(sTag);
		Helper h = new Helper();
		h.hideKeyBoard();
		Thread.sleep(1000);

		String createTag_xpath="**/XCUIElementTypeOther[`name=‘create tag’`]";

		if (Verify.objExists(createTag)) {
			Verify.objExists((MobileElement)Engine.getDriver().findElement(MobileBy.iOSClassChain(createTag_xpath)));
			Engine.getDriver().findElement(MobileBy.iOSClassChain(createTag_xpath)).click();
			Commentary.log(LogStatus.INFO,"Creating new Tag... "+sTag);	
		}
		else {
			String cc = "**/XCUIElementTypeStaticText[`name='"+sTag+"'`]";
			Engine.getDriver().findElement(MobileBy.iOSClassChain(cc)).click();
			Thread.sleep(500);
			Commentary.log(LogStatus.INFO,"Selected Tag which is already present.. "+sTag);
		}
		Thread.sleep(1000);

		this.tagsApplyButton.click();
		Thread.sleep(2000);	
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
		Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ sText + "\").instance(0))"));
		Thread.sleep(1000);
		this.note.click();
		this.note.clear();
		this.editNotes.sendKeys(sNote);
		Thread.sleep(1000);

		Helper h = new Helper();
		h.hideKeyBoard();
	}

	public void enterNotes_ios (String sNote) throws Exception {

		// scroll into notes
		//		String sXpath="//*[@name='Note']";
		//		MobileElement me = (MobileElement) Engine.getDriver().findElement(By.xpath(sXpath));
		//		String me_id = me.getId();
		//		HashMap<String, String> scrollObject = new HashMap<String, String>();
		//		scrollObject.put("element", me_id);
		//		scrollObject.put("name", "label == 'Note'");
		//		Engine.getDriver().executeScript("mobile:scroll", scrollObject);  // scroll to the target element
		//		Thread.sleep(1000);	
		//		this.note.click();
		//		this.note.clear();
		//		this.note.sendKeys(sNote);

		String cc="**/XCUIElementTypeStaticText[`name='Note'`]";
		MobileElement me = (MobileElement) Engine.getDriver().findElement(MobileBy.iOSClassChain(cc));
		String me_id = me.getId();
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("element", me_id);
		scrollObject.put("toVisible", "not an empty string");
		Engine.getDriver().executeScript("mobile:scroll", scrollObject);  // scroll to the target element
		Thread.sleep(1000);
		Engine.getDriver().findElement((MobileBy.iOSClassChain(cc))).click();
		Thread.sleep(1000);
		this.editNotes.sendKeys(sNote);
		Thread.sleep(1000);
		this.tags.click();
		Thread.sleep(1000);
	}

	public void selectPayee(String payees) throws Exception{

		Helper h = new Helper();

		if (h.getEngine().equals("android"))
			this.selectPayeeName_android(payees);
		else
			this.selectPayeeName_ios(payees);
	}

	public void selectPayeeName_android (String payees) throws Exception {

		String sXpath = "//android.widget.TextView[@text='"+payees+"']";
		String createPayee_xpath="//android.widget.TextView[@content-desc='create payee']";
		String allPayees_Xpath = "//android.widget.ScrollView/android.view.ViewGroup//android.widget.TextView[@text=\"All Payees\"]/../../../android.view.ViewGroup//android.widget.TextView[@text='"+payees+"']";

		if(Verify.objExists(this.payeeName))
			this.payeeName.click();

		if(Verify.objExists(this.from))
			this.from.click();

		if(Verify.objExists(this.description))
			this.description.click();

		Thread.sleep(1000);

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
				Engine.getDriver().findElement(By.xpath(sXpath)).click();
				Commentary.log(LogStatus.INFO,"Selecting Payee.. "+payees);
			}
		}
		else {
			Engine.getDriver().findElement(By.xpath(allPayees_Xpath)).click();
			Thread.sleep(500);
			Commentary.log(LogStatus.INFO,"Selected Payee from All Payees List.. "+payees);
		}
		Thread.sleep(1000);		
	}

	public void selectPayeeName_ios (String payees) throws Exception {

		String sXpath = "**/XCUIElementTypeScrollView/XCUIElementTypeOther[`name CONTAINS '"+payees+"'`]";
		String createPayee_xpath="**/XCUIElementTypeOther[`name='create payee'`]";

		if(Verify.objExists(this.payeeName))
			this.payeeName.click();

		if(Verify.objExists(this.from))
			this.from.click();

		if(Verify.objExists(this.description))
			this.description.click();

		Thread.sleep(1000);
		
		Verify.waitForObject(this.searchPayee, 1);
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
				Engine.getDriver().findElement(MobileBy.iOSClassChain(sXpath)).click();
				Commentary.log(LogStatus.INFO,"Selecting Payee .. "+payees);		
			}
		}
		else {
			//String cc = "**/XCUIElementTypeOther[$name='All Payees'$]/XCUIElementTypeOther[`name=='RadioButton "+payees+"'`]";
			String cc = "**/XCUIElementTypeStaticText[`name='"+payees+"'`]";
			Engine.getDriver().findElement(MobileBy.iOSClassChain(cc)).click();
			Thread.sleep(500);
			Commentary.log(LogStatus.INFO,"Selected Payee from All Payees List.. "+payees);
		}
		Thread.sleep(1000);	
	}

	public void selectFromAccount (String acct) throws Exception {

		Helper h = new Helper();
		Thread.sleep(1000);

		this.fromAccount.click();
		Thread.sleep(2500);

		if (h.getEngine().equals("android")) {
			String xpath = "//android.widget.TextView[@text='"+acct+"']";
			Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ acct + "\").instance(0))"));
			Thread.sleep(1000);

			if (! Verify.objExists((MobileElement)Engine.getDriver().findElement(By.xpath(xpath)))) {
				Commentary.log(LogStatus.FAIL,"Error****** Account ["+acct+"] is not found on Account drawer.");
				throw new Exception("Error****** Account ["+acct+"] not found on Account drawer.");
			}

			MobileElement desiredAccount = (MobileElement)Engine.getDriver().findElement(By.xpath(xpath));
			Verify.waitForObject(desiredAccount, 2);
			desiredAccount.click();

			Thread.sleep(500);	
		}

		else {
			String xpath = "**/XCUIElementTypeStaticText[`name=='"+acct+"'`][1]";
			MobileElement me = (MobileElement)Engine.getDriver().findElement(MobileBy.iOSClassChain(xpath));

			if (! Verify.objExists(me)) {
				try {
					JavascriptExecutor js = (JavascriptExecutor) Engine.getDriver(); 
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
			Verify.waitForObject(me, 2);
			me.click();
			Thread.sleep(500);
		}	
	}

	public void selectToAccount (String acct) throws Exception {

		Helper h = new Helper();
		Thread.sleep(1000);

		this.toAccount.click();
		Thread.sleep(2500);

		if (h.getEngine().equals("android")) {
			String xpath = "//android.widget.TextView[@text='"+acct+"']";
			Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ acct + "\").instance(0))"));
			Thread.sleep(1000);

			if (! Verify.objExists((MobileElement)Engine.getDriver().findElement(By.xpath(xpath)))) {
				Commentary.log(LogStatus.FAIL,"Error****** Account ["+acct+"] is not found on Account drawer.");
				throw new Exception("Error****** Account ["+acct+"] not found on Account drawer.");
			}

			MobileElement desiredAccount = (MobileElement)Engine.getDriver().findElement(By.xpath(xpath));
			Verify.waitForObject(desiredAccount, 2);
			desiredAccount.click();

			Thread.sleep(500);	
		}

		else {
			String xpath = "**/XCUIElementTypeStaticText[`name=='"+acct+"'`][1]";
			MobileElement me = (MobileElement)Engine.getDriver().findElement(MobileBy.iOSClassChain(xpath));

			if (! Verify.objExists(me)) {
				try {
					JavascriptExecutor js = (JavascriptExecutor) Engine.getDriver(); 
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
			Verify.waitForObject(me, 2);
			me.click();
			Thread.sleep(500);
		}
	}

	public void selectFrequency(String frequency) throws Exception {

		Helper h = new Helper();
		this.frequency.click();
		Thread.sleep(2000);

		if (h.getEngine().equals("android"))
			this.selectFrequency_android(frequency);
		else
			this.selectFrequency_ios(frequency);
	}

	public void selectFrequency_android(String frequency) throws Exception {

		String sXpath = "//android.widget.TextView[@text='"+frequency+"']";

		if (! Verify.objExists(this.closeFrequency))
			Commentary.log(LogStatus.FAIL,"Error****** Reminders Detail > Tapping on Frequency, did not open Frequency selection drawer.");

		if(frequency.equals("Monthly")) {
			this.closeFrequency.click();
		}
		else {
			Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ frequency + "\").instance(0))"));

			Thread.sleep(1000);
			Engine.getDriver().findElement(By.xpath(sXpath)).click();
			Thread.sleep(1000);	
		}

		if(Verify.objExists(this.editSeriesHeaderText) && frequency.equals("Monthly")) {
			this.frequency.click();
			Thread.sleep(2000);
			Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ frequency + "\").instance(0))"));

			Thread.sleep(1000);
			Engine.getDriver().findElement(By.xpath(sXpath)).click();
			Thread.sleep(1000);
		}
	}

	public void selectFrequency_ios(String frequency) throws Exception {

		String sXpath = "**/XCUIElementTypeStaticText[`name='"+frequency+"'`]";

		if(frequency.equals("Monthly")) {
			this.closeFrequency.click();
		}
		else {
			Engine.getDriver().findElement(MobileBy.iOSClassChain(sXpath)).click();
			Thread.sleep(1000);
		}

		if(Verify.objExists(this.editSeriesHeaderText) && frequency.equals("Monthly")) {
			this.frequency.click();
			Thread.sleep(2000);
			Engine.getDriver().findElement(MobileBy.iOSClassChain(sXpath)).click();
			Thread.sleep(1000);
		}
	}

	public void selectEvery(String every) throws Exception {

		Helper h = new Helper();
		Verify.waitForObject(this.every, 2);
		this.every.click();

		if (h.getEngine().equals("android"))
			this.selectEvery_android(every);	
		else
			this.selectEvery_ios(every);	

		if(Verify.objExists(this.buttonDone)) 
			this.buttonDone.click();

		Thread.sleep(1000);
	}

	public void selectEvery_android(String every) throws Exception {

		String s[] = every.split("");
		int iCount;

		Thread.sleep(2000);
		Engine.getDriver().findElement(By.xpath("//android.widget.ImageView[@content-desc='delete']")).click();
		Thread.sleep(1000);

		for(iCount=0; iCount<s.length; iCount++) {
			Engine.getDriver().findElement(By.xpath("//*[@text='"+s[iCount]+"']")).click();
		}
	}

	public void selectEvery_ios(String every) throws Exception {

		String s[] = every.split("");
		int iCount;

		Thread.sleep(2000);
		Engine.getDriver().findElement(MobileBy.iOSClassChain("**/XCUIElementTypeOther[`name='delete'`][2]")).click();
		Thread.sleep(1000);

		for(iCount=0; iCount<s.length; iCount++) {
			Engine.getDriver().findElement(MobileBy.iOSClassChain("**/XCUIElementTypeStaticText[`name='"+s[iCount]+"'`]")).click();
		}
	}

	public void selectDay(String Day) throws Exception {

		Helper h = new Helper();
		Verify.waitForObject(this.day, 2);
		this.day.click();
		Thread.sleep(1000);

		if (h.getEngine().equals("android"))
			this.selectDay_android(Day);	
		else
			this.selectDay_ios(Day);	

		Thread.sleep(1000);
	}

	public void selectDay_android(String Day) throws Exception {

		Verify.waitForObject((MobileElement)Engine.getDriver().findElement(By.xpath("//android.widget.Spinner[@content-desc='pickerView']")), 1);
		Engine.getDriver().findElement(By.xpath("//android.widget.Spinner[@content-desc='pickerView']")).click();
		Thread.sleep(1000);
		Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ Day + "\").instance(0))"));
		Thread.sleep(1000);
		Engine.getDriver().findElement(By.xpath("//android.widget.CheckedTextView[@text='"+Day+"']")).click();

		if(Verify.objExists(this.okBtn)) 
			this.okBtn.click();
	}

	public void selectDay_ios(String Day) throws Exception {

		MobileElement pickerWheel = (MobileElement) Engine.getDriver().findElement(MobileBy.className("XCUIElementTypePickerWheel"));
		for(int i=0; i<=2; i++) {
			pickerWheel.setValue(Day);
		}

		if(Verify.objExists(this.okBtn)) 
			this.okBtn.click();
	}

	public void selectEvery2WeeksOn(String every2WeeksOn) throws Exception {

		Helper h = new Helper();
		Verify.waitForObject(this.everyTwoWeeksOn, 2);
		this.everyTwoWeeksOn.click();
		Thread.sleep(1000);

		if (h.getEngine().equals("android"))
			this.selectEvery2WeeksOn_android(every2WeeksOn);	
		else
			this.selectEvery2WeeksOn_ios(every2WeeksOn);	

		Thread.sleep(1000);
	}

	public void selectEvery2WeeksOn_android(String every2WeeksOn) throws Exception {

		Verify.waitForObject((MobileElement)Engine.getDriver().findElement(By.xpath("//android.widget.Spinner[@content-desc='pickerView']")), 1);
		Engine.getDriver().findElement(By.xpath("//android.widget.Spinner[@content-desc='pickerView']")).click();
		Thread.sleep(2000);
		Engine.getDriver().findElement(By.xpath("//android.widget.CheckedTextView[@text='"+every2WeeksOn+"']")).click();

		if(Verify.objExists(this.okBtn)) 
			this.okBtn.click();
	}

	public void selectEvery2WeeksOn_ios(String every2WeeksOn) throws Exception {

		MobileElement el = (MobileElement) Engine.getDriver().findElement(MobileBy.className("XCUIElementTypePickerWheel"));
		for(int i=0; i<=1; i++) {
			el.setValue(every2WeeksOn);
		}

		if(Verify.objExists(this.okBtn)) 
			this.okBtn.click();
	}

	public void selectFirstOn(String firstOn) throws Exception {

		Helper h = new Helper();
		Verify.waitForObject(this.firstOn, 2);
		this.firstOn.click();
		Thread.sleep(2000);

		if (h.getEngine().equals("android"))
			this.selectFirstOn_android(firstOn);	
		else
			this.selectFirstOn_ios(firstOn);	

		Thread.sleep(1000);
	}

	public void selectFirstOn_android(String firstOn) throws Exception {

		Verify.waitForObject((MobileElement)Engine.getDriver().findElement(By.xpath("//android.widget.Spinner[@content-desc='pickerView']")), 1);
		Engine.getDriver().findElement(By.xpath("//android.widget.Spinner[@content-desc='pickerView']")).click();
		Thread.sleep(2000);
		Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""+ firstOn + "\").instance(0))"));
		Thread.sleep(1000);
		Engine.getDriver().findElement(By.xpath("//android.widget.CheckedTextView[@text='"+firstOn+"']")).click();

		if(Verify.objExists(this.okBtn)) 
			this.okBtn.click();
	}

	public void selectFirstOn_ios(String firstOn) throws Exception {

		MobileElement el = (MobileElement) Engine.getDriver().findElement(MobileBy.className("XCUIElementTypePickerWheel"));
		for(int i=0; i<=2; i++) {
			el.setValue(firstOn);
		}

		if(Verify.objExists(this.okBtn)) 
			this.okBtn.click();
	}

	public void selectSecondOn(String secondOn) throws Exception {

		Helper h = new Helper();
		Verify.waitForObject(this.secondOn, 2);
		this.secondOn.click();
		Thread.sleep(1000);

		if (h.getEngine().equals("android"))
			this.selectSecondOn_android(secondOn);	
		else
			this.selectSecondOn_ios(secondOn);	

		Thread.sleep(1000);
	}

	public void selectSecondOn_android(String secondOn) throws Exception {

		Verify.waitForObject((MobileElement)Engine.getDriver().findElement(By.xpath("//android.widget.Spinner[@content-desc='pickerView']")), 1);
		Engine.getDriver().findElement(By.xpath("//android.widget.Spinner[@content-desc='pickerView']")).click();
		Thread.sleep(2000);
		Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""+ secondOn + "\").instance(0))"));
		Thread.sleep(1000);
		Engine.getDriver().findElement(By.xpath("//android.widget.CheckedTextView[@text='"+secondOn+"']")).click();

		if(Verify.objExists(this.okBtn)) 
			this.okBtn.click();
	}

	public void selectSecondOn_ios(String secondOn) throws Exception {

		MobileElement el = (MobileElement) Engine.getDriver().findElement(MobileBy.className("XCUIElementTypePickerWheel"));
		for(int i=0; i<=2; i++) {
			el.setValue(secondOn);
		}

		if(Verify.objExists(this.okBtn)) 
			this.okBtn.click();
	}

	public void selectYearlyDate(String yearlyDate) throws Exception {

		Helper h = new Helper();
		Verify.waitForObject(this.date, 2);
		this.date.click();
		Thread.sleep(1000);

		if (h.getEngine().equals("android"))
			this.selectYearlyDate_android(yearlyDate);	
		else
			this.selectYearlyDate_ios(yearlyDate);	

		Thread.sleep(1000);
	}

	public void selectYearlyDate_android(String mddyyyy) throws Exception {

		String[] a = mddyyyy.split("/");

		String month = new DateFormatSymbols().getShortMonths()[Integer.parseInt(a[0])-1].toString();
		String date = a[1];
		String year = a[2];

		try {
			this.enterMonth.click();
			Thread.sleep(1000);
			this.enterMonth.clear();
			this.enterMonth.sendKeys(month);
			Thread.sleep(1000);

			this.enterDate.click();
			this.enterDate.clear();
			this.enterDate.sendKeys(date);
			Thread.sleep(1000);

			this.enterYear.click();
			this.enterYear.clear();
			this.enterYear.sendKeys(year);
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.buttonOK.click();
		Thread.sleep(1000);
	}

	public void selectYearlyDate_ios(String mddyyyy) throws Exception {

		String[] a = mddyyyy.split("/");

		String month = new DateFormatSymbols().getMonths()[Integer.parseInt(a[0])-1].toString();
		String date = a[1];
		String year = a[2];

		int iCount;

		for (iCount=0; iCount < 3; iCount++)
			enterMonth.sendKeys(month);
		Thread.sleep(500);

		for (iCount=0; iCount < 3; iCount++)
			enterDate.sendKeys(date);
		Thread.sleep(500);

		for (iCount=0; iCount < 2; iCount++)
			enterYear.sendKeys(year);

		this.buttonOK.click();
		Thread.sleep(500);	
	}

	public void selectFirstOnDate(String FirstOnDate) throws Exception {

		Helper h = new Helper();
		Verify.waitForObject(this.firstOn, 2);
		this.firstOn.click();

		if (h.getEngine().equals("android"))
			this.selectFirstOnDate_android(FirstOnDate);	
		else
			this.selectFirstOnDate_ios(FirstOnDate);	

		Thread.sleep(1000);
	}

	public void selectFirstOnDate_android(String mddyyyy) throws Exception {

		String[] a = mddyyyy.split("/");

		String month = new DateFormatSymbols().getShortMonths()[Integer.parseInt(a[0])-1].toString();
		String date = a[1];
		String year = a[2];

		try {
			this.enterMonth.click();
			Thread.sleep(1000);
			this.enterMonth.clear();
			this.enterMonth.sendKeys(month);
			Thread.sleep(1000);

			this.enterDate.click();
			this.enterDate.clear();
			this.enterDate.sendKeys(date);
			Thread.sleep(1000);

			this.enterYear.click();
			this.enterYear.clear();
			this.enterYear.sendKeys(year);
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.buttonOK.click();
		Thread.sleep(1000);
	}

	public void selectFirstOnDate_ios(String mddyyyy) throws Exception {

		String[] a = mddyyyy.split("/");

		String month = new DateFormatSymbols().getMonths()[Integer.parseInt(a[0])-1].toString();
		String date = a[1];
		String year = a[2];

		int iCount;

		for (iCount=0; iCount < 3; iCount++)
			enterMonth.sendKeys(month);
		Thread.sleep(500);

		for (iCount=0; iCount < 3; iCount++)
			enterDate.sendKeys(date);
		Thread.sleep(500);

		for (iCount=0; iCount < 2; iCount++)
			enterYear.sendKeys(year);

		this.buttonOK.click();
		Thread.sleep(500);	
	}

	public void selectSecondOnDate(String SecondOnDate) throws Exception {

		Helper h = new Helper();
		Verify.waitForObject(this.secondOn, 2);
		this.secondOn.click();

		if (h.getEngine().equals("android"))
			this.selectFirstOnDate_android(SecondOnDate);	
		else
			this.selectFirstOnDate_ios(SecondOnDate);	

		Thread.sleep(1000);
	}

	public void selectSecondOnDate_android(String mddyyyy) throws Exception {

		String[] a = mddyyyy.split("/");

		String month = new DateFormatSymbols().getShortMonths()[Integer.parseInt(a[0])-1].toString();
		String date = a[1];
		String year = a[2];

		try {
			this.enterMonth.click();
			Thread.sleep(1000);
			this.enterMonth.clear();
			this.enterMonth.sendKeys(month);
			Thread.sleep(1000);

			this.enterDate.click();
			this.enterDate.clear();
			this.enterDate.sendKeys(date);
			Thread.sleep(1000);

			this.enterYear.click();
			this.enterYear.clear();
			this.enterYear.sendKeys(year);
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.buttonOK.click();
		Thread.sleep(1000);
	}

	public void selectSecondOnDate_ios(String mddyyyy) throws Exception {

		String[] a = mddyyyy.split("/");

		String month = new DateFormatSymbols().getMonths()[Integer.parseInt(a[0])-1].toString();
		String date = a[1];
		String year = a[2];

		int iCount;

		for (iCount=0; iCount < 3; iCount++)
			enterMonth.sendKeys(month);
		Thread.sleep(500);

		for (iCount=0; iCount < 3; iCount++)
			enterDate.sendKeys(date);
		Thread.sleep(500);

		for (iCount=0; iCount < 2; iCount++)
			enterYear.sendKeys(year);

		this.buttonOK.click();
		Thread.sleep(500);	
	}

	public void selectEndOnDate(String EndOnDate) throws Exception {

		Helper h = new Helper();
		Verify.waitForObject(this.endDate, 2);
		this.endDate.click();

		Verify.waitForObject(EndOnDateOption, 2);
		EndOnDateOption.click();

		Verify.waitForObject(this.lastReminderDate, 2);
		this.lastReminderDate.click();
		Thread.sleep(1000);

		if (h.getEngine().equals("android"))
			this.selectEndOnDate_android(EndOnDate);	
		else
			this.selectEndOnDate_ios(EndOnDate);	

		Thread.sleep(2000);
	}

	public void selectEndOnDate_android(String mddyyyy) throws Exception {

		String[] a = mddyyyy.split("/");

		String month = new DateFormatSymbols().getShortMonths()[Integer.parseInt(a[0])-1].toString();
		String date = a[1];
		String year = a[2];

		try {
			this.enterMonth.click();
			Thread.sleep(1000);
			this.enterMonth.clear();
			this.enterMonth.sendKeys(month);
			Thread.sleep(1000);

			this.enterDate.click();
			this.enterDate.clear();
			this.enterDate.sendKeys(date);
			Thread.sleep(1000);

			this.enterYear.click();
			this.enterYear.clear();
			this.enterYear.sendKeys(year);
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.buttonOK.click();
	}

	public void selectEndOnDate_ios(String mddyyyy) throws Exception {

		String[] a = mddyyyy.split("/");

		String month = new DateFormatSymbols().getMonths()[Integer.parseInt(a[0])-1].toString();
		String date = a[1];
		String year = a[2];

		int iCount;

		for (iCount=0; iCount < 3; iCount++)
			enterMonth.sendKeys(month);
		Thread.sleep(500);

		for (iCount=0; iCount < 3; iCount++)
			enterDate.sendKeys(date);
		Thread.sleep(500);

		for (iCount=0; iCount < 2; iCount++)
			enterYear.sendKeys(year);

		this.buttonOK.click();
	}

	public void selectEndAfterNumberOfReminders(String NumberOfReminders) throws Exception {

		Helper h = new Helper();
		Verify.waitForObject(this.endDate, 2);
		this.endDate.click();

		Verify.waitForObject(endAfterNumberOfRemindersOption, 2);
		endAfterNumberOfRemindersOption.click();

		Verify.waitForObject(this.endRemindersAfter, 2);
		this.endRemindersAfter.click();
		Thread.sleep(1000);

		if (h.getEngine().equals("android"))
			this.selectEndAfterNumberOfReminders_android(NumberOfReminders);	
		else
			this.selectEndAfterNumberOfReminders_ios(NumberOfReminders);	

		if(Verify.objExists(this.buttonDone)) 
			this.buttonDone.click();

		Thread.sleep(1000);
	}

	public void selectEndAfterNumberOfReminders_android(String NumberOfReminders) throws Exception {

		String s[] = NumberOfReminders.split("");
		int iCount;

		Engine.getDriver().findElement(By.xpath("//android.widget.ImageView[@content-desc='delete']")).click();
		Thread.sleep(1000);

		for(iCount=0; iCount<s.length; iCount++) {
			Engine.getDriver().findElement(By.xpath("//*[@text='"+s[iCount]+"']")).click();
		}
	}

	public void selectEndAfterNumberOfReminders_ios(String NumberOfReminders) throws Exception {

		String s[] = NumberOfReminders.split("");
		int iCount;

		Engine.getDriver().findElement(MobileBy.iOSClassChain("**/XCUIElementTypeOther[`name='delete'`][2]")).click();
		Thread.sleep(1000);

		for(iCount=0; iCount<s.length; iCount++) {
			Engine.getDriver().findElement(MobileBy.iOSClassChain("**/XCUIElementTypeStaticText[`name='"+s[iCount]+"'`]")).click();
		}
	}

	public void scrolltoNext90DaysFilter() throws Exception {

		for(int i=1; i<=3; i++) {
			this.scrollFilter();
		}	
	}

	public void scrollFilter() throws Exception {

		Helper h = new Helper();
		if (h.getEngine().equals("android"))
			scrollFilter_android();
		else
			scrollFilter_IOS();		
	}

	public void scrollFilter_IOS() throws Exception {

		MobileElement element = this.next30Days;
		String elementID = element.getId();
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("element", elementID); // Only for ‘scroll in element’
		scrollObject.put("direction", "right");
		Engine.getDriver().executeScript("mobile:scroll", scrollObject);
		Thread.sleep(1000);
	}

	public void scrollFilter_android() throws Exception {

		Dimension size = this.next30Days.getSize();
		int x_start=(int)(size.width*0.99);
		int x_end=(int)(size.width*0.01);
		int y=this.next30Days.getRect().getY();

		TouchAction touchAction = new TouchAction(Engine.getDriver());

		touchAction
		.press(point(x_start, y))
		.waitAction(waitOptions(ofMillis(1000)))
		.moveTo(point(x_end, y))
		.release().perform();
	}

	public void tapOnSeriesTab() throws Exception {

		Verify.waitForObject(this.seriesTab, 1);
		this.seriesTab.click();
		Thread.sleep(1000);
	}

	public void tapOnDeleteSeries() throws Exception {

		Thread.sleep(2000);

		Helper h = new Helper();
		if (h.getEngine().equals("android")){
			String sXpath="//android.view.ViewGroup[@content-desc='Delete Series']";
			Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Delete Series\").instance(0))"));
			Thread.sleep(1000);
			Engine.getDriver().findElement(By.xpath(sXpath)).click();
			Thread.sleep(1000);
		}
		else {
			String cc="**/XCUIElementTypeStaticText[`name='Delete Series'`]";
			MobileElement me = (MobileElement) Engine.getDriver().findElement(MobileBy.iOSClassChain(cc));
			String me_id = me.getId();
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("element", me_id);
			scrollObject.put("toVisible", "not an empty string");
			Engine.getDriver().executeScript("mobile:scroll", scrollObject);  // scroll to the target element
			Thread.sleep(1000);
			Engine.getDriver().findElement(By.name("Delete Series")).click();
			Thread.sleep(1000);
		}
	}

	public void deleteSeries() throws Exception {

		Verify.waitForObject(this.seriesTab, 1);
		this.seriesTab.click();

		this.tapOnReminderSeries();

		this.deleteSeriesFromEditButton();
	}

	public void deleteSeriesFromEditButton() throws Exception {

		Verify.waitForObject(this.editButton, 1);
		this.editButton.click();
		this.tapOnDeleteSeries();

		Verify.waitForObject(this.deleteReminderSeriesAlertButton, 1);
		this.deleteReminderSeriesAlertButton.click();
		Thread.sleep(2000);
		Verify.waitForObject(this.youHaveNoScheduledReminders, 2);
	}

	public void tapOnTags() throws Exception {

		Helper h = new Helper();
		if (h.getEngine().equals("android")){
			String sXpath="//android.view.ViewGroup[@content-desc='Tags']";
			Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Tags\").instance(0))"));
			Thread.sleep(1000);
			Engine.getDriver().findElement(By.xpath(sXpath)).click();
			Thread.sleep(1000);
		}
		else {
			String cc="**/XCUIElementTypeStaticText[`name='Tags'`]";
			MobileElement me = (MobileElement) Engine.getDriver().findElement(MobileBy.iOSClassChain(cc));
			String me_id = me.getId();
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("element", me_id);
			scrollObject.put("toVisible", "not an empty string");
			Engine.getDriver().executeScript("mobile:scroll", scrollObject);  // scroll to the target element
			Thread.sleep(1000);
			Engine.getDriver().findElement((MobileBy.iOSClassChain("**/XCUIElementTypeStaticText[`name='Tags'`]"))).click();
			Thread.sleep(1000);
		}
	}

	public void tapOnReminderSeries() throws Exception {

		Verify.waitForObject(this.reminderName, 1);
		this.reminderName.click();
		Verify.waitForObject(this.reminderSeriesNameOnViewSeries, 1);
	}

	public void searchRecentReminderSeries(String searchString) throws Exception {
		Verify.waitForObject(searchSeriesTxtField, 1);
		searchSeriesTxtField.click();
		searchSeriesTxtField.clear();
		searchSeriesTxtField.sendKeys(searchString);

		Helper h = new Helper();
		h.hideKeyBoard();
		Thread.sleep(1000);
	}

	public void tapOnEnterOption() throws Exception {

		Verify.waitForObject(this.enterOption, 1);
		this.enterOption.click();
		Thread.sleep(2500);
	}

	public void tapOnIgnoreThisInstanceOption() throws Exception {

		Verify.waitForObject(this.moreActionsOption, 2);
		this.moreActionsOption.click();
		Verify.waitForObject(this.ignoreThisInstanceOption, 1);
		this.ignoreThisInstanceOption.click();
		Thread.sleep(2000);
	}

	public void tapOnEnterAllPreviousInstancesOption() throws Exception {

		Verify.waitForObject(this.enterAllPreviousInstancesOption, 1);
		this.enterAllPreviousInstancesOption.click();
		Thread.sleep(2500);
	}

	public void tapOnIgnoreAllPreviousInstancesOption() throws Exception {

		Verify.waitForObject(this.moreActionsOption, 2);
		this.moreActionsOption.click();
		Verify.waitForObject(this.ignoreAllPreviousInstancesOption, 1);
		this.ignoreAllPreviousInstancesOption.click();
		Thread.sleep(2000);
	}

	public void tapOnEditThisInstanceOption() throws Exception {

		Verify.waitForObject(this.moreActionsOption, 2);
		this.moreActionsOption.click();
		Verify.waitForObject(this.editThisInstanceOption, 1);
		this.editThisInstanceOption.click();
		Thread.sleep(2000);
	}

	public void tapOnEditThisAndFutureInstancesOption() throws Exception {

		Verify.waitForObject(this.moreActionsOption, 2);
		this.moreActionsOption.click();
		Verify.waitForObject(this.editThisAndFutureInstancesOption, 1);
		this.editThisAndFutureInstancesOption.click();
		Thread.sleep(2000);
	}

	public void tapOnDeleteThisAndFutureInstances() throws Exception {

		Verify.waitForObject(this.moreActionsOption, 2);
		this.moreActionsOption.click();
		Verify.waitForObject(this.deleteThisAndFutureInstancesOption, 1);
		this.deleteThisAndFutureInstancesOption.click();
		Thread.sleep(2000);
	}

	public int getTransactionListSize() throws Exception {

		BillsAndIncomePage bi = new BillsAndIncomePage();
		List<MobileElement> li = bi.getAllSearchTransactions ();
		Commentary.log(LogStatus.INFO, "No of Transactions appeared in the search .."+li.size());
		int listSize = li.size();
		return listSize;
	}

	public List<MobileElement> getAllSearchTransactions() throws Exception{

		Helper h = new Helper();
		if (h.getEngine().equals("android"))
			return getAllSearchTransactions_android();
		else
			return getAllSearchTransactions_ios();		
	}

	public List<MobileElement> getAllSearchTransactions_android () throws Exception{

		String sXpath = "//android.widget.LinearLayout[contains(@resource-id,'list_row')]/android.widget.RelativeLayout/..";
		List <MobileElement> me = null;

		me = Engine.getDriver().findElements(By.xpath(sXpath));

		return me;
	}

	public List<MobileElement> getAllSearchTransactions_ios () throws Exception{

		String sXpath = "**/XCUIElementTypeTable/XCUIElementTypeCell";
		List <MobileElement> me = null;
		me = Engine.getDriver().findElements(MobileBy.iOSClassChain(sXpath));

		return me;
	}

	public List<MobileElement> getAllSearchRemindersSeries() throws Exception{

		Helper h = new Helper();
		if (h.getEngine().equals("android"))
			return getAllSearchRemindersSeries_android();
		else
			return getAllSearchRemindersSeries_ios();		
	}

	public List<MobileElement> getAllSearchRemindersSeries_android () throws Exception{

		String sXpath = "//android.widget.TextView[contains(@content-desc,'Payee Name: ')]";
		List <MobileElement> me = null;

		me = Engine.getDriver().findElements(By.xpath(sXpath));

		return me;
	}

	public List<MobileElement> getAllSearchRemindersSeries_ios () throws Exception{

		String sXpath = "**/XCUIElementTypeStaticText[`name BEGINSWITH 'Payee Name: '`]";
		List <MobileElement> me = null;
		me = Engine.getDriver().findElements(MobileBy.iOSClassChain(sXpath));

		return me;
	}

	public void deleteAlreadyPresentReminderSeries() throws Exception {

		OverviewPage op = new OverviewPage();

		Verify.waitForObject(this.seriesTab, 1);
		this.seriesTab.click();
		Thread.sleep(2000);

		List<MobileElement> li = this.getAllSearchRemindersSeries();
		Commentary.log(LogStatus.INFO, "No of Reminder Series appeared in the search .."+li.size());
		int listSize = li.size();

		if (li.isEmpty())
			Commentary.log(LogStatus.INFO, "No Reminder Series present.");
		else {
			for(int i=1; i<=listSize; i++) {
				Verify.waitForObject(this.reminderName, 1);
				this.reminderName.click();
				deleteSeriesFromEditButton();
				Thread.sleep(3000);
				Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 1);
			}
		}
		this.remindersTab.click();
		Thread.sleep(1000);
	}

	public List<MobileElement> getAllReminderEntriesDate() throws Exception{

		Helper h = new Helper();
		if (h.getEngine().equals("android"))
			return getAllReminderEntriesDate_android();
		else
			return getAllReminderEntriesDate_ios();		
	}

	public List<MobileElement> getAllReminderEntriesDate_android () throws Exception{

		String sXpath = "//android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.TextView";
		List <MobileElement> me = null;

		me = Engine.getDriver().findElements(By.xpath(sXpath));

		return me;
	}

	public List<MobileElement> getAllReminderEntriesDate_ios () throws Exception{

		String sXpath = "**/XCUIElementTypeStaticText[`name='bottomRightLabel'`]";
		List <MobileElement> me = null;
		me = Engine.getDriver().findElements(MobileBy.iOSClassChain(sXpath));

		return me;
	}

	public void validateLastDayOfMonth(String name, int i) throws Exception {

		SoftAssert sa = new SoftAssert();

		if(name.contains("JAN")) {
			if(this.getAllReminderEntriesDate().get(i).getText().contains("Jan 31"))
				Commentary.log(LogStatus.INFO, "Last Date of January Month is displayed.");
			else
				Commentary.log(sa, LogStatus.FAIL, "Last Date of January Month is NOT displayed.");
		}

		if(name.contains("FEB")) {
			if(this.getAllReminderEntriesDate().get(i).getText().contains("Feb 28")) 
				Commentary.log(LogStatus.INFO, "Last Date of February Month is displayed.");
			else
				Commentary.log(sa, LogStatus.FAIL, "Last Date of February Month is NOT displayed.");
		}

		if(name.contains("MAR")) {
			if(this.getAllReminderEntriesDate().get(i).getText().contains("Mar 31"))
				Commentary.log(LogStatus.INFO, "Last Date of March Month is displayed.");
			else
				Commentary.log(sa, LogStatus.FAIL, "Last Date of March Month is NOT displayed.");
		}

		if(name.contains("APR")) {
			if(this.getAllReminderEntriesDate().get(i).getText().contains("Apr 30"))
				Commentary.log(LogStatus.INFO, "Last Date of April Month is displayed.");
			else
				Commentary.log(sa, LogStatus.FAIL, "Last Date of April Month is NOT displayed.");
		}

		if(name.contains("MAY")) {
			if(this.getAllReminderEntriesDate().get(0).getText().contains("May 31"))
				Commentary.log(LogStatus.INFO, "Last Date of May Month is displayed.");
			else
				Commentary.log(sa, LogStatus.FAIL, "Last Date of May Month is NOT displayed.");
		}

		if(name.contains("JUN")) {
			if(this.getAllReminderEntriesDate().get(i).getText().contains("Jun 30"))
				Commentary.log(LogStatus.INFO, "Last Date of June Month is displayed.");
			else
				Commentary.log(sa, LogStatus.FAIL, "Last Date of June Month is NOT displayed.");
		}

		if(name.contains("JUL")) {
			if(this.getAllReminderEntriesDate().get(i).getText().contains("Jul 31"))
				Commentary.log(LogStatus.INFO, "Last Date of July Month is displayed.");
			else
				Commentary.log(sa, LogStatus.FAIL, "Last Date of July Month is NOT displayed.");
		}

		if(name.contains("AUG")) {
			if(this.getAllReminderEntriesDate().get(i).getText().contains("Aug 31"))
				Commentary.log(LogStatus.INFO, "Last Date of August Month is displayed.");
			else
				Commentary.log(sa, LogStatus.FAIL, "Last Date of August Month is NOT displayed.");
		}

		if(name.contains("SEP")) {
			if(this.getAllReminderEntriesDate().get(i).getText().contains("Sep 30"))
				Commentary.log(LogStatus.INFO, "Last Date of September Month is displayed.");
			else
				Commentary.log(sa, LogStatus.FAIL, "Last Date of September Month is NOT displayed.");
		}

		if(name.contains("OCT")) {
			if(this.getAllReminderEntriesDate().get(i).getText().contains("Oct 31"))
				Commentary.log(LogStatus.INFO, "Last Date of October Month is displayed.");
			else
				Commentary.log(sa, LogStatus.FAIL, "Last Date of October Month is NOT displayed.");
		}

		if(name.contains("NOV")) {
			if(this.getAllReminderEntriesDate().get(i).getText().contains("Nov 30"))
				Commentary.log(LogStatus.INFO, "Last Date of November Month is displayed.");
			else
				Commentary.log(sa, LogStatus.FAIL, "Last Date of November Month is NOT displayed.");
		}

		if(name.contains("DEC")) {
			if(this.getAllReminderEntriesDate().get(i).getText().contains("Dec 31"))
				Commentary.log(LogStatus.INFO, "Last Date of December Month is displayed.");
			else
				Commentary.log(sa, LogStatus.FAIL, "Last Date of December Month is NOT displayed.");
		}
	}
}
