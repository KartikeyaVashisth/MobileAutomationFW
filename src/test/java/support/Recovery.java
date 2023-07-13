package support;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import org.testng.internal.Utils;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.saucelabs.saucerest.SauceREST;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import referee.EM2;
import referee.ETM;
import referee.ErrorUtil;
import referee.ExtentManager;
import referee.Verify;
import support.APIClient;
import support.APIException;

public class Recovery {
	
	public static ExtentTest quickenTest;
	
	public static ThreadLocal<String> sEngine = new ThreadLocal<String>();
	public static ThreadLocal<String> sHost= new ThreadLocal<String>();
	public static ThreadLocal<String> sTestName= new ThreadLocal<String>();
	public static ThreadLocal<String> sCloudService= new ThreadLocal<String>();
	public static ThreadLocal<String> sEnv= new ThreadLocal<String>();
	public static ThreadLocal<String> testRunId = new ThreadLocal<String>();
	public static ThreadLocal<String> testCaseId = new ThreadLocal<String>();
	
	public Boolean wannaUploadTheBuild(){
		
		if(this.sCloudService.get().equals("sauceLabs")) {
		
			String uploadBuild = System.getProperty("buildpath");
			
			if (uploadBuild == null || uploadBuild.equals("DoNotUpload"))
			return false;
			
			System.out.println("UPLOADING BUILD TO SAUCE > TRUE");
			return true;
			
		}
		else {
			return false;
		}
	}
	
	
	
	
	
	@BeforeSuite
	public void testPlanEnter() throws Exception{
		
		/*
		ExtentManager em = new ExtentManager();
		em.initializeRepObject();*/
		
		EM2.getInstance();	
		
		
			
	} 
	
	
	
	
	@BeforeTest
	@Parameters({"host","engine","test","env","RUN_ID"})
	public void beforeTestEnter(@Optional("readFromPropertiesFile")String host, @Optional("readFromPropertiesFile")String engine, @Optional("readFromPropertiesFile")String testName, @Optional("readFromPropertiesFile")String env, @Optional("readFromPropertiesFile")String RUN_ID) throws Exception{
		
		System.out.println("Before Test Start");
		Helper h = new Helper();
		
		
	
		
		/* ******  Commenting for now as we are not using this piece of code, For testrail integration we have written separate code ****
		 if (RUN_ID.equals("readFromPropertiesFile"))
			 testRunId.set("readFromPropertiesFile");
		 */
		
		
		// load engine and host
		if (host.equals("readFromPropertiesFile"))
		{
			Globals g = new Globals();
			h.loadProperties();
			sEngine.set(g.testProperty.get("engine"));
			sHost.set(g.testProperty.get("host"));
			sTestName.set("LOCAL_Companion"+g.testProperty.get("engine"));
			sEnv.set(g.testProperty.get("env"));
			
		}
		else {
			h.loadProperties();
			sEngine.set(engine);
			sHost.set(host);
			sTestName.set(testName);
			sEnv.set(env);
			
//	******  Commenting for now as we are not using this piece of code, For testrail integration we have written separate code ****
			/* if (!RUN_ID.equals("readFromPropertiesFile")) {
				System.out.println("Alert Alert!");
				testRunId.set(RUN_ID);
		}*/
		}
		
		sCloudService.set(Globals.testProperty.get("cloud_service"));
		
		System.out.println("Engine: ["+sEngine.get()+"], Host: ["+sHost.get()+"], Env: ["+sEnv.get()+"]");
		System.out.println("Before Test Run ID: "+this.testRunId.get());
		
		
		
		if (wannaUploadTheBuild()) {
			
			if (sEngine.get().equals("android"))
				h.uploadAndroidBuild();
			else
				h.uploadIOSBuild();
		}
			
		// initializeDriver
		try {
			
			Engine.setDriver();	
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		System.out.println("launch done...");
		
		
	}
	
	
	
	@BeforeMethod
	public void testCaseEnter(Method m) throws Exception{
		
		Helper h = new Helper();
		
		System.out.println("BeforeMethod Starts........."+m.getName()+" ["+h.getEngine()+"] ");
		
		ErrorUtil.resetTestErrors();
		
		// start test logs
		ETM tm = new ETM();
		tm.startTest(m.getName());
		tm.getTest().log(LogStatus.INFO,"StartTime "+new SimpleDateFormat("HH.mm.ss").format(new Date()));
		
		// relaunch the app
		if (this.sEngine.get().equals("android")) {
			
			Activity activity = new Activity("com.quicken.qm2014", "com.quicken.qm2014.MainActivity");
//          //()Engine.getDriver()).startActivity(activity);
//          
//			Engine.ad.startActivity(activity);
			//Activity activity = new Activity("com.quicken.acme", "com.quicken.qm2014.MainActivity");
//			((AndroidDriver<WebElement>)Engine.getDriver()).startActivity(activity);
			((AndroidDriver)Engine.getDriver()).activateApp("com.quicken.qm2014");
			Thread.sleep(2000);

		}
			
		else
//			((IOSDriver<WebElement>)Engine.getDriver()).launchApp();
			((IOSDriver)Engine.getDriver()).activateApp("com.intuit.quickencompanion.ios");
			
		Thread.sleep(10000);
		System.out.println("BeforeMethod Ends........."+m.getName());
		
	}
	
	@AfterMethod
	
//	******  You can comment this method and enablke the below commented method if you want the testRail integration with your project ****
	public void testCaseExit(ITestResult result, Method method) throws Exception{
		
		//TEST_CASE_PASSED_STATUS = 1;
		//TEST_CASE_FAILED_STATUS = 5;
		int testTrailStatus = 1;
		
		Helper h = new Helper();
		
		System.out.println("AfterMethod start...."+method.getName()+" ["+h.getEngine()+"] ");
		
		
		
		/*
		ExtentManager em = new ExtentManager();
		
		em.initializeRepObject().endTest(getRep());*/
		/*
		List<LogEntry> logEntries = Engine.getDriver().manage().logs().get("logcat").getAll();
		System.out.println(logEntries);
		*/
		
		
		List<String> softFails = ErrorUtil.getTestErrors();
		if (result.getStatus() == ITestResult.FAILURE) {
			testTrailStatus = 5;
			ETM.getTest().log(LogStatus.FAIL, result.getThrowable());
		   } 
		
		if ((softFails.size() != 0)) {
			testTrailStatus = 5;
			ETM.getTest().log(LogStatus.FAIL,method.getName());
		}
		else
			ETM.getTest().log(LogStatus.PASS,method.getName());
		
		ETM.getTest().log(LogStatus.INFO, "EndTime "+new SimpleDateFormat("HH.mm.ss").format(new Date()));
		ETM.endTest();
		ETM.map2.get((int)Thread.currentThread().getId()).flush();
		
		Thread.sleep(1000);
		
//		Engine.tlDriver.get().closeApp();
		if (this.sEngine.get().equals("android"))
//			((AndroidDriver<WebElement>)Engine.getDriver()).closeApp();
			((AndroidDriver)Engine.getDriver()).terminateApp("com.quicken.qm2014");
		else
//			((IOSDriver<WebElement>)Engine.getDriver()).closeApp();
			((IOSDriver)Engine.getDriver()).terminateApp("com.intuit.quickencompanion.ios");
		
		System.out.println("AfterMethod: "+testRunId.get());
		
		if (!testRunId.get().equals("readFromPropertiesFile"))
			//TestRailManager.addResultForTestCase(testCaseId.get(), testTrailStatus, "");
		System.out.println("");
		System.out.println("-----------------------------------------------------------------");
		System.out.println("");
		
	}
	/*   You can  enable below piece of code if you want to integrate testrail with your project. based on your test case result it will update the status on TestRail for each test cases.
	 * public void tearDown(ITestResult result) throws Throwable {
		if(result.getStatus()==ITestResult.SUCCESS) {
			TestRailManager.addResultForTestCase(this.testRunId.get(),this.testCaseId.get(), TestRailManager.TEST_CASE_PASSED_STATUS, result.getThrowable());
		}
		else if(result.getStatus()==ITestResult.FAILURE) {
			
			TestRailManager.addResultForTestCase(this.testRunId.get(),this.testCaseId.get(), TestRailManager.TEST_CASE_FAILED_STATUS, result.getThrowable());
		}
		
		if (this.sEngine.get().equals("android"))

			((AndroidDriver)Engine.getDriver()).terminateApp("com.quicken.qm2014");
		else

			((IOSDriver)Engine.getDriver()).terminateApp("com.intuit.quickencompanion.ios");
		
	}*/

	
	@AfterTest
	public void testPlanExit() throws Exception{
		
		System.out.println("After test "+sHost.get());
		
		Engine.getDriver().quit();
		
	}
	
//	******  Commenting for now as we are not using this piece of code, For testrail integration we have written separate code ****
//	public static void addResultForTestCase(String testCaseId, int status,
//            String error) throws IOException, APIException {
//		 String TESTRAIL_USERNAME          = "kallol.das@quicken.com";
//		 String TESTRAIL_PASSWORD          = "Quicken@01";
//		  String RAILS_ENGINE_URL           = "https://quicken.testrail.net";
//        String testRunId = TEST_RUN_ID.get();
//
//        APIClient client = new APIClient(RAILS_ENGINE_URL);
//        client.setUser(TESTRAIL_USERNAME);
//        client.setPassword(TESTRAIL_PASSWORD);
//        Map data = new HashMap();
//        data.put("status_id", status);
//        data.put("comment", "Test Executed - Status updated automatically from Appium test automation.");
//        client.sendPost("add_result_for_case/"+testRunId+"/"+testCaseId+"",data );
//
//    }

	
}
