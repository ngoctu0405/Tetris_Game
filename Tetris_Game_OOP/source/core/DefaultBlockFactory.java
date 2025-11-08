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
        
         switch (RAND.nextInt(7)) {
            case 0: return new BlockI();
            case 1: return new BlockO();
            case 2: return new BlockT();
            case 3: return new BlockS();
            case 4: return new BlockZ();
            case 5: return new BlockJ();
            default: return new BlockL();
        }
    }
}   
