package BekaCookware;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CartPage;
import utilities.ConfigProperty;
import utilities.DriverManager;
import utilities.LanguageManager;

public class TestNG_CartPage {

    WebDriver driver;
    CartPage cartPage;

    @DataProvider(name = "languages")
    public Object[][] languageSelection() {
        return new Object[][]{
                {"EN"},
                {"NL"}
        };
    }
    @BeforeMethod
    public void setUp(Object[] testData) {

        String language = testData[0].toString();

        DriverManager.initDriver();
        driver = DriverManager.getDriver();

        driver.get(ConfigProperty.getProperty("Base_URL"));

        cartPage = new CartPage(driver);

        cartPage.acceptCookies();
        cartPage.setLanguageInGeoLocationForm(language);

        LanguageManager.loadLanguage(language);
    }

    @Test(dataProvider = "languages",
            description = "Search product and validate title", priority =10)
    public void searchProductToAddToCart(String language) throws InterruptedException {

        cartPage.addTextAndSearchForProduct("pan");

        String expectedProduct =
                LanguageManager.getLabel("cartPage", "productRequired");

        cartPage.selectTheDesiredProduct(expectedProduct);

        Assert.assertEquals(
                cartPage.getProductTitle(),
                expectedProduct,
                "Product title mismatch"
        );
    }

    @Test(dataProvider = "languages",
            description = "Add to cart and validate total price", priority =20)
    public void addToCartAndValidate(String language) throws InterruptedException {

        cartPage.addTextAndSearchForProduct("pan");

        String expectedProduct =
                LanguageManager.getLabel("cartPage", "productRequired");

        cartPage.selectTheDesiredProduct(expectedProduct);

        String quantity =
                LanguageManager.getLabel("cartPage", "addToCart10");

        cartPage.selectTheDesiredQuantity(quantity);
        cartPage.clickAddToCart();
        cartPage.clickFlyoutCloseButton();
        // cartPage.clickGoToCartButton();

        double calculatedTotal =
                cartPage.calculateTotalCost(quantity);

        Assert.assertEquals(
                cartPage.getTotalPrice(),
                calculatedTotal,
                "Total price mismatch"
        );
    }

    @Test(dataProvider = "languages",
            description = "Empty cart validation", priority=30)
    public void emptyCartTest(String language) throws InterruptedException {

        cartPage.addTextAndSearchForProduct("Cicla braadpan");
        cartPage.selectTheDesiredProduct("Cicla braadpan");
        cartPage.selectTheDesiredQuantity("5");
        cartPage.clickAddToCart();
        cartPage.clickCartDrawerCloseButton();
        cartPage.addTextAndSearchForProduct("CHEF BRAADPAN");
        cartPage.selectTheDesiredProduct("CHEF BRAADPAN");
        cartPage.selectTheDesiredQuantity("5");
        cartPage.clickAddToCart();
        //cartPage.clickGoToCartButton();
        cartPage.clickFlyoutCloseButton();
        cartPage.emptyCart();
        cartPage.clickFlyoutCloseButton();

        Assert.assertTrue(
                cartPage.isCartEmptyMessageDisplayed(),
                "Cart is not empty"
        );
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
