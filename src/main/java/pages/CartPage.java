package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.LanguageManager;
import utilities.WaitManager;

import java.time.Duration;
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

    //@FindBy(xpath="//div[@id='cart-container']")
    @FindBy(xpath="//div[@class='nav-triggers']/a[2]")
    WebElement cartButton;

    @FindBy(xpath="//button[contains(@class,'cart-drawer')]")
    WebElement cartDrawerCloseButton;

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
        WaitManager.waitForElementToBeVisible(driver,addToCart,10);
        addToCart.click();
    }

    public void clickCartDrawerCloseButton()
    {
        WaitManager.waitForElementToBeVisible(driver,cartDrawerCloseButton,10);
        cartDrawerCloseButton.click();
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
    public void clickGoToCartButton()
    {
        WaitManager.waitForElementToBeVisible(driver,cartButton,10);
        cartButton.click();
    }

    public String getErrorMessageForExceedingProductLimit()
    {
    return errorWhenMoreProductsAdded.getText().trim();
    }

    /***************Delete from Cart related methods and xpaths************/
      // Delete button for each product
    @FindBy(xpath = "//button[contains(@class,'remove')]")
    private List<WebElement> removeButtons;

    // Empty cart message

    @FindBy(xpath = "//div[@id='cart-container']/p")
    private WebElement emptyCartMessage;

    public int getCartItemCount() {
        return removeButtons.size();
    }
    public void removeProductByIndex(int index) {
        removeButtons.get(index).click();
    }

    public void emptyCart() {
        System.out.println("is there a remove button "+getCartItemCount());

        while (!removeButtons.isEmpty()) {
            // Always click first button
            removeProductByIndex(0);

            // Wait until number of buttons decreases
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.numberOfElementsToBeLessThan(
                            By.xpath("//button[contains(@class,'remove')]"),
                            removeButtons.size()
                    ));
        }
    }

    public boolean isCartEmptyMessageDisplayed() {
        WaitManager.waitForElementToBeVisible(driver,emptyCartMessage,10);
        return emptyCartMessage.isDisplayed();
    }

    public String getEmptyCartMessage()
    {
        WaitManager.waitForElementToBeVisible(driver,emptyCartMessage,10);
        return emptyCartMessage.getText().trim();
    }


}
