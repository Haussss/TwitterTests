import helpers.DriverSingleTon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static helpers.DriverSingleTon.getDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

import static helpers.DriverSingleTon.getDriver;

public class ProblemsWithWebTest {
    private static WebDriver driver;
    private final static String BASE_URL = "http://the-internet.herokuapp.com/";
    private static final String WAY = "./src/main/resources/FF_upload.exe";
    private static final String PICTURE_ONE = "./src/main/resources/smileys.png";
    private static final String PICTURE_TWO = "D:\\kartinka.png";
    private static final String PICTURE_TREE = "D:\\wall_y15b85.png";
    @BeforeMethod
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
      //  driver.get(BASE_URL);
        driver.get("http://the-internet.herokuapp.com/upload");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
@Test

    public void autoItUploadTest() throws IOException, InterruptedException {
        Runtime.getRuntime().exec(new String[]{WAY, PICTURE_ONE});

        driver.findElement(By.id("file-upload")).click();
        driver.findElement(By.id("file-submit")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".example")).getText().contains("File Uploaded!"));
    }
}
