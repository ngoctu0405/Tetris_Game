package core.blocks;

import core.Position;
import javafx.scene.paint.Color;

public class BlockT extends Block {
    private int rot = 0; // Biến lưu trữ trạng thái xoay của khối

    public BlockT() {
        color = Color.MEDIUMPURPLE; // Màu của khối
        tiles = new Position[] { new Position(-1,4), new Position(0,3), new Position(0,4), new Position(0,5) }; // Vị trí các ô của khối
    }

    @Override
    public void rotate() {
        // Xoay khối quanh ô giữa (tiles[2])
        Position c = tiles[2].copy(); // Sao chép vị trí ô giữa
        if (rot == 0)
            tiles = new Position[] { new Position(c.row - 1, c.col), new Position(c.row, c.col - 1),
                    new Position(c.row, c.col), new Position(c.row + 1, c.col) }; // Xoay 90 độ
        else if (rot == 1)
            tiles = new Position[] { new Position(c.row, c.col + 1), new Position(c.row - 1, c.col),
                    new Position(c.row, c.col), new Position(c.row, c.col - 1) }; // Xoay 180 độ
        else if (rot == 2)
            tiles = new Position[] { new Position(c.row + 1, c.col), new Position(c.row, c.col + 1),
                    new Position(c.row, c.col), new Position(c.row - 1, c.col) }; // Xoay 270 độ
        else
            tiles = new Position[] { new Position(c.row, c.col - 1), new Position(c.row + 1, c.col),
                    new Position(c.row, c.col), new Position(c.row, c.col + 1) }; // Xoay về 0 độ
        rot = (rot + 1) % 4; // Cập nhật trạng thái xoay
    }

    @Override
    public Block copy() {
        BlockT newBlock = new BlockT(); // Tạo khối mới
        newBlock.copyStateFrom(this); // Sao chép trạng thái từ khối hiện tại
        newBlock.rot = this.rot; // Sao chép trạng thái xoay
        return newBlock; // Trả về khối mới
    }
}
