package at.technikum.sint.rest.dataaccess;

import at.technikum.sint.rest.base.Item;

import java.util.*;

/**
 * Created by @author
 */
public class ItemManager {

    private static Map<Integer, Item> items;
    private static int lastId;
    private static ItemManager itemsManager;

    public static ItemManager getItemManager() {
        if(itemsManager == null) {
            itemsManager = new ItemManager();
        }
        return itemsManager;
    }

    private ItemManager() {
        if(items == null) {
            items = new HashMap<Integer, Item>();
        }
    }

    public Collection<Item> getItems() {
        return items.values();
    }

    public Item getItem(int id) {
        return items.get(id);
    }

    public int addItem(Item item) {
        item.setId(lastId);
        items.put(lastId, item);
        return lastId++;
    }

    public boolean deleteItem(int id) {
        items.remove(id);
        return true;
    }

}
