package Model;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends AChessPiece{
  private boolean alreadyMoved;

  public Pawn(char color, Tile startTile) {
    super(color, startTile);
    this.alreadyMoved = false;
  }

  @Override
  public boolean move(Tile dest) {
    this.alreadyMoved = true;
    return true;
  }

  @Override
  public List<Tile> getLegalMoves(Tile[][] board) {
    ArrayList<Tile> legalMoves = new ArrayList<>();

    int x = this.getPosition().getXCoord();
    int y = this.getPosition().getYCoord();
    char c = this.getColor();

    if (c == 'w') {
      if (!this.alreadyMoved && !board[x + 2][y].isOccupied()) {
        legalMoves.add(board[x + 2][y]);
      }
      if ((x + 1 < 8) && !board[x + 1][y].isOccupied()) {
        legalMoves.add(board[x + 1][y]);
      }
      if ((x + 1 < 8 && y - 1 >= 0) && board[x + 1][y - 1].isOccupied() && (
          board[x + 1][y - 1].getPiece().getColor() == 'b')) {
        legalMoves.add(board[x + 1][y - 1]);
      }
      if ((x + 1 < 8 && y + 1 < 8) && board[x + 1][y + 1].isOccupied() && (
          board[x + 1][y + 1].getPiece().getColor() == 'b')) {
        legalMoves.add(board[x + 1][y - 1]);
      }
    }

    if (c == 'b') {
      if (!this.alreadyMoved && !board[x - 2][y].isOccupied()) {
        legalMoves.add(board[x - 2][y]);
      }
      if ((x - 1 >= 0) && !board[x - 1][y].isOccupied()) {
        legalMoves.add(board[x - 1][y]);
      }
      if ((x - 1 >= 0 && y - 1 >= 0) && board[x - 1][y - 1].isOccupied()
          && board[x - 1][y - 1].getPiece().getColor() == 'w') {
        legalMoves.add(board[x - 1][y - 1]);
      }
      if ((x - 1 >= 0 && y + 1 < 8) && board[x - 1][y + 1].isOccupied()
          && board[x - 1][y + 1].getPiece().getColor() == 'w') {
        legalMoves.add(board[x - 1][y + 1]);
      }
    }

    return legalMoves;
  }
}
