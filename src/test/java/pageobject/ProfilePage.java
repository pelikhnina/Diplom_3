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

    public String getName() {
        return driver.findElement(profileName).getAttribute("value");
    }
    public String getEmail() {
        return driver.findElement(profileEmail).getAttribute("value");
    }

}
