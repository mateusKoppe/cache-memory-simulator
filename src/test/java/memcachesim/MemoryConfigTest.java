package memcachesim;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemoryConfigTest {
    private MemoryConfig memoryConfig;

    // Todo: Test with invalid values
    @BeforeEach
    void setUp() {
        this.memoryConfig = new MemoryConfig();
    }

    @Test
    void blocksAmount() {
        this.memoryConfig.setBitsBlocks(4);
        assertEquals(this.memoryConfig.getBlocksAmount(), 16);
        this.memoryConfig.setBitsBlocks(5);
        assertEquals(this.memoryConfig.getBlocksAmount(), 32);
    }

    @Test
    void setsAmount() {
        this.memoryConfig.setBitsCacheSets(2);
        assertEquals(this.memoryConfig.getCacheSetsAmount(), 4);
        this.memoryConfig.setBitsCacheSets(3);
        assertEquals(this.memoryConfig.getCacheSetsAmount(), 8);
    }

    @Test
    void cellsPerBlock() {
        this.memoryConfig.setBitsCells(2);
        assertEquals(this.memoryConfig.getCellsPerBlock(), 4);
        this.memoryConfig.setBitsCells(5);
        assertEquals(this.memoryConfig.getCellsPerBlock(), 32);
    }

    @Test
    void valueLimit() {
        this.memoryConfig.setBitsCellValue(2);
        assertEquals(this.memoryConfig.getValueLimit(), 3);
        this.memoryConfig.setBitsCellValue(5);
        assertEquals(this.memoryConfig.getValueLimit(), 31);
    }

    @Test
    void rowsInSet() {
        this.memoryConfig.setBitsCacheRow(4);
        this.memoryConfig.setBitsCacheSets(2);
        assertEquals(this.memoryConfig.getRowsInSet(), 4);
        this.memoryConfig.setBitsCacheRow(5);
        assertEquals(this.memoryConfig.getRowsInSet(), 8);
    }

    @Test
    void bitsCacheLabel() {
        this.memoryConfig.setBitsCacheSets(2);
        this.memoryConfig.setBitsBlocks(3);
        assertEquals(this.memoryConfig.getBitsCacheLabel(), 1);
        this.memoryConfig.setBitsCacheSets(4);
        this.memoryConfig.setBitsBlocks(8);
        assertEquals(this.memoryConfig.getBitsCacheLabel(), 4);
    }

    @Test
    void bitsCellValue() {
        this.memoryConfig.setBitsCellValue(3);
        assertEquals(this.memoryConfig.getBitsCellValue(), 3);
    }

    @Test
    void bitsCells() {
        this.memoryConfig.setBitsCells(3);
        assertEquals(this.memoryConfig.getBitsCells(), 3);
    }

    @Test
    void bitsBlocks() {
        this.memoryConfig.setBitsBlocks(3);
        assertEquals(this.memoryConfig.getBitsBlocks(), 3);
    }

    @Test
    void bitsCacheRow() {
        this.memoryConfig.setBitsCacheRow(5);
        assertEquals(this.memoryConfig.getBitsCacheRow(), 5);
    }

    @Test
    void bitsCacheSets() {
        this.memoryConfig.setBitsCacheSets(2);
        assertEquals(this.memoryConfig.getBitsCacheSets(), 2);
    }
}