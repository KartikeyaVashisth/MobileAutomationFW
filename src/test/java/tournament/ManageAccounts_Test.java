package tournament;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.SignInPage;
import dugout.WelcomePage;
import referee.Commentary;
import support.Helper;
import support.Recovery;
import support.UserName;

public class ManageAccounts_Test extends Recovery {

	String sPassword = "Intuit!1";
	String sDataset = "multiedit_issue";
	String sPassword_stage = "Quicken@01";
	String sDataset_stage = "";
	String sManualChecking = "Manual_Checking";
	String sOnlineChecking ="onl_checking1";
	String sManualCreditCard = "Manual_CC";
	String sOnlineCreditCard ="onl_cc";
	String sManualCash="Manual_cash";
	String sOnlineCash="onl_cash";
	String sManualSaving = "Manual_Savings";
	String sOnlineSaving = "onl_savings1";
	String backButton1_ios = "Banking & Credit, back";

	public String getUsername_basedOnEnv() throws Exception{

		UserName un = new UserName();
		un.stage_ios = "@stage.com";
		un.stage_android = "@stage.com";
		un.prod_ios = "quicken789@gmail.com";
		un.prod_android = "quicken789@gmail.com";
		return un.getUserName();	
	}

	@Test(priority = 1, enabled = true)
	public void TC1_ValidateAddTransaction() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		String sUsername = getUsername_basedOnEnv();
		WelcomePage w = new WelcomePage();
		w.setEnvironment(h.getEnv());

		SignInPage si = new SignInPage();
		if(h.getEnv().contentEquals("stage"))
			si.signIn(sUsername, sPassword_stage, sDataset_stage);
		else
			si.signIn(sUsername, sPassword, sDataset);

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that the .");
	
	}
}
