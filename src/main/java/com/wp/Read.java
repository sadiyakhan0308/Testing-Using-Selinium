package com.wp;

import static org.testng.Assert.expectThrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Read {
	
	 WebDriver driver;
	 
	//----------Taking driver and url from config.properties----------------
	 
	@BeforeTest
	public void browser() throws IOException {
		
		  String path=System.getProperty("user.dir");
		  FileInputStream f= new FileInputStream("F:\\workspace\\Test\\config.properties");
		   Properties prop  = new Properties();
		   prop.load(f);
		   
		   String genericPath=path+prop.getProperty("Driverurl");
		   System.setProperty("webdriver.chrome.driver",genericPath);
		
		
		   driver=new ChromeDriver();

			   
		  driver.get(prop.getProperty("FlipCarturl"));//will open a link
			

		
		
	}
	
	//--------------READING LOGIN CREDENTIALS FROM EXCEL SHEET------------------
	
	@ Test(priority=1,enabled=true)
	public void readCredentials() throws IOException, InterruptedException 
	
	{
		
        
		 FileInputStream fis=new FileInputStream(new File("C:\\Users\\sadiya khan\\Documents\\Book1.xlsx"));
		XSSFWorkbook wb= new XSSFWorkbook(fis);
		XSSFSheet sheet=wb.getSheet("Sheet1");
		
		XSSFRow row= sheet.getRow(0);
		String username = row.getCell(0).getStringCellValue();
		System.out.println(username);
		driver.findElement(By.xpath("(//input[@type=\"text\"])[2]")).sendKeys(username);
		XSSFRow r= sheet.getRow(1);
		String password= r.getCell(0).getStringCellValue();
		//System.out.println(pass);
		driver.findElement(By.xpath("//input[@type=\"password\"]")).sendKeys(password);
		driver.findElement(By.xpath("//button[@type=\"submit\"and @class=\"_2AkmmA _1LctnI _7UHT_c\"]")).click();
		
		//------------Using Action------------
		Actions actions = new Actions(driver);
		 Action sendEsc = actions.sendKeys(Keys.ESCAPE).build();
		 
		 sendEsc.perform();
		
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		Thread.sleep(1000);
		 Action sendPageDown = actions.sendKeys(Keys.PAGE_DOWN).build();
		 sendPageDown.perform();
		driver.findElement(By.name("q")).sendKeys("Dell");
		driver.findElement(By.className("vh79eN")).click();
		driver.findElement(By.xpath("//a[@class=\"_2cLu-l\"]")).click();
		
		//------------switch to new tab-------------------
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//input[@type=\"text\" and @class=\"_16qL6K _366U7Q\"]")).sendKeys("Sadiya");
		driver.findElement(By.name("phone")).sendKeys("8225813274");
		driver.findElement(By.name("pincode")).sendKeys("452001");
		driver.findElement(By.name("addressLine2")).sendKeys("BhavarKua");
		driver.findElement(By.name("addressLine1")).sendKeys("Opposite Tinkus");
		WebElement city = driver.findElement(By.xpath("//select[@name=\"city\"]"));
		Select cityDropDown = new Select(city);
		cityDropDown.selectByVisibleText("INDORE");

		WebElement element = driver.findElement(By.xpath("//select[@name=\"state\"]"));
		Select countryDropDown = new Select(element);
		countryDropDown.selectByVisibleText("Madhya Pradesh");
		// driverChrome.findElement(By.xpath("//div[@class=\"_6ATDKp\"]")).click();
		driver.findElement(By.xpath("//button[@type=\"button\" and @class=\"_2AkmmA EqjTfe _7UHT_c\"]")).click();
		driver.findElement(By.xpath("//button[@class=\"_2AkmmA _2Q4i61 _7UHT_c\"]")).click();

	}
	
	//------------Taking Image using sikuli---------------- 
	@ Test(priority=2,enabled=true)
	public void sikuliMethod() throws FindFailed, InterruptedException {
		
		Screen screen= new Screen();
		Pattern searchBox= new Pattern("C:\\Users\\sadiya khan\\Capture.PNG");
		screen.type(searchBox, "java");
		
		screen.click(searchBox);
		
		Thread.sleep(5000);
		
	}
	
	
	@ Test(priority=3,enabled=true)
	
	public void takeScreenshot() throws IOException
	{
		 driver.get("https://www.flipkart.com");
			// Maximize the window
			driver.manage().window().maximize();
				 
	  // Take screenshot and store as a file format
		
	File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	try
	{
	 //  copy the  screenshot to desired location using copyFile //method
	FileUtils.copyFile(src, new File("C:\\Users\\sadiya khan\\ss.png"));
	}
	 
	catch (IOException e)
	
	 {
	  System.out.println(e.getMessage());
	 
	 }
	 }

	@AfterTest
	public void closeBrowser() {
		
		driver.close();
		driver.quit();
	}

	
}
