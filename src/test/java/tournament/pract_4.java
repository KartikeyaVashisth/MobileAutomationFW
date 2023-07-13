package tournament;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class pract_4 {
	
	@Test
	public void web1() throws MalformedURLException, InterruptedException{
		
		System.out.println("commented first testcase.....");
		
		/*DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName","emulator-5556");
		capabilities.setCapability("platformVersion","7.0");
		capabilities.setCapability("autoWebView", "true");
		capabilities.setCapability("platformName","Android");
	    capabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
	    capabilities.setCapability(MobileCapabilityType.TAKES_SCREENSHOT, "true");
	    capabilities.setCapability("chromedriverExecutable","/Users/kgrandhi/Documents/ChromeDriver/2.18/chromedriver");
	    AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.get("http://www.mailinator.com");
	    Thread.sleep(2000);
	    driver.quit();*/
	    
	    /*DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName","emulator-5556");
		capabilities.setCapability("platformVersion","7.0");
		capabilities.setCapability("autoWebView", "true");
		capabilities.setCapability("platformName","Android");
	    capabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
	    capabilities.setCapability(MobileCapabilityType.TAKES_SCREENSHOT, "true");
	    capabilities.setCapability("chromedriverExecutable","/Users/kgrandhi/Documents/ChromeDriver/2.18/chromedriver");
	    AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.get("http://www.mailinator.com");
	    Thread.sleep(2000);
	    //driver.quit();
	    
	    MFApage m = new MFApage();
	    m.inputMFA.sendKeys("123456");
	    m.btnSubmit.click();*/
	}
	
//****** This piece of code we can enable when we want to use the Testrail integration with our project . Based on test case status the status on Testrail will get update for each testcase ******	
//	@Override
//	@BeforeTest
//	@Parameters({"host","engine","test","env","RUN_ID"})
//	public void beforeTestEnter(@Optional("readFromPropertiesFile")String host, @Optional("readFromPropertiesFile")String engine, @Optional("readFromPropertiesFile")String testName, @Optional("readFromPropertiesFile")String env, @Optional("readFromPropertiesFile")String RUN_ID) throws Exception{
//		this.testRunId.set("2330");
//		super.beforeTestEnter(host, engine, testName, env, RUN_ID);
//		
//	}
	
	
	@Test
	public void sample() throws Exception{
		
		
		String methodName = "  tournament.pract_1.verifyAccount() throws java.lang.Exception";
		
		methodName = "  tournament.pract_1.verifyAccount() throws java.lang.Exception";
		methodName=methodName.substring(methodName.lastIndexOf(".") + 1).trim();
		System.out.println(methodName);
	}

}
