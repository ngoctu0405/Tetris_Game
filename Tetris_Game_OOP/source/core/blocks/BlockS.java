package core.blocks;

import core.Position;
import java.awt.Color; // <-- ĐÃ SỬA

public class BlockS extends Block {
    private boolean rot=false;
    public BlockS() {
        color = Color.GREEN; // <-- ĐÃ SỬA (Color.LIMEGREEN -> Color.GREEN)
        tiles = new Position[] { new Position(-1,5), new Position(-1,4), new Position(0,4), new Position(0,3) };
    }
    
    @Override public void rotate() {
        Position c = tiles[2].copy();
        if (!rot) tiles = new Position[] { new Position(c.row-1,c.col), new Position(c.row,c.col), new Position(c.row,c.col-1), new Position(c.row+1,c.col-1) };
        else tiles = new Position[] { new Position(c.row,c.col+1), new Position(c.row,c.col), new Position(c.row-1,c.col), new Position(c.row-1,c.col-1) };
        rot = !rot;
    }

    // THÊM HÀM COPY
    @Override
    public Block copy() {
        BlockS newBlock = new BlockS();
        newBlock.copyStateFrom(this);
        newBlock.rot = this.rot;
        return newBlock;
    }
}