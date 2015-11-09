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
    public static final By USER_NAME_FIELD = Locators.get("Registr.user");
    public static final By EMAIL_FIELD = Locators.get("Registr.email");
    public static final By PSWD_FIELD = Locators.get("Registr.pswd");
    public static final By LOGIN_BUTTON = Locators.get("Registr.button");
    public static final By EMAIL_VALIDATION_MESSAGE = Locators.get("Registr.activevalid.email");
    public static final By PASS_VALIDATION_MESSAGE = Locators.get("Registr.activevalid.pswd");
    public static final By PHONE_INVALID_MESSAGE = Locators.get("Registr.activevalid.phone");
    public static final By PASS_WEAK_MESSAGE = Locators.get("Registr.weak.pswd");
    public static final By PASS_OBVIOUS_MESSAGE = Locators.get("Registr.obvious.pswd");

//    public UIMapLoginPage(WebDriver driver) {
//        this.driver = driver;
//    }

    public static void login(String user, String email, String pswd) {
        DriverSingleTon.getDriver().findElement(USER_NAME_FIELD).sendKeys(user);
        DriverSingleTon.getDriver().findElement(EMAIL_FIELD).sendKeys(email);
        DriverSingleTon.getDriver().findElement(PSWD_FIELD).sendKeys(pswd);
        DriverSingleTon.getDriver().findElement(LOGIN_BUTTON).click();
    }
    public static void fillForm(String user, String email, String pass){
        DriverSingleTon.getDriver().findElement(USER_NAME_FIELD).sendKeys(user);
        DriverSingleTon.getDriver().findElement(EMAIL_FIELD).sendKeys(email);
        DriverSingleTon.getDriver().findElement(PSWD_FIELD).sendKeys(pass);
    }

    public static void login( String loginnname, String email, String pass, String emailValidation, String passValidation) {
        DriverSingleTon.getDriver().findElement(USER_NAME_FIELD).sendKeys(loginnname);
        DriverSingleTon.getDriver().findElement(EMAIL_FIELD).sendKeys(email);
        DriverSingleTon.getDriver().findElement(PSWD_FIELD).sendKeys(pass);
        List<WebElement> emailList = DriverSingleTon.getDriver().findElements(EMAIL_VALIDATION_MESSAGE);
        if (emailList.isEmpty()) {
            System.out.println("Email is right");
        }else{
            System.out.println(emailValidation);
        }

        List<WebElement> pswdList = DriverSingleTon.getDriver().findElements(PASS_VALIDATION_MESSAGE);
        if (pswdList.isEmpty()) {
            System.out.println("Password is right");
        }else{
            System.out.println(passValidation);
        }


        }


    public  static List<WebElement> getValidationMessages() {
        return DriverSingleTon.getDriver().findElements(EMAIL_VALIDATION_MESSAGE);

    }
}
