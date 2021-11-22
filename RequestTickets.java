import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.URI;

/*
 *
 *  
 */
public class RequestTickets {
    public static void main(String[] args) {
        var client = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder(URI.create("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY"))
                .header("accept", "application/json").build();
    }
}