import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import helpers.Helpers;
import java.net.MalformedURLException;
import java.net.URL;

public class SeleniumGridTest {
    @Test
    public void simpleGridTest() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName("chrome");
        URL hubAdress = new URL("http://192.168.75.1:4444/wd/hub");
        Helpers.saveScreenshots(By.id("sad"),"D:\\Haussss\\Screenshots\\screenshot.png");
        WebDriver driver = new RemoteWebDriver(hubAdress,caps);
        driver.get("http://the-internet.herokuapp.com/");
        System.out.println(driver.getTitle());
    }
}
