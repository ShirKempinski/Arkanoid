import biuoop.DrawSurface;

/**
 * Sprite can be drawn on the screen, and can be notified that time has passed.
 */
public interface Sprite {
    /**
     * Draw the sprite to the screen.
     *
     * @param d - the drawSurface
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * add the sprite to the game.
     *
     * @param game - the game.
     */
    void addToGame(GameLevel game);
}
