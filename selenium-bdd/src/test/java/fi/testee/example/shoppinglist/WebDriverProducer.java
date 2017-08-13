package fi.testee.example.shoppinglist;

import fi.testee.example.shoppinglist.webdriver.HtmlUnit;
import org.openqa.selenium.WebDriver;

import javax.enterprise.inject.Produces;

import static java.lang.Runtime.getRuntime;

public class WebDriverProducer {
    private static WebDriver webDriver;

    public interface WebDriverFactory {
        WebDriver create();
    }

    @Produces
    public WebDriver webDriver() {
        synchronized (WebDriverProducer.class) {
            if (webDriver == null) {
                webDriver = getWebDriverFactory().create();
                getRuntime().addShutdownHook(new Thread(webDriver::close));
            }
            return webDriver;
        }
    }

    private WebDriverFactory getWebDriverFactory() {
        final String factoryClassName = System.getProperty(getClass().getName() + ".factoryClass");
        if (null == factoryClassName) {
            return new HtmlUnit();
        }
        try {
            final String fqn = HtmlUnit.class.getPackage().getName() + "." + factoryClassName;
            return (WebDriverFactory) Class.forName(fqn).newInstance();
        } catch (final InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new RuntimeException("Unable to instantiate web driver factory", e);
        }
    }
}
