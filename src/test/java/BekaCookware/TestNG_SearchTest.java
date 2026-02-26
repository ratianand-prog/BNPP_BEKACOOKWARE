package BekaCookware;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductPage;
import pages.SearchPage;
import utilities.ConfigProperty;
import utilities.DriverManager;
import utilities.LanguageManager;

public class TestNG_SearchTest {

    WebDriver driver;
    SearchPage searchPage;
    @DataProvider(name = "languages")
    public Object[][] languageSelection() {
        return new Object[][]{
                {"EN"},
                {"NL"},
        };
    }

    @BeforeMethod(groups = "smoke")
    public void driverSetup() {
        DriverManager.initDriver();
        driver = DriverManager.getDriver();
        driver.get(ConfigProperty.getProperty("Base_URL"));
    }

    @Test(groups = "smoke",dataProvider = "languages", description = "Search a product and validate the search title and product found")
    public void searchProductFound(String language) throws InterruptedException {
        searchPage = new SearchPage(driver);
        searchPage.acceptCookies();
        searchPage.setLanguageInGeoLocationForm(language);
        LanguageManager.loadLanguage(language);
        searchPage.addTextAndSearchForProduct((LanguageManager.getLabel("searchPage","searchTextFound")));

        Assert.assertEquals(searchPage.getSeacrhPageH1Title(),
                (LanguageManager.getLabel("searchPage","searchResultTitle")),
                "validation search title after the search is clicked");
        Assert.assertEquals(searchPage.getFirstModel(),
                (LanguageManager.getLabel("searchPage","searchTextFound")),
                "validation after search first model text is correct");

        String expectedSearchPageMessage=LanguageManager.getLabel("searchPage","searchResultFoundMessage")
                .concat(" '"+(LanguageManager.getLabel("searchPage","searchTextFound"))+"'");

        String actualSearchPageMessage=searchPage.getSearchPageMessage();
        actualSearchPageMessage = actualSearchPageMessage.replace("\"", "'").replace("“", "'").replace("”", "'");

        System.out.println(expectedSearchPageMessage);
        System.out.println(actualSearchPageMessage);
        Assert.assertEquals(actualSearchPageMessage.contains(expectedSearchPageMessage),true,
                "validating results found for the searched product");

    }

    @Test(dataProvider = "languages", description = "Search a product and validate the search title and product not found")
    public void searchProductNotFound(String language) throws InterruptedException {
        searchPage = new SearchPage(driver);
        searchPage.acceptCookies();
        searchPage.setLanguageInGeoLocationForm(language);
        LanguageManager.loadLanguage(language);
        searchPage.addTextAndSearchForProduct((LanguageManager.getLabel("searchPage","searchTextNotFound")));

        Assert.assertEquals(searchPage.getSeacrhPageH1Title(),
                (LanguageManager.getLabel("searchPage","searchResultTitle")),
                "validation search title after the search is clicked");

        String expectedSearchPageMessage=LanguageManager.getLabel("searchPage","searchResultNotFoundMessage")
                .concat(" '"+(LanguageManager.getLabel("searchPage","searchTextNotFound"))+"'");

        String actualSearchPageMessage=searchPage.getSearchPageMessage();
        actualSearchPageMessage = actualSearchPageMessage.replace("\"", "'").replace("“", "'").replace("”", "'");

        System.out.println(expectedSearchPageMessage);
        System.out.println(actualSearchPageMessage);
        Assert.assertEquals(actualSearchPageMessage.contains(expectedSearchPageMessage),true,
                "validating no results found for the searched product");

    }


    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
