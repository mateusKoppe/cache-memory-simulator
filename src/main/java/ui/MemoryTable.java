package ui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.table.Table;
import memcachesim.Block;
import memcachesim.Cell;
import memcachesim.Memory;

public class MemoryTable {
    private final Memory memory;

    public MemoryTable(Memory memory) {
        this.memory = memory;
    }

    public Table render () {
        Table<String> memoryTable = new Table<String>("Memory Address", "Value");
        memoryTable.setPreferredSize(new TerminalSize(40, 20));
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
