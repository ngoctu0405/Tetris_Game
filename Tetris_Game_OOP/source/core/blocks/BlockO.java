package core.blocks;

import core.Position;
import java.awt.Color; // <-- ĐÃ SỬA

public class BlockO extends Block {

    public BlockO() {
        color = Color.YELLOW; // <-- ĐÃ SỬA (Color.GOLD -> Color.YELLOW)
        tiles = new Position[]{new Position(-1, 4), new Position(-1, 5), new Position(0, 4), new Position(0, 5)};
    }

    @Override
    public void rotate() {
        /* O doesn't rotate */ }

    // THÊM HÀM COPY
    @Override
    public Block copy() {
        BlockO newBlock = new BlockO();
        newBlock.copyStateFrom(this);
        return newBlock;
    }
}
