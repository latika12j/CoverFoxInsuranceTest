package coverFoxUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class Utility 
{
	public static String readDataFromExcel(int row, int cell) throws EncryptedDocumentException, IOException
	{
	Reporter.log("Reading data from ExcelSheet", true);

	FileInputStream myfile= new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\coverfox.xlsx");

	org.apache.poi.ss.usermodel.Sheet mysheet = WorkbookFactory.create(myfile).getSheet("Sheet1");

	String data = mysheet.getRow(row).getCell(cell).getStringCellValue();
	
	return data;
	}
	
	public static void takeScreenShot(WebDriver driver,String TCID) throws IOException
	{
	Reporter.log("Taking screenshot", true);
	
	String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

	File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	File dest=new File("C:\\Users\\lenovo\\Desktop\\images\\coverFox"+TCID+"_"+timeStamp+".png");
	
	Reporter.log("Saved screenshot at "+dest, true);
	
	org.openqa.selenium.io.FileHandler.copy(src, dest);
	}
	
	public static String readDataFromPropertyFile(String key) throws IOException
	{
		//Create Object of Properties Class
		Properties prop=new Properties();
		//File Location
		FileInputStream myfile=new FileInputStream(System.getProperty("user.dir")+"//CoverFoxData.properties");
		//load file
		prop.load(myfile);
		//pass the Key for the data we want
		String value=prop.getProperty(key);
		return value;	
		
	}
}
