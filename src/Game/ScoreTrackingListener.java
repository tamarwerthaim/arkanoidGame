package Game;

import Sprites.Ball;
import Sprites.Block;

/**
 * The {@code ScoreTrackingListener} is a {@link HitListener} that updates the game's score
 * whenever a block is hit and removed from the game.
 * Each hit increases the score by a fixed amount (5 points).
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructs a {@code ScoreTrackingListener} with a reference to a {@link Counter}
     * that tracks the current score.
     *
     * @param scoreCounter the counter used to track and update the score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Called whenever a block is hit.
     * Increases the score by 5 points.
     *
     * @param beingHit the block that was hit.
     * @param hitter the ball that hit the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}
