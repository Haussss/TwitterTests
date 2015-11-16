import helpers.DriverSingleTon;
import helpers.WebDriverUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static helpers.DriverSingleTon.getDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ActionsTest {
    private WebDriver driver;
    private final static String BASE_URL = "http://the-internet.herokuapp.com/";


    @BeforeMethod
    public void setup() {

        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        getDriver().manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        getDriver().get(BASE_URL);
    }

    @AfterMethod
    public void teardown() {
        DriverSingleTon.getDriver().quit();
    }

    @Test
    public void sendKeysTest() throws InterruptedException {
        getDriver().findElement(By.linkText("Key Presses")).click();
        Actions actions = new Actions(getDriver());

        for (int i = 80; i < 90; i++) {
            String enterValue = (String.valueOf((char) i));
            actions.sendKeys(enterValue).perform();
            //        actions.sendKeys(Keys.CONTROL + "a"); //hotkey
            actions.contextClick();
            Thread.sleep(500);
            WebElement displayed = getDriver().findElement(By.id("result"));
            Assert.assertEquals(displayed.getText(), "You entered: " + enterValue);
            Thread.sleep(500);

        }

    }

    @Test
    public void contextMenu() throws InterruptedException {

        getDriver().findElement(By.linkText("Context Menu")).click();
        Actions actions = new Actions(getDriver());
        WebElement contextButton = getDriver().findElement(By.id("hot-spot"));

        actions
                .contextClick(contextButton)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER).perform();
        Thread.sleep(1500);
        Alert alert = getDriver().switchTo().alert();
        Assert.assertEquals(alert.getText(), "You selected a context menu");
        alert.accept();
        Assert.assertFalse(WebDriverUtils.isAlertPresent(getDriver()), "Alert isn't presented");
    }

    @Test
    public void hoverTest() {
        getDriver().findElement(By.linkText("Hovers")).click();
        Actions action = new Actions(getDriver());
        List<WebElement> hoverLists = getDriver().findElements(By.cssSelector(".figure"));
        int counter = 1;
        for (WebElement hoverList : hoverLists) {
            action.moveToElement(hoverList).click().perform();
            Assert.assertTrue(hoverList.findElement(By.tagName("h5")).isDisplayed());
            Assert.assertEquals(hoverList.findElement(By.tagName("h5")).getText(), "name: user" + counter);
            counter++;
        }

    }

    @Test
    public void dragAndDropTest() throws InterruptedException {
        getDriver().findElement(By.linkText("Drag and Drop")).click();
        WebElement source = getDriver().findElement(By.id("column-a"));
        WebElement target = getDriver().findElement(By.id("column-b"));
        Actions action = new Actions(getDriver());
        // action.dragAndDrop(source,target).perform();
        action
                .clickAndHold(source)
                        //  .moveToElement(target)
                .moveByOffset(200, 300)
                .release()
                .perform();
        action.moveToElement(source).perform();
        Thread.sleep(2000);
        action.click(source);
        Thread.sleep(2000);
        action.clickAndHold(source).perform();
        Thread.sleep(2000);
        action.moveToElement(target).perform();
        Thread.sleep(2000);
        action.release().perform();
        Assert.assertEquals(source.findElement(By.tagName("header")).getText(), "B");
        Assert.assertEquals(target.findElement(By.tagName("header")).getText(), "A");

    }

    @Test
    public void alertTest() throws InterruptedException {
        getDriver().findElement(By.linkText("JavaScript Alerts")).click();
        //  WebElement blablabla = driver.findElement(By.id("result"));
        // blablabla.click();
        WebElement alertButton = getDriver().findElement(By.cssSelector("button[onclick = 'jsAlert()']"));
        alertButton.click();
        Assert.assertTrue(WebDriverUtils.isAlertPresent(getDriver()), "Alert isn't presented");
        Alert alert = getDriver().switchTo().alert();
        Assert.assertEquals(alert.getText(), "I am a JS Alert");
        alert.accept();
        Thread.sleep(3500);
        Assert.assertEquals(getDriver().findElement(By.id("result")).getText(), "You successfuly clicked an alert");

    }

    @Test
    public void confirmTest() {
        getDriver().findElement(By.linkText("JavaScript Alerts")).click();
        WebElement confirmButton = getDriver().findElement(By.cssSelector("button[onclick = 'jsConfirm()']"));
        confirmButton.click();
        Alert alert = getDriver().switchTo().alert();
        Assert.assertEquals(alert.getText(), "I am a JS Confirm");
        alert.accept();
        Assert.assertEquals(getDriver().findElement(By.id("result")).getText(), "You clicked: Ok");
        confirmButton.click();
        getDriver().switchTo().alert();
        Assert.assertEquals(alert.getText(), "I am a JS Confirm");
        alert.dismiss();
        Assert.assertEquals(getDriver().findElement(By.id("result")).getText(), "You clicked: Cancel");
    }

    @Test
    public void promptTest() {
        getDriver().findElement(By.linkText("JavaScript Alerts")).click();
        WebElement promptButton = getDriver().findElement(By.cssSelector("button[onclick = 'jsPrompt()']"));
        promptButton.click();
        Alert alert = getDriver().switchTo().alert();
        Assert.assertEquals(alert.getText(), "I am a JS prompt");
        alert.accept();
        Assert.assertEquals(getDriver().findElement(By.id("result")).getText(), "You entered:");
        promptButton.click();
        getDriver().switchTo().alert();
        alert.dismiss();
        Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: null");
        promptButton.click();
        driver.switchTo().alert();
        alert.sendKeys("Bla Bla BLa");
        alert.accept();
        Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: Bla Bla BLa");
    }

    @Test
    public void tabsTest() {
        driver.findElement(By.linkText("Multiple Windows")).click();
        driver.findElement(By.cssSelector("#content a")).click();
        List<String> handles = new ArrayList<>(driver.getWindowHandles());
        Assert.assertEquals(handles.size(), 2);
        driver.switchTo().window(handles.get(1));
        Assert.assertEquals(driver.getCurrentUrl(), "http://the-internet.herokuapp.com/windows/new");
        Assert.assertEquals(driver.getTitle(), "New Window");
        WebElement heading = driver.findElement(By.cssSelector("div.example h3"));
        Assert.assertEquals(heading.getText(), "New Window");
        driver.close();
        driver.switchTo().window((handles.get(0)));
        Assert.assertEquals(driver.getWindowHandles().size(), 1);
        Assert.assertEquals(driver.getCurrentUrl(), "http://the-internet.herokuapp.com/windows");
    }

    @Test
    public void frameTest() {
        driver.findElement(By.linkText("Frames")).click();
        driver.findElement(By.linkText("Nested Frames")).click();
        driver.switchTo().frame("frame-bottom");
        Assert.assertEquals(driver.findElement(By.tagName("body")).getText().trim(), "BOTTOM");
        driver.switchTo().defaultContent();
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-left");
        Assert.assertEquals(driver.findElement(By.tagName("body")).getText().trim(), "LEFT");
        driver.switchTo().parentFrame();
        //  driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-right");
        Assert.assertEquals(driver.findElement(By.tagName("body")).getText().trim(), "RIGHT");
        driver.switchTo().parentFrame();
        // driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-middle");
        Assert.assertEquals(driver.findElement(By.tagName("body")).getText().trim(), "MIDDLE");
    }

    @Test
    public void iframeTest() throws InterruptedException {
        Actions action = new Actions(driver);
        driver.manage().window().setSize(new Dimension(600, 400));
        driver.findElement(By.linkText("Frames")).click();
        driver.findElement(By.linkText("iFrame")).click();
        driver.switchTo().frame("mce_0_ifr");
        driver.findElement(By.id("tinymce")).click();
        action
                .sendKeys("AAA")
                .sendKeys(Keys.ENTER)
                .sendKeys("BBB")
                .sendKeys(Keys.ENTER)
                .sendKeys("CCC")
                .sendKeys(Keys.ENTER)
                .perform();
        action.keyDown(Keys.CONTROL).sendKeys("a").build().perform();
        driver.switchTo().defaultContent();
        driver.findElement(By.id("mceu_10")).click();
        Thread.sleep(5000);
        Assert.assertEquals(driver.findElement(By.id("mceu_29")).getText().trim(), "ol » li");
        // ((JavascriptExecutor)driver).executeScript("");
    }

}