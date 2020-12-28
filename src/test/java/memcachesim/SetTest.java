package memcachesim;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SetTest {
    private Set set;
    private MemoryConfig memoryConfig;
    private Set smallSet;

    @BeforeEach
    void setUp() {
        this.memoryConfig = new MemoryConfig();
        this.memoryConfig.setBitsBlocks(4);
        this.memoryConfig.setBitsCacheRow(4);
        this.memoryConfig.setBitsCacheSets(2);
        this.memoryConfig.setBitsCells(2);
        Block block = new Block(this.memoryConfig, 0b1101);
        this.set = new Set(this.memoryConfig);
        this.set.cacheBlock(block);

        MemoryConfig smallConfig = new MemoryConfig();
        smallConfig.setBitsCacheRow(2);
        smallConfig.setBitsCacheSets(1);
        smallConfig.setBitsCells(2);
        smallConfig.setBitsBlocks(3);
        Block smallBlock = new Block(this.memoryConfig, 0b110);
        this.smallSet = new Set(smallConfig);
        this.smallSet.cacheBlock(smallBlock);
    }

    @Test
    void blocksAmount() {
        assertEquals(this.set.getRows().length, 4);
    }

    @Test
    void cacheBlockFillAll() {
        this.smallSet.cacheBlock(new Block(this.memoryConfig, 0b100));
        assertFalse(this.smallSet.readInAddress(0b01001).isHit());
        assertTrue(this.smallSet.readInAddress(0b10001).isHit());
        this.smallSet.cacheBlock(new Block(this.memoryConfig, 0b110));
        assertTrue(this.smallSet.readInAddress(0b11001).isHit());
        this.smallSet.cacheBlock(new Block(this.memoryConfig, 0b110));
        assertFalse(this.smallSet.readInAddress(0b10001).isHit());
    }

    @Test
    void readInAddress() {
        assertFalse(this.set.readInAddress(0b011001).isHit());
        Block newBlock = new Block(this.memoryConfig, 0b0110);
        newBlock.getCells()[0b01].setValue(0b11110000);
        this.set.cacheBlock(newBlock);
        assertTrue(this.set.readInAddress(0b011001).isHit());
        assertEquals(this.set.readInAddress(0b011001).getValue(), 0b11110000);
    }

    @Test
    void writeInAddress() {
        assertFalse(this.set.writeInAddress(0b100101, 0b11110000).isHit());
        Block newBlock = new Block(this.memoryConfig, 0b1001);
        this.set.cacheBlock(newBlock);
        assertTrue(this.set.writeInAddress(0b100101, 0b11110000).isHit());
    }

    @Test
    void incrementScoresOnRead() {
        this.set.readInAddress(0b110101);
        Optional<Row> readRow = this.set.getRowByLabel(0b11);

        for (Row row : this.set.getRows()) {
            if (row == readRow.get()) {
                assertEquals(row.getScore(), 0);
            } else {
                assertEquals(row.getScore(), 2);
            }
        }
    }

    @Test
    void getRowByLabel() {
        assertNotEquals(this.set.getRowByLabel(0b11), Optional.empty());
        assertEquals(this.set.getRowByLabel(0b10), Optional.empty());
        this.set.cacheBlock(new Block(this.memoryConfig, 0b1001));
        assertNotEquals(this.set.getRowByLabel(0b10), Optional.empty());
    }
}