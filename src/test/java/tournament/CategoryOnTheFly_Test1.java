package tournament;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.AllAccountsPage;
import dugout.BankingAndCreditCardPage;
import dugout.ManageCategories;
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

public class CategoryOnTheFly_Test1 extends Recovery{

	//String sUserName = "mobileautomation1@quicken.com";
	String sPassword = "Quicken@01";
	String sDataset = "categoryOnFly";
	String sChecking = "sChecking";
	String backButton1_ios = "Banking & Credit, back";
	String sSavings = "sSavings";
	//String sDataset1 = "OnlineAcc_Automation";
	
	
	public String getUsername_basedOnEnv() throws Exception{

		UserName un = new UserName();
		un.stage_ios = "categoryflymobile++@stage.com";
		un.stage_android = "categoryonflyAndroid++@stage.com";
		un.prod_ios = "";
		un.prod_android = "";
		return un.getUserName();	
	}

	@Test(priority = 1, enabled = true)
	public void TR1_test() throws Exception{

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		String sUsername = getUsername_basedOnEnv();
		WelcomePage w = new WelcomePage();
		w.setEnvironment(h.getEnv());

		SignInPage si = new SignInPage();
		si.signIn(sUsername, sPassword, sDataset);
		
		Commentary.log(LogStatus.INFO, "Create Category On the Fly and change the category name on the category pop-up and save transaction.");

		String payeename = h.getCurrentTime();
		OverviewPage op = new OverviewPage();
		op.navigateToAllTransactions();
		
		TransactionsPage tp = new TransactionsPage();
		tp.addTransaction.click();
	
		TransactionRecord tRec = new TransactionRecord();
		CategoryRecord catRecord = new CategoryRecord();
		tRec.setAmount("30.00");
		//tRec.setTransactionType("expense");
		tRec.setAccount("sChecking");
		//tRec.setPayee(payeename);
		catRecord.setCategoryName("zolo");
		catRecord.setCategoryType("Expense");
		catRecord.setSubCategoryOf("Cash & ATM");
		tRec.setCategoryRecord(catRecord);
		catRecord.setUpdateCategoryName(true);
		catRecord.setUpdateCategoryType(true);
		catRecord.setUpdateSubcategoryOf(true);
		
		
		TransactionDetailPage td = new TransactionDetailPage();
		
		td.addTransaction(tRec);
		
		if(h.getEngine().equals("android")) {
			
			Verify.waitForObject(tp.searchTransactionTxtField, 2);
			tp.searchTransactionTxtField.click();
			tp.searchTransactionTxtField.clear();
			tp.searchTransactionTxtField.sendKeys(catRecord.getCategoryName());
			
			
			
			h.hideKeyBoard();
			Thread.sleep(1000);
		}
		else {
			
			Verify.waitForObject(tp.searchTransactionTxtField, 2);
			tp.searchTransactionTxtField.click();
			tp.searchTransactionTxtField.clear();
			tp.searchTransactionTxtField.sendKeys(catRecord.getCategoryName());
		
			//tp.searchTransaction(catRecord.getCategoryName());
		}
		
		AllAccountsPage aa = new AllAccountsPage();
		
		List<WebElement> li = aa.getAllSearchTransactions ();
		Commentary.log(LogStatus.INFO, "No of Transactions appeared in the search .."+li.size());
		
		if (li.isEmpty())
			Commentary.log(LogStatus.FAIL, "Category was not created successfully");
		else
			Commentary.log(sa, LogStatus.PASS, "Category got created successfully");
		
		
			sa.assertAll();
}
	
	@Test(priority = 2, enabled = true)
	public void TR2_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "Verify that add category icon appears when unique category name user tries to enter");

		
		String payeename = h.getCurrentTime();
		OverviewPage op = new OverviewPage();
		op.navigateToAllTransactions();
		
		TransactionsPage tp = new TransactionsPage();
		tp.addTransaction.click();
		
		TransactionDetailPage td = new TransactionDetailPage();
		td.enterAmount("45.00");
		if (Verify.objExists_check(td.category)) {
			td.category.click();
		} else {
			td.categories.click();
		}

		Verify.waitForObject(td.closeCategory, 1);

		td.searchCategory("quick");
		
		if(Verify.objExists(td.createCategory)) {
			
			Commentary.log(LogStatus.PASS, "Crete category '+' button appearing.");
		}
		else {
			Commentary.log(sa, LogStatus.FAIL, "Create category button did not appear.");
		}
		
		sa.assertAll();
		
	}
	
	
	
	@Test(priority = 3, enabled = true)
	public void TR3_test() throws Exception {
		


		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "Verify that if amount is Negative creating category will be of Expense Type");
		
		
		String payeename = h.getCurrentTime();
		OverviewPage op = new OverviewPage();
		op.navigateToAllTransactions();
		
		TransactionsPage tp = new TransactionsPage();
		tp.addTransaction.click();
		//CategoryRecord catRecord = new CategoryRecord();
		TransactionDetailPage td = new TransactionDetailPage();
		
		
		td.enterAmount("30.00");
		//td.enterTransactionType("income");
		td.selectAccount("sChecking");
		//catRecord.setCategoryName("Zolo");
		
		if (Verify.objExists_check(td.category)) {
			td.category.click();
		} else {
			td.categories.click();
		}

		Verify.waitForObject(td.closeCategory, 1);

		td.searchCategory("Zoloo");
		Thread.sleep(1000);
		td.createCategory.click();
		
		if(Verify.objExists(td.selectedCategoryTypeEx)) {
			
			Commentary.log(LogStatus.PASS, "Category type is showing Expense");
		}
		else {
			Commentary.log(sa, LogStatus.FAIL, "Category type is not showing Expense");
		}
		
		sa.assertAll();
		Thread.sleep(2000);
		

	}

	
	
	@Test(priority = 4, enabled = true)
	public void TR4_test() throws Exception {
		

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "Verify that if amount is Positive creating category will be of Income Type");
		
		
		String payeename = h.getCurrentTime();
		OverviewPage op = new OverviewPage();
		op.navigateToAllTransactions();
		
		TransactionsPage tp = new TransactionsPage();
		tp.addTransaction.click();
		//CategoryRecord catRecord = new CategoryRecord();
		TransactionDetailPage td = new TransactionDetailPage();
		
		
		td.enterAmount("30.00");
		td.enterTransactionType("income");
		td.selectAccount("sChecking");
		//catRecord.setCategoryName("Zolo");
		
		if (Verify.objExists_check(td.category)) {
			td.category.click();
		} else {
			td.categories.click();
		}

		Verify.waitForObject(td.closeCategory, 1);

		td.searchCategory("Zoloo");
		Thread.sleep(1000);
		td.createCategory.click();
		
		if(Verify.objExists(td.selectedCategoryTypeIn)) {
			
			Commentary.log(LogStatus.PASS, "Category type is showing Income");
		}
		else {
			Commentary.log(sa, LogStatus.FAIL, "Category type is not showing Income");
		}
		
		
		sa.assertAll();
		Thread.sleep(2000);
		

	}
	
	@Test(priority = 5, enabled = true)
	public void TR5_test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "Verify default subcategory will be 'None'");
		
		String payeename = h.getCurrentTime();
		OverviewPage op = new OverviewPage();
		op.navigateToAllTransactions();
		
		TransactionsPage tp = new TransactionsPage();
		tp.addTransaction.click();
		//CategoryRecord catRecord = new CategoryRecord();
		TransactionDetailPage td = new TransactionDetailPage();
		
		td.buttonDone.click();
		
		if (Verify.objExists_check(td.category)) {
			td.category.click();
		} else {
			td.categories.click();
		}

		Verify.waitForObject(td.closeCategory, 1);

		td.searchCategory("Zoloo");
		Thread.sleep(1000);
		td.createCategory.click();
		
		if(Verify.objExists(td.subcategoryNone)) {
			Commentary.log(LogStatus.PASS, "Subcategory is showing 'None'");
		}
		else {
			Commentary.log(sa, LogStatus.FAIL, "Subcategory is not showing 'None'");
		}
		sa.assertAll();
	}
	
	@Test(priority = 6, enabled = true)
	public void TR6_test() throws Exception{

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "Verify if created category is assigned to any subcategory it will show up under the parent  category");

		String payeename = h.getCurrentTime();
		
		OverviewPage op = new OverviewPage();
		op.navigateToAllTransactions();
		
		TransactionsPage tp = new TransactionsPage();
		tp.addTransaction.click();
	
		TransactionRecord tRec = new TransactionRecord();
		CategoryRecord catRecord = new CategoryRecord();
		tRec.setAmount("30.00");
		//tRec.setTransactionType("expense");
		tRec.setAccount("sChecking");
		//tRec.setPayee(payeename);
		catRecord.setCategoryName("zolo");
		catRecord.setCategoryType("Expense");
		catRecord.setSubCategoryOf("Cash & ATM");
		tRec.setCategoryRecord(catRecord);
		catRecord.setUpdateCategoryName(true);
		catRecord.setUpdateCategoryType(true);
		catRecord.setUpdateSubcategoryOf(true);
		
		
		TransactionDetailPage td = new TransactionDetailPage();
		
		td.addTransaction(tRec);
		Thread.sleep(1000);
		
		tp.backButton.click();
		op.navigateToManageCategories();
		ManageCategories mc = new ManageCategories();
		td.searchCategory(catRecord.getCategoryName());
		
		if(Verify.objExists(mc.cashAtmParentCat)) {
			Commentary.log(LogStatus.PASS, "Subcategory appeared under parent category");
		}
		else {
			Commentary.log(sa, LogStatus.FAIL, "Subcategory did not appear under parent category");
		}
		
		sa.assertAll();
		
	}
		
		@Test(priority = 7, enabled = true)
		public void TR7_test() throws Exception{

			SoftAssert sa = new SoftAssert();
			Helper h = new Helper();
			
			Commentary.log(LogStatus.INFO, "Verify user should be able to create third level category");

			String payeename = h.getCurrentTime();
			
			
			
			OverviewPage op = new OverviewPage();
			op.navigateToAllTransactions();
			
			TransactionsPage tp = new TransactionsPage();
			tp.addTransaction.click();
		
			TransactionRecord tRec = new TransactionRecord();
			CategoryRecord catRecord = new CategoryRecord();
			tRec.setAmount("30.00");
			//tRec.setTransactionType("expense");
			tRec.setAccount("sChecking");
			//tRec.setPayee(payeename);
			catRecord.setCategoryName("Second Level Cat");
			catRecord.setCategoryType("Expense");
			catRecord.setSubCategoryOf("Firstlevelcat");
			tRec.setCategoryRecord(catRecord);
			catRecord.setUpdateCategoryName(true);
			catRecord.setUpdateCategoryType(true);
			catRecord.setUpdateSubcategoryOf(true);
			
			
			TransactionDetailPage td = new TransactionDetailPage();
			
			td.addTransaction(tRec);
			Thread.sleep(1000);
			
			tp.backButton.click();
			op.navigateToManageCategories();
			ManageCategories mc = new ManageCategories();
			td.searchCategory(catRecord.getCategoryName());
			
			if(Verify.objExists(mc.parentcatCategory)) {
				Commentary.log(LogStatus.PASS, "Parent category exists");
			}
			else {
				Commentary.log(LogStatus.FAIL, "Parent category does not exist");
			}
			
			if(Verify.objExists(mc.firstLevelCatCategory)) {
				Commentary.log(LogStatus.PASS, "Subcategory of third level got created");
			}
			else {
				Commentary.log(sa, LogStatus.FAIL, "Subcategory of third level not created");
			}
			
			sa.assertAll();
			
		}
		
		@Test(priority = 8, enabled = true)
		public void TR8_test() throws Exception{

			SoftAssert sa = new SoftAssert();
			Helper h = new Helper();
			
			Commentary.log(LogStatus.INFO, "Verify the validation message once user change the category name to an existing name in the category pop-up");

			String payeename = h.getCurrentTime();
			
			
			
			OverviewPage op = new OverviewPage();
			op.navigateToAllTransactions();
			
			TransactionsPage tp = new TransactionsPage();
			tp.addTransaction.click();
		
			TransactionDetailPage td =  new TransactionDetailPage();
			td.enterAmount("40.00");
			td.selectAccount("sChecking");
			
			if (Verify.objExists_check(td.category)) {
				td.category.click();
			} else {
				td.categories.click();
			}

			Verify.waitForObject(td.closeCategory, 1);

			td.searchCategory("Zoloo");
			Thread.sleep(1000);
			td.createCategory.click();
			
			td.categoryNameTxtField.click();
			td.categoryNameTxtField1.clear();
			h.hideKeyBoard();
			td.categoryNameTxtField1.sendKeys("Cash & ATM");
			
			h.hideKeyBoard();
			
			td.createButton.click();
			
			if(Verify.objExists(td.categoryExistsLabel)) {
				Commentary.log(LogStatus.PASS, "Duplicate category can not be created");
			}
			else {
				Commentary.log(sa, LogStatus.FAIL, "Duplicate category got created.");
			}
			
			sa.assertAll();
		}
		
		@Test(priority = 9, enabled = true)
		public void TR9_test() throws Exception{

			SoftAssert sa = new SoftAssert();
			Helper h = new Helper();
			
			Commentary.log(LogStatus.INFO, "Verify that user should be able to change the category name to a unique one in the category pop-up ad create the category");

			String catName = h.getCurrentTime();
			OverviewPage op = new OverviewPage();
			op.navigateToAllTransactions();
			
			TransactionsPage tp = new TransactionsPage();
			tp.addTransaction.click();
		
			TransactionDetailPage td =  new TransactionDetailPage();
			td.enterAmount("40.00");
			td.selectAccount("sChecking");
			
			if (Verify.objExists_check(td.category)) {
				td.category.click();
			} else {
				td.categories.click();
			}

			Verify.waitForObject(td.closeCategory, 1);
			
			Thread.sleep(1000);
			
			
				td.searchCategoryTextField1.click();
				td.searchCategoryTextField.sendKeys("Zoloo");
				Thread.sleep(1000);
				
				if(h.getEngine().equals("ios")) {
				
				h.hideKeyBoard();
				}
				else {
					Commentary.log(LogStatus.INFO, "hide keyboard not required");
				}
				
			

			//td.searchCategory("Zoloo");
			
				
			
			Thread.sleep(1000);
			td.createCategory.click();
			

			
			 td.categoryNameTxtField.click();
			  td.categoryNameTxtField1.clear();
			  h.hideKeyBoard();
			  Thread.sleep(1000);
			  Commentary.log(LogStatus.INFO, "Updating Category name");
			  td.categoryNameTxtField1.sendKeys(catName);
			
			h.hideKeyBoard();
			
			td.createButton.click();
			
			Thread.sleep(3000);
			td.searchCategoryTextField1.click();
			String searchedCat = td.searchCategoryTextField1.getText();
			
			if(catName.equalsIgnoreCase(searchedCat)) {
				
				Commentary.log(LogStatus.PASS, "Category created and search shows "+td.searchCategoryTextField1.getText()+" the updated category");
			}
			
			else {
				Commentary.log(sa,LogStatus.FAIL, "Category not created with the expected updated text");
			}
			
			sa.assertAll();
			
		}
		
		@Test(priority = 10, enabled = true)
		public void TR10_test() throws Exception{

			SoftAssert sa = new SoftAssert();
			Helper h = new Helper();
			
			Commentary.log(LogStatus.INFO, "Verify the discard message and tap on discard to validate the discard functionality");

			String catName = h.getCurrentTime();
			OverviewPage op = new OverviewPage();
			op.navigateToAllTransactions();
			
			TransactionsPage tp = new TransactionsPage();
			tp.addTransaction.click();
		
			TransactionDetailPage td =  new TransactionDetailPage();
			td.enterAmount("40.00");
			
			
			if (Verify.objExists_check(td.category)) {
				td.category.click();
			} else {
				td.categories.click();
			}

			Verify.waitForObject(td.closeCategory, 1);
			Thread.sleep(2000);
			
			if(h.getEngine().equals("android")) {
				
				
				td.searchCategoryTextField1.click();
				td.searchCategoryTextField.sendKeys("Zoloo");
				Thread.sleep(1000);
				
			}
			else {

			td.searchCategory("Zoloo");
			}
			
			Thread.sleep(1000);
			td.createCategory.click();
			
			//td.subCategoryOf.click();
			//td.searchCategoryTextField.click();
			
			if(h.getEngine().equals("ios")) {
			
				td.subCategoryOf.click();
				td.selectCategory("Cash & ATM");
			}
			else {
				//td.subCategoryOf.click();
				//td.selectCategory("Cash & ATM");
				td.selectCategory_android("Cash & ATM");
			}
			
			//td.searchCategoryTextField.sendKeys("Cash & ATM");
			td.cancelbutton.click();
			
			if(Verify.objExists(td.discardChangesLabel)) {
				
				Commentary.log(LogStatus.PASS, "Discard changes label appeared");
				
				td.discardButton.click();
				Commentary.log(LogStatus.INFO, "Discard button clicked");
			}
			else {
				Commentary.log(sa, LogStatus.FAIL, "Discard changes label not appeared.");
			}
			
			sa.assertAll();
			
		}
		
		
//		@Test(priority = 11, enabled = true)
//		public void TR11_test() throws Exception{
//
//			SoftAssert sa = new SoftAssert();
//			Helper h = new Helper();
//			
//			Commentary.log(LogStatus.INFO, "Verify that while doing edit transaction we can create new category on the fly");
//
//			//String catName = h.getCurrentTime();
//			String payeeName = h.getCurrentTime();
//			OverviewPage op = new OverviewPage();
//			op.navigateToAcctList();
//			
//			BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
//			//bcc.txtTodaysBalance.click();
//			bcc.allTransactionButton.click();
//			
//			TransactionsPage tp = new TransactionsPage();
//			tp.addTransaction.click();
//			
//			TransactionRecord trec = new TransactionRecord();
//			trec.setAmount("50.00");
//			trec.setAccount("sChecking");
//			trec.setPayee(payeeName);
//			trec.setCategory("Education");
//			
//			TransactionDetailPage td = new TransactionDetailPage();
//			td.addTransaction(trec);
//			
//			tp.searchTransactionTxtField.click();
//			tp.searchTransactionTxtField.sendKeys(payeeName);
//				
//			tp.tapOnFirstTransaction();
//			Thread.sleep(2000);
//			
//			TransactionRecord tRec = new TransactionRecord();
//			CategoryRecord catRecord = new CategoryRecord();
//			catRecord.setCategoryName("xyz");
//			catRecord.setCategoryType("Expense");
//			catRecord.setSubCategoryOf("Cash & ATM");
//			tRec.setCategoryRecord(catRecord);
//			catRecord.setUpdateCategoryName(true);
//			catRecord.setUpdateCategoryType(false);
//			catRecord.setUpdateSubcategoryOf(true);
//			
//			td.editTransaction(tRec);
//			
//			
//			
//			Thread.sleep(1000);
//			Verify.waitForObject(tp.txtAllTransactions, 1);
//			tp.searchTransactionTxtField.clear();
//			tp.searchTransactionTxtField.sendKeys(catRecord.getCategoryName());
//			
//			AllAccountsPage aa = new AllAccountsPage();
//			
//			List<MobileElement> li = aa.getAllSearchTransactions ();
//			
//			if(li.size() >= 1) {
//				Commentary.log(LogStatus.PASS, "New category created while editing the transaction");
//			}
//			else {
//				Commentary.log(LogStatus.FAIL, "New category did not get created");
//			}
//	}
//		
//		@Test(priority = 12, enabled = true)
//		public void TR12_test() throws Exception{
//
//			SoftAssert sa = new SoftAssert();
//			Helper h = new Helper();
//			
//			Commentary.log(LogStatus.INFO, "Verify that for Income type transaction Expense category wont be visible on search ");
//
//			String catName = h.getCurrentTime();
//			OverviewPage op = new OverviewPage();
//			op.navigateToAllTransactions();
//			
//			TransactionsPage tp = new TransactionsPage();
//			tp.addTransaction.click();
//		
//			TransactionDetailPage td =  new TransactionDetailPage();
//			td.enterAmount("40.00");
//			td.enterTransactionType("income");
//			
//			
//			if (Verify.objExists_check(td.category)) {
//				td.category.click();
//			} else {
//				td.categories.click();
//			}
//
//			Verify.waitForObject(td.closeCategory, 1);
//
//			td.searchCategory("Zolo");
//			Thread.sleep(1000);
//			td.createCategory.click();
//			
//			td.subCategoryOf.click();
//			td.searchCategory("Cash & ATM");
//			
//			if(Verify.objExists(td.radiobuttonCashATM)) {
//				Commentary.log(LogStatus.FAIL, "Expense category is vivisble");
//			}
//			else {
//				Commentary.log(LogStatus.PASS, "Expense category is not visible for Income type Transaction");
//			}
//	
//		}
		
		
		
}