package memcachesim;

public class Memory {
    private final MemoryConfig memoryConfig;
    private final Cache cache;
    private Block[] blocks;
    private int hits;
    private int miss;

    public Memory(MemoryConfig memoryConfig) {
        this.memoryConfig = memoryConfig;
        this.cache = new Cache(memoryConfig);
        this.generateBlocks(memoryConfig);
    }

    public void writeInAddress(int address, int value) {
        CacheResponse response = this.cache.writeInAddress(address, value);
        if (response.isHit()) {
            this.hits++;
            return;
        }

        this.miss++;
        this.writeInMemoryAddress(address, value);
    }

    public int readInAddress(int address) {
        CacheResponse response = this.cache.readInAddress(address);
        if (response.isHit()) {
            this.hits++;
            return response.getValue();
        }

        this.miss++;
        Block block = this.getBlockByAddress(address);
        Row row = this.cache.cacheBlock(block);

        if (row.isChanged()) {
            this.writeBlock(row.getBlock());
        }

        int cellSize = (int) Math.pow(this.memoryConfig.getBitsCells(), 2);
        int cellAddress = address & (cellSize -1);

        return block.readInCell(cellAddress);
    }

    public void writeInMemoryAddress(int address, int value) {
        int block = address >> this.memoryConfig.getBitsCells();
        int cellsInBlock = this.memoryConfig.getCellsPerBlock();
        int cell = address & cellsInBlock -1;
        this.blocks[block].writeInCell(cell, value);
    }

    public int readInMemoryAddress(int address) {
        int block = address >> this.memoryConfig.getBitsCells();
        int cellsInBlock = this.memoryConfig.getCellsPerBlock();
        int cell = address & cellsInBlock -1;
        return this.blocks[block].readInCell(cell);
    }

    public void writeBlock(Block block) {
        this.blocks[block.getAddress()].copyBlock(block);
    }

    public Block getBlockByAddress(int address) {
        int block = address >> this.memoryConfig.getBitsCells();
        return  this.blocks[block];
    }

    public int getHits() {
        return hits;
    }

    public int getMiss() {
        return miss;
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
