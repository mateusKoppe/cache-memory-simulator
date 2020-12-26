package memcachesim;

public class Cache {
    private final MemoryConfig memoryConfig;
    private Set[] sets;

    Cache (MemoryConfig memoryConfig) {
        this.memoryConfig = memoryConfig;
        this.generateSets(memoryConfig);
    }

    public Row cacheBlock(Block block) {
        int address = block.getAddress() << this.memoryConfig.getBitsCells();
        int set = this.getSetAddress(address);
        return this.sets[set].cacheBlock(block);
    }

    public CacheResponse writeInAddress(int address, int value) {
        int set = this.getSetAddress(address);
        return this.sets[set].writeInAddress(address, value);
    }

    public CacheResponse readInAddress(int address) {
        int set = this.getSetAddress(address);
        return this.sets[set].readInAddress(address);
    }

    public Set[] getSets() {
        return sets;
    }


    private int getSetAddress(int address) {
        int block = address >> this.memoryConfig.getBitsCells();
        int labelSize = (int) Math.pow(2, this.memoryConfig.getBitsCacheSets());
        return block & labelSize - 1;
    }

    private void generateSets(MemoryConfig memoryConfig) {
        this.sets = new Set[memoryConfig.getCacheSetsAmount()];
        for (int i = 0; i < memoryConfig.getCacheSetsAmount(); i++) {
            this.sets[i] = new Set(memoryConfig);
        }
    }
}
