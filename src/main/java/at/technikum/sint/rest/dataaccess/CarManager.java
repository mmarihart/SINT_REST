package at.technikum.sint.rest.dataaccess;

import at.technikum.sint.rest.base.Car;

import java.util.*;

/**
 * Created by @author
 */
public class CarManager {

    private static Map<Integer, Car> cars;
    private static int lastId;
    private static CarManager carManager;

    public static CarManager getItemManager() {
        if (carManager == null) {
            carManager = new CarManager();
        }
        return carManager;
    }

    private CarManager() {
        if (cars == null) {
            cars = new HashMap<Integer, Car>();
        }
    }

    public Collection<Car> getCars() {
        return cars.values();
    }

    public Car getCar(int id) {
        return cars.get(id);
    }

    public int addCar(String name, String manufacturer, String type, int seats, double kw, String lat, String lon, double price, String currency) {
        //TODO: include Florie's currency translation
        Car x = new Car(lastId, name, manufacturer, type, seats, kw, true, lat, lon, price, null);
        cars.put(lastId, x);
        return lastId++;
    }

    public boolean deleteCar(int id) {
        cars.remove(id);
        return true;
    }

    public void rentCar(int id, Date returnDate) throws Exception {
        Car x = (Car) cars.get(id);
        if (x.isAvailable()) {
            x.setAvailable(false);
            x.setAvailableOn(returnDate);
        } else {
            throw new Exception("Car already rented");
        }
    }

    public void returnCar(int id, String lon, String lat) throws Exception {
        Car x = (Car) cars.get(id);
        if (!x.isAvailable()) {
            x.setAvailable(true);
            x.setLon(lon);
            x.setLat(lat);
            x.setAvailableOn(Calendar.getInstance().getTime());
        } else {
            throw new Exception("Car not rented");
        }
    }

    private Collection<Car> searchByName(Collection<Car> cars, String name) {
        if (name == null) {
            return cars;
        }
        List<Car> result = new ArrayList<Car>();
        for (Car x : cars) {
            if (name.equals(x.getName())) {
                result.add(x);
            }
        }
        return result;
    }

    private Collection<Car> searchByManufacturer(Collection<Car> cars, String manufacturer) {
        if (manufacturer == null) {
            return cars;
        }
        List<Car> result = new ArrayList<Car>();
        for (Car x : cars) {
            if (manufacturer.equals(x.getManufacturer())) {
                result.add(x);
            }
        }
        return result;
    }

    private Collection<Car> searchByType(Collection<Car> cars, String type) {
        if (type == null) {
            return cars;
        }
        List<Car> result = new ArrayList<Car>();
        for (Car x : cars) {
            if (type.equals(x.getType())) {
                result.add(x);
            }
        }
        return result;
    }

    private Collection<Car> searchBySeats(Collection<Car> cars, int seats) {
        if (seats == 0) {
            return cars;
        }
        List<Car> result = new ArrayList<Car>();
        for (Car x : cars) {
            if (seats == x.getSeats()) {
                result.add(x);
            }
        }
        return result;
    }

    private Collection<Car> searchByMinPrice(Collection<Car> cars, double price) {
        if (price == 0) {
            return cars;
        }
        List<Car> result = new ArrayList<Car>();
        for (Car x : cars) {
            if (price <= x.getPrice()) {
                result.add(x);
            }
        }
        return result;
    }

    private Collection<Car> searchByMaxPrice(Collection<Car> cars, double price) {
        if (price == 0) {
            return cars;
        }
        List<Car> result = new ArrayList<Car>();
        for (Car x : cars) {
            if (price >= x.getPrice()) {
                result.add(x);
            }
        }
        return result;
    }

    private Collection<Car> searchByDate(Collection<Car> cars, Date date) {
        if (date == null) {
            return cars;
        }
        List<Car> result = new ArrayList<Car>();
        for (Car x : cars) {
            if (x.getAvailableOn().before(date)) {
                result.add(x);
            }
        }
        return result;
    }

    private Collection<Car> searchByKw(Collection<Car> cars, double kw) {
        if (kw == 0) {
            return cars;
        }
        List<Car> result = new ArrayList<Car>();
        for (Car x : cars) {
            if (kw == x.getKw()) {
                result.add(x);
            }
        }
        return result;
    }

    public Collection<Car> search(String type, int seats, double priceMax, double priceMin, Date date, double kw, String name, String manufacturer) {
        Collection<Car> result = cars.values();
        result = searchByName(result, name);
        result = searchByManufacturer(result, manufacturer);
        result = searchByType(result, type);
        result = searchBySeats(result, seats);
        result = searchByMaxPrice(result, priceMax);
        result = searchByMinPrice(result, priceMin);
        result = searchByDate(result, date);
        result = searchByKw(result, kw);
        return result;
    }

}
