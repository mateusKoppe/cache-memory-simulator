package ui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.table.Table;
import memcachesim.Cache;
import memcachesim.Cell;
import memcachesim.Row;
import memcachesim.Set;

public class CacheTable {
    private final Cache cache;

    public CacheTable(Cache cache) {
        this.cache = cache;
    }

    public Table render () {
        Table<String> cacheTable = new Table<String>("Cache Address", "Value", "Score");
        cacheTable.setPreferredSize(new TerminalSize(40, 20));
        for (Set set: this.cache.getSets()) {
            for (Row row: set.getRows()) {
                for (Cell cell: row.getBlock().getCells()) {
                    cacheTable.getTableModel().addRow(
                            Utils.formatBinary(cell.getAddress(), this.cache.getConfig().getBitsAddress()),
                            Utils.formatBinary(cell.getValue()),
                            Integer.toString(row.getScore())
                    );
                }
            }
        }
        return cacheTable;
    }
}
