package base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Date;
import java.text.SimpleDateFormat;

public class appCommonMethods extends commonFunctions 

{
	public void Fn_IsDisplayed_By_Xpath(String Locator_Val, String stepdescription) throws Exception {
		Thread.sleep(1000);
		try {
			getDriver().findElement(By.xpath(Locator_Val)).isDisplayed();
			extentReporting.executeReport(getDriver(), stepdescription, "PASS");
		} catch (Exception e) {
			System.out.println("Expected Element Not displayed : " + stepdescription + e.getMessage());
			extentReporting.executeReport(getDriver(), stepdescription + "\n Expected Element Not displayed ",
					"FAIL");
			
		}
	}
}
