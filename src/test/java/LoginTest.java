import io.restassured.response.ValidatableResponse;
import org.example.user.AuthService;
import org.example.user.User;
import org.example.user.UserAssertions;
import org.example.user.UserGenerator;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageobject.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoginTest extends BaseTest {
    private static final UserGenerator userGenerator = new UserGenerator();
    private static final AuthService authService = new AuthService();
    private final List<String> accessTokens = new ArrayList<>();
    private User user;

    @Before
    public void setup() {
        super.setup();
        this.user = userGenerator.generateRandomUser();
        ValidatableResponse response = authService.createUser(user);
        UserAssertions.createdSuccessfully(response, user);
        String accessToken = response.extract().path("accessToken");
        accessTokens.add(accessToken);
    }
    @Test
    public void loginByHomepageButtonTest () {
        new HomePage(driver).clickOnLoginButton();
        this.performLoginAndCheck(user);

    }
    @Test
    public void loginByAccountButtonTest() {
        new HomePage(driver).clickOnAccountButton();
        this.performLoginAndCheck(user);

    }
    @Test
    public void loginByRegisterForm() {
        driver.navigate().to(BASE_URL + "/register");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickOnLoginLink();

        this.performLoginAndCheck(user);
    }

    @Test
    public void loginByForgotLinkTest() {
        driver.navigate().to(BASE_URL + "/login");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnForgotLink();

        RestorePasswordPage restorePasswordPage = new RestorePasswordPage(driver);
        restorePasswordPage.clickOnLoginLink();

        this.performLoginAndCheck(user);
    }

    private void performLoginAndCheck(User user) {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());

        HomePage homePage = new HomePage(driver);
        homePage.clickOnAccountButton();

        ProfilePage profilePage = new ProfilePage(driver);
        Assert.assertThat(profilePage.getEmail(), CoreMatchers.containsString(user.getEmail()));
        Assert.assertThat(profilePage.getName(), CoreMatchers.containsString(user.getName()));
    }

    @After
    public void tearDown() {
        for (String accessToken : accessTokens) {
            authService.deleteUser(accessToken);
        }
        accessTokens.clear();
    }
}
