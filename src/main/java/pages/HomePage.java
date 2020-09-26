package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class HomePage {
  private WebDriver driver = null;
  private By userIconOnHeaderJira = By.id("header-details-user-fullname");
  private By quickSearchInputField = By.id("quickSearchInput");
  private By searchBugIssueByName = By.xpath("//*[@id='quicksearch-menu']//child::span[contains(text(),'WEBINAR-9060')]");
  private By searchTaskIssueByName = By.xpath("//*[@id='quicksearch-menu']//child::span[contains(text(),'WEBINAR-12467')]");
  private By createIssueDialogOpenedAsCreateIssueForm = By.id("create-issue-dialog"); //locator for CreateIssueForm

  private By createButtonOnHeader = By.id("create_link");

  public HomePage(WebDriver driver) {
    this.driver = driver;
  }

  public boolean isUserIconOnHeaderPresent() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30).getSeconds());
    return wait.until(presenceOfElementLocated(userIconOnHeaderJira)).isDisplayed();
  }

  public boolean isQuickSearchInputFieldEnable() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30).getSeconds());
    return wait.until(presenceOfElementLocated(quickSearchInputField)).isEnabled();
  }

  public void inputNameToSearchFieldOnHeader(String search) {
    driver.findElement(quickSearchInputField).sendKeys(search);
  }

  public void isFoundBugIssueByNameClickable() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30).getSeconds());
    wait.until(presenceOfElementLocated(searchBugIssueByName)).click();
  }

  public boolean isCreateButtonOnHeaderPresent() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30).getSeconds());
    return wait.until(presenceOfElementLocated(createButtonOnHeader)).isEnabled();
  }

  public void isCreateButtonOnHeaderClickable() {
    clickOnElementWithRetry(createButtonOnHeader, createIssueDialogOpenedAsCreateIssueForm, 3, 3 );
  }

  private void clickOnElementWithRetry(By elementToBeClicked, By successCriteriaElement, int attempts, int timeOutInSeconds) {
    WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
    for (int i = 0; i < attempts; i++) {
      try {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successCriteriaElement)).isDisplayed();
        break;
      } catch (TimeoutException e) {
        wait.until(ExpectedConditions.elementToBeClickable(elementToBeClicked));
        driver.findElement(elementToBeClicked).click();
      }
    }
  }

  public void isFoundTaskIssueByNameClickable() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30).getSeconds());
    wait.until(presenceOfElementLocated(searchTaskIssueByName)).click();
  }
}