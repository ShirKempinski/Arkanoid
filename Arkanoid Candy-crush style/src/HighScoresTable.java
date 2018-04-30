import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * an empty high-scores table with the specified maxSize.
 */
public class HighScoresTable implements Serializable {
    private int maxSize;
    private ArrayList<ScoreInfo> table;

    /**
     * an empty high-scores table with the specified maxSize.
     * @param maxSize - the maximal amount of scores in the table
     */
    public HighScoresTable(int maxSize) {
        this.maxSize = maxSize;
        this.table = new ArrayList<ScoreInfo>();
    }

    /**
     * constructor with default maxSize.
     */
    public HighScoresTable() {
        this.maxSize = 10;
        this.table = new ArrayList<ScoreInfo>();
    }

    /**
     * add a high-score.
     * @param score the score.
     */
    public void add(ScoreInfo score) {
        if (getRank(score.getScore()) <= this.maxSize) {
            this.table.add(score);
            Collections.sort(this.table);
            if (this.table.size() > this.maxSize) {
                this.table.remove(this.table.size() - 1);
            }
        } else {
            this.table.add(score);
        }
    }

    /**
     * maxSize().
     * @return table maxSize.
     */
    public int size() {
        return this.maxSize;
    }

    /**
     * The list is sorted such that the highest scores come first.
     * @return the table of the current high scores.
     */
    public List<ScoreInfo> getHighScores() {
        return this.table;
    }

    /**
     * the player's rank.
     * @param score - the current player's score
     * @return the rank of the score if this score would go in the table.
     */
    public int getRank(int score) {
        for (int i = 0; i < this.table.size(); i++) {
            if (score > this.table.get(i).getScore()) {
                return i + 1;
            }
        }
        if (this.table.size() >= this.size()) {
            return this.table.size();
        }
        return this.size() - 1;
    }

    /**
     * Clears the table.
     */
    public void clear() {
        this.table = new ArrayList<ScoreInfo>(this.maxSize);
    }

    /**
     * Load table data from file. current table data is cleared.
     * @param filename - the loaded file
     * @throws IOException -  if the class is not found.
     *                        if the file is not found, load() will create a new empty table.
     */
    public void load(File filename) throws IOException {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(filename));
            this.table = (ArrayList<ScoreInfo>) ois.readObject();
        } catch (FileNotFoundException e) {
            HighScoresTable newEemptyTable = new HighScoresTable(this.maxSize);
            newEemptyTable.save(filename);
            this.table = newEemptyTable.table;
            this.maxSize = newEemptyTable.maxSize;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename.getName());
            }
        }
    }

    /**
     * Save table data to the specified file.
     * @param filename - the file path
     * @throws IOException - if the file is not found.
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(this.table);

        } catch (FileNotFoundException e) {
            System.err.println("Unable to find file: " + filename.getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename.getName());
            }
        }
    }

    /**
     * Read a table from file.
     * @param filename - the file.
     * @return the table.
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable table = new HighScoresTable();
        try {
            table.load(filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }
}