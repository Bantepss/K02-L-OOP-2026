package nangorblast.enemy;

import nangorblast.common.Entity;
import nangorblast.common.Position;

public abstract class Enemy extends Entity {
    protected int health;
    protected final double speed;
    protected final int scoreValue;
    protected boolean active = true;

    protected Enemy(Position position, int health, double speed, int scoreValue) {
        super(position);
        this.health = health;
        this.speed = speed;
        this.scoreValue = scoreValue;
    }

    public synchronized int getHealth() { return health; }
    public double getSpeed() { return speed; }
    public int getScoreValue() { return scoreValue; }
    public synchronized boolean isActive() { return active; }

    /** Mengembalikan true hanya jika pukulan ini yang membunuh enemy. */
    public synchronized boolean takeDamage(int amount) {
        if (!active) return false;
        health -= amount;
        if (health <= 0) {
            health = 0;
            active = false;
            return true;
        }
        return false;
    }
}