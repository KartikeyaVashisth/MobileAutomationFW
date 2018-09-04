package dugout;

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
		

		@iOSFindBy(xpath="//*[starts-with(@name, 'Bank Account Balances Checking Accounts')]")
		@AndroidFindBy(xpath="//*[@text='Transaction Summary']")
		public MobileElement transactionSummaryHeader;
		
		@iOSFindBy(xpath="//*[starts-with(@name, 'Recent Transactions')]")
		@AndroidFindBy(xpath="//*[@text='Transaction Summary']/../*[@class='android.widget.ImageButton']")
		public MobileElement backButtonOnHeader;
		
		public void navigateBackToDashboard() throws Exception{
			
			backButtonOnHeader.click();
			Thread.sleep(5000);
			
			OverviewPage op = new OverviewPage();
			op.scrollView.swipe(SwipeElementDirection.DOWN, 10, 10, 1000);
			Thread.sleep(3000);
			op.scrollView.swipe(SwipeElementDirection.DOWN, 10, 10, 1000);
			Thread.sleep(3000);
			op.scrollView.swipe(SwipeElementDirection.DOWN, 10, 10, 1000);
			Thread.sleep(7000);
			
		}
}
