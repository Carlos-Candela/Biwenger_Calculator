package Controller;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BiwengerCalculator {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\drivers\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-popup-blocking");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-blink-features=AutomationControlled");

        WebDriver driver = new ChromeDriver(options);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get("https://biwenger.as.com/");

        driver.findElement(By.id("didomi-notice-agree-button")).click();
        driver.findElement(By.cssSelector("a.btn.primary.xl")).click();
        WebElement yaTengoCuenta = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Ya tengo cuenta")));
        yaTengoCuenta.click();

        Credentials credentials = new Credentials();
        credentials.obtenerLogin();
        driver.findElement(By.name("email")).sendKeys(credentials.getEmail());
        driver.findElement(By.name("password")).sendKeys(credentials.getPassword());

        WebElement iniciarSesion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.btn.squared")));
        iniciarSesion.click();

        WebElement botonEquipos = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("i.icon.icon-prize.ng-star-inserted")));
        botonEquipos.click();
        WebElement anuncio = driver.findElement(By.xpath("/html/body/app-root/main/div/ng-component/ads"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none';", anuncio);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loader-wrapper")));
        WebElement botonMasDatos = driver.findElement(By.xpath("//*[@id=\"users\"]/league-detail-user-list/user-list/adv-list/div[2]/div/div/i[2]"));
        botonMasDatos.click();

        WebElement tablaDatosEquipos = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody")));
        WebElement tbody = driver.findElement(By.cssSelector("tbody"));

        // Encuentra todas las filas (tr) dentro del tbody
        List<WebElement> rows = tbody.findElements(By.tagName("tr"));

        // Crea una lista para almacenar objetos Team
        List<Equipo> equipos = new ArrayList<>();

        // Itera sobre cada fila
        for (WebElement row : rows) {
            // Extrae los datos de cada columna (td)
            String position = row.findElement(By.cssSelector("user-position")).getText();
            String name = row.findElement(By.cssSelector(".user-name a")).getText();
            String points = row.findElement(By.cssSelector("td[aria-label*='puntos'] strong")).getText();
            String value = row.findElement(By.cssSelector("td[aria-label*='Valor de Equipo']")).getText();
            String players = row.findElement(By.cssSelector("td[aria-label*='Jugadores']")).getText();
            String lastUpdate = row.findElement(By.cssSelector("time-relative")).getText();

            // Crea un objeto Team y agrégalo a la lista
            equipos.add(new Equipo(position, name, points, value, players, lastUpdate));
        }

        // Imprime los equipos
        for (Equipo equipo : equipos) {
            System.out.println(equipo.toString());


        /*
        WebElement body = driver.findElement(By.tagName("body"));

            boolean reinicio = true;
            while (reinicio) {
                try {
                    WebElement reinicioLiga = driver.findElement(By.cssSelector("h3[_ngcontent-ng-c1543853168]"));
                    if (reinicioLiga.isDisplayed()) {
                        reinicio = false;
                    }
                } catch (Exception e) {
                    body.sendKeys(Keys.PAGE_DOWN);
                }



    */


        }
    }
}


