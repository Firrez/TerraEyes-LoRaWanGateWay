public class DownLinkTelegram {
    private String cmd = "tx";
    private int port = 1;
    private String EUI;
    private String data = "111222";
    private boolean confirmed = false;

    public DownLinkTelegram(String eui) {
        EUI = eui;
    }

    public String getCmd() {
        return cmd;
    }

    public int getPort() {
        return port;
    }

    public String getEUI() {
        return EUI;
    }

    public String getData() {
        return data;
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}
