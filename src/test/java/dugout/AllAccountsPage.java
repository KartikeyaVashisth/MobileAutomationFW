package dugout;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import support.Engine;
import support.Helper;

public class AllAccountsPage {
	
	public AllAccountsPage () {
		try {
			Helper h = new Helper();
			if (h.getEngine().equals("android"))
				PageFactory.initElements(new AppiumFieldDecorator(Engine.ad),this);
			else
				PageFactory.initElements(new AppiumFieldDecorator(Engine.iosd),this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@iOSFindBy(xpath="//*[contains(@name,'All Accounts')]")
	@AndroidFindBy(xpath="//*[@text='All Accounts']")
	public MobileElement textAllAccounts;
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Sign Out']")
	@AndroidFindBy(xpath="//*[@class='android.widget.ImageButton']")
	public MobileElement backButton;
	
	public void navigateBackToDashboard() throws Exception {
		
		backButton.click();
		Thread.sleep(3000);
	}

}
