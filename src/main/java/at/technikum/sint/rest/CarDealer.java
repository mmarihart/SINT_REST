package at.technikum.sint.rest;

/**
 * Created by @author
 */

import at.technikum.sint.rest.base.Car;
import at.technikum.sint.rest.dataaccess.CarManager;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

//TODO: change everything from List based solution to database solution (SQLite)

// The Java class will be hosted at the URI path "/<artifactname>/apiv1/"
@Path("/cardealer/api")
public class CarDealer {
    private CarManager cManager = CarManager.getItemManager();

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getClichedMessage() {
        // Return some cliched textual content
        return "Hello TE123ST123";
    }

    @GET
    @Path("/car")
    public String getCars(@DefaultValue("USD") @QueryParam("currency") String currency) {
        String resultString = "[";
        for (Car c : cManager.getCars()) {
            resultString += c.toString() + ",";
        }
        resultString = resultString.substring(0, resultString.length() - 1) + "]";
        return "{\"success\":" + true + ",\"result\":" + resultString + "}";
    }

    @POST
    @Path("/car")
    public String createCar(String postData, @DefaultValue("USD") @QueryParam("currency") String currency) {
        try {
            JSONObject jsonObject = new JSONObject(postData);
            return "{\"success\":" + true + ",\"id\":" +
                    cManager.addCar(jsonObject.getString("name"),
                            jsonObject.getString("manufacturer"),
                            jsonObject.getString("type"),
                            jsonObject.getInt("seats"),
                            jsonObject.getDouble("kw"),
                            jsonObject.getString("lat"),
                            jsonObject.getString("lon"),
                            jsonObject.getDouble("price"), currency) +
                    "}";
        } catch (JSONException e) {
            e.printStackTrace();
            return "{\"success\":" + false + "}";
        }
    }

    @GET
    @Path("/car/{carid}")
    public String getCar(@PathParam("carid") int carid, @DefaultValue("USD") @QueryParam("currency") String currency) {
        Car c = cManager.getCar(carid);
        return "{\"success\":" + true + ", \"result\":" + c.toString(currency);
    }

    @DELETE
    @Path("/car/{carid}")
    public String deleteCar(@PathParam("carid") int carid) {
        cManager.deleteCar(carid);
        return "{\"success\":" + true + "}";
    }

    @POST
    @Path("/car/{carid}/rent")
    public String rentCar(@PathParam("carid") int carid, String postData) {
        try {
            JSONObject jsonObject = new JSONObject(postData);
            Date returnDate = new SimpleDateFormat("dd-MM-yyyy").parse(jsonObject.getString("return"));
            cManager.rentCar(carid, returnDate);
        } catch (JSONException e) {
            e.printStackTrace();
            return "\"success\":" + false + ",\"error\":\"error parsing JSON\"}";
        } catch (ParseException e) {
            e.printStackTrace();
            return "\"success\":" + false + ",\"error\":\"dd-MM-yyyy format expected for date\"}";
        } catch (Exception e) {
            return "\"success\":" + false + ",\"error\":\"" + e.getMessage() + "\"}";
        }
        return "\"success\":" + true + "}";
    }

    @POST
    @Path("/car/{carid}/return")
    public String returnCar(@PathParam("carid") int carid, String postData) {
        try {
            JSONObject jsonObject = new JSONObject(postData);
            String returnLon = jsonObject.getString("lon");
            String returnLat = jsonObject.getString("lat");
            cManager.returnCar(carid, returnLon, returnLat);
        } catch (JSONException e) {
            e.printStackTrace();
            return "\"success\":" + false + ",\"error\":\"error parsing JSON\"}";
        } catch (Exception e) {
            return "\"success\":" + false + ",\"error\":\"" + e.getMessage() + "\"}";
        }
        return "\"success\":" + true + "}";
    }

    @POST
    @Path("/car/query")
    public String searchCar(String postData, @DefaultValue("USD") @QueryParam("currency") String currency) {
        String type = null;
        int seats = 0;
        double priceMax = 0;
        double priceMin = 0;
        Date date = null;
        double kw = 0;
        String name = null;
        String manufacturer = null;
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(postData);
        } catch (JSONException e) {
            e.printStackTrace();
            return "\"success\":" + false + ",\"error\":\"error parsing JSON\"}";
        }

        try {
            type = jsonObject.getString("type");
        } catch (JSONException e) {
        }
        try {
            seats = jsonObject.getInt("seats");
        } catch (JSONException e) {
        }
        try {
            priceMax = jsonObject.getDouble("priceMax");
        } catch (JSONException e) {
        }
        try {
            priceMin = jsonObject.getDouble("priceMin");
        } catch (JSONException e) {
        }
        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse(jsonObject.getString("date"));
        } catch (JSONException e) {
        } catch (ParseException e) {
            return "\"success\":" + false + ",\"error\":\"dd-MM-yyyy format expected for date\"}";
        }
        try {
            kw = jsonObject.getDouble("kw");
        } catch (JSONException e) {
        }
        try {
            name = jsonObject.getString("name");
        } catch (JSONException e) {
        }
        try {
            manufacturer = jsonObject.getString("manufacturer");
        } catch (JSONException e) {
        }
        Collection<Car> result = cManager.search(type, seats, priceMax, priceMin, date, kw, name, manufacturer);
        String resultString = "[";
        for (Car x : result) {
            resultString += x.toString(currency) + ",";
        }
        resultString = resultString.substring(0, resultString.length() - 1) + "]";
        return "\"success\":" + true + ",\"result\":" + resultString + "}";

    }

}
