import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Ass3Game.
 */
public class Ass6Game {
    /**
     * main.
     * @param args - the levels set file path.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        File scores =  new File("highscores.ser");
        AnimationRunner runner = new AnimationRunner(gui);
        KeyboardSensor k = gui.getKeyboardSensor();

        InputStream is;
        if (args.length != 0) {
            is = ClassLoader.getSystemClassLoader().getResourceAsStream(args[0]);
        } else {
            is = ClassLoader.getSystemClassLoader().getResourceAsStream("level_sets.txt");
        }

        LineNumberReader reader = new LineNumberReader(new InputStreamReader(is));
        List<LevelsSet> sets = new ArrayList<LevelsSet>();
        String line;
        String key = null, path = null, message = null;

        try {
            while ((line = reader.readLine()) != null) {
                if (reader.getLineNumber() % 2 == 0) {
                    path = line;
                } else {
                    key = line.substring(0, 1);
                    message = line.substring(2);
                    continue;
                }
                sets.add(new LevelsSet(key, message, path));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        MenuAnimation<Task<Void>> subMenu = new MenuAnimation<Task<Void>>(k, runner);
        for (LevelsSet set: sets) {
            Task<Void> temp = new Task<Void>() {
                public Void run() {
                    GameFlow game = new GameFlow(gui, runner, k, scores);
                    InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(set.getPath());
                    LevelSpecificationReader l = new LevelSpecificationReader();
                    List<LevelInformation> levels = l.fromReader(stream);

                    game.runLevels(levels);
                    return null;
                }
            };
            subMenu.addSelection(set.getKey(), set.getName(), temp);
        }
        Menu<Task<Void>> mainMenu = new MenuAnimation<Task<Void>>(k, runner);
        mainMenu.addSubMenu("s", "Start New Game", subMenu);
        mainMenu.addSelection("h", "High Scores Table", new HighScoresTask(k, scores, runner));
        mainMenu.addSelection("q", "Quit", new QuitTask(gui));

        while (true) {
            runner.run(mainMenu);
            Task<Void> task = mainMenu.getStatus();
            task.run();
        }
    }
}