package pages;

import helpers.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static helpers.DriverSingleTon.getDrivet;
import java.util.List;

public class UIMapLoginPage {
    private static WebDriver driver;
    public static final By USER_NAME_FIELD = Locators.get("Registr.user");
    public static final By EMAIL_FIELD = Locators.get("Registr.email");
    public static final By PSWD_FIELD = Locators.get("Registr.pswd");
    public static final By LOGIN_BUTTON = Locators.get("Registr.button");
    public static final By VALIDATION_MESSAGE = Locators.get("Registr.valid.email");
    public static final By EMAIL_VALIDATION_MESSAGE = Locators.get("Registr.activevalid.email");
    public UIMapLoginPage(WebDriver driver) {
  this.driver = driver;
 }

    public static void  login (WebDriver driver,String user,String email, String pswd) {
        getDrivet().findElement(USER_NAME_FIELD).sendKeys(user);
        getDrivet().findElement(EMAIL_FIELD).sendKeys(email);
        getDrivet().findElement(PSWD_FIELD).sendKeys(pswd);
        getDrivet().findElement(LOGIN_BUTTON).click();
    }
    public List<WebElement> getValidationMessages(){
        return getDrivet().findElements(VALIDATION_MESSAGE);
    }
}
