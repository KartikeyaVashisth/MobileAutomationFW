package support;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestRailManager {
	
	
	//public static String TEST_RUN_ID= "2320";
	public static String TESTRAIL_USERNAME = "kallol.das@quicken.com";
	public static String TESTRAIL_PASSWORD = "Quicken@01";
	public static String RAIL_ENGINE_URL = "https://quicken.testrail.com/";
	public static final int TEST_CASE_PASSED_STATUS = 1;
	public static final int TEST_CASE_FAILED_STATUS = 5;
	
	
	public static void addResultForTestCase(String testRunId, String testCaseId, int status,
            Throwable throwable) throws IOException, APIException {
		
        //String testRunId = TEST_RUN_ID;
		

        APIClient client = new APIClient(RAIL_ENGINE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        Map data = new HashMap();
        data.put("status_id", status);
        data.put("comment", "Test Executed - Status updated automatically from Appium test automation.");
        client.sendPost("add_result_for_case/"+testRunId+"/"+testCaseId+"",data );

    }


}


/*
 * Information: For the Testrail integration with our automation project We need this file. Along with this file we have API client , API exception file under Support
 * Which are ready made file given by Java. 
 * Also Under recovery there is a commented method that we have uncomment and the above another methid is there which we need to comment to enable the testrail integrarion.
 * Lastly Under each test class we have to mention the Test caseid for each test case compare to the test rail.
 * Run ID we have to mention only once. We will get the run id from the Testrail 'test reult & execution' tab.
 */
