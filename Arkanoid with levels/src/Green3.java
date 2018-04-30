import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * the third level.
 */
public class Green3 implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocities = new ArrayList<Velocity>();
        velocities.add(Velocity.fromAngleAndSpeed(35, 5));
        velocities.add(Velocity.fromAngleAndSpeed(-35, 5));
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
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Green3Background();
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList<Block>();
        Color c = Color.GRAY;
        for (int i = 0; i < 5; i++) {
            int hits = 1;
            switch (i) {
            case 1:
                c = Color.RED;
                break;
            case 2:
                c = Color.YELLOW;
                break;
            case 3:
                c = Color.BLUE;
                break;
            case 4:
                c = Color.WHITE;
                break;
            default:
                break;
            }
            for (int j = i; j <= 10; j++) {
                if (i == 0) {
                    hits = 2;
                }
                Block b = new Block(new Rectangle(new Point(780 - 50 * (j - i + 1), 200 + i * 25), 50, 25), c, hits);
                blocks.add(b);
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 56;
    }

}
