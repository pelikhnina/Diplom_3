package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
    private final WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    private static final By profileName = By.xpath("//label[.='Имя']/../input");
    private static final By profileEmail = By.xpath("//label[.='Логин']/../input");
    private static final By logoutButton = By.xpath("//button[text()='Выход']");
    private static final By constructorButton = By.xpath("//a[@href='/']");
    private static final By logo = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']");
    private static final By profileHeader = By.xpath("//a[@href='/account/profile']");

    public String getName() {
        return driver.findElement(profileName).getAttribute("value");
    }
    public String getEmail() {
        return driver.findElement(profileEmail).getAttribute("value");
    }
    public void clickOnConstructorButton() {
        driver.findElement(constructorButton).click();
    }
    public void clickOnLogo() {
        driver.findElement(logo).click();
    }
    public boolean isProfilePage() {
        return driver.findElement(profileHeader).isDisplayed();
    }

    public void logout() {
        driver.findElement(logoutButton).click();
    }
}
