package core.blocks;

import core.Position;
import javafx.scene.paint.Color;

// Lớp đại diện cho khối hình chữ L trong Tetris
public class BlockL extends Block {
    // trạng thái quay (0-3)
    private int rot = 0;

    // Khởi tạo: gán màu và vị trí ban đầu của 4 ô
    public BlockL() {
        color = Color.ORANGE;
        tiles = new Position[] { new Position(-1,5), new Position(0,3), new Position(0,4), new Position(0,5) };
    }

    // Quay khối theo chiều kim đồng hồ; mốc quay là tiles[2]
    @Override
    public void rotate() {
        // sao chép vị trí mốc để tính toán các ô sau khi quay
        Position c = tiles[2].copy();

        if (rot == 0)
            // trạng thái 0 -> 1
            tiles = new Position[] {
                new Position(c.row - 1, c.col),
                new Position(c.row, c.col),
                new Position(c.row + 1, c.col),
                new Position(c.row + 1, c.col + 1)
            };
        else if (rot == 1)
            // trạng thái 1 -> 2
            tiles = new Position[] {
                new Position(c.row, c.col + 1),
                new Position(c.row, c.col),
                new Position(c.row, c.col - 1),
                new Position(c.row - 1, c.col - 1)
            };
        else if (rot == 2)
            // trạng thái 2 -> 3
            tiles = new Position[] {
                new Position(c.row + 1, c.col),
                new Position(c.row, c.col),
                new Position(c.row - 1, c.col),
                new Position(c.row - 1, c.col - 1)
            };
        else
            // trạng thái 3 -> 0
            tiles = new Position[] {
                new Position(c.row, c.col - 1),
                new Position(c.row, c.col),
                new Position(c.row, c.col + 1),
                new Position(c.row + 1, c.col + 1)
            };

        // cập nhật trạng thái quay vòng quanh 0-3
        rot = (rot + 1) % 4;
    }

    // Tạo bản sao của khối, bao gồm trạng thái quay
    @Override
    public Block copy() {
        BlockL newBlock = new BlockL();
        newBlock.copyStateFrom(this);
        newBlock.rot = this.rot;
        return newBlock;
    }
}
