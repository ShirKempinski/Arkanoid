import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import biuoop.DrawSurface;

/**
 * BackgroundImage.
 */
public class BackgroundImage implements Sprite {

    private BufferedImage image;

    /**
     * constructor.
     * @param filename - the image's path.
     */
    public BackgroundImage(String filename) {
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(filename);
        try {
            this.image = ImageIO.read(is);
        } catch (IOException e) {
          System.out.println("Error: " + e);
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage(0, 0, this.image);
    }

    @Override
    public void timePassed(double dt) {
        return;
    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}