package referee;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import support.Recovery;

public class Verify {
	
	/*public static boolean objExists (MobileElement me){
		ExtentTest quickenTest = Recovery.quickenTest;
		
		try{	
			me.isDisplayed();
			System.out.println(me.isDisplayed());
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
		
	}*/
	
	public static boolean objExists (WebElement me){
		ExtentTest quickenTest = Recovery.quickenTest;
		
		try{
			boolean b = me.isDisplayed();
			
			return b;	
		}
		catch (NoSuchElementException n){
			return false;	
		}
		catch (Throwable e){
			//System.out.println("****Error Occurred****: Method->objExists"+e.getMessage());
			quickenTest.log(LogStatus.ERROR,e.getMessage());
			return false;	
		}
		
	}
	
	public static boolean objExists_check (WebElement me){
		ExtentTest quickenTest = Recovery.quickenTest;
		
		try{	
			me.isDisplayed();
			return true;	
		}
		catch (NoSuchElementException n){
			return false;	
		}
		catch (Throwable e){
			
			return false;	
		}
		
	}
	
	public static boolean waitForObject (WebElement me, Integer wttime) throws Exception{
		
		Integer iCount = 0;
		
		while (iCount < wttime){
			
			if (! objExists_check(me))
				Thread.sleep(12000);
			else
				return true;
				
			//System.out.println("12secs * "+iCount);
			Commentary.log(LogStatus.INFO, "12secs * "+iCount);
			iCount++;	
		}
		
		return false;	
		
	}
	
	public static boolean waitForObjectToDisappear (WebElement me, Integer wttime) throws Exception{
		
		Integer iCount = 0;
		
		while (iCount < wttime){
			
			if (objExists_check(me))
				Thread.sleep(10000);
			else
				return true;
				
			iCount++;		
			
		}
		
		return false;	
		
	}

}
