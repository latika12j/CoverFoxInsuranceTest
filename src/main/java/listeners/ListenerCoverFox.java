package listeners;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import coverFoxBase.Base;
import coverFoxUtility.Utility;

public class ListenerCoverFox extends Base implements ITestListener
{
	@Override
	public void onTestFailure(ITestResult result)
	{
	try 
	{
	Utility.takeScreenShot(driver, result.getName());
	} 
	catch (IOException e) 
	{
	e.printStackTrace();
	}
	
	}
	@Override
	public void onTestSuccess(ITestResult result) 
	{
	}
	
	@Override
	public void onTestSkipped(ITestResult result) 
	{
	}
}
