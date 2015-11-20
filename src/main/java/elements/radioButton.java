package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;

/**
 * Created by Admin on 20.11.2015.
 */
public class radioButton extends Checkbox {
    protected radioButton(SearchContext host, By locator) {
        super(host, locator);
    }
}
