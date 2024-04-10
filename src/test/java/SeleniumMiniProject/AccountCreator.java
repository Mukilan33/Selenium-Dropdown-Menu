package SeleniumMiniProject;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AccountCreator {
	private static WebDriver driver = null;

	public  WebDriver getWebDriver(String driverStr) {
		// Initialize WebDriver based on user choice
		if (driverStr.equalsIgnoreCase("Chrome")) {
			System.out.println("Chrome Browser is selected");
			//System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (driverStr.equalsIgnoreCase("Edge")) {
			System.out.println("Edge Browser is selected");
			//System.setProperty("webdriver.edge.driver", "./driver/msedgedriver.exe");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			
		}
		return driver;
	}
	// Method to navigate to the registration page
	public void navigateToRegistrationPage(String URL) {
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.linkText("Create a new account")).click();
		driver.manage().window().maximize();
		System.out.println("Navigation to registration page successful");

	}
	// Method to fill the registration form
	public void fillRegistrationForm(String name, String email, String password) {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"tblcrtac\"]/tbody/tr[3]/td[3]/input")).sendKeys(name);
		driver.findElement(By.xpath("//*[@id=\"tblcrtac\"]/tbody/tr[7]/td[3]/input[1]")).sendKeys(email);
		driver.findElement(By.xpath("//*[@id=\"tblcrtac\"]/tbody/tr[7]/td[3]/input[2]")).click();
		driver.findElement(By.xpath("//*[@id='radio_login']")).click();
		driver.findElement(By.cssSelector("#newpasswd")).sendKeys(password);
		driver.findElement(By.cssSelector("#newpasswd1")).sendKeys(password);
		driver.findElement(By.className("nomargin")).click();
	}
	// Print all options
	public  void printAllOptions() {

		List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"country\"]"));
		for (WebElement element : elements) {
			System.out.println(element.getText());
		}
	}
	// Method to select date of birth from dropdowns
	public   void selectDateOfBirth(String day, String month, String year) {

		int dayInt = (int) Float.parseFloat(day);
		int yearInt = (int) Float.parseFloat(year);
		Select daySelect = new Select(driver.findElement(By.xpath("//*[@id=\"tblcrtac\"]/tbody/tr[22]/td[3]/select[1]")));
		daySelect.selectByValue(String.valueOf(dayInt));
		Select monthSelect = new Select(driver.findElement(By.xpath("//*[@id=\"tblcrtac\"]/tbody/tr[22]/td[3]/select[2]")));
		monthSelect.selectByVisibleText(month);
		Select yearSelect = new Select(driver.findElement(By.xpath("//*[@id=\"tblcrtac\"]/tbody/tr[22]/td[3]/select[3]")));
		yearSelect.selectByValue(String.valueOf(yearInt));
	}
	// Method to select the country from dropdown
	public   void selectCountry(String country) {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement element = driver.findElement(By.xpath("//*[@id=\"country\"]"));
		Select select = new Select(element);
		System.out.println(select.getOptions().size());
		select.selectByVisibleText(country);
		System.out.println(select.getFirstSelectedOption().getText());
	}
	// Method to verify if the country selection is correct
	public  void verifyCountrySelection() {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement element = driver.findElement(By.xpath("//*[@id=\"country\"]"));
		Select select = new Select(element);
		if (select.getFirstSelectedOption().getText().equals("India")) {
			System.out.println("TEST CASE PASSED: Country selection is correct");
		}
		else
		{
			System.out.println("TEST CASE FAILED: Incorrect country selection");
		}
	}
	// Method to capture a screenshot
	public  void takeScreenshot() throws IOException 
	{

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(source, new File("./snap/img.png"));
	}
	// Method to quit the WebDriver instance
	public void quit() {

		driver.quit();
		System.out.println("Browser closed successfully");
	}
}






