package core.blocks;

import core.Position;
import javafx.scene.paint.Color;

/**
 * Lớp BlockO đại diện cho khối O (hình vuông 2x2) trong trò chơi.
 */
public class BlockO extends Block {
    public BlockO() {
        // Màu của khối O
        color = Color.GOLD;
        // Vị trí các ô (tiles) ban đầu của khối O (2 hàng x 2 cột)
        tiles = new Position[] { new Position(-1,4), new Position(-1,5), new Position(0,4), new Position(0,5) };
    }

    @Override
    public void rotate() {
        // Khối O không cần xoay vì xoay vẫn giữ nguyên hình dạng
    }
    
    @Override 
    public Block copy() {
        // Tạo một thể hiện mới của BlockO và sao chép trạng thái hiện tại vào đó
        BlockO newBlock = new BlockO();
        newBlock.copyStateFrom(this);
        return newBlock;
    }
}
