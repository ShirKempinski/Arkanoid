import java.awt.Color;

import biuoop.DrawSurface;

/**
 * Level indicator is in charge of displaying the current level.
 */
public class LevelIndicator implements Sprite {

    private LevelInformation level;

    /**
     * constructor.
     * @param level this level.
     */
    public LevelIndicator(LevelInformation level) {
        this.level = level;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(620, 15, "Level Name: " + level.levelName(), 14);
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