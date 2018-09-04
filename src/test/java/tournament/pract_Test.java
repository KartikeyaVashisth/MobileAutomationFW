package tournament;

import org.testng.annotations.Test;

public class pract_Test {

	@Test
	public void tc1_test(){
		
		String buildNumber = System.getProperty("buildnumber"); 
		System.out.println("************* kalyan build number **********");
		System.out.println(buildNumber);
	}
}
