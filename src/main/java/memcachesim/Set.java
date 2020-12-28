package memcachesim;

import java.util.Optional;

public class Set {
    private final MemoryConfig memoryConfig;
    private Row[] rows;

    Set(MemoryConfig memoryConfig) {
        this.memoryConfig = memoryConfig;
        this.generateRows(memoryConfig);
    }

    public Row cacheBlock(Block block) {
        int worstI = 0;
        for (int i = 0; i < this.rows.length; i++) {
            if (this.rows[worstI].getScore() < this.rows[i].getScore()) {
                worstI = i;
            }
        }
        Row worstRow = this.rows[worstI];
        Row newRow = new Row(this.memoryConfig);
        this.rows[worstI] = newRow;
        this.incrementScoreRows();
        newRow.writeBlock(block);
        return worstRow;
    }

    public CacheResponse readInAddress(int address) {
        MemoryConfig config = this.memoryConfig;
        int shiftSize = config.getBitsCells() + config.getBitsCacheSets();
        int label = address >> shiftSize;
        for (Row row: this.rows) {
            if (row.getLabel() != label) continue;
            this.incrementScoreRows();
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
            this.incrementScoreRows();
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

    private void incrementScoreRows() {
        for (Row row: this.rows) {
            row.incrementScore();
        }
    }
}
