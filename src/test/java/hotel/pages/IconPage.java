package hotel.pages;

import java.nio.file.Path;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IconPage {

  private final WebDriver driver;

  private final WebDriverWait wait;

  public IconPage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    this.wait.until(ExpectedConditions.titleContains("Setting Icon"));
    if (this.driver.getTitle() == null || !this.driver.getTitle().startsWith("Setting Icon")) {
      throw new IllegalStateException("wrong page: " + this.driver.getTitle());
    }
  }

  public void setIcon(Path file) {
    var iconInput = driver.findElement(By.id("icon"));
    iconInput.sendKeys(file.toAbsolutePath().toString());
  }

  public void setZoom(int value) {
    var zoomInput = driver.findElement(By.id("zoom"));
    ((JavascriptExecutor) driver)
        .executeScript("arguments[0].value = arguments[1]", zoomInput, Integer.toString(value));
  }

  public void setColor(Color color) {
    var colorInput = driver.findElement(By.id("color"));
    ((JavascriptExecutor) driver)
        .executeScript("arguments[0].value = arguments[1]", colorInput, color.asHex());
  }

  public MyPage goToMyPage() {
    var submitButton = driver.findElement(By.cssSelector("#icon-form > button"));
    submitButton.click();
    return new MyPage(driver);
  }

  public String getIconMessage() {
    var iconMessage = driver.findElement(By.cssSelector("#icon ~ .invalid-feedback"));
    return iconMessage.getText();
  }
}
