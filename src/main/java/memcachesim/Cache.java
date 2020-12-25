package memcachesim;

import java.security.InvalidParameterException;

public class Cache {
    private final MemoryConfig memoryConfig;
    private Set[] sets;

    Cache (MemoryConfig memoryConfig) {
        this.memoryConfig = memoryConfig;
        this.generateSets(memoryConfig);
    }

    public void writeInAddress(int address, int value) {
        int set = address >> this.memoryConfig.getBitsCells();
    }

    private void generateSets(MemoryConfig memoryConfig) {
        this.sets = new Set[memoryConfig.getCacheSetsAmount()];
        for (int i = 0; i < memoryConfig.getCacheSetsAmount(); i++) {
            this.sets[i] = new Set(memoryConfig);
        }
    }
}
