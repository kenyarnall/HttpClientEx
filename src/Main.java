import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {

    public static void main(String[] args) {
        var client = HttpClient.newBuilder()
                .build();

        var request = HttpRequest.newBuilder()
                .uri(URI.create("http://mas.lvc.edu"))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body());
            for (var header : response.headers().map().keySet()) {
                System.out.print(header + ": ");
                for (var val : response.headers().map().get(header))
                    System.out.print(val + " ");
                System.out.println();
            }
        }
        catch (IOException e) {
            System.out.println("Error making request");
        }
        catch (InterruptedException e) {
            System.out.println("Connection interrupted");
        }
    }
}
