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

public class TestSeccionPantallasPrincipalesWeb {
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
  public void testCU09() throws Exception {
    driver.get("https://uniextremadura.github.io/proyecto-gps-asee-2022-23-gd02/");

    driver.manage().window().maximize();
    js.executeScript("window.scrollTo(0, 700)");

    Thread.sleep(2000);

    assertEquals("SOBRE FULLTANK", driver.findElement(By.xpath("//div[@id='about']/div/h2")).getText());

    js.executeScript("window.scrollTo(700, 1000)");

    assertEquals("El motivo de desarrollo de esta página web es poder mostrar al usuario información sobre la aplicación FullTank, la cual ha sido desarrollada por 4 alumnos del Grado de Ing. Informática en Ing. del Software.", driver.findElement(By.xpath("//div[@id='about']/div/p")).getText());
    assertEquals("FullTank proporciona a sus usuario una mayor información de las gasolineras a la hora de realizar sus repostajes, por lo que ayuda a tomar deciones con mayor rapidez al tener toda la información conjunta en esta aplicación. Además, permite que el usuario ahorre dinero al conocer los precios de sus gasolineras cercanas.", driver.findElement(By.xpath("//div[@id='about']/div/p[2]")).getText());

    assertEquals("Esta aplicación dispone de una pantalla principal en la que se muestra un mapa con la ubicación actual del usuario, en el cual se puede observar un conjunto de macadores rojos que corresponden a las ubicaciones de las diversas estaciones de repostaje.", driver.findElement(By.xpath("//div[@id='about']/div/table/tbody/tr/td[2]/p")).getText());
    assertEquals("Una vez se hayan cargado las ubicaciones de las gasolineras solo se mostrarán aquellas que se encuentran en un radio de 111km de la ubicación del usuario.", driver.findElement(By.xpath("//div[@id='about']/div/table/tbody/tr/td[2]/p[2]")).getText());
    assertEquals("Es importante mencionar que en caso de que no se acepten los persimos de localización que se solicitan al inicio de la aplicación se obtendran las estaciones cercanas a una ubicación predeterminada, concretamente la ubicación es en Cáceres.", driver.findElement(By.xpath("//div[@id='about']/div/table/tbody/tr/td[2]/p[3]")).getText());

    js.executeScript("window.scrollTo(1000, 1300)");

    assertEquals("Mediante el acceso a la información de una determinada gasolinera se observa la siguiente información:", driver.findElement(By.xpath("//div[@id='about']/div/table/tbody/tr[2]/td/p")).getText());
    assertEquals("Además, usando los botones de la parte inferior el usuario puede llevar a cabo las siguientes funcionalidades:", driver.findElement(By.xpath("//div[@id='about']/div/table/tbody/tr[2]/td/p[2]")).getText());

    js.executeScript("window.scrollTo(1300, 1600)");

    try {
      assertEquals("Para llevar a cabo con mayor rapidez la busqueda de aquellas gasolineras que dispongan de un combustible concreto se ha generado un filtro.", driver.findElement(By.xpath("//div[@id='about']/div/table/tbody/tr[3]/td[2]/p")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Cuando se filtre la información el usuario se puede encontrar con dos caso:", driver.findElement(By.xpath("//div[@id='about']/div/table/tbody/tr[3]/td[2]/p[2]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    js.executeScript("window.scrollTo(1600, 1900)");
    try {
      assertEquals("Puede no haber ninguna gasolinera con ese tipo de combusitble por lo que se muestra un mensaje informado al usuario.", driver.findElement(By.xpath("//div[@id='about']/div/table/tbody/tr[3]/td[2]/ul/li")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("En caso de existir alguna estación de repostaje con ese tipo de combustible se mostrará una lista con la ubicación de todas las gasolineras.", driver.findElement(By.xpath("//div[@id='about']/div/table/tbody/tr[3]/td[2]/ul/li[2]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }

    js.executeScript("window.scrollTo(1900, 2200)");

    assertEquals("Otra de las pantallas características de la aplicación es la del historial de repostaje, donde se almacena la información de todos los repostajes que ha realizado el usuario y los ha notificado.", driver.findElement(By.xpath("//div[@id='about']/div/table/tbody/tr[4]/td/p")).getText());
    assertEquals("En cada uno de los repostajes se muestra la fecha en la que realizó, la ubicación y el rótulo de la gasolinera, los litros que se han repostado y el precio total del repostaje.", driver.findElement(By.xpath("//div[@id='about']/div/table/tbody/tr[4]/td/p[2]")).getText());
    assertEquals("Esta ventana permite al usuario conocer si el pprecio del combustible ha variado con respecto al último repostaje.", driver.findElement(By.xpath("//div[@id='about']/div/table/tbody/tr[4]/td/p[3]")).getText());
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
