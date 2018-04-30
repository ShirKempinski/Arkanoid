import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * LevelSpecificationReader.
 */
public class LevelSpecificationReader {

    private List<LevelInformation> levels;
    private List<Block> blocks = new ArrayList<Block>();

    /**
     * gets a file name and returns a list of LevelInformation objects.
     * @param reader reader
     * @return a list of LevelInformation objects.
     */
    public List<LevelInformation> fromReader(InputStream reader) {
        this.levels = new ArrayList<LevelInformation>();
        ArrayList<String> lines = splitToLines(reader);
        String line = lines.get(0);
        List<Velocity> initialBallVelocities = null;
        int paddleSpeed = 0;
        int paddleWidth = 0;
        int numberOfBlocksToRemove = 0;
        int xStartBlocks = 0;
        int yStartBlocks = 0;
        int rowHeight = 0;
        String levelName = null;
        Sprite background = null;
        BlocksFromSymbolsFactory factory = null;
        for (int i = 0; i < lines.size(); i++) {
            while (!line.equals("START_BLOCKS")) {
                if (line.equals("END_LEVEL")) {
                    break;
                }
                line = lines.get(i);
                if (line.equals("START_LEVEL") || line.startsWith("#") || line.equals("")) {
                    i++;
                    continue;
                }

                if (line.contains("level_name")) {
                    levelName = getVal(line, "level_name");
                }
                if (line.contains("paddle_speed")) {
                    paddleSpeed = Integer.valueOf(getVal(line, "paddle_speed"));
                }
                if (line.contains("paddle_width")) {
                    paddleWidth = Integer.valueOf(getVal(line, "paddle_width"));
                }
                if (line.contains("num_blocks")) {
                    numberOfBlocksToRemove = Integer.valueOf(getVal(line, "num_blocks"));
                }
                if (line.contains("blocks_start_x")) {
                    xStartBlocks = Integer.valueOf(getVal(line, "blocks_start_x"));
                }
                if (line.contains("blocks_start_y")) {
                    yStartBlocks = Integer.valueOf(getVal(line, "blocks_start_y"));
                }
                if (line.contains("row_height")) {
                    rowHeight = Integer.valueOf(getVal(line, "row_height"));
                }
                if (line.contains("ball_velocities")) {
                    initialBallVelocities = velocitiesFromStr(getVal(line, "ball_velocities"));
                }
                if (line.contains("background")) {
                    background = createBackground(getVal(line, "background"));
                }
                if (line.contains("block_definitions")) {
                    factory = BlocksDefinitionReader
                            .fromReader(ClassLoader.getSystemClassLoader().getResourceAsStream(
                                    getVal(line, "block_definitions")));
                }
                i++;
            }
            // read blocks information
            if (line.contains("END_LEVEL")) {
                Level level = new Level(initialBallVelocities, paddleSpeed, paddleWidth, levelName, background,
                        this.blocks, numberOfBlocksToRemove);
                this.levels.add(level);
                this.blocks = new ArrayList<Block>();
                if (i < lines.size() - 1) {
                    line = lines.get(i);
                }
                continue;
            }

            line = lines.get(i);
            while (!line.contains("END_BLOCKS")) {
                int x = xStartBlocks;

                for (int j = 0; j < line.length(); j++) {
                    if (factory.isSpaceSymbol(Character.toString(line.charAt(j)))) {
                        x += factory.getSpaceWidth(Character.toString(line.charAt(j)));
                    } else if (factory.isBlockSymbol(Character.toString(line.charAt(j)))) {
                        Block b = factory.getBlock(Character.toString(line.charAt(j)), xStartBlocks, yStartBlocks);
                        int width = b.getWidth();
                        this.blocks.add(factory.getBlock(Character.toString(line.charAt(j)), x, yStartBlocks));
                        x += width;
                    }
                }

                yStartBlocks += rowHeight;
                i++;
                line = lines.get(i);
            }
            line = lines.get(i);
        }
        return this.levels;
    }

    /**
     * Split the given stream to lines.
     * @param reader inputStream
     * @return array of the lines.
     */
    public static ArrayList<String> splitToLines(InputStream reader) {
        BufferedReader br = new BufferedReader(new InputStreamReader(reader));

        ArrayList<String> lines = new ArrayList<String>();
        String line;
        try {
            line = br.readLine();
            while (line != null) {
                lines.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    /**
     * get a line and a key and return the value that folows.
     * @param line - the line.
     * @param key - the key
     * @return the value that folows the key.
     */
    public static String getVal(String line, String key) {
        if (!line.contains(key)) {
            return null;
        }

        int startKeyIndex = line.indexOf(key);
        int endKeyIndex = startKeyIndex + key.length();

        if (key.equals("ball_velocities") || key.equals("level_name")) {
            return line.substring(endKeyIndex + 1);
        }

        int endOfValIndex = line.indexOf(" ", endKeyIndex + 1);
        if (endOfValIndex == -1) {
            endOfValIndex = line.length();
        }
        return line.substring(endKeyIndex + 1, endOfValIndex);
    }

    /**
     * velocitiesFromStr.
     * @param str - the string of velocities.
     * @return the list of velocities.
     */
    public ArrayList<Velocity> velocitiesFromStr(String str) {
        ArrayList<Velocity> velocities = new ArrayList<Velocity>();
        String[] splited = str.split(" ");
        for (int i = 0; i < splited.length; i++) {
            String[] vel = splited[i].split(",");
            velocities.add(Velocity.fromAngleAndSpeed(Integer.valueOf(vel[0]), Integer.valueOf(vel[1])));
        }
        return velocities;
    }

    /**
     * CreateBackground.
     * @param str - the string with the bg type and description.
     * @return the background
     */
    public Sprite createBackground(String str) {
        String type = str.substring(0, str.indexOf("("));
        String background = str.substring(str.indexOf("(") + 1, str.indexOf(")"));
        if (type.equals("color")) {
            return new BackgroundColor(ColorsParser.colorFromString(background));
        } else {
            return new BackgroundImage(background);
        }
    }
}