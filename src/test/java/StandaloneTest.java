
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;


public class StandaloneTest {
    public static  void main (String [] args){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/loginpagePractise/");

//        Login
        driver.findElement(By.id("username")).sendKeys("rahulshettyacademy");
        driver.findElement(By.id("password")).sendKeys("Learning@830$3mK2");
        driver.findElement(By.id("terms")).click();
        driver.findElement(By.id("signInBtn")).click();
        String logintitle = driver.getTitle();
        System.out.println("Home Page title is "+logintitle);
        List <WebElement> products= driver.findElements(By.cssSelector("[class='col-lg-3 col-md-6 mb-3']"));
        products.stream()
                .filter(product -> product.findElement(By.xpath("//h4[@class='card-title']/a"))
                        .getText()
                        .equalsIgnoreCase("iphone X"))
                .findFirst()
                .ifPresent(product ->
                        product.findElement(By.cssSelector("[class='btn btn-info']")).click()
                );
        driver.findElement(By.cssSelector("[class='nav-link btn btn-primary']")).click();
        List <WebElement> carts = driver.findElements(By.xpath("//h4[@class='media-heading']/a"));
        Boolean val = carts.stream().anyMatch(prod->prod.getText().equalsIgnoreCase("iphone X"));
        Assert.assertTrue(val);
        driver.findElement(By.cssSelector(".btn.btn-success")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.findElement(By.cssSelector("#country")).sendKeys("India");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".suggestions a"))).click();
        driver.findElement(By.xpath("//label[@for='checkbox2']")).click();
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-success alert-dismissible']/strong"))).getText().toString();
        Assert.assertEquals(text,"Success!");






    }

}
