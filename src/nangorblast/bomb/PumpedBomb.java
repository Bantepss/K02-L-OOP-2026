package nangorblast.bomb;

import nangorblast.common.Position;
import nangorblast.player.Player;

/**
 * Bomb besar: meledak 4,5 detik setelah dipasang, jangkauan 2 tile lebih jauh
 * dari Normal Bomb (maksimum 7), memberi 2 damage ke boss, dan bisa
 * menghancurkan Reinforced Block (F02.2.2).
 */
public class PumpedBomb extends Bomb {
    public static final double FUSE_TIME = 4.5;
    public static final int EXTRA_RANGE = 2;
    public static final int MAX_RANGE = 7;

    public PumpedBomb(Player owner, Position position) {
        super(BombType.PUMPED, owner, position,
              Math.min(owner.getBlastRange() + EXTRA_RANGE, MAX_RANGE), FUSE_TIME);
    }

    @Override
    protected boolean hasFuse() {
        return true;
    }

    @Override
    public int getDamage() {
        return 2;
    }

    @Override
    public boolean canDestroyReinforced() {
        return true;
    }
}
