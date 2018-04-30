import java.awt.Color;

import biuoop.DrawSurface;
/**
 * HighScoresAnimation.
 */
public class HighScoresAnimation implements Animation {

    private HighScoresTable scoresTable;
    private boolean stop;

    /**
     * constructor.
     * @param scores - the scores table.
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.scoresTable = scores;
        this.stop = false;
    }


    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(new Color(70, 140, 200));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLACK);
        d.drawText(60, 150, "HIGH SCORES", 100);
        d.drawText(240, 200, "Index", 20);
        d.drawText(380, 200, "NAME", 20);
        d.drawText(510, 200, "SCORE", 20);

        for (int i = 0; i < this.scoresTable.getHighScores().size(); i++) {
            d.drawLine(240, 210 + i * 30, 560, 210 + i * 30);
            d.setColor(Color.BLACK);
            d.drawText(240, 230 + i * 30, Integer.toString(i + 1), 14);
            d.drawText(380, 230 + i * 30, this.scoresTable.getHighScores().get(i).getName(), 14);
            d.drawText(510, 230 + i * 30, Integer.toString(this.scoresTable.getHighScores().get(i).getScore()), 14);
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}