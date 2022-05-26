public class DownLinkTelegram {
    private String cmd = "tx";
    private int port = 1;
    private String EUI = "0004A30B00259F36";
    private String data = "111222";
    private boolean confirmed = false;

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

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setEUI(String EUI) {
        this.EUI = EUI;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}
