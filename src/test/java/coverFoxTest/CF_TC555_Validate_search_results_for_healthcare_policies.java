package coverFoxTest;

import java.io.IOException;
import java.time.Duration;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import coverFoxBase.Base;
import coverFoxPOM.CoverFoxAddressDetailsPage;
import coverFoxPOM.CoverFoxHealthPlanPage;
import coverFoxPOM.CoverFoxHealthPlanResultsPage;
import coverFoxPOM.CoverFoxHomePage;
import coverFoxPOM.CoverFoxMemberDetailsPage;
import coverFoxUtility.Utility;

public class CF_TC555_Validate_search_results_for_healthcare_policies extends Base
{
	public static Logger logger;
	CoverFoxHomePage home;
	CoverFoxHealthPlanPage healthPlan;
	CoverFoxAddressDetailsPage addressDetails;
	CoverFoxMemberDetailsPage memberDetails;
	CoverFoxHealthPlanResultsPage result;
	
	
	@BeforeClass
	public void launchBrowser() throws InterruptedException
	{
		logger=Logger.getLogger("CoverFoxInsurance");
		PropertyConfigurator.configure("log4j.properties");
		launchCoverFox();
		home=new CoverFoxHomePage(driver);
		healthPlan= new CoverFoxHealthPlanPage(driver);
		addressDetails= new CoverFoxAddressDetailsPage(driver);
		memberDetails= new CoverFoxMemberDetailsPage(driver);
		result= new CoverFoxHealthPlanResultsPage(driver);
		
		Reporter.log("Opening browser..", true);
		driver.get("https://www.coverfox.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	
	@BeforeMethod
	public void enterMemberDetails() throws InterruptedException, EncryptedDocumentException, IOException
	{
	Reporter.log("Clicking on gender button ", true);
	home.clickOnFemaleButton();
	logger.info("Clicking on Female Button");
	Thread.sleep(1000);
	Reporter.log("Clicking on next button ", true);
	healthPlan.clickOnNextButton();
	logger.info("Clicking on Next Button");
	Thread.sleep(1000);
	Reporter.log("Handling age drop down ", true);
	memberDetails.handleAgeDropDown(Utility.readDataFromExcel(1, 0));
	Reporter.log("Clicking on next button ", true);
	logger.info("Clicking on Next Button");
	memberDetails.clickOnNextButton();
	Thread.sleep(1000);
	Reporter.log("Entering pin code ",true);
	logger.info("Entering Pincode");
	addressDetails.enterPinCode(Utility.readDataFromExcel(1, 1));
	Thread.sleep(4000);
	Reporter.log("Entering mobile number ",true);
	logger.info("Entering Mobile Number");
	addressDetails.enterMobNum(Utility.readDataFromExcel(1, 2));
	Thread.sleep(1000);
	Reporter.log("Clicking on continue button ", true);
	logger.info("Clicking on Continue Button");
	addressDetails.clickOnContinueButton();
	Thread.sleep(1000);
	}

	@Test
	public void validateTestPlansFromTextAndBanners() throws InterruptedException, IOException
	{
	Thread.sleep(5000);
	Reporter.log("Fetching number of results from text ", true);
	int textResult = result.availablePlanNumberFromText();
	Thread.sleep(7000);
	Reporter.log("Fetching number of results from Banners ", true);
	int bannerResult = result.availablePlanNumberFromBanners();
	Thread.sleep(1000);
	Assert.assertEquals(textResult, bannerResult,"Text results are not matching with Banner results, TC is failed");
	//System.out.println(Utility.readDataFromPropertyFile("MobNum"));
	Reporter.log("TC is passed ", true);
	Utility.takeScreenShot(driver, "CF_TC555");

	}

	@AfterMethod
	public void closeBrowser() throws InterruptedException
	{
	Thread.sleep(3000);
	closeCoverFox();
	}
	
	
  
}
