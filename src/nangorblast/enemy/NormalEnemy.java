package nangorblast.enemy;

import nangorblast.common.Position;
import nangorblast.score.Score;

public class NormalEnemy extends Enemy {
    public static final int NORMAL_ENEMY_HEALTH = 1;
    public static final double DEFAULT_SPEED = 1.0;
    public static final int SCORE_VALUE = Score.NORMAL_ENEMY_POINTS;

    public NormalEnemy(Position position) {
        super(position, NORMAL_ENEMY_HEALTH, DEFAULT_SPEED, SCORE_VALUE);
    }

    @Override
    public void update(double deltaTime) {
        // gerakan ditambahkan di tahap berikutnya (Strategy Patrol/Chase)
    }
}