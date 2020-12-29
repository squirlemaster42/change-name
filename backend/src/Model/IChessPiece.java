package Model;

import java.util.List;

public interface IChessPiece {
  public Tile getPosition();

  public void setPosition(Tile tile);

  public char getColor();

  public boolean move (Tile dest);

  public List<Tile> getLegalMoves(Tile[][] board);
}
