import java.util.Map;
import java.util.Set;

/**
 * a mechanism (object) with a method that will get a symbol and create the desired block.
 */
public class BlocksFromSymbolsFactory {

    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * BlocksFromSymbolsFactory.
     * @param spacerWidths spacers
     * @param blockCreators block creators
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> spacerWidths, Map<String, BlockCreator> blockCreators) {
        this.spacerWidths = spacerWidths;
        this.blockCreators = blockCreators;
    }

    /**
     * isSpaceSymbol.
     * @param s the symbol
     * @return true if 's' is a valid space symbol.
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.keySet().contains(s);
    }

    /**
     * isBlockSymbol.
     * @param s the symbol
     * @return true if 's' is a valid block symbol.
     */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.keySet().contains(s);
    }

    /**
     * getBlock.
     * @param s - the block's symbol
     * @param xpos - left x of the block
     * @param ypos - up y of the block
     * @return the requested block.
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreators.get(s).create(xpos, ypos);
    }

    /**
     * getSpaceWidth.
     * @param s the key to the width.
     * @return the width in pixels associated with the given spacer-symbol.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }

    /**
     * getSpacers.
     * @return the spacers.
     */
    public Set<String> getSpacers() {
        return this.spacerWidths.keySet();
    }
}