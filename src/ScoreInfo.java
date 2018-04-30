import java.io.Serializable;

/**
 * information of the player's name and score.
 */
public class ScoreInfo implements Serializable, Comparable {
    private String name;
    private int score;

    /**
     * constructor.
     * @param name - the playe's name
     * @param score - the score.
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * getName.
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * getScore.
     * @return the score
     */
    public int getScore() {
        return this.score;
    }

    @Override
    public int compareTo(Object comparation) {
       return (((ScoreInfo) comparation).getScore()) - this.score;
    }
}
