import io.restassured.response.ValidatableResponse;
import org.example.user.User;
import org.example.user.UserAssertions;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pageobject.HomePage;
import pageobject.LoginPage;
import pageobject.ProfilePage;
import org.example.user.AuthService;

import org.example.user.UserGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestUtil {
    private static final UserGenerator userGenerator = new UserGenerator();
    private static final AuthService authService = new AuthService();
    private static final List<String> accessTokens = new ArrayList<>();
    public static User createTestUser() {
        User user = userGenerator.generateRandomUser();
        ValidatableResponse response = authService.createUser(user);
        UserAssertions.createdSuccessfully(response, user);
        String accessToken = response.extract().path("accessToken");
        accessTokens.add(accessToken);

        return user;
    }
    public static void performLoginAndCheck(User user, WebDriver driver) {

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());

        HomePage homePage = new HomePage(driver);
        homePage.clickOnAccountButton();

        ProfilePage profilePage = new ProfilePage(driver);
        Assert.assertTrue(profilePage.isProfilePage());
        Assert.assertThat(profilePage.getEmail(), CoreMatchers.containsString(user.getEmail()));
        Assert.assertThat(profilePage.getName(), CoreMatchers.containsString(user.getName()));
    }
    public static void cleanUp(WebDriver driver) {
        if(driver != null) {
            driver.quit();
        }

        for (String accessToken : accessTokens) {
            authService.deleteUser(accessToken);
        }
        accessTokens.clear();
    }
}
