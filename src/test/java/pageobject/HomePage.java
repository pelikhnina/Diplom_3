package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    private final static By bunButton = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and .//span[text()='Булки']]");
    private final static By sauceButton = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and .//span[text()='Соусы']]");
    private final static By fillingButton = By.xpath("//div[contains(@class, 'tab_tab__1SPyG') and .//span[text()='Начинки']]");
    private final static String activeClassName = "tab_tab_type_current__2BEPc";

    public void clickOnBunButton() {
        driver.findElement(bunButton).click();
    }
    public void clickOnSauceButton() {
        driver.findElement(sauceButton).click();
    }
    public void clickOnFillingButton() {
        driver.findElement(fillingButton).click();
    }

    public boolean isBunButtonActive() {
        WebElement button = driver.findElement(bunButton);
        return button.getAttribute("class").contains(activeClassName);
    }

    public boolean isSauceButtonActive() {
        WebElement button = driver.findElement(sauceButton);
        return button.getAttribute("class").contains(activeClassName);
    }
    public boolean isFillingButtonActive() {
        WebElement button = driver.findElement(fillingButton);
        return button.getAttribute("class").contains(activeClassName);
    }
}
