package base;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import com.aventstack.extentreports.reporter.configuration.Theme;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class baseclass 
{
	private WebDriver driver;
	public static Properties Obj_property;
	public static Properties rumtimedata;
	public static Properties props;
	public static Properties testdata;
	public static Map<String, List<String>> datalistmap;
	public boolean present;
	public static WebDriver remotedriver;
	public static int Counter=1;

	@BeforeSuite
	public void initialization() throws IOException
	{
		LoadObjRepository();
		Loadtestdata();
		extentReporting.initializeReport();
	}

	public void LoadObjRepository() throws IOException
	{		
		FileInputStream objfile = new FileInputStream(new File(System.getProperty("user.dir") + "\\src\\main\\java\\utilities\\objectRepository.properties"));
		Obj_property = new Properties();
		Obj_property.load(objfile);		
	}

	public void Loadtestdata() throws IOException
	{		
		FileInputStream testdatafile = new FileInputStream(new File( System.getProperty("user.dir")+ "\\src\\main\\java\\utilities\\testdata.properties"));
		testdata = new Properties();
		testdata.load(testdatafile);
		
			
	}

	public   WebDriver getDriver() {
		if(driver==null) {
			return remotedriver;

		}else {
			return driver;
		}
	}

	public  void setDriver(WebDriver driver) {
		remotedriver=driver;
		this.driver = driver;
	}
	@BeforeMethod
	@Parameters("browser")
	public void Fn_LaunchBrowser(@Optional String browser) throws Exception
	{
		switch(testdata.getProperty("Execution_Platform"))
		{
			case "TestNG":
				//System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "\\Drivers\\chromedriver2.exe");
				//setDriver(new ChromeDriver());				
				
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.setPageLoadStrategy(PageLoadStrategy.NONE);			
                HashMap<String, Object> chromeLocalStatePrefs = new HashMap<String, Object>();
                List<String> experimentalFlags = new ArrayList<String>();
                experimentalFlags.add("same-site-by-default-cookies@2");
                experimentalFlags.add("cookies-without-same-site-must-be-secure@2");
                chromeLocalStatePrefs.put("browser.enabled_labs_experiments", experimentalFlags);
                options.setExperimentalOption("localState", chromeLocalStatePrefs);
                //driver = new ChromeDriver(options);
                setDriver(new ChromeDriver(options));
               
				break;

			case "Selenium_Grid":
				DesiredCapabilities caps = new DesiredCapabilities();
				if(browser.equalsIgnoreCase("CHROME")) {
					System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "\\Drivers\\chromedriver2.exe");
					caps = DesiredCapabilities.chrome();
				}
				else if(browser.equalsIgnoreCase("Internet Explorer")) 
				{
					System.setProperty("webdriver.ie.driver",System.getProperty("user.dir") + "\\Drivers\\IEDriverServer.exe");
					caps = DesiredCapabilities.internetExplorer();			
				}
				else if(browser.equalsIgnoreCase("Firefox")) 
				{
					System.setProperty("webdriver.ie.driver",System.getProperty("user.dir") + "\\Drivers\\geckodriver.exe");
					caps = DesiredCapabilities.firefox(); 
				}
				
			case "Browser_Stack":
				// need to implement in multiple browser
				String USERNAME="tusharkulkarni5";
				String ACCESSKEY="3Cypy7xaqDtq9nayWhyk";
				String URL="https://"+USERNAME+":"+ACCESSKEY+"@hub.browserstack.com/wd/hub";
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("browserName", "Safari");
				capabilities.setCapability("browserVersion", "12.0");
				HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
				browserstackOptions.put("os", "OS X");
				browserstackOptions.put("osVersion", "Mojave");
				browserstackOptions.put("local", "false");
				browserstackOptions.put("seleniumVersion", "3.14.0");
				capabilities.setCapability("bstack:options", browserstackOptions);
				URL browserurl=new URL(URL);
				setDriver(new RemoteWebDriver(browserurl, capabilities));
				break;
		}

		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();			
		getDriver().manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);		
		//System.out.println(getDriver().manage().getCookieNamed("same-site-by-default-cookies"));
		
	}
@AfterMethod public void Fn_CloseBrowser()
{
	extentReporting.close();
	getDriver().quit();
}
	/*
	 * @AfterMethod public void Fn_CloseBrowser() throws Exception {
	 * htmlBuilder.setLength(0);
	 * Fn_addlog("---------End--------"+htmlBuilder.append("</br>"));
	 * htmlBuilder.setLength(0);
	 * Fn_addlog("************************************"+htmlBuilder.append("</br>")+
	 * ""+htmlBuilder.append("</br>"));
	 * 
	 * if (datamap!=null) { datamap.clear(); getDriver().quit(); } else {
	 * getDriver().quit(); } }
	 */
	/*
	 * public static void Fn_addlog(String desc) throws Exception { try {
	 * Html=htmlBuilder.toString(); layoutHandler.setFormatter(formatter);
	 * Add_Log.info(desc); htmlBuilder.setLength(0);
	 * 
	 * }catch(Exception e) {
	 * 
	 * } }
	 */
	
	
}
