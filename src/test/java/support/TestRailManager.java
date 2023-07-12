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
