package support;

import java.io.File;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.saucelabs.saucerest.SauceREST;

public class SauceLabs {

	
	private static String USERNAME = "kalyan_grandhi";
	private static String ACCESS_KEY = "10fde941-0bec-4273-bca6-c7c827f36234";
	
	
	
	
	public String getAppiumURL(){
		if (Recovery.sHost.get().equals("cloud")){
			return "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
		} else
			return "http://127.0.0.1:4723/wd/hub";
	}
	
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
	
	
public DesiredCapabilities getCloudCapabilities(){
	
	
		Helper h = new Helper();
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
	
		
		
		if (h.getEngine().equals("android")){ 
			
			// ** SauceLab related desired capabilities for cloud android **
			
			
			capabilities.setCapability(CapabilityType.BROWSER_NAME,"Android");
			capabilities.setCapability("name", h.getTestName());
			capabilities.setCapability("appiumVersion", "1.17.1");
			capabilities.setCapability("automationName","appium");
			capabilities.setCapability("platformVersion","11.0");
			capabilities.setCapability("deviceName","Google Pixel 3 XL GoogleAPI Emulator");
			//capabilities.setCapability("deviceName","Android Emulator");
			//capabilities.setCapability("deviceName","Samsung Galaxy S9 Plus HD GoogleAPI Emulator");
			//capabilities.setCapability("deviceName","Android GoogleAPI Emulator");
			//capabilities.setCapability("platformVersion","8.1"); 
			
			capabilities.setCapability("deviceOrientation", "portrait");
			capabilities.setCapability("browserName", "");
			capabilities.setCapability("platformName","Android");
			capabilities.setCapability("autoWebView", "true");
			capabilities.setCapability("maxDuration", 4000);
			capabilities.setCapability("newCommandTimeout", 1120);
			capabilities.setCapability("noResetValue", true);
			capabilities.setCapability("ignoreUnimportantViews", true);
			//capabilities.setCapability("chromedriverExecutable","/Users/kgrandhi/Documents/ChromeDriver/2.18/chromedriver");
			//capabilities.setCapability("app", "sauce-storage:QuickenRelease.apk");
			capabilities.setCapability("app", "sauce-storage:Quicken.apk");
			capabilities.setCapability("appPackage","com.quicken.qm2014");
			capabilities.setCapability("appActivity","com.quicken.qm2014.MainActivity");
			
}
		
else if(h.getEngine().equals("ios")){
			
			// ** SauceLab related desired capabilities for cloud android **
			
			capabilities.setCapability("platformName", "iOS");
			capabilities.setCapability("name", h.getTestName());
			//capabilities.setCapability("deviceName", "iPhone 8 Simulator");
			//capabilities.setCapability("platformVersion", "12.0"); //9.3
			//capabilities.setCapability("platformVersion", "11.2");
			capabilities.setCapability("appiumVersion", "1.15.0");
			capabilities.setCapability("deviceName","iPhone Simulator");
			capabilities.setCapability("platformVersion","13.0");
			capabilities.setCapability("browserName", "");
			capabilities.setCapability("deviceOrientation", "portrait");
			capabilities.setCapability("autoWebView", "true");
//			capabilities.setCapability("autoAcceptAlerts", true);
		    capabilities.setCapability("autoGrantPermissions", true);
		    capabilities.setCapability("maxDuration", 4000);
			capabilities.setCapability("newCommandTimeout", 1120);
			capabilities.setCapability("noResetValue", true);
			capabilities.setCapability("app", "sauce-storage:IOSRegression.zip");			
			capabilities.setCapability("appPackage","com.intuit.quickencompanion.ios");
			capabilities.setCapability("automationName","XCUITest");
			
}
		return capabilities;
}
}
