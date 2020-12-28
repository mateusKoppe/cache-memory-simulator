package ui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.table.Table;
import memcachesim.Block;
import memcachesim.Cell;
import memcachesim.Memory;

public class MemoryTable {
    private final Memory memory;
    private final WindowBasedTextGUI gui;

    public MemoryTable(Memory memory, WindowBasedTextGUI gui) {
        this.memory = memory;
        this.gui = gui;
    }

    public Table render () {
        Table<String> memoryTable = new Table<String>("Memory Address", "Value");

        int rows = gui.getScreen().getTerminalSize().getRows() - 7;

        memoryTable.setPreferredSize(new TerminalSize(40, rows));
        for (Block block: this.memory.getBlocks()) {
            for (Cell cell: block.getCells()) {
                memoryTable.getTableModel().addRow(
                    Utils.formatBinary(cell.getAddress(), this.memory.getConfig().getBitsAddress()),
                    Utils.formatBinary(cell.getValue())
                );
            }
        }
        return memoryTable;
    }
}
