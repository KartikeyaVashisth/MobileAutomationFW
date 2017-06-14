package referee;

import org.openqa.selenium.NoSuchElementException;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileElement;
import support.Recovery;

public class Verify {
	
	public static boolean objExists (MobileElement me){
		ExtentTest quickenTest = Recovery.quickenTest;
		
		try{	
			me.isDisplayed();
			return true;	
		}
		catch (NoSuchElementException n){
			return false;	
		}
		catch (Throwable e){
			System.out.println("****Error Occured****: Method->objExists");
			quickenTest.log(LogStatus.ERROR,e.getMessage());
			return false;	
		}
		
	}

}
