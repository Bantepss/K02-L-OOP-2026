package nangorblast.common;

/**
 * Kelas dasar untuk semua objek yang punya posisi di map
 * (Player, Enemy, Bomb, Power-Up, dan seterusnya).
 * SEMENTARA: kelas bersama, nama dan isinya perlu disepakati dengan Farhan.
 */
public abstract class Entity {
    protected Position position;

    protected Entity(Position position) {
        this.position = position;
    }

    public synchronized Position getPosition() {
        return position;
    }

    public synchronized void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Dipanggil game loop setiap frame.
     * @param deltaTime waktu sejak frame sebelumnya, dalam detik
     */
    public abstract void update(double deltaTime);
}
