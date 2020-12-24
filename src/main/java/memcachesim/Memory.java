package memcachesim;

import java.security.InvalidParameterException;

public class Memory {
    private Block[] blocks;

    public Memory(int blocks) {
        if (blocks <= 0) {
            throw new InvalidParameterException("blocks cannot be less than 1");
        }
        this.generateCells(blocks);
    }

    private void generateCells(int blocks) {
        this.blocks = new Block[blocks];
        for (int i = 0; i < blocks; i++) {
            this.blocks[i] = new Block(8);
        }
    }

    public Block[] getBlocks() {
        return blocks;
    }
}
