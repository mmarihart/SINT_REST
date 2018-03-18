package at.technikum.sint.rest;

/**
 * Created by @author
 */

import at.technikum.sint.rest.base.Basket;
import at.technikum.sint.rest.base.Item;
import at.technikum.sint.rest.dataaccess.BasketManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//TODO: change everything from List based solution to database solution (SQLite)

// The Java class will be hosted at the URI path "/<artifactname>/apiv1/"
@Path("/webshop/api")
public class Webshop {
    BasketManager bManager = new BasketManager();

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getClichedMessage() {
        // Return some cliched textual content
        return "Hello TE123ST123";
    }

    @GET
    @Path("/basket")
    public String getBaskets() {
        return bManager.getAllBaskets().toString();
    }

    @POST
    @Path("/basket")
    public int createBasket() {

        return bManager.createBasket();
    }

    @PUT
    @Path("/basket")
    public String addItem() {
        return "BasketAddItem";
    }

    @DELETE
    @Path("/basket/{basketid}")
    public boolean deleteBasket(@PathParam("basketid") int basketid) {
        bManager.deleteBasket(basketid);
        return true;
    }

    @GET
    @Path("/basket/{basketid}")
    public String getBasketItems(@PathParam("basketid") int basketid) {
        Basket b = bManager.getBasket(basketid);
        return b.getItems().toString();
    }

    @GET
    @Path("/item")
    public Item getItems() {
        return null;
    }
}
