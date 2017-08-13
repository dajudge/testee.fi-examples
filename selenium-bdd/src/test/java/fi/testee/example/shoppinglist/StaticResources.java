package fi.testee.example.shoppinglist;

import fi.testee.rest.AutoDetectingStaticResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.Consumer;

import static java.util.Arrays.asList;
import static org.apache.commons.io.IOUtils.copy;

public class StaticResources extends AutoDetectingStaticResourceResolver {
    private static final Logger LOG = LoggerFactory.getLogger(StaticResources.class);

    @Override
    protected Consumer<OutputStream> resolveResource(final String path) {
        final File file = determine(path);
        LOG.trace("{}", file.getAbsolutePath());
        if (!file.exists()) {
            return null;
        }
        return os -> {
            try (final InputStream is = new FileInputStream(file)) {
                copy(is, os);
            } catch (final IOException e) {
                throw new RuntimeException("Failed to load resource", e);
            }
        };
    }

    private File determine(final String path) {
        // This is most likely not the most pretty way to resolve resources, but it work both
        // in IntelliJ and gradle
        File rootDir = new File(".").getAbsoluteFile();
        while (asList("selenium-bdd", ".").contains(rootDir.getName())) {
            rootDir = rootDir.getParentFile();
        }
        return new File(rootDir, "selenium-bdd/src/main/webapp/static" + path);
    }
}
