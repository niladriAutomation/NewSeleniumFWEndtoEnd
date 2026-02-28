
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public LandingPage lp;


    public WebDriver initializeDriver () throws IOException {
        Properties prop = new Properties();
        FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"//Global.properties");
        prop.load(file);
        String browsername = prop.getProperty("browser");
        if (browsername.equalsIgnoreCase("chrome"))driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }
    public static String getScreenshot(String testName, WebDriver driver)
            throws IOException {

        File src = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        // 🔥 Get timestamp report folder
        String reportFolder = ExtentReporterNG.getReportPath();

        // Create screenshots folder INSIDE timestamp folder
        String screenshotDir = reportFolder + "/screenshots";

        File dir = new File(screenshotDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Absolute path to save file
        String absolutePath =
                screenshotDir + "/" + testName + ".png";

        FileUtils.copyFile(src, new File(absolutePath));

        // ✅ RETURN RELATIVE PATH FROM index.html LOCATION
        return "screenshots/" + testName + ".png";
    }
    @BeforeMethod
    public void launchApplication() throws IOException {
        driver = initializeDriver();
        lp = new LandingPage(driver);
        lp.goTo("https://rahulshettyacademy.com/loginpagePractise/");

    }
    @AfterMethod
    public void tearDown(){
        driver.close();
    }

}
