package helpers;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverSetUp {
    private static WebDriver webDriver;
    @Before
    public static void setupSuite() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxProfile geoDisabled = new FirefoxProfile();
        geoDisabled.setPreference("geo.enabled", false);
        geoDisabled.setPreference("geo.provider.use_corelocation", false);
        geoDisabled.setPreference("geo.prompt.testing", false);
        geoDisabled.setPreference("geo.prompt.testing.allow", false);
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability(FirefoxDriver.PROFILE, geoDisabled);
        webDriver = new FirefoxDriver(capabilities);
    }

    public static WebDriver getWebDriver(){
        return webDriver;
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }
}
