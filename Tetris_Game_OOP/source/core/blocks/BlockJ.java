package core.blocks;

import core.Position;
import java.awt.Color; // <-- ĐÃ SỬAa

public class BlockJ extends Block {
    private int rot=0;
    public BlockJ() {
        color = Color.BLUE; // <-- ĐÃ SỬA (Color.DARKBLUE -> Color.BLUE)
        tiles = new Position[] { new Position(-1,3), new Position(0,3), new Position(0,4), new Position(0,5) };
    }
    
    @Override public void rotate() {
        Position c = tiles[2].copy();
        if (rot==0) tiles = new Position[] { new Position(c.row-1,c.col), new Position(c.row-1,c.col+1), new Position(c.row,c.col+1), new Position(c.row+1,c.col+1) };
        else if (rot==1) tiles = new Position[] { new Position(c.row,c.col+1), new Position(c.row+1,c.col+1), new Position(c.row+1,c.col), new Position(c.row+1,c.col-1) };
        else if (rot==2) tiles = new Position[] { new Position(c.row+1,c.col), new Position(c.row+1,c.col-1), new Position(c.row,c.col-1), new Position(c.row-1,c.col-1) };
        else tiles = new Position[] { new Position(c.row,c.col-1), new Position(c.row-1,c.col-1), new Position(c.row-1,c.col), new Position(c.row-1,c.col+1) };
        rot = (rot+1)%4;
    }

    // THÊM HÀM COPY
    @Override
    public Block copy() {
        BlockJ newBlock = new BlockJ();
        newBlock.copyStateFrom(this);
        newBlock.rot = this.rot;
        return newBlock;
    }
}