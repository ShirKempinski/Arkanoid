import java.awt.Color;

import biuoop.DrawSurface;

/**
 * Green with a building.
 */
public class Green3Background implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        // background
        d.setColor(new Color(4, 127, 4));
        d.fillRectangle(0, 0, 800, 600);

        // building
        d.setColor(new Color(43, 47, 43));
        d.fillRectangle(55, 450, 95, 150);
        d.setColor(Color.WHITE);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(65 + j * 16, 460 + i * 31, 10, 25);
            }
        }

        // antenna
        d.setColor(new Color(53, 57, 53));
        d.fillRectangle(86, 375, 33, 75);
        d.setColor(new Color(71, 77, 71));
        d.fillRectangle(97, 215, 11, 160);

        d.setColor(new Color(255, 217, 178));
        d.fillCircle(102, 202, 13);
        d.setColor(new Color(245, 72, 72));
        d.fillCircle(102, 202, 8);
        d.setColor(Color.WHITE);
        d.fillCircle(102, 202, 3);
    }

    @Override
    public void timePassed() {
        return;
    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

}
