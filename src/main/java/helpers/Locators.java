package helpers;

import org.openqa.selenium.By;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Locators {
    private final static Properties LOKATORS;

    private enum LocatorType {
        id, name, css, xpath, tag
    }

    static {
        LOKATORS = new Properties();
        try (InputStream in = Locators.class.getResourceAsStream("/locators.properties")) {
            LOKATORS.load(in);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static By getByText(String text) {
        String xpath = LOKATORS.getProperty("text.tmp");
        return By.xpath(String.format(xpath, text));
    }

    public static By get(String templateName, String value) {
        String template = LOKATORS.getProperty(templateName);
        String locatorString = String.format(template, value);
        return getLocator(locatorString);
    }

    public static By get(String elementKey) {
        String locatorString = LOKATORS.getProperty(elementKey);
        return getLocator(locatorString);
    }

    private static By getLocator(String locatorString) {
        String[] locatorParts = locatorString.split("=", 2);
        LocatorType locatorType = LocatorType.valueOf(locatorParts[0]);
        String selector = locatorParts[1];
        switch (locatorType) {
            case id:
                return By.id(selector);
            case name:
                return By.name(selector);
            case css:
                return By.cssSelector(selector);
            case xpath:
                return By.xpath(selector);
            case tag:
                return By.tagName(selector);
            default:
                throw new UnsupportedOperationException("Unsupporter locator type used: " + locatorType);
        }
    }
}
