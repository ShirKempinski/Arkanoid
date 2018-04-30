import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * End screen.
 */
public class EndScreen implements Animation {

    private KeyboardSensor keyboard;
    private boolean stop;
    private boolean win;
    private Sprite winScreen;
    private Sprite loseScreen;

    /**
     * constructor.
     * @param k - the keyboard sensor.
     * @param win - boolean that indicate if the player win or lose.
     * @param finalScore - the player''s score.
     */
    public EndScreen(KeyboardSensor k, boolean win, int finalScore) {
       this.keyboard = k;
       this.win = win;
       this.stop = false;
       this.winScreen = new WinScreen(finalScore);
       this.loseScreen = new LoseScreen(finalScore);
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        if (this.win) {
            this.winScreen.drawOn(d);
        } else {
            this.loseScreen.drawOn(d);
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}