package referee;

import java.util.HashMap;
import java.util.Map;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ETM {
	
	
	static ExtentReports report = EM2.getInstance();

	static Map<Integer,ExtentTest> map = new HashMap<Integer,ExtentTest>();
	public static Map<Integer,ExtentReports> map2 = new HashMap<Integer,ExtentReports>();

	public static synchronized ExtentTest startTest(String testName)
	{
		return startTest(testName, "");
	}
	
	public static synchronized ExtentTest startTest(String testName,String description)
	{
		ExtentTest testRep = report.startTest(testName, description);
		map.put((int)Thread.currentThread().getId(), testRep);
		map2.put((int)Thread.currentThread().getId(), report);
		return testRep;
	}

	public static synchronized ExtentTest getTest()
	{
		return map.get((int)Thread.currentThread().getId());
	}

	public static synchronized void endTest()
	{
		
		
		report.endTest(map.get((int)Thread.currentThread().getId()));
	}

	
	
	
	/*
	//static ExtentReports report = EM2.getInstance();
	static ThreadLocal<ExtentReports> report = new ThreadLocal<ExtentReports>();

	//static Map<Integer,ExtentTest> map = new HashMap()<Integer,ExtentTest>();
	
	static Map<Integer, ExtentTest> map = new HashMap<Integer, ExtentTest>();

	public static synchronized ExtentTest startTest(String testName)
	{
		return startTest(testName, "");
	}
	
	public static synchronized ExtentTest startTest(String testName,String description)
	{
		//report.get().st
		report.set(EM2.getInstance());
		ExtentTest testRep = report.get().startTest(testName, description);
		map.put((int)Thread.currentThread().getId(), testRep);
		System.out.println(Thread.currentThread().getId()+"gggg");
		return testRep;
	}

	public static synchronized ExtentTest getTest()
	{
		System.out.println(Thread.currentThread().getId()+"jjjj");
		return map.get((int)Thread.currentThread().getId());
	}

	public static synchronized void endTest()
	{
		report.get().endTest(map.get((int)Thread.currentThread().getId()));
	}*/

}
