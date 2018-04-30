import java.awt.Color;
import java.util.Random;
import biuoop.DrawSurface;

/**
 * win screen.
 */
public class WinScreen implements Sprite {

    private int score;

    /**
     * constructor.
     * @param score - the score number.
     */
    public WinScreen(int score) {
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Ball.randomColor(new Random()));
        d.drawText(110, d.getHeight() / 2 - 50, "You Win!", 150);
        d.drawText(190, d.getHeight() / 2 + 100, "Your score is " + this.score, 60);
    }

    @Override
    public void timePassed(double dt) {
        return;
    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

}
