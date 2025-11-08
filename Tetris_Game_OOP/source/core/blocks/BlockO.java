package core.blocks;

import core.Position;
import javafx.scene.paint.Color;

public class BlockO extends Block {
    public BlockO() {
        color = Color.GOLD;
        tiles = new Position[] { new Position(-1,4), new Position(-1,5), new Position(0,4), new Position(0,5) };
    }

    @Override
    public void rotate() {
        /* O doesn't rotate */ }
    
    @Override 
    public Block copy() {
        BlockO newBlock = new BlockO();
        newBlock.copyStateFrom(this);
        return newBlock;
    }
}
