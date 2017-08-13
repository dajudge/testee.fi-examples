package fi.testee.example.shoppinglist.webdriver;

import fi.testee.example.shoppinglist.WebDriverProducer;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import static java.lang.System.setProperty;

public class Chrome implements WebDriverProducer.WebDriverFactory {
    private static final Logger LOG = LoggerFactory.getLogger(Chrome.class);

    @Override
    public WebDriver create() {
        final URL url = getClass().getClassLoader().getResource("webdrivers/chromedriver/win32/chromeDriver.exe");
        setProperty("webdriver.chrome.driver", createTempFile(url).getAbsolutePath());
        return new ChromeDriver();
    }

    private File createTempFile(final URL url) {
        try {
            File tmpdir = new File(System.getProperty("java.io.tmpdir"), "testee.fi");
            tmpdir.mkdirs();
            final File file = new File(tmpdir, "chromeDriver.exe");
            if (!file.exists()) {
                LOG.info("Copying webdriver to {}", file.getAbsolutePath());
                try (
                        final InputStream is = url.openStream();
                        final OutputStream os = new FileOutputStream(file)
                ) {
                    IOUtils.copy(is, os);
                }
            } else {
                LOG.info("Re-using webdriver at {}", file.getAbsolutePath());
            }
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
