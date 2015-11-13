package helpers;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import static helpers.DriverSingleTon.getDriver;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Helpers {
    public static void check(WebElement checkbox){
//        if (!checkbox.isSelected()){
//            checkbox.click();
//        }
        setCheckboxTo(checkbox,true);
    }

    public static void uncheck(WebElement checkbox){
//        if (checkbox.isSelected()){
//            checkbox.click();
//        }
        setCheckboxTo(checkbox,true);
    }


    private static void setCheckboxTo(WebElement checkbox, boolean value){
        if (checkbox.isSelected() != value){
            checkbox.click();
        }
    }

    public static boolean isAlertPresent(WebDriver driver){
        try {
            Alert alert = driver.switchTo().alert();
            return true;
        }catch (NoAlertPresentException ex){
            return false;
        }
    }

    public static List<String> readAllLines(String resourcePath) throws IOException {
        return Files.readAllLines(new File(resourcePath).toPath(), Charset.defaultCharset());
    }

public static void saveScreenshots(By element,String path) {
    TakesScreenshot screenMaker = (TakesScreenshot) (getDriver().findElement(element));
    File screen = screenMaker.getScreenshotAs(OutputType.FILE);
    try {
        FileUtils.copyFile(screen, new File("D:\\Haussss\\Screenshots\\screenshot.png"));
    } catch (IOException e) {
        System.out.println(e.getMessage());
    }

}
    public static void saveScreenshots(String path) {
        TakesScreenshot screenMaker = (TakesScreenshot)getDriver();
        File screen = screenMaker.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screen, new File("D:\\Haussss\\Screenshots\\screenshot.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}



