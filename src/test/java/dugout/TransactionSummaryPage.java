package dugout;

import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
//import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import referee.Verify;
import support.Engine;
import support.Helper;

public class TransactionSummaryPage {
	
		public TransactionSummaryPage () {
			try {
				
				PageFactory.initElements(new AppiumFieldDecorator(Engine.getDriver()),this);
			} catch (Exception e) {
				
				e.printStackTrace();
			}	
		}
		
		@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name=='Transaction Summary'`]")
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Transaction Summary']")
		public MobileElement transactionSummaryHeader;
		
		@iOSXCUITFindBy(accessibility="Back")
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Transaction Summary']/../android.widget.ImageButton")
		public MobileElement backButtonOnHeader;
		
		@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeButton[1]")
		@AndroidFindBy(xpath="(//*[@class='android.widget.ImageButton'])[1]")
		public MobileElement backButton;
		
		@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeScrollView/**/XCUIElementTypeStaticText")
		@AndroidFindBy(xpath="(//android.widget.ScrollView//android.widget.TextView)[1]")
		public MobileElement monthHeader;
		
		@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name='button Category'`]")
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Category']")
		public MobileElement categoryTab;
		
		@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name='button Payee'`]")
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Payee']")
		public MobileElement payeeTab;
		
		//@iOSXCUITFindBy(iOSClassChain ="**/XCUIElementTypeScrollView/XCUIElementTypeOther[`name CONTAINS '$'`]/XCUIElementTypeOther[2]")
		@iOSXCUITFindBy(iOSClassChain ="**/XCUIElementTypeScrollView[-1]/XCUIElementTypeOther[`name CONTAINS '$'`]/XCUIElementTypeOther[1]")
		@AndroidFindBy(xpath="//android.widget.TextView[@text='shop']/../android.widget.TextView[contains(@text,'$')]")
		//@AndroidFindBy(xpath="(//android.widget.ScrollView//android.widget.ImageView/../android.widget.TextView)[1]/../android.widget.TextView[contains(@text,'$')]")
		public MobileElement payeeTile;
		
		//@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeScrollView/XCUIElementTypeOther[`name CONTAINS '$'`]/XCUIElementTypeOther[`name CONTAINS 'Internet'`][2]")
		@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeScrollView[-1]/**/XCUIElementTypeOther[`name CONTAINS 'Internet' AND name contains '$'`][-1]")
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Internet']/../android.widget.TextView[contains(@text,'$')]")
		//@AndroidFindBy(xpath="(//android.widget.ScrollView//android.widget.ImageView/../android.widget.TextView)[1]")
		public MobileElement categoryTile;
		
		@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='No Transactions by Category'`]")
		@AndroidFindBy(xpath="//android.widget.TextView[@text='No Transactions by Category']")
		public MobileElement noTransactionCategory;
		
		@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='No Transactions by Payee'`]")
		@AndroidFindBy(xpath="//android.widget.TextView[@text='No Transactions by Payee']")
		public MobileElement noTransactionPayee;
		
		@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name CONTAINS '$'`]/XCUIElementTypeOther[15]")
		@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'$')]")
		public MobileElement firstRecordInList;
		
		@iOSXCUITFindBy(iOSClassChain ="**/XCUIElementTypeScrollView/XCUIElementTypeOther[`name CONTAINS '$'`]/XCUIElementTypeOther[3]")
		@AndroidFindBy(xpath="(//android.widget.ScrollView//android.widget.ImageView/../android.widget.TextView)[1]")
		public MobileElement transactionCategoryPayeeText;
		
		@iOSXCUITFindBy(iOSClassChain ="**/XCUIElementTypeOther[`name contains 'Summary'`]/**/XCUIElementTypeStaticText[`name contains 'Net Income :'`]")
		@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Summary')]/../android.widget.TextView[contains(@text,'Net Income :')]")
		public MobileElement netIncomeAmount;
		
		@iOSXCUITFindBy(iOSClassChain ="**/XCUIElementTypeOther[`name contains 'Summary'`]/**/XCUIElementTypeStaticText[`name contains 'Earned :'`]")
		@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Summary')]/../android.widget.TextView[contains(@text,'Earned :')]")
		public MobileElement earnedAmount;
		
		@iOSXCUITFindBy(iOSClassChain ="**/XCUIElementTypeOther[`name contains 'Summary'`]/**/XCUIElementTypeStaticText[`name contains 'Spent :'`]")
		@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Summary')]/../android.widget.TextView[contains(@text,'Spent :')]")
		public MobileElement spentAmount;
		
		@iOSXCUITFindBy(iOSClassChain ="**/XCUIElementTypeButton[`name = 'fab'`]")
		@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.quicken.qm2014:id/fab']")
		public MobileElement addTransactionButton;
		
		public void navigateBackToDashboard() throws Exception{
			
			Verify.waitForObject(this.backButtonOnHeader, 1);
			backButtonOnHeader.click();
			Thread.sleep(10000);		
		}
		
		public MobileElement getCurrentMonthYear() throws Exception {
			Helper h = new Helper();
			String[] monthNames = {"JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"};
			
			Calendar c= Calendar.getInstance();
			int cyear = c.get(Calendar.YEAR);
			int cmonth = c.get(Calendar.MONTH);
			String summaryMonthText = monthNames[cmonth] +" "+ cyear;
			String xPath_IOS = "//XCUIElementTypeStaticText[@name='"+summaryMonthText+"']";
			String xPath_ANDROID = "//android.widget.TextView[@text='"+summaryMonthText+"']";
			
			if (h.getEngine().equalsIgnoreCase("ios")) {
				 MobileElement me = (MobileElement) Engine.getDriver().findElement(By.xpath(xPath_IOS));
				 return me;
			} else {
				 MobileElement me = (MobileElement) Engine.getDriver().findElement(By.xpath(xPath_ANDROID));
				 return me;
			}
			
		}
		public String getCategoryPayeeName () {
			return this.transactionCategoryPayeeText.getText().trim().split(" ")[0];
			//return this.transactionCategoryPayeeText.getText();
		}
		
		public void tapOnPayeeTab() throws Exception {

			Verify.waitForObject(this.payeeTab, 1);
			this.payeeTab.click();
			Thread.sleep(1000);
		}
}
