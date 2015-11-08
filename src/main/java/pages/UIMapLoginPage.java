package pages;

import helpers.DriverSingleTon;
import helpers.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

public class UIMapLoginPage {
    WebDriver driver = DriverSingleTon.getDriver();
    //private static WebDriver driver;
    public static final By USER_NAME_FIELD = Locators.get("Registr.user");
    public static final By EMAIL_FIELD = Locators.get("Registr.email");
    public static final By PSWD_FIELD = Locators.get("Registr.pswd");
    public static final By LOGIN_BUTTON = Locators.get("Registr.button");
    public static final By EMAIL_VALIDATION_MESSAGE = Locators.get("Registr.activevalid.email");
    public static final By PASS_VALIDATION_MESSAGE = Locators.get("Registr.activevalid.pswd");

    public UIMapLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public static void login(WebDriver driver, String user, String email, String pswd) {
        driver.findElement(USER_NAME_FIELD).sendKeys(user);
        driver.findElement(EMAIL_FIELD).sendKeys(email);
        driver.findElement(PSWD_FIELD).sendKeys(pswd);
        driver.findElement(LOGIN_BUTTON).click();
    }

    public static void login(WebDriver driver,String loginnname,String email, String pass, String emailValidation, String passValidation) {
        driver.findElement(USER_NAME_FIELD).sendKeys(loginnname);
        driver.findElement(EMAIL_FIELD).sendKeys(email);
        driver.findElement(PSWD_FIELD).sendKeys(pass);
        List<WebElement> emailList = driver.findElements(EMAIL_VALIDATION_MESSAGE);
        if (emailList.isEmpty()) {
            System.out.println("Email is right");
        }else{
            System.out.println(emailValidation);
        }

        List<WebElement> pswdList = driver.findElements(PASS_VALIDATION_MESSAGE);
        if (pswdList.isEmpty()) {
            System.out.println("Password is right");
        }else{
            System.out.println(passValidation);
        }


        }


    public List<WebElement> getValidationMessages() {
        return driver.findElements(EMAIL_VALIDATION_MESSAGE);

    }
}
