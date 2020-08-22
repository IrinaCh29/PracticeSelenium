import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertEquals;

public class Lesson12Test {

  WebDriver driver = null;

  @BeforeMethod
  public void setUp() {
    WebDriverFactory.createInstance("Chrome");
    driver = WebDriverFactory.getDriver();
  }

  @Test
  public void createIssueTest() {
    driver.get("https://jira.hillel.it/secure/Dashboard.jspa");
    driver.findElement(By.id("login-form-username")).sendKeys("IrinaChub");
    driver.findElement(By.id("login-form-password")).sendKeys("IrinaChub");
    driver.findElement(By.id("login")).click();
    // !!!Creating sn object WebDriverWait wait = new WebDriverWait  is for current class
    //Explicit Wait for element to appear
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
    assertEquals(wait.until(presenceOfElementLocated(By.xpath("//*[contains(text(),'Activity Stream']"))).isDisplayed(), true);
//    boolean elementIsPresent = wait.until(presenceOfElementLocated(By.xpath("//*[contains(text(),'Activity Stream')]"))).isDisplayed();
//    assertEquals(elementIsPresent, true);

    driver.findElement(By.id("create_link")).click();
    assertEquals(wait.until(presenceOfElementLocated(By.id("issuetype-single-select"))).isDisplayed(), true);
    driver.findElement(By.id("summary")).sendKeys("Test Summary");
  }













  //from the lesson
  @Test
  public void createIssue() {
    driver.get("https://jira.hillel.it/secure/Dashboard.jspa");
    driver.findElement(By.id("login-form-username")).sendKeys("IrinaChub");
    driver.findElement(By.id("login-form-password")).sendKeys("IrinaChub");
    driver.findElement(By.id("login")).click();

    //Explicit Wait for element to appear
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30).getSeconds());
    boolean elementIsPresent = wait.until(presenceOfElementLocated(By.id("create_link"))).isEnabled();
    assertEquals(elementIsPresent, true);

    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    driver.findElement(By.id("create_link")).click();


    wait.until(presenceOfElementLocated(By.id("project-field"))).isDisplayed();
    driver.findElement(By.id("project-field")).clear();
    driver.findElement(By.id("project-field")).sendKeys("Webinar (WEBINAR)");
    driver.findElement(By.id("project-field")).sendKeys(Keys.TAB);

    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    driver.findElement(By.id("issuetype-field")).clear();
    driver.findElement(By.id("issuetype-field")).sendKeys("Task");
    driver.findElement(By.id("issuetype-field")).sendKeys(Keys.TAB);

    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    driver.findElement(By.id("summary")).sendKeys("summary");
    driver.findElement(By.id("reporter-field")).clear();
    driver.findElement(By.id("reporter-field")).sendKeys("IrinaChub");
    driver.findElement(By.id("reporter-field")).sendKeys(Keys.TAB);

    driver.findElement(By.id("create-issue-submit")).click();


    //Explicit Wait for element to appear
    //wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
    boolean popUpIsPresent = wait.until(presenceOfElementLocated(By.className("aui-message-success"))).isDisplayed();
    assertEquals(popUpIsPresent, true);

    //aui-flag-container
  }

  @AfterMethod
  public void tearDown() {
    driver.quit();
  }
}