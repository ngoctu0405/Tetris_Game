// Khai báo package có tên là core
package core;

// Import thư viện Color từ javafx để sử dụng màu sắc
import javafx.scene.paint.Color;

// Định nghĩa lớp Cell (ô vuông trong game Tetris)
public class Cell {
    // Khai báo biến color kiểu Color là final (không thể thay đổi sau khi khởi tạo)
    public final Color color;
    // Constructor của lớp Cell, nhận vào một tham số màu sắc
    public Cell(Color c) { this.color = c; }
}
