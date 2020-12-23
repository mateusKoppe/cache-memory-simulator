package memcachesim;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class BlockTest {
    Block block;

    @BeforeEach
    void setUp() {
        this.block = new Block(4);
    }

    @Test
    void validateArgs () {
        assertDoesNotThrow(() -> {
            new Block(4);
        });
        assertThrows(InvalidParameterException.class, () -> {
            new Block(-1);
        });
    }

    @Test
    void cellsAmount() {
        assertEquals(this.block.getCells().length, 4);
    }
}