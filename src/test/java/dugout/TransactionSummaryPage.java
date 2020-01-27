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
import support.Engine;
import support.Helper;

public class TransactionSummaryPage {
	
	
		public TransactionSummaryPage () {
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
		

		//@iOSFindBy(xpath="//XCUIElementTypeOther[@name='Transaction Summary']")
		@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name=='Transaction Summary'`]")
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Transaction Summary']")
		public MobileElement transactionSummaryHeader;
		
		//@iOSFindBy(xpath="//XCUIElementTypeButton[@name='Back']")
		@iOSXCUITFindBy(accessibility="Back")
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Transaction Summary']/../android.widget.ImageButton")
		public MobileElement backButtonOnHeader;
		
		//@iOSFindBy(xpath="//XCUIElementTypeOther[@name='button Category']")
		@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name='button Category'`]")
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Category']")
		public MobileElement categoryTab;
		
		//@iOSFindBy(xpath="//XCUIElementTypeOther[@name='button Payee']")
		@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name='button Payee'`]")
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Payee']")
		public MobileElement payeeTab;
		
		@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name BEGINSWITH 'walmart'`]")
		@AndroidFindBy(xpath="//android.widget.TextView[@text='walmart']/../android.widget.TextView[contains(@text,'$')]")
		public MobileElement payeeTile;
		
		@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name BEGINSWITH 'Internet'`]/XCUIElementTypeOther[1]")
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Internet']/../android.widget.TextView[contains(@text,'$')]")
		public MobileElement categoryTile;
		
		//@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"No Transactions by Category\"]")
		@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='No Transactions by Category'`]")
		@AndroidFindBy(xpath="//android.widget.TextView[@text='No Transactions by Category']")
		public MobileElement noTransactionCategory;
		
		//@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"No Transactions by Payee\"]")
		@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name='No Transactions by Payee'`]")
		@AndroidFindBy(xpath="//android.widget.TextView[@text='No Transactions by Payee']")
		public MobileElement noTransactionPayee;
		
		@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name CONTAINS '$'`]/XCUIElementTypeOther[15]")
		@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'$')]")
		public MobileElement firstRecordInList;
		
		@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[contains(@name, '$')][2]")
		@AndroidFindBy(xpath="//android.view.ViewGroup[@index=1][descendant::android.widget.TextView[contains(@text,'$')]]//android.widget.TextView[not(contains(@text, '$'))]")
		public MobileElement transactionCategoryPayeeText;
		
		public void navigateBackToDashboard() throws Exception{
			
			backButtonOnHeader.click();
			Thread.sleep(10000);		
		}
		
		public MobileElement getCurrentMonthYear() {
			Helper h = new Helper();
			String[] monthNames = {"JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"};
			
			Calendar c= Calendar.getInstance();
			int cyear = c.get(Calendar.YEAR);
			int cmonth = c.get(Calendar.MONTH);
			String summaryMonthText = monthNames[cmonth] +" "+ cyear;
			String xPath_IOS = "//XCUIElementTypeStaticText[@name='"+summaryMonthText+"']";
			String xPath_ANDROID = "//android.widget.TextView[@text='"+summaryMonthText+"']";
			
			if (h.getEngine().equalsIgnoreCase("ios")) {
				 MobileElement me = (MobileElement) Engine.iosd.findElement(By.xpath(xPath_IOS));
				 return me;
			} else {
				 MobileElement me = (MobileElement) Engine.ad.findElement(By.xpath(xPath_ANDROID));
				 return me;
			}
			
		}
		public String getCategoryPayeeName () {
			return this.transactionCategoryPayeeText.getText().trim().split(" ")[0];
			
		}
}
