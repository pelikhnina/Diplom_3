import org.example.user.User;
import org.example.user.UserGenerator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageobject.RegisterPage;

import java.util.concurrent.TimeUnit;

public class RegisterTest extends BaseTest {
    private User user;
    @Before
    public void setup() {
        super.setup();
        this.user = TestUtil.createTestUser();
    }
    @Test
    public void registerTest() {
        driver.get(BASE_URL + "/register");
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.navigate().to(BASE_URL + "/login");

        TestUtil.performLoginAndCheck(user, driver);
    }

    @Test
    public void registerInvalidPasswordTest() {
        RegisterPage registerPage = new RegisterPage(driver);
        user.setPassword(new UserGenerator().generateShortPassword());
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());

        Assert.assertEquals("Некорректный пароль", registerPage.getPasswordError().getText());
    }
    @After
    public void tearDown() {
        TestUtil.cleanUp();
    }
}
