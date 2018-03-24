package at.technikum.sint.rest.dataaccess;

import at.technikum.sint.rest.base.Basket;

import java.util.*;

/**
 * Created by @author
 */
public class BasketManager {
    private static Map<Integer, Basket> baskets;
    private static int lastId;

    private static BasketManager basketManager;

    public static BasketManager getBasketManager() {
        if(basketManager == null) {
            basketManager = new BasketManager();
        }
        return basketManager;
    }

    private BasketManager() {
        if(baskets == null) {
            baskets = new HashMap<Integer, Basket>();
        }
    }

    public Collection<Basket> getAllBaskets() {
        return baskets.values();
    }

    private int createBasket(String name) {
        Basket b = new Basket(baskets.size(), name);
        baskets.put(lastId, b);
        return lastId++;
    }

    public int createBasket() {
        return createBasket(null);
    }

    public boolean deleteBasket(int basketId) {
        baskets.remove(basketId);
        return true;
    }

    public Basket getBasket(int id) {
        return baskets.get(id);
    }

}
