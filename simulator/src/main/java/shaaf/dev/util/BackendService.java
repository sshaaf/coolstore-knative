package shaaf.dev.util;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@RegisterRestClient(configKey = "backend")
public interface BackendService {

    // http://cart-user1-cloudnativeapps.apps.cluster-dp2n6.dp2n6.sandbox2882.opentlc.com/api/cart/checkout/id-0.5958815944784861
    @POST
    @Path("/api/cart/checkout/{cartId}")
    @Produces(MediaType.APPLICATION_JSON)
    Response checkOutCart(@PathParam("cartId") String cartId, Order order);


    // http://cart-user1-cloudnativeapps.apps.cluster-dp2n6.dp2n6.sandbox2882.opentlc.com/api/cart/id-0.5958815944784861/329299/2
    @POST
    @Path("/api/cart/{cartId}/{item}/{quantity}")
    void createCart(@PathParam("cartId") String cartId, @PathParam("item") String item, @PathParam("quantity") String quantity);

}
