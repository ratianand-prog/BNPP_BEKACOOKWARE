package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.LanguageManager;

import java.util.List;

public class ProductPage extends HomePage {

    private WebDriver driver;

    public ProductPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='container']//h1")
    private WebElement productTitle;

    @FindBy(id="ProductCountDesktop")
    private WebElement productCountDesktopMessage; //Er zijn 212 producten gevonden

    @FindBy(xpath="//div[@class='swiper-container swiper-container-initialized swiper-container-horizontal']" +
            "//div[1]/a/div[@class='collection__subcollection-image']")
    private WebElement productSets;

    @FindBy(xpath="//div[@class='swiper-container swiper-container-initialized swiper-container-horizontal']" +
            "//div[2]/a/div[@class='collection__subcollection-image']")
    private WebElement productPot;

    @FindBy(xpath="//div[@class='swiper-container swiper-container-initialized swiper-container-horizontal']" +
            "//div[3]/a/div[@class='collection__subcollection-image']")
    private WebElement productPan;

    @FindBy(xpath="//div[@class='swiper-container swiper-container-initialized swiper-container-horizontal']" +
            "//div[4]/a/div[@class='collection__subcollection-image']")
    private WebElement productSaucepan;

    @FindBy(xpath="//div[@class='swiper-container swiper-container-initialized swiper-container-horizontal']" +
            "//div[4]/a/div[@class='collection__subcollection-image']")
    private WebElement productSkillets;

    @FindBy(xpath="//div[@class='swiper-container swiper-container-initialized swiper-container-horizontal']" +
            "//div[5]/a/div[@class='collection__subcollection-image']")
    private WebElement productOven;

    public String getProductTitle() {
        return productTitle.getText().trim();
    }

    public String getProductSetsTitle() {
        return productSets.getText().trim();
    }

    public String getProductPotsTitle() {
        return productPot.getText().trim();
    }

    public String getProductPanTitle() {
        return productPan.getText().trim();
    }

    public String getProductSaucepanTitle() {
        return productSaucepan.getText().trim();
    }
    public String getProductSkilletsTitle() {
        return productSkillets.getText().trim();
    }


    public String getProductCountMessage() {
        return productCountDesktopMessage.getText().trim();
    }
    public String getProductOvenTitle() {
        return productOven.getText().trim();
    }

    /***************Validations for images***************/
    @FindBy(xpath = "//div[@class='product__image']//img")
    private List<WebElement> productImages;

    public List<WebElement> getProductImages() {
        return productImages;
    }
}