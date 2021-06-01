package referee;

import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;

import support.Recovery;

public class EM2 {
	
	
	static Date date =new Date();
	public static ExtentReports extent;
	public static ThreadLocal<String> fname = new ThreadLocal<String>();
	public static String filename = ""+date.getTime()+".html";
	public static ThreadLocal<String> rPath = new ThreadLocal<String>();
	public static String reportPath= "./Reports/extent_reports/"+ (date.getMonth()+1) +"/"+date.getDate()+"/"+filename;
	
	public synchronized static ExtentReports getInstance()
	{
		if(extent == null)
		{
			
			extent = new ExtentReports(reportPath,true);
		}
		return extent;
		
	}
	
	
	
	
	
	/*
	static Date date =new Date();
	public static String s = Recovery.s.get();
	public static ThreadLocal <ExtentReports> extent ; //= new ThreadLocal<ExtentReports>()
	public static ThreadLocal<String> fname = new ThreadLocal<String>();
	public static ThreadLocal<String> rPath = new ThreadLocal<String>();
	
	public static String filename = "_"+date.getTime()+".html";
	public static String reportPath= "./Reports/extent_reports/"+ (date.getMonth()+1) +"/"+date.getDate()+"/"+filename;
	
	
	public static ExtentReports getInstance()
	{
		if(extent == null)
		{
			
			
			fname.set(Recovery.s.get()+filename);
			
			rPath.set("./Reports/extent_reports/"+ (date.getMonth()+1) +"/"+date.getDate()+"/"+fname.get());
			
			//extent.set(rPath.get(),true);
			
			extent.set(new ExtentReports(rPath.get(),true));
			
			
			
			
			//extent = new ExtentReports(rPath.get(),true);
			
		}
		return extent.get();
		
	}*/
}
