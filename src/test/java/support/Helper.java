package support;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

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
			System.out.println("************");
			System.out.println(key +"   :  "+globals.testProperty.get(key));
			System.out.println("************");
			
		}
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	
	}
		catch(Exception e){
			System.out.println("FAIL************");
			System.out.println(e.getMessage());
			System.out.println("FAIL************");
		}
		
		
		
}
	
	public String getHost(){
		
		Globals globals = new Globals();
		
		return globals.testProperty.get("host");
		
	}
	
	public String getUsername(){
		
		Globals globals = new Globals();
		
		return globals.testProperty.get("username");
		
	}
	
	public String getPassword(){
		
		Globals globals = new Globals();
		return globals.testProperty.get("password");
		
	}
	public String getEngine(){
		
		Globals globals = new Globals();
		
		return globals.testProperty.get("engine");
		
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
				capabilities.setCapability("appPackage","com.quicken.qm2014");
				capabilities.setCapability("appActivity","com.mint.core.overview.RouterActivity");
				String USERNAME = "kalyan_grandhi";
				String ACCESS_KEY = "10fde941-0bec-4273-bca6-c7c827f36234";
				url = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
	
			}
			
			// local
			else{
				capabilities.setCapability(CapabilityType.BROWSER_NAME,"Android");
				capabilities.setCapability("deviceName","emulator-5554");
				capabilities.setCapability("platformVersion","6.0");
				capabilities.setCapability("autoWebView", "true");
				capabilities.setCapability("platformName","Android");
				capabilities.setCapability("newCommandTimeout", 1120);
				capabilities.setCapability("appPackage","com.quicken.qm2014");
				capabilities.setCapability("appActivity","com.mint.core.overview.RouterActivity");
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
			capabilities.setCapability("appPackage","com.quicken.qm2014");
			capabilities.setCapability("appActivity","com.mint.core.overview.RouterActivity");
		}
		else if(getEngine().equals("ios")){
			capabilities.setCapability("platformName", "iOS");
		    capabilities.setCapability("deviceName", "iPhone 6 Simulator");
		    capabilities.setCapability("platformVersion", "9.3");
		    capabilities.setCapability("browserName", "");
		    capabilities.setCapability("deviceOrientation", "portrait");
		    capabilities.setCapability("appiumVersion", "1.5.3");
		    capabilities.setCapability("autoWebView", "true");
			capabilities.setCapability("newCommandTimeout", 1120);
			capabilities.setCapability("app", "sauce-storage:Quicken.zip");
			capabilities.setCapability("appPackage","com.intuit.quickencompanion.ios");
			capabilities.setCapability("automationName","appium");
			
		}
		return capabilities;
		
	}
	
	public DesiredCapabilities getLocalCapabilities() throws Exception{
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		if (getEngine().equals("android")){
			
			capabilities.setCapability(CapabilityType.BROWSER_NAME,"Android");
			capabilities.setCapability("deviceName","emulator-5554");
			capabilities.setCapability("platformVersion","6.0");
			capabilities.setCapability("autoWebView", "true");
			capabilities.setCapability("platformName","Android");
			capabilities.setCapability("newCommandTimeout", 1120);
			capabilities.setCapability("appPackage","com.quicken.qm2014");
			capabilities.setCapability("appActivity","com.quicken.qm2014.MainActivity");
			capabilities.setCapability("chromedriverExecutable","/Users/kgrandhi/Documents/ChromeDriver/2.18/chromedriver");
			
		}
		else if(getEngine().equals("ios")){
			
			// ios local capabilities needs to be updated
			System.out.println("*****Local Capabilities for IOS are inCorrect********");
			if (true)
				throw new Exception("*****Local Capabilities for IOS are inCorrect********");
			
			capabilities.setCapability("platformName", "iOS");
		    capabilities.setCapability("deviceName", "iPhone 6 Simulator");
		    capabilities.setCapability("platformVersion", "9.3");
		    capabilities.setCapability("browserName", "");
		    capabilities.setCapability("deviceOrientation", "portrait");
		    capabilities.setCapability("appiumVersion", "1.5.3");
		    capabilities.setCapability("autoWebView", "true");
			capabilities.setCapability("newCommandTimeout", 1120);
			capabilities.setCapability("app", "sauce-storage:Quicken.zip"); 
			capabilities.setCapability("appPackage","com.intuit.quickencompanion.ios");
			capabilities.setCapability("automationName","appium");
			
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
		
		
	
	
	
	

}
