package fi.testee.example.shoppinglist.web;

import fi.testee.example.shoppinglist.facade.ShoppingListFacade;
import fi.testee.example.shoppinglist.facade.transport.ShoppingListItemTO;
import fi.testee.example.shoppinglist.util.TimeService;
import fi.testee.example.shoppinglist.web.transport.ShoppingListCTO;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.UUID;

@Path("/shoppingList")
public class ShoppingListResource {
    @Inject
    private TimeService timeService;
    @EJB
    private ShoppingListFacade facade;

    @DELETE
    @Path("/{uuid}")
    public void deleteItem(@PathParam("uuid")final String uuid) {
        facade.deleteItem(uuid);
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public void addItem(final ShoppingListItemTO item) {
        facade.addItem(new ShoppingListItemTO(
                UUID.randomUUID().toString(),
                timeService.timestamp(),
                item.getTitle()
        ));
    }

    @GET
    @Produces("application/json")
    public ShoppingListCTO getItems() {
        return new ShoppingListCTO(facade.getItems());
    }
}
