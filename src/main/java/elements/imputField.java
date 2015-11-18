package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class imputField extends ElementBase{
    public imputField(SearchContext host, By locator) {
    super(host,locator);
    }
    public void append(String value){
        wrappedElement.sendKeys(value);
    }
    public void clear(){
        wrappedElement.clear();
    }
    public void setText(String text){
        clear();
        append(text);
    }
    public void getText(){
        wrappedElement.getAttribute("value");
    }
    public boolean isEnabled(){
        return wrappedElement.isEnabled();
    }

}
