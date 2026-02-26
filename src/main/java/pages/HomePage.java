package pages;

        import org.openqa.selenium.*;
        import org.openqa.selenium.support.*;
        import org.openqa.selenium.support.PageFactory;
        import utilities.ConfigProperty;
        import utilities.DriverManager;
        import utilities.WaitManager;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//div/div/button[2]")
    private WebElement acceptCookiesButton;
    @FindBy(xpath="//a[@class='page-header__logo']")
    private WebElement logo;

    @FindBy(xpath="//div[@id='geolocation-container']//button[@type='submit']")
    private WebElement geoLocationSubmitButton;

    //div[@id='geolocation-container']//button[@type='submit']
    @FindBy(xpath="//select[@id='GeolocationLanguageSelect']")
    private WebElement languageDropdown;

    @FindBy(xpath="//nav/ul/li[1][@class='main-nav__list-item main-nav__list-item--has-dropdown']/a")
    private WebElement productList;

    @FindBy(xpath="//button[@data-modal-id='search-modal']")
    private WebElement searchFeature;

    @FindBy(xpath="//div[@class='page-wrap']//div[@class='modal-wrapper modal-wrapper--search'][2]//form[@class='search-bar']//input[@id='search']")
    private WebElement search;

    @FindBy(xpath="//button[@aria-label='Close dialog']")
    WebElement flyoutCloseButton;
    public void acceptCookies() {
        try {
            acceptCookiesButton.click();
        } catch (Exception ignored) {}
    }

    public boolean isLogoDisplayed() {
        return logo.isDisplayed();
    }

    public void clickGeolocationSubmitButton() {
       geoLocationSubmitButton.click();
    }

    public void setLanguageInGeoLocationForm(String language) {
        try {
            driver.findElement(By.xpath("//select[@id='GeolocationLanguageSelect']/option[@value='"+language+"']")).click();
            geoLocationSubmitButton.click();
        } catch (Exception ignored) {
            // do nothing
        }
    }

    public void clickFlyoutCloseButton() {
        try {
            flyoutCloseButton.click();
        } catch (Exception ignored) {
            // do nothing
        }
    }

    public void openProducts() {
        productList.click();
    }

    public void addTextAndSearchForProduct(String productName) throws InterruptedException {
        searchFeature.click();
        WaitManager.waitForElementToBeVisible(driver,search,10);
        search.clear();
        search.sendKeys(productName);
        search.sendKeys(Keys.ENTER);
    }

    public double regexConversion(String number)
    {
        String cleanTotal = number
                .replaceAll("[^0-9,]", "")  // remove € and dot
                .replace(".", "")           // remove thousand separator
                .replace(",", ".");         // convert decimal comma to dot
        return Double.parseDouble(cleanTotal);
    }
}
