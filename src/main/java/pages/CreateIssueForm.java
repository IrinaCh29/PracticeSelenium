package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class CreateIssueForm {
  private WebDriver driver = null;
  private By projectNameField = By.id("project-field");
  private By issueTypeField = By.id("issuetype-field");
  private By summaryField = By.id("summary");
  private By reporterField = By.id("reporter-field");
  private By textAreaOnDescription = By.xpath("//*[@id='description-wiki-edit']//child::a[text()='Text']");
  private By descriptionTextArea = By.id("description");
  private By issueSubmitButton = By.id("create-issue-submit");
  private By successPopUpMessage = By.className("aui-message-success");

  public CreateIssueForm(WebDriver driver) {
    this.driver = driver;
  }

  public boolean isProjectNameFieldPresent() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30).getSeconds());
    return wait.until(presenceOfElementLocated(projectNameField)).isDisplayed();
  }

  public void clearProjectNameField() {
    driver.findElement(projectNameField).clear();
  }

  public void enterProjectName(String projectName) {
    driver.findElement(projectNameField).sendKeys(projectName);
  }

  public void pressTabOnProjectName() {
    driver.findElement(projectNameField).sendKeys(Keys.TAB);
  }

  public boolean isIssueTypeFieldPresent() {
    return driver.findElement(issueTypeField).isDisplayed();
  }

  public void clearIssueTypeField() {
    driver.findElement(issueTypeField).clear();
  }

  public void enterIssueName(String issueName) {
    driver.findElement(issueTypeField).sendKeys(issueName);
  }

  public void pressTabOnIssueName() {
    driver.findElement(issueTypeField).sendKeys(Keys.TAB);
  }

  public boolean isSummaryFieldPresent() {
    return driver.findElement(summaryField).isDisplayed();
  }

  public void enterSummary(String summaryText) {
    driver.findElement(summaryField).sendKeys(summaryText);
  }

  public boolean isReporterFieldPresent() {
    return driver.findElement(reporterField).isDisplayed();
  }

  public void clearReporterField() {
    driver.findElement(reporterField).clear();
  }

  public void enterReporter(String reporter) {
    driver.findElement(reporterField).sendKeys(reporter);
  }

  public void pressTabOnReporterName() {
    driver.findElement(reporterField).sendKeys(Keys.TAB);
  }

  public boolean isTextAreaOnDescriptionEnable() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30).getSeconds());
    return wait.until(presenceOfElementLocated(textAreaOnDescription)).isEnabled();
  }

  public void theTextAreaOnDescriptionIsClickable() {
    driver.findElement(textAreaOnDescription).click();
  }

  public void enterSomeDescription(String description) {
    driver.findElement(descriptionTextArea).sendKeys(description);
  }

  public void isIssueSubmitButtonClickable() {
    driver.findElement(issueSubmitButton).click();
  }

  public boolean isSuccessPopUpMessagePresent() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30).getSeconds());
    return wait.until(presenceOfElementLocated(successPopUpMessage)).isDisplayed();
  }

  public boolean successPopUpMessageConsistOf() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2).getSeconds());
    WebElement projectNameIsPresent = wait.until(ExpectedConditions.visibilityOfElementLocated(successPopUpMessage));
    return wait.until(ExpectedConditions.textToBePresentInElement(projectNameIsPresent, "WEBINAR"));
  }
}