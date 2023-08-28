package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private final static By bunButton = By.xpath("//div[@class='tab_tab__1SPyG' and .//span[text()='Булки']]");
    private final static By sauceButton = By.xpath("//div[@class='tab_tab__1SPyG' and .//span[text()='Соусы']]");
    private final static By fillingButton = By.xpath("//div[@class='tab_tab__1SPyG' and .//span[text()='Начинки']]");
    public void clickOnBunButton() {
        driver.findElement(bunButton).click();
    }
    public void clickOnSauceButton() {
        driver.findElement(sauceButton).click();
    }
    public void clickOnFillingButton() {
        driver.findElement(fillingButton).click();
    }
}
