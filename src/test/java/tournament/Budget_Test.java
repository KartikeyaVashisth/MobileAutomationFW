package tournament;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.BankingAndCreditCardPage;
import dugout.BudgetsPage;
import dugout.LongCategoryNamePage;
import dugout.OverviewPage;
import dugout.SettingsPage;
import dugout.SignInPage;
import dugout.TransactionDetailPage;
import dugout.TransactionsPage;
import dugout.WelcomePage;
import io.appium.java_client.AppiumBy;
import referee.Commentary;
import referee.Verify;
import support.Engine;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;
import support.UserName;

public class Budget_Test extends Recovery {
	String sUsername = "indrajit.deshmukh+prod@quickendev.com";
	String sPassword = "Quicken@01";
	String sDataset = "Search_functionality_test";
	String sDataset_stage = "budget1";
	String sManualAccount = "Checking";
	
	
	public String getUsername_basedOnEnv() throws Exception{

		UserName un = new UserName();
		un.stage_ios = "budget_ios++@stage.com";
		un.stage_android = "budget_android++@stage.com";
		un.prod_ios = "quicken789@gmail.com";
		un.prod_android = "quicken789@gmail.com";
		return un.getUserName();	
	}
	
	//****** This piece of code we can enable when we want to use the Testrail integration with our project . Based on test case status the status on Testrail will get update for each testcase ******	
	//@Override
	//@BeforeTest
	//@Parameters({"host","engine","test","env","RUN_ID"})
	//public void beforeTestEnter(@Optional("readFromPropertiesFile")String host, @Optional("readFromPropertiesFile")String engine, @Optional("readFromPropertiesFile")String testName, @Optional("readFromPropertiesFile")String env, @Optional("readFromPropertiesFile")String RUN_ID) throws Exception{
    //this.testRunId.set("2330");
    //super.beforeTestEnter(host, engine, testName, env, RUN_ID);

    //}
	
	
	@Test (priority = 1, enabled = true)
	public void BudgetTest_1() throws Exception {
		
		//this.testCaseId.set("C951465");
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		String sUsername = getUsername_basedOnEnv();
		WelcomePage w = new WelcomePage();
		BudgetsPage b = new BudgetsPage();
		w.setEnvironment(h.getEnv());
		
	    SignInPage si = new SignInPage();
		if(h.getEnv().contentEquals("stage"))
			si.signIn(sUsername, sPassword, sDataset_stage);
		else
			si.signIn(sUsername, sPassword, sDataset);
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify Budget UI on the dashboard card.");
		
		//if(Verify.objExists(si.cancelIcon)) {
		//	si.cancelIcon.click();
		//	Thread.sleep(2000);
		//}
		
		
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.tapOnBudgetCard();
		Thread.sleep(2000);
		
		
		if (b.verify_budgetHeader())
			Commentary.log(sa, LogStatus.PASS,"PASS: Current month budget screen got dispalyed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Current month budget screen did not appear.");
		
		
		if (Verify.objExists(b.budgetViewOption))
			Commentary.log(sa, LogStatus.PASS,"PASS: Budget view option got displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Budget view option did not displayed.");
		
		
		if (Verify.objExists(b.personalExpenses))
			Commentary.log(sa, LogStatus.PASS,"PASS: Personal expenses categories got displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Personal expenses categories did not displayed.");
		
		
		if (Verify.objExists(b.personalIncome))
			Commentary.log(sa, LogStatus.PASS,"PASS: Personal income categories got displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Personal income categories did not displayed.");
		
		
		sa.assertAll();
		
	}
	

	@Test (priority = 2, enabled = true)
	public void BudgetTest_2() throws Exception{
	
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		BudgetsPage b = new BudgetsPage();
		String category = "Auto & Transport:Car Wash";
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify swipe menu for budget category which don’t have child categories.");
		
		
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.tapOnBudgetCard();
		b.personalExpenses.click();
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.swipe_budgetcategory(category);

		 
		if (Verify.objExists(b.editSwipeOption))
			Commentary.log(sa, LogStatus.PASS,"PASS: Edit Swipe option got displayed for budget category which don’t have child categories.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Edit Swipe option did not displayed for budget category which don’t have child categories.");
			 
			
		 if (Verify.objExists(b.viewSwipeOption))
			 Commentary.log(sa, LogStatus.PASS,"PASS: View Swipe option got displayed for budget category which don’t have child categories.");
		 else
			 Commentary.log(sa, LogStatus.FAIL,"FAIL: View Swipe option did not displayed for budget category which don’t have child categories.");
		 
		 
		Thread.sleep(1000);
		sa.assertAll();
		
	}
		
	
	@Test (priority = 3, enabled = true)
	public void BudgetTest_3() throws Exception{
	
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		BudgetsPage b = new BudgetsPage();
		
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.tapOnBudgetCard();
		String category = "Bills & Utilities";
		String swipecategory = "Home Phone";
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify swipe menu for budget category which have child categories.");
		
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.personalExpenses.click();
		//b.clickonBudgetCategory(category);
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.swipe_budgetcategory(category);
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		 
		if (Verify.objExists(b.editSwipeOption))
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Edit Swipe option got displayed for budget category which have child categories.");
		else
			Commentary.log(sa, LogStatus.PASS,"PASS: Edit Swipe option did not displayed for budget category which have child categories.");
			 
			
		 if (Verify.objExists(b.viewSwipeOption))
			 Commentary.log(sa, LogStatus.PASS,"PASS: View Swipe option got displayed for budget category which have child categories.");
		 else
			 Commentary.log(sa, LogStatus.FAIL,"FAIL: View Swipe option did not displayed for budget category which have child categories.");
		 
		 
		Thread.sleep(1000);
		sa.assertAll();
		
	}
		
	
	@Test (priority = 4, enabled = true)
	public void BudgetTest_4() throws Exception{
	
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		BudgetsPage b = new BudgetsPage();
		String category = "Auto & Transport:Car Wash";
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify ‘View’ option from swipe gesture for budget category.");
		
		
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.tapOnBudgetCard();
		b.personalExpenses.click();
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.swipe_budgetcategory(category);
        b.viewSwipeOption.click();

		 
		if (Verify.objExists(b.searchTransactionTextBox))
			Commentary.log(sa, LogStatus.PASS,"PASS: Transaction list screen got displayed after clicking on the View Swipe option got displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Transaction list screen did not got displayed after clicking on the View Swipe option got displayed.");
			 	 
		 
		Thread.sleep(1000);
		sa.assertAll();
		
	}
	
	

	@Test (priority = 5, enabled = true)
	public void BudgetTest_5() throws Exception{
	
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		BudgetsPage b = new BudgetsPage();
		String category = "Auto & Transport:Car Wash";
	
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify ‘Edit’ option from swipe gesture for budget category.");
		
		
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.tapOnBudgetCard();
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 3);
		b.personalExpenses.click();
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.swipe_budgetcategory(category);
        b.editSwipeOption.click();
	
		 
		if (Verify.objExists(b.editBudgetScreen))
			Commentary.log(sa, LogStatus.PASS,"PASS: Edit budget screen got displayed after clicking on the Edit Swipe option got displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Edit budget screen did not got displayed after clicking on the Edit Swipe option got displayed.");
			 	 
		 
		Thread.sleep(1000);
		sa.assertAll();
		
	}
	
	

	@Test (priority = 6, enabled = true)
	public void BudgetTest_6() throws Exception{
	
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		BudgetsPage b = new BudgetsPage();
		
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.tapOnBudgetCard();
		String category = "Bills & Utilities";
		String swipecategory = "Home Phone";
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify ‘View' option from swipe gesture for budget category which having child categories.");
		
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.personalExpenses.click();
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.swipe_budgetcategory(category);
		b.viewSwipeOption.click();

		 
		if (Verify.objExists(b.searchTransactionTextBox))
			Commentary.log(sa, LogStatus.PASS,"PASS: Transaction list screen got displayed after clicking on the View Swipe option got displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Transaction list screen did not got displayed after clicking on the View Swipe option got displayed.");
			 	 
		 
		Thread.sleep(1000);
		sa.assertAll();
		
	}
	
	

	@Test (priority = 7, enabled = true)
	public void BudgetTest_7() throws Exception{
	
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		BudgetsPage b = new BudgetsPage();
		
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.tapOnBudgetCard();
		String category = "Bills & Utilities";
		String swipecategory = "Home Phone";
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify UI for budget category which having child categories.");
		
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.personalExpenses.click();
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.clickonBudgetCategory(category);

		 
		if (Verify.objExists(b.homePhoneCategory))
			Commentary.log(sa, LogStatus.PASS,"PASS: Child category got displayed after clicking on the parent category name.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Child category did not got displayed after clicking on the  parent category name.");
			 	 
		 
		Thread.sleep(1000);
		sa.assertAll();
		
		
	}
	
	
	@Test (priority = 8, enabled = true)
	public void BudgetTest_8() throws Exception{
	
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		BudgetsPage b = new BudgetsPage();
		String category = "Auto & Transport:Car Wash";
		String amount= "30";
		
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify updation of allocated budget amount for parent budget category.");
		
		
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.tapOnBudgetCard();
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 3);
		b.personalExpenses.click();
		
		b.changeBudgetAmount(category, amount);
		//Thread.sleep(1000);
		b.verifyBudgetAmount(category, amount);

		 
		Thread.sleep(1000);
		sa.assertAll();
		
	}
	
	
	@Test (priority = 9, enabled = true)
	public void BudgetTest_9() throws Exception{
	
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		BudgetsPage b = new BudgetsPage();
		String category = "Bills & Utilities";
		String childcategory = "Home Phone";
		String amount= "20";
		
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify updation of allocated budget amount for child budget category.");
		
		
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.tapOnBudgetCard();
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 2);
		b.personalExpenses.click();
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 2);
		b.clickonBudgetCategory(category);
		
		b.changechildCategoryBudgetAmount(childcategory, amount);
		b.verifyBudgetAmountforChildcategory(childcategory, amount);

		 
		Thread.sleep(1000);
		sa.assertAll();
		
	}
	
	
	@Test (priority = 10, enabled = true)
	public void BudgetTest_10() throws Exception{
	
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		BudgetsPage b = new BudgetsPage();
		TransactionDetailPage td = new TransactionDetailPage();
		String category = "Auto & Transport:Parking";
		String amount = "30";
	
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify updation of allocated budget amount for Edit budget screen.");
		
		
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.tapOnBudgetCard();
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 3);
		b.personalExpenses.click();
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 3);
		b.swipe_budgetcategory(category);
        b.editSwipeOption.click();
        Thread.sleep(3000);
        b.clickonEditbudgetscreen.click();
        Thread.sleep(2000);
        b.clickonamount.click();
        for (int i = 0; i<2; i++)
	 		b.deletekey.click();
	 		Thread.sleep(500);
	 		td.enterAmount("30");
        
        b.saveUpdateBudgetAmount.click();
	    Thread.sleep(2000);
        b.backButtonEditBudgetscreen.click();
        Thread.sleep(1000);
		 
        b.verifyBudgetAmount(category, amount);
		Thread.sleep(1000);
		sa.assertAll();
		
	}
	
	
	@Test (priority = 11, enabled = true)
	public void BudgetTest_11() throws Exception{
	
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		BudgetsPage b = new BudgetsPage();
		String category = "Bills & Utilities";
		String childcategory = "Home Phone";
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify swipe menu for budget category for child budget categories.");
		
		
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.tapOnBudgetCard();
		b.personalExpenses.click();
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.clickonBudgetCategory(category);
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		//Thread.sleep(1000);
		b.swipe_budgetcategory(childcategory);

		 
		if (Verify.objExists(b.editSwipeOption))
			Commentary.log(sa, LogStatus.PASS,"PASS: Edit Swipe option got displayed for child budget categories.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Edit Swipe option did not displayed for child budget categories.");
			 
			
		 if (Verify.objExists(b.viewSwipeOption))
			 Commentary.log(sa, LogStatus.PASS,"PASS: View Swipe option got displayed for child budget categories.");
		 else
			 Commentary.log(sa, LogStatus.FAIL,"FAIL: View Swipe option did not displayed for child budget categories.");
		 
		 
		Thread.sleep(1000);
		sa.assertAll();
		
	}
	
	
	@Test (priority = 12, enabled = true)
	public void BudgetTest_12() throws Exception{
	
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		BudgetsPage b = new BudgetsPage();
		OverviewPage op = new OverviewPage();
		TransactionsPage tp = new TransactionsPage();
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		String category = "Car Wash";
		String payeeName = "BudgetTest";
		String sManualChecking = "Checking";
		String TransAmount = "10.00";
		String searchcategory = "Auto & Transport:Car Wash";
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify the budget data after adding new transaction from mobile.");		
		
		tRec.setAmount(TransAmount); 
		tRec.setAccount(sManualChecking);
		tRec.setCategory(category);
		tRec.setPayee(payeeName);
		tRec.setTransactionType("expense");
		tRec.setDate(h.getFutureDaysDate(0));
		
		op.addTransaction.click();
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount "+tRec.getAmount());
		
		
		
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.tapOnBudgetCard();
		b.personalExpenses.click();
		b.verifyTransactionBudgetAmount(category, TransAmount);
		b.clickonBudgetCategory(searchcategory);
		tp.searchRecentTransaction(payeeName);
		tp.tapOnFirstTransaction();

		Verify.waitForObject(td.category, 2);
		op.scroll_down();
		Verify.waitForObject(td.deleteTransaction, 2);
		td.deleteTransaction.click();
		Thread.sleep(1500);
		if (Verify.objExists_check(td.deleteTransactionAlertButton)) {
			td.deleteTransactionAlertButton.click();
		}
		Thread.sleep(5000);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);

		if (Verify.objExists(tp.txtNoResultFound))
			Commentary.log(LogStatus.INFO, "PASS: Successfully Deleted the selected transaction.");
		else
			Commentary.log(sa, LogStatus.FAIL, "Unable to Delete the selected transaction.");


		 
		Thread.sleep(1000);
	
		
		sa.assertAll();
		
	}
	
	
	@Test (priority = 13, enabled = true)
	public void BudgetTest_13() throws Exception{
	
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		BudgetsPage b = new BudgetsPage();
		OverviewPage op = new OverviewPage();
		TransactionsPage tp = new TransactionsPage();
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		String category = "Car Wash";
		String payeeName = "BudgetTest";
		String sManualChecking = "Checking";
		String TransAmount = "10.00";
		String searchcategory = "Auto & Transport:Car Wash";
		String editamount= "20.00";
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify the budget data after updating existing transaction from mobile.");		
		
		tRec.setAmount(TransAmount); 
		tRec.setAccount(sManualChecking);
		tRec.setCategory(category);
		tRec.setPayee(payeeName);
		tRec.setTransactionType("expense");
		tRec.setDate(h.getFutureDaysDate(0));
		
		op.addTransaction.click();
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount "+tRec.getAmount());
		
		
		
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.tapOnBudgetCard();
		b.personalExpenses.click();
		b.clickonBudgetCategory(searchcategory);
		tp.searchRecentTransaction(payeeName);
		tp.tapOnFirstTransaction();
		
		tRec.setAmount(editamount);
		tRec.setAccount(sManualChecking);
		tRec.setCategory(category);
		tRec.setPayee(payeeName);
		tRec.setTransactionType("expense");
		tRec.setDate(h.getFutureDaysDate(0));
		td.editTransaction(tRec);
		b.backbutton1.click();
		
		b.verifyTransactionBudgetAmount(searchcategory, editamount);
		Thread.sleep(1000);
		b.clickonBudgetCategory(searchcategory);
		tp.searchRecentTransaction(payeeName);
		tp.tapOnFirstTransaction();

		Verify.waitForObject(td.category, 2);
		op.scroll_down();
		Verify.waitForObject(td.deleteTransaction, 2);
		td.deleteTransaction.click();
		Thread.sleep(1500);
		if (Verify.objExists_check(td.deleteTransactionAlertButton)) {
			td.deleteTransactionAlertButton.click();
		}
		Thread.sleep(5000);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);

		if (Verify.objExists(tp.txtNoResultFound))
			Commentary.log(LogStatus.INFO, "PASS: Successfully Deleted the selected transaction.");
		else
			Commentary.log(sa, LogStatus.FAIL, "Unable to Delete the selected transaction.");


		 
		Thread.sleep(1000);
		sa.assertAll();
		
	}
	
	
	@Test (priority = 14, enabled = true)
	public void BudgetTest_14() throws Exception{
	
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		BudgetsPage b = new BudgetsPage();
		
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.tapOnBudgetCard();
		String category = "Auto & Transport:Car Wash";
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify search option should get displayed on the transaction screen when there is no transactions for budget category.");
		
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.personalExpenses.click();
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.clickonBudgetCategory(category);
	
		 
		if (Verify.objExists(b.searchTransactionTextBox))
			Commentary.log(sa, LogStatus.PASS,"PASS: search option got displayed on the transaction screen when there is no transactions for budget category.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: search option did not got displayed on the transaction screen when there is no transactions for budget category.");
			 	 
		 
		Thread.sleep(1000);
		sa.assertAll();
		
	}
	
	
	@Test (priority = 15, enabled = true)
	public void BudgetTest_15() throws Exception{
	
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		BudgetsPage b = new BudgetsPage();
		
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.tapOnBudgetCard();
		String category = "Auto & Transport:Gas & Fuel";
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that rollover screen should get displayed for rollover enabled category. ");
		
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.personalExpenses.click();
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		
		if (h.getEngine().equals("android")) {
			WebElement budgetamount = Engine.getDriver().findElement(By.xpath("//android.widget.TextView[@text='"+category+"']/..//android.widget.TextView[contains(@text,'out of')]"));
			budgetamount.click();
		}
		else {
			WebElement budgetamount = Engine.getDriver().findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeOther[`name contains'"+category+"'`][-2]/**/XCUIElementTypeStaticText[`name contains'out of'`]"));
		    budgetamount.click();
		}
		 
		if (Verify.objExists(b.rollovertext))
			Commentary.log(sa, LogStatus.PASS,"PASS: Rollover screen got displayed for rollover enabled category.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Rollover screen didnot got displayed for rollover enabled category.");
			 	 
		 
		Thread.sleep(1000);
		sa.assertAll();
		
	}
	
	
	@Test (priority = 16, enabled = true)
	public void BudgetTest_16() throws Exception{
	
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		BudgetsPage b = new BudgetsPage();
		
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify  options shown when user clicks on budget view icon ");
		
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.tapOnBudgetCard();
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.budgetViewOption.click();
		
		if (Verify.objExists(b.monthlyView))
			Commentary.log(sa, LogStatus.PASS,"PASS: Monthly view option got displayed on the budget view option screen.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Monthly view option didnot got displayed on the budget view option screen.");
		
		if (Verify.objExists(b.quarterlyView))
			Commentary.log(sa, LogStatus.PASS,"PASS: Quarterly view option got displayed on the budget view option screen.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Quarterly view option didnot got displayed on the budget view option screen.");
		if (Verify.objExists(b.yeartoDateView))
			Commentary.log(sa, LogStatus.PASS,"PASS: YeartoDate view option got displayed on the budget view option screen.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: YeartoDate view option didnot got displayed on the budget view option screen.");
		
		Thread.sleep(1000);
		sa.assertAll();
		
	}
	
	
	@Test (priority = 17, enabled = true)
	public void BudgetTest_17() throws Exception{
	
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		BudgetsPage b = new BudgetsPage();
		
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify  options shown when user clicks on budget view icon ");
		
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.tapOnBudgetCard();
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.budgetViewOption.click();
		b.monthlyView.click();
		b.applybudgetView.click();
		Thread.sleep(1000);

		if (b.verify_budgetHeader())
			Commentary.log(sa, LogStatus.PASS,"PASS: Current month budget screen got dispalyed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Current month budget screen did not appear.");
		
		Thread.sleep(1000);
		b.budgetViewOption.click();
		b.quarterlyView.click();
		b.applybudgetView.click();
		Thread.sleep(1000);
		
		if (Verify.objExists(b.Quarterlylabel))
			Commentary.log(sa, LogStatus.PASS,"PASS: Quarterly view option got displayed on the budget view option screen.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Quarterly view option didnot got displayed on the budget view option screen.");
		
		Thread.sleep(1000);
		b.budgetViewOption.click();
		b.yeartoDateView.click();
		b.applybudgetView.click();
		
		if (Verify.objExists(b.YTDLabel))
			Commentary.log(sa, LogStatus.PASS,"PASS: YeartoDate view option got displayed on the budget view option screen.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: YeartoDate view option didnot got displayed on the budget view option screen.");
		
		Thread.sleep(1000);
		sa.assertAll();
		
	}
	

	@Test (priority = 18, enabled = true)
	public void BudgetTest_18() throws Exception{
	
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		BudgetsPage b = new BudgetsPage();
		
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify Budget view icon is shown only in 'Month View'. ");
		
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.tapOnBudgetCard();
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.budgetViewOption.click();
		b.monthlyView.click();
		b.applybudgetView.click();
		Thread.sleep(1000);

		if (b.verify_budgetHeader())
			Commentary.log(sa, LogStatus.PASS,"PASS: Current month budget screen got dispalyed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Current month budget screen did not appear.");
		

		if (Verify.objExists(b.budgetViewOption))
			Commentary.log(sa, LogStatus.PASS,"PASS: Budget view icon is shown only in 'Month View'.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Budget view icon is not showing  in the 'Month View'.");
		
	    
		Thread.sleep(1000);
		sa.assertAll();
		
	}
	
	
	@Test (priority = 19, enabled = true)
	public void BudgetTest_19() throws Exception{
	
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		BudgetsPage b = new BudgetsPage();
		
		DateFormat date =  new SimpleDateFormat("YY");
		Date date1 = new Date();
		String sCard = date.format(date1).toString();
		sCard = "1/"+sCard ;
		
		DateFormat date2 =  new SimpleDateFormat("M/YY");
		Date date3 = new Date();
		String sCard1 = date2.format(date3).toString();
		sCard1 = "YTD: "+sCard+" - "+ sCard1;
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: verify label and date range shown When user selects  'year to Date'  Budget ");
		
		Commentary.log(LogStatus.INFO, "Excepted 'year to Date' range for Budget: " +sCard1);
		
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.tapOnBudgetCard();
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.budgetViewOption.click();
		b.yeartoDateView.click();
		b.applybudgetView.click();
		Thread.sleep(1000);
		String sTemp = b.YTDrange.getText();
		
		Commentary.log(LogStatus.INFO, "Actual 'year to Date' range for Budget: " +sTemp);
		
		if( sCard1.equalsIgnoreCase(sTemp))
			Commentary.log(sa, LogStatus.PASS,"PASS: label and date range shown is correct When user selects  'year to Date'  Budget'.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: verify label and date range shown is not correct When user selects  'year to Date'  Budget");
		
	    
		Thread.sleep(1000);
		sa.assertAll();

		
	}
	
	
	@Test (priority = 20, enabled = true)
	public void BudgetTest_20() throws Exception{
	
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		BudgetsPage b = new BudgetsPage();
		
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: verify label and date range shown When user selects 'Quaterly' Budget. ");
		
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.tapOnBudgetCard();
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.budgetViewOption.click();
		b.quarterlyView.click();
		b.applybudgetView.click();
		Thread.sleep(1000);
		
		if (Verify.objExists(b.Q1Quarter))
			Commentary.log(sa, LogStatus.PASS,"PASS: Q1 Quarter chip is displayed in 'Quarterly View'.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Q1 Quarter chip didnot got displayed in 'Quarterly View'.");

		if (Verify.objExists(b.Q2Quarter))
			Commentary.log(sa, LogStatus.PASS,"PASS: Q2 Quarter chip is displayed in 'Quarterly View'.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Q2 Quarter chip didnot got displayed in 'Quarterly View'.");

		if (Verify.objExists(b.Q3Quarter))
			Commentary.log(sa, LogStatus.PASS,"PASS: Q3 Quarter chip is displayed in 'Quarterly View'.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Q3 Quarter chip didnot got displayed in 'Quarterly View'.");
		
		if (Verify.objExists(b.Q4Quarter))
			Commentary.log(sa, LogStatus.PASS,"PASS: Q4 Quarter chip is displayed in 'Quarterly View'.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Q4 Quarter chip didnot got displayed in 'Quarterly View'.");
		
	    
		Thread.sleep(1000);
		sa.assertAll();
		
	}
	
	
	@Test (priority = 21, enabled = true)
	public void BudgetTest_21() throws Exception{
	
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		BudgetsPage b = new BudgetsPage();
		
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify proper data is loaded while switching between budget views. ");
		
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.tapOnBudgetCard();
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.budgetViewOption.click();
		b.monthlyView.click();
		b.applybudgetView.click();
		Thread.sleep(1000);

		if (b.verify_budgetHeader())
			Commentary.log(sa, LogStatus.PASS,"PASS: Current month budget screen got dispalyed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Current month budget screen did not appear.");
		
		if (Verify.objExists(b.personalExpenses))
			Commentary.log(sa, LogStatus.PASS,"PASS: Personal expenses categories got displayed in the monthly view.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Personal expenses categories did not displayed in the monthly view.");
		
		
		if (Verify.objExists(b.personalIncome))
			Commentary.log(sa, LogStatus.PASS,"PASS: Personal income categories got displayed in the monthly view.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Personal income categories did not displayed in the monthly view.");
		
		
		Thread.sleep(1000);
		b.budgetViewOption.click();
		b.quarterlyView.click();
		b.applybudgetView.click();
		Thread.sleep(1000);
		
		if (Verify.objExists(b.Quarterlylabel))
			Commentary.log(sa, LogStatus.PASS,"PASS: Quarterly view option got displayed on the budget view option screen in the quarterly view.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Quarterly view option didnot got displayed on the budget view option screen in the quarterly view.");
		
		if (Verify.objExists(b.personalExpenses))
			Commentary.log(sa, LogStatus.PASS,"PASS: Personal expenses categories got displayed in the quarterly view.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Personal expenses categories did not displayed in the quarterly view.");
		
		
		if (Verify.objExists(b.personalIncome))
			Commentary.log(sa, LogStatus.PASS,"PASS: Personal income categories got displayed in the quarterly view.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Personal income categories did not displayed in the quarterly view.");
		
		
		Thread.sleep(1000);
		b.budgetViewOption.click();
		b.yeartoDateView.click();
		b.applybudgetView.click();
		
		if (Verify.objExists(b.YTDLabel))
			Commentary.log(sa, LogStatus.PASS,"PASS: YeartoDate view option got displayed on the budget view option screen.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: YeartoDate view option didnot got displayed on the budget view option screen.");
		
		if (Verify.objExists(b.personalExpenses))
			Commentary.log(sa, LogStatus.PASS,"PASS: Personal expenses categories got displayed in the YearToDate view.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Personal expenses categories did not displayed in the YearToDate view.");
		
		
		if (Verify.objExists(b.personalIncome))
			Commentary.log(sa, LogStatus.PASS,"PASS: Personal income categories got displayed in the YearToDate view.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Personal income categories did not displayed in the YearToDate view.");
		
		
		Thread.sleep(1000);
		sa.assertAll();
		
	}
	
	
	@Test (priority = 22, enabled = true)
	public void BudgetTest_22() throws Exception{
	
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		BudgetsPage b = new BudgetsPage();
		OverviewPage op = new OverviewPage();
		TransactionsPage tp = new TransactionsPage();
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		String category = "Car Wash";
		String payeeName = "BudgetTest";
		String sManualChecking = "Checking";
		String TransAmount = "10.00";
		String searchcategory = "Auto & Transport:Car Wash";
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify  Total, Left and Targeted Balances shown in 'Quaterly' view after addition of transaction in Specific category.");		
		
		tRec.setAmount(TransAmount); 
		tRec.setAccount(sManualChecking);
		tRec.setCategory(category);
		tRec.setPayee(payeeName);
		tRec.setTransactionType("expense");
		tRec.setDate(h.getFutureDaysDate(0));
		
		op.addTransaction.click();
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount "+tRec.getAmount());
		
		
		
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.tapOnBudgetCard();
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.budgetViewOption.click();
		b.quarterlyView.click();
		b.applybudgetView.click();
		b.personalExpenses.click();
		b.verifyTransactionBudgetAmount(category, TransAmount);
		b.clickonBudgetCategory(searchcategory);
		tp.searchRecentTransaction(payeeName);
		tp.tapOnFirstTransaction();

		Verify.waitForObject(td.category, 2);
		op.scroll_down();
		Verify.waitForObject(td.deleteTransaction, 2);
		td.deleteTransaction.click();
		Thread.sleep(1500);
		if (Verify.objExists_check(td.deleteTransactionAlertButton)) {
			td.deleteTransactionAlertButton.click();
		}
		Thread.sleep(5000);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);

		if (Verify.objExists(tp.txtNoResultFound))
			Commentary.log(LogStatus.INFO, "PASS: Successfully Deleted the selected transaction.");
		else
			Commentary.log(sa, LogStatus.FAIL, "Unable to Delete the selected transaction.");


		 
		Thread.sleep(1000);
	
		
		sa.assertAll();
		
	}
	
	
	@Test (priority = 23, enabled = true)
	public void BudgetTest_23() throws Exception{
	
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		BudgetsPage b = new BudgetsPage();
		OverviewPage op = new OverviewPage();
		TransactionsPage tp = new TransactionsPage();
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		String category = "Car Wash";
		String payeeName = "BudgetTest";
		String sManualChecking = "Checking";
		String TransAmount = "10.00";
		String searchcategory = "Auto & Transport:Car Wash";
		String editamount= "20.00";
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify  Total, Left and Targeted Balances shown in 'Quaterly' view after updation of transaction in Specific category. ");		
		
		tRec.setAmount(TransAmount); 
		tRec.setAccount(sManualChecking);
		tRec.setCategory(category);
		tRec.setPayee(payeeName);
		tRec.setTransactionType("expense");
		tRec.setDate(h.getFutureDaysDate(0));
		
		op.addTransaction.click();
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount "+tRec.getAmount());
		
		
		
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.tapOnBudgetCard();
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.budgetViewOption.click();
		b.quarterlyView.click();
		b.applybudgetView.click();
		b.personalExpenses.click();
		b.clickonBudgetCategory(searchcategory);
		tp.searchRecentTransaction(payeeName);
		tp.tapOnFirstTransaction();
		
		tRec.setAmount(editamount);
		tRec.setAccount(sManualChecking);
		tRec.setCategory(category);
		tRec.setPayee(payeeName);
		tRec.setTransactionType("expense");
		tRec.setDate(h.getFutureDaysDate(0));
		td.editTransaction(tRec);
		b.backbutton1.click();
		
		b.verifyTransactionBudgetAmount(searchcategory, editamount);
		Thread.sleep(1000);
		b.clickonBudgetCategory(searchcategory);
		tp.searchRecentTransaction(payeeName);
		tp.tapOnFirstTransaction();

		Verify.waitForObject(td.category, 2);
		op.scroll_down();
		Verify.waitForObject(td.deleteTransaction, 2);
		td.deleteTransaction.click();
		Thread.sleep(1500);
		if (Verify.objExists_check(td.deleteTransactionAlertButton)) {
			td.deleteTransactionAlertButton.click();
		}
		Thread.sleep(5000);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);

		if (Verify.objExists(tp.txtNoResultFound))
			Commentary.log(LogStatus.INFO, "PASS: Successfully Deleted the selected transaction.");
		else
			Commentary.log(sa, LogStatus.FAIL, "Unable to Delete the selected transaction.");


		 
		Thread.sleep(1000);
		sa.assertAll();
		
	}
	
	
	@Test (priority = 24, enabled = true)
	public void BudgetTest_24() throws Exception{
	
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		BudgetsPage b = new BudgetsPage();
		OverviewPage op = new OverviewPage();
		TransactionsPage tp = new TransactionsPage();
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		String category = "Car Wash";
		String payeeName = "BudgetTest";
		String sManualChecking = "Checking";
		String TransAmount = "10.00";
		String searchcategory = "Auto & Transport:Car Wash";
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify  Total, Left and Targeted Balances shown in 'Quaterly' view after addition of transaction in Specific category.");		
		
	    Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
	   // op.navigateToAllTransactions();
		op.addTransaction.click();
		Thread.sleep(1000);
			
		String payeename = h.getCurrentTime();
		HashMap<Integer,String> amount = new HashMap<Integer, String>();
		HashMap<Integer,String> cat = new HashMap<Integer, String>();
		HashMap<Integer,String[]> tags = new HashMap<Integer, String[]>();
		
		cat.put(1, "Home Phone");
		cat.put(2, "Car Wash");
		amount.put(1, "50.00");
		amount.put(2, "10.00");	
		tRec.setAmount("60.00");
		tRec.setTransactionType("expense");
		tRec.setAccount(sManualChecking);
		tRec.setPayee(payeename);
			
		td.addSplit(tRec, amount, cat, tags);
		Thread.sleep(3000);
	
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount "+tRec.getAmount());
		
		
		
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 2);
		b.tapOnBudgetCard();
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 2);
		b.budgetViewOption.click();
		b.quarterlyView.click();
		b.applybudgetView.click();
		b.personalExpenses.click();
		b.verifyTransactionBudgetAmount(category, TransAmount);
		b.clickonBudgetCategory(searchcategory);
		tp.searchRecentTransaction(payeename);
		tp.tapOnFirstTransaction();

		Verify.waitForObject(td.category, 2);
		op.scroll_down();
		Verify.waitForObject(td.deleteTransaction, 2);
		td.deleteTransaction.click();
		Thread.sleep(1500);
		if (Verify.objExists_check(td.deleteTransactionAlertButton)) {
			td.deleteTransactionAlertButton.click();
		}
		Thread.sleep(5000);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);

		if (Verify.objExists(tp.txtNoResultFound))
			Commentary.log(LogStatus.INFO, "PASS: Successfully Deleted the selected transaction.");
		else
			Commentary.log(sa, LogStatus.FAIL, "Unable to Delete the selected transaction.");


		 
		Thread.sleep(1000);
	
		
		sa.assertAll();
		
	}
	
	
	@Test (priority = 25, enabled = true)
	public void BudgetTest_25() throws Exception{
	
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		BudgetsPage b = new BudgetsPage();
		
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify proper data is loaded once user switches  between budgets in 'Quaterly'' view ");
		
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.tapOnBudgetCard();
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.budgetViewOption.click();
		b.quarterlyView.click();
		b.applybudgetView.click();
		Thread.sleep(1000);

		if (Verify.objExists(b.Quarterlylabel))
			Commentary.log(sa, LogStatus.PASS,"PASS: Quarterly view option got displayed on the budget view option screen in the quarterly view.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Quarterly view option didnot got displayed on the budget view option screen in the quarterly view.");
		
		if (Verify.objExists(b.personalExpenses))
			Commentary.log(sa, LogStatus.PASS,"PASS: Personal expenses categories got displayed in the quarterly view.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Personal expenses categories did not displayed in the quarterly view.");
		
		
		if (Verify.objExists(b.personalIncome))
			Commentary.log(sa, LogStatus.PASS,"PASS: Personal income categories got displayed in the quarterly view.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Personal income categories did not displayed in the quarterly view.");
		
	
	    
		Thread.sleep(2000);
		b.selectBudget("Long Budget name testing automa");
		Thread.sleep(1000);
		
		if (Verify.objExists(b.Quarterlylabel))
			Commentary.log(sa, LogStatus.PASS,"PASS: Quarterly view option got displayed on the budget view option screen in the quarterly view.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Quarterly view option didnot got displayed on the budget view option screen in the quarterly view.");
		
		if (Verify.objExists(b.personalExpenses))
			Commentary.log(sa, LogStatus.PASS,"PASS: Personal expenses categories got displayed after switching budget in the quarterly view.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Personal expenses categories did not displayed after switching budget in the quarterly view.");
		
		
		if (Verify.objExists(b.personalIncome))
			Commentary.log(sa, LogStatus.PASS,"PASS: Personal income categories got displayed after switching budget in the quarterly view.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Personal income categories did not displayed after switching budget in the quarterly view.");
		
		Thread.sleep(1000);
		b.selectBudget("My Budget 1");
		Thread.sleep(1000);
		
		sa.assertAll();
		
	}
	
	
	@Test (priority = 26, enabled = true)
	public void BudgetTest_26() throws Exception{
	
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		BudgetsPage b = new BudgetsPage();
		
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify proper data is loaded once user switches  between budgets in 'year To Date'' view. ");
		
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.tapOnBudgetCard();
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.budgetViewOption.click();
		b.yeartoDateView.click();
		b.applybudgetView.click();
		Thread.sleep(1000);

		if (Verify.objExists(b.YTDLabel))
			Commentary.log(sa, LogStatus.PASS,"PASS: YearToDate label got displayed on the budget view screen.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: YearToDate label didnot got displayed on the budget view option screen.");
		
		if (Verify.objExists(b.personalExpenses))
			Commentary.log(sa, LogStatus.PASS,"PASS: Personal expenses categories got displayed in the YTD view.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Personal expenses categories did not displayed in the YTD view.");
		
		
		if (Verify.objExists(b.personalIncome))
			Commentary.log(sa, LogStatus.PASS,"PASS: Personal income categories got displayed in the YTD view.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Personal income categories did not displayed in the YTD view.");
		
	
	    
		Thread.sleep(1000);
		b.selectBudget("Long Budget name testing automa");
		Thread.sleep(1000);
		
		if (Verify.objExists(b.YTDLabel))
			Commentary.log(sa, LogStatus.PASS,"PASS: YearToDate label got displayed on the budget view screen.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: YearToDate label didnot got displayed on the budget view option screen.");
		
		if (Verify.objExists(b.personalExpenses))
			Commentary.log(sa, LogStatus.PASS,"PASS: Personal expenses categories got displayed after switching budget in the YTD view.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Personal expenses categories did not displayed after switching budget in the YTD view.");
		
		
		if (Verify.objExists(b.personalIncome))
			Commentary.log(sa, LogStatus.PASS,"PASS: Personal income categories got displayed after switching budget in the YTD view.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Personal income categories did not displayed after switching budget in the YTD view.");
		
		Thread.sleep(1000);
		b.selectBudget("My Budget 1");
		Thread.sleep(1000);
		
		sa.assertAll();
		
	}
	
	
	@Test (priority = 27, enabled = true)
	public void BudgetTest_27() throws Exception{
	
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		BudgetsPage b = new BudgetsPage();
		
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify proper data is loaded once user switches  between Quaters in 'Quaterly' view");
		
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.tapOnBudgetCard();
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.budgetViewOption.click();
		b.quarterlyView.click();
		b.applybudgetView.click();
		Thread.sleep(1000);
		b.Q1Quarter.click();
		
		if (Verify.objExists(b.Q1Quarter))
			Commentary.log(sa, LogStatus.PASS,"PASS: Quarterly 1 chip got displayed on the budget view option screen in the quarterly view.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Quarterly 1 chip didnot got displayed on the budget view option screen in the quarterly view.");
		
		if (Verify.objExists(b.personalExpenses))
			Commentary.log(sa, LogStatus.PASS,"PASS: Personal expenses categories got displayed in the quarterly 1 view.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Personal expenses categories did not displayed in the quarterly 1 view.");
		
		
		if (Verify.objExists(b.personalIncome))
			Commentary.log(sa, LogStatus.PASS,"PASS: Personal income categories got displayed in the quarterly 1 view.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Personal income categories did not displayed in the quarterly 1 view.");
		
		Thread.sleep(1000);
		b.Q2Quarter.click();
		
		if (Verify.objExists(b.Q2Quarter))
			Commentary.log(sa, LogStatus.PASS,"PASS: Quarterly 2 chip got displayed on the budget view option screen in the quarterly 2 view.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Quarterly 2 chip didnot got displayed on the budget view option screen in the quarterly 2 view.");
		
		if (Verify.objExists(b.personalExpenses))
			Commentary.log(sa, LogStatus.PASS,"PASS: Personal expenses categories got displayed in the quarterly 2 view.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Personal expenses categories did not displayed in the quarterly 2 view.");
		
		
		if (Verify.objExists(b.personalIncome))
			Commentary.log(sa, LogStatus.PASS,"PASS: Personal income categories got displayed in the quarterly 2 view.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Personal income categories did not displayed in the quarterly 2 view.");
	    
		Thread.sleep(1000);
		b.Q3Quarter.click();
		
		if (Verify.objExists(b.Q3Quarter))
			Commentary.log(sa, LogStatus.PASS,"PASS: Quarterly 3 chip got displayed on the budget view option screen in the quarterly view.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Quarterly 3 chip didnot got displayed on the budget view option screen in the quarterly view.");
		
		if (Verify.objExists(b.personalExpenses))
			Commentary.log(sa, LogStatus.PASS,"PASS: Personal expenses categories got displayed in the quarterly 3 view.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Personal expenses categories did not displayed in the quarterly 3 view.");
		
		
		if (Verify.objExists(b.personalIncome))
			Commentary.log(sa, LogStatus.PASS,"PASS: Personal income categories got displayed in the quarterly 3 view.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Personal income categories did not displayed in the quarterly 3 view.");
		
		Thread.sleep(1000);
		b.Q4Quarter.click();
		
		if (Verify.objExists(b.Q4Quarter))
			Commentary.log(sa, LogStatus.PASS,"PASS: Quarterly 4 chip got displayed on the budget view option screen in the quarterly view.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Quarterly 4 chip didnot got displayed on the budget view option screen in the quarterly view.");
		
		if (Verify.objExists(b.personalExpenses))
			Commentary.log(sa, LogStatus.PASS,"PASS: Personal expenses categories got displayed in the quarterly 4 view.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Personal expenses categories did not displayed in the quarterly 4 view.");
		
		
		if (Verify.objExists(b.personalIncome))
			Commentary.log(sa, LogStatus.PASS,"PASS: Personal income categories got displayed in the quarterly 4 view.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Personal income categories did not displayed in the quarterly 4 view.");
		
		
		Thread.sleep(1000);
		b.selectBudget("My Budget 1");
		Thread.sleep(1000);
		sa.assertAll();
		
	}
	
	
	@Test (priority = 28, enabled = true)
	public void BudgetTest_28() throws Exception{
	
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		BudgetsPage b = new BudgetsPage();
		String budgetname = "Long Budget name testing automa";
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that If user creates a budget with Long Budget name then UI should be proper. ");
		
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.tapOnBudgetCard();
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.selectBudget(budgetname);
	    Thread.sleep(1000);

	    if (b.verify_budgetHeader())
			Commentary.log(sa, LogStatus.PASS,"PASS: Current month budget screen got dispalyed.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Current month budget screen did not appear.");
		
		if (Verify.objExists(b.personalExpenses))
			Commentary.log(sa, LogStatus.PASS,"PASS: Personal expenses categories got displayed in the monthly view.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Personal expenses categories did not displayed in the monthly view.");
		
		
		if (Verify.objExists(b.personalIncome))
			Commentary.log(sa, LogStatus.PASS,"PASS: Personal income categories got displayed in the monthly view.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Personal income categories did not displayed in the monthly view.");
	
	    
		Thread.sleep(1000);
		b.selectBudget("My Budget 1");
		Thread.sleep(1000);
		sa.assertAll();
		
	}
	
	
	@Test (priority = 29, enabled = true)
	public void BudgetTest_29() throws Exception{
	
		this.testCaseId.set("C951458");
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		BudgetsPage b = new BudgetsPage();
		OverviewPage op = new OverviewPage();
	
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify Zero data state for budget card. ");
		
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		Verify.waitForObject(op.hambergerIcon, 2);
		op.hambergerIcon.click();
		Thread.sleep(3000);

		SettingsPage sp = new SettingsPage();
		Verify.waitForObject(sp.datasetDDButton, 2);
		sp.datasetDDButton.click();
		Thread.sleep(4000);

		sp.switchDataset("budget_test");
		Thread.sleep(1000);
		b.tapOnBudgetCard();
	
		
		if (Verify.objExists(b.zerostateforbudget))
			Commentary.log(sa, LogStatus.PASS,"PASS: Zero data state screen got displayed on the budget card.");
		else
			Commentary.log(sa, LogStatus.FAIL,"FAIL: Zero data state screen didnot displayed on the budget card.");
	
	    
		Thread.sleep(1000);
		b.backButton.click();
		Verify.waitForObject(op.hambergerIcon, 2);
		op.hambergerIcon.click();
		Thread.sleep(1000);
		Verify.waitForObject(sp.datasetDDButton, 2);
		sp.datasetDDButton.click();
		Thread.sleep(1000);
		sp.switchDataset("Budget1");
		Thread.sleep(2000);
		sa.assertAll();
		
	}
	
	@Test (priority = 30, enabled = true)
	public void BudgetTest_30() throws Exception{
	
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		BudgetsPage b = new BudgetsPage();
		OverviewPage op = new OverviewPage();
		TransactionsPage tp = new TransactionsPage();
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		String category = "Car Wash";
		String payeeName = "BudgetTest";
		String sManualChecking = "Checking";
		String TransAmount = "10.00";
		String searchcategory = "Auto & Transport:Car Wash";
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify  Total, Left and Targeted Balances shown in 'YTD' view after addition of transaction in Specific category.");		
		
		tRec.setAmount(TransAmount); 
		tRec.setAccount(sManualChecking);
		tRec.setCategory(category);
		tRec.setPayee(payeeName);
		tRec.setTransactionType("expense");
		tRec.setDate(h.getFutureDaysDate(0));
		
		op.addTransaction.click();
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount "+tRec.getAmount());
		
		
		
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.tapOnBudgetCard();
		Verify.waitForObjectToDisappear(b.refreshSpinnerIcon, 4);
		b.budgetViewOption.click();
		b.yeartoDateView.click();
		b.applybudgetView.click();
		b.personalExpenses.click();
		b.verifyTransactionBudgetAmount(category, TransAmount);
		b.clickonBudgetCategory(searchcategory);
		tp.searchRecentTransaction(payeeName);
		tp.tapOnFirstTransaction();

		Verify.waitForObject(td.category, 2);
		op.scroll_down();
		Verify.waitForObject(td.deleteTransaction, 2);
		td.deleteTransaction.click();
		Thread.sleep(1500);
		if (Verify.objExists_check(td.deleteTransactionAlertButton)) {
			td.deleteTransactionAlertButton.click();
		}
		Thread.sleep(5000);
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);

		if (Verify.objExists(tp.txtNoResultFound))
			Commentary.log(LogStatus.INFO, "PASS: Successfully Deleted the selected transaction.");
		else
			Commentary.log(sa, LogStatus.FAIL, "Unable to Delete the selected transaction.");


		 
		Thread.sleep(1000);
	
		
		sa.assertAll();
		
	}

	
	
}
