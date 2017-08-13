package fi.testee.example.shoppinglist.webdriver;

import fi.testee.example.shoppinglist.WebDriverProducer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static com.gargoylesoftware.htmlunit.BrowserVersion.BEST_SUPPORTED;

public class HtmlUnit implements WebDriverProducer.WebDriverFactory {
    @Override
    public WebDriver create() {
        return new HtmlUnitDriver(BEST_SUPPORTED, true);
    }
}
