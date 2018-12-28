package WebTesting.AutomationTask;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC001_VerifyAddUserDetails extends BasePage
{
 public static final Logger log=Logger.getLogger(TC001_VerifyAddUserDetails.class.getName());
 AbsaTestPage pomclass;
 public static Propertiefiles pr=new Propertiefiles();

 @BeforeMethod
 @Parameters("browser")
 public void OpenBrowser(String browser) throws Exception 
 {
	browserLaunch(browser, pr.loaddata("BrowserUrl"));
 }
 
 @Test
  public void AbsaAssessmentTest() throws Exception 
  {
	  log.info("Started ----- TC001_VerifyAddUserDetails -----");
	  pomclass=new AbsaTestPage(driver);//created object of the page and create constructor
	  pomclass.validateTitle();
	  pomclass.validateUrl();
	  pomclass.validateUserListTable();
	  pomclass.addUserClick();
	  pomclass.addingNewUser();
	  pomclass.checkingUserInList();
    //  driver.quit();
	  log.info("Ended ----- TC001_VerifyAddUserDetails ----- ");
  }
  
  @AfterMethod
  public void CloseBrowser() 
  {
	  driver.quit(); //Used to quit from the browser
  }

}
