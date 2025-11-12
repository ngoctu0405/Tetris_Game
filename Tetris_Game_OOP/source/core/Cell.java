package core;

import java.awt.Color; 

/**
 * Lớp đại diện cho một ô trong trò chơi Tetris.
 * Mỗi ô có một màu sắc xác định.
 */
public class Cell {
    public final Color color;   
    public Cell(Color c) { this.color = c; }
}