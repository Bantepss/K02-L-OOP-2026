package nangorblast.score;

/**
 * Menghitung score permainan (F08).
 *
 * Aturan:
 * - Score dimulai dari 0 dan kembali ke 0 saat restart.
 * - Normal Enemy dikalahkan: +100.
 * - Cerberus atau Altair dikalahkan: +250 per boss.
 * - Boss lain dikalahkan: +500.
 * - Bonus hanya saat WIN: +100 per nyawa tersisa dan +10 per detik timer tersisa.
 *
 * Semua method synchronized karena score bisa ditambah dari beberapa thread
 * (misalnya enemy dan boss dikalahkan oleh ledakan yang sama).
 */
public class Score {
    public static final int NORMAL_ENEMY_POINTS = 100;
    public static final int BLACK_FORTRESS_BOSS_POINTS = 250;
    public static final int OTHER_BOSS_POINTS = 500;
    public static final int POINTS_PER_LIFE = 100;
    public static final int POINTS_PER_SECOND = 10;

    private int value;
    private boolean winBonusGiven;

    public Score() {
        reset();
    }

    public synchronized void addNormalEnemyKill() {
        value += NORMAL_ENEMY_POINTS;
    }

    /**
     * Menambah score saat boss dikalahkan.
     * @param bossName nama boss, misalnya "Cerberus" atau "Altair"
     */
    public synchronized void addBossKill(String bossName) {
        if ("Cerberus".equalsIgnoreCase(bossName) || "Altair".equalsIgnoreCase(bossName)) {
            value += BLACK_FORTRESS_BOSS_POINTS;
        } else {
            value += OTHER_BOSS_POINTS;
        }
    }

    /**
     * Menambah bonus nyawa dan sisa waktu. Hanya dipanggil saat WIN,
     * dan hanya berlaku satu kali per permainan.
     */
    public synchronized void applyWinBonus(int livesLeft, int secondsLeft) {
        if (winBonusGiven) {
            return;
        }
        value += Math.max(0, livesLeft) * POINTS_PER_LIFE;
        value += Math.max(0, secondsLeft) * POINTS_PER_SECOND;
        winBonusGiven = true;
    }

    /** Mengembalikan score ke 0 (dipanggil saat restart). */
    public synchronized void reset() {
        value = 0;
        winBonusGiven = false;
    }

    public synchronized int getValue() {
        return value;
    }
}
