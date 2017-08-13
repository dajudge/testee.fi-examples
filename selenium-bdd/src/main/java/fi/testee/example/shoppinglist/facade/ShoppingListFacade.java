package fi.testee.example.shoppinglist.facade;

import fi.testee.example.shoppinglist.business.ShoppingListService;
import fi.testee.example.shoppinglist.facade.transport.ShoppingListItemTO;
import fi.testee.example.shoppinglist.model.ShoppingListItem;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Collection;
import java.util.stream.Collectors;

@Stateless
public class ShoppingListFacade {
    @EJB
    private ShoppingListService shoppingListService;

    public void addItem(final ShoppingListItemTO item) {
        shoppingListService.create(new ShoppingListItem(
                item.getUuid(),
                item.getTimestamp(),
                item.getTitle()
        ));
    }

    public Collection<ShoppingListItemTO> getItems() {
        return shoppingListService.getAll().stream()
                .map(it -> new ShoppingListItemTO(it.getUuid(), it.getTimestamp(), it.getTitle()))
                .collect(Collectors.toSet());
    }

    public void deleteItem(final String uuid) {
        shoppingListService.deleteById(uuid);
    }
}
