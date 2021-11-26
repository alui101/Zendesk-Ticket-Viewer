import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;

/*
 *
 *  
 */
public class RequestTickets {
    private static String oAuthtoken = "";

    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();

        // Create HTTP request object
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://zccahass.zendesk.com/api/v2/tickets.json")).GET()
                .header("Authorization", "Bearer" + oAuthtoken).header("Content-Type", "application/json").build();

        // use the client to send the request
        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject json = new JSONObject();
            try {
                json = new JSONObject(response.body());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(json.getJSONArray("tickets").length());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}