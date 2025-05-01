package org.ascii_paint.utils.painting;

import java.awt.image.BufferedImage;

/***
 * class Converter - основная логика по преобразованию
 */

public class Converter {
    private Detail        quality;
    private BufferedImage image;
    private int           width, height;
    private String        size;

    /// Methods /////////////////////////////////////////////////////////
    /// Method for converting image into ASCII-characters
    public char[][] convertToText() {
        char[] pallet = Pallet.getPallet(quality);
        char[][] converted = new char[height][width];

        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        resized.getGraphics().drawImage(image, 0, 0, width, height, null);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int brightness = 255 - (resized.getRGB(x, y) & 0xFF);
                int index = (int) (brightness / 255.0 * (pallet.length - 1));
                converted[y][x] = pallet[index];
            }
        }

        size = String.format("%dx%d", height, width);

        return converted;
    }

    /// GETTERS //////////////////////////////////////////
    public String getSize() {
        return size;
    }

    public Detail getQuality() {
        return quality;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    /// GETTERS //////////////////////////////////////////

    /// SETTERS //////////////////////////////////////////
    public void setQuality(Detail quality) {
        this.quality = quality;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setSize(String size) {
        this.size = size;
    }
    /// SETTERS //////////////////////////////////////////

    /// Methods /////////////////////////////////////////////////////////

    /// Constructors ////////////////////////////////////////////////////
    public Converter(BufferedImage image, Detail quality, int width) {
        this.quality = quality;
        this.image = image;
        this.width = width;
        height = (int) (image.getHeight() * (width / (double) image.getWidth()) * 0.5);
    }
    /// Constructors ////////////////////////////////////////////////////
}
