import org.example.user.User;
import org.example.user.UserGenerator;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageobject.LoginPage;
import pageobject.ProfilePage;
import pageobject.RegisterPage;

import java.util.concurrent.TimeUnit;

public class RegisterTest extends BaseTest {
    @Before
    public void setup() {
        super.setup();
        driver.get(BASE_URL + "/register");
    }
    @Test
    public void registerTest() {
        UserGenerator userGenerator = new UserGenerator();
        User user = userGenerator.generateRandomUser();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());
        driver.navigate().to(BASE_URL + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        driver.navigate().to(BASE_URL + "/account/profile");
        ProfilePage profilePage = new ProfilePage(driver);

        Assert.assertThat(profilePage.getEmail(), CoreMatchers.containsString(user.getEmail()));
        Assert.assertThat(profilePage.getName(), CoreMatchers.containsString(user.getName()));
    }

    @Test
    public void registerInvalidPasswordTest() {
        UserGenerator userGenerator = new UserGenerator();
        User user = userGenerator.generateRandomUser();
        user.setPassword(userGenerator.generateShortPassword());

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());

        Assert.assertEquals("Некорректный пароль", registerPage.getPasswordError().getText());
    }
}
