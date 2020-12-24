package memcachesim;

import java.security.InvalidParameterException;

public class Set {
    private final MemoryConfig memoryConfig;
    private Row[] rows;

    Set(MemoryConfig memoryConfig) {
        this.memoryConfig = memoryConfig;
        this.generateRows(memoryConfig);
    }

    private void generateRows (MemoryConfig memoryConfig) {
        this.rows = new Row[memoryConfig.getRowsInSet()];
        for (int i = 0; i < memoryConfig.getRowsInSet(); i++) {
            this.rows[i] = new Row(4);
        }
    }
}
