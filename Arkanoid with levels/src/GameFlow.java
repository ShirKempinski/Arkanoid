import biuoop.KeyboardSensor;
import java.util.List;
import biuoop.GUI;

/**
 * Game flow is in charge of creating the differnet levels, and moving from one level to the next.
 */
public class GameFlow {

    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private GUI gui;
    private Counter lives;
    private Counter score;

    /**
     * constructor.
     * @param ar - AnimationRunner.
     * @param ks - KeyboardSensor.
     * @param gui - gui.
     */
    public GameFlow(GUI gui, AnimationRunner ar, KeyboardSensor ks) {
        this.keyboardSensor = ks;
        this.animationRunner = ar;
        this.score = new Counter(0);
        this.lives = new Counter(7);
    }

    /**
     * run the game levels.
     * @param levels - the game levels
     */
    public void runLevels(List<LevelInformation> levels) {
       // ...
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner,
                    this.score, this.lives);
            level.initialize();

            while (level.getBlockRemover().getCounter().getValue() > 0) {
                level.playOneTurn();
                if (level.getLives().getValue() == 0) {
                    while (this.lives.getValue() != 0) {
                        this.lives.decrease(1);
                    }
                    break;
                }
                if (level.getBallsCounter().getValue() == 0) {
                    continue;
                }
            }
            if (this.lives.getValue() == 0) {
                EndScreen loseScreen = new EndScreen(this.keyboardSensor, false, this.score.getValue());
                while (!loseScreen.shouldStop()) {
                    this.animationRunner.run(loseScreen);
                }
                break;
            }
       }
       if (this.lives.getValue() != 0) {
           EndScreen winScreen = new EndScreen(this.keyboardSensor, true, this.score.getValue());
           while (!winScreen.shouldStop()) {
               this.animationRunner.run(winScreen);
           }
       }
    }
}