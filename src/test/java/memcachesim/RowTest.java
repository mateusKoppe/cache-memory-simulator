package memcachesim;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RowTest {
    private MemoryConfig memoryConfig;
    private Row rowDefault;

    @BeforeEach
    void setUp() {
        this.memoryConfig = new MemoryConfig();
        this.memoryConfig.setBitsBlocks(5);
        this.memoryConfig.setBitsCells(2);
        this.memoryConfig.setBitsCacheSets(2);
        this.rowDefault = new Row(this.memoryConfig);
        this.rowDefault.incrementScore();
    }

    @Test
    void getBlock() {
        assertEquals(this.rowDefault.getBlock().getClass(), Block.class);
    }

    @Test
    void readInAddress() {
        int testValue = 0b11110000;
        this.rowDefault.getBlock().getCells()[0b01].setValue(testValue);
        int value = this.rowDefault.readInAddress(0b1110101);
        assertEquals(value, testValue);
    }

    @Test
    void writeValue() {
        this.rowDefault.writeInAddress(0b1110101, 0b11110000);
        int value = this.rowDefault.getBlock().getCells()[0b01].getValue();
        assertEquals(value, 0b11110000);
    }

    @Test
    void hasAddress() {
        this.rowDefault.writeInAddress(0b1110101, 0b11110000);
        assertFalse(this.rowDefault.hasAddress(0b0100101));
        assertTrue(this.rowDefault.hasAddress(0b1110101));
    }

    @Test
    void label() {
        this.rowDefault.writeInAddress(0b1110101, 0b11110000);
        assertEquals(this.rowDefault.getLabel(), 0b111);
        this.rowDefault.writeInAddress(0b1000101, 0b11110000);
        assertEquals(this.rowDefault.getLabel(), 0b100);
    }

    @Test
    void scoreOnRead() {
        assertEquals(this.rowDefault.getScore(), 1);
        this.rowDefault.readInAddress(0b1110101);
        assertEquals(this.rowDefault.getScore(), 0);
    }

    @Test
    void scoreOnWrite() {
        assertEquals(this.rowDefault.getScore(), 1);
        this.rowDefault.writeInAddress(0b1110101, 0b11110000);
        assertEquals(this.rowDefault.getScore(), 0);
    }

    @Test
    void changed() {
        assertEquals(this.rowDefault.getChanged(), false);
        this.rowDefault.writeInAddress(0b1110101, 0b11110000);
        assertEquals(this.rowDefault.getChanged(), true);
    }

    @Test
    void incrementScore() {
        assertEquals(this.rowDefault.getScore(), 1);
        this.rowDefault.incrementScore();
        assertEquals(this.rowDefault.getScore(), 2);
    }
}