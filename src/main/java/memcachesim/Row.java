package memcachesim;

public class Row {
    Block block;
    Boolean isWritten = false;

    Row (int cells) {
        this.block = new Block(new MemoryConfig());
    }
}
