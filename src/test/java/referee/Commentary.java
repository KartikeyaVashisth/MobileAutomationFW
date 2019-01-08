package referee;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import support.Recovery;

public class Commentary extends Recovery  {
	
	/* public static void log (LogStatus ls, String msg){
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
		  
		  
	  }*/
	
	public static void log (SoftAssert sa, LogStatus ls, String msg ) {
		
		quickenTest.log(ls, msg);
		System.out.println(msg);
		
		
		if (ls == LogStatus.FAIL) {
			//ErrorUtil.addVerificationFailure(msg);
			ErrorUtil eu = new ErrorUtil();
			eu.addSoftFailure(msg);
			sa.assertTrue(1>2, msg+"\n");
			//sa.assertTrue(false, msg);
		}
		
		
		
	}

}
