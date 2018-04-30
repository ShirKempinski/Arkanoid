/**
 * LevelsSet.
 */
public class LevelsSet {
    private String key;
    private String name;
    private String path;

    /**
     * constructor.
     * @param key - key
     * @param name - name
     * @param path - path
     */
    public LevelsSet(String key, String name, String path) {
        this.name = name;
        this.path = path;
    }

    /**
     * getKey.
     * @return key
     */
    public String getKey() {
        return this.key;
    }

    /**
     * getName.
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * getPath.
     * @return path
     */
    public String getPath() {
        return this.path;
    }
}
