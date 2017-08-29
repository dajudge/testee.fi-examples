package fi.testee.example.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("resource")
public class MyResource {

    @Inject
    private MyFacade myFacade;

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response getResponse(final Request request) {
        return new Response(myFacade.perform(request.getValue()));
    }
}
