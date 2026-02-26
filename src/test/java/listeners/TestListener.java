package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.*;
import utilities.*;

public class TestListener implements ITestListener {

    ExtentReports extent = ExtentReportManager.getInstance();
    ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.fail(result.getThrowable());

        String path = ScreenshotManager.captureScreenshot(
                DriverManager.getDriver(),
                result.getMethod().getMethodName()
        );

        test.addScreenCaptureFromPath(path);
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
        extent.flush();
    }
}