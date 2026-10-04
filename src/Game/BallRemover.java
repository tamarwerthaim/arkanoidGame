package Game;

import Sprites.Ball;
import Sprites.Block;

/**
 * A {@code BallRemover} is a HitListener responsible for removing balls from the game
 * when they hit a designated block (usually the bottom of the screen, representing the "death region").
 * It also updates a counter tracking the number of balls remaining in the game.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * Constructs a new {@code BallRemover} with access to the game and a ball counter.
     *
     * @param game the game instance from which balls should be removed
     * @param remainingBlocks a counter tracking the number of remaining balls in the game
     */
    public BallRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBalls = remainingBlocks;
    }

    /**
     * Called when a ball hits a block this listener is attached to.
     * Removes the ball from the game and decreases the remaining ball counter.
     *
     * @param beingHit the block that was hit (typically the "death region" block)
     * @param hitter the ball that hit the block and should be removed
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}
