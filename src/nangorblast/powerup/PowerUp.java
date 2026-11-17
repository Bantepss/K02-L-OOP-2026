package nangorblast.powerup;

import nangorblast.common.Entity;
import nangorblast.common.Position;
import nangorblast.player.Player;

/**
 * Item di map yang memberi peningkatan kemampuan ke player (F06).
 * Efeknya permanen sampai permainan berakhir atau di-restart, dan tidak hilang
 * saat player kehilangan nyawa.
 */
public class PowerUp extends Entity {
    private final PowerUpType type;
    private boolean active;

    public PowerUp(PowerUpType type, Position position) {
        super(position);
        this.type = type;
        this.active = true;
    }

    /** Power-Up tidak bergerak dan tidak punya timer. */
    @Override
    public void update(double deltaTime) {
        // tidak ada yang perlu diperbarui
    }

    /**
     * Memberikan efek ke player lalu menandai Power-Up sudah diambil.
     * Hanya berhasil satu kali; setelah itu engine menghapusnya dari map.
     * @return true jika efek diberikan sekarang
     */
    public synchronized boolean applyTo(Player player) {
        if (!active) {
            return false;
        }
        type.applyTo(player);
        active = false;
        return true;
    }

    public PowerUpType getType() {
        return type;
    }

    public synchronized boolean isActive() {
        return active;
    }
}
