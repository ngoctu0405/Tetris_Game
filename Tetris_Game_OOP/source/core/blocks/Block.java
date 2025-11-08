package core.blocks; // khai báo package của lớp

import core.Position; // import lớp Position từ package core
import javafx.scene.paint.Color; // import lớp Color từ JavaFX

public abstract class Block { // lớp trừu tượng đại diện cho một khối
    protected Position[] tiles; // mảng các ô (vị trí) cấu thành khối
    protected javafx.scene.paint.Color color; // màu của khối

    public abstract void rotate(); // phương thức trừu tượng để quay khối
    public void moveDown() { for (var p: tiles) p.row++; } // di chuyển khối xuống: tăng chỉ số hàng của từng ô
    public void moveLeft() { for (var p: tiles) p.col--; } // di chuyển khối sang trái: giảm chỉ số cột của từng ô
    public void moveRight(){ for (var p: tiles) p.col++; } // di chuyển khối sang phải: tăng chỉ số cột của từng ô
    public Position[] getTiles(){ return tiles; } // trả về mảng vị trí các ô của khối
    public javafx.scene.paint.Color getColor(){ return color; } // trả về màu của khối
    public abstract Block copy(); // phương thức trừu tượng tạo và trả về một bản sao của khối

    protected void copyStateFrom(Block source) { // sao chép trạng thái từ khối nguồn vào khối hiện tại
        this.color = source.color; // sao chép màu
        this.tiles = new Position[source.tiles.length]; // tạo mảng mới có cùng độ dài
        for (int i = 0; i < source.tiles.length; i++) {
            this.tiles[i] = source.tiles[i].copy(); // sao chép sâu từng Position để không tham chiếu chung
        }
    }
}
