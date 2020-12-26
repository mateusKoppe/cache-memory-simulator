package memcachesim;

public class CacheResponse {
    private boolean hit;
    private int value;

    public CacheResponse(boolean hit) {
        this.hit = hit;
    }

    public CacheResponse(boolean hit, int value) {
        this.hit = hit;
        this.value = value;
    }

    public boolean isHit() {
        return hit;
    }

    public int getValue() {
        return value;
    }
}
