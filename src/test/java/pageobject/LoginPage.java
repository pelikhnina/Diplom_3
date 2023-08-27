package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    private final By passwordField = By.xpath("//input[@type='password']");
    private final By emailField = By.xpath("");
    private final static By loginButton = By.xpath("//a[contains(@class, 'AppHeader_header__link__3D_hX')]");


    public void clickOnLoginButton() {
        driver.findElement(loginButton).click();
    }
}
