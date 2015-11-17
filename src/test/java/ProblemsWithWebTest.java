import helpers.DataProviders;
import helpers.DriverSingleTon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sun.awt.windows.ThemeReader;

import static helpers.DriverSingleTon.getDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

import static helpers.DriverSingleTon.getDriver;

public class ProblemsWithWebTest {
    private static WebDriver driver;
    private final static String BASE_URL = "http://the-internet.herokuapp.com/";
    private final static String FF_PATH = "./src/main/resources/FF_upload.exe";
    @BeforeMethod
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        driver.get(BASE_URL);

    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
@Test

    public void autoItUploadTest() throws IOException, InterruptedException {
        driver.findElement(By.linkText("File Upload")).click();
        File eXe = new File("./src/main/resources/FF_upload.exe");
        String WAY = eXe.getAbsolutePath();
        File pict = new File("./src/main/resources/smileys.png");
        String PICTURE = pict.getAbsolutePath();
        Runtime.getRuntime().exec(new String[]{WAY, PICTURE});

        driver.findElement(By.id("file-upload")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("file-submit")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector(".example")).getText().trim().contains("File Uploaded!"));
    }
    @Test(dataProvider = "basicautorization",dataProviderClass = DataProviders.class)
    public void bathicADTest(String login,String pswd) throws IOException, InterruptedException {
        File bawExe = new File("./src/main/resources/baw.exe");
        String bawPath = bawExe.getAbsolutePath();
        Runtime.getRuntime().exec(new String[]{bawPath,login,pswd});
        driver.findElement(By.linkText("Basic Auth")).click();
        Thread.sleep(1500);
        Assert.assertTrue(driver.findElement(By.cssSelector(".example")).getText().trim().contains("Congratulations! You must have the proper credentials."));
    }
}
