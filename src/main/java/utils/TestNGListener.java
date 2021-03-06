package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class TestNGListener implements ITestListener {

  @Override
  // запускается 1 раз перед каждым тестом
  public void onTestStart(ITestResult result) {
    String browserName = result.getTestContext().getCurrentXmlTest().getParameter("browserName");
    System.out.println("Browser Name is: " + browserName);
    WebDriverFactory.createInstance(browserName);
  }

  @Override
  public void onTestSuccess(ITestResult result) {
    System.out.println("OnTestSuccess Method from TestNGListener is working at the moment");
  }

  @Override
  public void onTestFailure(ITestResult result) {
    File screenshotsFolder = new File(System.getProperty("user.dir") + "/screenshots");
    if (!screenshotsFolder.exists()) {
      screenshotsFolder.mkdir();
    }
    File screenshot = captureScreenshot();
    Path pathToScreenshot = Paths.get(screenshot.getPath());
    try {
      String localTime = java.time.LocalTime.now().toString().replace(":", ".");
      String screenshotName = screenshotsFolder + "/" + "Screenshot_" + localTime + ".png";
      Files.copy(pathToScreenshot, Paths.get(screenshotName), StandardCopyOption.COPY_ATTRIBUTES);
    } catch (IOException e) {
      e.printStackTrace();
    }
    WebDriverFactory.getDriver().quit();
  }

  @Override
  public void onTestSkipped(ITestResult result) {
    System.out.println("onTestSkipped Method from TestNGListener is working at the moment");
  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    System.out.println("onTestFailedButWithinSuccessPercentage Method from TestNGListener is working at the moment");
  }

  @Override
  // запускается 1 раз перед тестом
  public void onStart(ITestContext context) {
    String browserName = context.getCurrentXmlTest().getParameter("browserName");
    System.out.println("Listener. Browser name is " + browserName);
    WebDriverFactory.createInstance(browserName);
  }

  @Override
  public void onFinish(ITestContext context) {
    System.out.println("onFinish Method from TestNGListener is working at the moment");
    WebDriverFactory.getDriver().quit();
  }

  private File captureScreenshot() {
    return ((TakesScreenshot) WebDriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
  }
}