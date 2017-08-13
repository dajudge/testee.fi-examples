package fi.testee.example.shoppinglist.webdriver;

import fi.testee.example.shoppinglist.WebDriverProducer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.URL;

import static fi.testee.utils.UrlUtils.toFile;
import static java.lang.System.setProperty;

public class Chrome implements WebDriverProducer.WebDriverFactory {
    @Override
    public WebDriver create() {
        final URL url = getClass().getClassLoader().getResource("webdrivers/chromedriver/win32/chromeDriver.exe");
        setProperty("webdriver.chrome.driver", toFile(url).getAbsolutePath());
        return new ChromeDriver();
    }
}
