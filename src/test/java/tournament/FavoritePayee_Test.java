package tournament;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.BankingAndCreditCardPage;
import dugout.BillsAndIncomePage;
import dugout.FavoritePayeePage;
import dugout.OverviewPage;
import dugout.SignInPage;
import dugout.TagManagementPage;
import dugout.TransactionDetailPage;
import dugout.TransactionsPage;
import referee.Commentary;
import referee.Verify;
import support.Engine;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;

public class FavoritePayee_Test extends Recovery {
	String sUsername = "quicken789@gmail.com";
	String sPassword = "Quicken@01";
	String sDataset = "Search_functionality_test";

	@Test (priority = 1, enabled = true)
	public void FP1_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		SignInPage si = new SignInPage();
		si.signIn(sUsername, sPassword, sDataset);

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that the Display Favorite Payees option is present under the Settings section of the Hamburger menu.");

		FavoritePayeePage fp = new FavoritePayeePage();
		OverviewPage op = new  OverviewPage();
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 4);
		fp.hambergerIcon.click();
		Thread.sleep(3000);
		fp.scrollDownToSettings();
		fp.settingsOption.click();
		Thread.sleep(2000);


		if(Verify.objExists(fp.DisplayFavoritePayee))
			Commentary.log(sa,LogStatus.PASS, "Pass:Display Favorite Payees option is present under the Settings section of the Hamburger menu.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Display Favorite Payees option is not present under the Settings section of the Hamburger menu.");

		sa.assertAll();

	}


	@Test (priority = 2, enabled = true)
	public void FP2_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that the Favorite Payees section is seen on the Payee drawer when adding payees on the Add transactions page with 'Display Favorite Payees' is enabled.");

		FavoritePayeePage fp = new FavoritePayeePage();
		OverviewPage op = new  OverviewPage();
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 4);
		TransactionDetailPage td = new TransactionDetailPage();

		fp.EnablefavoritPayee();
		op.addTransaction.click();
		td.enterAmount("10.00");
		Thread.sleep(3000);
		fp.payee.click();

		if(Verify.objExists(fp.FavoritePayeeLabel))
			Commentary.log(sa,LogStatus.PASS, "Pass:Favorite Payees section is present in the Payee drawer");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Favorite Payees section is not present in the Payee drawer");

		Thread.sleep(5000);

		sa.assertAll();

	}

	@Test (priority = 3, enabled = true)
	public void FP3_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

        Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that the Favorite Payees section is seen on the Payee drawer when editing payees on the View transactions page with 'Display Favorite Payees' is enabled.");

		FavoritePayeePage fp = new FavoritePayeePage();
		OverviewPage op = new  OverviewPage();
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();

		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 4);
		op.navigateToAcctList();
		bcc.allTransactionButton.click();
		tp.tapOnFirstTransaction();
		fp.payee.click();

		if(Verify.objExists(fp.FavoritePayeeLabel))
            Commentary.log(sa,LogStatus.PASS, "Pass:Favorite Payees section is present in the Payee drawer when editing payees on the View transactions page.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Favorite Payees section is not present in the Payee drawer when editing payees on the View transactions page.");

		Thread.sleep(5000);

		sa.assertAll();

	}


	@Test (priority = 4, enabled = true)
	public void FP4_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
        
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify the sequence order of the sections when both 'Display Yelp Recommendation' and 'Display Favorite Payees' options are enabled..");

		FavoritePayeePage fp = new FavoritePayeePage();
		OverviewPage op = new  OverviewPage();
		TransactionDetailPage td = new TransactionDetailPage();

		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 4);
		op.addTransaction.click();
		td.enterAmount("10.00");
		Thread.sleep(3000);
		fp.payee.click();

		if(Verify.objExists(fp.yelpNearByLabel))
            Commentary.log(sa,LogStatus.PASS, "Pass: Display Yelp Recommendation section is present in the Payee drawer on the View transactions page.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Display Yelp Recommendation section is not present in the Payee drawer when on the View transactions page.");


		if(Verify.objExists(fp.FavoritePayeeLabel))
			Commentary.log(sa,LogStatus.PASS, "Pass: Favorite Payees section is present in the Payee drawer on the View transactions page.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Favorite Payees section is not present in the Payee drawer on the View transactions page.");

		if(Verify.objExists(fp.allPayeesLabel))
			Commentary.log(sa,LogStatus.PASS, "Pass: All Payees section is present in the Payee drawer on the View transactions page.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: All Payees section is not present in the Payee drawer on the View transactions page.");


		Thread.sleep(5000);

		sa.assertAll();

	}


	@Test (priority = 5, enabled = true)
	public void FP5_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

        Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that the Favorite Payees section should NOT be seen on the Payee drawer when adding payees on the Add transactions page with 'Display Favorite Payees' disabled.");

		FavoritePayeePage fp = new FavoritePayeePage();
		OverviewPage op = new  OverviewPage();
		TransactionDetailPage td = new TransactionDetailPage();
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 4);
		fp.DisablefavoritPayee();
		op.addTransaction.click();
		td.enterAmount("10.00");
		Thread.sleep(3000);
		fp.payee.click();

		if(Verify.objExists(fp.FavoritePayeeLabel))
			Commentary.log(sa,LogStatus.FAIL, "FAIL:Favorite Payees section is  present in the Payee drawer");
		else
			Commentary.log(sa, LogStatus.PASS, "PASS: Favorite Payees section is not present in the Payee drawer");

		Thread.sleep(5000);
		sa.assertAll();

	}


	@Test (priority = 6, enabled = true)
	public void FP6_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

        Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that the user has option to disable the 'Display Favorite Payees' settings from the payee drawer itself.");

		FavoritePayeePage fp = new FavoritePayeePage();
		OverviewPage op = new  OverviewPage();
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 4);

		fp.EnablefavoritPayee();
		fp.HideFavoritePayeeFromPayeeDrawer();


		if(Verify.objExists(fp.FavoritePayeeLabel))
            Commentary.log(sa,LogStatus.FAIL, "FAIL:Favorite Payees section is  present in the Payee drawer");
		else
			Commentary.log(sa, LogStatus.PASS, "PASS: Favorite Payees section is not present in the Payee drawer");

		Thread.sleep(1000);

		sa.assertAll();

	}


	@Test (priority = 7, enabled = true)
	public void FP7_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that the user is able to add a payee as Favorite and the same is seen under the Favorite Payees section.");

		FavoritePayeePage fp = new FavoritePayeePage();
		OverviewPage op = new  OverviewPage();
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 4);
		fp.EnablefavoritPayee();
		TransactionDetailPage td = new TransactionDetailPage();

		op.addTransaction.click();
		td.enterAmount("10.00");
		Thread.sleep(1000);
		fp.payee.click();
		Thread.sleep(1000);
		fp.AddPayeeAsFavPayee("Axis");
		fp.verifyFavPayee("Axis");
		Thread.sleep(1000);

		sa.assertAll();

	}


	@Test (priority = 8, enabled = true)
	public void FP8_Test() throws Exception {
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();


		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that the search results on the payee drawer should show the Favorite Payees section as well if the searched keyword matches with the Yelp and All payees..");

		FavoritePayeePage fp = new FavoritePayeePage();
		OverviewPage op = new  OverviewPage();
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 6);
		TransactionDetailPage td = new TransactionDetailPage();

		op.addTransaction.click();
		td.enterAmount("10.00");
		Thread.sleep(1000);
		fp.payee.click();
		Thread.sleep(1000);
		td.searchPayee.sendKeys("Axis");
		h.hideKeyBoard();
		Thread.sleep(3000);

		if(Verify.objExists(fp.FavoritePayeeLabel)) {


			Commentary.log(LogStatus.PASS,"PASS: Showing favorite payee section on the payee drawer. ");
		}
		else {

			Commentary.log(LogStatus.FAIL,"FAIL: Not Showing favorite payee section on the payee drawer. ");
		}

		sa.assertAll();

	}


	@Test (priority = 9, enabled = true)
	public void FP9_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that user is able to remove a payee from the Favorites list.");

		FavoritePayeePage fp = new FavoritePayeePage();
		OverviewPage op = new  OverviewPage();
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 6);

		TransactionDetailPage td = new TransactionDetailPage();
		fp.EnablefavoritPayee();
		op.addTransaction.click();
		td.enterAmount("10.00");
		Thread.sleep(1000);
		fp.payee.click();
		Thread.sleep(1000);
		fp.RemoveFavPayee("Axis");
		fp.verifyRemoveFavPayee("Axis");
		Thread.sleep(1000);

		sa.assertAll();

	}


	@Test (priority = 10, enabled = true)
	public void FP10_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that the user should not see Favorite Payees section on the Add Reminder series Payee drawer.");

		FavoritePayeePage fp = new FavoritePayeePage();
		OverviewPage op = new  OverviewPage();
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 4);
		BillsAndIncomePage bi = new BillsAndIncomePage();
		fp.DisablefavoritPayee();
		op.tapOnBillsAndIncomeCard();
		bi.addNewReminderSeries("Bill");
		bi.payeeName.click();

		if(Verify.objExists(fp.FavoritePayeeLabel))
			Commentary.log(sa,LogStatus.FAIL, "FAIL:Favorite Payees section is  present in the Add Reminder series Payee drawer.");
		else
			Commentary.log(sa, LogStatus.PASS, "PASS: Favorite Payees section is not present in on the Add Reminder series Payee drawer.");

		Thread.sleep(1000);

		sa.assertAll();

	}

}