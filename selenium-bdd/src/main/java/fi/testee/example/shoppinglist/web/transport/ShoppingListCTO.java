package fi.testee.example.shoppinglist.web.transport;

import fi.testee.example.shoppinglist.facade.transport.ShoppingListItemTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ShoppingListCTO {
    private List<ShoppingListItemTO> items;

    public ShoppingListCTO() {
    }

    public ShoppingListCTO(final Collection<ShoppingListItemTO> items) {
        this.items = new ArrayList<>(items);
    }

    public List<ShoppingListItemTO> getItems() {
        return items;
    }

    public void setItems(final List<ShoppingListItemTO> items) {
        this.items = items;
    }
}
