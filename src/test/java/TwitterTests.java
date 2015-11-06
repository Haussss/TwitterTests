import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import pages.*;
import helpers.*;
import static helpers.DriverSingleTon.getDrivet;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TwitterTests {
    private final static String BASE_URL = "https://twitter.com/signup";
    private UIMapLoginPage loginPage;
    private WebDriver driver;

@BeforeMethod
    public void setup (){
        getDrivet().manage().window().maximize();
        getDrivet().manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        getDrivet().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        getDrivet().manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        getDrivet().get(BASE_URL);
        loginPage = new UIMapLoginPage(driver);
}
    @AfterMethod
    public void teardown() {
        getDrivet().quit();
    }

    @Test
        public void twitterLoginFormTest(){
        loginPage.login(getDrivet(), "tomsmith", "asdasda2323232#", "super");
       // List<WebElement> validations = get
        Assert.assertTrue(getDrivet().findElement(Locators.get("Registr.valid.email")).isDisplayed());
        Assert.assertTrue(getDrivet().findElement(Locators.get("Registr.valid2.pswd")).isDisplayed());
    }
    @Test(dataProvider = "registrationData",dataProviderClass = DataProviders.class)
    public void
    passInvalidTest(String email, String pass,String emailValidation,String passValidation){

    }
}
