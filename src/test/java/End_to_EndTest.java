
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class End_to_EndTest extends BaseTest {

    @Test(dataProvider = "getData")
    public void  submitOrder(HashMap<String, String> input) throws IOException {
        ProductPage pg = lp.LoginToApplication(input.get("email"), input.get("paswword"));
        ExtentReporterNG.logStep("User logged in", driver);
        String logintitle = driver.getTitle();
        System.out.println("Home Page title is " + logintitle);
        pg.getProductByNameandClickCart(input.get("Productname"));
        ExtentReporterNG.logStep("Product added to cart", driver);
        pg.clickCartButton();
        Cartpage cp = new Cartpage(driver);
        Boolean value = cp.VerifyProductName(input.get("Productname"));
        Assert.assertTrue(value);
        ExtentReporterNG.logStep("Product reflected in CART verified", driver);
        CheckoutPage checout = cp.clickCheckoutButton();
        ExtentReporterNG.logStep("CheckoutButtonClicked", driver);
        String successtext = checout.EnterDetailsClickCheckoutandValidateSuccess("India");
        Assert.assertEquals(successtext, "Success!");
        ExtentReporterNG.logStep("Order Placed", driver);

    }


    @DataProvider
    public Object[][] getData() throws IOException {
        DataReadder DR = new DataReadder();
        List<HashMap<String , String>> data = DR.getJSONData(System.getProperty("user.dir")+"//src//test//TestData.JSON");
        return new Object[][]{{data.get(0)},{data.get(1)}};


    }

/*


I am using selenium with Java suggest me the entire architecture pipeline so from code repository to execution , reporting , mail notification in AWS service but without jenkins
 */


}
