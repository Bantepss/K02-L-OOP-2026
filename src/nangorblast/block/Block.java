package nangorblast.block;

import nangorblast.common.Position;

public abstract class Block {
    private final Position position;
    private boolean active = true;

    public Block(Position position) {
        this.position = position;
    }

    public Position getPosition() { return position; }
    public boolean isActive() { return active; }

    protected void deactivate() { active = false; }

    public boolean blocksMovement() { return active; }
    public boolean blocksExplosion() { return active; }

    public abstract boolean isDestructible();
    public abstract void hitByExplosion();
}