package Controller;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class ObtencionDatos {


    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    public void login() {

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



    }


    public void obtenerEquipos() {
        WebElement botonEquipos = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("i.icon.icon-prize.ng-star-inserted")));
        botonEquipos.click();

        //Comprueba y cierra el anuncio que intercepta el click.

        try {
            // Esperar hasta que el iframe del anuncio desaparezca
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("google_ads_iframe_/7811748/biwenger_mob/league_0__container__")));
        } catch (TimeoutException e) {
            System.out.println("El iframe del anuncio no desapareció a tiempo.");
        }

        WebElement botonMasDatos = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"users\"]/league-detail-user-list/user-list/adv-list/div[2]/div/div/i[2]")));
        botonMasDatos.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("ng-component.ad.rectangle")));
        WebElement tbody = driver.findElement(By.cssSelector("tbody[_ngcontent-ng-c683283966]"));

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
        }


    }

    public void scrollInicio() {
            WebElement body = driver.findElement(By.tagName("body"));

            boolean reinicio = true;
            while (reinicio) {
                try {
                    WebElement reinicioLiga = driver.findElement(By.xpath("/html/body/app-root/main/div/ng-component/div/div[2]/ng-component/league-board-post[139]/h3"));
                    if (reinicioLiga.isDisplayed()) {
                        reinicio = false;
                    }
                } catch (Exception e) {
                    body.sendKeys(Keys.PAGE_DOWN);
                }
            }
        }
    }
