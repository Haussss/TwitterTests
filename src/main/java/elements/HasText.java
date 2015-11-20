package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;

/**
 * Created by Admin on 20.11.2015.
 */
public abstract class HasText extends ElementBase {
    protected HasText(SearchContext host, By locator) {
        super(host, locator);
    }
    public String getText(){
        return wrappedElement.getText();
    }
}
