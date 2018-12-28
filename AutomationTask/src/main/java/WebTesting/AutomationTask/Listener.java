package WebTesting.AutomationTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class Listener extends BasePage implements ITestListener
{

	public void onTestStart(ITestResult teststart) 
	{
	 Reporter.log("Test started Running:" + teststart.getMethod().getMethodName());
	 //log is static to call the static "Reporter" is class and then give the message, in testng file(emailable-report.html)
	}

	
	public void onTestSkipped(ITestResult testskipp)
	{
	 Reporter.log("Test is Skipped:" + testskipp.getMethod().getMethodName());	
	}
	
	
	public void onTestFailure(ITestResult testfailure) 
	{
	 Calendar calendar = Calendar.getInstance();//to get the system date we can use "Date" or "Calendar" class	
	 //getInstance is static method and called through class name
	 SimpleDateFormat formater =new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");//date format
	 String methodName = testfailure.getName();//current test name we get
	 if(!testfailure.isSuccess())//if not success
	 {
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);//take a screen shot
		try
		{
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()+"/src/main/java/WebTesting/AutomationTask/";
			//to get root directory of the project
			File destFile = new File((String)reportDirectory + "/Failure_ScreenShots/"+methodName + "_"+ formater.format(calendar.getTime()) + ".png");
			//destination path
			FileUtils.copyFile(srcFile, destFile);
			Reporter.log("<a href='"+ destFile.getAbsolutePath() + "'><img src='"+destFile.getAbsolutePath()+"' height='100' width='100'/></a>");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	 }
	}	

	
	
	public void onTestSuccess(ITestResult testsuccess)
	{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("mm_dd_yyyy_hh_mm_ss");
		String methodName = testsuccess.getName();
		
		Reporter.log("Test Success:"+testsuccess.getMethod().getMethodName());
		
		if(testsuccess.isSuccess())
		{
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try {
				String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()+"/src/main/java/WebTesting/AutomationTask/";
				File destFile = new File((String)reportDirectory + "/Success_ScreenShots/"+methodName + "_"+ formater.format(calendar.getTime()) + ".png");
				FileUtils.copyFile(srcFile, destFile);
				Reporter.log("<a href='"+ destFile.getAbsolutePath() + "'><img src='"+destFile.getAbsolutePath()+"' height='100' width='100'/></a>");
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult testfailbutwithinsuccesspercentage) 
	{
	// Reporter.log("Test is Failed But Within Success Percentage:" + testfailbutwithinsuccesspercentage.getMethod().getMethodName());	
	}

	
	public void onStart(ITestContext onstart) 
	{
	// Reporter.log("On Start:" + ((ITestResult) onstart).getMethod().getMethodName());
	}

	
	public void onFinish(ITestContext onfinish)
	{
	// Reporter.log("On Finish:" + ((ITestResult) onfinish).getMethod().getMethodName());	
	}

}


/*
Into the classes (Listener,Retry,RetryListener) we will be implemented some interfaces,interfaces having some abstract methods.

Listener : suppose the script is getting failed or passed or skipped what to do,script started execution what message we have 
           to display all this we need to give in listener class in implementing the "iTestListener" interface
           
Abstract method in iTestListener interface : onTestStart,onTestSuccess,onTestFailure,onTestSkipped,
                                             onTestFailedButWithinSuccessPercentage,onStart,onFinish

We need to configure the Listener and RetryListener classes in "xml" file under "listeners" tag. 
   they put one eye contact on current class execution
*/