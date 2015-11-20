package elements;


import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class DropDown extends HasText {
   private Select select;

    protected DropDown(SearchContext host, By locator) {
        super(host, locator);
        select = new Select(wrappedElement);
    }
    public boolean isMultiple(){
        return select.isMultiple();
    }
  //  @Override
//    public String getText(){
//        return select.getFirstSelectedOption();
//    }
//    public void selectByText(){
//        select.selectByVisibleText();
//    }
    public void selectByIndex(int index){
        select.selectByIndex(index);
    }
    public List<String> getOptions(){
        List<WebElement> options = select.getOptions();
        List<String> optionText = new ArrayList<>();
        for (WebElement option : options){
            optionText.add(option.getText());
        }
        return optionText;
    }
}
