import biuoop.DrawSurface;
/**
 * Animation.
 */
public interface Animation {

    /**
     * doOneFrame(DrawSurface) is in charge of the logic.
     * @param d the draeSurface
     */
    void doOneFrame(DrawSurface d);

    /**
     * shouldStop() is in charge of stopping condition.
     * @return true or false
     */
    boolean shouldStop();
}
