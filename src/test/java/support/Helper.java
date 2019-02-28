package support;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.lang3.time.DateUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Helper {
	
	public void loadProperties(){
		
		try{
		Enumeration readProps;
		
		String propFilePath = this.getClass().getClassLoader().getResource("").getPath()+"/props/test.properties";
		
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
		
		Globals globals = new Globals();
		
		if (System.getProperty("host") == null)
			return globals.testProperty.get("host");
		
		return System.getProperty("host");
		
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
		
		
		
		if (System.getProperty("engine") == null) 
			return globals.testProperty.get("engine");
		
		
		return System.getProperty("engine");
		
	}
	
	public String getDeviceName(){
		
		Globals globals = new Globals();
		
		return globals.testProperty.get("deviceName");
		
	}
	
	public void initAppiumDriver(){
		
		try{
			String url;
			URL serverURL;
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			
			if (getHost().equals("cloud")){
				
				capabilities.setCapability(CapabilityType.BROWSER_NAME,"Android");
				capabilities.setCapability("appiumVersion", "1.6"); // 1.5.3
				capabilities.setCapability("automationName","appium");
				capabilities.setCapability("deviceName","Android Emulator");
				capabilities.setCapability("deviceOrientation", "portrait");
				capabilities.setCapability("browserName", "");
				capabilities.setCapability("platformVersion","6.0");//5.1
				capabilities.setCapability("platformName","Android");
				capabilities.setCapability("autoWebView", "true");
				capabilities.setCapability("newCommandTimeout", 1120);
				capabilities.setCapability("app", "sauce-storage:Quicken.apk");
				capabilities.setCapability("appPackage","com.quicken.qm2014_1");
				capabilities.setCapability("appActivity","com.quicken.qm2014.MainActivity");
				String USERNAME = "kalyan_grandhi";
				String ACCESS_KEY = "10fde941-0bec-4273-bca6-c7c827f36234";
				url = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
	
			}
			
			// local
			else{
				System.out.println("hhhhhhhheiii");
				capabilities.setCapability(CapabilityType.BROWSER_NAME,"Android");
				capabilities.setCapability("deviceName","emulator-5554");
				capabilities.setCapability("platformVersion","6.0");
				capabilities.setCapability("autoWebView", "true");
				capabilities.setCapability("platformName","Android");
				capabilities.setCapability("newCommandTimeout", 1120);
				capabilities.setCapability("appPackage","com.quicken.qm2014");
				capabilities.setCapability("appActivity","com.quicken.qm2014.MainActivity");
				capabilities.setCapability("chromedriverExecutable","/Users/kgrandhi/Documents/ChromeDriver/2.18/chromedriver");
				url = "http://127.0.0.1:4723/wd/hub";
			}
			
			serverURL = new URL(url);
			Globals globals = new Globals();
			
			if (getEngine().equals("android"))
				globals.driver = new AndroidDriver(serverURL, capabilities);	
			else if (getEngine().equals("ios"))
				globals.driver = new IOSDriver(serverURL, capabilities);
			else
				throw new Exception("Invalid Engine String: ["+getEngine()+"]");
						
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public DesiredCapabilities getCloudCapabilities(){
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		if (getEngine().equals("android")){
			
			capabilities.setCapability(CapabilityType.BROWSER_NAME,"Android");
			//capabilities.setCapability("appiumVersion", "1.6"); // 1.5.3
			capabilities.setCapability("appiumVersion", "1.7.1");
			capabilities.setCapability("automationName","appium");
			//capabilities.setCapability("deviceName","Android Emulator");
			//capabilities.setCapability("deviceName","Samsung Galaxy S8 Plus HD GoogleAPI Emulator");
			capabilities.setCapability("deviceName","Android GoogleAPI Emulator");
			
			capabilities.setCapability("deviceOrientation", "portrait");
			capabilities.setCapability("browserName", "");
			capabilities.setCapability("platformVersion","6.0");//5.1
			//capabilities.setCapability("platformVersion","7.0");
			capabilities.setCapability("platformName","Android");
			capabilities.setCapability("autoWebView", "true");
			capabilities.setCapability("maxDuration", 3250);
			capabilities.setCapability("newCommandTimeout", 1120);
			capabilities.setCapability("noResetValue", true);
			//capabilities.setCapability("chromedriverExecutable","/Users/kgrandhi/Documents/ChromeDriver/2.18/chromedriver");
			capabilities.setCapability("app", "sauce-storage:Quicken.apk");
			capabilities.setCapability("appPackage","com.quicken.qm2014");
			capabilities.setCapability("appActivity","com.quicken.qm2014.MainActivity");
		}
		else if(getEngine().equals("ios")){
			capabilities.setCapability("platformName", "iOS");
		    capabilities.setCapability("deviceName", "iPhone 6 Simulator");
		    //capabilities.setCapability("platformVersion", "12.0"); //9.3
		    capabilities.setCapability("platformVersion", "11.2");
		    capabilities.setCapability("browserName", "");
		    capabilities.setCapability("deviceOrientation", "portrait");
		    //capabilities.setCapability("appiumVersion", "1.9.1");// 1.5.3
		    capabilities.setCapability("appiumVersion", "1.8.0");// 1.5.3
		    capabilities.setCapability("autoWebView", "true");
		    capabilities.setCapability("autoAcceptAlerts", true);
	        capabilities.setCapability("autoGrantPermissions", true);
	        capabilities.setCapability("maxDuration", 3250);
			capabilities.setCapability("newCommandTimeout", 1120);
			capabilities.setCapability("noResetValue", true);
			capabilities.setCapability("app", "sauce-storage:Quicken.zip");
			capabilities.setCapability("appPackage","com.intuit.quickencompanion.ios");
			capabilities.setCapability("automationName","appium");
			
		}
		return capabilities;
		
	}
	
	public DesiredCapabilities getLocalCapabilities() throws Exception{
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		if (getEngine().equals("android")){
			
			//capabilities.setCapability(CapabilityType.BROWSER_NAME,"Android");
			//capabilities.setCapability("deviceName","emulator-5556");
			capabilities.setCapability("deviceName","emulator-5554");
			capabilities.setCapability("platformVersion","6.0");//6.0
			//capabilities.setCapability("platformVersion","7.0");
			capabilities.setCapability("noResetValue", true);
			capabilities.setCapability("autoGrantPermissions", true);
			capabilities.setCapability("autoWebView", "true");
			capabilities.setCapability("platformName","Android");
			capabilities.setCapability("newCommandTimeout", 1120);
			//capabilities.setCapability("appWaitActivity","com.android.settings.Settings$OverlaySettingsActivity, com.quicken.qm2014.MainActivity");
			//capabilities.setCapability("appWaitPackage","com.android.settings, com.quicken.qm2014");
			capabilities.setCapability("appActivity","com.quicken.qm2014.MainActivity");
			capabilities.setCapability("appPackage","com.quicken.qm2014");
			capabilities.setCapability("chromedriverExecutable","/Users/kgrandhi/Documents/ChromeDriver/2.18/chromedriver");
			
		}
		else if(getEngine().equals("ios")){
			
			// ios local capabilities needs to be updated
			//System.out.println("*****Local Capabilities for IOS are inCorrect********");
			/*if (true)
				throw new Exception("*****Local Capabilities for IOS are inCorrect********");*/
			
			/*capabilities.setCapability("platformName", "iOS");
		    //capabilities.setCapability("deviceName", "iPhone6s_9.3");
			capabilities.setCapability("deviceName", "iPhone 6");
		    capabilities.setCapability("platformVersion", "9.3");
		    capabilities.setCapability("noResetValue", true);
		    capabilities.setCapability("fullReset", false);
		    //
		    capabilities.setCapability("browserName", "");
		    //capabilities.setCapability("udid", "09252A1B-BEA7-4090-8B9E-8D0AB08E739D");
		    capabilities.setCapability("udid", "9A809FC5-BCEF-40A5-BE56-7369513227C8");
		    
		    //09252A1B-BEA7-4090-8B9E-8D0AB08E739D
		    capabilities.setCapability("deviceOrientation", "portrait");
		    capabilities.setCapability("appiumVersion", "1.6.4");
		    capabilities.setCapability("autoWebView", "true");
			capabilities.setCapability("newCommandTimeout", 1120);
			// capabilities.setCapability("app", "sauce-storage:Quicken.zip"); 
			// native app com.intuit.quickencompanion.ios
			//capabilities.setCapability("bundleId","com.intuit.quicken.ios");
			capabilities.setCapability("bundleId","com.intuit.quickencompanion.ios");
			capabilities.setCapability("automationName","XCUITest");
			//capabilities.setCapability("automationName","appium");
			capabilities.setCapability("app", "/Users/kgrandhi/Documents/Quicken_apk/QuickenRN_IOS.zip"); */
			
			System.out.println("IOS Capppppppppppppppppppppppppp");
			
			
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
			//capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.1"); //7.1//11.4
			capabilities.setCapability("platformVersion", "11.2");
			 capabilities.setCapability("appiumVersion", "1.8.0");
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone Simulator");
			capabilities.setCapability("bundleId","com.intuit.quickencompanion.ios");
	        capabilities.setCapability("automationName","XCUITest");
	        capabilities.setCapability("noReset", true);
	        capabilities.setCapability("autoWebView", "true");
	        capabilities.setCapability("autoAcceptAlerts", true);
	        capabilities.setCapability("autoGrantPermissions", true); //autoAcceptAlerts
	        capabilities.setCapability("browserName", "");
	        //capabilities.setCapability("newCommandTimeout",0);
	        capabilities.setCapability("app", "/Users/kgrandhi/Downloads/QuickenRN_IOS.zip");
	        
			
		}
		
		return capabilities;
		
	}
	
	public String getAppiumURL(){
		String USERNAME = "kalyan_grandhi";
		String ACCESS_KEY = "10fde941-0bec-4273-bca6-c7c827f36234";
		
		if (getHost().equals("cloud")){
			return "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";	
		}
		else
			return "http://127.0.0.1:4723/wd/hub";		
	}
	
	public DesiredCapabilities getCapabilities() throws Exception{
		
		if (getHost().equals("cloud"))
			return getCloudCapabilities();
		else
			return getLocalCapabilities();	
		
	}
	
	public void waitForRefresh (int millisec) throws InterruptedException{
		Thread.sleep(millisec);
		
	}
	
	public void quicken_scroll_mobile() throws InterruptedException {
		
		if (this.getEngine().equals("android")) {
			Dimension size = Engine.ad.manage().window().getSize();
			Engine.ad.swipe(size.width - 10, size.height * 4 / 8, size.width - 10, size.height / 7, 500);
			Thread.sleep(3000);	
			Engine.ad.swipe(size.width - 10, size.height * 4 / 8, size.width - 10, size.height / 7, 500);
		}
		else {
			
			Dimension size = Engine.iosd.manage().window().getSize();
			Engine.iosd.swipe(size.width - 10, size.height * 4 / 8, size.width - 10, size.height / 7, 500);
			Thread.sleep(3000);
			
			
		}
	}
	
	public void hideKeyBoard() throws Exception {
		
		if (this.getEngine().equals("android")) 
			Engine.ad.hideKeyboard();
		else
			Engine.iosd.hideKeyboard();
		
		Thread.sleep(1000);
		
	}
	
	public void getContext() throws Exception {
		
		if (this.getEngine().equals("android")) 
			Engine.ad.getContext();
		else
			Engine.iosd.getContext();
		
		Thread.sleep(1000);	
	}
	
	public Double processBalanceAmount(String sBalance) {
		
		String a[] = sBalance.split("");
		int i;
		String temp="";
		
		for(i=0; i<a.length; i++) {
			if (a[i].equals("$"))
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
		
		
	
	
	
	

}
