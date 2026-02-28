import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.IOException;

public class Listener implements ITestListener {

    ExtentReports extent = ExtentReporterNG.getReportObject();
    WebDriver driver;

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("🔥 Listener triggered for: "
                + result.getMethod().getMethodName());
        ExtentTest test =
                extent.createTest(result.getMethod().getMethodName());
        ExtentReporterNG.extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReporterNG.extentTest.get()
                .log(Status.PASS, "Test Case Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentReporterNG.extentTest.get()
                .fail(result.getThrowable());
        driver = getDriverFromTest(result);

        try {
            String filePath =
                    BaseTest.getScreenshot(result.getMethod().getMethodName(), driver);

            ExtentReporterNG.extentTest.get()
                    .addScreenCaptureFromPath(filePath,
                            result.getMethod().getMethodName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReporterNG.extentTest.get()
                .skip(result.getThrowable());

        driver = getDriverFromTest(result);

        try {
            String filePath =
                    BaseTest.getScreenshot(result.getMethod().getMethodName(), driver);

            ExtentReporterNG.extentTest.get()
                    .addScreenCaptureFromPath(filePath,
                            result.getMethod().getMethodName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReporterNG.getReportObject().flush();

    }

    // 🔹 Utility method
    private WebDriver getDriverFromTest(ITestResult result) {
        try {
            return (WebDriver) result.getTestClass()
                    .getRealClass()
                    .getField("driver")
                    .get(result.getInstance());
        } catch (Exception e) {
            throw new RuntimeException("Unable to fetch driver", e);
        }
    }
}