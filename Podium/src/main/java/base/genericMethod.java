package base;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;


public class genericMethod extends baseclass {
	/**********************************************************************************************
	 * This method is used to check whether element is displayed/present or not?
	 * 
	 * @param Locator_Val     This refer to the xpath of the field
	 * @param stepdescription user can defined the step description using this
	 *                        variable
	 * @throws Exception
	 **********************************************************************************************/
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

	/*
	 * public static ExtentTest createTest(String testName) { // TODO Auto-generated
	 * method stub return createTest(testName); }
	 * 
	 * public static ExtentTest getTest() { // TODO Auto-generated method stub
	 * return null; }
	 */
		

}
