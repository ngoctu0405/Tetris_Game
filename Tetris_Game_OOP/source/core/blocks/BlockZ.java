package core.blocks;

import core.Position;
import java.awt.Color; // <-- Nhập lớp Color từ java.awt

public class BlockZ extends Block {

    // Biến để theo dõi trạng thái xoay của khối Z
    private boolean rot = false;

    // Hàm khởi tạo khối Z
    public BlockZ() {
        color = Color.RED; // <-- Sử dụng màu đỏ từ java.awt.Color
        // Khởi tạo 4 vị trí ban đầu của khối Z
        tiles = new Position[]{new Position(-1, 3), new Position(-1, 4), new Position(0, 4), new Position(0, 5)};
    }

    // Phương thức xoay khối Z
    @Override
    public void rotate() {
        // Lấy vị trí của khối trung tâm (vị trí thứ 2)
        Position c = tiles[2].copy();
        if (!rot) {
            // Xoay từ trạng thái nằm ngang sang trạng thái đứng
            tiles = new Position[]{new Position(c.row - 1, c.col), new Position(c.row, c.col), new Position(c.row, c.col + 1), new Position(c.row + 1, c.col + 1)}; 
        } else {
            // Xoay từ trạng thái đứng sang trạng thái nằm ngang
            tiles = new Position[]{new Position(c.row, c.col - 1), new Position(c.row, c.col), new Position(c.row - 1, c.col), new Position(c.row - 1, c.col + 1)};
        }
        rot = !rot; // Đảo trạng thái xoay
    }

    // Phương thức sao chép khối Z
    @Override
    public Block copy() {
        BlockZ newBlock = new BlockZ(); // Tạo khối Z mới
        newBlock.copyStateFrom(this); // Sao chép trạng thái từ khối hiện tại
        newBlock.rot = this.rot; // Sao chép trạng thái xoay
        return newBlock;
    }
}
