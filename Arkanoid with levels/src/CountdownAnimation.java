import java.awt.Color;

import biuoop.DrawSurface;

/**
 * on-screen count-down from 3 to 1, which will show up at the beginning of each turn.
 */
public class CountdownAnimation implements Animation {

    private double numOfSecondes;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private double startTime;

    /**
     * constructor.
     * @param numOfSeconds to wait till the game begins
     * @param countFrom - number to start the counting from
     * @param gameScreen - the screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSecondes = numOfSeconds * 1000 / countFrom;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        gameScreen.drawAllOn(d);
        d.setColor(Color.WHITE);
        if (this.countFrom <= 0) {
            this.stop = true;
        }
        if (System.currentTimeMillis() <= this.startTime + this.numOfSecondes) {
            d.setColor(Color.WHITE);
            d.drawText(376, 300, Integer.toString(this.countFrom), 100);
            d.drawText(376, 299, Integer.toString(this.countFrom), 100);
            d.drawText(374, 299, Integer.toString(this.countFrom), 100);
            d.drawText(374, 301, Integer.toString(this.countFrom), 100);
            d.setColor(Color.BLACK);
            d.drawText(375, 300, Integer.toString(this.countFrom), 100);

        } else {
            this.countFrom--;
            this.startTime = System.currentTimeMillis();
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}