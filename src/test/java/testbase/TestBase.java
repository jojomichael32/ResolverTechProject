package testbase;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class TestBase {
	
	public static WebDriver driver;
	public static String browser;
	public static Properties prop;
	public static String explicitTime;
	public static String implicitTime;
	
	
	
	public static  WebDriver getInstance() throws Throwable {
		
		File file = new File("./src/main/java/config/config.properties");
		FileInputStream inStream = new FileInputStream(file);
		
		prop = new Properties();
		prop.load(inStream);
		browser = prop.getProperty("browser");
		explicitTime=prop.getProperty("explicitTime");
		implicitTime=prop.getProperty("implicitTime");
		
		if(browser.equalsIgnoreCase("chrome"))
		{
			ChromeOptions opt = new ChromeOptions();
			System.setProperty("webdriver.chrome.driver", prop.getProperty("chromeAgent"));
			driver = new ChromeDriver(opt);
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			FirefoxOptions opt = new FirefoxOptions();
			System.setProperty("webdriver.gecko.driver",prop.getProperty("firefoxAgent"));
			driver = new FirefoxDriver(opt);
			
		}
		else if(browser.equalsIgnoreCase("edge"))
		{
			EdgeOptions opt = new EdgeOptions();
			System.setProperty("webdriver.edge.driver",prop.getProperty("edgeAgent"));
			driver = new EdgeDriver(opt);
			
			
			
		}
		else if(browser.equalsIgnoreCase("IE"))
		{
			
		}
		else
		{
			Throwable thr = new Throwable();
			thr.initCause(null);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(implicitTime), TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		return driver;
	}

}
