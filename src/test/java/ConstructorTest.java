import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageobject.HomePage;

public class ConstructorTest extends BaseTest {
    @Before
    public void setup() {
        super.setup();
    }
    @Test
    public void navigateToBunSectionTest () {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnSauceButton();
        homePage.clickOnBunButton();
        boolean isActive = homePage.isBunButtonActive();
        Assert.assertTrue("Кнопка Булки должна стать активной", isActive);
    }
    @Test
    public void navigateToSauceSectionTest () {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnSauceButton();
        boolean isActive = homePage.isSauceButtonActive();
        Assert.assertTrue("Кнопка Соусы должна стать активной", isActive);
    }
    @Test
    public void navigateToFillingSectionTest () {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnFillingButton();
        boolean isActive = homePage.isFillingButtonActive();
        Assert.assertTrue("Кнопка Начинка должна стать активной", isActive);
    }
    @After
    public void tearDown() {
        TestUtil.cleanUp(driver);
    }
}
