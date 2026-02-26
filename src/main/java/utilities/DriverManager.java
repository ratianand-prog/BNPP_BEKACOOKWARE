package utilities;

//import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {

    private static WebDriver driver;

    public static WebDriver initDriver() {
        System.setProperty("webdriver.chrome.driver", ".idea/Executables/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    public static WebDriver getDriver() {
        return driver;}

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}