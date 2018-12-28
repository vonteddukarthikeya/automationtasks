package WebTesting.AutomationTask;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BasePage extends CustomReports
{
	public static WebDriver driver;
	public static Propertiefiles pr=new Propertiefiles();

	public static void browserLaunch(String browser,String url) throws Exception
	{
		if(browser.equalsIgnoreCase("Chrome"))  //Chrome browser Launching
		{
			System.setProperty("webdriver.chrome.driver", pr.loaddata("ChromeDriverPath")); 
			driver=new ChromeDriver();	
		}	
		else if (browser.equalsIgnoreCase("Firefox"))  //Firefox browser Launching
		{
			System.setProperty("webdriver.gecko.driver", pr.loaddata("FireFoxDriverPath"));
			driver=new FirefoxDriver();
		}
		else if (browser.equalsIgnoreCase("IE"))  //IE browser Launching
		{
			System.setProperty("webdriver.ie.driver", pr.loaddata("IEDriverPath"));
			driver=new InternetExplorerDriver();
		}
		else if (browser.equalsIgnoreCase("Edge"))  //Edge browser Launching
		{
			System.setProperty("webdriver.edge.driver",pr.loaddata("EdgeDriverPath"));
			driver=new EdgeDriver();
		}

		driver.get(url); 
		driver.manage().window().maximize(); //Maximize the screen
		driver.manage().deleteAllCookies();  //To delete all cookies
		String log4jConfigPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfigPath);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); //Implicitly Wait
	}
	
//For Explicitly Wait common method
		public static void waitforElement(WebElement element, long time)
		{
			WebDriverWait explicitlyWait = new WebDriverWait(driver, time);
			explicitlyWait.until(ExpectedConditions.elementToBeClickable(element));
		}
}
