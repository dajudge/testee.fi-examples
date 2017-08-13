package fi.testee.example.shoppinglist.business;

import fi.testee.example.shoppinglist.dataaccess.ShoppingListItemDAO;
import fi.testee.example.shoppinglist.model.ShoppingListItem;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ShoppingListService {
    @EJB
    private ShoppingListItemDAO dao;

    public List<ShoppingListItem> getAll() {
        return dao.getAll();
    }

    public void create(final ShoppingListItem item) {
        dao.create(item);
    }

    public void deleteById(final String uuid) {
        dao.delete(dao.findById(uuid));
    }
}
