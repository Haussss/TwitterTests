package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;

public class Link extends HasText{
    protected Link(SearchContext host, By locator) {
        super(host, locator);
    }

    public String getURL(){
        return wrappedElement.getAttribute("href");
    }

}
