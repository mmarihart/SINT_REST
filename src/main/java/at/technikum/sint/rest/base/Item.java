package at.technikum.sint.rest.base;

/**
 * Created by @author
 */
public class Item {

    private int id;
    private String name;
    private double price;
    private String description;
    private String manufacturer;

    public Item(int id) {
        this.id = id;
    }

    public Item(int id, String name, double price, String description, String manufacturer) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.manufacturer = manufacturer;
    }

}
