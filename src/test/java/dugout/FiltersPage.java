package dugout;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

import java.time.Duration;
import java.util.Collections;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.PointerInput.MouseButton;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumBy;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.touch.offset.PointOption;
import referee.Verify;
import support.Engine;
import support.Helper;

public class FiltersPage {

	public FiltersPage() {

		try {
			PageFactory.initElements(new AppiumFieldDecorator(Engine.getDriver()), this);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Date Range'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Date Range']")
	private WebElement dateRangeText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains 'All Dates'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='All Dates']")
	private WebElement allDatesChip;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains 'This Month'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='This Month']")
	private WebElement thisMonthChip;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains 'Last Month'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Last Month']")
	private WebElement lastMonthChip;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains 'Last 12 Months'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Last 12 Months']")
	private WebElement last12MonthsChip;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains 'This Year'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='This Year']")
	private WebElement thisYearChip;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains 'Last Year'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Last Year']")
	private WebElement lastYearChip;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains 'Year to Date'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Year to Date']")
	private WebElement yearToDateChip;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains 'Custom'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Custom']")
	private WebElement customChip;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Sort by'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Sort by']")
	private WebElement sortByText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains 'Newest to Oldest '`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Newest to Oldest ']")
	private WebElement newestToOldestChip;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains 'Oldest to Newest '`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Oldest to Newest ']")
	private WebElement oldestToNewestChip;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains 'Amount Low to High'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Amount Low to High']")
	private WebElement amountLowToHighChip;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains 'Amount High to Low'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Amount High to Low']")
	private WebElement amountHighToLowChip;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains 'Payee Name '`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Payee Name ']")
	private WebElement payeeNameChip;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains 'Clear Status '`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Clear Status ']")
	private WebElement clearStatusChip;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Filters'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Filters']")
	private WebElement filtersText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'All Accounts'`][-1]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='All Accounts']")
	private WebElement allAccountsoption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'All Categories'`][-1]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='All Categories']")
	private WebElement allCategoriesoption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'All Payees'`][-1]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='All Payees']")
	private WebElement allPayeesoption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'All Tags'`][-1]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='All Tags']")
	private WebElement allTagsoption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Status'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Status']")
	private WebElement statusText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Transactions'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Transactions']")
	private WebElement transactionsText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Reviewed'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Reviewed']")
	private WebElement reviewedOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Not Reviewed'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Not Reviewed']")
	private WebElement notReviewedOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Cleared'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Cleared']")
	private WebElement clearedOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Uncleared'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Uncleared']")
	private WebElement unclearedOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Reconciled'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Reconciled']")
	private WebElement reconciledOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Unreconciled'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Unreconciled']")
	private WebElement unreconciledOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Uncategorized'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Uncategorized']")
	private WebElement uncategorizedOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Clear'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Clear']")
	private WebElement clearStatusButton;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Select All'`][-1]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Select All']")
	private WebElement selectAllButton;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Clear All'`][-1]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Clear All']")
	private WebElement clearAllButton;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Select Accounts'`][-1]")
	@AndroidFindBy(xpath = "//android.view.View[@text='Select Accounts']")
	private WebElement selectAccountsHeaderText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Select Categories'`][-1]")
	@AndroidFindBy(xpath = "//android.view.View[@text='Select Categories']")
	private WebElement selectCategoriesHeaderText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Select Payees'`][-1]")
	@AndroidFindBy(xpath = "//android.view.View[@text='Select Payees']")
	private WebElement selectPayeesHeaderText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Select Tags'`][-1]")
	@AndroidFindBy(xpath = "//android.view.View[@text='Select Tags']")
	private WebElement selectTagsHeaderText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Apply'`][-1]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Apply']")
	private WebElement applyButton;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Show Results'`][-1]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Show Results']")
	private WebElement showResultsButton;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`name = 'search category'`]")
	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Search Category']")
	private WebElement categorySearchBar;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`name = 'search payee'`]")
	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Search Payee']")
	private WebElement payeeSearchBar;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`name = 'search tags'`]")
	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Search Tags']")
	private WebElement tagsSearchBar;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name = 'Clear Filters'`]")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='Clear Filters']")
	private WebElement clearFiltersButton;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name = 'sort icon filtered'`]")
	@AndroidFindBy(id = "com.quicken.qm2014:id/sortBtn")
	private WebElement filteredSortIcon;

	public void selectFilter(String filterName) throws Exception {

		Helper h = new Helper();
		if(h.getEngine().equals("android")) {

			if(filterName.equals("This Year") || filterName.equals("Last Year") || filterName.equals("Year to Date") || filterName.equals("Custom")) {

				swipeDateRangeChips();
				tapOnFilter(filterName);
			}

			else if(filterName.equals("Newest to Oldest") || filterName.equals("Oldest to Newest")) {

				//tapOnFilter(filterName);
				String android_locator="//android.widget.TextView[@text='"+filterName+" ']";
				Engine.getDriver().findElement(By.xpath(android_locator)).click();
				Thread.sleep(1000);
			}

			else if(filterName.equals("Payee Name") || filterName.equals("Clear Status")) {

				swipeSortByFilterChips();
				//tapOnFilter(filterName);
				String android_locator="//android.widget.TextView[@text='"+filterName+" ']";
				Engine.getDriver().findElement(By.xpath(android_locator)).click();
				Thread.sleep(1000);
			}

			else if(filterName.equals("Cleared") || filterName.equals("Uncleared") || filterName.equals("Reconciled") || filterName.equals("Unreconciled") || filterName.equalsIgnoreCase("Uncategorized")) {

				scrollTransactionStatusFilterOptions();
				tapOnFilter(filterName);
			}

			else {
				tapOnFilter(filterName);
				Thread.sleep(1000);
			}

		}
		else {

			if(filterName.equals("This Year") || filterName.equals("Last Year") || filterName.equals("Year to Date") || filterName.equals("Custom")) {

				swipeDateRangeChips();
				tapOnFilter(filterName);
			}

			else if(filterName.equals("Amount High To Low") || filterName.equals("Payee Name") || filterName.equals("Clear Status")) {

				swipeSortByFilterChips();
				tapOnFilter(filterName);
				Thread.sleep(1000);
			}

			else if(filterName.equals("Cleared") || filterName.equals("Uncleared") || filterName.equals("Reconciled") || filterName.equals("Unreconciled") || filterName.equalsIgnoreCase("Uncategorized")) {

				scrollTransactionStatusFilterOptions();
				tapOnFilter(filterName);
			}

			else {
				tapOnFilter(filterName);
				Thread.sleep(1000);
			}
		}
	}

	public void selectAccountsFilter(String accountName) throws Exception {

		Helper h = new Helper();
		this.allAccountsoption.click();
		Verify.waitForObject(this.selectAccountsHeaderText, 1);
		clearAllButton.click();
		Thread.sleep(500);

		if(h.getEngine().equals("android")) {
			String android_locator="(//android.widget.TextView[@text = '"+accountName+"'])[1]";
			Engine.getDriver().findElement(By.xpath(android_locator)).click();
		}
		else {
			String iOS_locator="**/XCUIElementTypeStaticText[`name = '"+accountName+"'`][-1]";
			Engine.getDriver().findElement(AppiumBy.iOSClassChain(iOS_locator)).click();
		}
		Thread.sleep(1000);
		applyButton.click();
	}

	public void selectCategoriesFilter(String category) throws Exception {

		Helper h = new Helper();
		this.allCategoriesoption.click();
		Verify.waitForObject(this.selectCategoriesHeaderText, 1);
		clearAllButton.click();
		Thread.sleep(500);
		this.categorySearchBar.click();
		this.categorySearchBar.sendKeys(category);
		Thread.sleep(1000);

		if(h.getEngine().equals("android")) {
			String android_locator = "//android.widget.TextView[@text = '"+category+"']/preceding-sibling::android.view.ViewGroup[@content-desc='RadioButton']";
			Engine.getDriver().findElement(By.xpath(android_locator)).click();
		}
		else {
			String iOS_locator="**/XCUIElementTypeStaticText[`name = '"+category+"'`][-1]";
			Engine.getDriver().findElement(AppiumBy.iOSClassChain(iOS_locator)).click();
		}

		h.hideKeyBoard();
		Thread.sleep(1000);
		applyButton.click();
	}

	public void selectPayeesFilter(String payeeName) throws Exception {

		Helper h = new Helper();

		this.allPayeesoption.click();
		Verify.waitForObject(this.selectPayeesHeaderText, 1);
		clearAllButton.click();
		Thread.sleep(500);
		this.payeeSearchBar.click();
		this.payeeSearchBar.sendKeys(payeeName);
		Thread.sleep(1000);

		if(h.getEngine().equals("android")) {
			String android_locator="//android.widget.TextView[@text = '"+payeeName+"']/preceding-sibling::android.view.ViewGroup[contains(@content-desc,'RadioButton')]";
			Engine.getDriver().findElement(By.xpath(android_locator)).click();
		}
		else {
			String iOS_locator="**/XCUIElementTypeStaticText[`name = '"+payeeName+"'`][-1]";
			Engine.getDriver().findElement(AppiumBy.iOSClassChain(iOS_locator)).click();
		}

		h.hideKeyBoard();
		Thread.sleep(1000);
		applyButton.click();
	}

	public void selectTagsFilter(String tags) throws Exception {

		Helper h = new Helper();
		this.allAccountsoption.click();
		Verify.waitForObject(this.selectAccountsHeaderText, 1);
		clearAllButton.click();
		Thread.sleep(500);
		this.tagsSearchBar.click();
		this.tagsSearchBar.sendKeys(tags);
		Thread.sleep(1000);

		if(h.getEngine().equals("android")) {
			String android_locator="//android.widget.TextView[@text = '"+tags+"']/preceding-sibling::android.view.ViewGroup[contains(@content-desc,'RadioButton')]";
			Engine.getDriver().findElement(By.xpath(android_locator)).click();
		}
		else {
			String iOS_locator="**/XCUIElementTypeStaticText[`name = '"+tags+"'`][-1]";
			Engine.getDriver().findElement(AppiumBy.iOSClassChain(iOS_locator)).click();
		}

		h.hideKeyBoard();
		Thread.sleep(1000);
		applyButton.click();
	}

	public void swipeDateRangeChips() throws Exception {

		Helper h = new Helper();
		if(h.getEngine().equals("android")) {

			Verify.waitForObject(this.last12MonthsChip, 1);
			Point location = this.last12MonthsChip.getLocation();

			int x_start=location.getX();
			int x_end=100;
			int y=location.getY();

//			TouchAction touchAction = new TouchAction(Engine.getDriver());
//
//			for(int i=1 ;i<=3;i++) { 
//				touchAction.press(PointOption.point(x_start, y)).waitAction(waitOptions(ofMillis(3000))).moveTo(PointOption.point(x_end, y)).release().perform();
			
			PointerInput finger1 = new PointerInput(Kind.TOUCH, "finger1");
			Sequence sequence = new Sequence(finger1, 1)
					.addAction(finger1.createPointerMove(Duration.ZERO, Origin.viewport(), x_start, y)) 
					.addAction(finger1.createPointerDown(MouseButton.LEFT.asArg())) 
					.addAction(new Pause(finger1, Duration.ofMillis(200)))
					.addAction(finger1.createPointerMove(Duration.ofMillis(100), Origin.viewport(), x_end, y)) 
					.addAction(finger1.createPointerUp(MouseButton.LEFT.asArg()));
			
			Engine.getDriver().perform(Collections.singletonList(sequence));
			
			}
		
		else {
//			WebElement element = this.last12MonthsChip;
//			String elementID = element.getId();
//			HashMap<String, String> scrollObject = new HashMap<String, String>();
//			scrollObject.put("element", elementID);
//			scrollObject.put("direction", "left");
//			Engine.getDriver().executeScript("mobile:swipe", scrollObject);
//			Thread.sleep(1000);
			
			Verify.waitForObject(this.last12MonthsChip, 1);
			Point location = this.last12MonthsChip.getLocation();

			int x_start=location.getX();
			int x_end=30;
			int y=location.getY();
			
			PointerInput finger1 = new PointerInput(Kind.TOUCH, "finger1");
			Sequence sequence = new Sequence(finger1, 1)
					.addAction(finger1.createPointerMove(Duration.ZERO, Origin.viewport(), x_start, y)) 
					.addAction(finger1.createPointerDown(MouseButton.LEFT.asArg())) 
					.addAction(new Pause(finger1, Duration.ofMillis(200)))
					.addAction(finger1.createPointerMove(Duration.ofMillis(100), Origin.viewport(), x_end, y)) 
					.addAction(finger1.createPointerUp(MouseButton.LEFT.asArg()));
			
			Engine.getDriver().perform(Collections.singletonList(sequence));
		}
	}

	public void swipeSortByFilterChips() throws Exception {

		Helper h = new Helper();
		if(h.getEngine().equals("android")) {

			Verify.waitForObject(this.amountLowToHighChip, 1);
			Point location = this.amountLowToHighChip.getLocation();

			int x_start=location.getX();
			int x_end=30;
			int y=location.getY();

//			TouchAction touchAction = new TouchAction(Engine.getDriver());
//
//			for(int i=1 ;i<=2;i++) { 
//				touchAction.press(PointOption.point(x_start, y)).waitAction(waitOptions(ofMillis(3000))).moveTo(PointOption.point(x_end, y)).release().perform();
			
			PointerInput finger1 = new PointerInput(Kind.TOUCH, "finger1");
			Sequence sequence = new Sequence(finger1, 1)
					.addAction(finger1.createPointerMove(Duration.ZERO, Origin.viewport(), x_start, y)) //Duration.Zero means that the pointer will immediately move to the start point.
					.addAction(finger1.createPointerDown(MouseButton.LEFT.asArg())) //This will click on left button of the mouse by pushing the finger down.
					.addAction(new Pause(finger1, Duration.ofMillis(200))) //This will hold for the set duration, keeping the left button of mouse clicked.
					.addAction(finger1.createPointerMove(Duration.ofMillis(100), Origin.viewport(), x_end, y)) //This will move the finger
					.addAction(finger1.createPointerUp(MouseButton.LEFT.asArg())); //This will lift the finger up from the mouse button.
			
			Engine.getDriver().perform(Collections.singletonList(sequence));
			}

		else {
//			WebElement element = this.amountLowToHighChip;
//			String elementID = element.getId();
//			HashMap<String, String> scrollObject = new HashMap<String, String>();
//			scrollObject.put("element", elementID);
//			scrollObject.put("direction", "left");
//			Engine.getDriver().executeScript("mobile:swipe", scrollObject);
//			Thread.sleep(1000);
			
			Verify.waitForObject(this.amountLowToHighChip, 1);
			Point location = this.amountLowToHighChip.getLocation();

			int x_start=location.getX();
			int x_end=30;
			int y=location.getY();
			
			PointerInput finger1 = new PointerInput(Kind.TOUCH, "finger1");
			Sequence sequence = new Sequence(finger1, 1)
					.addAction(finger1.createPointerMove(Duration.ZERO, Origin.viewport(), x_start, y)) 
					.addAction(finger1.createPointerDown(MouseButton.LEFT.asArg())) 
					.addAction(new Pause(finger1, Duration.ofMillis(200)))
					.addAction(finger1.createPointerMove(Duration.ofMillis(100), Origin.viewport(), x_end, y)) 
					.addAction(finger1.createPointerUp(MouseButton.LEFT.asArg()));
			
			Engine.getDriver().perform(Collections.singletonList(sequence));
		}
	}

	public void scrollTransactionStatusFilterOptions() throws Exception {

		Helper h = new Helper();
		if (h.getEngine().equals("android")){
			Engine.getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"Uncategorized\").instance(0))"));
			Thread.sleep(1000);
		}
		else {
//			WebElement element = this.uncategorizedOption;
//			String elementID = element.getId();
//			HashMap<String, String> scrollObject = new HashMap<String, String>();
//			scrollObject.put("element", elementID);
//			scrollObject.put("direction", "down");
//			Engine.getDriver().executeScript("mobile:swipe", scrollObject);
//			Thread.sleep(1000);
			
			Verify.waitForObject(this.reviewedOption, 1);
			Point location = this.reviewedOption.getLocation();

			int x=location.getX();
			int y_start=location.getY();
			int y_end=300;
			
			PointerInput finger1 = new PointerInput(Kind.TOUCH, "finger1");
			Sequence sequence = new Sequence(finger1, 1)
					.addAction(finger1.createPointerMove(Duration.ZERO, Origin.viewport(), x, y_start)) 
					.addAction(finger1.createPointerDown(MouseButton.LEFT.asArg())) 
					.addAction(new Pause(finger1, Duration.ofMillis(200)))
					.addAction(finger1.createPointerMove(Duration.ofMillis(100), Origin.viewport(), x, y_end)) 
					.addAction(finger1.createPointerUp(MouseButton.LEFT.asArg()));
			
			Engine.getDriver().perform(Collections.singletonList(sequence));
		}
	}

	public void tapOnFilter(String filterName) throws Exception {

		Helper h = new Helper();
		if(h.getEngine().equals("android")) {
			String android_locator="//android.widget.TextView[@text='"+filterName+"']";
			Engine.getDriver().findElement(By.xpath(android_locator)).click();
		}
		else {
			String iOS_locator="**/XCUIElementTypeStaticText[`name contains '"+filterName+"'`]";
			Engine.getDriver().findElement(AppiumBy.iOSClassChain(iOS_locator)).click();
		}
	}

	public void clickShowResultsButton() throws Exception {
		Verify.waitForObject(showResultsButton, 1);
		this.showResultsButton.click();
		Thread.sleep(1000);
	}

	public void clickClearFilters() throws Exception {
		Verify.waitForObject(clearFiltersButton, 1);
		this.clearFiltersButton.click();
		Thread.sleep(1000);
	}

	public void clickFilteredSortIcon() throws Exception {
		Verify.waitForObject(filteredSortIcon, 1);
		this.filteredSortIcon.click();
		Thread.sleep(1000);
	}

	public boolean isClearFiltersButtonDisplayed() {

		try{
			boolean b = this.clearFiltersButton.isDisplayed();
			return b;	
		}
		catch (NoSuchElementException n){
			return false;	
		}

	}

}
