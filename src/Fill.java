import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * Color or image.
 */
public class Fill {

    private Color color;
    private BufferedImage image;

    /**
     * FillColor construtor.
     * @param color color.
     */
    public Fill(Color color) {
        this.color = color;
        this.image = null;
    }

    /**
     * FillImage constructor.
     * @param filename filename.
     */
    public Fill(String filename) {
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(filename);
        try {
            this.image = ImageIO.read(is);
        } catch (IOException e) {
          System.out.println("Error: " + e);
        }
        this.color = null;
    }

    /**
     * getColor.
     * @return the color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * getImage.
     * @return the image.
     */
    public BufferedImage getImage() {
        return this.image;
    }
}
