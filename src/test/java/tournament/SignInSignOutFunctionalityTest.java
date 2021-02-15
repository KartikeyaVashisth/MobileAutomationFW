package tournament;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.OverviewPage;
import dugout.SettingsPage;
import dugout.SignInPage;
import dugout.WelcomePage;
import referee.Commentary;
import referee.Verify;
import support.Engine;
import support.Helper;
import support.Recovery;

public class SignInSignOutFunctionalityTest extends Recovery {

	String sUserName = "varsha.h@quicken.com";
	String sPassword = "Intuit!1";
	String sWrongPassword = "wrongPassword";
	String sDataset = "ST Phase 2";

	@Test (priority=1, enabled = true)
	public void TC1_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		SignInPage si = new SignInPage();
		WelcomePage w = new WelcomePage();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Testing Signing In Functionality with Invalid credentials.");

		Thread.sleep(3000);
		w.xpath_btnWelcomeSignIn.click();

		if (! Verify.waitForObject(si.emailID, 3))
			quickenTest.log(LogStatus.ERROR,"SignIn widget not loaded.");

		si.emailID.click();
		Thread.sleep(1000);
		si.userName.clear();
		si.userName.sendKeys(sUserName);
		si.lblPassword.click();
		Thread.sleep(1000);

		si.password.sendKeys(sWrongPassword);

		si.btnSignIn.click();
		Thread.sleep(2000);

		Verify.waitForObject(si.invalidCredentialsText, 1);
		if (Verify.objExists(si.invalidCredentialsText))
			Commentary.log(LogStatus.INFO, "PASS: \"Invalid Quicken ID or password text\" is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: \"Invalid Quicken ID or password text\" is NOT displayed.");

		sa.assertAll();
	}

	@Test (priority=2, enabled = true)
	public void TC2_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		SignInPage si = new SignInPage();
		WelcomePage w = new WelcomePage();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating that Username is pre-filled with last signed in user while signing in.");

		Thread.sleep(3000);
		w.xpath_btnWelcomeSignIn.click();

		Verify.waitForObject(si.usernameTextField, 2);
		if(si.usernameTextField.getText().equals(sUserName))
			Commentary.log(LogStatus.INFO, "PASS: Username is seen pre-filled with last signed in user while signing in.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Username is not seen pre-filled with last signed in user while signing in.");

		sa.assertAll();
	}

	@Test (priority=3, enabled = true)
	public void TC3_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		SignInPage si = new SignInPage();
		si.signIn(sUserName, sPassword, sDataset);

		Commentary.log(LogStatus.INFO, "User is successfully signed in.");
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Validating Signing Out Functionality now.");

		OverviewPage op = new OverviewPage();
		op.hambergerIcon.click();

		SettingsPage sp = new SettingsPage();

		Verify.waitForObject(sp.datasetDDButton, 1);
		if (Verify.objExists(sp.logout))
			Commentary.log(LogStatus.INFO, "PASS: Sign Out button is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Sign Out button is NOT displayed.");

		sp.logout.click();

		Verify.waitForObject(sp.cancelBtn, 1);
		sp.cancelBtn.click();

		if (Verify.objExists(sp.logout))
			Commentary.log(LogStatus.INFO, "PASS: As expected, After pressing cancel option, sign out button is still displayed and the app is not signed out.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: After pressing cancel option, sign out button is NOT displayed and the app signed out unexpectedly.");

		Verify.waitForObject(sp.logout, 1);
		sp.logout.click();
		Verify.waitForObject(sp.signOutConfirmationBtn, 1);
		sp.signOutConfirmationBtn.click();

		WelcomePage w = new WelcomePage();

		if (Verify.objExists(w.welcomePageText) && Verify.objExists(w.xpath_btnWelcomeSignIn))
			Commentary.log(LogStatus.INFO, "PASS: As expected, Welcome page is displayed and the app is signed out.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Welcome page is NOT displayed and the app didn't signed out.");

		sa.assertAll();
	}
}
