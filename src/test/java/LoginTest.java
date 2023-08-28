import org.example.user.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.*;
import java.util.concurrent.TimeUnit;

public class LoginTest extends BaseTest {
    private User user;

    @Before
    public void setup() {
        super.setup();
        this.user = TestUtil.createTestUser();
    }
    @Test
    public void loginByHomepageButtonTest () {
        new HomePage(driver).clickOnLoginButton();
        TestUtil.performLoginAndCheck(user, driver);

    }
    @Test
    public void loginByAccountButtonTest() {
        new HomePage(driver).clickOnAccountButton();
        TestUtil.performLoginAndCheck(user, driver);

    }
    @Test
    public void loginByRegisterForm() {
        driver.navigate().to(BASE_URL + "/register");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickOnLoginLink();

        TestUtil.performLoginAndCheck(user, driver);
    }

    @Test
    public void loginByForgotLinkTest() {
        driver.navigate().to(BASE_URL + "/login");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnForgotLink();

        RestorePasswordPage restorePasswordPage = new RestorePasswordPage(driver);
        restorePasswordPage.clickOnLoginLink();

        TestUtil.performLoginAndCheck(user, driver);
    }

    @After
    public void tearDown() {
        TestUtil.cleanUp();
    }
}
