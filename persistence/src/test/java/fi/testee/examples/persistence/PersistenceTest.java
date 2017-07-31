package fi.testee.examples.persistence;

import fi.testee.examples.persistence.model.MyEntity;
import fi.testee.flyway.annotation.Flyway;
import fi.testee.h2.H2PostgresConnectionFactory;
import fi.testee.jdbc.TestData;
import fi.testee.jdbc.TestDataSource;
import fi.testee.jdbc.TestDataSources;
import fi.testee.jpa.TestPersistenceUnits;
import fi.testee.junit4.TestEEfi;
import fi.testee.liqiubase.annotations.Liquibase;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@TestDataSource(name = "jdbc/dataSource1", factory = H2PostgresConnectionFactory.class)
@TestDataSource(name = "jdbc/dataSource2", factory = H2PostgresConnectionFactory.class)
@Liquibase(dataSource = "jdbc/dataSource1", changeLogFile = "liquibase/changelog.xml")
@Flyway(dataSource = "jdbc/dataSource2")
@RunWith(TestEEfi.class)
public class PersistenceTest {
    @TestData
    public static void setupTestData(
            final TestDataSources dataSources,
            final TestPersistenceUnits persistenceUnits
    ) throws SQLException {
        // Insert an entity with ID 1 into "jdbc/dataSource1"
        try (final Connection c = dataSources.get("jdbc/dataSource1").getConnection()) {
            try (final PreparedStatement s = c.prepareStatement("INSERT INTO MyEntity(ID) VALUES(1)")) {
                s.executeUpdate();
            }
        }

        // The persistence.xml wires "myPersistenceUnit" to "jdbc/dataSource2", so
        // that's where the entity instance with ID 2 ends up.
        final EntityManager em = persistenceUnits.get("myPersistenceUnit");
        em.persist(new MyEntity(2L));
    }

    @PersistenceContext(unitName = "myPersistenceUnit")
    private EntityManager entityManager;
    @Resource(mappedName = "jdbc/dataSource1")
    private DataSource dataSource;

    @Test
    public void can_test_with_jpa() {
        // Entity with ID 2 is available
        assertNotNull(entityManager.find(MyEntity.class, 2l));
    }

    @Test
    public void can_test_with_jdbc() throws SQLException {
        try (
                final Connection c = dataSource.getConnection();
                final PreparedStatement s = c.prepareStatement("SELECT * FROM MyEntity WHERE ID=1");
                final ResultSet rs = s.executeQuery()
        ) {
            // Entity with ID 1 is available
            assertTrue(rs.next());
        }
    }
}
