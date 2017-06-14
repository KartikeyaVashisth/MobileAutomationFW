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

public class Engine {
	
	public static MobileDriver<MobileElement> mobilePlay;
	public static URL serverURL;
	
	public static void setDriver() throws Exception{
		
		Helper h = new Helper();
		
		serverURL = new URL(h.getAppiumURL());
		if (h.getEngine().equals("android"))
			mobilePlay = new AndroidDriver<MobileElement>(serverURL, h.getCapabilities());
		else
			mobilePlay = new IOSDriver<MobileElement>(serverURL, h.getCapabilities());
				
		mobilePlay.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
			
	}
	
	public static MobileDriver<MobileElement> getDriver() throws Exception{
		
		if (mobilePlay==null)
			setDriver();
		
		return mobilePlay;
		
	}

}
