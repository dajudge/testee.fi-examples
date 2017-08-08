package fi.testee.examples.junit5;

import fi.testee.junit5.TestEEfi;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestEEfi.class)
public class JUnit5Test {
    @Test
    public void some_test_method() {

    }
}
