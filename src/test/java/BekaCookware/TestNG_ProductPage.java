package BekaCookware;

        import org.openqa.selenium.WebElement;
        import org.testng.Assert;
        import org.testng.annotations.*;
        import org.testng.asserts.SoftAssert;
        import pages.*;
        import utilities.*;

        import org.openqa.selenium.WebDriver;

        import java.util.List;

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

    @Test(description = "Validating the image source is not empty")
    public void validateAllProductImagesHaveSrc()
    {
        ProductPage productPage = new ProductPage(driver);

        List<WebElement> images = productPage.getProductImages();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(images.size() > 0,
                "No product images found");

        for (int i = 0; i < images.size(); i++) {

            WebElement image = images.get(i);
            String src = image.getAttribute("src");

            softAssert.assertNotNull(src,
                    "Image " + i + " src is null");

            softAssert.assertFalse(
                    src == null || src.trim().isEmpty(),
                    "Image " + i + " src is empty"
            );

            System.out.println("Validated image: " + i);
        }

         softAssert.assertAll();//This has to be added to ensure that the test fails in case image is missing
    }



       @AfterSuite
    public void tearDown() {
        DriverManager.quitDriver();
    }
}