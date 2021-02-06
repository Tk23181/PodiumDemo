package base;

//import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.Select;

public class commonFunctions extends extentReporting
{


	public void Fn_ElementExist_Byid(String Object_Property)
	{

		present = getDriver().findElements(By.id(Object_Property)).size() > 0;

	}

	
	public void Fn_Checked_ID(String Locator_Val, String stepdescription ) throws Exception
	{	
		Thread.sleep(1000);
		try 
		{
			WebElement checkbox = getDriver().findElement(By.id(Locator_Val));
			if(!checkbox.isSelected())
				checkbox.click();
			extentReporting.executeReport(getDriver(),stepdescription, "PASS");
		}
		catch (Exception e)
		{
			System.out.println("Object Not available to Click : " + stepdescription + e.getMessage());
			extentReporting.executeReport(getDriver(),stepdescription, "FAIL");
			
			
		}
	}
}