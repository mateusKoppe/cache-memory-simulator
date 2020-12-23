package memcachesim;

import java.security.InvalidParameterException;

public class Block {
    private Cell[] cells;

    public Block(int cells) {
        if (cells <= 0) {
            throw new InvalidParameterException("cells cannot be less than 1");
        }
        this.generateCells(cells);
    }

    private void generateCells (int cells) {
        this.cells = new Cell[cells];
        for (int i = 0; i < cells; i++) {
            this.cells[i] = new Cell(8);
        }
    }

    public Cell[] getCells() {
        return this.cells;
    }
}
