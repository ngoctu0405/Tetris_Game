package core.blocks;

import core.Position;
import java.awt.Color;

/**
 * BlockI - Khối I trong trò chơi Tetris
 * Khối này có thể xoay giữa hướng ngang và hướng dọc
 */
public class BlockI extends Block {
    // Biến kiểm tra khối đang ở hướng dọc (true) hay ngang (false)
    private boolean vertical = false;
    
    /**
     * Constructor: Khởi tạo khối I ở vị trí ban đầu (hướng ngang)
     * Màu: CYAN
     * Vị trí bắt đầu: 4 ô liên tiếp theo chiều ngang ở hàng -1 (trước khi xuất hiện trên bảng)
     */
    public BlockI() {
        color = Color.CYAN; // Màu cho khối I
        // tiles là mảng các ô (Position) kế thừa từ lớp Block
        tiles = new Position[] {
            new Position(-1, 3),
            new Position(-1, 4),
            new Position(-1, 5),
            new Position(-1, 6)
        };
    }
    
    /**
     * Xoay khối: chuyển giữa trạng thái ngang và dọc
     * Sử dụng ô thứ hai (index 1) làm tâm xoay để giữ sự tương đồng với cách xoay của khối I
     */
    @Override
    public void rotate() {
        // Sao chép vị trí ô làm tâm để tính toán vị trí mới
        Position c = tiles[1].copy();
        if (!vertical) {
            // Hiện đang ngang -> chuyển sang dọc: các ô xếp theo hàng (tăng row)
            tiles = new Position[] {
                new Position(c.row - 1, c.col),
                new Position(c.row,     c.col),
                new Position(c.row + 1, c.col),
                new Position(c.row + 2, c.col)
            };
        } else {
            // Hiện đang dọc -> chuyển sang ngang: các ô xếp theo cột (tăng col)
            tiles = new Position[] {
                new Position(c.row, c.col - 1),
                new Position(c.row, c.col),
                new Position(c.row, c.col + 1),
                new Position(c.row, c.col + 2)
            };
        }
        // Đổi trạng thái hướng sau khi xoay
        vertical = !vertical;
    }

    /**
     * Tạo bản sao của khối (deep copy trạng thái)
     * Gọi phương thức hỗ trợ từ lớp cha để sao chép các thuộc tính chung
     */
    @Override
    public Block copy() {
        BlockI newBlock = new BlockI();
        // Sao chép trạng thái (vị trí ô, màu, ...) từ instance hiện tại
        newBlock.copyStateFrom(this);
        // Sao chép trạng thái riêng (vertical)
        newBlock.vertical = this.vertical;
        return newBlock;
    }
}