import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BusinessLink {
    private static final String url;
    private static final Gson gson;
    private static final HttpClient client;

    public static final int ERROR_CODE = -1;
    public static final int SUCCESS_CODE = 200;

    static {
        url = "http://terraeyes.azurewebsites.net/Terrarium";
        gson = new Gson();
        client = HttpClient.newBuilder().build();
    }

    private BusinessLink() {
    }

    public static int sendRawData(RawData rawData) {
        try {
            String dataAsJson = gson.toJson(rawData);
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .uri(URI.create(url))
                    .POST(HttpRequest.BodyPublishers.ofString(dataAsJson))
                    .build();
            HttpResponse<?> response = client.send(request, HttpResponse.BodyHandlers.discarding());
            return response.statusCode();
        }
        catch (InterruptedException | IOException e)
        {
            System.out.println("Error message: " + e.getMessage());
            return -1;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }
}
