import java.awt.Color;

import biuoop.DrawSurface;

/**
 * lose screen.
 */
public class LoseScreen implements Sprite {

    private int score;

    /**
     * constructor.
     * @param score - the score number.
     */
    public LoseScreen(int score) {
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.RED);
        d.drawText(130, d.getHeight() / 2 - 50, "Game Over.", 100);
        d.drawText(195, d.getHeight() / 2 + 80, "Your score is " + this.score, 60);
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