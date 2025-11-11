package core.blocks;

import core.Position;
import java.awt.Color; // <-- ĐÃ SỬA

public class BlockL extends Block {
    private int rot=0;
    public BlockL() {
        color = Color.ORANGE; // <-- Dùng java.awt.Color
        tiles = new Position[] { new Position(-1,5), new Position(0,3), new Position(0,4), new Position(0,5) };
    }
    
    @Override public void rotate() {
        Position c = tiles[2].copy();
        if (rot==0) tiles = new Position[] { new Position(c.row-1,c.col), new Position(c.row,c.col), new Position(c.row+1,c.col), new Position(c.row+1,c.col+1) };
        else if (rot==1) tiles = new Position[] { new Position(c.row,c.col+1), new Position(c.row,c.col), new Position(c.row,c.col-1), new Position(c.row-1,c.col-1) };
        else if (rot==2) tiles = new Position[] { new Position(c.row+1,c.col), new Position(c.row,c.col), new Position(c.row-1,c.col), new Position(c.row-1,c.col-1) };
        else tiles = new Position[] { new Position(c.row,c.col-1), new Position(c.row,c.col), new Position(c.row,c.col+1), new Position(c.row+1,c.col+1) };
        rot = (rot+1)%4;
    }

    // THÊM HÀM COPY
    @Override
    public Block copy() {
        BlockL newBlock = new BlockL();
        newBlock.copyStateFrom(this);
        newBlock.rot = this.rot;
        return newBlock;
    }
}