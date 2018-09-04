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
	
	//public static MobileDriver<MobileElement> mobilePlay;
	public static URL serverURL;
	public static AndroidDriver ad;
	public static MobileDriver me;
	public static IOSDriver iosd;
	
	public static void setDriver() throws Exception{
		
		Helper h = new Helper();
		
		serverURL = new URL(h.getAppiumURL());
		if (h.getEngine().equals("android"))
			//mobilePlay = new AndroidDriver<MobileElement>(serverURL, h.getCapabilities());
			ad = new AndroidDriver<MobileElement>(serverURL, h.getCapabilities());
		else
			iosd = new IOSDriver<MobileElement>(serverURL, h.getCapabilities());
				
		//mobilePlay.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		
			
	}
	
	public static MobileDriver getDriver() throws Exception{
		
		if (kEngine.ad==null && kEngine.id==null)
			setDriver();
		
		if (kEngine.ad != null)
			return kEngine.ad;
		else
			return kEngine.id;
		
		
		
		
		
	}

}
