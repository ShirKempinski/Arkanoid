import biuoop.GUI;

/**
 * Quit task.
 */
public class QuitTask implements Task<Void> {

    private GUI gui;

    /**
     * constructor.
     * @param gui - gui.
     */
    public QuitTask(GUI gui) {
        this.gui = gui;
    }

    @Override
    public Void run() {
        this.gui.close();
        return null;
    }
}
