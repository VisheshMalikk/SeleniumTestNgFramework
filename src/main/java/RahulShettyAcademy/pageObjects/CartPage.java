package RahulShettyAcademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import RahulShettyAcademy.abstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;
	
	By cartSection = By.cssSelector(".cartSection");
	
	@FindBy(xpath = "//button[text()='Checkout']")
	WebElement checkOutBtn;


	public boolean displayCartProduct(String productName) {
		fnWaitForElementToAppear(cartSection);
		boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	
	public CheckoutPage goToCheckout() {
		checkOutBtn.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}
	
	
	

}
