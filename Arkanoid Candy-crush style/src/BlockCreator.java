/**
 * BlockCreator.
 */
public interface BlockCreator {

    /**
     * Create a block at the specified location.
     * @param xpos left x of the block
     * @param ypos upper y of the block
     * @return the new block.
     */
    Block create(int xpos, int ypos);
}
