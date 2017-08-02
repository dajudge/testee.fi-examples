package fi.testee.examples.cucumber;

import fi.testee.cucumber.annotation.CucumberSetup;
import fi.testee.examples.cucumber.adapter.TwitterAdapter;
import fi.testee.flyway.annotation.Flyway;
import fi.testee.h2.H2PostgresConnectionFactory;
import fi.testee.jdbc.TestDataSource;
import org.mockito.Mock;

@CucumberSetup
@TestDataSource(name = "jdbc/testDataSource", factory = H2PostgresConnectionFactory.class)
@Flyway(dataSource = "jdbc/testDataSource")
public class TestSetup {
    @Mock
    private TwitterAdapter twitterAdapter;
}
