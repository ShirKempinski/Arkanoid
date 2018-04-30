/**
 * Removes the ball from the game.
 */
public class BallRemover implements HitListener {

    private GameLevel game;

    /**
     * constructor.
     * @param g the game.
     */
    public BallRemover(GameLevel g) {
        this.game = g;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.equals(this.game.getDeathRegion())) {
            hitter.removeFromGame(this.game);
        }
    }

}
