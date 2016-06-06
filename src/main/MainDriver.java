package main;

import static libraries.Constants.IEDriver;
import static libraries.Constants.browserName;
import static libraries.Constants.chromeDriver;
import libraries.ExcelUtility;
import libraries.Keywords;
import libraries.Reusable;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class MainDriver {

	public static WebDriver driver;
	public static ExcelUtility input;
	public static Keywords actions;
	public static Reusable module;
	
	@BeforeSuite
	public void setUP(){
		System.out.println("Before Suite");
		input = new ExcelUtility();
	}
	
	@BeforeClass
	public void initializeDriver(){
		System.out.println("Before Class");
		selectDriverObject();
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		DOMConfigurator.configure("log4j.xml");
		actions = new Keywords(driver);
		module = new Reusable(driver);
	}
	
	@AfterClass
	public void quiteDriver(){
		System.out.println("After Class");
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	
	public WebDriver selectDriverObject(){
		
		if(browserName.equalsIgnoreCase("firefox")){
			driver = new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("Chrome")){
			
			System.setProperty("webdriver.chrome.driver", chromeDriver);
			driver = new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("IE") || browserName.equalsIgnoreCase("Internet Explorer")){
			
			System.setProperty("webdriver.ie.driver", IEDriver);
			driver = new InternetExplorerDriver();
		}
		
		return driver;
	}
}
