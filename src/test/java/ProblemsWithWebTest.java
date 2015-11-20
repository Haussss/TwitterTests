import helpers.DataProviders;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ProblemsWithWebTest {
    private static WebDriver driver;
    private final static String BASE_URL = "http://the-internet.herokuapp.com/";
    private final static File FF_UPLOAD_EXE = new File("./src/main/resources/FF_upload.exe");
    private final static File UPLOAD_PICTURE = new File("./src/main/resources/smileys.png");
    private final static File BASIC_AUTORIZATION_EXE = new File("./src/main/resources/baw.exe");
    private static final String UPLOAD_FILE_PATH = "./src/main/resources/smileys.png";
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
        //String WAY = FF_UPLOAD_EXE.getAbsolutePath();
        //String PICTURE = UPLOAD_PICTURE.getAbsolutePath();
        Runtime.getRuntime().exec(new String[]{FF_UPLOAD_EXE.getAbsolutePath(), UPLOAD_PICTURE.getAbsolutePath()});
        driver.findElement(By.id("file-upload")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("file-submit")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".example")).getText().trim().contains("File Uploaded!"));
    }
    @Test(dataProvider = "basicautorization",dataProviderClass = DataProviders.class)
    public void bathicADTest(String login,String pswd) throws IOException, InterruptedException {
        //String bawPath = BASIC_AUTORIZATION_EXE.getAbsolutePath();
        Runtime.getRuntime().exec(new String[]{BASIC_AUTORIZATION_EXE.getAbsolutePath(), login, pswd});
        driver.findElement(By.linkText("Basic Auth")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".example")).getText().trim().contains("Congratulations! You must have the proper credentials."));
    }
    @Test
    public void uploadFileTest(){
        String absolutePash = new File (UPLOAD_FILE_PATH).getAbsolutePath();
        String fileName = new File(UPLOAD_FILE_PATH).getName();
        driver.findElement(By.linkText("File Upload")).click();
        driver.findElement(By.cssSelector("form input[type='file']")).sendKeys(absolutePash);
        driver.findElement(By.id("file-submit")).click();
        WebElement content = driver.findElement(By.id("content"));

        Assert.assertEquals(content.findElement(By.tagName("h3")).getText(),"File Uploaded!");
        Assert.assertEquals(driver.findElement(By.id("uploaded-files")).getText().trim(),fileName);
    }
}
