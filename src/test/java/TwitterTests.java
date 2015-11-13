    import helpers.DataProviders;
    import helpers.DriverSingleTon;
    import helpers.Helpers;
    import org.openqa.selenium.By;
    import org.openqa.selenium.WebElement;
    import org.testng.Assert;
    import org.testng.annotations.AfterMethod;
    import org.testng.annotations.BeforeMethod;
    import org.testng.annotations.Test;
    import pages.RegisterPage;
    import java.io.IOException;
    import java.util.List;

    import static helpers.DriverSingleTon.getDriver;


    public class TwitterTests {
        public static final String BASE_URL = "https://twitter.com/signup?lang=en-gb";
//        @BeforeMethod(alwaysRun = true)
//        public void setup(){
//            getDriver().get(BASE_URL);
//        }
        @AfterMethod(alwaysRun = true)
        public void teardown() {
            DriverSingleTon.quit();
        }

        @Test
        public void signUpTest1(){
            List<WebElement>validations= RegisterPage.getValidationMessages();
            Assert.assertEquals(validations.size(), 0);
            WebElement validation = getDriver().findElement(RegisterPage.EMAIL_FIELD);
            validation.sendKeys("fsdfs");
            Assert.assertTrue(validation.isDisplayed());
            Helpers.saveScreenshots("D:\\Haussss\\Screenshots\\screenshot.png");
            Helpers.saveScreenshots(RegisterPage.EMAIL_FIELD,"D:\\Haussss\\Screenshots\\screenshot.png");
            Assert.assertEquals(validation.getText(), "");
            Assert.assertEquals(RegisterPage.getValidationMessages().size(), 1);
        }
        @Test
        public void loginTest(){

            getDriver().get("http://the-internet.herokuapp.com/login");
            getDriver().findElement(By.id("username")).sendKeys("tomsmith");
            Assert.assertEquals(getDriver().findElement(By.id("username")).getAttribute("value"),"tomsmith");
//            Assert.assertTrue(getDriver().findElement(By.cssSelector("#flash.success")).isDisplayed());
//            Assert.assertTrue(getDriver().findElement(By.cssSelector("a[href='/logout']")).isDisplayed());
        }
        @Test(dataProvider = "registrationData", dataProviderClass = DataProviders.class)
        public void signUpTes(String user, String email, String pass, String emailValidation, String passValidation,String phoneValidation,
                              String strPassValidation,String takenPassValidation,String nameValidation,String twittNameValidation) throws IOException, InterruptedException {
            RegisterPage.fillForm(user, email, pass);
            int validations = 0;

            if (emailValidation.length() > 2) {
                validations++;
                WebElement emailValidationElement = getDriver().findElement(RegisterPage.ACTIVE_EMAIL_VALIDATION);
                Assert.assertTrue(emailValidationElement.isDisplayed());
                Assert.assertEquals(emailValidationElement.getText(), emailValidation);
            }

            if (passValidation.length() > 2) {
                validations++;
                WebElement passValidationMessage = getDriver().findElement(RegisterPage.ACTIVE_PASS_VALIDATION);
                Assert.assertTrue(passValidationMessage.isDisplayed());
                Assert.assertEquals(passValidationMessage.getText(), passValidation);
            }
            if (phoneValidation.length() > 2) {
                validations++;
                Thread.sleep(1000);
                WebElement phoneValidationMessage = getDriver().findElement(RegisterPage.PHONE_VALIDATION );
                Assert.assertTrue(phoneValidationMessage.isDisplayed());
                Assert.assertEquals(phoneValidationMessage.getText(), phoneValidation);
            }
            if (strPassValidation.length() > 2) {
                validations++;
                Thread.sleep(1000);
                WebElement obviousPassValidationMessage = getDriver().findElement(RegisterPage.OBVIOUS_PASS_VALIDATION);
                Assert.assertTrue(obviousPassValidationMessage.isDisplayed());
                Assert.assertEquals(obviousPassValidationMessage.getText(), strPassValidation);
            }
            if (takenPassValidation.length() > 2) {
                validations++;
                Thread.sleep(1000);
                WebElement takenEmailValidationMessage = getDriver().findElement(RegisterPage.TAKEN_EMAIL_VALIDATION);
                Assert.assertTrue(takenEmailValidationMessage.isDisplayed());
                Assert.assertEquals(takenEmailValidationMessage.getText(), takenPassValidation);
            }
            if (nameValidation.length() > 2) {
                validations++;
                WebElement loginValidationMessage = getDriver().findElement(RegisterPage.ACTIVE_LOGIN_VALIDATION);
                Assert.assertTrue(loginValidationMessage.isDisplayed());
                Assert.assertEquals(loginValidationMessage.getText(), nameValidation);
            }
            if (twittNameValidation.length() > 2) {
                validations++;
                WebElement twitterLoginValidationMessage = getDriver().findElement(RegisterPage.TWITTER_LOGIN_VALIDATION);
                Assert.assertTrue(twitterLoginValidationMessage.isDisplayed());
                Assert.assertEquals(twitterLoginValidationMessage.getText(), twittNameValidation);
            }
            Assert.assertEquals(RegisterPage.getValidationMessages().size(), validations);
        }
    }
