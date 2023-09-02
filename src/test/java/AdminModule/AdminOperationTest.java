package AdminModule;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import Convox.GenericLibraries.PropertyFileLibrary;
import Convox.ObjectRepository.AdminModule.AdminHomePage;
import Convox.ObjectRepository.AdminModule.AdminLoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AdminOperationTest {

	public static void main(String[] args) throws Throwable {	
		
		PropertyFileLibrary pLib = new PropertyFileLibrary();
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();
		String URL = pLib.getpropertyfile("url");
		String USERNAME = pLib.getpropertyfile("admin_Username");
		String PASSWORD = pLib.getpropertyfile("admin_Password");
		driver.get(URL);
		
		// ADMIN Login to the ConVox CCS Application 
		AdminLoginPage adlp = new AdminLoginPage(driver);
		adlp.LoginToAdminModule(USERNAME, PASSWORD);
		Reporter.log("--- Logged Into Admin Module Succesfully ---",true);
		
		// ADMIN Logout from ConVox CCS 3.2.4 Application
		AdminHomePage adhp = new AdminHomePage(driver);
		adhp.AdminLogout();
		Reporter.log("--- Logged Out from Admin Module Succesfully ---",true);

	}

}
