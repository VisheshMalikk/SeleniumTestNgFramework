package RahulShettyAcademy.TestComponents;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

import RahulShettyAcademy.pageObjects.LandingPage;

// Github Creds 
// Visheshmalikk@gmail.com - 1@Github2345

// Jenkins Creds - 
// visheshmalik - 1@Malik2345

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initilizeDriver() throws Exception {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\RahulShettyAcademy\\resources\\GlobalData.properties");
		prop.load(fis);
		// mvn test -P Regression
		// mvn test
		// mvn clean
		// mvn test -P Regression -D browser=firefox
		String browserName = System.getProperty("browser") !=null ? System.getProperty("browser") :  prop.getProperty("browser");
		//String browserName = prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome")) {
		driver = new ChromeDriver();
		} else if(browserName.equalsIgnoreCase("firefox")) {
			// GeckoDriver
		} else if(browserName.equalsIgnoreCase("edge")) {
			// EdgeDriver
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		return driver;
	}
	
	@BeforeTest
	public LandingPage launchApplication() throws Exception {
		driver = initilizeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}

}
