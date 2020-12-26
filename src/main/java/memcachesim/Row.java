package memcachesim;

public class Row {
    private final MemoryConfig memoryConfig;
    private final Block block;
    private int label = 0;
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
        this.setLabelByAddress(address);
        this.block.getCells()[cell].setValue(value);
    }

    public void writeBlock(int label, Block block) {
        this.block.copyBlock(block);
        this.label = label;
    }

    public boolean hasAddress(int address) {
        MemoryConfig config = this.memoryConfig;
        int shiftSize = config.getBitsCells() + config.getBitsCacheSets();
        int addressLabel = address >> shiftSize;
        return this.label == addressLabel;
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
        return this.label;
    }

    public Boolean isChanged() {
        return changed;
    }

    private void resetScore() {
        this.score = 0;
    }

    private void setLabelByAddress(int address) {
        MemoryConfig config = this.memoryConfig;
        int shiftSize = config.getBitsCells() + config.getBitsCacheSets();
        this.label = address >> shiftSize;
    }
}
