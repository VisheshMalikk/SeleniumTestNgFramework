package RahulShettyAcademy.tests;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {

	public static void main(String[] args) throws Exception {
		
		String productName = "ZARA COAT 3";
	
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://rahulshettyacademy.com/client");
		
		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("funny@gmail.com");
		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("1@Vm2345678");
		driver.findElement(By.xpath("//input[@id='login']")).click();
		// Explict Wait
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement matchedProduct = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		matchedProduct.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.cssSelector("[routerLink*='/dashboard/cart']")).click();
		
		
		List<WebElement> productsInCart = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean flag = productsInCart.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(flag);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,70)", "");
		
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		
		Actions action = new Actions(driver);
		action.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		List<WebElement> filteredCountry = driver.findElements(By.cssSelector(".ta-results button span"));
		List<WebElement> exactResult = filteredCountry.stream().filter(country-> country.getText().trim().equalsIgnoreCase("India")).collect(Collectors.toList());
		exactResult.get(0).click();
		
		js.executeScript("window.scrollBy(0,200)", "");
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='hero-primary']")));
		String confirmMessage = driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		System.out.println("Test Case Completed !!!!");
		
		Thread.sleep(4000);

		driver.close();
	}

}
