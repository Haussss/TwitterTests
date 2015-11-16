import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class SeleniumGridTest {
    @Test
    public void simpleGridTest() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName("firefox");
        URL hubAdress = new URL("http://192.168.75.1:4444/wd/hub");

        WebDriver driver = new RemoteWebDriver(hubAdress,caps);
        driver.get("http://the-internet.herokuapp.com/");
        System.out.println(driver.getTitle());
    }
}
