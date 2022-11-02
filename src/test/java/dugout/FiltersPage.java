package dugout;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import support.Engine;

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
	@AndroidFindBy(xpath = "")
	private MobileElement dateRangeText;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='All Dates'`]")
	@AndroidFindBy(xpath = "")
	private MobileElement allDatesChip;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='This Month'`]")
	@AndroidFindBy(xpath = "")
	private MobileElement thisMonthChip;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Last Month'`]")
	@AndroidFindBy(xpath = "")
	private MobileElement lastMonthChip;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Last 12 Months'`]")
	@AndroidFindBy(xpath = "")
	private MobileElement last12MonthsChip;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='This Year'`]")
	@AndroidFindBy(xpath = "")
	private MobileElement thisYearChip;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Last Year'`]")
	@AndroidFindBy(xpath = "")
	private MobileElement lastYearChip;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Year to Date'`]")
	@AndroidFindBy(xpath = "")
	private MobileElement yearToDateChip;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Custom'`]")
	@AndroidFindBy(xpath = "")
	private MobileElement customChip;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Sort by'`]")
	@AndroidFindBy(xpath = "")
	private MobileElement sortByText;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Newest to Oldest '`]")
	@AndroidFindBy(xpath = "")
	private MobileElement newestToOldestChip;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Oldest to Newest '`]")
	@AndroidFindBy(xpath = "")
	private MobileElement oldestToNewestChip;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Amount Low to High'`]")
	@AndroidFindBy(xpath = "")
	private MobileElement amountLowToHighChip;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Amount High to Low'`]")
	@AndroidFindBy(xpath = "")
	private MobileElement amountHighToLowChip;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Payee Name '`]")
	@AndroidFindBy(xpath = "")
	private MobileElement payeeNameChip;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Clear Status '`]")
	@AndroidFindBy(xpath = "")
	private MobileElement clearStatusChip;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Filters'`]")
	@AndroidFindBy(xpath = "")
	private MobileElement filtersText;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'All Accounts'`][-1]")
	@AndroidFindBy(xpath = "")
	private MobileElement allAccountsoption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'All Categories'`][-1]")
	@AndroidFindBy(xpath = "")
	private MobileElement allCategoriesoption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'All Payees'`][-1]")
	@AndroidFindBy(xpath = "")
	private MobileElement allPayeesoption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'All Tags'`][-1]")
	@AndroidFindBy(xpath = "")
	private MobileElement allTagsoption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name contains 'Status'`]")
	@AndroidFindBy(xpath = "")
	private MobileElement statusText;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Transactions'`]")
	@AndroidFindBy(xpath = "")
	private MobileElement transactionsText;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Reviewed'`]")
	@AndroidFindBy(xpath = "")
	private MobileElement reviewedOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Not Reviewed'`]")
	@AndroidFindBy(xpath = "")
	private MobileElement notReviewedOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Cleared'`]")
	@AndroidFindBy(xpath = "")
	private MobileElement clearedOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Uncleared'`]")
	@AndroidFindBy(xpath = "")
	private MobileElement unclearedOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Reconciled'`]")
	@AndroidFindBy(xpath = "")
	private MobileElement reconciledOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Unreconciled'`]")
	@AndroidFindBy(xpath = "")
	private MobileElement unreconciledOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Uncategorized'`]")
	@AndroidFindBy(xpath = "")
	private MobileElement uncategorizedOption;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name = 'Clear'`]")
	@AndroidFindBy(xpath = "")
	private MobileElement clearButton;
	
	
}
