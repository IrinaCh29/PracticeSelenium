import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CreateIssueForm;
import pages.HomePage;
import pages.LoginPage;
import utils.WebDriverFactory;

import static org.testng.Assert.assertTrue;

public class CancelationCreateIssueTest {

  WebDriver driver = null;
  LoginPage loginPage = null;
  HomePage homePage = null;
  CreateIssueForm createIssueForm = null;

  @BeforeMethod
  public void setUp() {
    WebDriverFactory.createInstance("Chrome");
    driver = WebDriverFactory.getDriver();
    loginPage = new LoginPage(driver);
    homePage = new HomePage(driver);
    createIssueForm = new CreateIssueForm(driver);
  }

  @Test
  public void cancelCreationIssueDismissAlert() {
    loginPage.navigateTo();
    loginPage.enterUserName("IrinaChub");
    loginPage.enterPassword("IrinaChub");
    loginPage.clickLogin();

    assertTrue(homePage.isCreateButtonOnHeaderPresent());

    homePage.isCreateButtonOnHeaderClickable();

    assertTrue(createIssueForm.isProjectNameFieldPresent());
    createIssueForm.clearProjectNameField();
    createIssueForm.enterProjectName("Webinar (WEBINAR)");
    createIssueForm.pressTabOnProjectName();

    assertTrue(createIssueForm.isIssueTypeFieldPresent());
    createIssueForm.clearIssueTypeField();
    createIssueForm.enterIssueName("Task");
    createIssueForm.pressTabOnIssueName();

    assertTrue(createIssueForm.isSummaryFieldPresent());
    createIssueForm.enterSummary("summary");

    createIssueForm.clickCancelButton();
    createIssueForm.dismissAlert();

    Assert.assertTrue(createIssueForm.createIssueFormIsNotDisappeared());
  }

  @Test
  public void cancelCreationIssueAcceptAlert() {
    loginPage.navigateTo();
    loginPage.enterUserName("IrinaChub");
    loginPage.enterPassword("IrinaChub");
    loginPage.clickLogin();

    assertTrue(homePage.isCreateButtonOnHeaderPresent());

    homePage.isCreateButtonOnHeaderClickable();

    assertTrue(createIssueForm.isProjectNameFieldPresent());
    createIssueForm.clearProjectNameField();
    createIssueForm.enterProjectName("Webinar (WEBINAR)");
    createIssueForm.pressTabOnProjectName();

    assertTrue(createIssueForm.isIssueTypeFieldPresent());
    createIssueForm.clearIssueTypeField();
    createIssueForm.enterIssueName("Task");
    createIssueForm.pressTabOnIssueName();

    assertTrue(createIssueForm.isSummaryFieldPresent());
    createIssueForm.enterSummary("summary");

    createIssueForm.clickCancelButton();
    createIssueForm.acceptAlert();

    Assert.assertTrue(createIssueForm.createIssueFormIsDisappeared());
  }

  @AfterMethod
  public void tearDown() {
    driver.quit();
  }
}