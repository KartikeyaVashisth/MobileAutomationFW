package tournament;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;


import dugout.BankingAndCreditCardPage;
import dugout.OverviewPage;
import dugout.SignInPage;
import dugout.TransactionDetailPage;
import dugout.TransactionsPage;
import dugout.WelcomePage;
import referee.Commentary;
import referee.Verify;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;
import support.UserName;


public class SearchFunctionality_Test extends Recovery{
	
	String sUsername = "indrajit.deshmukh+prod@quickendev.com";
	String sPassword = "Quicken@01";
	String sDataset = "Search_functionality_test";
	String sDataset_stage = "Searchfunctionality_test";
	String sAccountName = "Checking2 XX6789";
	String sManualChecking = "Checking";
	String sPayeeName = "Nordstrom";
	String sCategoryname = "fast food";
	String sTag = "test tag";

	public String getUsername_basedOnEnv() throws Exception{

		UserName un = new UserName();
		un.stage_ios = "searchfunctionality_ios++@stage.com";
		un.stage_android = "searchfunctionality_android++@stage.com";
		un.prod_ios = "quicken789@gmail.com";
		un.prod_android = "quicken789@gmail.com";
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
	
	@Test (priority = 1, enabled = true)
	public void sf1_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		String sUsername = getUsername_basedOnEnv();
		WelcomePage w = new WelcomePage();
		w.setEnvironment(h.getEnv());

		SignInPage si = new SignInPage();
		if(h.getEnv().contentEquals("stage"))
			si.signIn(sUsername, sPassword, sDataset_stage);
		else
			si.signIn(sUsername, sPassword, sDataset);
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]:Search by payee name in the all accounts.");
		
		OverviewPage op = new OverviewPage();
		TransactionsPage tp = new TransactionsPage();
		op.navigateToAcctList();
		tp.searchTransaction(sPayeeName);
		if(Verify.objExists(tp.txtNoResultFound))
			Commentary.log(sa, LogStatus.FAIL, "FAIL: NO Transactions found with payee name = "+sPayeeName+" ");
		else {
				Commentary.log(LogStatus.PASS, "PASS: Transactions found with Payee name as  "+sPayeeName+" ");
		
			    tp.tapOnFirstTransaction();
			    TransactionDetailPage td = new TransactionDetailPage();
			    String sPayeeName_after = td.getTransactionPayee();
			    if(sPayeeName.equalsIgnoreCase(sPayeeName_after))
				     Commentary.log(LogStatus.PASS, "PASS: Searched payeename "+sPayeeName+"  is matching with searched transaction payeename "+sPayeeName_after+" ");
			    else
				     Commentary.log(sa, LogStatus.FAIL, "FAIL: Searched payeename "+sPayeeName+"  is not matching with searched transaction payeename "+sPayeeName_after+"  ");
			}
		sa.assertAll();
	
	}
	
	@Test (priority = 2, enabled = true)
	public void sf2_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
	
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Search by payee of one account in some other account.");
		
		
		String payeeName = "oneAccountPayee_"+h.getCurrentTime();

		OverviewPage op = new OverviewPage();
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		
		tRec.setAmount("5.00"); 
		tRec.setAccount(sManualChecking);
		//tRec.setCategory("Internet");
		tRec.setPayee(payeeName);
		tRec.setTransactionType("expense");
		tRec.setDate(h.getFutureDaysDate(0));
		
		op.addTransaction.click();
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount "+tRec.getAmount());
		
	    TransactionsPage tp = new TransactionsPage();
		BankingAndCreditCardPage bc = new BankingAndCreditCardPage();
		op.navigateToAcctList();
		bc.selectAccount(sAccountName);
		tp.searchTransactionInAccount(payeeName);	
		if(Verify.objExists(tp.txtNoResultFound))
			Commentary.log( LogStatus.PASS, "PASS: NO Transactions found with Payee of one account in some other account = "+payeeName+" ");
		else 
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Transactions found with Payee Payee of one account in some other account =  "+payeeName+" ");
	
		sa.assertAll();
	
	}
	
	
	@Test (priority = 3, enabled = true)
	public void sf3_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		String sPayeespace = "   ";
	
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]:Apply space and tap on search for result.");
		
		OverviewPage op = new OverviewPage();
		TransactionsPage tp = new TransactionsPage();
		op.navigateToAcctList();
		tp.searchTransaction(sPayeespace);
		if(Verify.objExists(tp.txtNoResultFound))
			Commentary.log( LogStatus.PASS, "PASS: NO Transactions found with payee name with spaces  "+sPayeespace+" ");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Transactions found with payee name with spaces "+sPayeespace+" ");
		
		sa.assertAll();
	
	}
	
	@Test (priority = 4, enabled = true)
	public void sf4_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		String sInvalidPayee = "Invalid payee";
	
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Invalid payee search.");
		
		OverviewPage op = new OverviewPage();
		TransactionsPage tp = new TransactionsPage();
		op.navigateToAcctList();
		tp.searchTransaction(sInvalidPayee);
		if(Verify.objExists(tp.txtNoResultFound))
			Commentary.log( LogStatus.PASS, "PASS: NO Transactions found with Invalid payee name = "+sInvalidPayee+" ");
		else 
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Transactions found with Invalid payee name = "+sInvalidPayee+" ");
		
		sa.assertAll();
	
	}
	
	
	@Test (priority = 5, enabled = true)
	public void sf5_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
	
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Search by Payee in the Particular account.");
		
		OverviewPage op = new OverviewPage();
		TransactionsPage tp = new TransactionsPage();
		BankingAndCreditCardPage bc = new BankingAndCreditCardPage();
		op.navigateToAcctList();
		bc.selectAccount(sAccountName);
		tp.searchTransactionInAccount(sPayeeName);
		if(Verify.objExists(tp.txtNoResultFound))
			Commentary.log(sa, LogStatus.FAIL, "FAIL: NO Transactions found with Payee name as  "+sPayeeName+" ");
		else {
			Commentary.log(LogStatus.PASS, "PASS: Transactions found with Payee name as  "+sPayeeName+" ");
	
		    tp.tapOnFirstTransaction();
		    TransactionDetailPage td = new TransactionDetailPage();
		    String sPayeeName_after = td.getTransactionPayee();
		    if(sPayeeName.equalsIgnoreCase(sPayeeName_after))
			     Commentary.log(LogStatus.PASS, "PASS: Searched payeename "+sPayeeName+"  is matching with searched transaction payeename "+sPayeeName_after+" ");
		    else
			     Commentary.log(sa, LogStatus.FAIL, "FAIL: Searched payeename "+sPayeeName+"  is not matching with searched transaction payeename "+sPayeeName_after+"  ");
		}
		
		sa.assertAll();
	
	}
	
	@Test (priority = 6, enabled = true)
	public void sf6_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
	
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]:Search by category should work.");
		
		OverviewPage op = new OverviewPage();
		TransactionsPage tp = new TransactionsPage();
		op.navigateToAcctList();
		tp.searchTransaction(sCategoryname);
		if(Verify.objExists(tp.txtNoResultFound))
			Commentary.log(sa, LogStatus.FAIL, "FAIL: NO Transactions found with category name = "+sCategoryname+" ");
		else {
			Commentary.log(LogStatus.PASS, "PASS: Transactions found with category name = "+sCategoryname+" ");
		
		    tp.tapOnFirstTransaction();
		    TransactionDetailPage td = new TransactionDetailPage();
		    String sCategoryName_after = td.getTransactionCategory();
		    if(sCategoryname.equalsIgnoreCase(sCategoryName_after))
			      Commentary.log(LogStatus.PASS, "PASS: Searched category "+sCategoryname+"  is matching with search transaction category "+sCategoryName_after+" ");
		    else
			      Commentary.log(sa, LogStatus.FAIL, "FAIL: Searched category "+sCategoryname+"  is not matching with search transaction category "+sCategoryName_after+"  ");
		
		}
		sa.assertAll();
	
	}
	
	@Test (priority = 7, enabled = true)
	public void sf7_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]:Search by tag should work.");
		
		OverviewPage op = new OverviewPage();
		TransactionsPage tp = new TransactionsPage();
		op.navigateToAcctList();
		tp.searchTransaction(sTag);
		if(Verify.objExists(tp.txtNoResultFound))
			Commentary.log(sa, LogStatus.FAIL, "FAIL: NO Transactions found with tag name = "+sTag+" ");
		else {
			Commentary.log(LogStatus.PASS, "PASS: Transactions found with tag name = "+sTag+" ");
		
		    tp.tapOnFirstTransaction();
		    TransactionDetailPage td = new TransactionDetailPage();
		    String sTag_after = td.getTransactionTags();
		    if(sTag.equalsIgnoreCase(sTag_after))
			     Commentary.log(LogStatus.PASS, "PASS: Searched tag "+sTag+"  is matching with search transaction tag "+sTag_after+" ");
		    else
			     Commentary.log(sa, LogStatus.FAIL, "FAIL: Searched tag "+sTag+"  is not matching with search transaction tag "+sTag_after+"  ");
		}
		sa.assertAll();
	
	}
	
	@Test (priority = 8, enabled = true)
	public void sf8_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		String sMemo = "test memo";
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]:Search by memo should work.");
		
		OverviewPage op = new OverviewPage();
		TransactionsPage tp = new TransactionsPage();
		op.navigateToAcctList();
		tp.searchTransaction(sMemo);
		if(Verify.objExists(tp.txtNoResultFound))
			Commentary.log(sa, LogStatus.FAIL, "FAIL: NO Transactions found with memo = "+sMemo+" ");
		else {
			Commentary.log(LogStatus.PASS, "PASS: Transactions found with memo = "+sMemo+" ");
		
		    //tp.tapOnFirstTransaction();
		   // TransactionDetailPage td = new TransactionDetailPage();
		   // String sMemo_after = td.getTransactionNotes();
		    //if(sMemo.equalsIgnoreCase(sMemo_after))
			  // Commentary.log(LogStatus.PASS, "PASS: Searched memo "+sMemo+"  is matching with search transaction category "+sMemo_after+" ");
		   // else
			  // Commentary.log(sa, LogStatus.FAIL, "FAIL: Searched memo "+sMemo+"  is not matching with search transaction category "+sMemo_after+"  ");
		}
		sa.assertAll();
	
	}
	
	
	@Test (priority = 9, enabled = true)
	public void sf9_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		String sAmount = "-5.0";
		Double sAmount_after;
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]:Search by amount should work.");
		
		OverviewPage op = new OverviewPage();
		TransactionsPage tp = new TransactionsPage();
		op.navigateToAcctList();
		tp.searchTransaction(sAmount);
		if(Verify.objExists(tp.txtNoResultFound))
			Commentary.log(sa, LogStatus.FAIL, "FAIL: NO Transactions found with amount = "+sAmount+" ");
		else {
			Commentary.log(LogStatus.PASS, "PASS: Transactions found with amount = "+sAmount+" ");
		
		    tp.tapOnFirstTransaction();
		    TransactionDetailPage td = new TransactionDetailPage();
		    if(h.getEngine().equals("android")) {
				 sAmount_after = h.processBalanceAmount(td.getTransactionAmount());
				
			} else {
				 sAmount_after = h.processBalanceAmount(td.getTransactionAmount().replace("Amount: ", ""));
				 
			}
		    
		    String sAmount_after_1 = Double.toString(sAmount_after);
	
		    if(sAmount.equalsIgnoreCase(sAmount_after_1))
			   Commentary.log(LogStatus.PASS, "PASS: Searched amount "+sAmount+"  is matching with search transaction amount "+sAmount_after_1+" ");
		    else
			   Commentary.log(sa, LogStatus.FAIL, "FAIL: Searched amount "+sAmount+"  is not matching with search transaction amount "+sAmount_after_1+"  ");
		
		}
		sa.assertAll();
	
	}
	
	@Test (priority = 10, enabled = true)
	public void sf10_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		String sNoPayee = "no Payee";

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Search by No Payee should provide the list of only No payee transactions.");
		
		OverviewPage op = new OverviewPage();
		TransactionsPage tp = new TransactionsPage();
		op.navigateToAcctList();
		tp.searchTransaction(sNoPayee);
		if(Verify.objExists(tp.txtNoResultFound))
			    Commentary.log(sa, LogStatus.PASS, "PASS: NO Transactions found with payee name = "+sNoPayee+" ");
		else {
			    Commentary.log(LogStatus.FAIL, "FAIL: Transactions found with Payee name = "+sNoPayee+" ");
		    
			    //tp.tapOnFirstTransaction();
			    //TransactionDetailPage td = new TransactionDetailPage();
			    ///String sNoPayee_after = td.getTransactionPayee();
			    //if(sNoPayee.equalsIgnoreCase(sNoPayee_after))
				 //  Commentary.log(LogStatus.PASS, "PASS: Searched category "+sNoPayee+"  is matching with search transaction category "+sNoPayee_after+" ");
			    //else
				//   Commentary.log(sa, LogStatus.FAIL, "FAIL:Searched category "+sNoPayee+"  is not matching with search transaction category "+sNoPayee_after+"  ");
			}
		
		sa.assertAll();
	
	}
	
	
	@Test (priority = 11, enabled = true)
	public void sf11_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		String sCategory = "Uncategorized";
	
		
	    Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Search by Uncategorised should provide the list of only Uncategorised category transactions.");
		
		OverviewPage op = new OverviewPage();
		TransactionsPage tp = new TransactionsPage();
        op.navigateToAcctList();
		tp.searchTransaction(sCategory);
		if(Verify.objExists(tp.txtNoResultFound))
			Commentary.log(sa, LogStatus.FAIL, "FAIL: NO Transactions found with category as  "+sCategory+" ");
		else {
			Commentary.log(LogStatus.PASS, "PASS: Transactions found with category as  "+sCategory+" ");
		
		    tp.tapOnFirstTransaction();
		    TransactionDetailPage td = new TransactionDetailPage();
		    String sCategory_after = td.getTransactionCategory();
		    if(sCategory.equalsIgnoreCase(sCategory_after))
			   Commentary.log(LogStatus.PASS, "PASS: Searched category "+sCategory+"  is matching with search transaction category "+sCategory_after+" ");
		    else
			   Commentary.log(sa, LogStatus.FAIL, "FAIL: Searched category "+sCategory+"  is not matching with search transaction category "+sCategory_after+"  ");
		
		}  
		    
		sa.assertAll();
	
	}
	
	@Test (priority = 12, enabled = true)
	public void sf13_Test() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: After searching with a keyword if user tap on cancel the App should navigate to the normal Transaction list screen.");
		
		OverviewPage op = new OverviewPage();
		TransactionsPage tp = new TransactionsPage();
		BankingAndCreditCardPage bc = new BankingAndCreditCardPage();
		op.navigateToAcctList();
		bc.selectAccount(sAccountName);
		tp.searchTransactionInAccount(sPayeeName);	
		tp.taponClearSearchButton();
		if(Verify.objExists(tp.buttonClearSearch))
			Commentary.log(sa, LogStatus.FAIL, "FAIL: App is not navigating to transaction list screen after tapping on the cancel button. ");
		else
			Commentary.log(LogStatus.PASS, "PASS: App is navigating to transaction list screen after tapping on the cancel button. ");
		
		sa.assertAll();
	
	}
	
}
