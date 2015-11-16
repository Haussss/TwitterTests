package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class DriverSingleTon {

    private static WebDriver driver;
    private DriverSingleTon() {}


    public static WebDriver getDriver() {
        if (driver != null) {
            return driver;
        } else {
            initDriver("chrome");
            return driver;
        }
    }
    public static void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static WebDriver initDriver(String browser) {
        String browserName = System.getProperty("browser", browser);
        String remote = System.getProperty("remote","local");
        if (remote.equals("local")) {
            switch (browser) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "ie":
                    driver = new InternetExplorerDriver();
                    break;
                case "htmlunit":
                    driver = new HtmlUnitDriver();
                    break;
//                case "phantomjs":
//                    driver = new PhantomJSDriver();
//                    break;
                default:
                    driver = new ChromeDriver();
                    break;
            }
        }
        else {
            DesiredCapabilities caps;
            switch (browserName) {

                case "chrome": default:
                    caps = DesiredCapabilities.chrome();
                    break;
                case "firefox":
                    caps = DesiredCapabilities.firefox();
                    break;
                case "ie":
                    caps = DesiredCapabilities.internetExplorer();
                    break;
                case "htmlunit":
 //                   caps = DesiredCapabilities.htmlUnitWithJs();
                    break;
                case "phantomjs":
                    caps = DesiredCapabilities.phantomjs();
                    break;


            }
             try {
 //                driver = new RemoteWebDriver(new URL(remote),caps);
             }catch (Exception ex){
                 System.out.println(ex.getMessage());
             }

        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

}
