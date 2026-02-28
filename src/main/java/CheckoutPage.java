import ReUsableComponent.Reusablecomponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends Reusablecomponent {
    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }
    @FindBy (css = "#country") WebElement countryfield;
    @FindBy (css = ".suggestions a") WebElement CountrySuggestion;
    @FindBy (xpath = "//label[@for='checkbox2']") WebElement checkbox;
    @FindBy (xpath = "//input[@type='submit']") WebElement Submit;
    @FindBy (xpath = "//div[@class='alert alert-success alert-dismissible']/strong") WebElement Successmsg;





    public String EnterDetailsClickCheckoutandValidateSuccess(String countryname){
        countryfield.sendKeys(countryname);
        CountrySuggestion.click();
        checkbox.click();
        Submit.click();
        String text = Successmsg.getText().toString();
        return text;



    }

}
