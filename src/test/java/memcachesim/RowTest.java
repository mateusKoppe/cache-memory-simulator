package memcachesim;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RowTest {
    private MemoryConfig memoryConfig;
    private Row row;

    @BeforeEach
    void setUp() {
        this.memoryConfig = new MemoryConfig();
        this.memoryConfig.setBitsBlocks(5);
        this.memoryConfig.setBitsCells(2);
        this.memoryConfig.setBitsCacheSets(2);
        this.row = new Row(this.memoryConfig);
        this.row.incrementScore();
    }

    @Test
    void getBlock() {
        assertEquals(this.row.getBlock().getClass(), Block.class);
    }

    @Test
    void writeBlock() {
        Block block = new Block(this.memoryConfig);
        this.row.writeBlock(0b11, block);
        assertEquals(this.row.getLabel(), 0b11);
        assertNotEquals(this.row.getBlock(), block);
        for (int i = 0; i < block.getCells().length; i++) {
            assertEquals(
                block.getCells()[i].getValue(),
                this.row.getBlock().getCells()[i].getValue()
            );
        }
    }

    @Test
    void readInAddress() {
        int testValue = 0b11110000;
        this.row.getBlock().getCells()[0b01].setValue(testValue);
        int value = this.row.readInAddress(0b1110101);
        assertEquals(value, testValue);
    }

    @Test
    void writeValue() {
        this.row.writeInAddress(0b1110101, 0b11110000);
        int value = this.row.getBlock().getCells()[0b01].getValue();
        assertEquals(value, 0b11110000);
    }

    @Test
    void hasAddress() {
        this.row.writeInAddress(0b1110101, 0b11110000);
        assertFalse(this.row.hasAddress(0b0100101));
        assertTrue(this.row.hasAddress(0b1110101));
    }

    @Test
    void scoreOnRead() {
        assertEquals(this.row.getScore(), 1);
        this.row.readInAddress(0b1110101);
        assertEquals(this.row.getScore(), 0);
    }

    @Test
    void scoreOnWrite() {
        assertEquals(this.row.getScore(), 1);
        this.row.writeInAddress(0b1110101, 0b11110000);
        assertEquals(this.row.getScore(), 0);
    }

    @Test
    void changed() {
        assertEquals(this.row.isChanged(), false);
        this.row.writeInAddress(0b1110101, 0b11110000);
        assertEquals(this.row.isChanged(), true);
    }

    @Test
    void incrementScore() {
        assertEquals(this.row.getScore(), 1);
        this.row.incrementScore();
        assertEquals(this.row.getScore(), 2);
    }
}