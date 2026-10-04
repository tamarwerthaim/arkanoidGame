package Game;

import Sprites.Ball;
import Sprites.Block;

/**
 * The {@code HitListener} interface should be implemented by objects that want to be notified
 * when a {@link Block} is hit by a {@link Ball}.
 */
public interface HitListener {

    /**
     * This method is called whenever the {@code beingHit} block is hit.
     *
     * @param beingHit the block that was hit.
     * @param hitter the ball that hit the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
