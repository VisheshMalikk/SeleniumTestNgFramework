package RahulShettyAcademy.pageObjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import RahulShettyAcademy.abstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {

	WebDriver driver;

	// ---------------- Constructor ----------------
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// ---------------- Locators ---------------------

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement country;

	@FindBy(css = ".ta-results button span")
	List<WebElement> countryList;

	@FindBy(css = ".action__submit")
	WebElement submit;

	@FindBy(css = ".action__submit")
	WebElement submitBtn;

	By filteredCountryResults = By.cssSelector(".ta-results");

	// ------------------ Actions ---------------------

	public void getCountry(String countryName) {
		Actions action = new Actions(driver);
		action.sendKeys(country, countryName).build().perform();
		fnWaitForElementToAppear(filteredCountryResults);
		List<WebElement> match = countryList.stream().filter(country -> country.getText().trim().equalsIgnoreCase("India")).collect(Collectors.toList());
		match.get(0).click();
	}

	public void clickSubmitBtn() {
		submitBtn.click();
	}

}
