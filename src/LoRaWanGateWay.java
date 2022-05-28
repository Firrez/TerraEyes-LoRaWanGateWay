import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class LoRaWanGateWay implements WebSocket.Listener {
    private final WebSocket server;

    public void sendDownLink(String jsonTelegram) {
        server.sendText(jsonTelegram, true);
    }

    //URL -> wss://iotnet.teracom.dk/app?token=vnoUhgAAABFpb3RuZXQudGVyYWNvbS5ka_j9ctg1JnsNo1n5Rxn3neg=
    public LoRaWanGateWay(String url) {
        HttpClient client = HttpClient.newHttpClient();
        CompletableFuture<WebSocket> ws = client.newWebSocketBuilder()
                .buildAsync(URI.create(url), this);

        server = ws.join();
    }

    public void onOpen(WebSocket webSocket) {
        webSocket.request(1);
        System.out.println("WebSocket Listener has been opened for requests.");
    }

    public void onError(WebSocket webSocket, Throwable error) {
        System.out.println("A(n) " + error.getCause() + " was thrown.");
        error.printStackTrace();
        webSocket.abort();
    }

    public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
        System.out.println("WebSocket closed!");
        System.out.println("Status: " + statusCode + " Reason: " + reason);
        return CompletableFuture.completedFuture("onClose() completed.").thenAccept(System.out::println);
    }

    public CompletionStage<?> onPing(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        System.out.println("Ping: Client ---> Server");
        System.out.println(message.asCharBuffer());
        return CompletableFuture.completedFuture("Ping completed.").thenAccept(System.out::println);
    }

    public CompletionStage<?> onPong(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        System.out.println("Pong: Client ---> Server");
        System.out.println(message.asCharBuffer());
        return CompletableFuture.completedFuture("Pong completed.").thenAccept(System.out::println);
    }

    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
        String indented = (new JSONObject(data.toString())).toString(4);
        sendData(indented);
        sendTelegrams();
        webSocket.request(1);
        return CompletableFuture.completedFuture("onText() completed").thenAccept(System.out::println);
    }

    private void sendData(String data) {
        int statusCode;
        RawData rawData = DataCleaner.clean(data);

        System.out.println(rawData.getEUI());

        if (rawData.getCmd().equals("gw"))
            statusCode = BusinessLink.sendRawData(rawData);
        else
            return;

        if (statusCode == BusinessLink.SUCCESS_CODE)
            System.out.println("Data sent successfully!");
        else if (statusCode == BusinessLink.ERROR_CODE)
            System.out.println("ERROR, failed to send data!");
        else
            System.out.println("Error code: " + statusCode);
    }

    private void sendTelegrams() {
        List<String> EUIs = BusinessLink.getFeedRequests();

        if (EUIs == null) return;
        for (String eui : EUIs)
        {
            DownLinkTelegram telegram = new DownLinkTelegram(eui);
            String jsonTelegram = new JSONObject(telegram).toString();
            sendDownLink(jsonTelegram);
        }
    }
}
