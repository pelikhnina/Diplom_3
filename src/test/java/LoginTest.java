import io.restassured.response.ValidatableResponse;
import org.example.user.AuthService;
import org.example.user.User;
import org.example.user.UserAssertions;
import org.example.user.UserGenerator;
import org.junit.Before;
import org.junit.Test;
import pageobject.HomePage;
import pageobject.LoginPage;

public class LoginTest extends BaseTest {
    private static final UserGenerator userGenerator = new UserGenerator();
    private static final AuthService authService = new AuthService();
    @Before
    public void setup() {
        super.setup();
    }
    @Test
    public void login() {
        driver.get(BASE_URL + "/login");
        User user = userGenerator.generateRandomUser();
        ValidatableResponse response = authService.createUser(user);
        UserAssertions.createdSuccessfully(response, user);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());

    }
    @Test
    public void loginByHomepageButtonTest () {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLoginButton();

        User user = userGenerator.generateRandomUser();
        ValidatableResponse response = authService.createUser(user);
        UserAssertions.createdSuccessfully(response, user);

    }
}
