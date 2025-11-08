package core.blocks;

import core.Position;
import javafx.scene.paint.Color;

public class BlockZ extends Block {
    private boolean rot=false;
    public BlockZ() {
        color = Color.RED;
        tiles = new Position[] { new Position(-1,3), new Position(-1,4), new Position(0,4), new Position(0,5) };
    }

    @Override
    public void rotate() {
        Position c = tiles[2].copy();
        if (!rot)
            tiles = new Position[] { new Position(c.row - 1, c.col), new Position(c.row, c.col),
                    new Position(c.row, c.col + 1), new Position(c.row + 1, c.col + 1) };
        else
            tiles = new Position[] { new Position(c.row, c.col - 1), new Position(c.row, c.col),
                    new Position(c.row - 1, c.col), new Position(c.row - 1, c.col + 1) };
        rot = !rot;
    }
    
    @Override
    public Block copy() {
        BlockZ newBlock = new BlockZ();
        newBlock.copyStateFrom(this);
        newBlock.rot = this.rot;
        return newBlock;
    }
}
