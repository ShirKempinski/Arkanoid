import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Second level.
 */
public class WideEasy implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocities = new ArrayList<Velocity>();
        velocities.add(Velocity.fromAngleAndSpeed(5, 5));
        velocities.add(Velocity.fromAngleAndSpeed(15, 5));
        velocities.add(Velocity.fromAngleAndSpeed(25, 5));
        velocities.add(Velocity.fromAngleAndSpeed(35, 5));
        velocities.add(Velocity.fromAngleAndSpeed(45, 5));
        velocities.add(Velocity.fromAngleAndSpeed(-5, 5));
        velocities.add(Velocity.fromAngleAndSpeed(-15, 5));
        velocities.add(Velocity.fromAngleAndSpeed(-25, 5));
        velocities.add(Velocity.fromAngleAndSpeed(-35, 5));
        velocities.add(Velocity.fromAngleAndSpeed(-45, 5));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 660;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new WideEasyBackground();
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList<Block>();
        Color c = Color.RED;
        for (int i = 0; i < 15; i++) {
            if (i == 2) {
                c = Color.ORANGE;
            } else if (i == 4) {
                c = Color.YELLOW;
            } else if (i == 6) {
                c = Color.GREEN;
            } else if (i == 9) {
                c = Color.BLUE;
            } else if (i == 11) {
                c = Color.PINK;
            } else if (i == 13) {
                c = Color.CYAN;
            } else if (i == 14) {
                Block b = new Block(new Rectangle(new Point(20 + 51 * i, 300), 46, 25), c, 1);
                blocks.add(b);
                break;
            }
            Block b = new Block(new Rectangle(new Point(20 + 51 * i, 300), 51, 25), c, 1);
            blocks.add(b);
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}