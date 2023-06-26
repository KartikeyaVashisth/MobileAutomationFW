package dugout;

import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.pagefactory.AndroidBy;
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
		
		@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name=='Transaction Summary'`]")
		@AndroidFindBy(xpath="//android.view.View[@text='Transaction Summary']")
		public WebElement transactionSummaryHeader;
		
		@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name=='Monthly Summary'`]")
		@AndroidFindBy(xpath="//android.view.View[@text='Monthly Summary']")
		public WebElement monthlySummaryHeader;
		
		@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeButton[`name='Go back'`]")
		@AndroidFindBy(xpath="//*[@text='Monthly Summary']/../*[@class='android.widget.Button']")
		public WebElement backButtonOnHeader;
		
		@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeButton[1]")
		@AndroidFindBy(xpath="//android.widget.Button")
		public WebElement backButton;
		
		@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeScrollView/**/XCUIElementTypeStaticText")
		@AndroidFindBy(xpath="(//android.widget.ScrollView//android.widget.TextView)[1]")
		public WebElement monthHeader;
		
		@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name='button Category'`]")
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Category']")
		public WebElement categoryTab;
		
		@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name='button Payee'`]")
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Payee']")
		public WebElement payeeTab;
		
		//@iOSXCUITFindBy(iOSClassChain ="**/XCUIElementTypeScrollView/XCUIElementTypeOther[`name CONTAINS '$'`]/XCUIElementTypeOther[2]")
		@iOSXCUITFindBy(iOSClassChain ="**/XCUIElementTypeScrollView[-1]/XCUIElementTypeOther[`name CONTAINS '$'`]/XCUIElementTypeOther[`name contains 'payee: shop'`]/**/XCUIElementTypeStaticText[`name contains 'amount:'`]")
		@AndroidFindBy(xpath="//android.widget.TextView[@text='shop']/../android.widget.TextView[contains(@text,'$')]")
		//@AndroidFindBy(xpath="(//android.widget.ScrollView//android.widget.ImageView/../android.widget.TextView)[1]/../android.widget.TextView[contains(@text,'$')]")
		public WebElement payeeTile;
		
		//@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeScrollView/XCUIElementTypeOther[`name CONTAINS '$'`]/XCUIElementTypeOther[`name CONTAINS 'Internet'`][2]")
		@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeScrollView[-1]/**/XCUIElementTypeOther[`name CONTAINS 'Internet' AND name contains '$'`][-1]")
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Internet']/../android.widget.TextView[contains(@text,'$')]")
		//@AndroidFindBy(xpath="(//android.widget.ScrollView//android.widget.ImageView/../android.widget.TextView)[1]")
		public WebElement categoryTile;
		
		@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='No Transactions by Category'`]")
		@AndroidFindBy(xpath="//android.widget.TextView[@text='No Transactions by Category']")
		public WebElement noTransactionCategory;
		
		@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='No Transactions by Payee'`]")
		@AndroidFindBy(xpath="//android.widget.TextView[@text='No Transactions by Payee']")
		public WebElement noTransactionPayee;
		
		@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeScrollView[-1]/**/XCUIElementTypeStaticText[`name CONTAINS 'payee:'`][1]")
		@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'$')]")
		public WebElement firstRecordInList;
		
		@iOSXCUITFindBy(iOSClassChain ="**/XCUIElementTypeScrollView/XCUIElementTypeOther[`name CONTAINS '$'`]/XCUIElementTypeOther[3]/**/XCUIElementTypeStaticText[1]")
		@AndroidFindBy(xpath="(//android.widget.ScrollView//android.widget.ImageView/../android.widget.TextView)[1]")
		public WebElement transactionCategoryPayeeText;
		
		@iOSXCUITFindBy(iOSClassChain ="**/XCUIElementTypeOther[`name contains 'Summary'`]/**/XCUIElementTypeStaticText[`name contains 'Net Income :'`]")
		@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Summary')]/../android.widget.TextView[contains(@text,'Net Income :')]")
		public WebElement netIncomeAmount;
		
		@iOSXCUITFindBy(iOSClassChain ="**/XCUIElementTypeOther[`name contains 'Summary'`]/**/XCUIElementTypeStaticText[`name contains 'Earned :'`]")
		@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Summary')]/../android.widget.TextView[contains(@text,'Earned :')]")
		public WebElement earnedAmount;
		
		@iOSXCUITFindBy(iOSClassChain ="**/XCUIElementTypeOther[`name contains 'Summary'`]/**/XCUIElementTypeStaticText[`name contains 'Spent :'`]")
		@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Summary')]/../android.widget.TextView[contains(@text,'Spent :')]")
		public WebElement spentAmount;
		
		@iOSXCUITFindBy(iOSClassChain ="**/XCUIElementTypeButton[`name = 'fab'`]")
		@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.quicken.qm2014:id/fab']")
		public WebElement addTransactionButton;
		
		@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeStaticText' AND name  CONTAINS 'Shopping'")
		@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='category: Shopping']")
		public WebElement splitCatShop;
		
		@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeStaticText' AND name  CONTAINS 'Travel'")
		@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='category: Travel']")
		public WebElement splitCatTravel;
		
		public void navigateBackToDashboard() throws Exception{
			
			Verify.waitForObject(this.backButtonOnHeader, 1);
			backButtonOnHeader.click();
			Thread.sleep(10000);		
		}
		
		public WebElement getCurrentMonthYear() throws Exception {
			Helper h = new Helper();
			String[] monthNames = {"JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"};
			
			Calendar c= Calendar.getInstance();
			int cyear = c.get(Calendar.YEAR);
			int cmonth = c.get(Calendar.MONTH);
			String summaryMonthText = monthNames[cmonth] +" "+ cyear;
			String xPath_IOS = "//XCUIElementTypeStaticText[@name='"+summaryMonthText+"']";
			String xPath_ANDROID = "//android.widget.TextView[@text='"+summaryMonthText+"']";
			
			if (h.getEngine().equalsIgnoreCase("ios")) {
				 WebElement me = (WebElement) Engine.getDriver().findElement(By.xpath(xPath_IOS));
				 return me;
			} else {
				 WebElement me = (WebElement) Engine.getDriver().findElement(By.xpath(xPath_ANDROID));
				 return me;
			}
			
		}
		public String getCategoryName() {
			Helper h = new Helper();
			if (h.getEngine().equalsIgnoreCase("android")) 
				return this.transactionCategoryPayeeText.getText().trim().split(" ")[0];
			else
				return this.transactionCategoryPayeeText.getText().substring(10);
			//return this.transactionCategoryPayeeText.getText();
		}
		
		public String getPayeeName() {
			Helper h = new Helper();
			if (h.getEngine().equalsIgnoreCase("android")) 
				return this.transactionCategoryPayeeText.getText().trim().split(" ")[0];
			else
				return this.transactionCategoryPayeeText.getText().substring(7);
			//return this.transactionCategoryPayeeText.getText();
		}
		
		public void tapOnPayeeTab() throws Exception {

			Verify.waitForObject(this.payeeTab, 1);
			this.payeeTab.click();
			Thread.sleep(1000);
		}
}
