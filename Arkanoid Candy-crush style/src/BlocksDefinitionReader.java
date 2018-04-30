import java.awt.Color;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * BlocksDefinitionReader.
 */
public class BlocksDefinitionReader {
    /**
     * BlocksFromSymbolsFactory.
     * @param reader reader.
     * @return factory
     */
    public static BlocksFromSymbolsFactory fromReader(InputStream reader) {
        ArrayList<String> lines = LevelSpecificationReader.splitToLines(reader);
        Map<String, Integer> spaceWidths = new HashMap<String, Integer>();
        Map<String, BlockCreator> blocksTypes = new HashMap<String, BlockCreator>();
        Map<String, Object> def = new HashMap<String, Object>();
        Map<String, Object> blockParams = new HashMap<String, Object>();


        Map<Integer, Fill> defOneBlockFills = new HashMap<Integer, Fill>();
        Map<Integer, Fill> oneBlockFills = new HashMap<Integer, Fill>();

        for (String line : lines) {

            if (line.startsWith("#") || line.equals("")) {
                continue;
            }
            if (line.contains("default")) {
                if (line.contains("symbol")) {
                    def.put("symbol", LevelSpecificationReader.getVal(line, "symbol"));
                    //defBlockSymbol = LevelSpecificationReader.getVal(line, "symbol");
                }
                if (line.contains("height")) {
                    //defHeight = Integer.parseInt(LevelSpecificationReader.getVal(line, "height"));
                    def.put("height", Integer.parseInt(LevelSpecificationReader.getVal(line, "height")));
                }
                if (line.contains("width")) {
                    //defBlockWidth = Integer.parseInt(LevelSpecificationReader.getVal(line, "width"));
                    def.put("width", Integer.parseInt(LevelSpecificationReader.getVal(line, "width")));
                }
                if (line.contains("hit_points")) {
                    //defHitPoints = Integer.parseInt(LevelSpecificationReader.getVal(line, "hit_points"));
                    def.put("hit_points", Integer.parseInt(LevelSpecificationReader.getVal(line, "hit_points")));
                }
                if (line.contains("fill")) {
                    int endOfValIndex = line.indexOf(" ", line.indexOf("fill"));
                    createFill(line.substring(line.indexOf("fill"), endOfValIndex), defOneBlockFills,
                            (int) blockParams.get("hit_Points"));
                }
                if (line.contains("stroke")) {
                    //defStroke = createStroke(LevelSpecificationReader.getVal(line, "stroke"));
                    def.put("stroke", createStroke(LevelSpecificationReader.getVal(line, "stroke")));
                }
            }
            if (line.startsWith("bdef")) {

                blockParams = new HashMap<String, Object>();
                blockParams.putAll(def);
                oneBlockFills = new HashMap<Integer, Fill>();
                oneBlockFills.putAll(defOneBlockFills);

                if (line.contains("symbol")) {
                    //blockSymbol = LevelSpecificationReader.getVal(line, "symbol");
                    blockParams.put("symbol", LevelSpecificationReader.getVal(line, "symbol"));
                }
                if (line.contains("height")) {
                    //height = Integer.parseInt(LevelSpecificationReader.getVal(line, "height"));
                    blockParams.put("height", Integer.parseInt(LevelSpecificationReader.getVal(line, "height")));
                }
                if (line.contains("width")) {
                    //blockWidth = Integer.parseInt(LevelSpecificationReader.getVal(line, "width"));
                    blockParams.put("width", Integer.parseInt(LevelSpecificationReader.getVal(line, "width")));
                }
                if (line.contains("hit_points")) {
                    //hitPoints = Integer.parseInt(LevelSpecificationReader.getVal(line, "hit_points"));
                    blockParams.put("hit_points", Integer.parseInt(
                            LevelSpecificationReader.getVal(line, "hit_points")));
                }
                if (line.contains("fill")) {
                    int i = line.indexOf("fill");
                    while (i < line.length() && i != -1) {
                    int endOfValIndex = line.indexOf(" ", i);
                    if (endOfValIndex == -1) {
                        endOfValIndex = line.length();
                    }
                    createFill(line.substring(i, endOfValIndex), oneBlockFills, (int) blockParams.get("hit_points"));
                    i = line.indexOf("fill", i + 5);
                    }
                }
                if (line.contains("stroke")) {
                    //stroke = createStroke(LevelSpecificationReader.getVal(line, "stroke"));
                    blockParams.put("stroke", createStroke(LevelSpecificationReader.getVal(line, "stroke")));
                }
                BlockFromSymbol b = new BlockFromSymbol((int) blockParams.get("height"),
                        (int) blockParams.get("width"), (int) blockParams.get("hit_points"), oneBlockFills,
                        (Color) blockParams.get("stroke"));
                blocksTypes.put((String) blockParams.get("symbol"), b);
            }
            if (line.startsWith("sdef")) {
                String spaceSymbol;
                int spaceWidth;
                if (line.contains("symbol")) {
                    spaceSymbol = LevelSpecificationReader.getVal(line, "symbol");
                    spaceWidth = Integer.parseInt(LevelSpecificationReader.getVal(line, "width"));
                    spaceWidths.put(spaceSymbol, spaceWidth);
                }
            }
        }
        return new BlocksFromSymbolsFactory(spaceWidths, blocksTypes);
    }

    /**
     * CreateFill.
     * @param str - the string with the fill type and description.
     * @param oneBlockFills - map of the fills
     * @param hitPoints - hits.
     */
    public static void createFill(String str, Map<Integer, Fill> oneBlockFills, int hitPoints) {
        String[] s = str.split(":");
        String fillType = s[0];
        String type = s[1].substring(0, s[1].indexOf("("));
        String f = str.substring(str.indexOf("(") + 1, str.indexOf(")"));
        Fill fill;
        if (type.equals("color")) {
            fill = new Fill(ColorsParser.colorFromString(f));
        } else {
            fill = new Fill(f);
        }
        if (fillType.contains("-")) {
            Integer k = Character.getNumericValue(fillType.charAt(5));
            oneBlockFills.put(k, fill);
        } else {
            for (int i = 1; i <= hitPoints; i++) {
                oneBlockFills.put(i, fill);
            }
        }
    }

    /**
     * CreateStroke.
     * @param str string.
     * @return color.
     */
    public static Color createStroke(String str) {
        String color = str.substring(str.indexOf("(") + 1, str.indexOf(")"));
        return ColorsParser.colorFromString(color);
    }
}