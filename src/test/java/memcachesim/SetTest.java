package memcachesim;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SetTest {
    private Set set;

    @BeforeEach
    void setUp() {
        this.set = new Set(4);
    }

    @Test
    void validateArgs () {
        assertDoesNotThrow(() -> {
            new Set(4);
        });
        assertThrows(InvalidParameterException.class, () -> {
            new Set(-1);
        });
    }
}