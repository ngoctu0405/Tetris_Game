package core.blocks;

import core.Position;
import java.awt.Color; // <-- ĐÃ SỬA

public class BlockI extends Block {
    private boolean vertical = false;
    public BlockI() {
        color = Color.CYAN; // <-- Dùng java.awt.Color
        tiles = new Position[] { new Position(-1,3), new Position(-1,4), new Position(-1,5), new Position(-1,6) };
    }
    
    @Override public void rotate() {
        Position c = tiles[1].copy();
        if (!vertical) {
            tiles = new Position[] { new Position(c.row-1,c.col), new Position(c.row,c.col), new Position(c.row+1,c.col), new Position(c.row+2,c.col) };
        } else {
            tiles = new Position[] { new Position(c.row,c.col-1), new Position(c.row,c.col), new Position(c.row,c.col+1), new Position(c.row,c.col+2) };
        }
        vertical = !vertical;
    }

    // THÊM HÀM COPY
    @Override
    public Block copy() {
        BlockI newBlock = new BlockI();
        newBlock.copyStateFrom(this);
        newBlock.vertical = this.vertical;
        return newBlock;
    }
}