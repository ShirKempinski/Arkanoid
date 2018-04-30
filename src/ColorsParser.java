import java.awt.Color;

/**
 * creates color from string.
 */
public class ColorsParser {
    /**
     * parse color definition and return the specified color.
     * @param s string
     * @return the color.
     */
    public static Color colorFromString(String s) {
        if (s.contains("RGB")) {
            s = s.substring(s.indexOf("(") + 1);
            String[] rgb = s.split(",");
            int r = Integer.valueOf(rgb[0]);
            int g = Integer.valueOf(rgb[1]);
            int b = Integer.valueOf(rgb[2]);
            return new Color(r, g, b);
        } else if (s.equals("red")) {
            return Color.RED;
        } else if (s.equals("black")) {
            return Color.BLACK;
        } else if (s.equals("blue")) {
            return Color.BLUE;
        } else if (s.equals("cyan")) {
            return Color.CYAN;
        } else if (s.equals("gray")) {
            return Color.GRAY;
        } else if (s.equals("lightGray")) {
            return Color.LIGHT_GRAY;
        } else if (s.equals("green")) {
            return Color.GREEN;
        } else if (s.equals("orange")) {
            return Color.ORANGE;
        } else if (s.equals("pink")) {
            return Color.PINK;
        } else if (s.equals("white")) {
            return Color.WHITE;
        } else if (s.equals("yellow")) {
            return Color.YELLOW;
        } else {
            return null;
        }
    }
}
