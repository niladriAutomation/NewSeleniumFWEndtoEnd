import ReUsableComponent.Reusablecomponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Cartpage extends Reusablecomponent {
    WebDriver driver;

    public Cartpage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }
    @FindBy (xpath = "//h4[@class='media-heading']/a") private List <WebElement> cartproducts;
    @FindBy (css = ".btn.btn-success")  WebElement checkoutButton;


    public Boolean VerifyProductName(String Product){
        Boolean val = cartproducts.stream().anyMatch(prod->prod.getText().equalsIgnoreCase(Product));
        System.out.println("Value is  = " + val);
        return val;


    }
    public CheckoutPage clickCheckoutButton(){
        checkoutButton.click();
        return new  CheckoutPage(driver);

    }

}
