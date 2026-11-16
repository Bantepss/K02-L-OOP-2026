package nangorblast.block;

import nangorblast.common.Position;

public class IndestructibleBlock extends Block {
    public IndestructibleBlock(Position position) { super(position); }

    @Override public boolean isDestructible() { return false; }
    @Override public void hitByExplosion() { }
}