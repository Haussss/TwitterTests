package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

public abstract class ElementBase {
    protected final SearchContext host;
    protected final WebElement wrappedElement ;
    protected final By locator;

    protected ElementBase(SearchContext host, By locator) {
        this.host = host;
        this.locator = locator;
        wrappedElement = host.findElement(locator);
    }
    public void click(){
        wrappedElement.click();
        return null;
    }
    public void focus(){
        wrappedElement.sendKeys("");
    }
    public boolean isDisplayed(){
        return wrappedElement.isDisplayed();
    }
    public WebElement getWrappedElement(){
        return wrappedElement;
    }
    public By getLocator(){
        return locator;
    }
    public SearchContext getHost(){
        return host;
    }
}
