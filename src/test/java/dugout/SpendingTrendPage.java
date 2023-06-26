package dugout;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumBy;
//import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.touch.offset.PointOption;
import referee.Commentary;
import referee.Verify;
import support.Engine;
import support.Helper;

public class SpendingTrendPage {
	
	public SpendingTrendPage () {
		try {
			
			PageFactory.initElements(new AppiumFieldDecorator(Engine.getDriver()),this);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
//	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Spending by Category\"]")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeOther[`name='Spending by Category'`]")
	@AndroidFindBy(xpath="//android.view.View[@text='Spending by Category']")
	public WebElement spendingTrendHeader;
	
//	@iOSFindBy(xpath="//XCUIElementTypeButton[@name=\"Back\"]")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeButton[`name='Go back'`]")
	@AndroidFindBy(xpath="//*[@text='Spending by Category']/../*[@class='android.widget.Button']")
	public WebElement backButtonOnHeader;
	
//	@iOSFindBy(xpath="//*[@name=\"You don't have any transactions.\"]")
//	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name=\"You don't have any transactions.\"`]")
	@iOSXCUITFindBy(iOSNsPredicate = "name = \"You don't have any transactions.\"")
	@AndroidFindBy(xpath="//*[@text=\"You don't have any transactions.\"]")
	public WebElement youDontHaveAnyTxns;
	
//	@iOSFindBy(xpath="//*[@name=\"Last 30 Days\"]")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name == 'Last 30 Days'`]")
	@AndroidFindBy(xpath="//*[@text=\"Last 30 Days\"]")
	public WebElement last30Days;
	
//	@iOSFindBy(xpath="//*[@name=\"This Month\"]")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name == 'This Month'`]")
	@AndroidFindBy(xpath="//*[@text=\"This Month\"]")
	public WebElement thisMonth;
	
//	@iOSFindBy(xpath="//*[@name=\"Last Month\"]")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name == 'Last Month'`]")
	@AndroidFindBy(xpath="//*[@text=\"Last Month\"]")
	public WebElement lastMonth;
	
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name == 'Last 3 Months'`]")
	@AndroidFindBy(xpath="//*[@text=\"Last 3 Months\"]")
	public WebElement last3Months;
	
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name == 'Last 6 Months'`]")
	@AndroidFindBy(xpath="//*[@text=\"Last 6 Months\"]")
	public WebElement last6Months;
	
//	@iOSFindBy(xpath="//*[@name=\"Month to Date\"]")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name == 'Month to Date'`]")
	@AndroidFindBy(xpath="//*[@text=\"Month to Date\"]")
	public WebElement monthToDate;
	
//	@iOSFindBy(xpath="//*[@name=\"Year to Date\"]")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name == 'Year to Date'`]")
	@AndroidFindBy(xpath="//*[@text=\"Year to Date\"]")
	public WebElement yearToDate;
	
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name == 'Custom'`]")
	@AndroidFindBy(xpath="//*[@text=\"Custom\"]")
	public WebElement custom;
	
//	@iOSFindBy(xpath="//XCUIElementTypeActivityIndicator[@name=\"In progress\"]")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeActivityIndicator[`name == 'In progress'`]")
	@AndroidFindBy(xpath="//android.widget.ProgressBar")
	public WebElement progressBar;
	
//	@iOSFindBy(xpath="//XCUIElementTypeScrollView[descendant::XCUIElementTypeStaticText[contains(@name,'$')]]//XCUIElementTypeStaticText[not(contains(@name, '$'))]")
//	@iOSFindBy(xpath="//XCUIElementTypeOther[@name='horizontalScrollView']//XCUIElementTypeStaticText[not(contains(@name, '$')) and @visible='true']")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name== 'bottomLeftLabel'`][1]")
	@AndroidFindBy(xpath="//android.widget.HorizontalScrollView[descendant::android.widget.TextView[contains(@text,'$')]]//android.widget.TextView[not(contains(@text, '$'))]")
	public WebElement categoryName;
	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeStaticText' AND  name BEGINSWITH[c] 'Total for'")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@content-desc,'Total for ')]")
	public WebElement categoryName1;
	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeStaticText' AND name BEGINSWITH[c] 'amount: '")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@content-desc,'amount:')]")
	public WebElement amount1;
	
//	@iOSFindBy(xpath="//XCUIElementTypeScrollView//XCUIElementTypeStaticText[contains(@name, '$')]")
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeScrollView/**/XCUIElementTypeStaticText[`name CONTAINS '$'  AND visible == 1`]")
	@AndroidFindBy(xpath="//android.widget.HorizontalScrollView//android.widget.TextView[contains(@text, '$')]")
	public WebElement amount;
	
//	@iOSFindBy(xpath="//XCUIElementTypeScrollView[descendant::XCUIElementTypeStaticText[contains(@name, '$')]]")
//	@iOSFindBy(xpath="(//XCUIElementTypeOther[@name='horizontalScrollView'])[1]")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name='horizontalScrollView'`][1]")
	@AndroidFindBy(xpath="//android.widget.HorizontalScrollView[descendant::android.widget.TextView[contains(@text,'$')]]")
	public WebElement scrollCategory;
	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeStaticText' AND name = 'topRightLabel'")
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'list_row_amount')]")
	public WebElement commonAmount;
	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeStaticText' AND name = 'category: Travel'")
	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='category: Travel']")
	public WebElement splitTravelCat;
	
	@iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeStaticText' AND name = 'category: Shopping'")
	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='category: Shopping']")
	public WebElement splitShoppingCat;
	
	
	public void navigateBackToDashboard() throws Exception {
		
		this.backButtonOnHeader.click();
		Thread.sleep(3000);
	}
	
	public void scrollCategory_android() throws Exception{

		Dimension size = this.scrollCategory.getSize();

		int y_start=(int)(size.width*0.90);        
		int x_start=(int)(size.width*0.46);//668
		int x_end=3;

//		TouchAction touchAction = new TouchAction(Engine.getDriver());
//
//		touchAction
//		.press(point(x_start, y_start))
//		.waitAction(waitOptions(ofMillis(1000)))
//		.moveTo(point(x_end, y_start))
//		.release().perform();
	}

	public void scrollCategory_ios() throws Exception{
		
//	 	WebElement element = this.categoryName;
//		WebElement element = this.scrollCategory;
//        String elementID = element.getId();
//        HashMap<String, String> scrollObject = new HashMap<String, String>();
//        scrollObject.put("element", elementID); // Only for ‘scroll in element’
//        scrollObject.put("direction", "right");
//        Engine.getDriver().executeScript("mobile:scroll", scrollObject);
//        Thread.sleep(1000);
      
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
		Helper h = new Helper();
		Verify.waitForObject(this.categoryName, 1);
		if (h.getEngine().equalsIgnoreCase("android")) {
			String sCategory = this.categoryName.getText();
			sCategory = sCategory.split("Total for")[1].trim();
			return sCategory;
		} else {
			String sCategory = this.categoryName.getAttribute("value");
			return sCategory;
		}
	}

	public Double getAmount() throws Exception{

		Helper h = new Helper();

		if (Verify.objExists_check(this.youDontHaveAnyTxns))
			return 0.0;

		Verify.waitForObject(amount, 2);
		return h.processBalanceAmount(this.amount.getText());

	}

	public void scrollFilter_IOS() throws Exception{

//		WebElement element = this.last3Months;
//		String elementID = element.getId();
//		HashMap<String, String> scrollObject = new HashMap<String, String>();
//		scrollObject.put("element", elementID);
//		scrollObject.put("direction", "left");
//		Engine.getDriver().executeScript("mobile:swipe", scrollObject);
//		Thread.sleep(1000);
	}

	public void scrollFilter_android() throws Exception{

		Verify.waitForObject(this.last3Months, 1);
		Point location = this.last3Months.getLocation();

		int x_start=location.getX();
		int x_end=100;
		int y=location.getY();

//		TouchAction touchAction = new TouchAction(Engine.getDriver());
//
//		for(int i=1 ;i<=2;i++) { 
//			touchAction.press(PointOption.point(x_start, y)).waitAction(waitOptions(ofMillis(3000))).moveTo(PointOption.point(x_end, y)).release().perform();
//		}
	}
	
	public void scrollFilter() throws Exception{

		Helper h = new Helper();
		if (h.getEngine().equals("android"))
			scrollFilter_android();
		else
			scrollFilter_IOS();		
	}

	public void navigateToTopTrendingCategoriesCard() throws Exception {

		SettingsPage sp = new SettingsPage();
		sp.clickOnReportsOption();

		Verify.waitForObject(sp.spendingByCategoryOption, 1);
		sp.spendingByCategoryOption.click();
		Thread.sleep(1000);
	}

	public void deleteSpendingTrendTransactions() throws Exception{

		Helper h = new Helper();

		if (h.getEngine().equals("ios"))
			deleteSpendingTrendTransactions_ios();
		else
			deleteSpendingTrendTransactions_android();
	}

	public void deleteSpendingTrendTransactions_ios() throws Exception {


		if(Verify.objExists(youDontHaveAnyTxns))
			Commentary.log(LogStatus.INFO, "No transaction is available");
		else {
			Commentary.log(LogStatus.INFO, "Transactions are available and we are going to delete them.");

			int i = getNumberOfRows();

			System.out.println("Number of  Transaction Rows found "+i);

			for (int j = 1; j<=i; j++) {	
				commonAmount.click();
				Thread.sleep(500);

				TransactionDetailPage td = new TransactionDetailPage();
				td.deleteTransation();
				Thread.sleep(1000);
			}
		}
	}

	public void deleteSpendingTrendTransactions_android() throws Exception{


		if(Verify.objExists(youDontHaveAnyTxns))
			Commentary.log(LogStatus.INFO, "No transaction is available");
		else {
			Commentary.log(LogStatus.INFO, "Transactions are available and we are going to delete them.");

			while(getNumberOfRows() > 0) {

				Thread.sleep(2000);
				commonAmount.click();
				Thread.sleep(3000);

				TransactionDetailPage td = new TransactionDetailPage();
				td.scroll_down();
				td.deleteTransaction.click();
				Thread.sleep(1000);
				td.deleteTransactionAlertButton.click();
				Thread.sleep(1000);
			}
		}	

	}

	public int getNumberOfRows() throws Exception {

		Helper h = new Helper();

		if (h.getEngine().equals("ios")){
			List <WebElement> me = Engine.getDriver().findElements(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`name = 'topRightLabel'`]"));
			return me.size();
		}
		else {
			List <WebElement> me = Engine.getDriver().findElements(By.xpath("//android.widget.TextView[contains(@resource-id,'list_row_amount')]"));
			return me.size();
		}
	}

}
