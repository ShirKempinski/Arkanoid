import java.awt.Color;

import biuoop.DrawSurface;
/**
 * LivesIndicator is in charge of displaying the current lives.
 */
public class LivesIndicator implements Sprite {

    private int lives;
    private GameLevel g;

    /**
     * constructor.
     * @param g - the game.
     */
    public LivesIndicator(GameLevel g) {
        this.g = g;
        this.lives = g.getLives().getValue();
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(50, 15, "Lives: " + this.lives, 14);

    }

    @Override
    public void timePassed() {
        this.lives = this.g.getLives().getValue();

    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

}
