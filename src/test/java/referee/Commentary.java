package referee;

import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import support.Recovery;

public class Commentary {
	
	 public static void log (LogStatus ls, String msg){
		  ExtentTest quickenTest = Recovery.quickenTest;
		  
		  
		  //quickenTest.log(ls, msg);
		 // System.out.println(ls);
		  
		  switch (ls)
		  {
		  case INFO:
			  quickenTest.log(ls, msg);
			  break;
		  case FAIL:
			  quickenTest.log(ls, msg);
			  try{
				  Assert.assertTrue(false, msg);
			  }
			  catch(Throwable t){
				  	System.out.println("caught the error.."+t);
					ErrorUtil.addVerificationFailure(t);
					System.out.println("added to failure list......");
				}
			  break;
			default:
				System.out.println("Invalid logstatus "+ls);
			  
		  }
		  
		  
	  }

}
