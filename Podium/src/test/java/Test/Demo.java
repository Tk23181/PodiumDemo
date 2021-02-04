package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.baseclass;
import base.extentReporting;

public class Demo extends baseclass
{

	@Test
	@Parameters("browser")
	public void demo1() throws Exception
	{
		extentReporting.startTestcase("demo1");
		getDriver().get(testdata.getProperty("AppURL"));
		Thread.sleep(5000);
		Actions action= new Actions(getDriver());
		action.moveToElement(getDriver().findElement(By.xpath(Obj_property.getProperty("pg_products")))).build().perform();
		extentReporting.executeReport(getDriver(), "click on product", "PASS");
		action.moveToElement(getDriver().findElement(By.xpath(Obj_property.getProperty("pg_reviews")))).click().perform();
		Thread.sleep(5000);
		
		action.moveToElement(getDriver().findElement(By.xpath(Obj_property.getProperty("pg_products")))).build().perform();
		extentReporting.executeReport(getDriver(), "click on feedback", "PASS");
		action.moveToElement(getDriver().findElement(By.xpath(Obj_property.getProperty("pg_feedback")))).click().perform();
		Thread.sleep(5000);
		
		
	}


}
