import helpers.DriverSingleTon;
import helpers.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import helpers.AuthService;
import helpers.WebDriverUtils;

import static helpers.DriverSingleTon.getDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class InternetTest {
    //  private WebDriver driver;
    private final static String BASE_URL = "http://the-internet.herokuapp.com/";
    private final static String ERROR_MESSAGEPSWD = "Your password is invalid!";
    private final static String ERROR_MESSAGEUSRN = "Your username is invalid!";

    @BeforeMethod
    public void setup() {
        //driver = new ChromeDriver();
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        getDriver().manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        getDriver().get(BASE_URL);
    }

    @AfterMethod
    public void teardown() {
        DriverSingleTon.getDriver().quit();
    }

    @Test
    public void elementGetVisibleTest() {
        getDriver().findElement(By.linkText("Dynamic Loading")).click();
        getDriver().findElement(By.partialLinkText("Example 1")).click();
        WebElement startButton = getDriver().findElement(By.cssSelector("#start button"));
        WebElement finishBlock = getDriver().findElement(By.id("finish"));
        startButton.click();
        Assert.assertFalse(startButton.isDisplayed(), "Start button is not dissapear");
        WebDriverWait wait = new WebDriverWait(getDriver(), 12);
        wait.until(ExpectedConditions.visibilityOf(finishBlock));
        Assert.assertTrue(finishBlock.isDisplayed(), "Finish block is visible");
        Assert.assertEquals(finishBlock.getText(), "Hello World!");
    }

    @Test
    public void elementAppearedTest() {
        getDriver().findElement(By.linkText("Dynamic Loading")).click();
        getDriver().findElement(By.partialLinkText("Example 2")).click();
        WebElement startButton = getDriver().findElement(By.cssSelector("#start button"));
        startButton.click();
        WebDriverWait wait = new WebDriverWait(getDriver(), 13);
        //  wait.until(ExpectedConditions.elementSelectionStateToBe(By.id("loading"), false));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
        WebElement finishBlock = getDriver().findElement(By.id("finish"));
        Assert.assertEquals(finishBlock.getText(), "Hello World!");

    }

    @Test
    public void loginTest() {
        getDriver().findElement(By.linkText("Form Authentication")).click();
        WebElement login = getDriver().findElement(By.id("username"));
        login.sendKeys("tomsmith");
        WebElement pass = getDriver().findElement(By.id("password"));
        pass.sendKeys("SuperSecretPassword!");
        getDriver().findElement(By.cssSelector(".radius")).click();
        WebElement finishButton = getDriver().findElement(By.cssSelector(".icon-2x.icon-signout"));
        Assert.assertTrue(finishButton.isDisplayed(), "Finish button is visible");
        Assert.assertEquals(finishButton.getText(), "Logout");
        WebElement finishTitle = getDriver().findElement(By.id("flash"));
        Assert.assertTrue(finishTitle.isDisplayed(), "Finish title is visible");
        Assert.assertTrue(finishTitle.getText().contains("You logged into a secure area!"));

    }

    @Test
    public void logotTest() {
        getDriver().findElement(By.linkText("Form Authentication")).click();
        WebElement login = getDriver().findElement(By.id("username"));
        login.sendKeys("tomsmith");
        WebElement pass = getDriver().findElement(By.id("password"));
        pass.sendKeys("SuperSecretPassword!");
        getDriver().findElement(By.cssSelector(".radius")).click();
        getDriver().findElement(By.cssSelector(".button.secondary.radius")).click();
        WebElement logoutTitle = getDriver().findElement(By.id("flash"));
        Assert.assertTrue(logoutTitle.isDisplayed(), "Logout title is visible");
        Assert.assertTrue(logoutTitle.getText().contains("You logged out of the secure area!"));
        WebElement logoutText = getDriver().findElement(By.cssSelector(".example"));
        Assert.assertTrue(logoutText.getText().contains("Login Page"));
    }

    @Test
    public void validationTest() {
        getDriver().findElement(By.linkText("Form Authentication")).click();
        Assert.assertTrue(AuthService.isErrorMessageDisplayedAndCorrect(getDriver(), "wronglogin", "SuperSecretPassword!",
                ERROR_MESSAGEUSRN), "Error message isn't displayed");
        Assert.assertTrue(AuthService.isErrorMessageDisplayedAndCorrect(getDriver(), "tomsmith", "WrondPassword",
                ERROR_MESSAGEPSWD), "Error message isn't displayed");
        Assert.assertTrue(AuthService.isErrorMessageDisplayedAndCorrect(getDriver(), "", "",
                ERROR_MESSAGEUSRN), "Error message isn't displayed");
        Assert.assertTrue(AuthService.isErrorMessageDisplayedAndCorrect(getDriver(), "wronglogin", "wrongpassword",
                ERROR_MESSAGEUSRN), "Error message isn't displayed");

    }


    @Test
    public void checkBoxTest() {
        getDriver().findElement(By.linkText("Checkboxes")).click();
        List<WebElement> checkboxes = getDriver().findElements(By.cssSelector("input[type='checkbox']"));
        WebDriverUtils.check(checkboxes.get(0));
        WebDriverUtils.uncheck(checkboxes.get(1));
        Assert.assertTrue(checkboxes.get(0).isSelected());
        Assert.assertFalse(checkboxes.get(1).isSelected());
    }

    @Test
    public void dropDownTest() {
        getDriver().findElement(By.linkText("Dropdown")).click();
        WebElement select = getDriver().findElement(By.id("dropdown"));
        Select dropdown = new Select(select);
        List<WebElement> options = dropdown.getOptions();
        Assert.assertEquals(options.size(), 3);
        Assert.assertFalse(dropdown.isMultiple());
        //Assert.assertEquals(options.);

    }

    @Test
    public static void emailForgotTest() throws InterruptedException {

        Helpers.sendMailMessage("28z5pd+2q129q3h1u@sharklasers.com");
        Helpers.mailRecived("agusha");

//        getDriver().get("http://the-internet.herokuapp.com/forgot_password");
//        WebElement field = getDriver().findElement(By.id("email"));
//        field.sendKeys("28qabj+5o0yt1ocejhw@sharklasers.com");
//        WebElement button = getDriver().findElement(By.id("form_submit"));
//        button.click();
//        Thread.sleep(200);
//        getDriver().get("https://www.guerrillamail.com");
//        WebElement butt = getDriver().findElement(By.id("inbox-id"));
//        butt.click();
//        getDriver().findElement(By.cssSelector("input[type='text']")).sendKeys("XaycuTo");
//        WebElement butt2 = getDriver().findElement(By.cssSelector(".save"));
//        butt2.click();
//        getDriver().findElement(By.id("email.list"));
//        Assert.assertFalse(getDriver().findElement(By.cssSelector(".mail_row")).isDisplayed());
//        Assert.assertEquals(getDriver().findElement(By.cssSelector(".mail_row .td2")).getText(), "no-reply@the-internet.herokuapp.com ");
//        getDriver().findElement(By.cssSelector(".mail_row .td2")).click();
//        Assert.assertEquals(getDriver().findElement(By.cssSelector(".email_subject")).getText(), "Forgot Password from the-internet");

    }


}