package fi.testee.example.shoppinglist.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.lang.System.setProperty;

public class Chrome extends AbstractBaseExtractingWebDriverFactory {

    @Override
    public WebDriver create() {
        setProperty("webdriver.chrome.driver", getDriverFile().getAbsolutePath());
        return new ChromeDriver();
    }

    @Override
    protected String getDriverName() {
        return "chromedriver";
    }
}
