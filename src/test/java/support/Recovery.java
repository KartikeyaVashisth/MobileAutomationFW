package support;

import java.lang.reflect.Method;
import java.util.List;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.internal.Utils;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import referee.ErrorUtil;
import referee.ExtentManager;

public class Recovery {
	
	public static ExtentTest quickenTest;
	
	
	@BeforeSuite
	public static void testPlanEnter(){
		
		System.out.println("....Initializing Reports....");
		
		ExtentManager em = new ExtentManager();
		em.initializeRepObject();
		
		
	}
	
	@BeforeMethod
	public static void testCaseEnter(Method method) throws Exception{
		
		// reset errors
		ErrorUtil.resetTestErrors();
		
		// Load properties
		Helper h = new Helper();
		h.loadProperties();
		
		
		// start test logs
		System.out.println(method);
		String methodName = method.toString().replace("public", "").replace("void","");
		methodName=methodName.substring(methodName.lastIndexOf(".") + 1).trim();
		System.out.println(methodName);
		
		ExtentManager em = new ExtentManager();
		//em.quickenTest = em.initializeRepObject().startTest(methodName);
		quickenTest = em.initializeRepObject().startTest(methodName);
		
		// set driver
		Engine.setDriver();
			
	}
	
	@AfterMethod
	public static void testCaseExit(ITestResult result, Method method){
		ExtentManager em = new ExtentManager();
		
		List<String> softFails = ErrorUtil.getTestErrors();
		if (result.getStatus() == ITestResult.FAILURE) {
		      quickenTest.log(LogStatus.FAIL, result.getThrowable());
		   } 
		
	
		
		if (softFails.size() != 0){
			result.setStatus(ITestResult.FAILURE);
			
			
			int size = softFails.size();
			//if there's only one failure just set that
			if (size == 1) {
				
				 quickenTest.log(LogStatus.FAIL, softFails.get(0));
				 Throwable t = new Exception(softFails.get(0));
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
		
		// close driver
		if (Engine.mobilePlay != null)
			Engine.mobilePlay.quit();
		
	}
	
	

}
