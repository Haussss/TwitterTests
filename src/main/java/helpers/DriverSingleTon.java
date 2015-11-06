package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class DriverSingleTon {
    private static WebDriver driver;

    private DriverSingleTon() {

    }

    public static WebDriver getDrivet() {
        if (driver != null) {
            return driver;
        } else {
            initDriver("default");
            return driver;
        }
    }

    public static void quit() {
        if (driver != null) {
            driver.quit();
        }
    }

    private static void initDriver(String browser) {
        String browserName = System.getProperty("browser", browser);
        switch (browserName) {
            case "chrome":
            default:
                driver = new ChromeDriver();
            case "firefox":
                driver = new FirefoxDriver();
            case "IE":
                driver = new InternetExplorerDriver();
        }
    }

}
