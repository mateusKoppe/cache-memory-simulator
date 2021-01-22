package ui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.table.Table;
import memcachesim.*;

public class CacheTable {
    private final Cache cache;
    private final WindowBasedTextGUI gui;

    public CacheTable(Cache cache, WindowBasedTextGUI gui) {
        this.cache = cache;
        this.gui = gui;
    }

    public Table render () {
        Table<String> cacheTable = new Table<String>("Cache Address", "Value", "Score");

        int rows = gui.getScreen().getTerminalSize().getRows() - 7;

        cacheTable.setPreferredSize(new TerminalSize(40, rows));
        for (Set set: this.cache.getSets()) {
            for (Row row: set.getRows()) {
                for (Cell cell: row.getBlock().getCells()) {
                    cacheTable.getTableModel().addRow(
                            Utils.formatBinary(cell.getAddress(), this.cache.getConfig().getBitsAddress()),
                            Utils.formatBinary(cell.getValue()),
                            Utils.formatBinary(row.getScore(), 3)
                    );
                }
            }
        }
        return cacheTable;
    }
}
