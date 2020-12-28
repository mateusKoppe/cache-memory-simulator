package memcachesim;

import java.security.InvalidParameterException;

public class Cell {
    private final MemoryConfig memoryConfig;
    private final int address;
    private int value;

    public Cell(MemoryConfig memoryConfig) {
        this(memoryConfig, 0);
    }

    public Cell(MemoryConfig memoryConfig, int address) {
        this.memoryConfig = memoryConfig;
        this.address = address;
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

    public int getAddress() {
        return address;
    }
}
