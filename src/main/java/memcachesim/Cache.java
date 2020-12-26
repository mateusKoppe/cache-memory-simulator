package memcachesim;

public class Cache {
    private final MemoryConfig memoryConfig;
    private Set[] sets;

    Cache (MemoryConfig memoryConfig) {
        this.memoryConfig = memoryConfig;
        this.generateSets(memoryConfig);
    }

    public Row cacheBlock(int address, Block block) {
        int set = this.getSetAddress(address);
        int label = this.getLabelAddress(address);
        return this.sets[set].cacheBlock(label, block);
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

    private int getLabelAddress(int address) {
        return address >> this.memoryConfig.getBitsCacheSets();
    }

    private int getSetAddress(int address) {
        int block = address >> this.memoryConfig.getBitsCells();
        int labelSize = (int) Math.pow(2, this.memoryConfig.getBitsCacheLabel());
        return block & labelSize - 1;
    }

    private void generateSets(MemoryConfig memoryConfig) {
        this.sets = new Set[memoryConfig.getCacheSetsAmount()];
        for (int i = 0; i < memoryConfig.getCacheSetsAmount(); i++) {
            this.sets[i] = new Set(memoryConfig);
        }
    }
}
