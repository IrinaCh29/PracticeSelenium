package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

  private WebDriver driver = null;

  public HomePage(WebDriver driver) {
    this.driver = driver;
  }

  public void navigateTo() {
    driver.get("https://jira.hillel.it/secure/Dashboard.jspa");
  }

  public boolean userIconIsPresent() {
    return driver.findElement(By.id("header-details-user-fullname")).isDisplayed();
  }
}