package tournament;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.AllAccountsPage;
import dugout.BankingAndCreditCardPage;
import dugout.OverviewPage;
import dugout.SignInPage;
import dugout.TransactionDetailPage;
import dugout.TransactionsPage;
import dugout.WelcomePage;
import referee.Commentary;
import referee.Verify;
import support.CategoryRecord;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;
import support.UserName;

public class CategoryOnTheFly_Test2 extends Recovery{

	//String sUserName = "mobileautomation1@quicken.com";
	String sPassword = "Quicken@01";
	String sDataset = "categoryOnFly";
	String sChecking = "sChecking";
	String backButton1_ios = "Banking & Credit, back";
	String sSavings = "sSavings";
	//String sDataset1 = "OnlineAcc_Automation";
	
	
	public String getUsername_basedOnEnv() throws Exception{

		UserName un = new UserName();
		un.stage_ios = "categoryflymobile1++@stage.com";
		un.stage_android = "categoryonflyAndroid1++@stage.com";
		un.prod_ios = "";
		un.prod_android = "";
		return un.getUserName();	
	}
	
	//****** This piece of code we can enable when we want to use the Testrail integration with our project . Based on test case status the status on Testrail will get update for each testcase ******	
//	@Override
//	@BeforeTest
//	@Parameters({"host","engine","test","env","RUN_ID"})
//	public void beforeTestEnter(@Optional("readFromPropertiesFile")String host, @Optional("readFromPropertiesFile")String engine, @Optional("readFromPropertiesFile")String testName, @Optional("readFromPropertiesFile")String env, @Optional("readFromPropertiesFile")String RUN_ID) throws Exception{
//		this.testRunId.set("2330");
//		super.beforeTestEnter(host, engine, testName, env, RUN_ID);
//		
//	}
	
	
	@Test(priority = 1, enabled = true)
	public void TR1_test() throws Exception{

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		String sUsername = getUsername_basedOnEnv();
		WelcomePage w = new WelcomePage();
		w.setEnvironment(h.getEnv());

		SignInPage si = new SignInPage();
		si.signIn(sUsername, sPassword, sDataset);
		
		Thread.sleep(1000);
		
		Commentary.log(LogStatus.INFO, "Verify that while doing edit transaction we can create new category on the fly");

		//String catName = h.getCurrentTime();
		String payeeName = h.getCurrentTime();
		OverviewPage op = new OverviewPage();
		op.navigateToAcctList();
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		//bcc.txtTodaysBalance.click();
		bcc.allTransactionButton.click();
		
		TransactionsPage tp = new TransactionsPage();
		tp.addTransaction.click();
		
		TransactionRecord trec = new TransactionRecord();
		trec.setAmount("50.00");
		trec.setAccount("sChecking");
		trec.setPayee(payeeName);
		trec.setCategory("Education");
		
		TransactionDetailPage td = new TransactionDetailPage();
		td.addTransaction(trec);
		
		tp.searchTransactionTxtField.click();
		tp.searchTransactionTxtField.sendKeys(payeeName);
			
		tp.tapOnFirstTransaction();
		Thread.sleep(2000);
		
		TransactionRecord tRec = new TransactionRecord();
		CategoryRecord catRecord = new CategoryRecord();
		catRecord.setCategoryName("xyz");
		catRecord.setCategoryType("Expense");
		catRecord.setSubCategoryOf("Cash & ATM");
		tRec.setCategoryRecord(catRecord);
		catRecord.setUpdateCategoryName(true);
		catRecord.setUpdateCategoryType(false);
		catRecord.setUpdateSubcategoryOf(true);
		
		td.editTransaction(tRec);
		
		
		
		Thread.sleep(1000);
		Verify.waitForObject(tp.txtAllTransactions, 1);
		tp.searchTransactionTxtField.clear();
		tp.searchTransactionTxtField.sendKeys(catRecord.getCategoryName());
		
		AllAccountsPage aa = new AllAccountsPage();
		
		List<WebElement> li = aa.getAllSearchTransactions ();
		
		if(li.size() >= 1) {
			Commentary.log(LogStatus.PASS, "New category created while editing the transaction");
		}
		else {
			Commentary.log(sa, LogStatus.FAIL, "New category did not get created");
		}
		
		sa.assertAll();
}
	
	@Test(priority = 2, enabled = true)
	public void TR2_test() throws Exception{

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "Verify that for Income type transaction Expense category wont be visible on search ");

		String catName = h.getCurrentTime();
		OverviewPage op = new OverviewPage();
		op.navigateToAllTransactions();
		
		TransactionsPage tp = new TransactionsPage();
		tp.addTransaction.click();
	
		TransactionDetailPage td =  new TransactionDetailPage();
		td.enterAmount("40.00");
		td.enterTransactionType("income");
		
		
		if (Verify.objExists_check(td.category)) {
			td.category.click();
		} else {
			td.categories.click();
		}

		Verify.waitForObject(td.closeCategory, 1);

		td.searchCategory("Zolo");
		Thread.sleep(1000);
		td.createCategory.click();
		
		td.subCategoryOf.click();
		td.searchCategory("Entertainment");
		
		if(Verify.objExists(td.cashAtmParentCat)) {
			Commentary.log(sa, LogStatus.FAIL, "Expense category is vivisble");
		}
		else {
			Commentary.log(LogStatus.PASS, "Expense category is not visible for Income type Transaction");
		}

		sa.assertAll();

	}
}
