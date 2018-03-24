import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by @author
 */
public class ApiTest {

    private static String urlBase = "http://127.0.0.1:8888/SintRest/cardealer/api/";

    private static String createCar(String name, String manufacturer, String type, int seats, double kw, String lat, String lon, double price) throws IOException {
        String body = "{" +
                "\"name\":\"" + name + "\"," +
                "\"manufacturer\":\"" + manufacturer + "\"," +
                "\"type\":\"" + type + "\"," +
                "\"seats\":" + seats + "," +
                "\"kw\":" + kw + "," +
                "\"lat\":\"" + lat + "\"," +
                "\"lon\":\"" + lon + "\"," +
                "\"price\":" + price +
                "}";
        return sendRequest("POST", "car", body);
    }

    private static String getCars() throws IOException {
        return sendRequest("GET", "car", "");
    }

    private static String deleteCar(int id) throws IOException {
        return sendRequest("DELETE", "car/" + Integer.toString(id), "");
    }

    private static String getCar(int id) throws IOException {
        return sendRequest("GET", "car/" + Integer.toString(id), "");
    }

    private static String rentCar(int id, String date) throws IOException {
        String body = "{" +
                "\"return\":\"" + date.toString() + "\"}";
        return sendRequest("POST", "car/" + Integer.toString(id) + "/rent", body);
    }

    private static String returnCar(int id, String lat, String lon) throws IOException {
        String body = "{" +
                "\"lon\":\"" + lon + "\"," +
                "\"lat\":\"" + lat + "\"}";
        return sendRequest("POST", "car/" + Integer.toString(id) + "/return", body);
    }

    private static String queryCars(String name) throws IOException {
        String body = "{" +
                "\"name\":\"" + name + "\"" +
                "}";
        return sendRequest("POST", "car/query", body);
    }

    private static String sendRequest(String method, String url, String body) throws IOException {
        HttpURLConnection connection;
        URL connectionUrl = new URL(urlBase + url);
        connection = (HttpURLConnection) connectionUrl.openConnection();
        connection.setRequestMethod(method);
        connection.setRequestProperty("Content-Length", Integer.toString(body.getBytes().length));
        connection.setUseCaches(false);
        if (!method.equals("DELETE") && !method.equals("GET")) {
            connection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(body);
            wr.close();
        } else {
            connection.getResponseCode();
        }


        InputStream is = connection.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\r');
        }
        rd.close();
        return response.toString();
    }

    public static void main(String[] args) {
        HttpURLConnection connection = null;

        try {
            // Create a test basket
            System.out.println("Testing endpoint POST /car");
            int carId = new JSONObject(createCar("Prius", "Toyota", "Family", 6, 10.3, "0.00000", "0.00000", 123.4)).getInt("id");
            int carId2 = new JSONObject(createCar("Scenic", "Renault", "Coupe", 5, 120.3, "0.00000", "0.00000", 43.4)).getInt("id");
            int carId3 = new JSONObject(createCar("Carrera", "Porsche", "Sport", 2, 1990.3, "0.00000", "0.00000", 900)).getInt("id");

            System.out.println("Testing endpoint GET /car/{carid}");
            System.out.println(getCar(carId));

            System.out.println("Testing endpoint DELETE /car/{carid}");
            System.out.println(getCars());
            deleteCar(carId3);
            System.out.println(getCars());


            System.out.println("Testing endpoint POST /car/{carid}/rent");
            rentCar(carId, new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime()));
            System.out.println(getCar(carId));

            System.out.println("Testing endpoint POST /car/{carid}/return");
            returnCar(carId, "1.000000", "2.0000000");
            System.out.println(getCar(carId));

            System.out.println("Testing endpoint POST /car/query");
            System.out.println(queryCars("Prius"));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
