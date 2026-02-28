
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class End_End_Error_Test extends BaseTest {

    @Test(dataProvider = "getData",retryAnalyzer = Retry.class)
    public void  submitOrder(HashMap<String, String> input) throws IOException {
        ProductPage pg = lp.LoginToApplication(input.get("email"), input.get("paswword"));
        ExtentReporterNG.logStep("User logged in", driver);
        String logintitle = driver.getTitle();
        System.out.println("Home Page title is " + logintitle);
        pg.getProductByNameandClickCart(input.get("Productname"));
        ExtentReporterNG.logStep("Product added to cart", driver);
        pg.clickCartButton();
        Cartpage cp = new Cartpage(driver);
        Boolean value = cp.VerifyProductName(input.get("invalidproduct"));
        ExtentReporterNG.logStep("Product reflected in CART verified", driver);
        Assert.assertTrue(value);
        CheckoutPage checout = cp.clickCheckoutButton();
        ExtentReporterNG.logStep("CheckoutButtonClicked", driver);
        String successtext = checout.EnterDetailsClickCheckoutandValidateSuccess("India");
        Assert.assertEquals(successtext, "Success!");
        ExtentReporterNG.logStep("Order Placed", driver);
    }
    @DataProvider
    public Object[][] getData() throws IOException {
        DataReadder DR = new DataReadder();
        List<HashMap<String, String>> data = DR.getJSONData(System.getProperty("user.dir") + "//src//test//TestData.JSON");
        return new Object[][]{{data.get(2)}};


    }
}
