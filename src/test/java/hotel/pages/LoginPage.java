package hotel.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

  private final WebDriver driver;

  private final WebDriverWait wait;

  public LoginPage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    this.wait.until(ExpectedConditions.titleContains("Login"));
    if (this.driver.getTitle() == null || !this.driver.getTitle().startsWith("Login")) {
      throw new IllegalStateException("wrong page: " + this.driver.getTitle());
    }
  }

  public MyPage doLogin(String email, String password) {
    var emailInput = driver.findElement(By.id("email"));
    emailInput.clear();
    emailInput.sendKeys(email);
    var passwordInput = driver.findElement(By.id("password"));
    passwordInput.clear();
    passwordInput.sendKeys(password);
    var loginButton = driver.findElement(By.id("login-button"));
    loginButton.click();
    return new MyPage(driver);
  }

  public void doLoginExpectingFailure(String email, String password) {
    var emailInput = driver.findElement(By.id("email"));
    emailInput.clear();
    emailInput.sendKeys(email);
    var passwordInput = driver.findElement(By.id("password"));
    passwordInput.clear();
    passwordInput.sendKeys(password);
    var loginButton = driver.findElement(By.id("login-button"));
    loginButton.click();
  }

  public String getEmailMessage() {
    var emailMessage = driver.findElement(By.id("email-message"));
    return emailMessage.getText();
  }

  public String getPasswordMessage() {
    var passwordMessage = driver.findElement(By.id("password-message"));
    return passwordMessage.getText();
  }
}
