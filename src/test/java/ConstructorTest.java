import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.HomePage;

public class ConstructorTest extends BaseTest {
    private WebDriver driver;
    @Before

    @Test
    public void navigateToBunSectionTest () {
        HomePage homePage = new HomePage();
        homePage.clickOnBunButton();
    }
    @Test
    public void navigateToSauceSectionTest () {
        HomePage homePage = new HomePage();
        homePage.clickOnSauceButton();
    }
    @Test
    public void navigateToFillingSectionTest () {
        HomePage homePage = new HomePage();
        homePage.clickOnFillingButton();
    }
}
