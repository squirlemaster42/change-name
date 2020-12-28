package frontchess;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;

public class ChessHttpHandler {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private final OkHttpClient client;

    public ChessHttpHandler(){
        client = new OkHttpClient();
    }

    public String sendPGN(String pgn, String destinationURL) throws IOException {
        RequestBody body = RequestBody.create(genPGNJson(pgn), JSON);

        Request request = new Request.Builder().url(destinationURL).post(body).build();
        Response response = client.newCall(request).execute();
        return Objects.requireNonNull(response.body()).string();
    }

    String genPGNJson(String pgn){
        return "";
    }
}