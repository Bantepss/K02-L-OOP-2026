package nangorblast.bomb;

import nangorblast.common.Position;
import nangorblast.player.Player;

/** Bomb dasar: meledak 3 detik setelah dipasang, jangkauan mengikuti blastRange player (F02.2.1). */
public class NormalBomb extends Bomb {
    public static final double FUSE_TIME = 3.0;

    public NormalBomb(Player owner, Position position) {
        super(BombType.NORMAL, owner, position, owner.getBlastRange(), FUSE_TIME);
    }

    @Override
    protected boolean hasFuse() {
        return true;
    }

    @Override
    public int getDamage() {
        return 1;
    }
}
