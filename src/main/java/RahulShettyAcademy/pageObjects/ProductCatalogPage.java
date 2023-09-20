package RahulShettyAcademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import RahulShettyAcademy.abstractComponents.AbstractComponents;

public class ProductCatalogPage extends AbstractComponents {
	WebDriver driver;
	
	// ------------Constructor to initilaize required variables ---------------------	
	public ProductCatalogPage(WebDriver driver) {
		// Every Subclass needs to send driver for Parent with the help of super(driver);
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// --------------------------- Locators ------------------------------------------
	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	@FindBy(css = ".ng-tns-c31-1")
	WebElement spinner;
	
	By prodList = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	// --------------------------  Actions  --------------------------------------
	
	// Get Product List
	public List<WebElement> getProductList() {
		fnWaitForElementToAppear(prodList);
		return products;
	}
	// Get Product with specific name
	public WebElement getProductName(String productName) {
		WebElement matchedProduct = getProductList().stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return matchedProduct;
	}
	// Add to Cart Action
	public void addProductToCart(String productName) {
		WebElement matchedProduct = getProductName(productName);
		matchedProduct.findElement(addToCart).click();
		fnWaitForElementToAppear(toastMessage);
		fnWaitForElementToDisappear(spinner);
	}

}
