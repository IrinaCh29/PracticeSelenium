import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.WebDriverFactory;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest {
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

  @DataProvider(name = "Logins")
  public Object[][] createData1() {
    return new Object[][]{
        {"webinar5", "wrongPassword", "Извините, имя пользователя или пароль неверны - пожалуйста, попробуйте еще раз."},
        {"wrongUserName", "webinar5", "Извините, имя пользователя или пароль неверны - пожалуйста, попробуйте еще раз."},
//        {"webinar5", "wrongPassword", "Sorry, your username and password are incorrect - please try again."},
//        { "wrongUserName", "webinar5", "Sorry, your username and password are incorrect - please try again." },
    };
  }

  @Test
  public void successfulLogin() {
    loginPage.navigateTo();
    loginPage.enterUserName("IrinaChub");
    loginPage.enterPassword("IrinaChub");
    loginPage.clickLogin();

    assertTrue(homePage.isUserIconOnHeaderPresent());
  }

//  @Test(dataProvider = "Logins")
//  public void unsuccessfulLogin(String name, String password, String expectedResult) throws InterruptedException {
//    loginPage.navigateTo();
//    loginPage.enterUserName(name);
//    loginPage.enterPassword(password);
//    loginPage.clickLogin();
//
//    assertTrue(loginPage.isErrorMessagePresent(expectedResult));
//  }
//
//  @Test
//  public void failedTest() {
//    loginPage.navigateTo();
//    assertEquals(1, 2);
//  }

  @AfterMethod
  public void tearDown() {
    driver.quit();
  }
}