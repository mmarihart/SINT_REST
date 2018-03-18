package at.technikum.sint.rest.dataaccess;

import at.technikum.sint.rest.base.Basket;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @author
 */
public class BasketManager {
    public static List<Basket> baskets;


    public BasketManager() {
        if(baskets == null) {
            baskets = new ArrayList<Basket>();
        }
    }

    public List<Basket> getAllBaskets() {
        return baskets;
    }

    public int createBasket(String name) {
        Basket b = new Basket(baskets.size(), name);
        baskets.add(b);
        return baskets.size();
    }

    public int createBasket() {
        return createBasket(null);
    }

    public boolean deleteBasket(int basketId) {
        baskets.remove(basketId-1);
        return true;
    }

    public Basket getBasket(int id) {
        return baskets.get(id-1);
    }

}
