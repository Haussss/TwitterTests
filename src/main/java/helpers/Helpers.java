package helpers;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

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

public void saveScreenshots(String path){
    TakesScreenshot screenshotMaker = (TakesScreenshot) getDtiver();
    File screen = screenshotMaker.getScreenshotAs(OutputType.FILE);
    try {
        FileUtils.copyFile(screen,new File("path*"));
    }catch (IOException e){
        System.out.println(e.getMessage());
    }



}


    
}