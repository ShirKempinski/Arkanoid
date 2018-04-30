import java.awt.Color;

import biuoop.DrawSurface;

/**
 * ScoreIndicator is in charge of displaying the current score.
 */
public class ScoreIndicator implements Sprite {

    private int scores;
    private GameLevel game;

    /**
     * constructor.
     * @param g the game
     */
    public ScoreIndicator(GameLevel g) {
        this.game = g;
        this.scores = g.getScore().getValue();
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, 800, 20);
        d.setColor(Color.BLACK);
        d.drawText(370, 15, "Score: " + this.scores, 14);
    }

    @Override
    public void timePassed() {
        this.scores = this.game.getScore().getValue();
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}