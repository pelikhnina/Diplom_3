import org.example.user.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageobject.HomePage;
import pageobject.ProfilePage;


public class ProfileTest extends BaseTest {
    private User user;
    @Before
    public void setup() {
        super.setup();
        this.user = TestUtil.createTestUser();
    }
    @Test
    public void navigateToAccount() {
        new HomePage(driver).clickOnAccountButton();
        TestUtil.performLoginAndCheck(user, driver);
    }
    @Test
    public void navigateFromAccountByConstructorButton() {
        new HomePage(driver).clickOnAccountButton();
        TestUtil.performLoginAndCheck(user, driver);
        new ProfilePage(driver).clickOnConstructorButton();
        Assert.assertTrue(new HomePage(driver).isHomePage());
    }
    @Test
    public void navigateFromAccountByLogo() {
        new HomePage(driver).clickOnAccountButton();
        TestUtil.performLoginAndCheck(user, driver);
        new ProfilePage(driver).clickOnLogo();
        Assert.assertTrue(new HomePage(driver).isHomePage());
    }
    @After
    public void tearDown() {
        TestUtil.cleanUp();
    }
}
