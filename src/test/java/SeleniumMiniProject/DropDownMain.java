package SeleniumMiniProject;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;


import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DropDownMain {

	public static void main(String[] args) {
		// Creating instance of AccountCreator
		AccountCreator accountCreator = new AccountCreator();
		// Prompt user to enter browser choice
		boolean isValidBrowser = false;
		System.out.println("Enter the browser to Automate (Chrome/Edge)");
		Scanner scanner = new Scanner(System.in);
		// Loop to ensure a valid browser input
		while (!isValidBrowser) {
			String browser = scanner.next();
			if (browser.equalsIgnoreCase("Chrome") || browser.equalsIgnoreCase("Edge")) {
				accountCreator.getWebDriver(browser);
				isValidBrowser = true;
			} else {
				System.out.println("Enter Valid Browser name (Chrome/Edge)");
			}
		}
		scanner.close();
		try {

			// Read data from Excel file
			File excelFile = new File("C:\\Users\\2318461\\eclipse-workspace\\DropDownMenu\\src\\test\\resources\\Details.xlsx");
			FileInputStream fis = new FileInputStream(excelFile);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("Sheet1");
			XSSFRow row = sheet.getRow(1);

			// Extract data from Excel cells
			String websiteLink = row.getCell(0).toString();
			String name = row.getCell(1).toString();
			String email = row.getCell(2).toString();
			String password = row.getCell(3).toString();
			String dayOfBirth = row.getCell(4).toString();
			String monthOfBirth = row.getCell(5).toString();
			String yearOfBirth = row.getCell(6).toString();
			String country = row.getCell(7).toString();


			// Navigating to registration page
			accountCreator.navigateToRegistrationPage(websiteLink);
			// Filling registration form
			accountCreator.fillRegistrationForm(name, email, password);
			// Printing all options
			accountCreator.printAllOptions();
			// Selecting date of birth
			accountCreator.selectDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth);
			// Selecting country
			accountCreator.selectCountry(country);
			// Verifying country selection

			accountCreator.verifyCountrySelection();
			// Capturing screenshot
			accountCreator.takeScreenshot();
			// Quitting the browser
			accountCreator.quit();

			workbook.close();
		} catch (Exception e) {
			System.out.println("Error reading Excel file: " + e.getMessage());
		}
	}
}


