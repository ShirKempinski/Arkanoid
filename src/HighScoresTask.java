import biuoop.KeyboardSensor;

import java.io.File;

/**
 * HighScoresTask.
 */
public class HighScoresTask implements Task<Void> {

    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private File f;

    /**
     * constructor.
     * @param k - sensor.
     * @param f - file.
     * @param ar - runnner
     */
    public HighScoresTask(KeyboardSensor k, File f, AnimationRunner ar) {
        this.animationRunner = ar;
        this.keyboardSensor = k;
        this.f = f;
    }

    @Override
    public Void run() {
        KeyPressStoppableAnimation kpsTable = new KeyPressStoppableAnimation(this.keyboardSensor,
                KeyboardSensor.SPACE_KEY, new HighScoresAnimation(HighScoresTable.loadFromFile(this.f)));
        while (!kpsTable.shouldStop()) {
            this.animationRunner.run(kpsTable);
        }
        return null;
    }
}