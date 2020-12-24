package memcachesim;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class CacheTest {
    private Cache cache;
    private MemoryConfig memoryConfig;

    @BeforeEach
    void setUp() {
        this.memoryConfig = new MemoryConfig();
        Memory memory = new Memory(this.memoryConfig);
        this.cache = new Cache(this.memoryConfig);
    }
}