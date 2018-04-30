import java.awt.Color;

import biuoop.DrawSurface;
/**
 * The background of the sun.
 */
public class WideEasyBackground implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        // background
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, 800, 600);

        // the sun
        d.setColor(new Color(255, 217, 178));
        for (int i = 0; i < 150; i++) {
            d.drawLine(150, 150, 20 + 5 * i, 300);
        }
        d.fillCircle(150, 150, 60);
        d.setColor(new Color(217, 220, 105));
        d.fillCircle(150, 150, 50);
        d.setColor(new Color(255, 236, 51));
        d.fillCircle(150, 150, 40);
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
