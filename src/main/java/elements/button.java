package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

public class button extends HasText{

    public button(SearchContext host, By locator) {
        super(host,locator);
    }

    public boolean isEnabled(){
       return wrappedElement.isEnabled();
    }

}
