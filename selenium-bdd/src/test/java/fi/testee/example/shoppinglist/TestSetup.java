package fi.testee.example.shoppinglist;

import fi.testee.cucumber.annotation.CucumberSetup;
import fi.testee.flyway.annotation.Flyway;
import fi.testee.h2.H2PostgresConnectionFactory;
import fi.testee.jdbc.TestDataSource;

@CucumberSetup
@TestDataSource(name = "jdbc/shoppingListDB", factory = H2PostgresConnectionFactory.class)
@Flyway(dataSource = "jdbc/shoppingListDB")
public class TestSetup {

}
