package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProjectPage {

	private WebDriver dr;
	
	public ProjectPage(WebDriver driver)
	{
		this.dr=driver;
		PageFactory.initElements(dr, this);
	}
	
	@FindBy(xpath="//input[@id='inputEmail'] [@type='email']")
	WebElement email;
	
	@FindBy(xpath="//input[@id='inputPassword'] [@type='password']")
	WebElement password;
	
	@FindBy(xpath="//button[text()='Sign in'][@type = 'submit']")
	WebElement SubmitButton;
	
	
	@FindBy(xpath="//ul[@class='list-group']/li")
	List <WebElement> items;

	@FindBy(css="button[id='dropdownMenuButton'][type='button']")
	WebElement MenuButton;
	
	@FindBy(css="div[class='dropdown-menu show'] a[class='dropdown-item']")
	List<WebElement> dropdownMenuShow;
	
	@FindBy(xpath="//div[@class='jumbotron'] /child::div[@id='test-4-div']/button")
	List<WebElement> enabledButton;
	
	@FindBy(xpath="//button[@id='test5-button' and @type = 'button']")
	WebElement disabledButton;
	
	//@FindBy(xpath="//table[@class='table table-bordered table-dark']/tbody/tr/td")
	
	public boolean isEmailtDisplayed()
	{
		boolean b=false;
		try {
			b=email.isDisplayed();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			}
		return b;
	}
	
	public boolean isPasswrodtDisplayed()
	{
		boolean b=false;
		try {
			b=password.isDisplayed();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			}
		return b;
	}
	public boolean isSignOnDisplayed()
	{
		boolean b=false;
		try {
			b=SubmitButton.isDisplayed();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			}
		return b;
	}
	
	public void enterEmail(String str)
	{
		email.sendKeys(str);
	}
	
	public void enterPassword(String str)
	{
		password.sendKeys(str);
	}
	
	//In the test 2 div, assert that there are three values in the listgroup
	
	public int verifyItemsInListGroup()
	{
		int count = items.size();
		return count;
	}
	
	//Assert that the second list item's value is set to "List Item 2"
	
	public String getListItem(String str, int i)
	{
		//List Item 2
		String str1=dr.findElement(By.xpath("//li[contains(text(),'"+str+" "+i+"')]")).getText();
		int a = str1.length();
		return str1.substring(0, a-2);
		//return dr.findElement(By.xpath("//li[contains(text(),'"+str+" "+i+"')]")).getText();          
	}
	
	public String getListItemBadge(String str, int i)
	{
		//List Item 2
		return dr.findElement(By.xpath("//li[contains(text(),'"+str+" "+i+"')]/span")).getText();          
	}
	
	public String getCellData(int row, int col)
	{
		return dr.findElement(By.xpath("//table[@class='table table-bordered table-dark']/tbody/tr["+row+"]/td["+col+"]")).getText();
	}
}
