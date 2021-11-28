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
 * Handles connecting to the api and getting the JSON
 */
public class RequestTickets {

    // Modify these variables
    private String oAuthtoken = "ca9d6e6994f34142724aba4232bf9b4ec0ac26fa48aa7df5d29c81486bb3fb60";
    private String subdomain = "zccahass";

    // Keeps track of errors
    public String error = "none";

    /**
     * 
     * @return all the tickets for the user's account
     */
    public JSONObject getAllTickets() {
        HttpClient client = HttpClient.newHttpClient();

        // Create HTTP request object
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://" + subdomain + ".zendesk.com/api/v2/tickets.json")).GET()
                .header("Authorization", "Bearer " + oAuthtoken).header("Content-Type", "application/json").build();
        JSONObject tickets = new JSONObject();

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
                error = "Unable to store Json String receieved from request into a JSONObject";
            }
        } catch (IOException | InterruptedException e) {
            error = "Error occured during reading the data or thread is interrupted";
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
                .uri(URI.create("https://" + subdomain + ".zendesk.com/api/v2/tickets/" + String.valueOf(id) + ".json"))
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
                e.printStackTrace();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return tickets;
    }
}