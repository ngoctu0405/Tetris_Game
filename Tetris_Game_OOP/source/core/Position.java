package core;

/**
 * Lớp Position đại diện cho một vị trí trong ma trận với hàng và cột.
 * 
 * Các thuộc tính:
 * - row: chỉ số hàng của vị trí.
 * - col: chỉ số cột của vị trí.
 * 
 * Các phương thức:
 * - Position(int r, int c): Khởi tạo một đối tượng Position với hàng và cột được chỉ định.
 * - Position copy(): Tạo và trả về một bản sao của đối tượng Position hiện tại.
 */
public class Position {
    public int row, col;
    public Position(int r, int c) { this.row = r; this.col = c; }
    public Position copy() { return new Position(row, col); }
}
