package core;

import core.blocks.Block;

public interface BlockFactory {
    // chỉ cần 1 hàm này thôi
    Block createRandom();
}
