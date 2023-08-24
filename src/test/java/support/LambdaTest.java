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
import org.openqa.selenium.Dimension;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;
import org.json.JSONObject;
import com.saucelabs.saucerest.SauceREST;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class LambdaTest {
	
	private static String USERNAME = "kallol.das";
	private static String ACCESS_KEY = "5duuN311efP6Nhuhj9kJYn8JfACxznRdlEnFSxW8ju9YgR1hlr";
	
	/**
	 * Gets the appiumURL set for LambdaTest connection.
	 * @return
	 */
	
	public String getAppiumURL(){
		String USERNAME = "kallol.das";
		String ACCESS_KEY = "5duuN311efP6Nhuhj9kJYn8JfACxznRdlEnFSxW8ju9YgR1hlr";
		
		if (Recovery.sHost.get().equals("cloud")){
			return "https://" + USERNAME + ":" + ACCESS_KEY + "@mobile-hub.lambdatest.com/wd/hub";	
		}
		else
//			return "http://127.0.0.1:4723/wd/hub";
			return "http://127.0.0.1:4723";
		
	
	}
	

	public MutableCapabilities getCloudCapabilities(){
		
		// LambdaTest cloud capabilities
		
		Helper h = new Helper();
			
//		DesiredCapabilities capabilities = new DesiredCapabilities();
		MutableCapabilities capabilities = new MutableCapabilities();
		String buildName = System.getProperty("buildtag");
		if(buildName==null) {
			buildName = "LOCAL_"+Recovery.sEngine.get();
		}
		
		if (Recovery.sEngine.get().equals("android")){ 
		
			capabilities.setCapability("appium:build", buildName);
			capabilities.setCapability("appium:name", h.getTestName());
			capabilities.setCapability("appium:deviceName", "Pixel 6");// Pixel.*
			capabilities.setCapability("appium:automationName", "UiAutomator2");
			capabilities.setCapability("appium:platformVersion","13");
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("appium:appiumVersion", "2.0");
			capabilities.setCapability("appium:isRealMobile", true);
			capabilities.setCapability("appium:app", this.getLambdaTestAppUrl());
			capabilities.setCapability("appium:deviceOrientation", "PORTRAIT");
			capabilities.setCapability("appium:console", true);
			capabilities.setCapability("appium:network", false);
			capabilities.setCapability("appium:video", true);
			capabilities.setCapability("appium:devicelog", true);
			capabilities.setCapability("appium:newCommandTimeout", 1500);
			capabilities.setCapability("appium:waitForIdleTimeout", 0);
			capabilities.setCapability("appium:ignoreUnimportantViews", true);
			 
			
		}
		else if(Recovery.sEngine.get().equals("ios")) {
			
			capabilities.setCapability("appium:build", buildName);
			capabilities.setCapability("appium:name", h.getTestName());
			capabilities.setCapability("appium:deviceName", "iPhone.*");
			capabilities.setCapability("appium:automationName", "XCUITest");
			capabilities.setCapability("appium:platformVersion","16");
			capabilities.setCapability("platformName", "iOS");
			capabilities.setCapability("appiumVersion", "2.0");
			capabilities.setCapability("appium:isRealMobile", true);
			capabilities.setCapability("appium:app", this.getLambdaTestAppUrl());
			capabilities.setCapability("appium:deviceOrientation", "PORTRAIT");
			capabilities.setCapability("appium:console", true);
			capabilities.setCapability("appium:network", false);
			capabilities.setCapability("appium:video", true);
			capabilities.setCapability("appium:devicelog", true);
			capabilities.setCapability("appium:newCommandTimeout", 1500);
			capabilities.setCapability("appium:waitForIdleTimeout", 0);
			capabilities.setCapability("appium:autoAcceptAlerts", true);
			 
			
		}
		return capabilities;

	}
	/**
	 * Retrieves the appUrl from the 'lambdatest_response' set in pom.xml for cloud builds.
	 * or, Retrieves the appURL hard-coded in function for local automation runs.
	 * @return
	 */
	
	private String getLambdaTestAppUrl() {
		Helper h = new Helper();
		String app_url = System.getProperty("lambdatest_response");
		if(app_url != null) {
			
			JSONObject lambdaTestResponseData = new JSONObject(app_url);
			app_url = lambdaTestResponseData.getString("app_url");		// this is set in Jenkins
			
		}else {
			if(h.getEngine().equals("ios")) {
				app_url = "lt://APP10160631101688108356272199";
			}
			else {
				app_url = "lt://APP1016039251686124385288360";
			}
			
		}
		
	
		return app_url;
	}
	}
