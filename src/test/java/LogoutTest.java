import org.example.user.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageobject.LoginPage;
import pageobject.ProfilePage;

public class LogoutTest extends BaseTest {
    private User user;
    @Before
    public void setup() {
        super.setup();
        this.user = TestUtil.createTestUser();
    }

    @Test
    public void logoutTest() {
        driver.get(BASE_URL + "/login");
        TestUtil.performLoginAndCheck(user, driver);
        new ProfilePage(driver).logout();
        Assert.assertTrue(new LoginPage(driver).isLoginPage());
    }
    @After
    public void tearDown() {
        TestUtil.cleanUp();
    }
}
