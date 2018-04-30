import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Created by Shir on 6/20/2017.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String endKey;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * constructor.
     * @param sensor - the keyboardSensor.
     * @param key - the stop key.
     * @param animation - the current animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.endKey = key;
        this.stop = false;
        this.animation = animation;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
            this.animation.doOneFrame(d, dt);
            if (this.sensor.isPressed(this.endKey) && !this.isAlreadyPressed) {
                this.stop = true;
            } else if (this.sensor.isPressed(this.endKey)) {
                this.isAlreadyPressed = false;
                this.stop = true;
            }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
 }
