package tournament;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.OverviewPage;
import dugout.RenamingRulesPage;
import dugout.SignInPage;
import dugout.TransactionDetailPage;
import dugout.WelcomePage;
import io.appium.java_client.MobileBy;
import referee.Commentary;
import referee.Verify;
import support.Engine;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;
import support.UserName;

public class RenamingRulesTest1 extends Recovery {

	public String sPayee = "PAYEE";
	public String sQuicken = "QUICKEN";
	

	public String getUsername_basedOnEnv() throws Exception {

		UserName r = new UserName();
		// r.stage_ios = "ios8++@mailinator.com";
		r.stage_ios = "kalyan.grandhi@quicken.com";
		r.stage_android = "kalyan.grandhi@quicken.com";
		r.prod_ios = "kalyan.grandhi@quicken.com";
		r.prod_android = "kalyan.grandhi@quicken.com";
		return r.getUserName();
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

	@Test(priority = 0)
	public void RR1_VerifyMenu() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		Commentary.log(LogStatus.INFO, "[" + h.getEngine() + "]: C953957: Verify the menu option for Renaming rules.");
		String sUsername = getUsername_basedOnEnv();
		
		WelcomePage w = new WelcomePage(); 
		w.setEnvironment(h.getEnv());
		  
		SignInPage signIn = new SignInPage(); 
		  
		OverviewPage op = new OverviewPage(); 
		op.navigateToRenamingRules();
		  
		RenamingRulesPage rrp = new RenamingRulesPage();
		
		if(Verify.objExists(rrp.renamingRulesHeader))
			Commentary.log(sa, LogStatus.PASS, "PASS: Renaming rules screen appeared.");
		else 
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Renaming rules screen is NOT shown.");
		
		if(Verify.objExists(rrp.add))
			Commentary.log(LogStatus.PASS, "PASS: Add Rules button appeared.");
		else 
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Renaming rules button did not appear..");
		
		sa.assertAll();
		 

	}
	
	
	@Test(priority = 1)
	public void RR1_VerifyZeroState() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		this.testCaseId.set("C953958");	
		Commentary.log(LogStatus.INFO, "[" + h.getEngine() + "]: "+this.testCaseId.get()+ ": Verify the zero data state for renaming rules screen.");
		OverviewPage op = new OverviewPage(); 
		op.navigateToRenamingRules();
		  
		RenamingRulesPage rrp = new RenamingRulesPage();
		
		Commentary.log(LogStatus.INFO, "Clean-Up: Deleting all the Rules");
		rrp.deleteAllRules(); 
		
		if(Verify.objExists(rrp.noRenamingRulesAddedYet))
			Commentary.log(sa,LogStatus.PASS, "PASS: 'No renaming rules added yet' displayed");
		else 
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'No renaming rules added yet' not displayed");
		
		sa.assertAll();
		 
	}
	
	
	
	@Test(priority = 3)
	public void RR1_PayeeContainsRuleAdd() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		this.testCaseId.set("C953959");	
		Commentary.log(LogStatus.INFO, "[" + h.getEngine() + "]: "+this.testCaseId.get()+ ": Verify that renaming rule can be added for match criteria 'Payee contains'.");
		String sRule= "Auto_"+new SimpleDateFormat("HH-mm-ss").format(new Date());
		String sDownld= "DownL_"+new SimpleDateFormat("HH-mm-ss").format(new Date());
		OverviewPage op = new OverviewPage(); 
		op.navigateToRenamingRules();
		  
		RenamingRulesPage rrp = new RenamingRulesPage();
		rrp.addRule(sRule, sPayee, sDownld);
		
		if(rrp.verifyRule(sRule))
			Commentary.log(sa,LogStatus.PASS, "PASS: 'Rule '"+sRule+ "' created successfully");
		else 
			Commentary.log(sa,LogStatus.FAIL, "FAIL: 'Rule '"+sRule+ "' verification failed");
		
		Commentary.log(sa,LogStatus.INFO, "Clean-Up: Deleting all the Rules");
		rrp.deleteAllRules();
		
		sa.assertAll();
		 
	}
	
	@Test(priority = 4)
	public void RR1_QuickenContainsRuleAdd() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		this.testCaseId.set("C953960");	
		Commentary.log(LogStatus.INFO, "[" + h.getEngine() + "]: "+this.testCaseId.get()+ ": Verify that renaming rule can be added for match criteria 'Quicken name is'.");
		String sRule= "Auto_"+new SimpleDateFormat("HH-mm-ss").format(new Date());
		String sDownld= "DownL_"+new SimpleDateFormat("HH-mm-ss").format(new Date());
		OverviewPage op = new OverviewPage(); 
		op.navigateToRenamingRules();
		  
		RenamingRulesPage rrp = new RenamingRulesPage();
		rrp.addRule(sRule, sQuicken, sDownld);
		
		if(rrp.verifyRule(sRule))
			Commentary.log(sa,LogStatus.PASS, "PASS: 'Rule '"+sRule+ "' created successfully");
		else 
			Commentary.log(sa,LogStatus.FAIL, "FAIL: 'Rule '"+sRule+ "' verification failed");
		
		Commentary.log(sa,LogStatus.INFO, "Clean-Up: Deleting all the Rules");
		rrp.deleteAllRules();
		
		sa.assertAll();
		 
	}
	
	@Test(priority = 5)
	public void RR1_RuleWithMultipleCriteria() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		this.testCaseId.set("C953961");	
		Commentary.log(LogStatus.INFO, "[" + h.getEngine() + "]: "+this.testCaseId.get()+ ": Verify that renaming rule can be created with both the match criteria for the same rename payee.");
		String sRule= "Auto_"+new SimpleDateFormat("HH-mm-ss").format(new Date());
		String sDownld1= "DownL1_"+new SimpleDateFormat("HH-mm-ss").format(new Date());
		String sDownld2= "DownL2_"+new SimpleDateFormat("HH-mm-ss").format(new Date());
		OverviewPage op = new OverviewPage(); 
		op.navigateToRenamingRules();
		  
		RenamingRulesPage rrp = new RenamingRulesPage();
		Commentary.log(LogStatus.INFO, "Creating multiple rules for a single RenamePayee");
		rrp.addRule(sRule, sPayee, sDownld1);
		rrp.addRule(sRule, sQuicken, sDownld2);
		
		rrp.expand_rule(sRule);
		
		
		if(rrp.verify_multipleRule(sDownld1))
			Commentary.log(sa,LogStatus.PASS, "PASS: 'Rule '"+sDownld1+ "' found under RenamePayee "+sRule);
		else 
			Commentary.log(sa,LogStatus.FAIL, "FAIL: 'Rule '"+sDownld1+ "' did not show under RenamePayee "+sRule);
		
		if(rrp.verify_multipleRule(sDownld2))
			Commentary.log(sa,LogStatus.PASS, "PASS: 'Rule '"+sDownld2+ "' found under RenamePayee "+sRule);
		else 
			Commentary.log(sa,LogStatus.FAIL, "FAIL: 'Rule '"+sDownld2+ "' did not show under RenamePayee "+sRule);
		
		Commentary.log(sa,LogStatus.INFO, "Clean-Up: Deleting all the Rules");
		rrp.deleteAllRules();
		
		sa.assertAll();
		 
	}
	
	@Test(priority = 6)
	public void RR1_RuleEdit() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		this.testCaseId.set("C953965");	
		Commentary.log(LogStatus.INFO, "[" + h.getEngine() + "]: "+this.testCaseId.get()+ ": Verify that the renaming rule can be edited to changed the match criteria.");
		String sRule= "Auto_"+new SimpleDateFormat("HH-mm-ss").format(new Date());
		String sDownld1= "DownL1_"+new SimpleDateFormat("HH-mm-ss").format(new Date());
		
		OverviewPage op = new OverviewPage(); 
		op.navigateToRenamingRules();
		  
		RenamingRulesPage rrp = new RenamingRulesPage();
		Commentary.log(LogStatus.INFO, "Creating rule "+sRule);
		rrp.addRule(sRule, sQuicken, sDownld1);
		
		if(rrp.get_matchingCriteria(sRule).equals(sQuicken.toUpperCase()))
			Commentary.log(sa,LogStatus.PASS, "PASS: 'Matching Critera for '"+sRule+ "' found as expected  "+sQuicken);
		else 
			Commentary.log(sa,LogStatus.FAIL, "FAIL: 'Matching Critera for '"+sRule+ "' expected was "+sQuicken);
		
		Commentary.log(LogStatus.INFO, "Changing the matching criteria to "+sPayee);
		rrp.selectRule(sRule);
		rrp.editRule(sRule, sPayee, sDownld1);
		
		if(rrp.get_matchingCriteria(sRule).equals(sPayee.toUpperCase()))
			Commentary.log(sa, LogStatus.PASS, "PASS: 'Matching Critera for '"+sRule+ "' found as expected  "+sPayee);
		else 
			Commentary.log(sa,LogStatus.FAIL, "FAIL: 'Matching Critera for '"+sRule+ "' expected was "+sPayee);
		
		Commentary.log(LogStatus.INFO, "Changing the matching criteria for the rule to "+sQuicken);
		rrp.selectRule(sRule);
		rrp.editRule(sRule, sQuicken, sDownld1);
		if(rrp.get_matchingCriteria(sRule).equals(sQuicken.toUpperCase()))
			Commentary.log(sa, LogStatus.PASS, "PASS: 'Matching Critera for '"+sRule+ "' found as expected  "+sQuicken);
		else 
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Matching Critera for '"+sRule+ "' expected was "+sQuicken);
		Commentary.log(LogStatus.INFO, "Clean-Up: Deleting all the Rules");
		rrp.deleteAllRules();
		
		sa.assertAll();
		 
	}
	
	@Test(priority = 7)
	public void RR1_Test8() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		this.testCaseId.set("C953966");	
		Commentary.log(LogStatus.INFO, "[" + h.getEngine() + "]: "+this.testCaseId.get()+ ": Verify that the renaming rule can be edited to change the rule name.");
		String sRule= "Auto_"+new SimpleDateFormat("HH-mm-ss").format(new Date());
		String sDownld1= "DownL1_"+new SimpleDateFormat("HH-mm-ss").format(new Date());
		String sRenameRule= "RR_"+new SimpleDateFormat("HH-mm-ss").format(new Date());
		
		OverviewPage op = new OverviewPage(); 
		op.navigateToRenamingRules();
		  
		RenamingRulesPage rrp = new RenamingRulesPage();
		Commentary.log(LogStatus.INFO, "Creating rule "+sRule);
		rrp.addRule(sRule, sQuicken, sDownld1);
		
		Commentary.log(LogStatus.INFO, "Renaming the created rule to "+sRenameRule);
		rrp.selectRule(sRule);
		rrp.editRule(sRenameRule, "", "");
		
		if(rrp.verifyRule(sRenameRule))
			Commentary.log(sa, LogStatus.PASS, "PASS: 'Rule '"+sRule+ "' successfully renamed to  "+sRenameRule);
		else 
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Rule name '"+sRule+ "' not changed to "+sRenameRule);
		Commentary.log(LogStatus.INFO, "Clean-Up: Deleting all the Rules");
		rrp.deleteAllRules();
		
		
		
		sa.assertAll();
		 
	}
	
	
	@Test(priority = 8)
	public void RR1_Test9() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		this.testCaseId.set("C953967");	
		Commentary.log(LogStatus.INFO, "[" + h.getEngine() + "]: "+this.testCaseId.get()+ ": Verify that the renaming rule can be edited to change the download payee name.");
		String sRule= "Auto_"+new SimpleDateFormat("HH-mm-ss").format(new Date());
		String sDownld1= "DownL1_"+new SimpleDateFormat("HH-mm-ss").format(new Date());
		String sRenameDnld= "RD_"+new SimpleDateFormat("HH-mm-ss").format(new Date());
		
		OverviewPage op = new OverviewPage(); 
		op.navigateToRenamingRules();
		  
		RenamingRulesPage rrp = new RenamingRulesPage();
		Commentary.log(LogStatus.INFO, "Creating rule "+sRule+" with downloaded payee name as "+sDownld1);
		rrp.addRule(sRule, sQuicken, sDownld1);
		
		Commentary.log(LogStatus.INFO, "Changing the downloadedPayee name to "+sRenameDnld);
		rrp.selectRule(sRule);
		rrp.editRule("", "", sRenameDnld);
		
		if(rrp.get_DownloadPayeeName(sRule).equals(sRenameDnld))
			Commentary.log(sa, LogStatus.PASS, "PASS: 'Downloaded PayeeName for '"+sRule+ "' got updated as  "+sRenameDnld);
		else 
			Commentary.log(sa, LogStatus.FAIL, "FAIL: 'Downloaded PayeeName for '"+sRule+ "' should be "+sRenameDnld);
		Commentary.log(LogStatus.INFO, "Clean-Up: Deleting all the Rules");
		rrp.deleteAllRules();
		
		sa.assertAll();
		
		 
	}
	
	@Test(priority = 9)
	public void RR1_Test10() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		this.testCaseId.set("C953968");	
		Commentary.log(LogStatus.INFO, "[" + h.getEngine() + "]: "+this.testCaseId.get()+ ": Verify that individual match criteria can be deleted under a rule.");
		String sRule= "Auto_"+new SimpleDateFormat("HH-mm-ss").format(new Date());
		String sDownld1= "DownL1_"+new SimpleDateFormat("HH-mm-ss").format(new Date());
		String sDownld2= "DownL2_"+new SimpleDateFormat("HH-mm-ss").format(new Date());
		OverviewPage op = new OverviewPage(); 
		op.navigateToRenamingRules();
		  
		RenamingRulesPage rrp = new RenamingRulesPage();
		Commentary.log(LogStatus.INFO, "Creating multiple rules for a single RenamePayee");
		rrp.addRule(sRule, sPayee, sDownld1);
		rrp.addRule(sRule, sQuicken, sDownld2);
		
		rrp.deleteMatchingCriteriaOfARule(sRule, sDownld1);
		
		if(!rrp.verify_multipleRule(sDownld1))
			Commentary.log(sa,LogStatus.PASS, "PASS: 'Rule Criteria '"+sDownld1+ "' successfully deleted for rule "+sRule);
		else 
			Commentary.log(sa,LogStatus.FAIL, "FAIL: 'Matching Criteria '"+sDownld1+ "' did not get deleted for rule "+sRule);
		
		Commentary.log(sa,LogStatus.INFO, "Clean-Up: Deleting all the Rules");
		rrp.deleteAllRules();
		
		sa.assertAll();
		 
	}
	
	@Test(priority = 10)
	public void RR1_Test11() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		this.testCaseId.set("C953969");	
		Commentary.log(LogStatus.INFO, "[" + h.getEngine() + "]: "+this.testCaseId.get()+ ": Verify that the rule with one or more match criteria can be deleted.");
		String sRule= "Auto_"+new SimpleDateFormat("HH-mm-ss").format(new Date());
		String sDownld1= "DownL1_"+new SimpleDateFormat("HH-mm-ss").format(new Date());
		String sDownld2= "DownL2_"+new SimpleDateFormat("HH-mm-ss").format(new Date());
		String sDownld3= "DownL3_"+new SimpleDateFormat("HH-mm-ss").format(new Date());
		OverviewPage op = new OverviewPage(); 
		op.navigateToRenamingRules();
		  
		RenamingRulesPage rrp = new RenamingRulesPage();
		Commentary.log(LogStatus.INFO, "Creating multiple rules for a single RenamePayee");
		rrp.addRule(sRule, sPayee, sDownld1);
		rrp.addRule(sRule, sQuicken, sDownld2);
		rrp.addRule(sRule, sQuicken, sDownld3);
		
		rrp.deleteAllRules();
		
		if(!rrp.verifyRule(sRule))
			Commentary.log(sa,LogStatus.PASS, "PASS: 'Rule '"+sRule+"' with multiple criteria Deleted Successfully");
		else 
			Commentary.log(sa,LogStatus.FAIL, "FAIL: 'Rule '+"+sRule+"' with multiple criteria didnot get deleted");
		
		sa.assertAll();
		 
	}
	
	
	
	

}
