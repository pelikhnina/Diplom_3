package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class RegisterPage {
    private final WebDriver driver;
    private static final By inputName = By.xpath("//label[.='Имя']/../input");
    private static final By inputEmail = By.xpath("//label[.='Email']/../input");
    private static final By inputPassword = By.name("Пароль");

    private static final By statusError = By.xpath("//p[contains(@class, 'input__error')]");

    private static final By registerButton = By.xpath("//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");
    public RegisterPage(WebDriver driver){
        this.driver = driver;
    }
    private void setName(String name) {
        driver.findElement(inputName).sendKeys(name);
    }
    private void setEmail(String email) {
        driver.findElement(inputEmail).sendKeys(email);
    }
    private void setPassword(String password) {
        driver.findElement(inputPassword).sendKeys(password);
    }
    private void clickOnRegisterButton() {
        driver.findElement(registerButton).click();
    }
    public WebElement getPasswordError() {
        WebDriverWait wait = new WebDriverWait(driver, 10);  // ожидание до 10 секунд

        return wait.until(ExpectedConditions.visibilityOfElementLocated(statusError));
    }
    public void register(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickOnRegisterButton();
    }
}
