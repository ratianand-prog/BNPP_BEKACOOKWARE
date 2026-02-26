package BekaCookware;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.SearchPage;
import pages.CartPage;
import utilities.ConfigProperty;
import utilities.DriverManager;
import utilities.LanguageManager;

public class TestNG_CartTest {
    WebDriver driver;
    CartPage cartPage;
    SearchPage searchPage;
    @DataProvider(name = "languages")
    public Object[][] languageSelection() {
        return new Object[][]{
                {"EN"},
                {"NL"},
        };
    }
    @Test(dataProvider = "languages")
    public void driverSetup(String language) {
        DriverManager.initDriver();
        driver = DriverManager.getDriver();
    }

    @Test(dataProvider = "languages",description = "Search product to add to cart",priority=10)
    public void searchProductToAddToCart(String language) throws InterruptedException {
        driver.get(ConfigProperty.getProperty("Base_URL"));
        cartPage = new CartPage(driver);
        cartPage.acceptCookies();
        cartPage.setLanguageInGeoLocationForm(language);
        cartPage.clickFlyoutCloseButton();
        cartPage.addTextAndSearchForProduct("pan");
        LanguageManager.loadLanguage(language);
        cartPage.selectTheDesiredProduct((LanguageManager.getLabel("cartPage","productRequired")));
        Assert.assertEquals(cartPage.getProductTitle(),LanguageManager.getLabel("cartPage","productRequired")
                ,"Validate the title is same as the selected product");

    }

@Test(description = "Validation",priority=20)
    public void addToCartAndValidate() throws InterruptedException {
    cartPage = new CartPage(driver);
    LanguageManager.loadLanguage("EN");
    cartPage.selectTheDesiredQuantity(LanguageManager.getLabel("cartPage","addToCart10"));
    Thread.sleep(5000);
    cartPage.clickFlyoutCloseButton();
    Thread.sleep(5000);
    cartPage.clickAddToCart();
    cartPage.clickCartButton();
    double totalcost=cartPage.calculateTotalCost(LanguageManager.getLabel("cartPage","addToCart10"));
    cartPage.clickCartButton();
    Assert.assertEquals(cartPage.getTotalPrice(),totalcost);
    }

    @AfterSuite
    public void tearDown() {
        DriverManager.quitDriver();
    }
}