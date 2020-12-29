package Model;

import java.util.ArrayList;
import java.util.List;

public class Knight extends AChessPiece {

  public Knight(char color, Tile startTile) {
    super(color, startTile);
  }

  @Override
  public List<Tile> getLegalMoves(Tile[][] board) {
    ArrayList<Tile> legalMoves = new ArrayList<>();

    int x = this.getPosition().getXCoord();
    int y = this.getPosition().getYCoord();
    char c = this.getColor();

    for (int a = 2; a >= -2; a--) {
      for (int b = 2; b >= -2; b--) {
        if ((Math.abs(a) == 2 ^ Math.abs(b) == 2) && (a != 0 && b != 0)) {
          int xDest = x + a;
          int yDest = y + b;
          if ((xDest >= 0 && xDest < 8) && (yDest >= 0 && yDest < 8)) {
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
    }
    return legalMoves;
  }
}
