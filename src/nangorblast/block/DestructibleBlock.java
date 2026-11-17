package nangorblast.block;

import nangorblast.common.Position;

public class DestructibleBlock extends Block {
    public DestructibleBlock(Position position) { super(position); }

    @Override public boolean isDestructible() { return true; }
    @Override public void hitByExplosion() { deactivate(); }
}