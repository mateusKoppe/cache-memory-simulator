import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.gui2.dialogs.TextInputDialog;
import com.googlecode.lanterna.gui2.table.Table;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import memcachesim.*;
import ui.CacheTable;
import ui.MemoryTable;
import ui.Ui;

import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();

        MemoryConfig memoryConfig = new MemoryConfig();
        Memory memorySimulator = new Memory(memoryConfig);

        try {
            final Screen screen = terminalFactory.createScreen();
            screen.startScreen();

            final WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);

            final Window window = new BasicWindow("Memory");

            Ui ui = new Ui(memorySimulator, textGUI);
            ui.onRender(new Runnable() {
                @Override
                public void run() {
                    window.setComponent(ui.render());
                }
            });

            window.setComponent(ui.render());
            textGUI.addWindowAndWait(window);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
