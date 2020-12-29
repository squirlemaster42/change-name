package Model;

import java.util.List;

public abstract class AChessPiece implements IChessPiece {

  private final char color;
  private Tile currentTile;

  public AChessPiece(char color, Tile startTile) {
    this.color = color;
    this.currentTile = startTile;
  }

  public Tile getPosition() {
    return currentTile;
  }

  public void setPosition(Tile tile) {
    this.currentTile = tile;
  }

  public char getColor() {
    return color;
  }

  public boolean move(Tile dest) {
    IChessPiece destPiece = dest.getPiece();

    if (destPiece != null) {
      if (destPiece.getColor() == this.getColor()) {
        return false;
      }
    }
    currentTile.removePiece();
    dest.setPiece(this);
    return true;
  }

  public abstract List<Tile> getLegalMoves(Tile[][] board);

  public int[] getLinearOccupants(Tile[][] board, int x, int y) {
    int lastXabove = 7;
    int lastXbelow = 0;
    int lastYleft = 0;
    int lastYright = 7;

    for (int i = x + 1; i < 8; i++) {
      if (board[i][y].isOccupied()) {
        if (board[i][y].getPiece().getColor() != this.color) {
          lastXabove = i;
        } else {
          lastXabove = i - 1;
        }
        break;
      }
    }

    for (int i = x - 1; i >= 0; i--) {
      if (board[i][y].isOccupied()) {
        if (board[i][y].getPiece().getColor() != this.color) {
          lastXbelow = i;
        } else {
          lastXbelow = i + 1;
        }
        break;
      }
    }

    for (int i = y - 1; i >= 0; i--) {
      if (board[x][i].isOccupied()) {
        if (board[x][i].getPiece().getColor() != this.color) {
          lastYleft = i;
        } else {
          lastYleft = i + 1;
        }
        break;
      }
    }

    for (int i = y + 1; i < 8; i++) {
      if (board[x][i].isOccupied()) {
        if (board[x][i].getPiece().getColor() != this.color) {
          lastYright = i;
        } else {
          lastYright = i - 1;
        }
        break;
      }
    }

    return new int[]{lastXabove, lastXbelow, lastYleft, lastYright};
  }

  public int[][] getDiagonalOccupants(Tile[][] board, int x, int y) {
    int[] lastNE = new int[]{x, y};
    int[] lastSE = new int[]{x, y};
    int[] lastSW = new int[]{x, y};
    int[] lastNW = new int[]{x, y};

    while (lastNE[0] + 1 < 8 && lastNE[1] + 1 < 8) {
      int xDest = lastNE[0] + 1;
      int yDest = lastNE[1] + 1;
      if (board[xDest][yDest].isOccupied()) {
        if (board[xDest][yDest].getPiece().getColor() != this.color) {
          lastNE[0] = xDest;
          lastNE[1] = yDest;
        }
        break;
      } else {
        lastNE[0] = xDest;
        lastNE[1] = xDest;
      }
    }

    while (lastSE[0] - 1 >= 0 && lastSE[1] + 1 < 8) {
      int xDest = lastSE[0] - 1;
      int yDest = lastSE[1] + 1;
      if (board[xDest][yDest].isOccupied()) {
        if (board[xDest][yDest].getPiece().getColor() != this.color) {
          lastSE[0] = xDest;
          lastSE[1] = yDest;
        }
        break;
      } else {
        lastSE[0] = xDest;
        lastSE[1] = yDest;
      }
    }

    while (lastSW[0] - 1 >= 0 && lastSW[1] - 1 >= 0) {
      int xDest = lastSW[0] - 1;
      int yDest = lastSW[1] - 1;
      if (board[xDest][yDest].isOccupied()) {
        if (board[xDest][yDest].getPiece().getColor() != this.color) {
          lastSW[0] = xDest;
          lastSW[1] = yDest;
        }
        break;
      } else {
        lastSW[0] = xDest;
        lastSW[1] = yDest;
      }
    }

    while (lastNW[0] + 1 < 8 && lastNW[1] - 1 >= 0) {
      int xDest = lastNW[0] + 1;
      int yDest = lastNW[1] - 1;
      if (board[xDest][yDest].isOccupied()) {
        if (board[xDest][yDest].getPiece().getColor() != this.color) {
          lastNW[0] = xDest;
          lastNW[1] = yDest;
        }
        break;
      } else {
        lastNW[0] = xDest;
        lastNW[1] = yDest;
      }
    }

    return new int[][]{lastNE, lastSE, lastSW, lastNW};
  }
}
