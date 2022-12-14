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

public class TestSeccionIntegrantesGrupoWeb {
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
  public void testCU08() throws Exception {
    driver.get("https://uniextremadura.github.io/proyecto-gps-asee-2022-23-gd02/");

    driver.manage().window().maximize();
    js.executeScript("window.scrollTo(0, 3600)");
    Thread.sleep(2000);

    driver.findElement(By.xpath("//img[@alt='Icono de Github']")).click();
    driver.get("https://github.com/RafaCMur");
    driver.get("https://uniextremadura.github.io/proyecto-gps-asee-2022-23-gd02/");

    js.executeScript("window.scrollTo(0, 3600)");
    Thread.sleep(2000);

    driver.findElement(By.xpath("//div[@id='integrantes_id']/div/ul/li[2]/a/img")).click();
    driver.get("https://github.com/alcamposc");
    driver.get("https://uniextremadura.github.io/proyecto-gps-asee-2022-23-gd02/");

    js.executeScript("window.scrollTo(0, 3600)");
    Thread.sleep(2000);

    driver.findElement(By.xpath("//div[@id='integrantes_id']/div/ul/li[3]/a/img")).click();
    driver.get("https://github.com/ArantxaRodriguez");
    driver.get("https://uniextremadura.github.io/proyecto-gps-asee-2022-23-gd02/");

    js.executeScript("window.scrollTo(0, 3600)");
    Thread.sleep(2000);

    driver.findElement(By.xpath("//div[@id='integrantes_id']/div/ul/li[4]/a/img")).click();
    driver.get("https://github.com/Adrianss67");
    driver.get("https://uniextremadura.github.io/proyecto-gps-asee-2022-23-gd02/");

    js.executeScript("window.scrollTo(0, 3600)");
    Thread.sleep(2000);

    assertEquals("FullTank", driver.getTitle());
    assertEquals("INTEGRANTES DEL GRUPO", driver.findElement(By.xpath("//div[@id='integrantes_id']/div/h2")).getText());
    assertEquals("FullTank ha sido desarrollada por los siguientes 4 integrantes, de los cuales tres de ellos cursan ASEE y GPS, el otro miembro solo cursa GPS:", driver.findElement(By.xpath("//div[@id='integrantes_id']/div/p")).getText());
    try {
      assertEquals("Rafael Cabanillas Murillo: cursa GPS y ASEE.", driver.findElement(By.xpath("//div[@id='integrantes_id']/div/ul/li")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Alberto Manuel Campos Clemente: cursa GPS y ASEE.", driver.findElement(By.xpath("//div[@id='integrantes_id']/div/ul/li[2]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Aranzazu Rodriguez Cabanillas: cursa GPS y ASEE.", driver.findElement(By.xpath("//div[@id='integrantes_id']/div/ul/li[3]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Adrián Sánchez Sánchez: cursa GPS.", driver.findElement(By.xpath("//div[@id='integrantes_id']/div/ul/li[4]")).getText());
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
