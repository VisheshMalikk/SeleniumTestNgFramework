package RahulShettyAcademy.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulShettyAcademy.abstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;

	// First method to execute when we touch the class after that anything will happen
	// It is the way where you can initialise your code - like variables driver
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); // Go and initialize all the elements or constructed the all elements
	}
	// From where we will get this driver : driver.findElement("XPATH");
	// If we create a a Local Driver like WebDriver driver; but this driver don't have life
	// To give the life to driver we have to get it from Test class
	// WebElement email = driver.findElement(By.xpath("//input[@id='userEmail']"));

	// There is another nice way to declare above line
	// Page Factory - It's a Design Pattern
	// We can reduce the syntax of creating the WebElement
	// Same step we can write as below

	// At run time it - @FindBy(id = "userEmail") will be constructed like this -
	// driver.findElement(By.xpath("//input[@id='userEmail']"));
	// We don't need to provide --- driver.findElement("XPATH"); and assign it to
	// the variable
	// But have a doubt that how below guy will know about driver
	// Below line will ask to construct - driver
	// Where is driver - driver is not here
	// So what PageFactory does it - It create a method initElements() - will
	// trigger to initialise all the elements
	
	@FindBy(id = "userEmail")
	WebElement email;

	@FindBy(id = "userPassword")
	WebElement password;

	@FindBy(id = "login")
	WebElement loginBtn;

	// Note - Page Object should not hold any data It should focus on Elements and
	// actions
	// Action Methods -

	public ProductCatalogPage loginApplication(String emailId, String pass) {
		email.sendKeys(emailId);
		password.sendKeys(pass);
		loginBtn.click();
		ProductCatalogPage productCatalog = new ProductCatalogPage(driver);
		return productCatalog;
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

}
