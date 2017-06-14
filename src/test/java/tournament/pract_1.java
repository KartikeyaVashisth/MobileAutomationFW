package tournament;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import dugout.SignInPage;
import referee.ErrorUtil;
import referee.ExtentManager;
import support.Engine;
import support.Recovery;

public class pract_1 extends Recovery {
	
	ExtentReports rep = ExtentManager.initializeRepObject();
	
	
	@Test
	public void tt1() {
		ExtentTest quickenTest = Recovery.quickenTest;
		System.out.println("in test case.....");
		quickenTest.log(LogStatus.INFO,"tt1");
		Assert.assertEquals(true, false);
	}
	
	@Test
	public void tt2() {
		ExtentTest quickenTest = Recovery.quickenTest;
		System.out.println("in test case.....");
		quickenTest.log(LogStatus.INFO,"tt2");
		Assert.assertEquals(true, true);
		
		try{
			Assert.assertEquals(true, false);	
			
		}
		catch (Throwable t){
			System.out.println("ddddddddddddddddd");
			System.out.println(t.getMessage());
			ErrorUtil.addVerificationFailure(t);
		}
	}
	@Test
	public void tt3() throws Exception {
		
		SignInPage signIn = new SignInPage();
		
		signIn.signIn();
	}

}
