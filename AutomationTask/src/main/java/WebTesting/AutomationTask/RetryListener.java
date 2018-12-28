package WebTesting.AutomationTask;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

import com.test.automation.UIAutomation.CustomListener.Retry;

public class RetryListener implements IAnnotationTransformer
{

	public void transform(ITestAnnotation arg0, Class arg1, Constructor arg2, Method arg3) 
	{
		IRetryAnalyzer retry = arg0.getRetryAnalyzer();//we get information null or notnull
		if(retry == null)
		{
			arg0.setRetryAnalyzer(Retry.class);//retry logic is written in "Retry.class"
		}			
	}
}


/*
on test success we take screenshots and when test gets failed at that time we need to retry it for morethan onetime
   (failure reason : network bandwidth,database servers down,application servers down,locator might be changed)

retryListener class we use "IAnnotationTransformer" interface and abstract method is "transform"
*/
