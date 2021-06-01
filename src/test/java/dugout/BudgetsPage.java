package dugout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import support.Engine;
import support.Helper;

public class BudgetsPage {
	
	public BudgetsPage () {
		try {
			
			PageFactory.initElements(new AppiumFieldDecorator(Engine.getDriver()),this);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	
	@iOSFindBy(xpath="//*[starts-with(@name, 'PERSONAL EXPENSES')]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='PERSONAL EXPENSES']")
	public MobileElement personalExpenses;
	
	public boolean verify_budgetHeader() throws Exception {
		
		DateFormat date =  new SimpleDateFormat("MMMM");
		Date date1 = new Date();
		String sCard = date.format(date1).toString();
		sCard = sCard +" "+"Budget";
		
		Helper h = new Helper();
		if (h.getEngine().equals("android")){
			String sXpath="//android.widget.TextView[@text='"+sCard+"']";
			return Engine.getDriver().findElement(By.xpath(sXpath)).isDisplayed();
		
		}
		else {
			return Engine.getDriver().findElement(By.name(sCard)).isDisplayed();
		}
		
	}

}
