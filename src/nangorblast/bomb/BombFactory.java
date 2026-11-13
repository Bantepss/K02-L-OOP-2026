package nangorblast.bomb;

import nangorblast.common.Position;
import nangorblast.player.Player;

/** Design pattern Factory: satu tempat untuk membuat bomb sesuai jenisnya. */
public final class BombFactory {

    private BombFactory() {
    }

    public static Bomb createBomb(BombType type, Player owner, Position position) {
        return switch (type) {
            case NORMAL -> new NormalBomb(owner, position);
            case PUMPED -> new PumpedBomb(owner, position);
            case REMOTE -> new RemoteBomb(owner, position);
        };
    }
}
