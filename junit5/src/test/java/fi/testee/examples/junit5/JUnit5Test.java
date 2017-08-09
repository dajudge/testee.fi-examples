package fi.testee.examples.junit5;

import fi.testee.junit5.TestEEfi;
import fi.testee.util.resourcedef.ResourceDef;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(TestEEfi.class)
public class JUnit5Test {
    @ResourceDef
    @Mock
    private ManagedExecutorService managedExecutorService;

    @Inject
    private MySessionBean subject;

    @Test
    public void some_test_method() {
        assertNotNull(subject.getExecutorService());
    }
}
