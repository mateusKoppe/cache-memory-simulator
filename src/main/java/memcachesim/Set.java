package memcachesim;

import java.security.InvalidParameterException;

public class Set {
    private Row[] rows;

    Set(int rows) {
        if (rows <= 0) {
            throw new InvalidParameterException("cells cannot be less than 1");
        }
        this.generateRows(rows);
    }

    private void generateRows (int rows) {
        this.rows = new Row[rows];
        for (int i = 0; i < rows; i++) {
            this.rows[i] = new Row(4);
        }
    }
}
