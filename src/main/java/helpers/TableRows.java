package helpers;
import static helpers.DriverSingleTon.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class TableRows {
    public static List<WebElement> getElementsColumnElements(int index){
        return getDriver().findElements(By.cssSelector((String.format("#table1 tbody tr td:nth-child(%d)",index))));
}}

