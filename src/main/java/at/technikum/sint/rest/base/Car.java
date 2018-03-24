package at.technikum.sint.rest.base;

import java.util.Date;

/**
 * Created by @author
 */
public class Car {

    private int id;
    private String name;
    private String manufacturer;
    private String type;
    private int seats;
    private double kw;
    private boolean available;
    private String lat;
    private String lon;
    private double price;
    private Date availableOn;

    public Car(int id, String name, String manufacturer, String type, int seats, double kw, boolean available, String lat, String lon, double price, Date availableOn) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.type = type;
        this.seats = seats;
        this.kw = kw;
        this.available = available;
        this.lat = lat;
        this.lon = lon;
        this.price = price;
        this.availableOn = availableOn;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public void setAvailableOn(Date availableOn) {
        this.availableOn = availableOn;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getType() {
        return type;
    }

    public int getSeats() {
        return seats;
    }

    public double getKw() {
        return kw;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public double getPrice() {
        return price;
    }

    public Date getAvailableOn() {
        return availableOn;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"name\":\"" + name + '\"' +
                ", \"manufacturer\":\"" + manufacturer + '\"' +
                ", \"type\":\"" + type + '\"' +
                ", \"seats\":\"" + seats + '\"' +
                ", \"kw\":\"" + kw + '\"' +
                ", \"available\":\"" + available + '\"' +
                ", \"lat\":\"" + lat + '\"' +
                ", \"lon\":\"" + lon + '\"' +
                ", \"price\":" + price +
                ", \"avaiableOn\":\"" + availableOn + '\"' +
                '}';
    }

    public String toString(String currency) {
        return "{" +
                "\"id\":" + id +
                ", \"name\":\"" + name + '\"' +
                ", \"manufacturer\":\"" + manufacturer + '\"' +
                ", \"type\":\"" + type + '\"' +
                ", \"seats\":\"" + seats + '\"' +
                ", \"kw\":\"" + kw + '\"' +
                ", \"available\":\"" + available + '\"' +
                ", \"lat\":\"" + lat + '\"' +
                ", \"lon\":\"" + lon + '\"' +
                ", \"price\":" + getTranslatedPrice(currency) +
                ", \"avaiableOn\":\"" + availableOn + '\"' +
                '}';
    }

    private double getTranslatedPrice(String currency) {
        //todo: call Florie's functionality
        return price;
    }
}