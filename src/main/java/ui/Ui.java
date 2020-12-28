package ui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.TextInputDialog;
import memcachesim.Memory;

public class Ui {
    private final Memory memory;
    private final WindowBasedTextGUI gui;
    private Runnable onRender;

    public Ui(Memory memory, WindowBasedTextGUI gui) {
        this.memory = memory;
        this.gui = gui;
    }

    public void onRender(Runnable onRender) {
        this.onRender = onRender;
    }

    public Panel render () {
        Panel contentPanel = new Panel(new GridLayout(2));
        contentPanel.setSize(new TerminalSize(80, 40));

        GridLayout gridLayout = (GridLayout) contentPanel.getLayoutManager();
        gridLayout.setHorizontalSpacing(3);

        contentPanel.addComponent(new Button("Write", new Runnable() {
            @Override
            public void run() {
                String address = TextInputDialog.showDialog(gui, "Address", "The cell address", "000000");
                String value = TextInputDialog.showDialog(gui, "Value", "The value", "00000000");
                memory.writeInAddress(
                        Integer.parseInt(address, 2),
                        Integer.parseInt(value, 2)
                );
                Panel contentPanel = new Panel(new GridLayout(2));
                contentPanel.setSize(new TerminalSize(80, 40));

                GridLayout gridLayout = (GridLayout) contentPanel.getLayoutManager();
                gridLayout.setHorizontalSpacing(3);

                contentPanel.addComponent(new MemoryTable(memory).render());
                contentPanel.addComponent(new CacheTable(memory.getCache()).render());
                onRender.run();
            }
        }));

        contentPanel.addComponent(new Label("Hits: " + this.memory.getHits()));

        contentPanel.addComponent(new Button("Read", new Runnable() {
            @Override
            public void run() {
                String address = TextInputDialog.showDialog(gui, "Address", "The cell address", "000000");
                memory.readInAddress(
                        Integer.parseInt(address, 2)
                );
                Panel contentPanel = new Panel(new GridLayout(2));
                contentPanel.setSize(new TerminalSize(80, 40));

                GridLayout gridLayout = (GridLayout) contentPanel.getLayoutManager();
                gridLayout.setHorizontalSpacing(3);

                contentPanel.addComponent(new MemoryTable(memory).render());
                contentPanel.addComponent(new CacheTable(memory.getCache()).render());
                onRender.run();
            }
        }));

        contentPanel.addComponent(new Label("Miss: " + this.memory.getMiss()));

        contentPanel.addComponent(new MemoryTable(this.memory).render());
        contentPanel.addComponent(new CacheTable(this.memory.getCache()).render());
        return contentPanel;
    }
}
