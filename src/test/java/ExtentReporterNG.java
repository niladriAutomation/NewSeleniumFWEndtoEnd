
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReporterNG {

    private static ExtentReports extent;
    private static String reportPath;

    // ONE ThreadLocal for whole framework
    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    /* ===================== EXTENT CONFIG ===================== */
    public static ExtentReports getReportObject() {

        if (extent == null) {

            // ✅ Create timestamp
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss")
                    .format(new Date());

            // ✅ Create folder path
            reportPath = System.getProperty("user.dir")
                    + "/reports/" + timestamp;

            // ✅ Create directory
            new File(reportPath).mkdirs();

            String fullPath = reportPath + "/index.html";

            ExtentSparkReporter reporter =
                    new ExtentSparkReporter(fullPath);

            reporter.config().setTheme(Theme.DARK);
            reporter.config().setReportName("🚀 UI Automation Execution Report");
            reporter.config().setDocumentTitle("🧪 Selenium Test Results");

            extent = new ExtentReports();
            extent.attachReporter(reporter);

            extent.setSystemInfo("Project", "Selenium FW Design");
            extent.setSystemInfo("QA Engineer", "Niladri Das");
            extent.setSystemInfo("Environment", "QA");
        }

        return extent;

    }
    public static String getReportPath() {
        return reportPath;
    }

    /* ===================== STEP LOGGER ===================== */
    public static void logStep(String stepDescription, WebDriver driver) {
        try {

            // 🔐 SAFETY: initialize if listener thread not ready
            if (extentTest.get() == null) {
                ExtentTest test =
                        ExtentReporterNG.getReportObject()
                                .createTest(
                                        org.testng.Reporter
                                                .getCurrentTestResult()
                                                .getMethod()
                                                .getMethodName()
                                );
                extentTest.set(test);
            }

            String path = BaseTest.getScreenshot(
                    stepDescription.replace(" ", "_"),
                    driver
            );

            extentTest.get().log(Status.INFO, stepDescription);
            extentTest.get().addScreenCaptureFromPath(path, stepDescription);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}