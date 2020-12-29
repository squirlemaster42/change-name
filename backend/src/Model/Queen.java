package Model;

import java.util.ArrayList;
import java.util.List;

public class Queen extends AChessPiece {

  public Queen(char color, Tile startTile) {
    super(color, startTile);
  }

  @Override
  public List<Tile> getLegalMoves(Tile[][] board) {
    ArrayList<Tile> legalMoves = new ArrayList<>();

    int x = this.getPosition().getXCoord();
    int y = this.getPosition().getYCoord();

    int[] linearMoveLimits = getLinearOccupants(board, x, y);

    for (int i = linearMoveLimits[0]; i <= linearMoveLimits[1]; i++) {
      if (i != x) {
        legalMoves.add(board[i][y]);
      }
    }
    for (int i = linearMoveLimits[2]; i <= linearMoveLimits[3]; i++) {
      if (i != y) {
        legalMoves.add(board[x][i]);
      }
    }

    int[][] diagonalMoveLimits = this.getDiagonalOccupants(board, x, y);

    for (int i = 0; i <= (diagonalMoveLimits[0][0] - diagonalMoveLimits[2][0]); i++) {
      if (diagonalMoveLimits[2][0] + i != x) {
        legalMoves.add(board[diagonalMoveLimits[2][0] + i][diagonalMoveLimits[2][1] + i]);
      }
    }

    for (int i = 0; i <= (diagonalMoveLimits[1][0] - diagonalMoveLimits[3][0]); i++) {
      if (diagonalMoveLimits[3][0] + i != x) {
        legalMoves.add(board[diagonalMoveLimits[3][0] + i][diagonalMoveLimits[3][1] + i]);
      }
    }

    return legalMoves;
  }
}
