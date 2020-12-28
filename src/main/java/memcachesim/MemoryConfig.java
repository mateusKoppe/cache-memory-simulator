package memcachesim;

public class MemoryConfig {
    int bitsCellValue = 8;
    int bitsCells = 2;
    int bitsBlocks = 5;
    int bitsCacheRow = 3;
    int bitsCacheSets = 2;

    public MemoryConfig() {}

    public int getBlocksAmount() {
        return (int) Math.pow(2, this.bitsBlocks);
    }

    public int getCellsPerBlock() {
        return (int) Math.pow(2, this.bitsCells);
    }

    public int getCacheSetsAmount() {
        return (int) Math.pow(2, this.bitsCacheSets);
    }

    public int getValueLimit() {
        return (int) Math.pow(2, this.bitsCellValue) - 1;
    }

    public int getRowsInSet() {
        return (int) Math.pow(2, this.getBitsCacheRow() - this.getBitsCacheSets());
    }

    public int getBitsCacheLabel() {
        return this.bitsBlocks - this.bitsCacheSets;
    }

    public int getBitsCellValue() {
        return bitsCellValue;
    }

    public void setBitsCellValue(int bitsCellValue) {
        this.bitsCellValue = bitsCellValue;
    }

    public int getBitsCells() {
        return bitsCells;
    }

    public void setBitsCells(int bitsCells) {
        this.bitsCells = bitsCells;
    }

    public int getBitsBlocks() {
        return bitsBlocks;
    }

    public void setBitsBlocks(int bitsBlocks) {
        this.bitsBlocks = bitsBlocks;
    }

    public int getBitsCacheRow() {
        return bitsCacheRow;
    }

    public void setBitsCacheRow(int bitsCacheRow) {
        this.bitsCacheRow = bitsCacheRow;
    }

    public int getBitsCacheSets() {
        return bitsCacheSets;
    }

    public void setBitsCacheSets(int bitsCacheSets) {
        this.bitsCacheSets = bitsCacheSets;
    }
}
