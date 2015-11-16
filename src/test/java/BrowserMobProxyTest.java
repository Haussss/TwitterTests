/**
 * Created by Admin on 16.11.2015.
 */

    import net.lightbody.bmp.core.har.Har;
    import net.lightbody.bmp.proxy.ProxyServer;

    import org.junit.Test;
    import org.openqa.selenium.Proxy;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.chrome.ChromeDriver;
    import org.openqa.selenium.firefox.FirefoxDriver;
    import org.openqa.selenium.remote.CapabilityType;
    import org.openqa.selenium.remote.DesiredCapabilities;


    public class BrowserMobProxyTest {
        @Test
//        public void bmpTest() throws Exception {
//            // запуск прокси сервера
//            ProxyServer server = new ProxyServer(4444);
//            server.start();
//
//            // получение Selenium proxy
//            Proxy proxy = server.seleniumProxy();
//
//            // конфигурация FirefoxDriver для использования прокси
//            DesiredCapabilities capabilities = new DesiredCapabilities();
//            capabilities.setCapability(CapabilityType.PROXY, proxy);
//
//            WebDriver driver = new FirefoxDriver(capabilities);
//
//            // открытие страницы
//            driver.get(SOME_URL);
//
//            // здесь основная часть теста
//
//            driver.quit();
//            server.stop();
//        }
        public void bawTest() throws Exception {
            ProxyServer server = new ProxyServer(4424);

            server.start();
            server.autoBasicAuthorization("the-internet.herokuapp.com","admin","admin");
            Proxy proxy = server.seleniumProxy();
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(CapabilityType.PROXY, proxy);
            WebDriver driver = new ChromeDriver(caps);
            driver.get("http://the-internet.herokuapp.com/");
            driver.quit();
            server.stop();

        }
    }

