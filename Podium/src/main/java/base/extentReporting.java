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
		htmlReporter.config().setReportName("Podium Automation Exceution report");
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
		String descfile = "C:\\Automation\\Screenshot\\" + timeStamp + System.getProperty("user.name") + ".jpg";
		File DestFile = new File("C:\\Automation\\Screenshot\\" + timeStamp + System.getProperty("user.name") + ".jpg");

		Files.copy(SrcFile, DestFile);

		if (Stepstatus.equalsIgnoreCase("PASS")) {
			exTest.log(Status.PASS, stepdescription,
					MediaEntityBuilder.createScreenCaptureFromPath(descfile).build());

			
		}
		if (Stepstatus.equalsIgnoreCase("FAIL")) {
			exTest.log(Status.FAIL, stepdescription,
					MediaEntityBuilder.createScreenCaptureFromPath(descfile).build());
			
		}

		if (Stepstatus.equalsIgnoreCase("INFO")) {
			exTest.log(Status.INFO, stepdescription,
					MediaEntityBuilder.createScreenCaptureFromPath(descfile).build());
			
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
