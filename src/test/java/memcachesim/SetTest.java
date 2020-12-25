package memcachesim;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class SetTest {
    private Set set;
    private MemoryConfig memoryConfig;

    @BeforeEach
    void setUp() {
        this.memoryConfig = new MemoryConfig();
        this.memoryConfig.setBitsCacheRow(4);
        this.memoryConfig.setBitsCacheSets(2);
        this.set = new Set(this.memoryConfig);
    }

    @Test
    void blocksAmount() {
        assertEquals(this.set.getRows().length, 4);
    }
}