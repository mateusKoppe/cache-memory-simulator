package memcachesim;

public class Row {
    private final MemoryConfig memoryConfig;
    private final Block block;
    private Boolean changed = false;
    private int score = 0;

    Row (MemoryConfig memoryConfig) {
        this.memoryConfig = memoryConfig;
        this.block = new Block(memoryConfig);
    }

    public int readInAddress(int address) {
        int cellsInBlock = this.memoryConfig.getCellsPerBlock();
        int cell = address & cellsInBlock -1;
        this.resetScore();
        return this.block.getCells()[cell].getValue();
    }

    public void writeInAddress(int address, int value) {
        int cellsInBlock = this.memoryConfig.getCellsPerBlock();
        int cell = address & cellsInBlock -1;
        this.resetScore();
        this.changed = true;
        this.block.getCells()[cell].setValue(value);
    }

    public void writeBlock(Block block) {
        this.block.copyBlock(block);
    }

    public boolean hasAddress(int address) {
        MemoryConfig config = this.memoryConfig;
        int shiftSize = config.getBitsCells();
        int addressLabel = address >> shiftSize;
        return this.block.getAddress() == addressLabel;
    }

    public void incrementScore() {
        this.score++;
    }

    public Block getBlock() {
        return block;
    }

    public int getScore() {
        return this.score;
    }

    public int getLabel() {
        MemoryConfig config = this.memoryConfig;
        int shiftSize = config.getBitsCacheSets();
        return this.block.getAddress() >> shiftSize;
    }

    public Boolean isChanged() {
        return changed;
    }

    private void resetScore() {
        this.score = 0;
    }
}
