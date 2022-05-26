import org.json.JSONObject;

public class Run {
    public static void main(String[] args) {

        String url = "wss://iotnet.teracom.dk/app?token=vnoUhgAAABFpb3RuZXQudGVyYWNvbS5ka_j9ctg1JnsNo1n5Rxn3neg=";
        String EUI_DEV20 = "0004A30B00259F36";
        /*DownLinkTelegram telegram = new DownLinkTelegram();
        LoRaWanGateWay gateWay = new LoRaWanGateWay(url);

        String jsonTelegram = new JSONObject(telegram).toString();

        gateWay.sendDownLink(jsonTelegram);*/

        String data = "0051017400850b71026300";
        String cmd = "gw";
        String eui = "abc123";
        long ts = Long.parseUnsignedLong("65782365293234");

        RawData test = new RawData(eui, data, cmd, ts);

        BusinessLink.sendRawData(test);

    }
}
