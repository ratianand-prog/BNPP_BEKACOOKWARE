package BekaCookware;

        import org.testng.Assert;
        import org.testng.annotations.*;
        import pages.*;
        import utilities.*;

        import org.openqa.selenium.WebDriver;

public class TestNG_ProductPage {

    WebDriver driver;
   ProductPage productPage;
    @DataProvider(name = "languages")
    public Object[][] languageSelection() {
        return new Object[][]{
                {"EN"},
                {"NL"},
        };
    }
    @BeforeMethod(groups = "localization",description = "Driver setup")
    public void driverSetup() {
        DriverManager.initDriver();
        driver = DriverManager.getDriver();
        driver.get(ConfigProperty.getProperty("Base_URL"));
    }

    @Test(dataProvider = "languages", description = "Loading and verify Product Page")
    public void loadProductPage(String language) throws InterruptedException {
        productPage = new ProductPage(driver);
        productPage.acceptCookies();
        productPage.setLanguageInGeoLocationForm(language);
        productPage.openProducts();
        Assert.assertTrue(productPage.getProductTitle().length() > 0);
        LanguageManager.loadLanguage(language);
        //this can be moved in the ENUM later
        Assert.assertEquals((LanguageManager.getLabel("productPage","title")),
                productPage.getProductTitle(),"validation of productPage title in the provided language");

//        Assert.assertEquals((LanguageManager.getLabel("productPage","sets")),
//                productPage.getProductSetsTitle(),"validation of productPage sets in the provided language");
//
//        Assert.assertEquals((LanguageManager.getLabel("productPage","pots")),
//                productPage.getProductPotsTitle(),"validation of productPage pots in the provided language");
//
        Assert.assertEquals((LanguageManager.getLabel("productPage","productCountMessage")),
                productPage.getProductCountMessage(),"validation of productPage count message in the provided language");



    }


       @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}