package pages;

import elements.button;
import elements.imputField;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.nio.file.WatchEvent;

public class PageElementsPageObject {
    private imputField userName;
    private imputField password;
    private button submit;

    public PageElementsPageObject(WebDriver driver){
        userName = new imputField(driver, By.id("username"));
        password = new imputField(driver, By.id("password"));
        submit = new button(driver, By.cssSelector("button[type='submit']"));
    }
    public void login(String user, String pswd){
        userName.setText(user);
        password.setText(pswd);
        submit.click();
    }
}
