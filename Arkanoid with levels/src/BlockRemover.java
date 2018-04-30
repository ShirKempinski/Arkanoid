/**
 * BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {

    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * constructor.
     * @param game - the game.
     * @param removedBlocks - the block.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * getCounter.
     * @return the remaining blocks counter
     */
    public Counter getCounter() {
        return this.remainingBlocks;
    }

    // Blocks that are hit and reach 0 hit-points should be removed from the game.
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHits() == 1) {
            beingHit.removeFromGame(this.game);
            beingHit.removeHitListener(this);
            this.game.getBlockRemover().getCounter().decrease(1);
        }

    }

}
