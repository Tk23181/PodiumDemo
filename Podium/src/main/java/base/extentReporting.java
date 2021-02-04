package base;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
//import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;

public class extentReporting extends genericMethod {
	private static ExtentReports extent;
	static ExtentTest exTest;

	// Initialize the test case
	/*
	 * public synchronized static ExtentTest startTestCase(String testName) throws
	 * Exception {
	 * 
	 * testcaseName=testName;
	 * 
	 * 
	 * htmlBuilder.setLength(0);
	 * Fn_addlog("************************************"+htmlBuilder.append("</br>"))
	 * ; StringBuilder
	 * testname=htmlBuilder.append("<b>").append(" Test case name :-- "+testcaseName
	 * .toUpperCase()).append("</b>").append("<br>");
	 * Fn_addlog(testname.toString());
	 * Fn_addlog("---------Started------------"+htmlBuilder.append("</br>"));
	 * 
	 * return genericMethod.createTest(testName); }
	 */

	/*
	 * //get the extent report instance public static ExtentReports getInstance() {
	 * if (extent == null) { createInstance(); } return extent; }
	 */

	// Create an extent report instance
	
	public static void startTestcase(String TCid)
	{
		if (extent == null){ initializeReport();}
		exTest=extent.createTest(TCid);
	}
	
	public static ExtentReports initializeReport() {
		String mytimeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String extrprtpath = System.getProperty("user.dir") + "\\test-output\\TestExecutionReports\\"
				+ "TestExecutionReport" + "_" + mytimeStamp + ".html";

		File testDirectory = new File(extrprtpath);
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(testDirectory);
		htmlReporter.config().setChartVisibilityOnOpen(false);
		htmlReporter.config().setDocumentTitle("Automation report_" + mytimeStamp);
		htmlReporter.config().setReportName("Halo Automation Exceution report");
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().getTheme();
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		return extent;
	}

	public static void executeReport(WebDriver Sdriver, String stepdescription, String Stepstatus) throws Exception {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		TakesScreenshot takescrn = (TakesScreenshot) Sdriver;
		File SrcFile = takescrn.getScreenshotAs(OutputType.FILE);
		// File DestFile=new File(System.getProperty("user.dir") +
		// "\\test-output\\TestExecutionReports\\screenshots\\"+ timeStamp + ".jpg");
		String descfile = "C:\\Automation\\Screenshot\\" + timeStamp + System.getProperty("user.name") + ".jpg";
		File DestFile = new File("C:\\Automation\\Screenshot\\" + timeStamp + System.getProperty("user.name") + ".jpg");

		Files.copy(SrcFile, DestFile);

		if (Stepstatus.equalsIgnoreCase("PASS")) {
			// generic_methods.getTest().log(Status.PASS, stepdescription,
			// MediaEntityBuilder.createScreenCaptureFromPath((System.getProperty("user.dir")
			// + "\\test-output\\TestExecutionReports\\screenshots\\" + timeStamp
			// +".jpg")).build());
			exTest.log(Status.PASS, stepdescription,
					MediaEntityBuilder.createScreenCaptureFromPath(descfile).build());

			/*
			 * htmlBuilder.setLength(0); StringBuilder
			 * testpass=htmlBuilder.append("<font color='green'>").append("Pass").append(
			 * "</font>").append(" - "+stepdescription).append("<br>");
			 * Fn_addlog(testpass.toString());
			 */
		}
		if (Stepstatus.equalsIgnoreCase("FAIL")) {
			// generic_methods.getTest().log(Status.FAIL, stepdescription,
			// MediaEntityBuilder.createScreenCaptureFromPath((System.getProperty("user.dir")
			// + "\\test-output\\TestExecutionReports\\screenshots\\" + timeStamp
			// +".jpg")).build());
			exTest.log(Status.FAIL, stepdescription,
					MediaEntityBuilder.createScreenCaptureFromPath(descfile).build());
			/*
			 * htmlBuilder.setLength(0); StringBuilder
			 * testpass=htmlBuilder.append("<font color='RED'>").append("Fail").append(
			 * "</font>").append(" - "+stepdescription).append("<br>");
			 * Fn_addlog(testpass.toString());
			 */
		}

		if (Stepstatus.equalsIgnoreCase("INFO")) {
			// generic_methods.getTest().log(Status.FAIL, stepdescription,
			// MediaEntityBuilder.createScreenCaptureFromPath((System.getProperty("user.dir")
			// + "\\test-output\\TestExecutionReports\\screenshots\\" + timeStamp
			// +".jpg")).build());
			exTest.log(Status.INFO, stepdescription,
					MediaEntityBuilder.createScreenCaptureFromPath(descfile).build());
			/*
			 * htmlBuilder.setLength(0); StringBuilder
			 * testpass=htmlBuilder.append("<font color='Grey'>").append("Info").append(
			 * "</font>").append(" - "+stepdescription).append("<br>");
			 * Fn_addlog(testpass.toString());
			 */
		}
	}

	public static void close() {

		try {
			extent.removeTest(exTest);
			extent.flush();
		} catch (Exception e) {

		}

	}
	
	
}
