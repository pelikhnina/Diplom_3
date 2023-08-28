import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class BaseTest {
    protected WebDriver driver;
    protected final ChromeOptions options = new ChromeOptions();
    protected static final String BASE_URL = "https://stellarburgers.nomoreparties.site";
    @Before
    public void setup() {
        setWebDriver("chrome");
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get(BASE_URL);
    }

    protected void setWebDriver(String browser) {
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\desum\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
                options.setBinary("C:\\Users\\desum\\Downloads\\chrome-win64\\chrome-win64\\chrome.exe");
                break;
            case "yandex":
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\desum\\Downloads\\yandexdriver\\yandexdriver.exe");
                options.setBinary("C:\\Users\\desum\\Downloads\\chrome-win64\\chrome-win64\\chrome.exe");
                break;
            default:
                throw new RuntimeException("Unknown browser driver");
        }

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
