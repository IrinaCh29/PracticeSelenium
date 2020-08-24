import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.IssuePage;
import pages.LoginPage;
import utils.WebDriverFactory;

import static org.testng.Assert.assertTrue;

public class EditIssueTest {
  WebDriver driver = null;
  LoginPage loginPage = null;
  HomePage homePage = null;
  IssuePage issuePage = null;

  @BeforeMethod
  public void setUp() {
    WebDriverFactory.createInstance("Chrome");
    driver = WebDriverFactory.getDriver();
    loginPage = new LoginPage(driver);
    homePage = new HomePage(driver);
    issuePage = new IssuePage(driver);
  }

  @Test
  public void commentToIssue() {
    loginPage.navigateTo();
    loginPage.enterUserName("IrinaChub");
    loginPage.enterPassword("IrinaChub");
    loginPage.clickLogin();

    assertTrue(homePage.isUserIconOnHeaderPresent());

    assertTrue(homePage.isQuickSearchInputFieldEnable());
    homePage.inputNameToSearchFieldOnHeader("WEBINAR-12467");
    homePage.isFoundTaskIssueByNameClickable();

    assertTrue(issuePage.isIssueTypeOnDetailsSectionPresent());

    assertTrue(issuePage.isTaskIssueLinkOnHeaderPresent());
    assertTrue(issuePage.isCommentTabOnIssueEnable());
    issuePage.isCommentTabOnIssueClickable();
    assertTrue(issuePage.isCommentButtonOnIssueEnable());
    issuePage.isCommentButtonOnIssueClickable();
    issuePage.inputTextInCommentTextArea("some comments");
    issuePage.isAddedCommentOnIssueIsSavedByButtonClickable();
    assertTrue(issuePage.isAddedCommentOnIssuePresent());

    issuePage.isDeletedButtonOfCommentOnIssueClickable();
    assertTrue(issuePage.isDeleteDialogOnIssuePresent());
    issuePage.isDeletedButtonOnDeleteCommentDialogClickable();
    assertTrue(issuePage.isDeletedCommentOnIssueNotPresent());
  }

  @AfterMethod
  public void tearDown() {
    driver.quit();
  }
}