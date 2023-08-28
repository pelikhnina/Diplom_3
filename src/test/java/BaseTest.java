import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class BaseTest {
    protected static WebDriver driver;
    protected static final String BASE_URL = "https://stellarburgers.nomoreparties.site";
    @Before
    public void setup() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\desum\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\desum\\Downloads\\chrome-win64\\chrome-win64\\chrome.exe");
        //options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get(BASE_URL);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
