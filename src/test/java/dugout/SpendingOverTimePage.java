package dugout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import support.Engine;
import support.Helper;

public class SpendingOverTimePage {
	
	
		public SpendingOverTimePage () {
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
		
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Spending Over Time']")
		public MobileElement spendingOverTimeHeader;
		
		@iOSFindBy(xpath="//*[normalize-space(@name)='Back']")
		@AndroidFindBy(xpath="//*[@class='android.widget.ImageButton']")
		public MobileElement backButtonOnHeader;
		
		@iOSFindBy(xpath="//*[normalize-space(@name)='Back']")
		@AndroidFindBy(xpath="//*[@resource-id='com.quicken.qm2014:id/list_row']")
		public MobileElement transactionList;
		
		@iOSFindBy(xpath="//*[normalize-space(@name)='Back']")
		@AndroidFindBy(xpath="//android.widget.TextView[starts-with(@text,'Total Spending: ')]")
		public MobileElement totalSpendingCurrentMonth;
		
		public void navigateBackToDashboard() throws Exception{
			
			backButtonOnHeader.click();
			Thread.sleep(10000);
			
			
		}
		
		
		

}
