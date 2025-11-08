package core.blocks;

import core.Position;
import javafx.scene.paint.Color;

// Khối I trong game Tetris (khối thẳng)
public class BlockI extends Block {
    // Biến kiểm tra khối đang nằm dọc hay ngang
    private boolean vertical = false;

    // Constructor: Khởi tạo khối I với màu xanh và vị trí ban đầu
    public BlockI() {
        color = Color.CYAN;
        // Tạo mảng 4 ô vuông theo hình ngang
        tiles = new Position[] { new Position(-1,3), new Position(-1,4), new Position(-1,5), new Position(-1,6) };
    }

    @Override
    // Phương thức xoay khối
    public void rotate() {
        // Xoay quanh tâm của ô thứ hai
        Position c = tiles[1].copy();
        if (!vertical) {
            // Nếu đang ngang, xoay thành dọc
            tiles = new Position[] { new Position(c.row - 1, c.col), new Position(c.row, c.col),
                    new Position(c.row + 1, c.col), new Position(c.row + 2, c.col) };
        } else {
            // Nếu đang dọc, xoay thành ngang
            tiles = new Position[] { new Position(c.row, c.col - 1), new Position(c.row, c.col),
                    new Position(c.row, c.col + 1), new Position(c.row, c.col + 2) };
        }
        // Đảo trạng thái dọc/ngang
        vertical = !vertical;
    }
    
    @Override
    // Phương thức tạo bản sao của khối
    public Block copy() {
        BlockI newBlock = new BlockI();
        newBlock.copyStateFrom(this);
        newBlock.vertical = this.vertical;
        return newBlock;
    }
}
