package core;
// Import các package cần thiết
import core.blocks.Block;
import javafx.scene.paint.Color;

// Định nghĩa lớp Board đại diện cho bảng game Tetris
public class Board {
    // Khai báo mảng 2 chiều grid kiểu Cell để lưu trữ các ô trong bảng game
    private final Cell[][] grid = new Cell[Config.ROWS][Config.COLS];

    // Kiểm tra xem một vị trí có nằm trong phạm vi bảng game hay không
    public boolean isInside(int r, int c) { return r>=0 && r<Config.ROWS && c>=0 && c<Config.COLS; }

    // Kiểm tra xem có thể đặt một khối block vào vị trí hiện tại trong bảng không
    public boolean canPlace(Block b) {
        // Duyệt qua từng ô trong khối block
        for (var p : b.getTiles()) {
            if (p.row<0) continue; // Bỏ qua các ô nằm phía trên bảng game
            if (!isInside(p.row, p.col)) return false; // Trả về false nếu block nằm ngoài bảng
            if (grid[p.row][p.col] != null) return false; // Trả về false nếu vị trí đã có block khác
        }
        return true; // Trả về true nếu có thể đặt được block
    }

    // Đặt khối block vào vị trí trong bảng game
    public void place(Block b) {
        // Duyệt qua từng ô trong khối block
        for (var p : b.getTiles()) {
            if (p.row<0) continue; // Bỏ qua các ô nằm phía trên bảng
            grid[p.row][p.col] = new Cell(b.getColor()); // Tạo ô mới với màu của block
        }
    }

    // Xóa các hàng đã đầy và trả về số hàng đã xóa
    public int clearLines() {
        int cleared = 0; // Đếm số hàng đã xóa
        for (int r = Config.ROWS-1; r>=0; r--) {
            boolean full = true; // Kiểm tra hàng có đầy không
            for (int c=0;c<Config.COLS;c++) if (grid[r][c]==null) { full=false; break; } // Kiểm tra từng ô trong hàng
            if (full) {
                cleared++; // Tăng số hàng đã xóa
                for (int rr=r; rr>0; rr--) grid[rr] = grid[rr-1]; // Di chuyển các hàng phía trên xuống
                grid[0] = new Cell[Config.COLS]; // Tạo hàng mới trống ở trên cùng
                r++; // Kiểm tra lại hàng hiện tại sau khi dịch chuyển
            }
        }
        return cleared; // Trả về số hàng đã xóa
    }

    // Lấy mảng grid hiện tại của bảng game
    public Cell[][] getGrid(){ return grid; }

    // Reset bảng game về trạng thái ban đầu (tất cả các ô đều null)
    public void reset() {
        for (int r=0;r<Config.ROWS;r++) for (int c=0;c<Config.COLS;c++) grid[r][c]=null;
    }
}
