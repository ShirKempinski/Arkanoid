import biuoop.DrawSurface;
/**
 * Animation.
 */
public interface Animation {

    /**
     * doOneFrame(DrawSurface) is in charge of the logic.
     * @param d the draeSurface
     * @param dt - specifies the amount of seconds passed since the last call.
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * shouldStop() is in charge of stopping condition.
     * @return true or false
     */
    boolean shouldStop();
}
