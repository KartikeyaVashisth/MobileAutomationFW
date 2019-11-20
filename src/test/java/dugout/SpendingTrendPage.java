package dugout;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

import java.util.HashMap;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
//import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
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
	
	//@iOSFindBy(xpath="//*[@name=\"You don't have any transactions.\"]")
	@iOSXCUITFindBy(iOSNsPredicate="'You don't have any transactions.'")
	@AndroidFindBy(xpath="//*[@text=\"You don't have any transactions.\"]")
	public MobileElement youDontHaveAnyTxns;
	
	@iOSFindBy(xpath="//*[@name=\"Last 30 Days\"]")
	@AndroidFindBy(xpath="//*[@text=\"Last 30 Days\"]")
	public MobileElement last30Days;
	
	@iOSFindBy(xpath="//*[@name=\"This Month\"]")
	@AndroidFindBy(xpath="//*[@text=\"This Month\"]")
	public MobileElement thisMonth;
	
	@iOSFindBy(xpath="//*[@name=\"Last Month\"]")
	@AndroidFindBy(xpath="//*[@text=\"Last Month\"]")
	public MobileElement lastMonth;
	
	@iOSFindBy(xpath="//*[@name=\"Month to Date\"]")
	@AndroidFindBy(xpath="//*[@text=\"Month to Date\"]")
	public MobileElement monthToDate;
	
	@iOSFindBy(xpath="//*[@name=\"Year to Date\"]")
	@AndroidFindBy(xpath="//*[@text=\"Year to Date\"]")
	public MobileElement yearToDate;
	
	@iOSFindBy(xpath="//XCUIElementTypeActivityIndicator[@name=\"In progress\"]")
	@AndroidFindBy(xpath="//android.widget.ProgressBar")
	public MobileElement progressBar;
	
	
	
	//@iOSFindBy(xpath="//XCUIElementTypeScrollView[descendant::XCUIElementTypeStaticText[contains(@name,'$')]]//XCUIElementTypeStaticText[not(contains(@name, '$'))]")
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name='horizontalScrollView']//XCUIElementTypeStaticText[not(contains(@name, '$')) and @visible='true']")
	@AndroidFindBy(xpath="//android.widget.HorizontalScrollView[descendant::android.widget.TextView[contains(@text,'$')]]//android.widget.TextView[not(contains(@text, '$'))]")
	public MobileElement categoryName;
	
	@iOSFindBy(xpath="//XCUIElementTypeScrollView//XCUIElementTypeStaticText[contains(@name, '$')]")
	//@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeScrollView/**/XCUIElementTypeStaticText[`name BEGINSWITH '$'`]")
	@AndroidFindBy(xpath="//android.widget.HorizontalScrollView//android.widget.TextView[contains(@text, '$')]")
	public MobileElement amount;
	
	//@iOSFindBy(xpath="//XCUIElementTypeScrollView[descendant::XCUIElementTypeStaticText[contains(@name, '$')]]")
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name='horizontalScrollView']")
	@AndroidFindBy(xpath="//android.widget.HorizontalScrollView[descendant::android.widget.TextView[contains(@text,'$')]]")
	public MobileElement scrollCategory;
	
	
	
	public void navigateBackToDashboard() throws Exception {
		
		this.backButtonOnHeader.click();
		Thread.sleep(3000);
		
		/*
		OverviewPage op = new OverviewPage();
		op.scrollView.swipe(SwipeElementDirection.DOWN, 10, 10, 1000);
		Thread.sleep(3000);
		op.scrollView.swipe(SwipeElementDirection.DOWN, 10, 10, 1000);
		Thread.sleep(3000);*/
		
	}
	
	public void scrollCategory_android() throws Exception{
		
		Dimension size = this.scrollCategory.getSize();
		System.out.println("Size is "+size);
		int y_start=(int)(size.width*0.90);        
        int x_start=(int)(size.width*0.46);//668
        
        int x_end=5;
       
//      Engine.ad.swipe(x_start,y,x_end,y,4000);
//    	Thread.sleep(1000);
    	TouchAction touchAction = new TouchAction(Engine.ad);
 
		
		touchAction
                .press(point(x_start, y_start))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(point(x_end, y_start))
                .release().perform();
			

	}
	
	public void scrollCategory_ios() throws Exception{
		
	 	MobileElement element = this.categoryName;
        String elementID = element.getId();
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("element", elementID); // Only for ‘scroll in element’
        scrollObject.put("direction", "right");
        Engine.iosd.executeScript("mobile:scroll", scrollObject);
        Thread.sleep(1000);
      
}
	
	public void scrollCategory() throws Exception{
		
		Helper h = new Helper();
		if (h.getEngine().equals("android"))
			scrollCategory_android();
		else
			scrollCategory_ios();	
		
	}
	
//	public boolean scrollToCategory(String sCategory) throws Exception{
//		
//		if (Verify.objExists_check(this.youDontHaveAnyTxns))
//			return false;
//		
//		if (getCategory().equals(sCategory))
//			return true;
//		
//		Integer iCount;
//		
//		// restricted scroll to only 4 times
//		for (iCount=0; iCount<4; iCount++) {
//			scrollCategory();
//			
//			if (getCategory().equals(sCategory))
//				return true;	
//		}	
//		
//		return false;
//		
//	}
	
	
	public String getCategory() throws Exception{
		
		String sCategory = this.categoryName.getText();
		
		sCategory = sCategory.split("Total for")[1].trim();
		
		return sCategory;
	}
	
	public Double getAmount() throws Exception{
		
		Helper h = new Helper();
		
		if (Verify.objExists_check(this.youDontHaveAnyTxns))
			return 0.0;
		
		return h.processBalanceAmount(this.amount.getText());
	
	}
	
	public void scrollFilter_IOS() throws Exception{
		
		MobileElement element = this.lastMonth;
        String elementID = element.getId();
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("element", elementID); // Only for ‘scroll in element’
        scrollObject.put("direction", "right");
        Engine.iosd.executeScript("mobile:scroll", scrollObject);
        Thread.sleep(1000);
	}
	
	public void scrollFilter_android() throws Exception{
    	
    	Dimension size = this.lastMonth.getSize();
    	int x_start=(int)(size.width*0.99);
    	int x_end=(int)(size.width*0.01);
    	int y=this.lastMonth.getRect().getY();
    	
  		TouchAction touchAction = new TouchAction(Engine.getDriver());
   	
  		touchAction
                  .press(point(x_start, y))
                  .waitAction(waitOptions(ofMillis(1000)))
                  .moveTo(point(x_end, y))
                  .release().perform();
	}
	
	public void scrollFilter() throws Exception{
		
		Helper h = new Helper();
		if (h.getEngine().equals("android"))
			scrollFilter_android();
		else
			scrollFilter_IOS();	
		
	}


}
