package core.blocks;

import core.Position;
import javafx.scene.paint.Color;

/**
 * Lớp BlockS: biểu diễn khối hình "S" trong trò Tetris.
 * Mỗi khối gồm 4 ô (tiles) lưu dưới dạng Position(row, col).
 */
public class BlockS extends Block {
    // trạng thái xoay: false = mặc định, true = đã xoay 90 độ
    private boolean rot = false;

    public BlockS() {
        // màu xanh lá cho khối S
        color = Color.LIMEGREEN;
        // vị trí khởi tạo của 4 ô (row, col)
        tiles = new Position[] {
            new Position(-1, 5),
            new Position(-1, 4),
            new Position(0, 4),
            new Position(0, 3)
        };
    }

    @Override
    public void rotate() {
        // dùng ô thứ 3 làm điểm neo (center) để tính toán vị trí mới
        Position c = tiles[2].copy();

        if (!rot)
            // chuyển từ trạng thái ngang -> dọc (xoay 90 độ)
            tiles = new Position[] {
                new Position(c.row - 1, c.col),
                new Position(c.row,     c.col),
                new Position(c.row,     c.col - 1),
                new Position(c.row + 1, c.col - 1)
            };
        else
            // chuyển từ trạng thái dọc -> ngang (xoay ngược lại)
            tiles = new Position[] {
                new Position(c.row,     c.col + 1),
                new Position(c.row,     c.col),
                new Position(c.row - 1, c.col),
                new Position(c.row - 1, c.col - 1)
            };

        // đổi trạng thái xoay
        rot = !rot;
    }
    
    @Override 
    public Block copy() {
        // tạo bản sao hoàn chỉnh, bao gồm trạng thái rot
        BlockS newBlock = new BlockS();
        newBlock.copyStateFrom(this);
        newBlock.rot = this.rot;
        return newBlock;
    }
}
