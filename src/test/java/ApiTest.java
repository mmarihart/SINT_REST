import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.javalite.http.Http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by @author
 */
public class ApiTest {

    private static String urlBase = "http://127.0.0.1:8888/SintRest/webshop/api/";

    private static String createBasket() throws IOException {
        return sendRequest("POST", "basket", "");
    }

    private static String getBaskets() throws IOException {
        return sendRequest("GET", "basket", "");
    }

    private static String deleteBasket(int id) throws IOException {
        return sendRequest("DELETE", "basket/" + Integer.toString(id), "");
    }

    private static String getItemsInBasket(int basketId) throws IOException {
        return sendRequest("GET", "basket/" + Integer.toString(basketId), "");
    }

    private static String addItemToBasket(int basketId, int itemId, int amount) throws IOException {
        String body = "{" +
                "\"id\":" + itemId + "," +
                "\"amount\":" + amount + "}";
        return sendRequest("PUT", "basket/" + Integer.toString(basketId), body);
    }

    private static String deleteItemFromBasket(int basketId, int itemId) throws IOException {
        return sendRequest("DELETE", "basket/" + Integer.toString(basketId) + "/" + Integer.toString(itemId), "");
    }

    private static String createItem(String name, double price, String description, String vendor) throws IOException {
        String body = "{" +
                "\"name\":\"" + name + "\"," +
                "\"price\":" + price + "," +
                "\"desc\":\"" + description + "\"," +
                "\"vendor\":\"" + vendor + "\"}";
        return sendRequest("POST", "item", body);

    }

    private static String getItems() throws IOException {
        return sendRequest("GET", "item", "");
    }

    private static String getItem(int itemId) throws IOException {
        return sendRequest("GET", "item/" + Integer.toString(itemId), "");
    }

    private static String deleteItem(int id) throws IOException {
        return sendRequest("DELETE", "item/" + Integer.toString(id), "");
    }

    private static String sendRequest(String method, String url, String body) throws IOException {
        HttpURLConnection connection;
        URL connectionUrl = new URL(urlBase + url);
        connection = (HttpURLConnection) connectionUrl.openConnection();
        connection.setRequestMethod(method);
        connection.setRequestProperty("Content-Length", Integer.toString(body.getBytes().length));
        connection.setUseCaches(false);
        if(!method.equals("DELETE") && !method.equals("GET")) {
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
            System.out.println("Testing endpoint POST /basket");
            int basketId = new JSONObject(createBasket()).getInt("id");
            int basketId2 = new JSONObject(createBasket()).getInt("id");
            int basketId3 = new JSONObject(createBasket()).getInt("id");

            System.out.println("Testing endpoint GET /basket/{basketid}");
            System.out.println(getBaskets());

            System.out.println("Testing endpoint DELETE /basket/{basketid}");
            deleteBasket(basketId3);
            System.out.println(getBaskets());

            // Create test items
            System.out.println("Testing endpoint POST /item");
            String response = createItem("Pfeffer", 10.5, "Spicey", "Pfeffer GmbH");
            int pepperId = new JSONObject(response).getInt("id");

            System.out.println("Testing endpoint GET /item/{itemid}");
            System.out.println(getItem(pepperId));

            response = createItem("Salz", 7, "Salty", "Salz GmbH");
            int saltId = new JSONObject(response).getInt("id");

            response = createItem("Minze", 32, "Minty", "Mint GmbH");
            int mintId = new JSONObject(response).getInt("id");

            System.out.println("Testing endpoint GET /item");
            System.out.println(getItems());

            System.out.println("Testing endpoint DELETE /item/{itemid}");
            deleteItem(saltId);
            System.out.println(getItems());

            //Add items to basket
            System.out.println("Testing endpoint PUT /basket");
            addItemToBasket(basketId, pepperId, 2);
            addItemToBasket(basketId, mintId, 1);

            System.out.println("Testing endpoint GET /basket/{basketid}");
            System.out.println(getItemsInBasket(basketId));

            System.out.println("Testing endpoint DELETE /basket/{basketid}/{itemid}");
            deleteItemFromBasket(basketId, mintId);
            System.out.println(getItemsInBasket(basketId));


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
