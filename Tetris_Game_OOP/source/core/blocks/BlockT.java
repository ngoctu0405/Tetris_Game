package core.blocks;

import core.Position;
import javafx.scene.paint.Color;

public class BlockT extends Block {
    private int rot = 0;
    public BlockT() {
        color = Color.MEDIUMPURPLE;
        tiles = new Position[] { new Position(-1,4), new Position(0,3), new Position(0,4), new Position(0,5) };
    }

    @Override
    public void rotate() {
        // simple rotation around tiles[2]
        Position c = tiles[2].copy();
        if (rot == 0)
            tiles = new Position[] { new Position(c.row - 1, c.col), new Position(c.row, c.col - 1),
                    new Position(c.row, c.col), new Position(c.row + 1, c.col) };
        else if (rot == 1)
            tiles = new Position[] { new Position(c.row, c.col + 1), new Position(c.row - 1, c.col),
                    new Position(c.row, c.col), new Position(c.row, c.col - 1) };
        else if (rot == 2)
            tiles = new Position[] { new Position(c.row + 1, c.col), new Position(c.row, c.col + 1),
                    new Position(c.row, c.col), new Position(c.row - 1, c.col) };
        else
            tiles = new Position[] { new Position(c.row, c.col - 1), new Position(c.row + 1, c.col),
                    new Position(c.row, c.col), new Position(c.row, c.col + 1) };
        rot = (rot + 1) % 4;
    }

    @Override
    public Block copy() {
        BlockT newBlock = new BlockT();
        newBlock.copyStateFrom(this);
        newBlock.rot = this.rot;
        return newBlock;
    }
}
