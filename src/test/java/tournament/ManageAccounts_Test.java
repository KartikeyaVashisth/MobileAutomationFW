package tournament;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.BankingAndCreditCardPage;
import dugout.ManageAccountsPage;
import dugout.NetWorthPage;
import dugout.OverviewPage;
import dugout.SettingsPage;
import dugout.SignInPage;
import dugout.WelcomePage;
import referee.Commentary;
import referee.Verify;
import support.Helper;
import support.Recovery;
import support.UserName;

public class ManageAccounts_Test extends Recovery {

	String sPassword = "";
	String sDataset = "";
	String sPassword_stage = "Quicken@01";
	String sDataset_stage = "ManageAccount";
	String sManualChecking = "Manual_Checking";
	String sManualCreditCard = "Manual_CC";
	String sManualSaving = "Manual_Savings";
	String backButton1_ios = "Banking & Credit, back";

	public String getUsername_basedOnEnv() throws Exception{

		UserName un = new UserName();
		un.stage_ios = "manageaccounts_ios++@stage.com";
		un.stage_android = "manageaccounts_android++@stage.com";
		un.prod_ios = "";
		un.prod_android = "";
		return un.getUserName();	
	}

	@Test(priority = 1, enabled = true)
	public void TestCase_1() throws Exception {

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

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that the user should be able to rename the account name.");
		
		SettingsPage sp = new SettingsPage();
		sp.clickOnSettingsOption();
		
		Verify.waitForObject(sp.manageAccountsOption,1);
		sp.manageAccountsOption.click();
		Thread.sleep(2000);
		
		ManageAccountsPage map = new ManageAccountsPage();
		map.clickingThreeDotsOfParticularAccount("Manual_Checking");
		map.chooseOption("Rename account");
		
		map.renameAccount("Manual_Checking_edited");
		Verify.waitForObject(map.otherFIText,1);
		
		map.clickingThreeDotsOfParticularAccount("Manual_Checking_edited");
		map.chooseOption("Rename account");
		
		if(map.accountNameGetText().equals("Manual_Checking_edited"))
			Commentary.log(LogStatus.INFO, "PASS: Rename account name is done successfully.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Rename account name is unsuccessful.");
		
		map.renameAccount("Manual_Checking");
		
		sa.assertAll();
	}
	
	@Test(priority = 2, enabled = true)
	public void TestCase_2() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that the user should be able to separate the account.");
		
		OverviewPage op = new OverviewPage();
		
		SettingsPage sp = new SettingsPage();
		sp.clickOnSettingsOption();
		
		Verify.waitForObject(sp.manageAccountsOption,1);
		sp.manageAccountsOption.click();
		Thread.sleep(2000);
		
		ManageAccountsPage map = new ManageAccountsPage();
		map.clickingThreeDotsOfParticularAccount("Manual_Checking");
		map.chooseOption("Keep this account separate");
		
		map.keepAccountSeparate();
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		
		if(map.isAccountSeparated(sManualChecking))
			Commentary.log(LogStatus.INFO, "PASS: Account is separated successfully.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Separate account is Unsuccessful.");
		
		map.clickingThreeDotsOfParticularAccount("Manual_Checking");
		map.chooseOption("Unmark this account as separate");
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		
		if(! map.isAccountSeparated(sManualChecking))
			Commentary.log(LogStatus.INFO, "PASS: Account is now successfully unmarked as separate.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Unmarking Separate account action is not successful.");
		
		sa.assertAll();
	}
	
	@Test(priority = 3, enabled = true)
	public void TestCase_3() throws Exception {
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that the user should be able to hide account in the account list.");
		
		OverviewPage op = new OverviewPage();
		
		SettingsPage sp = new SettingsPage();
		sp.clickOnSettingsOption();
		
		Verify.waitForObject(sp.manageAccountsOption,1);
		sp.manageAccountsOption.click();
		Thread.sleep(2000);
		
		ManageAccountsPage map = new ManageAccountsPage();
		map.clickingThreeDotsOfParticularAccount("Manual_Checking");
		map.chooseOption("Hide in account list");
		
		map.hideAccount();
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		
		if(map.isAccountHidden(sManualChecking))
			Commentary.log(LogStatus.INFO, "PASS: Account is marked hidden successfully.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Hide account is Unsuccessful.");
		
		map.clickingThreeDotsOfParticularAccount("Manual_Checking");
		map.chooseOption("Unhide account");
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 2);
		
		if(! map.isAccountHidden(sManualChecking))
			Commentary.log(LogStatus.INFO, "PASS: Account is now successfully unhided.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Unhiding account action is not successful.");
		
		sa.assertAll();
	}
	
	//Please check if the account is present in the dataset before running this TC.
	@Test(priority = 4, enabled = true)
	public void TestCase_4() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that the user should be able to delete the account.");
		
		String checking_balance,Balance_Manual_CheckingAccount, checking_balance_after;
		
		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		OverviewPage op = new OverviewPage();

		op.navigateToAcctList();
		Verify.waitForObject(bcc.checkingBalance, 1);
		checking_balance = bcc.checkingBalance.getText();
		
		bcc.selectAccount(sManualChecking);
		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		Balance_Manual_CheckingAccount = bcc.txtTodaysBalanceAmount.getText();
		
		bcc.backButton.click();
		Thread.sleep(1000);
		bcc.backButton.click();
		Thread.sleep(1000);
		
		SettingsPage sp = new SettingsPage();
		sp.clickOnSettingsOption();
		
		Verify.waitForObject(sp.manageAccountsOption,1);
		sp.manageAccountsOption.click();
		Thread.sleep(2000);
		
		ManageAccountsPage map = new ManageAccountsPage();
		map.clickingThreeDotsOfParticularAccount("Manual_Checking");
		map.chooseOption("Delete account");
		
		map.deleteAccount();
		map.goBack();
		
		Verify.waitForObject(sp.backButtonOnSettingsHeader, 1);
		sp.backButtonOnSettingsHeader.click();
		Thread.sleep(1000);
		
		op.navigateToAcctList();
		
		Verify.waitForObject(bcc.checkingBalance, 1);
		checking_balance_after = bcc.checkingBalance.getText();
		
		Double dchecking_balance = h.processBalanceAmount(checking_balance);
		Double dBalance_Manual_CheckingAccount = h.processBalanceAmount(Balance_Manual_CheckingAccount);
		Double dchecking_balance_after = h.processBalanceAmount(checking_balance_after);
		
		Integer CheckingTotalBalanceCompare = Double.compare(dchecking_balance-dBalance_Manual_CheckingAccount, dchecking_balance_after);
		
		if(CheckingTotalBalanceCompare==0)
			Commentary.log(LogStatus.INFO, "PASS: Delete account is done successfully.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Delete account is unsuccessful.");
		
		sa.assertAll();
	}
	
	//Please check if the FI is added for the dataset before running this TC. 
	@Test(priority = 5, enabled = true)
	public void TestCase_5() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that the user should be able to delete the FI.");
		
		String total_balance, total_balance_after, netWorth_amount_before, netWorth_amount_after;
		
		OverviewPage op = new OverviewPage();

		Verify.waitForObject(op.totalBalance, 1);
		total_balance = op.totalBalance.getText();
		String processedtotal_balance = total_balance.replaceAll("[^0-9.-]", "");
		
		NetWorthPage nw = new NetWorthPage();
		netWorth_amount_before = nw.netWorthAmountOnDashboard.getText();
		String processednetWorth_amount_before = netWorth_amount_before.replaceAll("[^0-9.-]", "");
		
		SettingsPage sp = new SettingsPage();
		sp.clickOnSettingsOption();
		
		Verify.waitForObject(sp.manageAccountsOption,1);
		sp.manageAccountsOption.click();
		Thread.sleep(2000);
		
		sp.clickOnSettingsOption();
		
		Verify.waitForObject(sp.manageAccountsOption,1);
		sp.manageAccountsOption.click();
		Thread.sleep(2000);
		
		ManageAccountsPage map = new ManageAccountsPage();
		map.clickingThreeDotsOfParticularFI("NIBank AWS E2E - Simple Auth");
		map.chooseOption("Delete financial institution");
		
		map.deleteFIInstitution();
		map.goBack();
		
		Verify.waitForObject(sp.backButtonOnSettingsHeader, 1);
		sp.backButtonOnSettingsHeader.click();
		Thread.sleep(1000);
		
		op.navigateToAcctList();
		
		Verify.waitForObject(op.totalBalance, 1);
		total_balance_after = op.totalBalance.getText();
		String processedtotal_balance_after = total_balance_after.replaceAll("[^0-9.-]", "");
		
		netWorth_amount_after = nw.netWorthAmountOnDashboard.getText();
		String processednetWorth_amount_after = netWorth_amount_after.replaceAll("[^0-9.-]", "");
		
		if(!processedtotal_balance.equals(processedtotal_balance_after))
			Commentary.log(LogStatus.INFO, "PASS: Total balance is changed after deleting the Financial institution.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Deletion of the Financial institution is unsuccessful.");
		
		if(!processednetWorth_amount_before.equals(processednetWorth_amount_after))
			Commentary.log(LogStatus.INFO, "PASS: NetWorth Amount is changed after deleting the Financial institution.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Deletion of the Financial institution is unsuccessful.");
		
		sa.assertAll();
	}
	
}
