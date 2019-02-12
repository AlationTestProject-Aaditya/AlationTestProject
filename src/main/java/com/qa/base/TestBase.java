package com.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

/**
 * @author aaditya.a
 *
 */
public class TestBase {

	
	public static WebDriver driver;
	public static Properties prop;
	
	public static ExtentReports extent;
	public static ExtentTest extentTest;
	
	static Logger log = Logger.getLogger(TestBase.class);
	
	
	/**
	 * @author aaditya.a
	 * Functionality - Constructor to Load the property file
	 */
	
	public TestBase(){
		prop = new Properties();
		try {
			InputStream input = new FileInputStream(System.getProperty("user.dir")+File.separator+"src"+"\\main\\java\\com\\qa\\config\\config.properties");
			prop.load(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
	
	/**
	 * @author aaditya.a
	 * Functionality - To initialize the driver & launch URL based on the browser name & URL mentioned in config.property file
	 */
	
	public static void initDriver(){
		try{
		if(prop.getProperty("browser").equalsIgnoreCase("chrome")){
			log.info("****************Starting Chrome Browser*****************");
			System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, System.getProperty("user.dir")+"\\src\\resources\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(prop.getProperty("browser").equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\resources\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		}catch(Exception e){
			log.error(e);
		}
	}
	
	/**
	 * @author aaditya.a
	 * Functionality - Initialize Extent reports for Test Reporting
	 */
	
	@BeforeSuite
	public void startExtent(){
		log.info("****************Starting Before Suite*****************");
		log.info("****************Starting Extent*****************");
		extent = new ExtentReports(System.getProperty("user.dir")+"//HTML Reports//ExtentAutomationSummary.html", true);
		extent.addSystemInfo("Title", "Amazon.com - Automation Report");
		extent.addSystemInfo("Environment", "Amazon.com");
		extent.addSystemInfo("OS","Windows 7");
	
	}
	
	/**
	 * @author aaditya.a
	 * Functionality - End the Extent Reports
	 */
	
	@AfterSuite
	public void endExtent(){
		extent.flush();
		extent.close();
		log.info("****************End Extent*****************");
	}
	
	
	
}
