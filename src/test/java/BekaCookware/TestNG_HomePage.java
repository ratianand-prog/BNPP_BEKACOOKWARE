package BekaCookware;

        import org.testng.Assert;
        import org.testng.annotations.*;
        import pages.HomePage;
        import utilities.*;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.testng.Assert;
        import org.testng.annotations.AfterMethod;
        import org.testng.annotations.BeforeMethod;
        import org.testng.annotations.Test;


public class TestNG_HomePage {

    WebDriver driver;
    HomePage homePage;

    @BeforeMethod (groups = "smoke")
    public void driverSetup() {
       DriverManager.initDriver();
       driver = DriverManager.getDriver();
       driver.get(ConfigProperty.getProperty("Base_URL"));
    }

    @Test (groups = "smoke", description = "verify homepage loads correctly")
    public void verifyHomePageLoads() throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.acceptCookies();
        homePage.clickGeolocationSubmitButton();
        Assert.assertTrue(homePage.isLogoDisplayed());
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}