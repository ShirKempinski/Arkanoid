/**
 * HitListener is an object that want to be notified of hit events.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * the hitter parameter is the Ball that's doing the hitting.
     * @param beingHit - the block
     * @param hitter - the ball
     */
    void hitEvent(Block beingHit, Ball hitter);
}
