package memcachesim;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemoryTest {
    Memory memory;
    private MemoryConfig memoryConfig;

    @BeforeEach
    void setUp() {
        this.memoryConfig = new MemoryConfig();
        this.memoryConfig.setBitsBlocks(4);
        this.memoryConfig.setBitsCells(2);
        this.memoryConfig.setBitsCacheRow(4);
        this.memoryConfig.setBitsCacheSets(2);
        this.memory = new Memory(this.memoryConfig);
        Block block = new Block(this.memoryConfig, 0b1101);
        block.writeInCell(0b10, 0b11110101);
        this.memory.getCache().cacheBlock(block);
    }

    @Test
    void amountOfBlocks() {
        assertEquals(this.memory.getBlocks().length, 16);
    }

    @Test
    void blocksAddress() {
        for (int i = 0; i < 16; i++) {
            assertEquals(this.memory.getBlocks()[i].getAddress(), i);
        }
    }

    @Test
    void writeInAddressInAddress() {
        this.memory.writeInAddress(0b101010, 0b11110000);
        assertFalse(this.memory.getCache().readInAddress(0b101010).isHit());
        assertEquals(
            this.memory.getBlocks()[0b1010].getCells()[0b10].getValue(),
            0b11110000
        );
    }

    @Test
    void writeInAddressInCache() {
        this.memory.writeInAddress(0b110110, 0b11110000);
        assertTrue(this.memory.getCache().readInAddress(0b110110).isHit());
        assertEquals(
                this.memory.getCache().readInAddress(0b110110).getValue(),
                0b11110000
        );
    }

    @Test
    void readInAddressInCache() {
        assertTrue(this.memory.getCache().readInAddress(0b110110).isHit());
        assertEquals(this.memory.getCache().readInAddress(0b110110).getValue(), 0b11110101);
        assertFalse(this.memory.getCache().readInAddress(0b111110).isHit());
    }

    @Test
    void readInAddressAndCache() {
        assertFalse(this.memory.getCache().readInAddress(0b111110).isHit());
        this.memory.readInAddress(0b111110);
        assertTrue(this.memory.getCache().readInAddress(0b111110).isHit());
    }

    @Test
    void readInAddressSaveChanges() {
        this.memoryConfig.setBitsCacheRow(1);
        this.memoryConfig.setBitsCacheSets(1);
        this.memory = new Memory(this.memoryConfig);

        assertFalse(this.memory.getCache().readInAddress(0b111110).isHit());
        this.memory.readInAddress(0b111110);
        this.memory.writeInAddress(0b111110, 0b01010101);
        assertNotEquals(
            this.memory.getBlockByAddress(0b111110).getCells()[0b10].getValue(),
            0b01010101
        );
        assertTrue(this.memory.getCache().readInAddress(0b111110).isHit());
        assertEquals(
            this.memory.getCache().readInAddress(0b111110).getValue(),
            0b01010101
        );

        this.memory.readInAddress(0b101110);
        assertEquals(
                this.memory.getBlockByAddress(0b111110).getCells()[0b10].getValue(),
                0b01010101
        );
        assertFalse(this.memory.getCache().readInAddress(0b111110).isHit());
    }

    @Test
    void writeInMemoryAddress() {
        this.memory.writeInMemoryAddress(0b1101, 0b00100000);
        int valueInMemory = this.memory.getBlocks()[0b11].getCells()[0b01].getValue();
        assertEquals(valueInMemory, 0b00100000);
    }

    @Test
    void readInMemoryAddress() {
        this.memory.getBlocks()[0b11].getCells()[0b01].setValue(0b00100000);
        assertEquals(this.memory.readInMemoryAddress(0b1101), 0b00100000);
    }

    @Test
    void writeBlock() {
        Block copyBlock = new Block(this.memoryConfig, 0b0101);
        copyBlock.writeInCell(0b01, 0b01010101);
        copyBlock.writeInCell(0b10, 0b10101010);
        this.memory.writeBlock(copyBlock);

        Block targetBlock = this.memory.getBlocks()[copyBlock.getAddress()];
        assertEquals(targetBlock.readInCell(0b01), 0b01010101);
        assertEquals(targetBlock.readInCell(0b10), 0b10101010);
    }

    @Test
    void blockByAddress() {
        assertEquals(this.memory.getBlockByAddress(0b101100), this.memory.getBlocks()[0b1011]);
        assertEquals(this.memory.getBlockByAddress(0b101100).getAddress(), 0b1011);
    }

    @Test
    void accessCache() {
        assertEquals(this.memory.getCache().getClass(), Cache.class);
    }

    @Test
    void countMissAndHits() {
        this.memory.readInAddress(0b110100); // H
        this.memory.readInAddress(0b111110); // M
        this.memory.readInAddress(0b111110); // H
        this.memory.writeInAddress(0b111111, 0b11100011); // H
        this.memory.readInAddress(0b100110); // M
        this.memory.writeInAddress(0b101110, 0b11100011); // H
        this.memory.readInAddress(0b001110); // M
        assertEquals(this.memory.getHits(), 4);
        assertEquals(this.memory.getMiss(), 3);
    }

    @Test
    void memoryConfig() {
        assertEquals(this.memory.getConfig().getClass(), MemoryConfig.class);
    }
}