package support;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.commons.lang3.time.DateUtils;
import org.json.simple.JSONObject;
import org.openqa.selenium.ContextAware;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import com.saucelabs.saucerest.SauceREST;

import io.appium.java_client.HidesKeyboard;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.SupportsContextSwitching;

public class Helper {
	
	
	// This Methos we have commented for now as this method is to check whether the build is available on the dropbox or not. Generally it will be available.
	/*public void getFileNames() throws Exception{
		
		//Creating a File object for directory
	      File directoryPath = new File(this.getClass().getClassLoader().getResource("").getPath());
	      //List of all files and directories
	      String contents[] = directoryPath.list();
	      System.out.println("List of files and directories in the specified directory:");
	      for(int i=0; i<contents.length; i++) {
	         System.out.println(contents[i]);
	      }
	      
	      
	      System.out.println("********* under**********");
	      File f = new File(this.getClass().getClassLoader().getResource("").getPath()+"/props");
	      System.out.println(f.exists());
	      System.out.println(f.isDirectory());
	      System.out.println("********* under**********");
	}*/
	
	
public void uploadAndroidBuild() throws Exception {
		
		System.out.println("uploading build to SAUCE storage");
		System.out.println("build path... "+System.getProperty("buildpath"));
		String appPath = System.getProperty("buildpath");
		String [] a = appPath.split("/");
		System.out.println("Quicken Build Version from the path..."+a[a.length-1]);
		
		SauceREST r = new SauceREST("kalyan_grandhi", "10fde941-0bec-4273-bca6-c7c827f36234");
		File f = new File(appPath);
		String response = r.uploadFile(f, "Quicken.apk", true);
		System.out.println("Sauce Upload Response -->> "+response);
		System.out.println("Completed..uploading build to SAUCE storage");
		
	}
	
	public void uploadIOSBuild() throws Exception{
		
		System.out.println("uploading build to SAUCE storage");
		System.out.println("build path... "+System.getProperty("buildpath"));
		String appPath = System.getProperty("buildpath");
		String [] a = appPath.split("/");
		System.out.println("Quicken Build Version from the path..."+a[a.length-1]);
		
		SauceREST r = new SauceREST("kalyan_grandhi", "10fde941-0bec-4273-bca6-c7c827f36234");
		File f = new File(appPath);
		String response = r.uploadFile(f, "IOSRegression.zip", true);
		System.out.println("Sauce Upload Response -->> "+response);
		System.out.println("Completed..uploading build to SAUCE storage");
		
	}
	public void loadProperties(){
		
		try{
		
		//getFileNames();  // This is just to check whether the file is existing in the desired location or not.
		Enumeration readProps;
		String propFilePath = this.getClass().getClassLoader().getResource("").getPath();
		
		if(propFilePath.contains("/Users/administrator/workspace/")) {
			propFilePath = System.getProperty("buildpath").split("Quicken")[0]+"src/test/resources/props/test.properties";
		} else {
			propFilePath = this.getClass().getClassLoader().getResource("").getPath()+"/props/test.properties";
		}
		
		System.out.println("Path: "+propFilePath);
		
		File file = new File(propFilePath);
		FileInputStream fileInput = new FileInputStream(file);
		Properties properties = new Properties();
		properties.load(fileInput);
		fileInput.close();
		
		Globals globals = new Globals();

		readProps = properties.keys();
		globals.testProperty = new HashMap<String, String>();
		while (readProps.hasMoreElements()) {
			String key = (String) readProps.nextElement();
			String value = properties.getProperty(key);
			globals.testProperty.put(key, value);
					
		}
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	
	}
		catch(Exception e){
			System.out.println(e.getMessage());	
		}
		
}
	
	public String getHost(){
		
//		Globals globals = new Globals();
//		
//		if (System.getProperty("host") == null) {
//			//return globals.testProperty.get("host");
//			return Recovery.sHost.get();
//		}
//		return System.getProperty("host");
		
		return Recovery.sHost.get();
	}
	
	public String getUsername(){
		
		Globals globals = new Globals();
		
		return globals.testProperty.get("username");
	}
	
	public String getPassword(){
		
		Globals globals = new Globals();
		return globals.testProperty.get("password");
	}
	
	public String getDatasetName(){
		
		Globals globals = new Globals();
		return globals.testProperty.get("dataset");
	}
	
	public String getEngine(){
		
		Globals globals = new Globals();
		
//		if (System.getProperty("engine") == null) {
//			//return globals.testProperty.get("engine");
//			return Recovery.sEngine.get();
//		}
//		return System.getProperty("engine");
		
		return Recovery.sEngine.get();
	}
	
	public String getEnv() {
//		Globals globals = new Globals();
//
//		// return globals.testProperty.get("env");
//		return Recovery.sEnv.get();
		
		return Recovery.sEnv.get();
	}
	
	public String getTestName(){

		return Recovery.sTestName.get();
	}

	public String getDeviceName(){
		
		Globals globals = new Globals();
		
		return globals.testProperty.get("deviceName");
	}
	
	public String getAppiumURL(){
		
		String appiumURL;
		if(Recovery.sCloudService.get().equals("lambdaTest")) {
			LambdaTest lambdaTest = new LambdaTest();
			appiumURL = lambdaTest.getAppiumURL();
		} 
		
		else {
			SauceLabs sauceLabs = new SauceLabs();
			appiumURL = sauceLabs.getAppiumURL();
		}
		
		return appiumURL;
	}
	
	
	public MutableCapabilities getCloudCapabilities(){
		
//		DesiredCapabilities capabilities = new DesiredCapabilities();
		MutableCapabilities capabilities = new MutableCapabilities();
		if(Recovery.sCloudService.get().equals("lambdaTest")) {
			LambdaTest lambdaTest = new LambdaTest();
			capabilities = lambdaTest.getCloudCapabilities();
		} else {
			SauceLabs sauceLabs = new SauceLabs();
			capabilities = sauceLabs.getCloudCapabilities();
		}
			
		return capabilities;
	}
		
	
	public MutableCapabilities getLocalCapabilities() throws Exception{
		
//		DesiredCapabilities capabilities = new DesiredCapabilities();
		MutableCapabilities capabilities = new MutableCapabilities();
		
		if (getEngine().equals("android")){
			
			capabilities.setCapability("appium:deviceName","emulator-5554");
			capabilities.setCapability("appium:platformVersion","13.0");//8.1
			capabilities.setCapability("appium:noReset", true);
			capabilities.setCapability("appium:autoGrantPermissions", true);
			capabilities.setCapability("appium:ignoreUnimportantViews", true);
			capabilities.setCapability("appium:autoWebView", "true");
			capabilities.setCapability("platformName","Android");
			capabilities.setCapability("appium:newCommandTimeout", 1120);
			capabilities.setCapability("appium:automationName", "UiAutomator2");
			capabilities.setCapability("appium:appActivity","com.quicken.qm2014.MainActivity");
			capabilities.setCapability("appium:appPackage","com.quicken.qm2014");
			
		}
		else if(getEngine().equals("ios")){
			
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
			capabilities.setCapability("platformVersion", "16.4");
			capabilities.setCapability("appiumVersion", "2.0.0");
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 14 Pro");
			capabilities.setCapability("appium:bundleId","com.intuit.quickencompanion.ios");
	        capabilities.setCapability("appium:automationName","XCUITest");
	        capabilities.setCapability("appium:noReset", true);
	        capabilities.setCapability("appium:autoWebView", "true");
//	        capabilities.setCapability("autoAcceptAlerts", true); //autoAcceptAlerts
	        capabilities.setCapability("appium:autoGrantPermissions", true); 
	        capabilities.setCapability("appium:browserName", "");
	        capabilities.setCapability("appium:simpleIsVisibleCheck", true);
	        capabilities.setCapability("appium:app", "/Users/kvashisth/Downloads/companion.zip");
	        	
		}
		
		return capabilities;
		
	}

	
//	public DesiredCapabilities getCapabilities() throws Exception{
	public MutableCapabilities getCapabilities() throws Exception{
		
		String textToDisplay = "";
		if(Recovery.sHost.get().equals("cloud")) {
			 textToDisplay="Service:["+Recovery.sCloudService.get()+ "]";
		}
		System.out.println("Host:["+Recovery.sHost.get()+"], Engine:["+Recovery.sEngine.get()+"] "+textToDisplay);
		
		if (getHost().equals("cloud"))
			return getCloudCapabilities();
		else
			return getLocalCapabilities();	
		
	}
	
	public void waitForRefresh (int millisec) throws InterruptedException{
		Thread.sleep(millisec);	
	}
	
//	public void quicken_scroll_mobile() throws InterruptedException {
//		
//		if (this.getEngine().equals("android")) {
//			Dimension size = Engine.ad.manage().window().getSize();
//			Engine.ad.swipe(size.width - 10, size.height * 4 / 8, size.width - 10, size.height / 7, 500);
//			Thread.sleep(3000);	
//			Engine.ad.swipe(size.width - 10, size.height * 4 / 8, size.width - 10, size.height / 7, 500);
//		}
//		else {
//			
//			Dimension size = Engine.iosd.manage().window().getSize();
//			Engine.iosd.swipe(size.width - 10, size.height * 4 / 8, size.width - 10, size.height / 7, 500);
//			Thread.sleep(3000);	
//		}
//	}
	
	public void hideKeyBoard() throws Exception {
		
		((HidesKeyboard) Engine.getDriver()).hideKeyboard();
		
//		if (this.getEngine().equals("android")) 
//			Engine.ad.hideKeyboard();
//		else
//			Engine.iosd.hideKeyboard();
		
		Thread.sleep(1000);
		
	}
	
	public void getContext() throws Exception {
		
//		if (this.getEngine().equals("android")) 
//			Engine.ad.getContext();
//		else
//			Engine.iosd.getContext();
		
		((ContextAware) Engine.getDriver()).getContext();
		Thread.sleep(500);	
	}
	
	public Double processBalanceAmount(String sBalance) {
		
		String a[] = sBalance.split("");
		int i;
		String temp="";
		
		for(i=0; i<a.length; i++) {
			if (a[i].equals("$") || a[i].equals(","))
				//ignore
				;
			else
				temp = temp+a[i];		
		}
		
		return Double.parseDouble(temp);
	}
	
	public String getYesterdaysDate() {
		
		Date date = DateUtils.addDays(new Date(), -1);
		SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy");
		
		return sdf.format(date);
	}
	
	public String getFutureDaysDate(Integer daysFromToday) {
		
		Date date = DateUtils.addDays(new Date(), daysFromToday); //Date object and it will select today's date as we are passing 0.
		SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy"); //SimpleDateFormat class will return date object in any date format we need.
		return sdf.format(date); //It will return today's date as today's date (eg 4/08/2020)
		
	}
	public String getDateformat(Integer daysFromToday) {
		Date date = DateUtils.addDays(new Date(), daysFromToday);
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
		return sdf.format(date);
	}
	
	public String getPastDaysDate (Integer daysFromToday) {
		
		
		daysFromToday = daysFromToday*-1;
		Date date = DateUtils.addDays(new Date(), daysFromToday);
		SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
		
		return sdf.format(date);
		
	}
	
	public String convertDateFormat(String sDate) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("M/d/yy");
		Date date = sdf.parse(sDate);
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, YYYY");
		String dateStr = dateFormat.format(date);
		
		return dateStr;
	}
	
	public boolean isValidDateFormat (String date, String dateFormat) {
		
		if (dateFormat.equals("MMM dd, YYYY")) {
			String regex = "^(?:(((Jan(uary)?|Ma(r(ch)?|y)|Jul(y)?|Aug(ust)?|Oct(ober)?|Dec(ember)?)\\ 31)|((Jan(uary)?|Ma(r(ch)?|y)|Apr(il)?|Ju((ly?)|(ne?))|Aug(ust)?|Oct(ober)?|(Sept|Nov|Dec)(ember)?)\\ (0?[1-9]|([12]\\d)|30))|(Feb(ruary)?\\ (0?[1-9]|1\\d|2[0-8]|(29(?=,\\ ((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)))))))\\,\\ ((1[6-9]|[2-9]\\d)\\d{2}))";
			Pattern pattern = Pattern.compile(regex); 
	        java.util.regex.Matcher matcher = pattern.matcher((CharSequence)date);
	        return matcher.matches(); 
		} else {
			// Use this for different date format validation if required
			return false;
		}
	}
	
	public String getLastMonthsDate() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");  
       return formatter.format(LocalDate.now().minusMonths(1)); 
	}
	
	public String[] getLastSixMonths() {
		
		String[] s = new String[6];
		 for (int i = 0; i < 6; i++) {
		        YearMonth date = YearMonth.now().minusMonths(i);
		        String monthName = date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
		        s[i]=monthName;
		    }
		 
		 return s;
	}
	
	public String getCurrentTime () {
		Calendar c = Calendar.getInstance();
		long time = c.getTimeInMillis();
		String timeAsString = String.valueOf(time);		 
		return timeAsString;
	}	
		
	public static String getCurrentMonth () {
		Calendar now = Calendar.getInstance();

	    String[] strMonths = new String[] { "January", "February", "March", "April", "May", "June", "July", "August",
	        "September", "October", "November", "December" };	
	    String currentMonth = strMonths[now.get(Calendar.MONTH)];
		return currentMonth;
		
	}		

}
