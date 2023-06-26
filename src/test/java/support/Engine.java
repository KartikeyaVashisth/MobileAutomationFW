package support;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import warroom.kEngine;

public class Engine {
	
	private ThreadLocal<String> sessionId = new ThreadLocal<String>();
//	public static ThreadLocal<AppiumDriver<WebElement>> tlDriver = new ThreadLocal<AppiumDriver<WebElement>>();
	public static ThreadLocal <AppiumDriver> tlDriver = new ThreadLocal<AppiumDriver>();
	public static ThreadLocal<URL>serverURL = new ThreadLocal<URL>();
	
//	public static AppiumDriver<WebElement> getDriver() throws Exception {
	public static AppiumDriver getDriver() throws Exception {
		  
		  return tlDriver.get();

	 }
	
	public static void setDriver() throws Exception{
		
//		AppiumDriver<WebElement> driver;
		
		Helper h = new Helper();
		
		serverURL.set(new URL (h.getAppiumURL()));
		System.out.println(serverURL.get());
		if (Recovery.sEngine.get().equals("android")) {
//			driver = new AndroidDriver<WebElement>(serverURL.get(), h.getCapabilities());	
			AndroidDriver driver = new AndroidDriver(serverURL.get(), h.getCapabilities());
			Engine.tlDriver.set(driver);
			//String s="https://us1.appium.testobject.com/wd/hub";
			//driver = new AndroidDriver<WebElement>(new URL(s), h.getCapabilities());	
		}
		else
		{
			
//			driver = new IOSDriver<WebElement>(serverURL.get(), h.getCapabilities());
			
			IOSDriver driver = new IOSDriver(serverURL.get(), h.getCapabilities());
			Engine.tlDriver.set(driver);
//			Thread.sleep(5000);

		}
		
		
//		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
//		Engine.tlDriver.set(driver);
		
		
		
	}

}
