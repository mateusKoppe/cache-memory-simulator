package memcachesim;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class CacheTest {
    private Cache cache;
    private MemoryConfig memoryConfig;

    @BeforeEach
    void setUp() {
        this.memoryConfig = new MemoryConfig();
        this.memoryConfig.setBitsCacheSets(2);
        this.memoryConfig.setBitsCacheRow(3);
        this.memoryConfig.setBitsBlocks(4);
        this.memoryConfig.setBitsCells(2);
        this.cache = new Cache(this.memoryConfig);
    }

    @Test
    void setsAmount() {
        assertEquals(this.cache.getSets().length, 4);
    }

    @Test
    public void cacheBlock() {
        Block block = new Block(this.memoryConfig);
        block.writeInCell(0b11, 0b11110000);
        assertEquals(this.cache.cacheBlock(1111, block).getClass(), Row.class);
    }

    @Test
    public void writeInAddress() {
        assertFalse(this.cache.writeInAddress(0b011100, 0b11110000).isHit());
        this.cache.getSets()[0b11].getRows()[0].writeInAddress(0b011100, 0b11110000);
        assertTrue(this.cache.writeInAddress(0b011100, 0b00001111).isHit());
    }

    @Test
    public void readInAddress() {
        assertFalse(this.cache.readInAddress(0b011100).isHit());
        this.cache.getSets()[0b11].getRows()[0].writeInAddress(0b011100, 0b11110000);
        assertTrue(this.cache.readInAddress(0b011100).isHit());
    }
}