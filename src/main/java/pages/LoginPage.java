package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

  private WebDriver driver = null;
  private By userNameInput = By.id("login-form-username");
  private By passwordInput = By.id("login-form-password");
  private By loginButton = By.id("login");

  public LoginPage(WebDriver driver) {
    this.driver = driver;
  }

  public void navigateTo() {
    driver.get("https://jira.hillel.it/secure/Dashboard.jspa");
  }

  public void enterUserName(String name) {
    driver.findElement(userNameInput).sendKeys(name);
  }

  public void enterPassword(String password) {
    driver.findElement(passwordInput).sendKeys(password);
  }

  public void clickLogin() {
    driver.findElement(loginButton).click();
  }

  public boolean isErrorMessagePresent(String message) {
    WebDriverWait wait = new WebDriverWait(driver, 10);
    return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),\'" + message + "\')]"))).isDisplayed();
  }
}