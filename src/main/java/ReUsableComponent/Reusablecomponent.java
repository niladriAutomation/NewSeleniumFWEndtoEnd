package ReUsableComponent;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
    public void waitForPageToLoad() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        wait.until(webDriver ->
                ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete")
        );
    }
    public void waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void clickCartButton() throws InterruptedException {
        waitForPageToLoad();
        Thread.sleep(8000);
        waitForElementToBeClickable(checkoutbutton);

        try{
            checkoutbutton.click();
        }
        catch(Exception e){

            ((JavascriptExecutor)driver)
                    .executeScript("arguments[0].click();", checkoutbutton);

        }
    }
}
