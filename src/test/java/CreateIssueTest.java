import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.WebDriverFactory;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CreateIssueTest {

  WebDriver driver = null;
  LoginPage loginPage = null;
  HomePage homePage = null;

  @BeforeMethod
  public void setUp() {
    WebDriverFactory.createInstance("Chrome");
    driver = WebDriverFactory.getDriver();
    loginPage = new LoginPage(driver);
    homePage = new HomePage(driver);
  }

  @Test
  public void createIssueTest() {
    loginPage.navigateTo();
    loginPage.enterUserName("IrinaChub");
    loginPage.enterPassword("IrinaChub");
    loginPage.clickLogin();

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

    wait.until(presenceOfElementLocated(By.xpath("//*[@id='description-wiki-edit']//child::a[text()='Text']"))).isEnabled();
    driver.findElement(By.xpath("//*[@id='description-wiki-edit']//child::a[text()='Text']")).click();
    driver.findElement(By.id("description")).sendKeys("some text of description");

    driver.findElement(By.id("create-issue-submit")).click();

    boolean popUpIsPresent = wait.until(presenceOfElementLocated(By.className("aui-message-success"))).isDisplayed();
    assertEquals(popUpIsPresent, true);

    WebElement projectNameIsPresent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("aui-message-success")));
    wait.until(ExpectedConditions.textToBePresentInElement(projectNameIsPresent, "WEBINAR"));
  }

  @Test
  public void addCommentToTicketTest() {
    loginPage.navigateTo();
    loginPage.enterUserName("IrinaChub");
    loginPage.enterPassword("IrinaChub");
    loginPage.clickLogin();

    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    assertTrue(driver.findElement(By.id("header-details-user-fullname")).isDisplayed());

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30).getSeconds());
    boolean elementIsPresent = wait.until(presenceOfElementLocated(By.id("quickSearchInput"))).isEnabled();
    assertEquals(elementIsPresent, true);

    driver.findElement(By.id("quickSearchInput")).sendKeys("WEBINAR-12467");
    driver.findElement(By.id("quickSearchInput")).sendKeys(Keys.ENTER);

    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    assertTrue(driver.findElement(By.xpath("//*[@id='stalker']//child::a[@data-issue-key='WEBINAR-12467']")).isDisplayed());

    wait.until(presenceOfElementLocated(By.xpath("//*[@id='issue-tabs']//child::*[@id='comment-tabpanel']"))).isEnabled();
    driver.findElement(By.xpath("//*[@id='issue-tabs']//child::*[@id='comment-tabpanel']")).click();

    wait.until(presenceOfElementLocated(By.id("footer-comment-button"))).isEnabled();
    driver.findElement(By.id(("footer-comment-button"))).click();
    driver.findElement(By.id("comment")).sendKeys("some comments");
    driver.findElement(By.id("issue-comment-add-submit")).click();

    WebElement addCommentTest = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(("//*[@id='issue_actions_container']//child::*[@class='action-body flooded']"))));
    wait.until(ExpectedConditions.textToBePresentInElement(addCommentTest, "some comments"));

    WebElement popUpDeleteCommentTest = driver.findElement(By.xpath("//*[@id='issue_actions_container']//child::*[@class='action-links']//child::*[@class='delete-comment issue-comment-action']"));
    popUpDeleteCommentTest.click();

    boolean deleteDialogIsPresent = wait.until(presenceOfElementLocated(By.xpath("//*[@id='delete-comment-dialog']//child::*[@id='comment-delete-submit']"))).isEnabled();
    assertEquals(deleteDialogIsPresent, true);
    driver.findElement(By.xpath("//*[@id='delete-comment-dialog']//child::*[@id='comment-delete-submit']")).click();

    WebElement deletedCommentTest = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(("//*[@id='issue_actions_container']//child::*[contains(text(), 'There are no comments yet on this issue.')]"))));
    wait.until(ExpectedConditions.textToBePresentInElement(deletedCommentTest, "There are no comments yet on this issue."));
  }


  @AfterMethod
  public void tearDown() {
    driver.quit();
  }
}
