package at.technikum.sint.rest.dataaccess;

import at.technikum.sint.rest.base.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @author
 */
public class ItemManager {

    public static List<Item> items;

    public ItemManager() {
        if(items == null) {
            items = new ArrayList<Item>();
        }
    }

    public List getItems() {
        return items;
    }

    public Item getItem(int id) {
        return items.get(id-1);
    }

    public int addItem(Item item) {
        items.add(item);
        return items.size();
    }

    public boolean deleteItem(int id) {
        items.remove(id-1);
        return true;
    }

}
