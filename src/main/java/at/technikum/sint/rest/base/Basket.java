package at.technikum.sint.rest.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @author
 */
public class Basket {

    private String name;
    private int id;

    private List<Item> items;

    public Basket(int id, String name) {
        this.id = id;
        this.name = name;
        this.items = new ArrayList<Item>();
    }

    public void addItem(Item x) {
        this.items.add(x);
    }

    public Item getItem(int index) {
        return items.get(index);
    }

    public List<Item> getItems() {
        return items;
    }

}
