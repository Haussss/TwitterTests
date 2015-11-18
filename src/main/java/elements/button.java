package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

public class button extends ElementBase{

    public button(SearchContext host, By locator) {
        super(host,locator);
    }
    public void click(){
        wrappedElement.click();
    }
    public void getText(){
        wrappedElement.getText();
    }
    public boolean isEnabled(){
       return wrappedElement.isEnabled();
    }

}
