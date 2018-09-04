package referee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.ITestResult;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentTest;

import support.Recovery;

public class ErrorUtil {
	private static Map<ITestResult,List> verificationFailuresMap = new HashMap<ITestResult,List>();
	private static Map<ITestResult,List> skipMap = new HashMap<ITestResult,List>();
	private static List<String> lsErrors = new ArrayList<String>();

	
	     public static void addVerificationFailure(Throwable e) {
	    	 
				List verificationFailures = getVerificationFailures();
				verificationFailuresMap.put(Reporter.getCurrentTestResult(), verificationFailures);
				verificationFailures.add(e);
				lsErrors.add(e.getMessage());
				
			}
		  
		  public static List getVerificationFailures() {
			
				List verificationFailures = verificationFailuresMap.get(Reporter.getCurrentTestResult());
				return verificationFailures == null ? new ArrayList() : verificationFailures;
			}
		  
		  public static List getTestErrors(){
			  
			  return lsErrors;
		  }
		  
		  public static void resetTestErrors(){
			  
			  lsErrors = new ArrayList<String>();
			  
		  }
		  
		 
		 
		  
}
