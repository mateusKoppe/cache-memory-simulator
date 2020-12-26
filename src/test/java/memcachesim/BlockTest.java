package memcachesim;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlockTest {
    Block block;
    private MemoryConfig memoryConfig;

    @BeforeEach
    void setUp() {
        this.memoryConfig = new MemoryConfig();
        this.memoryConfig.setBitsCells(2);
        this.block = new Block(this.memoryConfig);
    }

    @Test
    void writeInCell() {
        this.block.writeInCell(0b10, 0b01010101);
        assertEquals(this.block.getCells()[0b10].getValue(), 0b01010101);
    }

    @Test
    void constructors() {
        assertEquals(this.block.getAddress(), 0b0);
        Block addressedBlock = new Block(this.memoryConfig, 0b1010);
        assertEquals(addressedBlock.getAddress(), 0b1010);
    }

    @Test
    void readInCell() {
        this.block.getCells()[0b10].setValue(0b01010101);
        assertEquals(this.block.readInCell(0b10), 0b01010101);
    }

    @Test
    void copyBlock() {
        Block copyBlock = new Block(this.memoryConfig, 0b1110);
        assertNotEquals(copyBlock.getAddress(), this.block.getAddress());
        for (int i = 0; i < memoryConfig.getCellsPerBlock(); i++) {
            copyBlock.getCells()[i].setValue(0b11111111);
            this.block.getCells()[i].setValue(0b00000000);
            assertNotEquals(
                this.block.getCells()[i].getValue(),
                copyBlock.getCells()[i].getValue()
            );
        }
        this.block.copyBlock(copyBlock);
        assertEquals(copyBlock.getAddress(), this.block.getAddress());
        for (int i = 0; i < memoryConfig.getCellsPerBlock(); i++) {
            assertEquals(
                this.block.getCells()[i].getValue(),
                copyBlock.getCells()[i].getValue()
            );
        }
    }

    @Test
    void cellsAmount() {
        assertEquals(this.block.getCells().length, 4);
    }
}