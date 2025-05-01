package org.ascii_paint.utils.painting;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Class for getting image from its path
 * */

public class ImageLoader {
    private String pathToFile;

    /// Methods /////////////////////////////////////////////////////////
    public BufferedImage load() {
        try {
            return ImageIO.read(new File(pathToFile));
        } catch (IOException e) {
            System.out.println("Bad image");
            throw new RuntimeException(e);
        }
    }
    /// Methods /////////////////////////////////////////////////////////

    /// Constructors ////////////////////////////////////////////////////
    public ImageLoader(String pathToFile) {
        this.pathToFile = pathToFile;
    }
    /// Constructors ////////////////////////////////////////////////////
}
