package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import testbase.TestBase;

public class Utility extends TestBase {
	
	public static void scrollToElement(WebElement webele) {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(false);",webele);
		js.executeScript("window.scrollBy(0,300)","");
	}
	
	public static void clickonElementJS(WebElement el) {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();",el);
	}
	
	//Explicit timer
	
	public static void waitForElementToClickable(WebElement ele) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(Integer.parseInt(explicitTime)));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	public static void waitForElementToVisible(WebElement ele) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(Integer.parseInt(explicitTime)));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public static void waiteForElementPolling(WebElement ele) {
		
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		wait.withTimeout(Duration.ofSeconds(Integer.parseInt(explicitTime)))
		.pollingEvery(Duration.ofSeconds(5))
		.ignoring(NoSuchElementException.class);

	}
	
	public static void captureScreen() throws IOException {
		TakesScreenshot sc = (TakesScreenshot) driver;
		File file = sc.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(file, new File(getDate()+"image.jpg"));
	}
	
	public static String getDate() {
		
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("Y_MMM_d_H_m_s_S_z");
		String date = sdf.format(dt);
		return date;
		
	}
	
	public static void attachScreenshotToReport() throws IOException {
		TakesScreenshot sc = (TakesScreenshot) driver;
		String file = sc.getScreenshotAs(OutputType.BASE64);
		String st = "<img src=\"data:image/png;base64, "+file+"\"height=\"600\"width=\"800\" >";
		Reporter.log(st);
	}


}
