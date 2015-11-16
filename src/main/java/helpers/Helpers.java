package helpers;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.Assert;

import static helpers.DriverSingleTon.getDriver;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helpers {

    private static final By inputField = By.xpath("//span[@id='inbox-id']/input[@type='text']");

    public static void check(WebElement checkbox) {
//        if (!checkbox.isSelected()){
//            checkbox.click();
//        }
        setCheckboxTo(checkbox, true);
    }

    public static void uncheck(WebElement checkbox) {
//        if (checkbox.isSelected()){
//            checkbox.click();
//        }
        setCheckboxTo(checkbox, true);
    }


    private static void setCheckboxTo(WebElement checkbox, boolean value) {
        if (checkbox.isSelected() != value) {
            checkbox.click();
        }
    }

    public static boolean isAlertPresent(WebDriver driver) {
        try {
            Alert alert = driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException ex) {
            return false;
        }
    }

    public static List<String> readAllLines(String resourcePath) throws IOException {
        return Files.readAllLines(new File(resourcePath).toPath(), Charset.defaultCharset());
    }

    public static void saveScreenshots(String path) {
        TakesScreenshot screenshotMaker = (TakesScreenshot) getDriver();
        File screen = screenshotMaker.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screen, new File("path*"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

    public static void sendMailMessage(String email) {
        getDriver().get("http://the-internet.herokuapp.com/forgot_password");
        WebElement field = getDriver().findElement(By.id("email"));
        field.sendKeys(email);
        WebElement button = getDriver().findElement(By.id("form_submit"));
        button.click();
    }

    public static void mailRecived(String loginName) throws InterruptedException {
        // WebDriver driver = new HtmlUnitDriver(BrowserVersion.CHROME,true);
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.guerrillamail.com");
        Thread.sleep(1000);
        driver.findElement(By.id("inbox-id")).click();
        driver.findElement(inputField).clear();
        driver.findElement(inputField).sendKeys(loginName);
        driver.findElement(By.cssSelector(".save.button")).click();
        Thread.sleep(3000);
        List<WebElement> emails = driver.findElements(By.cssSelector("#email_list tr"));
        WebElement emailRow = null;
        for (WebElement mails : emails) {
            if (mails.getText().contains("no-reply@the-internet.herokuapp.com ")) {
                emailRow = mails;
                break;
            }

        }
        Thread.sleep(3000);
        emailRow.click();
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(By.className("email_from"))
                .getText().contains("no-reply@the-internet.herokuapp.com"));
        Assert.assertTrue(driver.findElement(By.cssSelector("strong[class='email_to']"))
                .getText().contains(loginName));
//        Pattern patternL = Pattern.compile("(username:) (\\S+)");
//        Matcher mL = patternL.matcher(driver.findElement(By.tagName("pre")).getText());
//        String trueLogin = mL.group(2);
//        Pattern pattern = Pattern.compile("(password:) (\\S+)");
//        Matcher mP = pattern.matcher(driver.findElement(By.tagName("pre")).getText());
//        String truePass = mP.group(2);

    }

    public static void emailVerify() {

    }
}