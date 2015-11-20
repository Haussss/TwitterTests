package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;

public class Checkbox extends ElementBase{

    protected Checkbox(SearchContext host, By locator) {
        super(host, locator);
    }
    public boolean isSelected(){
        return wrappedElement.isSelected();
    }
    public void check(){
        setValue(true);
    }
    public void uncheck(){
        setValue(false);
    }
    private void setValue(boolean expectedValue){
        if(isSelected()!= expectedValue){
            click();
        }
    }
}
