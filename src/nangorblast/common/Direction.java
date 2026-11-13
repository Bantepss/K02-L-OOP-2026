package nangorblast.common;

/**
 * Empat arah gerak pada grid. dx dan dy adalah perubahan kolom dan baris.
 * SEMENTARA: kelas bersama, nama dan isinya perlu disepakati dengan Farhan.
 */
public enum Direction {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
}
