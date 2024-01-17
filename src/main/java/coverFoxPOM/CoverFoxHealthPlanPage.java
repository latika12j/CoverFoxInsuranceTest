package coverFoxPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CoverFoxHealthPlanPage 
{
	@FindBy(linkText = "Female") private WebElement femaleButton;
	@FindBy(className = "next-btn") private WebElement nextButton;
		
	public CoverFoxHealthPlanPage(WebDriver driver)
	{
	PageFactory.initElements(driver, this);
	}
	
	public void clickOnFemaleButton()
	{
	femaleButton.click();
	}
	public void clickOnNextButton()
	{
	nextButton.click();
	}

}
