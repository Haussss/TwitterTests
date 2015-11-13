import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
//import org.openqa.selenium.phantomjs.PhantomJSDriver;
import helpers.Helpers;
import org.testng.Assert;
import org.testng.annotations.Test;
import static helpers.DriverSingleTon.getDriver;

import java.util.List;

/**
 * Created by Admin on 13.11.2015.
 */
public class main
{
//    public static void main(String[] args) throws InterruptedException {
//        WebDriver driver = new PhantomJSDriver();
//        driver.get("https://twitter.com/signup?lang=en-gb");
//        Helpers.saveScreenshots("D:\\Haussss\\Screenshots");
//        System.out.println(driver.getTitle());
//    }
    @Test
    public static void emailForgotTest() throws InterruptedException {

        getDriver().get("http://the-internet.herokuapp.com/forgot_password");
        WebElement field = getDriver().findElement(By.id("email"));
        field.sendKeys("28qabj+5o0yt1ocejhw@sharklasers.com");
        WebElement button = getDriver().findElement(By.id("form_submit"));
        button.click();
        Thread.sleep(200);
        WebDriver driver2 = new HtmlUnitDriver();
        driver2.get("https://www.guerrillamail.com");
        WebElement butt = driver2.findElement(By.id("inbox-id"));
        butt.click();
        butt.sendKeys("XaycuTo");
        WebElement butt2 = driver2.findElement(By.cssSelector(".save"));
        butt2.click();
        driver2.findElement(By.id("email.list"));
        Assert.assertFalse(driver2.findElement(By.cssSelector(".mail_row")).isDisplayed());
        Assert.assertEquals(driver2.findElement(By.cssSelector(".mail_row .td2")).getText(), "no-reply@the-internet.herokuapp.com ");
        driver2.findElement(By.cssSelector(".mail_row .td2")).click();
        Assert.assertEquals(driver2.findElement(By.cssSelector(".email_subject")).getText(), "Forgot Password from the-internet");

    }
//    public void sada(){
//        WebDriver driver = new HtmlUnitDriver();
//        driver.get("https://www.guerrillamail.com");
//        List<WebElement> emails = driver.findElements(By.cssSelector("#email_list tr"));
//        WebElement emailRow = null;
//        for(WebElement mails : emails){
//            if(mails.getText().contains("no-reply@the-internet.herokuapp.com ")){
//                emailRow = mails;
//                break;
//            }
//
//        }
//        emailRow.click();
//
//    }
}



