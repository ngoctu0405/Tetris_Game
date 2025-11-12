package core;

import core.blocks.Block;

// Lớp quản lý lưới (board) của trò chơi Tetris
public class Board {
    // Mảng 2D lưu các ô đã bị chiếm bởi các block (null nếu trống)
    private final Cell[][] grid = new Cell[Config.ROWS][Config.COLS];

    // Kiểm tra vị trí (r,c) có nằm trong bảng hay không
    public boolean isInside(int r, int c) { return r>=0 && r<Config.ROWS && c>=0 && c<Config.COLS; }

    // Kiểm tra xem block b có thể đặt vào lưới hiện tại hay không
    // Cho phép các ô có row < 0 (nằm phía trên màn hình, đang rơi vào)
    public boolean canPlace(Block b) {
        for (var p : b.getTiles()) {
            if (p.row<0) continue; // cho phép phần nằm phía trên màn hình
            if (!isInside(p.row, p.col)) return false; // ra ngoài biên
            if (grid[p.row][p.col] != null) return false; // ô đã bị chiếm
        }
        return true;
    }

    // Đặt block b vào lưới (ghi nhận màu ở các ô tương ứng)
    public void place(Block b) {
        for (var p : b.getTiles()) {
            if (p.row<0) continue; // bỏ qua phần nằm phía trên màn hình
            grid[p.row][p.col] = new Cell(b.getColor()); // <-- Dùng java.awt.Color
        }
    }
   
    // Xóa các hàng đầy và dịch các hàng phía trên xuống, trả về số hàng đã xóa
    public int clearLines() {
        int cleared = 0;
        for (int r = Config.ROWS-1; r>=0; r--) {
            boolean full = true;
            for (int c=0;c<Config.COLS;c++) if (grid[r][c]==null) { full=false; break; }
            if (full) {
                cleared++;
                // Dịch tất cả hàng phía trên xuống 1 hàng
                for (int rr=r; rr>0; rr--) grid[rr] = grid[rr-1];
                // Tạo hàng trống ở trên cùng
                grid[0] = new Cell[Config.COLS];
                r++; // kiểm tra lại cùng chỉ số hàng sau khi dịch
            }
        }
        return cleared;
    }

    // Trả về tham chiếu tới lưới (dùng để vẽ hoặc kiểm tra)
    public Cell[][] getGrid(){ return grid; }

    // Đặt lại lưới về trạng thái trống
    public void reset() {
        for (int r=0;r<Config.ROWS;r++) for (int c=0;c<Config.COLS;c++) grid[r][c]=null;
    }
}