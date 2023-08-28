import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HomePageTest extends BaseTest {
    private WebDriver driver;

    @Before
    public void start() {

    }

    @Test
    public void testHomePage() {

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
