import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * forth level.
 */
public class FinalFour implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocities = new ArrayList<Velocity>();
        velocities.add(Velocity.fromAngleAndSpeed(0, 5));
        velocities.add(Velocity.fromAngleAndSpeed(-35, 5));
        velocities.add(Velocity.fromAngleAndSpeed(35, 5));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 80;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new FinalFourBackground();
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList<Block>();
        Color c = Color.GRAY;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 15; j++) {
                switch (i) {
                case 1:
                    c = Color.RED;
                    break;
                case 2:
                    c = Color.YELLOW;
                    break;
                case 3:
                    c = Color.GREEN;
                    break;
                case 4:
                    c = Color.WHITE;
                    break;
                case 5:
                    c = Color.PINK;
                    break;
                case 6:
                    c = Color.CYAN;
                default:
                    break;
                }
                if (j == 14) {
                    Block b = new Block(new Rectangle(new Point(20 + 51 * j, 200 + 25 * i), 46, 25), c, 1);
                    blocks.add(b);
                } else {
                    Block b = new Block(new Rectangle(new Point(20 + 51 * j, 200 + 25 * i), 51, 25), c, 1);
                    blocks.add(b);
                }
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }

}
