package core.blocks;

import core.Position;
import java.awt.Color; // Nhập thư viện Color

public class BlockT extends Block {

    private int rot = 0; // Biến lưu trạng thái xoay (0-3)

    public BlockT() {
        color = Color.MAGENTA; // Màu khối T là tím đậm
        tiles = new Position[]{new Position(-1, 4), new Position(0, 3), new Position(0, 4), new Position(0, 5)}; // Vị trí ban đầu của khối T
    }

    @Override
    public void rotate() {
        Position c = tiles[2].copy(); // Lấy tâm xoay (vị trí thứ 2)
        if (rot == 0) {
            // Xoay từ trạng thái 0 sang trạng thái 1
            tiles = new Position[]{new Position(c.row - 1, c.col), new Position(c.row, c.col - 1), new Position(c.row, c.col), new Position(c.row + 1, c.col)}; 
        }else if (rot == 1) {
            // Xoay từ trạng thái 1 sang trạng thái 2
            tiles = new Position[]{new Position(c.row, c.col + 1), new Position(c.row - 1, c.col), new Position(c.row, c.col), new Position(c.row, c.col - 1)}; 
        }else if (rot == 2) {
            // Xoay từ trạng thái 2 sang trạng thái 3
            tiles = new Position[]{new Position(c.row + 1, c.col), new Position(c.row, c.col + 1), new Position(c.row, c.col), new Position(c.row - 1, c.col)}; 
        }else {
            // Xoay từ trạng thái 3 trở về trạng thái 0
            tiles = new Position[]{new Position(c.row, c.col - 1), new Position(c.row + 1, c.col), new Position(c.row, c.col), new Position(c.row, c.col + 1)};
        }
        rot = (rot + 1) % 4; // Cập nhật trạng thái xoay
    }

    // Sao chép khối T
    @Override
    public Block copy() {
        BlockT newBlock = new BlockT(); // Tạo khối T mới
        newBlock.copyStateFrom(this); // Sao chép trạng thái
        newBlock.rot = this.rot; // Sao chép trạng thái xoay
        return newBlock;
    }
}
