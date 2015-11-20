package elements;

import helpers.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Table extends ElementBase{
    public Table(SearchContext host, By locator) {
        super(host, locator);
    }
    public  List<WebElement> getColumnElements(int index){
        return wrappedElement.findElements(By.cssSelector((String.format("td:nth-child(%d)", ++index))));
    }
    public List<WebElement> getRowElements(int index){
        return wrappedElement
                .findElements(By.cssSelector("tbody tr")).get(index).findElements(By.tagName("td"));
    }
    public int getColumnNumber(){
        return getColumnElements(0).size();
    }
    public int getRowNumber(){
        return getRowElements(0).size();
    }
    public List<List<WebElement>> getRows(){
        List<List<WebElement>> rows = new ArrayList<>();
        for (int i = 0;i<getRowNumber();i++){
            rows.add(getRowElements(i));
        }
        return rows;
    }
    public List<List<WebElement>> getColumns(){
        List<List<WebElement>> colums = new ArrayList<>();
    for (int i = 0;i<getColumnNumber();i++){
        colums.add(getColumnElements(i));
    }
    return colums;
}

    public List<WebElement> getHeaderElements(){
        return wrappedElement.findElements(By.cssSelector("th"));
    }
    public WebElement getHeader(int index){
        return getHeaderElements().get(index);
    }
    public WebElement getHeader(String  name) throws Exception {
        for(WebElement header : getHeaderElements()){
            if (header.getText().equals(name)){
                return header;
            }
        }
        throw new Exception("No such header"+name);
    }
    public List<String> getHeaderElementsText(){
       return Helpers.getElementTexts(getHeaderElements());
    }

    public String getHeaderText(int index){
        return getHeader(index).getText();
    }
    public List<String> getRowElementsText(int index){
        return Helpers.getElementTexts(getRowElements(index));

    }
    public List<String> getColumnElementsText(int index){
        return Helpers.getElementTexts(getColumnElements(index));

    }
    public List<List<String>> getRowsText(){
        List<List<String>> texts = new ArrayList<>();
        for (int i = 0;i<getRowNumber();i++){
            texts.add(getRowElementsText(i));
        }
        return texts;
    }
    public List<List<String>> getColumnText(){
        List<List<String>> texts = new ArrayList<>();
        for (int i = 0;i<getColumnNumber();i++){
            texts.add(getColumnElementsText(i));
        }
        return texts;
    }

    public WebElement getCell(int x,int y){
        return getRowElements(x).get(y);
    }
    public String getCellText(int x,int y){
        return getCell(x, y).getText();
    }

    public void sort(String headerName) throws Exception {
        getHeader(headerName).click();
    }
    public void sort(int index) throws Exception {
        getHeader(index).click();
    }
}
