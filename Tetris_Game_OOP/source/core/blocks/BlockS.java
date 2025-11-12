package core.blocks;

import core.Position;
import java.awt.Color; // <-- Đã sửa

public class BlockS extends Block {

    private boolean rot = false; // Trạng thái quay của khối

    public BlockS() {
        color = Color.GREEN; // <-- Đã sửa (Color.LIMEGREEN -> Color.GREEN)
        // Khởi tạo vị trí ban đầu của 4 ô vuông của khối S
        tiles = new Position[]{new Position(-1, 5), new Position(-1, 4), new Position(0, 4), new Position(0, 3)};
    }

    // Xoay khối theo chiều kim đồng hồ
    @Override
    public void rotate() {
        Position c = tiles[2].copy(); // Lấy điểm tâm xoay
        if (!rot) {
            // Xoay từ trạng thái ngang sang trạng thái dọc
            tiles = new Position[]{new Position(c.row - 1, c.col), new Position(c.row, c.col), new Position(c.row, c.col - 1), new Position(c.row + 1, c.col - 1)}; 
        }else {
            // Xoay từ trạng thái dọc sang trạng thái ngang
            tiles = new Position[]{new Position(c.row, c.col + 1), new Position(c.row, c.col), new Position(c.row - 1, c.col), new Position(c.row - 1, c.col - 1)};
        }
        rot = !rot; // Đảo trạng thái quay
    }

    // Sao chép khối BlockS và trạng thái của nó
    @Override
    public Block copy() {
        BlockS newBlock = new BlockS();
        newBlock.copyStateFrom(this);
        newBlock.rot = this.rot; // Sao chép trạng thái quay
        return newBlock;
    }
}
