package listener;

import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import base.baseclass;
import base.extentReporting;

public class testListener 
{
	//private static final ExtentReports EXTENT = extentReporting.getInstance();
	@SuppressWarnings("unused")
	private static ThreadLocal<ExtentTest> test =new ThreadLocal<ExtentTest>();

	public synchronized void onStart(ITestContext context) 
	{
		//System.out.println(""New Test Started" +result.getName()");	
	}

	public synchronized void onFinish(ITestContext context) 
	{
		extentReporting.close();		
		//EXTENT.flush();
	}
	public synchronized void onTestSuccess(ITestResult result) 
	{ 

		try {
			//baseclass.Fn_addlog(result.getMethod().getMethodName() + " Passed!"+baseclass.htmlBuilder.append("</br>"));
			//baseclass.htmlBuilder.setLength(0);
			extentReporting.executeReport(baseclass.remotedriver, result.getMethod().getMethodName() + " Passed!", "PASS");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void onTestFailure(ITestResult result) 
	{
		//System.out.println((result.getMethod().getMethodName() + " failed!"));
		// test.get().fail(result.getThrowable());
		try
		{
			//baseclass.Fn_addlog(result.getMethod().getMethodName() + " failed!"+baseclass.htmlBuilder.append("</br>"));
			//baseclass.htmlBuilder.setLength(0);
			extentReporting.executeReport(baseclass.remotedriver, result.getMethod().getMethodName() + " failed!", "FAIL");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
