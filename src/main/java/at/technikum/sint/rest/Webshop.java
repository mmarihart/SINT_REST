package at.technikum.sint.rest;

/**
 * Created by @author
 */

import at.technikum.sint.rest.base.Basket;
import at.technikum.sint.rest.base.Item;
import at.technikum.sint.rest.dataaccess.BasketManager;
import at.technikum.sint.rest.dataaccess.ItemManager;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.*;

//TODO: change everything from List based solution to database solution (SQLite)

// The Java class will be hosted at the URI path "/<artifactname>/apiv1/"
@Path("/webshop/api")
public class Webshop {
    private BasketManager bManager = BasketManager.getBasketManager();
    private ItemManager iManager = ItemManager.getItemManager();

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
    public String createBasket() {
        return "{\"status\":\"success\",\"id\":" + bManager.createBasket() + "}";
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
        return b.getItems();
    }

    @PUT
    @Path("/basket/{basketid}")
    public String addBasketItem(@PathParam("basketid") int basketid, String postData) {
        try {
            JSONObject jsonObject = new JSONObject(postData);
            bManager.getBasket(basketid).addItem(iManager.getItem(jsonObject.getInt("id")), jsonObject.getInt("amount"));
            return "{\"status\":\"success\"}";
        } catch (JSONException e) {
            e.printStackTrace();
            return "{\"status\":\"error\"}";
        }
    }

    @DELETE
    @Path("/basket/{basketid}/{itemid}")
    public boolean deleteBasketItem(@PathParam("basketid") int basketid, @PathParam("itemid") int itemid) {
        bManager.getBasket(basketid).deleteItem(itemid);
        return true;
    }

    @GET
    @Path("/item")
    public String getItems() {
        return iManager.getItems().toString();
    }

    @POST
    @Path("/item")
    public String createItem(String postData) {
        try {
            JSONObject jsonObject = new JSONObject(postData);
            Item x = new Item(jsonObject.getString("name"), jsonObject.getDouble("price"), jsonObject.getString("desc"), jsonObject.getString("vendor"));
            return "{\"status\":\"success\",\"id\":" + iManager.addItem(x) + "}";

        } catch (JSONException e) {
            e.printStackTrace();
            return "{\"status\":\"error\"}";
        }
    }

    @GET
    @Path("/item/{itemid}")
    public String getItem(@PathParam("itemid") int itemId) {
        return iManager.getItem(itemId).toString();
    }

    @DELETE
    @Path("/item/{itemid}")
    public boolean deleteItem(@PathParam("itemid") int itemId) {
        return iManager.deleteItem(itemId);
    }
}
