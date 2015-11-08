import helpers.DataProviders;
import helpers.DriverSingleTon;
import helpers.Locators;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.UIMapLoginPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static helpers.DriverSingleTon.getDriver;

public class TwitterTests extends DataProviders {

    private final static String BASE_URL = "https://twitter.com/signup";
    private UIMapLoginPage loginPage;
    private UIMapLoginPage mesage;
    private DriverSingleTon driverType;

    @BeforeMethod
    public void setup() {
        // DriverSingleTon.quit();
        DriverSingleTon.initDriver("chrome");
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        getDriver().manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        getDriver().get(BASE_URL);
        loginPage = new UIMapLoginPage(getDriver());
        mesage = new UIMapLoginPage(getDriver());

    }

    @AfterMethod
    public void teardown() {
        getDriver().quit();
    }

    @Test
    public void twitterRegistrFormTest() {
        loginPage.login(getDriver(), "tomsmith", "asdasda2323232#", "super");
        List<WebElement> validations = mesage.getValidationMessages();
        Assert.assertTrue(getDriver().findElement(Locators.get("Registr.activevalid.email")).isDisplayed());
        Assert.assertTrue(getDriver().findElement(Locators.get("Registr.activevalid.pswd")).isDisplayed());
    }



    @Test(dataProvider = "registrationData")
    public void
    passInvalidTest(String loginnname,String email, String pass, String emailValidation, String passValidation) {
        loginPage.login(getDriver(),loginnname,email,pass,emailValidation,passValidation);

    }
}
