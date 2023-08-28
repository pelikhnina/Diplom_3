package pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriver driver;
    private static final By inputEmail = By.xpath("//label[.='Email']/../input");
    private static final By inputPassword = By.name("Пароль");
    private static final By loginButton = By.xpath("//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");

    private static final By accountProfileLink = By.xpath("//a[@href='/account']");
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public void clickOnLoginButton() {
        driver.findElement(loginButton).click();
    }

    private void setEmail(String email) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(inputEmail));
            driver.findElement(inputEmail).sendKeys(email);
        } catch (StaleElementReferenceException e) {
            WebElement staleElement = driver.findElement(inputEmail);
            staleElement.sendKeys(email);
        }
    }
    private void setPassword(String password) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(inputPassword));
            driver.findElement(inputPassword).sendKeys(password);
        } catch (StaleElementReferenceException e) {
            WebElement staleElement = driver.findElement(inputPassword);
            staleElement.sendKeys(password);
        }
    }

    private void clickOnProfileLink() {
        driver.findElement(accountProfileLink).click();
    }

    public void login(String email, String password) {
        setPassword(password);
        setEmail(email);
        clickOnLoginButton();
    }
}
