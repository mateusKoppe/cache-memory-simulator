package memcachesim;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class MemoryTest {
    Memory memory;

    @BeforeEach
    void setUp() {
        this.memory = new Memory(32);
    }

    @Test
    void validateArgs () {
        assertDoesNotThrow(() -> {
            new Memory(4);
        });
        assertThrows(InvalidParameterException.class, () -> {
            new Memory(-1);
        });
    }

    @Test
    void amountOfBlocks() {
        assertEquals(this.memory.getBlocks().length, 32);
    }
}