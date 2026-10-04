package Game;

import Sprites.Ball;
import Sprites.Block;

/**.
 * {@code BlockRemover} is a HitListener that is responsible for:
 * <ul>
 *     <li>Removing blocks from the game when they are hit by a ball of a different color</li>
 *     <li>Updating the counter that tracks the number of blocks remaining in the game</li>
 * </ul>
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructs a new {@code BlockRemover} with a reference to the game
     * and the counter that tracks how many blocks are still in play.
     *
     * @param game            the game instance from which blocks will be removed
     * @param remainingBlocks the counter tracking the number of blocks remaining in the game
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Called when a block is hit. If the ball color is different from the block color,
     * the block is removed from the game and the block counter is decreased.
     * The ball also changes its color to match the block that was hit.
     * Additionally, this listener is removed from the block to prevent duplicate calls.
     *
     * @param beingHit the block that was hit and will be removed
     * @param hitter   the ball that hit the block (its color will be updated)
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.setColor(beingHit.getColor());
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.game);
        this.remainingBlocks.decrease(1);
    }
}
