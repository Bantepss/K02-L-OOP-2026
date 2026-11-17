package nangorblast.common;

/**
 * Posisi tile pada grid map (x = kolom, y = baris, dimulai dari 0).
 * Bersifat immutable sehingga aman dipakai bersama oleh beberapa thread.
 * SEMENTARA: kelas bersama, nama dan isinya perlu disepakati dengan Farhan.
 */
public record Position(int x, int y) {

    /** Posisi satu tile ke arah dir dari posisi ini. */
    public Position move(Direction dir) {
        return new Position(x + dir.getDx(), y + dir.getDy());
    }
}
