import helpers.DataProviders;
import helpers.DriverSingleTon;
import helpers.Locators;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.UIMapLoginPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static helpers.DriverSingleTon.getDriver;


public class TwitterTests {

    private final static String BASE_URL = "https://twitter.com/signup";
    private UIMapLoginPage loginPage;
    private UIMapLoginPage mesage;
    private DriverSingleTon driverType;

    @BeforeMethod
    public void setup() {
        DriverSingleTon.initDriver("chrome");
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        getDriver().manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        getDriver().get(BASE_URL);
//        loginPage = new UIMapLoginPage(getDriver());
//        mesage = new UIMapLoginPage(getDriver());

    }

    @AfterMethod
    public void teardown() {
        DriverSingleTon.quit();
    }

    @Test
    public void twitterRegistrFormTest() {
        loginPage.login( "tomsmith", "asdasda2323232#", "super");
        List<WebElement> validations = mesage.getValidationMessages();
        Assert.assertTrue(getDriver().findElement(Locators.get("Registr.activevalid.email")).isDisplayed());
        Assert.assertTrue(getDriver().findElement(Locators.get("Registr.activevalid.pswd")).isDisplayed());
    }



    @Test(dataProvider = "registrationData")
    public void passInvalidTest(String loginnname,String email, String pass, String emailValidation, String passValidation) {
        loginPage.login(loginnname, email, pass, emailValidation, passValidation);

    }


    @Test(dataProvider = "registrationData",dataProviderClass = DataProviders.class)
    public void signUpTest(String loginName,String email, String pass, String emailValidation, String passValidation) {
        UIMapLoginPage.login(loginName, email, pass);

        int mvalidations = 0;

        if (emailValidation.length()>2){
            mvalidations++;
            WebElement mailValidation = getDriver().findElement(UIMapLoginPage.EMAIL_VALIDATION_MESSAGE);
            Assert.assertTrue(mailValidation.isDisplayed());
            Assert.assertEquals(mailValidation.getText(), "");
        }
        if (passValidation.length()>2){
            mvalidations++;
            WebElement pasValidation = getDriver().findElement(UIMapLoginPage.PASS_VALIDATION_MESSAGE);
            Assert.assertTrue(pasValidation.isDisplayed());
            Assert.assertEquals(pasValidation.getText(), "");
        }if (passValidation.length()>2){
            mvalidations++;
            WebElement pasValidation = getDriver().findElement(UIMapLoginPage.PASS_VALIDATION_MESSAGE);
            Assert.assertTrue(pasValidation.isDisplayed());
            Assert.assertEquals(pasValidation.getText(), "");
        }if (passValidation.length()>2){
            mvalidations++;
            WebElement pasValidation = getDriver().findElement(UIMapLoginPage.PASS_VALIDATION_MESSAGE);
            Assert.assertTrue(pasValidation.isDisplayed());
            Assert.assertEquals(pasValidation.getText(), "");
        }
        Assert.assertEquals(UIMapLoginPage.getValidationMessages().size(),mvalidations);
    }
}
