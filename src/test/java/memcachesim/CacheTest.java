package memcachesim;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class CacheTest {
    private Cache cache;

    @BeforeEach
    void setUp() {
        Memory memory = new Memory(16);
        this.cache = new Cache(memory, 4);
    }

    @Test
    void validateArgs () {
        assertDoesNotThrow(() -> {
            new Cache(new Memory(16),4);
        });
        assertThrows(InvalidParameterException.class, () -> {
            new Cache(new Memory(16),-1);
        });
    }
}