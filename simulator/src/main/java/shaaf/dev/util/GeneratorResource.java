package shaaf.dev.util;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
public class GeneratorResource {

    @Inject
    OrderRequests orderRequests;
    @GET
    @Path("orders/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> cartCheckouts(@PathParam("number") int numberOfCartCheckouts) {
       return orderRequests.generateCarts(numberOfCartCheckouts, true);
    }

}