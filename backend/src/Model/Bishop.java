package Model;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends AChessPiece {

  public Bishop(char color, Tile startTile) {
    super(color, startTile);
  }

  @Override
  public List<Tile> getLegalMoves(Tile[][] board) {
    ArrayList<Tile> legalMoves = new ArrayList<>();

    int x = this.getPosition().getXCoord();
    int y = this.getPosition().getYCoord();

    int[][] moveLimits = this.getDiagonalOccupants(board, x, y);

    for (int i = 0; i <= (moveLimits[0][0] - moveLimits[2][0]); i++) {
      if (moveLimits[2][0] + i != x) {
        legalMoves.add(board[moveLimits[2][0] + i][moveLimits[2][1] + i]);
      }
    }

    for (int i = 0; i <= (moveLimits[1][0] - moveLimits[3][0]); i++) {
      if (moveLimits[3][0] + i != x) {
        legalMoves.add(board[moveLimits[3][0] + i][moveLimits[3][1] + i]);
      }
    }

    return legalMoves;
  }
}
