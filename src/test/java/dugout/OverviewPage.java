package dugout;


import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;

public class OverviewPage {
	public OverviewPage (MobileDriver<MobileElement> driver){
		PageFactory.initElements(driver,this);	
	}
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Overview']")
	@AndroidFindBy(xpath="//*[@text='Overview']")
	MobileElement overView;
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Sign Out']")
	@AndroidFindBy(xpath="//*[@text='Sign Out']")
	MobileElement signOut;
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Updates']")
	MobileElement updates;
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Settings']")
	@AndroidFindBy(xpath="//*[@content-desc='More options']")
	MobileElement SettingsOrMoreOptions;

}
