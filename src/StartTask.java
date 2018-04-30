import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * StartTask.
 */
public class StartTask implements Task<Void> {

    private KeyboardSensor sensor;
    private File f;
    private AnimationRunner ar;
    private GUI gui;

    /**
     * Starts the game.
     * @param s - keyboardSensor.
     * @param f - the scores file.
     * @param ar - animationRunner
     * @param gui - gui;
     */
    public StartTask(GUI gui, KeyboardSensor s, File f, AnimationRunner ar) {
        this.sensor = s;
        this.f = f;
        this.ar = ar;
        this.gui = gui;
    }

    @Override
    public Void run() {
        GameFlow game = new GameFlow(this.gui, this.ar, this.sensor, this.f);
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(
                "definitions/hard_level_definitions.txt");
        LevelSpecificationReader l = new LevelSpecificationReader();
        List<LevelInformation> levels = l.fromReader(is);

        game.runLevels(levels);
        return null;
    }
}