import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class OkHttpClientUtil {

    public static void main(String[] args) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder request = new Request.Builder().url("http://localhost:8801").get();
        try {
            Response response = okHttpClient.newCall(request.build()).execute();
            System.out.print(response.body());
        } finally {
            okHttpClient.clone();
        }
    }
}