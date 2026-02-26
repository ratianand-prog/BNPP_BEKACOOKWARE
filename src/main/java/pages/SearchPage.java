package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.*;

import java.time.Duration;


public class SearchPage extends HomePage{
    private WebDriver driver;

    public SearchPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath="//div[@class='search-general']//div[@class='search-results-wrapper'][1]//h2/a")
    private WebElement firstModel;

    @FindBy(xpath="//h1")
    private WebElement seacrhPageH1Title;

    @FindBy(xpath="//div[@class='container']/nav/span[2]")
    private WebElement seacrhPageMessage;

    public String getFirstModel() {
        return firstModel.getText().trim();
    }
    public String getSearchPageMessage() {
        return seacrhPageMessage.getText().trim();
    }

    public String getSeacrhPageH1Title() {
        return seacrhPageH1Title.getText().trim();
    }

}
