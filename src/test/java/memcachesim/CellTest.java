package memcachesim;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    private Cell defaultCell;
    private MemoryConfig memoryConfig;

    @BeforeEach
    void setUp() {
        this.memoryConfig = new MemoryConfig();
        this.defaultCell = new Cell(this.memoryConfig);
    }

    @Test
    void defaultValues() {
        assertEquals(this.defaultCell.getValue(), 0);
    }

    @Test
    void throwsOutRangeValue () {
        assertDoesNotThrow(() -> {
            this.defaultCell.setValue(0b00000000);
            this.defaultCell.setValue(0b01010101);
            this.defaultCell.setValue(0b11111111);
        });
        assertThrows(InvalidParameterException.class, () -> {
            this.defaultCell.setValue(0b100000000);
        });
        assertThrows(InvalidParameterException.class, () -> {
            MemoryConfig memoryConfig = new MemoryConfig();
            memoryConfig.setBitsCellValue(3);
            new Cell(memoryConfig).setValue(0b1010);
        });
    }
}