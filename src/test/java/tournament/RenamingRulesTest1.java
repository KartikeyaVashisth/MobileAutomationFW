package tournament;

import java.util.List;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.OverviewPage;
import dugout.RenamingRulesPage;
import dugout.SignInPage;
import dugout.TagManagementPage;
import dugout.TransactionDetailPage;
import dugout.WelcomePage;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import referee.Commentary;
import support.Engine;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;
import support.UserName;

public class RenamingRulesTest1 extends Recovery {

	String ios = "ios1++@mailinator.com";
	String android = "android1++@mailinator.com";
	String sPassword = "Quicken@01";
	String sChkAcct = "manual_checking";
	String sDataset = "";

	public String stage_ios;
	public String stage_android;
	public String prod_ios;
	public String prod_android;

	public String getUsername_basedOnEnv() throws Exception {

		UserName r = new UserName();
		// r.stage_ios = "ios8++@mailinator.com";
		r.stage_ios = "kalyan.grandhi@quicken.com";
		r.stage_android = "akalyan.grandhi@quicken.co";
		r.prod_ios = "kalyan.grandhi@quicken.co";
		r.prod_android = "kalyan.grandhi@quicken.co";
		return r.getUserName();
	}

	@Test(priority = 0)
	public void TC1_ValidateAddTransaction() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		Commentary.log(LogStatus.INFO, "[" + h.getEngine() + "]: Delete all SWL cards");
		// String sUsername = getUsername_basedOnEnv();
		
		  WelcomePage w = new WelcomePage(); //w.setEnvironment(h.getEnv());
		  
		  //SignInPage signIn = new SignInPage(); 
		  
		  OverviewPage op = new OverviewPage(); 
		  op.navigateToRenamingRules();
		  
		  RenamingRulesPage rrp = new RenamingRulesPage();
		  rrp.deleteAllRules();
		  
		  rrp.addRule("abc", "payee", "bbc");
		  
		  System.out.println(rrp.verifyRule("abc"));
		  
		  rrp.selectRule("abc");
		  rrp.editRule("xyz", "quicken", "ndtv");
		  rrp.deleteRule("xyz");
		  sa.assertAll();
		 

	}

}
