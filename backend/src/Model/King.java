package Model;

import java.util.ArrayList;
import java.util.List;

public class King extends AChessPiece {

  public King(char color, Tile startTile) {
    super(color, startTile);
  }

  @Override
  public List<Tile> getLegalMoves(Tile[][] board) {
    ArrayList<Tile> legalMoves = new ArrayList<>();

    int x = this.getPosition().getXCoord();
    int y = this.getPosition().getYCoord();
    char c = this.getColor();

    for (int a = 1; a >= -1; a--) {
      for (int b = 1; b >= -1; b--) {
        if (a != 0 && b != 0) {
          int xDest = x + a;
          int yDest = y + b;
          if (board[xDest][yDest].isOccupied()) {
            if (board[xDest][yDest].getPiece().getColor() != c) {
              legalMoves.add(board[xDest][yDest]);
            }
          } else {
            legalMoves.add(board[xDest][yDest]);
          }
        }
      }
    }

    return legalMoves;
  }
}
