package core;

import core.blocks.Block;
import javafx.scene.paint.Color;

public class Board {
    private final Cell[][] grid = new Cell[Config.ROWS][Config.COLS];

    public boolean isInside(int r, int c) { return r>=0 && r<Config.ROWS && c>=0 && c<Config.COLS; }

    public boolean canPlace(Block b) {
        for (var p : b.getTiles()) {
            if (p.row<0) continue;
            if (!isInside(p.row, p.col)) return false;
            if (grid[p.row][p.col] != null) return false;
        }
        return true;
    }

    public void place(Block b) {
        for (var p : b.getTiles()) {
            if (p.row<0) continue;
            grid[p.row][p.col] = new Cell(b.getColor());
        }
    }

    public int clearLines() {
        int cleared = 0;
        for (int r = Config.ROWS-1; r>=0; r--) {
            boolean full = true;
            for (int c=0;c<Config.COLS;c++) if (grid[r][c]==null) { full=false; break; }
            if (full) {
                cleared++;
                for (int rr=r; rr>0; rr--) grid[rr] = grid[rr-1];
                grid[0] = new Cell[Config.COLS];
                r++;
            }
        }
        return cleared;
    }

    public Cell[][] getGrid(){ return grid; }

    public void reset() {
        for (int r=0;r<Config.ROWS;r++) for (int c=0;c<Config.COLS;c++) grid[r][c]=null;
    }
}
