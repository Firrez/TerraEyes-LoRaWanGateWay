public class RawData {
    private String EUI;
    private String data;
    private String cmd;
    private long ts;

    public RawData() {}

    public RawData(String EUI, String data, String cmd, long ts) {
        this.EUI = EUI;
        this.data = data;
        this.cmd = cmd;
        this.ts = ts;
    }

    public String getEUI() {
        return EUI;
    }

    public String getData() {
        return data;
    }

    public String getCmd() {
        return cmd;
    }

    public long getTs() {
        return ts;
    }


}
