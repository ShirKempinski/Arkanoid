import java.awt.Color;

import biuoop.DrawSurface;

/**
 * The background of a target.
 */
public class DirectHitBackground implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        //background
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);

        // lines
        d.setColor(Color.BLUE);
        d.drawLine(400, 48, 400, 148);
        d.drawLine(400, 172, 400, 272);
        d.drawLine(382, 160, 282, 160);
        d.drawLine(412, 160, 512, 160);

        // circles
        d.drawCircle(400, 160, 30);
        d.drawCircle(400, 160, 60);
        d.drawCircle(400, 160, 90);
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
