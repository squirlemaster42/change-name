package frontchess;

import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.Arrays;
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
        Gson gson = new Gson();
        PGNFile file = new PGNFile();
        String[] splitLines = pgn.split("]");
        System.out.println(Arrays.toString(splitLines));
        for(String line : splitLines){
            line = line.replace("[", "");
            if(line.contains("White")){
                System.out.println(Arrays.toString(line.split(" ")));
                file.whiteName = line.trim().split(" ")[1].replace("\"", "");
            }else if(line.contains("Black")){
                file.blackName = line.trim().split(" ")[1].replace("\"", "");
            }else if(line.contains("Date")){
                file.date = line.trim().split(" ")[1].replace("\"", "");
            }else{
                file.moves = line.trim().replaceAll("[\n\r]", "");
            }
        }
        return gson.toJson(file);
    }
}