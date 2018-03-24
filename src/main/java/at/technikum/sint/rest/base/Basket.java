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

    public void addItem(Item x, int amount) {
        for(int i = 0; i < amount; i++) {
            this.items.add(x);
        }
    }

    public Item getItem(int index) {
        return items.get(index);
    }

    public void deleteItem(int itemId) {
        List<Item> itemsToDelete = new ArrayList<Item>();
        for(Item x : items) {
            if(x.getId() == itemId) {
                itemsToDelete.add(x);
            }
        }
        for(Item x : itemsToDelete) {
            items.remove(x);
        }
    }

    public String getItems() {
        String result = "[";
        for(Item x: items) {
            result += x.toString();
        }
        return result + "]";
    }

    @Override
    public String toString() {
        return "{" +
                "\"name\":\"" + name + '\"' +
                ", \"id\":" + id +
                ", \"itemcount\":" + items.size() +
                '}';
    }
}
