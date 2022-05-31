import com.google.gson.Gson;

public class DataCleaner {

    public static RawData clean(String jsonData) {
        Gson gson = new Gson();
        return gson.fromJson(jsonData, RawData.class);
    }
}
