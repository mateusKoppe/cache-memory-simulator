package memcachesim;

public class Block {
    private final MemoryConfig memoryConfig;
    private int address = 0b0;
    private Cell[] cells;

    public Block(MemoryConfig memoryConfig) {
        this(memoryConfig, 0b0);
    }

    public Block(MemoryConfig memoryConfig, int address) {
        this.memoryConfig = memoryConfig;
        this.address = address;
        this.generateCells(memoryConfig);
    }

    public void writeInCell (int cell, int value) {
        this.cells[cell].setValue(value);
    }

    public int readInCell (int cell) {
        return this.cells[cell].getValue();
    }

    public void copyBlock (Block copyBlock) {
        this.address = copyBlock.getAddress();
        for (int i = 0; i < memoryConfig.getCellsPerBlock(); i++) {
            int value = copyBlock.getCells()[i].getValue();
            this.cells[i].setValue(value);
        }
    }

    public int getAddress() {
        return this.address;
    }

    public Cell[] getCells() {
        return this.cells;
    }

    private void generateCells (MemoryConfig memoryConfig) {
        this.cells = new Cell[memoryConfig.getCellsPerBlock()];
        for (int i = 0; i < memoryConfig.getCellsPerBlock(); i++) {
            this.cells[i] = new Cell(memoryConfig);
        }
    }
}
