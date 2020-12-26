package memcachesim;

public class Memory {
    private final MemoryConfig memoryConfig;
    private final Cache cache;
    private Block[] blocks;

    public Memory(MemoryConfig memoryConfig) {
        this.memoryConfig = memoryConfig;
        this.cache = new Cache(memoryConfig);
        this.generateBlocks(memoryConfig);
    }

    public void writeInAddress(int address, int value) {
        int block = address >> this.memoryConfig.getBitsCells();
        int cellsInBlock = this.memoryConfig.getCellsPerBlock();
        int cell = address & cellsInBlock -1;
        this.blocks[block].writeInCell(cell, value);
    }

    public int readInAddress(int address) {
        int block = address >> this.memoryConfig.getBitsCells();
        int cellsInBlock = this.memoryConfig.getCellsPerBlock();
        int cell = address & cellsInBlock -1;
        return this.blocks[block].readInCell(cell);
    }

    public Cache getCache() {
        return cache;
    }

    public Block[] getBlocks() {
        return blocks;
    }

    private void generateBlocks(MemoryConfig memoryConfig) {
        this.blocks = new Block[memoryConfig.getBlocksAmount()];
        for (int i = 0; i < memoryConfig.getBlocksAmount(); i++) {
            this.blocks[i] = new Block(memoryConfig, i);
        }
    }
}
