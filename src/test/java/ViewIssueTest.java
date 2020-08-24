import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.IssuePage;
import pages.LoginPage;
import utils.WebDriverFactory;

import static org.testng.Assert.assertTrue;

public class ViewIssueTest {
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
  public void viewIssue() {
    loginPage.navigateTo();
    loginPage.enterUserName("IrinaChub");
    loginPage.enterPassword("IrinaChub");
    loginPage.clickLogin();

    assertTrue(homePage.isUserIconOnHeaderPresent());

    homePage.inputNameToSearchFieldOnHeader("WEBINAR-9060");
    homePage.isFoundBugIssueByNameClickable();

    assertTrue(issuePage.isIssueTypeOnDetailsSectionPresent());

    assertTrue(issuePage.isBugIssueLinkOnHeaderPresent());
  }

  @AfterMethod
  public void tearDown() {
    driver.quit();
  }
}