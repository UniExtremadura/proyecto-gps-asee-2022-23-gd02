package es.unex.fulltank;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TestSeccionGruposInteresWeb {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  JavascriptExecutor js;

  @Before
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\34640\\Downloads\\chromedriver_win32_v108\\chromedriver.exe");
    driver = new ChromeDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    js = (JavascriptExecutor) driver;
  }

  @Test
  public void testCU10() throws Exception {
    driver.get("https://uniextremadura.github.io/proyecto-gps-asee-2022-23-gd02/");

    driver.manage().window().maximize();
    js.executeScript("window.scrollTo(0, 3000)");
    Thread.sleep(2000);

    assertEquals("GRUPOS DE INTERES", driver.findElement(By.xpath("//div[@id='Grupos_de_interes']/div/h2")).getText());
    try {
      assertEquals("Esta aplicacion está destinada principalmente a todas aquellas personas que dispongan de un vehículo o puedan conducir uno, debido a que se pretende ayudar al usuario a escoger la gasolinera que más se ajuste a sus necesidades.", driver.findElement(By.xpath("//div[@id='Grupos_de_interes']/div/p")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Otro grupo de interés son aquellas personas sin carnet que conduzcan automoviles o los copilotos de los vehículos para poder informar al piloto.", driver.findElement(By.xpath("//div[@id='Grupos_de_interes']/div/p[2]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Todos los grupos de interés que utilizan está aplicación se encuentran atraidos por el mismo objetivo, que es el de poder ahorrar dinero a la hora de repostar combustibles en la gasolinera más cercana posible a su ubicación actual.", driver.findElement(By.xpath("//div[@id='Grupos_de_interes']/div/p[3]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
