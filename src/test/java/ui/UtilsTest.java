package ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {
    @Test
    void formatBinary () {
        assertEquals(Utils.formatBinary(0), "00000000");
        assertEquals(Utils.formatBinary(0b110), "00000110");
        assertEquals(Utils.formatBinary(0b110, 5), "00110");
    }
}