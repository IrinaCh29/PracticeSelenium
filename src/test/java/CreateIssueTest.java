import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CreateIssueForm;
import pages.HomePage;
import pages.LoginPage;
import utils.WebDriverFactory;

import static org.testng.Assert.assertTrue;

public class CreateIssueTest {

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
  public void createIssue() {
    loginPage.navigateTo();
    loginPage.enterUserName("IrinaChub");
    loginPage.enterPassword("IrinaChub");
    loginPage.clickLogin();

    assertTrue(homePage.isCreateButtonOnHeaderPresent());
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    homePage.isCreateButtonOnHeaderClickable();

    assertTrue(createIssueForm.isProjectNameFieldPresent());
    createIssueForm.clearProjectNameField();
    createIssueForm.enterProjectName("Webinar (WEBINAR)");
    createIssueForm.pressTabOnProjectName();

    assertTrue(createIssueForm.isIssueTypeFieldPresent());
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    createIssueForm.clearIssueTypeField();
    createIssueForm.enterIssueName("Task");
    createIssueForm.pressTabOnIssueName();

    assertTrue(createIssueForm.isSummaryFieldPresent());
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    createIssueForm.enterSummary("summary");

    assertTrue(createIssueForm.isReporterFieldPresent());
    createIssueForm.clearReporterField();
    createIssueForm.enterReporter("IrinaChub");
    createIssueForm.pressTabOnReporterName();

    assertTrue(createIssueForm.isTextAreaOnDescriptionEnable());
    createIssueForm.theTextAreaOnDescriptionIsClickable();
    createIssueForm.enterSomeDescription("some text of description");

    createIssueForm.isIssueSubmitButtonClickable();

    assertTrue(createIssueForm.isSuccessPopUpMessagePresent());
    assertTrue(createIssueForm.successPopUpMessageConsistOf());
  }

  @AfterMethod
  public void tearDown() {
    driver.quit();
  }
}