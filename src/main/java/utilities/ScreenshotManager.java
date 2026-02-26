package utilities;

import org.openqa.selenium.*;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

    public class ScreenshotManager {

        public static String captureScreenshot(WebDriver driver, String testName) {

            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);

            String path = System.getProperty("user.dir")
                    + "/screenshots/" + testName + ".png";

            File destination = new File(path);

            try {
                FileUtils.copyFile(source, destination);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return path;
        }
    }
