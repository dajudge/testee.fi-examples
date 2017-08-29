package fi.testee.example.shoppinglist.webdriver;

import fi.testee.example.shoppinglist.WebDriverProducer;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public abstract class AbstractBaseExtractingWebDriverFactory implements WebDriverProducer.WebDriverFactory {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractBaseExtractingWebDriverFactory.class);
    private static final String OS = System.getProperty("os.name").toLowerCase();
    private static final String ARCH = System.getProperty("os.arch").toLowerCase();

    protected File getDriverFile() {
        if (isWindows()) {
            return extractDriver(getDriverName(), "win32", getDriverName() + ".exe");
        }
        if (isMac()) {
            return extractDriver(getDriverName(), "mac64", getDriverName());
        }
        if (isLinux()) {
            if (ARCH.endsWith("64")) {
                return extractDriver(getDriverName(), "linux64", getDriverName());
            } else {
                return extractDriver(getDriverName(), "linux32", getDriverName());
            }
        }
        throw new RuntimeException("Unsupported platform: " + OS + "/" + ARCH);
    }

    protected abstract String getDriverName();


    private File extractDriver(final String driver, final String platform, final String executable) {
        final URL url = getClass().getClassLoader()
                .getResource("webdrivers/" + driver + "/" + platform + "/" + executable);
        try {
            final File tmpdir = new File(System.getProperty("java.io.tmpdir"), "testee.fi");
            tmpdir.mkdirs();
            final File file = new File(tmpdir, executable);
            if (!file.exists()) {
                LOG.info("Copying webdriver {} to {}", url, file.getAbsolutePath());
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

    private static boolean isWindows() {
        return OS.indexOf("win") >= 0;
    }

    private static boolean isMac() {
        return OS.indexOf("mac") >= 0;
    }

    private static boolean isLinux() {
        return OS.indexOf("nux") >= 0;
    }
}
