import ReUsableComponent.Reusablecomponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends Reusablecomponent {
    WebDriver driver ;
    public  LandingPage(WebDriver driver){
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver , this);

    }

    @FindBy(id = "username")
    WebElement username ;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(id ="terms")
    WebElement termcheckbox;

    @FindBy (id = "signInBtn")
    WebElement SigninBUtton ;

    public ProductPage LoginToApplication(String email , String pwd){
        username.sendKeys(email);
        password.sendKeys(pwd);
        termcheckbox.click();
        SigninBUtton.click();
        return new ProductPage(driver);


    }
    public  void  goTo(String URL ){
        driver.get(String.valueOf(URL));
    }

}
