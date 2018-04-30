import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * PauseScreen display a screen with a "paused" message until a key is pressed.
 */
public class PauseScreen implements Animation {

    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * constructor.
     * @param k - the keyboard sensor.
     */
    public PauseScreen(KeyboardSensor k) {
       this.keyboard = k;
       this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(160, d.getHeight() / 2 - 30, "PAUSED", 120);
        d.drawText(100, d.getHeight() / 2 + 80, "press space to continue", 60);

        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}