import ReUsableComponent.Reusablecomponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductPage extends Reusablecomponent {

    WebDriver driver;

    public ProductPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    By Productsby = By.cssSelector(".col-lg-3.col-md-6.mb-3");
    By ProductnameText = By.xpath(".//h4[@class='card-title']/a");
    By Productcart = By.cssSelector(".btn.btn-info");

    public List<WebElement> getProductList() {
        waitForElement(Productsby);
        return driver.findElements(Productsby);
    }

    public void getProductByNameandClickCart(String pname) {

        WebElement product = getProductList().stream()
                .filter(p -> p.findElement(ProductnameText)
                        .getText()
                        .equalsIgnoreCase(pname))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("Product not found: " + pname));

        product.findElement(Productcart).click();
    }

}