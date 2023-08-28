package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RestorePasswordPage {
    private final WebDriver driver;

    public RestorePasswordPage (WebDriver driver) {
        this.driver = driver;
    }

    private final static By loginLink = By.xpath("//a[@href='/login']");

    public void clickOnLoginLink() {
        driver.findElement(loginLink).click();
    }
}
