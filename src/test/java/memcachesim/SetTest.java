package memcachesim;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SetTest {
    private Set set;
    private MemoryConfig memoryConfig;
    private Set smallSet;

    @BeforeEach
    void setUp() {
        this.memoryConfig = new MemoryConfig();
        this.memoryConfig.setBitsCacheRow(4);
        this.memoryConfig.setBitsCacheSets(2);
        this.set = new Set(this.memoryConfig);

        MemoryConfig smallConfig = new MemoryConfig();
        smallConfig.setBitsCacheRow(2);
        smallConfig.setBitsCacheSets(1);
        this.smallSet = new Set(smallConfig);
    }

    @Test
    void blocksAmount() {
        assertEquals(this.set.getRows().length, 4);
    }

    @Test
    void cacheBlockFillAll() {
        this.smallSet.getRows()[0].writeInAddress(0b0110101, 0b11110000);
        this.smallSet.getRows()[1].writeInAddress(0b1110101, 0b00001111);
        this.smallSet.readInAddress(0b0110101);
        this.smallSet.cacheBlock(0b10, new Block(this.memoryConfig));
        assertFalse(this.smallSet.readInAddress(0b1110101).isHit());
        assertTrue(this.smallSet.readInAddress(0b0110101).isHit());
        this.smallSet.getRows()[1].writeInAddress(0b1010101, 0b00001111);
        assertTrue(this.smallSet.readInAddress(0b1010101).isHit());
    }

    @Test
    void readInAddress() {
        assertFalse(this.set.readInAddress(0b0110101).isHit());
        this.set.getRows()[3].writeInAddress(0b0110101, 0b11110000);
        assertTrue(this.set.readInAddress(0b0110101).isHit());
        assertEquals(this.set.readInAddress(0b0110101).getValue(), 0b11110000);
    }

    @Test
    void writeInAddress() {
        assertFalse(this.set.writeInAddress(0b0110101, 0b11110000).isHit());
        this.set.getRows()[3].writeInAddress(0b0110101, 0b11110000);
        assertTrue(this.set.writeInAddress(0b0110101, 0b11110000).isHit());
    }

    @Test
    void incrementScoresOnRead() {
        for (Row row: this.set.getRows()) {
            assertEquals(row.getScore(), 0);
        }
        this.set.getRows()[3].writeInAddress(0b0110101, 0b11110000);
        this.set.readInAddress(0b0110101);
        assertEquals(this.set.getRows()[0].getScore(), 1);
        assertEquals(this.set.getRows()[1].getScore(), 1);
        assertEquals(this.set.getRows()[2].getScore(), 1);
        assertEquals(this.set.getRows()[3].getScore(), 0);
    }

    @Test
    void getRowByLabel() {
        assertEquals(this.set.getRowByLabel(0b11), Optional.empty());
        this.set.cacheBlock(0b11, new Block(this.memoryConfig));
        assertNotEquals(this.set.getRowByLabel(0b11), Optional.empty());
    }
}