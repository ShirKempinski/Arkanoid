import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Game holds the sprites and the collidables, and is in charge of the animation.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter balls;
    private BlockRemover blockRemover;
    private Counter score;
    private Counter lives;
    private Block deathRegion;
    private Sprite background;
    private Boolean running;
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private LevelInformation info;
    private Map<Integer, Fill> fills;

    /**
     * constructor.
     * @param info - the level information.
     * @param keyboard - the keyboard sensor.
     * @param runner - the animation runner.
     * @param score - current scores
     * @param lives - current lives
     */
    public GameLevel(LevelInformation info, KeyboardSensor keyboard, AnimationRunner runner,
            Counter score, Counter lives) {
        this.info = info;
        this.keyboard = keyboard;
        this.runner = runner;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.balls = new Counter(0);
        this.blockRemover = new BlockRemover(this, new Counter(0));
        this.score = score;
        this.lives = lives;
        this.fills = new HashMap<Integer, Fill>();
        this.fills.put(0, new Fill(ColorsParser.colorFromString("gray")));
        this.deathRegion = new Block(new Rectangle(new Point(0, 595), 800, 20), 0, null, fills);
        this.background = this.info.getBackground();
        this.running = true;
    }

    /**
     * Add a new Collidable to the collidables list.
     *
     * @param c - the collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add a new Sprite to the sprites list.
     *
     * @param s - the sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Remove the given collidable from the game.
     * @param c the collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidables().remove(c);
    }

    /**
     * Remove the sprite from the game.
     * @param s the sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSprites().remove(s);
    }

    /**
     * getBallsCounter.
     * @return the balls counter.
     */
    public Counter getBallsCounter() {
        return this.balls;
    }

    /**
     * getBlockRemover.
     * @return the block remover
     */
    public BlockRemover getBlockRemover() {
        return this.blockRemover;
    }

    /**
     * getDethRegion.
     * @return the death region
     */
    public Block getDeathRegion() {
        return this.deathRegion;
    }

    /**
     * getScore.
     * @return the scores.
     */
    public Counter getScore() {
        return this.score;
    }

    /**
     * getLives.
     * @return the lives.
     */
    public Counter getLives() {
        return this.lives;
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     */
    public void initialize() {

        this.deathRegion.addToGame(this);
        background.addToGame(this);

        Block left = new Block(new Rectangle(new Point(0, 0), 20, 600), 0, null, this.fills);
        Block right = new Block(new Rectangle(new Point(780, 0), 20, 600), 0, null, this.fills);
        Block top = new Block(new Rectangle(new Point(0, 20), 800, 20), 0, null, this.fills);

        left.addToGame(this);
        right.addToGame(this);
        top.addToGame(this);

        BallRemover ballRemover = new BallRemover(this);
        this.deathRegion.addHitListener(ballRemover);

        ScoreTrackingListener scoreListener = new ScoreTrackingListener(this.score);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this);
        scoreIndicator.addToGame(this);

        LivesIndicator livesIndicator = new LivesIndicator(this);
        livesIndicator.addToGame(this);

        LevelIndicator levelIndicator = new LevelIndicator(this.info);
        levelIndicator.addToGame(this);

        for (Block b: this.info.blocks()) {
            b.addToGame(this);
            b.addHitListener(this.blockRemover);
            b.addHitListener(scoreListener);
            this.blockRemover.getCounter().increase(1);
        }
    }

    /**
     * play one turn start the animation loop.
     */
    public void playOneTurn() {
        for (int i = 0; i < this.info.numberOfBalls(); i++) {
            Ball ball = new Ball(400, 500, 5, Color.WHITE, this.info.initialBallVelocities().get(i), this.environment);
            ball.addToGame(this);
        }

        Paddle paddle = new Paddle(this.keyboard, new Rectangle(new Point((800 - this.info.paddleWidth()) / 2, 570),
                this.info.paddleWidth(), this.info.paddleSpeed() * 360), Color.ORANGE, this.info.paddleSpeed());
        paddle.addToGame(this);

        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
        removeCollidable(paddle);
        this.sprites.getSprites().remove(paddle);
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
        if (this.balls.getValue() == 0) {
            this.lives.decrease(1);
            this.running = false;
        }
        if (this.blockRemover.getCounter().getValue() == 0) {
            this.running = false;
            this.score.increase(100);
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}