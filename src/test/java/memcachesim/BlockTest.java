package memcachesim;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class BlockTest {
    Block block;
    private MemoryConfig memoryConfig;

    @BeforeEach
    void setUp() {
        this.memoryConfig = new MemoryConfig();
        this.memoryConfig.setBitsCells(2);
        this.block = new Block(this.memoryConfig);
    }

    @Test
    void cellsAmount() {
        assertEquals(this.block.getCells().length, 4);
    }
}