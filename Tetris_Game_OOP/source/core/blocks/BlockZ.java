package core.blocks;

import core.Position;
import javafx.scene.paint.Color;

// Lớp BlockZ đại diện cho khối hình chữ Z trong trò chơi Tetris
public class BlockZ extends Block {
    private boolean rot = false; // Biến để theo dõi trạng thái xoay của khối

    // Constructor khởi tạo khối Z với màu đỏ và vị trí ban đầu
    public BlockZ() {
        color = Color.RED;
        tiles = new Position[] { new Position(-1, 3), new Position(-1, 4), new Position(0, 4), new Position(0, 5) };
    }

    // Phương thức xoay khối
    @Override
    public void rotate() {
        Position c = tiles[2].copy(); // Lưu vị trí trung tâm
        if (!rot) // Nếu chưa xoay
            tiles = new Position[] { new Position(c.row - 1, c.col), new Position(c.row, c.col),
                    new Position(c.row, c.col + 1), new Position(c.row + 1, c.col + 1) };
        else // Nếu đã xoay
            tiles = new Position[] { new Position(c.row, c.col - 1), new Position(c.row, c.col),
                    new Position(c.row - 1, c.col), new Position(c.row - 1, c.col + 1) };
        rot = !rot; // Đảo trạng thái xoay
    }
    
    // Phương thức sao chép khối
    @Override
    public Block copy() {
        BlockZ newBlock = new BlockZ(); // Tạo khối mới
        newBlock.copyStateFrom(this); // Sao chép trạng thái từ khối hiện tại
        newBlock.rot = this.rot; // Sao chép trạng thái xoay
        return newBlock; // Trả về khối mới
    }
}
