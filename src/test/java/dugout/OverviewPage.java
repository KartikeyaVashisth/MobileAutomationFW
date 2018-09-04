package dugout;


import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import support.Engine;
import support.Helper;
import warroom.kEngine;

public class OverviewPage {
	public OverviewPage () {
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
	
	/*@iOSFindBy(xpath="//*[normalize-space(@name)='Overview']")
	@AndroidFindBy(xpath="//*[@text='Overview']")
	MobileElement overView;
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Sign Out']")
	@AndroidFindBy(xpath="//*[@text='Sign Out']")
	MobileElement signOut;
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Updates']")
	MobileElement updates;
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Settings']")
	@AndroidFindBy(xpath="//*[@content-desc='More options']")
	MobileElement SettingsOrMoreOptions;*/
	
	@iOSFindBy(xpath="//*[starts-with(@name, 'Banking and Credit Account') and not(contains(@name, 'Recent Transactions'))]")
	@AndroidFindBy(xpath="//*[@text='Banking and Credit Account']")
	public MobileElement accountsCard;
	
	@iOSFindBy(xpath="//*[starts-with(@name, 'Recent Transactions')]/XCUIElementTypeOther[starts-with(@name, 'Recent Transactions')]")
	@AndroidFindBy(xpath="//*[@text='Recent Transactions']")
	public MobileElement recentTransactionsCard;
	
	/*@iOSFindBy(xpath="//*[starts-with(@name, 'Top Spending Category')]")
	@AndroidFindBy(xpath="//*[@text='Top Spending Category']")
	public MobileElement spendingCard;*/
	
	/*@iOSFindBy(xpath="//*[starts-with(@name, 'Bank Account Balances Checking Accounts')]")
	@AndroidFindBy(xpath="//android.widget.ImageView")
	public MobileElement hambergerIcon;*/
	
	
	@iOSFindBy(xpath="//*[starts-with(@name, 'Top Trending Categories Last 30 days')]/XCUIElementTypeOther[starts-with(@name, 'Top Trending Categories Last 30 days')]")
	@AndroidFindBy(xpath="//*[@text='Top Trending Categories']/..")
	public MobileElement topTrendingCard;
	
	@iOSFindBy(xpath="//*[starts-with(@name, 'Transaction Summary')]/XCUIElementTypeOther[starts-with(@name, 'Transaction Summary')]")
	@AndroidFindBy(xpath="//*[@text='Transaction Summary']/../../*[@class='android.view.ViewGroup']")
	public MobileElement transactionSummaryCard;
	
	@iOSFindBy(xpath="//*[starts-with(@name, 'Investing Securities & Cash')]/XCUIElementTypeOther[starts-with(@name, 'Investing Securities & Cash')]")
	@AndroidFindBy(xpath="//*[@text='Investing']/..")
	public MobileElement investingCard;
	
	@iOSFindBy(xpath="//*[starts-with(@name, 'Investing Securities & Cash')]/XCUIElementTypeOther[contains(@name, 'Securities & Cash')]")
	@AndroidFindBy(xpath="//*[@text='Investing']/..//*[@text='Securities & Cash']")
	public MobileElement investingSecuritiesCashLabel;
	
	@iOSFindBy(xpath="//*[starts-with(@name, 'Investing Securities & Cash')]/XCUIElementTypeOther[contains(@name, 'Today\'s Change')]")
	@AndroidFindBy(xpath="//*[@text='Investing']/..//*[@text='Today\'s Change']")
	public MobileElement investingTodaysChange;
	
	
	// actual cards..
	@iOSFindBy(xpath="//*[starts-with(@name, 'Bank Account Balances Checking Accounts')]")
	@AndroidFindBy(xpath="//*[@text='Banking and Credit Account']/..")
	public MobileElement ac;
	
	@iOSFindBy(xpath="//*[starts-with(@name, 'Recent Transactions')]")
	@AndroidFindBy(xpath="//*[@text='Recent Transactions']/..")
	public MobileElement rt;
	
	@iOSFindBy(xpath="//*[starts-with(@name, 'Top Spending Category')]")
	@AndroidFindBy(xpath="//*[@text='Top Trending Categories']/..")
	public MobileElement ttc;
	
	@iOSFindBy(xpath="//*[starts-with(@name, 'Banking and Credit Account') and not(contains(@name, 'Recent Transactions'))]/XCUIElementTypeOther[starts-with(@name, 'Banking and Credit Account Checking')]")
	@AndroidFindBy(xpath="//*[@text='Banking and Credit Account']/..//*[@text='Checking']")
	public MobileElement accountsCard_checkingLabel;
	
	@iOSFindBy(xpath="//*[starts-with(@name, 'Banking and Credit Account') and not(contains(@name, 'Recent Transactions'))]/XCUIElementTypeOther[contains(@name, 'Savings')]")
	@AndroidFindBy(xpath="//*[@text='Banking and Credit Account']/..//*[@text='Savings']")
	public MobileElement accountsCard_savingsLabel;
	
	@iOSFindBy(xpath="//*[starts-with(@name, 'Banking and Credit Account') and not(contains(@name, 'Recent Transactions'))]/XCUIElementTypeOther[contains(@name, 'Credit')]")
	@AndroidFindBy(xpath="//*[@text='Banking and Credit Account']/..//*[@text='Credit']")
	public MobileElement accountsCard_creditLabel;
	
	
	@iOSFindBy(xpath="//*[starts-with(@name, 'Banking and Credit Account') and not(contains(@name, 'Recent Transactions'))]/XCUIElementTypeOther[contains(@name, 'Other')]")
	@AndroidFindBy(xpath="//*[@text='Banking and Credit Account']/..//*[@text='Other']")
	public MobileElement accountsCard_otherLabel;
	
	@iOSFindBy(xpath="//*[starts-with(@name, 'Banking and Credit Account') and not(contains(@name, 'Recent Transactions'))]/XCUIElementTypeOther[contains(@name, 'Total')]")
	@AndroidFindBy(xpath="//*[@text='Banking and Credit Account']/..//*[@text='Total']")
	public MobileElement accountsCard_totalLabel;
	
	@iOSFindBy(xpath="//*[starts-with(@name, 'Banking and Credit Account') and not(contains(@name, 'Recent Transactions'))]/XCUIElementTypeOther[contains(@name, 'Cash')]")
	@AndroidFindBy(xpath="//*[@text='Banking and Credit Account']/..//*[@text='Cash']")
	public MobileElement accountsCard_cashLabel;
	
	@iOSFindBy(xpath="//*[@name ='Recent Transactions No Transaction available')]")
	@AndroidFindBy(xpath="//*[@text='Recent Transactions']/../*[@text='No Transaction available']")
	public MobileElement recentTxns_NoTxnsAvaialable;
	
	@iOSFindBy(xpath="//*[@name=concat('Top Trending Categories Last 30 days You don',\"'\",'t have any transactions.')]")
	@AndroidFindBy(xpath="//*[@text='Top Trending Categories']/../*[@text=concat('You don',\"'\",'t have any transactions.')]")
	public MobileElement tredningCard_YoudontHaveAnyTxn;
	
	@iOSFindBy(xpath="//*[@name='Transaction Summary $0 Earned $0 Spent $0']")
	@AndroidFindBy(xpath="//*[@text='Transaction Summary']/../*[@text='$0 Earned']")
	public MobileElement txnSummary_zeroEarned;
	
	@iOSFindBy(xpath="//*[@name='Transaction Summary $0 Earned $0 Spent $0']")
	@AndroidFindBy(xpath="//*[@text='Transaction Summary']/../*[@text='$0 Spent']")
	public MobileElement txnSummary_zeroSpent;
	
	@iOSFindBy(xpath="//*[@name='Transaction Summary $0 Earned $0 Spent $0']")
	@AndroidFindBy(xpath="//*[@text='Transaction Summary']/../*[@text='$0']")
	public MobileElement txnSummary_zeroDollar;
	
	@iOSFindBy(xpath="//*[normalize-space(@name) ='Investing Track your investing accounts and holdings on your phone. Tap to learn more']")
	@AndroidFindBy(xpath="//*[@text='Investing']/../*[@text='Track your investing accounts and holdings on your phone.']")
	public MobileElement invCard_zeroDataState;
	
	@iOSFindBy(xpath="//*[@name='navigationMenu']")
	@AndroidFindBy(xpath="//*[@content-desc='navigationMenu']//*[@class='android.widget.ImageView']")
	public MobileElement hambergerIcon;
	
	@AndroidFindBy(xpath="//*[@class='android.widget.ScrollView']")
	public MobileElement scrollView;
	
	public void scrollUptoAccountsCard() throws Exception {
		
		scrollView.swipe(SwipeElementDirection.DOWN, 10, 10, 1000);
		Thread.sleep(3000);
		scrollView.swipe(SwipeElementDirection.DOWN, 10, 10, 1000);
		Thread.sleep(3000);
		scrollView.swipe(SwipeElementDirection.DOWN, 10, 10, 1000);
		Thread.sleep(7000);
		
	}
	
	
	
	
	
	
	

}
