package core;

import core.blocks.*;
import java.util.Random;
public class DefaultBlockFactory implements BlockFactory {
    private final Random RAND = new Random();

    @Override
    public Block createRandom() {
        // spawn ở hàng 0, canh giữa theo số cột hiện có 
        int row = 0;
        int col = Config.COLS / 2 - 2; // bớt 2 để khối 4 ô không bị lệch phải
        int r = RAND.nextInt(7);
        
        switch (r) {
            case 0: return new blocks.IBlock(row, col);
            case 1: return new blocks.JBlock(row, col);
            case 2: return new blocks.LBlock(row, col);
            case 3: return new blocks.OBlock(row, col);
            case 4: return new blocks.SBlock(row, col);
            case 5: return new blocks.TBlock(row, col);
            case 6: return new blocks.ZBlock(row, col);
        }
    }
}   
