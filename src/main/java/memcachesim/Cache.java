package memcachesim;

import java.security.InvalidParameterException;

public class Cache {
    private Memory memory;
    private Set[] sets;

    Cache (Memory memory, int sets) {
        this.memory = memory;
        if (sets <= 0) {
            throw new InvalidParameterException("cells cannot be less than 1");
        }
        this.generateSets(sets);
    }

    private void generateSets(int cells) {
        this.sets = new Set[cells];
        for (int i = 0; i < cells; i++) {
            this.sets[i] = new Set(2);
        }
    }
}
