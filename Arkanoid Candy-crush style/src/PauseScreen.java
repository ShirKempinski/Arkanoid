import biuoop.DrawSurface;

/**
 * PauseScreen display a screen with a "paused" message until a key is pressed.
 */
public class PauseScreen implements Animation {

    private boolean stop;

    /**
     * constructor.
     */
    public PauseScreen() {
       this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawText(160, d.getHeight() / 2 - 30, "PAUSED", 120);
        d.drawText(100, d.getHeight() / 2 + 80, "press space to continue", 60);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}