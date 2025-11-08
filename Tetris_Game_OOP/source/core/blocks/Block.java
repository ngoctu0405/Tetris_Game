package core.blocks;

import core.Position;
import javafx.scene.paint.Color;

public abstract class Block {
    protected Position[] tiles;
    protected javafx.scene.paint.Color color;

    public abstract void rotate();
    public void moveDown() { for (var p: tiles) p.row++; }
    public void moveLeft() { for (var p: tiles) p.col--; }
    public void moveRight(){ for (var p: tiles) p.col++; }
    public Position[] getTiles(){ return tiles; }
    public javafx.scene.paint.Color getColor(){ return color; }
    // copy factory: must be overridden by subclasses for correct copy
    public abstract Block copy();

    protected void copyStateFrom(Block source) {
        this.color = source.color;
        this.tiles = new Position[source.tiles.length];
        for (int i = 0; i < source.tiles.length; i++) {
            this.tiles[i] = source.tiles[i].copy();
        }
    }
}
