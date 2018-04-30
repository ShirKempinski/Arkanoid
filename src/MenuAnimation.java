import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * MenuAnimation.
 * @param <T> type.
 */
public class MenuAnimation<T> implements Menu<T> {

    private List<Selections<T>> selections;
    private T status;
    private KeyboardSensor sensor;
    private boolean stop;
    private AnimationRunner runner;

    /**
     * Constructor.
     * @param s - sensor.
     * @param runner - runner
     */
    public MenuAnimation(KeyboardSensor s, AnimationRunner runner) {
        this.sensor = s;
        this.selections = new ArrayList<>();
        this.stop = false;
        this.status = null;
        this.runner = runner;
    }

    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.selections.add(new Selections(key, message, returnVal));
    }

    @Override
    public T getStatus() {
        for (Selections s : this.selections) {
            if (this.sensor.isPressed(s.getKey())) {
                if (s.getSubMenu() != null) {
                    this.runner.run(s.getSubMenu());
                    this.status = (T) s.getSubMenu().getStatus();
                    break;
                } else {
                    this.status = (T) s.getReturnValue();
                    break;
                }
            }
        }
        this.stop = false;
        return this.status;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.WHITE);
        d.drawText(200, 150, "Arkanoid", 100);

        for (int i = 0; i < this.selections.size(); i++) {
            d.drawText(140, 240 + i * 100, this.selections.get(i).getKey(), 50);
            d.drawText(280, 240 + i * 100, this.selections.get(i).getMessage(), 50);
        }
        this.status = null;
    }

    @Override
    public boolean shouldStop() {
        for (Selections s: this.selections) {
            if (this.sensor.isPressed(s.getKey())) {
                this.stop = true;
            }
        }
        return this.stop;
    }

    @Override
    public void addSubMenu(String key, String message, Menu<T> submenu) {
        this.selections.add(new Selections(key, message, submenu));
    }
}