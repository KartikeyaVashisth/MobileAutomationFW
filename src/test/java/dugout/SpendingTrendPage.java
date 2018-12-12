package dugout;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import referee.Verify;
import support.Engine;
import support.Helper;

public class SpendingTrendPage {
	
	public SpendingTrendPage () {
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
	
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Spending Trend\"]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Spending Trend']")
	public MobileElement spendingTrendHeader;
	
	@iOSFindBy(xpath="//XCUIElementTypeButton[@name=\"Back\"]")
	@AndroidFindBy(xpath="//*[@text='Spending Trend']/../*[@class='android.widget.ImageButton']")
	public MobileElement backButtonOnHeader;
	
	public void navigateBackToDashboard() throws Exception {
		
		
		System.out.println(Verify.objExists(backButtonOnHeader)+" eeeeeeeeeee");
		this.backButtonOnHeader.click();
		Thread.sleep(10000);
		
		/*OverviewPage op = new OverviewPage();
		op.scrollView.swipe(SwipeElementDirection.DOWN, 10, 10, 1000);
		Thread.sleep(3000);
		op.scrollView.swipe(SwipeElementDirection.DOWN, 10, 10, 1000);
		Thread.sleep(7000);*/
		
	}
	
	

}
