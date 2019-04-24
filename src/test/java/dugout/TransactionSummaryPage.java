package dugout;

import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
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
		

		@iOSFindBy(xpath="//XCUIElementTypeOther[@name='Transaction Summary']")
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Transaction Summary']")
		public MobileElement transactionSummaryHeader;
		
		@iOSFindBy(xpath="//XCUIElementTypeButton[@name='Back']")
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Transaction Summary']/../android.widget.ImageButton")
		public MobileElement backButtonOnHeader;
		
		@iOSFindBy(xpath="//XCUIElementTypeOther[@name='button Category']")
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Category']")
		public MobileElement categoryTab;
		
		@iOSFindBy(xpath="//XCUIElementTypeOther[@name='button Payee']")
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Payee']")
		public MobileElement payeeTab;
		
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
}
