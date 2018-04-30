import java.util.ArrayList;

import biuoop.DrawSurface;

/**
 * SpriteCollection hold a collection of sprites.
 */
public class SpriteCollection {
    private ArrayList<Sprite> sprites;

    /**
     * constractor.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * Add the given Sprite to the collection.
     *
     * @param s - the sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        ArrayList<Sprite> spts = new ArrayList<Sprite>(this.sprites);
        for (Sprite s : spts) {
            s.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d - the drawSurface
     */
    public void drawAllOn(DrawSurface d) {
        ArrayList<Sprite> spts = new ArrayList<Sprite>(this.sprites);
        for (Sprite s : spts) {
            s.drawOn(d);
        }
    }

    /**
     * getSprites.
     * @return the sprites.
     */
    public ArrayList<Sprite> getSprites() {
        return this.sprites;
    }
}
