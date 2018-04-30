import java.awt.Color;

import biuoop.DrawSurface;

/**
 * Rainbow level.
 */
public class FinalFourBackground implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        // background
        d.setColor(new Color(39, 109, 249));
        d.fillRectangle(0, 0, 800, 600);

        // rain 1
        d.setColor(new Color(208, 210, 212));
        for (int i = 0; i < 10; i++) {
            d.drawLine(105 + i * 10, 477, 90 + i * 10, 600);
        }

        // cloud 1
        d.setColor(new Color(208, 210, 212));
        d.fillCircle(130, 470, 25);
        d.fillCircle(115, 450, 20);
        d.setColor(new Color(192, 192, 192));
        d.fillCircle(150, 445, 25);
        d.setColor(new Color(171, 171, 171));
        d.fillCircle(180, 455, 27);
        d.fillCircle(160, 477, 20);

        // rain 2
        d.setColor(new Color(208, 210, 212));
        for (int i = 0; i < 10; i++) {
            d.drawLine(560 + i * 10, 655, 625 + i * 10, 532);
        }

        // cloud 2
        d.setColor(new Color(208, 210, 212));
        d.fillCircle(650, 525, 25);
        d.fillCircle(635, 505, 20);
        d.setColor(new Color(192, 192, 192));
        d.fillCircle(670, 500, 25);
        d.setColor(new Color(171, 171, 171));
        d.fillCircle(700, 510, 27);
        d.fillCircle(680, 530, 20);
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