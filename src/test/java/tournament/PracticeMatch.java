package tournament;

import java.io.IOException;
import support.Recovery;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.IInvokedMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import referee.ErrorUtil;



public class PracticeMatch extends Recovery  {
	
	protected Logger QuickenLogger = Logger.getLogger("MyLogger");
	private static ExtentReports extent;
	
	/*ExtentTest test;
	ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("/users/kgrandhi/extent.html");*/

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		

	}
	
	/*@Test
	public void tc1() throws IOException{
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		test = extent.createTest("MyFirstTest", "Sample description");

		
		
		
		// log(Status, details)
        test.log(Status.INFO, "This step shows usage of log(status, details)");

        // info(details)
        test.info("This step shows usage of info(details)");
        
        // log with snapshot
        test.fail("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
        
        // test with snapshot
        test.addScreenCaptureFromPath("screenshot.png");
        
        // calling flush writes everything to the log file
        extent.flush();
		
	}*/
	
	@Test
	public void tc2(){
		
		QuickenLogger.log(Level.INFO, "step1");
		QuickenLogger.log(Level.INFO, "step2");
		QuickenLogger.log(Level.WARNING, "step3");
		QuickenLogger.log(Level.SEVERE, "step4");
		QuickenLogger.log(Level.INFO, "step5");
		
	}
	
	@Test
	public void Over2()  {
		
		try{
			
			Assert.assertEquals(true, false);
			
			
		}
		catch (Throwable t){
			System.out.println("ddddddddddddddddd");
			System.out.println(t.getMessage());
			ErrorUtil.addVerificationFailure(t);
		}
		System.out.println("ddddddddddddddddd");
		
		try{
			
			Assert.assertEquals(true, false);
			
			
		}
		catch (Throwable t){
			System.out.println("ddddddddddddddddd");
			System.out.println(t.getMessage());
			ErrorUtil.addVerificationFailure(t);
		}
		System.out.println("ddddddddddddddddd");
		
		
	}
	
	@Test
	public void Over3()  {
		
		try{
			
			Assert.assertEquals(true, false);
			System.out.println("nnnnnn");
			
			
		}
		catch (Throwable t){
			System.out.println("ddddddddddddddddd");
			System.out.println(t.getMessage());
			ErrorUtil.addVerificationFailure(t);
		}
		System.out.println("ddddddddddddddddd");
		
		
	}
	
	@Test
	public void tc4(){
		
		extent = new ExtentReports(System.getProperty("user.dir") +"/test-output/wohoo.html", true);

        // creates a toggle for the given test, adds all log events under it    
        ExtentTest test = extent.startTest("Extent Report Test", "Sample description");
     

        // log(LogStatus, details)
        test.log(LogStatus.INFO, "Logging info...(logStatus, details)");

        // report with snapshot
        String img = test.addScreenCapture(System.getProperty("user.dir") +"/test-output/sw.png");
       
        
        try{
			
			Assert.assertEquals(true, false);
			
			
		}
		catch (Throwable t){
			System.out.println("ddddddddddddddddd");
			System.out.println(t.getMessage());
			 test.log(LogStatus.ERROR, "Image", "Error: "+t + img);
			//test.log(LogStatus.ERROR, t);
			test.log(LogStatus.INFO, "......text after error ........");
		}
        
        
        
        // end test
        extent.endTest(test);
        
        // calling flush writes everything to the log file
        extent.flush();
		
	}
	
	@Test
	public void tt() {
		System.out.println("in test case.....");
		Assert.assertEquals(true, true);
	}
	
	/*@AfterMethod
	public void endTC( ITestResult result){
		
		System.out.println(".... after tc");
		
		System.out.println(result.getStatus());
		List<Throwable> verificationFailures = ErrorUtil.getVerificationFailures();
		if (verificationFailures.size() != 0) {
			//set the test to failed
			result.setStatus(ITestResult.FAILURE);
			System.out.println("111111111111111111111111111111111111111");
			System.out.println(result.getThrowable());
			System.out.println("111111111111111111111111111111111111111");
			
			
		
	}
	
	
	}*/
	}
