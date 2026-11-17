package nangorblast.bomb;

import nangorblast.common.Entity;
import nangorblast.common.Position;
import nangorblast.player.Player;

/**
 * Kelas dasar semua jenis bomb (F02).
 *
 * Alur yang diharapkan di game loop:
 * 1. Setiap frame engine memanggil update(deltaTime).
 * 2. Jika isReadyToExplode() bernilai true, engine memanggil explode().
 * 3. explode() hanya mengembalikan true sekali, lalu engine membuat
 *    Explosion memakai getPosition(), getBlastRange(), getDamage(),
 *    dan canDestroyReinforced().
 * 4. Jika Explosion mengenai bomb lain, engine memanggil trigger() pada
 *    bomb itu (chain reaction).
 */
public abstract class Bomb extends Entity {
    private final BombType type;
    private final Player owner;
    private final int blastRange;
    protected double fuseTime;
    private boolean active;
    private boolean triggered;

    protected Bomb(BombType type, Player owner, Position position, int blastRange, double fuseTime) {
        super(position);
        this.type = type;
        this.owner = owner;
        this.blastRange = blastRange;
        this.fuseTime = fuseTime;
        this.active = true;
        this.triggered = false;
    }

    @Override
    public synchronized void update(double deltaTime) {
        if (!active || triggered || !hasFuse()) {
            return;
        }
        fuseTime -= deltaTime;
        if (fuseTime <= 0) {
            fuseTime = 0;
            triggered = true;
        }
    }

    /** Memicu bomb agar meledak pada frame berikutnya (detonasi remote atau chain reaction). */
    public synchronized void trigger() {
        if (active) {
            triggered = true;
        }
    }

    public synchronized boolean isReadyToExplode() {
        return active && triggered;
    }

    /**
     * Meledakkan bomb. Hanya berhasil satu kali (F02.3 aturan 8):
     * bomb menjadi tidak aktif dan kapasitas bomb dikembalikan ke pemilik.
     * @return true jika ledakan terjadi sekarang, false jika sudah pernah meledak
     */
    public synchronized boolean explode() {
        if (!active) {
            return false;
        }
        active = false;
        triggered = false;
        owner.onBombExploded(this);
        return true;
    }

    /** Apakah bomb ini meledak sendiri setelah fuse habis. */
    protected abstract boolean hasFuse();

    /** Damage kepada boss yang sedang rentan. */
    public abstract int getDamage();

    /** Hanya Pumped Bomb yang bisa menghancurkan Reinforced Block. */
    public boolean canDestroyReinforced() {
        return false;
    }

    public BombType getType() { return type; }
    public Player getOwner() { return owner; }
    public int getBlastRange() { return blastRange; }
    public synchronized double getFuseTime() { return fuseTime; }
    public synchronized boolean isActive() { return active; }
}
