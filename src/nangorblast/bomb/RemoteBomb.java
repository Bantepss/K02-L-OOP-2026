package nangorblast.bomb;

import nangorblast.common.Position;
import nangorblast.player.Player;

/**
 * Bomb yang tidak punya fuse; hanya meledak lewat perintah DETONATE
 * atau terkena Explosion bomb lain (F02.2.3).
 */
public class RemoteBomb extends Bomb {

    public RemoteBomb(Player owner, Position position) {
        super(BombType.REMOTE, owner, position, owner.getBlastRange(), 0);
    }

    @Override
    protected boolean hasFuse() {
        return false;
    }

    @Override
    public int getDamage() {
        return 1;
    }
}
