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

import io.appium.java_client.MobileElement;
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
	public static ThreadLocal<String> sEnv= new ThreadLocal<String>();
	public static ThreadLocal<String> TEST_RUN_ID = new ThreadLocal<String>();
	public static ThreadLocal<String> testCaseId = new ThreadLocal<String>();
	
	public Boolean wannaUploadTheBuild(){
		
		String uploadBuild = System.getProperty("buildpath");
		
		if (uploadBuild == null || uploadBuild.equals("DoNotUpload"))
			return false;
		
		System.out.println("UPLOADING BUILD TO SAUCE > TRUE");
		return true;
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
		
		if (RUN_ID.equals("readFromPropertiesFile"))
			TEST_RUN_ID.set("readFromPropertiesFile");
		
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
			
			if (!RUN_ID.equals("readFromPropertiesFile")) {
				System.out.println("Alert Alert!");
				TEST_RUN_ID.set(RUN_ID);
			}
		}
		
		System.out.println("Engine: ["+sEngine.get()+"], Host: ["+sHost.get()+"], Env: ["+sEnv.get()+"]");
		System.out.println("Before Test Run ID: "+TEST_RUN_ID.get());
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
			((AndroidDriver<MobileElement>)Engine.getDriver()).startActivity(activity);
			Thread.sleep(3000);

		}
			
		else
			((IOSDriver<MobileElement>)Engine.getDriver()).launchApp();
			
		Thread.sleep(14000);
		System.out.println("BeforeMethod Ends........."+m.getName());
		
	}
	
	@AfterMethod
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
		
		Engine.tlDriver.get().closeApp();
		if (this.sEngine.get().equals("android"))
			((AndroidDriver<MobileElement>)Engine.getDriver()).closeApp();
		else
			((IOSDriver<MobileElement>)Engine.getDriver()).closeApp();
		
		System.out.println("AfterMethod: "+TEST_RUN_ID.get());
		
		if (!TEST_RUN_ID.get().equals("readFromPropertiesFile"))
			this.addResultForTestCase(testCaseId.get(), testTrailStatus, "");
		System.out.println("");
		System.out.println("-----------------------------------------------------------------");
		System.out.println("");
		
		
	}
	
	@AfterTest
	public void testPlanExit() throws Exception{
		
		System.out.println("After test "+sHost.get());
		
		Engine.getDriver().quit();
		
	}
	
	public static void addResultForTestCase(String testCaseId, int status,
            String error) throws IOException, APIException {
		 String TESTRAIL_USERNAME          = "kalyan.grandhi@quicken.com";
		 String TESTRAIL_PASSWORD          = "Quicken4Win!";
		  String RAILS_ENGINE_URL           = "https://quicken.testrail.net";
        String testRunId = TEST_RUN_ID.get();

        APIClient client = new APIClient(RAILS_ENGINE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        Map data = new HashMap();
        data.put("status_id", status);
        data.put("comment", "Test Executed - Status updated automatically from Appium test automation.");
        client.sendPost("add_result_for_case/"+testRunId+"/"+testCaseId+"",data );

    }

	
}
