package fi.testee.examples.cucumber.dataaccess;

import fi.testee.examples.cucumber.model.Message;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.Set;

@Singleton
public class MessageDao {
    @PersistenceContext(unitName = "testUnit")
    private EntityManager entityManager;

    public void persist(final Message message) {
        entityManager.persist(message);
    }

    public Set<Message> getAll() {
        final TypedQuery<Message> query = entityManager.createQuery("SELECT m FROM Message m", Message.class);
        return new HashSet<>(query.getResultList());
    }
}
