import biuoop.GUI;
import java.util.ArrayList;
/**
 * Ass3Game.
 */
public class Ass5Game {
    /**
     * main.
     * @param args ignored
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        GameFlow game = new GameFlow(gui, new AnimationRunner(gui), gui.getKeyboardSensor());
        ArrayList<LevelInformation> levels = new ArrayList<LevelInformation>();
        if (args.length != 0) {
            for (int i = 0; i < args.length; i++) {
                try {
                    switch (Integer.parseInt(args[i])) {
                    case 1:
                        levels.add(new DirectHit());
                        break;
                    case 2:
                        levels.add(new WideEasy());
                        break;
                    case 3:
                        levels.add(new Green3());
                        break;
                    case 4:
                        levels.add(new FinalFour());
                        break;
                    default:
                        break;
                    }
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        }
        if (levels.isEmpty()) {
            levels.add(new DirectHit());
            levels.add(new WideEasy());
            levels.add(new Green3());
            levels.add(new FinalFour());
        }
        game.runLevels(levels);
        gui.close();
    }
}