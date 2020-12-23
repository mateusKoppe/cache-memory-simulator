package memcachesim;

import memcachesim.exception.CellOutRangeValueExeption;

import java.security.InvalidParameterException;

public class Cell {
    private int value;
    private int bits;

    public Cell() {
        this(8);
    }

    public Cell(int bits) {
        if (bits <= 0) {
            throw new InvalidParameterException("Bits cannot be less than 1");
        }
        this.bits = bits;
    }

    public void setValue(int value) throws CellOutRangeValueExeption {
        if (value < 0 || value > this.getValueLimit()) {
            throw new CellOutRangeValueExeption("value " + value + "is out of range of " + this.bits + " bits");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int getBits() {
        return bits;
    }

    public int getValueLimit() {
        return (int) Math.pow(2, this.bits) - 1;
    }
}
