package referee;

import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentManager {
	
	private static ExtentReports extent;
	
	
	public static ExtentReports initializeRepObject(){
		
		if (extent == null){
			
			Date curTimeStamp = new Date();
			String repName = "scorecard"+curTimeStamp.toString().replace(":", "_").replace(" ", "_")+".html";
			
			extent = new ExtentReports(System.getProperty("user.dir") +"/test-output/"+repName, true);
		}
		
		return extent;
	}
	
	

}
