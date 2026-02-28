package ReUsableComponent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Reusablecomponent {
    WebDriver driver ;
    public Reusablecomponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements( driver, this);
    }
    @FindBy(css = "[class='nav-link btn btn-primary']") WebElement checkoutbutton;
    public void waitForElement (By findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

    }
    public void clickCartButton(){
        checkoutbutton.click();


    }
}
