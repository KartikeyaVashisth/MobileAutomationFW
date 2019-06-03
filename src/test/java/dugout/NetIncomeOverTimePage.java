package dugout;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import referee.Verify;
import support.Engine;
import support.Helper;

public class NetIncomeOverTimePage {
	
	public NetIncomeOverTimePage () {
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
	
	
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name=\"Net Income Over Time\"]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Net Income Over Time']")
	public MobileElement netIncomeOverTimeHeader;
	
	@iOSFindBy(xpath="//*[normalize-space(@name)='Back']")
	@AndroidFindBy(xpath="//*[@class='android.widget.ImageButton']")
	public MobileElement backButtonOnHeader;
	
	@iOSFindBy(xpath="//XCUIElementTypeOther")
	@AndroidFindBy(xpath="//*[@resource-id='com.quicken.qm2014:id/listView']")
	public MobileElement transactionList;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[starts-with(@name,'Net Income: ')]")
	@AndroidFindBy(xpath="//android.widget.TextView[starts-with(@text,'Net Income: ')]")
	public MobileElement netIncomeCurrentMonth;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[starts-with(@name,'Net Income: ')]/../XCUIElementTypeStaticText[contains (@name,'$')]")
	@AndroidFindBy(xpath="//android.widget.TextView[starts-with(@text,'Net Income: ')]/../android.widget.TextView[contains(@text,'$')]")
	public MobileElement netIncomeForSelectedMonth;
	
	@iOSFindBy(xpath="//XCUIElementTypeCell/XCUIElementTypeStaticText[@name='bottomRightLabel']")
	@AndroidFindBy(xpath="//*[@resource-id='com.quicken.qm2014:id/list_row_date']")
	public MobileElement firstTransactionDate;
	
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name=\"You don't have any transactions.\"]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"You don't have any transactions.\"]")
	public MobileElement youDontHaveAnyTxns;
	
	public void navigateBackToDashboard() throws Exception{
		backButtonOnHeader.click();
		Thread.sleep(5000);
		
	}
	
	public String getSelectedMonth() throws Exception {
		
		String sMonth = this.netIncomeCurrentMonth.getText();
		
		return sMonth.substring(sMonth.indexOf(":")+2, sMonth.length());
	}
	
	public Double getSelectedMonthAmount() throws Exception {
		
		String sAmount = this.netIncomeForSelectedMonth.getText();
		
		Helper h = new Helper();
		return h.processBalanceAmount(sAmount);
		
	}
	
	public void tapOnTheMonth (String sMonth) throws Exception{
		
		Helper h = new Helper();
		
		if (h.getEngine().equals("android"))
			tapOnTheMonth_Android(sMonth);
		else
			tapOnTheMonth_IOS(sMonth);
		
		Thread.sleep(2000);
			
	}
	
	protected void tapOnTheMonth_IOS(String sMonth) throws Exception{
		
		sMonth = sMonth.toUpperCase();
		
		String sXpath;
		
		//sXpath ="//XCUIElementTypeOther[@name='MAR']/XCUIElementTypeOther[@name='MAR']";
		sXpath ="//XCUIElementTypeOther[@name='"+sMonth+"']/XCUIElementTypeOther[@name='"+sMonth+"']";
		
		Engine.iosd.findElement(By.xpath(sXpath)).click();
		
		
	}
	
	protected void tapOnTheMonth_Android(String sMonth) throws Exception{
		
		String sMonthUC = sMonth.toUpperCase();
		
		String sXpath;
		Helper h = new Helper();

		sXpath ="//android.widget.TextView[@text='"+sMonthUC+"']/../android.view.ViewGroup";
		
		List <MobileElement> le = Engine.ad.findElements(By.xpath(sXpath));
		
		Integer iCount;
		
		for (iCount=0; iCount < le.size(); iCount++) {
			h.getContext();
			le.get(iCount).click();
			h.getContext();
			Thread.sleep(1000);

			if (this.getSelectedMonth().equals(sMonth))
				return;	
		}
		
		throw new Exception(sMonth+" Month did not appear on NetIncomeOverTime Screen");
					
	}
	
	public String getMonthStringFromFirstTxn() throws Exception{
		
		Helper h = new Helper();
		
		if (Verify.objExists(this.youDontHaveAnyTxns))
			return "youDontHaveAnyTxns";
		
		String s = this.firstTransactionDate.getText();
		
		if (s.equals("Yesterday") || s.equals("Today") || s.equals("Tomorrow"))
			return h.getLastSixMonths()[0];
		
		return s.substring(0, 3);
	}
	
	protected Boolean verifyMonth_IOS(String sMonth) throws Exception {
		
		String sXpath ="//XCUIElementTypeOther[@name='"+sMonth.toUpperCase()+"']/XCUIElementTypeOther[@name='"+sMonth.toUpperCase()+"']";
		
		return Verify.objExists((MobileElement) Engine.iosd.findElement(By.xpath(sXpath)));
		
		
	}
	
	protected Boolean verifyMonth_Android(String sMonth) throws Exception {
		
		String sXpath ="//android.widget.TextView[@text='"+sMonth.toUpperCase()+"']";
		
		return Verify.objExists((MobileElement) Engine.ad.findElement(By.xpath(sXpath)));
		
		
	}
	public Boolean verifyMonth (String sMonth) throws Exception {
		
		Helper h = new Helper();
		
		if (h.getEngine().equals("android")) 
			
			return verifyMonth_Android(sMonth);	
		
		else
			return verifyMonth_IOS(sMonth);
		
	}

}
