package Model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Rook extends AChessPiece{

  public Rook(char color, Tile startTile) {
    super(color, startTile);
  }

  @Override
  public List<Tile> getLegalMoves(Tile[][] board) {
    ArrayList<Tile> legalMoves = new ArrayList<>();

    int x = this.getPosition().getXCoord();
    int y = this.getPosition().getYCoord();

    int[] moveLimits = getLinearOccupants(board, x, y);

    for (int i = moveLimits[0]; i <= moveLimits[1]; i++) {
      if (i != x) {
        legalMoves.add(board[i][y]);
      }
    }
    for (int i = moveLimits[2]; i <= moveLimits[3]; i++) {
      if (i != y) {
        legalMoves.add(board[x][i]);
      }
    }

    return legalMoves;
  }
}
