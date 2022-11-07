package dugout;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.touch.offset.PointOption;
import referee.Verify;
import support.Engine;
import support.Helper;
import support.Recovery;

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
	private MobileElement dateRangeText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains 'All Dates'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='All Dates']")
	private MobileElement allDatesChip;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains 'This Month'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='This Month']")
	private MobileElement thisMonthChip;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains 'Last Month'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Last Month']")
	private MobileElement lastMonthChip;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains 'Last 12 Months'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Last 12 Months']")
	private MobileElement last12MonthsChip;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains 'This Year'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='This Year']")
	private MobileElement thisYearChip;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains 'Last Year'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Last Year']")
	private MobileElement lastYearChip;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains 'Year to Date'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Year to Date']")
	private MobileElement yearToDateChip;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains 'Custom'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Custom']")
	private MobileElement customChip;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Sort by'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Sort by']")
	private MobileElement sortByText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains 'Newest to Oldest '`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Newest to Oldest ']")
	private MobileElement newestToOldestChip;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains 'Oldest to Newest '`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Oldest to Newest ']")
	private MobileElement oldestToNewestChip;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains 'Amount Low to High'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Amount Low to High']")
	private MobileElement amountLowToHighChip;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains 'Amount High to Low'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Amount High to Low']")
	private MobileElement amountHighToLowChip;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains 'Payee Name '`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Payee Name ']")
	private MobileElement payeeNameChip;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name contains 'Clear Status '`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Clear Status ']")
	private MobileElement clearStatusChip;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Filters'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Filters']")
	private MobileElement filtersText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'All Accounts'`][-1]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='All Accounts']")
	private MobileElement allAccountsoption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'All Categories'`][-1]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='All Categories']")
	private MobileElement allCategoriesoption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'All Payees'`][-1]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='All Payees']")
	private MobileElement allPayeesoption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'All Tags'`][-1]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='All Tags']")
	private MobileElement allTagsoption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Status'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Status']")
	private MobileElement statusText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Transactions'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Transactions']")
	private MobileElement transactionsText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Reviewed'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Reviewed']")
	private MobileElement reviewedOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Not Reviewed'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Not Reviewed']")
	private MobileElement notReviewedOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Cleared'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Cleared']")
	private MobileElement clearedOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Uncleared'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Uncleared']")
	private MobileElement unclearedOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Reconciled'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Reconciled']")
	private MobileElement reconciledOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Unreconciled'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Unreconciled']")
	private MobileElement unreconciledOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Uncategorized'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Uncategorized']")
	private MobileElement uncategorizedOption;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Clear'`]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Clear']")
	private MobileElement clearStatusButton;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Select All'`][-1]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Select All']")
	private MobileElement selectAllButton;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Clear All'`][-1]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Clear All']")
	private MobileElement clearAllButton;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Select Accounts'`][-1]")
	@AndroidFindBy(xpath = "//android.view.View[@text='Select Accounts']")
	private MobileElement selectAccountsHeaderText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Select Categories'`][-1]")
	@AndroidFindBy(xpath = "//android.view.View[@text='Select Categories']")
	private MobileElement selectCategoriesHeaderText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Select Payees'`][-1]")
	@AndroidFindBy(xpath = "//android.view.View[@text='Select Payees']")
	private MobileElement selectPayeesHeaderText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Select Tags'`][-1]")
	@AndroidFindBy(xpath = "//android.view.View[@text='Select Tags']")
	private MobileElement selectTagsHeaderText;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Apply'`][-1]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Apply']")
	private MobileElement applyButton;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name = 'Show Results'`][-1]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Show Results']")
	private MobileElement showResultsButton;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`name = 'search category'`]")
	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Search Category']")
	private MobileElement categorySearchBar;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`name = 'search payee'`]")
	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Search Payee']")
	private MobileElement payeeSearchBar;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`name = 'search tags'`]")
	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Search Tags']")
	private MobileElement tagsSearchBar;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name = 'Clear Filters'`]")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='Clear Filters']")
	private MobileElement clearFiltersButton;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name = 'sort icon filtered'`]")
	@AndroidFindBy(id = "com.quicken.qm2014:id/sortBtn")
	private MobileElement filteredSortIcon;

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
				Engine.getDriver().findElement(MobileBy.xpath(android_locator)).click();
				Thread.sleep(1000);
			}

			else if(filterName.equals("Payee Name") || filterName.equals("Clear Status")) {

				swipeSortByFilterChips();
				//tapOnFilter(filterName);
				String android_locator="//android.widget.TextView[@text='"+filterName+" ']";
				Engine.getDriver().findElement(MobileBy.xpath(android_locator)).click();
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
			Engine.getDriver().findElement(MobileBy.xpath(android_locator)).click();
		}
		else {
			String iOS_locator="**/XCUIElementTypeStaticText[`name = '"+accountName+"'`][-1]";
			Engine.getDriver().findElement(MobileBy.iOSClassChain(iOS_locator)).click();
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
			Engine.getDriver().findElement(MobileBy.xpath(android_locator)).click();
		}
		else {
			String iOS_locator="**/XCUIElementTypeStaticText[`name = '"+category+"'`][-1]";
			Engine.getDriver().findElement(MobileBy.iOSClassChain(iOS_locator)).click();
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
			Engine.getDriver().findElement(MobileBy.xpath(android_locator)).click();
		}
		else {
			String iOS_locator="**/XCUIElementTypeStaticText[`name = '"+payeeName+"'`][-1]";
			Engine.getDriver().findElement(MobileBy.iOSClassChain(iOS_locator)).click();
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
			Engine.getDriver().findElement(MobileBy.xpath(android_locator)).click();
		}
		else {
			String iOS_locator="**/XCUIElementTypeStaticText[`name = '"+tags+"'`][-1]";
			Engine.getDriver().findElement(MobileBy.iOSClassChain(iOS_locator)).click();
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

			TouchAction touchAction = new TouchAction(Engine.getDriver());

			for(int i=1 ;i<=3;i++) { 
				touchAction.press(PointOption.point(x_start, y)).waitAction(waitOptions(ofMillis(3000))).moveTo(PointOption.point(x_end, y)).release().perform();
			}
		}
		else {
			MobileElement element = this.last12MonthsChip;
			String elementID = element.getId();
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("element", elementID);
			scrollObject.put("direction", "left");
			Engine.getDriver().executeScript("mobile:swipe", scrollObject);
			Thread.sleep(1000);
		}
	}

	public void swipeSortByFilterChips() throws Exception {

		Helper h = new Helper();
		if(h.getEngine().equals("android")) {

			Verify.waitForObject(this.amountLowToHighChip, 1);
			Point location = this.amountLowToHighChip.getLocation();

			int x_start=location.getX();
			int x_end=100;
			int y=location.getY();

			TouchAction touchAction = new TouchAction(Engine.getDriver());

			for(int i=1 ;i<=2;i++) { 
				touchAction.press(PointOption.point(x_start, y)).waitAction(waitOptions(ofMillis(3000))).moveTo(PointOption.point(x_end, y)).release().perform();
			}
		}

		else {
			MobileElement element = this.amountLowToHighChip;
			String elementID = element.getId();
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("element", elementID);
			scrollObject.put("direction", "left");
			Engine.getDriver().executeScript("mobile:swipe", scrollObject);
			Thread.sleep(1000);
		}
	}

	public void scrollTransactionStatusFilterOptions() throws Exception {

		Helper h = new Helper();
		if (h.getEngine().equals("android")){
			Engine.getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"Uncategorized\").instance(0))"));
			Thread.sleep(1000);
		}
		else {
			MobileElement element = this.uncategorizedOption;
			String elementID = element.getId();
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("element", elementID);
			scrollObject.put("direction", "down");
			Engine.getDriver().executeScript("mobile:swipe", scrollObject);
			Thread.sleep(1000);
		}
	}

	public void tapOnFilter(String filterName) throws Exception {

		Helper h = new Helper();
		if(h.getEngine().equals("android")) {
			String android_locator="//android.widget.TextView[@text='"+filterName+"']";
			Engine.getDriver().findElement(MobileBy.xpath(android_locator)).click();
		}
		else {
			String iOS_locator="**/XCUIElementTypeStaticText[`name contains '"+filterName+"'`]";
			Engine.getDriver().findElement(MobileBy.iOSClassChain(iOS_locator)).click();
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
