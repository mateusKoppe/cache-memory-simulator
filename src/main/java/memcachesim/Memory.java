package memcachesim;

import java.security.InvalidParameterException;

public class Memory {
    private final MemoryConfig memoryConfig;
    private final Cache cache;
    private Block[] blocks;

    public Memory(MemoryConfig memoryConfig) {
        this.memoryConfig = memoryConfig;
        this.cache = new Cache(memoryConfig);
        this.generateCells(memoryConfig);
    }

    private void generateCells(MemoryConfig memoryConfig) {
        this.blocks = new Block[memoryConfig.getBlocksAmount()];
        for (int i = 0; i < memoryConfig.getBlocksAmount(); i++) {
            this.blocks[i] = new Block(memoryConfig);
        }
    }



    public Cache getCache() {
        return cache;
    }

    public Block[] getBlocks() {
        return blocks;
    }
}
