package frontchess;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestChessHttpHandler {

    @Test
    public void testCreatePGNJson(){
        String examplePGN = """
                [White "Jakob"]
                [Black "Dill"]
                [Date "2020.12.28"]

                1. e4 e5 2. Nf3 Nc6 3. Bb5 a6 {This is a comment.}
               ;This is also a comment
                4. Ba5 Nf6 5. O-O Be7
                """;
        ChessHttpHandler handler = new ChessHttpHandler();
        String jsonPGN = handler.genPGNJson(examplePGN);
        Gson gson = new Gson();
        PGNFile pgnFile = gson.fromJson(jsonPGN, PGNFile.class);
        assertEquals(pgnFile.whiteName, "Jakob");
        assertEquals(pgnFile.blackName, "Dill");
        assertEquals(pgnFile.date, "2020.12.28");
        assertEquals(pgnFile.moves, """
                1. e4 e5 2. Nf3 Nc6 3. Bb5 a6 {This is a comment.}
                ;This is also a comment
                4. Ba5 Nf6 5 O-O Be7
                """);
    }
}