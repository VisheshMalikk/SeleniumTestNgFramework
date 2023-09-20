package RahulShettyAcademy.abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import RahulShettyAcademy.pageObjects.CartPage;

public class AbstractComponents {

	// This will be the parent class of all Page Object class because It holds all the resuable stuff
	// To use this class into page object classes we will not create object of this class to use its method we will use OOPS concept Inheritance
	// Here we are using reuseability of the code of the parent class
	
	WebDriver driver;

	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[routerLink*='/dashboard/cart']")
	WebElement cartHeader;

	// Method for appearing element
	// Below method argument type is By because this is not the type of driver.findElement : This is just By locator 
	// WebElement ele = driver.findELement(By.id("email"));
	// Its just By class :  By.id("email")
	public void fnWaitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	// Method for disappearing element
	public void fnWaitForElementToDisappear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.invisibilityOf(element));

	}
	
	// Hearder Section Methods
	public CartPage goToCartPage() {
		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}

}
