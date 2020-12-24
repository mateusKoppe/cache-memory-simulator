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
}