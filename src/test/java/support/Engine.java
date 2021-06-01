package support;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import warroom.kEngine;

public class Engine {
	
	private ThreadLocal<String> sessionId = new ThreadLocal<String>();
	public static ThreadLocal<AppiumDriver<MobileElement>> tlDriver = new ThreadLocal<AppiumDriver<MobileElement>>();
	public static ThreadLocal<URL>serverURL = new ThreadLocal<URL>();
	
	public static AppiumDriver<MobileElement> getDriver() throws Exception {
		  
		  return tlDriver.get();

	 }
	
	public static void setDriver() throws Exception{
		
		AppiumDriver<MobileElement> driver;
		
		Helper h = new Helper();
		
		serverURL.set(new URL (h.getAppiumURL()));
		System.out.println(serverURL.get());
		if (Recovery.sEngine.get().equals("android")) {
			driver = new AndroidDriver<MobileElement>(serverURL.get(), h.getCapabilities());	
			//String s="https://us1.appium.testobject.com/wd/hub";
			//driver = new AndroidDriver<MobileElement>(new URL(s), h.getCapabilities());	
		}
		else
		{
			
			driver = new IOSDriver<MobileElement>(serverURL.get(), h.getCapabilities());
			

		}
		
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		Engine.tlDriver.set(driver);
		
		
		
	}

}
