import java.awt.Color;

import biuoop.DrawSurface;

/**
 * Background color.
 */
public class BackgroundColor implements Sprite {

    private Color c;

    /**
     * constructor.
     * @param c - the color.
     */
    public BackgroundColor(Color c) {
        this.c = c;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.c);
        d.fillRectangle(0, 0, 800, 600);
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