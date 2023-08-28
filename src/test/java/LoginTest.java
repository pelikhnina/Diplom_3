import groovy.util.logging.Log;
import io.restassured.response.ValidatableResponse;
import org.example.user.AuthService;
import org.example.user.User;
import org.example.user.UserAssertions;
import org.example.user.UserGenerator;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageobject.*;

import java.util.concurrent.TimeUnit;

public class LoginTest extends BaseTest {
    private static final UserGenerator userGenerator = new UserGenerator();
    private static final AuthService authService = new AuthService();
    @Before
    public void setup() {
        super.setup();
    }
    @Test
    public void loginByHomepageButtonTest () {
        User user = userGenerator.generateRandomUser();
        ValidatableResponse response = authService.createUser(user);
        UserAssertions.createdSuccessfully(response, user);

        HomePage homePage = new HomePage(driver);
        homePage.clickOnLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        homePage.clickOnAccountButton();

        ProfilePage profilePage = new ProfilePage(driver);

        Assert.assertThat(profilePage.getEmail(), CoreMatchers.containsString(user.getEmail()));
        Assert.assertThat(profilePage.getName(), CoreMatchers.containsString(user.getName()));

    }
    @Test
    public void loginByAccountButtonTest() {
        User user = userGenerator.generateRandomUser();
        ValidatableResponse response = authService.createUser(user);
        UserAssertions.createdSuccessfully(response, user);

        HomePage homePage = new HomePage(driver);
        homePage.clickOnAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        homePage.clickOnAccountButton();

        ProfilePage profilePage = new ProfilePage(driver);

        Assert.assertThat(profilePage.getEmail(), CoreMatchers.containsString(user.getEmail()));
        Assert.assertThat(profilePage.getName(), CoreMatchers.containsString(user.getName()));

    }
    @Test
    public void loginByRegisterForm() {
        User user = userGenerator.generateRandomUser();
        ValidatableResponse response = authService.createUser(user);
        UserAssertions.createdSuccessfully(response, user);

        driver.navigate().to(BASE_URL + "/register");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickOnLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        HomePage homePage = new HomePage(driver);
        homePage.clickOnAccountButton();

        ProfilePage profilePage = new ProfilePage(driver);

        Assert.assertThat(profilePage.getEmail(), CoreMatchers.containsString(user.getEmail()));
        Assert.assertThat(profilePage.getName(), CoreMatchers.containsString(user.getName()));
    }

    @Test
    public void loginByForgotLinkTest() {
        User user = userGenerator.generateRandomUser();
        ValidatableResponse response = authService.createUser(user);
        UserAssertions.createdSuccessfully(response, user);

        driver.navigate().to(BASE_URL + "/login");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnForgotLink();

        RestorePasswordPage restorePasswordPage = new RestorePasswordPage(driver);
        restorePasswordPage.clickOnLoginLink();

        loginPage.login(user.getEmail(), user.getPassword());

        HomePage homePage = new HomePage(driver);
        homePage.clickOnAccountButton();

        ProfilePage profilePage = new ProfilePage(driver);

        Assert.assertThat(profilePage.getEmail(), CoreMatchers.containsString(user.getEmail()));
        Assert.assertThat(profilePage.getName(), CoreMatchers.containsString(user.getName()));
    }
}
