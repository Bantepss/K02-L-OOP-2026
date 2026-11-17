package nangorblast.powerup;

import java.util.Random;

import nangorblast.common.Position;

/**
 * Design pattern Factory untuk Power-Up (F06.3).
 * Objek Random bisa diberikan dari luar supaya hasilnya bisa diatur saat testing.
 */
public class PowerUpFactory {
    /** Peluang Destructible Block menjatuhkan Power-Up saat hancur. */
    public static final double DROP_CHANCE = 0.30;

    private final Random random;

    public PowerUpFactory() {
        this(new Random());
    }

    public PowerUpFactory(Random random) {
        this.random = random;
    }

    /**
     * Dipanggil saat Destructible Block hancur. Dengan peluang 30%, menghasilkan
     * tepat satu Power-Up dengan jenis acak dari kelima jenis.
     * @return Power-Up baru, atau null jika tidak ada yang jatuh
     */
    public synchronized PowerUp tryDrop(Position position) {
        if (random.nextDouble() >= DROP_CHANCE) {
            return null;
        }
        PowerUpType[] types = PowerUpType.values();
        return new PowerUp(types[random.nextInt(types.length)], position);
    }

    /**
     * Membuat Power-Up jenis tertentu di posisi tertentu, misalnya Remote Detonator
     * yang dijamin ada di setiap map.
     */
    public PowerUp create(PowerUpType type, Position position) {
        return new PowerUp(type, position);
    }
}
