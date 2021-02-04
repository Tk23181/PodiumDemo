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

	public int generateRandomNumber()
	{	    
		Random objGenerator = new Random();
		int randomNumber = 100000 + objGenerator.nextInt(999999);
		return randomNumber;
	} 

	public void Fn_ElementExist_Byid(String Object_Property)
	{

		present = getDriver().findElements(By.id(Object_Property)).size() > 0;

	}

	public String CurrentDate() 
	{
		String currentDate = new SimpleDateFormat("MMddyy").format(new Date());
		return currentDate;

	} 

	public void Fn_NavigatetoWindow(String windowno)
	{
		Actions action = new Actions(getDriver());

		action.keyDown(Keys.LEFT_CONTROL).sendKeys("r").build().perform();

		getDriver().switchTo().frame(0);

		JavascriptExecutor js = (JavascriptExecutor)getDriver();

		js.executeScript("document.getElementById('cmdText').value=windowno;");

		js.executeScript("document.getElementById('runTaskButton').click();");
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
			//C5.NavigateToHomeScreen();
			
			//dispose();
		}
	}
}