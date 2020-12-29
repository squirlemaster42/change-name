package Model;

public enum Tile {
  a8(57), b8(58), c8(59), d8(60), e8(61), f8(62), g8(63), h8(64),
  a7(49), b7(50), c7(51), d7(52), e7(53), f7(54), g7(55), h7(56),
  a6(41), b6(42), c6(43), d6(44), e6(45), f6(46), g6(47), h6(48),
  a5(33), b5(34), c5(35), d5(36), e5(37), f5(38), g5(39), h5(40),
  a4(25), b4(26), c4(27), d4(28), e4(29), f4(30), g4(31), h4(32),
  a3(17), b3(18), c3(19), d3(20), e3(21), f3(22), g3(23), h3(24),
  a2(9), b2(10), c2(11), d2(12), e2(13), f2(14), g2(15), h2(16),
  a1(1), b1(2), c1(3), d1(4), e1(5), f1(6), g1(7), h1(8);

  int tileID;
  IChessPiece piece;

  Tile(int id) {
    tileID = id;
  }

  IChessPiece getPiece() {
    return this.piece;
  }

  void setPiece(IChessPiece piece) {
    this.piece = piece;
  }

  void removePiece() {
    this.piece = null;
  }

  boolean isOccupied() {
    return (this.piece != null);
  }

  int getXCoord() {
    return (tileID - 1) / 8;
  }

  int getYCoord() {
    return (tileID - 1) % 8;
  }
}
