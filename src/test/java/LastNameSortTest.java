import elements.Table;
import helpers.DriverSingleTon;
import helpers.Helpers;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import helpers.TableRows;
import static helpers.DriverSingleTon.getDriver;

public class LastNameSortTest {

    private final static String BASE_URL = "http://the-internet.herokuapp.com/tables";
    private static final int LAST_NAME_COLUMN = 0;
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
  public void LastNameSortTest(){
        List<String > unsorted = Helpers.getElementTexts(TableRows.getElementsColumnElements(1));
        Collections.sort(unsorted);
        getDriver().findElements(By.cssSelector("#table1 th")).get(0).click();
        // getDriver().findElement(By.cssSelector(".header span")).click();
        List<String > alreadysorted = Helpers.getElementTexts(TableRows.getElementsColumnElements(1));
        Assert.assertEquals(unsorted,alreadysorted);
        Collections.reverse(unsorted);
        getDriver().findElements(By.cssSelector("#table1 th")).get(0).click();
        //getDriver().findElement(By.cssSelector(".header span")).click();
        List<String > reverssorted = Helpers.getElementTexts(TableRows.getElementsColumnElements(1));
        Assert.assertEquals(unsorted, reverssorted);
    }
    @Test
    public void LastNameSortTableTest() throws Exception {

        Table table = new Table(getDriver(),By.id("table1"));
        List<String > unsorted = table.getColumnElementsText(LAST_NAME_COLUMN);
        table.getColumnElementsText(LAST_NAME_COLUMN);
        table.sort(LAST_NAME_COLUMN);
        Collections.sort(unsorted);
        Assert.assertEquals(unsorted, table.getColumnElementsText(LAST_NAME_COLUMN));
        Collections.reverse(unsorted);
//        getDriver().findElements(By.cssSelector("#table1 th")).get(0).click();
//        // getDriver().findElement(By.cssSelector(".header span")).click();
//        List<String > alreadysorted = Helpers.getElementTexts(TableRows.getElementsColumnElements(1));
//        Assert.assertEquals(unsorted,alreadysorted);
//        Collections.reverse(unsorted);
//        getDriver().findElements(By.cssSelector("#table1 th")).get(0).click();
//        //getDriver().findElement(By.cssSelector(".header span")).click();
//        List<String > reverssorted = Helpers.getElementTexts(TableRows.getElementsColumnElements(1));
//        Assert.assertEquals(unsorted,reverssorted);
    }
}
