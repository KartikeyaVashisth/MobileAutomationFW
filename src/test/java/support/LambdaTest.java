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
			return "http://127.0.0.1:4723/wd/hub";	
	
	}
	

	public DesiredCapabilities getCloudCapabilities(){
		
		// LambdaTest cloud capabilities
		
		Helper h = new Helper();
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		String buildName = System.getenv("buildtag");
		if(buildName==null) {
			buildName = "LOCAL_"+Recovery.sEngine.get();
		}
		
		if (Recovery.sEngine.get().equals("android")){ 
		
			capabilities.setCapability("build", buildName);
			capabilities.setCapability("name", h.getTestName());
			capabilities.setCapability("deviceName", "Pixel.*");
			capabilities.setCapability("platformVersion","12");
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("isRealMobile", true);
			capabilities.setCapability("app", this.getLambdaTestAppUrl());
			capabilities.setCapability("deviceOrientation", "PORTRAIT");
			capabilities.setCapability("console", true);
			capabilities.setCapability("network", false);
			capabilities.setCapability("video", true);
			capabilities.setCapability("devicelog", true);
			capabilities.setCapability("newCommandTimeout", 1500);
			capabilities.setCapability("waitForIdleTimeout", 0);
			capabilities.setCapability("ignoreUnimportantViews", true);
			 
			
		}
		else if(Recovery.sEngine.get().equals("ios")) {
			
			capabilities.setCapability("build", buildName);
			capabilities.setCapability("name", h.getTestName());
			capabilities.setCapability("deviceName", "iPhone.*");
			capabilities.setCapability("platformVersion","15.0");
			capabilities.setCapability("platformName", "iOS");
			capabilities.setCapability("isRealMobile", true);
			capabilities.setCapability("app", this.getLambdaTestAppUrl());
			capabilities.setCapability("deviceOrientation", "PORTRAIT");
			capabilities.setCapability("console", true);
			capabilities.setCapability("network", false);
			capabilities.setCapability("video", true);
			capabilities.setCapability("devicelog", true);
			capabilities.setCapability("newCommandTimeout", 1500);
			capabilities.setCapability("waitForIdleTimeout", 0);
			capabilities.setCapability("autoAcceptAlerts", true);
			 
			
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
				app_url = "lt://APP10160551841667721056744760";
			}
			else {
				app_url = "lt://APP10160532421667661979627588";
			}
			
		}
		
	
		return app_url;
	}
	}
