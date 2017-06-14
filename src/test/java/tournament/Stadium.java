package tournament;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import referee.ErrorUtil;


/**
 * Unit test for simple App.
 */
public class Stadium 
{
	/*@Test
	public void Over1(){
		
		try {
			//System.out.println("pathhhhh");
			//System.out.println(this.getClass().getClassLoader().getResource("").getPath());
			//System.out.println("pathhhhh");
			//this.getClass().getClassLoader().getResource("").getPath()
			String testPropPath = this.getClass().getClassLoader().getResource("").getPath()+"/test.properties";
			System.out.println("pathhhhh");
			System.out.println(testPropPath);
			System.out.println("pathhhhh");
			
			
			File file = new File(testPropPath);
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();

			Enumeration enuKeys = properties.keys();
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				String value = properties.getProperty(key);
				System.out.println(key + ": " + value);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
	
	@Test
	public void Over2()  {
		
		try{
			
			Assert.assertEquals(true, false);
			
			
		}
		catch (Throwable t){
			System.out.println("ddddddddddddddddd");
			System.out.println(t.getMessage());
			ErrorUtil.addVerificationFailure(t);
		}
		System.out.println("ddddddddddddddddd");
		
		try{
			
			Assert.assertEquals(true, false);
			
			
		}
		catch (Throwable t){
			System.out.println("ddddddddddddddddd");
			System.out.println(t.getMessage());
			ErrorUtil.addVerificationFailure(t);
		}
		System.out.println("ddddddddddddddddd");
		
		
	}
	
	@Test
	public void Over3()  {
		
		try{
			
			Assert.assertEquals(true, true);
			System.out.println("nnnnnn");
			
			
		}
		catch (Throwable t){
			System.out.println("ddddddddddddddddd");
			System.out.println(t.getMessage());
			ErrorUtil.addVerificationFailure(t);
		}
		System.out.println("ddddddddddddddddd");
		
		
	}
	
}
