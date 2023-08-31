package Convox.ObjectRepository.AdminModule;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Convox.GenericLibraries.WebDriverLibrary;

public class AdminHomePage extends WebDriverLibrary {
	
	// Declaration
	@FindBy (xpath = "//img[@src=\"../images/ADMIN_logo.png\"]")
	private WebElement AdminImg;
	
	@FindBy (xpath = "//button[@title='Sign Off']")
	private WebElement AdminLogoutBtn;
	
	// Initialization
	public AdminHomePage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	// Utilization
	public WebElement getAdminImg() {
		return AdminImg;
	}

	public WebElement getAdminLogoutBtn() {
		return AdminLogoutBtn;
	}
	
// Business Library Logics
	
	/**
	 * This Method is used to logout the Admin from Convox CCS 3.2.4 Application
	 */
	public void AdminLogout()
	{
		AdminImg.click();
		AdminLogoutBtn.click();
	}
	
}
