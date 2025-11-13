package core.blocks;

import core.Position;

/**
 * Lớp trừu tượng đại diện cho một khối (tetromino). Chứa các ô (tiles) và màu
 * sắc, cùng các thao tác di chuyển/rotate.
 */
public abstract class Block {

    // Mảng các ô (vị trí) tạo thành khối
    protected Position[] tiles;
    // Màu của khối (sử dụng java.awt.Color)
    protected java.awt.Color color;

    // Phương thức rotate đặc thù cho từng loại khối -> triển khai ở lớp con
    public abstract void rotate();

    // Di chuyển khối xuống 1 hàng
    public void moveDown() {
        for (var p : tiles) {
            p.row++;
    
        }}

    // Di chuyển khối sang trái 1 cột
    public void moveLeft() {
        for (var p : tiles) {
            p.col--;
    
        }}

    // Di chuyển khối sang phải 1 cột
    public void moveRight() {
        for (var p : tiles) {
            p.col++;
    
        }}
            
    // Trả về mảng ô hiện tại (tham chiếu)
    public Position[] getTiles() {
        return tiles;
    }

    // Trả về màu của khối
    public java.awt.Color getColor() {
        return color;
    }

    // Yêu cầu: tạo bản sao sâu (deep copy) của Block để tránh chia sẻ tham chiếu tới Position
    public abstract Block copy();

    // Sao chép trạng thái từ một Block nguồn vào this.
    // Thực hiện deep copy cho mảng Position bằng cách gọi Position.copy().
    protected void copyStateFrom(Block source) {
        this.color = source.color;
        this.tiles = new Position[source.tiles.length];
        for (int i = 0; i < source.tiles.length; i++) {
            this.tiles[i] = source.tiles[i].copy();
        }
    }
}
