package fi.testee.example.shoppinglist.dataaccess;

import fi.testee.example.shoppinglist.model.ShoppingListItem;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ShoppingListItemDAO {
    @PersistenceContext(unitName = "shoppingListUnit")
    private EntityManager entityManager;

    public List<ShoppingListItem> getAll() {
        return entityManager.createQuery("SELECT i FROM ShoppingListItem i", ShoppingListItem.class)
                .getResultList();
    }

    public void create(final ShoppingListItem item) {
        entityManager.persist(item);
    }

    public ShoppingListItem findById(final String uuid) {
        return entityManager.find(ShoppingListItem.class, uuid);
    }

    public void delete(final ShoppingListItem item) {
        entityManager.remove(item);
    }
}
