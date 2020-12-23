package memcachesim;

import memcachesim.exception.CellOutRangeValueExeption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    private Cell defaultCell;

    @BeforeEach
    void setUp() {
        this.defaultCell = new Cell();
    }

    @Test
    void defaultValues() {
        assertEquals(this.defaultCell.getValue(), 0);
        assertEquals(this.defaultCell.getBits(), 8);
    }

    @Test
    void validateArgs () {
        assertThrows(InvalidParameterException.class, () -> {
            new Cell(-1);
        });
    }

    @Test
    void throwsOutRangeValue () {
        assertDoesNotThrow(() -> {
            this.defaultCell.setValue(100);
        });
        assertThrows(CellOutRangeValueExeption.class, () -> {
            this.defaultCell.setValue(256);
        });
        assertThrows(CellOutRangeValueExeption.class, () -> {
            new Cell(4).setValue(20);
        });
    }

    @Test
    void valueLimit () {
        assertEquals(new Cell(8).getValueLimit(), 255);
        assertEquals(new Cell(4).getValueLimit(), 15);
        assertEquals(new Cell(2).getValueLimit(), 3);
    }
}