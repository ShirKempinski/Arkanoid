import java.awt.Color;
import java.util.Map;

/**
 * BlockFromSymbol.
 */
public class BlockFromSymbol implements BlockCreator {

    private int height;
    private int blockWidth;
    private int hitPoints;
    private Map<Integer, Fill> fills;
    private Color stroke;

    /**
     * BlockFromSymbol.
     * @param height - h
     * @param blockWidth -w
     * @param hitPoints - hits
     * @param fills - fills
     * @param stroke - s
     */
    public BlockFromSymbol(int height, int blockWidth, int hitPoints, Map<Integer, Fill> fills, Color stroke) {
        this.height = height;
        this.blockWidth = blockWidth;
        this.hitPoints = hitPoints;
        this.fills = fills;
        this.stroke = stroke;
    }

    @Override
    public Block create(int xpos, int ypos) {
        Rectangle rect = new Rectangle(new Point(xpos, ypos), this.blockWidth, this.height);
        return new Block(rect, this.hitPoints, this.stroke, this.fills);
    }

}
