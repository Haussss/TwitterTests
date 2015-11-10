package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static helpers.DriverSingleTon.getDriver;
import static helpers.Locators.get;

public class RegisterPage {

    public static final By USER_FULL_NAME_FIELD = get("Registr.user");
    public static final By EMAIL_FIELD = get("Registr.email");
    public static final By PASSWORD_FIELD = get("Registr.pswd");
    public static final By SIGN_UP_BUTTON = get("Registr.button");
    public static final By ALLERT = get("Registr.ActiveValidationMessages");
    public static final By ACTIVE_EMAIL_VALIDATION = get("Registr.emailValidationMessage");
    public static final By ACTIVE_PASS_VALIDATION = get("Registr.passValidationMessage");
    public static final By PHONE_VALIDATION = get("Registr.phoneValidationMessages");
    public static final By OBVIOUS_PASS_VALIDATION = get("Registr.obvoiusValidationMessages");
    public static final By TAKEN_EMAIL_VALIDATION = get("Registr.takenValidationMessages");
    public static final By ACTIVE_LOGIN_VALIDATION = get("Registr.loginValidationmessage");
    public static final By TWITTER_LOGIN_VALIDATION = get("Registr.twitterLoginValidationmessage");


    public static void signUp(String user, String email, String pass) {
        fillForm(user, email, pass);
        getDriver().findElement(SIGN_UP_BUTTON).click();
    }

    public static void fillForm(String user, String email, String pass) {
        getDriver().findElement(USER_FULL_NAME_FIELD).sendKeys(user);
        getDriver().findElement(EMAIL_FIELD).sendKeys(email);
        getDriver().findElement(PASSWORD_FIELD).sendKeys(pass);
    }

    public static List<WebElement> getValidationMessages(){
        return getDriver().findElements(ALLERT);
    }
}