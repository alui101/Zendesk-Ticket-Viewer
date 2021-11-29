package MVC;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URI;

/**
 * ****************************** Model ************************************
 * Handles connecting to the api/user's account and getting the tickets
 */
public class RequestTickets {

    private String subDomain = System.getenv("SUBDOMAIN");
    private String oAuthtoken = System.getenv("OAUTHTOKEN");

    /**
     * @return returns all the tickets for the user's account
     */
    public JSONObject getAllTickets() {
        HttpClient client = HttpClient.newHttpClient();
        // Create HTTP request object
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://" + subDomain
                        + ".zendesk.com/api/v2/incremental/tickets/cursor.json?start_time=0000000000"))
                .GET()
                .header("Authorization", "Bearer " + oAuthtoken).header("Content-Type", "application/json").build();
        JSONObject tickets = new JSONObject();

        // use the client to send the requests
        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // store response in a JSONObject
            try {
                // if request is succesful set it's value to the recieved json otherwise set it
                // to null
                if (response.statusCode() == 200)
                    tickets = new JSONObject(response.body());
                else
                    tickets = null;
            } catch (JSONException e) {
                System.out.println("Unable to store Json String receieved from request into a JSONObject");
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error occured during reading the data or thread is interrupted");
        }
        return tickets;
    }

    /**
     * @param id - id of ticket user wants to view
     * @return the ticket corresponding to the id
     */
    public JSONObject getTicket(int id) {
        HttpClient client = HttpClient.newHttpClient();
        JSONObject tickets = new JSONObject();

        // Create HTTP request object
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://" + subDomain + ".zendesk.com/api/v2/tickets/" + String.valueOf(id) + ".json"))
                .GET().header("Authorization", "Bearer " + oAuthtoken).header("Content-Type", "application/json")
                .build();

        // use the client to send the request
        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // store response in a JSONObject
            try {
                // if request is succesful set it's value to the recieved json otherwise set it
                // to null
                if (response.statusCode() == 200)
                    tickets = new JSONObject(response.body());
                else
                    tickets = null;
            } catch (JSONException e) {
                System.out.println("Unable to store Json String receieved from request into a JSONObject");
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error occured during reading data or thread is interrupted");
        }
        return tickets;
    }
}