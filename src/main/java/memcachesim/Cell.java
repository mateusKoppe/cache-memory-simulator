package memcachesim;

import java.security.InvalidParameterException;

public class Cell {
    private final MemoryConfig memoryConfig;
    private int value;

    public Cell(MemoryConfig memoryConfig) {

        this.memoryConfig = memoryConfig;
    }

    public void setValue(int value) {
        int bits = this.memoryConfig.getBitsCellValue();
        if (value < 0 || value > this.memoryConfig.getValueLimit()) {
            throw new InvalidParameterException("value " + value + "is out of range of " + bits + " bits");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
