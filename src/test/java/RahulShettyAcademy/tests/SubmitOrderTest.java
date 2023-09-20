package RahulShettyAcademy.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import RahulShettyAcademy.TestComponents.BaseTest;
import RahulShettyAcademy.pageObjects.CartPage;
import RahulShettyAcademy.pageObjects.CheckoutPage;
import RahulShettyAcademy.pageObjects.ProductCatalogPage;

public class SubmitOrderTest extends BaseTest {

		@Test
		public void submitOrder() throws Exception {
		String productName = "ZARA COAT 3";
		// Launching the the application
		// Its a method which will use in every Test Case method so add @BeforeTest Annotation on this method
		// Scan both the classes local and inherited class as well for @BeforeTest 
		// As we know that we can access method and variable from the Parent class --  launchApplication(); this method is in Parent class (BaseTest)
		// So we can remove below line but have a problem about landingPage
		//LandingPage landingPage = launchApplication();
		// So lets do some changes in BaseTest class
		
		// Login to Application
		ProductCatalogPage productCatalog = landingPage.loginApplication("funny@gmail.com", "1@Vm2345678");
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();
		boolean flag = cartPage.displayCartProduct(productName);
		// No assertions should be write in the page object file
		Assert.assertTrue(flag);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.getCountry("india");
		System.out.println("Test Case Completed !!!!");
		Thread.sleep(4000);
		}
}
