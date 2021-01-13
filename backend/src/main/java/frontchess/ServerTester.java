package frontchess;

import java.io.IOException;

public class ServerTester {

    public static final String examplePGN = """
                [White "Jakob"]
                [Black "Dill"]
                [Date "2020.12.28"]

                1. e4 e5 2. Nf3 Nc6 3. Bb5 a6 {This is a comment.}
               ;This is also a comment
               4. Ba5 Nf6 5. O-O Be7
                """;

    public static void main(String[] args){
        System.out.println("-------- Trying to send message --------");
        ChessHttpHandler handler = new ChessHttpHandler();
        try {
            handler.sendPGN(examplePGN, "ws://localhost:5000/moves");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
