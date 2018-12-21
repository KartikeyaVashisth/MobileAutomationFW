package support;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.internal.Utils;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.saucelabs.saucerest.SauceREST;

import io.appium.java_client.MobileElement;
import referee.ErrorUtil;
import referee.ExtentManager;
import referee.Verify;

public class Recovery {
	
	public static ExtentTest quickenTest;
	
	
	@BeforeSuite
	public static void testPlanEnter() throws Exception{
		
		
		System.out.println("uploading build to SAUCE storage");
		
		System.out.println("build path... "+System.getProperty("buildpath"));
		String appPath = System.getProperty("buildpath");
		
		String [] a = appPath.split("/");
		System.out.println("Quicken Build Version from the path..."+a[a.length-1]);
		
		//String appPath = "/Users/jenkins/workspace/Quicken_ReactNative_Develop/develop/android/Quicken.5.8.0.11928.debug.apk";
		SauceREST r = new SauceREST("kalyan_grandhi", "10fde941-0bec-4273-bca6-c7c827f36234");
		File f = new File(appPath);
		String response = r.uploadFile(f, a[a.length-1], true);
		System.out.println("Sauce Upload Response -->> "+response);
		System.out.println("Completed..uploading build to SAUCE storage");
		
		//Users/jenkins/workspace/Quicken_ReactNative_Develop/develop/android/Quicken.5.8.0.11928.debug.apk
		
		System.out.println("....Initializing Reports....");
		
		ExtentManager em = new ExtentManager();
		em.initializeRepObject();
		
		System.out.println("....Loading Properties....");
		// Load properties
		Helper h = new Helper();
		h.loadProperties();
		
		System.out.println("....Setting up Mobile Engine....");
		// test
		Engine.setDriver();
		System.out.println("Mobile Engine on...");
		
		if (h.getEngine().equals("android"))
			Engine.ad.launchApp();
		else
			Engine.iosd.launchApp();
		
		System.out.println("launch done...");
		
		
	}
	
	@BeforeMethod
	public static void testCaseEnter(Method method) throws Exception{
		System.out.println("Before Method started...........");
		
		
		// reset errors
		ErrorUtil.resetTestErrors();
		
		// Load properties
		/*Helper h = new Helper();
		h.loadProperties();*/
		
		
		// start test logs
		String methodName = method.toString();//.replace("public", "").replace("void","").trim();
		methodName = methodName.replace("public", "").replace("void","").replace(" throws java.lang.Exception", "").trim();
		methodName=methodName.substring(methodName.lastIndexOf(".") + 1).trim();
		System.out.println(methodName);
		
		ExtentManager em = new ExtentManager();
		//em.quickenTest = em.initializeRepObject().startTest(methodName);
		quickenTest = em.initializeRepObject().startTest(methodName);
		
		quickenTest.log(LogStatus.INFO,"StartTime "+new SimpleDateFormat("HH.mm.ss").format(new Date()));
		
		// set driver
		//Engine.setDriver();
		if (Engine.ad != null) {
			//Engine.ad.launchApp();
			Engine.ad.startActivity("com.quicken.qm2014", "com.quicken.qm2014.MainActivity");
			//System.out.println(".....waiting for spinner icon to disappear.....");
			//Verify.waitForObjectToDisappear((MobileElement)Engine.ad.findElement(By.xpath("//android.widget.ProgressBar")), 30)	;
			Thread.sleep(12000);
		}
		else {
			System.out.println("app launch start beforemethod.....");
			Engine.iosd.launchApp();
			System.out.println("app launch end beforemethod.....");
		}
			
		
		
		Thread.sleep(5000);
		//System.out.println("app launched.....");
		//System.out.println("Before Method end...........");
			
	}
	
	@AfterMethod
	public static void testCaseExit(ITestResult result, Method method) throws InterruptedException{
		
		System.out.println("After Method started...........");
		ExtentTest quickenTest = Recovery.quickenTest;
		ExtentManager em = new ExtentManager();
		
		List<String> softFails = ErrorUtil.getTestErrors();
		if (result.getStatus() == ITestResult.FAILURE) {
		      quickenTest.log(LogStatus.FAIL, result.getThrowable());
		   } 
		
	
		System.out.println("softFails size...."+softFails.size());
		System.out.println("softFails size...."+softFails.size());
		if (softFails.size() != 0){
			System.out.println("TRUEEEEEEEEEE");
		}
		System.out.println(softFails.size() != 0);
		if (softFails.size() != 0){
			result.setStatus(ITestResult.FAILURE);
			System.out.println("set the status to failure......!");
			
			
			int size = softFails.size();
			//if there's only one failure just set that
			if (size == 1) {
				
				 quickenTest.log(LogStatus.FAIL, softFails.get(0));
				 Throwable t = new Exception(softFails.get(0));
				 System.out.println("Throwable is settt...........!"+softFails.get(0));
				result.setThrowable(t);
			} else {
				//create a failure message with all failures and stack traces (except last failure)
				StringBuffer failureMessage = new StringBuffer("Multiple failures (").append(size).append("):nn");
				for (int i = 0; i < size-1; i++) {
					failureMessage.append("Failure ").append(i+1).append(" of ").append(size).append(":n");
					Throwable t = new Exception(softFails.get(i));//verificationFailures.get(i);
					String fullStackTrace = Utils.stackTrace(t, false)[1];
					failureMessage.append(fullStackTrace).append("nn");
				}

				//final failure
				Throwable last = new Exception(softFails.get(size-1));//verificationFailures.get(size-1);
				failureMessage.append("Failure ").append(size).append(" of ").append(size).append(":n");
				failureMessage.append(last.toString());

				//set merged throwable
				Throwable merged = new Throwable(failureMessage.toString());
				merged.setStackTrace(last.getStackTrace());

				result.setThrowable(merged);
				quickenTest.log(LogStatus.FAIL, result.getThrowable());
				
			}
			
		}
		else{
			
			quickenTest.log(LogStatus.PASS,method.getName());
			
			
		}
			
		em.initializeRepObject().endTest(quickenTest);
		em.initializeRepObject().flush();
		Thread.sleep(3000);
		
		if (Engine.ad != null){
			Engine.ad.closeApp();
			//Engine.ad.resetApp();
			Thread.sleep(2000);
			//Engine.ad.startActivity("com.quicken.qm2014", "com.quicken.qm2014.MainActivity");
			Thread.sleep(2000);
			System.out.println("reset done....");
		}
		else{
			Engine.iosd.closeApp();
			//Engine.iosd.resetApp();
			Thread.sleep(2000);
		}
			
		
		
		System.out.println("After Method end...........");
		quickenTest.log(LogStatus.INFO,"EndTime "+new SimpleDateFormat("HH.mm.ss").format(new Date()));
		
		
		
		
		
	}
	
	@AfterSuite
	public void TestPlanExit(){
		
		
		Helper h = new Helper();
		
		
		// close driver
		if (h.getEngine().equals("android")){
			//Engine.ad.close();
			Engine.ad.quit();
			System.out.println("iffffffffffff");
		}
		else{
			Engine.iosd.close();
		}
		
		
		
		
	}
	
	

}
