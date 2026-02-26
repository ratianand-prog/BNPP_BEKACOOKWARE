package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.LanguageManager;
import utilities.WaitManager;

import java.util.List;

public class CartPage extends HomePage{
    private WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


   // List<WebElement> products = driver.findElements(By.xpath("//div[@class='search-general']//div[@class='search-results-wrapper']"));
    @FindBy(xpath="//div[@class='search-general']//div[@class='search-results-wrapper']//h2/a")
    List<WebElement> products;

    @FindBy(xpath="//div[@class='quantity-select js-quantity-select']//input")
    WebElement quantityToBeAdded;

    @FindBy(xpath="//div[@class='product__center']//button[@type='submit']")
    WebElement addToCart;

    @FindBy(id="add-to-cart-error")
    WebElement errorWhenMoreProductsAdded;

    @FindBy(xpath="//h1")
    WebElement productTitle;

    @FindBy(xpath="//ul/li[4]/a[@data-modal-id='cart-modal']")
    WebElement cartButton;

    @FindBy(xpath="//div/div[@class='product__price']/span")
    WebElement productPrice;

    @FindBy(xpath="//p[@class='ajaxcart__total-price']")
    WebElement totalCart;
    public String getProductTitle()
    {
        return productTitle.getText().trim();
    }

    public void selectTheDesiredProduct(String requiredProduct)
    {
        for (WebElement product : products) {
            System.out.println(product.getText().trim());
            if (product.getText().trim().equalsIgnoreCase(requiredProduct)) {
                product.click();
                break; // stop after first match
            }
        }
    }

    public void selectTheDesiredQuantity(String quantity)
    {
        quantityToBeAdded.clear();
        quantityToBeAdded.sendKeys(quantity);

    }

    public void clickAddToCart()
    {
        addToCart.click();
    }

    public double getProductPrice()
    {
        System.out.println(regexConversion(productPrice.getText().trim()));
        return regexConversion(productPrice.getText().trim());

    }

    public double getTotalPrice()
    {
        WaitManager.waitForElementToBeVisible(driver,totalCart,10);
        System.out.println(regexConversion(totalCart.getText().trim()));
        return regexConversion(totalCart.getText().trim());
    }
    public double calculateTotalCost(String quantity)
    {
        double price = (getProductPrice());
        int quantity_int = Integer.parseInt(quantity);
        System.out.println(price*quantity_int);
        return price*quantity_int;
    }
    public void clickCartButton()
    {
        cartButton.click();
    }

    public String getErrorMessageForExceedingProductLimit()
    {
    return errorWhenMoreProductsAdded.getText().trim();
    }

}
