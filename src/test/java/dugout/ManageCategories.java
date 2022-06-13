package dugout;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import support.Engine;

public class ManageCategories {
	
	public ManageCategories () {
		try {
			
			PageFactory.initElements(new AppiumFieldDecorator(Engine.getDriver()),this);
		} catch (Exception e) {
			
			e.printStackTrace();
		}	
	}
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`name=='search category'`]")
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Search Category']")
	public MobileElement searchCategoryTextField;

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Cash & ATM'`]")
	@AndroidFindBy(xpath="//*[@text='Cash & ATM']")
	public MobileElement cashAtmParentCat;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Parentcat'`]")
	@AndroidFindBy(xpath="//*[@text='Parentcat']")
	public MobileElement parentcatCategory;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name='Firstlevelcat'`]")
	@AndroidFindBy(xpath="//*[@text='Firstlevelcat']")
	public MobileElement firstLevelCatCategory;
	
	
	
}
