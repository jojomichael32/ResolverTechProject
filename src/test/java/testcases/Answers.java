package testcases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.ProjectPage;
import testbase.TestBase;

public class Answers {
	
	WebDriver driver;
	ProjectPage projectPage;
	@BeforeTest
	public void envSetUp() throws Throwable {
		
		//System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver = TestBase.getInstance();
		//driver.get("file:///C:/Users/15197/Downloads/QE-index.html?");
		//driver.manage().window().maximize();
		projectPage=new ProjectPage(driver);
		
	}
	
	@Test(priority=1,description="Test 1 implimentation")
	public void Test1Demo()
	{
	
		Assert.assertTrue(projectPage.isEmailtDisplayed());
		Assert.assertTrue(projectPage.isPasswrodtDisplayed());
		Assert.assertTrue(projectPage.isSignOnDisplayed());
		
		//Enter in an email address and password combination into the respective fields
		projectPage.enterEmail("jm.jojo2775@gmail.com");
		projectPage.enterPassword("hzjxhjw2332");
		
	}
	
	@Test(priority=2,description="Test 2 implimentation")
	public void Test2Demo()
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,200)");
		
		Assert.assertEquals(projectPage.verifyItemsInListGroup(),3);
		Assert.assertEquals(projectPage.getListItem("List Item", 2), "List Item 2");
		Assert.assertEquals(projectPage.getListItemBadge("List Item", 2), "6");

	}
	
	@Test(priority=3,description="Test 3 implimentation")
	public void Test3Demo()
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,400)");
		
		//In the test 3 div, assert that "Option 1" is the default selected value
		WebElement button = driver.findElement(By.cssSelector("button[id='dropdownMenuButton'][type='button']"));
		String firstSelectedOpt= button.getText();
		Assert.assertEquals(firstSelectedOpt, "Option 1");
		
		//Select "Option 3" from the select list
		
		button.click();
		List<WebElement> dropdown = driver.findElements(By.cssSelector("div[class='dropdown-menu show'] a[class='dropdown-item']"));
		for(int i=0;i<dropdown.size();i++)
		{
			String name = dropdown.get(i).getText();
			if(name.contains("Option 3"))
			{
				dropdown.get(i).click();
			}
		}
	}
	
	@Test(priority=4,description="Test 4 implimentation")
	public void Test4Demo()
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,500)");
		
		//In the test 4 div, assert that the first button is enabled and that the second button is disabled
		
		List<WebElement> button = driver.findElements(By.xpath("//div[@class='jumbotron'] /child::div[@id='test-4-div']/button"));
		button.get(1).isEnabled();
		Assert.assertTrue(button.get(0).isEnabled());
		Assert.assertFalse(button.get(1).isEnabled());
		//Assert.assertFalse(driver.findElement(By.xpath("//button[@disabled and @type='button']")).isEnabled());		

	}
	@Test(priority=5,description="Test 5 implimentation")
	public void Test5Demo()
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,600)");
		
		//In the test 5 div, wait for a button to be displayed (note: the delay is random) and then click it
		WebElement div5button=driver.findElement(By.xpath("//button[@id='test5-button' and @type = 'button']"));
		WebDriverWait delay=new WebDriverWait(driver,Duration.ofSeconds(5));
		delay.until(ExpectedConditions.elementToBeClickable(div5button)).click();
		
		//Once you've clicked the button, assert that a success message is displayed
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='test5-alert' and @role= 'alert']")).getText(),"You clicked a button!");
		
		//Assert that the button is now disabled
		
		Assert.assertFalse(div5button.isEnabled());
	}
	
	//@Test(priority=6,description="Test 6 implimentation")
	//@Parameters({"r","c"})
	//public void Test6Demo(int row,int column)
	@Test(priority=6,description="Test 6 implimentation")
	public void Test6Demo()
	
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,700)");
		
		Assert.assertEquals(projectPage.getCellData(3, 3), "Ventosanzap");
}
	
	@AfterTest
   	public void burnDown() {
          	driver.close();
   	}

}
