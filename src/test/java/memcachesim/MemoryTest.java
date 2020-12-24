package memcachesim;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class MemoryTest {
    Memory memory;
    private MemoryConfig memoryConfig;

    @BeforeEach
    void setUp() {
        this.memoryConfig = new MemoryConfig();
        this.memoryConfig.setBitsBlocks(5);
        this.memory = new Memory(this.memoryConfig);
    }

    @Test
    void amountOfBlocks() {
        assertEquals(this.memory.getBlocks().length, 32);
    }

    @Test
    void accessCache() {
        assertEquals(this.memory.getCache().getClass(), Cache.class);
    }

    @Test
    void writeInAddress() {
        this.memory.writeInAddress(0b1101, 0b00100000);
        int valueInMemory = this.memory.getBlocks()[0b11].getCells()[0b01].getValue();
        assertEquals(valueInMemory, 0b00100000);
    }

    @Test
    void readInAddress() {
        this.memory.getBlocks()[0b11].getCells()[0b01].setValue(0b00100000);
        assertEquals(this.memory.readInAddress(0b1101), 0b00100000);
    }
}