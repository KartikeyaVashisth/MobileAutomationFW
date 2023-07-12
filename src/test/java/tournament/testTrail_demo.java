package tournament;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;

import support.APIClient;
import support.Recovery;

public class testTrail_demo extends Recovery {
	
	@Test(priority = 1)
	public void verifyAccountTest() throws Exception {
		
//		this.TEST_RUN_ID.set("1625");
		this.testCaseId.set("921864");
		
		Assert.assertTrue(false);
		
		
		
		
	}
	
	@Test(priority = 2)
	public void verifyAccountTest1() throws Exception {
		
	//	this.TEST_RUN_ID.set("1625");
//		this.testCaseId.set("895967");
//		String RAILS_ENGINE_URL = "https://quicken.testrail.com";
//		String TESTRAIL_USERNAME = "kallol.das@quicken.com";
//		String TESTRAIL_PASSWORD = "Quicken@01";
		
		
		Assert.assertTrue(true);
		//this.addResultForTestCase("921864", testTrailStatus, "");
//		addResultForTestCase("921864", 1, "");
		
		//APIClient client = new APIClient(RAILS_ENGINE_URL);
		//client.setUser(TESTRAIL_USERNAME);
	   // client.setPassword(TESTRAIL_PASSWORD);
		//JSONObject c = (JSONObject) client.sendGet("get_case/895966");
	    //JSONObject c = (JSONObject) client.sendGet("get_results_for_run/16222");
		
		//get_results_for_run
		//System.out.println(c.get("title"));
		
		
		
	}

}
