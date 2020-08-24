package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class IssuePage {

  private WebDriver driver = null;
  private By issueTypeOnDetailsSection = By.xpath("//*[contains(text(),'Type:')]//following::*[@id='type-val']");
  private By bugIssueLinkOnHeader = By.xpath("//*[@id='stalker']//child::a[@data-issue-key='WEBINAR-9060']");
  private By taskIssueLinkOnHeader = By.xpath("//*[@id='stalker']//child::a[@data-issue-key='WEBINAR-12467']");
  private By commentTabOnIssue = By.xpath("//*[@id='issue-tabs']//child::*[@id='comment-tabpanel']");
  private By commentButtonOnIssue = By.id("footer-comment-button");
  private By commentAreaOnIssue = By.id("comment");
  private By addCommentButtonOnIssue = By.id("issue-comment-add-submit");
  private By addedCommentTextOnIssue = By.xpath("//*[@id='issue_actions_container']//child::*[@class='action-body flooded']");
  private By deletedButtonOfCommentOnIssue = By.xpath("//*[@id='issue_actions_container']//child::*[@class='action-links']//child::*[@class='delete-comment issue-comment-action']");
  private By deleteCommentDialogOnIssue = By.xpath("//*[@id='delete-comment-dialog']//child::*[@id='comment-delete-submit']");
  private By deleteButtonOnDeleteCommentDialog = By.xpath("//*[@id='delete-comment-dialog']//child::*[@id='comment-delete-submit']");
  private By deletedCommentOnIssue = By.xpath(("//*[@id='issue_actions_container']//child::*[contains(text(), 'There are no comments yet on this issue.')]"));

  public IssuePage(WebDriver driver) {
    this.driver = driver;
  }

  public boolean isIssueTypeOnDetailsSectionPresent() {
    return driver.findElement(issueTypeOnDetailsSection).isDisplayed();
  }

  public boolean isBugIssueLinkOnHeaderPresent() {
    return driver.findElement(bugIssueLinkOnHeader).isDisplayed();
  }

  public boolean isTaskIssueLinkOnHeaderPresent() {
    return driver.findElement(taskIssueLinkOnHeader).isDisplayed();
  }

  public boolean isCommentTabOnIssueEnable() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30).getSeconds());
    return wait.until(presenceOfElementLocated(commentTabOnIssue)).isEnabled();
  }

  public void isCommentTabOnIssueClickable() {
    driver.findElement(commentTabOnIssue).click();
  }

  public boolean isCommentButtonOnIssueEnable() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30).getSeconds());
    return wait.until(presenceOfElementLocated(commentButtonOnIssue)).isEnabled();
  }

  public void isCommentButtonOnIssueClickable() {
    driver.findElement(commentButtonOnIssue).click();
  }

  public void inputTextInCommentTextArea(String comment) {
    driver.findElement(commentAreaOnIssue).sendKeys(comment);
  }

  public void isAddedCommentOnIssueIsSavedByButtonClickable() {
    driver.findElement(addCommentButtonOnIssue).click();
  }

  public boolean isAddedCommentOnIssuePresent() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30).getSeconds());
    WebElement addCommentTest = wait.until(ExpectedConditions.visibilityOfElementLocated(addedCommentTextOnIssue));
    return wait.until(ExpectedConditions.textToBePresentInElement(addCommentTest, "some comments"));
  }
  public void isDeletedButtonOfCommentOnIssueClickable() {
    driver.findElement(deletedButtonOfCommentOnIssue).click();
  }

  public boolean isDeleteDialogOnIssuePresent() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30).getSeconds());
    return wait.until(ExpectedConditions.visibilityOfElementLocated(deleteCommentDialogOnIssue)).isEnabled();
  }

  public void isDeletedButtonOnDeleteCommentDialogClickable() {
    driver.findElement(deleteButtonOnDeleteCommentDialog).click();
  }

  public boolean isDeletedCommentOnIssueNotPresent() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30).getSeconds());

    WebElement deletedCommentTest = wait.until(ExpectedConditions.visibilityOfElementLocated(deletedCommentOnIssue));
    return wait.until(ExpectedConditions.textToBePresentInElement(deletedCommentTest, "There are no comments yet on this issue."));
  }
}