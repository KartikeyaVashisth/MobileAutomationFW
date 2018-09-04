package dugout;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import support.Engine;
import support.Helper;

public class BankingAndCreditCardPage {
	
	public BankingAndCreditCardPage () {
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
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Back']")
	@AndroidFindBy(xpath="//*[@class='android.widget.ImageButton']")
	public MobileElement backButton;
	
	@iOSFindBy(xpath="//*[starts-with(@name, 'Bank Account Balances Checking Accounts')]")
	@AndroidFindBy(xpath="//*[@text='Checking']")
	public MobileElement checkingAccount;
	
	// coded for android. IOS behavior not clearly known
	public MobileElement getAccount(String acctName) throws Exception{
		
		String xPathForAcct = "//android.widget.ScrollView//android.view.ViewGroup//android.widget.TextView[normalize-space(@text)='"+acctName+"']";
		
		try{
			return (MobileElement) Engine.ad.findElementByXPath(xPathForAcct);
		}
		catch(Exception E){
			return null;
		}
		
	}
	
	/*public String getAccountBalance(String acctName){
		
		if (getAccount(acctName)==null){
			System.out.println(acctName+" does not exist");
			return null;
		}
		
	}*/

}
