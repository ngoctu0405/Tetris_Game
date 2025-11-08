package core;

public class Position {
    public int row, col;
    public Position(int r, int c) { this.row = r; this.col = c; }
    public Position copy() { return new Position(row, col); }
}
