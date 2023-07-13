package tournament;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.OverviewPage;
import dugout.SettingsPage;
import dugout.SignInPage;
import dugout.TagManagementPage;
import dugout.TransactionDetailPage;
import dugout.WelcomePage;
import referee.Commentary;
import referee.Verify;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;
import support.UserName;

public class TagManagement_Test extends Recovery {
	
	String sUsername = "indrajit.deshmukh+prod@quickendev.com";
	String sPassword = "Quicken@01";
	String sDataset = "Search_functionality_test";
	String sDataset_stage = "TagManagement";
	
	public String getUsername_basedOnEnv() throws Exception{

		UserName un = new UserName();
		un.stage_ios = "tagmanagement_ios++@stage.com";
		un.stage_android = "tagmanagement_android++@stage.com";
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
	public void tm1_Test() throws Exception {
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
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that tag should appear under settings");
		
		TagManagementPage tp = new TagManagementPage();
		OverviewPage op = new  OverviewPage();
		Verify.waitForObjectToDisappear(op.refreshSpinnerIcon, 4);
		tp.navigateToTags();
		
		if(Verify.objExists(tp.tagsHeader))
			Commentary.log(sa,LogStatus.PASS, "Pass:Tags screen appeared");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Tags screen did not appear");

		sa.assertAll();
		
	}
	
	

	
	@Test (priority = 2, enabled = true)
	public void tm3_Test() throws Exception {
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that user should be able to create new tag by tapping on create new tag");
		String sTag= "auto_"+new SimpleDateFormat("HH-mm-ss").format(new Date());
		
		TagManagementPage tp = new TagManagementPage();
		Verify.waitForObjectToDisappear(tp.refreshSpinnerIcon, 4);
		tp.navigateToTags();
	
		tp.deleteAutomationTags();
		Commentary.log(LogStatus.INFO, "Creating Tag with name "+sTag);
		tp.createTag(sTag);
		
		if (tp.verifyTag(sTag))
			Commentary.log(sa,LogStatus.PASS, "Pass: Tags created successfully!");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Couldnot find the created tag on the Tags screen..");

		sa.assertAll();
		
	}
	
	@Test (priority = 3, enabled = true)
	public void tm4_Test() throws Exception {
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that user should be able to enter description & copy number under Advanced section");
		String sTag= "auto_"+new SimpleDateFormat("HH-mm-ss").format(new Date());
		String stagDescri = "tag description";
		String snumber = "9";
		
		TagManagementPage tp = new TagManagementPage();
		Verify.waitForObjectToDisappear(tp.refreshSpinnerIcon, 4);
		tp.navigateToTags();
	
		tp.deleteAutomationTags();
		Thread.sleep(2000);
		Commentary.log(LogStatus.INFO, "Creating Tag with name "+sTag);
		tp.createTagwithAdvanced(sTag, stagDescri, snumber);
		
		if (tp.verifyTag(sTag))
			Commentary.log(sa,LogStatus.PASS, "Pass: Tags created successfully with description & copy number fields.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Couldnot find the created tag on the Tags screen..");

		sa.assertAll();
		
	}
	
	@Test (priority = 4, enabled = true)
	public void tm5_Test() throws Exception {
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that Copy number field should appear only for HBR business tier");
		String sTag= "auto_"+new SimpleDateFormat("HH-mm-ss").format(new Date());
		
		TagManagementPage tp = new TagManagementPage();
		Verify.waitForObjectToDisappear(tp.refreshSpinnerIcon, 4);
		tp.navigateToTags();
	
		tp.createNewTagBtn.click();
		Thread.sleep(5000);
		tp.advancedoption.click();
		
		if (Verify.objExists(tp.copyNumber))
			Commentary.log(sa,LogStatus.PASS, "Pass: Copy number field is appeared.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Copy number field is not appeared.");

		sa.assertAll();
		
	}
	
	@Test (priority = 5, enabled = true)
	public void tm6_Test() throws Exception {
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that user should be able to see the tags listed on the Tag screen after getting added");
		String sTag= "auto_"+new SimpleDateFormat("HH-mm-ss").format(new Date());
		
		TagManagementPage tp = new TagManagementPage();
		Verify.waitForObjectToDisappear(tp.refreshSpinnerIcon, 4);
		tp.navigateToTags();
	
		tp.deleteAutomationTags();
		Commentary.log(LogStatus.INFO, "Creating Tag with name "+sTag);
		tp.createTag(sTag);
		
		if (tp.verifyTag(sTag))
			Commentary.log(sa,LogStatus.PASS, "Pass: Tags is listed on the Tag screen after getting added");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Tags is not listed on the Tag screen after getting added");

		sa.assertAll();
		
	}
	
	@Test (priority = 6, enabled = true)
	public void tm7_Test() throws Exception {
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that user should be able to edit a tag by selecting the tag and tapping on edit button");
		String sTag= "auto_"+new SimpleDateFormat("HH-mm-ss").format(new Date());
		String sRenametag = "new";
		
		
		TagManagementPage tp = new TagManagementPage();
		Verify.waitForObjectToDisappear(tp.refreshSpinnerIcon, 4);
		tp.navigateToTags();
		tp.deleteAutomationTags();
	
		Commentary.log(LogStatus.INFO, "Creating Tag with name "+sTag);
		tp.createTag(sTag);
 		Thread.sleep(3000);
	    tp.editTag(sTag, sRenametag);
		
		if (tp.verifyTag(sRenametag))
			Commentary.log(sa,LogStatus.PASS, "Pass: Tags edited successfully!");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Unable to edit the tag.");
		
		Thread.sleep(1000);
		tp.deleteTag(sRenametag);

		sa.assertAll();
		
	}
	
	@Test (priority = 7, enabled = true)
	public void tm8_Test() throws Exception {
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that user should be able to enter description & copy number under Advanced section");
		String sTag= "auto_"+new SimpleDateFormat("HH-mm-ss").format(new Date());
		String stagDescri = "tag description";
		String snumber = "9";
		String srenameDescri = "tag edit";
		String srenameCopyNo = "5";
		
		
		
		TagManagementPage tp = new TagManagementPage();
		Verify.waitForObjectToDisappear(tp.refreshSpinnerIcon, 4);
		tp.navigateToTags();
	
		tp.deleteAutomationTags();
		Thread.sleep(2000);
		Commentary.log(LogStatus.INFO, "Creating Tag with name "+sTag);
		tp.createTagwithAdvanced(sTag, stagDescri, snumber);
		
		if (tp.verifyTag(sTag))
			Commentary.log(sa,LogStatus.PASS, "Pass: Tags created successfully with description & copy number fields.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Couldnot find the created tag on the Tags screen..");

        tp.editAdvancedTag(sTag, srenameDescri, srenameCopyNo);
		
        if (Verify.objExists(tp.tagUpdated))
			Commentary.log(LogStatus.PASS, "Pass: Tag Updated Successfully with description & copy number fields.");
 		else
 			Commentary.log(LogStatus.FAIL,"Fail: Tag Updation Failed");
 		
		
		Thread.sleep(2000);
		tp.deleteTag(sTag);

		sa.assertAll();
		
		
		
	}
	
	
	
	@Test (priority = 8, enabled = true)
	public void tm9_Test() throws Exception {
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that user should be able to delete single tag");
		String sTag= "auto_"+new SimpleDateFormat("HH-mm-ss").format(new Date());
	
		TagManagementPage tp = new TagManagementPage();
		Verify.waitForObjectToDisappear(tp.refreshSpinnerIcon, 4);
		tp.navigateToTags();
	
		Commentary.log(LogStatus.INFO, "Creating Tag with name "+sTag);
		tp.createTag(sTag);
 		Thread.sleep(3000);
		if (tp.verifyTag(sTag))
			Commentary.log(sa,LogStatus.PASS, "Pass: Tags created successfully!");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Couldnot find the created tag on the Tags screen..");
		
		tp.deleteTag(sTag);
		Thread.sleep(3000);
		if (tp.verifyTag(sTag))
			Commentary.log(sa,LogStatus.FAIL, "FAIL: Unable to delete tag.");
		else
			Commentary.log(sa, LogStatus.PASS, "Pass: Tag deleted successfully.");

		sa.assertAll();
		
	}
	

	
	@Test (priority = 9, enabled = true)
	public void tm10_Test() throws Exception {
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that user should be able to delete multiple tag at once by selecting");
		String sTag1= "auto_1"+new SimpleDateFormat("HH-mm-ss").format(new Date());
		String sTag2= "auto_2"+new SimpleDateFormat("HH-mm-ss").format(new Date());
		
		TagManagementPage tp = new TagManagementPage();
		Verify.waitForObjectToDisappear(tp.refreshSpinnerIcon, 4);
		tp.navigateToTags();
		
		Commentary.log(LogStatus.INFO, "Creating Tag with name "+sTag1);
		tp.createTag(sTag1);
		
		Commentary.log(LogStatus.INFO, "Creating another tag "+sTag2);
		tp.createTag(sTag2);
		
		Commentary.log(LogStatus.INFO, "Selecting Multiple TAGS");

		

		tp.deleteMultipleAutomationTags();
		Thread.sleep(1000);

		
		//Verify.waitForObjectToDisappear(tp.refreshSpinnerIcon, 3);
		if (!tp.verifyTag(sTag1) && !tp.verifyTag(sTag2))
			Commentary.log(sa,LogStatus.PASS, "Pass: Multiple tags got selected and deleted at a time");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: User should be able to select Multiple tags and deleted at a time");

		sa.assertAll();
		
	}

	@Test (priority = 10, enabled = true)
	public void tm11_Test() throws Exception {
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		String sManualChecking = "Checking";
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that tag should get removed from transaction where the tag or tags has been used");
		String sTag= "auto_"+new SimpleDateFormat("HH-mm-ss").format(new Date());
		
		String payeeName = "oneAccountPayee_"+h.getCurrentTime();

		OverviewPage op = new OverviewPage();
		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();
		TagManagementPage tp = new TagManagementPage();
		Verify.waitForObjectToDisappear(tp.refreshSpinnerIcon, 4);
		tp.navigateToTags();
		tp.deleteAutomationTags();
		Commentary.log(LogStatus.INFO, "Creating Tag with name "+sTag);
		tp.createTag(sTag);
		
		if (tp.verifyTag(sTag))
			Commentary.log(sa,LogStatus.PASS, "Pass: Tags created successfully!");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Couldnot find the created tag on the Tags screen..");
	
		Thread.sleep(1000);
		tp.backtoDashboardScreen();
		
		op.addTransaction.click();
		tRec.setAmount("5.00"); 
		tRec.setAccount(sManualChecking);
		//tRec.setCategory("Internet");
	    tRec.setPayee(payeeName);
		tRec.setTransactionType("expense");
		tRec.setDate(h.getFutureDaysDate(0));
		tRec.setTags(sTag);

		
		Verify.waitForObject(td.buttonDone, 1);
		td.addTransaction(tRec);
		Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount "+tRec.getAmount());
		

		Verify.waitForObjectToDisappear(tp.refreshSpinnerIcon, 4);
		tp.navigateToTags();
	

        tp.deleteUsedTag(sTag);
		
		if (tp.verifyTag(sTag))
			Commentary.log(sa,LogStatus.FAIL, "FAIL: Unable to delete Used tag.");
		else
			Commentary.log(sa, LogStatus.PASS, "Pass: Used tag deleted successfully.");

		sa.assertAll();
		
	}
	

	
	@Test (priority = 11, enabled = true)
	public void tm12_Test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that user should not be able to create duplicate tag");
		
		String sStamp = new SimpleDateFormat("HH-mm-ss").format(new Date());
		String sTag1= "auto_"+sStamp;
		String sTag2= "auto-"+sStamp;
		
		TagManagementPage tp = new TagManagementPage();
		Verify.waitForObjectToDisappear(tp.refreshSpinnerIcon, 4);
		tp.navigateToTags();
		
		tp.deleteAutomationTags();
		Commentary.log(LogStatus.INFO, "Creating Tag with name "+sTag1);
		tp.createTag(sTag1);
		
		Commentary.log(LogStatus.INFO, "Creating Tag with name "+sTag2);
		tp.createTag(sTag2);
		
		Commentary.log(LogStatus.INFO, "Editing Tag ["+sTag2+"] Renaming to ["+sTag1+"]");
		
		tp.editTag(sTag2, sTag1);
		
		if (Verify.objExists(tp.alreadyExists) || Verify.objExists(tp.updateBtn))
			Commentary.log(sa,LogStatus.PASS, "Pass: Already Exists message appeared when trying to create a duplicate Tag");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Already Exists message did not appear when trying to create a duplicate Tag");

		sa.assertAll();
		
	}
	
	
	
	@Test (priority = 12, enabled = true)
	public void tm13_Test() throws Exception {
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that app should show warning message when user will try to delete the tag");
		String sTag= "auto_"+new SimpleDateFormat("HH-mm-ss").format(new Date());
		
		TagManagementPage tp = new TagManagementPage();
		Verify.waitForObjectToDisappear(tp.refreshSpinnerIcon, 4);
		tp.navigateToTags();
	
		tp.deleteAutomationTags();
		Commentary.log(LogStatus.INFO, "Creating Tag with name "+sTag);
		tp.createTag(sTag);
		
		if (tp.verifyTag(sTag))
			Commentary.log(sa,LogStatus.PASS, "Pass: Tags created successfully!");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Couldnot find the created tag on the Tags screen..");
	
 		tp.deleteTagConfirmationMessage(sTag);
 		Thread.sleep(2000);
 		if(Verify.objExists_check(tp.areYouSureYouWant)) 
 			Commentary.log(sa,LogStatus.PASS, "Pass: warning message is displayed when user is trying to delete the tag");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: warning message is not displayed when user is trying to delete the tag");
		

		sa.assertAll();
		
	}
	
	
	@Test (priority = 13, enabled = true)
	public void tm15_Test() throws Exception{
		
		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();
		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verify that user should be able to create tag with max 32 character");
		String sTag= "auto_6789012345678901234567890123";
		String sExpectedTag= "auto_678901234567890123456789012";
		
		TagManagementPage tp = new TagManagementPage();
		Verify.waitForObjectToDisappear(tp.refreshSpinnerIcon, 4);
		tp.navigateToTags();
		

		tp.deleteAutomationTags();
		Commentary.log(LogStatus.INFO, "Creating Tag with name more than 32 chars"+sTag);
		tp.createTag(sTag);
		Thread.sleep(3000);
		
		Commentary.log(LogStatus.INFO, "selecting the tag"+sTag);
		String sActualTag = tp.getAutomationTags().getText().replace("Tag name: ", "");
		
		if (sActualTag.equals(sExpectedTag))
			Commentary.log(sa,LogStatus.PASS, "Pass: able to created tag with max of 32 chars");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL:  Tag name had more than 32 chars");
	
		
		tp.deleteTag(sExpectedTag);
		Thread.sleep(5000);

		sa.assertAll();
		
	}
	
	

}
