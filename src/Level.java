import java.util.List;

/**
 * Level.
 */
public class Level implements LevelInformation {

    private int numberOfBalls;
    private List<Velocity> initialBallVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;

    /**
     * constructor.
     * @param numberOfBlocksToRemove 1
     * @param initialBallVelocities 1
     * @param paddleSpeed 1
     * @param paddleWidth 1
     * @param levelName 1
     * @param background 1
     * @param blocks 1
     */
    public Level(List<Velocity> initialBallVelocities, int paddleSpeed, int paddleWidth,
            String levelName, Sprite background, List<Block> blocks, int numberOfBlocksToRemove) {
        this.background = background;
        this.blocks = blocks;
        this.initialBallVelocities = initialBallVelocities;
        this.levelName = levelName;
        this.numberOfBalls = initialBallVelocities.size();
        this.paddleSpeed = paddleSpeed;
        this.paddleWidth = paddleWidth;
        this.numberOfBlocksToRemove = numberOfBlocksToRemove;
    }
    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.initialBallVelocities;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }

}
