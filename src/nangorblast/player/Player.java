package nangorblast.player;

import nangorblast.bomb.BombType;
import nangorblast.common.Direction;
import nangorblast.common.Entity;
import nangorblast.common.Position;

/**
 * Karakter yang dikendalikan pemain (F01).
 *
 * Player tidak memeriksa map sendiri. Engine meminta posisi tujuan lewat
 * getTargetPosition(), mengecek ke map apakah tile itu bisa dilalui,
 * lalu memanggil moveTo(). Dengan begitu Player tidak bergantung pada
 * kelas map milik Nasywa.
 *
 * Method yang mengubah state dibuat synchronized karena Player diakses
 * oleh thread input dan thread game loop secara bersamaan.
 */
public class Player extends Entity {
    public static final int START_LIVES = 3;
    public static final int MAX_LIVES = 5;
    public static final int START_BOMB_CAPACITY = 1;
    public static final int MAX_BOMB_CAPACITY = 3;
    public static final int START_BLAST_RANGE = 1;
    public static final int MAX_BLAST_RANGE = 5;
    /** Kecepatan awal dalam tile per detik. */
    public static final double BASE_SPEED = 4.0;
    /** Speed Up menambah 50% kecepatan awal, maksimum +100% (F06.2). */
    public static final double MAX_SPEED = BASE_SPEED * 2;
    public static final double INVULNERABLE_DURATION = 1.5;

    private Direction direction;
    private int lives;
    private int bombCapacity;
    private int activeBombs;
    private int blastRange;
    private double speed;
    private boolean invulnerable;
    private double invulnerableTimer;
    private boolean remoteUnlocked;
    private BombType selectedBombType;
    /** Sisa waktu sebelum player boleh pindah tile lagi, dalam detik. */
    private double moveCooldown;

    public Player(Position spawnPosition) {
        super(spawnPosition);
        reset(spawnPosition);
    }

    /** Mengembalikan semua atribut ke kondisi awal permainan (dipakai saat restart). */
    public synchronized void reset(Position spawnPosition) {
        this.position = spawnPosition;
        this.direction = Direction.DOWN;
        this.lives = START_LIVES;
        this.bombCapacity = START_BOMB_CAPACITY;
        this.activeBombs = 0;
        this.blastRange = START_BLAST_RANGE;
        this.speed = BASE_SPEED;
        this.invulnerable = false;
        this.invulnerableTimer = 0;
        this.remoteUnlocked = false;
        this.selectedBombType = BombType.NORMAL;
        this.moveCooldown = 0;
    }

    @Override
    public synchronized void update(double deltaTime) {
        if (moveCooldown > 0) {
            moveCooldown = Math.max(0, moveCooldown - deltaTime);
        }
        if (invulnerable) {
            invulnerableTimer -= deltaTime;
            if (invulnerableTimer <= 0) {
                invulnerable = false;
                invulnerableTimer = 0;
            }
        }
    }

    // ---------- Pergerakan (F01.2.1) ----------

    /** Player boleh pindah tile jika jeda geraknya sudah habis dan masih hidup. */
    public synchronized boolean canMove() {
        return isAlive() && moveCooldown <= 0;
    }

    /**
     * Mencatat arah hadap dan mengembalikan tile tujuan.
     * Engine mengecek tile ini ke map sebelum memanggil moveTo().
     */
    public synchronized Position getTargetPosition(Direction dir) {
        this.direction = dir;
        return position.move(dir);
    }

    /** Memindahkan player ke tile tujuan yang sudah dicek engine bisa dilalui. */
    public synchronized void moveTo(Position target) {
        this.position = target;
        this.moveCooldown = 1.0 / speed;
    }

    // ---------- Nyawa dan respawn (F01.2.2, F01.2.4) ----------

    /**
     * Mengurangi satu nyawa. Tidak berefek jika player sedang invulnerable
     * atau sudah tidak punya nyawa.
     * @return true jika damage benar-benar diterima
     */
    public synchronized boolean takeDamage() {
        if (invulnerable || !isAlive()) {
            return false;
        }
        lives--;
        return true;
    }

    /**
     * Memunculkan kembali player di tile yang sudah dipilih engine
     * (titik spawn, atau tile kosong terdekat jika spawn sedang terisi).
     */
    public synchronized void respawn(Position spawnTile) {
        this.position = spawnTile;
        this.direction = Direction.DOWN;
        this.moveCooldown = 0;
        this.invulnerable = true;
        this.invulnerableTimer = INVULNERABLE_DURATION;
    }

    public synchronized boolean isAlive() {
        return lives > 0;
    }

    // ---------- Bomb (F01.2.5, F02.3) ----------

    /** Masih ada kapasitas untuk memasang bomb baru. */
    public synchronized boolean canPlaceBomb() {
        return isAlive() && activeBombs < bombCapacity;
    }

    /** Dipanggil setelah bomb berhasil dipasang. */
    public synchronized void onBombPlaced() {
        activeBombs++;
    }

    /** Dipanggil setelah bomb milik player selesai meledak; kapasitas kembali. */
    public synchronized void onBombExploded() {
        if (activeBombs > 0) {
            activeBombs--;
        }
    }

    /**
     * Mengganti jenis bomb untuk pemasangan berikutnya.
     * Remote Bomb hanya bisa dipilih setelah Remote Detonator diperoleh.
     * @return true jika pilihan berhasil diganti
     */
    public synchronized boolean selectBombType(BombType type) {
        if (type == BombType.REMOTE && !remoteUnlocked) {
            return false;
        }
        this.selectedBombType = type;
        return true;
    }

    // ---------- Efek Power-Up (F06.2) ----------

    public synchronized void increaseBombCapacity() {
        bombCapacity = Math.min(bombCapacity + 1, MAX_BOMB_CAPACITY);
    }

    public synchronized void increaseBlastRange() {
        blastRange = Math.min(blastRange + 1, MAX_BLAST_RANGE);
    }

    public synchronized void increaseSpeed() {
        speed = Math.min(speed + BASE_SPEED * 0.5, MAX_SPEED);
    }

    public synchronized void unlockRemote() {
        remoteUnlocked = true;
    }

    public synchronized void addLife() {
        lives = Math.min(lives + 1, MAX_LIVES);
    }

    // ---------- Getter ----------

    public synchronized Direction getDirection() { return direction; }
    public synchronized int getLives() { return lives; }
    public synchronized int getBombCapacity() { return bombCapacity; }
    public synchronized int getActiveBombs() { return activeBombs; }
    public synchronized int getBlastRange() { return blastRange; }
    public synchronized double getSpeed() { return speed; }
    public synchronized boolean isInvulnerable() { return invulnerable; }
    public synchronized boolean isRemoteUnlocked() { return remoteUnlocked; }
    public synchronized BombType getSelectedBombType() { return selectedBombType; }
}
