import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Game flow is in charge of creating the different levels, and moving from one level to the next.
 */
public class GameFlow {

    private GUI gui;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter lives;
    private Counter score;
    private File f;

    /**
     * constructor.
     * @param ar - AnimationRunner.
     * @param ks - KeyboardSensor.
     * @param gui - GUI.
     * @param f - file.
     */
    public GameFlow(GUI gui, AnimationRunner ar, KeyboardSensor ks, File f) {
        this.keyboardSensor = ks;
        this.animationRunner = ar;
        this.score = new Counter(0);
        this.lives = new Counter(7);
        this.f = f;
        this.gui = gui;
    }

    /**
     * run the game levels.
     * @param levels - the game levels
     */
    public void runLevels(List<LevelInformation> levels) {
        HighScoresTable scores = HighScoresTable.loadFromFile(this.f);
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
                KeyPressStoppableAnimation kpsLose = new KeyPressStoppableAnimation(this.keyboardSensor,
                        KeyboardSensor.SPACE_KEY, new EndScreen(this.keyboardSensor, false, this.score.getValue()));
                while (!kpsLose.shouldStop()) {
                    this.animationRunner.run(kpsLose);
                }
                break;
            }
        }
        if (this.lives.getValue() != 0) {
            KeyPressStoppableAnimation kpsWin = new KeyPressStoppableAnimation(this.keyboardSensor,
                    KeyboardSensor.SPACE_KEY, new EndScreen(this.keyboardSensor, true, this.score.getValue()));
            while (!kpsWin.shouldStop()) {
                this.animationRunner.run(kpsWin);
            }
        }
        if (scores.getRank(this.score.getValue()) <= scores.size() || scores.getHighScores().isEmpty()) {
            DialogManager dialog = this.gui.getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            scores.add(new ScoreInfo(name, this.score.getValue()));
            try {
              scores.save(this.f);
          } catch (IOException e) {
              e.printStackTrace();
          }
        }
        new HighScoresTask(this.keyboardSensor, this.f, this.animationRunner).run();
    }
}