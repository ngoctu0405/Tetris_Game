package core.blocks;

import core.Position;

public abstract class Block {
    protected Position[] tiles;
    protected java.awt.Color color; // <-- Dùng java.awt.Color

    public abstract void rotate();
    public void moveDown() { for (var p: tiles) p.row++; }
    public void moveLeft() { for (var p: tiles) p.col--; }
    public void moveRight(){ for (var p: tiles) p.col++; }
    public Position[] getTiles(){ return tiles; }
    public java.awt.Color getColor(){ return color; }

    // ======================================================
    // SỬA LỖI COPY (BUG TỪ CODE CŨ CỦA BẠN)
    // ======================================================
    public abstract Block copy();

    protected void copyStateFrom(Block source) {
        this.color = source.color;
        this.tiles = new Position[source.tiles.length];
        for (int i = 0; i < source.tiles.length; i++) {
            this.tiles[i] = source.tiles[i].copy(); 
        }
    }
}