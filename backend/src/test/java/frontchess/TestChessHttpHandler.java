package frontchess;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestChessHttpHandler {

    public static final String examplePGN = """
                [White "Jakob"]
                [Black "Dill"]
                [Date "2020.12.28"]

                1. e4 e5 2. Nf3 Nc6 3. Bb5 a6 {This is a comment.}
               ;This is also a comment
               4. Ba5 Nf6 5. O-O Be7
                """;

    @Test
    public void testCreatePGNJson(){
        ChessHttpHandler handler = new ChessHttpHandler();
        String jsonPGN = handler.genPGNJson(examplePGN);
        Gson gson = new Gson();
        PGNFile pgnFile = gson.fromJson(jsonPGN, PGNFile.class);
        assertEquals("Jakob", pgnFile.whiteName);
        assertEquals("Dill", pgnFile.blackName);
        assertEquals("2020.12.28", pgnFile.date);
        assertEquals("""
                1. e4 e5 2. Nf3 Nc6 3. Bb5 a6 {This is a comment.}
                ;This is also a comment
                4. Ba5 Nf6 5. O-O Be7
                """.replaceAll("[\n\r]", ""), pgnFile.moves);
    }

    @Test
    public void testSendPGN(){
        System.out.println("-------- Trying to send message --------");
        ChessHttpHandler handler = new ChessHttpHandler();
        try {
            handler.sendPGN(examplePGN, "ws://localhost:5000/moves");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
