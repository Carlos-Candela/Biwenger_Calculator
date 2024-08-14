package Controller;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;



public class BiwengerCalculator {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\drivers\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://biwenger.as.com/");

        driver.findElement(By.id("didomi-notice-agree-button")).click();
        driver.findElement(By.cssSelector("a.btn.primary.xl")).click();
        WebElement yaTengoCuenta = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Ya tengo cuenta")));
        yaTengoCuenta.click();

        Credentials credentials = new Credentials();

        driver.findElement(By.name("email")).sendKeys(credentials.email);
        driver.findElement(By.name("password")).sendKeys(credentials.password);

        WebElement iniciarSesion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.btn.squared")));
        iniciarSesion.click();


        WebElement body = driver.findElement(By.tagName("body"));

        boolean reinicio=true;
        while (reinicio) {
            try {
                WebElement reinicioLiga = driver.findElement(By.xpath("/html/body/app-root/main/div/ng-component/div/div[2]/ng-component/league-board-post[139]/h3"));
                if (reinicioLiga.isDisplayed()) {
                    reinicio=false;
                }
            } catch (Exception e) {
                body.sendKeys(Keys.PAGE_DOWN);
            }
        }







    }
}


