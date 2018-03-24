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

    public Item() {

    }

    public Item(String name, double price, String description, String manufacturer) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.manufacturer = manufacturer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"name\":\"" + name + '\"' +
                ", \"price\":" + price +
                ", \"desc\":\"" + description + '\"' +
                ", \"vendor\":\"" + manufacturer + '\"' +
                '}';
    }
}
