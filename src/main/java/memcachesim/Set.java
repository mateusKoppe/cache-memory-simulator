package memcachesim;

import java.util.Arrays;
import java.util.Optional;

public class Set {
    private final MemoryConfig memoryConfig;
    private Row[] rows;

    Set(MemoryConfig memoryConfig) {
        this.memoryConfig = memoryConfig;
        this.generateRows(memoryConfig);
    }

    public Row cacheBlock(int label, Block block) {
        int worstI = 0;
        for (int i = 0; i < this.rows.length; i++) {
            if (this.rows[worstI].getScore() < this.rows[i].getScore()) {
                worstI = i;
            }
        }
        Row worstRow = this.rows[worstI];
        Row newRow = new Row(this.memoryConfig);
        newRow.writeBlock(label, block);
        this.rows[worstI] = newRow;
        return worstRow;
    }

    public CacheResponse readInAddress(int address) {
        for (Row row: this.rows) {
            if (!row.hasAddress(address)) continue;
            this.incrementScore();
            return new CacheResponse(
              true,
              row.readInAddress(address)
            );
        }
        return new CacheResponse(false);
    }

    public CacheResponse writeInAddress(int address, int value) {
        for (Row row: this.rows) {
            if (!row.hasAddress(address)) continue;
            this.incrementScore();
            row.writeInAddress(address, value);
            return new CacheResponse(true);
        }
        return new CacheResponse(false);
    }

    public Optional<Row> getRowByLabel(int label) {
        Optional<Row> selectedRow = Optional.empty();
        for (Row row: this.rows) {
            if (row.getLabel() != label) continue;
            selectedRow = Optional.of(row);
            break;
        }

        return selectedRow;
    }

    public Row[] getRows() {
        return rows;
    }

    private void generateRows (MemoryConfig memoryConfig) {
        this.rows = new Row[memoryConfig.getRowsInSet()];
        for (int i = 0; i < memoryConfig.getRowsInSet(); i++) {
            this.rows[i] = new Row(memoryConfig);
        }
    }

    private void incrementScore() {
        for (Row row: this.rows) {
            row.incrementScore();
        }
    }
}
