package Controller;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BiwengerCalculator {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\drivers\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();


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

        WebElement elemento = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"users\"]/league-detail-user-list/user-list/adv-list/div[2]/div/div/i[2]")));
        elemento.click();


        WebElement tablaDatosEquipos = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody[_ngcontent-ng-c683283966]")));


        // Crea una lista para almacenar objetos Team
        List<Equipo> equipos = new ArrayList<>();


        // Reintentar la extracción hasta un máximo de 5 veces
        for (int i = 0; i < 5; i++) {
            try {
                // Encuentra todas las filas dentro del tbody
                List<WebElement> rows = tablaDatosEquipos.findElements(By.tagName("tr"));

                // Itera sobre cada fila
                for (WebElement row : rows) {
                    // Extrae los datos de cada columna (td)
                    String name = row.findElement(By.cssSelector(".user-name a")).getText();
                    String points = row.findElement(By.cssSelector("td[aria-label*='puntos']")).getText();
                    String value = row.findElement(By.cssSelector("td[aria-label*='Valor de Equipo']")).getText();
                    String players = row.findElement(By.cssSelector("td[aria-label*='Jugadores']")).getText();

                    // Crea un objeto Equipo y añádelo a la lista
                    Equipo equipo = new Equipo(name, points, value, players);
                    equipos.add(equipo);
                }

                break; // Salir del bucle si el procesamiento fue exitoso
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                // Espera y vuelve a intentar si se produce un StaleElementReferenceException
                System.out.println("StaleElementReferenceException: intentando de nuevo...");
                tablaDatosEquipos = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody[_ngcontent-ng-c683283966]")));
            }
        }


        // Imprime los equipos
        for (Equipo equipo : equipos) {
            System.out.println(equipo.toString());
        }

        driver.navigate().to("https://biwenger.as.com/");

        WebElement body = driver.findElement(By.tagName("body"));


        boolean reinicio = true;
        while (reinicio) {
            try {
                WebElement reinicioLiga = driver.findElement(By.cssSelector("div.content.leagueReset"));
                if (reinicioLiga.isDisplayed()) {
                    reinicio = false;
                }
            } catch (Exception e) {
                body.sendKeys(Keys.PAGE_DOWN);
            }
        }

        //Busca la cantidad inicial de dinero de la liga en el tablón.
        WebElement reinicioLiga = driver.findElement(By.cssSelector("div.content.leagueReset"));
        String dineroInicial = reinicioLiga.getText();


        // Expresión regular para encontrar uno o más dígitos

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(dineroInicial);

        while(matcher.find()){

        String cantidadInicial = matcher.group();
        int cantInicialEntero = 1000000*(Integer.parseInt(cantidadInicial));
        System.out.println(cantInicialEntero);

        }

    }
}


