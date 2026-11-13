package nangorblast.powerup;

import nangorblast.player.Player;

/**
 * Lima jenis Power-Up (F06.2). Setiap jenis punya efeknya sendiri lewat
 * method applyTo(), jadi menambah jenis baru cukup dengan menambah konstanta
 * di sini tanpa mengubah kelas lain.
 */
public enum PowerUpType {
    /** Menambah bombCapacity 1, maksimum 3. */
    BOMB_UP {
        @Override
        public void applyTo(Player player) {
            player.increaseBombCapacity();
        }
    },
    /** Menambah blastRange 1, maksimum 5. */
    FIRE_UP {
        @Override
        public void applyTo(Player player) {
            player.increaseBlastRange();
        }
    },
    /** Menambah kecepatan 50% dari kecepatan awal, maksimum +100%. */
    SPEED_UP {
        @Override
        public void applyTo(Player player) {
            player.increaseSpeed();
        }
    },
    /** Membuka kemampuan memakai Remote Bomb. */
    REMOTE_DETONATOR {
        @Override
        public void applyTo(Player player) {
            player.unlockRemote();
        }
    },
    /** Menambah 1 nyawa, maksimum 5. */
    HEART {
        @Override
        public void applyTo(Player player) {
            player.addLife();
        }
    };

    public abstract void applyTo(Player player);
}
