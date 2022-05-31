import java.sql.Timestamp;
import java.util.Date;

public class Run {
    public static void main(String[] args) {

        String url = "wss://iotnet.teracom.dk/app?token=vnoUhgAAABFpb3RuZXQudGVyYWNvbS5ka_j9ctg1JnsNo1n5Rxn3neg=";
        LoRaWanGateWay gateWay = new LoRaWanGateWay(url);

        /*
        String eui = "0004A30B00E8207E";
        long ts = new Timestamp(new Date().getTime()).getTime();
        long increment = 43200000;
        String data = "00e1021804800000004a00";
        String cmd = "gw";

        RawData rawData = new RawData(eui, data, cmd, ts);
        BusinessLink.sendRawData(rawData);

        int measurements = 0;
        do {
            ts = new Timestamp(ts + increment).getTime();
            rawData = new RawData(eui, data, cmd, ts);
            BusinessLink.sendRawData(rawData);
            measurements++;
        } while (measurements < 14);
        */

        while (true) {}

    }
}
