package core.blocks;

import core.Position;
import java.awt.Color; // Nhập thư viện Color

public class BlockJ extends Block {

    private int rot = 0; // Biến lưu trạng thái quay (0-3)

    public BlockJ() {
        color = Color.BLUE; // Màu của khối J là xanh dương
        // Khởi tạo 4 vị trí tiles của khối J
        tiles = new Position[]{new Position(-1, 3), new Position(0, 3), new Position(0, 4), new Position(0, 5)};
    }

    @Override
    public void rotate() {
        // Lấy vị trí tâm xoay
        Position c = tiles[2].copy();
        
        // Xoay khối theo 4 trạng thái khác nhau
        if (rot == 0) {
            tiles = new Position[]{new Position(c.row - 1, c.col), new Position(c.row - 1, c.col + 1), new Position(c.row, c.col + 1), new Position(c.row + 1, c.col + 1)};
        } else if (rot == 1) {
            tiles = new Position[]{new Position(c.row, c.col + 1), new Position(c.row + 1, c.col + 1), new Position(c.row + 1, c.col), new Position(c.row + 1, c.col - 1)};
        } else if (rot == 2) {
            tiles = new Position[]{new Position(c.row + 1, c.col), new Position(c.row + 1, c.col - 1), new Position(c.row, c.col - 1), new Position(c.row - 1, c.col - 1)};
        } else {
            tiles = new Position[]{new Position(c.row, c.col - 1), new Position(c.row - 1, c.col - 1), new Position(c.row - 1, c.col), new Position(c.row - 1, c.col + 1)};
        }
        // Cập nhật trạng thái quay
        rot = (rot + 1) % 4;
    }

    // Tạo bản sao của khối J
    @Override
    public Block copy() {
        BlockJ newBlock = new BlockJ();
        newBlock.copyStateFrom(this); // Sao chép trạng thái từ khối hiện tại
        newBlock.rot = this.rot; // Sao chép trạng thái quay
        return newBlock;
    }
}
