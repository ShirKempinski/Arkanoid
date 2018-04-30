import java.util.List;

/**
 * LevelInformation specifies the information required to fully describe a level.
 */
public interface LevelInformation {
    /**
     * numberOfBalls.
     * @return the number of balls in the game.
     */

    int numberOfBalls();
    /**
     * initialBallVelocities.
     * @return the initial velocity of each ball.
     */
    List<Velocity> initialBallVelocities();

    /**
     * paddleSpeed.
     * @return the paddle speed
     */
    int paddleSpeed();

    /**
     * paddleWidth.
     * @return the paddle width.
     */
    int paddleWidth();

    /**
     * levelName.
     * @return the level name.
     */
    String levelName();

    /**
     * getBackground.
     * @return  a sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     * @return the blocks list.
     */
    List<Block> blocks();

    /**
     * Number of levels that should be removed before the level is considered to be "cleared".
     * @return the number of the left blocks.
     */
    int numberOfBlocksToRemove();
}